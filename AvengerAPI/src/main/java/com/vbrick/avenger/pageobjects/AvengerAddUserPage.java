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
import com.vbrick.avenger.ObjProperty.AddUserPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerHomePropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerUserDashBoardPropertyPage;
import com.vbrick.avenger.dao.AddUserBeanPage;
import com.vbrick.avenger.dao.AvengerGroupsBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;
public class AvengerAddUserPage extends WebElements{
/**
	 * 
	 */

private static Logger logger = Logger.getLogger(AvengerAddUserPage.class);

    By firstname=By.xpath(AddUserPropertyPage.adduser_firstname.getProperty());	
    By lastName=By.xpath(AddUserPropertyPage.adduser_lastname.getProperty());	
    By email=By.xpath(AddUserPropertyPage.adduser_contactemail.getProperty());	
    By title=By.xpath(AddUserPropertyPage.adduser_title.getProperty());	
    By phoneNumber=By.xpath(AddUserPropertyPage.adduser_phoneno.getProperty());	
    By selectlang=By.name(AddUserPropertyPage.adduser_selectlang.getProperty());	
    By username=By.xpath(AddUserPropertyPage.adduser_username.getProperty());	
    By createbuttonlocator=By.xpath(AddUserPropertyPage.adduser_createbuttonlocator.getProperty());	
    By userslink =By.xpath(AvengerHomePropertyPage.homepg_userslink.getProperty());	
	By usernameerrortext=By.xpath(AddUserPropertyPage.adduser_usernameerrortext.getProperty());
	By emailerrortext=By.xpath(AddUserPropertyPage.adduser_contactemailerrortext.getProperty());
    By lastnameerrortext=By.xpath(AddUserPropertyPage.adduser_lastnameerrortext.getProperty());
    By availableroleslocator=By.xpath(AddUserPropertyPage.adduser_availableroleslocator.getProperty());
    By assignroleslocator=By.xpath(AddUserPropertyPage.adduser_assignroleslocator.getProperty());
    By availablegroupslocator=By.xpath(AddUserPropertyPage.adduser_availablegroupslocator.getProperty());
    By assigngroupslocator=By.xpath(AddUserPropertyPage.adduser_assigngroupslocator.getProperty());
    By availablegroupssearchtextboxlocator=By.xpath(AddUserPropertyPage.adduser_availablegroupssearchtextboxlocator.getProperty());
    By addrolessearchboxlocator = By.xpath(AddUserPropertyPage.adduser_addrolessearchboxlocator.getProperty());	
    By usernameinuseerrortextlocator=By.xpath(AddUserPropertyPage.adduser_usernameinuseerrortextlocator.getProperty());
	By groupslink =By.xpath(AvengerHomePropertyPage.homepg_groupslink.getProperty());	
	By adduserlink = By.linkText(AvengerUserDashBoardPropertyPage.avengeruserdashboard_adduserlink.getProperty());
	By addselectedgroup= By.xpath(AddUserPropertyPage.adduser_addselectedgroup.getProperty());	
	By donebuttonlocator= By.xpath(AddUserPropertyPage.adduser_donebuttonlocator.getProperty());	
	
   
	private WebDriver driver;
	private CustomReport customReport;
	private BasePage basePage;
	
	public AvengerAddUserPage(WebDriver driver,CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver=driver;

		this.customReport = customReport;
		this.basePage=basePage;
	}
	
	
	/**
	 *  This method only creates user without assigning groups and roles
	 * @param accountBeansPage
	 * @return
	 */
	
	public AvengerUserDashboardPage addUseraccount(AddUserBeanPage accountBeansPage)
    {       
		By adduserlink = By.linkText(AvengerUserDashBoardPropertyPage.avengeruserdashboard_adduserlink.getProperty());
		By groupslink =By.xpath(AvengerHomePropertyPage.homepg_groupslink.getProperty());	

		    enterText(firstname, accountBeansPage.getFirstname());
			enterText(lastName,  accountBeansPage.getLastname());
			enterText(email, accountBeansPage.getContactemail());
			customReport.reporter("Email is "+accountBeansPage.getContactemail(), "");
			enterText(title, accountBeansPage.getTitle());
			enterText(phoneNumber, accountBeansPage.getPhoneno());
		    selectValuefromDropDown(selectlang, accountBeansPage.getLanguage());
		    logger.info("User name value is"+accountBeansPage.getUsername());
		    enterText(username, accountBeansPage.getUsername());
			submit(createbuttonlocator);
			customReport.reporter("Entered Valid data for the User","");
          	waitForElementEnable(adduserlink);
			return basePage.avengerUserDashBoardPage(driver,  customReport, basePage);
    }
	
	
	
