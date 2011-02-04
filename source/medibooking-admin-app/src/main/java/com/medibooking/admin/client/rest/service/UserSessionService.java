package com.medibooking.admin.client.rest.service;

import com.medibooking.admin.shared.entity.User;

public interface UserSessionService {
	
	public void create(User user, final JsonResultAvailableCallback callback);

	public void destroy(String sessionId, final JsonResultAvailableCallback callback);

	public void validateSession(String sessionId, final JsonResultAvailableCallback callback);


}
