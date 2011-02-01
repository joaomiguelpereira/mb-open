package utils;

import play.mvc.Scope.Session;
import constants.SessionValuesConstants;
import models.User;

public class UserSessionUtils {

	public static void createAuthenticateUserSessionData(User user, Session session) {
		// set user login in session
		session.put(SessionValuesConstants.LOGIN_TOKEN, user
				.getLoginInformation().getLoginToken());
		session.put(SessionValuesConstants.LOGIN_EMAIL, user.getEmail());
		session.put(SessionValuesConstants.USER_TYPE, user.getUserType()
				.toString());
		
	}

}
