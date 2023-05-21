package com.vbrick.avenger.ObjProperty;

    public enum AvengerCategoriesPropertyPage {

	avengercategoriespage_createcategorybuttonlocator("//*[contains(@name,'createCategoryForm')]//*[@type='submit']"),
	avengercategoriespage_newcategorylocator("//input[@ng-model='createForm.name']"),
	avengercategoriespage_createbuttonlocator("//*[@name='createCategoryForm']//*[@type='submit']"),
	avengercategoriespage_cancelbuttonlocator("//button[text()='Cancel']"), 
	avengercategoriespage_categorynameDuplicatelocator("//*[@name='createCategoryForm']/div/div"),
	categorydeletebuttonlocator("//*[@ng-click='removeCategory(category)']"), 
	categorynocountlocator("//*[@class='child-row']"),
	categoryCancel("Cancel"), 
	editCategoryname("//*[@name='name']"), 
	saveCategorylocator("//*[@name='categoryForm']//*[@type='submit']"), 
	editCategory_fieildrequiredlocator("//*[@class='col-sm-6 error-field']"),
	categorynameinuse_locator("//*[@ng-show='category.isCategoryNameInUse'][not(contains(@class,'ng-hide'))]"), 
	categoryinvalid_locator("//*[contains(@ng-show,'CategoryNameNotValid')]"),
	allcategorieslocator("//*[@class='table-cell-library-name']//*[contains(@uisref,'portal.admin.categories')]"),
	deletecategorypopuplocator("//*[contains(@class,'modal-footer')]//*[@type='submit' or text()='OK' or @ng-show='actionText']"),
	deletecategorypopupnolocator("//*[@class='modal-footer']/button[contains(@ng-show,'cancelText')]");
	
	
	private String property;
	private AvengerCategoriesPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
