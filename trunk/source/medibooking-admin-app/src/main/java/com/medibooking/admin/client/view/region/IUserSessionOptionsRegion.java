package com.medibooking.admin.client.view.region;

import com.medibooking.admin.client.mvp.IPresenter;

public interface IUserSessionOptionsRegion {
	
	
	public void setPresenter(Presenter presenter);
	public interface Presenter extends IPresenter{
		public void destroyUserSession();		
	}

}
