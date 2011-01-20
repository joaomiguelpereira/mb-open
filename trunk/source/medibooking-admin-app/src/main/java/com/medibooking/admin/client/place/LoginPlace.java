package com.medibooking.admin.client.place;

import java.util.logging.Logger;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class LoginPlace extends Place {
	
	private static final Logger log = Logger.getLogger(LoginPlace.class.getName());
	
	@Prefix("!login")
	public static class Tokenizer implements PlaceTokenizer<LoginPlace> {

		@Override
		public LoginPlace getPlace(String token) {
			log.fine("CAlled getPlace for token "+token);
			return new LoginPlace();
		}

		@Override
		public String getToken(LoginPlace place) {
			log.fine("CAlled getToken for place "+place);
			return "startSession";
		}
		
	}

}
