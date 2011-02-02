package com.medibooking.admin.client.controller;


import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.medibooking.admin.client.event.JsonResultAvailableEvent;
import com.medibooking.admin.client.event.RequestEvent;
import com.medibooking.admin.client.mvp.AbstractController;
import com.medibooking.admin.client.place.HomePlace;
import com.medibooking.admin.client.place.LoginPlace;
import com.medibooking.admin.client.place.RegisterUserPlace;
import com.medibooking.admin.client.view.IMainView;
import com.medibooking.admin.client.view.MessageType;

public class WebAppController extends AbstractController implements
		IMainView.Presenter, PlaceChangeEvent.Handler, RequestEvent.Handler{

	private IMainView view = null;

	@Inject
	public WebAppController(EventBus eventBus,
			PlaceController placeController, IMainView view) {
		super();
		this.eventBus = eventBus;
		this.placeController = placeController;
		this.view = view;
		this.view.setPresenter(this);
		
		eventBus.addHandler(PlaceChangeEvent.TYPE, this);
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
