package com.medibooking.admin.client.view;

import com.medibooking.admin.client.mvp.IPresenter;
import com.medibooking.admin.shared.entity.User;

public interface ILoginView extends ErrorableView {
	
	public void setPresenter(Presenter presenter);
	public void setRegisteredBefore(boolean registeredBefore);
	public void initialize();
	public interface Presenter extends IPresenter{

		void loginUser(User user);

	}
	public void setUser(User user);

}
