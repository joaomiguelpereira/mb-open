package com.medibooking.admin.client.view.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;

import com.google.gwt.user.client.ui.Widget;

public class CheckBoxEditor extends AbstractErrorableBooleanInput {

	private static CheckBoxEditorUiBinder uiBinder = GWT
			.create(CheckBoxEditorUiBinder.class);

	@UiConstructor
	public CheckBoxEditor(String label) {
		this();
		this.checkBoxDelegate.setText(label);
	}
	
	
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




}
