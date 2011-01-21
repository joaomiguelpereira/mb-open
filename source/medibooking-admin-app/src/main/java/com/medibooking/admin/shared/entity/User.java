package com.medibooking.admin.shared.entity;

public class User {
	
	String name;
	String email;
	String emailConfirmation;
	String phone;
	String password;
	String passwordConfirmation;
	String address;
	
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

}
