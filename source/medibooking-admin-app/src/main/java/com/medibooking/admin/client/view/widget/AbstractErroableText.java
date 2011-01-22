package com.medibooking.admin.client.view.widget;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.medibooking.admin.client.view.resources.GlobalResources;

public abstract class AbstractErroableText extends Composite implements
		Focusable, HasValue<String>, Errorable {

	protected boolean hasErrors = false;
	private GlobalResources resources = GlobalResources.INSTANCE;

	@UiField
	TextBoxBase text;

	@UiField
	DivElement errorMessagesContainer;

	@Override
	public int getTabIndex() {
		return this.text.getTabIndex();
	}

	@Override
	public void setAccessKey(char key) {
		this.text.setAccessKey(key);

	}

	@Override
	public void setFocus(boolean focused) {
		this.text.setFocus(focused);

	}

	@Override
	public void setTabIndex(int index) {
		this.text.setTabIndex(index);
	}

	@Override
	public String getValue() {
		return this.text.getValue();
	}

	@Override
	public void setValue(String value) {
		this.text.setValue(value);

	}

	@Override
	public void setValue(String value, boolean fireEvents) {
		this.text.setValue(value, fireEvents);

	}

	@Override
	public void setError() {
		// Add the error style
		this.text.addStyleName(resources.editorCss().fieldWithErrors());
		this.hasErrors = true;

	}

	@Override
	public void setError(String message) {
		this.setError();
		// Add the message to the div
		this.errorMessagesContainer
				.setInnerHTML("<div><ul><li style='list-style: url(\""
						+ resources.listWarningIcon().getURL() + "\")'>"
						+ SafeHtmlUtils.fromString(message).asString()
						+ "</li></ul></div>");

	}

	@Override
	public void setErrors(String[] messages) {
		this.setError();
		StringBuilder innerHtml = new StringBuilder();

		innerHtml.append("<div><ul>");
		for (String message : messages) {
			innerHtml.append("<li style='list-style: url(\""
					+ resources.listWarningIcon().getURL() + "\")'>");
			innerHtml.append(SafeHtmlUtils.fromString(message).asString());
			innerHtml.append("</li>");
		}
		innerHtml.append("</ul></div>");
		this.errorMessagesContainer.setInnerHTML(innerHtml.toString());
	}

	@Override
	public void clearErrors() {
		// TODO Auto-generated method stub
		this.text.removeStyleName(resources.editorCss().fieldWithErrors());
		this.errorMessagesContainer.setInnerHTML("");
		this.hasErrors = false;

	}

	@Override
	public boolean hasErrors() {
		return this.hasErrors;
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<String> handler) {
		// TODO Auto-generated method stub
		return null;
	}

}
