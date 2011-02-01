package com.medibooking.admin.client.view.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.medibooking.admin.client.view.MessageType;
import com.medibooking.admin.client.view.resources.GlobalResources;

public class PageMessagePopup extends PopupPanel {

	// Singleton, Only one message of this at a time....
	private static PageMessagePopup INSTANCE = null;

	private static final String DEFAULT_HEIGH = "2em";
	private String message;
	private MessageType type;
	private Composite parent;
	private AbsolutePanel closeButtonPanel;
	private Anchor closeLink;

	public static void show(String message, MessageType type, Composite parent) {

		if (INSTANCE == null) {
			INSTANCE = new PageMessagePopup();
			// Handle resize events from parent
			Window.addResizeHandler(new ResizeHandler() {

				@Override
				public void onResize(ResizeEvent event) {
					if (INSTANCE.isVisible()) {
						INSTANCE.setWidth(event.getWidth() + "px");
						INSTANCE.closeButtonPanel.insert(INSTANCE.closeLink,
								event.getWidth() - 30, 0, 0);

					}

				}
			});
		}
		INSTANCE.message = message;
		INSTANCE.type = type;
		INSTANCE.parent = parent;
		//Clear style names
		INSTANCE.removeStyleName(GlobalResources.INSTANCE.css()
				.messageWithError());
		INSTANCE.removeStyleName(GlobalResources.INSTANCE.css()
				.messageWithWarning());
		INSTANCE.removeStyleName(GlobalResources.INSTANCE.css()
				.messageWithSuccess());

		INSTANCE.removeStyleName(GlobalResources.INSTANCE.css()
				.messageWithInfo());

		INSTANCE.clear();
		INSTANCE.show();

	}

	private PageMessagePopup() {
		super(true);

	}

	@Override
	public void show() {
		StringBuilder sb = new StringBuilder();

		sb.append("<div>");
		sb.append(message);
		sb.append("</div>");

		// final PopupPanel messagePopup = new PopupPanel(true);
		HTML messageHtml = new HTML(sb.toString());

		closeLink = new Anchor("X");
		closeLink.addStyleName(GlobalResources.INSTANCE.css().messageClose());
		closeButtonPanel = new AbsolutePanel();

		closeButtonPanel.add(messageHtml);
		closeButtonPanel.add(closeLink);

		this.add(closeButtonPanel);
		this.setAnimationEnabled(true);

		// get parent width

		this.setWidth(parent.getOffsetWidth() + "px");
		// Calculate the center just to adjust the left

		this.setPopupPosition(0, 0);
		closeButtonPanel.insert(closeLink, parent.getOffsetWidth() - 30, 0, 0);
		closeButtonPanel.setHeight(DEFAULT_HEIGH);

		switch (type) {
		case ERROR:
			this.addStyleName(GlobalResources.INSTANCE.css().messageWithError());
			break;
		case WARNING:
			this.addStyleName(GlobalResources.INSTANCE.css()
					.messageWithWarning());
			break;
		case SUCCESS:
			this.addStyleName(GlobalResources.INSTANCE.css()
					.messageWithSuccess());
			break;
		default:
			this.addStyleName(GlobalResources.INSTANCE.css().messageWithInfo());

		}

		// register handler

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
