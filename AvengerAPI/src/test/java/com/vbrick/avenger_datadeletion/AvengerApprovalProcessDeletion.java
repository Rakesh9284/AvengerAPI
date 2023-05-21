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
import com.vbrick.avenger.pageobjects.AvengerApprovalProcessPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLibrariesPage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerMediaSettingsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;

public class AvengerApprovalProcessDeletion extends TestBase {
	
	private static Logger logger = Logger.getLogger(AvengerApprovalProcessDeletion.class);
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
	public void setUP(@Optional(SBROWSER)String sbrowser,@Optional(SVERSION) String sgrid) throws MalformedURLException 
	{
	}

	
	String sUserName = deletionuser7;
	String sPassword=SANITYPASSWORD;
	By username = By.xpath("//div[@class='username']/input");
	
	By allapppronamelist=By.xpath("//a[@ui-sref='.detail({ processTemplateId: process.id })']");
	By deletepopuploctor=By.xpath("//*[contains(@class,'modal-footer')]//*[@type='submit' or text()='OK' or @ng-show='actionText']");
	By popupAcceptlocator=By.xpath("//*[contains(@class,'modal-footer')]//*[@type='submit' or text()='OK']");
	By cancelpopuplocator=By.xpath("//*[@class='glyphicons remove_2']/..");
	
	
	public void delete_Appprocess(String appproname,AvengerHomePage homepage)
	{
		By allappprodeletebutton=By.xpath("//a[text()[contains(.,'"+appproname+"')]]/../following-sibling::div[1]/button");
		homepage.click(allappprodeletebutton);
		logger.info("Clicked on delete button for approval process::"+appproname);
		
		
		if (homepage.isDisplayedWithoutException(deletepopuploctor)) {
			homepage.waitForElementPresent(deletepopuploctor);
			homepage.click(popupAcceptlocator);
			customReport.reporter("Approval process Deleted Successfully", "");
			logger.info("Deleted Successfully::"+appproname);
			homepage.pause(3000);
		}
		else {
			 homepage.click(cancelpopuplocator);
		     logger.info("Clicked on cancel popup button for approval process"+appproname);
		     homepage.pause(5000);
		   
	} 
	browserQuit(driver);
	}
	
	
	
	//Deletes approval process
	public void delete_AllApprovalProcess(String surl)throws InterruptedException{
		
		WebDriver driver = initializeDriver(sbrowser, "");
		//logger.info("The driver value is " + driver);
		BasePage basePage = new BasePage(driver, customReport, new BasePage());
		loginPage = basePage.avengerLoginPage(driver, customReport, basePage);
		driver.manage().window().maximize();
		
		launchURL(surl,driver);
		homepage = loginPage.loginAs(sUserName, sPassword);
		homepage.clickSettingsLink();
		AvengerMediaSettingsPage avengermediasettingspage=homepage.clickMediaSettingsLink();
		AvengerApprovalProcessPage avengerApprovalProcessPage=avengermediasettingspage.click_ApprovalProcesses();
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);
		
		ArrayList<String> allappprotitle= homepage.getAllWebElementValueslist(allapppronamelist);
		int totalapppro=allappprotitle.size();
		logger.info("Total NO.Of approval process present on the page Before deletion:::::"+totalapppro);
		
		if (homepage.isDisplayedWithoutException(allapppronamelist)) {
			for (int i = 1; i < totalapppro; i++) {	
				delete_Appprocess(allappprotitle.get(i),homepage);
			}
		}
		
		}	
		
	
	@Test(description="Delete Approval Process",groups={APPROVALPROCESS})
	public void deleteApprovalProcess_TC1()throws InterruptedException{
		delete_AllApprovalProcess(AutomationURL);
	}
	
		
	@AfterMethod(alwaysRun = true)
	public void browserClose(ITestResult result) {
		logger.info("In After method class");
		statusValue.clear();

	}
}
