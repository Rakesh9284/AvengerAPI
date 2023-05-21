package com.vbrick.avenger.uploadvideoapi_AV_6015_testcases;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
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
import com.vbrick.avenger.apibeans.AddUserApiBean;
import com.vbrick.avenger.apibeans.VideoAccessControlBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AddUserBeanPage;
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
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.serviceimpl.VideoAccessControlServiceAPI;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class VideoAccessControlService_AV_10176 extends TestBase {

	private static Logger logger = Logger.getLogger(VideoAccessControlService_AV_10176.class);
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
	private HashMap<String, String> loginapiresponse_mediaadmin;
	private HashMap<String, String> uploadvidoeapiresponse;
	private ApiUtils apiutils;
    private VideoAccessControlBean accesscontrolbeagPage;
	private HashMap<String, String> loginapiresponse_eventadmin;
	private HashMap<String, String> loginapiresponse_mediacontributor;
	private HashMap<String, String> loginapiresponse_mediacviewer;
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
		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		accesscontrolbeagPage = new VideoAccessControlBean();
		apibeanfactory = new ApiBeanFactory();
		assertionapiresponse = new AssertionAPIResponse();
		apiutils=new ApiUtils();
		new AddUserApiBean();
		new AddUserBeanPage();
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
	@Test(description = "To verify video accesss control assign  to specific User as AccountAdmin ",groups = {EDITVIDEOACCESSAPI})
    public void TC_01_VideoAccessControl_AV10176_api_AccessControl_To_AccountAdmin( ) {
    
           logger.info("API Level Code is excuting");
           sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();          
           customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
           
           UserServices userservices = new UserServices();
           loginapiresponse= userservices.doLogin(apiutils.userJson(sUserName), surl);
           assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
           customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
           logger.info("Login API response Code :::" + loginapiresponse);
          
           UploadVideoService uploadvidoeservice = new UploadVideoService();
           apibeanfactory.UploadVideoBean(adduploadvideobean);
           adduploadvideobean.setUploader(sUserName);
           adduploadvideobean.setVideoAccessControl("Private");
   	       loginapiresponse.put("Mandatory", "No");
   		   loginapiresponse.put("fileName", "No");
          
   		   uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
           logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
           customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
          
           VideoAccessControlServiceAPI videoaccesscontrolservice=new VideoAccessControlServiceAPI();
           accesscontrolbeagPage.setUsercanEdit("true");
           accesscontrolbeagPage.setUserid(loginapiresponse.get(IUserAccountsService.USERID));
           accesscontrolbeagPage.setUsertype("User");
           videoaccesscontrolservice.videoAccessControl(uploadvidoeapiresponse,videoaccesscontrolservice.createJsonVideoAccessControl(accesscontrolbeagPage, IVideoAccessControlService.jsonFlagUser));
          
           logger.info("Selenium Code is excuting");
           launchURL(surl);
           customReport.reporter("Application launch with URL : ", surl);
           homePage = loginPage.loginAs(sUserName, sPassword);
           AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
           avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
           AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
           customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
           commentspage.click_settingsLink();
           commentspage.click_details();
           AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
           customReport.customizedReport("false",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[0]),statusValue, driver, sTestcaseName);
           customReport.customizedReport("false",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[5]),statusValue, driver, sTestcaseName);
           customReport.checkinglist(statusValue);
    }
	
	@Test(description = "To verify video accesss control assign  to specific User as MediaAdmin ",groups = {EDITVIDEOACCESSAPI})
	public void TC_02_VideoAccessControl_AV10176_api_AccessControl_To_MediaAdmin( ) {
	    
        logger.info("API Level Code is excuting");
        sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
        customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
        
        UserServices userservices = new UserServices();
        loginapiresponse= userservices.doLogin(apiutils.userJson(sUserName), surl);
        assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
        logger.info("Login API response Code :::" + loginapiresponse);
        
        UploadVideoService uploadvidoeservice = new UploadVideoService();
        apibeanfactory.UploadVideoBean(adduploadvideobean);
        adduploadvideobean.setUploader(sUserName);
        adduploadvideobean.setVideoAccessControl("Public");
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
       
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
        logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
        customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
        loginapiresponse_mediaadmin= userservices.doLogin(apiutils.userJson(IUsersList.VideoAccessControlmediaadmin), surl);
        assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediaadmin.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
       
        VideoAccessControlServiceAPI videoaccesscontrolservice=new VideoAccessControlServiceAPI();
        accesscontrolbeagPage.setUsercanEdit("true");
        accesscontrolbeagPage.setUserid(loginapiresponse_mediaadmin.get(IUserAccountsService.USERID));
        accesscontrolbeagPage.setUsertype("User");
        adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);
       
        logger.info("Selenium Code is excuting");
        launchURL(surl);
        customReport.reporter("Application launch with URL : ", surl);
        homePage = loginPage.loginAs(sUserName, sPassword);
        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
        avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
        AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
        customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
        commentspage.click_settingsLink();
        commentspage.click_details();
        AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
        customReport.customizedReport("true",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[0]),statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
 }
	@Test(description = "To verify video accesss control assign  to specific User as EventAdmin ",groups = {EDITVIDEOACCESSAPI})
	public void TC_03_VideoAccessControl_AV10176_api_AccessControl_To_EventAdmin( ) {
	    
        logger.info("API Level Code is excuting");
        sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
        customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
        
        UserServices userservices = new UserServices();
        loginapiresponse= userservices.doLogin(apiutils.userJson(sUserName), surl);
        assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
        logger.info("Login API response Code :::" + loginapiresponse);
        
        UploadVideoService uploadvidoeservice = new UploadVideoService();
        apibeanfactory.UploadVideoBean(adduploadvideobean);
        adduploadvideobean.setUploader(sUserName);
        adduploadvideobean.setVideoAccessControl("Private");
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
       
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
        logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
        customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
        loginapiresponse_eventadmin= userservices.doLogin(apiutils.userJson(IUsersList.VideoAccessControleventadmin), surl);
        assertionapiresponse.verifyAssert_httpCode(loginapiresponse_eventadmin.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
       
        VideoAccessControlServiceAPI videoaccesscontrolservice=new VideoAccessControlServiceAPI();
        accesscontrolbeagPage.setUsercanEdit("true");
        accesscontrolbeagPage.setUserid(loginapiresponse_eventadmin.get(IUserAccountsService.USERID));
        accesscontrolbeagPage.setUsertype("User");
        videoaccesscontrolservice.videoAccessControl(uploadvidoeapiresponse,videoaccesscontrolservice.createJsonVideoAccessControl(accesscontrolbeagPage, IVideoAccessControlService.jsonFlagUser));
       
        logger.info("Selenium Code is excuting");
        launchURL(surl);
        customReport.reporter("Application launch with URL : ", surl);
        homePage = loginPage.loginAs(sUserName, sPassword);
        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
        avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
        AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
        customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
        commentspage.click_settingsLink();
        commentspage.click_details();
        AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
        customReport.checkinglist(statusValue);
 }
	@Test(description = "To verify video accesss control assign  to specific User as MediaContributor ",groups = {EDITVIDEOACCESSAPI})
	public void TC_04_VideoAccessControl_AV10176_api_AccessControl_To_MediaContributor( ) {
	    
        logger.info("API Level Code is excuting");
        sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
        customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
       
        UserServices userservices = new UserServices();
        loginapiresponse= userservices.doLogin(apiutils.userJson(sUserName), surl);
        assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
        logger.info("Login API response Code :::" + loginapiresponse);
       
        UploadVideoService uploadvidoeservice = new UploadVideoService();
        apibeanfactory.UploadVideoBean(adduploadvideobean);
        adduploadvideobean.setUploader(sUserName);
        adduploadvideobean.setVideoAccessControl("Private");
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
        logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
        customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
        loginapiresponse_mediacontributor= userservices.doLogin(apiutils.userJson(IUsersList.VideoAccessControlmediacontributor), surl);
        assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediacontributor.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
       
        VideoAccessControlServiceAPI videoaccesscontrolservice=new VideoAccessControlServiceAPI();
        accesscontrolbeagPage.setUsercanEdit("true");
        accesscontrolbeagPage.setUserid(loginapiresponse_mediacontributor.get(IUserAccountsService.USERID));
        accesscontrolbeagPage.setUsertype("User");
        videoaccesscontrolservice.videoAccessControl(uploadvidoeapiresponse,videoaccesscontrolservice.createJsonVideoAccessControl(accesscontrolbeagPage, IVideoAccessControlService.jsonFlagUser));
       
        logger.info("Selenium Code is excuting");
        launchURL(surl);
        customReport.reporter("Application launch with URL : ", surl);
        homePage = loginPage.loginAs(sUserName, sPassword);
        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
        avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
        AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
        customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
        commentspage.click_settingsLink();
        commentspage.click_details();
        AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
        customReport.customizedReport("false",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[0]),statusValue, driver, sTestcaseName);
        customReport.customizedReport("false",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[5]),statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
 }
	@Test(description = "To verify video accesss control assign  to specific User as MediaViewer ",groups = {EDITVIDEOACCESSAPI})
	public void TC_05_VideoAccessControl_AV10176_api_AccessControl_To_MediaViewer()  {
	    
        logger.info("API Level Code is excuting");
        sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
        customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
       
        UserServices userservices = new UserServices();
        loginapiresponse= userservices.doLogin(apiutils.userJson(sUserName), surl);
        assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
        logger.info("Login API response Code :::" + loginapiresponse);
       
        UploadVideoService uploadvidoeservice = new UploadVideoService();
        apibeanfactory.UploadVideoBean(adduploadvideobean);
        adduploadvideobean.setUploader(sUserName);
        adduploadvideobean.setVideoAccessControl("Private");
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
        
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
        logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
        customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
        loginapiresponse_mediacviewer= userservices.doLogin(apiutils.userJson(IUsersList.VideoAccessControlmediaviewer), surl);
        assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediacviewer.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
        VideoAccessControlServiceAPI videoaccesscontrolservice=new VideoAccessControlServiceAPI();
        accesscontrolbeagPage.setUsercanEdit("true");
        accesscontrolbeagPage.setUserid(loginapiresponse_mediacviewer.get(IUserAccountsService.USERID));
        accesscontrolbeagPage.setUsertype("User");
        videoaccesscontrolservice.videoAccessControl(uploadvidoeapiresponse,videoaccesscontrolservice.createJsonVideoAccessControl(accesscontrolbeagPage, IVideoAccessControlService.jsonFlagUser));
       
        logger.info("Selenium Code is excuting");
        launchURL(surl);
        customReport.reporter("Application launch with URL : ", surl);
        homePage = loginPage.loginAs(sUserName, sPassword);
        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
        avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
        AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
        customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
        commentspage.click_settingsLink();
        commentspage.click_details();
        AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
        customReport.customizedReport("false",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[0]),statusValue, driver, sTestcaseName);
        customReport.customizedReport("false",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[5]),statusValue, driver, sTestcaseName);
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
