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
import com.vbrick.avenger.pageobjects.AvengerDevicesPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;

public class AvengerDmeDevicesDeletion extends TestBase {

	private static Logger logger = Logger.getLogger(AvengerDmeDevicesDeletion.class);
	
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
	
	//Deletes DME devices
	String sUserName = deletionuser8;
	String sPassword = SANITYPASSWORD;

	//By alldeviceslocator = By.xpath("//button[contains(@class,'btn-white')]");
	By alldevicesname=By.xpath("//*[contains(@class,'device-title')]/span");
	By deletepopuploctor=By.xpath("//*[contains(@class,'modal-footer')]//*[@type='submit' or @ng-show='actionText']");
	By cancelpopuplocator=By.xpath("//*[@class='glyphicons remove_2']/..");
	By username = By.xpath("//div[@class='username']/input");
	
	public void delete_AllDevices(String devicename, AvengerHomePage homepage,String url){
		By dropdownbuttonfordelete=By.xpath("//*[contains(text(),'"+devicename+"')]/../..//*[@class='dropdown']/button");
		By devicedeletebutton=By.xpath("//*[contains(text(),'"+devicename+"')]/../..//*[contains(@ng-click,'deleteDevice')]");
		homepage.pause(10000);
		if (!homepage.isDisplayedWithoutException(alldevicesname)) {
			launchURL(url);
			homepage.clickSettingsLink();
			homepage.click_DevicesLinkTab();
			homepage.clickDmeManagement();
		}
		//loginPage.waitForElementPresent(alldevicesname);
		homepage.click(dropdownbuttonfordelete);
		homepage.click(devicedeletebutton);
		logger.info("Clicked delete button for device:::"+devicename);
		homepage.pause(2000);
		if (homepage.isDisplayedWithoutException(deletepopuploctor)) {
			homepage.click(deletepopuploctor);
			logger.info("Device Deleted successfully:::"+devicename);
			if (homepage.isDisplayedWithoutException(alldevicesname)) {
				logger.info("Devices are visible on the page");
				
			}
			else {
				logger.info("Devices are not visible on the page");
				//homepage.clickSettingsLink();
				homepage.clickAllDevicesLink();
			}
		}
		else {
			homepage.click(cancelpopuplocator);
			logger.info("Clicked on cancel popup button for Device:::"+devicename);
			logger.info("Device not deleted is:::"+devicename);
			homepage.pause(3000);
		}
		if (homepage.isDisplayedWithoutException(alldevicesname)) {
			logger.info("Devices are visible on the page");
			
		}
		else {
			logger.info("Devices are not visible on the page");
			homepage.clickSettingsLink();
			homepage.clickAllDevicesLink();
		}
			
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
		homePage.click_DevicesLinkTab();
		 AvengerDevicesPage avengerDevicesPage = homePage.clickDmeManagement();
		ArrayList<String> alldevices = homePage.getAllWebElementValueslist(alldevicesname);
		logger.info("The list is"+alldevices);
		logger.info("The list is"+alldevices.size());
		
		if (alldevices.size() != 0) {
			for (int i = 0; i < alldevices.size(); i++) {
				try {
				/*	logger.info("Total NO.Of Devices present on the page Before deletion:::::"+homePage.elements(alldevicesname));
					logger.info("@@@@device name is"+alldevices.get(i));
				*/	delete_AllDevices(alldevices.get(i),homePage,url);
					logger.info("Total NO.Of Devices present on the page After deletion:::::"+homePage.elements(alldevicesname));
				} catch (Exception e) {
					logger.info("exception block");
					if(loginPage.isDisplayedWithoutException(username)){
					loginPage.loginAs(sUserName, sPassword);
					loginPage.pause(5000);
					//homePage.avengerDashboardPage();
				}
					else 
						logger.info("In else statement after exception");
						homePage.clickSettingsLink();
						logger.info("Clicked on settings link");
						homePage.clickAllDevicesLink();
						logger.info("Clicked on Devices");
			 }
			}	
		}
		logger.info("There are no Devices in the page");
		browserQuit(driver);
	}
	
	@Test(description="Delete Device",groups={DEVICES})
	public void AT_verify_Admin_DeleteDevice_TC1() {
		deleteDevices(AutomationURL);
	}
	
			
	@AfterMethod(alwaysRun = true)
	public void browserClose(ITestResult result) {
		logger.info("In After method class");
		statusValue.clear();

	}
	
}
