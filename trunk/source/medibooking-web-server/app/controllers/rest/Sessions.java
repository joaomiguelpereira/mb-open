package controllers.rest;

import java.util.HashMap;

import models.UserSession;
import models.User;
import play.Logger;
import play.i18n.Messages;
import utils.UserSessionUtils;
import json.JsonObjectWrapper;
import json.JsonPropHolder;

public class Sessions extends RESTController {

	private static final int LONG_SESSION_DURATION = 15;

	public static void create(JsonObjectWrapper body) {
		
		validation.required("password",body.getStringProperty("password")).message(Messages.get("controllers.rest.session.create.invalid.password"));
		validation.required("email",body.getStringProperty("email")).message(Messages.get("controllers.rest.session.create.invalid.email"));
		
		if ( validation.hasErrors() ) {
			renderJsonValidationErrors("controllers.rest.session.create.fail", "user", true);
		}
		String email = body.getStringProperty("email");
		String password = body.getStringProperty("password");
		Boolean keepLogged = body.getBooleanProperty("keepLogged");
		
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
		//check if exists any session for the user
		final UserSession userSession = new UserSession();
		
		
		userSession.create(user.getId(), request.remoteAddress,keepLogged?LONG_SESSION_DURATION:UserSession.DEFAULT_SESSION_DURATION);
		//return to client the sessionID
		Logger.debug("Session ID:"+userSession.getSessionId());
		
		renderJsonSuccess("controllers.rest.session.create.success", new JsonPropHolder().add("sessionId", userSession.getSessionId()));
		
	}


}
