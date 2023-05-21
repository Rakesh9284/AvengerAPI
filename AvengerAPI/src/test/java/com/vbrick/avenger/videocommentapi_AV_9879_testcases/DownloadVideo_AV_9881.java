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
 
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AvengerGroupsBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
 
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerEditRootAccountPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerUserDashboardPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IDownloadVideoService;
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.CreateGroupAPI;
import com.vbricks.avenger.serviceimpl.DeleteVideosAPI;
import com.vbricks.avenger.serviceimpl.DownloadVideoAPI;
import com.vbricks.avenger.serviceimpl.EditVideoRatingAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
 
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.ConstantsValue;
import com.vbricks.avenger.utils.HttpStatusCode;

public class DownloadVideo_AV_9881 extends TestBase {

	private static Logger logger = Logger.getLogger(DownloadVideo_AV_9881.class);
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
	private HashMap<String, String> loginapiresponse1;
	private HashMap<String, String> uploadvidoeapiresponse;
	private HashMap<String, String> editvidoeapiresponse;
	private HashMap<String,String> loginapiresponse_mediaviewer;
	private HashMap<String, String> creategroupapirespone;
	private HashMap<String,String> loginapiresponse_eventadmin;
	private HashMap<String,String> loginapiresponse_eventhost;
	private HashMap<String,String> loginapiresponse_mediacomtributor;
	private AvengerGroupsBeanPage avengergroupsbeanpage;
	private AddGroupBean addgroupbean;
	private AvengerHomePage avengerHomePage;
	private AvengerUserDashboardPage avengeruserdashboardpage;
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
		avengergroupsbeanpage = new AvengerGroupsBeanPage();
		apibeanfactory = new ApiBeanFactory();
		addgroupbean=new AddGroupBean();
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
		accountBeansFactory.AvengergroupsBean(avengergroupsbeanpage);

	}

	@Test(description = "DownloadVideo API - Download Video With Account Admin with Valid VideoId",groups = {DOWNLOADVIDEOAPI})
	public void TC_01_GET_DownloadVideo_api_check_with_AccoutAdmin_VideoIdValid_Positive(   ) throws InterruptedException {

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
		adduploadvideobean.setEnableDownloads(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		Thread.sleep(5000);
		
		DownloadVideoAPI downloadVideoAPI = new DownloadVideoAPI();
	    HashMap<String, String> deleteapirespone = downloadVideoAPI.getVideoDownload(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);
	}  
	
	@Test(description = "DownloadVideo API - Download Video With Account Admin Without EnableDownload",groups = {DOWNLOADVIDEOAPI})
	public void TC_02_GET_DownloadVideo_api_check_with_AccoutAdmin_Without_EnableDownload_NEGATIVE(   ) throws InterruptedException {

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
		adduploadvideobean.setEnableDownloads(IAPIConstantCodes.FALSE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
        Thread.sleep(5000);
				
		DownloadVideoAPI downloadVideoAPI = new DownloadVideoAPI();
	    HashMap<String, String> deleteapirespone = downloadVideoAPI.getVideoDownload(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200, deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);
	}  
	
	@Test(description="DownloadVideo API - Download Video With Media Admin with private video",groups = {DOWNLOADVIDEOAPI})
	public void TC_03_GET_DownloadVideo_api_check_with_MediaAdmin_PrivateVideo_Positive( ) throws InterruptedException {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.DownloadVideomediaadmin), surl);
		Assert.assertNotNull(loginapiresponse);
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
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	    Thread.sleep(1000);
				
	    DownloadVideoAPI downloadVideoAPI = new DownloadVideoAPI();
	    HashMap<String, String> deleteapirespone = downloadVideoAPI.getVideoDownload(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);
	}
	
	@Test(description="DownloadVideo API - Download Video With Media Contributor with AllUsers video",groups = {DOWNLOADVIDEOAPI})
	public void TC_04_GET_DownloadVideo_api_check_with_MediaContributor_AllusersVideo_Positive( ) throws InterruptedException {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.DownloadVideomediacontributor), surl);
		Assert.assertNotNull(loginapiresponse);
		logger.info("Login API response Code :::" + loginapiresponse);
 		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableDownloads(IAPIConstantCodes.TRUE);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[2]);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	    Thread.sleep(5000);
	   
	    DownloadVideoAPI downloadVideoAPI = new DownloadVideoAPI();
	    HashMap<String, String> deleteapirespone = downloadVideoAPI.getVideoDownload(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);
	}
	
	@Test(description = "DownloadVideo API - Download Video With Event Host with Private Video by giving access",groups = {DOWNLOADVIDEOAPI})
	public void TC_05_GET_DownloadVideo_api_check_with_EventHost_PrivateVideo_Positive() throws InterruptedException {

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
		adduploadvideobean.setEnableDownloads(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
		String accesscontrolforuser = IUsersList.DownloadVideoeventhost;		
		adduploadvideobean.setVideoAccessControl("Private1");
		
		//get userId
		String userIdOfUser = get_userIdFromUI(accesscontrolforuser);
		adduploadvideobean.setUserId(userIdOfUser);
		adduploadvideobean.setAccesscontrolforuser(accesscontrolforuser);		
		adduploadvideobean.setAccesscontrolforuser(IUsersList.DownloadVideoeventhost);
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
					
		loginapiresponse_eventhost = userservices.doLogin(apiutils.userJson(IUsersList.DownloadVideoeventhost), surl);
		loginapiresponse_eventhost.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));		
		Thread.sleep(6000);
		
		DownloadVideoAPI downloadVideoAPI = new DownloadVideoAPI();
	    HashMap<String, String> deleteapirespone = downloadVideoAPI.getVideoDownload(loginapiresponse_eventhost);	    
		assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);
	  
	}  
	

	@Test(description="DownloadVideo API - Download Video With Event Admin with Public video",groups = {DOWNLOADVIDEOAPI})
	public void TC_06_GET_DownloadVideo_api_check_with_EventAdmin_PublicVideo_Positive( ) {
		 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		Assert.assertNotNull(loginapiresponse);
		logger.info("Login API response Code :::" + loginapiresponse);
 		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);
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
		 
		loginapiresponse_eventadmin = userservices.doLogin(apiutils.userJson(IUsersList.DownloadVideoeventadmin), surl);
		loginapiresponse_eventadmin.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));		

	    DownloadVideoAPI downloadVideoAPI = new DownloadVideoAPI();
	    HashMap<String, String> deleteapirespone = downloadVideoAPI.getVideoDownload(loginapiresponse_eventadmin);
		assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);
	}
	
	
	@Test(description="DownloadVideo API - Download Video With Media Viewer Private video and MV assigned to a group having Media Admin access",groups = {DOWNLOADVIDEOAPI})
	public void TC_07_GET_DownloadVideo_api_check_with_MediaViewer_Privatevideo_Positive( ) throws InterruptedException {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		//deleting the assigned groups if any for the Media Viewer user
		delete_assignedGroupsForUser(IUsersList.DownloadVideomediaviewer);

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		Assert.assertNotNull(loginapiresponse);
		logger.info("Login API response Code :::" + loginapiresponse);
 		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableDownloads(IAPIConstantCodes.TRUE);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);				
		HashMap<String,String>loginapiresponse_mediviewer = userservices.doLogin(apiutils.userJson(IUsersList.DownloadVideomediaviewer), surl);
		
		//adding the Media Viewer user to a group	
		//getting the userIds
		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		String[] userIds = {loginapiresponse_mediviewer.get("userId")};
		addgroupbean.setUserids(userIds);
										
		//getting role ids
		UserServices userServices=new UserServices();
		HashMap<String, String> userroleapirespone = userServices.getRolesApi(loginapiresponse,IAPIConstantCodes.MEDIAADMIN);
		String roleid=apiutils.formatingapiresponse(userroleapirespone.get(IAPIConstantCodes.ROLEID));
		String[] roleIds = {userroleapirespone.get("roleId")};
		addgroupbean.setRoleids(roleIds);
												
		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
		creategroupapirespone = creategroupAPI.createGroup(loginapiresponse,creategroupjson);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);				
		loginapiresponse_mediviewer.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));				
		Thread.sleep(10000);
						
	    DownloadVideoAPI downloadVideoAPI = new DownloadVideoAPI();
	    HashMap<String, String> deleteapirespone = downloadVideoAPI.getVideoDownload(loginapiresponse_mediviewer);
	    assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    
	    //deleting the assigned group
	    delete_assignedGroupsForUser(IUsersList.DownloadVideomediaviewer);	    
	    customReport.checkinglist(statusValue);
	}
	
	@Test(description="DownloadVideo API - Download Video With AccountAdmin INValidVideoId NEGATATIVE",groups = {DOWNLOADVIDEOAPI})
	public void TC_08_GET_DownloadVideo_api_check_with_AccountAdmin_INValidVideoId_NEGATATIVE(   ) {
		 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(ConstantsValue.accountAdmin, surl);
		Assert.assertNotNull(loginapiresponse);
		logger.info("Login API response Code :::" + loginapiresponse);
 		
		loginapiresponse.put(IAPIConstantCodes.APIResponseVIDEOID, IDownloadVideoService.invalidVideoId);		
 
	    DownloadVideoAPI downloadVideoAPI = new DownloadVideoAPI();
	    HashMap<String, String> deleteapirespone = downloadVideoAPI.getVideoDownload(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
	    customReport.customizedReport(HttpStatusCode.httpsStatus401, deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);
	}

	
	@Test(description = "DownloadVideo API - Download Private Video With Account Admin Without AccessToken",groups = {DOWNLOADVIDEOAPI})
    public void TC_09_GET_Download_PrivateVideo_api_check_with_AccoutAdmin_Without_GivingAccessToken_NEGATIVE(   ) {

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
           adduploadvideobean.setVideoAccessControl("Private");
   	       loginapiresponse.put("Mandatory", "No");
   	       loginapiresponse.put("fileName", "No");
           
           uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
           logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
           assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
           customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
 
           uploadvidoeapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, null);
           
           DownloadVideoAPI downloadVideoAPI = new DownloadVideoAPI();
           HashMap<String, String> deleteapirespone = downloadVideoAPI.getVideoDownload(uploadvidoeapiresponse);
           assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
           customReport.customizedReport(HttpStatusCode.httpsStatus401, deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
           customReport.checkinglist(statusValue);
	}  

		
	@Test(description="DownloadVideo API - Download Video With Media Viewer with Private Video",groups = {DOWNLOADVIDEOAPI})
	public void TC_10_GET_DownloadVideo_api_check_with_MediaViewer_PrivateVideo_Negative(ITestContext context) {
		 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		Assert.assertNotNull(loginapiresponse);
		logger.info("Login API response Code :::" + loginapiresponse);
 		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableDownloads(IAPIConstantCodes.TRUE);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	 
		HashMap<String,String> loginapiresponse_mediviewer = userservices.doLogin(apiutils.userJson(IUsersList.DownloadVideomediaviewer1), surl);
		loginapiresponse_mediviewer.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));		
				
	    DownloadVideoAPI downloadVideoAPI = new DownloadVideoAPI();
	    HashMap<String, String> deleteapirespone = downloadVideoAPI.getVideoDownload(loginapiresponse_mediviewer);
	    assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
	    customReport.customizedReport(HttpStatusCode.httpsStatus401, deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);
	}
	
	@Test(description = "DownloadVideo API - Download Video With Account Admin with ALlUsers Video",groups = {DOWNLOADVIDEOAPI})
	public void TC_11_GET_DownloadVideo_api_check_with_AccoutAdmin_AllUsersVideo_Positive(   ) throws InterruptedException {

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
		adduploadvideobean.setEnableDownloads(IAPIConstantCodes.TRUE);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[2]);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		Thread.sleep(5000);
		
		DownloadVideoAPI downloadVideoAPI = new DownloadVideoAPI();
	    HashMap<String, String> deleteapirespone = downloadVideoAPI.getVideoDownload(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);
	}  
	
	@Test(description = "DownloadVideo API - Download Video With Event Host with AllUsers Video",groups = {DOWNLOADVIDEOAPI})
	public void TC_12_GET_DownloadVideo_api_check_with_EventHost_AllUsersVideo_Positive(   ) throws InterruptedException {

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
		adduploadvideobean.setEnableDownloads(IAPIConstantCodes.TRUE);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[2]);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		Thread.sleep(5000);
		
		loginapiresponse_eventhost = userservices.doLogin(apiutils.userJson(IUsersList.DownloadVideoeventhost), surl);
		loginapiresponse_eventhost.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));		
	    DownloadVideoAPI downloadVideoAPI = new DownloadVideoAPI();
	    HashMap<String, String> deleteapirespone = downloadVideoAPI.getVideoDownload(loginapiresponse_eventhost);	    
		assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);
	  
	}  
	
	@Test(description="DownloadVideo API - Download Video With Media Contributor with Valid VideoId",groups = {DOWNLOADVIDEOAPI})
	public void TC_13_GET_DownloadVideo_api_check_with_MediaContributor_VideoIdValid_Negative( ) {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.DownloadVideomediacontributor), surl);
		Assert.assertNotNull(loginapiresponse);
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
	 				
	    DownloadVideoAPI downloadVideoAPI = new DownloadVideoAPI();
	    HashMap<String, String> deleteapirespone = downloadVideoAPI.getVideoDownload(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatus401);
	    customReport.customizedReport(HttpStatusCode.httpsStatus401, deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);
	}

	@Test(description="DownloadVideo API - Download Video With Media Viewer with Public video",groups = {DOWNLOADVIDEOAPI})
	public void TC_14_GET_DownloadVideo_api_check_with_MediaViewer_PublicVideo_Positive(ITestContext context) throws InterruptedException {
		 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		Assert.assertNotNull(loginapiresponse);
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
	 
		HashMap<String,String>loginapiresponse_mediviewer = userservices.doLogin(apiutils.userJson(IUsersList.DownloadVideomediaviewer), surl);
		loginapiresponse_mediviewer.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));		
		Thread.sleep(5000);
	    DownloadVideoAPI downloadVideoAPI = new DownloadVideoAPI();
	    HashMap<String, String> deleteapirespone = downloadVideoAPI.getVideoDownload(loginapiresponse_mediviewer);
		assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deleteapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+deleteapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);
	}
	

	@AfterMethod(alwaysRun=true)
	public void browserClose(ITestResult result)
	{
		logger.info("In After method class");
		 statusValue.clear();
		 if(!result.isSuccess()){
		Reporter.log("Screen shot for failed Test Case" +customReport.AssertionresultOutput(driver, sTestcaseName));
		  }
		browserQuit();
	}
	
	public String get_userIdFromUI(String userName) {

		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		homePage.clickSettingsLink();
		AvengerUserDashboardPage avengerdashboardpage = homePage.clickUsersLink();
		AvengerEditRootAccountPage avengerUserEditPage = avengerdashboardpage.clicknewuser_link(userName);
		String url=homePage.getCurrentURL(); 
	    logger.info("url.split -"+url.split("/")[8]); 
	    homePage.click_logout();
	    return url.split("/")[8];		
	
	}
	
public void delete_assignedGroupsForUser(String username)  {
		
		launchURL(surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengeruserdashboardpage=avengerHomePage.clickUsersLink();
		avengeruserdashboardpage.clicknewuser_link(username);
		avengeruserdashboardpage.get_AllAssignedgroupsandDelete();
		avengerHomePage.click_logout();
	}

}
