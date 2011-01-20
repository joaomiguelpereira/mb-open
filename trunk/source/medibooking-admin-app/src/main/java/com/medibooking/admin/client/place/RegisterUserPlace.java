package com.medibooking.admin.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class RegisterUserPlace extends Place {
	
	@Prefix("!user")
	public static class Tokenizer implements PlaceTokenizer<RegisterUserPlace> {

		@Override
		public RegisterUserPlace getPlace(String token) {
			return new RegisterUserPlace();
			
		}
		@Override
		public String getToken(RegisterUserPlace place) {
			return "register";
		}
		
	}

}
