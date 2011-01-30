package com.medibooking.admin.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class LoginPlace extends Place {
	
	private boolean registeredBefore = false;
	
	public LoginPlace(boolean registeredBefore) {
		this.registeredBefore = registeredBefore; 
	}
	
	
	public boolean isRegisteredBefore() {
		return registeredBefore;
	}


	public void setRegisteredBefore(boolean registeredBefore) {
		this.registeredBefore = registeredBefore;
	}


	@Prefix("!login")
	public static class Tokenizer implements PlaceTokenizer<LoginPlace> {

		@Override
		public LoginPlace getPlace(String token) {
			
			return new LoginPlace(token.contains("?justArrived")?true:false);
		}

		@Override
		public String getToken(LoginPlace place) {
			return "startSession" + (place.registeredBefore?"?justArrived":"");
			
		}
		
	}

}
