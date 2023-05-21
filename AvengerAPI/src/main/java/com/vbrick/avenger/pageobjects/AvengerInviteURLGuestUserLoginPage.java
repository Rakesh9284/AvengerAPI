package com.vbrick.avenger.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerEventInviteURLGuestUserLoginPropertyPage;
import com.vbrick.avenger.dao.AvengerEventInviteURLGuestUserLoginBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerInviteURLGuestUserLoginPage extends WebElements{
private WebDriver driver;
	
private static Logger logger = Logger.getLogger(AvengerEventDetailsPage.class);
private CustomReport customReport;
private BasePage basePage; 

 By guestuserdiaplaynametextbox = By.id("name");
 By guestuseremailtextbox = By.id("email");
 By guestuserpasswordtextbox=By.id("password");
 By guestuserloginbutton=By.xpath(AvengerEventInviteURLGuestUserLoginPropertyPage.avengereventinviteurlguestuserloginpage_guestusersubmit.getProperty());
 By eventtitle=By.xpath(AvengerEventInviteURLGuestUserLoginPropertyPage.avengereventinviteurlguestuserloginpage_eventtitleonlogin.getProperty());
 By registeredtext=By.xpath(AvengerEventInviteURLGuestUserLoginPropertyPage.avengereventinviteurlguestuserloginpage_registeredtext.getProperty());
 By signinlink=By.xpath(AvengerEventInviteURLGuestUserLoginPropertyPage.avengereventinviteurlguestuserloginpage_SinginLink.getProperty());
 By licensedsigninlink=By.xpath(AvengerEventInviteURLGuestUserLoginPropertyPage.avengereventinviteurlguestuserloginpage_licensedSinginLink.getProperty());
	public AvengerInviteURLGuestUserLoginPage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		
		this.customReport= customReport;
		this.basePage = basePage;
	}
	
	public void setguestuserlogin(AvengerEventInviteURLGuestUserLoginBeanPage eventguestuserloginbeanpage){
		
		enterText(guestuserdiaplaynametextbox, eventguestuserloginbeanpage.getGuestusername());
		logger.info("Event Guest user name is"+eventguestuserloginbeanpage.getGuestusername());
		enterText(guestuseremailtextbox, eventguestuserloginbeanpage.getGuestemail());
		logger.info("Event Guest user email is"+eventguestuserloginbeanpage.getGuestemail());
		
	}
	
	public AvengerEventWebCastPage clicksubmit(){
		isEnabled(guestuserloginbutton);
		submit(guestuserloginbutton);
		logger.info("clicked on submit for event guest user");
		return basePage.avengerEventWebCastPage(driver, customReport, basePage);
	}
	
	public boolean verify_nametextbox(){
		return isDisplayedWithoutException(guestuserdiaplaynametextbox);
	}
	
	public boolean verify_passwordtextbox(){
		return isDisplayedWithoutException(guestuserpasswordtextbox);
	}
	
	public String verify_eventtitlepresentonguestlogin(){
		return getAttribute(eventtitle);
	}
	
	public boolean verify_submitbuttonforenable(){
		return isEnabled(guestuserloginbutton);
	}
	
	public String verify_registeredSignintext(){
		return getAttribute(registeredtext);
	}
	
	public AvengerLoginPage click_SigninLink()
	{
		  click(signinlink);
		  logger.info("Clicked on Guest Signin here Link");
		  return basePage.avengerLoginPage(driver, customReport, basePage);
		 }
	
	public void enterPassword(String stext){
		enterText(guestuserpasswordtextbox, stext);
	}
	
	public String verify_guestloginerrormsg(){
		By attendeeremovederrormsg=By.xpath("//*[contains(@ng-show,'AttendeeRemoved')]");
		 return getText(attendeeremovederrormsg);
	}
	public AvengerLoginPage click_licensedSigninLink()
	{
		  click(licensedsigninlink);
		  logger.info("Clicked on Guest Signin here Link");
		  return basePage.avengerLoginPage(driver, customReport, basePage);
		
		 }
}
