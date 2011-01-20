package com.medibooking.admin.client.view.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;


public class UserSessionOptionsWidget extends Composite {

	private static final Binder BINDER = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, UserSessionOptionsWidget> {
	}

	@UiField
	HasClickHandlers loginLink;
	@UiField
	HasClickHandlers logoutLink;
	@UiField
	Element userName;
	@UiField
	HasClickHandlers registerLink;

	
	public void setUserName(String userName) {
		this.userName.setInnerText(userName);
	}

	public UserSessionOptionsWidget() {
		initWidget(BINDER.createAndBindUi(this));
	}

	public HasClickHandlers getLoginLink() {
		return loginLink;
	}

	public void setLoginLink(HasClickHandlers loginLink) {
		this.loginLink = loginLink;
	}

	public HasClickHandlers getLogoutLink() {
		return logoutLink;
	}

	public void setLogoutLink(HasClickHandlers logoutLink) {
		this.logoutLink = logoutLink;
	}

	public HasClickHandlers getRegisterLink() {
		return registerLink;
	}

	public void setRegisterLink(HasClickHandlers registerLink) {
		this.registerLink = registerLink;
	}

	

}
