package com.vbrick.avenger.dao;

public class LoginBeanPage {
	
	
	private String Username;
	private String Password;
	private String helpTextUsername;
	private String helpTextPassword;
	private String errorMessage;
	private String Booleanvalue;
	private String invalidUsername;
	private String invalidPassword;
	
	
	
	public String getInvalidUsername() {
		return invalidUsername;
	}
	public void setInvalidUsername(String invalidUsername) {
		this.invalidUsername = invalidUsername;
	}
	public String getInvalidPassword() {
		return invalidPassword;
	}
	public void setInvalidPassword(String invalidPassword) {
		this.invalidPassword = invalidPassword;
	}
	
	public String getBooleanvalue() {
		return Booleanvalue;
	}
	public void setBooleanvalue(String booleanvalue) {
		Booleanvalue = booleanvalue;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getHelpTextUsername() {
		return helpTextUsername;
	}
	public void setHelpTextUsername(String helpTextUsername) {
		this.helpTextUsername = helpTextUsername;
	}
	public String getHelpTextPassword() {
		return helpTextPassword;
	}
	public void setHelpTextPassword(String helpTextPassword) {
		this.helpTextPassword = helpTextPassword;
	}
	
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}

}
