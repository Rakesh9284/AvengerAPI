package com.vbrick.avenger.ObjProperty;


public enum AvengerSystemSettingsPropertyPage {
	avengersystemsettingspage_securitylinkLocator("//div/a[contains(@uisref,'system-settings.security')]"),
	avengersystemsettingspage_apikeyslinkLocator("//div/a[contains(@ui-sref,'system-settings.api-keys')]"),
	avengersystemsettingspage_environmentlinklocator("//div/a[contains(@ui-sref,'system-settings.environment')]"),
	avengersystemsettingspage_brandinglinklocator("//div/a[contains(@ui-sref,'system-settings.branding')]"),
	avengersystemsettingspage_customfiledslinklocator("//div/a[contains(@uisref,'system-settings.metadata')]"),
	avengersystemsettingspage_homepage("//div/a[contains(@ui-sref,'system-settings.homepage-settings')]"),
	avengersystemsettingspage_systemMessageLocator("//div/a[contains(@ui-sref,'system-settings.system-messages')]");
	
	private String property;
	private AvengerSystemSettingsPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
