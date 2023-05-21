package com.vbrick.avenger.ObjProperty;

public enum CreateAccountProperty {
	
	//Create Account Page
		createAccount_ActiveTextLocator("//div[@class='panel-body']/span[2]"),
		createAccount_webAddressLocator("//input[@ng-model='account_name']"),
		createAccount_AccountNameLocator("//");

		private String property;
		private CreateAccountProperty(String property) {
			this.setProperty(property);
		}

		public String getProperty() {
			return property;
		}

		public void setProperty(String property) {
			this.property = property;
		}

}
