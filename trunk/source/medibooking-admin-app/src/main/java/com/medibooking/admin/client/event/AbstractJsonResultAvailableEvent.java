package com.medibooking.admin.client.event;
import com.google.gwt.event.shared.GwtEvent;
import com.medibooking.admin.client.rest.JsonResult;

public abstract class AbstractJsonResultAvailableEvent<T extends JsonResultAvailableEventHandler>
		extends GwtEvent<T> {

	protected JsonResult jsonResult;
	
	public AbstractJsonResultAvailableEvent(JsonResult result) {
		this.jsonResult = result;
	}

	@Override
	protected void dispatch(T handler) {
		handler.onJsonResultAvailable(this);

	}

	public JsonResult getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(JsonResult jsonResult) {
		this.jsonResult = jsonResult;
	}
	
}
