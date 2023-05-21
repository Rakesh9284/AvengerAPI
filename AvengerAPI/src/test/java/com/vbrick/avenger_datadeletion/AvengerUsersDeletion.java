package com.vbrick.avenger_datadeletion;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerEventsPage;
import com.vbrick.avenger.pageobjects.AvengerGroupsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerUserDashboardPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;

public class AvengerUsersDeletion extends TestBase {
	
	private static Logger logger = Logger.getLogger(AvengerUsersDeletion.class);
	private List<String> statusValue;
	private CustomReport customReport;
	private AvengerUserDashboardPage avengerUserDashBoardPage;
	private AvengerHomePage homepage; 
	private BasePage basePage;
	private AvengerLoginPage loginPage;
	
	@BeforeClass(alwaysRun = true)
	public void beforeClass() throws Exception {
		statusValue = new ArrayList<String>();
		customReport = new CustomReport();
	}

	@BeforeMethod(alwaysRun = true)
	@Parameters(value = { "sbrowser","sgrid"})
	public void setUP(@Optional(SBROWSER)String sbrowser,@Optional(SVERSION) String sgrid) throws MalformedURLException 
	{
		
	}
	
	String sUserName = deletionuser9;
	String sPassword=SANITYPASSWORD;
	
	By username = By.xpath("//div[@class='username']/input");
	By popuplocator = By.xpath("//*[contains(@class,'modal-footer')]//*[@type='submit' or text()='OK' or @ng-show='actionText']");
	By allusernamelist=By.xpath("//*[@class='flex-fill primary-cell table-cell']/../div[2]/span");
	By cancelpopuplocator=By.xpath("//*[@class='glyphicons remove_2']/..");
	
	//deletes users
		public void delete_Users(String username,AvengerHomePage homepage)
		{
			
			By userdeletedropdown=By.xpath("//*[contains(text(),'"+username+"')]/../../div[5]/div/div[2]/button");
			By userdeletebutton=By.xpath("//*[contains(text(),'"+username+"')]/../../div[5]/div/div[2]/button/../div/div/a[contains(@ng-click,'delete(user)')]");
			homepage.click(userdeletedropdown);
			homepage.click(userdeletebutton);
			logger.info("Clicked on delete button for user::"+username);
			
			
			if (homepage.isDisplayedWithoutException(popuplocator)) {
				homepage.waitForElementPresent(popuplocator);
				homepage.click(popuplocator);
				customReport.reporter("User Deleted Successfully", "");
				logger.info("User Successfully::"+username);
				homepage.pause(3000);
			}
			else {
				 homepage.click(cancelpopuplocator);
			     logger.info("Clicked on cancel popup button for group"+username);
			     homepage.pause(5000);
			   
		} 
		browserQuit(driver);
		}
	
	public void delete_AllUsers(String url)throws InterruptedException{
		WebDriver driver = initializeDriver(sbrowser, "");
		logger.info("The driver value is " + driver);
		basePage = new BasePage(driver, customReport, new BasePage());
		AvengerLoginPage loginPage = basePage.avengerLoginPage(driver, customReport, basePage);
		driver.manage().window().maximize();
		
		launchURL(surl,driver);
		homepage = loginPage.loginAs(sUserName, sPassword);
		homepage.clickSettingsLink();
		homepage.clickUsersLink();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(5000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(5000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(5000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		 Thread.sleep(5000);
		 jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		 Thread.sleep(5000);
		 jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		 Thread.sleep(5000);
		 jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		 Thread.sleep(5000);
		 jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		 Thread.sleep(5000);
		 jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		 Thread.sleep(5000);
		 jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		 Thread.sleep(5000);
		 jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		 Thread.sleep(5000);
		 jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		 Thread.sleep(5000);
		 jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			
		 ArrayList<String> allusernames= homepage.getAllWebElementValueslist(allusernamelist);
		 logger.info("Total NO.Of Users present on the list Before removing from the list :::::"+allusernames);
		 logger.info("Total NO.Of Users present on the list Before removing from the list :::::"+allusernames.size());
		 
		 String freuser1 = "api";
		 String freuser2 = "CGN1";
		 String freuser3 = "raj";
		 String freuser4 = "kal";
		 String freuser5 = "dav";
		 
		 String currentiuser = "";		 
		 Iterator<String> i = allusernames.iterator();
		 while (i.hasNext()) {
			 currentiuser = i.next();
			 
			   if (currentiuser.startsWith(freuser1) ||  currentiuser.startsWith(freuser2)
					   ||  currentiuser.startsWith(freuser3) || currentiuser.startsWith(freuser4)
					   ||  currentiuser.startsWith(freuser5))
			   {
				   i.remove();
			     
			   }
			 }
		 
		 logger.info("Total NO.Of Users present on the list after removing from the list :::::"+allusernames);
		 int totalusers=allusernames.size();
		 logger.info("Total NO.Of Users present on the list after removing from the list :::::"+totalusers);
		 	
			if (homepage.isDisplayedWithoutException(allusernamelist)) {
				for (int j = 1; j < totalusers; j++) {	
					delete_Users(allusernames.get(j),homepage);
				}
			}
			
			}	
	
	
	@Test(description="Delete Users",groups={USERS})
	public void deleteUsers_TC1()throws InterruptedException{
		delete_AllUsers(AutomationURL);
	}
		
	@AfterMethod(alwaysRun = true)
	public void browserClose(ITestResult result) {
		logger.info("In After method class");
		statusValue.clear();
	}
	
	
}
