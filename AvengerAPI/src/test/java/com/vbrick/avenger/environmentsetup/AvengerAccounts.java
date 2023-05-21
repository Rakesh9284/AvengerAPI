package com.vbrick.avenger.environmentsetup;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
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
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AddUserBeanPage;
import com.vbrick.avenger.dao.AvengerGroupsBeanPage;
import com.vbrick.avenger.dao.AvengerHomeBeanPage;
import com.vbrick.avenger.dao.CreateChildAccountBeansPage;
import com.vbrick.avenger.dao.CreatePasswordBeanPage;
import com.vbrick.avenger.dao.EditRootAccountBeanPage;
import com.vbrick.avenger.dao.LoginBeanPage;
import com.vbrick.avenger.dao.UserPasswordParametersBean;
import com.vbrick.avenger.pageobjects.AvengerAccountsPage;
import com.vbrick.avenger.pageobjects.AvengerAddUserPage;
import com.vbrick.avenger.pageobjects.AvengerCreateChildAccountPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;
import com.vbrick.avenger.setup.UserCreationUtility;

public class AvengerAccounts extends TestBase{
	private static Logger logger = Logger.getLogger(AvengerAccounts.class);
	private WebDriver driver;
	private AvengerLoginPage loginPage;
	private AvengerHomePage homePage;
	private AvengerCreateChildAccountPage avengerCreatechildAccountPage;
	private List<String> statusValue;
	private CustomReport customReport;
	private String sTestcaseName;
	private ResourceBundle bundle;
	private Locale locale;
	private BeanFactory accountBeansFactory;
	private CreateChildAccountBeansPage childaccountBeansPage;
	private LoginBeanPage loginbeanpage;
	private AddUserBeanPage adduserbeanpage;
	private EditRootAccountBeanPage editrootaccountbeanpage;
	private AvengerHomeBeanPage avengerhomebeanpage;
	private CreatePasswordBeanPage createpasswordbeanpage;
	private AvengerGroupsBeanPage avengergroupsbeanpage;
	private UserPasswordParametersBean userpasswordparametersbean;
	private AvengerAccountsPage avengeraccountspage;
	private BasePage basePage;
	private CreateChildAccountBeansPage billinginfobeanpage;
	//private LinkedList<String> hostNames=null;
	//private String shostName_sub[]= {"a","b","c","d","e","f"}; 
	private UserCreationUtility userCreationUtility;
	private AvengerAddUserPage avengeradduserpage;
	private String sUserName=null,sPassword=null;
	
	/*private void setHostNames1() {
		hostNames = new LinkedList<String>();
		logger.info("Lenght"+shostName_sub.length);
		for (int i = 0; i < shostName_sub.length; i++) {
			if (surl.contains("31")) {
				saccountName = "Account31";
				hostNames.add(start31 + shostName_sub[i] + end31);
			} else {
				saccountName = "Account22";
				hostNames.add(start22 + shostName_sub[i] + end22);

			}
		}

	}*/


