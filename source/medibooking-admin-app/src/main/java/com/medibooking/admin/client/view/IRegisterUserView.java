package com.medibooking.admin.client.view;

import com.google.gwt.user.client.ui.IsWidget;
import com.medibooking.admin.client.mvp.IPresenter;
import com.medibooking.admin.client.rest.JsonResult;
import com.medibooking.admin.shared.entity.User;

public interface IRegisterUserView extends ErrorableView {

	public void setPresenter(Presenter presenter);

	public interface Presenter extends IPresenter {

		void saveUser(User user);
		//Specific stuff for this presenter goes here
	}

	public void setUser(User user);

	public void initialize();


}
