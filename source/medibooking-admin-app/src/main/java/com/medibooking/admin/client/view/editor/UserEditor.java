package com.medibooking.admin.client.view.editor;



import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.medibooking.admin.client.view.widget.CheckBoxEditor;
import com.medibooking.admin.client.view.widget.Errorable;
import com.medibooking.admin.client.view.widget.PasswordTextBoxEditor;
import com.medibooking.admin.client.view.widget.TextAreaEditor;
import com.medibooking.admin.client.view.widget.TextBoxEditor;
import com.medibooking.admin.shared.entity.User;

public class UserEditor extends Composite implements Editor<User> {

	private static UserEditorUiBinder uiBinder = GWT
			.create(UserEditorUiBinder.class);

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

	public Errorable getName() {
		return this.name;
	}

	/**
	 * Const.
	 */
	public UserEditor() {
		initWidget(uiBinder.createAndBindUi(this));
		// Add the validations

		// Set focus on first field "name"
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

	public void clearErrors() {
		name.clearErrors();
		email.clearErrors();
		emailConfirmation.clearErrors();
		password.clearErrors();
		passwordConfirmation.clearErrors();
		address.clearErrors();
		phone.clearErrors();
		termsAgreement.clearErrors();
	}

	interface UserEditorUiBinder extends UiBinder<Widget, UserEditor> {
	}

}
