package com.vbrick.avenger.ObjProperty;

   public enum AvengerAddNewDmeDevicePropertyPage {
	
	avengeraddnewdmedevicepropertypage_devicenamelocator("//*[@name='deviceName']"),
	avengeraddnewdmedevicepropertypage_macaddresslocator("macAddress"),
	avengeraddnewdmedevicepropertypage_createbuttonlocator("//*[contains(@class,'footer')]//*[@type='submit']"),
	avengeraddnewdmedevicepropertypage_streamname("//*[contains(@name,'dmeVideoStreams')]//*[@placeholder='Name']"),
	avengeraddnewdmedevicepropertypage_streamurl("//*[contains(@name,'dmeVideoStreams')]//*[@placeholder='URL']"),
	avengeraddnewdmedevicepropertypage_encodingtype("//*[contains(text(),'Video Streams')]/..//*[contains(text(),'Encoding Type')]/.."),
	avengeraddnewdmedevicepropertypage_ismulticast("//*[contains(text(),'Multicast')]//input"),
	avengeraddnewdmedevicepropertypage_addurl("//*[contains(text(),'Add URL')]"),
	avengeraddnewdmedevicepropertypage_advancedtab("//*[@aria-label='Tabs']//*[contains(text(),'Advanced')]"),
	avengeraddnewdmedevicepropertypage_createurlstreamnamelocator("//*[contains(@name,'dmeStreamGroups')]//*[@placeholder='Name']"),
	avengeraddnewdmedevicepropertypage_prepositionContentcheckboxlocator("//*[@id='prepositionContent']"),
	avengeraddnewdmedevicepropertypage_vodplaybackcheckboxlocator("//*[@id='isVideoStorageDevice']"),
	avengeraddnewdmedevicepropertypage_prepositionContentcheckboxhide("//*[@id='prepositionContent']/.."),
	avengeraddnewdmedevicepropertypage_dmestreams("//*[@ng-show='group.active']/li/div/div[2]"),
	avengeraddnewdmedevicepropertypage_streamscancelbutton("//*[@ ng-click='remove($index)']/../button[2]"),
	avengeraddnewdmedevicepropertypage_dmedevicecreationcancelbutton("(//*[@class='btn-toolbar']/a)[1]"),
	avengeraddnewdmedevicepropertypage_dmeerromsgpopokbutton("//*[@class='modal-footer group ng-scope']//*[@ng-click='close()']");
	
	private String property;
	private AvengerAddNewDmeDevicePropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
