package com.vbrick.avenger.ObjProperty;

public enum CreatePasswordPropertyPage {
	
	createPassword_password("password"),
	createPassword_confirmpassword("confirmPassword"),
	createPassword_securityquestion("//select"),
	createPassword_typeyouranswer("//input[contains(@id,'securityAnswer')]"),
	createPassword_submitbuttonlocator("//*[@type='submit']"),
	createPassword_complexityerrortext("//label[contains(@class,'error-msg ng-binding')]"),
	createPassword_passwordlabeltext("//label[text()='Password']"),
	createPassword_confirmpasswordlabeltext("//label[text()='Confirm Password']"),
	createPassword_securityquestionlabeltext("//label[text()='Security Question:']"),
	createPassword_typeyouranswerlabeltext("//label[text()='Type your answer']"),
	createPassword_passwordonotmatcherrortext("//label[contains(text(),'Passwords do not match')]");
	
	private String property;
	private CreatePasswordPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	
	

}
