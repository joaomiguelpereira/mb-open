package com.medibooking.admin.client.view.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.medibooking.admin.shared.entity.User;

public class UserEditor extends Composite implements Editor<User> {

	private static UserEditor1UiBinder uiBinder = GWT
			.create(UserEditor1UiBinder.class);

	@UiField
	TextBox name;
	
	public UserEditor() {
		initWidget(uiBinder.createAndBindUi(this));
		
		//Set focus on first field
		this.addAttachHandler(new AttachEvent.Handler() {
			@Override
			public void onAttachOrDetach(AttachEvent event) {
				name.setFocus(true);
				
			}
		});
	}

	interface UserEditor1UiBinder extends UiBinder<Widget, UserEditor> {
	}

}
