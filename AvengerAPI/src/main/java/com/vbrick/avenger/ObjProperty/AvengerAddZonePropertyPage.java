package com.vbrick.avenger.ObjProperty;

    public enum AvengerAddZonePropertyPage {

	avengeraddzonepage_zonenamelocator("//*[@ng-model='zone.name']"),
	avengeraddzonepage_supportsmulticastlocator("//*[@ng-model='zone.supportsMulticast']"),
	avengeraddzonepage_ipaddresslocator("ips"),
	avengeraddzonepage_devicenamelocator("device"),
	avengeraddzonepage_addanotherdevicelocator("//*[@ng-click='addDevice()']"),
	avengeraddzonepage_createbuttonlocator("//*[contains(@class,'footer')]//*[@type='submit']"),
	avengeraddzonepage_cancelbuttonlocator("//button[contains(@ui-sref,'manage-zones')]"),
	avengeraddzonepage_removedevicebuttonlocator("//button[contains(@ng-click,'removeDevice')]"),
	avengeraddzonepage_nodeviceerrorlocator("//strong"),
	avengeraddzonepage_samedeviceerrorlocator("//*[contains(@ng-show,'duplicate')][not(contains(@class,'ng-hide'))]/strong"),
	avengeraddzonepage_uniquezoneerrorlocator("//*[@validation='zoneNameInUse']"),
	avengeraddzonepage_customizedevicebuttonlocator("//*[contains(@ng-click,'showCustomStream')]"),
	avengeeaddzonepage_selectedstreamsearchlocator("//*[@ng-model='selectedFilterTxt']"),
	avengeeaddzonepage_availablestreamsearchlocator("//*[@ng-model='availableFilterTxt']"),
	avengeeaddzonepage_removestreamlocator("//*[contains(@ng-click,'remove(stream)')]"),
	avengeeaddzonepage_addstreamlocator("//*[@ng-click='add(stream)'][1]");
		
	
	private String property;
	private AvengerAddZonePropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
}
