package com.vbrick.avenger.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.CreateAccountProperty;
import com.vbrick.avenger.ObjProperty.ManageAccountPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerManageAccountPage extends WebElements{

	private static Logger logger = Logger.getLogger(AvengerManageAccountPage.class); 
	
	
	
	By homeLinkLocator =By.linkText("");
	By controlPannelLinkLocator = By.linkText("");
	By moduleLinkLocator = By.linkText("");
	By moreLinkLocator = By.partialLinkText(ManageAccountPage.mangAccount_linkMoreLocator.getProperty());
	By textAccountLocator =By.xpath("");
	By addAccountLocator=By.xpath(ManageAccountPage.mngAccount_addAccountLocator.getProperty());
	By activeTextLocator = By.xpath(CreateAccountProperty.createAccount_ActiveTextLocator.getProperty());
	
	WebDriver driver;
	CustomReport customReport;
	private BasePage basePage;
	
	public AvengerManageAccountPage(WebDriver driver,
			  CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver=driver;

		this.customReport=customReport;
		this.basePage=basePage;
		// TODO Auto-generated constructor stub
	}	
	
	
	
		
	
	/**
	 *   This is used to check whether the Link is present on the page or not.
	 * @return : Boolean value whether the home link is present or not.
	 */
	public boolean homeLinkPresent()
	  {
		  return waitForElementPresent(homeLinkLocator);
	  }
	
	/** 
	 * This is used to check whether the Link is present on the page or not.
	 * @return: Boolean value whether the ControlPannel link is present or not.
	 */
	public boolean controlPannelLinkPresent()
	  {
		  return waitForElementPresent(controlPannelLinkLocator);
	  }
	
	/**
	 * This is used to check whether the Link is present on the page or not.
	 * @return:boolean value for the Modules Link is present
	 */
	public boolean modulesLinkPresent()
	 {
		 
		 return waitForElementPresent(moduleLinkLocator);
	 }
	 
	/**
	 *  This is used to check whether the Link is present on the page or not.
	 * @return:boolean value for the More Link is present
	 */
	public boolean moreLinkPresent()
	 {
		 
		 return waitForElementPresent(moreLinkLocator);
	 }
	
	/**
	 *   This will check for the presence of Text present on the page.
	 * @param sText : Text To be Verified
	 * @return : Boolean Value Whether the Text is present or not
	 */
	public boolean textAccountPresent(String sText)
	  {
		  return waitForTextPresent(textAccountLocator,  sText);
	  }
	
	
	public AvengerCreateChildAccountPage clickMore() throws InterruptedException {
		if (waitForElementPresent(addAccountLocator 
				)) {
			 click(moreLinkLocator);
			 logger.info("More Link is clicked");
			 customReport.reporter("Link is Clicked","");
			}
		return basePage.avengerCreateChildAccount(driver,  customReport, basePage);
	}
	
	
	 
	/**
	 * Click on the Add Account Button for the Creation of New Account.
	 * @return : Object for the Create Account Page
	 */
	public AvengerCreateChildAccountPage clickAddAccount()
	{
		click(addAccountLocator);
		logger.info("Add Account Button is Clicked");
		if(waitForTextPresent(activeTextLocator,  "Active"))
		{
			logger.info("The Active text is Present");
			customReport.reporter("The Active text is Present", "");
		}
		
	    return basePage.avengerCreateChildAccount(driver,  customReport, basePage);
	}
	  
}
