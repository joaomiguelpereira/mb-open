package functional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import json.JSONUtils;
import json.JsonObjectWrapper;

import models.User;

import org.junit.Ignore;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import play.Logger;
import play.data.validation.Error;
import play.data.validation.Validation;
import play.db.jpa.Model;
import play.i18n.Messages;
import play.mvc.Http;
import play.mvc.Http.Response;
import play.mvc.Router;
import play.mvc.Router.ActionDefinition;
import play.mvc.Scope;
import play.mvc.Scope.Flash;
import play.test.FunctionalTest;
import constants.SessionValuesConstants;
import controllers.Users;

@Ignore
public class ApplicationFunctionalTest extends FunctionalTest {

	private Class controllerClass;
	private String methodName;
	private Map<String, Object> args = new HashMap<String, Object>();
	private Class[] argTypes;

	protected void assertI18nHtmlTitlePresent(Response response, String key,
			Object... args) {
		assertTrue(response.out.toString().contains(Messages.get(key, args)));

	}

	protected void assertJsonValidationError(JsonObjectWrapper jsObj,
			String i18n) {

		String error = jsObj.getStringProperty(JSONUtils.ERROR_MESSAGE_PROP);
		assertNotNull(error);
		assertEquals(error, Messages.get(i18n));

	}

	protected void assertNoJsonValidationErrors(JsonObjectWrapper errosJsObj,
			String propName) {
		// check errors on name
		List<String> errorsList = errosJsObj.getStringArrayProperty(propName);
		assertNull(errorsList);
	}

	protected void assertJsonValidationErrors(JsonObjectWrapper errosJsObj,
			String propName, String[] expectedi18nErrorsArray) {

		// check errors on name
		List<String> errorsList = errosJsObj.getStringArrayProperty(propName);
		assertNotNull(errorsList);
		assertEquals(expectedi18nErrorsArray.length, errorsList.size());

		for (String expectedMessage : expectedi18nErrorsArray) {

			assertTrue(errorsList.contains(Messages.get(expectedMessage)));
		}

	}

	protected <T extends Model> void assertBindedModel(String key, T model) {

		T bindedModel = (T) Scope.RenderArgs.current().data.get(key);
		assertEquals(model.id, bindedModel.id);
	}

	protected void assertBindingExists(String key) {
		assertTrue(Scope.RenderArgs.current().data.containsKey(key));
		assertNotNull(Scope.RenderArgs.current().data.get(key));

	}

	protected void assertNoBindingExists(String key) {
		assertFalse(Scope.RenderArgs.current().data.containsKey(key));
	}

	protected void showValidationErrors() {

		Map<String, List<play.data.validation.Error>> errors = Validation
				.current().errorsMap();

		for (List<play.data.validation.Error> theErrors : errors.values()) {
			for (Error error : theErrors) {
				Logger.debug("Error on " + error.getKey() + ":"
						+ error.message());
			}
		}

	}

	/**
	 * Call FunctionalTest.GET
	 * 
	 * @return
	 */
	protected Response get() {
		return GET(calculateRouteURL());
	}

	/**
	 * 
	 * @param response
	 *            response
	 * @param expectedUrl
	 *            The expected URL
	 * @param expectedUrlparams
	 *            The params to construct the expected URL. Must have even
	 * 
	 */
	protected void assertRedirectedTo(Response response, String expectedUrl,
			Map<String, Object> expectedUrlparams) {
		ActionDefinition ad = Router.reverse(expectedUrl, expectedUrlparams);
		ad.absolute();
		String currentUrl = response.current().getHeader("Location");
		assertEquals(ad.url, currentUrl);
	}

	protected void logoutCurrentUser() {
		if (Scope.Session.current() != null) {
			Scope.Session.current().clear();
		}

		if (Flash.current() != null) {
			Flash.current().clear();
		}

	}

