package com.vbrick.avenger.ObjProperty;

public enum AvengerUploadPropertyPage {
	uploadpg_Uploadtab("//*[contains(text(),'My Videos')]"), 
	uploadpg_filterOff("//div[@data-toggle='buttons']"),
	uploadpg_ratingslocator("//*[@ng-show='mediaFeatures.enableRatings']/span[1]"),
	uploadpg_listviewbuttonlocator("(//*[contains(@aria-label,'List View')])[1]"),
	uploadpg_webeximportedfirstvideo("//*[contains(@class,'fixed-ratio group')]/h2/a[contains(text(),'David De Hora')]"),
	uploadpg_totalvideoslocator("//*[contains(@class,'mediaToolbarNav')]/../div[2]/div[1]"),
	uploadpg_tileviewbuttonlocator("(//*[contains(@aria-label,'Tile View')])[1]"),
	uploadpg_bulkeditlocator("//a[@title='Bulk Edit']"),
	uploadpg_selectallchkboxlocator("//button[contains(@ng-click,'SelectionModel.toggleSelectAll')]");

	private String property;

	private AvengerUploadPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
}
