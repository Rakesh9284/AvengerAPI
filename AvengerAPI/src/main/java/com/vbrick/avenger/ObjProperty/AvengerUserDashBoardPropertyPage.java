package com.vbrick.avenger.ObjProperty;


public enum AvengerUserDashBoardPropertyPage {
	
	avengeruserdashboard_adduserlink("Add User"),
	avengeruserdashboard_activeuserlink("//span[contains(@class,'icon-success')]"),
	avengeruserdashboard_licencedusers("//*[@class='license-status']/span[1]"),
	statuselement("//*[contains(@class,'btn-icon-left')]/span[2]"),
	avengeruserdashboard_allusers("//*[@class='flex-fill primary-cell table-cell']/../div[2]/span");
	
	private String property;
	private AvengerUserDashBoardPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}


}
