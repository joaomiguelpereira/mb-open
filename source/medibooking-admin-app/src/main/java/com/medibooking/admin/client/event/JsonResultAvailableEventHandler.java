package com.medibooking.admin.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface JsonResultAvailableEventHandler extends EventHandler {
	public void onJsonResultAvailable(AbstractJsonResultAvailableEvent<?> event);
}
