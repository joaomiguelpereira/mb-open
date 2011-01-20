package com.medibooking.admin.client.place;

import java.util.logging.Logger;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class HomePlace extends Place {
	private static final Logger log = Logger.getLogger(HomePlace.class.getName());
	
	@Prefix("home")
	public static class Tokenizer implements PlaceTokenizer<HomePlace> {

		@Override
		public HomePlace getPlace(String token) {
			
			return new HomePlace();
		}

		@Override
		public String getToken(HomePlace place) {
			
			return "";
		}
		
	}
}