package com.vbrick.avenger.ObjProperty;


public enum AvengerUserPasswordParamatersPropertyPage {

	avengeruserpasswordparameters_basic("//input[@value='basic']"),
	avengeruserpasswordparameters_medium("//input[@value='medium']"),
	avengeruserpasswordparameters_strong("//input[@value='strong']"),
	avengeruserpasswordparameters_custom("//input[@value='custom']"),
	avengeruserpasswordparameters_userpasswordtext("//*[@name='settingsForm']/div[1]"),
	avengeruserpasswordparameters_savebuttonlocator("//*[contains(@class,'panel-footer')]//*[@type='submit']"),
	avengeruserpasswordparameters_uppercaseletterlocator("//div[@name='optionsCustom']/div[2]/label/input"),
	avengeruserpasswordparameters_lowercaseletterlocator("//div[@name='optionsCustom']/div[1]/label/input"),
	avengeruserpasswordparameters_numberslocator("//div[@name='optionsCustom']/div[3]/label/input"),
	avengeruserpasswordparameters_specialcharacterlocator("//div[@name='optionsCustom']/div[4]/label/input"),
	avengeruserpasswordparameters_invalidloginattemptsallowed("//input[@placeholder='5']"),
	avengeruserpasswordparameters_minnofcharacterslocator("//input[@placeholder='8']"),
	avengeruserpasswordparameters_resetpasswordlocator("//*[contains(@ng-model,'enableForgotPassword')]"),
	avengeruserpasswordparameters_basictitle("//input[@value='basic']/.."),
	avengeruserpasswordparameters_mediumdescription("//input[@value='medium']/.."),
	avengeruserpasswordparameters_strongdescription("//input[@value='strong']/.."),
	avengeruserpasswordparameters_customdescription("//input[@value='custom']/.."),
	avengeruserpasswordparameters_basicdescription("//input[@value='basic']/../div"),
	avengeruserpasswordparameters_maximumlogonattempts("//*[contains(@ng-model,'securitySettings.lockoutSettings.maximumLogOnAttempts')]"),
	avengeruserpasswordparameters_invalidlogonattempts("//*[contains(@ng-class,'maximumLogOnAttempts')]/label"),
	avengeruserpasswordparameters_sessioninactivitytimeoutlocator("//input[@placeholder='30']"),
	avengeruserpasswordparameters_sessioninactivitytimeouterrortextlocator("//*[@name='sessionTimeout']/../../div[2]/label"),
	avengeruserpasswordparameters_forgotpasswordcheckboxlocator("enableForgotPassword"),
	avengeruserpasswordparameters_enableCheckbox("//*[@ng-model='securitySettings.ssoSettings.enabled']"),
	avengeruserpasswordparameters_enableGuestAccessCheckboxLocator("//*[@ng-model='securitySettings.allowGuestAccess']"),
	avengeruserpasswordparameters_getGuestAccessURL("//*[@ng-model='securitySettings.allowGuestAccess']/../../div"),
	avengeruserpasswordparameters_lockoutperiodcheckboxlocator("//*[contains(@ng-model,'securitySettings.lockoutSettings.lockoutPeriodMinutes')]"),
	avengeruserpasswordparameters_invalidattempetsperiod("//*[contains(@ng-model,'invalidAttemptPeriodMinutes')]"),
	avengeruserpasswordparameters_lockoutperiodlocator("//*[contains(@ng-model,'lockoutPeriodMinutes')]"),
	avengerpasswordparameters_cancelbuttonlocator("//*[contains(@class,'panel-footer')]//*[@type='submit']/../a"),
	avengerpasswordparameters_Resetbuttonlocator("//*[contains(@ng-click,'resetSystemLoginAcceptance')]");
	
	private String property;
	private AvengerUserPasswordParamatersPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}


}

