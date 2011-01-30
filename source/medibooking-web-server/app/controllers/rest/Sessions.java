package controllers.rest;

import play.Logger;
import json.JsonObjectWrapper;

public class Sessions extends RESTController {

	
	public static void create(JsonObjectWrapper body) {
		Logger.debug(body.getValue().toString());
		renderJsonSuccess("OK.hh");
	}
}
