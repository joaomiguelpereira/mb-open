package com.medibooking.admin.shared.entity;



import com.medibooking.admin.client.view.editor.UserEditor;
import com.medibooking.admin.shared.validation.AbstractValidator;

/**
 * REFACTOR EVERYTHING RELATED WITH VALIDATIONS. THIS IS A UGLY IMPLEMENTATION
 * @author jpereira
 *
 */
public class UserValidator extends AbstractValidator{

	public boolean validate(User edited, UserEditor userEditor) {
		userEditor.clearErrors();
		
		//Validate name
		isNotEmpty(edited.getName(), "O nome é obrigatório!", true);		
		hasSizeLimits(edited.getName(), 5, 40, "O nome tem de ter entre 5 a 40 caracteres", false);
		flushErrors(userEditor.getName());
		return isValid();
		//end validating name
		
		
	}

		

	
}
