package com.medibooking.admin.client.view.widget;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.medibooking.admin.client.view.resources.GlobalResources;

public class ProcessingIndicator extends PopupPanel {

	private static final ProcessingIndicator INSTANCE = new ProcessingIndicator();
	
	private ProcessingIndicator() {
		super();
		setAnimationEnabled(false);
		setAutoHideEnabled(false);
		setGlassEnabled(true);
		setGlassStyleName(GlobalResources.INSTANCE.css().glass());
		
		setHeight("20px");
		setWidth("140px");
		addStyleName(GlobalResources.INSTANCE.css().processingIndicator());
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.add(new Image(GlobalResources.INSTANCE.processingImage()));
		hPanel.add(new HTML("Espere por favor..."));
		
		add(hPanel);
		
		
		center();
		setPopupPosition(0, 0);
		
		
	}

	public static void start() {
		INSTANCE.show();
		
	}

	public static void stop() {
		INSTANCE.hide();
		
	}
}
