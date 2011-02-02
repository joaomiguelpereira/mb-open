package com.medibooking.admin.client.activity;


import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.medibooking.admin.client.mvp.AbstractWebAppActivity;
import com.medibooking.admin.client.view.IHomeView;

public class HomeActivity extends AbstractWebAppActivity implements
		IHomeView.Presenter {

	private final IHomeView view;
	
	@Inject
	public HomeActivity(IHomeView view, PlaceController placeController) {
		this.view = view;
		this.view.setPresenter(this);
		this.placeController = placeController;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.eventBus = eventBus;
		panel.setWidget(this.view);
	}

	

}
