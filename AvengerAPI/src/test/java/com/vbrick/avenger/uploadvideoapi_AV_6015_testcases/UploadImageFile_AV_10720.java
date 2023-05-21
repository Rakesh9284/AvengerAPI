package com.vbrick.avenger.uploadvideoapi_AV_6015_testcases;

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
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
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
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.service.IVideoAccessControlService;
import com.vbricks.avenger.serviceimpl.UploadVideoImageAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.serviceimpl.VideoAccessControlServiceAPI;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class UploadImageFile_AV_10720 extends TestBase {

	private static Logger logger = Logger.getLogger(UploadImageFile_AV_10720.class);
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
	private AssertionAPIResponse assertionapiresponse;
	private HashMap<String, String> loginapiresponse;
	private HashMap<String, String> uploadvidoeapiresponse;
	private HashMap<String, String> uploadImageapiresponse;
	private HashMap<String, String> loginapiresponse_eventadmin;
	private HashMap<String, String> loginapiresponse_mediaviewer;
	private VideoAccessControlBean accesscontrolbeagPage;
    private ApiUtils apiutils;
	@BeforeClass(alwaysRun = true)
	public void beforeClass(ITestContext context) throws Exception {
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
		assertionapiresponse = new AssertionAPIResponse();
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

	@Test(description = " To verify Image Content to spicific video using Image Content API with accountadmin",groups = {UPLOADIMAGEAPI})
	public void TC_01_UploadImageFiles_AV10720_Uploading_ImageContent_AccountAdmin(ITestContext context) throws InterruptedException {
	 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
 
        UploadVideoService uploadvidoeservice = new UploadVideoService();	
        apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setIsActive("true");																																				
		adduploadvideobean.setEnableRatings("true");
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		  
		UploadVideoImageAPI uploadVideoImageAPI=new UploadVideoImageAPI();
		uploadImageapiresponse = uploadVideoImageAPI.uploadVideoImageFile(uploadvidoeapiresponse, Setup.VALIDIMAGEPATH_PNG,IUploadVideoService.UPLOADIMAGE );
		logger.info("UploadVideo API response Code :::" + uploadImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadImageapiresponse);
				
		logger.info("Selenium Code is excuting");		 
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		Thread.sleep(60000);
        commentspage.click_settingsLink();
        commentspage.click_details();
        commentspage.clickAdvanced_Settings();
		customReport.customizedReport(true, commentspage.getThumbnailImageData().contains(".png"),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = " To verify Image Content to spicific video using Image Content API with Mediaadmin",groups = {UPLOADIMAGEAPI})
	public void TC_02_UploadImageFiles_AV10720_Uploading_ImageContent_MediaAdmin(ITestContext context) throws InterruptedException {
	 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UploadImageFilemediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
 
        UploadVideoService uploadvidoeservice = new UploadVideoService();		
        apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.UploadImageFilemediaadmin);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setIsActive("true");																																				
		adduploadvideobean.setEnableRatings("true");
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
        
		UploadVideoImageAPI uploadVideoImageAPI=new UploadVideoImageAPI();
		uploadImageapiresponse = uploadVideoImageAPI.uploadVideoImageFile(uploadvidoeapiresponse, Setup.VALIDIMAGEPATH_PNG,IUploadVideoService.UPLOADIMAGE );
		logger.info("UploadVideo API response Code :::" + uploadImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadImageapiresponse);
		
		logger.info("Selenium Code is excuting");		 
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		Thread.sleep(60000);
        customReport.customizedReport("1", commentspage.thumbnailforVideo(),statusValue, driver, sTestcaseName);
        commentspage.click_settingsLink();
        commentspage.click_details();
        commentspage.clickAdvanced_Settings();
		customReport.customizedReport(true, commentspage.getThumbnailImageData().contains(".png"),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = " To verify Image Content to spicific video using Image Content API with MediaContributor",groups = {UPLOADIMAGEAPI})
	public void TC_03_UploadImageFiles_AV10720_Uploading_ImageContent_MediaContributor(ITestContext context) throws InterruptedException {
	 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UploadImageFilemediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
 
        UploadVideoService uploadvidoeservice = new UploadVideoService();	
        apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.UploadImageFilemediacontributor);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setIsActive("true");																																				
		adduploadvideobean.setEnableRatings("true");
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
        
		UploadVideoImageAPI uploadVideoImageAPI=new UploadVideoImageAPI();
		uploadImageapiresponse = uploadVideoImageAPI.uploadVideoImageFile(uploadvidoeapiresponse, Setup.VALIDIMAGEPATH_PNG,IUploadVideoService.UPLOADIMAGE );
		logger.info("UploadVideo API response Code :::" + uploadImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadImageapiresponse);
		
		logger.info("Selenium Code is excuting");	 
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		Thread.sleep(60000);
        commentspage.click_settingsLink();
        commentspage.click_details();
        commentspage.clickAdvanced_Settings();
		customReport.customizedReport(true, commentspage.getThumbnailImageData().contains(".png"),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = " To verify Image Content to spicific video using Image Content API with EventAdmin",groups = {UPLOADIMAGEAPI})
	public void TC_04_UploadImageFiles_AV10720_Uploading_ImageContent_EventAdmin(ITestContext context) {
	 
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
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setIsActive("true");
		adduploadvideobean.setEnableRatings("true");
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		loginapiresponse_eventadmin = userservices.doLogin((apiutils.userJson(IUsersList.UploadImageFileeventadmin)), surl);
		loginapiresponse_eventadmin.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_eventadmin.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
		
		UploadVideoImageAPI uploadVideoImageAPI=new UploadVideoImageAPI();
		uploadImageapiresponse = uploadVideoImageAPI.uploadVideoImageFile(loginapiresponse_eventadmin, Setup.VALIDIMAGEPATH_PNG,IUploadVideoService.UPLOADIMAGE );
		assertionapiresponse.verifyAssert_httpCode(uploadImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode401);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode401, uploadImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadImageapiresponse);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = " To verify Image Content to spicific video using Image Content API with MediaViewer",groups = {UPLOADIMAGEAPI})
	public void TC_05_UploadImageFiles_AV10720_Uploading_ImageContent_MediaViewer(ITestContext context) {
		
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
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setIsActive("true");
		adduploadvideobean.setEnableRatings("true");
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		 
		loginapiresponse_mediaviewer = userservices.doLogin(apiutils.userJson(IUsersList.UploadImageFilemediaviewer), surl);
		loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
		
		UploadVideoImageAPI uploadVideoImageAPI=new UploadVideoImageAPI();
		uploadImageapiresponse = uploadVideoImageAPI.uploadVideoImageFile(loginapiresponse_mediaviewer, Setup.VALIDIMAGEPATH_PNG,IUploadVideoService.UPLOADIMAGE );
		logger.info("UploadVideo API response Code :::" + uploadImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode401);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode401, uploadImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadImageapiresponse);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = " To verify Image Content to spicific video using Image Content API with EventAdmin",groups = {UPLOADIMAGEAPI})
	public void TC_06_UploadImageFiles_AV10720_Uploading_ImageContent_EventAdmin_ByGivingPermisssion(ITestContext context) throws InterruptedException {
	 
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
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setIsActive("true");
		adduploadvideobean.setEnableRatings("true");
	    adduploadvideobean.setVideoAccessControl("Private");
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		HashMap<String,String> loginapiresponse_eventadmin = userservices.doLogin((apiutils.userJson(IUsersList.UploadImageFileeventadmin)), surl);
		loginapiresponse_eventadmin.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_eventadmin.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
		
		VideoAccessControlServiceAPI videoaccesscontrolservice=new VideoAccessControlServiceAPI();
        accesscontrolbeagPage.setUsercanEdit("true");
        accesscontrolbeagPage.setUserid(loginapiresponse_eventadmin.get("userId"));
        accesscontrolbeagPage.setUsertype("User");
        JSONObject json=videoaccesscontrolservice.createJsonVideoAccessControl(accesscontrolbeagPage, IVideoAccessControlService.jsonFlagUser);
        videoaccesscontrolservice.videoAccessControl(uploadvidoeapiresponse,json);
        
		Thread.sleep(60000);
		HashMap<String,String> loginapiresponse_eventadmin1 = userservices.doLogin((apiutils.userJson(IUsersList.UploadImageFileeventadmin)), surl);
		loginapiresponse_eventadmin1.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_eventadmin1.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
        
		UploadVideoImageAPI uploadVideoImageAPI=new UploadVideoImageAPI();
  		uploadImageapiresponse = uploadVideoImageAPI.uploadVideoImageFile(loginapiresponse_eventadmin1, Setup.VALIDIMAGEPATH_PNG,IUploadVideoService.UPLOADIMAGE );
  		assertionapiresponse.verifyAssert_httpCode(uploadImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
  		logger.info("UploadVideo API response Code :::" + uploadImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));		
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
				
		logger.info("Selenium Code is excuting");		 
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		Thread.sleep(60000);
		commentspage.click_settingsLink();
	    commentspage.click_details();
	    commentspage.clickAdvanced_Settings();
		customReport.customizedReport(true, commentspage.getThumbnailImageData().contains(".png"),statusValue, driver, sTestcaseName);
		System.out.println("Data inside the image content"+commentspage.getThumbnailImageData().contains(".png"));
		customReport.checkinglist(statusValue);		
        accesscontrolbeagPage.setUsercanEdit("false");
        accesscontrolbeagPage.setUserid(loginapiresponse_eventadmin.get("userId"));
        accesscontrolbeagPage.setUsertype("User");
        JSONObject json1=videoaccesscontrolservice.createJsonVideoAccessControl(accesscontrolbeagPage, IVideoAccessControlService.jsonFlagUser);
        videoaccesscontrolservice.videoAccessControl(uploadvidoeapiresponse,json1);        
 		
	}
	
	@Test(description = " To verify Image Content to spicific video using Image Content API with MediaViewer",groups = {UPLOADIMAGEAPI})
	public void TC_07_UploadImageFiles_AV10720_Uploading_ImageContent_MediaViewer_ByGivingPermission(ITestContext context) throws InterruptedException {
		
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
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setIsActive("true");
		adduploadvideobean.setEnableRatings("true");
	    adduploadvideobean.setVideoAccessControl("Private");
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		loginapiresponse_mediaviewer = userservices.doLogin(apiutils.userJson(IUsersList.UploadImageFilemediaviewer), surl);
		loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
		
		VideoAccessControlServiceAPI videoaccesscontrolservice=new VideoAccessControlServiceAPI();
        accesscontrolbeagPage.setUsercanEdit("true");
        accesscontrolbeagPage.setUserid(loginapiresponse_mediaviewer.get("userId"));
        accesscontrolbeagPage.setUsertype("User");
        JSONObject json=videoaccesscontrolservice.createJsonVideoAccessControl(accesscontrolbeagPage, IVideoAccessControlService.jsonFlagUser);
        videoaccesscontrolservice.videoAccessControl(uploadvidoeapiresponse,json);
        
		Thread.sleep(60000);
		HashMap<String,String>loginapiresponse_mediaviewer1 = userservices.doLogin(apiutils.userJson(IUsersList.UploadImageFilemediaviewer), surl);
		loginapiresponse_mediaviewer1.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_mediaviewer1.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
		
        UploadVideoImageAPI uploadVideoImageAPI=new UploadVideoImageAPI();
  		uploadImageapiresponse = uploadVideoImageAPI.uploadVideoImageFile(loginapiresponse_mediaviewer1, Setup.VALIDIMAGEPATH_PNG,IUploadVideoService.UPLOADIMAGE );
  		assertionapiresponse.verifyAssert_httpCode(uploadImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
  		logger.info("UploadVideo API response Code :::" + uploadImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));		
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Selenium Code is excuting");
		 
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		Thread.sleep(60000);
		commentspage.click_settingsLink();
	    commentspage.click_details();
	    commentspage.clickAdvanced_Settings();
		customReport.customizedReport(true, commentspage.getThumbnailImageData().contains(".png"),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);	
		accesscontrolbeagPage.setUsercanEdit("false");
	    accesscontrolbeagPage.setUserid(loginapiresponse_mediaviewer.get("userId"));
	    accesscontrolbeagPage.setUsertype("User");
	    JSONObject json1=videoaccesscontrolservice.createJsonVideoAccessControl(accesscontrolbeagPage, IVideoAccessControlService.jsonFlagUser);
	    videoaccesscontrolservice.videoAccessControl(uploadvidoeapiresponse,json1);
	}

	@Test(description = " To verify Supplemental Content to spicific video using Supplemental Content API with accountadmin",groups = {UPLOADIMAGEAPI})
	public void TC_08_UploadSupplementalFiles_AV10721_Uploading_SupplementalContent_AccountAdmin_InValidFileFormat( ) {
	 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
 
        UploadVideoService uploadvidoeservice = new UploadVideoService();	
        apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setIsActive("true");																																				
		adduploadvideobean.setEnableRatings("true");
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	
		HashMap<String,String>loginapiresponse_eventadmin = userservices.doLogin(apiutils.userJson(IUsersList.UploadImageFilemediaviewer), surl);
		loginapiresponse_eventadmin.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_eventadmin.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
		
		UploadVideoImageAPI uploadVideoImageAPI=new UploadVideoImageAPI();
  		uploadImageapiresponse = uploadVideoImageAPI.uploadVideoImageFile(loginapiresponse_eventadmin, Setup.VALIDIMAGEPATH_PNG,IUploadVideoService.UPLOADIMAGE );
  		assertionapiresponse.verifyAssert_httpCode(uploadImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode401);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode401, uploadImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);	 
		customReport.checkinglist(statusValue);
 
	}
	
	@Test(description = " To verify Supplemental Content to spicific video using Supplemental Content API with accountadmin",groups = {UPLOADIMAGEAPI})
	public void TC_09_UploadSupplementalFiles_AV10721_Uploading_SupplementalContent_AccountAdmin_InValidFileFormat( ) {
	 
	logger.info("API Level Code is excuting");
	sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
	customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
	UserServices userservices = new UserServices();
	loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
	assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
	customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
	logger.info("Login API response Code :::" + loginapiresponse);

    UploadVideoService uploadvidoeservice = new UploadVideoService();	
    apibeanfactory.UploadVideoBean(adduploadvideobean);
	adduploadvideobean.setUploader(sUserName);
	adduploadvideobean.setEnableComments("true");
	adduploadvideobean.setIsActive("true");																																				
	adduploadvideobean.setEnableRatings("true");
	loginapiresponse.put("Mandatory", "No");
	loginapiresponse.put("fileName", "No");
	uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
	logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
	assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
	customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
    
	UploadVideoImageAPI uploadVideoImageAPI=new UploadVideoImageAPI();	 
	uploadImageapiresponse = uploadVideoImageAPI.uploadVideoImageFile(uploadvidoeapiresponse, Setup.INVALIDIMAGEPATH_LOGO,IUploadVideoService.UPLOADIMAGE );
	logger.info("UploadVideo API response Code :::" + uploadImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
	assertionapiresponse.verifyAssert_httpCode(uploadImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode415);
	customReport.customizedReport(HttpStatusCode.httpsStatusCode415, uploadImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
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

