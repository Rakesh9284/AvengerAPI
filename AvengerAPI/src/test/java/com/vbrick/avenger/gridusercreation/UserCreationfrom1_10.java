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

public class UserCreationfrom1_10 extends TestBase{
	private static Logger logger = Logger.getLogger(UserCreationfrom1_10.class);

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

/*	@Test
	public void f(ITestContext ctx) {
		String suiteName = ctx.getCurrentXmlTest().getSuite().getName();
		logger.info("The suite name is "+suiteName);
	}
*/	
	
	
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
	static int i=1;
	//@Test(description="Creation of Users from 1 to 10",invocationCount=13)
	public void AT_verify_userCreationfrom_1_10() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(Configuration.getAutomationUsername(),Configuration.getAutomationPassword());
		homePage.clickSettingsLink();
		avengeruserdashboardpage=homePage.clickUsersLink();
		avengeradduserpage=avengeruserdashboardpage.clickAdduser();  
		adduserbeanpage.setUsername(uservalue+i);
		adduserbeanpage.setFirstname(uservalue+i);
		adduserbeanpage.setLastname(uservalue+i);
		adduserbeanpage.setContactemail(uservalue+i+adduserbeanpage.getDomainname());
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

	@Test(description="Creation of User 3")
	public void AT_verify_userCreation_3() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(Configuration.getAutomationUsername(),Configuration.getAutomationPassword());
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"3");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of User 4")
	public void AT_verify_userCreation_4() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(Configuration.getAutomationUsername(),Configuration.getAutomationPassword());
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"4");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of User 5")
	public void AT_verify_userCreation_5() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(Configuration.getAutomationUsername(),Configuration.getAutomationPassword());
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"5");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of User 6")
	public void AT_verify_userCreation_6() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(Configuration.getAutomationUsername(),Configuration.getAutomationPassword());
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"6");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	
	@Test(description="Creation of User 7")
	public void AT_verify_userCreation_7() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(Configuration.getAutomationUsername(),Configuration.getAutomationPassword());
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"7");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description="Creation of User 8")
	public void AT_verify_userCreation_8() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(Configuration.getAutomationUsername(),Configuration.getAutomationPassword());
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"8");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description="Creation of User 9")
	public void AT_verify_userCreation_9() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(Configuration.getAutomationUsername(),Configuration.getAutomationPassword());
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"9");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description="Creation of User 10")
	public void AT_verify_userCreation_10() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(Configuration.getAutomationUsername(),Configuration.getAutomationPassword());
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"10");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of User 11")
	public void AT_verify_userCreation_11() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(Configuration.getAutomationUsername(),Configuration.getAutomationPassword());
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"11");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of User 12")
	public void AT_verify_userCreation_12() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(Configuration.getAutomationUsername(),Configuration.getAutomationPassword());
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"12");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of User 13")
	public void AT_verify_userCreation_13() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(Configuration.getAutomationUsername(),Configuration.getAutomationPassword());
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"13");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	 @Test(description="Verify Click On Recently Add Video links to Video Page",groups={"Acceptance","FileUpload","PortalHome"})
	 public void AT_verify_clickVideoTitle_Sprint29_AV925()
	 {
		 sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		 accountBeansFactory = new BeanFactory();
		 AvengerEditVideoSettingsBeanPage editvideosettingsbean = new AvengerEditVideoSettingsBeanPage();
		 accountBeansFactory.AvengerEditVideoSettingsBeanByPage(editvideosettingsbean);

	//	 launchURL(surl);
		 homePage = loginPage.loginAs(Configuration.getAutomationUsername(),Configuration.getAutomationPassword());
		 FileUploadBeanPage fileUploadBeanPage = new FileUploadBeanPage();
		 fileUploadBeanPage.setFilesourcepath(getFilePath(MP4VIDEOSPATH));
		 String uploadedfilename= homePage.uploadingFile(fileUploadBeanPage);
		 logger.info("The Uploaded file name is"+ fileUploadBeanPage.getFilename());
		 customReport.customizedReport(fileUploadBeanPage.getFilename(),uploadedfilename, statusValue, driver, sTestcaseName); 
		 AvengerEditVideoSettingsPage editVideoSettingsPage = homePage.click_EditVideoSettings(fileUploadBeanPage);
		 editvideosettingsbean.setTags("");
		 editvideosettingsbean.setCategories("");
		 editvideosettingsbean.setLibraries("");
		 editVideoSettingsPage.editVideoSettings(editvideosettingsbean);
		 editVideoSettingsPage.pause(5000);
		 AvengerVideoCommentsPage avengerVideoCommentsPage = editVideoSettingsPage.click_saveButton(editvideosettingsbean);
		 homePage.click_avengerDashboardPage();
		 //homePage.getRecentlyAddedVideoTitle();
		 customReport.customizedReport(true,homePage.getRecentlyAddedVideoTitle().contains(editvideosettingsbean.getTitle()), statusValue, driver, sTestcaseName);
		 avengerVideoCommentsPage =  homePage.click_RecentlyVideoTitle();
		 customReport.customizedReport(true, avengerVideoCommentsPage.getVideoButton().contains("play"), statusValue, driver, sTestcaseName);
		 customReport.customizedReport(true, avengerVideoCommentsPage.getVideoStatus().contains("is-not-playing"), statusValue, driver, sTestcaseName);
		 customReport.checkinglist(statusValue);
	 }
	
	 @Test(description="delete button should not be there for root user ",groups="UserAdmin")
	 public void AT_Verify_deletebutton_ForRootuser_Maymilestone_AV924(){
		 sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
	//	 launchURL(surl);
		 customReport.reporter("Application launch with URL : ", surl);
		 //homePage = loginPage.loginAs(Configuration.getAutomationUsername(),Configuration.getAutomationPassword());
		 homePage = loginPage.loginAs(sUserName,sPassword);
		 homePage.clickSettingsLink();
		 avengeruserdashboardpage=homePage.clickUsersLink();
		 adduserbeanpage.setUsername(Configuration.getAutomationUsername());
		 customReport.customizedReport(adduserbeanpage.getUsername(),avengeruserdashboardpage.verifycreationofuser(adduserbeanpage), statusValue, driver, sTestcaseName);
		 customReport.customizedReport(false,avengeruserdashboardpage.verifyuserdeleteButton(Configuration.getAutomationUsername()),statusValue, driver, sTestcaseName);
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
