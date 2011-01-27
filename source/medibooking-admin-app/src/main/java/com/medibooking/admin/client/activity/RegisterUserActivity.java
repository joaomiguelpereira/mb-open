package com.medibooking.admin.client.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.medibooking.admin.client.event.CreateUserResultAvailableEvent;
import com.medibooking.admin.client.rest.service.UserService;
import com.medibooking.admin.client.view.IRegisterUserView;
import com.medibooking.admin.shared.entity.User;

public class RegisterUserActivity extends WebAppActivity implements
		IRegisterUserView.Presenter {

	private IRegisterUserView view;
	private UserService service;
	private CreateUserResultAvailableEvent.Handler createUserResultEventHandler;

	@Inject
	public RegisterUserActivity(PlaceController placeController,
			IRegisterUserView view, UserService userService) {
		this.view = view;
		this.placeController = placeController;
		this.view.setPresenter(this);
		this.service = userService;
		
		this.createUserResultEventHandler = new CreateUserResultAvailableEvent.Handler() {
			
			@Override
			public void onResultAvailable(CreateUserResultAvailableEvent event) {
				Window.alert(event.getJsonResult().getJsonString());
				
			}
		};
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.eventBus = eventBus;
		panel.setWidget(this.view);
		this.view.setUser(new User());
		this.view.initialize();
		//if this event is registers, then it's ignored
		CreateUserResultAvailableEvent.register(eventBus, this.createUserResultEventHandler).hashCode();
		
		
				
	}

	@Override
	public void saveUser(User user) {
		// delegate to the service
		this.service.createUser(user);
		// Register for event

	}

}
