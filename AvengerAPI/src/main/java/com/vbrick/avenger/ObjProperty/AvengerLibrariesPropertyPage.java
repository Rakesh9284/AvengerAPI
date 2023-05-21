package com.vbrick.avenger.ObjProperty;

public enum AvengerLibrariesPropertyPage {
	
	library_createlibrarybuttonlocator("//h4[contains(text(),'Channels')]/../div/div/a"),
	library_newlibrarylocator("//*[contains(@name,'name')]"),
	library_createbuttonlocator("//*[contains(@placeholder,'New Channel')]/../..//button"),

	library_publiclibrarylocator("//div[3]/div/div[2]/a"),
	library_customizebuttonlocator("Customize"),
	library_cancelbuttonlocator("Cancel"),
	library_saveteamlocator("(//span[@ng-show='$ctrl.team.id'])[1]"),
	library_librarynameerrortext("//*[@ng-show='rootLibraryForm.name.$error.libraryNameInUse']/label"),
	library_publicsublibrarybuttonlocator("//*[@class='child-container public-library disabled']"),
	library_alllibrarieslocator("//*[contains(@class,'table-cell-library-name padding-left-15')]"),
	library_firstlibrarylocator("(//*[contains(@ui-sref,'teams.edit')][@class='ng-binding'])[1]"),
	library_expandallbuttonlocator("//*[@ng-click='expandAll()']"),
	library_collapseallbuttonlocator("//*[@ng-click='collapseAll()']"),
	librarycannotbedeletedmessage("//*[@class='box-modal-header conflicted-bg']"),
	library_searchlibrary("//*[contains(@placeholder,'Find Channels')]");

	private String property;
	private AvengerLibrariesPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
