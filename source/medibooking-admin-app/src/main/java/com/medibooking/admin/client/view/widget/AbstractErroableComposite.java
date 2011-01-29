package com.medibooking.admin.client.view.widget;

import java.util.List;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.medibooking.admin.client.view.resources.GlobalResources;

public abstract class AbstractErroableComposite extends Composite implements Errorable {
	

	@UiField
	DivElement errorMessagesContainer;
	private boolean hasErrors = false;
	protected GlobalResources resources = GlobalResources.INSTANCE;

	protected abstract void addErrorStyle();
		
	
	protected abstract  void removeErrorStyle();
	@Override
	public void setError() {
		// Add the error style
		addErrorStyle();
		//this.text.addStyleName(resources.editorCss().fieldWithErrors());
		this.hasErrors  = true;

	}
	@Override
	public void setError(String message) {
		this.setError();
		// Add the message to the div
		this.errorMessagesContainer
				.setInnerHTML("<div><ul><li style='list-style: url(\""
						+ resources.warningIcon().getURL() + "\")'>"
						+ SafeHtmlUtils.fromString(message).asString()
						+ "</li></ul></div>");

	}

	
	@Override
	public void setErrors(List<String> messages) {
		this.setError();
		StringBuilder innerHtml = new StringBuilder();

		innerHtml.append("<div><ul>");
		for (String message : messages) {
			innerHtml.append("<li style='list-style: url(\""
					+ resources.warningIcon().getURL() + "\")'>");
			innerHtml.append(SafeHtmlUtils.fromString(message).asString());
			innerHtml.append("</li>");
		}
		innerHtml.append("</ul></div>");
		this.errorMessagesContainer.setInnerHTML(innerHtml.toString());
	}

	@Override
	public void clearErrors() {
		// TODO Auto-generated method stub
		removeErrorStyle();
		//this.text.removeStyleName(resources.editorCss().fieldWithErrors());
		this.errorMessagesContainer.setInnerHTML("");
		this.hasErrors = false;

	}

	@Override
	public boolean hasErrors() {
		return this.hasErrors;
	}
	


}
