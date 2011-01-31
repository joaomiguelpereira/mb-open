package controllers.rest;

import play.Logger;
import json.JsonObjectWrapper;

public class Sessions extends RESTController {

	
	public static void create(JsonObjectWrapper body) {
		
		validation.required("password",body.getStringProperty("password"));
		validation.required("email",body.getStringProperty("email"));
		
		if ( validation.hasErrors() ) {
			renderJsonValidationErrors("controllers.rest.session.invaliddata", "user", true);
		}
	}
}
