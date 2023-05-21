package com.vbrick.avenger.ObjProperty;

public enum AvengerMediaPropertyPage {

	avengermediapage_videostablocator("//*[contains(@ui-sref,'portal.media.uploads')]/span"),
	avengermediapage_videossearchlocator("//div/ol/li[4]/form/input"),
	avengermediapage_videossearchbuttonlocator("//div/ol/li[4]/form/span/button"),
	avengermediapage_noofvideoslocator("//*[@ng-show='video.category']"),
	avengermediapage_categoriestablocator("//*[contains(@ui-sref,'portal.media.browse')]"),	
	avengermediapage_mediasearchtextboxlocator("//*[contains(@name,'editValue')]"),
	avengermediapage_mediasearchbuttonlocator("(//*[contains(@aria-label,'Search')])[2]"),
	avengermediapage_playlisttablocator("(//*[contains(text(),'My Playlists')])"),
	avengermediapage_currentsortlocator("(//*[@ng-model='sortField'])[1]"),
	avengermediapage_titlelocator("(//*[contains(@value,'title')])[1]"),
	avengermediapage_uploaddatelocator("(//*[contains(@value,'whenUploaded')])[1]"),
	avengermediapage_uploadbylocator("(//*[contains(@value,'uploaderName')])[1]"),
	avengermediapage_allvideoslocator("//h2/a"),
	avengermediapage_allvideosdatelocator("//span/a/../../span[2]"),
	avengermediapage_allvideosnamelocator("//span/a"),
	avengermediapage_uploadvideotablocator("//button[contains(@ui-sref,'portal.media.playlists')]/../button[4]"),
	avengermediapage_tileViewButtonLocator("//*[@class='glyphicons show_big_thumbnails']"),
	avengermediapage_tileViewsLocator("//*[@class='tile-item']"),
	avengermediapage_listViewButtonLocator("//*[@class='glyphicons show_lines']"),
	avengerMediaPage_firstVideoInRecentlyAddedVideosLocator("(//vb-carousel-strip-item[1]/div/div/div[2])[1]/h4/a"),
	avengerMediaPage_titleNameText("//a[@class='file-name ng-binding']"), 
	avengermediapage_pendingApprovalTabLink("//*[contains(@uisref,'portal.media.pending-videos')]"),
	avengermediapage_tileviewstarslocator("//i"),
	avengermediapage_webexbuttonlocator("//*[contains(@ng-click,'importWebEx')]"),
	avengermediapage_webexloginusernametextbox("//*[@id='webExUsername']"),
	avengermediapage_webexloginpasswordtextbox("//*[@ng-model='webExPassword']"),
	avengermediapage_webexloginsubmitbutton("//*[@id='webExPassword']/../../../..//*[@type='submit']"),
	avengermediapage_revcreatebutton("span[class*='rev-create']"),
	avengermediapage_revcreatetext("//*[contains(@class,'button-rev-create')]/.."),
	avengermediapage_allvideostablocator("(//my-videos-menu-items/ul/li)[1]/a"),
	avengermediapage_browsevideostablocator("//*[contains(@ui-sref,'media.browse')]"),
	avengermediapage_videooptionsavaiable("//*[@id='simple-btn-keyboard-nav']"),
	avengermediapage_browsevideoslocator("(//my-videos-menu-items/ul/li)[2]/a");
	
	private String property;
	private AvengerMediaPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
}

