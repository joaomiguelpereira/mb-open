package com.medibooking.admin.client.view;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.inject.Inject;
import com.medibooking.admin.client.place.HomePlace;
import com.medibooking.admin.client.place.LoginPlace;
import com.medibooking.admin.client.place.RegisterUserPlace;
import com.medibooking.admin.client.view.resources.GlobalResources;
import com.medibooking.admin.client.view.widget.UserSessionOptionsWidget;

public class DesktopWebAppShell extends Composite implements IMainView {

	private static final Binder BINDER = GWT.create(Binder.class);

	private final Logger log = Logger.getLogger(DesktopWebAppShell.class
			.getName());

	@UiField
	Image logo;

	@UiField
	SlidingPanel contentPanel;
		
	//@UiField
	//SlidingPanel contentPanel;
	//ScrollPanel scrollablePanel;

	@UiField(provided = true)
	LoginView loginView;

	@UiField
	UserSessionOptionsWidget userSessionOptionsWidget;

	@UiField(provided = true)
	HomeView homeView;

	@UiField(provided = true)
	RegisterUserView registerUserView;
	
	@UiField
	LayoutPanel messages;
	

	private Presenter presenter;

	@Inject
	public DesktopWebAppShell(ILoginView loginView, IHomeView homeView,
			IRegisterUserView registerUserView) {
		log.fine("Home view: " + homeView.hashCode());
		this.loginView = (LoginView) loginView;
		this.homeView = (HomeView) homeView;

		this.registerUserView = (RegisterUserView) registerUserView;
		init();

		
	}
	
	private void init() {
		initWidget(BINDER.createAndBindUi(this));
		contentPanel.setAnimationSpeed(100);
		
		
		//Register handlers for userSessionWidget
		userSessionOptionsWidget.getLoginLink().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				presenter.goTo(new LoginPlace());
			}
		});
		
		userSessionOptionsWidget.getRegisterLink().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				presenter.goTo(new RegisterUserPlace());
				
			}
		});
		
		userSessionOptionsWidget.getLogoutLink().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				presenter.logoutRequested();
				
			}
		});
	}

	interface Binder extends UiBinder<DockLayoutPanel, DesktopWebAppShell> {
	}

	@UiHandler("logo")
	void onLogoClicked(ClickEvent event) {
		presenter.goTo(new HomePlace());
	}

	public AcceptsOneWidget getContentPanel() {
		return this.contentPanel;
	}

	@Override
	public void revealLoginView() {
		contentPanel.setWidget(loginView);

	}

	@Override
	public void revealHomeView() {
		contentPanel.setWidget(homeView);

	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void revealRegisterUserView() {
		contentPanel.setWidget(registerUserView);

	}

	@Override
	public void showMessage(String message, MessageType type) {
		//create an panel to show the message, if it does not exists yet
		StringBuilder sb =  new StringBuilder();
		
		sb.append("<div>");
		sb.append(message);
		sb.append("</div>");
		
		this.messages.add(new HTML(sb.toString()));
		
		switch (type) {
		case ERROR:
			this.messages.addStyleName(GlobalResources.INSTANCE.css().messageWithError());
			break;
		}
		messages.setVisible(true);
		// Create a new timer that calls Window.alert().
	    Timer t = new Timer() {
	      public void run() {
	       messages.clear();
	       messages.setVisible(false);
	       this.cancel();
	      }
	    };

	    // Schedule the timer to run once in 4 seconds.
	    t.schedule(3000);
		
		
		
		
		
		
	}

}
