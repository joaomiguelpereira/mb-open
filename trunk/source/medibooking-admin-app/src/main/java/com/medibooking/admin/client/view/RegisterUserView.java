package com.medibooking.admin.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.medibooking.admin.client.view.editor.UserEditor;
import com.medibooking.admin.shared.entity.User;

public class RegisterUserView extends Composite implements IRegisterUserView{

	
	@UiField
	UserEditor userEditor;

	interface Driver extends SimpleBeanEditorDriver<User, UserEditor> {}
	Driver driver = GWT.create(Driver.class);
	
	private static final Binder BINDER = GWT.create(Binder.class);
	private Presenter presenter;
	
	
	public RegisterUserView() {
		initWidget(BINDER.createAndBindUi(this));
		driver.initialize(userEditor);
		driver.edit(new User());
		//register submit handle
		
		
		this.userEditor.getSubmit().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				handleSubmitUser();
			}
		});
		
		
		
	}
	
	private void handleSubmitUser() {

		User edited = driver.flush();
		if ( driver.hasErrors() ){
			Window.alert("errore");
		}
		Window.alert("Name:"+edited.getName());
		Window.alert("email:"+edited.getEmail());
		Window.alert("Password:"+edited.getPassword());
		Window.alert("Address:"+edited.getAddress());
		Window.alert("Terms:"+edited.getTermsAgreement());
		
	}
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
		
	}
	
	interface Binder extends UiBinder<ScrollPanel, RegisterUserView> {
		 
	}

	

}
