package controllers;

import models.enums.UserType;
import annotations.authorization.RequiresUserSession;

public class Admin extends Application {

	
	//check if is admin
	//@RequiresUserSession(userTypes = {UserType.ADMIN, UserType.BUSINESS_ADMIN})
	public static void index() {
		//if in development, add the ?gwt.codesvr=127.0.0.1:9997
		
		render();
	}
}
