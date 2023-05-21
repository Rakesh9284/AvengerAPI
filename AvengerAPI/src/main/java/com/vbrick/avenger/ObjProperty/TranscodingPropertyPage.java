package com.vbrick.avenger.ObjProperty;

public enum TranscodingPropertyPage {
	
	transcoding_addpresetbuttonlocator("Add Preset"),
	transcoding_settingsbuttonlocator("Settings");
	
	private String property;
	private TranscodingPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
	

}
