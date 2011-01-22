package com.medibooking.admin.client.view.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.ui.client.ValueBoxEditorDecorator;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;


//TODO: MAke it work
public class CompositeErrorDecorator extends AbstractErroableComposite {

	private static CompositeErrorDecoratorUiBinder uiBinder = GWT
			.create(CompositeErrorDecoratorUiBinder.class);

	interface CompositeErrorDecoratorUiBinder extends
			UiBinder<Widget, CompositeErrorDecorator> {
	}

	@UiConstructor
	public CompositeErrorDecorator() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiChild
	public void addRadioButton(RadioButton rb) {
		
	}

	@Override
	protected void addErrorStyle() {
		// No style for the main element
		ValueBoxEditorDecorator<String> test;

	}

	@Override
	protected void removeErrorStyle() {
		// No style for the main element

	}

}
