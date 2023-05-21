package com.vbrick.avenger.ObjProperty;

public enum RootAccountPropertyPage {
	
	rootaccountpg_accountname("accountName"),
	rootaccountpg_accounthostname("hostname"),
	rootaccountpg_username("username"),
	rootaccountpg_password("password"),
	rootaccountpg_confirmpassword("confirmPassword"),
	rootaccountpg_emailaddress("rootUserEmail"),
	rootaccountpg_emailserveraddress("smtpHostname"),
	rootaccountpg_port("smtpPort"),
	rootaccountpg_emailserverusername("smtpUsername"),
	rootaccountpg_emailserverpassword("smtpPassword"),
	rootaccountpg_entercontactinformationbuttonlocator("//*[@name='setupForm1']/div[2]/h2/div/button"), 
	rootaccountpg_triggeraddresstextboxlocator("smtpFromAddress"),
	rootaccountpg_lastnametextboxlocator("lastName"),
	rootaccountpg_firstnametextboxlocator("//*[@ng-model='rootAccount.rootUser.firstName']"),
	rootaccountpg_titletextboxlocator("//*[@ng-model='rootAccount.rootUser.title']"),
	rootaccountpg_phonenotextboxlocator("//*[@ng-model='rootAccount.rootUser.phone']")
	;
	private String property;
	private RootAccountPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}


}
