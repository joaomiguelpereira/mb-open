package com.medibooking.admin.client.event;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.medibooking.admin.client.rest.JsonResult;

public class CreateSessionResultAvailableEvent extends AbstractJsonResultAvailableEvent<CreateSessionResultAvailableEvent.Handler> {

	public static Type<Handler> TYPE = new Type<CreateSessionResultAvailableEvent.Handler>();

	public interface Handler extends JsonResultAvailableEventHandler {
	}

	public CreateSessionResultAvailableEvent(JsonResult result) {
		super(result);
	}


	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

	
	public static HandlerRegistration register(EventBus eventBus,
			CreateSessionResultAvailableEvent.Handler handler) {
		return eventBus.addHandler(TYPE, handler);
	}
}
