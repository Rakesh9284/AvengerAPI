package com.vbrick.avenger.pageobjects;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerHomePropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerUserPasswordParamatersPropertyPage;
import com.vbrick.avenger.dao.UserPasswordParametersBean;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;
public class AvengerUserPasswordParametersPage extends WebElements{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private static Logger logger = Logger.getLogger(AvengerUserPasswordParametersPage.class);
   
   By basic= By.xpath(AvengerUserPasswordParamatersPropertyPage.avengeruserpasswordparameters_basic.getProperty());
   By medium= By.xpath(AvengerUserPasswordParamatersPropertyPage.avengeruserpasswordparameters_medium.getProperty());
   By strong= By.xpath(AvengerUserPasswordParamatersPropertyPage.avengeruserpasswordparameters_strong.getProperty());
   By custom= By.xpath(AvengerUserPasswordParamatersPropertyPage.avengeruserpasswordparameters_custom.getProperty());
   By savebuttonlocator=By.xpath(AvengerUserPasswordParamatersPropertyPage.avengeruserpasswordparameters_savebuttonlocator.getProperty());
   By minnoofcharacters=By.xpath(AvengerUserPasswordParamatersPropertyPage.avengeruserpasswordparameters_minnofcharacterslocator.getProperty());
   By uppercaseletter=By.xpath(AvengerUserPasswordParamatersPropertyPage.avengeruserpasswordparameters_uppercaseletterlocator.getProperty());
   By lowercaseletter=By.xpath(AvengerUserPasswordParamatersPropertyPage.avengeruserpasswordparameters_lowercaseletterlocator.getProperty());
   By numbers=By.xpath(AvengerUserPasswordParamatersPropertyPage.avengeruserpasswordparameters_numberslocator.getProperty());
   By specialcharacter=By.xpath(AvengerUserPasswordParamatersPropertyPage.avengeruserpasswordparameters_specialcharacterlocator.getProperty());
   By invalidloginattemptsallowed=By.xpath(AvengerUserPasswordParamatersPropertyPage.avengeruserpasswordparameters_invalidloginattemptsallowed.getProperty());
   By resetpasswordcheckboxlocator=By.xpath(AvengerUserPasswordParamatersPropertyPage.avengeruserpasswordparameters_resetpasswordlocator.getProperty());
   By basicdescription=By.xpath(AvengerUserPasswordParamatersPropertyPage.avengeruserpasswordparameters_basicdescription.getProperty());
   By basictitle=By.xpath(AvengerUserPasswordParamatersPropertyPage.avengeruserpasswordparameters_basictitle.getProperty());
   By mediumdescription=By.xpath(AvengerUserPasswordParamatersPropertyPage.avengeruserpasswordparameters_mediumdescription.getProperty());
   By strongdescription=By.xpath(AvengerUserPasswordParamatersPropertyPage.avengeruserpasswordparameters_strongdescription.getProperty());
   By customdescription=By.xpath(AvengerUserPasswordParamatersPropertyPage.avengeruserpasswordparameters_customdescription.getProperty());
   By maximumlogonattempts=By.xpath(AvengerUserPasswordParamatersPropertyPage.avengeruserpasswordparameters_maximumlogonattempts.getProperty());
   By invalidlogonattempts=By.xpath(AvengerUserPasswordParamatersPropertyPage.avengeruserpasswordparameters_invalidlogonattempts.getProperty());
   By sessioninactivitytimeoutlocator=By.xpath(AvengerUserPasswordParamatersPropertyPage.avengeruserpasswordparameters_sessioninactivitytimeoutlocator.getProperty());     
   By sessioninactivitytimeouterrortextlocator=By.xpath(AvengerUserPasswordParamatersPropertyPage.avengeruserpasswordparameters_sessioninactivitytimeouterrortextlocator.getProperty());
   By forgotpasswordcheckboxlocator = By.name(AvengerUserPasswordParamatersPropertyPage.avengeruserpasswordparameters_forgotpasswordcheckboxlocator.getProperty());
   By enableCheckboxLocator = By.xpath(AvengerUserPasswordParamatersPropertyPage.avengeruserpasswordparameters_enableCheckbox.getProperty()); 
   By enableGuestAccessCheckboxLocator=By.xpath(AvengerUserPasswordParamatersPropertyPage.avengeruserpasswordparameters_enableGuestAccessCheckboxLocator.getProperty());
   By getGuestAccessURL=By.xpath(AvengerUserPasswordParamatersPropertyPage.avengeruserpasswordparameters_getGuestAccessURL.getProperty());
   By lockoutPeriodLocator=By.xpath(AvengerUserPasswordParamatersPropertyPage.avengeruserpasswordparameters_lockoutperiodcheckboxlocator.getProperty());
   By invalidAttemptsperiodminutesLocator=By.xpath(AvengerUserPasswordParamatersPropertyPage.avengeruserpasswordparameters_invalidattempetsperiod.getProperty());
   By lockoutperiodlocator=By.xpath(AvengerUserPasswordParamatersPropertyPage.avengeruserpasswordparameters_lockoutperiodlocator.getProperty());
   By cancelButtonlocator=By.xpath(AvengerUserPasswordParamatersPropertyPage.avengerpasswordparameters_cancelbuttonlocator.getProperty());
   By userdropdown=By.xpath(AvengerHomePropertyPage.homepg_userslink.getProperty());
   //By allowsharingofmetadataprivatevideoslocator=By.xpath("//*[contains(@name,'sharingAndEmbeddingSettingsEnabled')]");
   By resetButtonLocator=By.xpath(AvengerUserPasswordParamatersPropertyPage.avengerpasswordparameters_Resetbuttonlocator.getProperty());
   By LegalHoldLocator=By.xpath("//*[contains(@ng-model,'securitySettings.enableLegalHold')]");
   
