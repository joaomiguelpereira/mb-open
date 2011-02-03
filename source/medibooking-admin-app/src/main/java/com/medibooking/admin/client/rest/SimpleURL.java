package com.medibooking.admin.client.rest;

import java.util.HashMap;

import com.google.gwt.http.client.URL;

public class SimpleURL {

	private String url;
	private HashMap<String, String> params;
	public SimpleURL(String url) {
		this.url = url;
		this.params = new HashMap<String, String>();
	}

	public void addParam(String key, String value) {
		this.params.put(key, value);
	}
	
	public String build() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.url);
		if ( this.params.size() > 0 ) {
			
			sb.append("?");
			int count = 0;
			for ( String key: this.params.keySet()) {
				sb.append(key);
				sb.append("=");
				sb.append(this.params.get(key));
				if ( this.params.size()>++count) {
					sb.append("&");
				}
			}
		}
		return URL.encode(sb.toString());
	}
	
	@Override
	public String toString() {
		return build();
	}
	

}
