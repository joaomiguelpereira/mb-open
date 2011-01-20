package com.medibooking.admin.client.mvp;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.PlaceHistoryMapperWithFactory;
import com.google.gwt.place.shared.WithTokenizers;
import com.medibooking.admin.client.place.HomePlace;
import com.medibooking.admin.client.place.LoginPlace;
import com.medibooking.admin.client.place.RegisterUserPlace;

@WithTokenizers({
	HomePlace.Tokenizer.class,
	LoginPlace.Tokenizer.class,
	RegisterUserPlace.Tokenizer.class
})
public interface WebAppPlaceHistoryMapper extends PlaceHistoryMapper {

	

}
