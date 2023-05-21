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
import com.vbrick.avenger.pageobjects.AvengerLibrariesPage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerMediaSettingsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;

public class AvengerTeamDeletion extends TestBase {
	
	private static Logger logger = Logger.getLogger(AvengerTeamDeletion.class);
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

	//deletes teams
	String sUserName = deletionuser4;
	String sPassword=SANITYPASSWORD;
	By username = By.xpath("//div[@class='username']/input");
	
	By allteamsnamelist=By.xpath("//a[@ui-sref='portal.admin.user-access.teams.edit({teamId:team.id})']");
	By deletepopuploctor=By.xpath("//*[contains(@class,'modal-footer')]//*[@type='submit' or text()='OK' or @ng-show='actionText']");
	By popupAcceptlocator=By.xpath("//*[contains(@class,'modal-footer')]//*[@type='submit']");
	By cancelpopuplocator=By.xpath("//*[@class='glyphicons remove_2']/..");
	
	
	public void delete_Teams(String teamname,AvengerHomePage homepage)
	{
		By allteamsdeletebutton=By.xpath("//a[text()[contains(.,'"+teamname+"')]]/../following-sibling::div[1]/button");
		homepage.click(allteamsdeletebutton);
		logger.info("Clicked on delete button for team::"+teamname);
		
		
		if (homepage.isDisplayedWithoutException(deletepopuploctor)) {
			homepage.waitForElementPresent(deletepopuploctor);
			homepage.click(popupAcceptlocator);
			customReport.reporter("Team Deleted Successfully", "");
			logger.info("Deleted Successfully::"+teamname);
			homepage.pause(3000);
		}
		else {
			 homepage.click(cancelpopuplocator);
		     logger.info("Clicked on cancel popup button for team"+teamname);
		     homepage.pause(5000);
		   
	} 
	browserQuit(driver);
	}
	
	public void delete_AllTeams(String surl)throws InterruptedException {
		
		WebDriver driver = initializeDriver(sbrowser, "");
		//logger.info("The driver value is " + driver);
		BasePage basePage = new BasePage(driver, customReport, new BasePage());
		loginPage = basePage.avengerLoginPage(driver, customReport, basePage);
		driver.manage().window().maximize();
		
		launchURL(surl,driver);
		homepage = loginPage.loginAs(sUserName, sPassword);
		homepage.clickSettingsLink();
		AvengerMediaSettingsPage avengermediasettingspage=homepage.clickMediaSettingsLink();
		AvengerLibrariesPage avengerlibrarypage =avengermediasettingspage.click_LibrariesLinkLocator();
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);
		
		ArrayList<String> allteamstitle= homepage.getAllWebElementValueslist(allteamsnamelist);
		int totalteams=allteamstitle.size();
		logger.info("Total NO.Of teams present on the page Before deletion:::::"+totalteams);
		
		if (homepage.isDisplayedWithoutException(allteamsnamelist)) {
			for (int i = 1; i < totalteams; i++) {	
				if(!allteamstitle.get(i).equals("Donotdeleteteam1"))
					delete_Teams(allteamstitle.get(i),homepage);
			}
		}
		
		}	
	
	@Test(description="Delete teams",groups={TEAMS})
	public void deleteTeams_TC1()throws InterruptedException{
		delete_AllTeams(AutomationURL);
	}
	
		
	@AfterMethod(alwaysRun = true)
	public void browserClose(ITestResult result) {
		logger.info("In After method class");
		statusValue.clear();

	}
}
