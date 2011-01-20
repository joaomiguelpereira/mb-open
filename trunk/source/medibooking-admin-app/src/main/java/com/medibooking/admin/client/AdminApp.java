package com.medibooking.admin.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.medibooking.admin.client.gin.WebAppGinjector;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AdminApp implements EntryPoint {

	 
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		WebAppGinjector injector = GWT.create(WebAppGinjector.class);
		
		injector.getWebApp().run();
		
		
		//messages
		//Messages messages = GWT.create(Messages.class);
		//Injector
		
		//Window.alert("Is the user logged in? :"+injector.getUserSessionService().sessionExists()+ " -- "+messages.hello());
	}
}
