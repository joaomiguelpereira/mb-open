package com.medibooking.admin.client.manager;

public interface UserSessionManager {

	public boolean sessionExists();

	public void startSession(String sessionId, String email, Integer duration);

	public void destroySession();

	public String getEmail();

	public void createSessionFromCookies();

}
