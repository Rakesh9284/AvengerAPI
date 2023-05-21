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
import com.vbrick.avenger.pageobjects.AvengerDevicesPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;

public class AvengerDevicesDeletion extends TestBase {

	private static Logger logger = Logger.getLogger(AvengerDevicesDeletion.class);
	
	private List<String> statusValue;
	private CustomReport customReport;
	
	@BeforeClass(alwaysRun = true)
	public void beforeClass() throws Exception {
		statusValue = new ArrayList<String>();
		customReport = new CustomReport();
	}

	@BeforeMethod(alwaysRun = true)
	@Parameters(value = { "sbrowser","sgrid"})
	public void setUP(@Optional(SBROWSER) String sbrowser,@Optional(SVERSION) String sgrid) throws MalformedURLException {
		
	}
	
	String sUserName = deletionuser1;
	String sPassword = SANITYPASSWORD;
	//Deletes devices
	//By alldeviceslocator = By.xpath("//button[contains(@class,'btn-white')]");
	By alldevicesname=By.xpath("//*[@class='primary-cell table-cell']/a");
	By deletepopuplocator=By.xpath("//*[contains(@class,'modal-footer')]//*[@type='submit' or @ng-show='actionText']");
	By cancelpopuplocator=By.xpath("//*[@class='glyphicons remove_2']/..");
	By username = By.xpath("//div[@class='username']/input");
	By popupAcceptlocator=By.xpath("//*[contains(@class,'modal-footer')]//*[@type='submit']");
	
	public void delete_AllDevices(String devicename, AvengerHomePage homepage){
		By dropdownbuttonfordelete=By.xpath("//*[contains(text(),'"+devicename+"')]/../..//*[@class='dropdown']/button");
		By devicedeletebutton=By.xpath("//*[contains(text(),'"+devicename+"')]/../..//*[contains(@ng-click,'deleteDevice')]");
				
		homepage.click(dropdownbuttonfordelete);
		homepage.click(devicedeletebutton);
		
		logger.info("Clicked on delete button for device::"+devicename);
		if (homepage.isDisplayedWithoutException(deletepopuplocator)) {
			homepage.waitForElementPresent(deletepopuplocator);
			homepage.click(popupAcceptlocator);
			customReport.reporter("Deleted Successfully", "");
			logger.info("Deleted Successfully::"+devicename);
			homepage.pause(10000);
		}
		else {
				 homepage.click(cancelpopuplocator);
			     logger.info("Clicked on cancel popup button for device"+devicename);
			     homepage.pause(3000);
			    
		} 
		browserQuit(driver);
		}
	
		
		
		
		
		
	
	public void deleteDevices(String url){
		WebDriver driver = initializeDriver(sbrowser, "");
		logger.info("The driver value is " + driver);
		BasePage basePage = new BasePage(driver, customReport, new BasePage());
		AvengerLoginPage loginPage = basePage.avengerLoginPage(driver, customReport, basePage);
		driver.manage().window().maximize();
		
		launchURL(url,driver);
		AvengerHomePage homePage = loginPage.loginAs(sUserName, sPassword);
		homePage.clickSettingsLink();
		AvengerDevicesPage avengerDevicesPage = homePage.clickAllDevicesLink();
		ArrayList<String> alldevices = homePage.getAllWebElementValueslist(alldevicesname);
		//int totaldevices=homePage.elements(alldevicesname);
		int totaldevices=alldevices.size();
		logger.info("Total NO.Of devices on the page Before deletion:::::"+totaldevices);
		
		if (homePage.isDisplayedWithoutException(alldevicesname)) {
			for (int i = 1; i < totaldevices; i++) {	
				delete_AllDevices(alldevices.get(i),homePage);
			}
		
		}	
	
	}
	
		
	
	@Test(description="Delete Device",dependsOnGroups={AvengerPresentationProfileDeletions.PRESENTATIONPROFILES})
	public void AT_verify_Admin_DeleteDevice_TC1() {
		deleteDevices(AutomationURL);
	}
	
	
	@AfterMethod(alwaysRun = true)
	public void browserClose(ITestResult result) {
		logger.info("In After method class");
		statusValue.clear();

	}
	
}
