package com.medibooking.admin.client.rest;

public class ServerRouteResolver {

	public static class SessionRoutes {
		public static native String getCreate() /*-{
			return $wnd.sessionRoutes.create;
		}-*/;

		public static native String getDestroy() /*-{
			return $wnd.sessionRoutes.destroy;
		}-*/;
	
	}

	public static class UserRoutes {

		public static native String getCreate() /*-{
			return $wnd.userRoutes.create;
		}-*/;

	}

}
