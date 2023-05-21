package com.vbrick.avenger.ObjProperty;

public enum AvengerContentRestrictionPropertyPage {
	avengercontentrestrictionpage_contentrestrictionlinkLocator("//div/a[contains(@uisref,'system-settings.content-restriction')]"),
	avengercontentrestrictionpage_contentrestrictionpagename("//h4[contains(text(),'Content Restriction')]"),
	avengercontentrestrictionpage_savebuttonlocator("//*[contains(@class,'panel-footer')]//*[@type='submit']");
	
	private String property;
	
	private AvengerContentRestrictionPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}



}
