 package com.vbrick.avenger.createuserapi_AV_9874_testcases;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
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
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbrick.avenger.apibeans.AddUserApiBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AddUserBeanPage;
import com.vbrick.avenger.dao.CreatePasswordBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerAddUserPage;
import com.vbrick.avenger.pageobjects.AvengerConfirmPasswordPage;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerEditRootAccountPage;
import com.vbrick.avenger.pageobjects.AvengerEditVideoSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerUserDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserAccountService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.ConstantsValue;
import com.vbricks.avenger.utils.HttpStatusCode;

public class BulkUserCreation extends TestBase {
	private static Logger logger = Logger.getLogger(BulkUserCreation.class);
	private AvengerLoginPage loginPage;
	private AvengerHomePage homePage;
	private List<String> statusValue;
	private CustomReport customReport;
	@SuppressWarnings("unused")
	private Reasons reasons;
	private String sTestcaseName;
	private ResourceBundle bundle;
	private Locale locale;
	private BeanFactory accountBeansFactory;
	private MailinatorBeanPage mailinatorbeanpage;
	private ReadAndWriteToJSON readgriduserdata;
	private Map<String, String> userdata;
	private BasePage basePage;
	public ApiBeanFactory apibeanfactory;
    private ApiUtils apiutils;
	private AvengerHomePage avengerHomePage;
	private AvengerUserDashboardPage avengeruserdashboardpage;
	private HashMap<String,String> loginapiresponse;
	private HashMap<String,String> createuserdetailsbean;
	private CreatePasswordBeanPage createpasswordbeanpage;
	private AvengerEditRootAccountPage editrootaccountpage;
	private AvengerConfirmPasswordPage avengerconfirmpasswordpage;
	@BeforeClass(alwaysRun=true)
	public void beforeClass(ITestContext context) throws Exception {
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
		apiutils=new ApiUtils();
	/*	UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(ConstantsValue.accountAdmin, surl);
		HashMap<String, String> responsedata = userservices.getRolesApi(loginapiresponse, ConstantsValue.mediaContributor);
		context.setAttribute("rolesapiresponse", responsedata);*/
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
 
	
	@Test(description="To create media Contributor user "/*dataProvider="testcreation"*/)
	public void TC_01_POST_Create_Media_Contributor_User_Postive(ITestContext context,String username ,String filed ) 
	{
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
 
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(username), surl);
		HashMap<String, String> responsedata = userservices.getRolesApi(loginapiresponse, ConstantsValue.mediaContributor);
		context.setAttribute("rolesapiresponse", responsedata);
		
		UserAccountService useraccountservice = new UserAccountService();
		ApiBeanFactory bFactory = new ApiBeanFactory();
		AddUserApiBean userbean = new AddUserApiBean();
		bFactory.AddUserBean(userbean);
		userbean.setFirstname(filed);
		userbean.setLastname(filed);
		userbean.setUsername(filed);
		// Convert bean to map
		ObjectMapper mapper = new ObjectMapper();

		createuserdetailsbean = mapper.convertValue(userbean, HashMap.class);
	//	HashMap<String,String> rolesapiresponse=(HashMap<String, String>) context.getAttribute("rolesapiresponse");
		HashMap<String,String> usercreateApiResponseData=useraccountservice.createUserAPI(responsedata, ConstantsValue.inputType[0], ConstantsValue.validData, createuserdetailsbean);
   //     Assert.assertEquals(usercreateApiResponseData.get("responseHttpCode")+usercreateApiResponseData.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    Assert.assertNotNull(usercreateApiResponseData.get("userId"));
	 
        //Selenium Level Code
		launchURL(surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengeruserdashboardpage=avengerHomePage.clickUsersLink();
	 	createpasswordbeanpage=new CreatePasswordBeanPage();
		editrootaccountpage=avengeruserdashboardpage.clicknewuser_link(userbean);
		avengerconfirmpasswordpage=editrootaccountpage.click_UserConfirmationURL();
		createpasswordbeanpage.setPassword("vbqavbqa");
		createpasswordbeanpage.setConfirmpassword("vbqavbqa");
		createpasswordbeanpage.setSecurityquestion("What was your first car?");
		createpasswordbeanpage.setTypeyouranswer("BMW");
		loginPage= avengerconfirmpasswordpage.passwordCreation(createpasswordbeanpage);
 
		avengerHomePage.click_logout();
 
	 
	}
	
 	@DataProvider(name = "testcreation",parallel=true)
	public Object[][] createData1() {
	 return new Object[][] {
	   { "nageswara","griduser001"},
	   { "kalyan","griduser002"},
	   { "nageswara1","griduser003"},
	   { "aadmin","griduser004"},
	   { "dadmin","griduser005"},
	   { "madmin2","griduser006"},
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
