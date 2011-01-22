package com.medibooking.admin.client.view.widget;

import com.google.gwt.core.client.GWT;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.HTMLPanel;


public class TextBoxEditor extends AbstractErroableText {

	private static TextBoxEditorUiBinder uiBinder = GWT
			.create(TextBoxEditorUiBinder.class);

	
	
	


	public TextBoxEditor() {
		initWidget(uiBinder.createAndBindUi(this));

	}

	interface TextBoxEditorUiBinder extends UiBinder<HTMLPanel, TextBoxEditor> {
	}

	

}