	public AvengerUserDashboardPage addUseraccountRoles(AddUserBeanPage accountBeansPage,List<String> roles)
    {       
		By adduserlink = By.linkText(AvengerUserDashBoardPropertyPage.avengeruserdashboard_adduserlink.getProperty());
		By groupslink =By.xpath(AvengerHomePropertyPage.homepg_groupslink.getProperty());	

		    enterText(firstname, accountBeansPage.getFirstname());
			enterText(lastName,  accountBeansPage.getLastname());
			enterText(email, accountBeansPage.getContactemail());
			customReport.reporter("Email is "+accountBeansPage.getContactemail(), "");
			enterText(title, accountBeansPage.getTitle());
			enterText(phoneNumber, accountBeansPage.getPhoneno());
		    selectValuefromDropDown(selectlang, accountBeansPage.getLanguage());
		    logger.info("User name value is"+accountBeansPage.getUsername());
		    enterText(username, accountBeansPage.getUsername());
			for(int i=0;i<roles.size();i++)
			{
		   pause(5000);
		   logger.info("===="+driver.toString().contains("InternetExplorerDriver"));
		  click(assignroleslocator);
		  pause(5000);
		    enterText(addrolessearchboxlocator,  roles.get(i));
			    pause(5000);
			    click(availableroleslocator);
			    customReport.reporter("Clicked on role "+roles.get(i), "");
		        customReport.reporter("Clicked on role "+roles.get(i), "");
		    
			}
			submit(createbuttonlocator);
			customReport.reporter("Entered Valid data for the User","");
			waitForElementEnable(adduserlink);
			return basePage.avengerUserDashBoardPage(driver,  customReport, basePage);
    }

	
	
	
	public void sameUserName(AddUserBeanPage accountBeansPage){
		 enterText(firstname, accountBeansPage.getFirstname());
			enterText(lastName,  accountBeansPage.getLastname());
			enterText(email, accountBeansPage.getContactemail());
			customReport.reporter("Email is "+accountBeansPage.getContactemail(), "");
			enterText(title, accountBeansPage.getTitle());
			enterText(phoneNumber, accountBeansPage.getPhoneno());
		    selectValuefromDropDown(selectlang, accountBeansPage.getLanguage());
		    logger.info("User name value is"+accountBeansPage.getUsername());
		    enterText(username, accountBeansPage.getUsername());
			submit(createbuttonlocator);
	}
	
	
	 public AvengerUserDashboardPage addUseraccount(AddUserBeanPage accountBeansPage,AvengerGroupsBeanPage avengergroupbeanpage)
		{
			enterText(firstname, accountBeansPage.getFirstname());
			enterText(lastName,  accountBeansPage.getLastname());
			enterText(email, accountBeansPage.getContactemail());
			enterText(title, accountBeansPage.getTitle());
			enterText(phoneNumber, accountBeansPage.getPhoneno());
			selectValuefromDropDown(selectlang, accountBeansPage.getLanguage());
			logger.info("User name value is"+accountBeansPage.getUsername());
			enterText(username, accountBeansPage.getUsername());
			enterText(availablegroupssearchtextboxlocator, avengergroupbeanpage.getNewgroup());
			click(availablegroupssearchtextboxlocator);
			pause(2000);
			click(addselectedgroup);
			pause(2000);
			click(availablegroupssearchtextboxlocator);
			click(donebuttonlocator);
			submit(createbuttonlocator);
			customReport.reporter("Entered Valid data for the User With Adding Group",avengergroupbeanpage.getNewgroup());
			return basePage.avengerUserDashBoardPage(driver,  customReport, basePage);
		}
	 
