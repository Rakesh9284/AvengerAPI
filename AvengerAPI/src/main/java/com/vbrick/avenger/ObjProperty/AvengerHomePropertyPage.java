package com.vbrick.avenger.ObjProperty;

public enum AvengerHomePropertyPage {
	
	//Homepage
		homepg_addVideoLocator("//div[@class='table-row']/div[12]/button"),
		homepg_adminlink("Admin"),
		homepg_loginlink("Login"),
		homepg_adduserlink("Add User"),
		homepg_userslink("//*[contains(@class,'dropdown-toggle') and contains(text(),'Users')]"),
		homepg_userdropdwn("//div[@class='list-group']/a[contains(text(),'Users')]"),
		homepg_usersaccountlicensed("//span[contains(text(),'Account Licensed for:')]"),
		homepg_licensed("//span[contains(text(),'Licensed:')]"),
		homepg_unlicensed("//span[contains(text(),'Unlicensed:')]"),
		homepg_total("//span[contains(text(),'Total')]"),
		homepg_accountlicensed("//span[contains(text(),'Account Licensed')]"),
		homepg_unusedlicenses("//span[contains(text(),'Unused Licenses')]"),
		homepg_groupslink("//*[contains(@uisref,'groups')]"),
		delete_Groups("//span[contains(text(),'Delete')]"),//("//*[contains(text(),'Delete')]"),
		delete_Channel("//button[contains(text(),'Delete')]"),
		delete_Categories("//button[contains(text(),'Delete')]"),
		delete_PresentationProfiles("//button[contains(text(),'Delete')]"),
		delete_Devices("(//span[contains(@class,'more-icon')])"),
		clickdelete_Devices("//*[contains(text(),'Delete')]"),
		confirm_delete_Groups("(//*[contains(@class,'btn btn-primary')])[3]"),
		confirm_delete_Category("//*[contains(@class,'modal-footer')]//button[contains(text(),'Delete')]"),
		confirm_delete_Presentationprofile("//*[contains(@class,'modal-footer')]//button[contains(text(),'OK')]"),
		confirm_delete_Device("//*[contains(@class,'modal-footer')]//button[contains(text(),' Delete ')]"),
		confirm_delete_Channel("(//*[contains(@type,'submit')])[2]"),
		homepg_channelslink("//*[contains(text(),' Channels ')]"),
		homepg_settingslink("System Settings"),
		homepg_mediasettingslink("//*[contains(text(),'Media Settings ')]"),
		homepg_deviceslinklocator("//div/a[text()='Devices']"),
		homepg_logoutlocator("//*[contains(@class,'profileIcon')]"),
		homepg_uploadlocator("//*[@class='glyphicons plus']"),
		homepg_addFile("//*[@name='upload']"),
		homepg_settings("//span[@class='glyphicons cogwheel']"),
		homepg_uploadprogress("//*[@role='progressbar']/span"),
		homepg_upload("upload"),
		homepg_allaccountsbreadcrumb("//*[@ng-show='account.parentAccountId']/.."),
		homepg_fileuploaderrorlocator("//*[@ng-show='video.status.invalidFile']"),
		homepg_notificationbuttonlocator("//*[@id='notifications-button']"),
		homepg_markallasreadlocator("//*[contains(text(),'Mark all as read')]"),
		homepg_dashboardlink("//*[@class='navbar-brand']"),
		homepg_roleslink("(//a[contains(@href,'roles')])[1]"),
		homepg_alldeviceslink("//div/a[contains(@uisref,'devices.devices')]"),
		homepg_addVideoByUrllink("//tabset/ul/li[2]/a/span[contains(text(),'Add URLs')]"),
		homepg_addUrl("//*[@name='url']"),
		homepg_addEncodingType("//*[@name='encodingType']"),
		homepg_addUrlbutton("//*[@name='videoLinkForm.ctrl']//*[@type='submit']"),
		homepg_webAddresslabel("//*[@ng-model='videoLink.url']/../label"),
		homepg_videoTypelabel("//form[@name='videoLinkForm.ctrl']/div/div[2]/div[1]/label"),
		homepg_encodingTypelabel("//form[@name='videoLinkForm.ctrl']/div/div[2]/div[2]/label"),
		homepg_homebuttonlocator("//header/nav/div/ol/li[3]/a[1]"),
		homepg_corasalloctor("//div[@id='feature-carousel']"),
		homepg_videotitlelocatoroncarosel("//div[@ng-hide='is_playing']/h2"),
		homepg_recentvideotitlelocator("//h4/a[contains(@ui-sref,'item.id')]/../a"),
		homepg_recentvideodatetimelocator("//h4/a[contains(@ui-sref,'item.id')]/../../h5"),
		videocommentpg_playbuttonlocator("//*[@ng-click='onClickPlayPause()']"),
		homepg_accountstablocator("//div/a[@ui-sref='portal.admin.account']"),
		homepg_Presentationprofiletablocator("//div/a[contains(@uisref,'portal.admin.devices.presentation-profiles')]"),
		homepg_zoneslocator("//div/a[contains(@ui-sref,'portal.admin.devices.zones')]"),
		homepg_recordinglocator("//div/a[contains(@ui-sref,'media-settings.recording')]"),
		homepg_reportslinklocator("//a[@ui-sref='portal.admin.reports']"),
		homepg_dismissallvideolinks("//*[@ng-click='dismissAllVideoLinks()']"),
		homepg_importfileslocator("//*[contains(@heading,'Tools')]/a"),
		homepg_dmenetworkstatisticslocator("//div/a[contains(@ui-sref,'portal.admin.devices.dme')]"),
		controlpanellocator("//*[contains(@vb-authorization-key,'controlPanel')]/a"),
		controlpaneldropdownlocator("(//*[contains(@href,'system-control-panel/runtimes')])[1]"),
		homepg_carosel("//vb-dashboard-carousel//h3/a[@class='ng-binding']"),
		homepg_unreadnotificationslocator("//*[contains(@class,'scrollable-area-wrapper padding-top-15 has-items')]//*[contains(text(),'Your Video Is Ready To Be Viewed')]"),
		homepg_allnotificationslocator("//*[contains(@class,'media-heading')]"),
		homepg_seeallnotificationbuttonlocator("//*[contains(text(),' See all notifications ')]"),
		homepg_videosincategory("//*[contains(text(),'Category:')]//../../../../../../div//h4/a"),
		homepg_useragreementagreebuttonlocator("//*[contains(@ng-click,'accept()')]"),
	    homepg_useragreementdeclinebuttonlocator("//*[contains(@ng-click,'decline()')]"),
	    homepg_helplinklocator("//*[contains(@aria-label,'Help')]"),
	  	homepg_dmemanagementlocator("//*[contains(@href,'dme-management')]");
	  
	  
		private String property;
		private AvengerHomePropertyPage(String property) {
			this.setProperty(property);
		}

		public String getProperty() {
			return property;
		}

		public void setProperty(String property) {
			this.property = property;
		}

}
