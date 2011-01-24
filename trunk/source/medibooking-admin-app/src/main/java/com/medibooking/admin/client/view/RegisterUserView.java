package com.medibooking.admin.client.view;


import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.medibooking.admin.client.view.editor.UserEditor;
import com.medibooking.admin.shared.entity.User;
import com.medibooking.admin.shared.entity.UserValidator;

public class RegisterUserView extends Composite implements IRegisterUserView{

	
	@UiField
	UserEditor userEditor;

	interface Driver extends SimpleBeanEditorDriver<User, UserEditor> {}
	Driver driver = GWT.create(Driver.class);
	private static final Binder BINDER = GWT.create(Binder.class);
	private Presenter presenter;
	private User user;
	
	public UserEditor getUserEditor() {
		return this.userEditor;
	}
	
	public RegisterUserView() {
		initWidget(BINDER.createAndBindUi(this));

	}
	
	private void handleSubmitUser() {
		
		//Delegate validation, save, etc to presenter
		//presenter.saveUser(driver.flush());
		
		//IValidator<User> userValidator = GWT.create(User.class);
		
		
		
		User edited = driver.flush();
		//Set<InvalidConstraint<User>> violations = userValidator.validate(edited);
		//Window.alert(violations.size()+"");
		
		///START UGLY VALIDATION
		UserValidator uv = new UserValidator();
		if ( uv.validate(edited, this.userEditor) ) {
			presenter.saveUser(edited);
		}
		
		//END UGLY VALIDATION
		
	}
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
		
	}
	
	interface Binder extends UiBinder<ScrollPanel, RegisterUserView> {
		 
	}

	@Override
	public void initialize() {
		//Initialize driver.
		driver.initialize(userEditor);
		driver.edit(this.user);
		userEditor.clearErrors();
		//register submit handle
		this.userEditor.getSubmit().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				handleSubmitUser();
			}
		});
		
		
	}

	@Override
	public void setUser(User user) {
		this.user = user;
		
	}

	

}
