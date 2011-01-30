package com.medibooking.admin.client.view.editor;

import java.util.List;
import java.util.Map;

public interface ErrorBinder<T> {

	public void bindErrors(T editor, Map<String, List<String>> errors);
}
