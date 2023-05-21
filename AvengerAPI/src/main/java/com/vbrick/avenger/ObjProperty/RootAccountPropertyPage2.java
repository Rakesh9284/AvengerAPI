package com.vbrick.avenger.ObjProperty;

public enum RootAccountPropertyPage2 {
	
	rootaccountpg2_firstname("contactFirstName"),
	rootaccountpg2_lastname("contactLastName"),
	rootaccountpg2_emailaddress("contactEmail"),
	rootaccountpg2_addressline1("//input[@ng-model='rootAccount.contactAddress.line1']"),
	rootaccountpg2_addressline2("//input[@ng-model='rootAccount.contactAddress.line2']"),
	rootaccountpg2_country("//*[@ng-model='rootAccount.contactAddress.countryCode']"),
	rootaccountpg2_state("//input[@ng-model='rootAccount.contactAddress.state']"),
	rootaccountpg2_city("//input[@ng-model='rootAccount.contactAddress.city']"),
	rootaccountpg2_postalcode("//input[@ng-model='rootAccount.contactAddress.postalCode']"),
	rootaccountpg2_phoneno("//input[@ng-model='rootAccount.contactPhone']"),
	rootaccountpg2_createrootaccountbuttonlocator("//*[contains(text(), 'Create Root Account')]"),
	rootaccountpg2_preferredlanguagelocator("//*[@ng-model='rootAccount.language']");
	
	private String property;
	private RootAccountPropertyPage2(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
	

}
