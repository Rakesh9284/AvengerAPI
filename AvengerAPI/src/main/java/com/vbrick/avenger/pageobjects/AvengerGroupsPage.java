package com.vbrick.avenger.pageobjects;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AddingUserIntoGroupsPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerCategoriesPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerGroupsPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerLibrariesPropertyPage;
import com.vbrick.avenger.dao.AddLdapDeviceBeanPage;
import com.vbrick.avenger.dao.AvengerGroupsBeanPage;
import com.vbrick.avenger.dao.LibraryBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;
public class AvengerGroupsPage extends WebElements{
	private static Logger logger = Logger.getLogger(AvengerGroupsPage.class);

	By creategroupbuttonlocator=By.xpath(AvengerGroupsPropertyPage.avengergrouppage_createbuttonlocator.getProperty());	
	By newgroupname=By.xpath(AvengerGroupsPropertyPage.avengergrouppage_newgroupname.getProperty());	
	By creategrouplocator=By.xpath(AvengerGroupsPropertyPage.avengergrouppage_creategrouplocator.getProperty());	
	By creategrouperrortext=By.xpath(AvengerGroupsPropertyPage.avengergrouppage_creategrouperrortext.getProperty());	
	By customizebuttonlocator=By.xpath(AvengerGroupsPropertyPage.avengergrouppage_customizebuttonlocator.getProperty());
	By deletecategorypopuplocator=By.xpath(AvengerCategoriesPropertyPage.deletecategorypopuplocator.getProperty());
	By importgroupfromldaplocator=By.xpath(AvengerGroupsPropertyPage.avengergrouppage_importgroupfromldaplocator.getProperty()); 
	By selectinguser=By.xpath(AddingUserIntoGroupsPropertyPage.adduserintogroups_selectinguser.getProperty());	
	By groupsearchlocator = By.xpath(AvengerGroupsPropertyPage.avengergrouppage_groupssearchlocator.getProperty());
	By userassignmentsearchloactor= By.xpath(AddingUserIntoGroupsPropertyPage.adduserintogroups_searchinputlocator.getProperty());
	By searchinputlocator=By.xpath(AddingUserIntoGroupsPropertyPage.adduserintogroups_searchinputlocator.getProperty());
	By allgroupslocator=By.xpath("//*[contains(@uisref,'portal.admin.user-access.groups.edit')]");

	
	private WebDriver driver;
	
	private CustomReport customReport;
	private BasePage basePage;

	public AvengerGroupsPage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver=driver;

