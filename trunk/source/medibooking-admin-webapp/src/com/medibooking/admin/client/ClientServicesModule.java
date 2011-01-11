package com.medibooking.admin.client;

import com.google.gwt.inject.client.AbstractGinModule;
import com.medibooking.admin.client.services.UserSessionService;
import com.medibooking.admin.client.services.impl.UserSessionServiceImpl;

public class ClientServicesModule extends AbstractGinModule {

	@Override
	protected void configure() {	
		bind(UserSessionService.class).to(UserSessionServiceImpl.class);
		
	}

}
