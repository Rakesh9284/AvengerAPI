package com.vbrick.avenger.videocommentapi_AV_9879_testcases;
 

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.vbrick.avenger.pageobjects.AvengerApprovalProcessQueuePage;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerMediaPage;
import com.vbrick.avenger.pageobjects.AvengerMediaSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.pageobjects.AvengerVideosPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.SendVideoforapproval;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.serviceimpl.VideosPendingforApproval;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;
public class GetVideosPendingforApproval extends TestBase{

	private static Logger logger = Logger.getLogger(GetVideosPendingforApproval.class);
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
    private HashMap<String, String> videopendingapirespone;
    private HashMap<String, String> videopendingapirespone_eventadmin;
    private HashMap<String, String> loginapiresponse_mediacontributor;
    private HashMap<String, String> videopendingapirespone_mediaviewer;
    private HashMap<String, String> videopendingapirespone_mediacontributor;
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

	@Test(description="To Verify the GetVideosPendingforApproval using GetVideosPendingforApproval API with Account Admin And EventAdmin",groups = {GETVIDEOSPENDINGFORAPPROVAL})
	public void TC_01_GET_GetVideosPendingforApproval_api_check_With_AccountAdmin_And_EventAdmin_Positive(ITestContext context ) throws InterruptedException {
		
		String approvalid=create_ApprovalProcessFromUI(sUserName,IUsersList.VideoPendingeventadmin);
		
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
		
		VideosPendingforApproval videosPendingforApproval=new VideosPendingforApproval();
		videopendingapirespone = videosPendingforApproval.getVideosPendingList(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(videopendingapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videopendingapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, videopendingapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videopendingapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		homePage.clickSettingsLink();
		AvengerMediaSettingsPage avengermediasettingspage=homePage.clickMediaSettingsLink();
		AvengerApprovalProcessQueuePage avengerapprovalprocessqueuepage=avengermediasettingspage.click_ApprovalProcessesQueue();
		ArrayList<String> listvalue=avengerapprovalprocessqueuepage.getALLApprovalVideos();		
		String fromattedJSON=apiutils.formatingapiresponse(videopendingapirespone.get("pendingvideonames"));
		ArrayList<String> myList = new ArrayList<String>(Arrays.asList(fromattedJSON.split(",")));
		
		for (String list : myList) {
			System.out.println("list value"+list);
			customReport.customizedReport(true,listvalue.contains(list), statusValue, driver, sTestcaseName);
		}
		homePage.click_logout();
		
		//login as Event Admin
		UserServices userservices1 = new UserServices();
		loginapiresponse_eventadmin = userservices1.doLogin(apiutils.userJson(IUsersList.VideoPendingeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_eventadmin.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_eventadmin.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse_eventadmin);
		
		VideosPendingforApproval videosPendingforApproval1=new VideosPendingforApproval();
		videopendingapirespone_eventadmin = videosPendingforApproval1.getVideosPendingList(loginapiresponse_eventadmin);
		assertionapiresponse.verifyAssert_httpCode(videopendingapirespone_eventadmin.get(IAPIConstantCodes.APIResponseHttpCode)+videopendingapirespone_eventadmin.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, videopendingapirespone_eventadmin.get(IAPIConstantCodes.APIResponseHttpCode)+videopendingapirespone_eventadmin.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(IUsersList.VideoPendingeventadmin, sPassword);
		AvengerDashboardPage avengerdashboardpage=homePage.avengerDashboardPage();
       	AvengerMediaPage avengerMediaPage=avengerdashboardpage.clickMediaDropDown();
		AvengerVideosPage avengerVideosPage=avengerMediaPage.click_AllVideos();
		avengerVideosPage.click_pendingApprovalTab();
		ArrayList<String> listvalue1=avengerVideosPage.getAll_pendingApprovalVideos();
		String fromattedJSON1=apiutils.formatingapiresponse(videopendingapirespone_eventadmin.get("pendingvideonames"));
		ArrayList<String> myList1 = new ArrayList<String>(Arrays.asList(fromattedJSON1.split(",")));
		logger.info("listvalues ::: "+listvalue1);
		logger.info("myList ::; "+myList1);
		for (String list1 : myList1) {
			System.out.println("list value"+list1);
			customReport.customizedReport(true,listvalue1.contains(list1), statusValue, driver, sTestcaseName);
		}
		customReport.checkinglist(statusValue);
	 }
	 
	@Test(description="To Verify the GetVideosPendingforApproval using GetVideosPendingforApproval API with AccountAdmin And MediaContributor",groups = {GETVIDEOSPENDINGFORAPPROVAL})
	public void TC_02_GET_GetVideosPendingforApproval_api_check_With_MediaAdmin_And_MediaContributor_Positive(ITestContext context ) throws InterruptedException {
		
		String approvalid=create_ApprovalProcessFromUI(IUsersList.VideoPendingmediaadmin,IUsersList.VideoPendingmediacontributor);
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.VideoPendingmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.VideoPendingmediaadmin);
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
		
		VideosPendingforApproval videosPendingforApproval=new VideosPendingforApproval();
		videopendingapirespone = videosPendingforApproval.getVideosPendingList(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(videopendingapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videopendingapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, videopendingapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videopendingapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(IUsersList.VideoPendingmediaadmin, sPassword);
		homePage.clickSettingsLink();
		AvengerMediaSettingsPage avengermediasettingspage=homePage.clickMediaSettingsLink();
		AvengerApprovalProcessQueuePage avengerapprovalprocessqueuepage=avengermediasettingspage.click_ApprovalProcessesQueue();
        ArrayList<String> listvalue=avengerapprovalprocessqueuepage.getALLApprovalVideos();
		String fromattedJSON=apiutils.formatingapiresponse(videopendingapirespone.get("pendingvideonames"));
		ArrayList<String> myList = new ArrayList<String>(Arrays.asList(fromattedJSON.split(",")));
		for (String list : myList) {
			System.out.println("list value"+list);
			customReport.customizedReport(true,listvalue.contains(list), statusValue, driver, sTestcaseName);
		}
		homePage.click_logout();
		
		//login as Media Contributor
		UserServices userservices1 = new UserServices();
		loginapiresponse_mediacontributor = userservices1.doLogin(apiutils.userJson(IUsersList.VideoPendingmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediacontributor.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_mediacontributor.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse_mediacontributor);
		
		VideosPendingforApproval videosPendingforApproval1=new VideosPendingforApproval();
		videopendingapirespone_mediacontributor = videosPendingforApproval1.getVideosPendingList(loginapiresponse_mediacontributor);
		assertionapiresponse.verifyAssert_httpCode(videopendingapirespone_mediacontributor.get(IAPIConstantCodes.APIResponseHttpCode)+videopendingapirespone_mediacontributor.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, videopendingapirespone_mediacontributor.get(IAPIConstantCodes.APIResponseHttpCode)+videopendingapirespone_mediacontributor.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(IUsersList.VideoPendingmediacontributor, sPassword);
		AvengerDashboardPage avengerdashboardpage=homePage.avengerDashboardPage();
       	AvengerMediaPage avengerMediaPage=avengerdashboardpage.clickMediaDropDown();
		AvengerVideosPage avengerVideosPage=avengerMediaPage.click_AllVideos();
		avengerVideosPage.click_pendingApprovalTab();
		ArrayList<String> listvalue1=avengerVideosPage.getAll_pendingApprovalVideos();
		String fromattedJSON1=apiutils.formatingapiresponse(videopendingapirespone_mediacontributor.get("pendingvideonames"));
		ArrayList<String> myList1 = new ArrayList<String>(Arrays.asList(fromattedJSON1.split(",")));
		for (String list1 : myList1) {
			System.out.println("list value"+list1);
			customReport.customizedReport(true,listvalue1.contains(list1), statusValue, driver, sTestcaseName);
		}
		
		customReport.checkinglist(statusValue);
	 }
	
	@Test(description="To Verify the GetVideosPendingforApproval using GetVideosPendingforApproval API with Account Admin And Mediaviewer",groups = {GETVIDEOSPENDINGFORAPPROVAL})
	public void TC_03_GET_GetVideosPendingforApproval_api_check_With_AccountAdmin_And_MediaViewer_Positive(ITestContext context ) throws InterruptedException {
		
		String approvalid=create_ApprovalProcessFromUI(IUsersList.VideoPendingmediaadmin,IUsersList.VideoPendingmediaviewer);
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.VideoPendingmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.VideoPendingmediaadmin);
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
		
		VideosPendingforApproval videosPendingforApproval=new VideosPendingforApproval();
		videopendingapirespone = videosPendingforApproval.getVideosPendingList(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(videopendingapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videopendingapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, videopendingapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videopendingapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(IUsersList.VideoPendingmediaadmin, sPassword);
		homePage.clickSettingsLink();
		AvengerMediaSettingsPage avengermediasettingspage=homePage.clickMediaSettingsLink();
		AvengerApprovalProcessQueuePage avengerapprovalprocessqueuepage=avengermediasettingspage.click_ApprovalProcessesQueue();
		 ArrayList<String> listvalue=avengerapprovalprocessqueuepage.getALLApprovalVideos();
			String fromattedJSON=apiutils.formatingapiresponse(videopendingapirespone.get("pendingvideonames"));
			ArrayList<String> myList = new ArrayList<String>(Arrays.asList(fromattedJSON.split(",")));
			for (String list : myList) {
				System.out.println("list value"+list);
				customReport.customizedReport(true,listvalue.contains(list), statusValue, driver, sTestcaseName);
			}
		homePage.click_logout();
		
		//login as Mediaviewer
		UserServices userservices1 = new UserServices();
		loginapiresponse_mediaviewer = userservices1.doLogin(apiutils.userJson(IUsersList.VideoPendingmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediaviewer.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_mediaviewer.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse_mediaviewer);
		
		VideosPendingforApproval videosPendingforApproval1=new VideosPendingforApproval();
		videopendingapirespone_mediaviewer = videosPendingforApproval1.getVideosPendingList(loginapiresponse_mediaviewer);
		assertionapiresponse.verifyAssert_httpCode(videopendingapirespone_mediaviewer.get(IAPIConstantCodes.APIResponseHttpCode)+videopendingapirespone_mediaviewer.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, videopendingapirespone_mediaviewer.get(IAPIConstantCodes.APIResponseHttpCode)+videopendingapirespone_mediaviewer.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(IUsersList.VideoPendingmediaviewer, sPassword);
		AvengerDashboardPage avengerdashboardpage=homePage.avengerDashboardPage();
       	AvengerMediaPage avengerMediaPage=avengerdashboardpage.clickMediaDropDown();
		AvengerVideosPage avengerVideosPage=avengerMediaPage.click_AllVideos();
		avengerVideosPage.click_pendingApprovalTab();
		ArrayList<String> listvalue1=avengerVideosPage.getAll_pendingApprovalVideos();
		String fromattedJSON1=apiutils.formatingapiresponse(videopendingapirespone_mediaviewer.get("pendingvideonames"));
	
		ArrayList<String> myList1 = new ArrayList<String>(Arrays.asList(fromattedJSON1.split(",")));
		for (String list1 : myList1) {
			System.out.println("list value:::"+list1);
			customReport.customizedReport(true,listvalue1.contains(list1), statusValue, driver, sTestcaseName);
		}
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
	
	public String create_ApprovalProcessFromUI(String username,String username1){
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
		adduserbean.setFirstname(username1.toString());
		adduserbean.setLastname(username1.toString());
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