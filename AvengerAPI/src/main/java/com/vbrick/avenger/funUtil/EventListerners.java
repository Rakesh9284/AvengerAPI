package com.vbrick.avenger.funUtil;

import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.Reporter;

import com.vbrick.Exception.ReasonsTimeOutException;

public class EventListerners implements WebDriverEventListener{
	
	private static Logger logger = Logger.getLogger(WebElements.class);

	/**
	 * This Method is for textBox 
	 */
	public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {
		
		arg0.getAttribute("value");
		logger.info("The Value entered in the textbox"+arg0.getAttribute("value"));
		if(arg0.getAttribute("value")==null)
		{
		logger.error("Value not entered in text box"+arg1.getTitle()+arg0.toString());
		Reporter.log("<font color='red'>"+"1.Automation Team will Address it:TimeOutException"+"<br>"+"2.The Object Properties have been Modified"+"<br>"+"In Page"
				+ arg1.getTitle() + " with locator" + arg0.toString()+"</font>");
		logger.error("####Element "+arg0.toString());
		throw new ReasonsTimeOutException("");
		}
	}

	public void afterClickOn(WebElement arg0, WebDriver arg1) {
		
		try{
			logger.info("The element that is clicked "+arg0.getText()+"---"+ arg0.isDisplayed());
		}
		catch(Exception e)
		{
			logger.info("exception$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		}
	}

	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		
		logger.info("The element is Found is "+arg0);
	}

	public void afterNavigateBack(WebDriver driver) {
		logger.info("After clicking back "+driver.getCurrentUrl() );
	}

	public void afterNavigateForward(WebDriver arg0) {
		logger.info("After clicking Forward "+arg0.getCurrentUrl());
		
	}

	public void afterNavigateTo(String arg0, WebDriver arg1) {
		logger.info("Navigate to "+arg0);
		
	}

	public void afterScript(String arg0, WebDriver arg1) {
		logger.info(" Ran Script "+arg0);
		
	}

	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1) {
		arg0.getAttribute("value");
		if(arg0.getAttribute("value")==null)
		{
		logger.error("Value not entered in text box"+arg1.getTitle()+arg0.toString());
		Reporter.log("<font color='red'>"+"1.Automation Team will Address it:TimeOutException"+"<br>"+"2.The Object Properties have been Modified"+"<br>"+"In Page"
				+ arg1.getTitle() + " with locator" + arg0.toString()+"</font>");
		throw new ReasonsTimeOutException("");
		}
		}

	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
		logger.info("Before clicking the element found is "+"----"+ arg0.isDisplayed());
	}

	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		try{
			logger.info("I am into before find element");
			logger.info("Before find by "+arg0.toString());
		}	catch(NoSuchElementException nosuchelement)
		{
			logger.info("I am into before findby error message+++++++++++++++++++++++++");
			Reporter.log("<font color='red'>"+"1.Automation Team will address it:NoSuchElementException"+"<br>"+"2.The Object Properties have been Modified"+"<br>"+"In Page"
					+ arg2.getTitle() + " with locator" + arg1.toString()+"</font>");
			logger.error("************The Element is not visible after wait for 120 sec:*************"
					+ arg1.toString());
			throw new ReasonsTimeOutException("");
		}
	}

	public void beforeNavigateBack(WebDriver driver) {
		logger.info("Before clicking back "+driver.getCurrentUrl());
		
	}

	public void beforeNavigateForward(WebDriver arg0) {
		logger.info("Before Navigate Forward "+arg0.getCurrentUrl());
	}

	public void beforeNavigateTo(String arg0, WebDriver arg1) {
		logger.info("Before Navigate to "+arg0);
	}

	public void beforeScript(String arg0, WebDriver arg1) {
		logger.info("Before Script started "+arg0.toString());
	}

	public void onException(Throwable arg0, WebDriver arg1) {
		logger.info(arg0.getClass().getName());
	}

	public void afterChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
