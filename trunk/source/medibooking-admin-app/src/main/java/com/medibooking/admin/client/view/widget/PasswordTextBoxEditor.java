package com.medibooking.admin.client.view.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

public class PasswordTextBoxEditor extends AbstractErroableText  {

	private static PasswordTextBoxEditorUiBinder uiBinder = GWT
			.create(PasswordTextBoxEditorUiBinder.class);

	interface PasswordTextBoxEditorUiBinder extends
			UiBinder<Widget, PasswordTextBoxEditor> {
	}

	public PasswordTextBoxEditor() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	

	public PasswordTextBoxEditor(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}


}
