package com.medibooking.admin.client.activity;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.medibooking.admin.client.event.AbstractJsonResultAvailableEvent;
import com.medibooking.admin.client.event.CreateSessionResultAvailableEvent;
import com.medibooking.admin.client.manager.UserSessionManager;
import com.medibooking.admin.client.mvp.AbstractWebAppActivity;
import com.medibooking.admin.client.place.HomePlace;
import com.medibooking.admin.client.rest.service.UserSessionService;
import com.medibooking.admin.client.view.ILoginView;
import com.medibooking.admin.shared.entity.User;

public class LoginActivity extends AbstractWebAppActivity implements
		ILoginView.Presenter, CreateSessionResultAvailableEvent.Handler {

	private static Logger log = Logger.getLogger(LoginActivity.class.getName());
	private final ILoginView view;
	private boolean registeredBefore;
	private UserSessionService service;
	private UserSessionManager sessionManager;

	@Inject
	public LoginActivity(UserSessionManager sessionManager,
			UserSessionService service, ILoginView view,
			PlaceController placeController) {
		this.placeController = placeController;
		this.view = view;
		this.service = service;
		this.sessionManager = sessionManager;
		this.view.setPresenter(this);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.eventBus = eventBus;
		panel.setWidget(view);
		this.view.setUser(new User());
		this.view.initialize();
		CreateSessionResultAvailableEvent.register(eventBus, this);

	}

	public void setRegisteredBefore(boolean registeredBefore) {
		this.registeredBefore = registeredBefore;
	}

	public boolean isRegisteredBefore() {
		return registeredBefore;
	}

	@Override
	public void loginUser(User user) {

		service.create(user);

	}

	@Override
	public void onJsonResultAvailable(AbstractJsonResultAvailableEvent<?> event) {
		if (event.getJsonResult().hasErrors()) {
			this.view.onErrors(event.getJsonResult());
		} else {
			this.sessionManager.startSession(event.getJsonResult()
					.getStringProperty("sessionId"), event.getJsonResult()
					.getStringProperty("email"),
					event.getJsonResult().getIntegerProperty("duration"));
			this.placeController.goTo(new HomePlace());
		}

	}

}
