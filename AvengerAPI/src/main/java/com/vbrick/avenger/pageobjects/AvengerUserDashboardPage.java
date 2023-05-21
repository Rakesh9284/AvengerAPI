package com.vbrick.avenger.pageobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AddUserPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerCategoriesPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerHomePropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerUserDashBoardPropertyPage;
import com.vbrick.avenger.ObjProperty.EditRootAccountProperty;
import com.vbrick.avenger.apibeans.AddUserApiBean;
import com.vbrick.avenger.dao.AddUserBeanPage;
import com.vbrick.avenger.dao.EditRootAccountBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;
public class AvengerUserDashboardPage extends WebElements{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(AvengerUserDashboardPage.class);
    By adduserlink=By.linkText(AvengerUserDashBoardPropertyPage.avengeruserdashboard_adduserlink.getProperty());	
    By createbuttonlocator=By.xpath(AddUserPropertyPage.adduser_createbuttonlocator.getProperty());	
    By firstname=By.name(EditRootAccountProperty.editrootaccount_firstname.getProperty());	
    By lastname=By.name(EditRootAccountProperty.editrootaccount_lastname.getProperty());	
    By availableroleslocator=By.xpath(AddUserPropertyPage.adduser_availableroleslocator.getProperty());
	By usersearchboxlocator = By.xpath(AddUserPropertyPage.adduser_usersearchboxlocator.getProperty());
	By deletedropdownlocator = By.xpath("//*[@class='more-icon']/..");
	By allusernamelist=By.xpath("//*[@class='flex-fill primary-cell table-cell']/../div[2]/span");
	By allcategorynameslist=By.xpath(AddUserPropertyPage.adduser_allcategorieslocator.getProperty());
	By allassignedgrpnameslist=By.xpath(AddUserPropertyPage.adduser_allassignedgroupslocator.getProperty());
	By saveuserlocator=By.xpath(AddUserPropertyPage.adduser_saveuserlocator.getProperty());
			
	
    private WebDriver driver;
	
	private CustomReport customReport;
	private BasePage basePage;
	private AvengerHomePage homepage; 
	private AvengerUserDashboardPage avengeruserdashboardpage;
	
	public AvengerUserDashboardPage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		
		super(driver);
		this.driver=driver;

