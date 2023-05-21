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

public class GetNotificationsAPI_AV_28389 extends TestBase {

	private static Logger logger = Logger.getLogger(GetNotificationsAPI_AV_28389.class);
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

	@Test(description = " To Get UnRead Notifications by accountadmin",groups = {GETNOTIFICATIONS})
	public void TC_01_GET_UnRead_Notifications_AccountAdmin() throws InterruptedException {
		 
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
	
		Thread.sleep(180000);
		GetNotificationsAPI GetNotificationsAPIresponse = new GetNotificationsAPI();
		HashMap<String, String> getunreadNotificationsapiresponse = GetNotificationsAPIresponse.getunreadNotifications(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		String fromattedJSONforuser=apiutils.formatingapiresponse(getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> apiunreadnotificationList = new ArrayList<String>(Arrays.asList(fromattedJSONforuser.split(",")));
		Collections.sort(apiunreadnotificationList);
		int apiunreadnotificationCount = apiunreadnotificationList.size();
		logger.info("API Notification list count is " +apiunreadnotificationCount);
		
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
		
		if (apiunreadnotificationCount == uiunreadnotificationCount) {
			customReport.customizedReport(apiunreadnotificationCount,uiunreadnotificationCount, statusValue, driver, sTestcaseName);
			logger.info("@@@API Count is" +apiunreadnotificationCount+ "@@@and UI Count is" +uiunreadnotificationCount);
		}
		else {
		customReport.customizedReport(apiunreadnotificationCount,uiunreadnotificationCount, statusValue, driver, sTestcaseName);
		}
		customReport.checkinglist(statusValue);	
		
		//Clicking Mark All As Read (To Clear all the notifications for that user)		
		avengerHomePage.click_markallasread();
	}
	
	@Test(description = " To Get UnRead Notifications by MediaViewers",groups = {GETNOTIFICATIONS})
	public void TC_02_GET_UnRead_Notifications_MediaViewer() throws InterruptedException { //Set uploader as MediaViewer to get notification for the respective user
		 
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
		adduploadvideobean.setUploader(IUsersList.GetNotifications_MediaViewer);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.GetNotifications_MediaViewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		Thread.sleep(100000);
		GetNotificationsAPI GetNotificationsAPIresponse = new GetNotificationsAPI();
		HashMap<String, String> getunreadNotificationsapiresponse = GetNotificationsAPIresponse.getunreadNotifications(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		String fromattedJSONforuser=apiutils.formatingapiresponse(getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> apiunreadnotificationList = new ArrayList<String>(Arrays.asList(fromattedJSONforuser.split(",")));
		Collections.sort(apiunreadnotificationList);
		int apiunreadnotificationCount = apiunreadnotificationList.size();
		logger.info("API Notification list count is " +apiunreadnotificationCount);
		
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage = loginPage.loginAs(IUsersList.GetNotifications_MediaViewer, sPassword);	
		avengerHomePage.click_notificationbutton();
		
		ArrayList<String> uiunreadnotificationList = avengerHomePage.get_UnreadNotifications();
		Collections.sort(uiunreadnotificationList);
		int uiunreadnotificationCount = uiunreadnotificationList.size();
		logger.info("@@@UI User Entities list count is " +uiunreadnotificationCount);
		
		if (apiunreadnotificationCount == uiunreadnotificationCount) {
			customReport.customizedReport(apiunreadnotificationCount,uiunreadnotificationCount, statusValue, driver, sTestcaseName);
			logger.info("@@@API Count is" +apiunreadnotificationCount+ "@@@and UI Count is" +uiunreadnotificationCount);
		}
		else {
		customReport.customizedReport(apiunreadnotificationCount,uiunreadnotificationCount, statusValue, driver, sTestcaseName);
		}
		customReport.checkinglist(statusValue);	
		
		//Clicking Mark All As Read (To Clear all the notifications for that user)	
		avengerHomePage.click_markallasread();
	}
	
	@Test(description = " To Get UnRead Notifications by MediaContributor",groups = {GETNOTIFICATIONS})
	public void TC_03_GET_UnRead_Notifications_MediaContributor() throws InterruptedException { //Set uploader as MediaContributor to get notification for the respective user
		 
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
		adduploadvideobean.setUploader(IUsersList.GetNotifications_MediaContributor);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.GetNotifications_MediaContributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		Thread.sleep(100000);
		GetNotificationsAPI GetNotificationsAPIresponse = new GetNotificationsAPI();
		HashMap<String, String> getunreadNotificationsapiresponse = GetNotificationsAPIresponse.getunreadNotifications(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		String fromattedJSONforuser=apiutils.formatingapiresponse(getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> apiunreadnotificationList = new ArrayList<String>(Arrays.asList(fromattedJSONforuser.split(",")));
		Collections.sort(apiunreadnotificationList);
		int apiunreadnotificationCount = apiunreadnotificationList.size();
		logger.info("API Notification list count is " +apiunreadnotificationCount);
		
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage = loginPage.loginAs(IUsersList.GetNotifications_MediaContributor, sPassword);	
		avengerHomePage.click_notificationbutton();
		
		ArrayList<String> uiunreadnotificationList = avengerHomePage.get_UnreadNotifications();
		Collections.sort(uiunreadnotificationList);
		int uiunreadnotificationCount = uiunreadnotificationList.size();
		logger.info("@@@UI User Entities list count is " +uiunreadnotificationCount);
		
		if (apiunreadnotificationCount == uiunreadnotificationCount) {
			customReport.customizedReport(apiunreadnotificationCount,uiunreadnotificationCount, statusValue, driver, sTestcaseName);
			logger.info("@@@API Count is" +apiunreadnotificationCount+ "@@@and UI Count is" +uiunreadnotificationCount);
		}
		else {
		customReport.customizedReport(apiunreadnotificationCount,uiunreadnotificationCount, statusValue, driver, sTestcaseName);
		}
		customReport.checkinglist(statusValue);	
		
		//Clicking Mark All As Read (To Clear all the notifications for that user)	
		avengerHomePage.click_markallasread();
	}
	
	@Test(description = " To Get UnRead Notifications by EventHost",groups = {GETNOTIFICATIONS})
	public void TC_04_GET_UnRead_Notifications_EventHost() throws InterruptedException { //Set uploader as EventHost to get notification for the respective user
		 
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
		adduploadvideobean.setUploader(IUsersList.GetNotifications_EventHost);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.GetNotifications_EventHost), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		Thread.sleep(100000);
		GetNotificationsAPI GetNotificationsAPIresponse = new GetNotificationsAPI();
		HashMap<String, String> getunreadNotificationsapiresponse = GetNotificationsAPIresponse.getunreadNotifications(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		String fromattedJSONforuser=apiutils.formatingapiresponse(getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> apiunreadnotificationList = new ArrayList<String>(Arrays.asList(fromattedJSONforuser.split(",")));
		Collections.sort(apiunreadnotificationList);
		int apiunreadnotificationCount = apiunreadnotificationList.size();
		logger.info("API Notification list count is " +apiunreadnotificationCount);
		
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage = loginPage.loginAs(IUsersList.GetNotifications_EventHost, sPassword);	
		avengerHomePage.click_notificationbutton();
		
		ArrayList<String> uiunreadnotificationList = avengerHomePage.get_UnreadNotifications();
		Collections.sort(uiunreadnotificationList);
		int uiunreadnotificationCount = uiunreadnotificationList.size();
		logger.info("@@@UI User Entities list count is " +uiunreadnotificationCount);
		
		if (apiunreadnotificationCount == uiunreadnotificationCount) {
			customReport.customizedReport(apiunreadnotificationCount,uiunreadnotificationCount, statusValue, driver, sTestcaseName);
			logger.info("@@@API Count is" +apiunreadnotificationCount+ "@@@and UI Count is" +uiunreadnotificationCount);
		}
		else {
		customReport.customizedReport(apiunreadnotificationCount,uiunreadnotificationCount, statusValue, driver, sTestcaseName);
		}
		customReport.checkinglist(statusValue);	
		
		//Clicking Mark All As Read (To Clear all the notifications for that user)	
		avengerHomePage.click_markallasread();
	}
	
	@Test(description = " To Get UnRead Notifications by EventAdmin",groups = {GETNOTIFICATIONS})
	public void TC_05_GET_UnRead_Notifications_EventAdmin() throws InterruptedException { //Set uploader as EventAdmin to get notification for the respective user
		 
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
		adduploadvideobean.setUploader(IUsersList.GetNotifications_EventAdmin);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.GetNotifications_EventAdmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		Thread.sleep(100000);
		GetNotificationsAPI GetNotificationsAPIresponse = new GetNotificationsAPI();
		HashMap<String, String> getunreadNotificationsapiresponse = GetNotificationsAPIresponse.getunreadNotifications(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		String fromattedJSONforuser=apiutils.formatingapiresponse(getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> apiunreadnotificationList = new ArrayList<String>(Arrays.asList(fromattedJSONforuser.split(",")));
		Collections.sort(apiunreadnotificationList);
		int apiunreadnotificationCount = apiunreadnotificationList.size();
		logger.info("API Notification list count is " +apiunreadnotificationCount);
		
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage = loginPage.loginAs(IUsersList.GetNotifications_EventAdmin, sPassword);	
		avengerHomePage.click_notificationbutton();
		
		ArrayList<String> uiunreadnotificationList = avengerHomePage.get_UnreadNotifications();
		Collections.sort(uiunreadnotificationList);
		int uiunreadnotificationCount = uiunreadnotificationList.size();
		logger.info("@@@UI User Entities list count is " +uiunreadnotificationCount);
		
		if (apiunreadnotificationCount == uiunreadnotificationCount) {
			customReport.customizedReport(apiunreadnotificationCount,uiunreadnotificationCount, statusValue, driver, sTestcaseName);
			logger.info("@@@API Count is" +apiunreadnotificationCount+ "@@@and UI Count is" +uiunreadnotificationCount);
		}
		else {
		customReport.customizedReport(apiunreadnotificationCount,uiunreadnotificationCount, statusValue, driver, sTestcaseName);
		}
		customReport.checkinglist(statusValue);	
		
		//Clicking Mark All As Read (To Clear all the notifications for that user)	
		avengerHomePage.click_markallasread();
	}
	

	@Test(description = " To Get UnRead Notifications by MediaAdmin",groups = {GETNOTIFICATIONS})
	public void TC_06_GET_UnRead_Notifications_MeidaAdmin() throws InterruptedException { //Set uploader as MediaAdmin to get notification for the respective user
		 
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
		adduploadvideobean.setUploader(IUsersList.GetNotifications_MediaAdmin);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.GetNotifications_MediaAdmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		Thread.sleep(100000);
		GetNotificationsAPI GetNotificationsAPIresponse = new GetNotificationsAPI();
		HashMap<String, String> getunreadNotificationsapiresponse = GetNotificationsAPIresponse.getunreadNotifications(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		String fromattedJSONforuser=apiutils.formatingapiresponse(getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> apiunreadnotificationList = new ArrayList<String>(Arrays.asList(fromattedJSONforuser.split(",")));
		Collections.sort(apiunreadnotificationList);
		int apiunreadnotificationCount = apiunreadnotificationList.size();
		logger.info("API Notification list count is " +apiunreadnotificationCount);
		
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage = loginPage.loginAs(IUsersList.GetNotifications_MediaAdmin, sPassword);	
		avengerHomePage.click_notificationbutton();
		
		ArrayList<String> uiunreadnotificationList = avengerHomePage.get_UnreadNotifications();
		Collections.sort(uiunreadnotificationList);
		int uiunreadnotificationCount = uiunreadnotificationList.size();
		logger.info("@@@UI User Entities list count is " +uiunreadnotificationCount);
		
		if (apiunreadnotificationCount == uiunreadnotificationCount) {
			customReport.customizedReport(apiunreadnotificationCount,uiunreadnotificationCount, statusValue, driver, sTestcaseName);
			logger.info("@@@API Count is" +apiunreadnotificationCount+ "@@@and UI Count is" +uiunreadnotificationCount);
		}
		else {
		customReport.customizedReport(apiunreadnotificationCount,uiunreadnotificationCount, statusValue, driver, sTestcaseName);
		}
		customReport.checkinglist(statusValue);	
		
		//Clicking Mark All As Read (To Clear all the notifications for that user)	
		avengerHomePage.click_markallasread();
	}
	
	@Test(description = " To Get All Notifications by accountadmin",groups = {GETNOTIFICATIONS})
	public void TC_07_GET_All_Notifications_AccountAdmin() throws InterruptedException {
		 
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
	
		Thread.sleep(50000);
		GetNotificationsAPI GetNotificationsAPIresponse = new GetNotificationsAPI();
		HashMap<String, String> getunreadNotificationsapiresponse = GetNotificationsAPIresponse.getAllNotifications(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		String fromattedJSONforuser=apiutils.formatingapiresponse(getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> apiunreadnotificationList = new ArrayList<String>(Arrays.asList(fromattedJSONforuser.split(",")));
		Collections.sort(apiunreadnotificationList);
		int apiunreadnotificationCount = apiunreadnotificationList.size();
		logger.info("API Notification list count is " +apiunreadnotificationCount);
		
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage = loginPage.loginAs(sUserName, sPassword);	
		avengerHomePage.click_notificationbutton();
		avengerHomePage.click_markallasread();
		avengerHomePage.click_notificationbutton();
		ArrayList<String> uiunreadnotificationList = avengerHomePage.get_AllNotifications();
		Collections.sort(uiunreadnotificationList);
		int uiunreadnotificationCount = uiunreadnotificationList.size();
		logger.info("@@@UI User Entities list count is " +uiunreadnotificationCount);
		
		if (apiunreadnotificationCount == uiunreadnotificationCount) {
			customReport.customizedReport(apiunreadnotificationCount,uiunreadnotificationCount, statusValue, driver, sTestcaseName);
			logger.info("@@@API Count is" +apiunreadnotificationCount+ "@@@and UI Count is" +uiunreadnotificationCount);
		}
	/////////	
		else {
		customReport.customizedReport(apiunreadnotificationCount,uiunreadnotificationCount, statusValue, driver, sTestcaseName);
		}
		customReport.checkinglist(statusValue);	
	}
	
	@Test(description = " To Get All Notifications by MediaViewers",groups = {GETNOTIFICATIONS})
	public void TC_08_GET_All_Notifications_MediaViewer() throws InterruptedException { //Set uploader as MediaViewer to get notification for the respective user
		 
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
		adduploadvideobean.setUploader(IUsersList.GetNotifications_MediaViewer);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.GetNotifications_MediaViewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		Thread.sleep(100000);
		GetNotificationsAPI GetNotificationsAPIresponse = new GetNotificationsAPI();
		HashMap<String, String> getunreadNotificationsapiresponse = GetNotificationsAPIresponse.getAllNotifications(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		String fromattedJSONforuser=apiutils.formatingapiresponse(getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> apiunreadnotificationList = new ArrayList<String>(Arrays.asList(fromattedJSONforuser.split(",")));
		Collections.sort(apiunreadnotificationList);
		int apiunreadnotificationCount = apiunreadnotificationList.size();
		logger.info("API Notification list count is " +apiunreadnotificationCount);
		
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage = loginPage.loginAs(IUsersList.GetNotifications_MediaViewer, sPassword);	
		avengerHomePage.click_notificationbutton();
		avengerHomePage.click_markallasread();
		avengerHomePage.click_notificationbutton();
		ArrayList<String> uiunreadnotificationList = avengerHomePage.get_AllNotifications();
		Collections.sort(uiunreadnotificationList);
		int uiunreadnotificationCount = uiunreadnotificationList.size();
		logger.info("@@@UI User Entities list count is " +uiunreadnotificationCount);
		
		if (apiunreadnotificationCount == uiunreadnotificationCount) {
			customReport.customizedReport(apiunreadnotificationCount,uiunreadnotificationCount, statusValue, driver, sTestcaseName);
			logger.info("@@@API Count is" +apiunreadnotificationCount+ "@@@and UI Count is" +uiunreadnotificationCount);
		}
		else {
		customReport.customizedReport(apiunreadnotificationCount,uiunreadnotificationCount, statusValue, driver, sTestcaseName);
		}
		customReport.checkinglist(statusValue);	
	}
	
	@Test(description = " To Get All Notifications by MediaContributor",groups = {GETNOTIFICATIONS})
	public void TC_09_GET_All_Notifications_MediaContributor() throws InterruptedException { //Set uploader as MediaContributor to get notification for the respective user
		  
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
		adduploadvideobean.setUploader(IUsersList.GetNotifications_MediaContributor);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.GetNotifications_MediaContributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		Thread.sleep(100000);
		GetNotificationsAPI GetNotificationsAPIresponse = new GetNotificationsAPI();
		HashMap<String, String> getunreadNotificationsapiresponse = GetNotificationsAPIresponse.getAllNotifications(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		String fromattedJSONforuser=apiutils.formatingapiresponse(getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> apiunreadnotificationList = new ArrayList<String>(Arrays.asList(fromattedJSONforuser.split(",")));
		Collections.sort(apiunreadnotificationList);
		int apiunreadnotificationCount = apiunreadnotificationList.size();
		logger.info("API Notification list count is " +apiunreadnotificationCount);
		
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage = loginPage.loginAs(IUsersList.GetNotifications_MediaContributor, sPassword);	
		avengerHomePage.click_notificationbutton();
		avengerHomePage.click_markallasread();
		avengerHomePage.click_notificationbutton();
		ArrayList<String> uiunreadnotificationList = avengerHomePage.get_AllNotifications();
		Collections.sort(uiunreadnotificationList);
		int uiunreadnotificationCount = uiunreadnotificationList.size();
		logger.info("@@@UI User Entities list count is " +uiunreadnotificationCount);
		
		if (apiunreadnotificationCount == uiunreadnotificationCount) {
			customReport.customizedReport(apiunreadnotificationCount,uiunreadnotificationCount, statusValue, driver, sTestcaseName);
			logger.info("@@@API Count is" +apiunreadnotificationCount+ "@@@and UI Count is" +uiunreadnotificationCount);
		}
		else {
		customReport.customizedReport(apiunreadnotificationCount,uiunreadnotificationCount, statusValue, driver, sTestcaseName);
		}
		customReport.checkinglist(statusValue);	
	}
	
	
	@Test(description = " To Get All Notifications by EventHost",groups = {GETNOTIFICATIONS})
	public void TC_10_GET_All_Notifications_EventHost() throws InterruptedException { //Set uploader as EventHost to get notification for the respective user
		  
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
		adduploadvideobean.setUploader(IUsersList.GetNotifications_EventHost);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.GetNotifications_EventHost), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		Thread.sleep(100000);
		GetNotificationsAPI GetNotificationsAPIresponse = new GetNotificationsAPI();
		HashMap<String, String> getunreadNotificationsapiresponse = GetNotificationsAPIresponse.getAllNotifications(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		String fromattedJSONforuser=apiutils.formatingapiresponse(getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> apiunreadnotificationList = new ArrayList<String>(Arrays.asList(fromattedJSONforuser.split(",")));
		Collections.sort(apiunreadnotificationList);
		int apiunreadnotificationCount = apiunreadnotificationList.size();
		logger.info("API Notification list count is " +apiunreadnotificationCount);
		
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage = loginPage.loginAs(IUsersList.GetNotifications_EventHost, sPassword);	
		avengerHomePage.click_notificationbutton();
		avengerHomePage.click_markallasread();
		avengerHomePage.click_notificationbutton();
		ArrayList<String> uiunreadnotificationList = avengerHomePage.get_AllNotifications();
		Collections.sort(uiunreadnotificationList);
		int uiunreadnotificationCount = uiunreadnotificationList.size();
		logger.info("@@@UI User Entities list count is " +uiunreadnotificationCount);
		
		if (apiunreadnotificationCount == uiunreadnotificationCount) {
			customReport.customizedReport(apiunreadnotificationCount,uiunreadnotificationCount, statusValue, driver, sTestcaseName);
			logger.info("@@@API Count is" +apiunreadnotificationCount+ "@@@and UI Count is" +uiunreadnotificationCount);
		}
		else {
		customReport.customizedReport(apiunreadnotificationCount,uiunreadnotificationCount, statusValue, driver, sTestcaseName);
		}
		customReport.checkinglist(statusValue);	
	}
	
	@Test(description = " To Get All Notifications by MediaAdmin",groups = {GETNOTIFICATIONS})
	public void TC_11_GET_All_Notifications_MediaAdmin() throws InterruptedException { //Set uploader as MediaAdmin to get notification for the respective user
		  
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
		adduploadvideobean.setUploader(IUsersList.GetNotifications_MediaAdmin);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.GetNotifications_MediaAdmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		Thread.sleep(100000);
		GetNotificationsAPI GetNotificationsAPIresponse = new GetNotificationsAPI();
		HashMap<String, String> getunreadNotificationsapiresponse = GetNotificationsAPIresponse.getAllNotifications(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		String fromattedJSONforuser=apiutils.formatingapiresponse(getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> apiunreadnotificationList = new ArrayList<String>(Arrays.asList(fromattedJSONforuser.split(",")));
		Collections.sort(apiunreadnotificationList);
		int apiunreadnotificationCount = apiunreadnotificationList.size();
		logger.info("API Notification list count is " +apiunreadnotificationCount);
		
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage = loginPage.loginAs(IUsersList.GetNotifications_MediaAdmin, sPassword);	
		avengerHomePage.click_notificationbutton();
		avengerHomePage.click_markallasread();
		avengerHomePage.click_notificationbutton();
		ArrayList<String> uiunreadnotificationList = avengerHomePage.get_AllNotifications();
		Collections.sort(uiunreadnotificationList);
		int uiunreadnotificationCount = uiunreadnotificationList.size();
		logger.info("@@@UI User Entities list count is " +uiunreadnotificationCount);
		
		if (apiunreadnotificationCount == uiunreadnotificationCount) {
			customReport.customizedReport(apiunreadnotificationCount,uiunreadnotificationCount, statusValue, driver, sTestcaseName);
			logger.info("@@@API Count is" +apiunreadnotificationCount+ "@@@and UI Count is" +uiunreadnotificationCount);
		}
		else {
		customReport.customizedReport(apiunreadnotificationCount,uiunreadnotificationCount, statusValue, driver, sTestcaseName);
		}
		customReport.checkinglist(statusValue);	
	}
	
	@Test(description = " To Get All Notifications by EventAdmin",groups = {GETNOTIFICATIONS})
	public void TC_12_GET_All_Notifications_EventAdmin() throws InterruptedException { //Set uploader as EventAdmin to get notification for the respective user
		  
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
		adduploadvideobean.setUploader(IUsersList.GetNotifications_MediaAdmin);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.GetNotifications_MediaAdmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		Thread.sleep(100000);
		GetNotificationsAPI GetNotificationsAPIresponse = new GetNotificationsAPI();
		HashMap<String, String> getunreadNotificationsapiresponse = GetNotificationsAPIresponse.getAllNotifications(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		String fromattedJSONforuser=apiutils.formatingapiresponse(getunreadNotificationsapiresponse.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> apiunreadnotificationList = new ArrayList<String>(Arrays.asList(fromattedJSONforuser.split(",")));
		Collections.sort(apiunreadnotificationList);
		int apiunreadnotificationCount = apiunreadnotificationList.size();
		logger.info("API Notification list count is " +apiunreadnotificationCount);
		
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage = loginPage.loginAs(IUsersList.GetNotifications_MediaAdmin, sPassword);	
		avengerHomePage.click_notificationbutton();
		avengerHomePage.click_markallasread();
		avengerHomePage.click_notificationbutton();
		ArrayList<String> uiunreadnotificationList = avengerHomePage.get_AllNotifications();
		Collections.sort(uiunreadnotificationList);
		int uiunreadnotificationCount = uiunreadnotificationList.size();
		logger.info("@@@UI User Entities list count is " +uiunreadnotificationCount);
		
		if (apiunreadnotificationCount == uiunreadnotificationCount) {
			customReport.customizedReport(apiunreadnotificationCount,uiunreadnotificationCount, statusValue, driver, sTestcaseName);
			logger.info("@@@API Count is" +apiunreadnotificationCount+ "@@@and UI Count is" +uiunreadnotificationCount);
		}
		else {
		customReport.customizedReport(apiunreadnotificationCount,uiunreadnotificationCount, statusValue, driver, sTestcaseName);
		}
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
