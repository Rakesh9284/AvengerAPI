package com.vbrick.avenger.videocommentapi_AV_9879_testcases;
 

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.RandomStringUtils;
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
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbrick.avenger.apibeans.AddVideoCommentBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AddCustomDeviceBeanPage;
import com.vbrick.avenger.dao.AddUserBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerApprovalProcessPage;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerMediaSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.SendVideoforapproval;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;
public class SendVideoforapprovalAPI extends TestBase{

	private static Logger logger = Logger.getLogger(SendVideoforapprovalAPI.class);
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
    public AddVideoCommentBean addvideocommentbean;
	private HashMap<String, String> loginapiresponse;
	private HashMap<String, String> loginapiresponse_mediaviewer;
    private AssertionAPIResponse assertionapiresponse;	
    private HashMap<String, String> sendvideoapprovalapirespone;
    private HashMap<String, String> loginapiresponse_eventadmin;
    private HashMap<String, String> uploadvidoeapiresponse;
	private ApiUtils apiutils; 
	private AddCustomDeviceBeanPage addcustomdevicebeanpage;
	private AvengerHomePage avengerHomePage;	
	private AddUploadVideoBean adduploadvideobean;
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
		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();		
		apibeanfactory = new ApiBeanFactory();
		addvideocommentbean=new AddVideoCommentBean();
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

