package com.medibooking.admin.client.view;

import com.google.gwt.user.client.ui.IsWidget;
import com.medibooking.admin.client.mvp.WebAppPresenter;
import com.medibooking.admin.shared.entity.User;

public interface ILoginView extends IsWidget {
	
	public void setPresenter(Presenter presenter);
	public void setRegisteredBefore(boolean registeredBefore);
	public void initialize();
	public interface Presenter extends WebAppPresenter{

		void loginUser(User user);

	}
	public void setUser(User user);

}
