package com.vbrick.avenger.ObjProperty;

public enum MediaSettingsPropertyPage {


	mediasettings_categorieslinklocator("//*[contains(@uisref,'portal.admin.media-settings.categories')]"),
	mediasettings_librarieslinklocator("(//a[@uisref='portal.admin.user-access.teams'])"),
	mediasettings_transcodinglinklocator("//div/a[contains(@ui-sref,'media-settings.transcoding')]"),
	mediasettings_featureslinklocator("//div/a[contains(@uisref,'media-settings.features')]"),
	medaisettings_playersettingslinklocator("//div/a[contains(@ui-sref,'media-settings.players')]"),
	mediasettings_approvalprocesseslinklocator("//div/a[contains(@uisref,'approval-processes')]"),
	mediasettings_expirationmanagementlinklocator("(//a[contains(@href,'manage-expiration')])[1]"),
	mediasettings_approvalprocessesQueuelinklocator("//div/a[contains(@uisref,'portal.admin.media-settings.approval-queue')]");
 	
	private String property;

	private MediaSettingsPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
