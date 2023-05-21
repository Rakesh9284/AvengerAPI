package com.vbrick.avenger.gridusercreation;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.vbrick.Exception.CustomReport;
import com.vbrick.Exception.Reasons;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AddUserBeanPage;
import com.vbrick.avenger.dao.CreatePasswordBeanPage;
import com.vbrick.avenger.pageobjects.AvengerAddUserPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;
import com.vbrick.avenger.setup.UserCreationUtility;

public class mediaviewerusercreation1_10 extends TestBase{
	private static Logger logger = Logger.getLogger(mediaviewerusercreation1_10.class);

	/**
	 * 
	 * @throws Exception
	 */
	private WebDriver driver;
	private AvengerLoginPage loginPage;
	private AvengerHomePage homePage;
	private List<String> statusValue;
	private CustomReport customReport;
	private Reasons reasons;
	private String sTestcaseName;
	private ResourceBundle bundle;
	private Locale locale;
	private BeanFactory accountBeansFactory;
//	private AvengerUserDashboardPage avengeruserdashboardpage;
	private AvengerAddUserPage avengeradduserpage;
	private AddUserBeanPage adduserbeanpage;
//	private AvengerConfirmPasswordPage avengerconfirmpasswordpage;
	private CreatePasswordBeanPage createpasswordbeanpage;
	private BasePage basePage;
//	private AvengerEditRootAccountPage editrootaccountpage;
	private UserCreationUtility userCreationUtility;
	
	@BeforeClass(alwaysRun=true)
	public void beforeClass(ITestContext ctx) throws Exception {
		reasons= new Reasons("");
		statusValue=new ArrayList<String>();
		customReport = new CustomReport();
		accountBeansFactory=new BeanFactory();
		adduserbeanpage=new AddUserBeanPage();
		createpasswordbeanpage=new CreatePasswordBeanPage();
		locale = new Locale(language);
		basePage = new  BasePage();
		createpasswordbeanpage= new CreatePasswordBeanPage();
		String suiteName = ctx.getCurrentXmlTest().getSuite().getName();
		logger.info("The suite name in Before class is "+suiteName);
		userCreationUtility= new UserCreationUtility(suiteName);
		}

	@BeforeMethod(alwaysRun=true)
	@Parameters(value = {"sbrowser","sgrid"/* , "surl", "sUserName", "sPassword"*/})
	public void setUP(@Optional(SBROWSER)String sbrowser,@Optional(SVERSION) String sgrid/*, String sUserName,String sPassword*/) throws MalformedURLException 
	{
		logger.info("Version value is"+sgrid);
		driver = initializeDriver(sbrowser,sgrid/*, surl ,sversion,sPlatform*/ );
		customReport.reset();
		logger.info("The driver value is "+driver);
		bundle=ResourceBundle.getBundle("ResourceBundle.BundleFile",locale);
		logger.info("value in bundle is"+bundle.getKeys());
		basePage= new BasePage(driver,customReport, new BasePage());
		loginPage = basePage.avengerLoginPage(driver,customReport, basePage);
		driver.manage().window().maximize();
		accountBeansFactory.CreatePasswordBean(createpasswordbeanpage);
		accountBeansFactory.AddUserBean(adduserbeanpage);
		launchURL(surl,driver);

		}
	

	@Test(description="Creation of Media Viewer User 1")
	public void AT_verify_mediavieweruserCreation_1() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs("griduser20","Password@123");
		userCreationUtility.mediaViewerUserCreation(homePage,adduserbeanpage,avengeradduserpage, mediaviewer+"1");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of Media Viewer User 2")
	public void AT_verify_mediavieweruserCreation_2() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs("griduser20","Password@123");
		userCreationUtility.mediaViewerUserCreation(homePage,adduserbeanpage,avengeradduserpage, mediaviewer+"2");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	
	@Test(description="Creation of Media Viewer User 3")
	public void AT_verify_mediavieweruserCreation_3() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs("griduser20","Password@123");
		userCreationUtility.mediaViewerUserCreation(homePage,adduserbeanpage,avengeradduserpage, mediaviewer+"3");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of Media Viewer User 4")
	public void AT_verify_mediavieweruserCreation_4() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs("griduser20","Password@123");
		userCreationUtility.mediaViewerUserCreation(homePage,adduserbeanpage,avengeradduserpage, mediaviewer+"4");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	
	@Test(description="Creation of Media Viewer User 5")
	public void AT_verify_mediavieweruserCreation_5() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs("griduser20","Password@123");
		userCreationUtility.mediaViewerUserCreation(homePage,adduserbeanpage,avengeradduserpage, mediaviewer+"5");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of Media Viewer User 6")
	public void AT_verify_mediavieweruserCreation_6() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs("griduser20","Password@123");
		userCreationUtility.mediaViewerUserCreation(homePage,adduserbeanpage,avengeradduserpage, mediaviewer+"6");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of Media Viewer User 7")
	public void AT_verify_mediavieweruserCreation_7() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs("griduser20","Password@123");
		userCreationUtility.mediaViewerUserCreation(homePage,adduserbeanpage,avengeradduserpage, mediaviewer+"7");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	
	@Test(description="Creation of Media Viewer User 8")
	public void AT_verify_mediavieweruserCreation_8() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs("griduser20","Password@123");
		userCreationUtility.mediaViewerUserCreation(homePage,adduserbeanpage,avengeradduserpage, mediaviewer+"8");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of Media Viewer User 9")
	public void AT_verify_mediavieweruserCreation_9() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs("griduser20","Password@123");
		userCreationUtility.mediaViewerUserCreation(homePage,adduserbeanpage,avengeradduserpage, mediaviewer+"9");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	
	@Test(description="Creation of Media Viewer User 10")
	public void AT_verify_mediavieweruserCreation_10() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs("griduser20","Password@123");
		userCreationUtility.mediaViewerUserCreation(homePage,adduserbeanpage,avengeradduserpage, mediaviewer+"10");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
		
	@AfterMethod(alwaysRun=true)
	public void browserClose(ITestResult result)
	{
		logger.info("In After method class");
		statusValue.clear();
		if(!result.isSuccess()){
			Reporter.log("Screen shot for failed Test Case" +customReport.AssertionresultOutput(driver, sTestcaseName));
		}
		browserQuit(driver);
	}
}
