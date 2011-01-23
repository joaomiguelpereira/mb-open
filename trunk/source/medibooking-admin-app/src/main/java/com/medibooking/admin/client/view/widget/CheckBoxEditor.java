package com.medibooking.admin.client.view.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class CheckBoxEditor extends AbstractErrorableBooleanInput implements HasText {

	private static CheckBoxEditorUiBinder uiBinder = GWT
			.create(CheckBoxEditorUiBinder.class);

	
	
	public CheckBoxEditor() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	interface CheckBoxEditorUiBinder extends UiBinder<Widget, CheckBoxEditor> {
	}

	@Override
	protected void addErrorStyle() {
		this.checkBoxDelegate.addStyleName(resources.editorCss().fieldWithErrors());
		
	}

	@Override
	protected void removeErrorStyle() {
		this.checkBoxDelegate.removeStyleName(resources.editorCss().fieldWithErrors());
		
	}


	@Override
	public String getText() {
		return this.checkBoxDelegate.getText();

	}


	@Override
	public void setText(String text) {
		this.checkBoxDelegate.setText(text);
		
	}






}