	@BeforeClass(alwaysRun=true)
	public void beforeClass(ITestContext ctx) throws Exception {
		//setHostNames();
		/*logger.info("-host0-"+hostNames.get(0));

		logger.info("-host1-"+hostNames.get(1));
		logger.info("-host2-"+hostNames.get(2));
		logger.info("-host3-"+hostNames.get(3));
		logger.info("-host4-"+hostNames.get(4));
		logger.info("-host5-"+hostNames.get(5));
	//	logger.info("-host6-"+hostNames.get(6));
		
		logger.info("Sub Account"+shostName_sub[0]);
		logger.info("Sub Account"+shostName_sub[1]);
		logger.info("Sub Account"+shostName_sub[2]);
		logger.info("Sub Account"+shostName_sub[3]);
		logger.info("Sub Account"+shostName_sub[4]);
		logger.info("Sub Account"+shostName_sub[5]);
*/
		logger.info("-ACCOUNTS---"+surl+"--"+surlA+"--"+surlB+"--"+surlC+"--"+surlD+"--"+surlE+"--"+surlF);
		statusValue=new ArrayList<String>();
		customReport = new CustomReport();
		accountBeansFactory=new BeanFactory();
		childaccountBeansPage = new CreateChildAccountBeansPage();
		adduserbeanpage=new AddUserBeanPage();
		loginbeanpage=new LoginBeanPage();
		editrootaccountbeanpage= new EditRootAccountBeanPage();
		avengerhomebeanpage = new AvengerHomeBeanPage();
		avengergroupsbeanpage = new AvengerGroupsBeanPage();
		createpasswordbeanpage = new CreatePasswordBeanPage();
		userpasswordparametersbean=new UserPasswordParametersBean();
		locale = new Locale(language);
		sUserName = "Account1";
		sPassword = "Password@123";
		basePage = new  BasePage();
		String suiteName = ctx.getCurrentXmlTest().getSuite().getName();
		logger.info("The suite name in Before class is "+suiteName);
		billinginfobeanpage=new CreateChildAccountBeansPage();
		userCreationUtility= new UserCreationUtility(suiteName);

	}



	@BeforeMethod(alwaysRun=true)
	@Parameters(value = {"sbrowser","sgrid"/* , "surl", "sUserName", "sPassword"*/})
	public void setUP(@Optional(SBROWSER)String sbrowser,@Optional(SVERSION) String sgrid/*, String sUserName,String sPassword*/) throws MalformedURLException 
	{
		logger.info("Version value is"+sgrid);
		driver = initializeDriver(sbrowser,sgrid/*, surl ,sversion,sPlatform*/ );
		logger.info("The driver value is "+driver);
		logger.info("Browser name is"+sbrowser);
		bundle=ResourceBundle.getBundle("ResourceBundle.BundleFile",locale);
		basePage= new BasePage(driver,customReport, new BasePage());
		loginPage = basePage.avengerLoginPage(driver,customReport, basePage);
		driver.manage().window().maximize();
		customReport.reset();
		accountBeansFactory.createChildAccountBeans(childaccountBeansPage);
		accountBeansFactory.LoginPageByBeans(loginbeanpage);
		accountBeansFactory.AddUserBean(adduserbeanpage);
		accountBeansFactory.EditRootAccountBean(editrootaccountbeanpage);
		accountBeansFactory.AvengerHomePageByBeans(avengerhomebeanpage);
		accountBeansFactory.CreatePasswordBean(createpasswordbeanpage);
		accountBeansFactory.AvengergroupsBean(avengergroupsbeanpage);
		accountBeansFactory.UserpasswordparameterBean(userpasswordparametersbean);
		accountBeansFactory.createChildAccountBeans(billinginfobeanpage);
		launchURL(surl,driver);
	}

	
	
