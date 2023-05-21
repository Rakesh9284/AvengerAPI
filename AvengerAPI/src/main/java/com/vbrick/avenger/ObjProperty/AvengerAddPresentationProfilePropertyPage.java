package com.vbrick.avenger.ObjProperty;

   public enum AvengerAddPresentationProfilePropertyPage {

	avengeraddpresentationprofilepage_namelocator("//input[@name='profileName']"),
	avengeraddpresentationprofilepage_descriptionlocator("//textarea[@name='description']"),
	avengeraddpresentationprofilepage_videosourcelocator("videoSource"),
	avengeraddpresentationprofilepage_removedestinationdevicelocator("//button[contains(@ng-click,'removeDestination')]"),
	avengeraddpresentationprofilepage_addanotherdestinationlocator("(//*[contains(@class,'btn btn-primary')])[2]"),
	avengeraddpresentationprofilepage_createbuttonlocator("//*[contains(@class,'footer')]//*[@type='submit']"),
	avengeraddpresentationprofilepage_cancelbuttonlocator("//*[contains(@class,'footer')]//*[@type='submit']/../a"),
	avengerpresentationprofilespage_updatebuttonlocator("//*[contains(@class,'footer')]//*[@type='submit']/div[2]"),
	avengerpresentationprofilespage_removedeviceerrorlocator("//*[contains(@ng-show,'destinationRequired')]/strong"),
	avengerpresentationprofilespage_destinationdevicelocator("device"),
	avengerpresentationprofilespage_multipledmeerrorlocator("//*[@class='clean-alert alert-danger'][contains(@ng-show,'error.duplicate')]/strong"),
	avengerpresentationprofilespage_selectedstreamsearchlocator("//*[@ng-model='selectedFilterTxt']"),
	avengerpresentationprofilespage_availablestreamsearchlocator("//*[@ng-model='availableFilterTxt']"),
	avengerpresentationprofilespage_removestreamlocator("//*[@ng-click='remove(stream)']"),
	avengerpresentationprofilespage_addstreamlocator("//*[contains(@class,'hover-wrap')]"),
	avengerpresentationprofilespage_customizedevicebuttonlocator("//*[contains(@ng-click,'customizing')]");
	
	private String property;
	private AvengerAddPresentationProfilePropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
	
}
