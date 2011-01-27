package com.medibooking.admin.client.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

	
	

	private Long id;
	private String jsonString;
	private String errorMessage;
	private String successMessage;
	private String warningMessage;
	private Integer status = new Integer(-1); // -1 did not receive anything
												// useful
	private HashMap<String, List<String>> fieldErrors = new HashMap<String, List<String>>();

	public JsonResult(String jsonString) {
		this.jsonString = jsonString;
	}

	public JsonResult parse() {
		if (jsonString == null) {
			throw new IllegalStateException(
					"The json string is null. Please provid a not null string in the constructor");
		}
		JSONObject jsObj = new JsonParser().parse(this.jsonString);

		// handle status
		if (jsObj.get(STATUS_PROP) != null
				&& jsObj.get(STATUS_PROP).isNumber() != null) {
			this.status = Integer.valueOf(jsObj.get(STATUS_PROP).isNumber()
					.toString());
		}
		// handle id
		if (jsObj.get(ID_PROP) != null && jsObj.get(ID_PROP).isNumber() != null) {
			this.id = Long.valueOf(jsObj.get(ID_PROP).isNumber().toString());
		}

		// handle error_message
		if (jsObj.get(ERROR_MESSAGE_PROP) != null) {
			this.errorMessage = jsObj.get(ERROR_MESSAGE_PROP).isString().stringValue();
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
		
		
		//now handle errors
		if (jsObj.get(ERRORS_PROP) != null ) {
			JSONObject fieldErrosObj =  jsObj.get(ERRORS_PROP).isObject();
			if ( fieldErrosObj!=null ) {
				Set<String> props =  fieldErrosObj.keySet();
				for (String prop : props ) {
					JSONArray objArray = fieldErrosObj.get(prop).isArray();
					
					if ( objArray != null ) {
						
						List<String> erroList = new ArrayList<String>();
						for (int i=0; i < objArray.size() ; i++ ) {
							erroList.add(objArray.get(i).isString().stringValue());
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

	public Integer getStatus() {
		return status;
	}

	public Long getId() {
		return id;
	}

	public String getJsonString() {
		
		
		return this.jsonString;
	}

}
