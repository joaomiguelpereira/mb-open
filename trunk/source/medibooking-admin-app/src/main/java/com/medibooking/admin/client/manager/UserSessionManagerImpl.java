package com.medibooking.admin.client.manager;

import java.util.Date;
import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Cookies;
import com.google.inject.Inject;
import com.medibooking.admin.client.event.UserSessionCreatedEvent;

public class UserSessionManagerImpl implements UserSessionManager {

	private static final String SESSION_ID_COOKIE_NAME = "sid";
	private static final String EMAIL_COOKIE_NAME = "sem";
	private String sessionId = null;
	private String email = null;
	private EventBus eventBus;
	private static final Logger log = Logger
			.getLogger(UserSessionManagerImpl.class.getName());

	
	@Inject
	public UserSessionManagerImpl(EventBus eventBus) {
		this.eventBus = eventBus;
	}
	
	public boolean sessionExists() {
		return sessionId!=null;
	}

	@Override
	public void startSession(String sessionId, String email, Integer duration) {
		
		log.fine("Starting session for: SessionId: " + sessionId
				+ " -- email: " + email + " -- sessionDuration:" + duration);

		this.sessionId = sessionId;
		this.email = email;
		
		if (duration>0) {
			Date expireDate = new Date(System.currentTimeMillis()+ duration*24*60*60*1000);
			//Create a cookie
			Cookies.setCookie(SESSION_ID_COOKIE_NAME, this.sessionId, expireDate);
			Cookies.setCookie(EMAIL_COOKIE_NAME, this.email, expireDate);
		} else {
			//Create a cookie
			Cookies.setCookie(SESSION_ID_COOKIE_NAME, this.sessionId );
			Cookies.setCookie(EMAIL_COOKIE_NAME, this.email);
		}
		
		//send event
		this.eventBus.fireEvent(new UserSessionCreatedEvent());
	}

}
