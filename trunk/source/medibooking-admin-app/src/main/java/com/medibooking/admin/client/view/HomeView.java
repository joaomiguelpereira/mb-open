package com.medibooking.admin.client.view;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.medibooking.admin.client.place.LoginPlace;
import com.medibooking.admin.client.place.RegisterUserPlace;

public class HomeView extends Composite implements IHomeView {

	
	private static final Binder BINDER = GWT.create(Binder.class);
	private static final Logger log = Logger.getLogger(HomeView.class.getName());
	private Presenter presenter = null;
	@UiField HTMLPanel content;
	@UiField Anchor loginLink;
	@UiField Anchor registerLink;
	
	
	public HomeView() {
		initWidget(BINDER.createAndBindUi(this));		
	}
	
	@UiHandler("registerLink")
	void onRegisterClicked(ClickEvent event) {
		presenter.goTo(new RegisterUserPlace());
	}
	
	@UiHandler("loginLink")
	void onLoginCliked(ClickEvent event) {
		presenter.goTo(new LoginPlace());
	}
	
	
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;	
	}
	
	interface Binder extends UiBinder<ScrollPanel, HomeView> {
		 
	}

}
