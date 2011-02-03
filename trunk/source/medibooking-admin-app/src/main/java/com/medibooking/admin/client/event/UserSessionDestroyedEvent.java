package com.medibooking.admin.client.event;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

public class UserSessionDestroyedEvent extends GwtEvent<UserSessionDestroyedEvent.Handler> {

	public interface Handler extends EventHandler {
		public void onSessionDestroyed(UserSessionDestroyedEvent event);
	}

	public static final Type<UserSessionDestroyedEvent.Handler> TYPE = new Type<UserSessionDestroyedEvent.Handler>();
	
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(Handler handler) {

			handler.onSessionDestroyed(this);
		
	}
	public static HandlerRegistration register(EventBus eventBus, Handler handler) {
		return eventBus.addHandler(TYPE, handler);
	}
}
