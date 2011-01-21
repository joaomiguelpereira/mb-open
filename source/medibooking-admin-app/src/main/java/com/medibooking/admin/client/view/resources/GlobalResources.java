package com.medibooking.admin.client.view.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource.NotStrict;
import com.google.gwt.resources.client.ImageResource;

public interface GlobalResources extends ClientBundle {
	
	public GlobalResources INSTANCE = GWT.create(GlobalResources.class);
	
	@Source("medibooking.logo.png")
	ImageResource logoHeader();
	
	@Source("attention.png")
	ImageResource attentionIcon();
	
	@NotStrict
    @Source("global.css")
    GlobalCss css();
	
	@NotStrict
	@Source("editor.css")
	EditorCss editorCss();
	
	
	
	
	
	
	
	

}
