package com.medibooking.admin.client.event.usersession;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.medibooking.admin.client.rest.JsonResult;

public class UserSessionEvent extends GwtEvent<UserSessionEventHandler> {

	public enum Operation {
		CREATE, DESTROY, VALIDATE;
	}

	public enum Status {
		FAIL, SUCCESS;
	}

	private JsonResult jsonResult;
	private Operation operation;
	private String sessionID;
	private String email;
	private Long userId;
	private Status status;
	public static Type<UserSessionEventHandler> TYPE = new Type<UserSessionEventHandler>();

	public UserSessionEvent(Operation operation, JsonResult jsonResult) {

		this.sessionID = jsonResult.getStringProperty("sessionId", false);
		this.email = jsonResult.getStringProperty("email",false);
		this.userId = jsonResult.getLongProperty("userId",false);
		if (jsonResult.hasErrors()) {
			this.setStatus(Status.FAIL);
		} else {
			this.setStatus(Status.SUCCESS);
		}
		
		this.jsonResult = jsonResult;
		this.setOperation(operation);
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	protected void dispatch(UserSessionEventHandler handler) {
		handler.onUserSessionEvent(this);

	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public Operation getOperation() {
		return operation;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<UserSessionEventHandler> getAssociatedType() {
		return TYPE;
	}

	public static HandlerRegistration register(EventBus eventBus,
			UserSessionEventHandler handler) {
		return eventBus.addHandler(TYPE, handler);
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	public JsonResult getJsonResult() {
		return this.jsonResult;
	}

	public void setJsonResult(JsonResult jsonResult) {
		this.jsonResult = jsonResult;
	}
}
