
package com.vbrick.avenger.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerForgotPasswordPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerHomePropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerLoginPropertyPage;
import com.vbrick.avenger.dao.AddUserBeanPage;
import com.vbrick.avenger.dao.CreatePasswordBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerLoginPage extends WebElements {
	
	private static Logger logger = Logger.getLogger(AvengerLoginPage.class);
	 CustomReport customReport;
	 WebDriver driver;
	 private BasePage basePage;
	
	 public AvengerLoginPage(WebDriver driver, CustomReport customReport, BasePage basePage) {
		super(driver);
		this.driver = driver;
		this.customReport=customReport;
		this.basePage=basePage;
	}
	
	By usernameLocator = By.xpath(AvengerLoginPropertyPage.loginpg_usernameLocator.getProperty());
	By passwordLocator = By.xpath(AvengerLoginPropertyPage.loginpg_passwordLocator.getProperty());
	By loginButtonLocator = By.xpath(AvengerLoginPropertyPage.loginpg_loginButtonLocator.getProperty());
	By addVideoButtonLocator = By.xpath(AvengerHomePropertyPage.homepg_addVideoLocator.getProperty());
    By errorTextLocator= By.xpath(AvengerLoginPropertyPage.loginpg_errormsg.getProperty());
    By suspendusererrortext=By.xpath(AvengerLoginPropertyPage.loginpg_errormg_suspenduser.getProperty());
    By system_inaccesibleerrortext= By.xpath(AvengerLoginPropertyPage.loginpg_errormg_systeminaccesibleerrortext.getProperty());
    By incorrectusernamepassworderrortext= By.xpath(AvengerLoginPropertyPage.loginpg_errormg_incorrectusernamepassworderrortext.getProperty());
    By lockedouterrortext= By.xpath(AvengerLoginPropertyPage.loginpg_errormg_lockedouterrortext.getProperty());
    By linknotvaliderrortext=By.xpath(AvengerLoginPropertyPage.loginpg_errormg_linknotvaliderrortext.getProperty());	     
    By loggedouterrortext=By.xpath(AvengerLoginPropertyPage.loginpg_errormg_loggedouterrortext.getProperty());
    By forgotpasswordLocator=By.xpath(AvengerLoginPropertyPage.loginpg_forgotpasswordLocator.getProperty());
    By forgotpassworduserNameLocator=By.name(AvengerLoginPropertyPage.forgotpg_forgotpassworduserNameLocator.getProperty());
    By userslink =By.xpath(AvengerHomePropertyPage.homepg_userslink.getProperty());	
    By securityanswerlocator=By.xpath(AvengerForgotPasswordPropertyPage.avengerforgotpasswordpage_securityanswerlocator.getProperty());
	By logoutlocator = By.xpath(AvengerHomePropertyPage.homepg_logoutlocator.getProperty());    
	By lockoutperioderrormessagelocator=By.xpath(AvengerLoginPropertyPage.loginpage_errormsg_userlocked.getProperty());
    By returnloginbuttonlocator=By.xpath(AvengerLoginPropertyPage.loginpage_returnloginbuton.getProperty());
    
    public String helpTextuserName()
	{
    	String sflag=null;
    	if(waitForElementEnable(passwordLocator))
    	{
    		sflag=driver.findElement(usernameLocator).getAttribute("placeholder");
    	}
    	return sflag;
    	
    }
    
	public String helpTextPassword()
	{
		String sflag=null;
    	if(waitForElementEnable(usernameLocator))
    	{
    		sflag=driver.findElement(passwordLocator).getAttribute("placeholder");
    	}
    	return sflag;
        		
		}
	
	/**
	 *  typeUsernamePresent is used to check Login text box is available on the web page.
	 * @return : Checks for the UserName textbox is displayed on the Screen or not.
	 */
	
	public boolean typeUsernamePresent() {
		
		return waitForElementPresent(usernameLocator);
	}

	/**
	 * typePasswordPresent is used to check Password text box is available on the web page.
	 * @return : Checks whether Password field is displayed on the Screen or not.
	 */
	public boolean typePasswordPresent() {
		
		logger.info("driver value is"+driver);
		logger.info("Locator  value is"+passwordLocator);
			
		return waitForElementPresent(passwordLocator);
	}

	/**
	 * submitButtonEnable is used to check whether submit button is enabled or not. 
	 * @return : Checks for the Enable of the login button on the Page.
	 */
	public boolean submitButtonEnable() {
		boolean status=false;
		if(waitForElementEnable(loginButtonLocator))
		{
		 status=isEnabled(loginButtonLocator);
		}
		return status;
	}
	
	/**
	 * Check for the Forgot Password Link present on the Page
	 * @return : booelan value wheather link is present or not
	 */
	
	public String forgotPasswordLinkPresent()
	{
		return String.valueOf(elements(forgotpasswordLocator));
	}

	
	public void click_forgotPasswordlink(AddUserBeanPage adduserbeanpage)
	{
		click(forgotpasswordLocator);
		By forgotpasswordtext=By.xpath("//input[@ng-model='username']");
		By submitbuttonlocator=By.xpath("//*[contains(text(),'Submit')]");
		waitForElementPresent(forgotpasswordtext);
		enterText(forgotpasswordtext,  adduserbeanpage.getUsername());
		click(submitbuttonlocator);
		
	}
	
	
	/**
	 * Checks for signIn button available on the Login Page
	 * @return boolean value true if value is present else false
	 */
	
	public boolean signInButtonPresent()
	{
		boolean status=false;
		if(waitForElementPresent(loginButtonLocator))
		{
		 status=isEnabled(loginButtonLocator);
		}
		return status;
	}
	
	public String signInText()
	{
		return getText(loginButtonLocator);
	}
	
	/**
	 * typeUsername is used to pass data to the web page.
	 * @param sUsername
	 * @return:Current object for the Avenger Login Page.
	 */
	
	public AvengerLoginPage typeUsername(String sUsername) {
		 
		logger.info("User Name Entered is"+sUsername);
		enterText(usernameLocator,  sUsername);
		customReport.reporter("User name has been Entered","");
		logger.info("The value is Entered");
		return this;
	}
	
	public Boolean verifyTextUserName(String stext)
	{
		if(getAttribute(usernameLocator,"value").equals(stext))
		{
			customReport.reporter("User is able to Input the Username","");
			logger.info("value of the textusername is"+getAttribute(usernameLocator,"value"));
			return getAttribute(usernameLocator,"value").equals(stext);
		}
			else
			{
				customReport.reporter("User is not able to Input the Username","");
				return false; 
	
			}
	}
	
	public Boolean verifyTextPassword(String stext)
	{
		if(getAttribute(passwordLocator,"value").equals(stext))
		{
			customReport.reporter("User is able to Input the Password","");
			return getAttribute(passwordLocator,"value").equals(stext);
		}
			else
			{
				customReport.reporter("User is not able to Input the Password","");
				return false; 
	
			}
	}
	
	
	
	/**
	 * typePassword is used to pass data to the web page. 
	 * @param sPassword
	 * @return : Current Object for the Avenger Login Page.
	 */
	public AvengerLoginPage typePassword(String sPassword) {
		logger.info("The value is of Password is--------->"+sPassword);
		enterText(passwordLocator,  sPassword);
		customReport.reporter("Password has been entered","");
		return this;
	}
	
	
	
	/**
	 *  submitLogin method is used to click on submit button on web page and  check for grid Button is available on web page
	 * @return: Reference for the Avenger Home Page.
	 */
	public AvengerHomePage submitLogin(String sUsername,String sPassword) {
		
		click(loginButtonLocator);
		logger.info("Submit button Clicked");
		customReport.reporter("Submit button Clicked","");
		customReport.reporter("Login SucessFully", "");
		return basePage.avengerHomePage(driver,  customReport, basePage);
	}
	
	/**
	 * loginAs method is used to pass Username and Password to the webpage
	 * @param sUsername
	 * @param sPassword
	 * @return : Avenger Home Object will be returned.
	 */
	public AvengerHomePage loginAs(String sUsername,String sPassword) {
		pause(5000);
		if(typePasswordPresent() && typeUsernamePresent())
		{
			logger.info("Enter the text for the UserId");
			typeUsername(sUsername);
			typePassword(sPassword);
		}
		return submitLogin(sUsername,sPassword);
	}

	public void switchFrameforEmbeddedCode(String sUserName,String sPassword ) {
		By frame=By.tagName("iframe");
		switchFrame(frame);
		InvalidloginAs(sUserName,sPassword);
		frameDefaultContent();
		switchFrame(frame);
	}
	
	/**
	 *  InvalidloginAs method is used when invalid login of a user.
	 * @param sUsername:Provide invalid username
	 * @param sPassword:Provide invalid password
	 */
	public void InvalidloginAs(String sUsername,String sPassword) {
		if(typePasswordPresent() && typeUsernamePresent())
		{
			logger.info("Enter the text for the UserId");
			typeUsername(sUsername);
			logger.info("The UserName value is-------"+sUsername);
			logger.info("The UserName value is-------"+sPassword);
			
			logger.info("Entered Text in username textbox"+ sUsername);
			typePassword(sPassword);
			customReport.reporter("sPassword Entered :",sPassword);
		}
		click(loginButtonLocator);

			}

	/**
	 *  It returns the error text for the suspended User 
	 * @return
	 */
	
	public String getSuspendeduserstatus()
	{
		logger.info("I am in getsuspended user status");
		logger.info("message for suspended user is"+getText(suspendusererrortext));
		if(waitForElementPresent(suspendusererrortext))
				{
			return getText(suspendusererrortext);
		}
			else
			{
				return "Suspended User Error Text not found";
	}
	}
	
	/**
	 * It returns the error text for System inaccessible 
	 * @return
	 */
	public String getSysteminaccessibletatus()
	{
		if(waitForElementPresent(system_inaccesibleerrortext))
			{
			return getText(system_inaccesibleerrortext);
		}
			else
			{
				return "System is not accesible at this time. Text is not found";
	    }
	}
	
	/**
	 *  It returns error text for Incorrect username and password.
	 * @return
	 */
	public String getIncorrectusernamepassworderrortext()
	{
		logger.info(" iam in incorrect username password block");
		if(waitForElementPresent(incorrectusernamepassworderrortext))
			{
		 logger.info("Incorrect username password error text is" +getText(incorrectusernamepassworderrortext));
		 return getText(incorrectusernamepassworderrortext);
		}
			else
			{
				return "Incorrect Username or Password Text is not found";
	    }
	}
	 
	public String getloggedouterrortext()
	{
		 
		return String.valueOf(elements(usernameLocator));
		
		
	}
	
	/**
	 *  invalidloginattempts is used to when user logs with incorrect password account should be locked with an error message.  
	 * @param accountBeansFactory 
	 * @return : it returns the error message  on the page as the return value.
	 */
   public String invalidloginattempts(AddUserBeanPage adduserbeanpage,int value)
   {
	   String sflag=null;
	   for(int i=0;i<=value;i++)
		{
		  enterText(usernameLocator,  adduserbeanpage.getUsername());
		  enterText(passwordLocator,  "value"+adduserbeanpage.getUsername());
	      click(loginButtonLocator);
	      waitForElementPresent(usernameLocator);
		}
	 sflag=getText(lockedouterrortext);
	logger.info("Incorrect login error message"+ sflag);	 
	   
	return sflag;
   }
	
	   
	
	   /**
	    *  it returns error text for Invalid login
	    * @return
	    */
	
	public String err_invalid_login() {
	    	return getText(errorTextLocator);
	      }
	
	/**
	 *  CreatedUserLogin method is used to login with new created user after adding.
	 * @param adduserbeanpage
	 * @param createpasswordbeanpage
	 * @return
	 */
	
	public AvengerHomePage CreatedUserLogin(AddUserBeanPage adduserbeanpage,CreatePasswordBeanPage createpasswordbeanpage)
	{
        customReport.reporter("Login with Created User Credentials",""); 
        logger.info("Create password value is"+createpasswordbeanpage.getPassword());
        
      return loginAs(adduserbeanpage.getUsername(),createpasswordbeanpage.getPassword());
		
	}
	
	public String get_linknotavailableerrortext() {
		return getText(linknotvaliderrortext);
	}
	
	 public AvengerForgotPasswordPage click_forgotpassword()
	 {
		 waitForElementPresent(forgotpasswordLocator);
		 customReport.reporter("Forgotpassword link found", "");
		 logger.info("Forgot password link found");
		 click(forgotpasswordLocator);
		 customReport.reporter("Forgot password Displayed sucessfully", "");
		 waitForElementPresent(forgotpassworduserNameLocator);
		 return basePage.avengerForgotPasswordPage(driver,  customReport, basePage);
	 }
	 
	 public void openingANewTab()
	 {
		openANewTab();
		customReport.reporter("Opened a new tab", "");
		
	 }
	 
	 public void enterTextandSubmit(AddUserBeanPage adduserbeanpage)
	 {
		 By forgotpasswordtext=By.xpath("//input[@ng-model='username']");
		 By submitbuttonlocator=By.xpath("//*[contains(text(),'Submit')]");
		 enterText(forgotpasswordtext,  adduserbeanpage.getUsername());
		 customReport.reporter("Entered usrername", getAttribute(forgotpasswordtext, "value"));
		 click(submitbuttonlocator);
		 customReport.reporter("Clicked on SubmitButton", "");
	 }
	 
	 public String getWindowHandleofCurrentPage()
	 {
		 return driver.getWindowHandle(); 
	 }
	
	 public void newWindowControls()
	 {
		 for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
			}
	 }
	 
	 public String adminDisableForgotPasswordError()
	 {
		 By forgotpassworderrortext = By.xpath("//div[@ng-switch='lastError']/p");
		 return getText(forgotpassworderrortext);
	 }
	 
	 public AvengerLoginPage getDriverContorl(WebDriver driver,  CustomReport customReport)
		{
			return basePage.avengerLoginPage(driver,  customReport, basePage);
		}
	 
	 /**
		 * loginAs method is used to pass Username and Password to the webpage and not verifying logout button.
		 * @param sUsername
		 * @param sPassword
		 * @return : Avenger Home Object will be returned.
		 */
	 public AvengerHomePage loginForVideoAutoPlay(String sUsername,String sPassword) {
		 typeUsername(sUsername);
		 typePassword(sPassword);
		 click(loginButtonLocator);
		 return basePage.avengerHomePage(driver,  customReport, basePage);
	 }
	 public String verify_BackgroundImageGuestLogin()
		{
		By videobackgroundimage=By.xpath("//div[contains(@class,'system-login-shell')]");
		pause(5000);
		logger.info(getAttribute(videobackgroundimage,"class"));
		return getAttribute(videobackgroundimage,"class");
		}
	 
	 public void click_returnloginbutton(){
		 click(returnloginbuttonlocator);
		 logger.info("Clicked on return login button");
		 pause(3000);
	 }
}