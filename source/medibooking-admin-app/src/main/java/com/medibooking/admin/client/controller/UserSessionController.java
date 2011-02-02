package com.medibooking.admin.client.controller;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;

import com.medibooking.admin.client.mvp.AbstractController;
import com.medibooking.admin.client.mvp.AbstractWebAppActivity;
import com.medibooking.admin.client.view.region.IUserSessionOptionsRegion;

/**
 * Global controller to handle user logins, logouts, etc
 * 
 * @author jpereira
 * 
 */
public class UserSessionController extends AbstractController implements IUserSessionOptionsRegion.Presenter {

	//This is injected in the reverse as compared with views
	private IUserSessionOptionsRegion view;

	@Inject
	public UserSessionController(EventBus eventBus, 
			PlaceController placeController, IUserSessionOptionsRegion view) {
		this.eventBus = eventBus;
		this.placeController = placeController;
		this.view = view;
		view.setPresenter(this);
		
	}


	@Override
	public void destroyUserSession() {
		Window.alert("HEllo");
		
	}



	
}
