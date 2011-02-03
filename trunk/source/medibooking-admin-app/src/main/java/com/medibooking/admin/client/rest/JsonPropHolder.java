package com.medibooking.admin.client.rest;

import java.util.HashMap;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class JsonPropHolder {

	private HashMap<String, Object> props = new HashMap<String, Object>();

	public JsonPropHolder add(String key, Object value) {
		this.props.put(key, value);
		return this;
	}

	public String toJson() {
		JSONObject jsonObject = new JSONObject();
		for (String key : props.keySet()) {
			Object value = props.get(key);
			JSONValue jsonVal = null;

			if (value instanceof Integer || value instanceof Long
					|| value instanceof Double || value instanceof Short
					|| value instanceof String || value instanceof Boolean) {
				jsonVal = new JSONString(value.toString());
			} else {
				throw new UnsupportedOperationException(
						"No object is allowed, just primitives or Boxes of those");
			}
			jsonObject.put(key, jsonVal);
		}
		return jsonObject.toString();
	}

	@Override
	public String toString() {
		return toJson();
	}

}
