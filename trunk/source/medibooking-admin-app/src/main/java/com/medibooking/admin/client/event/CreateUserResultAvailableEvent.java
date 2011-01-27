package com.medibooking.admin.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.medibooking.admin.client.rest.JsonResult;

public class CreateUserResultAvailableEvent extends GwtEvent<CreateUserResultAvailableEvent.Handler>{

	private JsonResult jsonResult;
	public static Type<Handler> TYPE = new Type<CreateUserResultAvailableEvent.Handler>();
	
	public interface Handler extends EventHandler {

		public void onResultAvailable(CreateUserResultAvailableEvent event);

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

}
