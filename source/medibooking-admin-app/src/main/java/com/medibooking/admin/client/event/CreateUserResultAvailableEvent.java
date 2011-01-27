package com.medibooking.admin.client.event;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.medibooking.admin.client.rest.JsonResult;

public class CreateUserResultAvailableEvent extends
		GwtEvent<CreateUserResultAvailableEvent.Handler> {

	private JsonResult jsonResult;
	public static Type<Handler> TYPE = new Type<CreateUserResultAvailableEvent.Handler>();

	public interface Handler extends EventHandler {

		public void onResultAvailable(CreateUserResultAvailableEvent event);

	}

	public CreateUserResultAvailableEvent(JsonResult jsonResult) {
		this.jsonResult = jsonResult;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {

		return CreateUserResultAvailableEvent.TYPE;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onResultAvailable(this);

	}

	public void setJsonResult(JsonResult jsonResult) {
		this.jsonResult = jsonResult;
	}

	public JsonResult getJsonResult() {
		return jsonResult;
	}

	// ASSUME ONLY ONE EVENT BUS
	public static HandlerRegistration register(EventBus eventBus,
			CreateUserResultAvailableEvent.Handler handler) {
		return eventBus.addHandler(TYPE, handler);

	}

}
