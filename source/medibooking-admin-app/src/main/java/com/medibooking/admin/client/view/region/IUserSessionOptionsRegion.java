package com.medibooking.admin.client.view.region;

public interface IUserSessionOptionsRegion {
	
	
	public void setPresenter(Presenter presenter);
	public interface Presenter {
		public void destroyUserSession();		
	}

}
