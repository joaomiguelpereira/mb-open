package com.medibooking.admin.client.mvp;

import com.google.gwt.place.shared.Place;

public interface WebAppPresenter {
	
	public void goTo(Place place);
	public void logoutRequested();

}
