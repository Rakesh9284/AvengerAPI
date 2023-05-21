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
import org.testng.Assert;
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
import com.vbrick.avenger.dao.AvengerVideoCommentsBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.DateTime;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IDeleteVideoService;
import com.vbricks.avenger.service.IMegratVideoService;
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.service.IVideoAccessControlService;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.serviceimpl.DeleteVideoCommentsAPI;
import com.vbricks.avenger.serviceimpl.DeleteVideosAPI;
import com.vbricks.avenger.serviceimpl.VideoCommentsAPI;
import com.vbricks.avenger.serviceimpl.GetVideoStatusAPI;
import com.vbricks.avenger.serviceimpl.SearchVideoAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoImageAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.ConstantsValue;
import com.vbricks.avenger.utils.HttpStatusCode;

import groovy.json.JsonSlurper;

public class GetVideosComments_AV_10649 extends TestBase {

	private static Logger logger = Logger.getLogger(GetVideosComments_AV_10649.class);
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
	private FileUploadBeanPage fileuploadbeanpage;
	private ReadAndWriteToJSON readgriduserdata;
	private Map<String, String> userdata;
	private BasePage basePage;
	private AddUploadVideoBean adduploadvideobean;
	public ApiBeanFactory apibeanfactory;
    public AddVideoCommentBean addvideocommentbean;
	private HashMap<String, String> loginapiresponse;
	private HashMap<String, String> uploadvidoeapiresponse;
	private HashMap<String, String> loginapiresponse_eventadmin;
	private HashMap<String, String> loginapiresponse_mediaviewer;
	private ApiUtils apiutils;
	private AssertionAPIResponse assertionapiresponse;
	
