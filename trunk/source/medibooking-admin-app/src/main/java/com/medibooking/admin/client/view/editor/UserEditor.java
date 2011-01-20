package com.medibooking.admin.client.view.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.medibooking.admin.shared.entity.User;

public class UserEditor extends Composite implements Editor<User> {

	private static UserEditor1UiBinder uiBinder = GWT
			.create(UserEditor1UiBinder.class);

	interface UserEditor1UiBinder extends UiBinder<Widget, UserEditor> {
	}

	public UserEditor() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
