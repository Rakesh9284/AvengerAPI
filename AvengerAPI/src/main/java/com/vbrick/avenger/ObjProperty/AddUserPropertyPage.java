package com.vbrick.avenger.ObjProperty;


    public enum AddUserPropertyPage {
	
	adduser_firstname("//*[contains(@name,'fn')]"),
	adduser_lastname("//*[contains(@name,'ln')]"),
	adduser_contactemail("//*[contains(@name,'email')]"),
	adduser_title("//*[contains(@id,'title')]"),
	adduser_phoneno("//*[contains(@id,'phone')]"),
	adduser_selectlang("language"),
	adduser_username("//input[@name='username']"),
	adduser_createbuttonlocator("//*[contains(@class,'footer')]//*[@type='submit']"),
	adduser_usernameerrortext("//label[@ng-show='userForm.username.$error.required']"),
	adduser_contactemailerrortext("//label[@ng-show='userForm.email.$error.required']"),
	adduser_lastnameerrortext("//label[@ng-show='userForm.lastName.$error.required']"),
	adduser_availableroleslocator("//div[@class='hover-wrap']"),
	adduser_assignroleslocator("//*[contains(@class,'btn btn-sm btn-icon-left')]"),
	adduser_availablegroupslocator("//*[@ng-click='add(group)']"),
	adduser_assigngroupslocator("//*[@ng-click='remove(group)']"),
	adduser_availablegroupssearchtextboxlocator("//*[@ng-model='insight.query']"),
	adduser_addrolessearchboxlocator("(//*[contains(@placeholder,'Find Roles')])[2]"),
	adduser_usernameinuseerrortextlocator("//label[@validation='usernameInUse']"),
	adduser_usersearchboxlocator("//*[contains(@name,'searchQuery')]"),
	adduser_addselectedgroup("(//*[@ng-hide='item.assigned'])[1]"),
	adduser_donebuttonlocator("//*[@ng-show='showOptions']"),
	adduser_allcategorieslocator("//*[contains(@class,'tagify__dropdown B7BB8__dropdown')]//span"),
	adduser_allassignedgroupslocator("//div[@infinite-scroll='$ctrl.loadAssignedPage()']//*[contains(@class,'ng-binding')]"),
	adduser_saveuserlocator("//button[@class='btn btn-primary']");
	
	private String property;
	private AddUserPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}

