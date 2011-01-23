package com.medibooking.admin.client.view.widget;

import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;

import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasValue;

public abstract class AbstractErrorableBooleanInput extends
		AbstractErroableComposite implements HasValue<Boolean>, Focusable,
		IsEditor<LeafValueEditor<Boolean>> {

	@UiField
	CheckBox checkBoxDelegate;

	@Override
	public LeafValueEditor<Boolean> asEditor() {
		return this.checkBoxDelegate.asEditor();
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

	@Override
	public int getTabIndex() {
		return checkBoxDelegate.getTabIndex();
	}

	@Override
	public void setAccessKey(char key) {
		checkBoxDelegate.setAccessKey(key);

	}

	@Override
	public void setFocus(boolean focused) {
		checkBoxDelegate.setFocus(focused);

	}

	@Override
	public void setTabIndex(int index) {
		checkBoxDelegate.setTabIndex(index);

	}

}
