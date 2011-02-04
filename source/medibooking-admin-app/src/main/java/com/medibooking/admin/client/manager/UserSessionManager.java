package com.medibooking.admin.client.manager;

import com.medibooking.admin.shared.entity.User;

public interface UserSessionManager {

	public boolean sessionExists();

	public void destroySession();

	public String getEmail();

	public void createSessionFromCookies();

	public void create(User user);

}
