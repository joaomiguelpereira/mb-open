package com.medibooking.admin.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;

public class LoginView extends Composite implements ILoginView {
	
	private static final Binder BINDER = GWT.create(Binder.class);
	private Presenter presenter;
	
	public LoginView() {
		initWidget(BINDER.createAndBindUi(this));
	}

	interface Binder extends UiBinder<ScrollPanel, LoginView> {
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
		
	}

}
