package com.medibooking.admin.client.view.widget;

/**
 * Editors implemening this interface show similar behavior in what concerns the
 * error indication
 * 
 * @author jpereira
 * 
 */
public interface Errorable {

	public void setError();

	public void setError(String message);

	public void setErrors(String[] messages);

	public void clearErrors();
	
	public boolean hasErrors();

}
