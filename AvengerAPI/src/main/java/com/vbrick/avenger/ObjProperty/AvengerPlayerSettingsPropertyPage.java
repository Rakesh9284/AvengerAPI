package com.vbrick.avenger.ObjProperty;

public enum AvengerPlayerSettingsPropertyPage {

	avengerplayersettingspropertypage_windowsfirstpreferencelocator("windowsFirstRank"),
	avengerplayersettingspropertypage_windowssecondpreferencelocator("windowsSecondRank"),
	avengerplayersettingspropertypage_windowsthirdpreferencelocator("windowsThirdRank"),
	avengerplayersettingspropertypage_macfirstpreferencelocator("macFirstRank"),
	avengerplayersettingspropertypage_macsecondpreferencelocator("macSecondRank"),
	avengerplayersettingspropertypage_macthirdpreferencelocator("macThirdRank"),
	avengerplayersettingspropertypage_macplayerselectederrorlocator("//*[@ng-show='playerRankForm.macThirdRank.$error.taken']"),
	avengerplayersettingspropertypage_windowsplayerselectederrorlocator("//*[@ng-show='playerRankForm.windowsThirdRank.$error.taken']"),
	avengerplayersettingspropertypage_macrankrequirederrorlocator("//*[@validation='skippedSecond'][contains(@ng-show,'macThirdRank')]"),
	avengerplayersettingspropertypage_windowsrankrequirederrorlocator("//*[@validation='skippedSecond'][contains(@ng-show,'windowsThirdRank')]"),
	avengerplayersettingspropertypage_savebuttonlocator("//*[@name='playerRankForm']//*[@type='submit']"),
	avengerplayersettingspropertypage_playertextlocator("//*[@class='panel-heading']/h4");
	
	
	
	private String property;
	private AvengerPlayerSettingsPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
}
