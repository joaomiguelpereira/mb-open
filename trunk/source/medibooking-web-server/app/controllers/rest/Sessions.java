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

	public static void validate(String sessionId) {
		Logger.debug("Validating session id: " + sessionId);
		// find the session id
		UserSession userSession = UserSession.find("bySessionId", sessionId)
				.first();
		
		if (userSession == null) {
			renderJsonError("Could not validate the session id");
		}
		User user = User.findById(userSession.getUserId());
		if (user == null) {
			renderJsonError("Could not validate the session id because the user referenced was not found");
		}

		renderJsonSuccess(
				"controllers.rest.session.create.success",
				new JsonPropHolder().add("sessionId", sessionId)
						.add("email", user.getEmail()).add("userId", user.id).add("duration", userSession.getDuration()));
	}

	public static void destroy(String sessionId) {
		Logger.debug("Destroying session id: " + sessionId);
		UserSession userSession = UserSession.find("bySessionId", sessionId)
				.first();
		// if the session does not exists in the DB, then don't care, probably
		// was deleted by a job, could be an old one
		if (userSession != null) {
			userSession.delete();
			Logger.debug("Session " + sessionId
					+ " was in database and is now destroyed...");
		}
		renderJsonSuccess("controllers.rest.session.destroy.success");
	}

	public static void create(JsonObjectWrapper body) {

		validation
				.required("password", body.getStringProperty("password"))
				.message(
						Messages.get("controllers.rest.session.create.invalid.password"));
		validation.required("email", body.getStringProperty("email")).message(
				Messages.get("controllers.rest.session.create.invalid.email"));

		if (validation.hasErrors()) {
			renderJsonValidationErrors("controllers.rest.session.create.fail",
					"user", true);
		}
		String email = body.getStringProperty("email");
		String password = body.getStringProperty("password");
		Boolean keepLogged = body.getBooleanProperty("keepLogged");

		// Find user by email
		User user = User.find("byEmail", email).first();

		if (user == null) {
			renderJsonError(
					"controllers.rest.session.create.fail.email.not.found",
					email);
		}

		// If found, but not active
		if (!user.isActive()) {
			renderJsonError(
					"controllers.rest.session.create.fail.user.not.registered",
					email);
		}
		// generate provided password hash

		String loginToken = user.authenticate(password, email);

		// If found, but not active
		if (loginToken == null) {
			renderJsonError("controllers.rest.session.create.fail");
		}
		// check if exists any session for the user
		final UserSession userSession = new UserSession();

		int duration = keepLogged ? LONG_SESSION_DURATION
				: UserSession.DEFAULT_SESSION_DURATION;
		userSession.create(user.getId(), request.remoteAddress, duration);
		// return to client the sessionID
		Logger.debug("Session ID:" + userSession.getSessionId());

		// If session duration is set to the default, then at the user side it
		// means that is valid while the broser is open, so send 0

		renderJsonSuccess(
				"controllers.rest.session.create.success",
				new JsonPropHolder()
						.add("sessionId", userSession.getSessionId())
						.add("email", user.getEmail())
						.add("userId", user.id)
						.add("duration",
								duration == UserSession.DEFAULT_SESSION_DURATION ? 0
										: duration));
	}

}
