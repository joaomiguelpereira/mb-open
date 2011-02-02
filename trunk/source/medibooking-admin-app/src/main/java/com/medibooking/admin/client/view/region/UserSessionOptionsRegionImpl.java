package com.medibooking.admin.client.view.region;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.medibooking.admin.client.place.LoginPlace;
import com.medibooking.admin.client.place.RegisterUserPlace;


public class UserSessionOptionsRegionImpl extends Composite implements IUserSessionOptionsRegion {

	private static final Binder BINDER = GWT.create(Binder.class);

	public UserSessionOptionsRegionImpl() {
		initWidget(BINDER.createAndBindUi(this));
	}
	
	interface Binder extends UiBinder<Widget, UserSessionOptionsRegionImpl> {
	}

	@UiField
	HasClickHandlers loginLink;
	@UiField
	HasClickHandlers logoutLink;
	@UiField
	Element userName;
	@UiField
	HasClickHandlers registerLink;
	IUserSessionOptionsRegion.Presenter presenter;

	
	public void setUserName(String userName) {
		this.userName.setInnerText(userName);
	}



	@UiHandler("logoutLink")
	void onLogoutClicked(ClickEvent event) {
		presenter.destroyUserSession();
	}


	@UiHandler("registerLink")
	void onRegisterClicked(ClickEvent event) {
		presenter.goTo(new RegisterUserPlace());
	}
	
	@UiHandler("loginLink")
	void onLonginClicked(ClickEvent event){
		presenter.goTo(new LoginPlace(false));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
		
	}
	
	
	

}