	@BeforeClass(alwaysRun = true)
	public void beforeClass() throws Exception {
		reasons = new Reasons("");
		statusValue = new ArrayList<String>();
		customReport = new CustomReport();
		accountBeansFactory = new BeanFactory();
		mailinatorbeanpage = new MailinatorBeanPage();
		fileuploadbeanpage = new FileUploadBeanPage();
		locale = new Locale(language);
		readgriduserdata = new ReadAndWriteToJSON();
		userdata = readgriduserdata.readGridUserData(this.getClass().getSimpleName()); // ("user52");
		sUserName = userdata.get("Username");
		sPassword = userdata.get("Password");
		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		addvideocommentbean=new AddVideoCommentBean();
		assertionapiresponse =new AssertionAPIResponse();
		apiutils=new ApiUtils();
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

	@Test(description="Vidoe Comment API - SubmitComment Account Admin with Valid data",groups = {GETVIDEOCOMMENTSAPI})
	public void TC_01_GET_GetVideosCommentsAPI_api_check_with_AccountAdmins_Positive(ITestContext context) throws InterruptedException {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();		
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);																																				
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		VideoCommentsAPI videoCommentsAPI=new VideoCommentsAPI(); 		
		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		HashMap<String, String> submitcommit = videoCommentsAPI.putsubmitComments(uploadvidoeapiresponse, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		 
	    HashMap<String, String> getvideocommit = videoCommentsAPI.getVideoComments(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode)+getvideocommit.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode)+getvideocommit.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(getvideocommit.get(IVideoAccessControlService.title));
		Thread.sleep(10000);
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(getvideocommit.get(IVideoAccessControlService.title));
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(getvideocommit.get(IVideoAccessControlService.title)),statusValue, driver, sTestcaseName);
		commentspage.click_commentsLink();
		customReport.customizedReport(getvideocommit.get(IVideoAccessControlService.commenttext),homePage.verify_label(getvideocommit.get(IVideoAccessControlService.commenttext)), statusValue, driver, sTestcaseName);
		String videimporteddetails=commentspage.get_commentUserName(addvideocommentbean);
	    customReport.customizedReport(getvideocommit.get(IVideoAccessControlService.firstname),videimporteddetails,statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description="Vidoe Comment API - SubmitComment Media Admin with Valid data",groups = {GETVIDEOCOMMENTSAPI})
	public void TC_02_GET_GetVideosCommentsAPI_api_check_with_MediaAdmins_Positive(ITestContext context) {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.GetVideoCommentsmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.GetVideoCommentsmediaadmin);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));		
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode ,uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	
		VideoCommentsAPI videoCommentsAPI=new VideoCommentsAPI();
		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		HashMap<String, String> submitcommit = videoCommentsAPI.putsubmitComments(uploadvidoeapiresponse, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		 
	    HashMap<String, String> getvideocommit = videoCommentsAPI.getVideoComments(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode)+getvideocommit.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode)+getvideocommit.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    	    
	    logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(getvideocommit.get(IVideoAccessControlService.title));
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(getvideocommit.get(IVideoAccessControlService.title));
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(getvideocommit.get(IVideoAccessControlService.title)),statusValue, driver, sTestcaseName);
		commentspage.click_commentsLink();
		customReport.customizedReport(getvideocommit.get(IVideoAccessControlService.commenttext),homePage.verify_label(getvideocommit.get(IVideoAccessControlService.commenttext)), statusValue, driver, sTestcaseName);
		String videimporteddetails=commentspage.get_commentUserName(addvideocommentbean);
	    customReport.customizedReport(getvideocommit.get(IVideoAccessControlService.firstname),videimporteddetails,statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Vidoe Comment API - SubmitComment Media Admin with Valid data",groups = {GETVIDEOCOMMENTSAPI})
	public void TC_03_GET_GetVideosCommentsAPI_api_check_with_EventAdmins_Positive(ITestContext context) {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {	}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
	 	loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		 
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UserServices userservices_eventadmin = new UserServices();
		loginapiresponse_eventadmin = userservices_eventadmin.doLogin(apiutils.userJson(IUsersList.GetVideoCommentseventadmin), surl);
		loginapiresponse_eventadmin.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_eventadmin.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
	
		VideoCommentsAPI videoCommentsAPI=new VideoCommentsAPI();
		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		HashMap<String, String> submitcommit = videoCommentsAPI.putsubmitComments(loginapiresponse_eventadmin, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		 
	    HashMap<String, String> getvideocommit = videoCommentsAPI.getVideoComments(loginapiresponse_eventadmin);
	    assertionapiresponse.verifyAssert_httpCode(getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode)+getvideocommit.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode)+getvideocommit.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    
	    logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(getvideocommit.get(IVideoAccessControlService.title));
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(getvideocommit.get(IVideoAccessControlService.title));
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(getvideocommit.get(IVideoAccessControlService.title)),statusValue, driver, sTestcaseName);
		commentspage.click_commentsLink();
		customReport.customizedReport(getvideocommit.get(IVideoAccessControlService.commenttext),homePage.verify_label(getvideocommit.get(IVideoAccessControlService.commenttext)), statusValue, driver, sTestcaseName);
		String videimporteddetails=commentspage.get_commentUserName(addvideocommentbean);
	    customReport.customizedReport(getvideocommit.get(IVideoAccessControlService.firstname),videimporteddetails,statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Vidoe Comment API - SubmitComment Media Contributor with Valid data",groups = {GETVIDEOCOMMENTSAPI})
	public void TC_04_GET_GetVideosCommentsAPI_api_check_with_MediaContributor_Positive(ITestContext context) {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {		}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.GetVideoCommentsmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.GetVideoCommentsmediacontributor);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));		
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode ,uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	
		VideoCommentsAPI videoCommentsAPI=new VideoCommentsAPI();
		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		HashMap<String, String> submitcommit = videoCommentsAPI.putsubmitComments(uploadvidoeapiresponse, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		 
	    HashMap<String, String> getvideocommit = videoCommentsAPI.getVideoComments(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode)+getvideocommit.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode)+getvideocommit.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
	    logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(getvideocommit.get(IVideoAccessControlService.title));
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(getvideocommit.get(IVideoAccessControlService.title));
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(getvideocommit.get(IVideoAccessControlService.title)),statusValue, driver, sTestcaseName);
		commentspage.click_commentsLink();
		customReport.customizedReport(getvideocommit.get(IVideoAccessControlService.commenttext),homePage.verify_label(getvideocommit.get(IVideoAccessControlService.commenttext)), statusValue, driver, sTestcaseName);
		String videimporteddetails=commentspage.get_commentUserName(addvideocommentbean);
	    customReport.customizedReport(getvideocommit.get(IVideoAccessControlService.firstname),videimporteddetails,statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	    
	}
	
	@Test(description="Vidoe Comment API - SubmitComment Media Viewer with Valid data",groups = {GETVIDEOCOMMENTSAPI})
	public void TC_05_GET_GetVideosCommentsAPI_api_check_with_Mediaviewer_Positive(ITestContext context) {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
	 	loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		 
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UserServices userservices_mediaviewer = new UserServices();
		loginapiresponse_mediaviewer = userservices_mediaviewer.doLogin(apiutils.userJson(IUsersList.GetVideoCommentsmediaviewer), surl);
		loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
	
		VideoCommentsAPI videoCommentsAPI=new VideoCommentsAPI();
		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		HashMap<String, String> submitcommit = videoCommentsAPI.putsubmitComments(loginapiresponse_mediaviewer, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		 
	    HashMap<String, String> getvideocomment = videoCommentsAPI.getVideoComments(loginapiresponse_mediaviewer);
	    assertionapiresponse.verifyAssert_httpCode(getvideocomment.get(IAPIConstantCodes.APIResponseHttpCode)+getvideocomment.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, getvideocomment.get(IAPIConstantCodes.APIResponseHttpCode)+getvideocomment.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    
	    logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(getvideocomment.get(IVideoAccessControlService.title));
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(getvideocomment.get(IVideoAccessControlService.title));
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(getvideocomment.get(IVideoAccessControlService.title)),statusValue, driver, sTestcaseName);
		commentspage.click_commentsLink();
		customReport.customizedReport(getvideocomment.get(IVideoAccessControlService.commenttext),homePage.verify_label(getvideocomment.get(IVideoAccessControlService.commenttext)), statusValue, driver, sTestcaseName);
		String videimporteddetails=commentspage.get_commentUserName(addvideocommentbean);
	    customReport.customizedReport(getvideocomment.get(IVideoAccessControlService.firstname),videimporteddetails,statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Vidoe Comment API - SubmitComment Account Admin with out giving commet",groups = {GETVIDEOCOMMENTSAPI})
	public void TC_06_GET_GetVideosCommentsAPI_api_check_with_AccountAdmins_Positive(ITestContext context) {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();		
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);																																				
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		VideoCommentsAPI videoCommentsAPI=new VideoCommentsAPI();
	    HashMap<String, String> getvideocommit = videoCommentsAPI.getVideoComments(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode)+getvideocommit.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode)+getvideocommit.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);
	}
	
	@Test(description="Vidoe Comment API - SubmitComment Account Admin with invalid videoid",groups = {GETVIDEOCOMMENTSAPI})
	public void TC_07_GET_GetVideosCommentsAPI_api_check_with_AccountAdmins_InvalidVideoID_Negative(ITestContext context) {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		loginapiresponse.put(IAPIConstantCodes.APIResponseVIDEOID, IDeleteVideoService.invalidVideoId);
		
		VideoCommentsAPI videoCommentsAPI=new VideoCommentsAPI();
	    HashMap<String, String> getvideocommit = videoCommentsAPI.getVideoComments(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
	    customReport.customizedReport(HttpStatusCode.httpsStatus401, getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Vidoe Comment API - SubmitComment Account Admin with VideoId private and getting commit by MediaViewer",groups = {GETVIDEOCOMMENTSAPI})
	public void TC_08_GET_GetVideosCommentsAPI_api_check_with_AccountAdmins_Negative(ITestContext context) {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);																																				
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		//Submit Commit By Account Admin
		VideoCommentsAPI videoCommentsAPI=new VideoCommentsAPI();
		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		HashMap<String, String> submitcommit = videoCommentsAPI.putsubmitComments(uploadvidoeapiresponse, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
         
		//Media Viewer Login
		UserServices userservices_eventadmin = new UserServices();
	    loginapiresponse_mediaviewer = userservices_eventadmin.doLogin(apiutils.userJson(IUsersList.GetVideoCommentsmediaviewer), surl);
	    loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
	    loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
			
	    //Get Commit By MediaViewer
	    HashMap<String, String> getvideocommit = videoCommentsAPI.getVideoComments(loginapiresponse_mediaviewer);
	    assertionapiresponse.verifyAssert_httpCode(getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
	    customReport.customizedReport(HttpStatusCode.httpsStatus401, getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);

	}
	
	@Test(description="Vidoe Comment API - PostComment Account Admin with Valid data and  get video comment with query param showall as false ",groups = {GETVIDEOCOMMENTSAPI})
	public void TC_09_GET_GetVideosCommentsAPI_api_check_with_AccountAdmins_withqueryparams_showAll_AsFalse_Positive_AV29374(ITestContext context) throws Exception {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();		
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);																																				
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		VideoCommentsAPI videoCommentsAPI=new VideoCommentsAPI(); 		
		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		addvideocommentbean.setUserName(loginapiresponse.get(IAPIConstantCodes.APIUSERNAME));
		
		HashMap<String, String> submitcommit = videoCommentsAPI.putsubmitComments(uploadvidoeapiresponse, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		uploadvidoeapiresponse.put(IAPIConstantCodes.showAll,IAPIConstantCodes.FALSE);
		JSONObject getvideocommit = videoCommentsAPI.getVideoCommmentsQueryparams(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode).toString(), HttpStatusCode.httpsStatusCode200);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode).toString(),statusValue, driver, sTestcaseName);
	    //assert with Isremoved
	    customReport.customizedReport(IAPIConstantCodes.FALSE,getvideocommit.get(IAPIConstantCodes.ISremoved).toString(),statusValue, driver, sTestcaseName); 
	    
	    
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(getvideocommit.get(IVideoAccessControlService.title).toString());
		Thread.sleep(10000);
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(getvideocommit.get(IVideoAccessControlService.title).toString());
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(getvideocommit.get(IVideoAccessControlService.title).toString()),statusValue, driver, sTestcaseName);
		commentspage.click_commentsLink();
		customReport.customizedReport(getvideocommit.get(IVideoAccessControlService.commenttext).toString(),homePage.verify_label(getvideocommit.get(IVideoAccessControlService.commenttext).toString()), statusValue, driver, sTestcaseName);
		String videimporteddetails=commentspage.get_commentUserName(addvideocommentbean);
	    customReport.customizedReport(getvideocommit.get(IVideoAccessControlService.firstname).toString(),videimporteddetails,statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description="Vidoe Comment API - PutComment as Media Admin with Valid data and delete comment on video and get video comment with query param showall as True and assert it with deletedtext ",groups = {GETVIDEOCOMMENTSAPI})
	public void TC_10_GET_GetVideosCommentsAPI_api_check_with_MediaAdmins_withqueryparams_showAll_AsTrue_withDeletedtext_Positive_AV29374(ITestContext context) throws Exception {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.GetVideoCommentsmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();		
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.GetVideoCommentsmediaadmin);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);																																				
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		VideoCommentsAPI videoCommentsAPI=new VideoCommentsAPI(); 		
		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		addvideocommentbean.setUserName(loginapiresponse.get(IAPIConstantCodes.APIUSERNAME));
		
		HashMap<String, String> submitcommit = videoCommentsAPI.putsubmitComments(uploadvidoeapiresponse, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		logger.info("Delete video comment Code is executing%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		DeleteVideoCommentsAPI deleteVideoCommentsAPI=new DeleteVideoCommentsAPI();
		HashMap<String, String> deletevideoapiresponse = deleteVideoCommentsAPI.deleteVideocomments(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deletevideoapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deletevideoapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
			
		uploadvidoeapiresponse.put(IAPIConstantCodes.showAll,IAPIConstantCodes.TRUE);
		
		JSONObject getvideocommit = videoCommentsAPI.getVideoCommmentsQueryparams(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode).toString(), HttpStatusCode.httpsStatusCode200);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode).toString(),statusValue, driver, sTestcaseName);
	    //assert with deleted text
	    customReport.customizedReport(addvideocommentbean.getComment(),getvideocommit.get(IVideoAccessControlService.commenttext).toString(),statusValue, driver, sTestcaseName); 
		customReport.checkinglist(statusValue);
	
	}
	
	@Test(description="Vidoe Comment API - PutComment as Account Admin with Valid data and delete comment on video and get video comment with query param showall as True and assert it with username ",groups = {GETVIDEOCOMMENTSAPI})
	public void TC_11_GET_GetVideosCommentsAPI_api_check_with_AccountAdmins_withqueryparams_showAll_AsTrue_withusername_Positive_AV29374(ITestContext context) throws Exception {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();		
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);																																				
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		VideoCommentsAPI videoCommentsAPI=new VideoCommentsAPI(); 		
		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		addvideocommentbean.setUserName(loginapiresponse.get(IAPIConstantCodes.APIUSERNAME));
		
		HashMap<String, String> submitcommit = videoCommentsAPI.putsubmitComments(uploadvidoeapiresponse, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		logger.info("Delete video comment Code is executing%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		DeleteVideoCommentsAPI deleteVideoCommentsAPI=new DeleteVideoCommentsAPI();
		HashMap<String, String> deletevideoapiresponse = deleteVideoCommentsAPI.deleteVideocomments(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deletevideoapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deletevideoapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
			
		uploadvidoeapiresponse.put(IAPIConstantCodes.showAll,IAPIConstantCodes.TRUE);
		
		JSONObject getvideocommit = videoCommentsAPI.getVideoCommmentsQueryparams(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode).toString(), HttpStatusCode.httpsStatusCode200);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode).toString(),statusValue, driver, sTestcaseName);
	    //assert with Username
	    customReport.customizedReport(sUserName,getvideocommit.get(IVideoAccessControlService.username).toString(),statusValue, driver, sTestcaseName); 
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description="Vidoe Comment API - PutComment as Account Admin with Valid data and delete comment on video and get video comment with query param showall as True and assert it with firstname ",groups = {GETVIDEOCOMMENTSAPI})
	public void TC_12_GET_GetVideosCommentsAPI_api_check_with_AccountAdmins_withqueryparams_showAll_AsTrue_withfirstname_Positive_AV29374(ITestContext context) throws Exception {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();		
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);																																				
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		VideoCommentsAPI videoCommentsAPI=new VideoCommentsAPI(); 		
		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		addvideocommentbean.setUserName(loginapiresponse.get(IAPIConstantCodes.APIUSERNAME));
		
		HashMap<String, String> submitcommit = videoCommentsAPI.putsubmitComments(uploadvidoeapiresponse, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		logger.info("Delete video comment Code is executing%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		DeleteVideoCommentsAPI deleteVideoCommentsAPI=new DeleteVideoCommentsAPI();
		HashMap<String, String> deletevideoapiresponse = deleteVideoCommentsAPI.deleteVideocomments(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deletevideoapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deletevideoapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
			
		uploadvidoeapiresponse.put(IAPIConstantCodes.showAll,IAPIConstantCodes.TRUE);
		
		JSONObject getvideocommit = videoCommentsAPI.getVideoCommmentsQueryparams(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode).toString(), HttpStatusCode.httpsStatusCode200);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode).toString(),statusValue, driver, sTestcaseName);
	    //assert with Firstname
	    customReport.customizedReport(loginapiresponse.get(IAPIConstantCodes.APIResponseFirstName),getvideocommit.get(IAPIConstantCodes.APIResponseFirstName).toString(),statusValue, driver, sTestcaseName); 
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description="Vidoe Comment API - PutComment as Account Admin with Valid data and delete comment on video and get video comment with query param showall as True and assert it with lastname ",groups = {GETVIDEOCOMMENTSAPI})
	public void TC_13_GET_GetVideosCommentsAPI_api_check_with_AccountAdmins_withqueryparams_showAll_AsTrue_withlastname_Positive_AV29374(ITestContext context) throws Exception {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();		
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);																																				
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		VideoCommentsAPI videoCommentsAPI=new VideoCommentsAPI(); 		
		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		addvideocommentbean.setUserName(loginapiresponse.get(IAPIConstantCodes.APIUSERNAME));
		
		HashMap<String, String> submitcommit = videoCommentsAPI.putsubmitComments(uploadvidoeapiresponse, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		logger.info("Delete video comment Code is executing%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		DeleteVideoCommentsAPI deleteVideoCommentsAPI=new DeleteVideoCommentsAPI();
		HashMap<String, String> deletevideoapiresponse = deleteVideoCommentsAPI.deleteVideocomments(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deletevideoapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deletevideoapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
			
		uploadvidoeapiresponse.put(IAPIConstantCodes.showAll,IAPIConstantCodes.TRUE);
		
		JSONObject getvideocommit = videoCommentsAPI.getVideoCommmentsQueryparams(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode).toString(), HttpStatusCode.httpsStatusCode200);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode).toString(),statusValue, driver, sTestcaseName);
	    //assert with lastname
	    customReport.customizedReport(loginapiresponse.get(IAPIConstantCodes.APIResponseLastName),getvideocommit.get(IAPIConstantCodes.APIResponseLastName).toString(),statusValue, driver, sTestcaseName); 
		customReport.checkinglist(statusValue);
		
	}

	@Test(description="Vidoe Comment API - PutComment as Account Admin with Valid data and delete comment on video and get video comment with query param showall as True and assert it with deletedBy ",groups = {GETVIDEOCOMMENTSAPI})
	public void TC_14_GET_GetVideosCommentsAPI_api_check_with_AccountAdmins_withqueryparams_showAll_AsTrue_withDeletedBy_Positive_AV29374(ITestContext context) throws Exception {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();		
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);																																				
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		//login as media viewer
		HashMap<String,String> loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.GetVideoCommentsmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		loginapiresponse1.put(IAPIConstantCodes.APIVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIVIDEOID));
				
		//sumbit videocomment as media vieewer
		VideoCommentsAPI videoCommentsAPI=new VideoCommentsAPI(); 		
		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		addvideocommentbean.setUserName(loginapiresponse1.get(IAPIConstantCodes.APIUSERNAME));
		 
		HashMap<String, String> submitcommit = videoCommentsAPI.putsubmitComments(loginapiresponse1, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		
		logger.info("Delete video comment Code is executing%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		DeleteVideoCommentsAPI deleteVideoCommentsAPI=new DeleteVideoCommentsAPI();
		HashMap<String, String> deletevideoapiresponse = deleteVideoCommentsAPI.deleteVideocomments(loginapiresponse1);
	    assertionapiresponse.verifyAssert_httpCode(deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deletevideoapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deletevideoapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	 		
		uploadvidoeapiresponse.put(IAPIConstantCodes.showAll,IAPIConstantCodes.TRUE);
		//get video comment as account admin
		JSONObject getvideocommit = videoCommentsAPI.getVideoCommmentsQueryparams(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode).toString(), HttpStatusCode.httpsStatusCode200);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode).toString(),statusValue, driver, sTestcaseName);
	    //assert with date
	    customReport.customizedReport(loginapiresponse1.get(IAPIConstantCodes.APIUSERNAME),getvideocommit.get(IVideoAccessControlService.deletedBy).toString(),statusValue, driver, sTestcaseName); 
		customReport.checkinglist(statusValue);

	}	
	@Test(description="Vidoe Comment API - PutComment as Account Admin with Valid data and delete comment on video and get video comment with query param showall as True and assert it with deleted when ",groups = {GETVIDEOCOMMENTSAPI})
	public void TC_15_GET_GetVideosCommentsAPI_api_check_with_AccountAdmins_withqueryparams_showAll_AsTrue_withdeletedwheen_Positive_AV29374(ITestContext context) throws Exception {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();		
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);																																				
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		VideoCommentsAPI videoCommentsAPI=new VideoCommentsAPI(); 		
		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		addvideocommentbean.setUserName(loginapiresponse.get(IAPIConstantCodes.APIUSERNAME));
		
		HashMap<String, String> submitcommit = videoCommentsAPI.putsubmitComments(uploadvidoeapiresponse, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		
		logger.info("Delete video comment Code is executing%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		DeleteVideoCommentsAPI deleteVideoCommentsAPI=new DeleteVideoCommentsAPI();
		HashMap<String, String> deletevideoapiresponse = deleteVideoCommentsAPI.deleteVideocomments(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deletevideoapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deletevideoapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    String datefromAPI=deletevideoapiresponse.get(IVideoAccessControlService.deletedWhen).toString();
	    String [] splitDate = datefromAPI.split("T");
        String datefromdeletevideoresponse = splitDate[0];   
        
	    
			
		uploadvidoeapiresponse.put(IAPIConstantCodes.showAll,IAPIConstantCodes.TRUE);
		
		JSONObject getvideocommit = videoCommentsAPI.getVideoCommmentsQueryparams(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode).toString(), HttpStatusCode.httpsStatusCode200);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode).toString(),statusValue, driver, sTestcaseName);
	    String datefromAPI1=getvideocommit.get(IVideoAccessControlService.deletedWhen).toString();
	    String [] splitDate1 = datefromAPI1.split("T");
        String datefromgetvideoresponse = splitDate1[0];   
        
	    //assert with deletedWhen
	    assertionapiresponse.verifyAssert_httpCode(datefromdeletevideoresponse,datefromgetvideoresponse); 
		customReport.checkinglist(statusValue);
	
	
	}
	
	
	@Test(description="Vidoe Comment API - PutComment as Account Admin with Valid data and delete comment on video and get video comment with query param showall as True and assert it with date ",groups = {GETVIDEOCOMMENTSAPI})
	public void TC_16_GET_GetVideosCommentsAPI_api_check_with_AccountAdmins_withqueryparams_showAll_AsTrue_withdate_Positive_AV29374(ITestContext context) throws Exception {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();		
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);																																				
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		VideoCommentsAPI videoCommentsAPI=new VideoCommentsAPI(); 		
		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		addvideocommentbean.setUserName(loginapiresponse.get(IAPIConstantCodes.APIUSERNAME));
		
		HashMap<String, String> submitcommit = videoCommentsAPI.putsubmitComments(uploadvidoeapiresponse, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		 String datefromAPI=submitcommit.get("date").toString();
		    String [] splitDate = datefromAPI.split("T");
	        String datefromsubmitcommentresponse = splitDate[0];   
		
		logger.info("Delete video comment Code is executing%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		DeleteVideoCommentsAPI deleteVideoCommentsAPI=new DeleteVideoCommentsAPI();
		HashMap<String, String> deletevideoapiresponse = deleteVideoCommentsAPI.deleteVideocomments(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deletevideoapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deletevideoapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
			
		uploadvidoeapiresponse.put(IAPIConstantCodes.showAll,IAPIConstantCodes.TRUE);
		
		JSONObject getvideocommit = videoCommentsAPI.getVideoCommmentsQueryparams(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode).toString(), HttpStatusCode.httpsStatusCode200);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode).toString(),statusValue, driver, sTestcaseName);
	    String datefromAPI1=getvideocommit.get(IVideoAccessControlService.date).toString();
	    String [] splitDate1 = datefromAPI1.split("T");
        String datefromgetvideoresponse = splitDate1[0];  
	    
	    //assert with date
	    customReport.customizedReport(datefromsubmitcommentresponse,datefromgetvideoresponse,statusValue, driver, sTestcaseName); 
		customReport.checkinglist(statusValue);
	
	
	}
	
	@Test(description="Vidoe Comment API - PutComment as  Media viewer with Valid data and delete comment on video as account admin and get video comment with query param showall as True and assert it with deletedBy ",groups = {GETVIDEOCOMMENTSAPI})
	public void TC_17_GET_GetVideosCommentsAPI_api_check_with_AccountAdmins_withqueryparams_showAll_AsTrue_withDeletedBy_Positive_AV29374(ITestContext context) throws Exception {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();		
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);																																				
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		//login as media viewer
		HashMap<String,String> loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.GetVideoCommentsmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		loginapiresponse1.put(IAPIConstantCodes.APIVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIVIDEOID));
				
		//sumbit videocomment as media vieewer
		VideoCommentsAPI videoCommentsAPI=new VideoCommentsAPI(); 		
		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		addvideocommentbean.setUserName(loginapiresponse1.get(IAPIConstantCodes.APIUSERNAME));
		 
		HashMap<String, String> submitcommit = videoCommentsAPI.putsubmitComments(loginapiresponse1, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		//Delete comment as account admin
		logger.info("Delete video comment Code is executing%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		DeleteVideoCommentsAPI deleteVideoCommentsAPI=new DeleteVideoCommentsAPI();
		HashMap<String, String> deletevideoapiresponse = deleteVideoCommentsAPI.deleteVideocomments(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deletevideoapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deletevideoapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	 		
		uploadvidoeapiresponse.put(IAPIConstantCodes.showAll,IAPIConstantCodes.TRUE);
		
		//get video comment as account admin
		JSONObject getvideocommit = videoCommentsAPI.getVideoCommmentsQueryparams(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode).toString(), HttpStatusCode.httpsStatusCode200);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode).toString(),statusValue, driver, sTestcaseName);
	    //assert with date
	    customReport.customizedReport(loginapiresponse.get(IAPIConstantCodes.APIUSERNAME),getvideocommit.get(IVideoAccessControlService.deletedBy).toString(),statusValue, driver, sTestcaseName); 
		customReport.checkinglist(statusValue);

	}	
	
	@Test(description="Vidoe Comment API - PutComment as Account admin with Valid data and delete comment on video as media viewer and get video comment with query param showall as True and assert it with username ",groups = {GETVIDEOCOMMENTSAPI})
	public void TC_18_GET_GetVideosCommentsAPI_api_check_with_AccountAdmins_withqueryparams_showAll_AsTrue_Negative_AV29374(ITestContext context) throws Exception {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();		
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);																																				
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		//login as media viewer
		HashMap<String,String> loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.GetVideoCommentsmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		loginapiresponse1.put(IAPIConstantCodes.APIVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIVIDEOID));
				
		//sumbit videocomment as account admin
		VideoCommentsAPI videoCommentsAPI=new VideoCommentsAPI(); 		
		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		addvideocommentbean.setUserName(loginapiresponse.get(IAPIConstantCodes.APIUSERNAME));
		 
		HashMap<String, String> submitcommit = videoCommentsAPI.putsubmitComments(uploadvidoeapiresponse, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		//Delete comment as media viewer
		logger.info("Delete video comment Code is executing%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		DeleteVideoCommentsAPI deleteVideoCommentsAPI=new DeleteVideoCommentsAPI();
		HashMap<String, String> deletevideoapiresponse = deleteVideoCommentsAPI.deleteVideocomments(loginapiresponse1);
	    assertionapiresponse.verifyAssert_httpCode(deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
	    customReport.customizedReport(HttpStatusCode.httpsStatus401, deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	 		
		uploadvidoeapiresponse.put(IAPIConstantCodes.showAll,IAPIConstantCodes.TRUE);
		
		//get video comment as account admin
		JSONObject getvideocommit = videoCommentsAPI.getVideoCommmentsQueryparams(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode).toString(), HttpStatusCode.httpsStatusCode200);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode).toString(),statusValue, driver, sTestcaseName);
	    //assert with Username
	    customReport.customizedReport(sUserName,getvideocommit.get(IVideoAccessControlService.username).toString(),statusValue, driver, sTestcaseName); 
		customReport.checkinglist(statusValue);

	}	
	
	@Test(description="Vidoe Comment API - PutComment as Media Viewer and delete comment on video as media viewer and get video comment as Media viewer with query param showall as True ",groups = {GETVIDEOCOMMENTSAPI})
	public void TC_19_GET_GetVideosCommentsAPI_api_check_with_Mediaviewer_withqueryparams_showAll_AsTrue_Negative_AV29374(ITestContext context) throws Exception {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();		
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);																																				
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		//login as media viewer
		HashMap<String,String> loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.GetVideoCommentsmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		loginapiresponse1.put(IAPIConstantCodes.APIVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIVIDEOID));
				
		//sumbit videocomment as media viewer
		VideoCommentsAPI videoCommentsAPI=new VideoCommentsAPI(); 		
		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		addvideocommentbean.setUserName(loginapiresponse1.get(IAPIConstantCodes.APIUSERNAME));
		 
		HashMap<String, String> submitcommit = videoCommentsAPI.putsubmitComments(uploadvidoeapiresponse, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		//Delete comment as media viewer
		logger.info("Delete video comment Code is executing%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		DeleteVideoCommentsAPI deleteVideoCommentsAPI=new DeleteVideoCommentsAPI();
		HashMap<String, String> deletevideoapiresponse = deleteVideoCommentsAPI.deleteVideocomments(loginapiresponse1);
	    assertionapiresponse.verifyAssert_httpCode(deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200, deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	 		
	    loginapiresponse1.put(IAPIConstantCodes.showAll,IAPIConstantCodes.TRUE);
		
		//get video comment as media viewer
	    HashMap<String, String> getvideocommit = videoCommentsAPI.getVideoCommmentsQueryparam(loginapiresponse1);
	    assertionapiresponse.verifyAssert_httpCode(getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode).toString(), HttpStatusCode.httpsStatus401);
	    customReport.customizedReport(HttpStatusCode.httpsStatus401, getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode).toString(),statusValue, driver, sTestcaseName);  
	    customReport.checkinglist(statusValue);
	}	
	
	
	@Test(description="Vidoe Comment API - PutComment as EventAdmin and delete comment on video as event admin and get video comment as event Admin with query param showall as True ",groups = {GETVIDEOCOMMENTSAPI})
	public void TC_20_GET_GetVideosCommentsAPI_api_check_with_EventAdmin_withqueryparams_showAll_AsTrue_Negative_AV29374(ITestContext context) throws Exception {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();		
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);																																				
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		//login as event admin
		HashMap<String,String> loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.GetVideoCommentseventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		loginapiresponse1.put(IAPIConstantCodes.APIVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIVIDEOID));
				
		//sumbit videocomment as  event admin
		VideoCommentsAPI videoCommentsAPI=new VideoCommentsAPI(); 		
		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		addvideocommentbean.setUserName(loginapiresponse1.get(IAPIConstantCodes.APIUSERNAME));
		 
		HashMap<String, String> submitcommit = videoCommentsAPI.putsubmitComments(uploadvidoeapiresponse, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		//Delete comment as  event admin
		logger.info("Delete video comment Code is executing%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		DeleteVideoCommentsAPI deleteVideoCommentsAPI=new DeleteVideoCommentsAPI();
		HashMap<String, String> deletevideoapiresponse = deleteVideoCommentsAPI.deleteVideocomments(loginapiresponse1);
	    assertionapiresponse.verifyAssert_httpCode(deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200, deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	 		
	    loginapiresponse1.put(IAPIConstantCodes.showAll,IAPIConstantCodes.TRUE);
		
		//get video comment as  event admin
	    HashMap<String, String> getvideocommit = videoCommentsAPI.getVideoCommmentsQueryparam(loginapiresponse1);
	    assertionapiresponse.verifyAssert_httpCode(getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode).toString(), HttpStatusCode.httpsStatus401);
	    customReport.customizedReport(HttpStatusCode.httpsStatus401, getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode).toString(),statusValue, driver, sTestcaseName);  
	    customReport.checkinglist(statusValue);
	}	
	@Test(description="Vidoe Comment API - PutComment as Media Contributor and delete comment on video as media contributor and get video comment as media Contributor with query param showall as True",groups = {GETVIDEOCOMMENTSAPI})
	public void TC_21_GET_GetVideosCommentsAPI_api_check_with_MediaContributor_withqueryparams_showAll_AsTrue_Negative_AV29374(ITestContext context) throws Exception {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();		
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);																																				
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		//login as media contributor
		HashMap<String,String> loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.GetVideoCommentsmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		loginapiresponse1.put(IAPIConstantCodes.APIVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIVIDEOID));
				
		//sumbit videocomment as media contributor
		VideoCommentsAPI videoCommentsAPI=new VideoCommentsAPI(); 		
		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		addvideocommentbean.setUserName(loginapiresponse1.get(IAPIConstantCodes.APIUSERNAME));
		 
		HashMap<String, String> submitcommit = videoCommentsAPI.putsubmitComments(uploadvidoeapiresponse, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		//Delete comment as  media contributor
		logger.info("Delete video comment Code is executing%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		DeleteVideoCommentsAPI deleteVideoCommentsAPI=new DeleteVideoCommentsAPI();
		HashMap<String, String> deletevideoapiresponse = deleteVideoCommentsAPI.deleteVideocomments(loginapiresponse1);
	    assertionapiresponse.verifyAssert_httpCode(deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200, deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	 		
	    loginapiresponse1.put(IAPIConstantCodes.showAll,IAPIConstantCodes.TRUE);
		
		//get video comment as  media contributor
	    HashMap<String, String> getvideocommit = videoCommentsAPI.getVideoCommmentsQueryparam(loginapiresponse1);
	    assertionapiresponse.verifyAssert_httpCode(getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode).toString(), HttpStatusCode.httpsStatus401);
	    customReport.customizedReport(HttpStatusCode.httpsStatus401, getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode).toString(),statusValue, driver, sTestcaseName);  
	    customReport.checkinglist(statusValue);
	}
	
	@Test(description="Vidoe Comment API - PutComment as Media Contributor and delete comment on video as media contributor and get video comment without auth token with query param showall as True",groups = {GETVIDEOCOMMENTSAPI})
	public void TC_22_GET_GetVideoCommentAPI_api_without_giving_authtoken_withqueryparams_showAll_AsTrue_Negative_AV29374(ITestContext context) throws Exception {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();		
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);																																				
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		//login as media contributor
		HashMap<String,String> loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.GetVideoCommentsmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		loginapiresponse1.put(IAPIConstantCodes.APIVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIVIDEOID));
				
		//sumbit videocomment as media contributor
		VideoCommentsAPI videoCommentsAPI=new VideoCommentsAPI(); 		
		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		addvideocommentbean.setUserName(loginapiresponse1.get(IAPIConstantCodes.APIUSERNAME));
		 
		HashMap<String, String> submitcommit = videoCommentsAPI.putsubmitComments(uploadvidoeapiresponse, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, submitcommit.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		//Delete comment as  media contributor
		logger.info("Delete video comment Code is executing%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		DeleteVideoCommentsAPI deleteVideoCommentsAPI=new DeleteVideoCommentsAPI();
		HashMap<String, String> deletevideoapiresponse = deleteVideoCommentsAPI.deleteVideocomments(loginapiresponse1);
	    assertionapiresponse.verifyAssert_httpCode(deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200, deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	 		
	    loginapiresponse1.put(IAPIConstantCodes.showAll,IAPIConstantCodes.TRUE);
	    loginapiresponse1.remove(IAPIConstantCodes.APIRequestAccessToken);
		//get video comment as  media contributor
	    HashMap<String, String> getvideocommit = videoCommentsAPI.getVideoCommmentsQueryparam(loginapiresponse1);
	    assertionapiresponse.verifyAssert_httpCode(getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode).toString(), HttpStatusCode.httpsStatus401);
	    customReport.customizedReport(HttpStatusCode.httpsStatus401, getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode).toString(),statusValue, driver, sTestcaseName);  
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
