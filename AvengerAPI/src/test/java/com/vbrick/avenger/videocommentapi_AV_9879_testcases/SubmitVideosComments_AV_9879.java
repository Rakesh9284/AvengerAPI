package com.vbrick.avenger.videocommentapi_AV_9879_testcases;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
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
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbrick.avenger.apibeans.AddVideoCommentBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerFeaturesPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerMediaSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.serviceimpl.VideoCommentsAPI;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.ConstantsValue;
import com.vbricks.avenger.utils.HttpStatusCode;

public class SubmitVideosComments_AV_9879 extends TestBase {

	private static Logger logger = Logger.getLogger(SubmitVideosComments_AV_9879.class);
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

	@Test(description = "To Verify the SumitComment on Video using SubmitComment APi with AccountAdmin",groups = {SUBMITCOMMENTSAPI})
	public void TC_01_PUT_SubmitComment_api_check_with_AccountAdmin_Positive() {

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
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		VideoCommentsAPI getvideocommentapi = new VideoCommentsAPI();
		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		HashMap<String, String> putsubmitcommentsapirespone = getvideocommentapi.putsubmitComments(uploadvidoeapiresponse, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(putsubmitcommentsapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+putsubmitcommentsapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, putsubmitcommentsapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+putsubmitcommentsapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.click_commentsLink();
		customReport.customizedReport(addvideocommentbean.getComment(),homePage.verify_label(addvideocommentbean.getComment()), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To Verify the SumitComment on Video using SubmitComment APi with Media Admin",groups = {SUBMITCOMMENTSAPI})
	public void TC_02_PUT_SubmitComment_api_check_with_MediaAdmin_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {	}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.SubmitVideosCommentsmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.SubmitVideosCommentsmediaadmin);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		VideoCommentsAPI getvideocommentapi = new VideoCommentsAPI();
		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		HashMap<String, String> putsubmitcommentsapirespone = getvideocommentapi.putsubmitComments(uploadvidoeapiresponse, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(putsubmitcommentsapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+putsubmitcommentsapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, putsubmitcommentsapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+putsubmitcommentsapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		//login as Media Admin
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.click_commentsLink();
		customReport.customizedReport(addvideocommentbean.getComment(),homePage.verify_label(addvideocommentbean.getComment()), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	@Test(description = "To Verify the SumitComment on Video using SubmitComment APi with Event Admin",groups = {SUBMITCOMMENTSAPI})
	public void TC_03_PUT_SubmitComment_api_check_with_EventAdmin_Positive() {

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
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		HashMap<String,String> eventloginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.SubmitVideosCommentseventadmin), surl);
 	    assertionapiresponse.verifyAssert_httpCode(eventloginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + eventloginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, eventloginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		eventloginapiresponse.put(IAPIConstantCodes.APIResponseVIDEOID,uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID) );
		
		VideoCommentsAPI getvideocommentapi = new VideoCommentsAPI();
		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		HashMap<String, String> putsubmitcommentsapirespone = getvideocommentapi.putsubmitComments(eventloginapiresponse, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(putsubmitcommentsapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+putsubmitcommentsapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, putsubmitcommentsapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+putsubmitcommentsapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		//login as Media Admin
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.click_commentsLink();
		customReport.customizedReport(addvideocommentbean.getComment(),homePage.verify_label(addvideocommentbean.getComment()), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
	}
	@Test(description = "To Verify the SumitComment on Video using SubmitComment APi with Media Contributor",groups = {SUBMITCOMMENTSAPI})
	public void TC_04_PUT_SubmitComment_api_check_with_MediaContributor_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.SubmitVideosCommentsmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.SubmitVideosCommentsmediacontributor);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,	adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		VideoCommentsAPI getvideocommentapi = new VideoCommentsAPI();
		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		HashMap<String, String> putsubmitcommentsapirespone = getvideocommentapi.putsubmitComments(uploadvidoeapiresponse, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(putsubmitcommentsapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+putsubmitcommentsapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, putsubmitcommentsapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+putsubmitcommentsapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.click_commentsLink();
		customReport.customizedReport(addvideocommentbean.getComment(),homePage.verify_label(addvideocommentbean.getComment()), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To Verify the SumitComment on Video using SubmitComment APi with Media Viewer",groups = {SUBMITCOMMENTSAPI})
	public void TC_05_PUT_SubmitComment_api_check_with_MediaViewer_Positive() {

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
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,	adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		HashMap<String,String> mediaViewerloginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.SubmitVideosCommentsmediaviewer), surl);
 	    assertionapiresponse.verifyAssert_httpCode(mediaViewerloginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + mediaViewerloginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, mediaViewerloginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	    
	    mediaViewerloginapiresponse.put(IAPIConstantCodes.APIResponseVIDEOID,uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID) );
		VideoCommentsAPI videocommentapi = new VideoCommentsAPI();
		apibeanfactory.SumbitCommentBean(addvideocommentbean);
	
		HashMap<String, String> putsubmitcommentsapirespone = videocommentapi.putsubmitComments(mediaViewerloginapiresponse, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(putsubmitcommentsapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+putsubmitcommentsapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, putsubmitcommentsapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+putsubmitcommentsapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.click_commentsLink();
		customReport.customizedReport(addvideocommentbean.getComment(),homePage.verify_label(addvideocommentbean.getComment()), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
	}
	@Test(description = "To Verify the SumitComment on Video using SubmitComment APi with AccountAdmin on Invalid VideoId",groups = {SUBMITCOMMENTSAPI})
	public void TC_06_PUT_SubmitComment_api_check_with_InValidVideoId_AccountAdmin_Negative() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		loginapiresponse.put(IUploadVideoService.ConstantvideoIdVar,IUploadVideoService.ConstantvideoIdvalue);
		
		VideoCommentsAPI getvideocommentapi = new VideoCommentsAPI();
		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		HashMap<String, String> putsubmitcommentsapirespone = getvideocommentapi.putsubmitComments(loginapiresponse, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(putsubmitcommentsapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus500);
	    customReport.customizedReport(HttpStatusCode.httpsStatus500, putsubmitcommentsapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To Verify the SumitComment on Video using SubmitComment APi with AccountAdmin ,when disable comment for video",groups = {SUBMITCOMMENTSAPI})
	public void TC_07_PUT_SubmitComment_api_check_with_DisableCommentForVideo_AccountAdmin_Negative() {

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
		adduploadvideobean.setEnableComments(IAPIConstantCodes.FALSE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		VideoCommentsAPI getvideocommentapi = new VideoCommentsAPI();
		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		HashMap<String, String> putsubmitcommentsapirespone = getvideocommentapi.putsubmitComments(uploadvidoeapiresponse, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(putsubmitcommentsapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
	    customReport.customizedReport(HttpStatusCode.httpsStatus401, putsubmitcommentsapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To Verify the SumitComment on Video using SubmitComment APi with AccountAdmin ,when Disable Comment Setting Feature Setting",groups = {SUBMITCOMMENTSAPI})
	public void TC_08_PUT_SubmitComment_api_check_with_DisableCommentForFeatures_AccountAdmin_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {	}.getClass().getEnclosingMethod().getName();
		
		// Disable Comments in Setting Page
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		homePage.clickSettingsLink();
		AvengerMediaSettingsPage mediasettingpage = homePage.clickMediaSettingsLink();
		AvengerFeaturesPage featurepage = mediasettingpage.click_FeaturesLinkLocator();
		featurepage.disableComments();
		featurepage.refreshBrowser();
		
		// API Comments Testing	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");

		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		VideoCommentsAPI getvideocommentapi = new VideoCommentsAPI();
		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		HashMap<String, String> putsubmitcommentsapirespone = getvideocommentapi.putsubmitComments(uploadvidoeapiresponse, addvideocommentbean); 
		assertionapiresponse.verifyAssert_httpCode(putsubmitcommentsapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
	    customReport.customizedReport(HttpStatusCode.httpsStatus401, putsubmitcommentsapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);    
		featurepage.enableComments();
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
