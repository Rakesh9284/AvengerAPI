package com.vbrick.avenger.ObjProperty;


public enum AvengerLibraryInformationPropertyPage {
	
	
	libraryinformationpage_librarynametextlocator("//*[@class='form-group required']/h4"),
	libraryinformationpage_librarydescriptiontextlocator("//*[@ng-model='collection.description']"),
	libraryinformationpage_librarynametextboxlocator("//*[contains(@aria-label,'Name')]"),
	libraryinformationpage_librarydescriptiontextboxlocator("//*[@ng-model='collection.description']"),
	libraryinformationpage_cancelbuttonlocator("//*[@class='btn-toolbar']/a"),
	libraryinformationpage_createandaddanotherlibrarylocator("//div/button[@ng-click='createThenReset()']"),
	libraryinformationpage_createlibrarybuttonlocator("//*[contains(@class,'footer')]//*[@type='submit']"),
	libraryinformationpage_errortextlocator("//*[@validation='nameConflict']"),
	libraryinformationpage_savelibrarybuttonlocator("(//*[contains(text(),'Save Channel')])[2]"),
	libraryinformationpage_blanklibrarynameerrortextlocator("//*[@ng-show='collectionForm.name.$error.required']"),
	libraryinformationpage_userstablocator("//a[contains(@ng-click,'users')]"),
	libraryinformationpage_availableuserssearchlocator("query"),
	libraryinformationpage_availabeluserlocator("//*[@ng-click='add(user)']"),
	libraryinformationpage_assigneduserlocator("//span[@class='circle glyphicons user']/../../div[2]"),
	libraryinformationpage_groupstablocator("//a[contains(@ng-click,'groups')]"),
	libraryinformationpage_availablegroupssearchlocator("//*[@ng-model='insight.query']"),
	libraryinformationpage_availabelgrouplocator("//*[@ng-click='add(group)']"),
	libraryinformationpage_assignedgrouplocator("//*[contains(@class,'circle glyphicons')]/../../div[2]"),
	libraryinformationpage_usersinlibrary("//*[contains(@ng-show,'userIds.length')]"),
	libraryinformationpage_groupsinlibrary("//*[contains(@ng-show,'groupIds.length')]"),
	libraryinformationpage_finduserlocator("(//div[contains(@class,'pP8HT__descriptionCell')])"),
	libraryinformationpage_donelocator("//button[contains(text(),'Done')]"),
	libraryinformationpage_selectgrouplocator("(//*[@ng-hide='item.assigned'])[1]"),
	libraryinformationpage_savelocator("(//*[@type='submit'])[4]");
	private String property;
	private AvengerLibraryInformationPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}

