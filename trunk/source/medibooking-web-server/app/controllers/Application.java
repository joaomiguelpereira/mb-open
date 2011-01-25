package controllers;

import java.util.HashMap;
import java.util.List;

import play.Logger;
import play.Play;
import play.mvc.Router;

import constants.SessionValuesConstants;
import models.Business;
import models.User;
import models.enums.UserType;

public class Application extends BaseController {

	public static void index() {
		// decide what is the front page for the current user
		if (hasSession()) {
			UserType uType = UserType.valueOf(session
					.get(SessionValuesConstants.USER_TYPE));

			switch (uType) {
			case BUSINESS_ADMIN:
			case ADMIN:
				flash.keep();
				//Businesses.list();
				if (Play.configuration.getProperty("application.mode").equals(
						"dev")) {
					String url = Router.reverse("Admin.index").url;
					redirect(url+"?gwt.codesvr=127.0.0.1:9997");
				} else {
					Admin.index();
				}
				break;
			default:
				render();
			}
		} else {
			render();
		}

	}

}
