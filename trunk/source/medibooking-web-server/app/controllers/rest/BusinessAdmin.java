package controllers.rest;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import json.JsonObjectWrapper;

import notifiers.UserMailer;

import models.BusinessAdministrator;
import models.User;
import models.enums.UserType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


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
		validation.minSize("password",password, 6);
		validation.maxSize("password",password, 24);
		
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
		User savedUser = null;
		switch (user.getUserType()) {

		case BUSINESS_ADMIN:
			savedUser = new BusinessAdministrator(user).save();
			break;

		default:
			savedUser = new User(user).save();
			break;
		}
		// send activation email to user's email
		UserMailer.activateAccount(savedUser);
		//Render json success stuff
		renderJsonSuccess("user.register.success", savedUser, "user");
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
		
		
		create(user, passwordConfirmation, emailConfirmation, termsAgreement);
		
	}

}