		public HashMap<String, String> getUserData()
		{
          HashMap<String,String> userdata= new HashMap<String, String>();
          userdata.put("firstname", getAttribute(firstname, "value"));
          userdata.put("lastName", getAttribute(lastName, "value"));
          userdata.put("email", getAttribute(email, "value"));
          userdata.put("title", getAttribute(title, "value"));
          userdata.put("phoneNumber", getAttribute(phoneNumber, "value"));
          userdata.put("username", getAttribute(username, "value"));
          userdata.put("selectlang", getCurrentValueFromDropdown(selectlang));
          return userdata;
		}

	 
	 
	 
	 public String addUser_deletefield(AddUserBeanPage accountBeansPage,By locatortodelete, By locatorxpath)
     {
		 enterText(firstname, accountBeansPage.getFirstname());
			enterText(lastName,  accountBeansPage.getLastname());
			enterText(email, accountBeansPage.getContactemail());
			enterText(title, accountBeansPage.getTitle());
			enterText(phoneNumber, accountBeansPage.getPhoneno());
		   selectValuefromDropDown(selectlang, accountBeansPage.getLanguage());
		   enterText(username, accountBeansPage.getUsername());
		    clearWebElementTextusingbackspace(locatortodelete, getAttribute(locatortodelete,"value").length());
		    return getText(locatorxpath);
     }
	
	 public boolean createbutton_isEnabled()
	 {
		boolean sflag= false;
		 if(isEnabled(createbuttonlocator)==true)
		 {
			 customReport.reporter("Create Button is Enabled","");
			 sflag=true;
		 }
		 else
		 {
			 customReport.reporter("Create Button is not Enabled","");
			 sflag=false;
		 }
		
		return sflag;
		 
	 }
	 
	 public ArrayList<String> get_allassignedroles()
	 {
		 waitForElementPresent(assignroleslocator);
	    List<WebElement> webelements= getAllWebElementValues(assignroleslocator);
		ArrayList<String> assignedroles =  new ArrayList<String>();
		for (WebElement webelement : webelements) {
		logger.info("Assigned  Roles is"+webelement.getText());
		assignedroles.add(webelement.getText());
		}
		return assignedroles;
	 }
	 
	 
	 public boolean check_assignedrole(AddUserBeanPage adduserbeanpage)
	 {
		 boolean sflag=false;
		 if( get_allassignedroles().contains(adduserbeanpage.getUserdefaultrole())==true)
		 {
			 sflag=true;
			customReport.reporter("Default Role Assigned to User",adduserbeanpage.getUserdefaultrole());
		 }
		 else
		 	 customReport.reporter("Default Role Assigned to User",adduserbeanpage.getUserdefaultrole());
		 return sflag;
	 }
	 
	 public ArrayList<String> get_allavailableroles()
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
	 
	 public ArrayList<String> get_allavailablegroups()
	 {
		
		 waitForElementPresent(availablegroupslocator);
	    List<WebElement> webelements= getAllWebElementValues(availablegroupslocator);
		ArrayList<String> availablegroups =  new ArrayList<String>();
		for (WebElement webelement : webelements) {
		logger.info("Available Groups is"+webelement.getText());
		availablegroups.add(webelement.getText());
		}
		return availablegroups;
	 }
	 public ArrayList<String> get_allassignedgroups()
	 {
		 waitForElementPresent(assigngroupslocator);
	    List<WebElement> webelements= getAllWebElementValues(assigngroupslocator);
		ArrayList<String> assignedgroups =  new ArrayList<String>();
		for (WebElement webelement : webelements) {
		logger.info("Assigned  Roles is"+webelement.getText());
		assignedgroups.add(webelement.getText());
		}
		return assignedgroups;
	 }
	 
	 public void add_Roles(List<String> roles)
		{
			
			for(int i=0;i<roles.size();i++)
			{
			
		   pause(5000);
		   logger.info("===="+driver.toString().contains("InternetExplorerDriver"));
		   if(driver.toString().contains("InternetExplorerDriver")||driver.toString().contains("firefox"))
		   {
			   logger.info("In IE Browser");
			  	
			   By administratorlocator=By.xpath("//*[contains(text(),'Administrative Actions')]/button[1]");
			   click(administratorlocator);
			  enterText(addrolessearchboxlocator,  roles.get(i));
		        pause(8000);
				   click(availableroleslocator);
				    enterText(phoneNumber, "9999999999");
		   }
		   else
		   {
				enterText(addrolessearchboxlocator,  roles.get(i));
			    pause(5000);
			    click(availableroleslocator);
			    customReport.reporter("Clicked on role "+roles.get(i), "");
		   }
		    customReport.reporter("Clicked on role "+roles.get(i), "");
		    
			}
		}	
		public AvengerUserDashboardPage click_SaveButton(AddUserBeanPage adduserbeanpage)
		{
			submit(createbuttonlocator);
			logger.info("clicked save button after adding role");
			waitForElementPresent(adduserlink);
			return basePage.avengerUserDashBoardPage(driver,  customReport, basePage);
		}
		