		this.customReport = customReport;
		this.basePage=basePage;
	}


	public AvengerAddingUsersintoGroupPage clickAddGroupButton()
	{
		click(creategrouplocator);	
		waitForElementPresent(newgroupname);
		return basePage.avengerAddUsersIntoGroupsPage(driver,  customReport, basePage);
	}

	public boolean checkcreatebuttonlocator()
	{
		boolean sflag=false;
		if(waitForElementPresent(creategroupbuttonlocator)==true)
		{
			sflag=true;
			customReport.reporter("Create button is present in groups Page","");
		}
		else 
		{
			customReport.reporter("Create button is not present in groups Page","");
			sflag=false;
		}
		return sflag;

	}
	public boolean checkCustomizebuttonlocator()
	{
		boolean sflag=false;
		if(waitForElementPresent(customizebuttonlocator)==true)
		{
			sflag=true;
			customReport.reporter("Customize button is present in groups Page","");
		}
		else 
		{
			sflag=false;
			customReport.reporter("Customize button is not present in groups Page","");

		}
		return sflag;

	}
	public boolean checknewgroupnametext()
	{
		boolean sflag=false;
		if(waitForElementPresent(newgroupname)==true)
		{
			sflag=true;
			customReport.reporter("New Group Name textbox  is present in groups Page","");
		}
		else 
		{
			customReport.reporter("New Group Name textbox  is not present in groups Page","");
			sflag=false;
		}
		return sflag;
	}

	public String createNewgroup(AvengerGroupsBeanPage beanspage)
	{
		By checkgroupcreation=By.xpath("//span[@class='glyphicons group circle']/..");
		enterText(newgroupname, beanspage.getNewgroup());
		logger.info("THe create group locator is"+creategrouplocator);
		logger.info("THe create new group value  is"+beanspage.getNewgroup());
        click(creategroupbuttonlocator);
		customReport.reporter("Create Group Button Locator is Clicked", "");
		pause(2000);
		waitForElementPresent(checkgroupcreation);
		enterText(groupsearchlocator,  beanspage.getNewgroup());
		pause(5000);
		clickEnter(groupsearchlocator);
		waitForElementPresent(checkgroupcreation);
        pause(3000);
		customReport.reporter("Group Created Successfully with Group Name",getText(checkgroupcreation));
		return  getText(checkgroupcreation);
	}

	By groupSearch = By.xpath(AvengerGroupsPropertyPage.avengergrouppage_groupssearchlocator.getProperty());
	public String verifyGroupsCreation(AvengerGroupsBeanPage avengerGroupsBeanPage) {
		
		enterText(groupSearch,  avengerGroupsBeanPage.getNewgroup());
		clickEnter(groupSearch);
		By groupLocator= By.partialLinkText(avengerGroupsBeanPage.getNewgroup());
		waitForElementPresent(groupLocator);
		String grpName = getText(groupLocator);
		customReport.reporter("Group Created Successfully","");
		return grpName;
	}

	public boolean verify_createNewgroup()
	{
		return locatorsVisibilityAsPerRoles(creategrouplocator);
	}

	public boolean verify_deletegroupvisibility(AvengerGroupsBeanPage beanspage)
	{
		enterText(groupsearchlocator,  beanspage.getNewgroup());
		clickEnter(groupsearchlocator);
		pause(5000);
		By deletegroupxpath=By.xpath("//*[@ng-click='deleteGroup(group)']");
		return locatorsVisibilityAsPerRoles(deletegroupxpath);
	}

	
	public String delete_Group(AvengerGroupsBeanPage beanspage)
	{

		enterText(groupsearchlocator,  beanspage.getNewgroup());
		clickEnter(groupsearchlocator);
		pause(5000);
		By deletegroupxpath=By.xpath("//*[@ng-click='deleteGroup(group)']");
		waitForElementPresent(deletegroupxpath);
		click(deletegroupxpath);
		pause(2000);
		waitForElementPresent(deletecategorypopuplocator);
		click(deletecategorypopuplocator);
		pause(5000);
		return String.valueOf(elements(deletegroupxpath));

	}

	public String duplicateGroup(AvengerGroupsBeanPage beanspage) 
	{
		
		enterText(newgroupname, beanspage.getNewgroup());
		logger.info("THe create group locator is"+creategrouplocator);
		click(creategroupbuttonlocator);
		customReport.reporter("Create Group Button Locator is Clicked", "");
		logger.info("THe duplicate group value  is"+beanspage.getNewgroup());
		logger.info("value returned for create duplicate group is"+getText(creategrouperrortext));		 
		waitForElementPresent(creategrouperrortext);
	    return	getAttribute(creategrouperrortext,"textContent");
		
		
		
	}

	public AvengerAddingUsersintoGroupPage clickNewGroup(AvengerGroupsBeanPage beanspage)
	{
		clearWebElementText(groupsearchlocator);
		pause(3000);
		enterText(groupsearchlocator,  beanspage.getNewgroup());
		clickEnter(groupsearchlocator);
		pause(5000);
		By newgrouplink=By.partialLinkText(beanspage.getNewgroup());	
		customReport.reporter("New Group link is Clicked"+beanspage.getNewgroup(), "");
		click(newgrouplink);	
		waitForElementPresent(searchinputlocator);
		return basePage.avengerAddUsersIntoGroupsPage(driver,  customReport, basePage);
	}

	
	
	
	public void create_subGroup(AvengerGroupsBeanPage avengergroupbeanpage)
	{
		By searchlocator=By.xpath("//input[contains(@ng-model, 'search')]");
		enterText(searchlocator,  avengergroupbeanpage.getNewgroup());
		clickEnter(searchlocator);
		By clicksubgroupbutton=By.xpath("//button/span[text()='Add Child']");
		click(clicksubgroupbutton);
		By entersubgrouptext=By.xpath("//*[@ng-model='group.childCreateFormData.name']");
		enterText(entersubgrouptext, avengergroupbeanpage.getSubgroupname());
		By clicksubgroupcreatebutton=By.xpath("//*[@ng-model='group.childCreateFormData.name']/../..//button");
		submit(clicksubgroupcreatebutton);
		By subgroupnamelocator=By.partialLinkText(avengergroupbeanpage.getSubgroupname());
		waitForElementPresent(subgroupnamelocator);
		
	}
	

	public String verify_subGroupCreation(AvengerGroupsBeanPage avengergroupbeanpage)
	{
		
		
		enterText(groupsearchlocator,  avengergroupbeanpage.getNewgroup());
		clickEnter(groupsearchlocator);
		By expandgroup = By.xpath("//*[@ng-click='expand(group)']");
		click(expandgroup);
		By subgroupnamelocator=By.partialLinkText(avengergroupbeanpage.getSubgroupname());
		waitForElementPresent(subgroupnamelocator);
		logger.info("Sub Group Name Created is"+getText(subgroupnamelocator));
		customReport.reporter("Group Name is "+avengergroupbeanpage.getNewgroup(),"Sub Group Name is"+getText(subgroupnamelocator)); 
		return getText(subgroupnamelocator);
	}


	public boolean checkGroupsPageOpens()
	{
		boolean sflag=false;

		if(waitForElementPresent(creategrouplocator)==true)
		{
			sflag=true;
			customReport.reporter("Groups page opens Successfully.","");
		}
		else
		{
			sflag=false;
			customReport.reporter("Unable to Open Groups page. ","");

		}
		return sflag;
	}
	public Boolean renamegroup_verification(String rename)
	{
		Boolean sflag=false;
		By renametext=By.partialLinkText(rename);
		logger.info("value of renametext xpath is"+renametext);
		if((waitForElementPresent(renametext))==true)
		{
			customReport.reporter("Rename group successfully","");	
			sflag=true;
		}
		else
		{
			sflag=false;
			customReport.reporter("Rename group successfully Failed","");	
		}

		return sflag;
	}

	public AvengerImportGroupfromLdapPage click_importgroupfromLdap(AddLdapDeviceBeanPage addldapdevicebeanpage)
	{
		click(importgroupfromldaplocator);
		By ldapconnectornamelocator=By.xpath("//*[contains(text(),'"+addldapdevicebeanpage.getLdapconnectornamelocator()+"')]");	
		
		click(ldapconnectornamelocator);
		pause(5000);
		return basePage.avengerImportGroupsFromLdapPage(driver,  customReport, basePage);

	}

	public void clickExpandAll()
	{
		By expandalllocator = By.xpath(AvengerGroupsPropertyPage.avengergrouppage_expandallbuttonlocator.getProperty());
		click(expandalllocator);
	}

	public void clickCollapseAll()
	{
		By collapsealllocator = By.xpath(AvengerGroupsPropertyPage.avengergrouppage_collapseallbuttonlocator.getProperty());
		click(collapsealllocator);
	}

	public boolean groupVisibility(String groupname)
	{
		By grouplocator = By.xpath("//*[contains(text(),'"+groupname+"')]");
		return isDisplayed(grouplocator);
	}

	public boolean verifysearchField() {
		   By usersearchboxlocator = By.xpath("//*[contains(@ng-submit,'searchGroups')]/input");
		    return isDisplayed(usersearchboxlocator);
		 }
	
	public void searchGroup(String groupname)
	{
		enterText(groupsearchlocator,  groupname);
		clickEnter(groupsearchlocator);
		pause(5000);
	}
	
	public ArrayList<String> searchGroupstr(String groupname) throws InterruptedException
	{
		enterText(groupsearchlocator,  groupname);
		clickEnter(groupsearchlocator);
		pause(5000);
		ArrayList<String> groups = get_AllGroups();
		return groups;
	}
	
	public String verify_LdapGroupCreation(String groupname)
	{
		By checkgroupcreation=By.partialLinkText(groupname);
		return getText(checkgroupcreation);
		}

	public int verify_groupexists(String groupname)
	{
		By checkgroupcreation=By.partialLinkText(groupname);
		waitForElementPresent(creategroupbuttonlocator);
		return elements(checkgroupcreation);
	}
	
	public Boolean verify_IsElementdoesnot_exists(AvengerGroupsBeanPage avengergroupsbeanpage)
	{
		By groupSearch = By.xpath(AvengerGroupsPropertyPage.avengergrouppage_groupssearchlocator.getProperty());
		enterText(groupSearch,  avengergroupsbeanpage.getNewgroup());
		clickEnter(groupSearch);
		By groupnamelocator= By.partialLinkText(avengergroupsbeanpage.getNewgroup());
		return isDisplayedWithoutException(groupnamelocator);
	}


	public Map<String,String> getlabels(){
		By clicksubgroupbutton=By.xpath("//button/span[text()='Add Child']");
		By nametextboxlocator=By.xpath("//input[@ng-model='rootGroupFormData.name']");
		Map<String ,String> groupspagelabelsmap=new HashMap<String, String>();
		groupspagelabelsmap.put("AddGroup", getText(creategrouplocator));
		groupspagelabelsmap.put("Addchild", getText(clicksubgroupbutton));
		groupspagelabelsmap.put("FindGroup", getAttribute(groupsearchlocator, "placeholder"));
		groupspagelabelsmap.put("NameSearchBox", getAttribute(nametextboxlocator, "placeholder"));
		return groupspagelabelsmap;
		
	}
	
	 public ArrayList<String> get_AllGroups() throws InterruptedException 
		{
		 JavascriptExecutor jse1 = (JavascriptExecutor) driver;
			int j;
			
			for(j=0;j<=20;j++) {
			jse1.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			Thread.sleep(5000);
			}
			ArrayList<String> groupnames= new ArrayList<String>();
			for (WebElement groupname : getAllWebElementValues(allgroupslocator)) 
			{
				logger.info("Group name is"+groupname.getText());
				groupnames.add(groupname.getText());	
			}
			return groupnames;
		}
	 
}