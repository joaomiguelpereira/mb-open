package com.medibooking.admin.client.event;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.medibooking.admin.client.rest.JsonResult;

public class CreateUserResultAvailableEvent extends AbstractJsonResultAvailableEvent<CreateUserResultAvailableEvent.Handler> {

	public static Type<Handler> TYPE = new Type<CreateUserResultAvailableEvent.Handler>();

	public interface Handler extends JsonResultAvailableEventHandler {

	}

	public CreateUserResultAvailableEvent(JsonResult jsonResult) {
		super(jsonResult);

	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {

		return CreateUserResultAvailableEvent.TYPE;
	}

	public static HandlerRegistration register(EventBus eventBus,
			CreateUserResultAvailableEvent.Handler handler) {
		return eventBus.addHandler(TYPE, handler);

	}

}
