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
import com.vbrick.avenger.pageobjects.AvengerConfirmPasswordPage;
import com.vbrick.avenger.pageobjects.AvengerEditRootAccountPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerUserDashboardPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;
import com.vbrick.avenger.setup.UserCreationUtility;

public class UserCreationforAccountD extends TestBase {
	private static Logger logger = Logger.getLogger(UserCreationforAccountD.class);

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
	private AvengerUserDashboardPage avengeruserdashboardpage;
	private AvengerAddUserPage avengeradduserpage;
	private AddUserBeanPage adduserbeanpage;
	private AvengerConfirmPasswordPage avengerconfirmpasswordpage;
	private CreatePasswordBeanPage createpasswordbeanpage;
	private BasePage basePage;
	private AvengerEditRootAccountPage editrootaccountpage;
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
		launchURL(surlD,driver);

		}
	
	
	@Test(description="Creation of User 100 in Account D")
	public void AT_verify_userCreation_100() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surlD);
		homePage = loginPage.loginAs(ACCOUNTUSER,SANITYPASSWORD);
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"100");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of User 101 in Account D")
	public void AT_verify_userCreation_101() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surlD);
		homePage = loginPage.loginAs(ACCOUNTUSER,SANITYPASSWORD);
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"101");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	
	@Test(description="Creation of User 102 in Account D")
	public void AT_verify_userCreation_102() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surlD);
		homePage = loginPage.loginAs(ACCOUNTUSER,SANITYPASSWORD);
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"102");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of User 114 in Account D")
	public void AT_verify_userCreation_114() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surlD);
		homePage = loginPage.loginAs(ACCOUNTUSER,SANITYPASSWORD);
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"114");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of User 119 in Account D")
	public void AT_verify_userCreation_119() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surlD);
		homePage = loginPage.loginAs(ACCOUNTUSER,SANITYPASSWORD);
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"119");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of User 120 in Account D")
	public void AT_verify_userCreation_120() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surlD);
		homePage = loginPage.loginAs(ACCOUNTUSER,SANITYPASSWORD);
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"120");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of User 2 in Account C")
	public void AT_verify_userCreation_2() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surlC);
		homePage = loginPage.loginAs(ACCOUNTUSER,SANITYPASSWORD);
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"2");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of User 3 in Account C")
	public void AT_verify_userCreation_3() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surlC);
		homePage = loginPage.loginAs(ACCOUNTUSER,SANITYPASSWORD);
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"3");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of User 4 in Account C")
	public void AT_verify_userCreation_4() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surlC);
		homePage = loginPage.loginAs(ACCOUNTUSER,SANITYPASSWORD);
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"4");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of User 5 in Account C")
	public void AT_verify_userCreation_5() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surlC);
		homePage = loginPage.loginAs(ACCOUNTUSER,SANITYPASSWORD);
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"5");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of Media Viewer User in Account C")
	public void AT_verify_mediavieweruserCreation1_AccountC() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Application launch with URL : ", surlC);
		homePage = loginPage.loginAs(ACCOUNTUSER,SANITYPASSWORD);
		userCreationUtility.mediaViewerUserCreation(homePage,adduserbeanpage,avengeradduserpage, mediaviewer+"1");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	@Test(description="Creation of Media Viewer User in Account C")
	public void AT_verify_mediavieweruserCreation2_AccountC() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Application launch with URL : ", surlC);
		homePage = loginPage.loginAs(ACCOUNTUSER,SANITYPASSWORD);
		userCreationUtility.mediaViewerUserCreation(homePage,adduserbeanpage,avengeradduserpage, mediaviewer+"2");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	@Test(description="Creation of Media Viewer User in Account C")
	public void AT_verify_mediavieweruserCreation3_AccountC() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Application launch with URL : ", surlC);
		homePage = loginPage.loginAs(ACCOUNTUSER,SANITYPASSWORD);
		userCreationUtility.mediaViewerUserCreation(homePage,adduserbeanpage,avengeradduserpage, mediaviewer+"3");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	@Test(description="Creation of Media Viewer User in Account C")
	public void AT_verify_mediavieweruserCreation4_AccountC() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Application launch with URL : ", surlC);
		homePage = loginPage.loginAs(ACCOUNTUSER,SANITYPASSWORD);
		userCreationUtility.mediaViewerUserCreation(homePage,adduserbeanpage,avengeradduserpage, mediaviewer+"4");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	@Test(description="Creation of Media Viewer User in Account C")
	public void AT_verify_mediavieweruserCreation5_AccountC() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Application launch with URL : ", surlC);
		homePage = loginPage.loginAs(ACCOUNTUSER,SANITYPASSWORD);
		userCreationUtility.mediaViewerUserCreation(homePage,adduserbeanpage,avengeradduserpage, mediaviewer+"5");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	
	@Test(description="Creation of Media Contributor User in Account C")
	public void AT_verify_mediaContributoruserCreation_AccountC() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Application launch with URL : ", surlC);
		homePage = loginPage.loginAs(ACCOUNTUSER,SANITYPASSWORD);
		
		List<String> roles = new ArrayList<String>();
		roles.add("Media Contributor");
		adduserbeanpage.setUsername(MEDIACONTRIBUTER_AUTO);
		adduserbeanpage.setLastname(MEDIACONTRIBUTER_AUTO);
		adduserbeanpage.setFirstname(MEDIACONTRIBUTER_AUTO);
		createpasswordbeanpage.setConfirmpassword(SANITYPASSWORD);
		createpasswordbeanpage.setPassword(SANITYPASSWORD);
		userCreationUtility.createUserwithSpecifiedRole(homePage, adduserbeanpage, roles, createpasswordbeanpage);
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of event admin User in Account C")
	public void AT_verify_eventAdminuserCreation_AccountC() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Application launch with URL : ", surlC);
		homePage = loginPage.loginAs(ACCOUNTUSER,SANITYPASSWORD);
		
		List<String> roles = new ArrayList<String>();
		roles.add("Event Admin");
		adduserbeanpage.setUsername(EVENTADMIN_AUTO);
		adduserbeanpage.setLastname(EVENTADMIN_AUTO);
		adduserbeanpage.setFirstname(EVENTADMIN_AUTO);
		createpasswordbeanpage.setConfirmpassword(SANITYPASSWORD);
		createpasswordbeanpage.setPassword(SANITYPASSWORD);
		userCreationUtility.createUserwithSpecifiedRole(homePage, adduserbeanpage, roles, createpasswordbeanpage);
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
