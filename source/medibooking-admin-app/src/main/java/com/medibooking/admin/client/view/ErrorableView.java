package com.medibooking.admin.client.view;

import com.google.gwt.user.client.ui.IsWidget;
import com.medibooking.admin.client.rest.JsonResult;

public interface ErrorableView extends IsWidget {

	
	public void onErrors(JsonResult jsonResult);
}
