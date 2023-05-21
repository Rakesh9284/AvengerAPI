package com.vbrick.avenger.ObjProperty;

   public enum AvengerEditVideoSettingsPropertyPage {
	
	
	avengereditvideosettingspage_titlelocator("title"),
	avengereditvideosettingspage_descriptionlocator("cke_editor1"),
	avengereditvideosettingspage_categorieslocator("//*[contains(@class,'MkQVa__tag')]/span"),
	avengereditvideosettingspage_tagslocator("//*[contains(@class,'tagify__tag-text')]"),
	avengereditvideosettingspage_librarieslocator("//*[@id='libraryInput']/..//input[@type='text']"),
	avengereditvideosettingspage_saveandexitbuttonlocator("//video-settings/form/div[1]/vb-ui-toolbar[2]//button[2]"),
	avengereditvideosettingspage_addtagbuttonlocator("//*[@name='new-tags']/../span/button"),
	avengereditvideosettingspage_titleerrortextlocator("//*[@name='title']/../div/label"),
	avengereditvideosettingspage_cancelbuttonlocator("(//*[contains(text(),'Cancel')])[2]"),
	avengereditvideosettingspage_libraryselectlocator("//*[@class='collection-item active']"),
	avengereditvideosettingspage_getcategorieslocator("//vb-categories-tags-input//*[contains(@aria-label,'remove tag')]/..//span"),
	avengereditvideosettingspage_blanklibraryerrortext("//*[@id='libraryInput']/..//*[@class='control-label']"),
	avengereditvideosettingspage_enablecomments("//*[contains(@aria-label,'Enable Comments')]"),
	avengereditvideosettingspage_enableratings("//*[contains(@aria-label,'Enable Ratings')]"),
	avengereditvideosettingspage_enableanonymousviewing("//*[@ng-model='video.anonymousViewingEnabled']"),
	avengereditvideosettingspage_supplementalfiles("//*[@name='supplemental-content']"),
	avengereditvideosettingspage_contenttitle("content.title"),
	avengereditvideosettingspage_okbutton("//button[contains(text(),'OK')]"),//("//*[@class='glyphicon glyphicon-ok']/.."),
	avengereditvideosettingspage_cancelbutton("(//button[contains(@ng-click,'cancel')])[1]"),
	avengereditvideosettingspage_videostatus("//*[contains(@id,'Status')]//button[contains(@class,'Active')]"),
	avengereditvideosettingspage_allSupplementalFiles("//section[contains(@ng-show,'SupplementalContent')]"),
	avengereditvideosettingspage_editVideoAccess("//button[contains(@ng-show,'forceEditAccess')]"),
	avengereditvideosettingspage_searchdonelocator("//*[@name='accessControlEntities']/..//*[contains(text(),'Done')]"),
	avengereditvideosettingspage_supplementalMedia("//*[contains(@heading,'Advanced')]/a"),
	avengereditvideosettingspage_captionlanguage("languageCode"),
	avengereditvideosettingspage_captionfileuploadbutton("//input[@name='transcription-file']/.."),
	avengereditvideosettingspage_generatesrttab("//*[@ng-show='enableVoiceBase']"),
	avngereditvideosettingspage_sendtranscriptionbutton("//*[@class='glyphicons microphone']/.."),
	avngereditvideosettingspage_transcriptionerrormsg("//li[@class='transcription-file-item']/div[3]/.."),
	avngereditvideosettingspage_expirationdatetextboxLocator("expirationDate"),
	avngereditvideosettingspage_expirationdatepickerLocator("//*[contains(@name,'expirationDate')]/..//button"),
	avngereditvideosettingspage_expirationselectdatelocator("//*[contains(@class,'btn btn-default btn-sm btn-info active')]"),
	avengereditvideosettingspage_publishdatefieldtextboxlocator("//button[@class='btn btn-default btn-sm btn-info active']//child::span[1]"),
	avengereditvideosettingspage_deletewhenvideoexpireslocator("//*[contains(@ng-model,'deleteOnExpiration')]"),
	avengereditvideosettingspage_deletewhenvideoexpirestextlocator("//*[contains(@ng-model,'deleteOnExpiration')]/.."),
	avengereditvideosettingspage_expirationdatebutton("//*[contains(@uib-btn-radio,'expirationOptions.DATE')]"),
	avengereditvideosettingspage_teamradiobutton("//*[contains(@uib-btn-radio,'Teams')]"),
	avengereditvideosettingspage_addteamstextboxlocator("//input[contains(@placeholder,'Add Channels')]"),
	avengereditvideosettingspage_settingslink("//*[contains(@href,'video')]/span[@class='glyphicons cogwheel']"),
	avengereditvideosettingspage_publishdatecalendar("//*[@class='glyphicons calendar']");
	
	private String property;
	private AvengerEditVideoSettingsPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
