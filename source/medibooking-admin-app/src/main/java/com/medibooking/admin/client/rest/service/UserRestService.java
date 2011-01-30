package com.medibooking.admin.client.rest.service;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.medibooking.admin.client.event.CreateUserResultAvailableEvent;
import com.medibooking.admin.client.rest.JsonResult;
import com.medibooking.admin.client.rest.ServerRouteResolver;
import com.medibooking.admin.shared.entity.User;

public class UserRestService extends RestService implements UserService {

	@Inject
	public UserRestService(EventBus eventBus) {
		super(eventBus);
	}

	public void createUser(User user) {

		//prepare data to send to REST API
		String jsonData = User.WRITER.toJson(user);
		//get the usr
		String url = ServerRouteResolver.UserRoutes.getCreate();
		
		post(url, jsonData, new JsonResultAvailableCallback() {

			@Override
			public void onJsonResultAvaialble(JsonResult result) {
				eventBus.fireEvent(new CreateUserResultAvailableEvent(result));
				
			}
		});
		
	}
	
	public void loginUser(User user) {
		String jsonData = User.WRITER.toJson(user);
		String url = ServerRouteResolver.UserRoutes.getLogin();
		post(url, jsonData, new JsonResultAvailableCallback() {
			
			@Override
			public void onJsonResultAvaialble(JsonResult result) {
				Window.alert(result.getJsonString());
				
			}
		});
	}

	
	
}
