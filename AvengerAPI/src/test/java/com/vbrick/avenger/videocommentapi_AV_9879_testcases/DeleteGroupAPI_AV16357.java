package com.vbrick.avenger.videocommentapi_AV_9879_testcases;
 

	

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
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
import com.vbrick.avenger.apibeans.AddGroupBean;
import com.vbrick.avenger.apibeans.AddTeamBean;
import com.vbrick.avenger.apibeans.CreateEventBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AddCustomDeviceBeanPage;
import com.vbrick.avenger.dao.AddNewDmeBeanPage;
import com.vbrick.avenger.dao.AddPresentationprofileBeanPage;
import com.vbrick.avenger.dao.AvengerGroupsBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLibrariesPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerAddCustomDevicePage;
import com.vbrick.avenger.pageobjects.AvengerAddPresentationProfilePage;
import com.vbrick.avenger.pageobjects.AvengerDevicesPage;
import com.vbrick.avenger.pageobjects.AvengerEventDetailsPage;
import com.vbrick.avenger.pageobjects.AvengerGroupsPage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerMediaSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerPresentationProfilesPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.CreateGroupAPI;
import com.vbricks.avenger.serviceimpl.DeleteGroupAPI;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class DeleteGroupAPI_AV16357 extends TestBase {

	private static Logger logger = Logger.getLogger(DeleteGroupAPI_AV16357.class);
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
	private ReadAndWriteToJSON readgriduserdata;
	private Map<String, String> userdata;
	private ApiUtils apiutils;
	private AvengerGroupsPage avengergroupspage;
	private AvengerGroupsBeanPage avengergroupsbeanpage;
	private AddPresentationprofileBeanPage addpresentationprofilebeanpage;
	private AddPresentationprofileBeanPage addeditpresentationprofilebeanpage;
	private AddNewDmeBeanPage addnewdmedevicebeanpage;
	private BasePage basePage;
	private AddCustomDeviceBeanPage addcustomdevicebeanpage;
	private AddCustomDeviceBeanPage addeditcustomdevicebeanpage;
	public ApiBeanFactory apibeanfactory;
	private HashMap<String, String> loginapiresponse;
	private HashMap<String, String> loginapiresponse_mediaadmin;
	private AvengerMediaSettingsPage avengermediasettingpage;
	private HashMap<String, String> loginapiresponse_mediacontributor;
	private HashMap<String, String> loginapiresponse_eventadmin;
	private HashMap<String, String> loginapiresponse_mediaviewer;
	private HashMap<String, String> loginapiresponse1;
	private AvengerLibrariesPage avengerlibrarypage;
	private HashMap<String, String> deleteTeamapiresponse;
	private AddTeamBean addteambean;
	private HashMap<String, String> createteamapirespone;
	private HashMap<String, String> deleteGroupapiresponse;
	private AssertionAPIResponse assertionapiresponse;
	private HashMap<String, String> creategroupapirespone;
	private AvengerHomePage avengerHomePage;
	private AddGroupBean addgroupbean;

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
		apibeanfactory = new ApiBeanFactory();
		addgroupbean=new AddGroupBean();
		assertionapiresponse =new AssertionAPIResponse();
		apiutils=new ApiUtils();
		avengergroupsbeanpage = new AvengerGroupsBeanPage();
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
		accountBeansFactory.AvengergroupsBean(avengergroupsbeanpage);
	}

	@Test(description = "To Verify the DeleteGroup using DeletegroupAPI with Account Admin",groups = {DELETEGROUPAPI})
	public void TC_01_DELETE_DeleteGroup_api_check_With_AccountAdmin_Positive(ITestContext context) {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
		creategroupapirespone = creategroupAPI.createGroup(loginapiresponse,creategroupjson);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
 
		loginapiresponse.put(IAPIConstantCodes.APIResponseGROUPID, creategroupapirespone.get(IAPIConstantCodes.APIGROUPID));
  
		// Delete Group
		DeleteGroupAPI deleteGroupAPI = new DeleteGroupAPI();
		deleteGroupapiresponse = deleteGroupAPI.deleteGroups(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengergroupspage =avengerHomePage.clickGroupsLink();
		avengergroupsbeanpage=new AvengerGroupsBeanPage();
		avengergroupsbeanpage.setNewgroup(addgroupbean.getName());
		customReport.customizedReport(false, avengergroupspage.verify_IsElementdoesnot_exists(avengergroupsbeanpage), statusValue, driver, sTestcaseName);  
	    customReport.checkinglist(statusValue);
	        
	}
	
	@Test(description = "To Verify the DeleteGroup using DeletegroupAPI with Media Admin",groups = {DELETEGROUPAPI})
	public void TC_02_DELETE_DeleteGroup_api_check_With_MediaAdmin_Negative(ITestContext context) {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
		creategroupapirespone = creategroupAPI.createGroup(loginapiresponse,creategroupjson);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
 
		UserServices userservices_mediaadmin = new UserServices();
 		loginapiresponse_mediaadmin = userservices_mediaadmin.doLogin(apiutils.userJson(IUsersList.DeleteGroupmediaadmin), surl);  
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediaadmin.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_mediaadmin.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse_mediaadmin);
		
		loginapiresponse.put(IAPIConstantCodes.APIResponseGROUPID, creategroupapirespone.get(IAPIConstantCodes.APIGROUPID));
		
		// Delete Group
		DeleteGroupAPI deleteGroupAPI = new DeleteGroupAPI();
		deleteGroupapiresponse = deleteGroupAPI.deleteGroups(loginapiresponse_mediaadmin);
		assertionapiresponse.verifyAssert_httpCode(deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpStatusCodeInt401null);
		customReport.customizedReport(HttpStatusCode.httpStatusCodeInt401null,deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To Verify the DeleteGroup using DeletegroupAPI with Media Contributor",groups = {DELETEGROUPAPI})
	public void TC_03_DELETE_DeleteGroup_api_check_With_MediaContributor_Negative(ITestContext context) {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
		creategroupapirespone = creategroupAPI.createGroup(loginapiresponse,creategroupjson);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
 
		UserServices userservices_mediaadmin = new UserServices();
 		loginapiresponse_mediacontributor = userservices_mediaadmin.doLogin(apiutils.userJson(IUsersList.DeleteGroupmediacontributor), surl);  
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediacontributor.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_mediacontributor.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse_mediacontributor);
		
		loginapiresponse.put(IAPIConstantCodes.APIResponseGROUPID, creategroupapirespone.get(IAPIConstantCodes.APIGROUPID));
		
		// Delete Group
		DeleteGroupAPI deleteGroupAPI = new DeleteGroupAPI();
		deleteGroupapiresponse = deleteGroupAPI.deleteGroups(loginapiresponse_mediacontributor);
		assertionapiresponse.verifyAssert_httpCode(deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpStatusCodeInt401null);
		customReport.customizedReport(HttpStatusCode.httpStatusCodeInt401null,deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To Verify the DeleteGroup using DeletegroupAPI with Event Admin",groups = {DELETEGROUPAPI})
	public void TC_04_DELETE_DeleteGroup_api_check_With_EventAdmin_Negative(ITestContext context) {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
		creategroupapirespone = creategroupAPI.createGroup(loginapiresponse,creategroupjson);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
 
		UserServices userservices_mediaadmin = new UserServices();
 		loginapiresponse_eventadmin = userservices_mediaadmin.doLogin(apiutils.userJson(IUsersList.DeleteGroupeventadmin), surl);  
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_eventadmin.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_eventadmin.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse_eventadmin);
		
		loginapiresponse.put(IAPIConstantCodes.APIResponseGROUPID, creategroupapirespone.get(IAPIConstantCodes.APIGROUPID));
		
		// Delete Group
		DeleteGroupAPI deleteGroupAPI = new DeleteGroupAPI();
		deleteGroupapiresponse = deleteGroupAPI.deleteGroups(loginapiresponse_eventadmin);
		assertionapiresponse.verifyAssert_httpCode(deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpStatusCodeInt401null);
		customReport.customizedReport(HttpStatusCode.httpStatusCodeInt401null,deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "TTo Verify the DeleteGroup using DeletegroupAPI with Media Viewer",groups = {DELETEGROUPAPI})
	public void TC_05_DELETE_DeleteGroup_api_check_With_MediaViewer_Negative(ITestContext context) {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
		creategroupapirespone = creategroupAPI.createGroup(loginapiresponse,creategroupjson);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
 
		UserServices userservices_mediaadmin = new UserServices();
 		loginapiresponse_mediaviewer = userservices_mediaadmin.doLogin(apiutils.userJson(IUsersList.DeleteGroupmediaviewer), surl);  
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediaviewer.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_mediaviewer.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse_mediaviewer);
		
		loginapiresponse.put(IAPIConstantCodes.APIResponseGROUPID, creategroupapirespone.get(IAPIConstantCodes.APIGROUPID));
		
		// Delete Group
		DeleteGroupAPI deleteGroupAPI = new DeleteGroupAPI();
		deleteGroupapiresponse = deleteGroupAPI.deleteGroups(loginapiresponse_mediaviewer);
		assertionapiresponse.verifyAssert_httpCode(deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpStatusCodeInt401null);
		customReport.customizedReport(HttpStatusCode.httpStatusCodeInt401null,deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);
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