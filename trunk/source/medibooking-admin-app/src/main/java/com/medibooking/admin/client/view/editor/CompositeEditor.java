package com.medibooking.admin.client.view.editor;

import java.util.List;
import java.util.Map;

import com.google.gwt.editor.client.Editor;

public interface CompositeEditor<T> extends Editor<T>{

	public void setFieldErrors(Map<String, List<String>> fieldErrors);
}