		public void click_CreateButtonlocator()
		 {
			isEnabled(createbuttonlocator);
			 click(createbuttonlocator);
		 }
	
		public String duplicateUserNameErrorText()
		{
		logger.info("Error for username in use error text is"+getText(usernameinuseerrortextlocator));
			return getText(usernameinuseerrortextlocator);
			
		}


		public Map<String, String> verify_labels() {
			By usersbuttonlocator=By.xpath("//div[@class='combined-toolbar responsive']/div/a");
			By usernamelable=By.xpath("//label[text()='Username']");
			By findrolessearch=By.xpath("//input[@placeholder='Find Roles']");
			By findgroupssearch=By.xpath("//input[@placeholder='Find Groupss']");
			Map<String, String> labelsmap=new HashMap<String, String>();
			labelsmap.put("users", getText(usersbuttonlocator));
			labelsmap.put("username", getText(usernamelable));
			labelsmap.put("findroles", getAttribute(findrolessearch, "placeholder"));
			labelsmap.put("findgroups", getAttribute(findgroupssearch, "placeholder"));
			return labelsmap;
		}

		public List<String> get_AllPreferredLanguagesforNewUser()
		{
			List<String>subtitlelanguage = getDropdownValue(selectlang);
	        return subtitlelanguage; 
		}
		
		public AvengerUserDashboardPage addUseraccountUniqueEmail(AddUserBeanPage accountBeansPage)
	    {       
			By adduserlink = By.linkText(AvengerUserDashBoardPropertyPage.avengeruserdashboard_adduserlink.getProperty());
			By groupslink =By.xpath(AvengerHomePropertyPage.homepg_groupslink.getProperty());	

			    enterText(firstname, accountBeansPage.getFirstname());
				enterText(lastName,  accountBeansPage.getLastname());
				enterText(email, accountBeansPage.getContactemail());
				customReport.reporter("Email is "+accountBeansPage.getContactemail(), "");
				enterText(title, accountBeansPage.getTitle());
				enterText(phoneNumber, accountBeansPage.getPhoneno());
			    selectValuefromDropDown(selectlang, accountBeansPage.getLanguage());
			    logger.info("User name value is"+accountBeansPage.getUsername());
			    enterText(username, accountBeansPage.getUsername());
				submit(createbuttonlocator);
				customReport.reporter("Entered Valid data for the User","");
				return basePage.avengerUserDashBoardPage(driver,  customReport, basePage);
	    }
		
		public AvengerUserDashboardPage addUseraccountUniqueEmailwithoutUser(AddUserBeanPage accountBeansPage)
	    {       
			By adduserlink = By.linkText(AvengerUserDashBoardPropertyPage.avengeruserdashboard_adduserlink.getProperty());
			By groupslink =By.xpath(AvengerHomePropertyPage.homepg_groupslink.getProperty());	

			    enterText(firstname, accountBeansPage.getFirstname());
				enterText(lastName,  accountBeansPage.getLastname());
				enterText(email, accountBeansPage.getContactemail());
				customReport.reporter("Email is "+accountBeansPage.getContactemail(), "");
				enterText(title, accountBeansPage.getTitle());
				enterText(phoneNumber, accountBeansPage.getPhoneno());
			    selectValuefromDropDown(selectlang, accountBeansPage.getLanguage());
				submit(createbuttonlocator);
				customReport.reporter("Entered Valid data for the User","");
				return basePage.avengerUserDashBoardPage(driver,  customReport, basePage);
	    }

	    public String verify_image() throws Exception  {
	    	By userprofilelocator=By.xpath("//*[contains(@class,'NC9WY__imagePreview')]"); 	
	    	Thread.sleep(5000);
			return getAttribute(userprofilelocator, "innerHTML");
	    	
	    }
	    public void deleteimage()  {
	    	By deleteimagelocator=By.xpath("//*[contains(text(),'Delete')]"); 	
	    	click(deleteimagelocator);
			logger.info("Profile picture Deleted successfully");
			By savebuttonlocator=By.xpath("//*[contains(text(),'Save')]");
			click(savebuttonlocator);
	    	
	    }

	    
}