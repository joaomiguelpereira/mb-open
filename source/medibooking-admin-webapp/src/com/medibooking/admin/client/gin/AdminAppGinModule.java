package com.medibooking.admin.client.gin;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Singleton;
import com.medibooking.admin.client.managers.UserSessionManager;
import com.medibooking.admin.client.managers.impl.UserSessionManagerImpl;

public class AdminAppGinModule extends AbstractGinModule {

	@Override
	protected void configure() {	
		//bind a singleton instance of UserSessionManagerImpl to UserSessionManager
		bind(UserSessionManager.class).to(UserSessionManagerImpl.class).in(Singleton.class);
		
		//Bind event Bus
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		
		//Bind the place Controller
		bind(PlaceController.class).to(InjectablePlaceController.class).in(Singleton.class);
		
		
	}

}
