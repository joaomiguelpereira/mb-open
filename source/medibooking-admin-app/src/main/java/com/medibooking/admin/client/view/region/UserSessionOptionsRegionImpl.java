package com.medibooking.admin.client.view.region;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.medibooking.admin.client.place.LoginPlace;
import com.medibooking.admin.client.place.RegisterUserPlace;

public class UserSessionOptionsRegionImpl extends Composite implements
		IUserSessionOptionsRegion {

	private static final Binder BINDER = GWT.create(Binder.class);

	@UiField
	Anchor loginLink;
	@UiField
	Anchor logoutLink;
	@UiField
	Element userName;
	@UiField
	Anchor registerLink;
	@UiField
	Anchor optionsLink;
	IUserSessionOptionsRegion.Presenter presenter;

	public UserSessionOptionsRegionImpl() {
		initWidget(BINDER.createAndBindUi(this));
		this.logoutLink.setVisible(false);
		optionsLink.setVisible(false);
	}

	interface Binder extends UiBinder<Widget, UserSessionOptionsRegionImpl> {
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
	void onLonginClicked(ClickEvent event) {
		presenter.goTo(new LoginPlace(false));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;

	}

	@Override
	public void setSessionData(String email) {
		this.loginLink.setVisible(false);
		this.registerLink.setVisible(false);
		this.logoutLink.setVisible(true);
		this.userName.setInnerHTML(email);
		this.optionsLink.setVisible(true);
		

	}

	@Override
	public void clearSessionDate() {
		this.loginLink.setVisible(true);
		this.registerLink.setVisible(true);
		this.logoutLink.setVisible(false);
		this.userName.setInnerHTML("");
		this.optionsLink.setVisible(false);
		
	}

}
