package com.vbrick.apiusercreation;

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
import com.vbrick.avenger.dao.AvengerEditVideoSettingsBeanPage;
import com.vbrick.avenger.dao.CreatePasswordBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.pageobjects.AvengerAddUserPage;
import com.vbrick.avenger.pageobjects.AvengerConfirmPasswordPage;
import com.vbrick.avenger.pageobjects.AvengerEditRootAccountPage;
import com.vbrick.avenger.pageobjects.AvengerEditVideoSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerUserDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Configuration;
import com.vbrick.avenger.setup.TestBase;
import com.vbrick.avenger.setup.UserCreationUtility;

public class APIEnvSetup extends TestBase{
	private static Logger logger = Logger.getLogger(APIEnvSetup.class);

	/**	 * 
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
	@Test(description="Creation of api User 1")
	public void AT_verify_userCreation_1() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(Configuration.getAutomationUsername(),Configuration.getAutomationPassword());
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, apiuservalue+"1");
		customReport.customizedReport(adduserbeanpage.getUsername()+" "+adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of api User 2")
	public void AT_verify_userCreation_2() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(Configuration.getAutomationUsername(),Configuration.getAutomationPassword());
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, apiuservalue+"2");
		customReport.customizedReport(adduserbeanpage.getUsername()+" "+adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of User 3")
	public void AT_verify_userCreation_3() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(Configuration.getAutomationUsername(),Configuration.getAutomationPassword());
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, apiuservalue+"3");
		customReport.customizedReport(adduserbeanpage.getUsername()+" "+adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	@Test(description="Creation of User 4")
	public void AT_verify_userCreation_4() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(Configuration.getAutomationUsername(),Configuration.getAutomationPassword());
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, apiuservalue+"4");
		customReport.customizedReport(adduserbeanpage.getUsername()+" "+adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}	
	@Test(description="Creation of User 5")
	public void AT_verify_userCreation_5() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(Configuration.getAutomationUsername(),Configuration.getAutomationPassword());
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, apiuservalue+"5");
		customReport.customizedReport(adduserbeanpage.getUsername()+" "+adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}	
	@Test(description="Creation of User 6")
	public void AT_verify_userCreation_6() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(Configuration.getAutomationUsername(),Configuration.getAutomationPassword());
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, apiuservalue+"6");
		customReport.customizedReport(adduserbeanpage.getUsername()+" "+adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
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
