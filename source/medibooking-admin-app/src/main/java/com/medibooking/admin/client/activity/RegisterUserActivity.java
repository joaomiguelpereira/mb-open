package com.medibooking.admin.client.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.medibooking.admin.client.event.AbstractJsonResultAvailableEvent;
import com.medibooking.admin.client.event.CreateUserResultAvailableEvent;
import com.medibooking.admin.client.mvp.AbstractWebAppActivity;
import com.medibooking.admin.client.place.LoginPlace;
import com.medibooking.admin.client.rest.JsonResult;
import com.medibooking.admin.client.rest.JsonResult.Status;
import com.medibooking.admin.client.rest.service.UserService;
import com.medibooking.admin.client.view.IRegisterUserView;
import com.medibooking.admin.shared.entity.User;
import com.medibooking.admin.shared.entity.UserType;

public class RegisterUserActivity extends AbstractWebAppActivity implements
		IRegisterUserView.Presenter, CreateUserResultAvailableEvent.Handler {

	private IRegisterUserView view;
	private UserService service;

	@Inject
	public RegisterUserActivity(PlaceController placeController,
			IRegisterUserView view, UserService userService) {
		this.view = view;
		this.placeController = placeController;
		this.view.setPresenter(this);
		this.service = userService;

	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.eventBus = eventBus;
		panel.setWidget(this.view);
		this.view.setUser(new User());
		this.view.initialize();
		// if this event is registers, then it's ignored
		CreateUserResultAvailableEvent.register(eventBus, this);

	}

	@Override
	public void saveUser(User user) {
		// delegate to the service
		// This interface is used only to create new BUSINESS_ADMINs
		user.setUserType(UserType.BUSINESS_ADMIN);
		this.service.createUser(user);
		// Register for event

	}

	@Override
	public void onJsonResultAvailable(AbstractJsonResultAvailableEvent<?> event) {
		JsonResult jsonResult = event.getJsonResult();
		// if has errors
		if (jsonResult.getStatus() == Status.FAIL) {
			this.view.onErrors(jsonResult.getFieldErrors());
		} else {
			this.placeController.goTo(new LoginPlace(true));
		}

	}

}
