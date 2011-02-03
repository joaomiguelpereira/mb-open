package com.medibooking.admin.client.rest.service;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.medibooking.admin.client.event.CreateSessionResultAvailableEvent;
import com.medibooking.admin.client.event.UserSessionDestroyedEvent;
import com.medibooking.admin.client.rest.JsonPropHolder;
import com.medibooking.admin.client.rest.JsonResult;
import com.medibooking.admin.client.rest.ServerRouteResolver;
import com.medibooking.admin.client.rest.SimpleURL;
import com.medibooking.admin.shared.entity.User;

public class UserSessionRestService extends RestService implements UserSessionService{

	
	@Inject
	public UserSessionRestService(EventBus eventBus) {
		super(eventBus);
	}
	
	@Override
	public void create(User user) {
		String jsonData = User.WRITER.toJson(user);
		String url = ServerRouteResolver.SessionRoutes.getCreate();
		post(url, jsonData, new JsonResultAvailableCallback() {
			
			@Override
			public void onJsonResultAvaialble(JsonResult result) {
				eventBus.fireEvent(new CreateSessionResultAvailableEvent(result));
				
				
			}
		});
		
	}

	@Override
	public void destroy(String sessionId) {
				
		SimpleURL url = new SimpleURL(ServerRouteResolver.SessionRoutes.getDestroy());
		url.addParam("sessionId", sessionId);
		
		delete(url.build(), null, new JsonResultAvailableCallback() {
			
			@Override
			public void onJsonResultAvaialble(JsonResult result) {
				eventBus.fireEvent(new UserSessionDestroyedEvent());
			}
		});
		
	}

}
