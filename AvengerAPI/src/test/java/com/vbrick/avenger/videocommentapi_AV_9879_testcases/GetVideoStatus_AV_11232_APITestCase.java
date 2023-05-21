package com.vbrick.avenger.videocommentapi_AV_9879_testcases;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbrick.avenger.apibeans.AddVideoCommentBean;
import com.vbrick.avenger.apibeans.VideoAccessControlBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerEditVideoSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.service.IVideoAccessControlService;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.serviceimpl.VideoAccessControlServiceAPI;
import com.vbricks.avenger.serviceimpl.CreateGroupAPI;
import com.vbricks.avenger.serviceimpl.GetVideoStatusAPI;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.ConstantsValue;
import com.vbricks.avenger.utils.HttpStatusCode;

public class GetVideoStatus_AV_11232_APITestCase extends TestBase {

	private static Logger logger = Logger.getLogger(GetVideoStatus_AV_11232_APITestCase.class);
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
	private AddUploadVideoBean adduploadvideobean;
	public ApiBeanFactory apibeanfactory;
	public AddVideoCommentBean addvideocommentbean;
	private HashMap<String, String> loginapiresponse;
	private HashMap<String, String> uploadvidoeapiresponse;
	private AssertionAPIResponse assertionapiresponse;
	private VideoAccessControlBean accesscontrolbeagPage;
	private AddGroupBean addgroupbean;
	private HashMap<String, String> creategroupapirespone;
	private HashMap<String, String>uploadvidoeapiresponse_admin;	
	private ApiUtils apiutils;

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
		accesscontrolbeagPage = new VideoAccessControlBean();
		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		addvideocommentbean = new AddVideoCommentBean();
		assertionapiresponse = new AssertionAPIResponse();
		apiutils = new ApiUtils();
		addgroupbean=new AddGroupBean();
	}

	@BeforeMethod(alwaysRun = true)
	@Parameters(value = { "sbrowser", "sgrid" })
	public void setUP(@Optional(SBROWSER) String sbrowser, @Optional(SVERSION) String sgrid)
			throws MalformedURLException {
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

	@Test(description = "To Verify the VideoStatus using VideosStatus API with Account Admin ",groups = {GETVIDEOSTATUSAPI})
	public void TC_01_GET_GetVideoStatusAPI_api_check_with_AccountAdmin_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue,driver, sTestcaseName);

		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		GetVideoStatusAPI getGetVideoStatusAPI = new GetVideoStatusAPI();
		HashMap<String, String> getGetVideoStatusAPIresponse = getGetVideoStatusAPI.getVideoDetails(uploadvidoeapiresponse);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(getGetVideoStatusAPIresponse.get(IVideoAccessControlService.videoTitle));
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(getGetVideoStatusAPIresponse.get(IVideoAccessControlService.videoTitle));
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(getGetVideoStatusAPIresponse.get(IVideoAccessControlService.videoTitle)),statusValue, driver, sTestcaseName); // title
		customReport.customizedReport(getGetVideoStatusAPIresponse.get(IVideoAccessControlService.videoid), commentspage.getvideoID(), statusValue,driver, sTestcaseName); // VideoId	
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();	
		customReport.customizedReport(avengereditvideosettingspage.get_VideoStatus().equals("Active"), getGetVideoStatusAPIresponse.get(IVideoAccessControlService.videoIsActive).equals("true"), statusValue,driver, sTestcaseName); // Active
		customReport.checkinglist(statusValue);

	}

	@Test(description = "To Verify the VideoStatus using VideosStatus API with Media admin ",groups = {GETVIDEOSTATUSAPI})
	public void TC_02_GET_GetVideosStatusAPI_api_check_with_MediaAdmin_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.GetVideoStatusmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue,driver, sTestcaseName);

		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.GetVideoStatusmediaadmin);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		GetVideoStatusAPI getGetVideoStatusAPI = new GetVideoStatusAPI();
		HashMap<String, String> getGetVideoStatusAPIresponse = getGetVideoStatusAPI.getVideoDetails(uploadvidoeapiresponse);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(getGetVideoStatusAPIresponse.get(IVideoAccessControlService.videoTitle));
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(getGetVideoStatusAPIresponse.get(IVideoAccessControlService.videoTitle));
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(getGetVideoStatusAPIresponse.get(IVideoAccessControlService.videoTitle)),statusValue, driver, sTestcaseName); // title
		customReport.customizedReport(getGetVideoStatusAPIresponse.get(IVideoAccessControlService.videoid), commentspage.getvideoID(), statusValue,driver, sTestcaseName); // VideoId
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
		customReport.customizedReport(avengereditvideosettingspage.get_VideoStatus().equals("Active"), getGetVideoStatusAPIresponse.get(IVideoAccessControlService.videoIsActive).equals("true"), statusValue,driver, sTestcaseName); // Active
		customReport.checkinglist(statusValue);

	}

	@Test(description = "To Verify the VideoStatus using VideosStatus API with Media Contributor ",groups = {GETVIDEOSTATUSAPI})
	public void TC_03_GET_GetVideosStatusAPI_api_check_with_MediaContributor_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.GetVideoStatusmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue,driver, sTestcaseName);

		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.GetVideoStatusmediacontributor);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		GetVideoStatusAPI getGetVideoStatusAPI = new GetVideoStatusAPI();
		HashMap<String, String> getGetVideoStatusAPIresponse = getGetVideoStatusAPI.getVideoDetails(uploadvidoeapiresponse);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(getGetVideoStatusAPIresponse.get(IVideoAccessControlService.videoTitle));
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(getGetVideoStatusAPIresponse.get(IVideoAccessControlService.videoTitle));
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(getGetVideoStatusAPIresponse.get(IVideoAccessControlService.videoTitle)),statusValue, driver, sTestcaseName); // title
		customReport.customizedReport(getGetVideoStatusAPIresponse.get(IVideoAccessControlService.videoid), commentspage.getvideoID(), statusValue,driver, sTestcaseName); // VideoId
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
		customReport.customizedReport(avengereditvideosettingspage.get_VideoStatus().equals("Active"), getGetVideoStatusAPIresponse.get(IVideoAccessControlService.videoIsActive).equals("true"), statusValue,driver, sTestcaseName); // Active
		customReport.checkinglist(statusValue);

	}

	@Test(description = "To Verify the VideoStatus using VideosStatus API with Media Viewer ",groups = {GETVIDEOSTATUSAPI})
	public void TC_04_GET_GetVideosStatusAPI_api_check_with_mediaViewer_Negative() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue,driver, sTestcaseName);

		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setVideoAccessControl(IAPIConstantCodes.PRIVATE);
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		UserServices userservices_mediaviewer = new UserServices();
		HashMap<String, String> loginapiresponse_mediaviewer = userservices_mediaviewer.doLogin(apiutils.userJson(IUsersList.GetVideoStatusmediaviewer), surl);
		loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));

		GetVideoStatusAPI getGetVideoStatusAPI = new GetVideoStatusAPI();
		HashMap<String, String> getGetVideoStatusAPIresponse = getGetVideoStatusAPI.getVideoDetails(loginapiresponse_mediaviewer);
		assertionapiresponse.verifyAssert_httpCode(getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = "To Verify the VideoStatus using VideosStatus API with Event Admin ",groups = {GETVIDEOSTATUSAPI})
	public void TC_05_GET_GetVideosStatusAPI_api_check_with_eventadmin_Negative() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue,driver, sTestcaseName);

		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setVideoAccessControl(IAPIConstantCodes.PRIVATE);
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		UserServices userservices_mediaviewer = new UserServices();
		HashMap<String, String> loginapiresponse_eventadmin = userservices_mediaviewer.doLogin(apiutils.userJson(IUsersList.GetVideoStatuseventadmin), surl);
		loginapiresponse_eventadmin.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_eventadmin.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
		GetVideoStatusAPI getGetVideoStatusAPI = new GetVideoStatusAPI();
		HashMap<String, String> getGetVideoStatusAPIresponse = getGetVideoStatusAPI.getVideoDetails(loginapiresponse_eventadmin);
		assertionapiresponse.verifyAssert_httpCode(getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = "Vidoe Comment API - GetVideoStatus API Media Viewer with Valid data By Giving Permission In Group",groups = {GETVIDEOSTATUSAPI})
	public void TC_06_GET_GetVideosStatusAPI_api_check_with_mediaViewer_By_Permission_Negative()throws InterruptedException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue,driver, sTestcaseName);

		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setVideoAccessControl(IAPIConstantCodes.PRIVATE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		UserServices userservices_mediaviewer = new UserServices();
		HashMap<String, String> loginapiresponse_mediaviewer = userservices_mediaviewer.doLogin(apiutils.userJson(IUsersList.GetVideoStatusmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediaviewer.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse_mediaviewer);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_mediaviewer.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
		loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIRequestAccessToken, loginapiresponse.get(IAPIConstantCodes.APIRequestAccessToken));

		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		String[] userIds = {loginapiresponse_mediaviewer.get("userId")};
		addgroupbean.setUserids(userIds);
		
		UserServices userServices=new UserServices();
	    HashMap<String, String> userroleapirespone = userServices.getRolesApi(loginapiresponse,IAPIConstantCodes.MEDIAVIEWER);
	    String roleid=apiutils.formatingapiresponse(userroleapirespone.get(IAPIConstantCodes.ROLEID));
	    String[] roleIds = {userroleapirespone.get("roleId")};
		addgroupbean.setRoleids(roleIds);
		
	    
		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
		creategroupapirespone = creategroupAPI.createGroup(loginapiresponse,creategroupjson);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		
		VideoAccessControlServiceAPI videoaccesscontrolservice = new VideoAccessControlServiceAPI();
		//accesscontrolbeagPage.setUsercanEdit(IAPIConstantCodes.TRUE);
		//accesscontrolbeagPage.setUserid(loginapiresponse_mediaviewer.get(IAPIConstantCodes.APIResponseUSERID));
		//accesscontrolbeagPage.setUsertype(IAPIConstantCodes.USER);
		accesscontrolbeagPage.setGroupcanEdit(IAPIConstantCodes.FALSE);
		accesscontrolbeagPage.setGroupid(creategroupapirespone.get(IAPIConstantCodes.APIGROUPID));
		accesscontrolbeagPage.setGrouptype(IAPIConstantCodes.group);
		
		JSONObject json = videoaccesscontrolservice.createJsonVideoAccessControl(accesscontrolbeagPage,IVideoAccessControlService.jsonFlagGroup);
		videoaccesscontrolservice.videoAccessControl(loginapiresponse, json);	
		Thread.sleep(60000);

		HashMap<String, String> loginapiresponse_mediaviewer1 = userservices_mediaviewer.doLogin(apiutils.userJson(IUsersList.GetVideoStatusmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediaviewer1.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse_mediaviewer1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_mediaviewer1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		loginapiresponse_mediaviewer1.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_mediaviewer1.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));

		GetVideoStatusAPI getGetVideoStatusAPI = new GetVideoStatusAPI();
		HashMap<String, String> getGetVideoStatusAPIresponse = getGetVideoStatusAPI.getVideoDetails(loginapiresponse_mediaviewer1);
		logger.info("Get Video Status API response Code :::" + getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);

	}

	@Test(description = "To Verify the VideoStatus using VideosStatus API with Event Admin,After Giving Permission in Group ",groups = {GETVIDEOSTATUSAPI})
	public void TC_07_GET_GetVideosStatusAPI_api_check_with_eventadmin_By_Giving_Permission_Negative()throws InterruptedException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue,driver, sTestcaseName);

		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setVideoAccessControl(IAPIConstantCodes.PRIVATE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		UserServices userservices_eventadmin = new UserServices();
		HashMap<String, String> loginapiresponse_eventadmin = userservices_eventadmin.doLogin(apiutils.userJson(IUsersList.GetVideoStatuseventadmin), surl);
		loginapiresponse_eventadmin.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_eventadmin.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
		loginapiresponse_eventadmin.put(IAPIConstantCodes.APIRequestAccessToken, loginapiresponse.get(IAPIConstantCodes.APIRequestAccessToken));

	/*	VideoAccessControlServiceAPI videoaccesscontrolservice = new VideoAccessControlServiceAPI();
		accesscontrolbeagPage.setUsercanEdit(IAPIConstantCodes.TRUE);
		accesscontrolbeagPage.setUserid(loginapiresponse_eventadmin.get(IAPIConstantCodes.APIResponseUSERID));
		accesscontrolbeagPage.setUsertype(IAPIConstantCodes.USER);
		JSONObject json = videoaccesscontrolservice.createJsonVideoAccessControl(accesscontrolbeagPage,IVideoAccessControlService.jsonFlagUser);
		videoaccesscontrolservice.videoAccessControl(uploadvidoeapiresponse, json);
		Thread.sleep(60000);*/

		HashMap<String, String> loginapiresponse_eventadmin1 = userservices_eventadmin.doLogin(apiutils.userJson(IUsersList.GetVideoStatuseventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_eventadmin1.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse_eventadmin1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_eventadmin1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		loginapiresponse_eventadmin1.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_eventadmin1.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));

		GetVideoStatusAPI getGetVideoStatusAPI = new GetVideoStatusAPI();
		HashMap<String, String> getGetVideoStatusAPIresponse = getGetVideoStatusAPI.getVideoDetails(loginapiresponse_eventadmin1);
		logger.info("UploadVideo API response Code :::" + getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = "To Verify the VideoStatus using VideosStatus API with Account Admin on InvalidVideoId ",groups = {GETVIDEOSTATUSAPI})
	public void TC_08_GET_GetVideosStatusAPI_api_check_AccountAdmins_Invalid_VideoId_Negative(ITestContext context) throws Exception {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue,driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setVideoAccessControl(IAPIConstantCodes.PRIVATE);
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		 
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		uploadvidoeapiresponse.replace("accesstoken",loginapiresponse.get("acesstoken")+1);
		
		GetVideoStatusAPI getGetVideoStatusAPI = new GetVideoStatusAPI();
		HashMap<String, String> getGetVideoStatusAPIresponse = getGetVideoStatusAPI.getVideoDetails(uploadvidoeapiresponse);
		assertionapiresponse.verifyAssert_httpCode(getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
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
