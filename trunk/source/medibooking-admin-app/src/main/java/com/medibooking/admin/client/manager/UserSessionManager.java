package com.medibooking.admin.client.manager;

public interface UserSessionManager {

	public boolean sessionExists();

	public void startSession(String stringProperty, String stringProperty2,
			Integer integerProperty);
}
