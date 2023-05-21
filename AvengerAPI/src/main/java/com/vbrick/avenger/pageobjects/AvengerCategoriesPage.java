package com.vbrick.avenger.pageobjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerCategoriesPropertyPage;
import com.vbrick.avenger.dao.AvengerCategoryBeanPage;
import com.vbrick.avenger.dao.LibraryBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerCategoriesPage  extends WebElements{

	private WebDriver driver;
	
	private static Logger logger = Logger.getLogger(AvengerHomePage.class);
	private CustomReport customReport;
	private BasePage basePage;
	By createcategorybuttonlocator = By.xpath(AvengerCategoriesPropertyPage.avengercategoriespage_createcategorybuttonlocator.getProperty());
	By newcategorylocator = By.xpath(AvengerCategoriesPropertyPage.avengercategoriespage_newcategorylocator.getProperty());
	By createbuttonlocator = By.xpath(AvengerCategoriesPropertyPage.avengercategoriespage_createbuttonlocator.getProperty());
	By cancelbuttonlocator = By.xpath(AvengerCategoriesPropertyPage.avengercategoriespage_cancelbuttonlocator.getProperty());
	By categorynameDuplicatelocator=By.xpath(AvengerCategoriesPropertyPage.avengercategoriespage_categorynameDuplicatelocator.getProperty());
	By categorydeletebuttonlocator =By.xpath(AvengerCategoriesPropertyPage.categorydeletebuttonlocator.getProperty());
	By categorynocountlocator =By.xpath(AvengerCategoriesPropertyPage.categorynocountlocator.getProperty());
	By cancelLocatorlocator= By.partialLinkText(AvengerCategoriesPropertyPage.categoryCancel.getProperty());
	By editCategorynamelocator= By.xpath(AvengerCategoriesPropertyPage.editCategoryname.getProperty());	
	By saveCategorylocator= By.xpath(AvengerCategoriesPropertyPage.saveCategorylocator.getProperty());
	By editCategory_fieildrequiredlocator= By.xpath(AvengerCategoriesPropertyPage.editCategory_fieildrequiredlocator.getProperty());
	By categorynameinuse_locator= By.xpath(AvengerCategoriesPropertyPage.categorynameinuse_locator.getProperty());
	By categoryinvalid_locator= By.xpath(AvengerCategoriesPropertyPage.categoryinvalid_locator.getProperty());
	By allcategorieslocator=By.xpath(AvengerCategoriesPropertyPage.allcategorieslocator.getProperty());
	By deletecategorypopuplocator=By.xpath(AvengerCategoriesPropertyPage.deletecategorypopuplocator.getProperty());

	public AvengerCategoriesPage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		
		this.customReport= customReport;
		this.basePage = basePage;
	}

	public String verifyCreateCategoryButton() {
		String flag;
		logger.info("In Verify Create category BUtton");
		if(elements(createbuttonlocator)>0){
			flag="enabled";
			customReport.reporter("Created button Found","");
		}
		else{
			flag="disabled";
		}
		return flag;
	}
 
	public Boolean verify_IsElementdoesnot_exists(LibraryBeanPage libraryBeanPage)
	{
		
		By librarynamelocator= By.partialLinkText(libraryBeanPage.getLibraryname());
		return isDisplayedWithoutException(librarynamelocator);
	}
	
	public String verifyCancelButton() {
		return waitForElementPresentVisible(cancelLocatorlocator);
	}
	
	public void clickCreatButton() {
	}

	public Boolean verifynewCategorytextbox() {
		customReport.reporter("Only Category Name is Input box","");
		return waitForElementPresent(newcategorylocator);
	}

	public void cancel_Category(AvengerCategoryBeanPage beanpage)
	{
		click(createcategorybuttonlocator);
		enterText(newcategorylocator, beanpage.getNewcategory());
		click(cancelLocatorlocator);
	}

	public void create_Category(AvengerCategoryBeanPage beanpage)
	{
		enterText(newcategorylocator, beanpage.getNewcategory());
		submit(createbuttonlocator);
		customReport.reporter("Category Created is", beanpage.getNewcategory());
	}

	public boolean verifycreateCategory()
	{
		return locatorsVisibilityAsPerRoles(createcategorybuttonlocator);
	}



	public ArrayList<String> create_MultipleCategories(AvengerCategoryBeanPage categorybeanpage,int noofCategoriestobecreated)
	{
		ArrayList<String> multiplecategorieslist = new ArrayList<String>();
		for(int j=0;j<noofCategoriestobecreated;j++)
		{
			multiplecategorieslist.add(RandomStringUtils.randomAlphabetic(5).toLowerCase());
			categorybeanpage.setNewcategory(multiplecategorieslist.get(j));
			create_Category( categorybeanpage);
			click(createcategorybuttonlocator);
			check_NewCategoryCreation(categorybeanpage);
		}
		Collections.sort(multiplecategorieslist);
		return multiplecategorieslist;
	}

	public ArrayList<String> get_AllCategories()
	{

		ArrayList<String> librarynames= new ArrayList<String>();
		for (WebElement categoryname : getAllWebElementValues(allcategorieslocator)) 
		{
			logger.info("Category  text is"+categoryname.getText());
			librarynames.add(categoryname.getText());	
		}
		return librarynames;
	}


	public String create_CategoryInvalid()
	{
		enterText(newcategorylocator, RandomStringUtils.randomAlphabetic(104));
		submit(createbuttonlocator);
		return getText(categoryinvalid_locator);
	}

	public String verifycreate_CategoryDuplicate(AvengerCategoryBeanPage beanpage)
	{
		click(createcategorybuttonlocator);
		enterText(newcategorylocator, beanpage.getNewcategory());
		click(createbuttonlocator);
		check_NewCategoryCreation(beanpage).equals(beanpage.getNewcategory());
		enterText(newcategorylocator, beanpage.getNewcategory());
		click(createbuttonlocator);
		return getText(categorynameDuplicatelocator);
	}

	public String verifycategoryNameinUser(AvengerCategoryBeanPage beanpage,String text)
	{

		By categorynamealreadyinuse=By.xpath("//*[contains(text(),'"+text+"')]");
		enterText(newcategorylocator, beanpage.getNewcategory());
		click(createbuttonlocator);
		check_NewCategoryCreation(beanpage).equals(beanpage.getNewcategory());
		enterText(newcategorylocator, beanpage.getNewcategory());
		click(createbuttonlocator);
		return getText(categorynamealreadyinuse);
	}

	public int deleteCategory(AvengerCategoryBeanPage beanpage)
	{
		By deletecategorylocator=By.xpath("//*[(text()='"+beanpage.getNewcategory()+"')]/../..//*[@ng-click='removeCategory(category)']");
		click(deletecategorylocator);
		pause(5000);
		By deletepopup = By.xpath("//*[contains(@class,'modal-body')]");
		click(deletecategorypopuplocator);
		pause(10000);
		return elements(deletecategorylocator);	
	}

	public void editCategory(AvengerCategoryBeanPage beanspage)
	{
		By newcategorylink = By.xpath("//*[contains(text(),'"+ beanspage.getNewcategory() + "')]");
		click(newcategorylink); 
		waitForElementPresent(saveCategorylocator);
		customReport.reporter("Category Edited", "");
		clearWebElementTextusingbackspace(editCategorynamelocator,  beanspage.getNewcategory().length());
		beanspage.setNewcategory(beanspage.getNewcategory()+RandomStringUtils.randomAlphabetic(5));
		logger.info("-------------------"+beanspage.getNewcategory());
		waitForElementPresent(editCategorynamelocator);
		enterText(editCategorynamelocator,  beanspage.getNewcategory());
		submit(saveCategorylocator);
		customReport.reporter("Save Category Clicked", "");
		logger.info("The category is edited with name ::::"+beanspage.getNewcategory());
		waitForElementPresent(createcategorybuttonlocator);
	}

	public String verifyCategoryNameRequired(AvengerCategoryBeanPage beanspage)
	{
		By newcategorylink = By.xpath("//*[contains(text(),'"+ beanspage.getNewcategory() + "')]");
		click(newcategorylink); 
		customReport.reporter("Category Edited::", beanspage.getNewcategory());
		waitForElementPresent(saveCategorylocator);		 
		waitForElementPresent(editCategorynamelocator);
		pause(5000);
		clearWebElementTextusingbackspaceUsingAttribute(editCategorynamelocator);
		customReport.reporter("Text Cleared in Category Name Field","");
		return getText(editCategory_fieildrequiredlocator);
	}

	public boolean verifySaveCategorydisabled(AvengerCategoryBeanPage beanspage) 
	{
		By newcategorylink = By.xpath("//*[contains(text(),'"+ beanspage.getNewcategory() + "')]");
		click(newcategorylink); 
		customReport.reporter("Category Edited::", beanspage.getNewcategory());
		waitForElementPresent(saveCategorylocator);	
		waitForElementPresent(editCategorynamelocator);
		pause(5000);
		clearWebElementTextusingbackspaceUsingAttribute(editCategorynamelocator);
		customReport.reporter("Text Cleared in Category Name Field",""); 
		return getAttribute(saveCategorylocator,"outerHTML").contains("disabled=\"disabled\"");
	}

	public boolean verifyCategoryNotCreated(AvengerCategoryBeanPage beanspage) {
		By newcategorylink = By.xpath("//*[contains(text(),'"+ beanspage.getNewcategory() + "')]");
		if (elements(newcategorylink) == 0)
			return false;
		else
			return true;
	}

	public String check_NewCategoryCreation(AvengerCategoryBeanPage beanspage)
	{
		By newcategorylink=By.xpath("//*[(text()='"+beanspage.getNewcategory()+"')]");	
		if(waitForElementPresent(newcategorylink)==true)
		{
			customReport.reporter("Category Created Successfully",beanspage.getNewcategory());
		}
		else
		{
			customReport.reporter("Category Not Created Successfully","");
		}
		return getText(newcategorylink);   
	}

	public boolean deleteButtonCount()
	{
		if(elements(categorydeletebuttonlocator)== elements(categorynocountlocator))
			return true;
		else
			return false;
	}


	public String invalidCategoryName(String text)
	{
		By invalidcategorynamelocator=By.xpath("//span[contains(text(),'"+text+"')]");
		logger.info("Invalid Category Text is"+getText(invalidcategorynamelocator));
		return getText(invalidcategorynamelocator);
	}

	public Map<String, String> Verify_Labels() {

		By createcategorybuttonlocator=By.xpath("//button[contains(@ng-click, 'createCategoryForm')]");
		By newcategorylocator=By.xpath("//input[@ng-model='createForm.name']");
		Map<String,String> labelsmap=new HashMap<String, String>();
		labelsmap.put("createcategory", getText(createcategorybuttonlocator));
		labelsmap.put("newcategory", getAttribute(newcategorylocator, "placeholder"));		
		return labelsmap;
	}
	
	
	public void clickCategory(AvengerCategoryBeanPage beanspage)
	{
		By newcategorylink = By.xpath("//*[contains(text(),'"+ beanspage.getNewcategory() + "')]");
		click(newcategorylink); 
	}
	
	public void clickdeleteCategory(AvengerCategoryBeanPage beanpage)
	{
		By deletecategorylocator=By.xpath("//*[(text()='"+beanpage.getNewcategory()+"')]/../../div[5]/button");
		click(deletecategorylocator);
		
	}
	
	public void clickSaveCategory(String categoryname)
	{
		By savelocator = By.xpath("//*[@name='categoryForm']//*[@type='submit']");
		By newcategorylocator = By.xpath("//*[@name='categoryForm']//input");
		clearWebElementText(newcategorylocator);
		enterText(newcategorylocator, categoryname);
		clickInElements(savelocator);
	}
	
	 public void clickAddChildCategorylocator(AvengerCategoryBeanPage beanspage){
		 By addchildcategorylocator = By.xpath("//*[contains(text(),'"+beanspage.getNewcategory()+"')]/../..//*[@class='glyphicons plus']/..");
		 click(addchildcategorylocator);
	 }
	 
	 public void creatChildCategory(AvengerCategoryBeanPage beanspage){
		 By createchildcategorytextboxlocator = By.xpath("//*[contains(text(),'"+beanspage.getNewcategory()+"')]/../../../..//*[@name='childCreateForm']/div/input");
		 By createchildcategorysubmitbutton = By.xpath("//*[contains(text(),'"+beanspage.getNewcategory()+"')]/../../../..//*[@name='childCreateForm']/div[2]/div/button");
		 
		 enterText(createchildcategorytextboxlocator, beanspage.getSubcategory());
		 submit(createchildcategorysubmitbutton);
	 }
	
	 public String verifyCreatedChildCategory(AvengerCategoryBeanPage beanspage){
		 By childcategorycancelbuttonlocator = By.xpath("//*[contains(text(),'"+beanspage.getNewcategory()+"')]/../../../../div[3]//*[@class='btn-toolbar']/a");
		 By verifycreatedchildcategory = By.xpath("//*[contains(text(),'"+beanspage.getNewcategory()+"')]/../../../../div[4]//*[@class='table-cell-library-name']/a");
		return getText(verifycreatedchildcategory);
		 
	 }
	 
	 public String getCategoryItemCount(AvengerCategoryBeanPage beanspage){
		 By categoryitemcountlocator = By.xpath("//*[contains(text(),'"+beanspage.getNewcategory()+"')]/../..//*[contains(@class,'item-count')]/a");
		 return getText(categoryitemcountlocator);
	 }

	 public void changeorderCategories(String firstcategory,String secondcategory) {
		 	By firstcategorylocator=By.xpath("//*[contains(text(),'"+firstcategory+"')]/../../div[2]//span[contains(@class,'glyphicons show_lines')]");
			By secondcategorylocator=By.xpath("//*[contains(text(),'"+secondcategory+"')]/../../div[2]//span[contains(@class,'glyphicons show_lines')]");
			dragAndDrop(firstcategorylocator, secondcategorylocator);
			pause(5000);
			
		}
}




