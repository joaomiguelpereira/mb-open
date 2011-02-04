package com.medibooking.admin.client.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import name.pehl.totoe.json.client.JsonParser;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;

public class JsonResult {

	public static final String ERROR_MESSAGE_PROP = "__errorMessage";
	public static final String WARNING_MESSAGE_PROP = "__warningMessage";
	public static final String SUCCESS_MESSAGE_PROP = "__successMessage";
	public static final String STATUS_PROP = "__status";
	public static final String ID_PROP = "__id";
	public static final String ERRORS_PROP = "__errors";

	public enum Status {
		FAIL, SUCCESS, UNKNOW;
	}

	private Long id;
	private String jsonString;
	private String errorMessage;
	private String successMessage;
	private String warningMessage;
	private Status status = Status.UNKNOW;

	private JSONObject jsObj;
	private HashMap<String, List<String>> fieldErrors = new HashMap<String, List<String>>();

	public JsonResult(String jsonString) {
		this.jsonString = jsonString;
	}

	public JsonResult parse() {
		if (jsonString == null) {
			throw new IllegalStateException(
					"The json string is null. Please provid a not null string in the constructor");
		}

		jsObj = new JsonParser().parse(this.jsonString);

		// handle STATUS_PROP
		if (jsObj.get(STATUS_PROP) != null) {
			String tmpEnumVal = jsObj.get(STATUS_PROP).isString().stringValue();
			this.setStatus(Status.valueOf(tmpEnumVal));
		} else {
			throw new RuntimeException(
					"The response from the server does not contains the STATUS code....");
		}

		// handle id
		if (jsObj.get(ID_PROP) != null && jsObj.get(ID_PROP).isNumber() != null) {
			this.id = Long.valueOf(jsObj.get(ID_PROP).isNumber().toString());
		}

		// handle error_message
		if (jsObj.get(ERROR_MESSAGE_PROP) != null) {
			this.errorMessage = jsObj.get(ERROR_MESSAGE_PROP).isString()
					.stringValue();
		}

		// handle warning
		if (jsObj.get(WARNING_MESSAGE_PROP) != null) {
			this.warningMessage = jsObj.get(WARNING_MESSAGE_PROP).isString()
					.stringValue();
		}
		// handle success
		if (jsObj.get(SUCCESS_MESSAGE_PROP) != null) {
			this.successMessage = jsObj.get(SUCCESS_MESSAGE_PROP).isString()
					.stringValue();
		}

		// now handle errors
		if (jsObj.get(ERRORS_PROP) != null) {
			JSONObject fieldErrosObj = jsObj.get(ERRORS_PROP).isObject();
			if (fieldErrosObj != null) {
				Set<String> props = fieldErrosObj.keySet();
				for (String prop : props) {
					JSONArray objArray = fieldErrosObj.get(prop).isArray();

					if (objArray != null) {

						List<String> erroList = new ArrayList<String>();
						for (int i = 0; i < objArray.size(); i++) {
							erroList.add(objArray.get(i).isString()
									.stringValue());
						}
						this.fieldErrors.put(prop, erroList);
					}
				}
			}
		}
		return this;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public String getWarningMessage() {
		return warningMessage;
	}

	public Long getId() {
		return id;
	}

	public String getJsonString() {

		return this.jsonString;
	}

	public boolean hasErrors() {
		return this.errorMessage != null;
	}

	public Map<String, List<String>> getFieldErrors() {
		return this.fieldErrors;
	}

	@Override
	public String toString() {
		return this.jsonString;
	}

	public String getStringProperty(String propKey, boolean required) {
		String value = null;

		if (jsObj.get(propKey) != null) {
			value = jsObj.get(propKey).isString().stringValue();
		}

		if (value == null && required) {
			throw new RuntimeException("The key " + propKey
					+ " is required from server response");
		}

		return value;
	}

	public Integer getIntegerProperty(String propKey, boolean required) {
		Integer value = null;

		if (jsObj.get(propKey) != null && jsObj.get(propKey).isNumber() != null) {
			value = Integer.valueOf(jsObj.get(propKey).isNumber().toString());

		}
		if (value == null && required) {
			throw new RuntimeException("The key " + propKey
					+ " is required from server response");
		}

		return value;
	}

	public Long getLongProperty(String propKey, boolean required) {
		Long value = null;

		if (jsObj.get(propKey) != null && jsObj.get(propKey).isNumber() != null) {
			value = Long.valueOf(jsObj.get(propKey).isNumber().toString());

		}
		if (value == null && required) {
			throw new RuntimeException("The key " + propKey
					+ " is required from server response");
		}

		return value;
	}

	public boolean isSuccess() {
		return this.successMessage != null;
	}

	public boolean hasFieldErrors() {
		return this.fieldErrors != null && this.fieldErrors.size() > 0;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

}
