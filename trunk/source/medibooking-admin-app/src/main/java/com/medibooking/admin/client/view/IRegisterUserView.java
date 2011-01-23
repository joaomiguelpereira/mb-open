package com.medibooking.admin.client.view;

import com.google.gwt.user.client.ui.IsWidget;
import com.medibooking.admin.client.mvp.WebAppPresenter;

public interface IRegisterUserView extends IsWidget {

	public void setPresenter(Presenter presenter);

	public interface Presenter extends WebAppPresenter {
		//Specific stuff for this presenter goes here
	}
}
