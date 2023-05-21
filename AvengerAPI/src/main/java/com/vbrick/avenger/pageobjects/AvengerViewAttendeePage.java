package com.vbrick.avenger.pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerViewAttendeePage extends WebElements {
	
	private static Logger logger = Logger.getLogger(AvengerViewAttendeePage.class);
	 CustomReport customReport;
	 WebDriver driver;
	 private BasePage basePage;
	
	
public AvengerViewAttendeePage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver=driver;

		this.customReport = customReport;
		this.basePage=basePage;
	}


	  public boolean verify_OrderListAttendeeName(String xpathName,String username)
		{
		    By attendeenamelocator = By.xpath("//*[text()='"+xpathName+"']/../../../tbody/tr/th[1]");
			
		    List<WebElement> listOfElement = getAllWebElementValues(attendeenamelocator);
		    logger.info("The size for the element is"+listOfElement.size());
		    logger.info("The value of the Text is"+username);
		    logger.info("The value form the Page is"+listOfElement.get(0).getText());
			if(listOfElement.get(0).getText().contains(username))
				return true;
		 return false;
		}
  }

