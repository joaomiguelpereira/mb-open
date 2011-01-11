package com.medibooking.admin.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.medibooking.admin.client.gin.AdminAppGinjector;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AdminApp implements EntryPoint {

	private final AdminAppGinjector csInjector = GWT
			.create(AdminAppGinjector.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		Window.alert(csInjector.getUserSessionService().sessionExists() + "");
	}
}
