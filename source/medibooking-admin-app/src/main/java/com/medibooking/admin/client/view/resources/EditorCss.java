package com.medibooking.admin.client.view.resources;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.ImportedWithPrefix;
import com.google.gwt.resources.client.CssResource.Shared;

@Shared
@ImportedWithPrefix("editor")
public interface EditorCss extends CssResource {

	public String fieldWithErrors();

	public String editorContainer();

	public String editorTable();

	public String labelContainer();

	public String leftAligned();

	public String errorMsg();

	public String buttonArea();
	
	public String errorIndicator();
}
