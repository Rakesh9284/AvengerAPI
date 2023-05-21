package com.vbrick.avenger.dao;

public class CreatePasswordBeanPage {
	private String Password;
	private String confirmpassword;
	private String securityquestion;
	private String typeyouranswer;
	private String invalidpassword;
	public String getPassword() {
		return Password;
	}
	public String getInvalidpassword() {
		return invalidpassword;
	}
	public void setInvalidpassword(String invalidpassword) {
		this.invalidpassword = invalidpassword;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	public String getSecurityquestion() {
		return securityquestion;
	}
	public void setSecurityquestion(String securityquestion) {
		this.securityquestion = securityquestion;
	}
	public String getTypeyouranswer() {
		return typeyouranswer;
	}
	public void setTypeyouranswer(String typeyouranswer) {
		this.typeyouranswer = typeyouranswer;
	}
	
	
}
