package com.vbrick.avenger_datadeletion;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
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
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;

public class AvengerEventsDelete extends TestBase {

	private static Logger logger = Logger.getLogger(AvengerEventsDelete.class);
	private List<String> statusValue;
	private CustomReport customReport;
	
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
	
	String sUserName = "deletionuser3";
	String sPassword=SANITYPASSWORD;
	
	//Deletes events
	By username = By.xpath("//div[@class='username']/input");
	By deleteeventlocator = By.xpath("//*[@ng-click='deleteEvent()']");
	By eventtitletextboxloactor = By.xpath("//input[@name='title']");
	By deletecategorypopuplocator = By.xpath("//*[contains(@class,'modal-footer')]//*[@type='submit' or text()='OK' or @ng-show='actionText']");
	By joinwebcastbutton=By.xpath("//*[contains(@ng-show,'canJoin')]");
	By eventnotstartedlocator=By.xpath("//*[contains(@class,'event-not-started')]");
	By allevents =	By.xpath("//*[contains(@class,'fc-title')]");
	By eventtab=By.xpath("//*[contains(@ui-sref,'portal.scheduledEvents') and @ui-sref-active='active']");
	By todaybutton=By.xpath("//span[contains(text(),'today')]");
	
	public void delete_AllEvents(String eventname,AvengerHomePage homepage) {
		By eventtitleloactor = By.xpath("//span[contains(text(),'" +eventname+"')]");
		//homepage.waitForElement(eventtitleloactor);
		homepage.click(eventtitleloactor);
		logger.info("Clicked on event:::"+eventname);
		homepage.pause(10000);
		if (homepage.isDisplayedWithoutException(eventtitletextboxloactor)) {
			logger.info("Event title textbox is visible on the page");
			homepage.waitForElement(deleteeventlocator);
			logger.info("Delete event button is visible on the page");
			homepage.click(deleteeventlocator);
			logger.info("Clicked on Delete button");
			homepage.pause(6000);
			homepage.click(deletecategorypopuplocator);
			homepage.pause(6000);
			logger.info("Clicked on Delete popuplocator");
			logger.info("Event Deleted with title is :::: "+ eventname);
			homepage.pause(6000);
			if (homepage.isDisplayedWithoutException(allevents)) {
				logger.info("Events are visible on the page");
			}
			else {
				logger.info("Events are not visible on the page");
				/*homepage.navigateBackward();
				logger.info("Navigated Back to event page");*/
				homepage.click(eventtab);
				logger.info("Event tab is clicked");
				homepage.isDisplayedWithoutException(allevents);
				logger.info("Events are visible on the page");
			}
			
		}	
		 else {
			logger.info("Event is still broadcasting:::"+eventname);
			if (homepage.isDisplayedWithoutException(joinwebcastbutton)) {
				logger.info("Join webcast is displayed on the page :::"+eventname);
				homepage.click(eventtab);
				logger.info("Event tab is clicked");
				homepage.waitForElement(allevents);
				logger.info("Event are visible on the page");
			}
			else {
				homepage.isDisplayedWithoutException(eventnotstartedlocator);
				logger.info("Event admin is not broad casting yet for event:::"+eventname);
				homepage.click(eventtab);
				logger.info("Event tab is clicked");
				homepage.acceptAlert();
				logger.info("Alert Handled:::");
				homepage.waitForElement(allevents);
				logger.info("Event are visible on the page");
			}
		}
		
	}
	
	public void deleteEvent(String url){
		WebDriver driver = initializeDriver(sbrowser, "");
		logger.info("The driver value is " + driver);
		BasePage basePage = new BasePage(driver, customReport, new BasePage());
		AvengerLoginPage loginPage = basePage.avengerLoginPage(driver, customReport, basePage);
		driver.manage().window().maximize();
		
		launchURL(url,driver);
		AvengerHomePage homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
    	AvengerEventsPage avengereventspage = avengerdashboardpage.click_EventsTab();
		if (avengereventspage.wait(allevents)) {
			ArrayList<String>  alleventtitle = avengereventspage.getAllWebElementValueslist(allevents); //12
			
			logger.info("The list is" + alleventtitle.size());
			int i=0;
			while (alleventtitle.size() > 0) {
			//	for (int i = 1; i < alleventtitle.size(); i++) {
				ArrayList<String>  nonEmptyEvent =avengereventspage.getAllWebElementValueslist_withoutEmpty(allevents);
					try {
							logger.info("Before " + nonEmptyEvent.size());
							delete_AllEvents((nonEmptyEvent.get(1)), homePage);
							logger.info("After"+ nonEmptyEvent.size());
							logger.info("@@@--Events Deleted are ----"+i);
						i++;
							
					}catch (Exception notvisible) {
							if (loginPage.isDisplayedWithoutException(username)) {
								loginPage.loginAs(sUserName, sPassword);
								homePage.avengerDashboardPage();
								avengerdashboardpage.click_EventsTab();
							} else
								avengerdashboardpage.click_EventsTab();
						}
					}
				} 
			else
				browserQuit(driver);
			

	}
	
	
	 
	
	@Test(description="Delete Live Event",groups={WEBCASTS},enabled=AccountFlag)
	public void AT_verify_Admin_DeleteLiveEvent_TC1() {
		
		logger.info("-----------------------------------"+AutomationURL);
		deleteEvent(AutomationURL);
		logger.info("-----------------------END------------");

		
	}
	
	@AfterMethod(alwaysRun = true)
	public void browserClose(ITestResult result) {
		logger.info("In After method class");
		statusValue.clear();
	}
}
