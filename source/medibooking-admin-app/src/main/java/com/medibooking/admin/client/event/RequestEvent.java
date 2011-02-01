package com.medibooking.admin.client.event;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.medibooking.admin.client.event.RequestEvent.State;

public class RequestEvent extends GwtEvent<RequestEvent.Handler>{

	public enum State {
		START, END, IDLE;
	}
	
	private State state = State.IDLE;
	
	public RequestEvent(State state) {
		this.state = state;
	}
	public interface Handler extends EventHandler {
		public void onRequest(RequestEvent event);
	}
	
	public static Type<Handler> TYPE = new Type<RequestEvent.Handler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {

		return TYPE;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onRequest(this);

	}
	
	public static HandlerRegistration register(EventBus eventBus, Handler handler) {
		return eventBus.addHandler(TYPE, handler);
	}

	public State getState() {
		return this.state;
	}

}
