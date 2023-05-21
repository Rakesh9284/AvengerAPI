package com.vbrick.avenger.mobileappsupport;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;


import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
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
import com.vbrick.avenger.apibeans.AddGroupBean;
import com.vbrick.avenger.apibeans.AddTeamBean;
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbrick.avenger.apibeans.AddVideoCommentBean;
 
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AddUserBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerEditRootAccountPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerUserDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbrick.avenger.videocommentapi_AV_9879_testcases.SubmitVideosComments_AV_9879;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IDeleteVideoService;
import com.vbricks.avenger.service.IUserAccountsService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.CreateGroupAPI;
import com.vbricks.avenger.serviceimpl.CreateTeamAPI;
import com.vbricks.avenger.serviceimpl.DeleteGroupAPI;
import com.vbricks.avenger.serviceimpl.DeleteTeamAPI;
import com.vbricks.avenger.serviceimpl.DeleteVideosAPI;
 
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserDetailsAPI;
import com.vbricks.avenger.serviceimpl.UserEmailAPI;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.ConstantsValue;
import com.vbricks.avenger.utils.HttpStatusCode;

public class CheckForUploadRightsInUserAPI_AV_28488 extends TestBase {

	private static Logger logger = Logger.getLogger(CheckForUploadRightsInUserAPI_AV_28488.class);
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
    private ApiUtils apiutils;
	private AvengerHomePage avengerHomePage;
	private AvengerUserDashboardPage avengeruserdashboardpage;
	private BasePage basePage;
	private AddUploadVideoBean adduploadvideobean;
	public ApiBeanFactory apibeanfactory;
	public AddVideoCommentBean addvideocommentbean;
 	private HashMap<String, String> loginapiresponse;
 	private HashMap<String, String> loginapiresponse1;
 	private HashMap<String, String> creategroupapirespone;
 	private HashMap<String, String> createteamapirespone;
	private HashMap<String, String> uploadvidoeapiresponse;
	private HashMap<String, String> deleteTeamapiresponse;
	private HashMap<String, String> deleteGroupapiresponse;
	private HashMap<String,String> loginapiresponse_mediaviewer;
	private HashMap<String,String> loginapiresponse_eventadmin;
	private HashMap<String,String> loginapiresponse_mediacontributor;
    private AssertionAPIResponse assertionapiresponse;
	private AddTeamBean addteambean;
	private AddGroupBean addgroupbean;
    private AvengerEditRootAccountPage avengereditrootaccountpage; 
	@BeforeClass(alwaysRun = true)
	public void beforeClass() throws Exception {
		reasons = new Reasons("");
		statusValue = new ArrayList<String>();
		customReport = new CustomReport();
		accountBeansFactory = new BeanFactory();
		mailinatorbeanpage = new MailinatorBeanPage();
		new FileUploadBeanPage();
		locale = new Locale(language);
		readgriduserdata = new ReadAndWriteToJSON();
		userdata = readgriduserdata.readGridUserData(this.getClass().getSimpleName()); // ("user52");
		sUserName = userdata.get("Username");
		sPassword = userdata.get("Password");
		addgroupbean=new AddGroupBean();
		addteambean=new AddTeamBean();
		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		addvideocommentbean = new AddVideoCommentBean();
		assertionapiresponse =new AssertionAPIResponse();
		apiutils=new ApiUtils();
	}

	@BeforeMethod(alwaysRun = true)
	@Parameters(value = { "sbrowser", "sgrid" })
	public void setUP(@Optional(SBROWSER) String sbrowser, @Optional(SVERSION) String sgrid)throws MalformedURLException {
		customReport.reset();
		driver = initializeDriver(sbrowser, sgrid);
		logger.info("The driver value is " + driver);
		bundle = ResourceBundle.getBundle("ResourceBundle.BundleFile", locale);
		logger.info("value in bundle is" + bundle.getKeys());
		basePage = new BasePage(driver, customReport, new BasePage());
		loginPage = basePage.avengerLoginPage(driver, customReport, basePage);
		driver.manage().window().maximize();
		accountBeansFactory.MailinatorBean(mailinatorbeanpage);

	}

