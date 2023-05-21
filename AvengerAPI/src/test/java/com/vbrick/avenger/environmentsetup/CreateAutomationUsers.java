package com.vbrick.avenger.environmentsetup;


import java.io.FileReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.TimeoutException;
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
import com.vbrick.avenger.beansfactory.SetupFactory;
import com.vbrick.avenger.dao.AddUserBeanPage;
import com.vbrick.avenger.dao.CreateChildAccountBeansPage;
import com.vbrick.avenger.dao.CreatePasswordBeanPage;
import com.vbrick.avenger.dao.ManualRootAccountBeanPage;
import com.vbrick.avenger.dao.ManualRootAccountBeanPage2;
import com.vbrick.avenger.funUtil.FileOperation;
import com.vbrick.avenger.funUtil.SendMail;
import com.vbrick.avenger.pageobjects.AvengerAccountsPage;
import com.vbrick.avenger.pageobjects.AvengerAddUserPage;
import com.vbrick.avenger.pageobjects.AvengerConfirmPasswordPage;
import com.vbrick.avenger.pageobjects.AvengerCreateChildAccountPage;
import com.vbrick.avenger.pageobjects.AvengerEditRootAccountPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerRootAccountPage;
import com.vbrick.avenger.pageobjects.AvengerRootAccountPage2;
import com.vbrick.avenger.pageobjects.AvengerUserDashboardPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Configuration;
import com.vbrick.avenger.setup.TestBase;
import com.vbrick.avenger.setup.UserCreationUtility;

public class CreateAutomationUsers extends TestBase{
	private static Logger logger = Logger.getLogger(CreateAutomationUsers.class);

	/**
	 * 
	 * @throws Exception
	 */

	private AvengerLoginPage loginPage;
	private AvengerHomePage homePage;
	private AvengerCreateChildAccountPage avengerCreatechildAccountPage;
	private List<String> statusValue;
	private CustomReport customReport;
	private Reasons reasons;
	private String sTestcaseName;
	private ResourceBundle bundle;
	private Locale locale;
	private SetupFactory setupfactory;
	private BeanFactory accountBeansFactory;
	private CreateChildAccountBeansPage childaccountBeansPage;
	private AvengerUserDashboardPage avengeruserdashboardpage;
	private AvengerAddUserPage avengeradduserpage;
	private AddUserBeanPage adduserbeanpage;
	private AvengerRootAccountPage rootAccounPage;
	private AvengerRootAccountPage2 rootaccountPage2;
	private ManualRootAccountBeanPage ManualRootAccountBeanPage;
	private ManualRootAccountBeanPage2 ManualRootAccountBeanPage2;
	private AvengerConfirmPasswordPage avengerconfirmpasswordpage;
	private CreatePasswordBeanPage createpasswordbeanpage;
	private AvengerEditRootAccountPage avengereditrootaccountpage;
	private AvengerAccountsPage avengeraccountspage;
	private BasePage basePage;
	private UserCreationUtility userCreationUtility;
	

	@BeforeClass(alwaysRun=true)
	public void beforeClass(ITestContext ctx) throws Exception {
		reasons= new Reasons("");
		statusValue=new ArrayList<String>();
		customReport = new CustomReport();
		setupfactory=new SetupFactory();
		accountBeansFactory=new BeanFactory();
		childaccountBeansPage = new CreateChildAccountBeansPage();
		adduserbeanpage=new AddUserBeanPage();
		ManualRootAccountBeanPage = new ManualRootAccountBeanPage();
		ManualRootAccountBeanPage2=new ManualRootAccountBeanPage2();
		setupfactory.ManualRootAccountByBeans(ManualRootAccountBeanPage,sEnvironment,surl);
		setupfactory.ManualRootAccountByBeans2(ManualRootAccountBeanPage2,sEnvironment);
		accountBeansFactory.AddUserBean(adduserbeanpage);
		accountBeansFactory.createChildAccountBeans(childaccountBeansPage);
		createpasswordbeanpage=new CreatePasswordBeanPage();
		locale = new Locale(language);
		basePage = new  BasePage();
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
		basePage= new BasePage(driver,customReport, new BasePage());loginPage = basePage.avengerLoginPage(driver,customReport, basePage);
		driver.manage().window().maximize();
		accountBeansFactory.CreatePasswordBean(createpasswordbeanpage);

	}
	
