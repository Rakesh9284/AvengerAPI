package com.vbrick.avenger.pageobjects;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;
public class AvengerMessagePage extends WebElements{
private static Logger logger = Logger.getLogger(AvengerMessagePage.class);
    By userconfirmationurl=By.xpath("//h3[contains(text(),'This link is no longer valid.  Please log in again or reset your password.')]");	     
	WebDriver driver;
	CustomReport customReport;
	private BasePage basePage;
	
	public AvengerMessagePage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver=driver;

		this.customReport = customReport;
		this.basePage=basePage;
	}
	
   public String get_linknotavailabletext()
   {
	   return getText(userconfirmationurl);
   }

}
