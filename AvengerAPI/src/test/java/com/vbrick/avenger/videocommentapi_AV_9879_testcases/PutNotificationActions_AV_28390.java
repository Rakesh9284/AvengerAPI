package com.vbrick.avenger.videocommentapi_AV_9879_testcases;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.openqa.selenium.interactions.Pause;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
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
import com.vbrick.avenger.pageobjects.AvengerVideoNotificationPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IDeleteSupplementalFile;
import com.vbricks.avenger.service.IDeleteVideoService;
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUserAccountsService;
import com.vbricks.avenger.service.IUserVideoWatchingStatusService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.service.IVideoAccessControlService;
import com.vbricks.avenger.serviceimpl.DeleteSupplementalFilesAPI;
import com.vbricks.avenger.serviceimpl.GetNotificationsAPI;
import com.vbricks.avenger.serviceimpl.GetVideoSupplementalFiles;
import com.vbricks.avenger.serviceimpl.PutNotificationsAPI;
import com.vbricks.avenger.serviceimpl.UploadSupplementalServiceAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.serviceimpl.VideoAccessControlServiceAPI;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class PutNotificationActions_AV_28390 extends TestBase {

	private static Logger logger = Logger.getLogger(PutNotificationActions_AV_28390.class);
	private AvengerLoginPage loginPage;
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
	public AvengerHomePage avengerHomePage;
	private AssertionAPIResponse assertionapiresponse;
	private HashMap<String, String> loginapiresponse;
	private HashMap<String, String> loginapiresponse1;
	private HashMap<String, String> uploadsupplementalapiresponse;
	private HashMap<String, String> uploadsupplementalapiresponse2;
	private HashMap<String, String> uploadsupplementalapiresponse3;
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

	@Test(description = " To Put Read All Notifications by accountadmin",groups = {PUTNOTIFICATIONS})
	public void TC_01_PUT_Read_AllNotifications_AccountAdmin() {
		 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		//Upload Video (1)
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
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
				
		//Upload Video (2)
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
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
		
		//Upload Video (3)
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
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
	
		PutNotificationsAPI putNotificationsAPI = new PutNotificationsAPI();
		HashMap<String, String> putNotificationsapiresponse = putNotificationsAPI.putallNotificationsasRead(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage = loginPage.loginAs(sUserName, sPassword);	
		avengerHomePage.click_notificationbutton();
		customReport.customizedReport("No Unread Notifications",avengerHomePage.get_unreadnotificationcount(), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = " To Put Read All Notifications by MediaViewer",groups = {PUTNOTIFICATIONS})
	public void TC_02_PUT_Read_AllNotifications_MediaViewer() { //Set uploader as MediaViewer to get notification for the respective user
		 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		//Upload Video (1)
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(IUsersList.PutNotifications_MediaViewer);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		//Upload Video (2)
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(IUsersList.PutNotifications_MediaViewer);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);				
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);			
		
		//Upload Video (3)
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(IUsersList.PutNotifications_MediaViewer);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);				
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.PutNotifications_MediaViewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		 
		PutNotificationsAPI putNotificationsAPI = new PutNotificationsAPI();
		HashMap<String, String> putNotificationsapiresponse = putNotificationsAPI.putallNotificationsasRead(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage = loginPage.loginAs(IUsersList.PutNotifications_MediaViewer, sPassword);	
		avengerHomePage.click_notificationbutton();
		customReport.customizedReport("No Unread Notifications",avengerHomePage.get_unreadnotificationcount(), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = " To Put Read All Notifications by MediaContributor",groups = {PUTNOTIFICATIONS})
	public void TC_03_PUT_Read_AllNotifications_MediaContributor(){ //Set uploader as MediaContributor to get notification for the respective user
		 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		//Upload Video (1)
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(IUsersList.PutNotifications_MediaContributor);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		//Upload Video (2)
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(IUsersList.PutNotifications_MediaContributor);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);				
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);			
		
		//Upload Video (3)
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(IUsersList.PutNotifications_MediaContributor);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);				
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.PutNotifications_MediaContributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		PutNotificationsAPI putNotificationsAPI = new PutNotificationsAPI();
		HashMap<String, String> putNotificationsapiresponse = putNotificationsAPI.putallNotificationsasRead(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage = loginPage.loginAs(IUsersList.PutNotifications_MediaContributor, sPassword);	
		avengerHomePage.click_notificationbutton();
		customReport.customizedReport("No Unread Notifications",avengerHomePage.get_unreadnotificationcount(), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = " To Put Read All Notifications by EventHost",groups = {PUTNOTIFICATIONS})
	public void TC_04_PUT_Read_AllNotifications_EventHost(){ //Set uploader as EventHost to get notification for the respective user
		 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		//Upload Video (1)
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(IUsersList.PutNotifications_EventHost);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		//Upload Video (2)
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(IUsersList.PutNotifications_EventHost);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);				
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);			
		
		//Upload Video (3)
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(IUsersList.PutNotifications_EventHost);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);				
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.PutNotifications_EventHost), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		PutNotificationsAPI putNotificationsAPI = new PutNotificationsAPI();
		HashMap<String, String> putNotificationsapiresponse = putNotificationsAPI.putallNotificationsasRead(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage = loginPage.loginAs(IUsersList.PutNotifications_EventHost, sPassword);	
		avengerHomePage.click_notificationbutton();
		customReport.customizedReport("No Unread Notifications",avengerHomePage.get_unreadnotificationcount(), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	
}
	
	@Test(description = " To Put Read All Notifications by EventAdmin",groups = {PUTNOTIFICATIONS})
	public void TC_05_PUT_Read_AllNotifications_EventAdmin(){ //Set uploader as EventAdmin to get notification for the respective user
		 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		//Upload Video (1)
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(IUsersList.PutNotifications_EventAdmin);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		//Upload Video (2)
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(IUsersList.PutNotifications_EventAdmin);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);				
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);			
		
		//Upload Video (3)
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(IUsersList.PutNotifications_EventAdmin);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);				
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.PutNotifications_EventAdmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
	 
		PutNotificationsAPI putNotificationsAPI = new PutNotificationsAPI();
		HashMap<String, String> putNotificationsapiresponse = putNotificationsAPI.putallNotificationsasRead(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage = loginPage.loginAs(IUsersList.PutNotifications_EventAdmin, sPassword);	
		avengerHomePage.click_notificationbutton();
		customReport.customizedReport("No Unread Notifications",avengerHomePage.get_unreadnotificationcount(), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	
}
	@Test(description = " To Put Read All Notifications by MediaAdmin",groups = {PUTNOTIFICATIONS})
	public void TC_06_PUT_Read_AllNotifications_MediaAdmin(){ //Set uploader as MediaAdmin to get notification for the respective user
		 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		//Upload Video (1)
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(IUsersList.PutNotifications_MediaAdmin);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		//Upload Video (2)
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(IUsersList.PutNotifications_MediaAdmin);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);				
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);			
		
		//Upload Video (3)
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(IUsersList.PutNotifications_MediaAdmin);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);				
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.PutNotifications_MediaAdmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		PutNotificationsAPI putNotificationsAPI = new PutNotificationsAPI();
		HashMap<String, String> putNotificationsapiresponse = putNotificationsAPI.putallNotificationsasRead(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage = loginPage.loginAs(IUsersList.PutNotifications_MediaAdmin, sPassword);	
		avengerHomePage.click_notificationbutton();
		customReport.customizedReport("No Unread Notifications",avengerHomePage.get_unreadnotificationcount(), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	
}
	@Test(description = " To Put Read Specific Notifications by accountadmin",groups = {PUTNOTIFICATIONS})
	public void TC_07_PUT_Read_Specific_Notifications_AccountAdmin() throws InterruptedException{
		 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		//To Mark all the existing unread notifications as read
		PutNotificationsAPI putNotificationsAPI = new PutNotificationsAPI();
		HashMap<String, String> putNotificationsapiresponse = putNotificationsAPI.putallNotificationsasRead(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Upload Video (1)
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
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
				
		//Upload Video (2)
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
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
		
		//Upload Video (3)
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
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
	
		Thread.sleep(30000);
		GetNotificationsAPI GetNotificationsAPIresponse = new GetNotificationsAPI();
		HashMap<String, String> getunreadNotificationsapiresponse = GetNotificationsAPIresponse.getmultipleunreadNotifications(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		loginapiresponse.put(IAPIConstantCodes.NotificationId, getunreadNotificationsapiresponse.get(IAPIConstantCodes.NotificationId));
		
		HashMap<String, String> putNotificationsapiresponse1 = putNotificationsAPI.putsingleNotificationsasRead(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage = loginPage.loginAs(sUserName, sPassword);	
		avengerHomePage.click_notificationbutton();
		
		ArrayList<String> uiunreadnotificationList = avengerHomePage.get_UnreadNotifications();
		Collections.sort(uiunreadnotificationList);
		int uiunreadnotificationCount = uiunreadnotificationList.size();
		logger.info("@@@UI User Entities list count is " +uiunreadnotificationCount);
		
		customReport.customizedReport(2,uiunreadnotificationCount, statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = " To Put Read Specific Notifications by MediaViewer",groups = {PUTNOTIFICATIONS})
	public void TC_08_PUT_Read_Specific_Notifications_MediaViewer() throws InterruptedException {
		 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.PutNotifications_MediaViewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//To Mark all the existing unread notifications as read
		PutNotificationsAPI putNotificationsAPI = new PutNotificationsAPI();
		HashMap<String, String> putNotificationsapiresponse = putNotificationsAPI.putallNotificationsasRead(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Upload Video (1)
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(IUsersList.PutNotifications_MediaViewer);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		//Upload Video (2)
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(IUsersList.PutNotifications_MediaViewer);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);				
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);			
		
		//Upload Video (3)
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(IUsersList.PutNotifications_MediaViewer);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);				
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

        Thread.sleep(30000);
		GetNotificationsAPI GetNotificationsAPIresponse = new GetNotificationsAPI();
		HashMap<String, String> getunreadNotificationsapiresponse = GetNotificationsAPIresponse.getmultipleunreadNotifications(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		loginapiresponse1.put(IAPIConstantCodes.NotificationId, getunreadNotificationsapiresponse.get(IAPIConstantCodes.NotificationId));
		HashMap<String, String> putNotificationsapiresponse1 = putNotificationsAPI.putsingleNotificationsasRead(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage = loginPage.loginAs(IUsersList.PutNotifications_MediaViewer, sPassword);	
		avengerHomePage.click_notificationbutton();
		
		ArrayList<String> uiunreadnotificationList = avengerHomePage.get_UnreadNotifications();
		Collections.sort(uiunreadnotificationList);
		int uiunreadnotificationCount = uiunreadnotificationList.size();
		logger.info("@@@UI User Entities list count is " +uiunreadnotificationCount);
		
		customReport.customizedReport(2,uiunreadnotificationCount, statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = " To Put Read Specific Notifications by MediaContributor",groups = {PUTNOTIFICATIONS})
	public void TC_09_PUT_Read_Specific_Notifications_MediaContributor() throws InterruptedException {
		 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.PutNotifications_MediaContributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//To Mark all the existing unread notifications as read
		PutNotificationsAPI putNotificationsAPI = new PutNotificationsAPI();
		HashMap<String, String> putNotificationsapiresponse = putNotificationsAPI.putallNotificationsasRead(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Upload Video (1)
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(IUsersList.PutNotifications_MediaContributor);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		//Upload Video (2)
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(IUsersList.PutNotifications_MediaContributor);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);				
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);			
		
		//Upload Video (3)
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(IUsersList.PutNotifications_MediaContributor);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);				
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	
		Thread.sleep(30000);//30 seconds
		GetNotificationsAPI GetNotificationsAPIresponse = new GetNotificationsAPI();
		HashMap<String, String> getunreadNotificationsapiresponse = GetNotificationsAPIresponse.getmultipleunreadNotifications(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		loginapiresponse1.put(IAPIConstantCodes.NotificationId, getunreadNotificationsapiresponse.get(IAPIConstantCodes.NotificationId));
		HashMap<String, String> putNotificationsapiresponse1 = putNotificationsAPI.putsingleNotificationsasRead(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage = loginPage.loginAs(IUsersList.PutNotifications_MediaContributor, sPassword);	
		avengerHomePage.click_notificationbutton();
		
		ArrayList<String> uiunreadnotificationList = avengerHomePage.get_UnreadNotifications();
		Collections.sort(uiunreadnotificationList);
		int uiunreadnotificationCount = uiunreadnotificationList.size();
		logger.info("@@@UI User Entities list count is " +uiunreadnotificationCount);
		
		customReport.customizedReport(2,uiunreadnotificationCount, statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = " To Put Read Specific Notifications by EventHost",groups = {PUTNOTIFICATIONS})
	public void TC_10_PUT_Read_Specific_Notifications_EventHost() throws InterruptedException {
		 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.PutNotifications_EventHost), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//To Mark all the existing unread notifications as read
		PutNotificationsAPI putNotificationsAPI = new PutNotificationsAPI();
		HashMap<String, String> putNotificationsapiresponse = putNotificationsAPI.putallNotificationsasRead(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Upload Video (1)
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(IUsersList.PutNotifications_EventHost);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		//Upload Video (2)
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(IUsersList.PutNotifications_EventHost);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);				
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);			
		
		//Upload Video (3)
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(IUsersList.PutNotifications_EventHost);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);				
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	
		Thread.sleep(30000);
		GetNotificationsAPI GetNotificationsAPIresponse = new GetNotificationsAPI();
		HashMap<String, String> getunreadNotificationsapiresponse = GetNotificationsAPIresponse.getmultipleunreadNotifications(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		loginapiresponse1.put(IAPIConstantCodes.NotificationId, getunreadNotificationsapiresponse.get(IAPIConstantCodes.NotificationId));
		HashMap<String, String> putNotificationsapiresponse1 = putNotificationsAPI.putsingleNotificationsasRead(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage = loginPage.loginAs(IUsersList.PutNotifications_EventHost, sPassword);	
		avengerHomePage.click_notificationbutton();
		
		ArrayList<String> uiunreadnotificationList = avengerHomePage.get_UnreadNotifications();
		Collections.sort(uiunreadnotificationList);
		int uiunreadnotificationCount = uiunreadnotificationList.size();
		logger.info("@@@UI User Entities list count is " +uiunreadnotificationCount);
		
		customReport.customizedReport(2,uiunreadnotificationCount, statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = " To Put Read Specific Notifications by MediaAdmin",groups = {PUTNOTIFICATIONS})
	public void TC_11_PUT_Read_Specific_Notifications_MediaAdmin() throws InterruptedException {
		 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.PutNotifications_MediaAdmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//To Mark all the existing unread notifications as read
		PutNotificationsAPI putNotificationsAPI = new PutNotificationsAPI();
		HashMap<String, String> putNotificationsapiresponse = putNotificationsAPI.putallNotificationsasRead(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Upload Video (1)
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(IUsersList.PutNotifications_MediaAdmin);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		//Upload Video (2)
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(IUsersList.PutNotifications_MediaAdmin);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);				
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);			
		
		//Upload Video (3)
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(IUsersList.PutNotifications_MediaAdmin);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);				
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	
		Thread.sleep(30000);
		GetNotificationsAPI GetNotificationsAPIresponse = new GetNotificationsAPI();
		HashMap<String, String> getunreadNotificationsapiresponse = GetNotificationsAPIresponse.getmultipleunreadNotifications(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		loginapiresponse1.put(IAPIConstantCodes.NotificationId, getunreadNotificationsapiresponse.get(IAPIConstantCodes.NotificationId));
		HashMap<String, String> putNotificationsapiresponse1 = putNotificationsAPI.putsingleNotificationsasRead(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage = loginPage.loginAs(IUsersList.PutNotifications_MediaAdmin, sPassword);	
		avengerHomePage.click_notificationbutton();
		
		ArrayList<String> uiunreadnotificationList = avengerHomePage.get_UnreadNotifications();
		Collections.sort(uiunreadnotificationList);
		int uiunreadnotificationCount = uiunreadnotificationList.size();
		logger.info("@@@UI User Entities list count is " +uiunreadnotificationCount);
		
		customReport.customizedReport(2,uiunreadnotificationCount, statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = " To Put Read Specific Notifications by EventAdmin",groups = {PUTNOTIFICATIONS})
	public void TC_12_PUT_Read_Specific_Notifications_EventAdmin() throws Exception {
		 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.PutNotifications_EventAdmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//To Mark all the existing unread notifications as read
		PutNotificationsAPI putNotificationsAPI = new PutNotificationsAPI();
		HashMap<String, String> putNotificationsapiresponse = putNotificationsAPI.putallNotificationsasRead(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Upload Video (1)
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(IUsersList.PutNotifications_EventAdmin);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		//Upload Video (2)
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(IUsersList.PutNotifications_EventAdmin);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);				
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);			
		
		//Upload Video (3)
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(IUsersList.PutNotifications_EventAdmin);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);				
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		Thread.sleep(30000);
		GetNotificationsAPI GetNotificationsAPIresponse = new GetNotificationsAPI();
		HashMap<String, String> getunreadNotificationsapiresponse = GetNotificationsAPIresponse.getmultipleunreadNotifications(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		loginapiresponse1.put(IAPIConstantCodes.NotificationId, getunreadNotificationsapiresponse.get(IAPIConstantCodes.NotificationId));
		HashMap<String, String> putNotificationsapiresponse1 = putNotificationsAPI.putsingleNotificationsasRead(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, putNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage = loginPage.loginAs(IUsersList.PutNotifications_EventAdmin, sPassword);	
		avengerHomePage.click_notificationbutton();
		
		ArrayList<String> uiunreadnotificationList = avengerHomePage.get_UnreadNotifications();
		Collections.sort(uiunreadnotificationList);
		int uiunreadnotificationCount = uiunreadnotificationList.size();
		logger.info("@@@UI User Entities list count is " +uiunreadnotificationCount);
		
		customReport.customizedReport(2,uiunreadnotificationCount, statusValue, driver, sTestcaseName);
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
		
