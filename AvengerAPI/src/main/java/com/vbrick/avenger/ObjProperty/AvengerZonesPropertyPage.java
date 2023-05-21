package com.vbrick.avenger.ObjProperty;

public enum AvengerZonesPropertyPage {

	avengerzonespage_addzonebuttonlocator("//a[contains(@ui-sref,'add-zone')]"),
	avengerzonespage_expandallbuttonlocator("//*[@ng-click='expandAll()']"),
	avengerzonespage_collapseallbuttonlocator("//*[@ng-click='collapseAll()']"),
	avengerzonespage_zonesearch("//*[@ng-model='filterTxt']");
	
	private String property;
	private AvengerZonesPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
}
