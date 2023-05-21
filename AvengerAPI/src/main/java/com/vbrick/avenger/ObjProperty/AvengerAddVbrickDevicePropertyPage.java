package com.vbrick.avenger.ObjProperty;


    public enum AvengerAddVbrickDevicePropertyPage {
	avengeraddvbrickdevicepage_devicenamelocator("//input[@name='name']"),
	avengeraddvbrickdevicepage_macaddresslocator("//input[@name='macAddress']"),
	avengeraddvbrickdevicepage_createbuttonlocator("//*[contains(@class,'footer')]//*[@type='submit']"),
	avengeraddvbrickdevicepage_streamname("streamName"),
	avengeraddvbrickdevicepage_streamurl("//*[@ng-model='stream.url']"),
	avengeraddvbrickdevicepage_encodingtype("//*[@ng-model='stream.encodingType']"),
	avengeraddvbrickdevicepage_ismulticast("//*[@ng-model='stream.isMulticast']"),
	avengeraddvbrickdevicepage_addurl("//*[@ng-click='add()']"),
	avengeraddvbrickdevicepage_deleteurllocator("//*[@ ng-click='remove($index)']"),
	avengeraddvbrickdevicepage_removedevicelocator("//*[contains(@ng-click,'remove')]"),
	avengeraddvbrickdevicepage_canceldeletebutton("//*[@ng-show='stream.delete']");
	
	
	private String property;
	private AvengerAddVbrickDevicePropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
