package com.vbrick.avenger.ObjProperty;

public enum AvengerTranscodingGlobalSettingsPropertyPage {

	avengertranscodingglobalsettingspage_addvideodefaultvaluelocator("//*[@class='bulleted-items']/li"),
	avengertranscodingglobalsettingspage_recorddefaultvaluelocator("//*[@name='Record']/div/div[1]/div[1]/ul/li[2]"),
	avengertranscodingglobalsettingspage_schedulerecorddefaultvaluelocator("locatorremoved"),
	avengertranscodingglobalsettingspage_webcastrecorddefaultvaluelocator("locatorremoved"),
	avengertranscodingglobalsettingspage_savechangesbuttonlocator("//*[contains(@class,'footer')]//*[@ng-click='saveSettings(transcodingSettings)']"),
	avengertranscodingglobalsettingspage_addvideolabellocator("Add Video"),
	avengertranscodingglobalsettingspage_recordlabellocator("Record"),
	avengertranscodingglobalsettingspage_schedulerecordlabellocator("Record Scheduled Event"),
	avengertranscodingglobalsettingspage_webcastrecordlabellocator("Webcast Record"),
	avengertranscodingglobalsettingspage_addvideoeditbuttonlocator("//*[@name='Add Video']/div/div[1]/div[1]/div[1]/button"),
	avengertranscodingglobalsettingspage_assignedpresetslocatorforaddvideo("//*[contains(@name,'Add Video')]//*[contains(@ng-click,'remove(preset)')]/div/div"),
	avengertranscodingglobalsettingspage_availablepresetlocator("//*[@class='table-cell all-elements']/div[3]"),
	avengertranscodingglobalsettingspage_allavailablepresetslocator("//*[@ng-click='add(preset)']/div/div"),
	avengertranscodingglobalsettingspage_allassignedpresetslocator("//*[@ng-click='remove(preset)']"),
	avengertranscodingglobalsettingspage_recordeditbuttonlocator("//*[@name='Record']/div/div[1]/div[1]/div[1]/button"),
	avengertranscodingglobalsettingspage_schedulerecordeditbuttonlocator("//*[@name='Scheduled Record']/div/div[1]/div[1]/button"),
	avengertranscodingglobalsettingspage_webcastrecordeditbuttonlocator("//*[@name='Webcast Record']/div/div[1]/div[1]/button"),
	avengertranscodingglobalsettingspage_assignedpresetslocatorforrecord("//*[contains(@name,'Record')]//*[contains(@ng-click,'remove(preset)')]/div/div"),
	avengertranscodingglobalsettingspage_allavailablepresetslocatorforaddvideo("//*[contains(@name,'Add Video')]//*[contains(@ng-click,'add(preset)')]/div/div"),
	avengertranscodingglobalsettingspage_allavailablepresetslocatorforrecord("//*[contains(@name,'Record')]//*[contains(@ng-click,'add(preset)')]/div/div");
	
	
	
	private String property;
	private AvengerTranscodingGlobalSettingsPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}


}
