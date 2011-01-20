package com.medibooking.admin.client.view.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.NotStrict;
import com.google.gwt.resources.client.ImageResource;

public interface GlobalResources extends ClientBundle {
	@NotStrict
    @Source("global.css")
    CssResource css();
	
	
	@Source("medibooking.logo.png")
	ImageResource logoHeader();

}
