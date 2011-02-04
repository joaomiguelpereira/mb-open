package com.medibooking.admin.client.rest.service;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.medibooking.admin.client.rest.ServerRouteResolver;
import com.medibooking.admin.client.rest.SimpleURL;
import com.medibooking.admin.shared.entity.User;

public class UserSessionRestService extends RestService implements
		UserSessionService {

	@Inject
	public UserSessionRestService(EventBus eventBus) {
		super(eventBus);
	}

	@Override
	public void create(User user, final JsonResultAvailableCallback callback) {
		String jsonData = User.WRITER.toJson(user);
		String url = ServerRouteResolver.SessionRoutes.getCreate();
		post(url, jsonData,callback, false);

	}

	@Override
	public void destroy(String sessionId, final JsonResultAvailableCallback callback) {

		SimpleURL url = new SimpleURL(
				ServerRouteResolver.SessionRoutes.getDestroy());
		url.addParam("sessionId", sessionId);

		delete(url.build(), null, callback, true);

	}

	@Override
	public void validateSession(String sessionId, final JsonResultAvailableCallback callback) {

		SimpleURL url = new SimpleURL(
				ServerRouteResolver.SessionRoutes.getValidate());
		url.addParam("sessionId", sessionId);
		put(url, null,callback, true);

	}

}
