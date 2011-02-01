package controllers.rest;

import models.User;
import play.Logger;
import play.i18n.Messages;
import utils.UserSessionUtils;
import json.JsonObjectWrapper;

public class Sessions extends RESTController {

	
	public static void create(JsonObjectWrapper body) {
		
		validation.required("password",body.getStringProperty("password")).message(Messages.get("controllers.rest.session.create.invalid.password"));
		validation.required("email",body.getStringProperty("email")).message(Messages.get("controllers.rest.session.create.invalid.email"));
		
		if ( validation.hasErrors() ) {
			renderJsonValidationErrors("controllers.rest.session.create.fail", "user", true);
		}
		String email = body.getStringProperty("email");
		String password = body.getStringProperty("password");
		//Find user by email
		User user = User.find("byEmail", email).first();
		
		if (user==null) {
			renderJsonError("controllers.rest.session.create.fail.email.not.found",email);
		}
		
		//If found, but not active
		if ( !user.isActive() ) {
			renderJsonError("controllers.rest.session.create.fail.user.not.registered",email);
		}
		//generate provided password hash
		
		String loginToken = user.authenticate(password, email);

		//If found, but not active
		if ( loginToken==null ) {
			renderJsonError("controllers.rest.session.create.fail");
		}
		
		renderJsonSuccess("controllers.rest.session.create.success");
		
	}

	
	
}
