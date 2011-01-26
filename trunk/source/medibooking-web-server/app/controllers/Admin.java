package controllers;

import play.Play;
import play.mvc.Router;
import models.enums.UserType;
import annotations.authorization.RequiresUserSession;

public class Admin extends Application {

	// check if is admin
	// @RequiresUserSession(userTypes = {UserType.ADMIN,
	// UserType.BUSINESS_ADMIN})
	public static void index() {

		render();

	}

	public static void ui() {
		// if in development, add the ?gwt.codesvr=127.0.0.1:9997
		// Todo: Add those params as configurable items
		if (Play.configuration.getProperty("application.mode").equals("dev")) {
			String url = Router.reverse("Admin.index").url;
			redirect(url + "?gwt.codesvr=127.0.0.1:9997");
		} else {
			Admin.index();
		}

	}
}
