package com.vbrick.avenger_datadeletion;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerZonesPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;

public class AvengerZonesDeletions extends TestBase{

	private static Logger logger = Logger.getLogger(AvengerZonesDeletions.class);
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
	
	//deletes zones
	String sUserName = deletionuser4;
	String sPassword=SANITYPASSWORD;
	
	By deletePopuplocator=By.xpath("//*[contains(@class,'modal-footer')]//*[@type='submit' or text()='OK' or @ng-show='actionText']");
	By popupAcceptlocator=By.xpath("//*[contains(@class,'modal-footer')]//*[@type='submit']");
	By allzoneslocator = By.xpath("//*[contains(@ng-click,'removeZone')]");
	By firstzonelocator=By.xpath("(//*[contains(@ng-click,'removeZone')])[1]");
	
	public void delete_AllZonesSingleClick(AvengerHomePage homePage,String url){
		homePage.clickSettingsLink();
		homePage.clickZonesTab();
		
		int value=homePage.elements(allzoneslocator);
		for (int i = 1; i <= value; i++) {
			homePage.waitForElementPresent(firstzonelocator);
			logger.info("First Presentation profile is visible on the page");
			homePage.click(firstzonelocator);
			logger.info("Successfully clicked on presenation profile delete button");
			homePage.waitForElementPresent(deletePopuplocator);
			homePage.click(popupAcceptlocator);
			customReport.reporter("Deleted Successfully", "");
			homePage.pause(2000);
			if (homePage.isDisplayedWithoutException(firstzonelocator)) {
				customReport.reporter("Verified deleted Zone and it was successfull", "");
			}
			else {
				customReport.reporter("There was some issue deleting zone", "");
				launchURL(url);
				homePage.clickZonesTab();
			}
		}
	}
	
	public void delete_Zones(String url){
		WebDriver driver = initializeDriver(sbrowser, "");
		logger.info("The driver value is " + driver);
		BasePage basePage = new BasePage(driver, customReport, new BasePage());
		AvengerLoginPage loginPage = basePage.avengerLoginPage(driver, customReport, basePage);
		driver.manage().window().maximize();
		launchURL(url,driver);
		
		AvengerHomePage homePage = loginPage.loginAs(sUserName, sPassword);
		logger.info("Total NO.Of Zones present on the page Before deletion:::::"+homePage.elements(allzoneslocator));
		delete_AllZonesSingleClick(homePage,url);
		logger.info("Total NO.Of Zones present on the page After deletion:::::"+homePage.elements(allzoneslocator));
		browserQuit(driver);
	}
	
	@Test(description="Delete Zones",groups={ZONELOGIC})
	public void DeleteAllZonesInSingleClick_TC1() {
		delete_Zones(AutomationURL);
	}
	
	
	@AfterMethod(alwaysRun = true)
	public void browserClose(ITestResult result) {
		logger.info("In After method class");
		statusValue.clear();
		
	}
	
}