	@Test(description="Verify Build Numbers are Equal are not ", priority=1)
	public void VerifyBuildNumbers() 
	{
		String currentBuildNumber=SendMail.BuildNumber(Configuration.getAutomationURL());
		ArrayList<String> filedata=FileOperation.fileReadingNotepad(FileOperation.getFilePath(BUILDNUMBER));
		logger.info("file data from file"+filedata.get(0));
		logger.info("file data from URL"+currentBuildNumber);
		customReport.customizedReport(false, filedata.get(0).equals(currentBuildNumber), statusValue, driver, sTestcaseName); 
		FileOperation.writeFile(BUILDNUMBER, currentBuildNumber);
		customReport.checkinglist(statusValue);
	}
	
	
	@Test(description="Adding an Root Account", priority=2)
	public void createRootAccount() 
	{
		logger.info(" I am in Root account test cases");
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("The testcase running is"+sTestcaseName);
		launchURL(surl);
		rootAccounPage = basePage.avengerRootAccountPage(driver,customReport, basePage);
		rootaccountPage2=rootAccounPage.addRootAccount(ManualRootAccountBeanPage);
		rootaccountPage2.addRootAccount(ManualRootAccountBeanPage2);	  
		homePage = loginPage.loginAs(ManualRootAccountBeanPage.getUserName(),ManualRootAccountBeanPage.getPassword());
		customReport.customizedReport(true, homePage.verify_logoutButton(), statusValue, driver, sTestcaseName); 	
		customReport.checkinglist(statusValue);
	}



