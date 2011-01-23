package com.medibooking.admin.client.view;

import com.google.gwt.user.client.ui.IsWidget;
import com.medibooking.admin.client.mvp.WebAppPresenter;
import com.medibooking.admin.shared.entity.User;

public interface IRegisterUserView extends IsWidget {

	public void setPresenter(Presenter presenter);

	public interface Presenter extends WebAppPresenter {

		void saveUser(User user);
		//Specific stuff for this presenter goes here
	}

	public void setUser(User user);

	public void initialize();
}
