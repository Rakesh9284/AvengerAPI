package com.vbrick.avenger.ObjProperty;


    public enum AvengerDevicesPropertyPage {
	
	avengerdevicespage_AddaDevicebuttonlocator("//*[contains(text(),'Add a Device')]"),
	avengerdevicespage_AddaVbricklinklocator("//*[@uisref='portal.admin.devices.encoder']"),
	avengerdevicespage_AddaLdaplinklocator("//*[contains(@role,'menu')]//*[contains(@ng-reflect-state,'ldap-conn')]"),
	avengerdevicespage_addacustomdevicelocator("//*[contains(text(),'Add Custom Device')]"),	
	avengerdevicespage_addakamaidevicelocator("//*[@uisref='portal.admin.devices.akamai']"),
	avengerdevicespage_AddaDmelinklocator("//*[contains(text(),'Add DME')]"),
	avengerdevicespage_updatedmebuttonlocator("//*[contains(@class,'footer')]//*[@type='submit']");
	
	private String property;
	private AvengerDevicesPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
