package com.vbrick.avenger.createuserapi_AV_9874_testcases;

 
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.vbrick.Exception.CustomReport;
import com.vbrick.Exception.Reasons;
import com.vbrick.avenger.apibeans.AddUserApiBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AddUserBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerEditRootAccountPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerUserDashboardPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IUserAccountsService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.UserAccountService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.ConstantsValue;
import com.vbricks.avenger.utils.HttpStatusCode;


public class LoginUserAPI_9874 extends TestBase {
	
	
	private static Logger logger = Logger.getLogger(LoginUserAPI_9874.class);
	private AvengerLoginPage loginPage;
	private List<String> statusValue;
	private CustomReport customReport;
	@SuppressWarnings("unused")
	private Reasons reasons;
	private String sTestcaseName;
	private ResourceBundle bundle;
	private Locale locale;
	private BeanFactory accountBeansFactory;
	private MailinatorBeanPage mailinatorbeanpage;
	private HashMap<String,String> createuserdetailsbean;
	private ReadAndWriteToJSON readgriduserdata;
	private Map<String, String> userdata;
	private BasePage basePage;
	public ApiBeanFactory apibeanfactory;
	private AssertionAPIResponse assertionapiresponse;
	private AvengerHomePage avengerHomePage;
	private HashMap<String,String> loginapiresponse;
	private HashMap<String,String> loginapiresponse1;
	private ApiUtils apiutils;
	@BeforeClass(alwaysRun=true)
	public void beforeClass() throws Exception {
		reasons= new Reasons("");
		statusValue=new ArrayList<String>();
		customReport = new CustomReport();
		accountBeansFactory=new BeanFactory();
		mailinatorbeanpage=new MailinatorBeanPage();
		new FileUploadBeanPage();
		locale = new Locale(language);
		readgriduserdata = new ReadAndWriteToJSON();
		userdata = readgriduserdata.readGridUserData(this.getClass().getSimpleName()); //("user52");
		sUserName = userdata.get("Username");
		sPassword = userdata.get("Password");
		assertionapiresponse=new AssertionAPIResponse(); 
		apiutils=new ApiUtils(); 
 	}
	
	@BeforeMethod(alwaysRun=true)
	@Parameters(value = {"sbrowser","sgrid"})
	public void setUP(@Optional(SBROWSER)String sbrowser,@Optional(SVERSION) String sgrid) throws MalformedURLException 
	{
		customReport.reset();
		driver = initializeDriver(sbrowser,sgrid);
		logger.info("The driver value is "+driver);
		bundle=ResourceBundle.getBundle("ResourceBundle.BundleFile",locale);
		logger.info("value in bundle is"+bundle.getKeys());
		basePage= new BasePage(driver,customReport, new BasePage());
		loginPage = basePage.avengerLoginPage(driver,customReport, basePage);
		driver.manage().window().maximize();
		accountBeansFactory.MailinatorBean(mailinatorbeanpage);
		
	}
	
	@Test(description="To check Alluser is logging successfully with valid credential ",dataProvider="AlluserList",groups = {LOGINUSERAPI})
	public void TC_01_POST_UserLogin_As_AccountAdmin_Postive(String username,String password) 
	{
	 	UserServices userservices = new UserServices();
	 	loginapiresponse = userservices.doLogin(apiutils.userJson(username,password), surl);
		logger.info("Login API response Code :::" + loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName); 
	
	    //Selenium Level Code
		launchURL(surl);
		avengerHomePage=loginPage.loginAs(username,password);
		customReport.customizedReport(username +" "+ username,avengerHomePage.verify_userName(username), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
	}
	
 
	@DataProvider(name = "AlluserList")
	public Object[][] createData1() {
	 return new Object[][] {
	   {IUsersList.loginaccountadmin, sPassword },
	   {IUsersList.Logineventhost,sPassword},
	   {IUsersList.logineventadmin,sPassword},
	   {IUsersList.loginmediaadmin, sPassword},
	   {IUsersList.loginmediaviewer,sPassword},
	   {IUsersList.loginmediaviewer, sPassword},
	   
	 };
	}

 	 
	@Test(description="To check Alluser is logging failed with Invalid  credential  ",dataProvider="InvalidUserList",groups = {LOGINUSERAPI})
	public void TC_02_POST_UserLogin_As_AllusersInvalidUserName_Negative(String username,String password) 
	{
	 	UserServices userservices = new UserServices(); 
	 	loginapiresponse = userservices.doLogin(apiutils.userJson(username,password), surl);
		logger.info("Login API response Code :::" + loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName); 
        
	}
	
	@DataProvider(name = "InvalidUserList")
	public Object[][] userdetails() {
	 return new Object[][] {
	   {"Test"+RandomStringUtils.random(5), sPassword},
	   {"Test1"+RandomStringUtils.random(5),sPassword},
	   {"test3"+RandomStringUtils.random(5), sPassword},
	   {"test4"+RandomStringUtils.random(5), sPassword},
	   {"test5"+RandomStringUtils.random(5), sPassword},
	   {"test6"+RandomStringUtils.random(5), sPassword},
	   
	 };
	}
	 
	
	@Test(description="To check Alluser is logging failed with Invalid  credential ",dataProvider="InvalidPasswordList",groups = {LOGINUSERAPI})
	public void TC_03_POST_UserLogin_As_AllusersInvalidPassword_Negative(String username,String password) 
	{
	 	UserServices userservices = new UserServices();
	 	loginapiresponse = userservices.doLogin(apiutils.userJson(username,password), surl);
		logger.info("Login API response Code :::" + loginapiresponse); 
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName); 
		  
	}
	@DataProvider(name = "InvalidPasswordList")
	public Object[][] createData() {
	 return new Object[][] {
		 {IUsersList.loginaccountadmin, "vbqavbqa123" },
		   {IUsersList.logineventadmin, "vbqavbqa123"},
		   {IUsersList.Logineventhost, "vbqavbqa123"},
		   {IUsersList.loginmediaadmin, "vbqavbqa123"},
		   {IUsersList.loginmediaviewer, "vbqavbqa123"},
		   {IUsersList.loginmediaviewer, "vbqavbqa123"},
	 };
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
