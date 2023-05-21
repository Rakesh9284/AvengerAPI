package com.vbrick.avenger.pageobjects;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AddUserPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerForgotPasswordPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerUserDashBoardPropertyPage;
import com.vbrick.avenger.ObjProperty.CreatePasswordPropertyPage;
import com.vbrick.avenger.ObjProperty.EditRootAccountProperty;
import com.vbrick.avenger.dao.AddUserBeanPage;
import com.vbrick.avenger.dao.AvengerGroupsBeanPage;
import com.vbrick.avenger.dao.EditRootAccountBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;
public class AvengerEditRootAccountPage extends WebElements{
	
private static final long serialVersionUID = 1L;
private static Logger logger = Logger.getLogger(AvengerEditRootAccountPage.class);
    By firstname=By.name(EditRootAccountProperty.editrootaccount_firstname.getProperty());	
    By lastname=By.name(EditRootAccountProperty.editrootaccount_lastname.getProperty());	
    By contactemail=By.name(EditRootAccountProperty.editrootaccount_email.getProperty());	
    By title=By.xpath(EditRootAccountProperty.editrootaccount_title.getProperty());	
    By phonenumber=By.xpath(EditRootAccountProperty.editrootaccount_phone.getProperty());	
    By language=By.name("language");
    By submitbuttonlocator=By.xpath(EditRootAccountProperty.editrootaccount_submitbuttonlocator.getProperty());	
	By buttonlocator=By.xpath(EditRootAccountProperty.editrootaccount_buttonlocator.getProperty());
	By linklocator=By.xpath(EditRootAccountProperty.editrootaccount_linklocator.getProperty());
	By adduserlink=By.linkText(AvengerUserDashBoardPropertyPage.avengeruserdashboard_adduserlink.getProperty());	
    By password=By.name(CreatePasswordPropertyPage.createPassword_password.getProperty());	
    By userconfirmationurl=By.xpath(EditRootAccountProperty.editrootaccount_userconfirmationurl.getProperty());
    By resetpasswordbuttonlocator=By.xpath(EditRootAccountProperty.editrootaccount_resetpasswordbuttonlocator.getProperty());
    By showpasswordresetbuttonlocator=By.xpath(EditRootAccountProperty.editrootaccount_showpasswordresetbuttonlocator.getProperty());
    By userpasswordreseturllocator=By.xpath(EditRootAccountProperty.editrootaccount_userpasswordreseturllocator.getProperty());
    By showconfirmationbuttonlocator=By.xpath(EditRootAccountProperty.editrootaccount_showconfirmationbuttonlocator.getProperty()); 
    By assignrolesearchtextboxlocator=By.xpath(EditRootAccountProperty.editrootaccount_assignrolesearchtextboxlocator.getProperty());
    By availablerolesearchtextboxlocator=By.xpath(EditRootAccountProperty.editrootaccount_availablerolesearchtextboxlocator.getProperty());
    By addrolebuttonlocator=By.xpath(EditRootAccountProperty.editrootaccount_addrolebuttonlocator.getProperty());
    By removerolebuttonlocator=By.xpath(EditRootAccountProperty.editrootaccount_removerolebuttonlocator.getProperty());
    By availableroleslocator=By.xpath(EditRootAccountProperty.editrootaccount_availableroleslocator.getProperty());
    By assignroleslocator=By.xpath(EditRootAccountProperty.editrootaccount_assignedroleslocator.getProperty());
    By username=By.xpath("//*[@class='panel-heading']/h4");
    By availablegroupslocator=By.xpath(EditRootAccountProperty.editrootaccount_availablegroupslocator.getProperty());
    By assigngroupslocator=By.xpath(EditRootAccountProperty.editrootaccount_assigngroupslocator.getProperty());
    By securityanswerlocator=By.xpath(AvengerForgotPasswordPropertyPage.avengerforgotpasswordpage_securityanswerlocator.getProperty());
    By addrolessearchboxlocator = By.xpath(AddUserPropertyPage.adduser_addrolessearchboxlocator.getProperty());
    By createbuttonlocator=By.xpath(AddUserPropertyPage.adduser_createbuttonlocator.getProperty());	
    By availablegroupsearchtextboxlocator=By.xpath(EditRootAccountProperty.editrootaccount_availablegroupsearchtextboxlocator.getProperty());
    By addgroupbuttonlocator=By.xpath(EditRootAccountProperty.editrootaccount_addgroupbuttonlocator.getProperty());
    By assigngroupssearchtextboxlocator=By.xpath(EditRootAccountProperty.editrootaccount_assigngroupssearchtextboxlocator.getProperty());  
    

    private WebDriver driver;
	
	private CustomReport customReport;
	private BasePage basePage;
	
	public AvengerEditRootAccountPage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver=driver;

