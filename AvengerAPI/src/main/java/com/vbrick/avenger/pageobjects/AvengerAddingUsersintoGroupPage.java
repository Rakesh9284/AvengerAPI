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
import com.vbrick.avenger.ObjProperty.AvengerGroupsPropertyPage;
import com.vbrick.avenger.dao.AddUserBeanPage;
import com.vbrick.avenger.dao.AvengerGroupsBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;
public class AvengerAddingUsersintoGroupPage extends WebElements{

private static Logger logger = Logger.getLogger(AvengerAddingUsersintoGroupPage.class);

    By selectinguser=By.xpath(AddingUserIntoGroupsPropertyPage.adduserintogroups_selectinguser.getProperty());	
    By groupnamelocator=By.xpath(AddingUserIntoGroupsPropertyPage.adduserintogroups_groupnamelocator.getProperty());
    By savegroupbuttonlocator=By.xpath(AddingUserIntoGroupsPropertyPage.adduserintogroups_savegroupbuttonlocator.getProperty());
    By searchinputlocator=By.xpath(AddingUserIntoGroupsPropertyPage.adduserintogroups_searchinputlocator.getProperty());
    By searchbuttonlocator=By.xpath(AddingUserIntoGroupsPropertyPage.adduserintogroups_searchbuttonlocator.getProperty());
    By assigneduserssearchinputlocator=By.xpath(AddingUserIntoGroupsPropertyPage.adduserintogroups_assigneduserssearchinputlocator.getProperty());
    By assignedusersearchbuttonlocator=By.xpath(AddingUserIntoGroupsPropertyPage.adduserintogroups_assignedusersearchbuttonlocator.getProperty());
    By assigneduser=By.xpath(AddingUserIntoGroupsPropertyPage.adduserintogroups_assigneduser.getProperty());
    By availablerolesearchtextboxlocator=By.xpath(AddingUserIntoGroupsPropertyPage.adduserintogroups_availablerolesearchtextboxlocator.getProperty());
    By assignrolesearchtextboxlocator=By.xpath(AddingUserIntoGroupsPropertyPage.adduserintogroups_assignrolesearchtextboxlocator.getProperty());
    By addrolebuttonlocator=By.xpath(AddingUserIntoGroupsPropertyPage.adduserintogroups_addrolebuttonlocator.getProperty());
    By removerolebuttonlocator=By.xpath(AddingUserIntoGroupsPropertyPage.adduserintogroups_removerolebuttonlocator.getProperty());
    By availableuserslocator=By.xpath(AddingUserIntoGroupsPropertyPage.adduserintogroups_availableuserslocator.getProperty());
    By assignuserslocator=By.xpath(AddingUserIntoGroupsPropertyPage.adduserintogroups_assignuserslocator.getProperty());
    By firstavailableuserlocator=By.xpath(AddingUserIntoGroupsPropertyPage.adduserintogroups_firstavailableuserlocator.getProperty());
    By firstassignuserlocator=By.xpath(AddingUserIntoGroupsPropertyPage.adduserintogroups_firstassignuserlocator.getProperty());
    By assignroleslocator=By.xpath(AddingUserIntoGroupsPropertyPage.adduserintogroups_assignroleslocator.getProperty());
    By availableroleslocator=By.xpath(AddingUserIntoGroupsPropertyPage.adduserintogroups_availableroleslocator.getProperty());
    By creategroupinputlocator=By.xpath(AvengerGroupsPropertyPage.avengergrouppage_newgroupname.getProperty());	
    By removeuserinputlocator=By.xpath(AddingUserIntoGroupsPropertyPage.adduserintogroups_removeuserinputlocator.getProperty());
	By groupsbuttonlocator=By.xpath(AddingUserIntoGroupsPropertyPage.adduserintogroups_groupsbuttonlocator.getProperty());
	By finduserssearch=By.xpath(AddingUserIntoGroupsPropertyPage.adduserintogroups_finduserssearch.getProperty());
	By findrolessearch=By.xpath(AddingUserIntoGroupsPropertyPage.adduserintogroups_findrolessearch.getProperty());
	By finduser=By.xpath(AddingUserIntoGroupsPropertyPage.adduserintogroups_finduser.getProperty());
    By finduserdonelocator= By.xpath(AddingUserIntoGroupsPropertyPage.adduserintogroups_finduserdonelocator.getProperty());
	By userassignmenttextboxlocator=By.xpath("//*[@ng-model='insight.query']");	
	By assignuserlocator=By.xpath("//*[@ng-show='item.assigned']/..//*[@class='glyphicons plus']");
	By donelocator=By.xpath("//button[@ng-click='closeOptions()']");
	     
    private WebDriver driver;
	private CustomReport customReport;
	private BasePage basePage;
	
	public AvengerAddingUsersintoGroupPage(WebDriver driver,CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver=driver;

		this.customReport = customReport;
		this.basePage=basePage;
	}
	
	public void searchUser(AddUserBeanPage beanpage)
	{
		enterText(searchinputlocator, beanpage.getFirstname());
		click(searchinputlocator);
		waitForElementPresent(finduser);
		logger.info("The user is displayed in the dropdown");
	}
	
	public String enter_groupname(AvengerGroupsBeanPage beanspage)
	{
		enterText(groupnamelocator, beanspage.getRenamegroup());
	    return getAttribute(groupnamelocator,"value");
	}
	
