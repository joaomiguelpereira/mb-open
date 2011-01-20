package com.medibooking.admin.client.place;


import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;


public class HomePlace extends Place {
	//the ! ensures it will be crawlable by google
	@Prefix("!home")
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