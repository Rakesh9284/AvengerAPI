package com.vbrick.avenger.ObjProperty;

public enum AvengerWebexPropertyPage {
	
	avengerwebexpage_selectvideocheckbox("(//vb-grid-list-row/vb-ui-checkbox)[1]"),
	avengerwebexpage_nameofthevideo("(//*[contains(@ng-show,'recording')]/..)[1]/../..//*[contains(@class,'grid')][2]"),
	avengerwebexpage_Videoimportedstatus("(//*[contains(@ng-show,'recording')]/..)[1]/../..//*[contains(@class,'grid')][3]/span"),
	avengerwebexpage_Videoimportedstatuswithoutok("(//vb-grid-list-column/..//*[contains(@class,'circle_ok')])[1]"),
	avengerwebexpage_Videocreateddate("(//*[contains(@ng-show,'recording')]/..)[1]/../..//*[contains(@class,'grid')][4]"),
	avengerwebexpage_Videodurationtimelocator("(//*[contains(@ng-show,'recording')]/..)[1]/../..//*[contains(@class,'grid')][5]"),
	avengerwebexpage_Videodufilesizelocator("(//*[contains(@ng-show,'recording')]/..)[1]/../..//*[contains(@class,'grid')][6]"),
	avengerwebexpage_selectvideocheckboxforsecondvideo("(//*[contains(@ng-show,'item.selected')]/..)[2]"),
	avengerwebexpage_selectvideocheckboxforthirdvideo("(//*[contains(@ng-show,'item.selected')]/..)[3]"),
	avengerwebexpage_selectvideocheckboxforforthvideo("(//*[contains(@ng-show,'item.selected')]/..)[4]"),
	avengerwebexpage_webeximportbuttonlocator("//*[@name='webexImportForm']//*[@type='submit']"),
	avengerwebexpage_webeximportedvideosverification("//*[@class='glyphicons circle_ok']"),
	avengerwebexpage_webexseealluploadsbutton("//*[contains(@ng-click,'dismissAllImports()')]/..//*[contains(@href,'media/uploads')]"),
	avengerwebexpage_webexcancelbutton("//*[contains(@name,'webexImportForm')]//*[contains(text(),'Cancel')]");
	
	private String property;
	
	private AvengerWebexPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
