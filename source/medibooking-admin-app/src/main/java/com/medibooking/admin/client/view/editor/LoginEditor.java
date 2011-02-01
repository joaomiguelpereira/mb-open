package com.medibooking.admin.client.view.editor;

import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.medibooking.admin.client.view.widget.CheckBoxEditor;
import com.medibooking.admin.client.view.widget.PasswordTextBoxEditor;
import com.medibooking.admin.client.view.widget.TextBoxEditor;
import com.medibooking.admin.shared.entity.User;

public class LoginEditor extends Composite implements CompositeEditor<User> {

	private static LoginEditorUiBinder uiBinder = GWT
			.create(LoginEditorUiBinder.class);

	private static LoginEditorErrorBinder errorBinder = GWT
			.create(LoginEditorErrorBinder.class);

	@UiField
	TextBoxEditor email;
	
	@UiField
	PasswordTextBoxEditor password;
	
	@UiField
	CheckBoxEditor keepLogged;
	
	@UiField
	HasClickHandlers submit;
	
	public HasClickHandlers getSubmit() {
		return submit;
	}

	public void setSubmit(HasClickHandlers submit) {
		this.submit = submit;
	}

	interface LoginEditorUiBinder extends UiBinder<Widget, LoginEditor> {
	}

	interface LoginEditorErrorBinder extends ErrorBinder<LoginEditor> {

	}

	public LoginEditor() {
		initWidget(uiBinder.createAndBindUi(this));
		// Set focus on first field "email"
		this.addAttachHandler(new AttachEvent.Handler() {
			@Override
			public void onAttachOrDetach(AttachEvent event) {
				email.setFocus(true);
			}
		});
	}

	@Override
	public void setFieldErrors(Map<String, List<String>> fieldErrors) {
		errorBinder.bindErrors(this, fieldErrors);
	}

	@Override
	public void clearErrors() {
		email.clearErrors();
		password.clearErrors();
		
	}

}
