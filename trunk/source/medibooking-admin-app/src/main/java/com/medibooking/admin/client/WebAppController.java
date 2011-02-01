package com.medibooking.admin.client;


import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.medibooking.admin.client.activity.WebAppActivity;
import com.medibooking.admin.client.event.JsonResultAvailableEvent;
import com.medibooking.admin.client.event.LogoutRequestedEvent;
import com.medibooking.admin.client.event.RequestEvent;
import com.medibooking.admin.client.place.HomePlace;
import com.medibooking.admin.client.place.LoginPlace;
import com.medibooking.admin.client.place.RegisterUserPlace;
import com.medibooking.admin.client.view.IMainView;
import com.medibooking.admin.client.view.MessageType;

public class WebAppController extends WebAppActivity implements
		IMainView.Presenter, PlaceChangeEvent.Handler, RequestEvent.Handler {

	private final IMainView view;

	@Inject
	public WebAppController(IMainView view, EventBus eventBus,
			PlaceController placeController) {
		super();
		this.view = view;
		this.view.setPresenter(this);
		this.eventBus = eventBus;
		this.placeController = placeController;
		eventBus.addHandler(PlaceChangeEvent.TYPE, this);
		eventBus.addHandler(LogoutRequestedEvent.TYPE, new LogoutHandler());
		eventBus.addHandler(JsonResultAvailableEvent.TYPE,
				new JsonResultAvailableResultHandler(this.view));
		//Configure loading 
		RequestEvent.register(this.eventBus, this);
		

	}

	@Override
	public void goTo(Place place) {
		this.placeController.goTo(place);
	}

	@Override
	public void onPlaceChange(PlaceChangeEvent event) {

		if (event.getNewPlace() instanceof LoginPlace) {
			view.revealLoginView();
		} else if (event.getNewPlace() instanceof HomePlace) {
			view.revealHomeView();
		} else if (event.getNewPlace() instanceof RegisterUserPlace) {
			view.revealRegisterUserView();
		}

	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		// This will never be called, because it's the main controller that is
		// initialized once
		this.eventBus = eventBus;
		panel.setWidget(this.view);
	}

	/**
	 * 
	 * @author jpereira
	 * 
	 */
	private static class LogoutHandler implements LogoutRequestedEvent.Handler {

		@Override
		public void onLogoutRequested(LogoutRequestedEvent event) {
			Window.alert("Logout requested");

		}

	}

	/**
	 * 
	 * @author jpereira
	 * 
	 */
	private static class JsonResultAvailableResultHandler implements
			JsonResultAvailableEvent.Handler {

		private IMainView view;

		public JsonResultAvailableResultHandler(IMainView view) {
			this.view = view;
		}

		@Override
		public void onResultAvailable(JsonResultAvailableEvent event) {

			MessageType type = null;
			String message = null;
			if ((message = event.getJsonResult().getErrorMessage()) != null) {
				type = MessageType.ERROR;
			} else if ((message = event.getJsonResult().getWarningMessage()) != null) {
				type = MessageType.WARNING;
			} else if ((message = event.getJsonResult().getSuccessMessage()) != null) {
				type = MessageType.SUCCESS;
			}
			
			if ( message!=null) {
				view.showMessage(message, type);
			}

		}

	}

	@Override
	public void onRequest(RequestEvent event) {
		
		
		
		if ( event.getState() == RequestEvent.State.START ) {
			view.showRequestIndicator();
		} else {
			view.hideRequestIndicator();
		}
		
		
		
	}

}
