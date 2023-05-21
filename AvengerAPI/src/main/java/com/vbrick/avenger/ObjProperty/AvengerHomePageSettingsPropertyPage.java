package com.vbrick.avenger.ObjProperty;

public enum AvengerHomePageSettingsPropertyPage {
	
	homepagesetting_firstcarouseldropdrown("(//*[contains(@name,'CarouselSettings')]//select)[1]"),
	homepagesetting_secondcarouseldropdrown("(//*[contains(@name,'CarouselSettings')]//select)[2]"),
	homepagesetting_thirdcarouseldropdrown("(//*[contains(@name,'CarouselSettings')]//select)[3]"),
	homepagesetting_fourthcarouseldropdrown("(//*[contains(@name,'CarouselSettings')]//select)[4]"),
	homepagesetting_fifthcarouseldropdrown("(//*[contains(@name,'CarouselSettings')]//select)[5]"),
	homepagesetting_savebutton("//*[contains(@class,'panel-footer')]//button"),
	homepagesetting_acceptalert("//*[@class='modal-footer']/button[2]");
	
	private String property;
	private AvengerHomePageSettingsPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
