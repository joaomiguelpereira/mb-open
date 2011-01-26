package com.medibooking.admin.client.rest;

public class ServerRouteResolver {

	
	public static class UserRoutes {
		public static native String getCreate() /*-{
			return $wnd.userRoutes.create;
		}-*/;
	}
	
}
