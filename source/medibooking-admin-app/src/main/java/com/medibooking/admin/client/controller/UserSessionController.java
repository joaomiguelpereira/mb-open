package com.medibooking.admin.client.controller;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.medibooking.admin.client.event.usersession.UserSessionEvent;
import com.medibooking.admin.client.event.usersession.UserSessionEvent.Operation;
import com.medibooking.admin.client.event.usersession.UserSessionEventHandler;
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
		IUserSessionOptionsRegion.Presenter, UserSessionEventHandler {

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
		UserSessionEvent.register(this.eventBus, this);

	}

	@Override
	public void destroyUserSession() {
		userSessionManager.destroySession();

	}

	@Override
	public void onUserSessionEvent(UserSessionEvent event) {
		switch (event.getOperation()) {
		case DESTROY:
			this.view.clearSessionDate();
			break;
		case CREATE:
			this.view.setSessionData(event.getEmail());
		default:
			break;
		}
		
		
	}

	

}
