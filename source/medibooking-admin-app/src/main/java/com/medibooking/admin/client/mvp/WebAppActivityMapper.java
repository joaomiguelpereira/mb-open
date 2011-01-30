package com.medibooking.admin.client.mvp;

import java.util.HashMap;
import java.util.logging.Logger;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.medibooking.admin.client.activity.HomeActivity;
import com.medibooking.admin.client.activity.LoginActivity;
import com.medibooking.admin.client.activity.RegisterUserActivity;
import com.medibooking.admin.client.place.HomePlace;
import com.medibooking.admin.client.place.LoginPlace;
import com.medibooking.admin.client.place.RegisterUserPlace;

public class WebAppActivityMapper implements ActivityMapper {

	private final EventBus eventBus;

	private static final Logger log = Logger
			.getLogger(WebAppActivityMapper.class.getName());

	private final HashMap<Class<?>, Activity> placeActivityMappings = new HashMap<Class<?>, Activity>();

	@Inject
	public WebAppActivityMapper(EventBus eventBus, HomeActivity homeActivity,
			LoginActivity loginActivity,
			RegisterUserActivity registerUserActivity) {

		this.eventBus = eventBus;
		this.placeActivityMappings.put(HomePlace.class, homeActivity);
		this.placeActivityMappings.put(LoginPlace.class, loginActivity);
		this.placeActivityMappings.put(RegisterUserPlace.class, registerUserActivity);
		
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof LoginPlace ) {
			LoginActivity activity = (LoginActivity)this.placeActivityMappings.get(place.getClass()); 
			activity.setRegisteredBefore(((LoginPlace)place).isRegisteredBefore());
			return activity; 
		}//Fall into others
		return this.placeActivityMappings.get(place.getClass());
	}

}