	public AvengerGroupsPage click_savegroupbutton()
	{
		submit(savegroupbuttonlocator);
		customReport.reporter("Save Group Button is Clicked","");
		waitForElementPresent(creategroupinputlocator);
		return basePage.avengerGroupsPage(driver,  customReport, basePage);
	}
	
	/**
	 *  it is used to assign the role of a group from available users to assign users
	 * @param beanspage
	 */
	public void assign_roleToGroup(AvengerGroupsBeanPage beanspage)
	{
		 waitForElementPresent(availableroleslocator);
		logger.info("I am in assign role To Group Function");
		enterText(findrolessearch,  beanspage.getGrouprole());
		click(availableroleslocator);
		pause(5000);
		click_savegroupbutton();
		 waitForElementPresent(creategroupinputlocator);
	}
	
	
	public void available_roleToGroup(AvengerGroupsBeanPage beanspage)
	{
		 waitForElementPresent(assignroleslocator);
		 pause(5000);
	    List<WebElement> assignroles= getAllWebElementValues(assignroleslocator);
		for (WebElement role : assignroles) {
		if(role.getText().equals(beanspage.getGrouprole()))
		{
			logger.info("role value is"+beanspage.getGrouprole());
			logger.info("Web element Text is"+role.getText());
			customReport.reporter(beanspage.getGrouprole(), "Has Been Moved From Assign role to Available roles");
			role.click();
		    pause(5000);
		  
		}
		}
		click_savegroupbutton();
       waitForElementPresent(creategroupinputlocator);
		
	}
	
	
	
	public String  verify_roleAssignedGroup(AvengerGroupsBeanPage beanspage)
	{
		enterText(assignrolesearchtextboxlocator,  beanspage.getGrouprole());
	    pause(5000);
		waitForElementPresent(removerolebuttonlocator);
		customReport.reporter("Assigned Role to group is",getText(removerolebuttonlocator));
	  return getText(removerolebuttonlocator);
	
	}
	
	 public ArrayList<String> get_allassignedUsers()
	 {
	    logger.info("elements.size"+elements(assignuserslocator));
		 List<WebElement> webelements= getAllWebElementValues(assignuserslocator);
		ArrayList<String> assignedroles =  new ArrayList<String>();
		for (WebElement webelement : webelements) {
		logger.info("Assigned  user is"+webelement.getText());
		assignedroles.add(webelement.getText());
		}
		return assignedroles;
	 }
	 
	 public ArrayList<String> get_allavailableUsers()
	 {
		pause(3000);
		 waitForElementPresent(availableuserslocator);
	    List<WebElement> webelements= getAllWebElementValues(availableuserslocator);
		ArrayList<String> availableusers =  new ArrayList<String>();
		for (WebElement webelement : webelements) {
		logger.info("Available Roles is"+webelement.getText());
		availableusers.add(webelement.getText());
		}
		return availableusers;
	 }
	 
	 public ArrayList<String> get_allassignedRoles()
	 {
		 waitForElementPresent(assignroleslocator);
	   pause(5000);
		 logger.info("elements.size"+elements(assignroleslocator));
		 List<WebElement> webelements= getAllWebElementValues(assignroleslocator);
		ArrayList<String> assignedroles =  new ArrayList<String>();
		for (WebElement webelement : webelements) {
		logger.info("Assigned  Roles is"+webelement.getText());
		assignedroles.add(webelement.getText());
		}
		pause(5000);
		return assignedroles;
	 }
	 
	
	 public ArrayList<String> get_allavailableRoles()
	 {
		 waitForElementPresent(availableroleslocator);
	    List<WebElement> webelements= getAllWebElementValues(availableroleslocator);
		ArrayList<String> availableroles =  new ArrayList<String>();
		for (WebElement webelement : webelements) {
		logger.info("Available Roles is"+webelement.getText());
			availableroles.add(webelement.getText());
		}
		return availableroles;
	 }
	 
	 
	public String assign_availableUserToAssignUser()
	{
	 
		String availableuser= getText(firstavailableuserlocator);
	  click(firstavailableuserlocator);
	   customReport.reporter(availableuser, "Has Been Moved From Available Users To Assigned Users"); 
	  
		
	   return availableuser;	
	}
	public String assign_assignUserToAvailableUser()
	{
	   
	  String assignuser= getText(firstassignuserlocator);
	  click(firstassignuserlocator);
	  customReport.reporter(assignuser, "Has Been Moved From Assign Users To Available Users"); 
	  
	  return assignuser;	
	}
	
	public void addUsersToGroup(AddUserBeanPage adduserbeanpage)
    {
		enterText(userassignmenttextboxlocator,  adduserbeanpage.getFirstname()+" "+adduserbeanpage.getLastname());
		click(userassignmenttextboxlocator);
		pause(8000);
		click(assignuserlocator);
       
    }
	
	public void removeUsersfromGroup(AddUserBeanPage adduserbeanpage)
    {
		By removeuserlocator=By.xpath("//*[contains(text(),'"+adduserbeanpage.getUsername()+"')]/..//*[contains(@ng-click,'removeItem')]");
		pause(3000);
		click(removeuserlocator);
    }
	
	
	

	public Map<String, String> verify_labels() {
		Map<String, String> newgrouppagelablesmap=new HashMap<String, String>();
		newgrouppagelablesmap.put("groups", getText(groupsbuttonlocator));
		newgrouppagelablesmap.put("finduserssearch", getAttribute(finduserssearch, "placeholder"));
		newgrouppagelablesmap.put("findrolessearch", getAttribute(findrolessearch, "placeholder"));
			return newgrouppagelablesmap;
	}
	
	 
}