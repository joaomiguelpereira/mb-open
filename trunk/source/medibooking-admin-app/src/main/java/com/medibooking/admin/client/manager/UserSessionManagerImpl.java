package com.medibooking.admin.client.manager;

import java.util.Date;
import java.util.logging.Logger;


import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Cookies;
import com.google.inject.Inject;
import com.medibooking.admin.client.event.usersession.UserSessionEvent;
import com.medibooking.admin.client.event.usersession.UserSessionEvent.Operation;
import com.medibooking.admin.client.rest.JsonResult;
import com.medibooking.admin.client.rest.service.JsonResultAvailableCallback;
import com.medibooking.admin.client.rest.service.UserSessionService;
import com.medibooking.admin.shared.entity.User;

public class UserSessionManagerImpl implements UserSessionManager {

	private static final String SESSION_ID_COOKIE_NAME = "sid";
	private static final String EMAIL_COOKIE_NAME = "sem";
	private static final String USERID_COOKIE_NAME = "sui";

	private String sessionId = null;
	private String email = null;
	private Long userId = null;
	private EventBus eventBus;
	private UserSessionService service;

	private static final Logger log = Logger
			.getLogger(UserSessionManagerImpl.class.getName());

	@Inject
	public UserSessionManagerImpl(EventBus eventBus, UserSessionService service) {
		this.eventBus = eventBus;
		this.service = service;

		// UserSessionDestroyedEvent.register(this.eventBus, this);

	}

	public boolean sessionExists() {
		return sessionId != null;
	}

	private void persistSessionWithCookies(Integer duration) {

		if (duration > 0) {
			Date expireDate = new Date(System.currentTimeMillis() + duration
					* 24 * 60 * 60 * 1000);
			// Create a cookie
			Cookies.setCookie(SESSION_ID_COOKIE_NAME, this.sessionId,
					expireDate);
			Cookies.setCookie(EMAIL_COOKIE_NAME, this.email, expireDate);
			Cookies.setCookie(USERID_COOKIE_NAME, this.userId.toString(),
					expireDate);

		} else {
			// Create a cookie
			Cookies.setCookie(SESSION_ID_COOKIE_NAME, this.sessionId);
			Cookies.setCookie(EMAIL_COOKIE_NAME, this.email);
			Cookies.setCookie(USERID_COOKIE_NAME, this.userId.toString());
		}

	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public void createSessionFromCookies() {
		if (Cookies.getCookie(SESSION_ID_COOKIE_NAME) != null
				&& Cookies.getCookie(EMAIL_COOKIE_NAME) != null) {
			// this.email = Cookies.getCookie(EMAIL_COOKIE_NAME);
			// this.sessionId = Cookies.getCookie(SESSION_ID_COOKIE_NAME);

			service.validateSession(Cookies.getCookie(SESSION_ID_COOKIE_NAME),
					new JsonResultAvailableCallback() {
						@Override
						public void onJsonResultAvaialble(JsonResult result) {
							// If response is good, then create a session
							if (result.getStatus() == JsonResult.Status.SUCCESS) {
								// try to get the duration from the result

								createClientSession(result);
								// send event about session created
								eventBus.fireEvent(new UserSessionEvent(
										Operation.CREATE, result));
							} else {
								removeSessionFromClient();
								eventBus.fireEvent(new UserSessionEvent(
										UserSessionEvent.Operation.DESTROY, result));
							}

						}
					});

		}

	}

	@Override
	public void destroySession() {
		service.destroy(this.sessionId, new JsonResultAvailableCallback() {

			@Override
			public void onJsonResultAvaialble(JsonResult result) {
				// destroy anyways, even if the response is not the best on
				removeSessionFromClient();
				eventBus.fireEvent(new UserSessionEvent(
						UserSessionEvent.Operation.DESTROY, result));
			}
		});
	}

	private void removeSessionFromClient() {
		Cookies.removeCookie(SESSION_ID_COOKIE_NAME);
		Cookies.removeCookie(EMAIL_COOKIE_NAME);
		this.sessionId = null;
		this.email = null;
		this.userId = null;
		// drop an event

	}

	@Override
	public void create(User user) {
		// just delegate it to the server using the service
		service.create(user, new JsonResultAvailableCallback() {
			@Override
			public void onJsonResultAvaialble(JsonResult result) {

				if (result.isSuccess()) {
					createClientSession(result);
				}
				eventBus.fireEvent(new UserSessionEvent(
						UserSessionEvent.Operation.CREATE, result));
			}
		});

	}

	private void createClientSession(JsonResult result) {
		this.sessionId = result.getStringProperty("sessionId",true);
		this.userId = result.getLongProperty("userId",true);
		this.email = result.getStringProperty("email",true);

		persistSessionWithCookies(result.getIntegerProperty("duration",true));

	}

}
