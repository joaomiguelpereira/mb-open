package com.medibooking.admin.client.gin;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Singleton;
import com.medibooking.admin.client.WebApp;
import com.medibooking.admin.client.WebAppImpl;
import com.medibooking.admin.client.activity.HomeActivity;
import com.medibooking.admin.client.activity.LoginActivity;
import com.medibooking.admin.client.activity.RegisterUserActivity;
import com.medibooking.admin.client.controller.WebAppController;
import com.medibooking.admin.client.controller.UserSessionController;
import com.medibooking.admin.client.manager.UserSessionManager;
import com.medibooking.admin.client.manager.UserSessionManagerImpl;
import com.medibooking.admin.client.mvp.WebAppActivityMapper;
import com.medibooking.admin.client.mvp.WebAppPlaceHistoryMapper;
import com.medibooking.admin.client.rest.service.UserRestService;
import com.medibooking.admin.client.rest.service.UserService;
import com.medibooking.admin.client.rest.service.UserSessionRestService;
import com.medibooking.admin.client.rest.service.UserSessionService;
import com.medibooking.admin.client.view.DesktopWebAppShell;
import com.medibooking.admin.client.view.HomeView;
import com.medibooking.admin.client.view.IHomeView;
import com.medibooking.admin.client.view.ILoginView;
import com.medibooking.admin.client.view.IMainView;
import com.medibooking.admin.client.view.IRegisterUserView;
import com.medibooking.admin.client.view.LoginView;
import com.medibooking.admin.client.view.RegisterUserView;
import com.medibooking.admin.client.view.region.IUserSessionOptionsRegion;
import com.medibooking.admin.client.view.region.UserSessionOptionsRegionImpl;

public class WebAppGinModule extends AbstractGinModule {

	@Override
	protected void configure() {	
		
		
		//REST SERVICES
		bind(UserService.class).to(UserRestService.class);
		bind(UserSessionService.class).to(UserSessionRestService.class);
		
		//Display Regions and their controllers
		bind(IUserSessionOptionsRegion.class).to(UserSessionOptionsRegionImpl.class).in(Singleton.class);
		bind(UserSessionController.class).in(Singleton.class);
		
		

		//Main view and its controller
		bind(IMainView.class).to(DesktopWebAppShell.class).in(Singleton.class);
		bind(WebAppController.class).in(Singleton.class);
		
		//Reusable activities (must care with state in the activities)
		bind(HomeActivity.class).in(Singleton.class);
		bind(LoginActivity.class).in(Singleton.class);
		bind(RegisterUserActivity.class).in(Singleton.class);
		
		
		//bind Views, reusable
		bind(IHomeView.class).to(HomeView.class).in(Singleton.class);
		bind(ILoginView.class).to(LoginView.class).in(Singleton.class);
		bind(IRegisterUserView.class).to(RegisterUserView.class).in(Singleton.class);
		
		
		//bind activityMapper
		bind(ActivityMapper.class).to(WebAppActivityMapper.class).in(Singleton.class);
		
		//bind placeHistoryMapper as singleton
		bind(WebAppPlaceHistoryMapper.class).in(Singleton.class);
		
		//Bind WebApp as singleton
		bind(WebApp.class).to(WebAppImpl.class).in(Singleton.class);
		
		//bind a singleton instance of UserSessionManagerImpl to UserSessionManager
		bind(UserSessionManager.class).to(UserSessionManagerImpl.class).in(Singleton.class);
		
		//Bind event Bus
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		
		//Bind the place Controller
		bind(PlaceController.class).to(InjectablePlaceController.class).in(Singleton.class);
		
		
		
		
	}

}
