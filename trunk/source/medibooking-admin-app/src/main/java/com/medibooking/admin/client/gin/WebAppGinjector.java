package com.medibooking.admin.client.gin;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;
import com.medibooking.admin.client.Messages;
import com.medibooking.admin.client.WebApp;
import com.medibooking.admin.client.activity.HomeActivity;
import com.medibooking.admin.client.activity.LoginActivity;
import com.medibooking.admin.client.manager.UserSessionManager;
import com.medibooking.admin.client.mvp.WebAppPlaceHistoryMapper;
import com.medibooking.admin.client.place.HomePlace;


@GinModules(WebAppGinModule.class)
public interface WebAppGinjector extends Ginjector {
	
	
	
	
	//getHomeActivity
	public HomeActivity getHomeActivity();
	
	//get ActivityMapper as singleton
	public ActivityMapper getActivityMapper();
	
	//get HomePlace as Singleton
	public HomePlace getHomePlace();
	//Get the place history mapper
	public WebAppPlaceHistoryMapper getPlaceHistoryMapper();
	
	//get the main WebApp
	public WebApp getWebApp();
	
	// Get UserSession Manager
	public UserSessionManager getUserSessionService();

	// get EventBus
	public EventBus getEventBus();
	
	//Get the PlaceController
	public PlaceController getPlaceController();
	
	//Get the WebApp Messages
	public Messages getMessages();

}