	@Test(description="Creating Mutiple Accounts")
	public void CreateAccounts_31A() 
	{
		logger.info("-----------"+this.getClass().getSimpleName());
		logger.info("sTestcaseName"+sTestcaseName);
		customReport.reporter("Application launch with URL : ",surl);
		homePage = loginPage.loginAs(sUserName,sPassword);
		homePage.clickSettingsLink();
		//logger.info("@@@"+hostNames.size());
		avengeraccountspage = homePage.clickAccountsTab();
		avengerCreatechildAccountPage=avengeraccountspage.click_addchildaccount();
		childaccountBeansPage.setAccountname(accountBeansFactory.selectedAccount(surl)+"a");
		childaccountBeansPage.setAccounthostname(surlA.replace("https:", "").replace("/", "").replace("http:",""));//
		childaccountBeansPage.setMaximumactiveusers("200");
		avengerCreatechildAccountPage.addChildAccount(childaccountBeansPage);
		avengeraccountspage=avengerCreatechildAccountPage.click_CreateButton(childaccountBeansPage);
		customReport.customizedReport(childaccountBeansPage.getAccountname(),avengeraccountspage.verify_childaccountcreation(childaccountBeansPage), statusValue, driver, sTestcaseName);
		avengeraccountspage.click_newChildAccountCreation(childaccountBeansPage);
		userCreationUtility.gridUserCreation_Account(homePage,adduserbeanpage,avengeradduserpage, uservalue+"1");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
		}
	
	
	@Test(description="Creating Mutiple Accounts")
	public void CreateAccounts_31B() 
	{
		logger.info("-----------"+this.getClass().getSimpleName());
		logger.info("sTestcaseName"+sTestcaseName);
		customReport.reporter("Application launch with URL : ",surl);
		homePage = loginPage.loginAs(sUserName,sPassword);
		homePage.clickSettingsLink();
		//logger.info("@@@"+hostNames.size());
		avengeraccountspage = homePage.clickAccountsTab();
		avengerCreatechildAccountPage=avengeraccountspage.click_addchildaccount();
		childaccountBeansPage.setAccountname(accountBeansFactory.selectedAccount(surl)+"b");
		childaccountBeansPage.setAccounthostname(surlB.replace(URLTYPE, "").replace("/", "").replace("http:",""));//
		childaccountBeansPage.setMaximumactiveusers("200");
		avengerCreatechildAccountPage.checkLicensedGuestAccessCheckBox(bundle.getString("accountpage.licensedguesstaccesstext"));
		avengerCreatechildAccountPage.addChildAccount(childaccountBeansPage);
		avengeraccountspage=avengerCreatechildAccountPage.click_CreateButton(childaccountBeansPage);
		customReport.customizedReport(childaccountBeansPage.getAccountname(),avengeraccountspage.verify_childaccountcreation(childaccountBeansPage), statusValue, driver, sTestcaseName);
		avengeraccountspage.click_newChildAccountCreation(childaccountBeansPage);
		userCreationUtility.gridUserCreation_Account(homePage,adduserbeanpage,avengeradduserpage, uservalue+"1");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
		}
	
	
	//This test case covers "AT_Verify_UserLoginTheirOWNAccount_NavigateThroughInterface_AccessChildAccounts_Av868"
	@Test(description="Creating Mutiple Accounts")
	public void CreateAccounts_31C() 
	{
		logger.info("-----------"+this.getClass().getSimpleName());
		logger.info("sTestcaseName"+sTestcaseName);
		customReport.reporter("Application launch with URL : ",surl);
		homePage = loginPage.loginAs(sUserName,sPassword);
		homePage.clickSettingsLink();
		//logger.info("@@@"+hostNames.size());
		avengeraccountspage = homePage.clickAccountsTab();
		avengerCreatechildAccountPage=avengeraccountspage.click_addchildaccount();
		childaccountBeansPage.setAccountname(accountBeansFactory.selectedAccount(surl)+"c");
		childaccountBeansPage.setAccounthostname(surlC.replace(URLTYPE, "").replace("/", "").replace("http:",""));//
		childaccountBeansPage.setMaximumactiveusers("200");
		avengerCreatechildAccountPage.addChildAccount(childaccountBeansPage);
		avengeraccountspage=avengerCreatechildAccountPage.click_CreateButton(childaccountBeansPage);
		customReport.customizedReport(childaccountBeansPage.getAccountname(),avengeraccountspage.verify_childaccountcreation(childaccountBeansPage), statusValue, driver, sTestcaseName);
		avengeraccountspage.click_newChildAccountCreation(childaccountBeansPage);
		userCreationUtility.gridUserCreation_Account(homePage,adduserbeanpage,avengeradduserpage, uservalue+"1");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
		}
	
	
	@Test(description="Creating Mutiple Accounts")
	public void CreateAccounts_31D() 
	{
		logger.info("-----------"+this.getClass().getSimpleName());
		logger.info("sTestcaseName"+sTestcaseName);
		customReport.reporter("Application launch with URL : ",surlD);
		homePage = loginPage.loginAs(sUserName,sPassword);
		homePage.clickSettingsLink();
		//logger.info("@@@"+hostNames.size());
		avengeraccountspage = homePage.clickAccountsTab();
		avengerCreatechildAccountPage=avengeraccountspage.click_addchildaccount();
		childaccountBeansPage.setAccountname(accountBeansFactory.selectedAccount(surl)+"d");
		childaccountBeansPage.setAccounthostname(surlD.replace(URLTYPE, "").replace("/", "").replace("http:",""));//
			childaccountBeansPage.setMaximumactiveusers("200");
		avengerCreatechildAccountPage.addChildAccount(childaccountBeansPage);
		avengeraccountspage=avengerCreatechildAccountPage.click_CreateButton(childaccountBeansPage);
		customReport.customizedReport(childaccountBeansPage.getAccountname(),avengeraccountspage.verify_childaccountcreation(childaccountBeansPage), statusValue, driver, sTestcaseName);
		avengeraccountspage.click_newChildAccountCreation(childaccountBeansPage);
		userCreationUtility.gridUserCreation_Account(homePage,adduserbeanpage,avengeradduserpage, uservalue+"1");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);

	}
	
	
	@Test(description="Creating Mutiple Accounts")
	public void CreateAccounts_31E() 
	{
		logger.info("-----------"+this.getClass().getSimpleName());
		logger.info("sTestcaseName"+sTestcaseName);
		customReport.reporter("Application launch with URL : ",surl);
		homePage = loginPage.loginAs(sUserName,sPassword);
		homePage.clickSettingsLink();
		//logger.info("@@@"+hostNames.size());
		avengeraccountspage = homePage.clickAccountsTab();
		avengerCreatechildAccountPage=avengeraccountspage.click_addchildaccount();
		childaccountBeansPage.setAccountname(accountBeansFactory.selectedAccount(surl)+"e");
		childaccountBeansPage.setAccounthostname(surlE.replace(URLTYPE, "").replace("/", "").replace("http:",""));//
			childaccountBeansPage.setMaximumactiveusers("200");
		avengerCreatechildAccountPage.addChildAccount(childaccountBeansPage);
		avengeraccountspage=avengerCreatechildAccountPage.click_CreateButton(childaccountBeansPage);
		customReport.customizedReport(childaccountBeansPage.getAccountname(),avengeraccountspage.verify_childaccountcreation(childaccountBeansPage), statusValue, driver, sTestcaseName);
		avengeraccountspage.click_newChildAccountCreation(childaccountBeansPage);
		userCreationUtility.gridUserCreation_Account(homePage,adduserbeanpage,avengeradduserpage, uservalue+"1");
		customReport.customizedReport(adduserbeanpage.getUsername(),homePage.verify_userName(adduserbeanpage.getUsername()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
		}
	
	@Test(description="Creating Mutiple Accounts")
	public void CreateAccounts_31F() 
	{
		logger.info("-----------"+this.getClass().getSimpleName());
		logger.info("sTestcaseName"+sTestcaseName);
		customReport.reporter("Application launch with URL : ",surlF);
		homePage = loginPage.loginAs(sUserName,sPassword);
		homePage.clickSettingsLink();
		//logger.info("@@@"+hostNames.size());
		avengeraccountspage = homePage.clickAccountsTab();
		avengerCreatechildAccountPage=avengeraccountspage.click_addchildaccount();
		childaccountBeansPage.setAccountname(accountBeansFactory.selectedAccount(surl)+"f");
		childaccountBeansPage.setAccounthostname(surlF.replace(URLTYPE, "").replace("/", "").replace("http:",""));//
		childaccountBeansPage.setMaximumactiveusers("200");
		avengerCreatechildAccountPage.addChildAccount(childaccountBeansPage);
		avengeraccountspage=avengerCreatechildAccountPage.click_CreateButton(childaccountBeansPage);
		customReport.customizedReport(childaccountBeansPage.getAccountname(),avengeraccountspage.verify_childaccountcreation(childaccountBeansPage), statusValue, driver, sTestcaseName);
		avengeraccountspage.click_newChildAccountCreation(childaccountBeansPage);
		userCreationUtility.gridUserCreation_Account(homePage,adduserbeanpage,avengeradduserpage, uservalue+"1");
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
