package com.medibooking.admin.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.medibooking.admin.client.view.RegisterUserView.Driver;
import com.medibooking.admin.client.view.editor.LoginEditor;
import com.medibooking.admin.client.view.editor.UserEditor;
import com.medibooking.admin.shared.entity.User;

public class LoginView extends Composite implements ILoginView {
	
	private static final Binder BINDER = GWT.create(Binder.class);
	
	private Presenter presenter;
	private boolean registeredBefore;
	private User user;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	Driver driver = GWT.create(Driver.class);
	interface Driver extends SimpleBeanEditorDriver<User, LoginEditor> {
	}
	
	@UiField
	LoginEditor loginEditor;
	
	public LoginView() {
		initWidget(BINDER.createAndBindUi(this));
		//register click handler
		loginEditor.getSubmit().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				handleSubmit();
				
			}

			
		});
	}

	private void handleSubmit() {
		user = driver.flush();
		presenter.loginUser(user);
		
	}
	interface Binder extends UiBinder<ScrollPanel, LoginView> {
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
		
	}

	@Override
	public void setRegisteredBefore(boolean registeredBefore) {
		this.registeredBefore = registeredBefore;
		
	}

	@Override
	public void initialize() {
		// Initialize driver.
		driver.initialize(loginEditor);
		driver.edit(this.user);
		loginEditor.clearErrors();
		
	}

}
