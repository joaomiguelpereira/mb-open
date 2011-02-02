package com.medibooking.admin.client.activity;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.medibooking.admin.client.mvp.IPresenter;


public abstract class WebAppActivity extends AbstractActivity implements IPresenter {

	
	private static final Logger log = Logger.getLogger(WebAppActivity.class.getName());
	
	protected PlaceController placeController;
	protected EventBus eventBus;
	
	protected void logDebug(String what) {
		log.fine("DEBUG Message from "+this.getClass().getName()+": "+what);
	}
	
	@Override
	public void goTo(Place place) {
		
		this.placeController.goTo(place);
		
	}
	

}