		this.customReport = customReport;
		this.basePage=basePage;
		// TODO Auto-generated constructor stub
	}
	public AvengerAddUserPage clickAdduser() {
		// TODO Auto-generated method stub
		waitForElementPresent(adduserlink);
		click(adduserlink);
		customReport.reporter("Add User link is Clicked","");
		waitForElementPresent(createbuttonlocator);
		return basePage.avengerAddUserpage(driver,  customReport, basePage);
	}
      
	public String verifycreationofuser(AddUserBeanPage adduserbeanpage)
	{
		String sflag=""; 
	
		By newuserlink=By.xpath("//*[(text()='"+adduserbeanpage.getUsername()+"')]");
		By usersearchboxlocator = By.xpath("//*[contains(@ng-submit,'searchUsers')]/input");
		pause(3000);
		enterText(usersearchboxlocator,  adduserbeanpage.getUsername());
		clickEnter(usersearchboxlocator);
		sflag=getText(newuserlink);
		return sflag;
	}
	
	public void searchUser(AddUserBeanPage adduserbeanpage)
	{
		By newuserlink=By.xpath("//*[(text()='"+adduserbeanpage.getUsername()+"')]");
		By usersearchboxlocator = By.xpath("//*[contains(@ng-submit,'searchUsers')]/input");
		pause(3000);
		enterText(usersearchboxlocator,  adduserbeanpage.getUsername());
		clickEnter(usersearchboxlocator);
		pause(3000);
	}

	public ArrayList<String> searchUserstr(String username) throws InterruptedException
	{
		By usersearchboxlocator = By.xpath("//*[contains(@name,'searchQuery')]");
		pause(3000);
		enterText(usersearchboxlocator,  username);
		clickEnter(usersearchboxlocator);
		pause(3000);
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		int j;
		
		for(j=0;j<=5;j++) {
		jse1.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(5000);
		}
		ArrayList<String> users = get_AllUsers();
		return users;
	}
	
	/**
	 * The new user created will be selected
	 * @param accountBeansPage
	 * @return
	 */
	public AvengerEditRootAccountPage  clicknewuser_link(AddUserBeanPage accountBeansPage)
	{
		
		By userslink =By.xpath(AvengerHomePropertyPage.homepg_userslink.getProperty());	
		pause(5000);
		click(usersearchboxlocator);
		enterText(usersearchboxlocator,  accountBeansPage.getFirstname());
		clickEnter(usersearchboxlocator);
		pause(5000);
		By usernamelocator = By.xpath("(//*[contains(text(),'"+accountBeansPage.getFirstname()+" "+accountBeansPage.getLastname()+"')])");
		click(usernamelocator);
		waitForElementPresent(firstname	);
		return basePage.avengerEditRootAccountPage(driver,  customReport, basePage);
		
      }
	
	public AvengerEditRootAccountPage  clicknewuser_link1(AddUserBeanPage accountBeansPage)
	{
		
		By userslink =By.xpath(AvengerHomePropertyPage.homepg_userslink.getProperty());	
		pause(5000);
		click(usersearchboxlocator);
		enterText(usersearchboxlocator,  accountBeansPage.getFirstname());
		clickEnter(usersearchboxlocator);
		pause(5000);
		By usernamelocator = By.xpath("(//*[contains(text(),'"+accountBeansPage.getFirstname()+" "+accountBeansPage.getLastname()+"')])[2]");
		click(usernamelocator);
		waitForElementPresent(firstname	);
		return basePage.avengerEditRootAccountPage(driver,  customReport, basePage);
		
      }
	
	public AvengerEditRootAccountPage  searchandclicknewuser_link(AddUserBeanPage accountBeansPage)
	{
		
		By userslink =By.xpath(AvengerHomePropertyPage.homepg_userslink.getProperty());	
		pause(5000);
		click(usersearchboxlocator);
		enterText(usersearchboxlocator,  accountBeansPage.getUsername());
		clickEnter(usersearchboxlocator);
		pause(5000);
		By usernamelocator = By.xpath("//*[contains(text(),'"+accountBeansPage.getLastname()+"')]");
		click(usernamelocator);
		waitForElementPresent(lastname);
		return basePage.avengerEditRootAccountPage(driver,  customReport, basePage);
		
      }
	
		
	//API
		public AvengerEditRootAccountPage  clicknewuser_link(AddUserApiBean accountBeansPage)
		{
		
			By userslink =By.xpath(AvengerHomePropertyPage.homepg_userslink.getProperty());	
			pause(5000);
			click(usersearchboxlocator);
			enterText(usersearchboxlocator,  accountBeansPage.getFirstname());
			clickEnter(usersearchboxlocator);
			pause(5000);
			By usernamelocator = By.xpath("//*[contains(text(),'"+accountBeansPage.getFirstname()+" "+accountBeansPage.getLastname()+"')]");
			click(usernamelocator);
			waitForElementPresent(firstname	);
			return basePage.avengerEditRootAccountPage(driver,  customReport, basePage);
		
      }
			
			public AvengerEditRootAccountPage  clicknewuser_link(String username)
			{
				
				By userslink =By.xpath(AvengerHomePropertyPage.homepg_userslink.getProperty());	
				pause(3000);
				click(usersearchboxlocator);
				enterText(usersearchboxlocator,  username);
				clickEnter(usersearchboxlocator);
				pause(3000);
				By usernamelocator = By.xpath("//a[contains(text(),'"+username+"')]");
				click(usernamelocator);
				waitForElementPresent(firstname	);
				return basePage.avengerEditRootAccountPage(driver,  customReport, basePage);
				
		      }
			
			
				
	public Boolean verifying_Editedvalues(EditRootAccountBeanPage editrootaccountbeanpage)
	{
		pause(3000);
		enterText(usersearchboxlocator,  editrootaccountbeanpage.getFirstname()+" "+editrootaccountbeanpage.getLastname());
		clickEnter(usersearchboxlocator);
		By editeduservalue=By.xpath("//*[contains(text(),'"+editrootaccountbeanpage.getFirstname()+" "+editrootaccountbeanpage.getLastname()+"')]");
		Boolean sflag=waitForElementPresent(editeduservalue);
		return sflag;
		 
	}

	public void suspendUser(AddUserBeanPage adduserbeanpage)
	{
		searchUser(adduserbeanpage);
		click(deletedropdownlocator);
		By suspenduser=By.xpath("//*[@ng-click='suspend(user)']");
		customReport.reporter("suspended user value is" +adduserbeanpage.getUsername(),"");
		click(suspenduser);
		By suspendedlocatorxpath=By.xpath("//*[text()='"+adduserbeanpage.getUsername()+"']/../..//*[contains(@class,'icon-danger')]");
		waitForElementPresent(suspendedlocatorxpath);
	}
	public String awaitingconfirmation(AddUserBeanPage accountBeansPage)
	{
		By awaitingconfirmationmessage=By.xpath("//*[contains(text(),'"+accountBeansPage.getUsername()+"')]/../../div[3]/div[5]");	
		return getText(awaitingconfirmationmessage);

	}
	public String lockedusermessage(AddUserBeanPage accountBeansPage)
	{
		pause(3000);
		enterText(usersearchboxlocator,  accountBeansPage.getUsername());
		clickEnter(usersearchboxlocator);
		By userlocked=By.xpath("//*[text()='"+accountBeansPage.getUsername()+"']/ancestor::div//*[@class='glyphicons lock']/..");
		return getText(userlocked);
	
	}
	
	public void clickUnlockUserButton(AddUserBeanPage accountBeansPage) 
	{
		clicknewuser_link(accountBeansPage);
		By userlockedbutton=By.xpath("//*[@ng-click='unlockUser()']");	
		click(userlockedbutton);
		pause(5000);
		logger.info("locked user button is clicked");
		
	}
	
	
	public AvengerAddUserPage click_AddedUserForEditing(AddUserBeanPage adduserbeanpage)
	{
		pause(5000);
		enterText(usersearchboxlocator,  adduserbeanpage.getFirstname());
		clickEnter(usersearchboxlocator);
		By usernamelocator = By.xpath("//*[contains(text(),'"+adduserbeanpage.getFirstname()+" "+adduserbeanpage.getLastname()+"')]");
		click(usernamelocator);
		waitForElementPresent(availableroleslocator);
		return basePage.avengerAddUserpage(driver,  customReport, basePage);
	}
	
	 public boolean verify_Adduser()
	{
		return locatorsVisibilityAsPerRoles(adduserlink);
	}
	public void unsuspendUser(AddUserBeanPage adduserbean)
	{
		searchUser(adduserbean);
		click(deletedropdownlocator);
		By unsuspenduser=By.xpath("//*[@ng-click='unsuspend(user)']");
		click(unsuspenduser);
		customReport.reporter("Clicked on Unsuspend User Button","");
		By unsuspendactiveuserxpath=By.xpath("//*[text()='"+adduserbean.getUsername()+"']/../..//*[contains(@class,'icon-success')]");
         waitForElementPresent(unsuspendactiveuserxpath);
	}

	public void deleteUser(AddUserBeanPage adduserbeanpage) {
		
		By usersearchboxlocator = By.xpath("//*[contains(@ng-submit,'searchUsers')]/input");
		pause(3000);
		enterText(usersearchboxlocator,  adduserbeanpage.getUsername());
		clickEnter(usersearchboxlocator);
		pause(15000);
		click(deletedropdownlocator);
		By deleteuserlocator=By.xpath("//*[@ng-click='delete(user)']");
		click(deleteuserlocator);
	}
	public String confirmDelete(String command){
		 By popuptextlocator=By.xpath("//div[@class='modal-body ng-binding']");
		By deleteusercancelpopuplocator=By.xpath(AvengerCategoriesPropertyPage.deletecategorypopupnolocator.getProperty());
		By deleteuserpopuplocator=By.xpath(AvengerCategoriesPropertyPage.deletecategorypopuplocator.getProperty());
		pause(3000);
       String popuptext=getText(popuptextlocator);
		if(command.equals("Y"))
		{
			click(deleteuserpopuplocator);
			customReport.reporter("User deleted succesfully!!!!", "");
			pause(5000);
		}
		else
		{
			click(deleteusercancelpopuplocator);

		}
		return popuptext;
	}
	public String verifyDeletionOfuser(AddUserBeanPage adduserbeanpage) {

		enterText(usersearchboxlocator,  adduserbeanpage.getUsername());
		clickEnter(usersearchboxlocator);
		By createduser=By.xpath("//*[contains(text(),'"+adduserbeanpage.getFirstname()+" "+adduserbeanpage.getLastname()+"')]");
		return String.valueOf(elements(createduser));
	}	
	
	 public boolean verifyuserdeleteButton(String value) {
		 pause(3000);
		 enterText(usersearchboxlocator, value);
		 clickEnter(usersearchboxlocator);
		 By rootuserdeletebuttonlocator=By.xpath("//*[@class='more-icon']");
		 return isDisplayedWithoutException(rootuserdeletebuttonlocator);
		}
	 
	 public boolean verifysearchField() {
		    return isDisplayed(usersearchboxlocator);
		 }
	public Map<String,String> verifylables() {
		By usersearchboxlocator = By.xpath("//*[contains(@ng-submit,'searchUsers')]/input");
		By namelocator=By.xpath("//*[@class='sort-col']");
		By suspendlocator=By.xpath("//*[@ng-click='suspend(user)']");
		By deletelocator=By.xpath("//button[@ng-click='delete(user)']");
		Map<String,String> userslablesmap=new HashMap<String, String>();

		userslablesmap.put("adduser", getText(adduserlink));
		userslablesmap.put("searchuser",getAttribute(usersearchboxlocator, "placeholder"));
		userslablesmap.put("name",getText(namelocator));
		userslablesmap.put("suspend",getText(suspendlocator));
		userslablesmap.put("delete",getText(deletelocator));


		return userslablesmap;
	}
	
	public String getActiveUserCount()
	{
		By activeusercount= By.xpath(AvengerUserDashBoardPropertyPage.avengeruserdashboard_activeuserlink.getProperty());
		logger.info("Size of active users is"+ elements(activeusercount));
		return String.valueOf(elements(activeusercount));
	}

	
	public String getLicencedUserCount()
	{
		By licencedusercount = By.xpath(AvengerUserDashBoardPropertyPage.avengeruserdashboard_licencedusers.getProperty());
		logger.info("Size of licenced users is"+ getText(licencedusercount).split(":")[1]);
		return getText(licencedusercount).split(":")[1];
	}
	

	public String activeStatus(AddUserBeanPage accountBeansPage)
	{
		By activeStatus=By.xpath("//*[contains(text(),'"+accountBeansPage.getUsername()+"')]/../../div[3]/div[1]/span[2]");	
		return getText(activeStatus);

	}

  public String getUserStatus(String username)
  {
	  By userstatus=By.xpath("//*[contains(text(),'lowercase("+username+")')]/../..//*[contains(@class,'status')]");
	  return getText(userstatus);
	  
  }
  
  
 public String get_UserStatus(String username)
	  {
	 	By userslink =By.xpath(AvengerHomePropertyPage.homepg_userslink.getProperty());	
	 	pause(5000);
	 	click(usersearchboxlocator);
	 	enterText(usersearchboxlocator,  username.toLowerCase());
	 	clickEnter(usersearchboxlocator);
	 	pause(5000);
	 	By userstatus=By.xpath("//*[contains(text(),'"+username.toLowerCase()+"')]/../..//*[contains(@class,'status')]");
		return getText(userstatus);
		  
	  }
 
 public void assign_email()
 {
	 driver.findElement (By.xpath("//input[@name='email']")).sendKeys("jayanthi.mucharla@vbrick.com");
	 
 }
  public ArrayList<String> get_AllUsers() throws InterruptedException
	{

		ArrayList<String> usernames= new ArrayList<String>();
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		int j;
		
		for(j=0;j<=20;j++) {
		jse1.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(5000);
		}
		for (WebElement username : getAllWebElementValues(allusernamelist)) 
		{
			logger.info("User  Value is"+username.getText());
			usernames.add(username.getText());	
		}
		return usernames;
	}
  
  public ArrayList<String> get_AllCategories() throws InterruptedException
	{

		ArrayList<String> usercategories= new ArrayList<String>();
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		int j;
		
		for(j=0;j<=40;j++) {
		jse1.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(5000);
		}
		for (WebElement username : getAllWebElementValues(allcategorynameslist)) 
		{
			logger.info("User  Value is"+username.getText());
			usercategories.add(username.getText());	
		}
		return usercategories;
	}
  
  public ArrayList<String> get_AllAssignedgroups()
  {
	  ArrayList<String> assignedgrpnames= new ArrayList<String>();
		for (WebElement assignedgrpname : getAllWebElementValues(allassignedgrpnameslist)) 
		{
			logger.info("Group Value is"+assignedgrpname.getText());
			assignedgrpnames.add(assignedgrpname.getText());	
		}
		return assignedgrpnames;
  }
  
  public void get_AllAssignedgroupsandDelete()
  {
	  	List<WebElement> assignedgrpnamesdeletelocator =  driver.findElements(By.xpath("//div[@infinite-scroll='$ctrl.loadAssignedPage()']//span[@class='glyphicons remove_2']"));
	  	
	  	try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  	if(assignedgrpnamesdeletelocator.size() > 0) {
		for (WebElement assignedgrpnamedeleteloc : assignedgrpnamesdeletelocator) 
		{
			
			logger.info("Clicking on delete button for all the assigned groups");
			assignedgrpnamedeleteloc.click();
		}
		
		click(saveuserlocator);	

	  	}
	  	
	  	else {
	  		logger.info("No groups assigned for the user");
	  	}
	
  }
  
}