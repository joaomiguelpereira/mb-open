package com.medibooking.admin.client;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.medibooking.admin.client.services.UserSessionService;

@GinModules(ClientServicesModule.class)
public interface ClientServicesInjector extends Ginjector {
	
	public UserSessionService getUserSessionService();

}
