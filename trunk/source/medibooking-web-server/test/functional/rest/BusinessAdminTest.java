package functional.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import json.JSONUtils;
import json.JsonObjectWrapper;

import models.User;
import models.enums.UserType;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


import play.Logger;
import play.i18n.Messages;
import play.mvc.Http.Response;
import play.mvc.Scope.Flash;
import play.mvc.Router;
import play.test.Fixtures;

import functional.ApplicationFunctionalTest;

public class BusinessAdminTest extends ApplicationFunctionalTest {

	@Before
	public void setup() {
		Fixtures.deleteAll();
		Fixtures.load("users.yml");
		if (Flash.current() != null) {
			Flash.current().clear();
		}
		logoutCurrentUser();

	}

	@Test
	public void throwJsonErrorOnCreateWithInvalidData() {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("body", "invalid json");

		Response res = POST(Router.reverse("rest.BusinessAdmin.create", params));

		assertJSONError(res, "invalid.request.format.error");
	}

	@Test
	public void throwJsonErrorOnCreateWithNoData() {

		Map<String, Object> params = new HashMap<String, Object>();
		// params.put("body", "invalid json");

		Response res = POST(Router.reverse("rest.BusinessAdmin.create", params));

		assertJSONError(res, "invalid.request.format.error");

		params.put("body", null);

		res = POST(Router.reverse("rest.BusinessAdmin.create", params));

		assertJSONError(res, "invalid.request.format.error");
	}

	@Test
	public void produceJsonErrosOnCreateWithInvalidUserRepresentation() {

		Map<String, Object> params = new HashMap<String, Object>();
		// params.put("body", "invalid json");

		Response res = POST(Router.reverse("rest.BusinessAdmin.create", params));

		assertJSONError(res, "invalid.request.format.error");

		params.put("body", "{\"key1\":\"val1\"}");

		res = POST(Router.reverse("rest.BusinessAdmin.create", params));
		// assert errors object exist in the response as Json
		JsonElement jsEl = new JsonParser().parse(res.out.toString());
		// check if it's an object
		assertNotNull(jsEl);
		assertTrue(jsEl.isJsonObject());

		// create a wrapper
		JsonObjectWrapper jsObj = new JsonObjectWrapper(jsEl.getAsJsonObject());

		JsonObjectWrapper errosJsObj = jsObj.getPropertyAsJsonObject(JSONUtils.ERRORS_PROP);
		// Assert exists a prop name errors
		assertNotNull(errosJsObj);

		assertJsonValidationErrors(errosJsObj, "name",
				new String[] { "validation.required" });

		assertJsonValidationErrors(errosJsObj, "email",
				new String[] { "validation.required" });
		assertJsonValidationErrors(errosJsObj, "password",
				new String[] { "validation.required" });

		assertJsonValidationErrors(errosJsObj, "termsAgreement",
				new String[] { "validation.accept.termsAndConditions" });
		assertJsonValidationErrors(errosJsObj, "password",
				new String[] { "validation.required" });
		assertJsonValidationError(jsObj, "controllers.users.create.error");

	}

	@Test
	public void produceJsonErrorsOnCreateWithInvalidUser() {

		Map<String, Object> params = new HashMap<String, Object>();

		params.put(
				"body",
				"{\"userType\":\"BUSINESS_ADMIN\",\"password\":\"12345\",\"name\":\"joao pereira\", \"email\":\"email@gmail.com\",\"emailConfirmation\":\"emailconf\",\"termsAgreement\":\"true\"}");

		Response res = POST(Router.reverse("rest.BusinessAdmin.create", params));
		// assert errors object exist in the response as Json
		JsonElement jsEl = new JsonParser().parse(res.out.toString());
		// check if it's an object
		assertNotNull(jsEl);
		assertTrue(jsEl.isJsonObject());

		// create a wrapper
		JsonObjectWrapper jsObj = new JsonObjectWrapper(jsEl.getAsJsonObject());

		JsonObjectWrapper errosJsObj = jsObj.getPropertyAsJsonObject(JSONUtils.ERRORS_PROP);
		// Assert exists a prop name errors
		assertNotNull(errosJsObj);

		assertNoJsonValidationErrors(errosJsObj, "name");
		assertNoJsonValidationErrors(errosJsObj, "email");
		assertJsonValidationErrors(errosJsObj, "passwordConfirmation",
				new String[] { "validation.passwords.notMatch" });

		assertNoJsonValidationErrors(errosJsObj, "termsAgreement");
		assertNoJsonValidationErrors(errosJsObj, "userType");

		assertJsonValidationErrors(errosJsObj, "emailConfirmation",
				new String[] { "validation.emails.notMatch" });

		assertJsonValidationError(jsObj, "controllers.users.create.error");

	}

	@Test
	public void createBusinessAdminUser() {

		Map<String, Object> params = new HashMap<String, Object>();

		params.put(
				"body",
				"{\"userType\":\"BUSINESS_ADMIN\",\"password\":\"123456\",\"passwordConfirmation\":\"123456\",\"name\":\"joao pereira\", \"email\":\"email@gmail.com\",\"emailConfirmation\":\"email@gmail.com\",\"termsAgreement\":\"true\",\"phone\":\"123456789\"}");

		Response res = POST(Router.reverse("rest.BusinessAdmin.create", params));
		// assert errors object exist in the response as Json
		assertNoJSONError(res);
		
		JsonElement jsEl = new JsonParser().parse(res.out.toString());
		// check if it's an object
		assertNotNull(jsEl);
		assertTrue(jsEl.isJsonObject());

		// create a wrapper
		JsonObjectWrapper jsObj = new JsonObjectWrapper(jsEl.getAsJsonObject());

		JsonObjectWrapper errosJsObj = jsObj.getPropertyAsJsonObject(JSONUtils.ERRORS_PROP);
		// Assert exists a prop name errors
		assertNull(errosJsObj);
		
		assertNotNull(User.find("byEmail", "email@gmail.com"));
		User u = User.find("byEmail", "email@gmail.com").first();
		assertEquals(u.getUserType(), UserType.BUSINESS_ADMIN);

	}
	
	@Test
	public void createUser() {

		Map<String, Object> params = new HashMap<String, Object>();

		params.put(
				"body",
				"{\"userType\":\"USER\",\"password\":\"123456\",\"passwordConfirmation\":\"123456\",\"name\":\"joao pereira\", \"email\":\"email@gmail.com\",\"emailConfirmation\":\"email@gmail.com\",\"termsAgreement\":\"true\",\"phone\":\"123456789\"}");

		Response res = POST(Router.reverse("rest.BusinessAdmin.create", params));
		// assert errors object exist in the response as Json
		assertNoJSONError(res);
		
		JsonElement jsEl = new JsonParser().parse(res.out.toString());
		// check if it's an object
		assertNotNull(jsEl);
		assertTrue(jsEl.isJsonObject());

		// create a wrapper
		JsonObjectWrapper jsObj = new JsonObjectWrapper(jsEl.getAsJsonObject());

		JsonObjectWrapper errosJsObj = jsObj.getPropertyAsJsonObject(JSONUtils.ERRORS_PROP);
		// Assert exists a prop name errors
		assertNull(errosJsObj);
		
		assertNotNull(User.find("byEmail", "email@gmail.com"));
		User u = User.find("byEmail", "email@gmail.com").first();
		assertEquals(u.getUserType(), UserType.USER);
		

	}



}
