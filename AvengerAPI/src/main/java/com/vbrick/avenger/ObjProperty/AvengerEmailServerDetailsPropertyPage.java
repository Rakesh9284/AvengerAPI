package com.vbrick.avenger.ObjProperty;


    public enum AvengerEmailServerDetailsPropertyPage {

	
	emailserveraddresslocator("smtpHostname"),
	portlocator("smtpPort"),
	emailserverusernamelocator("smtpUserName"),
	emailserverpasswordlocator("smtpPassword"),
	emailfromaddresslocator("smtpFromAddress"),
	savechangesbuttonlocator("//*[@name='environmentForm']//*[@type='submit']"),
	cancelbuttonlocator("//*[@name='environmentForm']//a"),
	savedsuccesslocator("//div[@ng-show='status.saveSuccessful']"),
	emailserveraddresserrortext("//label[@ng-show='environmentForm.smtpHostname.$error.required']"),
	porterrortext("//label[@ng-show='environmentForm.smtpPort.$error.required']");
	
	private String property;
	
	private AvengerEmailServerDetailsPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
}
