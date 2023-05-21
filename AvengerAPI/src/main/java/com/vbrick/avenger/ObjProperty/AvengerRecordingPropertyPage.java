package com.vbrick.avenger.ObjProperty;

public enum AvengerRecordingPropertyPage {

	avengerrecordingpage_primarydmelocator("//select[@name='primaryDme']"),
	avengerrecordingpage_secondarydmelocator("secondaryDme"),
	avengerrecordingpage_savebuttonlocator("//*[@ng-click='reload()']/../button[2]");
	
	
	private String property;
	private AvengerRecordingPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
}
