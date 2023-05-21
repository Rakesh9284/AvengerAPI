package com.vbrick.avenger.ObjProperty;

public enum AvengerPresentationProfilesPropertyPage {

	avengerpresentationprofilespage_AddaPresentationprofilelocator("//*[contains(@uisref,'devices.create-presentation-profile')]");

	private String property;
	private AvengerPresentationProfilesPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
}
