package com.medibooking.admin.client.view.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import com.medibooking.admin.client.view.widget.CheckBoxEditor;
import com.medibooking.admin.client.view.widget.PasswordTextBoxEditor;
import com.medibooking.admin.client.view.widget.TextAreaEditor;
import com.medibooking.admin.client.view.widget.TextBoxEditor;
import com.medibooking.admin.shared.entity.User;

public class UserEditor extends Composite implements Editor<User> {

	private static UserEditor1UiBinder uiBinder = GWT
			.create(UserEditor1UiBinder.class);

	
	@UiField
	TextBoxEditor name;
	
	@UiField
	TextBoxEditor email;
	
	@UiField
	TextBoxEditor emailConfirmation;
	
	@UiField
	TextBoxEditor phone;
	
	@UiField
	TextAreaEditor address;
	
	@UiField
	PasswordTextBoxEditor password;
	
	@UiField
	PasswordTextBoxEditor passwordConfirmation;
	
	@UiField
	CheckBoxEditor termsAgreement;
	
	@UiField
	HasClickHandlers submit;
	
	/**
	 * Const.
	 */
	public UserEditor() {
		initWidget(uiBinder.createAndBindUi(this));
		
		//Set focus on first field "name"
		this.addAttachHandler(new AttachEvent.Handler() {
			@Override
			public void onAttachOrDetach(AttachEvent event) {
				name.setFocus(true);
				
			}
		});
		
	}
	
	public HasClickHandlers getSubmit() {
		return this.submit;
	}
	
	@UiHandler("submit") 
	void onSubmit(ClickEvent event) {
		
		//validate just to test the errors
		if (name.hasErrors() ) {
			name.clearErrors();
			email.clearErrors();
			emailConfirmation.clearErrors();
			password.clearErrors();
			passwordConfirmation.clearErrors();
			address.clearErrors();
			phone.clearErrors();
			termsAgreement.clearErrors();
		} else {
			
			//name.setErrors(new String[] {"Error Message 1","Error Message 2", "Error Message 3"});
			name.setError("One error");
			email.setErrors(new String[] {"error 1", "error 2"});
			emailConfirmation.setErrors(new String[] {"error 3", "error 4"});
			password.setError("One fucking error");
			passwordConfirmation.setErrors(new String[] {"error pass", "error pass21"});
			address.setError("teste");
			phone.setError("Hello");
			termsAgreement.setErrors(new String[] {"ahsgdjas", "aasd"});
			
		}
	}
	
	interface UserEditor1UiBinder extends UiBinder<Widget, UserEditor> {
	}

}
