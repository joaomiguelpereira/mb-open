package com.medibooking.admin.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.medibooking.admin.client.rest.JsonResult;

public class JsonResultAvailableEvent extends
		GwtEvent<JsonResultAvailableEvent.Handler> {
	private JsonResult jsonResult;
	public static Type<Handler> TYPE = new Type<JsonResultAvailableEvent.Handler>();

	public JsonResultAvailableEvent(JsonResult result) {
		this.jsonResult = result;
	}

	public interface Handler extends EventHandler {

		public void onResultAvailable(JsonResultAvailableEvent event);

	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {

		return JsonResultAvailableEvent.TYPE;
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

}
