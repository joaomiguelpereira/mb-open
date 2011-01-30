package com.medibooking.admin.client.activity;



import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.medibooking.admin.client.view.ILoginView;
import com.medibooking.admin.shared.entity.User;

public class LoginActivity extends WebAppActivity implements
		ILoginView.Presenter {


	private final ILoginView view;
	private boolean registeredBefore;

	@Inject
	public LoginActivity(ILoginView view, PlaceController placeController) {
		this.placeController = placeController;
		this.view = view;
		
		this.view.setPresenter(this);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.eventBus = eventBus;
		panel.setWidget(view);
		this.view.setUser(new User());
		this.view.initialize();
	}

	public void setRegisteredBefore(boolean registeredBefore) {
		this.registeredBefore = registeredBefore;
	}

	public boolean isRegisteredBefore() {
		return registeredBefore;
	}

	@Override
	public void loginUser(User user) {
		Window.alert("OK");
		
	}

}
