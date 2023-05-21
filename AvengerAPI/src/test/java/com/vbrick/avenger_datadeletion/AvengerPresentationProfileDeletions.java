package com.vbrick.avenger_datadeletion;

import java.net.MalformedURLException;
import java.util.ArrayList;
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
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerPresentationProfilesPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;

public class AvengerPresentationProfileDeletions extends TestBase  {
	
	private static Logger logger = Logger.getLogger(AvengerPresentationProfileDeletions.class);
	private String myBrowser;
	private List<String> statusValue;
	private CustomReport customReport;
	private BasePage basePage;
	private AvengerPresentationProfilesPage avengerPresentationProfilesPage;
	
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
	
	//deletes presentation profiles
	String sUserName =deletionuser2;
	String sPassword=SANITYPASSWORD;
	
	By username = By.xpath("//div[@class='username']/input");
	By firstPresenationDevicelocator=By.xpath("(//*[contains(@ng-click,'removePresentationProfile')])[1]");
	By deletePopuplocator=By.xpath("//*[contains(@class,'modal-footer')]//*[@type='submit' or @ng-show='actionText']");
	By allpresenatationslocator=By.xpath("//*[contains(@ng-click,'removePresentationProfile')]");
	By popupAcceptlocator=By.xpath("//*[contains(@class,'modal-footer')]//*[@type='submit']");
	By allpresenationprofilename=By.xpath("//table/tbody/tr/th/a");
	By cancelpopuplocator=By.xpath("//*[@class='glyphicons remove_2']/..");
	By allpresentationprofileslist =By.xpath("//table/tbody/tr/th/a");
	
	public void delete_Presentations(String presenationname,AvengerHomePage homepage){
		By presentationdeletebutton=By.xpath("//*[contains(text(),'"+presenationname+"')]/../../th[2]/div/button");
		homepage.waitForElementPresent(presentationdeletebutton);
		
		homepage.click(presentationdeletebutton);
		logger.info("Clicked on delete button for presenation profile::"+presenationname);
		if (homepage.isDisplayedWithoutException(deletePopuplocator)) {
			homepage.waitForElementPresent(deletePopuplocator);
			homepage.click(popupAcceptlocator);
			customReport.reporter("Deleted Successfully", "");
			logger.info("Deleted Successfully::"+presenationname);
			homepage.pause(3000);
		}
		else {
				 homepage.click(cancelpopuplocator);
			     logger.info("Clicked on cancel popup button for presenation profile"+presenationname);
			     homepage.pause(5000);
			    
		} 
		browserQuit(driver);
		}
	
	
	public void delete_AllPresentationProfiles(String url)throws InterruptedException{
		WebDriver driver = initializeDriver(sbrowser, "");
		logger.info("The driver value is " + driver);
		basePage = new BasePage(driver, customReport, new BasePage());
		AvengerLoginPage loginPage = basePage.avengerLoginPage(driver, customReport, basePage);
		driver.manage().window().maximize();
		
		launchURL(url,driver);
		AvengerHomePage homePage = loginPage.loginAs(sUserName, sPassword);
		homePage.clickSettingsLink();
		avengerPresentationProfilesPage=homePage.click_PresentationProfilesTab();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(5000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(5000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(5000);
		
		ArrayList<String> allpresenationtitle= homePage.getAllWebElementValueslist(allpresentationprofileslist);
		int totalprofiles=homePage.elements(allpresentationprofileslist);
		logger.info("Total NO.Of presentation profiles on the page Before deletion:::::"+totalprofiles);
		
		if (homePage.isDisplayedWithoutException(allpresentationprofileslist)) {
			for (int i = 1; i < totalprofiles; i++) {	
				delete_Presentations(allpresenationtitle.get(i),homePage);
			}
		
		}	
	
	}

	@Test(description="Delete Presenatation Profile",groups={PRESENTATIONPROFILES})
	public void deletePresenationProfiles_TC1()throws InterruptedException{
		delete_AllPresentationProfiles(AutomationURL);
	}
	
	
	@AfterMethod(alwaysRun = true)
	public void browserClose(ITestResult result) {
		logger.info("In After method class");
		statusValue.clear();

	}
	

}
