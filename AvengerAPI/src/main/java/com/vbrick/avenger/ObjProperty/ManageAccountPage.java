package com.vbrick.avenger.ObjProperty;

public enum ManageAccountPage {
	//Manage Account Page
		mngAccount_addAccountLocator("//div[@class='table-row']/div[7]/button"),
		mangAccount_linkMoreLocator("More");
		
		private String property;
		private ManageAccountPage(String property) {
			this.setProperty(property);
		}

		public String getProperty() {
			return property;
		}

		public void setProperty(String property) {
			this.property = property;
		}

}
