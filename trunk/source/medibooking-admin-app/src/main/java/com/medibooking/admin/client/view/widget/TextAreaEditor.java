package com.medibooking.admin.client.view.widget;

import com.google.gwt.core.client.GWT;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

public class TextAreaEditor extends AbstractErroableText{

	private static TextAreaEditoUiBinder uiBinder = GWT
			.create(TextAreaEditoUiBinder.class);

	interface TextAreaEditoUiBinder extends UiBinder<Widget, TextAreaEditor> {
	}

	public TextAreaEditor() {
		initWidget(uiBinder.createAndBindUi(this));
	}
}
