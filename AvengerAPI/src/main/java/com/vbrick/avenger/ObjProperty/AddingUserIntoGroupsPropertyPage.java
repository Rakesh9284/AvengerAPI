package com.vbrick.avenger.ObjProperty;


    public enum AddingUserIntoGroupsPropertyPage {
	adduserintogroups_selectinguser("//*[@ng-click='add(user)']"),
	adduserintogroups_groupnamelocator("//*[@ng-model='group.name']"),
	adduserintogroups_savegroupbuttonlocator("//*[contains(@class,'heading')]//*[@type='submit']"),
	adduserintogroups_searchinputlocator("(//input[@placeholder='Find Items'])"),//("(//input[@placeholder='Find items ...'])"),
	adduserintogroups_searchbuttonlocator("//*[text()='Available Users']/../div[2]/div[2]/button"),
	adduserintogroups_assigneduserssearchinputlocator("//div[3]/div[2]/div/div/div[1]/div[1]/div/div[3]/div/div/input"),
	adduserintogroups_assignedusersearchbuttonlocator("//*[contains(text(),'Assigned Users')]/../div[2]/div[2]/form/button"),
	adduserintogroups_assigneduser("//*[@class='admin-movable-item ng-scope ng-binding']/button[1]"),
	adduserintogroups_assignrolesearchtextboxlocator("//div[4]/div[2]/div/div/div[1]/div[1]/div/div[3]/div/div/input"),
	adduserintogroups_availablerolesearchtextboxlocator("//div[4]/div[2]/div/div/div[2]/div[1]/div[2]/div[3]/div/div/input"),
	adduserintogroups_addrolebuttonlocator("//div[@ng-click='add(role)'][1]/div/button[2]"),
	adduserintogroups_removerolebuttonlocator("//div[@ng-click='remove(role)'][1]/div"),
	adduserintogroups_availableuserslocator("//*[@ng-click='add(user)']"),
	adduserintogroups_assignuserslocator("//*[@class='flex-fill text-ellipsis ng-binding']"),
	adduserintogroups_firstavailableuserlocator("//*[contains(@ng-click,'add(user)')][1]/div"),
	adduserintogroups_firstassignuserlocator("//*[contains(@ng-click,'remove(user)')][1]/div"),
	adduserintogroups_assignroleslocator("//*[contains(@ng-click,'remove(role)')]"),
	adduserintogroups_availableroleslocator("//*[contains(@ng-click,'add(role)')]"),
	adduserintogroups_removeuserinputlocator("//*[@selected-items-heading='Assigned Users']//input[@ng-model='selectedFilterTxt[selectedFilterProperty]']"),
	adduserintogroups_groupsbuttonlocator("//div[@class='combined-toolbar responsive']/div/a"),
	adduserintogroups_finduserssearch("//input[@placeholder='Find Users']"),
	adduserintogroups_findrolessearch("//input[@placeholder='Find Roles'][contains(@ng-model,'availableFilterTxt')]"),
	adduserintogroups_finduser("(//*[@ng-hide='item.assigned']/..)[1]"),
	adduserintogroups_finduserdonelocator("//*[@ng-model='insight.query']/../button");
	
	
	private String property;
	private AddingUserIntoGroupsPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
	

}
	
