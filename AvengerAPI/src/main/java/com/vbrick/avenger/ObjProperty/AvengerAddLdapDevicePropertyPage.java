package com.vbrick.avenger.ObjProperty;


    public enum AvengerAddLdapDevicePropertyPage {
	
	
	avengeraddldapdevicepropertypage_ldapconnectornamelocator("//input[@name='ldapConnectorName']"),
	avengeraddldapdevicepropertypage_ldapconnectormacaddresslocator("//input[@name='ldapConnectorMacAddress']"),
	avengeraddldapdevicepropertypage_directorytypelocator("//select[@name='directoryType']"),
	avengeraddldapdevicepropertypage_hostlocator("//input[@name='host']"),
	avengeraddldapdevicepropertypage_syncintervallocator("//select[@name='syncInterval']"),
	avengeraddldapdevicepropertypage_createbuttonlocator("//*[@on='addMode']"),
	avengeraddldapdevicepropertypage_usernamelocator("//*[@name='userName']"),
	avengeraddldapdevicepropertypage_passwordlocator("//*[@name='password']"),
	avengeraddldapdevicepropertypage_rootscopelocator("//*[@name='rootScope']"),
	avengeraddldapdevicepropertypage_groupobjectscopelocator("groupObjectClass"),
	avengeraddldapdevicepropertypage_userobjectscopelocator("userObjectClass"),
	avengeraddldapdevicepropertypage_usernameattributelocator("userUsernameAttribute"),
	avengeraddldapdevicepropertypage_usertitleattributelocator("userTitleAttribute"),
	avengeraddldapdevicepropertypage_userfirstnameattributelocator("userFirstNameAttribute"),
	avengeraddldapdevicepropertypage_userlastnameattributelocator("userLastNameAttribute"),
	avengeraddldapdevicepropertypage_useremailaddressattributelocator("userEmailAddressAttribute"),
	avengeraddldapdevicepropertypage_telephonenumberattributelocator("userPhoneNumberAttribute"),
	avengeraddldapdevicepropertypage_ldapconnectornameerrotextlocator("//*[@ng-show='ldapConnectorForm.ldapConnectorName.$error.required']"),
	avengeraddldapdevicepropertypage_ldapconnectormacaddresserrortextlocator("//*[@ng-show='ldapConnectorForm.ldapConnectorMacAddress.$error.required']"),
	avengeraddldapdevicepropertypage_membershiplocator("membershipAttribute"),
	avengeraddldapdevicepropertypage_alldevicesbuttonlocator("//div[@class='combined-toolbar responsive']/div/a"),
	avengeraddldapdevicepropertypage_portlocator("//*[@name='port']/.."),
	avengeraddldapdevicepropertypage_sslenabled("//*[@name='ssl']"),
	avengeraddldapdevicepropertypage_directconnectionactivebutton("//*[contains(@ng-model,'directConnection')][1]"),
	avengeraddldapdevicepropertypage_directconnectioninactivebutton("//*[contains(@ng-model,'directConnection')][2]"),
	avengeraddldapdevicepropertypage_connectornodemacaddress("(//*[@name='macAddress'])[1]"),
	avengeraddldapdevicepropertypage_connectornodemacstatus("(//*[contains(@ng-click,'verifyConnectorNodeRequired')])[1]"),
	avengeraddldapdevicepropertypage_addconnectornodebutton("//*[contains(@ng-click,'addConnectorNode')]"),
	avengeraddldapdevicepropertypage_addconnectornodemacaddress2("(//*[@name='macAddress'])[2]"),
	avengeraddldapdevicepropertypage_secondmacaddressstatus("(//*[@name='macAddress'])[2]/../../../..//button[2]");
	
	private String property;
	private AvengerAddLdapDevicePropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}


}
