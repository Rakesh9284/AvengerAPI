package com.vbrick.avenger.ObjProperty;

    public enum AvengerAddNewCustomDevicePropertyPage {

	avengeraddnewcustomdevicepage_devicenamelocator("//*[contains(@name,'name')]"),
	avengeraddnewcustomdevicepage_ipaddresslocator("ipAddress"),
	avengeraddnewcustomdevicepage_videosourcelocator("isSource"),
	avengeraddnewcustomdevicepage_viewingdestinationlocator("isDestination"),
	avengeraddnewcustomdevicepage_streamnamelocator("streamName"), 
	avengeraddnewcustomdevicepage_streamurllocator("//*[contains(@name,'url')]"), 
	avengeraddnewcustomdevicepage_streamencodingtypelocator("//*[contains(@name,'encodingType')]"),
	avengeraddnewcustomdevicepage_streamismulticastlocator("//*[contains(text(),'Is Multicast')]/input"),
	avengeraddnewcustomdevicepage_createbuttonlocator("(//*[contains(@type,'submit')])[1]"),
	avengeraddnewcustomdevicepage_cancelbuttonlocator("(//*[contains(@type,'submit')])[1]/../a"),
	avengeraddnewcustomdevicepage_addurl("//*[contains(@ng-click,'add()')]"),
	deviceNamemadatory("//input[@name='name']/../../..//div[contains(@role,'alert')]"),
	isDestinationmandatory("//input[contains(@name,'isDestination')]/../../div"),
	ipaddressmandatory("//input[contains(@name,'ipAddress')]/../../.."),
	removedevice("//*[contains(@ng-click,'remove')]"),
	alldevicesbuttonlocator("//div[@class='combined-toolbar responsive']/div/a"),
	videostreamsource("//*[@name='isSource']/.."),
	viewingdestinion("//*[@name='isDestination']/.."),
	removestream("//button[@ng-show='!stream.delete']"),
	avengeraddnewcustomdevicepage_getstreamnamelocator("//*[@class='combined-toolbar']//h4");
	
	private String property;
	private AvengerAddNewCustomDevicePropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
}
