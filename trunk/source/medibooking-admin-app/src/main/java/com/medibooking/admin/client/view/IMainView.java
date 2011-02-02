package com.medibooking.admin.client.view;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.medibooking.admin.client.mvp.IPresenter;

public interface IMainView extends IsWidget {
	
	public void setPresenter(Presenter presenter);
	
	
	public interface Presenter extends IPresenter{
		
	}

	public void showMessage(String message, MessageType type);
	
	public void revealLoginView();

	public void revealHomeView();

	public AcceptsOneWidget getContentPanel();

	public void revealRegisterUserView();

	public void showRequestIndicator();

	public void hideRequestIndicator();

}
