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
import com.vbrick.avenger.dao.EditRootAccountBeanPage;
import com.vbrick.avenger.pageobjects.AvengerAddUserPage;
import com.vbrick.avenger.pageobjects.AvengerConfirmPasswordPage;
import com.vbrick.avenger.pageobjects.AvengerEditRootAccountPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerUserDashboardPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;
import com.vbrick.avenger.setup.UserCreationUtility;

public class UserCreationfrom130_140 extends TestBase{
	private static Logger logger = Logger.getLogger(UserCreationfrom130_140.class);

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
		launchURL(surl,driver);
		}
	static int i=121;
//	@Test(description="Creation of Users from 130  to 130",invocationCount=11)
	public void AT_verify_userCreationfrom_130_130() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs("griduser13","Password@123");
		homePage.clickSettingsLink();
		avengeruserdashboardpage=homePage.clickUsersLink();
		avengeradduserpage=avengeruserdashboardpage.clickAdduser();  
		adduserbeanpage.setUsername(uservalue+i);
		adduserbeanpage.setFirstname(uservalue+i);
		adduserbeanpage.setLastname(uservalue+i);
		ArrayList<String> roles = new ArrayList<String>();
		roles.add("Account Admin");
		avengeruserdashboardpage= avengeradduserpage.addUseraccountRoles(adduserbeanpage, roles);
		editrootaccountpage=avengeruserdashboardpage.clicknewuser_link(adduserbeanpage);
		avengerconfirmpasswordpage=editrootaccountpage.click_UserConfirmationURL();
		createpasswordbeanpage.setPassword("Password@123");
		createpasswordbeanpage.setConfirmpassword("Password@123");
		loginPage= avengerconfirmpasswordpage.passwordCreation(createpasswordbeanpage);
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
		}

	@Test(description="Creation of User 131")
	public void AT_verify_userCreation_131() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs("griduser13","Password@123");
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"131");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}	
	@Test(description="Creation of User 132")
	public void AT_verify_userCreation_132() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs("griduser13","Password@123");
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"132");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of User 133")
	public void AT_verify_userCreation_133() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs("griduser13","Password@123");
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"133");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of User 134")
	public void AT_verify_userCreation_134() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs("griduser13","Password@123");
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"134");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of User 135")
	public void AT_verify_userCreation_135()
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs("griduser13","Password@123");
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"135");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of User 136")
	public void AT_verify_userCreation_136() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs("griduser13","Password@123");
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"136");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of User 137")
	public void AT_verify_userCreation_137() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs("griduser13","Password@123");
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"137");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of User 138")
	public void AT_verify_userCreation_138() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs("griduser13","Password@123");
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"138");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of User 139")
	public void AT_verify_userCreation_139() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs("griduser13","Password@123");
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"139");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}	
	
	@Test(description="Creation of User 140")
	public void AT_verify_userCreation_140() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs("griduser13","Password@123");
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"140");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@AfterMethod(alwaysRun=true)
	public void browserClose(ITestResult result)
	{
		i++;
		logger.info("In After method class");
		statusValue.clear();
		if(!result.isSuccess()){
			Reporter.log("Screen shot for failed Test Case" +customReport.AssertionresultOutput(driver, sTestcaseName));
		}
		browserQuit(driver);
	}
	
	
		
		
		
		
	
	
}
