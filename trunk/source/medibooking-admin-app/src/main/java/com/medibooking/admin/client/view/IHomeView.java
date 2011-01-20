package com.medibooking.admin.client.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface IHomeView extends IsWidget {

	public void setPresenter(Presenter presenter);
	
	public interface Presenter {
		public void goTo(Place place);
	}
}