	@Test(description="To Verify the SendVideoforApproval using SendVideoforApproval API with Account Admin",groups = {SENDVIDEOFORAPPROVAL})
	public void TC_01_PUT_SendVideoforApproval_api_check_With_AccountAdmin_Positive(ITestContext context ) {
		
		String approvalid=create_ApprovalProcessFromUI(sUserName);	
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableDownloads(IAPIConstantCodes.TRUE);		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");		
		
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		loginapiresponse.put("VidoeID", uploadvidoeapiresponse.get("videoId"));
		loginapiresponse.put("ApprovalProcessID", approvalid);
		
		SendVideoforapproval sendVideoforapproval=new SendVideoforapproval();
		JSONObject categroiesjson=sendVideoforapproval.createApprovalJson();
		sendvideoapprovalapirespone = sendVideoforapproval.sendVideo(loginapiresponse,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(sendvideoapprovalapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+sendvideoapprovalapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, sendvideoapprovalapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+sendvideoapprovalapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		//login as Media Admin
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport("Approve",homePage.verify_label("Approve"), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	 
	}
	 
	@Test(description="To Verify the SendVideoforApproval using SendVideoforApproval API with Media Admin",groups = {SENDVIDEOFORAPPROVAL})
	public void TC_02_PUT_SendVideoforApproval_api_check_With_Mediaadmin_Positive(ITestContext context ) {
		
		String approvalid=create_ApprovalProcessFromUI(IUsersList.SendVideoForApprovalmediaadmin);		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.SendVideoForApprovalmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.SendVideoForApprovalmediaadmin);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableDownloads(IAPIConstantCodes.TRUE);		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		loginapiresponse.put("VidoeID", uploadvidoeapiresponse.get("videoId"));
		loginapiresponse.put("ApprovalProcessID", approvalid);
		
		SendVideoforapproval sendVideoforapproval=new SendVideoforapproval();
		JSONObject categroiesjson=sendVideoforapproval.createApprovalJson();
		sendvideoapprovalapirespone = sendVideoforapproval.sendVideo(loginapiresponse,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(sendvideoapprovalapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+sendvideoapprovalapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, sendvideoapprovalapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+sendvideoapprovalapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		//login as Media Admin
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport("Approve",homePage.verify_label("Approve"), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	 
	}
	
	@Test(description="To Verify the SendVideoforApproval using SendVideoforApproval API with Media Admin",groups = {SENDVIDEOFORAPPROVAL})
	public void TC_03_PUT_SendVideoforApproval_api_check_With_MediaContributor_Positive(ITestContext context ) {
		
		String approvalid=create_ApprovalProcessFromUI(IUsersList.SendVideoForApprovalcontributor);		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.SendVideoForApprovalcontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.SendVideoForApprovalcontributor);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableDownloads(IAPIConstantCodes.TRUE);		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		loginapiresponse.put("VidoeID", uploadvidoeapiresponse.get("videoId"));
		loginapiresponse.put("ApprovalProcessID", approvalid);
		
		SendVideoforapproval sendVideoforapproval=new SendVideoforapproval();
		JSONObject categroiesjson=sendVideoforapproval.createApprovalJson();
		sendvideoapprovalapirespone = sendVideoforapproval.sendVideo(loginapiresponse,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(sendvideoapprovalapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+sendvideoapprovalapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, sendvideoapprovalapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+sendvideoapprovalapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		//login as Media Admin
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport("Approve",homePage.verify_label("Approve"), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	 
	}
	
	@Test(description="To Verify the SendVideoforApproval using SendVideoforApproval API with Media Admin",groups = {SENDVIDEOFORAPPROVAL})
	public void TC_04_PUT_SendVideoforApproval_api_check_With_EventAdmin_Negative(ITestContext context ) {
		
		String approvalid=create_ApprovalProcessFromUI(IUsersList.SendVideoForApprovaleventadmin);		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableDownloads(IAPIConstantCodes.TRUE);		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//UserServices userservices = new UserServices();
		loginapiresponse_eventadmin = userservices.doLogin(apiutils.userJson(IUsersList.SendVideoForApprovaleventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_eventadmin.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_eventadmin.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse_eventadmin);
		loginapiresponse_eventadmin.put("VidoeID", uploadvidoeapiresponse.get("videoId"));
		loginapiresponse_eventadmin.put("ApprovalProcessID", approvalid);
		
		SendVideoforapproval sendVideoforapproval=new SendVideoforapproval();
		JSONObject categroiesjson=sendVideoforapproval.createApprovalJson();
		sendvideoapprovalapirespone = sendVideoforapproval.sendVideo(loginapiresponse_eventadmin,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(sendvideoapprovalapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, sendvideoapprovalapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	 
	}
	
	@Test(description="To Verify the SendVideoforApproval using SendVideoforApproval API with Account Admin",groups = {SENDVIDEOFORAPPROVAL})
	public void TC_05_PUT_SendVideoforApproval_api_check_With_MediaViewer_Negative(ITestContext context ) {
		
		String approvalid=create_ApprovalProcessFromUI(IUsersList.SendVideoForApprovalmediaviewer);		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableDownloads(IAPIConstantCodes.TRUE);		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
		
		//UserServices userservices = new UserServices();
		loginapiresponse_mediaviewer = userservices.doLogin(apiutils.userJson(IUsersList.SendVideoForApprovalmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediaviewer.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_mediaviewer.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse_mediaviewer);
		loginapiresponse_mediaviewer.put("VidoeID", uploadvidoeapiresponse.get("videoId"));
		loginapiresponse_mediaviewer.put("ApprovalProcessID", approvalid);
		
		SendVideoforapproval sendVideoforapproval=new SendVideoforapproval();
		JSONObject categroiesjson=sendVideoforapproval.createApprovalJson();
		sendvideoapprovalapirespone = sendVideoforapproval.sendVideo(loginapiresponse_mediaviewer,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(sendvideoapprovalapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, sendvideoapprovalapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
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
	
	public String create_ApprovalProcessFromUI(String username){
		launchURL(surl);
         avengerHomePage=loginPage.loginAs(sUserName,sPassword);
         avengerHomePage.clickSettingsLink();
		AvengerMediaSettingsPage avengermediasettingspage=avengerHomePage.clickMediaSettingsLink();
		AvengerApprovalProcessPage avengerapprovalprocesspage=avengermediasettingspage.click_ApprovalProcesses();
		avengerapprovalprocesspage.click_CreateApprovalProcessButton();
		String randomalphabet=RandomStringUtils.randomAlphabetic(6);
		avengerapprovalprocesspage.addApprovalProcessName(randomalphabet);
		AddUserBeanPage adduserbean=new AddUserBeanPage();
		adduserbean.setFirstname(username);
		adduserbean.setLastname(username);
		avengerapprovalprocesspage.addusersRequiringApproval(adduserbean);
		adduserbean.setFirstname(IUsersList.SendVideoForApprovalcontributor.toString());
		adduserbean.setLastname(IUsersList.SendVideoForApprovalcontributor.toString());
		avengerapprovalprocesspage.addusersForApproval(adduserbean);
		avengerapprovalprocesspage.pause(1000);
		avengerapprovalprocesspage.clickcreateapprovalProcessSubmitButtonLocator();
		avengerapprovalprocesspage.clickCreatedApprovalProcess(randomalphabet);
		avengerapprovalprocesspage.verify_Label(randomalphabet);
		String id=avengerapprovalprocesspage.getApprovalProcessID();
	    //avengerapprovalprocesspage.pause(5000);
		avengerHomePage.click_logout();
		return id;
	}
	
	
}