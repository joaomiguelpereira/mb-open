package com.medibooking.admin.client.view.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.medibooking.admin.client.view.resources.GlobalResources;

public class ErrorWindow extends PopupPanel {

	private static final ErrorWindow INSTANCE = new ErrorWindow();
	private static final String WIDTH = "400px";
	private static final String HEIGHT = "200ps";
	
	private ScrollPanel scrollPanel;
	private VerticalPanel verticalPanel;
	private Anchor closeLink;

	private ErrorWindow() {
		this.setAnimationEnabled(false);
		this.setAutoHideEnabled(false);
		this.setGlassEnabled(true);
		this.setGlassStyleName(GlobalResources.INSTANCE.css().glassDark());
		this.setWidth(WIDTH);
		this.setHeight(HEIGHT);
		this.verticalPanel = new VerticalPanel();
		this.scrollPanel = new ScrollPanel();
		this.closeLink = new Anchor("Fechar");
		closeLink.addStyleName(GlobalResources.INSTANCE.css().errorWindowClose());
		
		HTML title = new HTML("Ocorreu um erro na Aplicação!");

		title.addStyleName(GlobalResources.INSTANCE.css().errorWindowTitle());
		scrollPanel.addStyleName(GlobalResources.INSTANCE.css().errorWindowContent());
		this.verticalPanel.add(title);

		this.verticalPanel.add(this.scrollPanel);

		this.verticalPanel.add(this.closeLink);
		this.verticalPanel.setCellHorizontalAlignment(this.closeLink,HasHorizontalAlignment.ALIGN_RIGHT);
		this.add(verticalPanel);
		this.closeLink.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				hide();

			}
		});
		this.addStyleName(GlobalResources.INSTANCE.css().errorWindow());

	}

	public static void show(Throwable e) {
		INSTANCE.scrollPanel.clear();
		INSTANCE.scrollPanel.add(new HTML(e.getMessage()));
		INSTANCE.center();
		INSTANCE.show();
	}

}