		this.customReport = customReport;
		this.basePage = basePage;
		// TODO Auto-generated constructor stub
	}
	
	
	public void clickShowButton()
	{
		waitForElementPresent(firstname);
		click(showpasswordresetbuttonlocator);		
	}
	
	public boolean verifyShowButtonAvailabilty()
	{
		boolean flag=false;
		if(elements(showpasswordresetbuttonlocator)>0){
			customReport.reporter("Show button available", "");
			flag=true;
	}
		return flag;
	}
	
	public boolean verifyResetButtonAvailabilty()
	{
		boolean flag=false;
		if(elements(resetpasswordbuttonlocator)>0){
			customReport.reporter("Reset button available", "");
			flag=true;
	}
		return flag;
	}
	
		public AvengerUserDashboardPage EditRootAccount(EditRootAccountBeanPage accountbeanpage)
	{
		clearWebElementText(firstname);
		clearWebElementText(lastname);
	   enterText(firstname,  accountbeanpage.getFirstname());	
	   enterText(lastname,  accountbeanpage.getLastname());	
	   enterText(contactemail,  accountbeanpage.getContactemail());	
	   enterText(title,  accountbeanpage.getTitle());	
	   enterText(phonenumber,  accountbeanpage.getPhonenumber());	
	   selectValuefromDropDown(language, accountbeanpage.getLanguage());
	   submit(submitbuttonlocator);	
	   waitForElementPresent(adduserlink);
	   return basePage.avengerUserDashBoardPage(driver,  customReport, basePage);
	      
	}
    
	
	public AvengerConfirmPasswordPage Clickonbutton(EditRootAccountBeanPage accountbeanpage) 
	{
		selectValuefromDropDown(language, accountbeanpage.getLanguage());
		enterText(firstname,  accountbeanpage.getFirstname());	
		enterText(lastname,  accountbeanpage.getLastname());	
		enterText(contactemail,  accountbeanpage.getContactemail());	
		enterText(title,  accountbeanpage.getTitle());	
		enterText(phonenumber,  accountbeanpage.getPhonenumber());	
		click(buttonlocator);	
		accountbeanpage.setUserconfirmationurl(getText(userconfirmationurl));
		click(linklocator);
		waitForElementPresent(password);
		customReport.reporter("New Available Url is Clicked","");
		  
	   return basePage.avengerConfirmPasswordPage(driver,  customReport, basePage);
	}
	
	 public void assignRole_user(AddUserBeanPage adduserbean) 
	 {
		 logger.info("I am in role page---");
		   enterText(firstname,  adduserbean.getFirstname());	
		   enterText(lastname,  adduserbean.getLastname());	
		   enterText(contactemail,  adduserbean.getContactemail());	
		   enterText(title,  adduserbean.getTitle());	
		   enterText(phonenumber,  adduserbean.getPhoneno());	
		   selectValuefromDropDown(language, adduserbean.getLanguage());
		   logger.info("The Assigned role is"+adduserbean.getUserrole());
		   click(availablerolesearchtextboxlocator);
		   enterText(availablerolesearchtextboxlocator, adduserbean.getUserrole());
		    waitForElementPresent(availablerolesearchtextboxlocator);
			   pause(5000);
		   click(addrolebuttonlocator);
		   pause(5000);
		   waitForElementEnable(submitbuttonlocator);
		   submit(submitbuttonlocator);
		   customReport.reporter("Role Has been assigned to the user",""+adduserbean.getUsername());
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
		 pause(3000);
	    List<WebElement> webelements= getAllWebElementValues(assigngroupslocator);
		ArrayList<String> assignedgroups =  new ArrayList<String>();
		for (WebElement webelement : webelements) {
		logger.info("Assigned  Groups are "+webelement.getText());
		assignedgroups.add(webelement.getText());
		}
		return assignedgroups;
	 }
	 
	 public int get_allAssignedGroupsElements()
	 {
		 return elements(assigngroupslocator);
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
	 
    public AvengerForgotPasswordPage reset_UserPassword()
    {
      waitForElementPresent(resetpasswordbuttonlocator);
    	click(resetpasswordbuttonlocator);
    	waitForElementPresent(showpasswordresetbuttonlocator);
      click(showpasswordresetbuttonlocator);
      waitForElementPresent(userpasswordreseturllocator);
      click(userpasswordreseturllocator);
      waitForElementPresent(securityanswerlocator);
      return basePage.avengerForgotPasswordPage(driver,  customReport, basePage);
      
    }
	
    public AvengerConfirmPasswordPage click_UserConfirmationURL()
    {
     	waitForElementPresent(showconfirmationbuttonlocator);
    	click(showconfirmationbuttonlocator);
     	waitForElementPresent(userconfirmationurl);
     	click(userconfirmationurl);
     	waitForElementPresent(password);
     	return basePage.avengerConfirmPasswordPage(driver,  customReport, basePage);
      	
    }
    
    public AvengerForgotPasswordPage click_ResetConfirmationURL()
    {
    	click(resetpasswordbuttonlocator);
    	customReport.reporter("Clicked on Reset Password Button", "");
    	click(showpasswordresetbuttonlocator);
    	customReport.reporter("Clicked on Show Password Button","");
    	click(userpasswordreseturllocator);
    	customReport.reporter("User Password Reset  Url is Clicked","");
    	waitForElementPresent(securityanswerlocator);
    	return basePage.avengerForgotPasswordPage(driver,  customReport, basePage);
    }

    public String click_resetPasswordButton()
   
    {
    	pause(4000);
    	click(resetpasswordbuttonlocator);
    	click(showpasswordresetbuttonlocator);
    	customReport.reporter("Clicked on Reset Password Button", "");
    	pause(3000);
       logger.info("Reset Password Link is"+getText(userpasswordreseturllocator));
    	return getText(userpasswordreseturllocator);
    }
    
    public boolean verify_UserNameFieldNotPresent()
    {
    	if(isDisplayedWithoutException(username)==false)
    		customReport.reporter("UserName Field is Not Available In Edit User Page","");
    	else
    		customReport.reporter("UserName Field is Available In Edit User Page","");
        return isDisplayedWithoutException(username);
    	
    }
    
    public void add_Roles(List<String> roles)
	{
		
		for(int i=0;i<roles.size();i++)
		{
	   pause(5000);
	   logger.info("===="+driver.toString().contains("InternetExplorerDriver"));
	   if(driver.toString().contains("InternetExplorerDriver"))
	   {
		   logger.info("In IE Browser");
		  	
		   By administratorlocator=By.xpath("//*[contains(text(),'Administrative Actions')]/button[1]");
		   click(administratorlocator);
		  enterText(addrolessearchboxlocator,  roles.get(i));
	        pause(8000);
			   click(availableroleslocator);
	   }
	   else
	   {
			click(assignroleslocator);
			pause(5000);
		   enterText(addrolessearchboxlocator,  roles.get(i));
		    pause(5000);
		    click(availableroleslocator);
		    customReport.reporter("Clicked on role "+roles.get(i), "");
	   }
	    customReport.reporter("Clicked on role "+roles.get(i), "");
	    
		}
	}	
	 public void click_CreateButtonlocator()
	 {
		 pause(5000);
		 submit(createbuttonlocator);
	 }
	
	 public void assignGroups_user(AvengerGroupsBeanPage avengergroupbeanpage) 
	 {
		   click(availablegroupsearchtextboxlocator);
		   enterText(availablegroupsearchtextboxlocator, avengergroupbeanpage.getNewgroup());
		    waitForElementPresent(availablegroupsearchtextboxlocator);
			   pause(5000);
		   click(addgroupbuttonlocator);
		   pause(5000);
		   waitForElementEnable(submitbuttonlocator);
		   click(submitbuttonlocator);
		   customReport.reporter("Group Has been assigned to the user",""+avengergroupbeanpage.getNewgroup());
	 }
	
	 public void remove_groups(AvengerGroupsBeanPage avengergroupbeanpage)
	 {
		 enterText(assigngroupssearchtextboxlocator, avengergroupbeanpage.getNewgroup());
		 click(assigngroupslocator);
		 pause(5000);
		   click(submitbuttonlocator);
	 }
	 
	 public ArrayList<Boolean> verify_fieldDisabled()
	 {
		 ArrayList<Boolean> verifyfieldsenabled = new ArrayList<Boolean>();
		 verifyfieldsenabled.add(isEnabled(contactemail));
		 verifyfieldsenabled.add(isEnabled(firstname));
		 verifyfieldsenabled.add(isEnabled(lastname));
		 verifyfieldsenabled.add(isEnabled(title));
		 verifyfieldsenabled.add(isEnabled(phonenumber));
		 verifyfieldsenabled.add(isEnabled(resetpasswordbuttonlocator));
		 verifyfieldsenabled.add(isEnabled(resetpasswordbuttonlocator));
		 verifyfieldsenabled.add(isEnabled(createbuttonlocator));
		 return verifyfieldsenabled;	
	 }

	 public HashMap<String, String> get_allUserfieldValues()
	 {
	  String selectedOption = new Select(driver.findElement(By.xpath("//select[@name='language']"))).getFirstSelectedOption().getText();
      HashMap<String,String> uservalues=new HashMap<String, String>();
      uservalues.put("firstname", getAttribute(firstname, "value"));
      uservalues.put("lastname", getAttribute(lastname, "value"));
      uservalues.put("title", getAttribute(title, "value"));
      uservalues.put("phonenumber", getAttribute(phonenumber, "value"));
      uservalues.put("preferredlanguage", getDropDownSelectedOption(language));
      uservalues.put("contactemail", getAttribute(contactemail, "value"));
      uservalues.put("username", getText(username));          
      return uservalues;
	 }
	 
	 public Map<String, String> verify_labels() {
			By usersbuttonlocator=By.xpath("//div[@class='combined-toolbar responsive']/div/a");
			By findrolessearch=By.xpath("//input[@placeholder='Find Roles']");
			By findgroupssearch=By.xpath("//input[@placeholder='Find Groups']");
			Map<String, String> labelsmap=new HashMap<String, String>();
			labelsmap.put("users", getText(usersbuttonlocator));
			labelsmap.put("findroles", getAttribute(findrolessearch, "placeholder"));
			labelsmap.put("findgroups", getAttribute(findgroupssearch, "placeholder"));
			return labelsmap;
	 }
	 
}