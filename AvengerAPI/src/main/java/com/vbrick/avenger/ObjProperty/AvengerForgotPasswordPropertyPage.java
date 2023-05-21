package com.vbrick.avenger.ObjProperty;


public enum AvengerForgotPasswordPropertyPage {

	
	avengerforgotpasswordpage_securityanswerlocator("//*[@ng-model='passwordReset.securityAnswer']"),
	avengerforgotpasswordpage_passwordlocator("//*[@name='password']"),
	avengerforgotpasswordpage_confirmpasswordlocator("//*[@name='confirmPassword']"),
	avengerforgotpasswordpage_submitbuttonlocator("//*[@type='submit']"),
	avengerforgotpasswordpage_securityquestiontext("//*[@name='passwordResetForm']/h4[1]"),
	avengerforgotpasswordpage_securityanswererrortext("//*[@ng-show='status.passwordResetFailed']"),
    forgotpg_SubmitbuttonLocator("//button[@type='submit']"), 
	forgotpg_emailSendMessagelocator("//div[@ng-show='status.success']"),
	forgot_SignInlocator("//a[@ng-show='status.success']"),
	securityanswerblankerrortextlocator("//*[@ng-show='passwordResetForm.securityAnswer.$error.required']");
	
	private String property;
	private AvengerForgotPasswordPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	
}
