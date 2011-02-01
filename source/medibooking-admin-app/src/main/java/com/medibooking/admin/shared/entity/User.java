package com.medibooking.admin.shared.entity;

import com.google.gwt.core.client.GWT;

import name.pehl.piriti.client.json.Json;
import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.client.json.JsonWriter;


public class User implements ValueObject{

	public interface UserReader extends JsonReader<User>{}
	public static final UserReader READER = GWT.create(UserReader.class);

	public interface UserWriter extends JsonWriter<User>{}
	public static final UserWriter WRITER = GWT.create(UserWriter.class);

	
	
	@Json Long id;
	
	@Json String name;
	@Json String email;
	@Json String emailConfirmation;
	//@Json String phone;
	@Json String password;
	@Json String passwordConfirmation;
	//@Json String address;
	@Json Boolean termsAgreement;
	@Json UserType userType;
	@Json Boolean keepLogged;
	
	
	public Boolean getKeepLogged() {
		return keepLogged;
	}
	public void setKeepLogged(Boolean keepLogged) {
		this.keepLogged = keepLogged;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailConfirmation() {
		return emailConfirmation;
	}
	public void setEmailConfirmation(String emailConfirmation) {
		this.emailConfirmation = emailConfirmation;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	public Boolean getTermsAgreement() {
		return termsAgreement;
	}
	public void setTermsAgreement(Boolean termsAgreement) {
		this.termsAgreement = termsAgreement;
	}
	
	
}
