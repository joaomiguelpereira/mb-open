package com.medibooking.admin.client.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.medibooking.admin.client.view.IRegisterUserView;

public class RegisterUserActivity extends WebAppActivity implements IRegisterUserView.Presenter{

	private IRegisterUserView view;
	
	@Inject
	public RegisterUserActivity(PlaceController placeController, IRegisterUserView view) {
		this.view = view;
		this.placeController = placeController;
		this.view.setPresenter(this);
	}
	
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.eventBus = eventBus;
		panel.setWidget(this.view);
		
	}

}
