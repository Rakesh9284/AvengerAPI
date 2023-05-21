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
import com.vbrick.avenger.apibeans.DeleteVideoCommentBean;
import com.vbrick.avenger.apibeans.EditVideoCommentBean;
import com.vbrick.avenger.apibeans.EventAccessControlBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
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
import com.vbricks.avenger.service.IEditVideoCommentService;
import com.vbricks.avenger.service.IMegratVideoService;
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUserVideoWatchingStatusService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.service.IVideoAccessControlService;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.serviceimpl.DeleteVideoCommentsAPI;
import com.vbricks.avenger.serviceimpl.DeleteVideosAPI;
import com.vbricks.avenger.serviceimpl.EditVideoComments;
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

public class UpdateVideoComments extends TestBase {

	private static Logger logger = Logger.getLogger(UpdateVideoComments.class);
	private AvengerLoginPage loginPage;
	private AvengerHomePage homePage;
	private List<String> statusValue;
	private CustomReport customReport;
	@SuppressWarnings("unused")
	private Reasons reasons;
	private String sTestcaseName;
	private EditVideoCommentBean editVideoCommentBean;
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
		editVideoCommentBean = new EditVideoCommentBean();
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

	@Test(description="Vidoe Comment API - SubmitComment Account Admin with Valid data",groups = {UPDATEVIDEOCOMMENTSAPI})
	public void TC_01_POST_UpdateVideosCommentsAPI_api_check_with_AccountAdmins_Positive(ITestContext context) {
		
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

		//Update Root Comments
	    EditVideoComments videocomments = new EditVideoComments();
	    HashMap<String, String> comments = videocomments.editVideoComments(uploadvidoeapiresponse, videocomments.usercomment(IAPIConstantCodes.APIROOTCOMMENT));
	    assertionapiresponse.verifyAssert_httpCode(comments.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
	    
	    VideoCommentsAPI videoCommentsAPI=new VideoCommentsAPI();
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
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Vidoe Comment API - SubmitComment Account Admin with Invalid VideoID",groups = {UPDATEVIDEOCOMMENTSAPI})
	public void TC_02_POST_UpdateVideosCommentsAPI_InvalidVideoID_check_with_AccountAdmins_Negative(ITestContext context) {

        logger.info("API Level Code is excuting");
        sTestcaseName = new Object() {    }.getClass().getEnclosingMethod().getName();
     
        UserServices userservices = new UserServices();
        loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
        assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
        logger.info("Login API response Code :::" + loginapiresponse);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

        loginapiresponse.put(IAPIConstantCodes.APIResponseVIDEOID, IUserVideoWatchingStatusService.invalidVideoId);       
        logger.info("Update video comments Code is executing%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            
        //Update Root Comments           
    	EditVideoComments videocomments = new EditVideoComments();
    	HashMap<String, String> comments = videocomments.editVideoComments(loginapiresponse, videocomments.usercomment(IAPIConstantCodes.APIROOTCOMMENT));
    	assertionapiresponse.verifyAssert_httpCode(comments.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode500);
    	customReport.customizedReport(HttpStatusCode.httpsStatus500, comments.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
    	customReport.checkinglist(statusValue);
    	    
    	}
	
	@Test(description="Vidoe Comment API - SubmitComment MediaViewer(who has only view access) with Valid data",groups = {UPDATEVIDEOCOMMENTSAPI})
	public void TC_03_POST_UpdateVideosCommentsAPI_api_check_with_MediaViewer_Positive(ITestContext context) {
		
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
	
		HashMap<String,String> mediaViewerloginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UpdateCommentMediaViewer), surl);
 	    assertionapiresponse.verifyAssert_httpCode(mediaViewerloginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + mediaViewerloginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, mediaViewerloginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	    
	    mediaViewerloginapiresponse.put(IAPIConstantCodes.APIResponseVIDEOID,uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID) );		
        
	    //Update Root Comments
  	    EditVideoComments videocomments = new EditVideoComments();
  	    HashMap<String, String> comments = videocomments.editVideoComments(mediaViewerloginapiresponse,videocomments.usercomment(IAPIConstantCodes.APIROOTCOMMENT));
  	    assertionapiresponse.verifyAssert_httpCode(comments.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
  	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200, comments.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
  	    customReport.checkinglist(statusValue);
  	    
	}
	
	@Test(description="Vidoe Comment API - SubmitComment MediaViewer(who doesn't have view access) with Valid data",groups = {UPDATEVIDEOCOMMENTSAPI})
	public void TC_04_POST_UpdateVideosCommentsAPI_api_check_with_MediaViewer_Negative(ITestContext context) {
		
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
		adduploadvideobean.setIsActive(IAPIConstantCodes.FALSE);																																				
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		HashMap<String,String> mediaViewerloginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UpdateCommentMediaViewer), surl);
 	    assertionapiresponse.verifyAssert_httpCode(mediaViewerloginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + mediaViewerloginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, mediaViewerloginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	    
	    mediaViewerloginapiresponse.put(IAPIConstantCodes.APIResponseVIDEOID,uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID) );
		
        //Update Root Comments
  	    EditVideoComments videocomments = new EditVideoComments();
  	    HashMap<String, String> comments = videocomments.editVideoComments(mediaViewerloginapiresponse,videocomments.usercomment(IAPIConstantCodes.APIROOTCOMMENT));
  	    assertionapiresponse.verifyAssert_httpCode(comments.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatus401);
  	    customReport.customizedReport(HttpStatusCode.httpsStatus401, comments.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
  	    customReport.checkinglist(statusValue);
  	    
	}
	
	@Test(description="Vidoe Comment API - SubmitComment on be half of another user with Valid data",groups = {UPDATEVIDEOCOMMENTSAPI})
	public void TC_05_POST_UpdateVideosCommentsAPI_api_check_on_behalf_of_anotheruser_Positive(ITestContext context) {
		
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

        //Update Root Comments
  	    EditVideoComments videocomments = new EditVideoComments();
  	 
  	   //uploadvidoeapiresponse.get(IAPIConstantCodes.APIRESPONSEJSON);
  	    HashMap<String, String> comments = videocomments.editVideoCommentsonbehalfofanotheruser(uploadvidoeapiresponse, videocomments.usercomment1(IAPIConstantCodes.APIROOTCOMMENT, IUsersList.UpdateCommentMediaViewer));  	
  	    assertionapiresponse.verifyAssert_httpCode(comments.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
  	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200, comments.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
  	    customReport.checkinglist(statusValue);
	
  	    logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(uploadvidoeapiresponse.get(IVideoAccessControlService.videoTitle));
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(uploadvidoeapiresponse.get(IVideoAccessControlService.videoTitle));
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(uploadvidoeapiresponse.get(IVideoAccessControlService.videoTitle)),statusValue, driver, sTestcaseName);
		commentspage.click_commentsLink();
		customReport.customizedReport("apimvu52 apimvu52",homePage.verify_label(IUsersList.UpdateCommentMediaViewer+" "+IUsersList.UpdateCommentMediaViewer), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	   
	}
	
	@Test(description="Vidoe Comment API - SubmitChildComment Account Admin with Valid data",groups = {UPDATEVIDEOCOMMENTSAPI})
	public void TC_06_POST_UpdateVideosCommentsAPI_api_adding_a_Childcomment_INVALID_CommentID_AccountAdmins_Negative(ITestContext context) {
 
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

	    HashMap<String, String> getcommentid = videoCommentsAPI.getVideoCommentid(uploadvidoeapiresponse);
	    EditVideoComments videocomments = new EditVideoComments();
	    loginapiresponse.put("videoId", uploadvidoeapiresponse.get("videoId"));
	    getcommentid.put("InvalidCommentID", "e78b18b7-aa19-44ae-b32c-d262ba5987af");
	  	HashMap<String, String> comments = videocomments.addingachildComment(loginapiresponse,videocomments.childComment(IAPIConstantCodes.APICHILDCOMMENT,IUsersList.UpdateCommentMediaViewer, getcommentid.get("InvalidCommentID")));	  	 
	  	assertionapiresponse.verifyAssert_httpCode(comments.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatus404);
	  	customReport.customizedReport(HttpStatusCode.httpsStatus404, comments.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	  	customReport.checkinglist(statusValue);
	}
	
	@Test(description="Vidoe Comment API - SubmitChildComment Account Admin with Valid data",groups = {UPDATEVIDEOCOMMENTSAPI})
	public void TC_07_POST_UpdateVideosCommentsAPI_api_adding_a_Childcomment_to_a_video_AccountAdmins_Positive(ITestContext context) {
 
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

	    HashMap<String, String> getcommentid = videoCommentsAPI.getVideoCommentid(uploadvidoeapiresponse);
	    EditVideoComments videocomments = new EditVideoComments();
	    loginapiresponse.put("videoId", uploadvidoeapiresponse.get("videoId"));
	  	HashMap<String, String> comments = videocomments.addingachildComment(loginapiresponse,videocomments.childComment(IAPIConstantCodes.APICHILDCOMMENT,IUsersList.UpdateCommentMediaViewer, getcommentid.get("commentId")));	  	 
	  	assertionapiresponse.verifyAssert_httpCode(comments.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
	  	customReport.customizedReport(HttpStatusCode.httpsStatusCode200, comments.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	  	customReport.checkinglist(statusValue);
	
	  	logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(getvideocommit.get(IVideoAccessControlService.title));
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(getvideocommit.get(IVideoAccessControlService.title));
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(getvideocommit.get(IVideoAccessControlService.title)),statusValue, driver, sTestcaseName);
		commentspage.click_commentsLink();
		customReport.customizedReport(IAPIConstantCodes.APICHILDCOMMENT,homePage.verify_label(IAPIConstantCodes.APICHILDCOMMENT), statusValue, driver, sTestcaseName);
		String videimporteddetails=commentspage.get_commentUserName(addvideocommentbean);
	    customReport.customizedReport(getvideocommit.get(IVideoAccessControlService.firstname),videimporteddetails,statusValue, driver, sTestcaseName);
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
			browserQuit(driver);
		}
}