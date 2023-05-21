package com.vbrick.avenger.funUtil;

import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.vbrick.Exception.ReasonsTimeOutException;
import com.vbrick.ngwebdriver.NgWebDriver;

public abstract class WaitForElements extends RolesAccess{

	public static Logger logger = Logger.getLogger(WaitForElements.class);
	public WebDriverWait driverWait;
	int poolFlag=3;
	public long WAIT = 60;
	WebDriver driver;
	String sPageTitle=null;
	
	public NgWebDriver ngWebDriver;
	
	public WaitForElements(WebDriver driver)
	{
		this.driver=driver;
		sPageTitle=driver.getTitle();
		driverWait=new WebDriverWait(driver, WAIT);
		ngWebDriver = new NgWebDriver((JavascriptExecutor)driver);
	}
	
	public boolean locatorsVisibilityAsPerRoles(By locator) {
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		boolean flag = false;
		int i = 0;
		try {
			while (flag == false && i <= poolFlag) {
				if (driverWait.until(
						ExpectedConditions.visibilityOfElementLocated(locator))
						.isDisplayed()) {
					flag = true;
				} else {
					flag = false;
				}
				i++;
			}
		}
		catch (org.openqa.selenium.TimeoutException e) {
			flag=false;
		}
		return flag;
}
	
	
	
	public boolean wait( By locator)
	{
		ngWebDriver.waitForAngularRequestsToFinish();
		int i=0;
		boolean displayed=false;
		//logger.info("find element started-----------------"+locator+"-------------------------------------");
		while (displayed==false && i <= poolFlag) {
			try {
			//	logger.info("The value of i value"+i);
			if (driverWait.until(
						ExpectedConditions.visibilityOfElementLocated(locator))
						.isDisplayed()) {
					displayed=true;
					break;
				}
			} catch (org.openqa.selenium.TimeoutException exception) {
				displayed = false;
				logger.info("---------Inside TimeOutException------"+locator);
			}
			catch (org.openqa.selenium.NoSuchElementException noexception) {
				displayed = false;
				logger.info("---------Inside NoSuchElementException------" +locator);
			}
			catch(StaleElementReferenceException staleexception)
			{
				logger.info("Stale Element Exception Block");
				displayed = false;
			}
			//logger.info("The value of i"+i);
			i++;
		}
	//	logger.info("find element Ended--------------------------"+locator+"-----------------------------"+displayed);
		return displayed;
	}

	/**
	 * Waits for the visibility of elements Present on the screen
	 * 
	 * @param locator
	 *            : Locator name
	 * @param wait
	 *            :  object
	 * @param sPageTitle
	 *            : Provide information on which page the object is not present.
	 * @return : true if object is Present
	 */
	public boolean waitForElementPresent(By locator) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		boolean flag = false;
		//try {
		/*	while (flag == false && i <= poolFlag) {
				if (driverWait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed()) {
					logger.info("locator value is"+locator);
					logger.info("The value of the driver wait is"+driverWait.until(
						ExpectedConditions.visibilityOfElementLocated(locator))
						.findElements(locator));
					// logger.info("The Object is PRESENT on the page with Page title :"+sPageTitle+" with locator");
					// locator);
					flag = true;
				} else {
					logger.info("The Object is NOT present on the page with Page title :"
							+ sPageTitle + " with locator" + locator);
					flag = false;
				}
				logger.info("the value of i is"+i);
				logger.info("THe Pool Flag values are "+poolFlag);
				i++;
			}
	     */	
		flag=wait( locator);
	//	logger.info("Flag value is"+flag);
		
