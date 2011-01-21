package com.medibooking.admin.client.view.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.medibooking.admin.client.view.resources.GlobalResources;
import com.medibooking.admin.shared.entity.User;

public class UserEditor extends Composite implements Editor<User> {

	private static UserEditor1UiBinder uiBinder = GWT
			.create(UserEditor1UiBinder.class);

	GlobalResources resourecs = GlobalResources.INSTANCE;
	@UiField
	TextBox name;
	
	@UiField
	TextBox email;
	
	@UiField
	TextBox emailConfirmation;
	
	@UiField
	TextBox phone;
	
	@UiField
	TextArea address;
	
	@UiField
	PasswordTextBox password;
	
	@UiField
	PasswordTextBox passwordConfirmation;
	
	@UiField
	CheckBox termsAgreement;
	
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

	@UiHandler("submit") 
	void onSubmit(ClickEvent event) {
		//validate just to test the errors
		if ( name.getValue().isEmpty() ) {
			//showErrorFor(name);
			
			name.addStyleName(resourecs.editorCss().fieldWithErrors());
		}
		
	}
	private void showErrorFor() {
		
	}
	interface UserEditor1UiBinder extends UiBinder<Widget, UserEditor> {
	}

}
