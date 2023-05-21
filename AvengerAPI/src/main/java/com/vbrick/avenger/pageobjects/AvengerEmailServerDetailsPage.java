package com.vbrick.avenger.pageobjects;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerEmailServerDetailsPropertyPage;
import com.vbrick.avenger.dao.AvengerEmailServerDetailsBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerEmailServerDetailsPage extends WebElements{

	private WebDriver driver;
	
	private static Logger logger = Logger.getLogger(AvengerEmailServerDetailsPage.class);
	private CustomReport customReport;
	private BasePage basePage;
	
	public AvengerEmailServerDetailsPage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		
		this.customReport= customReport;
		this.basePage=basePage;
	}
	
	By emailserveraddresslocator = By.name(AvengerEmailServerDetailsPropertyPage.emailserveraddresslocator.getProperty());
	By portlocator = By.name(AvengerEmailServerDetailsPropertyPage.portlocator.getProperty());
	By usernamelocator = By.name(AvengerEmailServerDetailsPropertyPage.emailserverusernamelocator.getProperty());
	By passwordlocator = By.name(AvengerEmailServerDetailsPropertyPage.emailserverpasswordlocator.getProperty());
	By emailfromaddresslocator = By.name(AvengerEmailServerDetailsPropertyPage.emailfromaddresslocator.getProperty());
	By savechangesbuttonlocator = By.xpath(AvengerEmailServerDetailsPropertyPage.savechangesbuttonlocator.getProperty());
	By cancelbuttonlocator = By.xpath(AvengerEmailServerDetailsPropertyPage.cancelbuttonlocator.getProperty());
	By savedsuccesslocator = By.xpath(AvengerEmailServerDetailsPropertyPage.savedsuccesslocator.getProperty());
	By emailserveraddresserror= By.xpath(AvengerEmailServerDetailsPropertyPage.emailserveraddresserrortext.getProperty());
	By porterror = By.xpath(AvengerEmailServerDetailsPropertyPage.porterrortext.getProperty());

	
	public HashMap<String, String> get_EmailServerDetails()
	{
		HashMap<String, String> emailserverdetails = new HashMap<String, String>();
		emailserverdetails.put("emailserveraddress",getAttribute(emailserveraddresslocator, "value"));
		emailserverdetails.put("port",getAttribute(portlocator, "value"));
		emailserverdetails.put("username",getAttribute(usernamelocator, "value"));
		emailserverdetails.put("password",getAttribute(passwordlocator, "value"));
		emailserverdetails.put("emailfromaddress",getAttribute(emailfromaddresslocator, "value"));
		return emailserverdetails;     
	}
	
	
	public void click_SaveChangesButton()
	{
		clickEnter(savechangesbuttonlocator);
		customReport.reporter("Save Changes Button Clicked", "");
		pause(5000);
		
	}
	
	public void click_SaveChangesPopUp()
	{
		By savedpopuplocator=By.xpath("//*[@ng-click='close()']");
		waitForElementPresent(savechangesbuttonlocator);
	}
	
	public String check_EditEmailServerDetails()
	{
		return 	getAttribute(savedsuccesslocator, "innerHTML").toUpperCase();
	}
	
	public String emailServerDetailsError()
	{
		return getText(emailserveraddresserror);
	}
	
	public String portError()
	{
		return getText(porterror);
	}
	
	public Map<String, String> getLabels()
	{
		Map<String, String> emailserverlabels = new HashMap<String, String>();
		By fromaddress = By.xpath("//*[@name='smtpFromAddress']/../../../../label");
		By username = By.xpath("//*[@name='smtpUserName']/../../../..//label");
		emailserverlabels.put("FromAddress", getText(fromaddress));
		emailserverlabels.put("UserName", getText(username));
		return emailserverlabels;
	}
	
	public Map<String,String> getErrorMessages()
	{
		Map<String, String> errormessages = new HashMap<String, String>();
		By serveraddresserror = By.xpath("//label[contains(@ng-show,'smtpHostname')][2]");
		By porterror = By.xpath("//label[contains(@ng-show,'smtpPort')][2]");
		By porterror1 = By.xpath("//label[contains(@ng-show,'smtpPort')][3]");
		By fromaddresserror = By.xpath("//label[contains(@ng-show,'smtpFromAddress')]");
		errormessages.put("EmailServerError", getText(serveraddresserror));
		errormessages.put("PortError", getText(porterror));
		errormessages.put("PortError1", getText(porterror1));
		errormessages.put("FromAddressError", getText(fromaddresserror));
		return errormessages;
	}
	
	public void enterEmailAddress(String address)
	{
		enterText(emailserveraddresslocator,  address);
	}
	
	public void enterPort(String port)
	{
		enterText(portlocator,  port);
	}
	
	public void enterFromAddress(String fromaddress)
	{
		enterText(emailfromaddresslocator,  fromaddress);
	}
	
}
