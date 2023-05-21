package com.vbrick.avenger.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.RootAccountPropertyPage;
import com.vbrick.avenger.ObjProperty.RootAccountPropertyPage2;
import com.vbrick.avenger.dao.ManualRootAccountBeanPage;
import com.vbrick.avenger.dao.RootAccountBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerRootAccountPage extends WebElements{
	
private static Logger logger = Logger.getLogger(AvengerRootAccountPage.class);
	By accountName = By.name(RootAccountPropertyPage.rootaccountpg_accountname.getProperty());
	By hostName = By.name(RootAccountPropertyPage.rootaccountpg_accounthostname.getProperty());
	By username = By.name(RootAccountPropertyPage.rootaccountpg_username.getProperty());
	By password = By.name(RootAccountPropertyPage.rootaccountpg_password.getProperty());
	By confirmPassword = By.name(RootAccountPropertyPage.rootaccountpg_confirmpassword.getProperty());
	By emailaddress = By.name(RootAccountPropertyPage.rootaccountpg_emailaddress.getProperty());
	By emailserveraddress = By.name(RootAccountPropertyPage.rootaccountpg_emailserveraddress.getProperty());
	By port =By.name(RootAccountPropertyPage.rootaccountpg_port.getProperty());
	By emailserverusername = By.name(RootAccountPropertyPage.rootaccountpg_emailserverusername.getProperty());
	By emailserverpassword = By.name(RootAccountPropertyPage.rootaccountpg_emailserverpassword.getProperty());
	By entercontactinformationbuttonlocator=By.xpath(RootAccountPropertyPage.rootaccountpg_entercontactinformationbuttonlocator.getProperty());
	By triggeraddress=By.name(RootAccountPropertyPage.rootaccountpg_triggeraddresstextboxlocator.getProperty());
	By lastName=By.name(RootAccountPropertyPage.rootaccountpg_lastnametextboxlocator.getProperty());
    By firstName=By.xpath(RootAccountPropertyPage.rootaccountpg_firstnametextboxlocator.getProperty());
    By title=By.xpath(RootAccountPropertyPage.rootaccountpg_titletextboxlocator.getProperty());
	By phonenumber=By.xpath(RootAccountPropertyPage.rootaccountpg_phonenotextboxlocator.getProperty());
	By preferredlanguage=By.xpath(RootAccountPropertyPage2.rootaccountpg2_createrootaccountbuttonlocator.getProperty());
	

	
	private WebDriver driver;
	
	private CustomReport customReport;
	private BasePage basePage;
	
	public AvengerRootAccountPage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		
		super(driver);
		this.driver=driver;

		this.customReport = customReport;
		this.basePage=basePage;
	}
	
	
	
	
      public AvengerRootAccountPage2 addRootAccount(RootAccountBeanPage accountBeansPage)
      {
    	  logger.info("First line"+accountBeansPage.getAccountName());
    	    enterText(accountName, accountBeansPage.getAccountName());
      	  logger.info("seconf line");

    	    customReport.reporter("Enter text in Account name", accountBeansPage.getAccountName());
			
    	    enterText(hostName,  accountBeansPage.getAccountHostName());
    	    customReport.reporter("Enter text in Host name", accountBeansPage.getAccountHostName());

			enterText(username,  accountBeansPage.getUserName());
    	    customReport.reporter("Enter text in user name", accountBeansPage.getUserName());

			enterText(password, accountBeansPage.getPassword());
    	    customReport.reporter("Enter text in Password", accountBeansPage.getPassword());

			enterText(confirmPassword, accountBeansPage.getConfirmPassword());
    	    customReport.reporter("Enter text in Confirm Password ", accountBeansPage.getConfirmPassword());

			enterText(emailaddress, accountBeansPage.getEmailAddress());
    	    customReport.reporter("Enter text in Email Address", accountBeansPage.getEmailAddress());

			enterText(emailserveraddress, accountBeansPage.getEmailServerAddress());
    	    customReport.reporter("Enter text in Email Server Adress", accountBeansPage.getEmailServerAddress());

			enterText(port, accountBeansPage.getPort());
    	    customReport.reporter("Enter text in port", accountBeansPage.getPort());

			enterText(emailserverusername, accountBeansPage.getEmailServerUserName());
    	    customReport.reporter("Enter text in Email Server Username", accountBeansPage.getEmailServerUserName());

			enterText(emailserverpassword, accountBeansPage.getEmailServerPassword());
    	    customReport.reporter("Enter text in Email Server Password", accountBeansPage.getEmailServerPassword());

			enterText(triggeraddress,  accountBeansPage.getFromAddress());
    	    customReport.reporter("Enter text in Trigger", accountBeansPage.getFromAddress());

    	    enterText(lastName,  accountBeansPage.getLastName());
    	    customReport.reporter("Entered the last name",accountBeansPage.getLastName());
    	    
    	     click(entercontactinformationbuttonlocator);
			logger.info("Root Account Created");
            waitForElementPresent(preferredlanguage);
			
			return basePage.avengerRootAccountPage2(driver,  customReport, basePage);
      }
      
      public AvengerRootAccountPage2 addRootAccount(ManualRootAccountBeanPage accountBeansPage)
      {
    	  logger.info("First line"+accountBeansPage.getAccountName());
    	    enterText(accountName, accountBeansPage.getAccountName());
      	  logger.info("seconf line");

    	    customReport.reporter("Enter text in Account name", accountBeansPage.getAccountName());
			enterText(username,  accountBeansPage.getUserName());
    	    customReport.reporter("Enter text in user name", accountBeansPage.getUserName());

			enterText(password, accountBeansPage.getPassword());
    	    customReport.reporter("Enter text in Password", accountBeansPage.getPassword());

			enterText(confirmPassword, accountBeansPage.getConfirmPassword());
    	    customReport.reporter("Enter text in Confirm Password ", accountBeansPage.getConfirmPassword());

			enterText(emailaddress, accountBeansPage.getEmailAddress());
    	    customReport.reporter("Enter text in Email Address", accountBeansPage.getEmailAddress());

			enterText(emailserveraddress, accountBeansPage.getEmailServerAddress());
    	    customReport.reporter("Enter text in Email Server Adress", accountBeansPage.getEmailServerAddress());

			enterText(port, accountBeansPage.getPort());
    	    customReport.reporter("Enter text in port", accountBeansPage.getPort());

			enterText(emailserverusername, accountBeansPage.getEmailServerUserName());
    	    customReport.reporter("Enter text in Email Server Username", accountBeansPage.getEmailServerUserName());

			enterText(emailserverpassword, accountBeansPage.getEmailServerPassword());
    	    customReport.reporter("Enter text in Email Server Password", accountBeansPage.getEmailServerPassword());

			enterText(triggeraddress,  accountBeansPage.getFromAddress());
    	    customReport.reporter("Enter text in Trigger", accountBeansPage.getFromAddress());
    	  
    	    enterText(lastName,  accountBeansPage.getLastName());
    	    customReport.reporter("Entered the last name",accountBeansPage.getLastName());

			click(entercontactinformationbuttonlocator);
		     waitForElementPresent(preferredlanguage);
				
			return basePage.avengerRootAccountPage2(driver,  customReport, basePage);
      }

}
