package com.medibooking.admin.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class LogoutRequestedEvent extends GwtEvent<LogoutRequestedEvent.Handler> {

	public interface Handler extends EventHandler {

		public void onLogoutRequested(LogoutRequestedEvent event);

	}

	public static Type<Handler> TYPE = new Type<LogoutRequestedEvent.Handler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {

		return TYPE;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onLogoutRequested(this);

	}

}
