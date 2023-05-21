package com.vbrick.avenger.ObjProperty;

public enum EditRootAccountProperty {
	
	
	
	editrootaccount_firstname("fn"),
	editrootaccount_lastname("ln"),
	editrootaccount_email("email"),
	editrootaccount_title("//*[contains(@id,'title')]"),
	editrootaccount_phone("//*[contains(@id,'phone')]"),
	editrootaccount_language("language"),
	editrootaccount_urlconfirmationbutton("//input[@ng-model='user.email']"),
	editrootaccount_submitbuttonlocator("//*[contains(text(),'Save User')]"),
	editrootaccount_buttonlocator("//*[contains(@ng-click,'getConfirmationToken()')]"),
	editrootaccount_linklocator("//*[@ng-show='user.confirmationUrl']/label/a"),
	editrootaccount_userconfirmationurl("(//*[contains(@class,'control-label')]/a)[1]"),
	editrootaccount_resetpasswordbuttonlocator("//*[@ng-click='resetPassword()']"),
	editrootaccount_showpasswordresetbuttonlocator("//*[@ng-click='getPasswordResetToken()']"),
	editrootaccount_userpasswordreseturllocator("//*[@ng-show='user.passwordResetUrl']/label/a"),
    editrootaccount_showconfirmationbuttonlocator("(//*[contains(@class,'btn btn-primary')])[5]"),
	editrootaccount_assignrolesearchtextboxlocator("//*[contains(text(),'Assigned Roles')]/../div[2]/div[2]/form/input"),
	editrootaccount_availablerolesearchtextboxlocator("//*[@ng-click='add(role)']/../../div[2]/div/div[2]/form/div/input"),
	editrootaccount_addrolebuttonlocator("//div[@ng-click='add(role)'][1]/div"),
	editrootaccount_removerolebuttonlocator("//div[@ng-click='remove(role)'][1]/div"),
	editrootaccount_availableroleslocator("//*[contains(@class,'padding-top-5 virtual-scroll-child')]"),
	editrootaccount_assignedroleslocator("(//*[contains(@class,'padding-top-5 virtual-scroll-child')])[1]"),
	editrootaccount_availablegroupslocator("//*[contains(@ng-click,'add(group)')]"),
	editrootaccount_assigngroupslocator("//*[contains(@class,'pP8HT__descriptionCell')]"),
	editrootaccount_availablegroupsearchtextboxlocator("//*[contains(text(),'Available Groups')]/../div[2]/div[2]/input"),
	editrootaccount_addgroupbuttonlocator("//div[@ng-click='add(group)'][1]/div"),
	editrootaccount_assigngroupssearchtextboxlocator("//*[@ng-model='insight.query']");
	
	
	private String property;
	private EditRootAccountProperty(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
	

}
