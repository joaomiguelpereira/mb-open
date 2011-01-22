package com.medibooking.admin.client.view.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;

import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;

import com.google.gwt.user.client.ui.Widget;

public class CheckBoxEditor extends Composite implements HasValue<Boolean> {

	private static CheckBoxEditorUiBinder uiBinder = GWT
			.create(CheckBoxEditorUiBinder.class);

	@UiField
	CheckBox checkBoxDelegate;

	@UiConstructor
	public CheckBoxEditor(String label) {
		this();
		this.checkBoxDelegate.setText(label);
	}
	
	/*@UiChild(limit=1)
	public void addHtml(HTML text) {
		this.checkBox.setText(text.toString());
	}*/
	/*public CheckBoxEditor(String text) {
		this(); //Call no-arg const.
		this.checkBox.setText(text);
	}
	
	public CheckBoxEditor(SafeHtml html) {
		this(html.asString());
	}*/
	
	public CheckBoxEditor() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	interface CheckBoxEditorUiBinder extends UiBinder<Widget, CheckBoxEditor> {
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<Boolean> handler) {
		return this.checkBoxDelegate.addValueChangeHandler(handler);
	}

	@Override
	public Boolean getValue() {
		return this.checkBoxDelegate.getValue();
	}

	@Override
	public void setValue(Boolean value) {
		this.checkBoxDelegate.setValue(value);
	}

	@Override
	public void setValue(Boolean value, boolean fireEvents) {
		this.checkBoxDelegate.setValue(value, fireEvents);
		
	}


}
