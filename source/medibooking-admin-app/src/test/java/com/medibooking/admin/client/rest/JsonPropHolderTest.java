package com.medibooking.admin.client.rest;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

import static org.junit.Assert.*;
public class JsonPropHolderTest extends GWTTestCase {

	@Test
	public void testToJson() {
		JsonPropHolder jsonPropHolder = new JsonPropHolder();
		jsonPropHolder.add("testKey", "testValue");
		
		assertEquals("{\"testKey\":\"testValue\"}", jsonPropHolder.toJson());
	}

	@Override
	public String getModuleName() {
		
		return null;
	}
}