   private WebDriver driver;
	
	private CustomReport customReport;
	private BasePage basePage;
	
	public AvengerUserPasswordParametersPage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver=driver;

		this.customReport = customReport;
		this.basePage=basePage;
		// TODO Auto-generated constructor stub
	}
	
	
	public void clickBasic_complexity()  
	{
	    click(basic);
	    customReport.reporter("Basic Complexity Radio Button is Clicked",""); 
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  
	    click_saveButton();
	    
	}
	
	public void clickMedium_complexity() 
	{
	    click(medium);
	    customReport.reporter("Medium Complexity Radio Button is Clicked",""); 
	    click_saveButton();
	}
	
	public void clickStrong_complexity() 
	{
	    logger.info(" I am in click strong complexity");
		click(strong);
	    customReport.reporter("Strong Complexity Radio Button is Clicked","");
	    click_saveButton();
	}
	
	public void clickCustom_complexityasChecked() 
	{
		check_Checkbox(custom);
	    enterText(minnoofcharacters,  "14");
	    check_Checkbox(lowercaseletter);
	    check_Checkbox(uppercaseletter);
	    check_Checkbox(numbers);
	    check_Checkbox(specialcharacter);
	    customReport.reporter("Custom Complexity Radio Button is Clicked",""); 
	    click_saveButton();
	}
	
	public void clickCustom_complexity() 
	{
		check_Checkbox(custom);
	    customReport.reporter("Custom Complexity Radio Button is Clicked",""); 
	    click_saveButton();
	}
	
	
	public String minnofcharactersetting()
	{
		return getAttribute(minnoofcharacters, "value");
	}
	
	public void enter_minofcharactersetting(UserPasswordParametersBean bean)
	{
		enterText(minnoofcharacters, bean.getMinimumnoofcharacters());
	    click_saveButton();
		
	}
	
	public void clickCustom_complexityasUnchecked()  
	{
		check_Checkbox(custom);
	    enterText(minnoofcharacters,  "14");
	    uncheck_Checkbox(lowercaseletter);
	    uncheck_Checkbox(uppercaseletter);
	    uncheck_Checkbox(numbers);
	    uncheck_Checkbox(specialcharacter);
	    customReport.reporter("Custom Complexity Radio Button is Clicked",""); 
	    click_saveButton();
	}
	
	public void  click_saveButton()
	{
		submit(savebuttonlocator);
		waitForElementPresent(forgotpasswordcheckboxlocator);
		 customReport.reporter("Save Button is Clicked",""); 
	}
	
	public void invalidlogin_attemptsallowed()
	{
		 click(basic);
		 customReport.reporter("Basic Complexity Radio Button is Clicked",""); 
		 enterText(invalidloginattemptsallowed,  "3");
		 customReport.reporter("Invalid login attempts allowed is Entered","3");
		 check_Checkbox(resetpasswordcheckboxlocator);
		 customReport.reporter("Enabled Check box is clicked","");
		 click_saveButton();
			
			
	}
	
	public void enter_Invalidloginattempts(String value)
	{
		 enterText(invalidloginattemptsallowed,  value);
	}
	public void enter_sessionInactivityTimeout(UserPasswordParametersBean userpasswordparametersbean)
	{
		enterText(sessioninactivitytimeoutlocator,  userpasswordparametersbean.getSessioninactivitytimeout());
		 click_saveButton();	
	}
	

	 public void get_basicDescription()
	  {
		logger.info(" I am in basic description");
		 List<WebElement> elements=	 driver.findElements(By.tagName("div"));
		 logger.info(" Element size is"+elements.size());
		 for(int i=0;i<elements.size();i++)
		 {
			 logger.info("@@@@@@@@@@The text value is"+elements.get(i).getText());
		 }
	  }
	
	
	
  public String get_basicTitle()
  {

	  logger.info("basic Title is"+getText(basictitle));
	  return getText(basictitle);
  }

  public String get_mediumDescription()
  {
	  return getText(mediumdescription); 
  }
  
  public String get_strongDescription()
  {
	  return getText(strongdescription); 
  }
  
  public String get_customDescription()
  {
	  return getText(customdescription); 
	    
  }
  public String get_Invalidlogonattemptstext()
  {
	  return getText(invalidlogonattempts);
  }
  
  
  public boolean checkBasic_selected()
  {
	  logger.info("basic selection"+ driver.findElement(basic).isSelected());
    if(isSelected(basic)==true)
    {
	  customReport.reporter("The preset Basic is saved and the setting radio button is still selected","");
    }
    else
    	customReport.reporter("Basic is Not Selected as the Password Complexity","");
    	
	  return isSelected(basic); 
  }

  public boolean checkMedium_selected()
  {
	  logger.info("medium selection"+ driver.findElement(basic).isSelected());
    if(isSelected(medium)==true)
    {
	  customReport.reporter("The preset Medium is saved and the setting radio button is still selected","");
    }
    else
    	customReport.reporter("Medium is Not Selected as the Password Complexity","");
    	
	  return isSelected(medium); 
  }

  public boolean checkStrong_selected()
  {
	  logger.info("Strong selection"+ driver.findElement(basic).isSelected());
    if(isSelected(strong)==true)
    {
	  customReport.reporter("The preset Strong is saved and the setting radio button is still selected","");
    }
    else
    	 customReport.reporter("Strong is Not Selected as the Password Complexity","");
    	
	  return isSelected(strong); 
  }

  public boolean checkCustom_selected()
  {
	  logger.info("Custom selection"+ driver.findElement(custom).isSelected());
    if(isSelected(custom)==true)
    {
	  customReport.reporter("The preset Custom is saved and the setting radio button is still selected","");
    }
    else
    	customReport.reporter("Custom is Not Selected as the Password Complexity","");
    	
	  return isSelected(custom); 
  }
  
  
  public String get_minCharacters()
  {
	  logger.info(" I am in min Characters");
	  return getText(minnoofcharacters);
  }
  
 
  public boolean checklowercaseletter_selected()
  {
	  logger.info("Check Lower case letter selected"+ driver.findElement(lowercaseletter).isSelected());
    
	  if(isSelected(lowercaseletter)==true)
    {
	  customReport.reporter("lower case letter rule is Turned On By default","");
    }
    else
    	 customReport.reporter("Lower Case letter Turned Off By default","");
       return isSelected(lowercaseletter); 
  }
  
  public boolean checkuppercaseletter_selected()
  {
	  logger.info("Check Lower case letter selected"+ driver.findElement(uppercaseletter).isSelected());
    if(isSelected(uppercaseletter)==true)
    {
	  customReport.reporter("Upper case letter rule is Turned On By default","");
    }
    else
    {
    	 customReport.reporter("Upper Case letter Turned Off By default","");
    }
    	 return isSelected(uppercaseletter); 
  }
  
  public boolean checknumber_selected()
  {
	  logger.info("Check Lower case letter selected"+ driver.findElement(numbers).isSelected());
    if(isSelected(numbers)==true)
    {
	  customReport.reporter("Number rule is Turned On By default","");
    }
    else
    {
    	 customReport.reporter("Number Turned Off By default","");
    }
    	 return isSelected(numbers); 
  }
  
  public boolean checkspecialcharacters_selected()
  {
	  logger.info("Check Lower case letter selected"+ driver.findElement(specialcharacter).isSelected());
    if(isSelected(specialcharacter)==true)
    {
	  customReport.reporter("Special Characters rule is Turned On By default","");
    }
    else
    {
    	 customReport.reporter("Special Characters Turned Off By default","");
    }
    	 return isSelected(specialcharacter); 
  }

  public void enter_maxlogonattemptsallowed(String value)
  {
	  enterText(maximumlogonattempts, value);
	  customReport.reporter("The maximum logon attempts allowed is",getAttribute(maximumlogonattempts, "Value"));
	  click_saveButton();
  }
  
  public boolean VerifyLegalHoldCheckBox(){
		
		return isSelected(LegalHoldLocator);
	}
	public void checkLegalHold() {
		 check_Checkbox(LegalHoldLocator);
		 customReport.reporter("Checking legal hold button ","");
	}
	public void un_checkLegalHold() {
		uncheck_Checkbox(LegalHoldLocator);
		 customReport.reporter("Unchecking legal hold button","");
	}
  
  public boolean verify_maxlogonattemptsallowed()
  {
	  return locatorsVisibilityAsPerRoles(maximumlogonattempts);
  }
  public String get_maxlogonattemptsallowed()
  {
	  return getAttribute(maximumlogonattempts, "value");
	
  }
  
  public String get_sessionInactivityTimeoutErrorText(UserPasswordParametersBean userpasswordparametersbean,String text)
  {
	  
	  By sessioninactivelocator=By.xpath("//label[contains(text(),'"+text+"')]");
	  
	  enterText(sessioninactivitytimeoutlocator,  userpasswordparametersbean.getSessioninactivitytimeout());
	  return getText(sessioninactivelocator);  
  }
  
  public String get_sessionInactivityTimeout()
  {
	  logger.info("Session Inactivity Timeout Value is"+getAttribute(sessioninactivitytimeoutlocator, "value"));
	  return getAttribute(sessioninactivitytimeoutlocator, "value");
  }
  
  public boolean check_ForgotPasswordEnabled()
  {
	 return isSelected(forgotpasswordcheckboxlocator);
  }

  public void uncheck_ForgotPasswordCheckBox()
  {
	  uncheck_Checkbox(forgotpasswordcheckboxlocator);
  }

  public void check_ForgotPasswordCheckBox()
  {
	  check_Checkbox(forgotpasswordcheckboxlocator);
  }
  
  public void switchTab(String windowhandle)
  {
	  EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
	  eventFiringWebDriver.getKeyboard().pressKey(Keys.CONTROL);
	  eventFiringWebDriver.getKeyboard().pressKey(Keys.TAB);
	  eventFiringWebDriver.getKeyboard().releaseKey(Keys.CONTROL);
	  driver.switchTo().window(windowhandle);
	  
  }

 
 public void clearwebelementtext(By locator)
 {
	 clearWebElementTextusingbackspace(locator, getAttribute(locator, "value").length());
 }
  public Map<String, String> Verify_Labels() {
	
	By basicminlocator=By.xpath("//input[@value='basic']/../../div[1]/div");
	By  lowercaseletters=By.xpath("//input[contains(@ng-model, 'requireLowercaseLetter')]/..");
	By uppercaseletters=By.xpath("//input[contains(@ng-model, 'requireUppercaseLetter')]/..");
	By mustcontainsnumbers=By.xpath("//input[contains(@ng-model, 'requireNumber')]/..");
	By specialcharacters=By.xpath("//input[contains(@ng-model, 'requireSpecialCharacter')]/..");
	By enabledlocator=By.xpath("//input[contains(@ng-model, 'enableForgotPassword')]/..");
	
	Map<String, String> labelsmap=new HashMap<String, String>();
	
	labelsmap.put("Minimum8Characters", getText(basicminlocator) );
	labelsmap.put("MustContainLowerCaseLetters", getText(lowercaseletters));
	labelsmap.put("MustContainUpperCaseLetters", getText(uppercaseletters));
	labelsmap.put("MustContainNumbers", getText(mustcontainsnumbers));
	labelsmap.put("MustContainSpecialCharacters", getText(specialcharacters));
	labelsmap.put("Enabled", getText(enabledlocator));
	return labelsmap;
}

  public void check_SingleSignOn() {

		check_Checkbox(enableCheckboxLocator);
		waitForElementEnable(enableCheckboxLocator);
		customReport.reporter("Single Sign On Checkbox is Enabled ","");
	}
  public void uncheck_SingleSignOn() {

		uncheck_Checkbox(enableCheckboxLocator);
		customReport.reporter("Single Sign On Checkbox is Unchecked ","");
	}
  public void check_GuestAccess() {

		check_Checkbox(enableGuestAccessCheckboxLocator);
		waitForElementEnable(enableGuestAccessCheckboxLocator);
		customReport.reporter("Guest Access Checkbox is Enabled ","");
	}
  public void uncheck_GuestAccess() {

	  uncheck_Checkbox(enableGuestAccessCheckboxLocator);
	  customReport.reporter("Guest Access Checkbox is Disabled ","");
	}
  
  public String getGuestAccessURL(){
	  return getText(getGuestAccessURL);
  }
  
  public boolean verify_GuestAccessURL(){
	  return isDisplayedWithoutException(getGuestAccessURL);
  }
  
  public boolean verify_GuestAccessCheckBox(){
	  
	 return isSelected(enableCheckboxLocator);
  }
  
  public AvengerHomePage click_cancelButton(){
		click(cancelButtonlocator);
		pause(3000);
		waitForElementPresent(userdropdown);
		logger.info("User Dropdown is displayed");
		return basePage.avengerHomePage(driver, customReport, basePage);
	}
  
  public void enter_lockoutperiodtime(String value){
		enterText(lockoutperiodlocator, value);
		logger.info("enter text in lockout period filed "+value);
	}
  
	public void enter_invalidattemptperiodminutes(String value){
		enterText(invalidAttemptsperiodminutesLocator,value);
		logger.info("enter tex in invalid attempts period minutes "+value);
	}
	public String getinvalidAttemptperiodDefaultValue(){
	return	getAttribute(invalidAttemptsperiodminutesLocator,"placeholder");
	}
	
	
	public void enterUserAgreementMessage(String launguage,UserPasswordParametersBean userpasswordparameterbean){
		By userAgrementMessageLocator=By.xpath("//*[contains(text(),'"+launguage+"')]/..//textarea");
		enterText(userAgrementMessageLocator, userpasswordparameterbean.getUserAgreementMessage());;
	}
	
	public void click_useragreementResetbutton(){
			
		click(resetButtonLocator);
	}
	
	public void clear_PrivacyMessageData(String launguage) {
		By userAgrementMessageLocator=By.xpath("//*[contains(text(),'"+launguage+"')]/..//textarea");
		clearWebElementText(userAgrementMessageLocator);
	}
	
/*	public void checkAllowSharingofmetadataprivateVideos()
	{
		check_Checkbox(allowsharingofmetadataprivatevideoslocator);
	}
	
	public void unCheckAllowSharingofmetadataprivateVideos()
	{
		uncheck_Checkbox(allowsharingofmetadataprivatevideoslocator);
	}*/
}