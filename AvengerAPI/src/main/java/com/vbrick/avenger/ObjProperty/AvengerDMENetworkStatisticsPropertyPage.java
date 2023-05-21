package com.vbrick.avenger.ObjProperty;

   public enum AvengerDMENetworkStatisticsPropertyPage {

	dmenetworkstatisticspage_adddmebuttonlocator("//*[contains(@class,'btn-primary')]");
	
	
	private String property;
	private AvengerDMENetworkStatisticsPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
}