	@Test(description = "To verify UploadVideo & CreateEvent Rights of an accountadmin with accountadmin",groups = {GETUSERDETAILS_USERID})	
	// The Rights should be as mentioned for the mentioned AA : "canUpload": true,  "canCreateEvents": true. Since Account Admin will have both the rights	 
	public void TC_01_GET_UploadRights_of_an_AccountAdmin_with_AccoutAdmin_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		UserDetailsAPI userDetailsAPI=new UserDetailsAPI();
		HashMap<String, String> userdetailapirespone = userDetailsAPI.CheckForUploadRightsInUserAPI(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(userdetailapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+userdetailapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, userdetailapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+userdetailapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		customReport.customizedReport(true, userdetailapirespone.get("canUpload").contains("true"), statusValue, driver, sTestcaseName);  
		customReport.customizedReport(true, userdetailapirespone.get("canCreateEvents").contains("true"), statusValue, driver, sTestcaseName);  
		customReport.checkinglist(statusValue);		
		
	}
	
	@Test(description = "To verify UploadVideo & CreateEvent Rights of a MediaViewer with accountadmin",groups = {GETUSERDETAILS_USERID})	
	// The Rights should be as mentioned for the mentioned AA : "canUpload": false,  "canCreateEvents": false. Since MediaViewer will not have both the rights 
	public void TC_02_GET_UploadRights_of_a_MediaViewer_with_AccoutAdmin_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.UploadRidhtsformediaviewer1), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);
		
		UserDetailsAPI userDetailsAPI=new UserDetailsAPI();
		HashMap<String, String> userdetailapirespone = userDetailsAPI.CheckForUploadRightsInUserAPI(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(userdetailapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+userdetailapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, userdetailapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+userdetailapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		customReport.customizedReport(false, userdetailapirespone.get("canUpload").contains("true"), statusValue, driver, sTestcaseName);  
		customReport.customizedReport(false, userdetailapirespone.get("canCreateEvents").contains("true"), statusValue, driver, sTestcaseName);  
		customReport.checkinglist(statusValue);		
		
	}
	
	@Test(description = "To verify UploadVideo & CreateEvent Rights of a MediaContributor with accountadmin",groups = {GETUSERDETAILS_USERID})	
	// The Rights should be as mentioned for the mentioned AA : "canUpload": True,  "canCreateEvents": false. Since MediaContributor can upload a video but cannot create an event	 
	public void TC_03_GET_UploadRights_of_a_MediaContributor_with_AccoutAdmin_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.UploadRightsformediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);
		
		UserDetailsAPI userDetailsAPI=new UserDetailsAPI();
		HashMap<String, String> userdetailapirespone = userDetailsAPI.CheckForUploadRightsInUserAPI(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(userdetailapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+userdetailapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, userdetailapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+userdetailapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		customReport.customizedReport(true, userdetailapirespone.get("canUpload").contains("true"), statusValue, driver, sTestcaseName);  
		customReport.customizedReport(false, userdetailapirespone.get("canCreateEvents").contains("true"), statusValue, driver, sTestcaseName);  
		customReport.checkinglist(statusValue);		
		
	}
	
	@Test(description = "To verify UploadVideo & CreateEvent Rights of a EventHost with accountadmin",groups = {GETUSERDETAILS_USERID})	
	// The Rights should be as mentioned for the mentioned AA : "canUpload": False,  "canCreateEvents": True. Since EventHost cannot upload a video but can create an event	 
	public void TC_04_GET_UploadRights_of_an_EventHost_with_AccoutAdmin_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.UploadRightsforeventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);
		
		UserDetailsAPI userDetailsAPI=new UserDetailsAPI();
		HashMap<String, String> userdetailapirespone = userDetailsAPI.CheckForUploadRightsInUserAPI(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(userdetailapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+userdetailapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, userdetailapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+userdetailapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		customReport.customizedReport(false, userdetailapirespone.get("canUpload").contains("true"), statusValue, driver, sTestcaseName);  
		customReport.customizedReport(true, userdetailapirespone.get("canCreateEvents").contains("true"), statusValue, driver, sTestcaseName);  
		customReport.checkinglist(statusValue);		
		
	}
	
	@Test(description = "To verify UploadVideo & CreateEvent Rights of a MediaViewer after assgning MV to a group having AccountAdmin role with accountadmin",groups = {GETUSERDETAILS_USERID})	
	// The Rights should be as mentioned for the mentioned AA : "canUpload": true,  "canCreateEvents": true. Since MediaViewer is assinged to a group having account admin role	 
	public void TC_05_GET_UploadRights_of_a_MediaViewer_with_AccoutAdmin_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
				
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
	
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.UploadRightsformediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		logger.info("Event Admin Login API response Code :::" + loginapiresponse1);	
		
		//assigning the user to a group
		//getting the userIds
		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		String[] userIds = {loginapiresponse1.get("userId")};
		addgroupbean.setUserids(userIds);
												
		//getting roleids
		UserServices userServices=new UserServices();
		HashMap<String, String> userroleapirespone = userServices.getRolesApi(loginapiresponse,IAPIConstantCodes.ACCOUNTADMIN);
		String roleid=apiutils.formatingapiresponse(userroleapirespone.get(IAPIConstantCodes.ROLEID));
		String[] roleIds = {userroleapirespone.get("roleId")};
		addgroupbean.setRoleids(roleIds);
										
		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
		creategroupapirespone = creategroupAPI.createGroup(loginapiresponse,creategroupjson);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);								
		
		UserDetailsAPI userDetailsAPI=new UserDetailsAPI();
		HashMap<String, String> userdetailapirespone = userDetailsAPI.CheckForUploadRightsInUserAPI(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(userdetailapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+userdetailapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, userdetailapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+userdetailapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		customReport.customizedReport(true, userdetailapirespone.get("canUpload").contains("true"), statusValue, driver, sTestcaseName);  
		customReport.customizedReport(true, userdetailapirespone.get("canCreateEvents").contains("true"), statusValue, driver, sTestcaseName);  
		customReport.checkinglist(statusValue);		
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void browserClose(ITestResult result) {
		logger.info("In After method class");
		statusValue.clear();
		if (!result.isSuccess()) {
			Reporter.log(
					"Screen shot for failed Test Case" + customReport.AssertionresultOutput(driver, sTestcaseName));
		}
		browserQuit();
	}
}