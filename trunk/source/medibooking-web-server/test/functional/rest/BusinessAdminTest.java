package functional.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.enums.UserType;

import org.junit.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import data.binding.JsonObjectWrapper;

import play.Logger;
import play.i18n.Messages;
import play.mvc.Http.Response;
import play.mvc.Router;

import functional.ApplicationFunctionalTest;

public class BusinessAdminTest extends ApplicationFunctionalTest {

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
		//assert errors object exist in the response as Json
		JsonElement jsEl = new JsonParser().parse(res.out.toString());
		//check if it's an object
		assertNotNull(jsEl);
		assertTrue(jsEl.isJsonObject());
		
		//create a wrapper
		JsonObjectWrapper jsObj = new JsonObjectWrapper(jsEl.getAsJsonObject());
		
		JsonObjectWrapper errosJsObj = jsObj.getPropertyAsJsonObject("errors");
		//Assert exists a prop name errors
		assertNotNull(errosJsObj);
		
		assertJsonValidationErrors(errosJsObj, "name", new String[] {"validation.required"});
		
		assertJsonValidationErrors(errosJsObj, "email", new String[] {"validation.required"});
		assertJsonValidationErrors(errosJsObj, "password", new String[] {"validation.required"});
		
		assertJsonValidationErrors(errosJsObj, "termsAgreement", new String[] {"validation.accept.termsAndConditions"});
		assertJsonValidationErrors(errosJsObj, "password", new String[] {"validation.required"});
		assertJsonValidationError(jsObj, "controllers.users.create.error");

	}

	@Test
	public void produceJsonErrorsOnCreateWithInvalidUser() {

		Map<String, Object> params = new HashMap<String, Object>();
		
		
		params.put("body", "{\"userType\":\"BUSINESS_ADMIN\",\"password\":\"12345\",\"name\":\"joao pereira\", \"email\":\"email@gmail.com\",\"emailConfirmation\":\"emailconf\",\"termsAgreement\":\"true\"}");

		Response res = POST(Router.reverse("rest.BusinessAdmin.create", params));
		//assert errors object exist in the response as Json
		JsonElement jsEl = new JsonParser().parse(res.out.toString());
		//check if it's an object
		assertNotNull(jsEl);
		assertTrue(jsEl.isJsonObject());
		
		//create a wrapper
		JsonObjectWrapper jsObj = new JsonObjectWrapper(jsEl.getAsJsonObject());
		
		JsonObjectWrapper errosJsObj = jsObj.getPropertyAsJsonObject("errors");
		//Assert exists a prop name errors
		assertNotNull(errosJsObj);
		
		assertNoJsonValidationErrors(errosJsObj, "name");
		assertNoJsonValidationErrors(errosJsObj, "email");
		assertJsonValidationErrors(errosJsObj, "passwordConfirmation", new String[] {"validation.passwords.notMatch"});
		
		assertNoJsonValidationErrors(errosJsObj, "termsAgreement");
		assertNoJsonValidationErrors(errosJsObj, "userType");
		
		
		assertJsonValidationErrors(errosJsObj, "emailConfirmation", new String[] {"validation.emails.notMatch"});
		
		assertJsonValidationError(jsObj, "controllers.users.create.error");
		
		

	}


	

	
}