	protected void authenticateUser(String email, String password,
			boolean keepLogged) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);
		params.put("password", password);
		params.put("keepLogged", keepLogged);
		Http.Response response = POST(Router.reverse("Users.authenticate",
				params));
		assertSuccessFlashed("login.successful");
		User user = User.find("byEmail", email).first();
		assertEquals(Long.valueOf(1L), user.getLoginInformation()
				.getSuccessfulLoginCount());
		assertEquals(
				Scope.Session.current().get(SessionValuesConstants.LOGIN_EMAIL),
				email);
		assertEquals(
				Scope.Session.current().get(SessionValuesConstants.LOGIN_TOKEN),
				user.getLoginInformation().getLoginToken());
		Flash.current().clear();

	}

	protected void assertJSONResponse(Response response, String json) {
		// Assert is JSON Response
		assertTrue(response.contentType.contains("application/json"));
		assertTrue(response.out.toString().contains(json));

	}

	protected void assertJSONSuccess(Response response, String i18n) {
		Map<String, String> expectedMap = new HashMap<String, String>();
		expectedMap.put(JSONUtils.ERROR_MESSAGE_PROP, Messages.get(i18n));
		assertJSONResponse(response, new Gson().toJson(expectedMap));

	}
	

	protected void assertNoJSONError(Response response) {
		// Assert is JSON Response
		assertTrue(response.contentType.contains("application/json"));
		//convert thhe response out to obj
		Logger.debug("Cheching if erros exist in response: "+response.out.toString());
		JsonElement jsElement = new JsonParser().parse(response.out.toString());
		if ( jsElement.isJsonObject() ) {
			JsonObjectWrapper jsWrapper = new JsonObjectWrapper(jsElement.getAsJsonObject());
			assertNull(jsWrapper.getStringProperty(JSONUtils.ERROR_MESSAGE_PROP));
		} 
		
	}
	
	protected void assertJSONError(Response response, String il18nKey) {
		// Assert is JSON Response
		assertTrue(response.contentType.contains("application/json"));
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put(JSONUtils.ERROR_MESSAGE_PROP, Messages.get(il18nKey));
		String jsonString = new Gson().toJson(jsonMap);
		assertTrue(response.out.toString().contains(jsonString));
	}

	protected void assertSuccessFlashed(String i18nKey) {
		assertEquals(Messages.get(i18nKey), Flash.current().get("success"));
	}

	protected void assertErrorFlashed(String i18nKey) {
		assertEquals(Messages.get(i18nKey), Flash.current().get("error"));
	}

	protected void assertWarningFlashed(String i18nKey) {
		assertEquals(Messages.get(i18nKey), Flash.current().get("warning"));
	}

	protected void assertNoWarningFlashed() {
		if (Flash.current().contains("warning")) {
			Logger.debug("Warning Flashed:" + Flash.current().get("warning"));
		}
		assertNull(Flash.current().get("warning"));

	}

	protected void assertNoErrorFlashed() {
		if (Flash.current().contains("error")) {
			Logger.debug("Error Flashed:" + Flash.current().get("error"));
		}
		assertNull(Flash.current().get("error"));

	}

	/**
	 * Calculate reverse route and return a URL as String
	 * 
	 * @return The URL reversed from controllerClass, actionName and args
	 */
	protected String calculateRouteURL() {
		return Router.reverse(this.controllerClass.getName() + "."
				+ this.methodName, this.args).url;
	}

	/**
	 * Reset all information
	 */
	protected void reset() {
		this.controllerClass = null;
		this.methodName = null;
		this.args.clear();
		this.argTypes = null;
	}

	/**
	 * Add the action (methodName)
	 * 
	 * @param actionName
	 * @return
	 */
	protected ApplicationFunctionalTest withAction(String actionName) {
		// try to see if the methos exists
		if (this.controllerClass == null) {
			throw new IllegalArgumentException(
					"Please use withController first to set the controller class");
		}
		try {
			if (this.args != null) {
				this.controllerClass.getMethod(actionName, this.argTypes);
			} else {
				this.controllerClass.getMethod(actionName);
			}

		} catch (SecurityException e) {
			throw e;
		} catch (NoSuchMethodException e) {
			StringBuffer argumentsError = new StringBuffer();
			if (this.argTypes == null) {
				argumentsError.append("no args");
			} else {
				argumentsError.append("args ");
				int i = 0;
				for (Class clazz : this.argTypes) {
					argumentsError.append("<");
					argumentsError.append(clazz);
					argumentsError.append(">");
					if (i < this.argTypes.length - 1) {
						argumentsError.append(",");
					}
					i++;

				}
			}
			throw new IllegalArgumentException("No such method: " + actionName
					+ " for class: " + this.controllerClass.getName()
					+ " with " + argumentsError.toString());
		}

		this.methodName = actionName;

		return this;
	}

	/**
	 * Add the params
	 * 
	 * @param args
	 * @return
	 */
	protected ApplicationFunctionalTest withArgs(Object... args) {
		// The args must be an even number
		if (args == null || args.length % 2 != 0) {
			throw new IllegalArgumentException(
					"Please specify even number of arguments where argn is the key and argn+1 is the value");
		}
		argTypes = new Class[args.length / 2];
		for (int i = 0, j = 0; i < args.length - 1; i = i + 2, j++) {
			this.args.put(args[i].toString(), args[i + 1]);
			argTypes[j] = args[i + 1].getClass();
		}

		return this;
	}

	/**
	 * Set the controller to test
	 * 
	 * @param controllerClass
	 * @return
	 */
	protected ApplicationFunctionalTest withController(
			Class<Users> controllerClass) {
		this.controllerClass = controllerClass;
		return this;
	}
}
