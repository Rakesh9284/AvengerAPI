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
import com.vbrick.avenger.pageobjects.AvengerMediaPage;
import com.vbrick.avenger.pageobjects.AvengerUploadsPage;
import com.vbrick.avenger.pageobjects.AvengerVideoBulkEditPage;
import com.vbrick.avenger.pageobjects.AvengerVideosPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;

public class AvengerVideosDeletetion extends TestBase{

	private static Logger logger = Logger.getLogger(AvengerVideosDeletetion.class);
	private List<String> statusValue;
	private CustomReport customReport;
	private AvengerHomePage homepage; 
	private AvengerLoginPage loginPage;
		
	@BeforeClass(alwaysRun = true)
	public void beforeClass() throws Exception {
		statusValue = new ArrayList<String>();
		customReport = new CustomReport();
	}

	@BeforeMethod(alwaysRun = true)
	@Parameters(value = { "sbrowser","sgrid"})
	public void setUP(@Optional(SBROWSER) String sbrowser,@Optional(SVERSION) String sgrid) throws MalformedURLException {
		
		
	}
	
	//deletes videos
	
	String sUserName = "deletionuser5";
	String sPassword = SANITYPASSWORD;
	
	By videosdeletebutton=By.xpath("//span[@class='glyphicons bin']/..");
	By deletepopuploctor=By.xpath("//*[contains(@class,'modal-footer')]//*[@type='submit' or text()='OK' or @ng-show='actionText']");
	By bulkedit=By.xpath("//span[@class='glyphicons edit']");
	By totalvideocount=By.xpath("//*[@ng-show='status.active']/span");
	
	public void delete_AllVideosOneByOne(AvengerHomePage homepage){
		
		//for (int i = 1; i < homepage.elements(totalvideocount); i++) {
		for (int i = 1; i < 20; i++) {
		By firstvideocheckboxlocator=By.xpath("(//*[@class='btn btn-checkbox ng-scope'])["+1+"]");
		logger.info("The element with locator on the page is::::::::"+firstvideocheckboxlocator);
		homepage.waitForElementPresent(firstvideocheckboxlocator);
		logger.info("First video checkbox is visible on the page");
		homepage.clickusingjavascript(firstvideocheckboxlocator);
		logger.info("Selected checkbox for first videos");
	}
		homepage.waitForElementPresent(videosdeletebutton);
		homepage.click(videosdeletebutton);
		logger.info("Clicked on delete button");
		homepage.waitForElementPresent(deletepopuploctor);
		logger.info("Delete video popup is visible on the page");
		homepage.clickWithoutJavaScript(deletepopuploctor);
		logger.info("Video Bulk delete conformation popup is visible on the page");
		homepage.waitForElementPresent(deletepopuploctor);
		homepage.pause(10000);
		try{
		homepage.clickSelenium(deletepopuploctor);
		}
		catch(Exception e)
		{
			homepage.pause(10000);
			logger.info("@@@@@@@@@@Exception caught not able to click");
			homepage.clickSelenium(deletepopuploctor);
		}
		logger.info("Videos selected are deleted successfully");
		homepage.pause(10000);
		//loginPage.navigateBrowser(BulkVideosURL);
	}
	
	public void delete_AllVideosSingleClick(String surl, AvengerHomePage homepage){
		
		WebDriver driver = initializeDriver(sbrowser, "");
		//logger.info("The driver value is " + driver);
		BasePage basePage = new BasePage(driver, customReport, new BasePage());
		loginPage = basePage.avengerLoginPage(driver, customReport, basePage);
		driver.manage().window().maximize();
		
		launchURL(surl,driver);
		AvengerHomePage homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerMediaPage avengerMediaPage=homePage.avengerDashboardPage().clickMediaTab();
		AvengerVideosPage avengerVideosPage=avengerMediaPage.click_AllVideos();
		avengerVideosPage.clickBulkEditoption();
		avengerVideosPage.checkselectallcheckbox();
		
			homepage.click(videosdeletebutton);
			logger.info("Clicked on delete button");
			homepage.waitForElementPresent(deletepopuploctor);
			logger.info("Delete video popup is visible on the page");
			homepage.click(deletepopuploctor);
			logger.info("Video Bulk delete conformation popup is visible on the page");
			homepage.pause(15000);
			homepage.waitForElementPresent(deletepopuploctor);
			homepage.click(deletepopuploctor);
			logger.info("Clicked on OK button for popup");
		
				
		browserQuit(driver);
	}
	
	public void deleteVideosAccount(String surl/*, WebDriver driver*/)
	{
		WebDriver driver = initializeDriver(sbrowser, "");
		//logger.info("The driver value is " + driver);
		BasePage basePage = new BasePage(driver, customReport, new BasePage());
		AvengerLoginPage	loginPage = basePage.avengerLoginPage(driver, customReport, basePage);
		driver.manage().window().maximize();
		
		launchURL(surl,driver);
		AvengerHomePage homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerMediaPage avengerMediaPage=homePage.avengerDashboardPage().clickMediaTab();
		AvengerVideosPage avengerVideosPage=avengerMediaPage.click_AllVideos();
		avengerVideosPage.clickBulkEditoption();
		//delete_AllVideosSingleClick(homePage);
		
		By totalvideoscount=By.xpath("//*[contains(@count,'totalVideos')]");
		
		String str=homePage.getText(totalvideoscount);
		String numberOnly= str.replaceAll("[^0-9]", "");
        int totalvideos=Integer.parseInt(numberOnly);
		System.out.println("Total video are "+totalvideos);

		while(totalvideos>20)
		{
			delete_AllVideosOneByOne(homePage);
			avengerMediaPage=homePage.avengerDashboardPage().clickMediaTab();
			avengerVideosPage=avengerMediaPage.click_AllVideos();
			avengerVideosPage.clickBulkEditoption();
			str=homePage.getText(totalvideoscount);
			numberOnly= str.replaceAll("[^0-9]", "");
			totalvideos=Integer.parseInt(numberOnly);
			System.out.println("@@@Total video inside the loop "+totalvideos);
			
		}
		//customReport.customizedReport("0 Videos", loginPage.verify_label("0 Videos"), statusValue, driver, sTestcaseName);
		//browserQuit(driver);
	}
	
	
	@Test(description="Delete Videos",groups={CUSTOMVIDEOACCESS})
	public void admin_DeleteVideos(){
		delete_AllVideosSingleClick(AutomationURL, homepage);
		
	}
	
		
	@AfterMethod(alwaysRun = true)
	public void browserClose(ITestResult result) {
		logger.info("In After method class");
		
		
	}
}
