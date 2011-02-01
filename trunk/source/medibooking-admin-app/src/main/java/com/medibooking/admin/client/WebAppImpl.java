package com.medibooking.admin.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.inject.Inject;
import com.medibooking.admin.client.manager.UserSessionManager;
import com.medibooking.admin.client.mvp.WebAppPlaceHistoryMapper;
import com.medibooking.admin.client.place.HomePlace;
import com.medibooking.admin.client.view.IMainView;
import com.medibooking.admin.client.view.resources.GlobalResources;
import com.medibooking.admin.client.view.widget.ErrorWindow;
import com.medibooking.admin.client.view.widget.ProcessingIndicator;

public class WebAppImpl implements WebApp {
	// Logger
	private static final Logger log = Logger.getLogger(WebApp.class.getName());
	// Flag is if run method was alreadyCalled
	private boolean isRunning = false;

	private final EventBus eventBus;
	private final PlaceController placeController;
	private final UserSessionManager userSessionManager;
	private final IMainView mainView;
	private final WebAppPlaceHistoryMapper placeHistoryMapper;

	private final ActivityMapper activityMapper;
	private final WebAppController webAppController;

	@Inject
	public WebAppImpl(IMainView shell, EventBus eventBus,
			PlaceController placeController,
			UserSessionManager userSessionManager,
			WebAppPlaceHistoryMapper placeHistoryMapper,
			ActivityMapper activityMapper, WebAppController webAppController) {
		this.eventBus = eventBus;
		this.placeController = placeController;
		this.userSessionManager = userSessionManager;
		this.mainView = shell;
		this.placeHistoryMapper = placeHistoryMapper;
		

		this.activityMapper = activityMapper;
		this.webAppController = webAppController;
	}

	/**
	 * Start the WebApp
	 */
	public void run() {
		if (!isRunning) {
			init();

			ActivityManager activityManager = new ActivityManager(
					activityMapper, eventBus);

			activityManager.setDisplay(this.mainView.getContentPanel());
			PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(
					this.placeHistoryMapper);
			historyHandler.register(this.placeController, eventBus,
					new HomePlace());

			RootLayoutPanel.get().add(mainView);
			historyHandler.handleCurrentHistory();

		}

	}

	private void init() {

		log.fine("Initializing the application...");
		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
			public void onUncaughtException(Throwable e) {
				//In the event that the Processing Indicator is working, just stop it
				ProcessingIndicator.stop();
				ErrorWindow.show(e);
				
				//Window.alert("Error: " + e.getMessage());
				log.log(Level.SEVERE, e.getMessage(), e);
			}
		});
		// Inject global resources.
		GWT.<GlobalResources> create(GlobalResources.class).css()
				.ensureInjected();

		GWT.<GlobalResources> create(GlobalResources.class).editorCss()
				.ensureInjected();
		// TODO: Configure remote handler for logging. For dev, everything is
		// dumped in jetty console
		/**
		 * if (LogConfiguration.loggingIsEnabled()) { // Add remote logging
		 * handler log.fine("Registering remote logging handle...");
		 * RequestFactoryLogHandler.LoggingRequestProvider provider = new
		 * RequestFactoryLogHandler.LoggingRequestProvider() { public
		 * LoggingRequest getLoggingRequest() { return
		 * requestFactory.loggingRequest(); } };
		 * Logger.getLogger("").addHandler( new
		 * RequestFactoryLogHandler(provider, Level.WARNING, new
		 * ArrayList<String>())); }
		 **/
	}

}
