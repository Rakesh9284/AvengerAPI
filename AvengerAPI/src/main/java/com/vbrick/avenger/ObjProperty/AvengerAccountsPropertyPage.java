package com.vbrick.avenger.ObjProperty;

    public enum AvengerAccountsPropertyPage {

	avengeraccountspage_addaccountlocator("//*[contains(@ui-sref,'account-add')]"),
	avengeraccountspage_allaccountsbreadcrumb("//*[contains(@class,'active')]//span[@ng-show='account.children.length']/.."),
	avengeraccountspage_editsettingbutton("//*[contains(@class,'active')]//*[@ui-sref='portal.admin.account-edit']"),
	avengeraccountspage_parentaccountlocator("//*[@class='list-group']//a[contains(@ui-sref,'account.parentAccountId')]"),
	avengeraccountspage_billingtab("//*[@heading='Billing']/a"),
	avengeraccountspage_acceptdelete("//*[@class='modal-footer']//button[contains(@ng-click,'close')]"),
	avengeraccountspage_deleteaccountconformationtext("//*[contains(@class,'modal-body')]"),
	avengeraccountspage_deleteaccounterrormessage("//h4//*[contains(@class,'circle_exclamation_mark')]/.."),
	avengeraccountspage_deletesubaccounterrormessage("//*[contains(@class,'conflicted-bg')]/p"),
	avengeraccountspage_Okbuttonforsubaccounterrormsg("//*[contains(@class,'modal-footer')]/button");
	
	private String property;
	private AvengerAccountsPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
}
