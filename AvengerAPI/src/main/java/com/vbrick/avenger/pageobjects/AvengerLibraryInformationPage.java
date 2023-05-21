package com.vbrick.avenger.pageobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AddingUserIntoGroupsPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerLibrariesPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerLibraryInformationPropertyPage;
import com.vbrick.avenger.dao.AddUserBeanPage;
import com.vbrick.avenger.dao.AvengerGroupsBeanPage;
import com.vbrick.avenger.dao.LibraryBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerLibraryInformationPage extends WebElements {
	
	By librarynametextlocator=By.xpath(AvengerLibraryInformationPropertyPage.libraryinformationpage_librarynametextlocator.getProperty());
	By librarydescriptiontextlocator = By.xpath(AvengerLibraryInformationPropertyPage.libraryinformationpage_librarydescriptiontextlocator.getProperty());
	By librarynametextboxlocator = By.xpath(AvengerLibraryInformationPropertyPage.libraryinformationpage_librarynametextboxlocator.getProperty());
	By cancelbuttonlocator= By.xpath(AvengerLibraryInformationPropertyPage.libraryinformationpage_cancelbuttonlocator.getProperty());
	By createandaddanotherlibrarylocator= By.xpath(AvengerLibraryInformationPropertyPage.libraryinformationpage_createandaddanotherlibrarylocator.getProperty());
	By createlibrarybuttonlocator= By.xpath(AvengerLibraryInformationPropertyPage.libraryinformationpage_createlibrarybuttonlocator.getProperty());
	By librarynameerrortext=By.xpath(AvengerLibraryInformationPropertyPage.libraryinformationpage_errortextlocator.getProperty());
	By savelibrarybuttonlocator=By.xpath(AvengerLibraryInformationPropertyPage.libraryinformationpage_savelibrarybuttonlocator.getProperty());
	By blanklibrarynameerrortext=By.xpath(AvengerLibraryInformationPropertyPage.libraryinformationpage_blanklibrarynameerrortextlocator.getProperty());
	By librarycreatelibrarybuttonlocator=By.linkText(AvengerLibrariesPropertyPage.library_createlibrarybuttonlocator.getProperty());
	By userstablocator = By.xpath(AvengerLibraryInformationPropertyPage.libraryinformationpage_userstablocator.getProperty());
	By availableuserssearchboxlocator = By.name(AvengerLibraryInformationPropertyPage.libraryinformationpage_availableuserssearchlocator.getProperty());
	By availableuserlocator = By.xpath(AvengerLibraryInformationPropertyPage.libraryinformationpage_availabeluserlocator.getProperty());
	By assigneduserlocator = By.xpath(AvengerLibraryInformationPropertyPage.libraryinformationpage_assigneduserlocator.getProperty());
	By groupstablocator = By.xpath(AvengerLibraryInformationPropertyPage.libraryinformationpage_groupstablocator.getProperty());
	By availablegroupssearchboxlocator = By.xpath(AvengerLibraryInformationPropertyPage.libraryinformationpage_availablegroupssearchlocator.getProperty());
	By availablegrouplocator = By.xpath(AvengerLibraryInformationPropertyPage.libraryinformationpage_availabelgrouplocator.getProperty());
	By assignedgrouplocator = By.xpath(AvengerLibraryInformationPropertyPage.libraryinformationpage_assignedgrouplocator.getProperty());
	By usersinlibrary = By.xpath(AvengerLibraryInformationPropertyPage.libraryinformationpage_usersinlibrary.getProperty());
	By groupsinlibrary = By.xpath(AvengerLibraryInformationPropertyPage.libraryinformationpage_groupsinlibrary.getProperty());
	By finduserlocator= By.xpath(AvengerLibraryInformationPropertyPage.libraryinformationpage_finduserlocator.getProperty());
	By donelocator= By.xpath(AvengerLibraryInformationPropertyPage.libraryinformationpage_donelocator.getProperty());
	By librarydescriptiontextboxlocator = By.xpath(AvengerLibraryInformationPropertyPage.libraryinformationpage_librarydescriptiontextboxlocator.getProperty());
	By selectinggrouplocator=By.xpath(AvengerLibraryInformationPropertyPage.libraryinformationpage_selectgrouplocator.getProperty());
	By savebuttonlocator=By.xpath(AvengerLibraryInformationPropertyPage.libraryinformationpage_savelocator.getProperty());
	By librarycreatebuttonlocator = By.xpath(AvengerLibrariesPropertyPage.library_createbuttonlocator.getProperty());
	By TeamRoleLocator=By.xpath("//*[contains(@ng-if,'teamRoleTypes')]");
	By TeamMemberLocator=By.xpath("(//*[contains(@ng-click,'TeamMember')])[2]");
	
	private WebDriver driver;
	
	private CustomReport customReport;
	private BasePage basePage;
	private static Logger logger = Logger.getLogger(AvengerLibraryInformationPage.class);
	
	public AvengerLibraryInformationPage(WebDriver driver,  
			CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		
		this.customReport = customReport;
		this.basePage=basePage;
	}



	public void enter_libraryNameText(LibraryBeanPage librarybeanpage)
	{
		enterText(librarynametextboxlocator, librarybeanpage.getLibraryname());
        customReport.reporter("Library Name Entered As",   librarybeanpage.getLibraryname());
	}
	
	public String get_libraryNameText()
	{
		waitForElementPresent(librarynametextboxlocator);
		customReport.reporter("Value in Library Name Text Box is",getAttribute(librarynametextboxlocator, "value"));
		return getAttribute(librarynametextboxlocator, "value");
	}
	
	
	public void delete_libraryNameText(LibraryBeanPage librarybeanpage)
	{
	 clearWebElementTextusingbackspace(librarynametextboxlocator,  librarybeanpage.getLibraryname().length());
     customReport.reporter("Library Name Text is Deleted","");
	}
	
	public void enter_libraryDescriptionText(LibraryBeanPage librarybeanpage)
	{
		enterText(librarydescriptiontextboxlocator, librarybeanpage.getLibrarydescription());
        customReport.reporter("Library Description Entered As",librarybeanpage.getLibrarydescription());
	}
	
	public String get_libraryDescriptionText()
	{
		waitForElementPresent(librarydescriptiontextlocator);
		customReport.reporter("Value in Library Description Text Box is",getAttribute(librarydescriptiontextlocator, "value"));
		return getAttribute(librarydescriptiontextlocator, "value");
	}
	
	public Boolean verify_cancelButtonEnable()
	{
		if(isEnabled(cancelbuttonlocator)==true)
			customReport.reporter("Cancel button is Enabled","");
		else
			customReport.reporter("Cancel button is Disabled","");
			
		return isEnabled(cancelbuttonlocator);
			
	}
	
	public Boolean verify_createButtonEnable()
	{
		boolean flag=false;
        waitForElementPresent(createlibrarybuttonlocator);
        logger.info("OUTER HTML"+getAttribute(createlibrarybuttonlocator, "outerHTML"));
        if(getAttribute(savelibrarybuttonlocator, "outerHTML").contains("disabled")){
        	customReport.reporter("Create Library is Disabled","");
			flag=true;
        }
		else{
			customReport.reporter("Create Library  button is Enabled","");
			flag=false;
		}
			
		return flag;
		}
	
	public Boolean verify_createAndAddAnotherLibraryButtonEnable()
	{
		if(isEnabled(createandaddanotherlibrarylocator)==true)
			customReport.reporter("Create and Add Another Library button is Enabled","");
		else
			customReport.reporter("Create and Add Another Library button is Disabled","");
			
		return isEnabled(createandaddanotherlibrarylocator);
	}
	
	public String verify_libraryNameTextPresent()
	{
		waitForElementPresent(librarynametextlocator);
	    customReport.reporter("Library Name text is Present in Library Information Page","");
          return getText(librarynametextlocator);
	}
	public String verify_libraryDescriptionTextPresent()
	{
		waitForElementPresent(librarydescriptiontextlocator);
		customReport.reporter("Library Description text  is Present in Library Information Page","");
		return getText(librarydescriptiontextlocator);
	}
	public Boolean verify_libraryNameTextBoxPresent()
	{
		waitForElementPresent(librarynametextboxlocator);
		customReport.reporter("Library Name TextBox is Present in Library Information Page","");
		return true;
	}
	public Boolean verify_libraryDescriptionTextBoxPresent()
	{
		waitForElementPresent(librarydescriptiontextlocator);
		customReport.reporter("Library Name TextBox is Present in Library Information Page","");
		return true;
	}
	public String verify_cancelButtonPresent()
	{
		waitForElementPresent(cancelbuttonlocator);
		customReport.reporter("Cancel Button is Present in Library Information Page","");
		return getText(cancelbuttonlocator);
	}
	public String  verify_createAndAddAnotherLibraryButtonPresent()
	{
		waitForElementPresent(createandaddanotherlibrarylocator);
		customReport.reporter("Library Name TextBox is Present in Library Information Page","");
		return getText(createandaddanotherlibrarylocator);
		
	}
	
	public String verify_createLibraryButtonPresent()
	{
		waitForElementPresent(savebuttonlocator);
		customReport.reporter("Library Name TextBox is Present in Library Information Page","");
		return getText(savebuttonlocator);
	}
	
	public void clickCreateLibraryInformation()
	{
		waitForElementPresent(createlibrarybuttonlocator);
		submit(createlibrarybuttonlocator);
		pause(2000);
		customReport.reporter("Create Library Button is Clicked on library information page","");
	}
	
	public void click_cancelButton()
	{
		waitForElementPresent(cancelbuttonlocator);
		click(cancelbuttonlocator);
		customReport.reporter("Cancel Library Button is Clicked","");
	}
	
	public void click_createAndAddAnotherLibraryButton()
	{
		waitForElementPresent(createandaddanotherlibrarylocator);
		click(createandaddanotherlibrarylocator);
		customReport.reporter("Create and Add Another Library Button is Clicked","");
	}
	
	public AvengerLibrariesPage click_saveLibrary()
	{
		submit(savelibrarybuttonlocator);
		customReport.reporter("Save Library Button is Clicked","");
		pause(15000);
		waitForElementPresent(librarycreatelibrarybuttonlocator);
		return basePage.avengerLibrariesPage(driver,  customReport, basePage);
		 
	}
	public boolean verify_saveLibraryButtonPresent()
	{
		boolean flag=false;
        logger.info("OUTER HTML"+getAttribute(savelibrarybuttonlocator, "outerHTML"));
        if(getAttribute(savebuttonlocator, "outerHTML").contains("disabled")){
			customReport.reporter("Save Library  button is disabled","");
			flag=true;
        }
		else{
			customReport.reporter("Save Library is enabled","");
			flag=false;
		}
			
		return flag;
	}
	
	
    public String get_errorText()
    {
    	 return getText(librarynameerrortext);
    }

    public String get_blankLibraryNameerrorText()
    {
    	return getText(blanklibrarynameerrortext);
    }
    
    public boolean verifyUsersTabDisplayed()
    {
    	return isDisplayed(userstablocator);
    }
    
    public void clickUsersTab()
    {
    	click(userstablocator);
    	pause(5000);
    }
    
    
    public void addUsersToLibrary(AddUserBeanPage adduserbeanpage) throws InterruptedException
    {
    	enterTextDisplayedElement(availableuserssearchboxlocator,  adduserbeanpage.getFirstname());
    	Thread.sleep(5000);
    	click(availableuserssearchboxlocator);
    	clickUsingSwitch(finduserlocator);
    	click(donelocator);
    	customReport.reporter("User added to library is", adduserbeanpage.getFirstname());
    }
    
    public String getAssignedUsers()
    {
    	pause(5000);
    	customReport.reporter("Assigned user is ", getText(assigneduserlocator));
    	return getText(assigneduserlocator);
    	
    }
    
    public boolean verifyGroupsTabDisplayed()
    {
    	return isDisplayed(groupstablocator);
    }
    
    public void addGroupsToLibrary(AvengerGroupsBeanPage groupbeanpage)
    {
    	pause(5000);
    	waitForElementPresent(availablegroupssearchboxlocator);
    	enterText(availablegroupssearchboxlocator, groupbeanpage.getNewgroup());
    	click(availablegroupssearchboxlocator);
    	pause(5000);
    	click(selectinggrouplocator);
    	click(donelocator);
    	customReport.reporter("Group added to library is", groupbeanpage.getNewgroup());
   }
    public String getAssignedGroups()
    {
    	pause(5000);
    	customReport.reporter("Assigned group is", getText(assignedgrouplocator));
    	return getText(assignedgrouplocator);
    }
    
    public String getUsersInLibrary()
    {
    	return getText(usersinlibrary);
    }
    
    public String getGroupsInLibrary()
    {
    	return getText(groupsinlibrary);
    }
    
    public ArrayList<String> get_allavailableUsers()
	 {
    	By availableuserslocator=By.xpath(AddingUserIntoGroupsPropertyPage.adduserintogroups_availableuserslocator.getProperty());
		waitForElementPresent(availableuserslocator);
	    List<WebElement> webelements= getAllWebElementValues(availableuserslocator);
		ArrayList<String> availableusers =  new ArrayList<String>();
		for (WebElement webelement : webelements) {
		logger.info("Available Roles is"+webelement.getText());
		availableusers.add(webelement.getText());
		}
		return availableusers;
	 }
    
    public ArrayList<String> get_AllAssignedUsers()
	 {
 	    logger.info("elements.size"+elements(assigneduserlocator));
 		 List<WebElement> webelements= getAllWebElementValues(assigneduserlocator);
 		ArrayList<String> assignedusers =  new ArrayList<String>();
 		for (WebElement webelement : webelements) {
 		logger.info("Assigned  user is"+webelement.getText().replace("EDIT",""));
 		assignedusers.add(webelement.getText());
 		}
 		return assignedusers;
	 }
    public String assign_availableUserToAssignUser()
	{
    	By firstavailableuserlocator=By.xpath(AddingUserIntoGroupsPropertyPage.adduserintogroups_firstavailableuserlocator.getProperty());
		String availableuser= getText(firstavailableuserlocator);
	  click(firstavailableuserlocator);
	   customReport.reporter(availableuser, "Has Been Moved From Available Users To Assigned Users"); 
	   return availableuser;	
	}



	public Map<String, String> Verify_Labels() {
	
		By collectionsbuttonlocator=By.xpath("//div[@class='combined-toolbar responsive']/div/a");
		By availablegroupslocator=By.xpath("//*[@ng-show='!useServerSearchForAvailableItems']/div/h4");
		By addalllocator=By.xpath("//div[@ng-show='!useServerSearchForAvailableItems']/div[2]/button/span[2]");
		String addvalue=getText(addalllocator);
		String[] addlabel=addvalue.split("\\(");
    	String addall=addlabel[0];
		 Map<String,String> labelsmap=new HashMap<String, String>();
		 labelsmap.put("collections", getText(collectionsbuttonlocator));
		 labelsmap.put("findgroups", getAttribute(availablegroupssearchboxlocator,"placeholder"));
		 labelsmap.put("availablegroups", getText(availablegroupslocator));
		 labelsmap.put("addall",addall);
		return labelsmap;
	}
	
	public String verify_Label(String labelname)
	{
		By labelxpath= By.xpath("//*[text()='"+labelname+"']");
		return getText(labelxpath);
	}

	public String verify_LabelWithContains(String labelname)
	{
		By labelxpath= By.xpath("//*[contains(text(),'"+labelname+"')]");
		return getText(labelxpath);
	}
	
	public void click_TeamRole(AddUserBeanPage adduserbeanpage){
		logger.info("Frist name is   "+adduserbeanpage.getFirstname());
		By TeamRoleLocator= By.xpath("(//*[contains(text(),'"+adduserbeanpage.getFirstname()+"')])/../../div[3]/button");
		clickusingjavascript(TeamRoleLocator);
	}
	public void click_TeamMember(AddUserBeanPage adduserbeanpage,int Value){
		logger.info("Frist name is   "+adduserbeanpage.getFirstname());
		By TeamMemberLocator=By.xpath("//*[contains(@class,'dropdown open show')]/div/ul/li["+Value+"]");
		click(TeamMemberLocator);
	}


}
