package com.medibooking.admin.client.view;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;
import com.medibooking.admin.client.place.HomePlace;
import com.medibooking.admin.client.view.region.IUserSessionOptionsRegion;
import com.medibooking.admin.client.view.region.UserSessionOptionsRegionImpl;
import com.medibooking.admin.client.view.widget.PageMessagePopup;
import com.medibooking.admin.client.view.widget.ProcessingIndicator;

public class DesktopWebAppShell extends Composite implements IMainView {

	private static final Binder BINDER = GWT.create(Binder.class);

	private final Logger log = Logger.getLogger(DesktopWebAppShell.class
			.getName());

	@UiField
	Image logo;

	@UiField
	SlidingPanel contentPanel;

	@UiField(provided = true)
	LoginView loginView;

	@UiField(provided=true)
	UserSessionOptionsRegionImpl userSessionOptionsViewRegion;

	@UiField(provided = true)
	HomeView homeView;

	@UiField(provided = true)
	RegisterUserView registerUserView;

	private IMainView.Presenter presenter;

	@Inject
	public DesktopWebAppShell(ILoginView loginView, IHomeView homeView,
			IRegisterUserView registerUserView, IUserSessionOptionsRegion userSessionOptionsViewRegion) {
		log.fine("Home view: " + homeView.hashCode());
		this.loginView = (LoginView) loginView;
		this.homeView = (HomeView) homeView;
		this.userSessionOptionsViewRegion = (UserSessionOptionsRegionImpl)userSessionOptionsViewRegion;
		this.registerUserView = (RegisterUserView) registerUserView;
		init();
	}

	private void init() {
		initWidget(BINDER.createAndBindUi(this));
		contentPanel.setAnimationSpeed(100);
		
		
		// Register handlers for userSessionWidget
		/*
		userSessionOptionsWidget.getLoginLink().addClickHandler(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						presenter.goTo(new LoginPlace(false));
					}
				});

		userSessionOptionsWidget.getRegisterLink().addClickHandler(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						presenter.goTo(new RegisterUserPlace());

					}
				});

		userSessionOptionsWidget.getLogoutLink().addClickHandler(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						presenter.DestroyUserSessionRequested();

					}
				});*/
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
	public void revealRegisterUserView() {
		contentPanel.setWidget(registerUserView);

	}

	@Override
	public void showMessage(String message, MessageType type) {
		// create an panel to show the message, if it does not exists yet
		PageMessagePopup.show(message, type, this);
	}

	@Override
	public void showRequestIndicator() {
		ProcessingIndicator.start();

	}

	@Override
	public void hideRequestIndicator() {
		ProcessingIndicator.stop();

	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
		
	}

}
