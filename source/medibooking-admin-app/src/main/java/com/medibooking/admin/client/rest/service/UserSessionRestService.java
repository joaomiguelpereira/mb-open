package com.medibooking.admin.client.rest.service;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.medibooking.admin.client.event.CreateSessionResultAvailableEvent;
import com.medibooking.admin.client.rest.JsonResult;
import com.medibooking.admin.client.rest.ServerRouteResolver;
import com.medibooking.admin.shared.entity.User;

public class UserSessionRestService extends RestService implements UserSessionService{

	
	@Inject
	public UserSessionRestService(EventBus eventBus) {
		super(eventBus);
	}
	
	@Override
	public void create(User user) {
		String jsonData = User.WRITER.toJson(user);
		String url = ServerRouteResolver.UserRoutes.getLogin();
		post(url, jsonData, new JsonResultAvailableCallback() {
			
			@Override
			public void onJsonResultAvaialble(JsonResult result) {
				eventBus.fireEvent(new CreateSessionResultAvailableEvent(result));
				
				
			}
		});
		
	}

}