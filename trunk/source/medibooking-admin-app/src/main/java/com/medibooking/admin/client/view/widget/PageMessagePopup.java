package com.medibooking.admin.client.view.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.medibooking.admin.client.view.MessageType;
import com.medibooking.admin.client.view.resources.GlobalResources;

public class PageMessagePopup extends PopupPanel {
	
	private static final String DEFAULT_HEIGH = "2em";
	private String message;
	private MessageType type;
	private Composite parent;
	public PageMessagePopup(String message, MessageType type, Composite parent) {
		super(true);
		this.message = message;
		this.type = type;
		this.parent = parent;
		
	}
	
	
	@Override
	public void show() {
		StringBuilder sb = new StringBuilder();

		sb.append("<div>");
		sb.append(message);
		sb.append("</div>");
		
		
		//final PopupPanel messagePopup = new PopupPanel(true);
		HTML messageHtml = new HTML(sb.toString());
		
		Anchor closeLink = new Anchor("X");
		closeLink.addStyleName(GlobalResources.INSTANCE.css().messageClose());
		AbsolutePanel absPanel = new AbsolutePanel();
		
		absPanel.add(messageHtml);
		absPanel.add(closeLink);		
		
		
		this.add(absPanel);
		this.setAnimationEnabled(true);
		
		
		// get parent width

		this.setWidth(parent.getOffsetWidth() + "px");
		//Calculate the center just to adjust the left
		

		
		this.setPopupPosition(0, 0);
		absPanel.insert(closeLink, parent.getOffsetWidth()-30, 0, 0);
		absPanel.setHeight(DEFAULT_HEIGH);
		

		switch (type) {
		case ERROR:
			this.addStyleName(GlobalResources.INSTANCE.css()
					.messageWithError());
			break;

		}
		
		//register handler 
		
		closeLink.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				hide();
				
			}
		});
		// Create a new timer that calls Window.alert().
		Timer t = new Timer() {
			public void run() {
				
				hide();
				this.cancel();
			}
		};

		super.show();
		// Schedule the timer to run once in 4 seconds.
		t.schedule(5000);
		
	}

}