			if(!flag)
			{
				Reporter.log("<font color='red'>"+"1.Automation Team will Address it:TimeOutException"+"<br>"+"2.The Object Properties have been Modified"+"<br>"+"In Page"
						+ sPageTitle + " with locator" + locator+"</font>");
				logger.error("************The Element is not visible after wait for 120 sec:*************"
						+ locator.toString());
				  throw new ReasonsTimeOutException("");
			}
				return flag;
			/*}
		catch(StaleElementReferenceException staleexception)
		{
			logger.info("Stale Element Exception Block");
			flag = false;
		}
		catch(NoSuchElementException nosuchelement)
		{
			flag = false;
			Reporter.log("<font color='red'>"+"1.Automation Team will address it:NoSuchElementException"+"<br>"+"2.The Object Properties have been Modified"+"<br>"+"In Page"
					+ sPageTitle + " with locator" + locator+"</font>");
			logger.error("************The Element is not visible after wait for 120 sec:*************"
					+ locator.toString());
		}
		catch (org.openqa.selenium.TimeoutException e) {

			Reporter.log("<font color='red'>"+"1.Automation Team will Address it:TimeOutException"+"<br>"+"2.The Object Properties have been Modified"+"<br>"+"In Page"
					+ sPageTitle + " with locator" + locator+"</font>");
			logger.error("************The Element is not visible after wait for 120 sec:*************"
					+ locator.toString());
			  throw new ReasonsTimeOutException("");
			}
		return flag;
*/		}
		
	
	/**
	 * Waits for the visibility of elements Present on the screen
	 * 
	 * @param locator
	 *            : Locator name
	 * @param wait
	 *            :  object
	 * @param sPageTitle
	 *            : Provide information on which page the object is not present.
	 * @return : true if object is Present
	 * @throws InterruptedException 
	 */
	public String waitForElementPresentVisible(By locator)  {
		
		String flag = "disabled";
		int i = 0;
		try {
			while (flag.equals("disabled") && i <= poolFlag) {
				if (driverWait.until(
						ExpectedConditions.visibilityOfElementLocated(locator))
						.isEnabled()) {
					// logger.info("The Object is PRESENT on the page with Page title :"+sPageTitle+" with locator"+
					// locator);
					flag="enabled";
					logger.info("flag value is"+flag);
					logger.info("value of i is"+i);
								} else {
					logger.info("The Object is NOT present on the page with Page title :"
							+ sPageTitle + " with locator" + locator);
					flag = "disabled";
				}
				i++;
			}
		} catch(StaleElementReferenceException staleexception)
		{
			logger.info("Stale Element Exception Block");
			flag ="disabled";
			Reporter.log("<font color='red'>"+"1.Automation Team will address it:StaleElementException"+"<br>"+"2.The Object Properties have been Modified"+"<br>"+"In Page"
					+ sPageTitle + " with locator" + locator+"</font>");
			logger.error("************The Element is not visible after wait for 120 sec:*************"
					+ locator.toString());
	
		}
		catch(NoSuchElementException nosuchelement)
		{
			flag ="disabled";
			Reporter.log("<font color='red'>"+"1.Automation Team will address it:NoSuchElementException"+"<br>"+"2.The Object Properties have been Modified"+"<br>"+"In Page"
					+ sPageTitle + " with locator" + locator+"</font>");
			logger.error("************The Element is not visible after wait for 120 sec:*************"
					+ locator.toString());
		}
		return flag;
		}	

	/**
	 * Waits for the elements Present on the screen
	 * 
	 * @param locator
	 *            : Locator name
	 * @param wait
	 *            :  object
	 * @param sPageTitle
	 *            : Provide information on which page the object is not present.
	 * @return true if the webElement is enabled
	 */
	public boolean waitForElementEnable(By locator) {
		boolean flag = false;
		int i=0;
		try {
		while(flag==false && i<=poolFlag){
		if (driverWait.until(ExpectedConditions.visibilityOfElementLocated(locator))
				.isEnabled()) {
		//	logger.info("The Object is ENABLED on the page with Page title :"+sPageTitle+" with locator"+ locator);
			flag = true;
		} else {
			logger.info("The Object is NOT ENABLED on the page with Page title :"+sPageTitle+" with locator"+ locator);
			flag = false;
		}
	
	i++;
	}
	
		}catch (org.openqa.selenium.TimeoutException e) {
			Reporter.log("The Element is not visible after wait for 120 sec:TimeoutException"
					+ sPageTitle + " with locator" + locator);
			logger.error("**************The Element is not visible after wait for 120 sec:**************"
					+ locator.toString());
			  throw new ReasonsTimeOutException("");
			}
		return flag;
	}

	/**
	 * Waits for the elements Present on the screen
	 * 
	 * @param locator
	 *            : Locator name
	 * @param wait
	 *            :  object
	 * @param sPageTitle
	 *            : Provide information on which page the object is not present.
	 * @return : True if the text is present on the screen.
	 */
	public boolean waitForTextPresent(By locator, 
			String sText) {
		logger.info("The Object to be located in on PAGE :" + sPageTitle);
		boolean flag = false;
		int i=0;
		try
		{
		while(flag==false && i<=poolFlag){
		if (driverWait.until(ExpectedConditions.textToBePresentInElement(locator,
				sText))) {
			logger.info("The TEXT IS PRESENT on the page :"+sPageTitle+" with text"+ sText);
			flag = true;
		} else {
			logger.info("The TEXT IS NOT PRESENT on the page :"+sPageTitle+" with text"+ sText);
			flag = false;

		}
		i++;
		}
		}catch(StaleElementReferenceException staleexception)
		{
			logger.info("Stale Element Exception Block");
			Reporter.log("<font color='red'>"+"1.Automation Team will address it:StaleElementException"+"<br>"+"2.The Object Properties have been Modified"+"<br>"+"In Page"
					+ sPageTitle + " with locator" + locator+"</font>");
			logger.error("************The Element is not visible after wait for 120 sec:*************"
					+ locator.toString());
	
		}
		catch(NoSuchElementException nosuchelement)
		{
			Reporter.log("<font color='red'>"+"1.Automation Team will address it:NoSuchElementException"+"<br>"+"2.The Object Properties have been Modified"+"<br>"+"In Page"
					+ sPageTitle + " with locator" + locator+"</font>");
			logger.error("************The Element is not visible after wait for 120 sec:*************"
					+ locator.toString());
		}
		catch (org.openqa.selenium.TimeoutException e) {
			Reporter.log("The Element is not visible after wait for 120 sec:"
					+ sPageTitle + " with locator" + locator);
			logger.error("***********The Element is not visible after wait for 120 sec:**************"
					+ locator.toString());
			  throw new ReasonsTimeOutException("");
			}
		return flag;
	}
	
	public boolean waitForElements(By locator)
	{
		boolean flag=false;
		if(driverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator)) != null)
		{
			flag=true;
		}
		else
			flag=false;
		return flag;
	}
	
	public boolean waitforWebElement( WebElement element)
	{
		boolean flag=false;
		if(driverWait.until(ExpectedConditions.elementToBeClickable(element))!=null)
		{
			flag=true;
		}
		else
			flag=false;
		return flag;
	}
	public boolean waitForElement(By locator)
	{
		boolean flag=false;
		if(driverWait.until(ExpectedConditions.presenceOfElementLocated(locator)) != null)
		{
			flag=true;
		}
		else
			flag=false;
		return flag;
	}
	
	public void waitForElementtobeClickable(By locator)
	{
		 driverWait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	
}
