package com.vbrick.avenger.ObjProperty;


    public enum AvengerApiKeysPropertyPage {
	avengerapikeyspage_addkeylinkbuttonlocator("//button[contains(@vb-authorization-key,'apiKey.add')]"),
	avengerapikeyspage_newapikeynamelocator("//input[@ng-model='newApiKey.name']"),
	avengerapikeyspage_newapikeylocator("//input[@ng-model='newApiKey.key']"),
	avengerapikeyspage_createbuttonlocator("//*[@name='form']//*[@type='submit']"),
	avengerapikeyspage_cancelbuttonlocator("//*[@name='form']//*[@type='submit']/../button[1]");
	
	private String property;
	private AvengerApiKeysPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