	@Test(description="Creating Automation Users",priority=3)
	public void createAutomationUsers()
	{

		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("The testcase running is"+sTestcaseName);

		JSONParser parser = new JSONParser();
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		launchURL(surl);
		homePage = loginPage.loginAs(ManualRootAccountBeanPage.getUserName(),ManualRootAccountBeanPage.getPassword());
		homePage.clickSettingsLink();
		try {
			logger.info("Environment is"+runtimeEnvironment().get("users"));
			FileReader fr = new FileReader(runtimeEnvironment().get("users"));
			Object obj = parser.parse(new FileReader(runtimeEnvironment().get("users")));
			JSONObject jsonObject = (JSONObject) obj;
			Map json = (Map)parser.parse(new FileReader(runtimeEnvironment().get("users")));
			Iterator iter = json.entrySet().iterator();
			while(iter.hasNext()){
				Map.Entry entry = (Map.Entry)iter.next();
				logger.info("Keys in json file is"+entry.getKey());
				JSONArray uservalues = (JSONArray) jsonObject.get(entry.getKey());
				Iterator<String> iterator =  uservalues.iterator();;
				while (iterator.hasNext()) {
					adduserbeanpage.setFirstname(iterator.next());
					adduserbeanpage.setLastname(iterator.next());
					adduserbeanpage.setContactemail(iterator.next());
					adduserbeanpage.setUsername(iterator.next());
					logger.info("User first name is"+adduserbeanpage.getFirstname());
					logger.info("User Last name is"+adduserbeanpage.getLastname());
					logger.info("User Contact email  is"+adduserbeanpage.getContactemail());
					logger.info("User  name is"+adduserbeanpage.getUsername());
					try{
						avengeruserdashboardpage=homePage.clickUsersLink();
						avengeradduserpage=avengeruserdashboardpage.clickAdduser();    
						avengeruserdashboardpage= avengeradduserpage.addUseraccount(adduserbeanpage);
						customReport.customizedReport(adduserbeanpage.getUsername(),avengeruserdashboardpage.verifycreationofuser(adduserbeanpage), statusValue, driver, sTestcaseName);
						customReport.customizedReport(adduserbeanpage.getUsername(),avengeruserdashboardpage.verifycreationofuser(adduserbeanpage), statusValue, driver, sTestcaseName);
						avengereditrootaccountpage= avengeruserdashboardpage.clicknewuser_link(adduserbeanpage);
						ArrayList<String> roles = new ArrayList<String>();
						roles.add("Account Admin");
						avengereditrootaccountpage.add_Roles(roles);
						avengeradduserpage.click_CreateButtonlocator();
						customReport.reporter("User Created with username:"+adduserbeanpage.getUsername(),"Role assigned to user is"+adduserbeanpage.getUserrole());
						avengeradduserpage=avengeruserdashboardpage.clickAdduser();
					}
					catch(TimeoutException e)
					{
						customReport.reporter("User Name already exists",adduserbeanpage.getUsername());
						e.printStackTrace();
					}
				}
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		} 
	}


	@Test(description="Creating Automation Users as Active",priority=5)
	public void createAutomationUsersActive() 
	{
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("The testcase running is"+sTestcaseName);

		JSONParser parser = new JSONParser();
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		launchURL(surl);
		homePage = loginPage.loginAs(ManualRootAccountBeanPage.getUserName(),ManualRootAccountBeanPage.getPassword());
		homePage.clickSettingsLink();
		try {
			logger.info("Environment is Active Automation Users");
			FileReader fr = new FileReader(ACTIVEAUTOMATIONUSERSFILE);
			Object obj = parser.parse(new FileReader(ACTIVEAUTOMATIONUSERSFILE));
			JSONObject jsonObject = (JSONObject) obj;
			Map json = (Map)parser.parse(new FileReader(ACTIVEAUTOMATIONUSERSFILE));
			Iterator iter = json.entrySet().iterator();
			while(iter.hasNext()){
				Map.Entry entry = (Map.Entry)iter.next();
				logger.info("Keys in json file is"+entry.getKey());
				JSONArray uservalues = (JSONArray) jsonObject.get(entry.getKey());
				Iterator<String> iterator =  uservalues.iterator();;
				while (iterator.hasNext()) {
					adduserbeanpage.setFirstname(iterator.next());
					adduserbeanpage.setLastname(iterator.next());
					adduserbeanpage.setContactemail(iterator.next());
					adduserbeanpage.setUsername(iterator.next());
					adduserbeanpage.setUserrole(iterator.next());
					adduserbeanpage.setLanguage(iterator.next());
					logger.info("User first name is"+adduserbeanpage.getFirstname());
					logger.info("User Last name is"+adduserbeanpage.getLastname());
					logger.info("User Contact email  is"+adduserbeanpage.getContactemail());
					logger.info("User  name is"+adduserbeanpage.getUsername());
					logger.info("User Role is"+adduserbeanpage.getUserrole());
					logger.info("User language is"+adduserbeanpage.getLanguage());
					try{
						avengeruserdashboardpage=homePage.clickUsersLink();
						avengeradduserpage=avengeruserdashboardpage.clickAdduser();    
						avengeruserdashboardpage= avengeradduserpage.addUseraccount(adduserbeanpage);
						customReport.customizedReport(adduserbeanpage.getUsername(),avengeruserdashboardpage.verifycreationofuser(adduserbeanpage), statusValue, driver, sTestcaseName);
						avengereditrootaccountpage= avengeruserdashboardpage.clicknewuser_link(adduserbeanpage);
						ArrayList<String> roles = new ArrayList<String>();
						roles.add(adduserbeanpage.getUserrole());
						if(!adduserbeanpage.getUserrole().equalsIgnoreCase("Media Viewer"))
							avengereditrootaccountpage.add_Roles(roles);
						avengeradduserpage.click_CreateButtonlocator();
						customReport.reporter("User Created with username:"+adduserbeanpage.getUsername(),"Role assigned to user is"+adduserbeanpage.getUserrole());
						customReport.customizedReport(adduserbeanpage.getUsername(),avengeruserdashboardpage.verifycreationofuser(adduserbeanpage), statusValue, driver, sTestcaseName);
						avengereditrootaccountpage= avengeruserdashboardpage.clicknewuser_link(adduserbeanpage);
						avengerconfirmpasswordpage=avengereditrootaccountpage.click_UserConfirmationURL();
						createpasswordbeanpage.setConfirmpassword("Password@123456");
						createpasswordbeanpage.setPassword("Password@123456");
						loginPage= avengerconfirmpasswordpage.passwordCreation(createpasswordbeanpage);
						homePage.click_logout();
						//homePage.clickLoginLink();
						homePage=loginPage.CreatedUserLogin(adduserbeanpage,createpasswordbeanpage);
						customReport.customizedReport(true,homePage.checkLogoutButton(), statusValue, driver,sTestcaseName);
						homePage.click_logout();
						homePage = loginPage.loginAs(ManualRootAccountBeanPage.getUserName(),ManualRootAccountBeanPage.getPassword());
						homePage.clickSettingsLink();
						avengeruserdashboardpage=homePage.clickUsersLink();
						avengeradduserpage=avengeruserdashboardpage.clickAdduser();
					}
					catch(TimeoutException e)
					{
						customReport.reporter("User Name already exists",adduserbeanpage.getUsername());
						e.printStackTrace();
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
	}



	
	@Test(description="Verify Child Account creation with  Valid Data",priority=4)
	public void createAccount() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("The testcase running is"+sTestcaseName);
		JSONParser parser = new JSONParser();
		System.setProperty("org.uncommons.reportng.escape-output", "false");

		try {
			launchURL(surl);
			customReport.reporter("Application launch with URL : ", surl);
			homePage = loginPage.loginAs(ManualRootAccountBeanPage.getUserName(),ManualRootAccountBeanPage.getPassword());
			homePage.clickSettingsLink();
			FileReader fr = new FileReader(runtimeEnvironment().get("accounts"));
			Object obj = parser.parse(new FileReader(runtimeEnvironment().get("accounts")));
			JSONObject jsonObject = (JSONObject) obj;
			Map json = (Map)parser.parse(new FileReader(runtimeEnvironment().get("accounts")));
			Iterator iter = json.entrySet().iterator();
			while(iter.hasNext()){
				Map.Entry entry = (Map.Entry)iter.next();
				logger.info("Keys in json file is"+entry.getKey());
				JSONArray uservalues = (JSONArray) jsonObject.get(entry.getKey());
				Iterator<String> iterator = uservalues.iterator();
				while (iterator.hasNext()) {
					childaccountBeansPage.setAccountname(iterator.next());
					childaccountBeansPage.setFirstname(iterator.next());
					childaccountBeansPage.setLastname(iterator.next());
					childaccountBeansPage.setAccounthostname(iterator.next());
					childaccountBeansPage.setContactemail(iterator.next());
					try
					{
						avengeraccountspage=homePage.clickAccountsTab();
						avengerCreatechildAccountPage=avengeraccountspage.click_addchildaccount();
						avengerCreatechildAccountPage.addChildAccount(childaccountBeansPage);
						avengerCreatechildAccountPage.click_CreateButton(childaccountBeansPage);
						customReport.customizedReport(childaccountBeansPage.getAccountname(), avengeraccountspage.verify_childaccountcreation(childaccountBeansPage), statusValue, driver, sTestcaseName);
						avengeraccountspage.click_addchildaccount();
					}
					catch(TimeoutException e)
					{
						customReport.reporter("<br>"+"Account already exists with Account Name ",childaccountBeansPage.getAccountname());

						e.printStackTrace();
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		} 
	}	

	@Test(description="Creation of User 1",priority=3)
	public void AT_verify_userCreation_1() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(Configuration.getAutomationUsername(),Configuration.getAutomationPassword());
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"1");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of User 2",priority=4)
	public void AT_verify_userCreation_2() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(Configuration.getAutomationUsername(),Configuration.getAutomationPassword());
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, uservalue+"2");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of User 3",priority=5)
	public void AT_verify_userCreation_3() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(Configuration.getAutomationUsername(),Configuration.getAutomationPassword());
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, "Account"+"1");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Creation of User for Role Setup",priority=6)
	public void AT_verify_RoleSetup() 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(Configuration.getAutomationUsername(),Configuration.getAutomationPassword());
		userCreationUtility.gridUserCreation(homePage,adduserbeanpage,avengeradduserpage, ROLESENVSETUPUSER);
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
		browserQuit();
	}
}
