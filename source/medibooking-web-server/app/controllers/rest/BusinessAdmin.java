package controllers.rest;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import models.User;
import models.enums.UserType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import data.binding.JsonObjectWrapper;

import play.Logger;
import play.data.validation.Valid;
import play.i18n.Messages;
import play.mvc.Scope.Params;

public class BusinessAdmin extends RESTController {

	private static void create(User user, String passwordConfirmation,
			String emailConfirmation, Boolean termsAgreement) {
		String password = user.getPassword();
		// Validate user
		validation.valid(user);
		
		validation.required("password",password);
		validation.range("password",password, 6,24);
		
		validation.equals("emailConfirmation",emailConfirmation, "email",user.getEmail()).message(
				Messages.get("validation.emails.notMatch"));

		validation.equals("passwordConfirmation",passwordConfirmation, "password",password).message(
				Messages.get("validation.passwords.notMatch"));

		validation.isTrue("termsAgreement",termsAgreement).message(
				"validation.accept.termsAndConditions");

		
	
		if (validation.hasErrors()) {
			renderJsonValidationErrors("controllers.users.create.error", "user", true);
		}
		
		//save the user
		
		
		
	}

	public static void create(JsonObjectWrapper body) {
		if (body == null) {
			jsonError("invalid.request.format.error");
		}

		User user = body.getAs(User.class);
		String passwordConfirmation = body
				.getStringProperty("passwordConfirmation");
		String emailConfirmation = body.getStringProperty("emailConfirmation");
		boolean termsAgreement = body.getBooleanProperty("termsAgreement");
		
		//UserType userType = body.getEnumProperty("userType", UserType.class);
		Logger.debug("User Type is:" +user.getUserType()+ "-- class "+user.getClass().getName());
		
		create(user, passwordConfirmation, emailConfirmation, termsAgreement);
		
	}

}
