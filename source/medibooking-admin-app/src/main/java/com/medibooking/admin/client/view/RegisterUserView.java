package com.medibooking.admin.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;

public class RegisterUserView extends Composite implements IRegisterUserView{

	
	//Boilerplate stuff
	private static final Binder BINDER = GWT.create(Binder.class);
	private Presenter presenter;
	
	
	public RegisterUserView() {
		initWidget(BINDER.createAndBindUi(this));
		
		
	}
	
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
		
	}
	
	interface Binder extends UiBinder<ScrollPanel, RegisterUserView> {
		 
	}

}
