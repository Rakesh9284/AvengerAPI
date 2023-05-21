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
import com.vbrick.avenger.apibeans.VideoAccessControlBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AddUserBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.LibraryBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerEditVideoSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLibrariesPage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbrick.avenger.videocommentapi_AV_9879_testcases.SubmitVideosComments_AV_9879;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IDeleteVideoService;
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.service.IVideoAccessControlService;
import com.vbricks.avenger.serviceimpl.CreateGroupAPI;
import com.vbricks.avenger.serviceimpl.CreateTeamAPI;
import com.vbricks.avenger.serviceimpl.DeleteVideosAPI;
import com.vbricks.avenger.serviceimpl.EditGroupAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.serviceimpl.VideoAccessControlServiceAPI;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.ConstantsValue;
import com.vbricks.avenger.utils.HttpStatusCode;

public class DeleteVideoApi_AV_9129 extends TestBase {

	private static Logger logger = Logger.getLogger(DeleteVideoApi_AV_9129.class);
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
	private AvengerLibrariesPage avengerlibrarypage;
	private LibraryBeanPage librarybeanpage;
	private Map<String, String> userdata;
    private ApiUtils apiutils;
	private VideoAccessControlBean accesscontrolbeagPage;
	private BasePage basePage;
	private AddUploadVideoBean adduploadvideobean;
	private AddTeamBean addteambean;
	private AddGroupBean addgroupbean;
	public ApiBeanFactory apibeanfactory;
	public AddVideoCommentBean addvideocommentbean;
 	private HashMap<String, String> loginapiresponse;
 	private HashMap<String, String> loginapiresponse1;
	private HashMap<String, String> uploadvidoeapiresponse;
	private HashMap<String,String> loginapiresponse_mediaviewer;
	private HashMap<String,String> loginapiresponse_eventadmin;
	private HashMap<String,String> editGroupapiresponse;
	private HashMap<String, String> createteamapirespone;
	private HashMap<String,String> loginapiresponse_mediacontributor;
	private HashMap<String, String> creategroupapirespone;
    private AssertionAPIResponse assertionapiresponse;
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
		accesscontrolbeagPage = new VideoAccessControlBean();
		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		addteambean=new AddTeamBean();
		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		addvideocommentbean = new AddVideoCommentBean();
		librarybeanpage = new LibraryBeanPage();
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
		accountBeansFactory.LibraryBeanByPage(librarybeanpage);

	}

	@Test(description = "Delete required Video using DeleteVideoAPi along With Account Admin",groups = {DELETEVIDEOAPI})
	public void TC_01_DELETE_DeleteVideo_api_check_DeleteVideo_with_AccoutAdmin_Positive() {

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
		adduploadvideobean.setEnableComments(IDeleteVideoService.TRUE);
		adduploadvideobean.setIsActive(IDeleteVideoService.TRUE);
		adduploadvideobean.setEnableRatings(IDeleteVideoService.TRUE);
		adduploadvideobean.setVideoAccessControl("Private");
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		DeleteVideosAPI deleteVideosAPI=new DeleteVideosAPI();
	    HashMap<String, String> deleteapirespone = deleteVideosAPI.deleteVideos(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		customReport.customizedReport("0" ,String.valueOf(avengerdashboardpage.verifyVideoDeleted(adduploadvideobean.getTitle())), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "Delete required Video using DeleteVideoAPi along With MediaAdmin",groups = {DELETEVIDEOAPI})
	public void TC_02_DELETE_DeleteVideo_api_check_DeleteVideo_with_MediaAdmin_Positive() {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.DeleteVideomediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.DeleteVideomediaadmin);
		adduploadvideobean.setEnableComments(IDeleteVideoService.TRUE);
		adduploadvideobean.setIsActive(IDeleteVideoService.TRUE);
		adduploadvideobean.setEnableRatings(IDeleteVideoService.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));		
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode ,uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	
		DeleteVideosAPI deleteVideosAPI=new DeleteVideosAPI();
	    HashMap<String, String> deleteapirespone = deleteVideosAPI.deleteVideos(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		customReport.customizedReport("0" ,String.valueOf(avengerdashboardpage.verifyVideoDeleted(adduploadvideobean.getTitle())), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "Required Video should not delete using Delete VideoAPi ,when EventHost doen't have delete access permission",groups = {DELETEVIDEOAPI})
	public void TC_03_DELETE_DeleteVideo_api_check_DeleteVideo_with_EventHost_Negative() {

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
		adduploadvideobean.setEnableComments(IDeleteVideoService.TRUE);
		adduploadvideobean.setIsActive(IDeleteVideoService.TRUE);
		adduploadvideobean.setEnableRatings(IDeleteVideoService.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UserServices userservices_eventhost = new UserServices();
		loginapiresponse_eventadmin = userservices_eventhost.doLogin(IUsersList.DeleteVideoeventhost, surl);
		loginapiresponse_eventadmin.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_eventadmin.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
		
		DeleteVideosAPI deleteVideosAPI=new DeleteVideosAPI(); 
	    HashMap<String, String> deleteapirespone = deleteVideosAPI.deleteVideos(loginapiresponse_eventadmin);
	    assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
	    customReport.customizedReport(HttpStatusCode.httpsStatus401, deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "Required Video should not delete using Delete VideoAPi ,when MediaContributor doen't have delete access permission",groups = {DELETEVIDEOAPI})
	public void TC_04_DELETE_DeleteVideo_api_check_DeleteVideo_with_MediaContributor_Negative() {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.DeleteVideomediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		 
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.DeleteVideomediacontributor);
		adduploadvideobean.setEnableComments(IDeleteVideoService.TRUE);
		adduploadvideobean.setIsActive(IDeleteVideoService.TRUE);
		adduploadvideobean.setEnableRatings(IDeleteVideoService.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UserServices userservices_mediacontributor = new UserServices();
		loginapiresponse_mediacontributor = userservices_mediacontributor.doLogin(IUsersList.DeleteVideomediacontributor, surl);
		loginapiresponse_mediacontributor.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_mediacontributor.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
		
		DeleteVideosAPI deleteVideosAPI=new DeleteVideosAPI(); 
	    HashMap<String, String> deleteapirespone = deleteVideosAPI.deleteVideos(loginapiresponse_mediacontributor);
	    assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
	    customReport.customizedReport(HttpStatusCode.httpsStatus401, deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "Required Video should delete using Delete VideoAPi ,when MediaViewer is given access permission",groups = {DELETEVIDEOAPI})
	public void TC_05_DELETE_DeleteVideo_api_check_DeleteVideo_with_MediaViewer_Positive() throws InterruptedException {
			
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
		adduploadvideobean.setEnableComments(IDeleteVideoService.TRUE);
		adduploadvideobean.setIsActive(IDeleteVideoService.TRUE);
		adduploadvideobean.setEnableRatings(IDeleteVideoService.TRUE);
		adduploadvideobean.setVideoAccessControl(IDeleteVideoService.PRIVATE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
        adduploadvideobean.setAccesscontrolforuser(IUsersList.DeleteVideomediaviewer);
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UserServices userservices_mediaviewer = new UserServices();
		loginapiresponse_mediaviewer = userservices_mediaviewer.doLogin(apiutils.userJson(IUsersList.DeleteVideomediaviewer), surl);
		loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
		
		 VideoAccessControlServiceAPI videoaccesscontrolservice=new VideoAccessControlServiceAPI();
         accesscontrolbeagPage.setUsercanEdit(IDeleteVideoService.TRUE);
         accesscontrolbeagPage.setUserid(loginapiresponse_mediaviewer.get(IDeleteVideoService.USERID));
         accesscontrolbeagPage.setUsertype(IDeleteVideoService.USER);
         JSONObject json=videoaccesscontrolservice.createJsonVideoAccessControl(accesscontrolbeagPage, IVideoAccessControlService.jsonFlagUser);
         videoaccesscontrolservice.videoAccessControl(uploadvidoeapiresponse,json);

         Thread.sleep(6000);
         		
         DeleteVideosAPI deleteVideosAPI=new DeleteVideosAPI(); 
         HashMap<String, String> deleteapirespone = deleteVideosAPI.deleteVideos(loginapiresponse_mediaviewer);
         logger.info("Delete Video API response Code :::" + deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode));  
         assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
         customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
                      
         logger.info("Selenium Code is excuting");
         launchURL(surl);
         customReport.reporter("Application launch with URL : ", surl);
         homePage = loginPage.loginAs(sUserName, sPassword);
         AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
         avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
         customReport.customizedReport("0" ,String.valueOf(avengerdashboardpage.verifyVideoDeleted(adduploadvideobean.getTitle())), statusValue, driver, sTestcaseName);
         customReport.checkinglist(statusValue);
  
         accesscontrolbeagPage.setUsercanEdit(IDeleteVideoService.FALSE);
         accesscontrolbeagPage.setUserid(loginapiresponse_mediaviewer.get(IDeleteVideoService.USERID));
         accesscontrolbeagPage.setUsertype(IDeleteVideoService.USER);
         JSONObject json1=videoaccesscontrolservice.createJsonVideoAccessControl(accesscontrolbeagPage, IVideoAccessControlService.jsonFlagUser);
         videoaccesscontrolservice.videoAccessControl(uploadvidoeapiresponse,json1);
         
	}
		
	@Test(description = "Delete required Video using DeleteVideoAPi  along With InvalidVideoId",groups = {DELETEVIDEOAPI})
	public void TC_06_DELETE_DeleteVideoAPI_api_check_AccountAdmin_with_InvalidVideoId_Negative() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {	}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		loginapiresponse.put(IAPIConstantCodes.APIResponseVIDEOID, IDeleteVideoService.invalidVideoId);
		DeleteVideosAPI deleteVideosAPI=new DeleteVideosAPI(); 
	    HashMap<String, String> deleteapirespone = deleteVideosAPI.deleteVideos(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
	    customReport.customizedReport(HttpStatusCode.httpsStatus401, deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	    
	}
	@Test(description = "Delete required Video using DeleteVideoAPi along With EventAdmin After giving Access Permission",groups = {DELETEVIDEOAPI})
    public void TC_07_DELETE_DeleteVideoAPI_api_check_with_EventAdmin_ByGivingPermission_Positive() throws InterruptedException {

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
           adduploadvideobean.setEnableComments(IDeleteVideoService.TRUE);
           adduploadvideobean.setIsActive(IDeleteVideoService.TRUE);
           adduploadvideobean.setEnableRatings(IDeleteVideoService.TRUE);
           adduploadvideobean.setVideoAccessControl(IDeleteVideoService.PRIVATE);
           adduploadvideobean.setAccesscontrolforuser(IUsersList.DeleteVideoeventadmin);
           loginapiresponse.put("Mandatory", "No");
   		   loginapiresponse.put("fileName", "No");
           uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
           logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
           assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
           customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
           
           UserServices userservices_eventadmin = new UserServices();
           loginapiresponse_eventadmin = userservices_eventadmin.doLogin(apiutils.userJson(IUsersList.DeleteVideoeventadmin), surl);
           loginapiresponse_eventadmin.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
           loginapiresponse_eventadmin.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
           
           VideoAccessControlServiceAPI videoaccesscontrolservice=new VideoAccessControlServiceAPI();
           accesscontrolbeagPage.setUsercanEdit(IDeleteVideoService.TRUE);
           accesscontrolbeagPage.setUserid(loginapiresponse_eventadmin.get(IDeleteVideoService.USERID));
           accesscontrolbeagPage.setUsertype(IDeleteVideoService.USER);
           JSONObject json=videoaccesscontrolservice.createJsonVideoAccessControl(accesscontrolbeagPage, IVideoAccessControlService.jsonFlagUser);
           videoaccesscontrolservice.videoAccessControl(uploadvidoeapiresponse,json);

           Thread.sleep(6000);
           
           DeleteVideosAPI deleteVideosAPI=new DeleteVideosAPI(); 
           HashMap<String, String> deleteapirespone = deleteVideosAPI.deleteVideos(loginapiresponse_eventadmin);
           logger.info("Delete Video API response Code :::" + deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode));  
           assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
           customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
                        
           logger.info("Selenium Code is excuting");
           launchURL(surl);
           customReport.reporter("Application launch with URL : ", surl);
           homePage = loginPage.loginAs(sUserName, sPassword);
           AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
           avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
           customReport.customizedReport("0" ,String.valueOf(avengerdashboardpage.verifyVideoDeleted(adduploadvideobean.getTitle())), statusValue, driver, sTestcaseName);
           customReport.checkinglist(statusValue);
    
           accesscontrolbeagPage.setUsercanEdit(IDeleteVideoService.FALSE);
           accesscontrolbeagPage.setUserid(loginapiresponse_eventadmin.get(IDeleteVideoService.USERID));
           accesscontrolbeagPage.setUsertype(IDeleteVideoService.USER);
           JSONObject json1=videoaccesscontrolservice.createJsonVideoAccessControl(accesscontrolbeagPage, IVideoAccessControlService.jsonFlagUser);
           videoaccesscontrolservice.videoAccessControl(uploadvidoeapiresponse,json1);        
           
    }
    
    @Test(description = "Delete required Video using DeleteVideoAPi along With MediaControbutor After assigning to a group having Access Permission",groups = {DELETEVIDEOAPI})
    public void TC_08_DELETE_DeleteVideoAPI_api_check_MediaContributor_Byassigningtogrouphavingpermission_Positive() throws InterruptedException {

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
           adduploadvideobean.setEnableComments(IDeleteVideoService.TRUE);
           adduploadvideobean.setIsActive(IDeleteVideoService.TRUE);
           adduploadvideobean.setEnableRatings(IDeleteVideoService.TRUE);
           adduploadvideobean.setVideoAccessControl(IDeleteVideoService.PRIVATE);
   	       loginapiresponse.put("Mandatory", "No");
   		   loginapiresponse.put("fileName", "No");
                      
           uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
           logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
           assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
           customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
           
           //assigning mediacontributor1 to a group           
           loginapiresponse_mediacontributor = userservices.doLogin(apiutils.userJson(IUsersList.DeleteVideomediacontributor1), surl);
   		   assertionapiresponse.verifyAssert_httpCode( loginapiresponse_mediacontributor.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
   		   customReport.customizedReport(HttpStatusCode.httpsStatusCode200,  loginapiresponse_mediacontributor.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
   		   logger.info("Login API response Code :::" +  loginapiresponse_mediacontributor);

   			//getting the userIds
   			CreateGroupAPI creategroupAPI=new CreateGroupAPI();
   			apibeanfactory.CreateGroupBean(addgroupbean);
   			String[] userIds = { loginapiresponse_mediacontributor.get("userId")};
   			addgroupbean.setUserids(userIds);
   						
   			//getting roleids
   			UserServices userServices=new UserServices();
   			HashMap<String, String> userroleapirespone = userServices.getRolesApi(loginapiresponse,IAPIConstantCodes.MEDIAADMIN);
   			String roleid=apiutils.formatingapiresponse(userroleapirespone.get(IAPIConstantCodes.ROLEID));
   			String[] roleIds = {userroleapirespone.get("roleId")};
   			addgroupbean.setRoleids(roleIds);
   				
	   		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
	   		creategroupapirespone = creategroupAPI.createGroup(loginapiresponse,creategroupjson);
	   		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
	   		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	   		
            loginapiresponse_mediacontributor.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
            loginapiresponse_mediacontributor.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
           
            DeleteVideosAPI deleteVideosAPI=new DeleteVideosAPI();
   	        HashMap<String, String> deleteapirespone = deleteVideosAPI.deleteVideos(loginapiresponse_mediacontributor);
   	        assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
   	        customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
   	       
   	       //Removing the assigned user mediacontributor1 from MediaAdmin group
   	        addgroupbean.setName("APIEDITName"+apiutils.randomString(5));
   	        addgroupbean.setUserids(null);
   	        addgroupbean.setRoleids(null);
	   	    EditGroupAPI editGroupAPI = new EditGroupAPI();
			loginapiresponse.put("groupId", creategroupapirespone.get(IAPIConstantCodes.APIGROUPID));
			JSONObject creategroupjsonedit=creategroupAPI.createGroupJson(addgroupbean);
	
			editGroupapiresponse = editGroupAPI.editGroup(loginapiresponse, creategroupjsonedit);
			assertionapiresponse.verifyAssert_httpCode(
			editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + editGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),
			HttpStatusCode.httpsStatusCode200OK);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + editGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);
 
   	        logger.info("Selenium Code is excuting");
   	        launchURL(surl);
   	        customReport.reporter("Application launch with URL : ", surl);
   	        homePage = loginPage.loginAs(sUserName, sPassword);
   	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
   	        avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
   	        customReport.customizedReport("0" ,String.valueOf(avengerdashboardpage.verifyVideoDeleted(adduploadvideobean.getTitle())), statusValue, driver, sTestcaseName);
   	        customReport.checkinglist(statusValue);
    }
    
    @Test(description = "Delete required Video using DeleteVideoAPi along With Media Admin for All Users Video",groups = {DELETEVIDEOAPI})
    public void TC_09_DELETE_DeleteVideoAPI_api_check_MediaAdmin_AllUsersVideo_Positive() throws InterruptedException {

    	logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.DeleteVideomediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.DeleteVideomediaadmin);
		adduploadvideobean.setEnableComments(IDeleteVideoService.TRUE);
		adduploadvideobean.setIsActive(IDeleteVideoService.TRUE);
		adduploadvideobean.setEnableRatings(IDeleteVideoService.TRUE);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[2]);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode ,uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	
		DeleteVideosAPI deleteVideosAPI=new DeleteVideosAPI();
	    HashMap<String, String> deleteapirespone = deleteVideosAPI.deleteVideos(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		customReport.customizedReport("0" ,String.valueOf(avengerdashboardpage.verifyVideoDeleted(adduploadvideobean.getTitle())), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}

    @Test(description = "Delete required Public Video using DeleteVideoAPi along With Account Admin",groups = {DELETEVIDEOAPI})
	public void TC_10_DELETE_DeleteVideo_api_check_DeleteVideo_with_AccoutAdmin_Positive() {

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
		adduploadvideobean.setEnableComments(IDeleteVideoService.TRUE);
		adduploadvideobean.setIsActive(IDeleteVideoService.TRUE);
		adduploadvideobean.setEnableRatings(IDeleteVideoService.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		DeleteVideosAPI deleteVideosAPI=new DeleteVideosAPI();
	    HashMap<String, String> deleteapirespone = deleteVideosAPI.deleteVideos(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		customReport.customizedReport("0" ,String.valueOf(avengerdashboardpage.verifyVideoDeleted(adduploadvideobean.getTitle())), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
    
    @Test(description = "Delete required Video using DeleteVideoAPi along With Account Admin for Teams Video",groups = {DELETEVIDEOAPI})
    public void TC_11_DELETE_DeleteVideoAPI_api_check_AccountAdmin_TeamsVideo_Positive() throws InterruptedException {

    	logger.info("API Level Code is excuting");
    	sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		addteambean.setTeamMembers(true);
				
		String[] userIds = {loginapiresponse.get("userId")};
		addteambean.setUserids(userIds);
		addteambean.setTeammemberasuser(userIds[0]);
		addteambean.setTeammemberasgroup("");
		
		JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.DeleteVideoaccountadmin);
		adduploadvideobean.setEnableComments(IDeleteVideoService.TRUE);
		adduploadvideobean.setIsActive(IDeleteVideoService.TRUE);
		adduploadvideobean.setEnableRatings(IDeleteVideoService.TRUE);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[3]);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
		String accesscontrolforteam = addteambean.getName();
		adduploadvideobean.setAccesscontrolforteam(accesscontrolforteam+"a");		
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));		
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode ,uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	
		DeleteVideosAPI deleteVideosAPI=new DeleteVideosAPI();
	    HashMap<String, String> deleteapirespone = deleteVideosAPI.deleteVideos(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		customReport.customizedReport("0" ,String.valueOf(avengerdashboardpage.verifyVideoDeleted(adduploadvideobean.getTitle())), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}

    @Test(description = "Verify that deleting a legal video gives an error",groups = {DELETEVIDEOAPI})
   	public void TC_12_Delete_legalholv_video_with_accountadmin_Negative() {

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
   		adduploadvideobean.setEnableComments(IDeleteVideoService.TRUE);
   		adduploadvideobean.setIsActive(IDeleteVideoService.TRUE);
   		adduploadvideobean.setEnableRatings(IDeleteVideoService.TRUE);
   	    loginapiresponse.put("Mandatory", "No");
   		loginapiresponse.put("fileName", "No");
   		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
   		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
   		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
   		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
   		
   		logger.info("Selenium Code is excuting");
   		launchURL(surl);
   		customReport.reporter("Application launch with URL : ", surl);
   		homePage = loginPage.loginAs(sUserName, sPassword);
   		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
   		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.click_settingsLink();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickLegalholdButton();
   			
   		DeleteVideosAPI deleteVideosAPI=new DeleteVideosAPI();
   	    HashMap<String, String> deleteapirespone = deleteVideosAPI.deleteVideos(uploadvidoeapiresponse);
   	    assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
	    customReport.customizedReport(HttpStatusCode.httpsStatus401, deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);	    
	    commentspage.click_settingsLink();
		AvengerEditVideoSettingsPage avengereditvideosettingspage1 = commentspage.clickremoveLegalholdButton();		
   		customReport.checkinglist(statusValue);
   	}
		
    @AfterMethod(alwaysRun = true)
	public void browserClose(ITestResult result)
	{
		logger.info("In After method class");
		 statusValue.clear();
		 if(!result.isSuccess()){
		Reporter.log("Screen shot for failed Test Case" +customReport.AssertionresultOutput(driver, sTestcaseName));
		  }
		browserQuit();
	}

	public String create_teamfromUI() {

		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		homePage.clickSettingsLink();
		avengerlibrarypage = homePage.click_LibrariesLinkLocator();
		String teamname = avengerlibrarypage.createNewteam(librarybeanpage);
		librarybeanpage.setLibraryname(teamname);
		homePage.click_logout();
		return teamname;
	
	}
	
	public String get_teamidfromUI(String teamname) {

		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		homePage.clickSettingsLink();
		avengerlibrarypage = homePage.click_LibrariesLinkLocator();
		String teamid = avengerlibrarypage.getteamid(teamname);
		homePage.click_logout();
		return teamid;
	
	}
}
 