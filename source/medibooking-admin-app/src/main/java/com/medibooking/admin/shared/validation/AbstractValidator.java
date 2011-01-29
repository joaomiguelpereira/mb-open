package com.medibooking.admin.shared.validation;

import java.util.ArrayList;

import com.medibooking.admin.client.view.widget.Errorable;

public abstract class AbstractValidator {

	private ArrayList<String> errors = new ArrayList<String>();
	private boolean isValid = true;

	protected void flushErrors(Errorable erroable) {
		
		if (!errors.isEmpty()) {
			erroable.setErrors(errors);
			isValid = false;
		}
		
		errors.clear();
	}

	protected void isNotEmpty(String value, String message, boolean accumulate) {

		if (value == null || value.isEmpty()) {
			accumulateMessage(message, accumulate);
		}
	}

	protected void hasSizeLimits(String value, int min, int max, String message,
			boolean accumulate) {

		if (value != null) {
			if ( value.length()< min || value.length()> max) {
				accumulateMessage(message, accumulate);
			}
		}
	}

	private void accumulateMessage(String message, boolean accumulate) {
		if (accumulate) {
			errors.add(message);
		} else if (!accumulate && errors.size()==0) {
			errors.add(message);
		}
	}


	public boolean isValid() {
		return isValid;
	}
}
