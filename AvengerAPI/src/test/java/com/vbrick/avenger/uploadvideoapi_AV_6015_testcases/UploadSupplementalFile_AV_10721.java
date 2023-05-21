package com.vbrick.avenger.uploadvideoapi_AV_6015_testcases;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.io.FilenameUtils;
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
import com.vbricks.avenger.service.IUserAccountsService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.service.IVideoAccessControlService;
import com.vbricks.avenger.serviceimpl.UploadSupplementalServiceAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.serviceimpl.VideoAccessControlServiceAPI;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class UploadSupplementalFile_AV_10721 extends TestBase {

	private static Logger logger = Logger.getLogger(UploadSupplementalFile_AV_10721.class);
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
	private HashMap<String, String> uploadsupplementalapiresponse;
	private HashMap<String, String> uploadvidoeapiresponse;
	private HashMap<String, String> loginapiresponse_eventadmin;
	private HashMap<String, String> loginapiresponse_mediaviewer;
	private HashMap<String, String> loginapiresponse_mediaviewer1;
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

	@Test(description = " To verify Supplemental Content   as text file to spicific video using Supplemental Content API with accountadmin",groups = {UPLOADSUPPL_FILEAPI})
	public void TC_01_UploadSupplementalFiles_AV10721_Uploading_SupplementalContent_AccountAdmin() throws InterruptedException {
	 
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
        UploadSupplementalServiceAPI uploadsupplementalservice=new UploadSupplementalServiceAPI();
        
		HashMap<String,String> supplementalFiles=uploadsupplementalservice.createJsonforSupplementalFiles(FilenameUtils.getBaseName(TEXTDOCUMENTPATH)+".txt"); 
 		uploadsupplementalapiresponse = uploadsupplementalservice.uploadSupplementalFile(uploadvidoeapiresponse, Setup.TEXTDOCUMENTPATH,supplementalFiles );
		logger.info("UploadVideo API response Code :::" + uploadsupplementalapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadsupplementalapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
	 	
		logger.info("Selenium Code is excuting");
	 	launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		AvengerEditVideoSettingsPage avengereditvideosettingspage=commentspage.click_settingsLink();
		avengereditvideosettingspage.clickSupplementMediaTab();
		customReport.customizedReport(FilenameUtils.getBaseName(TEXTDOCUMENTPATH), avengereditvideosettingspage.getAlluploadedSupplementalFiles().get(0), statusValue, driver, sTestcaseName);
 		customReport.checkinglist(statusValue);
	}
	
	@Test(description = " To verify Supplemental Content as ppt to spicific video using Supplemental Content API with mediaadmin",groups = {UPLOADSUPPL_FILEAPI})
	public void TC_02_UploadSupplementalFiles_AV10721_Uploading_SupplementalContent_Mediaadmin() throws InterruptedException {
	 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UploadSupplementalFilemediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
        UploadVideoService uploadvidoeservice = new UploadVideoService();
	    apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.UploadSupplementalFilemediaadmin);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setIsActive("true");																																				
		adduploadvideobean.setEnableRatings("true");
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
     	UploadSupplementalServiceAPI uploadsupplementalservice=new UploadSupplementalServiceAPI();
		HashMap<String,String> supplementalFiles=uploadsupplementalservice.createJsonforSupplementalFiles(FilenameUtils.getBaseName(EVENTSECONDPPTPATH)+".ppt"); 
		uploadsupplementalapiresponse = uploadsupplementalservice.uploadSupplementalFile(uploadvidoeapiresponse, Setup.EVENTSECONDPPTPATH,supplementalFiles );
		logger.info("UploadVideo API response Code :::" + uploadsupplementalapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));		
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadsupplementalapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		AvengerEditVideoSettingsPage avengereditvideosettingspage=commentspage.click_settingsLink();
		avengereditvideosettingspage.clickSupplementMediaTab();
		customReport.customizedReport(FilenameUtils.getBaseName(EVENTSECONDPPTPATH), avengereditvideosettingspage.getAlluploadedSupplementalFiles().get(0), statusValue, driver, sTestcaseName);
 		customReport.checkinglist(statusValue);
	}
	
	@Test(description = " To verify Supplemental Content as pdf file to spicific video using Supplemental Content API with mediacontributor",groups = {UPLOADSUPPL_FILEAPI})
	public void TC_03_UploadSupplementalFiles_AV10721_Uploading_SupplementalContent_MediaContributor() throws InterruptedException {
	 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin((apiutils.userJson(IUsersList.UploadSupplementalFilemediacontributor)), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
        UploadVideoService uploadvidoeservice = new UploadVideoService();
	    apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.UploadSupplementalFilemediacontributor);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setIsActive("true");																																				
		adduploadvideobean.setEnableRatings("true");
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
   		UploadSupplementalServiceAPI uploadsupplementalservice=new UploadSupplementalServiceAPI();
		HashMap<String,String> supplementalFiles=uploadsupplementalservice.createJsonforSupplementalFiles(FilenameUtils.getBaseName(PDFDOCUMENTPATH)+".pdf"); 
 		uploadsupplementalapiresponse = uploadsupplementalservice.uploadSupplementalFile(uploadvidoeapiresponse, Setup.PDFDOCUMENTPATH,supplementalFiles );
		logger.info("UploadVideo API response Code :::" + uploadsupplementalapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadsupplementalapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		AvengerEditVideoSettingsPage avengereditvideosettingspage=commentspage.click_settingsLink();
		avengereditvideosettingspage.clickSupplementMediaTab();
		customReport.customizedReport(FilenameUtils.getBaseName(PDFDOCUMENTPATH), avengereditvideosettingspage.getAlluploadedSupplementalFiles().get(0), statusValue, driver, sTestcaseName);
 		customReport.checkinglist(statusValue);
	}
	
	@Test(description = " To verify Supplemental Content text file to spicific video using Supplemental Content API with eventadmin",groups = {UPLOADSUPPL_FILEAPI})
	public void TC_04_UploadSupplementalFiles_AV10721_Uploading_SupplementalContent_EventAdmin() {
	 
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
		
		UserServices userservices_eventadmin = new UserServices();
		loginapiresponse_eventadmin = userservices_eventadmin.doLogin((apiutils.userJson(IUsersList.UploadSupplementalFileeventadmin)), surl);
		loginapiresponse_eventadmin.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_eventadmin.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
		
		UploadSupplementalServiceAPI uploadsupplementalservice=new UploadSupplementalServiceAPI();
		HashMap<String,String> supplementalFiles=uploadsupplementalservice.createJsonforSupplementalFiles(FilenameUtils.getBaseName(EVENTSECONDPPTPATH)+".ppt"); 
     	uploadsupplementalapiresponse = uploadsupplementalservice.uploadSupplementalFile(loginapiresponse_eventadmin, Setup.TEXTDOCUMENTPATH,supplementalFiles );
		logger.info("UploadVideo API response Code :::" + uploadsupplementalapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
    	customReport.customizedReport(HttpStatusCode.httpsStatusCode401, uploadsupplementalapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);

	}
	
	@Test(description = " To verify Supplemental Content to spicific video using Supplemental Content API with mediaviewer",groups = {UPLOADSUPPL_FILEAPI})
	public void TC_05_UploadSupplementalFiles_AV10721_Uploading_SupplementalContent_Mediaviewer(  ) {
		
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
		loginapiresponse_mediaviewer = userservices_mediaviewer.doLogin(apiutils.userJson(IUsersList.UploadSupplementalFilemediaviewer), surl);
		loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
		
		UploadSupplementalServiceAPI uploadsupplementalservice=new UploadSupplementalServiceAPI();
		HashMap<String,String> supplementalFiles=uploadsupplementalservice.createJsonforSupplementalFiles(FilenameUtils.getBaseName(EVENTSECONDPPTPATH)+".ppt"); 
		uploadsupplementalapiresponse = uploadsupplementalservice.uploadSupplementalFile(loginapiresponse_mediaviewer, Setup.TEXTDOCUMENTPATH,supplementalFiles );
		logger.info("UploadVideo API response Code :::" + uploadsupplementalapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode401, uploadsupplementalapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = " To verify Supplemental Content as zip file to spicific video using Supplemental Content API with eventadmin AfterProvidingEditVideoPermission",groups = {UPLOADSUPPL_FILEAPI})
	public void TC_06_UploadSupplementalFiles_AV10721_Uploading_SupplementalContent_EventAdmin_AfterProvidingEditVideoPermission( ) throws InterruptedException {
	 
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
	   
		HashMap<String,String> loginapiresponse_eventadmin = userservices.doLogin((apiutils.userJson(IUsersList.UploadSupplementalFileeventadmin)), surl);
		loginapiresponse_eventadmin.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		
		loginapiresponse_eventadmin.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
		VideoAccessControlServiceAPI videoaccesscontrolservice=new VideoAccessControlServiceAPI();
        accesscontrolbeagPage.setUsercanEdit("true");
        accesscontrolbeagPage.setUserid(loginapiresponse_eventadmin.get(IUserAccountsService.USERID));
        accesscontrolbeagPage.setUsertype("User");
        JSONObject json=videoaccesscontrolservice.createJsonVideoAccessControl(accesscontrolbeagPage, IVideoAccessControlService.jsonFlagUser);
        videoaccesscontrolservice.videoAccessControl(uploadvidoeapiresponse,json);
     	Thread.sleep(60000);
     	
     	HashMap<String,String>   loginapiresponse_eventadmin1 = userservices.doLogin(apiutils.userJson(IUsersList.UploadSupplementalFileeventadmin), surl);
     	loginapiresponse_eventadmin1.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
     	loginapiresponse_eventadmin1.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
		
     	UploadSupplementalServiceAPI uploadsupplementalservice=new UploadSupplementalServiceAPI();
		HashMap<String,String> supplementalFiles=uploadsupplementalservice.createJsonforSupplementalFiles(FilenameUtils.getBaseName(ZIPDOCUMENTPATH)+".zip"); 
     	uploadsupplementalapiresponse = uploadsupplementalservice.uploadSupplementalFile(loginapiresponse_eventadmin1, Setup.ZIPDOCUMENTPATH,supplementalFiles );
		logger.info("SupplementalFile API response Code :::" + uploadsupplementalapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadsupplementalapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		AvengerEditVideoSettingsPage avengereditvideosettingspage=commentspage.click_settingsLink();
		avengereditvideosettingspage.clickSupplementMediaTab();
		customReport.customizedReport(FilenameUtils.getBaseName(ZIPDOCUMENTPATH), avengereditvideosettingspage.getAlluploadedSupplementalFiles().get(0), statusValue, driver, sTestcaseName);
 		customReport.checkinglist(statusValue);
 		 	   		
	}
	
	@Test(description = " To verify Supplemental Content docx file to spicific video using Supplemental Content API with mediaviewer AfterProvidingEditVideoPermission",groups = {UPLOADSUPPL_FILEAPI})
	public void TC_07_UploadSupplementalFiles_AV10721_Uploading_SupplementalContent_Mediaviewer_AfterProvidingEditVideoPermission( ) throws InterruptedException {
		
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
		
		loginapiresponse_mediaviewer = userservices.doLogin(apiutils.userJson(IUsersList.UploadSupplementalFilemediaviewer), surl);
		loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
		
		VideoAccessControlServiceAPI videoaccesscontrolservice=new VideoAccessControlServiceAPI();
		accesscontrolbeagPage.setUsercanEdit("true");
	    accesscontrolbeagPage.setUserid(loginapiresponse_mediaviewer.get(IUserAccountsService.USERID));
	    accesscontrolbeagPage.setUsertype("User");
	    JSONObject json=videoaccesscontrolservice.createJsonVideoAccessControl(accesscontrolbeagPage, IVideoAccessControlService.jsonFlagUser);
	    videoaccesscontrolservice.videoAccessControl(uploadvidoeapiresponse,json);
	    Thread.sleep(60000);
		loginapiresponse_mediaviewer1 = userservices.doLogin(apiutils.userJson(IUsersList.UploadSupplementalFilemediaviewer), surl);
		loginapiresponse_mediaviewer1.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_mediaviewer1.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
		
		UploadSupplementalServiceAPI uploadsupplementalservice=new UploadSupplementalServiceAPI();
		HashMap<String,String> supplementalFiles=uploadsupplementalservice.createJsonforSupplementalFiles(FilenameUtils.getBaseName(WORDDOCUMENTPATH)+".docx"); 
		uploadsupplementalapiresponse = uploadsupplementalservice.uploadSupplementalFile(loginapiresponse_mediaviewer1, Setup.WORDDOCUMENTPATH,supplementalFiles );
		logger.info("UploadVideo API response Code :::" + uploadsupplementalapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadsupplementalapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		AvengerEditVideoSettingsPage avengereditvideosettingspage=commentspage.click_settingsLink();
		avengereditvideosettingspage.clickSupplementMediaTab();
		customReport.customizedReport(FilenameUtils.getBaseName(WORDDOCUMENTPATH), avengereditvideosettingspage.getAlluploadedSupplementalFiles().get(0), statusValue, driver, sTestcaseName);
 		customReport.checkinglist(statusValue);
	}
	
	
	@Test(description = " To verify Supplemental Content to spicific video using Supplemental Content API with accountadmin",groups = {UPLOADSUPPL_FILEAPI})
	public void TC_08_UploadSupplementalFiles_AV10721_Uploading_SupplementalContent_AccountAdmin_InValidFileFormat() {
	 
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
		
		uploadvidoeapiresponse.put("accesstoken",IUploadVideoService.INVALIDACCESSTOKEN);
		UploadSupplementalServiceAPI uploadsupplementalservice=new UploadSupplementalServiceAPI();
		HashMap<String,String> supplementalFiles=uploadsupplementalservice.createJsonforSupplementalFiles(FilenameUtils.getBaseName(PDFDOCUMENTPATH)+".pdf"); 
 		uploadsupplementalapiresponse = uploadsupplementalservice.uploadSupplementalFile(uploadvidoeapiresponse, Setup.PDFDOCUMENTPATH,supplementalFiles );
		logger.info("UploadVideo API response Code :::" + uploadsupplementalapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode401, uploadsupplementalapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
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
