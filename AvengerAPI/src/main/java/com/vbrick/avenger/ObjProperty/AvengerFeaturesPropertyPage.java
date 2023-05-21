package com.vbrick.avenger.ObjProperty;

    public enum AvengerFeaturesPropertyPage {
	
	avengerfeaturespage_enablecomments("//*[@name='enableComments']"),
	avengerfeaturespage_savechangesbuttonlocator("//*[contains(text(),'Save Changes')]"),
	avengerfeaturespage_enableratings("enableRatings"),
	avengerfeaturespage_dashboardlink("//*[@class='navbar-brand']"),
	avengerfeaturespage_enablecategorieslocator("enableCategories"),
	avengerfeaturespage_enabletagslocator("enableTags"),
	avengerfeaturespage_enabledownloadslocator("enableDownloads"),
	avengerfeaturespage_webExIntegrationlocator("//*[contains(@ng-model,'webExSettings.isEnabled')]"),
	avengerfeaturespage_ssocheckboxlocator("//*[contains(@ng-model,'webExSettings.isSsoEnabled')]"),
	avengerfeaturespage_hostedwebsitelocator("webExUrl"),
	avengerfeaturespage_siteidlocator("webExSiteId"),
	avengerfeaturespage_partneridlocator("webExPartnerId"),
	avengerfeaturespage_adminusernamelocator("webExUserName"),
	avengerfeaturespage_adminpasswordlocator("webExPassword"),
	avengerfeaturespage_nbrstorageserviceurllocator("nbrStorageServiceUrl"),
	avengerfeaturespage_enabelembedscheckbox("//*[contains(@id,'enableEmbeds')]"),
	avengerfeaturespage_enabelembedsautoplaycheckbox("//*[contains(@ng-model,'features.autoplayDefault')]"),
	avengerfeaturespage_enableaddurlscheckbox("enableAddUrls"),
	avengerfeaturespage_addedviaurltext("//*[contains(@ng-model,'enableAddUrls')]/.."),
	avengerfeaturespage_sparkIntegrationlocator("//*[contains(@ng-model,'sparkSettings.isEnabled')]"),
	avengerfeaturespage_sparkIntegrationClientIDlocator("clientId"),
	avengerfeaturespage_sparkIntegrationClientSecretlocator("clientSecret"),
	avengerfeaturespage_voicebaseintegrationcheckbox("//*[contains(@ng-model,'features.voiceBaseSettings')]"),
	avengerfeaturespage_voicebaseintegrationAPIkeytextbox("//*[contains(@ng-model,'features.voiceBaseSettings.apiKey')]"),
	avengerfeaturespage_voicebaseintegrationpasswordtextbox("//*[contains(@ng-model,'features.voiceBaseSettings.password')]']"),
	avengerfeaturespage_tropointegration("//*[contains(@ng-model,'features.tropoSettings.isEnabled')]"),
	avengerfeaturespage_messagetokentextboxlocator("//*[contains(@ng-model,'features.tropoSettings.token')]"),
	avengerfeaturespage_manualbuttonlocator("(//*[contains(@ng-model,'features.slideDelay.isEnabled')])[2]"),
	avengerfeaturespage_delayinsecondstextboxlocator("//*[contains(@name,'delaySeconds')]"),
	avengerfeaturespage_offbuttonlocator("(//*[contains(@ng-model,'features.slideDelay.isEnabled')])[1]"),
	avengerfeaturespage_supplementalContent("enableSupplementalContent"),
	avengerfeaturespage_settingssavebuttonlocator("//*[contains(@class,'modal-footer')]/button[contains(@type,'submit')]"),
    avengerfeaturespage_sendlinkurltoallattended("//*[contains(@name,'webcastSendLinkToAttendeesEnabled')]");
	private String property;
	private AvengerFeaturesPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
	

}
