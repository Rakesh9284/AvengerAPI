package com.vbrick.avenger.ObjProperty;

public enum CreateChildAccountPropertyPage {

	createchildAccount_accountname("name"),
	createchildAccount_accounthostname("hostname"),
	createchildAccount_firstname("//input[@ng-model='account.contactFirstName']"),
	createchildAccount_lastname("contactLastName"),
	createchildAccount_contactemail("contactEmail"),
	createchildAccount_addressline1("//input[@ng-model='account.contactAddress.line1']"),
	createchildAccount_addressline2("//input[@ng-model='account.contactAddress.line2']"),
	createchildAccount_country("//select[@ng-model='account.contactAddress.countryCode']"),
	createchildAccount_state("//input[@ng-model='account.contactAddress.state']"),
	createchildAccount_city("//input[@ng-model='account.contactAddress.city']"),
	createchildAccount_postalcode("//input[@ng-model='account.contactAddress.postalCode']"),
	createchildAccount_phoneno("//input[@ng-model='account.contactPhone']"),
	createchildAccount_createbuttonlocator("//*[contains(@class,'footer')]//*[text()='Create']"),
	createchildAccount_addaccountlocator("//*[@ui-sref='portal.admin.account-add']"),
	createchildAccount_accountinformation("//*[@name='accountForm']/div/div[2]/div[1]"),
	createchildAccount_hostnameerror("//label[@ng-show='accountForm.hostname.$error.required']"),
	createchildAccount_accountnameerror("//label[@ng-show='accountForm.name.$error.required']"),
	createchildAccount_lastnameerror("//label[@ng-show='accountForm.contactLastName.$error.required']"),
	createchildAccount_emailerror("//*[@name='contactEmail']/../../div[4]/label[1]"),
	createchildAccount_preferredlanguage("//*[@ng-model='account.language']"),
	createchildAccount_savebuttonlocator("//*[@class='panel-footer']//*[@class='btn-toolbar']//button[1]"), 
	createchildAccount_cancelbuttonlocator("//*[contains(text(),'Cancel')]"),
	createchildAccount_invalidemailerrortextlocator("//*[@name='contactEmail']/../../div[4]/label[2]"),
	createchildAccount_maximumactiveuserslocator("licenseCount"),
	createchildAccount_billingaddresscheckbox("//input[@ng-model='useDifferentBillingAddress']"),
	createchildAccount_billinginfofirstname("//*[@ng-model='account.billingFirstName']"),
	createchildAccount_billinginfolastname("//*[@ng-model='account.billingLastName']"),
	createchildAccount_billinginfocontactemail("//*[@ng-model='account.billingEmail']"),
	createchildAccount_billinginfoaadressline1("//*[@ng-model='account.billingAddress.line1']"),
	createchildAccount_billinginfoaadressline2("//*[@ng-model='account.billingAddress.line2']"),
	createchildAccount_billinginfocountry("//*[@ng-model='account.billingAddress.countryCode']"),
	createchildAccount_billinginfostate("//input[@ng-model='account.billingAddress.state']"),
	createchildAccount_billinginfocity("//*[@ng-model='account.billingAddress.city']"),
	createchildAccount_billinginfopostalcode("//*[@ng-model='account.billingAddress.postalCode']"),
	createchildAccount_billinginfophonenumber("//*[@ng-model='account.billingPhone']"),
	createchildAccount_billinginfofootersavebutton("//*[@class='panel-footer']//*[@class='btn-toolbar']/button[1]"),
	createchildAccount_Licensedhoursetable("//*[@class='vb-input-field']"),
	createchildAccount_allocatinghoursbutton("//*[@class='vb-input-field']/button"),
	createchildAccount_allocatingpopup("//*[@class='modal-body']"),
	createchildAccount_allocatingexpiredatetextbox("//*[contains(@class,'input-date')]"),
	createchildAccount_allocatingpoupsavebutton("//*[@class='modal-footer']/button[2]"),
	createchildAccount_allocatinghoursfrom("from"),
	createchildAccount_allocatinghoursto("hours"),
	createchildAccount_timezone("timezone"), 
	createchildAccount_licensecount("//input[@name='licenseCount']");
	
	private String property;
	private CreateChildAccountPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	
	
}


