package com.vbrick.avenger.uploadvideoapi_AV_6015_testcases;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
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
import com.vbricks.avenger.serviceimpl.UploadTranscriptionServiceAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.serviceimpl.VideoAccessControlServiceAPI;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class UploadTranscriptionFile_AV_10646 extends TestBase {

	private static Logger logger = Logger.getLogger(UploadTranscriptionFile_AV_10646.class);
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
	private HashMap<String, String> uploadtranscriptionlapiresponse;
	private HashMap<String, String> uploadtranscriptionlapiresponse1;
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

	@Test(description = " To verify Transcription Content to spicific video using Transcription Content API with accountadmin",groups = {UPLOADTRANS_FILEAPI})
	public void TC_01_UploadTranscriptionFiles_AV10646_Uploading_TranscriptionContent_AccountAdmin() throws InterruptedException {
	 
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
    	
		UploadTranscriptionServiceAPI uploadtranscriptionlservice=new UploadTranscriptionServiceAPI();
		HashMap<String,String> transcriptionFiles=uploadtranscriptionlservice.createJsonforTranscriptionFiles(FilenameUtils.getBaseName(SRTFILEPATH3)+".srt","en"); 
		uploadtranscriptionlapiresponse = uploadtranscriptionlservice.uploadTranscriptionFile(uploadvidoeapiresponse, Setup.SRTFILEPATH3,transcriptionFiles );
		logger.info("UploadVideo API response Code :::" + uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		AvengerEditVideoSettingsPage avengereditvideosettingspage=commentspage.click_settingsLink();
		avengereditvideosettingspage.clickSupplementMediaTab();
		String langlist=commentspage.get_allsubtitlesLanguage(IUploadVideoService.ENGLISH);
	 	customReport.customizedReport(bundle.getString("editvideosettingpg.captionlanguageenglish"),langlist  , statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = " To verify Transcription Content to spicific video using Transcription Content API with mediaadmin",groups = {UPLOADTRANS_FILEAPI})
	public void TC_02_UploadTranscriptionFiles_AV10646_Uploading_TranscriptionContent_MediaAdmin(ITestContext context) throws InterruptedException {
	 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UploadTranscriptionFilemediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
       
		UploadVideoService uploadvidoeservice = new UploadVideoService();
	    apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.UploadTranscriptionFilemediaadmin);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setIsActive("true");																																				
		adduploadvideobean.setEnableRatings("true");
	  
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
     	
		UploadTranscriptionServiceAPI uploadtranscriptionlservice=new UploadTranscriptionServiceAPI();
		HashMap<String,String> transcriptionFiles=uploadtranscriptionlservice.createJsonforTranscriptionFiles(FilenameUtils.getBaseName(SRTFILEPATH3)+".srt","en"); 
		uploadtranscriptionlapiresponse = uploadtranscriptionlservice.uploadTranscriptionFile(uploadvidoeapiresponse, Setup.SRTFILEPATH3,transcriptionFiles );
		logger.info("UploadVideo API response Code :::" + uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadtranscriptionlapiresponse);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		AvengerEditVideoSettingsPage avengereditvideosettingspage=commentspage.click_settingsLink();
		avengereditvideosettingspage.clickSupplementMediaTab();
		String langlist=commentspage.get_allsubtitlesLanguage(IUploadVideoService.ENGLISH);
		customReport.customizedReport(bundle.getString("editvideosettingpg.captionlanguageenglish"),langlist  , statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description = " To verify Transcription Content to spicific video using Transcription Content API with MediaContributor",groups = {UPLOADTRANS_FILEAPI})
	public void TC_03_UploadTranscriptionFiles_AV10646_Uploading_TranscriptionContent_MediaContributor(ITestContext context) throws InterruptedException {
	 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UploadTranscriptionFilemediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
       
		UploadVideoService uploadvidoeservice = new UploadVideoService();
	    apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.UploadTranscriptionFilemediacontributor);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setIsActive("true");																																				
		adduploadvideobean.setEnableRatings("true");
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
     	
		UploadTranscriptionServiceAPI uploadtranscriptionlservice=new UploadTranscriptionServiceAPI();
		HashMap<String,String> transcriptionFiles=uploadtranscriptionlservice.createJsonforTranscriptionFiles(FilenameUtils.getBaseName(SRTFILEPATH3)+".srt","en"); 
		uploadtranscriptionlapiresponse = uploadtranscriptionlservice.uploadTranscriptionFile(uploadvidoeapiresponse, Setup.SRTFILEPATH3,transcriptionFiles );
		logger.info("UploadVideo API response Code :::" + uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		AvengerEditVideoSettingsPage avengereditvideosettingspage=commentspage.click_settingsLink();
		avengereditvideosettingspage.clickSupplementMediaTab();
		String langlist=commentspage.get_allsubtitlesLanguage(IUploadVideoService.ENGLISH);
		customReport.customizedReport(bundle.getString("editvideosettingpg.captionlanguageenglish"),langlist  , statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = " To verify Transcription Content to spicific video using Transcription Content API with EventAdmin",groups = {UPLOADTRANS_FILEAPI})
	public void TC_04_UploadTranscriptionFiles_AV10646_Uploading_TranscriptionContent_Eventadmin(ITestContext context) {
	 
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
		loginapiresponse_eventadmin = userservices.doLogin((apiutils.userJson(IUsersList.UploadTranscriptionFileeventadmin)), surl);
		loginapiresponse_eventadmin.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_eventadmin.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
		
		UploadTranscriptionServiceAPI uploadtranscriptionlservice=new UploadTranscriptionServiceAPI();
		HashMap<String,String> transcriptionFiles=uploadtranscriptionlservice.createJsonforTranscriptionFiles(FilenameUtils.getBaseName(SRTFILEPATH3)+".srt","en"); 
		uploadtranscriptionlapiresponse = uploadtranscriptionlservice.uploadTranscriptionFile(loginapiresponse_eventadmin, Setup.SRTFILEPATH3,transcriptionFiles );
		logger.info("UploadVideo API response Code :::" + uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));		
		customReport.customizedReport(HttpStatusCode.httpsStatusCode401, uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	 	
	}
	
	@Test(description = " To verify Transcription Content to spicific video using Transcription Content API with MediaViewer",groups = {UPLOADTRANS_FILEAPI})
	public void TC_05_UploadTranscriptionFiles_AV10646_Uploading_TranscriptionContent_MediaViewer(ITestContext context) {
	 
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
		
		UserServices userservices_mediaviewer = new UserServices();
		loginapiresponse_mediaviewer = userservices_mediaviewer.doLogin((apiutils.userJson(IUsersList.UploadTranscriptionFilemediaviewer)), surl);
		loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
		
		UploadTranscriptionServiceAPI uploadtranscriptionlservice=new UploadTranscriptionServiceAPI();
		HashMap<String,String> transcriptionFiles=uploadtranscriptionlservice.createJsonforTranscriptionFiles(FilenameUtils.getBaseName(SRTFILEPATH3)+".srt","en"); 
		uploadtranscriptionlapiresponse = uploadtranscriptionlservice.uploadTranscriptionFile(loginapiresponse_mediaviewer, Setup.SRTFILEPATH3,transcriptionFiles );
		logger.info("UploadVideo API response Code :::" + uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));		
		customReport.customizedReport(HttpStatusCode.httpsStatusCode401, uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = " To verify Transcription Content to spicific video using Transcription Content API with EventAdmin",groups = {UPLOADTRANS_FILEAPI})
	public void TC_06_UploadTranscriptionFiles_AV10646_Uploading_TranscriptionContent_Eventadmin_ByGivingEditPermission(ITestContext context) throws InterruptedException {
	 
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
		loginapiresponse_eventadmin = userservices.doLogin((apiutils.userJson(IUsersList.UploadTranscriptionFileeventadmin)), surl);
		loginapiresponse_eventadmin.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_eventadmin.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
		
		VideoAccessControlServiceAPI videoaccesscontrolservice=new VideoAccessControlServiceAPI();
        accesscontrolbeagPage.setUsercanEdit("true");
        accesscontrolbeagPage.setUserid(loginapiresponse_eventadmin.get("userId"));
        accesscontrolbeagPage.setUsertype("User");
        JSONObject json=videoaccesscontrolservice.createJsonVideoAccessControl(accesscontrolbeagPage, IVideoAccessControlService.jsonFlagUser);
        videoaccesscontrolservice.videoAccessControl(uploadvidoeapiresponse,json);
        Thread.sleep(60000);
       
        HashMap<String,String> loginapiresponse_eventadmin1 = userservices.doLogin((apiutils.userJson(IUsersList.UploadTranscriptionFileeventadmin)), surl);
		loginapiresponse_eventadmin1.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_eventadmin1.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
        UploadTranscriptionServiceAPI uploadtranscriptionlservice=new UploadTranscriptionServiceAPI();
       
        HashMap<String,String> transcriptionFiles=uploadtranscriptionlservice.createJsonforTranscriptionFiles(FilenameUtils.getBaseName(SRTFILEPATH3)+".srt","en"); 
  		uploadtranscriptionlapiresponse = uploadtranscriptionlservice.uploadTranscriptionFile(loginapiresponse_eventadmin1, Setup.SRTFILEPATH3,transcriptionFiles );
  		logger.info("UploadVideo API response Code :::" + uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
  		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		AvengerEditVideoSettingsPage avengereditvideosettingspage=commentspage.click_settingsLink();
		avengereditvideosettingspage.clickSupplementMediaTab();
		String langlist=commentspage.get_allsubtitlesLanguage(IUploadVideoService.ENGLISH);
		customReport.customizedReport(bundle.getString("editvideosettingpg.captionlanguageenglish"),langlist  , statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	
	}
	
	@Test(description = " To verify Transcription Content to spicific video using Transcription Content API with Mediaviewer",groups = {UPLOADTRANS_FILEAPI})
	public void TC_07_UploadTranscriptionFiles_AV10646_Uploading_TranscriptionContent_Mediaviewer_ByGivingEditPermission(ITestContext context) throws InterruptedException {
		
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
		
		UserServices userservices_mediaviewer = new UserServices();
		loginapiresponse_mediaviewer = userservices_mediaviewer.doLogin(apiutils.userJson(IUsersList.UploadTranscriptionFilemediaviewer), surl);
		loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
		
		VideoAccessControlServiceAPI videoaccesscontrolservice=new VideoAccessControlServiceAPI();
        accesscontrolbeagPage.setUsercanEdit("true");
        accesscontrolbeagPage.setUserid(loginapiresponse_mediaviewer.get("userId"));
        accesscontrolbeagPage.setUsertype("User");
        JSONObject json=videoaccesscontrolservice.createJsonVideoAccessControl(accesscontrolbeagPage, IVideoAccessControlService.jsonFlagUser);
        videoaccesscontrolservice.videoAccessControl(uploadvidoeapiresponse,json);
        Thread.sleep(60000);
        
        HashMap<String,String>loginapiresponse_mediaviewer1 = userservices_mediaviewer.doLogin(apiutils.userJson(IUsersList.UploadTranscriptionFilemediaviewer), surl);
        loginapiresponse_mediaviewer1.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
        loginapiresponse_mediaviewer1.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
        UploadTranscriptionServiceAPI uploadtranscriptionlservice=new UploadTranscriptionServiceAPI();
       
        HashMap<String,String> transcriptionFiles=uploadtranscriptionlservice.createJsonforTranscriptionFiles(FilenameUtils.getBaseName(SRTFILEPATH3)+".srt","en"); 
  		uploadtranscriptionlapiresponse = uploadtranscriptionlservice.uploadTranscriptionFile(loginapiresponse_mediaviewer1, Setup.SRTFILEPATH3,transcriptionFiles );
  		logger.info("UploadVideo API response Code :::" + uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
  		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		AvengerEditVideoSettingsPage avengereditvideosettingspage=commentspage.click_settingsLink();
		avengereditvideosettingspage.clickSupplementMediaTab();
		String langlist=commentspage.get_allsubtitlesLanguage(IUploadVideoService.ENGLISH);
		customReport.customizedReport(bundle.getString("editvideosettingpg.captionlanguageenglish"),langlist  , statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
 		
	}

	@Test(description = " To verify Transcription Content to spicific video using Transcription Content API with accountadmin",groups = {UPLOADTRANS_FILEAPI})
	public void TC_08_UploadTranscriptionFiles_AV10646_Uploading_TranscriptionContent_AccountAdmin_ByOverRidingAnExistingFile(ITestContext context) throws InterruptedException {
	 
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
		
		UploadTranscriptionServiceAPI uploadtranscriptionlservice=new UploadTranscriptionServiceAPI();
		HashMap<String,String> transcriptionFiles=uploadtranscriptionlservice.createJsonforTranscriptionFiles(FilenameUtils.getBaseName(SRTFILEPATH3)+".srt","en"); 
		uploadtranscriptionlapiresponse = uploadtranscriptionlservice.uploadTranscriptionFile(uploadvidoeapiresponse, Setup.SRTFILEPATH3,transcriptionFiles );
  		/*logger.info("UploadVideo API response Code :::" + uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));	
  		assertionapiresponse.verifyAssert_httpCode(uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);*/
		
		HashMap<String,String> transcriptionFiles1=uploadtranscriptionlservice.createJsonforTranscriptionFiles(FilenameUtils.getBaseName(SRTFILEPATH2)+".srt","en");
  		uploadtranscriptionlapiresponse1 = uploadtranscriptionlservice.uploadTranscriptionFile(uploadvidoeapiresponse, Setup.SRTFILEPATH2,transcriptionFiles1 );
  		logger.info("Uploadtranscriptionl API response Code :::" + uploadtranscriptionlapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode));
  		assertionapiresponse.verifyAssert_httpCode(uploadtranscriptionlapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsstatuscode500);
		customReport.customizedReport(HttpStatusCode.httpsstatuscode500, uploadtranscriptionlapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
 		
 		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		AvengerEditVideoSettingsPage avengereditvideosettingspage=commentspage.click_settingsLink();
		avengereditvideosettingspage.clickSupplementMediaTab();
		String langlist=commentspage.get_allsubtitlesLanguage(IUploadVideoService.ENGLISH);
		customReport.customizedReport(bundle.getString("editvideosettingpg.captionlanguageenglish"),langlist  , statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
 			 
	}	
	
	@Test(description = " To verify Transcription Content to spicific video using Transcription Content API with accountadmin",groups = {UPLOADTRANS_FILEAPI})
	public void TC_09_UploadTranscriptionFiles_AV10646_Uploading_TranscriptionContent_AccountAdmin_WithoutGivingLanguage(ITestContext context) {
	 
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
		
 		UploadTranscriptionServiceAPI uploadtranscriptionlservice=new UploadTranscriptionServiceAPI();
		HashMap<String,String> transcriptionFiles=uploadtranscriptionlservice.createJsonforTranscriptionFiles(FilenameUtils.getBaseName(SRTFILEPATH3)+".srt",""); 
		uploadtranscriptionlapiresponse = uploadtranscriptionlservice.uploadTranscriptionFile(uploadvidoeapiresponse, Setup.SRTFILEPATH2,transcriptionFiles );
  		logger.info("UploadVideo API response Code :::" + uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
  		assertionapiresponse.verifyAssert_httpCode(uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode400);		
		customReport.customizedReport(HttpStatusCode.httpsStatusCode400, uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
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
