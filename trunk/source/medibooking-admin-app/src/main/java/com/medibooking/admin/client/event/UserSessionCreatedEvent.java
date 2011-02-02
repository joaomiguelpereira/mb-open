package com.medibooking.admin.client.event;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

public class UserSessionCreatedEvent extends GwtEvent<UserSessionCreatedEvent.Handler> {
	
	public interface Handler extends EventHandler {
		public void onUserSessionCreated(UserSessionCreatedEvent event);
	}
	
	public static Type<Handler> TYPE = new Type<UserSessionCreatedEvent.Handler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {

		return TYPE;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onUserSessionCreated(this);

	}

	public static HandlerRegistration register(EventBus eventBus, Handler handler) {
		return eventBus.addHandler(TYPE, handler);
		
	}

}
