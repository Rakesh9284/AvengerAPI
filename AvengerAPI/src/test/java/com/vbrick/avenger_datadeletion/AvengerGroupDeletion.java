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
import com.vbrick.avenger.pageobjects.AvengerGroupsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLibrariesPage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerMediaSettingsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;

public class AvengerGroupDeletion extends TestBase {
	
	private static Logger logger = Logger.getLogger(AvengerGroupDeletion.class);
	private List<String> statusValue;
	private CustomReport customReport;
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

	
	String sUserName = deletionuser;
	String sPassword=SANITYPASSWORD;
	By username = By.xpath("//div[@class='username']/input");
	
    By allgroupsnamelist=By.xpath("//a[@ui-sref='portal.admin.user-access.groups.edit({groupId: group.id})']");
	By deletepopuploctor=By.xpath("//*[contains(@class,'modal-footer')]//*[@type='submit' or text()='OK' or @ng-show='actionText']");
	By popupAcceptlocator=By.xpath("//*[contains(@class,'modal-footer')]//*[@type='submit']");
	By cancelpopuplocator=By.xpath("//*[@class='glyphicons remove_2']/..");
	
	
	//deletes groups
	public void delete_Groups(String groupname,AvengerHomePage homepage)
	{
		
		By allgroupsdeletebutton=By.xpath("//a[text()[contains(.,'"+groupname+"')]]/../following-sibling::div[5]/child::div/button");
		homepage.click(allgroupsdeletebutton);
		logger.info("Clicked on delete button for group::"+groupname);
		
		
		if (homepage.isDisplayedWithoutException(deletepopuploctor)) {
			homepage.waitForElementPresent(deletepopuploctor);
			homepage.click(popupAcceptlocator);
			customReport.reporter("Group Deleted Successfully", "");
			logger.info("Deleted Successfully::"+groupname);
			homepage.pause(3000);
		}
		else {
			 homepage.click(cancelpopuplocator);
		     logger.info("Clicked on cancel popup button for group"+groupname);
		     homepage.pause(5000);
		   
	} 
	browserQuit(driver);
	}
		
	
	
	public void delete_AllGroups(String surl)throws InterruptedException{
		
		WebDriver driver = initializeDriver(sbrowser, "");
		logger.info("The driver value is " + driver);
		basePage = new BasePage(driver, customReport, new BasePage());
		AvengerLoginPage loginPage = basePage.avengerLoginPage(driver, customReport, basePage);
		driver.manage().window().maximize();
		
		launchURL(surl,driver);
		homepage = loginPage.loginAs(sUserName, sPassword);
		homepage.clickSettingsLink();
		AvengerGroupsPage avengergroupspage=homepage.clickGroupsLink();
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
			
		ArrayList<String> allgroupstitle= homepage.getAllWebElementValueslist(allgroupsnamelist);
		//int totalgroups=homepage.elements(allgroupsnamelist);
		int totalgroups=allgroupstitle.size();
		logger.info("Total NO.Of Groups present on the page Before deletion:::::"+totalgroups);
		
		if (homepage.isDisplayedWithoutException(allgroupsnamelist)) {
			for (int i = 1; i < totalgroups; i++) {	
				delete_Groups(allgroupstitle.get(i),homepage);
			}
		}
		
		}	
		
				
	@Test(description="Delete Groups",groups={GROUPS})
	public void deleteGroups_TC1()throws InterruptedException{
		delete_AllGroups(AutomationURL);
	}
	
		
	@AfterMethod(alwaysRun = true)
	public void browserClose(ITestResult result) {
		logger.info("In After method class");
		statusValue.clear();

	}
}
