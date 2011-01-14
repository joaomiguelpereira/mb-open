package com.medibooking.admin.client.gin;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;
import com.medibooking.admin.client.managers.UserSessionManager;

@GinModules(AdminAppGinModule.class)
public interface AdminAppGinjector extends Ginjector {

	// Get UserSession Manager
	public UserSessionManager getUserSessionService();

	// get EventBus
	public EventBus getEventBus();
	
	//Get the PlaceController
	public PlaceController getPlaceController();

}
