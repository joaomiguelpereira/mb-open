package com.medibooking.admin.client.controller;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

import com.medibooking.admin.client.AdminApp;
import com.medibooking.admin.client.event.UserSessionCreatedEvent;
import com.medibooking.admin.client.event.UserSessionDestroyedEvent;
import com.medibooking.admin.client.manager.UserSessionManager;
import com.medibooking.admin.client.mvp.AbstractController;
import com.medibooking.admin.client.view.region.IUserSessionOptionsRegion;

/**
 * Global controller to handle user logins, logouts, etc
 * 
 * @author jpereira
 * 
 */
public class UserSessionController extends AbstractController implements
		IUserSessionOptionsRegion.Presenter, UserSessionCreatedEvent.Handler,
		UserSessionDestroyedEvent.Handler {

	// This is injected in the reverse as compared with views
	private final IUserSessionOptionsRegion view;

	private final UserSessionManager userSessionManager;

	@Inject
	public UserSessionController(EventBus eventBus,
			PlaceController placeController, IUserSessionOptionsRegion view,
			UserSessionManager userSessionManager) {
		this.eventBus = eventBus;
		this.placeController = placeController;
		this.view = view;
		this.userSessionManager = userSessionManager;

		view.setPresenter(this);
		UserSessionCreatedEvent.register(this.eventBus, this);
		UserSessionDestroyedEvent.register(this.eventBus, this);

	}

	@Override
	public void destroyUserSession() {
		userSessionManager.destroySession();

	}

	@Override
	public void onUserSessionCreated(UserSessionCreatedEvent event) {
		this.view.setSessionData(this.userSessionManager.getEmail());
	}

	@Override
	public void onSessionDestroyed(UserSessionDestroyedEvent event) {
		this.view.clearSessionDate();
		
	}

}
