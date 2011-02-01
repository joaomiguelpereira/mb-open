package com.medibooking.admin.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.medibooking.admin.client.rest.JsonResult;
import com.medibooking.admin.client.view.editor.UserEditor;
import com.medibooking.admin.shared.entity.User;

public class RegisterUserView extends Composite implements IRegisterUserView {

	@UiField
	UserEditor userEditor;

	interface Driver extends SimpleBeanEditorDriver<User, UserEditor> {
	}

	Driver driver = GWT.create(Driver.class);
	private static final Binder BINDER = GWT.create(Binder.class);
	private Presenter presenter;
	private User user;

	public UserEditor getUserEditor() {
		return this.userEditor;
	}

	public RegisterUserView() {
		initWidget(BINDER.createAndBindUi(this));
		this.userEditor.getSubmit().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				handleSubmitUser();

			}
		});
	}

	private void handleSubmitUser() {
		User edited = driver.flush();
		presenter.saveUser(edited);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;

	}

	interface Binder extends UiBinder<ScrollPanel, RegisterUserView> {

	}

	@Override
	public void initialize() {
		// Initialize driver.
		driver.initialize(userEditor);
		driver.edit(this.user);
		userEditor.clearErrors();
		// register submit handle

	}

	@Override
	public void setUser(User user) {
		this.user = user;

	}

	@Override
	public void onErrors(JsonResult jsonResult) {
		// Bind validation errors on the editor
		if ( jsonResult.getFieldErrors() != null && !jsonResult.getFieldErrors().isEmpty()) {
			
			this.userEditor.setFieldErrors(jsonResult.getFieldErrors());
		}
		

	}

	@Override
	public void clearErrors() {
		this.userEditor.clearErrors();
		
	}

}
