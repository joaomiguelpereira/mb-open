package com.medibooking.admin.client.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.medibooking.admin.client.rest.service.UserService;
import com.medibooking.admin.client.view.IRegisterUserView;
import com.medibooking.admin.shared.entity.User;


public class RegisterUserActivity extends WebAppActivity implements IRegisterUserView.Presenter{

	private IRegisterUserView view;
	private UserService service;
		
	@Inject
	public RegisterUserActivity(PlaceController placeController, IRegisterUserView view, UserService userService) {
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
	}


	@Override
	public void saveUser(User user) {
		//delegate to the service
		this.service.createUser(user);
		
	}

}
