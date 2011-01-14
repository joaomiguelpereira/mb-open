package com.medibooking.admin.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Window;
import com.medibooking.admin.client.gin.AdminAppGinjector;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AdminApp implements EntryPoint {

	private final AdminAppGinjector appInjector = GWT
			.create(AdminAppGinjector.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		//Get singleton evenBus
		EventBus appEventBus = appInjector.getEventBus();
		
		//Get the singleton  placeController
		PlaceController appPlaceController = appInjector.getPlaceController();
		
		Window.alert(appPlaceController.getWhere().toString());
	}
}
