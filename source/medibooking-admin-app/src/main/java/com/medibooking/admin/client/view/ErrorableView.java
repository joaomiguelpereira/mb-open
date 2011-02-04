package com.medibooking.admin.client.view;

import java.util.Map;

import com.google.gwt.user.client.ui.IsWidget;

public interface ErrorableView extends IsWidget {

	
	public void onErrors(Map<String, java.util.List<String>> firldErrors);
	public void clearErrors();
}
