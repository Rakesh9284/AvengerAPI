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
import com.vbrick.avenger.apibeans.AddPlaylistBean;
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbrick.avenger.apibeans.AddVideoCommentBean;
import com.vbrick.avenger.apibeans.DeleteVideoCommentBean;
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
import com.vbricks.avenger.service.IDeleteVideoService;
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
import com.vbricks.avenger.serviceimpl.UserVideoCompletionAPI;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.ConstantsValue;
import com.vbricks.avenger.utils.HttpStatusCode;

import groovy.json.JsonSlurper;

public class DeleteVideoComments extends TestBase {

	private static Logger logger = Logger.getLogger(DeleteVideoComments.class);
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
	private AddPlaylistBean addplaylistbean;
	private FileUploadBeanPage fileuploadbeanpage;
	private AvengerFeaturesPage avengerfeaturespage;
	private ReadAndWriteToJSON readgriduserdata;
	private Map<String, String> userdata;
	private BasePage basePage;
	private AddUploadVideoBean adduploadvideobean;
	public ApiBeanFactory apibeanfactory;
    public AddVideoCommentBean addvideocommentbean;
    private DeleteVideoCommentBean deleteVideoCommentBean;
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
	
	@Test(description="DeleteVideo Comment API - Verify API to Delete �All comments� by Account Admin",groups = {DELETEVIDEOCOMMENTSAPI})
	public void TC_01_DeleteVideoCommentsAPI_api_check_with_AccountAdmins_Positive(ITestContext context) {
 
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

		HashMap<String, String> submitcommit1 = videoCommentsAPI.putsubmitComments(uploadvidoeapiresponse, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(submitcommit1.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit1.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, submitcommit1.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit1.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		 
	    HashMap<String, String> getvideocommit = videoCommentsAPI.getVideoComments(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode)+getvideocommit.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode)+getvideocommit.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	 
	    logger.info("Delete video comment Code is executing%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
	    DeleteVideoCommentsAPI deleteVideoCommentsAPI=new DeleteVideoCommentsAPI();
		HashMap<String, String> deletevideoapiresponse = deleteVideoCommentsAPI.deleteVideocomments(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deletevideoapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deletevideoapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	    avengerdashboardpage.searchMedia(getvideocommit.get(IVideoAccessControlService.title));
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(getvideocommit.get(IVideoAccessControlService.title));
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(getvideocommit.get(IVideoAccessControlService.title)),statusValue, driver, sTestcaseName);
		commentspage.click_settingsLink();
		commentspage.click_commentsLink();
		customReport.customizedReport("This comment has been removed",commentspage.ConfirmCommentDeletion(),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description="DeleteVideo Comment API - Trying to delete video comments by Account Admin when NO Comments are posted",groups = {DELETEVIDEOCOMMENTSAPI})
	public void TC_2_DeleteVideoCommentsAPI_NoComments_api_check_with_AccountAdmins_Negative(ITestContext context) {
 
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
			
	    logger.info("Delete video comment Code is executing%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
	    DeleteVideoCommentsAPI deleteVideoCommentsAPI=new DeleteVideoCommentsAPI();
		HashMap<String, String> deletevideoapiresponse = deleteVideoCommentsAPI.deleteVideocomments(uploadvidoeapiresponse);
		assertionapiresponse.verifyAssert_httpCode(deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus400);
		 customReport.customizedReport(HttpStatusCode.httpsStatus400, deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="DeleteVideo Comment API - Trying to delete video comments by Account Admin when CommentsDisabledforVideo",groups = {DELETEVIDEOCOMMENTSAPI})
	public void TC_03_DeleteVideoCommentsAPI_CommentsDisabledforVideo_check_with_AccountAdmins_Negative(ITestContext context) {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson1(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
 		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setDescription("");
		adduploadvideobean.setTitle("");
		adduploadvideobean.setEnableComments(IAPIConstantCodes.FALSE);
		adduploadvideobean.setEnableDownloads("");
		adduploadvideobean.setEnableRatings("");
		adduploadvideobean.setIsActive("");
		adduploadvideobean.setVideoAccessControl(null);
		adduploadvideobean.setCategories(null);
		adduploadvideobean.setCategoryIds(null);
		adduploadvideobean.setTags(null);		
		loginapiresponse.put("Mandatory", "Yes");
		loginapiresponse.put("fileName", "Yes");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
		
		logger.info("Delete video comment Code is executing%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
 	    DeleteVideoCommentsAPI deleteVideoCommentsAPI=new DeleteVideoCommentsAPI();
 		HashMap<String, String> deletevideoapiresponse = deleteVideoCommentsAPI.deleteVideocomments(uploadvidoeapiresponse);
 		logger.info(deletevideoapiresponse);	
 		assertionapiresponse.verifyAssert_httpCode(deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus400);
 		customReport.customizedReport(HttpStatusCode.httpsStatus400, deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
 		customReport.checkinglist(statusValue);
	}
	
	@Test(description="DeleteVideo Comment API - Trying to delete video comments by Account Admin with InvalidVideoID",groups = {DELETEVIDEOCOMMENTSAPI})
	public void TC_04_DeleteVideoCommentsAPI_InvalidVideoID_check_with_AccountAdmins_Negative(ITestContext context) {
     
            logger.info("API Level Code is excuting");
            sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
     
            UserServices userservices = new UserServices();
            loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
            assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
            logger.info("Login API response Code :::" + loginapiresponse);
            customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
            loginapiresponse.put(IAPIConstantCodes.APIResponseVIDEOID, IUserVideoWatchingStatusService.invalidVideoId);
                      
            logger.info("Delete video comment Code is executing%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    	    DeleteVideoCommentsAPI deleteVideoCommentsAPI=new DeleteVideoCommentsAPI();
    		HashMap<String, String> deletevideoapiresponse = deleteVideoCommentsAPI.deleteVideocomments(loginapiresponse);
    	    assertionapiresponse.verifyAssert_httpCode(deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
    	    customReport.customizedReport(HttpStatusCode.httpsStatus401, deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
    	}
	

	@Test(description="DeleteVideo Comment API - Trying to delete video comments by MediaViewer",groups = {DELETEVIDEOCOMMENTSAPI})
	public void TC_5_DeleteVideoCommentsAPI_NoEditRights_api_check_with_MediaViewer_Negative(ITestContext context) {
 
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
		loginapiresponse_mediaviewer = userservices_mediaviewer.doLogin(apiutils.userJson(IUsersList.DeleteCommentMediaViewer), surl);
			
	    logger.info("Delete video comment Code is executing%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
	    DeleteVideoCommentsAPI deleteVideoCommentsAPI=new DeleteVideoCommentsAPI();
		HashMap<String, String> deletevideoapiresponse = deleteVideoCommentsAPI.deleteVideocomments(loginapiresponse_mediaviewer);
		assertionapiresponse.verifyAssert_httpCode(deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="Video Comment API - DeleteChildComment Account Admin ",groups = {DELETEVIDEOCOMMENTSAPI})
	public void TC_06_DELETE_DeleteVideosCommentsAPI_api_Deleting_a_Childcomment_from_a_video_AccountAdmins_Positive(ITestContext context) {
 
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
	  	HashMap<String, String> comments = videocomments.addingachildComment(loginapiresponse,videocomments.childComment("I am a Child Comment","Apimvu23", getcommentid.get("commentId")));
	  	 
	  	assertionapiresponse.verifyAssert_httpCode(comments.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
	  	customReport.customizedReport(HttpStatusCode.httpsStatusCode200, comments.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	  	customReport.checkinglist(statusValue);
	  	
	  	HashMap<String, String> getvideocildcommit = videoCommentsAPI.getVideoChildCommentid(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(getvideocildcommit.get(IAPIConstantCodes.APIResponseHttpCode)+getvideocildcommit.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, getvideocildcommit.get(IAPIConstantCodes.APIResponseHttpCode)+getvideocildcommit.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	  	
	  	//Deleting a child comment	  	
	  	logger.info("Delete video comment Code is executing%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
	    DeleteVideoCommentsAPI deleteVideoCommentsAPI=new DeleteVideoCommentsAPI();
	    uploadvidoeapiresponse.put(IAPIConstantCodes.APIResponseCOMMENTIDs, getvideocildcommit.get("childcommentId"));
		HashMap<String, String> deleteVideochildcommentswithID = deleteVideoCommentsAPI.deleteVideochildcommentswithID(uploadvidoeapiresponse);
		assertionapiresponse.verifyAssert_httpCode(deleteVideochildcommentswithID.get(IAPIConstantCodes.APIResponseHttpCode)+deleteVideochildcommentswithID.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deleteVideochildcommentswithID.get(IAPIConstantCodes.APIResponseHttpCode)+deleteVideochildcommentswithID.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	  	
	    //Selenium Code to ConfirmCommentDeletion	    
	    logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	    avengerdashboardpage.searchMedia(getvideocommit.get(IVideoAccessControlService.title));
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(getvideocommit.get(IVideoAccessControlService.title));
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(getvideocommit.get(IVideoAccessControlService.title)),statusValue, driver, sTestcaseName);
		commentspage.click_settingsLink();
		commentspage.click_commentsLink();
		customReport.customizedReport("This comment has been removed",commentspage.ConfirmCommentDeletion(),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	    
	}
	
	@Test(description="DeleteVideo Comment API - Verify API to Delete �All comments� by Account Admin",groups = {DELETEVIDEOCOMMENTSAPI})
	public void TC_07_Delete_MultipleVideoCommentsAPI_api_check_with_AccountAdmins_Positive(ITestContext context) {
 
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

		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		HashMap<String, String> submitcommit2 = videoCommentsAPI.putsubmitComments(uploadvidoeapiresponse, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(submitcommit2.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit2.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, submitcommit2.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit2.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		HashMap<String, String> submitcommit3 = videoCommentsAPI.putsubmitComments(uploadvidoeapiresponse, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(submitcommit3.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit3.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, submitcommit3.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit3.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		 
	    HashMap<String, String> getvideocommit = videoCommentsAPI.getVideoComments(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode)+getvideocommit.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode)+getvideocommit.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

	    HashMap<String, String> getmultiplecommentids = videoCommentsAPI.getVideomultipleCommentids(uploadvidoeapiresponse);
	    EditVideoComments videocomments = new EditVideoComments();
	    loginapiresponse.put("videoId", uploadvidoeapiresponse.get("videoId"));
	    assertionapiresponse.verifyAssert_httpCode(getmultiplecommentids.get(IAPIConstantCodes.APIResponseHttpCode)+getmultiplecommentids.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, getmultiplecommentids.get(IAPIConstantCodes.APIResponseHttpCode)+getmultiplecommentids.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

	    //Deleting Multiple Comments of a Video	    
	   /* uploadvidoeapiresponse.put(IAPIConstantCodes.APIResponseCOMMENTID, getmultiplecommentids.get("commentId"));
	    uploadvidoeapiresponse.put(IAPIConstantCodes.APIResponseCOMMENTID2, getmultiplecommentids.get("commentId2"));
	    uploadvidoeapiresponse.put(IAPIConstantCodes.APIResponseCOMMENTID3, getmultiplecommentids.get("commentId3"));*/
	    logger.info("Delete video comment Code is executing%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
	    DeleteVideoCommentsAPI deleteVideoCommentsAPI=new DeleteVideoCommentsAPI();
		HashMap<String, String> deletevideoapiresponse = deleteVideoCommentsAPI.deleteVideocomments(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deletevideoapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deletevideoapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

	    //Selenium Code to ConfirmCommentDeletion	    
	    logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	    avengerdashboardpage.searchMedia(getvideocommit.get(IVideoAccessControlService.title));
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(getvideocommit.get(IVideoAccessControlService.title));
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(getvideocommit.get(IVideoAccessControlService.title)),statusValue, driver, sTestcaseName);
		commentspage.click_settingsLink();
		commentspage.click_commentsLink();
		customReport.customizedReport("This comment has been removed",commentspage.ConfirmCommentDeletion(),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	    
	}
	
	@Test(description="DeleteVideo Comment API - Verify API to Delete �All comments� by Account Admin",groups = {DELETEVIDEOCOMMENTSAPI})
	public void TC_08_Delete_VideoCommentsAPI_api_check_with_OneValid_OneInvalid_CommentIds_AccountAdmins_Positive(ITestContext context) {
 
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

		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		HashMap<String, String> submitcommit2 = videoCommentsAPI.putsubmitComments(uploadvidoeapiresponse, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(submitcommit2.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit2.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, submitcommit2.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit2.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

		apibeanfactory.SumbitCommentBean(addvideocommentbean);
		HashMap<String, String> submitcommit3 = videoCommentsAPI.putsubmitComments(uploadvidoeapiresponse, addvideocommentbean);
		assertionapiresponse.verifyAssert_httpCode(submitcommit3.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit3.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, submitcommit3.get(IAPIConstantCodes.APIResponseHttpCode)+submitcommit3.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	 
	    HashMap<String, String> getvideocommit = videoCommentsAPI.getVideoComments(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode)+getvideocommit.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, getvideocommit.get(IAPIConstantCodes.APIResponseHttpCode)+getvideocommit.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

	    HashMap<String, String> getmultiplecommentids = videoCommentsAPI.getVideomultipleCommentids(uploadvidoeapiresponse);
	    EditVideoComments videocomments = new EditVideoComments();
	    loginapiresponse.put("videoId", uploadvidoeapiresponse.get("videoId"));
	    assertionapiresponse.verifyAssert_httpCode(getmultiplecommentids.get(IAPIConstantCodes.APIResponseHttpCode)+getmultiplecommentids.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, getmultiplecommentids.get(IAPIConstantCodes.APIResponseHttpCode)+getmultiplecommentids.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

	    //Deleting One Valid & One Invalid Comments of a Video
	    uploadvidoeapiresponse.put(IAPIConstantCodes.APIResponseCOMMENTID, getmultiplecommentids.get("commentId"));
	    uploadvidoeapiresponse.put(IAPIConstantCodes.APIResponseCOMMENTID2, getmultiplecommentids.get("commentId2"));
	    uploadvidoeapiresponse.put("InvalidCommentID", "e78b18b7-aa19-44ae-b32c-d262ba5987af");
	    logger.info("Delete video comment Code is executing%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
	    DeleteVideoCommentsAPI deleteVideoCommentsAPI=new DeleteVideoCommentsAPI();
		HashMap<String, String> deletevideoapiresponse = deleteVideoCommentsAPI.deleteVideocommentswithoneVALIDandoneINVALIDIDs(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
	    customReport.customizedReport(HttpStatusCode.httpsStatus401, deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	}
	
	/*@Test(description="DeleteVideo Comment API - Deleting comments when comments are disabled for the account",groups = {DELETEVIDEOCOMMENTSAPI})
	public void TC_09_DeleteVideoCommentsAPI_WhenCommentsDisabledforAccount_check_with_AccountAdmins_Positive(ITestContext context) {
		
		// enabling Comments on account, (Media Settings > Features > Enable Comment = Uncheck)
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		homePage.clickSettingsLink();
		AvengerMediaSettingsPage avengermediasettingpage=homePage.clickMediaSettingsLink();
		AvengerFeaturesPage avengerfeaturespage=avengermediasettingpage.click_FeaturesLinkLocator();
		avengerfeaturespage.enableCategories();
		avengerfeaturespage.enableComments();
		avengerfeaturespage.enableRatings();
		avengerfeaturespage.clickSaveChanges();
		
		// Code for uploading Video and Posting Comments
		
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
			
		
		//disabling Comments on account, (Media Settings > Features > Enable Comment = Uncheck)
				
		avengerfeaturespage.disableComments();
		avengerfeaturespage.clickSaveChanges();
	
		VideoCommentsAPI videoCommentsAPI=new VideoCommentsAPI(); 
		logger.info("Delete video comment Code is executing%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
	 	DeleteVideoCommentsAPI deleteVideoCommentsAPI=new DeleteVideoCommentsAPI();
	 	HashMap<String, String> deletevideoapiresponse = deleteVideoCommentsAPI.deleteVideocomments(uploadvidoeapiresponse);
	 	logger.info(deletevideoapiresponse);	
	 	assertionapiresponse.verifyAssert_httpCode(deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus400);
	 	customReport.customizedReport(HttpStatusCode.httpsStatus400, deletevideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	 	customReport.checkinglist(statusValue);

		//Enabling Comments on account, (Media Settings > Features > Enable Comment = check)
		
		avengerfeaturespage.enableComments();
		avengerfeaturespage.clickSaveChanges();
		
	}*/
	
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
	