package com.medibooking.admin.client.activity;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.medibooking.admin.client.event.usersession.UserSessionEvent;
import com.medibooking.admin.client.event.usersession.UserSessionEvent.Status;
import com.medibooking.admin.client.event.usersession.UserSessionEventHandler;
import com.medibooking.admin.client.manager.UserSessionManager;
import com.medibooking.admin.client.mvp.AbstractWebAppActivity;
import com.medibooking.admin.client.place.HomePlace;
import com.medibooking.admin.client.view.ILoginView;
import com.medibooking.admin.shared.entity.User;

public class LoginActivity extends AbstractWebAppActivity implements
		ILoginView.Presenter, UserSessionEventHandler {

	private static Logger log = Logger.getLogger(LoginActivity.class.getName());
	private final ILoginView view;
	private boolean registeredBefore;
	private UserSessionManager userSessionManager;

	@Inject
	public LoginActivity(UserSessionManager sessionManager,
	/* UserSessionService service, */ILoginView view,
			PlaceController placeController) {
		this.placeController = placeController;
		this.view = view;
		// this.userSessionservice = service;
		this.userSessionManager = sessionManager;
		this.view.setPresenter(this);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.eventBus = eventBus;
		panel.setWidget(view);
		this.view.setUser(new User());
		this.view.initialize();
		UserSessionEvent.register(this.eventBus, this);

	}

	public void setRegisteredBefore(boolean registeredBefore) {
		this.registeredBefore = registeredBefore;
	}

	public boolean isRegisteredBefore() {
		return registeredBefore;
	}

	@Override
	public void loginUser(User user) {
		// delegate it to the manager
		userSessionManager.create(user);

	}

	@Override
	public void onUserSessionEvent(UserSessionEvent event) {

		if (event.getStatus() == Status.FAIL
				&& event.getJsonResult().hasFieldErrors()) {
			this.view.onErrors(event.getJsonResult().getFieldErrors());
		} else {
			placeController.goTo(new HomePlace());
		}

	}

}
