package com.medibooking.admin.client.rest.service;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
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
		
		post(url, jsonData);
		
		

	}

	
}
