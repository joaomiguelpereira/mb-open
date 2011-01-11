package com.medibooking.admin.client.gin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.medibooking.admin.client.managers.UserSessionManager;
import com.medibooking.admin.client.managers.impl.UserSessionManagerImpl;

public class AdminAppGinModule extends AbstractGinModule {

	@Override
	protected void configure() {	
		bind(UserSessionManager.class).to(UserSessionManagerImpl.class);
		
	}

}
