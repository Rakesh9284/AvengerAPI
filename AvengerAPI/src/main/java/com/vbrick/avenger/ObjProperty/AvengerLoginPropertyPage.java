package com.vbrick.avenger.ObjProperty;


public enum AvengerLoginPropertyPage {

	//Login page
		loginpg_usernameLocator("//*[@id='username']"),
		loginpg_passwordLocator("//*[@id='password']"),
		loginpg_loginButtonLocator("//button[@type='submit']"),
		loginpg_forgotPasswordLinkLocator("//a[contains(@ui-sref,'forgot-password')]"),
		loginpg_errormsg("//form[@name='loginForm']/div[2]"),
		loginpg_errormg_suspenduser_Text("//*[@ng-show='status.suspended']"),
		loginpg_errormg_suspenduser("//div[@ng-show='status.suspended']/div"),
		loginpg_errormg_systeminaccesibleerrortext("//div[@ng-show='status.error']/div"),
		loginpg_errormg_incorrectusernamepassworderrortext("//div[@ng-show='status.badCredentials']/div"),
		loginpg_errormg_lockedouterrortext("//div[@ng-show='status.lockedOut']/div"),
		loginpg_errormg_linknotvaliderrortext("//*[contains(text(),'This link is no longer valid.')]"),
		loginpg_errormg_loggedouterrortext("//div[@ng-show='status.loggedOut']/div"), 
		loginpg_forgotpasswordLocator("//*[contains(text(),'Forgot Password?')]"), 
		forgotpg_forgotpassworduserNameLocator("username"),
	    loginpage_errormsg_userlocked("//div[contains(@class,'error-msg')]//p"),
	    loginpage_returnloginbuton("//*[contains(@ui-sref,'login')]");
		private String property;
		private AvengerLoginPropertyPage(String property) {
			this.setProperty(property);
		}

		public String getProperty() {
			return property;
		}

		public void setProperty(String property) {
			this.property = property;
		}
	
}
