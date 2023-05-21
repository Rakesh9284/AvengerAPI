package com.vbrick.avenger.ObjProperty;

    public enum AvengerAddAkamaiDevicePropertyPage {

	avangeraddakamaidevicepropertypage_akamaidevicename("//*[@ng-model='akamaiDevice.name']"),
	avangeraddakamaidevicepropertypage_akamaidevicehostname("//*[@ng-model='akamaiDevice.hostname']"),
	avangeraddakamaidevicepropertypage_akamaisecuredelivercheckbox("//*[@ng-model='akamaiDevice.isSecureDelivery']"),
	avangeraddakamaidevicepropertypage_akamaicreatebuttonlocator("//*[@ng-show='createMode']/.."),
	avangeraddakamaidevicepropertypage_securedeliverylocator("isSecureDelivery");
	
	private String property;
	private AvengerAddAkamaiDevicePropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
}
