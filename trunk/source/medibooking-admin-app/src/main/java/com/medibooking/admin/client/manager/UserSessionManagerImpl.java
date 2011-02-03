package com.medibooking.admin.client.manager;

import java.util.Date;
import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Cookies;
import com.google.inject.Inject;
import com.medibooking.admin.client.event.UserSessionCreatedEvent;
import com.medibooking.admin.client.event.UserSessionDestroyedEvent;
import com.medibooking.admin.client.rest.service.UserSessionService;

public class UserSessionManagerImpl implements UserSessionManager,
		UserSessionDestroyedEvent.Handler {

	private static final String SESSION_ID_COOKIE_NAME = "sid";
	private static final String EMAIL_COOKIE_NAME = "sem";
	private String sessionId = null;
	private String email = null;
	private EventBus eventBus;
	private UserSessionService service;

	private static final Logger log = Logger
			.getLogger(UserSessionManagerImpl.class.getName());

	@Inject
	public UserSessionManagerImpl(EventBus eventBus, UserSessionService service) {
		this.eventBus = eventBus;
		this.service = service;
		UserSessionDestroyedEvent.register(this.eventBus, this);

	}

	public boolean sessionExists() {
		return sessionId != null;
	}

	@Override
	public void startSession(String sessionId, String email, Integer duration) {

		log.fine("Starting session for: SessionId: " + sessionId
				+ " -- email: " + email + " -- sessionDuration:" + duration);

		this.sessionId = sessionId;
		this.email = email;

		if (duration > 0) {
			Date expireDate = new Date(System.currentTimeMillis() + duration
					* 24 * 60 * 60 * 1000);
			// Create a cookie
			Cookies.setCookie(SESSION_ID_COOKIE_NAME, this.sessionId,
					expireDate);
			Cookies.setCookie(EMAIL_COOKIE_NAME, this.email, expireDate);
		} else {
			// Create a cookie
			Cookies.setCookie(SESSION_ID_COOKIE_NAME, this.sessionId);
			Cookies.setCookie(EMAIL_COOKIE_NAME, this.email);
		}

		// send event
		this.eventBus.fireEvent(new UserSessionCreatedEvent());
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public void createSessionFromCookies() {
		if (Cookies.getCookie(SESSION_ID_COOKIE_NAME) != null
				&& Cookies.getCookie(EMAIL_COOKIE_NAME) != null) {
			this.email = Cookies.getCookie(EMAIL_COOKIE_NAME);
			this.sessionId = Cookies.getCookie(SESSION_ID_COOKIE_NAME);
			// verify is the session is valid

			this.eventBus.fireEvent(new UserSessionCreatedEvent());
		}

	}

	@Override
	public void destroySession() {
		service.destroy(this.sessionId);
	}

	@Override
	public void onSessionDestroyed(UserSessionDestroyedEvent event) {
		Cookies.removeCookie(SESSION_ID_COOKIE_NAME);
		Cookies.removeCookie(EMAIL_COOKIE_NAME);
		this.sessionId = null;
		this.email = null;
	}

}
