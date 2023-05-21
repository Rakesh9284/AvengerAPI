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
import com.vbrick.avenger.pageobjects.AvengerCategoriesPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerMediaSettingsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;

public class AvengerCategoriesDeletion extends TestBase {
	
	private static Logger logger = Logger.getLogger(AvengerCategoriesDeletion.class);
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

	
	String sUserName = deletionuser6;
	String sPassword=SANITYPASSWORD;
	By username = By.xpath("//div[@class='username']/input");
	
	By allcategorieslist=By.xpath("//div[@class='child-row']/child::div[4]/a");
	By deletepopuploctor=By.xpath("//*[contains(@class,'modal-footer')]//*[@type='submit' or text()='OK' or @ng-show='actionText']");
	By popupAcceptlocator=By.xpath("//*[contains(@class,'modal-footer')]//*[@type='submit']");
	By cancelpopuplocator=By.xpath("//*[@class='glyphicons remove_2']/..");
	
	//Deletes categories
	public void delete_Categories(String categoryname,AvengerHomePage homepage)
	{
		By allcategoriesdeletebutton=By.xpath("//*[contains(text(),'"+categoryname+"')]/../following-sibling::div[3]/button");
		homepage.click(allcategoriesdeletebutton);
		logger.info("Clicked on delete button for group::"+categoryname);
		
		
		if (homepage.isDisplayedWithoutException(deletepopuploctor)) {
			homepage.waitForElementPresent(deletepopuploctor);
			homepage.click(popupAcceptlocator);
			customReport.reporter("Category Deleted Successfully", "");
			logger.info("Deleted Successfully::"+categoryname);
			homepage.pause(3000);
		}
		else {
			 homepage.click(cancelpopuplocator);
		     logger.info("Clicked on cancel popup button for category"+categoryname);
		     homepage.pause(5000);
		   
	} 
	browserQuit(driver);
	}
	
	public void delete_AllCategories(String surl)throws InterruptedException {
		
		WebDriver driver = initializeDriver(sbrowser, "");
		//logger.info("The driver value is " + driver);
		BasePage basePage = new BasePage(driver, customReport, new BasePage());
		loginPage = basePage.avengerLoginPage(driver, customReport, basePage);
		driver.manage().window().maximize();
		
		launchURL(surl,driver);
		homepage = loginPage.loginAs(sUserName, sPassword);
		homepage.clickSettingsLink();
		AvengerMediaSettingsPage avengermediasettingspage=homepage.clickMediaSettingsLink();
		AvengerCategoriesPage avengercategoriespage=avengermediasettingspage.click_CategoriesLinkLocator();
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);
		
		ArrayList<String> allcategoriestitle= homepage.getAllWebElementValueslist(allcategorieslist);
		int totalcategories=allcategoriestitle.size();
		logger.info("Total NO.Of categories present on the page Before deletion:::::"+totalcategories);
		
		if (homepage.isDisplayedWithoutException(allcategorieslist)) {
			for (int i = 1; i < totalcategories; i++) {	
				delete_Categories(allcategoriestitle.get(i),homepage);
			}
		}
		
		}	
	
	
		
	@Test(description="Delete Categories",groups={CATEGORIES})
	public void deleteCategories_TC1()throws InterruptedException{
		delete_AllCategories(AutomationURL);
	}
	
		
	@AfterMethod(alwaysRun = true)
	public void browserClose(ITestResult result) {
		logger.info("In After method class");
		statusValue.clear();

	}
}
