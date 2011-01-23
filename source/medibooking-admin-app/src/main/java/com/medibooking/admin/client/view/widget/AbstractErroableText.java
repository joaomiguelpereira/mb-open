package com.medibooking.admin.client.view.widget;

import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.ui.client.adapters.ValueBoxEditor;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBoxBase;

public  abstract class AbstractErroableText extends AbstractErroableComposite implements
		Focusable, HasValue<String>, Errorable,  IsEditor<ValueBoxEditor<String>>{


	@UiField
	TextBoxBase textDelegate;

	@Override
	public ValueBoxEditor<String> asEditor() {
		return textDelegate.asEditor();
	}

	@Override
	public int getTabIndex() {
		return this.textDelegate.getTabIndex();
	}

	@Override
	public void setAccessKey(char key) {
		this.textDelegate.setAccessKey(key);

	}

	@Override
	public void setFocus(boolean focused) {
		this.textDelegate.setFocus(focused);

	}

	@Override
	public void setTabIndex(int index) {
		this.textDelegate.setTabIndex(index);
	}

	@Override
	public String getValue() {
		return this.textDelegate.getValue();
	}

	@Override
	public void setValue(String value) {
		this.textDelegate.setValue(value);

	}

	@Override
	public void setValue(String value, boolean fireEvents) {
		this.textDelegate.setValue(value, fireEvents);

	}

	
	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<String> handler) {
		return this.textDelegate.addValueChangeHandler(handler);
		
	}

	@Override
	protected void addErrorStyle() {
		this.textDelegate.addStyleName(resources.editorCss().fieldWithErrors());
		
	}

	@Override
	protected void removeErrorStyle() {
		this.textDelegate.removeStyleName(resources.editorCss().fieldWithErrors());
		
	}

}
