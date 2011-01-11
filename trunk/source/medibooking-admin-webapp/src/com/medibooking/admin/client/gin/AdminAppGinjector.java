package com.medibooking.admin.client.gin;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.medibooking.admin.client.managers.UserSessionManager;

@GinModules(AdminAppGinModule.class)
public interface AdminAppGinjector extends Ginjector {
	
	public UserSessionManager getUserSessionService();

}
