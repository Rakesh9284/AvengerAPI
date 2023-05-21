package com.vbrick.avenger.videocommentapi_AV_9879_testcases;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
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
import com.vbrick.avenger.apibeans.CreateEmbeddedContentBean;
import com.vbrick.avenger.apibeans.CreateEventBean;
import com.vbrick.avenger.apibeans.CreateRealTimeAtteendesBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AddCustomDeviceBeanPage;
import com.vbrick.avenger.dao.AddNewDmeBeanPage;
import com.vbrick.avenger.dao.AddPresentationprofileBeanPage;
import com.vbrick.avenger.dao.AvengerEventDetailsBeanPage;
import com.vbrick.avenger.dao.AvengerGroupsBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.DateTime;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerAddCustomDevicePage;
import com.vbrick.avenger.pageobjects.AvengerAddPresentationProfilePage;
import com.vbrick.avenger.pageobjects.AvengerContentRestrictionPage;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerDevicesPage;
import com.vbrick.avenger.pageobjects.AvengerEditVideoSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerEventDetailsPage;
import com.vbrick.avenger.pageobjects.AvengerEventWebCastPage;
import com.vbrick.avenger.pageobjects.AvengerEventsPage;
import com.vbrick.avenger.pageobjects.AvengerGroupsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerPresentationProfilesPage;
import com.vbrick.avenger.pageobjects.AvengerSystemSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbrick.avenger_contentLinks.DeleteEmbeddedContent_AV28828;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.ICreateEmbeddedContentService;
import com.vbricks.avenger.service.ICreateEventService;
import com.vbricks.avenger.service.ICreateRealTimeAtteendes;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.CreateEventsAPI;
import com.vbricks.avenger.serviceimpl.EmbeddedContentAPI;
import com.vbricks.avenger.serviceimpl.GetEventStatusAPI;
import com.vbricks.avenger.serviceimpl.GetVideoReportAPI;
import com.vbricks.avenger.serviceimpl.HidingLevelAnalyticsApi;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.serviceimpl.UserVideoCompletionAPI;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class GetVideoReportApi extends TestBase {
	
	
	private static Logger logger = Logger.getLogger(GetVideoReportApi.class);
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
	private JSONObject loginapiresponse;
	public ApiBeanFactory apibeanfactory;
	private AssertionAPIResponse assertionapiresponse;
	private AvengerAddCustomDevicePage avengeraddcustomdevicepage;
	private AddPresentationprofileBeanPage addpresentationprofilebeanpage;
    private HashMap<String, String> eventlistapirespone;
	private ApiUtils apiutils; 
	private AvengerHomePage avengerHomePage;
	private HashMap<String, String> uploadvidoeapiresponse;
	private AddUploadVideoBean adduploadvideobean;
	
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
		apibeanfactory = new ApiBeanFactory(); 
		assertionapiresponse =new AssertionAPIResponse();
		apiutils=new ApiUtils();
		adduploadvideobean = new AddUploadVideoBean();
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

		/*@Test(description="To verify video report using get video report api of a video which is completely played without adding any query params in get video report api ",groups = {GETVideoReport})
		public void TC01_GET_VideoReport_API_WhenCompletevideo_IsPlayed_withoutQueryparams_Positive (ITestContext context) throws Exception
		{
			 
			logger.info("API Level Code is excuting");
			sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

			UserServices userservices = new UserServices();
		    loginapiresponse = userservices.doLoginUpdate(apiutils.userJson(sUserName), surl);
			System.out.println("response code is"+loginapiresponse.get("Statuscode"));
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
			logger.info("Login API response Code :::"+loginapiresponse);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
		
			UploadVideoService uploadvidoeservice = new UploadVideoService();
			apibeanfactory.UploadVideoBean(adduploadvideobean);
			adduploadvideobean.setUploader(sUserName);
			loginapiresponse.put("Mandatory","Yes");
			loginapiresponse.put("fileName", "Yes");
					
			uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
			logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			 
			 //Selenium Code
	        launchURL(surl);       
	        customReport.reporter("Application launch with URL : ", surl);
	        homePage = loginPage.loginAs(sUserName, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
	        AvengerVideoCommentsPage avengervideovommentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
	        avengervideovommentspage.click_settingsLink();
	        avengervideovommentspage.click_details();
	        AvengerEditVideoSettingsPage avengervideoeditseetingpage=avengervideovommentspage.clickBasic_Settings();
	        avengervideoeditseetingpage.getPublishDate1();
	        avengervideoeditseetingpage.clickSave();
	        avengervideoeditseetingpage.click_OkButton();
	        customReport.customizedReport(true, avengervideovommentspage.getVideoButton().contains("play"), statusValue, driver, sTestcaseName);
	        avengervideovommentspage.clickPlayButton();
	        customReport.customizedReport(false, avengervideovommentspage.getVideoStatus().contains("is-not-playing"), statusValue, driver, sTestcaseName);
	        Thread.sleep(30000);
	        customReport.customizedReport(false, avengervideovommentspage.getVideoButton().contains("pause"), statusValue, driver, sTestcaseName);
	        avengervideovommentspage.refreshBrowser();
	        Thread.sleep(90000);
			
			String videoTitle=adduploadvideobean.getTitle();
			
			//quryparams
			loginapiresponse.put("videoIds", "No");
			loginapiresponse.put("after", "No");
			loginapiresponse.put("before", "No");
					
		   GetVideoReportAPI videoreportapiresponse=new GetVideoReportAPI();
		   JSONObject getvideoreportapiresponse=videoreportapiresponse.GetVideoReport(loginapiresponse,videoTitle);
		   assertionapiresponse.verifyAssert_httpCode(getvideoreportapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString(), HttpStatusCode.httpsStatusCode200);
		    customReport.customizedReport(HttpStatusCode.httpsStatusCode200,getvideoreportapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString(),statusValue, driver, sTestcaseName);
		    
		    //
		    customReport.customizedReport(getvideoreportapiresponse.get(IAPIConstantCodes.APIVIDEOID).toString(),uploadvidoeapiresponse.get(IAPIConstantCodes.APIVIDEOID).toString(),statusValue, driver, sTestcaseName);
		    customReport.checkinglist(statusValue); 
		   
		    
		} 
		@Test(description="To verify video report using get video report api of a video when complete video is not played and  without adding any query params in get video report api",groups = {GETVideoReport})
		public void TC02_GET_VideoReport_API_WhenComplete_VideoIs_NotPlayed__withoutQueryparams_Positive(ITestContext context) throws Exception
		{
			 
			logger.info("API Level Code is excuting");
			sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

			UserServices userservices = new UserServices();
		    loginapiresponse = userservices.doLoginUpdate(apiutils.userJson(sUserName), surl);
			System.out.println("response code is"+loginapiresponse.get("Statuscode"));
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
			logger.info("Login API response Code :::"+loginapiresponse);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
		
			UploadVideoService uploadvidoeservice = new UploadVideoService();
			apibeanfactory.UploadVideoBean(adduploadvideobean);
			adduploadvideobean.setUploader(sUserName);
			loginapiresponse.put("Mandatory","Yes");
			loginapiresponse.put("fileName", "Yes");
					
			uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
			logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			 
			 //Selenium Code
	        launchURL(surl);       
	        customReport.reporter("Application launch with URL : ", surl);
	        homePage = loginPage.loginAs(sUserName, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
	        AvengerVideoCommentsPage avengervideovommentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
	        avengervideovommentspage.click_settingsLink();
	        avengervideovommentspage.click_details();
	        AvengerEditVideoSettingsPage avengervideoeditseetingpage=avengervideovommentspage.clickBasic_Settings();
	        avengervideoeditseetingpage.getPublishDate1();
	        avengervideoeditseetingpage.clickSave();
	        avengervideoeditseetingpage.click_OkButton();
	        customReport.customizedReport(true, avengervideovommentspage.getVideoButton().contains("play"), statusValue, driver, sTestcaseName);
	        avengervideovommentspage.clickPlayButton();
	        Thread.sleep(10000);
	        avengervideovommentspage.clickPlayButton();//to pause a video
	        avengervideovommentspage.refreshBrowser();
	        Thread.sleep(90000);
			
			String videoTitle=adduploadvideobean.getTitle();
			
			//quryparams
			loginapiresponse.put("videoIds", "No");
			loginapiresponse.put("after", "No");
			loginapiresponse.put("before", "No");
					
		   GetVideoReportAPI videoreportapiresponse=new GetVideoReportAPI();
		   JSONObject getvideoreportapiresponse=videoreportapiresponse.GetVideoReport(loginapiresponse,videoTitle);
		   assertionapiresponse.verifyAssert_httpCode(getvideoreportapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString(), HttpStatusCode.httpsStatusCode200);
		    customReport.customizedReport(HttpStatusCode.httpsStatusCode200,getvideoreportapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString(),statusValue, driver, sTestcaseName);
		    //assert with video completed or not
		    customReport.customizedReport(getvideoreportapiresponse.get("completed").toString(),"false",statusValue, driver, sTestcaseName);
		    customReport.checkinglist(statusValue);     
		} 
		@Test(description="To verify video report using get video report api of a video when complete video is not played and  without adding any query params in get video report api with expected browser",groups = {GETVideoReport})
		public void TC03_GET_VideoReport_API_WhenComplete_VideoIs_NotPlayed__withoutQueryparams_withBrowser_Positive (ITestContext context) throws Exception
		{
			 
			logger.info("API Level Code is excuting");
			sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

			UserServices userservices = new UserServices();
		    loginapiresponse = userservices.doLoginUpdate(apiutils.userJson(sUserName), surl);
			System.out.println("response code is"+loginapiresponse.get("Statuscode"));
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
			logger.info("Login API response Code :::"+loginapiresponse);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
		
			UploadVideoService uploadvidoeservice = new UploadVideoService();
			apibeanfactory.UploadVideoBean(adduploadvideobean);
			adduploadvideobean.setUploader(sUserName);
			loginapiresponse.put("Mandatory","Yes");
			loginapiresponse.put("fileName", "Yes");
					
			uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
			logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			 
			 //Selenium Code
	        launchURL(surl);       
	        customReport.reporter("Application launch with URL : ", surl);
	        homePage = loginPage.loginAs(sUserName, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
	        AvengerVideoCommentsPage avengervideovommentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
	        avengervideovommentspage.click_settingsLink();
	        avengervideovommentspage.click_details();
	        AvengerEditVideoSettingsPage avengervideoeditseetingpage=avengervideovommentspage.clickBasic_Settings();
	        avengervideoeditseetingpage.getPublishDate1();
	        avengervideoeditseetingpage.clickSave();
	        avengervideoeditseetingpage.click_OkButton();
	        customReport.customizedReport(true, avengervideovommentspage.getVideoButton().contains("play"), statusValue, driver, sTestcaseName);
	        avengervideovommentspage.clickPlayButton();
	        Thread.sleep(10000);
	        avengervideovommentspage.clickPlayButton();//to pause a video
	        avengervideovommentspage.refreshBrowser();
	        Thread.sleep(90000);
			
			String videoTitle=adduploadvideobean.getTitle();
			
			//quryparams
			loginapiresponse.put("videoIds", "No");
			loginapiresponse.put("after", "No");
			loginapiresponse.put("before", "No");
					
		   GetVideoReportAPI videoreportapiresponse=new GetVideoReportAPI();
		   JSONObject getvideoreportapiresponse=videoreportapiresponse.GetVideoReport(loginapiresponse,videoTitle);
		   assertionapiresponse.verifyAssert_httpCode(getvideoreportapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString(), HttpStatusCode.httpsStatusCode200);
		    customReport.customizedReport(HttpStatusCode.httpsStatusCode200,getvideoreportapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString(),statusValue, driver, sTestcaseName);
		    //assert with expected browser and userDevicetype
		    customReport.customizedReport(getvideoreportapiresponse.get("browser").toString(),"Chrome",statusValue, driver, sTestcaseName);
		    customReport.customizedReport(getvideoreportapiresponse.get("userDeviceType").toString(),"PC",statusValue, driver, sTestcaseName); 
		    customReport.checkinglist(statusValue);     
		} 
	
		@Test(description="To verify video report using get video report api of a video when complete video is not played and  without adding any query params in get video report api with Date viewed",groups = {GETVideoReport})
		public void TC04_GET_VideoReport_API_WhenComplete_VideoIs_NotPlayed__withoutQueryparams_withdateViewed_Positive (ITestContext context) throws Exception 
		{
			 
			logger.info("API Level Code is excuting");
			sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

			UserServices userservices = new UserServices();
		    loginapiresponse = userservices.doLoginUpdate(apiutils.userJson(sUserName), surl);
			System.out.println("response code is"+loginapiresponse.get("Statuscode"));
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
			logger.info("Login API response Code :::"+loginapiresponse);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
		
			UploadVideoService uploadvidoeservice = new UploadVideoService();
			apibeanfactory.UploadVideoBean(adduploadvideobean);
			adduploadvideobean.setUploader(sUserName);
			loginapiresponse.put("Mandatory","Yes");
			loginapiresponse.put("fileName", "Yes");
					
			uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
			logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			 
			 //Selenium Code
	        launchURL(surl);       
	        customReport.reporter("Application launch with URL : ", surl);
	        homePage = loginPage.loginAs(sUserName, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
	        AvengerVideoCommentsPage avengervideovommentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
	        avengervideovommentspage.click_settingsLink();
	        avengervideovommentspage.click_details();
	        AvengerEditVideoSettingsPage avengervideoeditseetingpage=avengervideovommentspage.clickBasic_Settings();
	        avengervideoeditseetingpage.getPublishDate1();
	        avengervideoeditseetingpage.clickSave();
	        avengervideoeditseetingpage.click_OkButton();
	        customReport.customizedReport(true, avengervideovommentspage.getVideoButton().contains("play"), statusValue, driver, sTestcaseName);
	        avengervideovommentspage.clickPlayButton();
	        Thread.sleep(10000);
	        avengervideovommentspage.clickPlayButton();//to pause a video
	        avengervideovommentspage.refreshBrowser();
	        Thread.sleep(90000);
			
			String videoTitle=adduploadvideobean.getTitle();
			
			//quryparams
			loginapiresponse.put("videoIds", "No");
			loginapiresponse.put("after", "No");
			loginapiresponse.put("before", "No");
					
		   GetVideoReportAPI videoreportapiresponse=new GetVideoReportAPI();
		   JSONObject getvideoreportapiresponse=videoreportapiresponse.GetVideoReport(loginapiresponse,videoTitle);
		   assertionapiresponse.verifyAssert_httpCode(getvideoreportapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString(), HttpStatusCode.httpsStatusCode200);
		    customReport.customizedReport(HttpStatusCode.httpsStatusCode200,getvideoreportapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString(),statusValue, driver, sTestcaseName);
		    //assert with expected browser and userDevicetype
		   // System.out.println("GMT TIM :::::::"+getGMTTime());
		    customReport.customizedReport(true,getvideoreportapiresponse.get("dateViewed").toString().contains(DateTime.getMMDDYYYY()),statusValue, driver, sTestcaseName);
		    customReport.checkinglist(statusValue);     
		    
		} 
		
		@Test(description="To verify video report upload video as account admin and watch video as media viewer and get video report api as account admin of a video which is completely played without adding any query params in get video report api ",groups = {GETVideoReport})
		public void TC05_GET_VideoReport_API_WatchVideoAs_MediaViewer_WhenCompletevideo_IsPlayed_withoutQueryparams_Positive (ITestContext context) throws Exception
		{
			 
			logger.info("API Level Code is excuting");
			sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

			UserServices userservices = new UserServices();
		    loginapiresponse = userservices.doLoginUpdate(apiutils.userJson(sUserName), surl);
			System.out.println("response code is"+loginapiresponse.get("Statuscode"));
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
			logger.info("Login API response Code :::"+loginapiresponse);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
		
			UploadVideoService uploadvidoeservice = new UploadVideoService();
			apibeanfactory.UploadVideoBean(adduploadvideobean);
			adduploadvideobean.setUploader(sUserName);
			loginapiresponse.put("Mandatory","Yes");
			loginapiresponse.put("fileName", "Yes");
					
			uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
			logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			 
			//Media viewer
			JSONObject loginapiresponse1 = userservices.doLoginUpdate(apiutils.userJson(IUsersList.Get_Mediaviewer), surl);
			 System.out.println("response code is"+loginapiresponse1.get("Statuscode"));
			 assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
			 logger.info("Login API response Code :::"+loginapiresponse1);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
			
			
			 //Selenium Code
	        launchURL(surl);       
	        customReport.reporter("Application launch with URL : ", surl);
	        homePage = loginPage.loginAs(IUsersList.Get_Mediaviewer, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
	        AvengerVideoCommentsPage avengervideovommentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
	        customReport.customizedReport(true, avengervideovommentspage.getVideoButton().contains("play"), statusValue, driver, sTestcaseName);
	        avengervideovommentspage.clickPlayButton();
	        customReport.customizedReport(false, avengervideovommentspage.getVideoStatus().contains("is-not-playing"), statusValue, driver, sTestcaseName);
	        Thread.sleep(30000);
	        customReport.customizedReport(false, avengervideovommentspage.getVideoButton().contains("pause"), statusValue, driver, sTestcaseName);
	        avengervideovommentspage.refreshBrowser();
	        Thread.sleep(90000);
			
			String videoTitle=adduploadvideobean.getTitle();
			
			//quryparams
			loginapiresponse.put("videoIds", "No");
			loginapiresponse.put("after", "No");
			loginapiresponse.put("before", "No");
					
		   GetVideoReportAPI videoreportapiresponse=new GetVideoReportAPI();
		   JSONObject getvideoreportapiresponse=videoreportapiresponse.GetVideoReport(loginapiresponse,videoTitle);
		   assertionapiresponse.verifyAssert_httpCode(getvideoreportapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString(), HttpStatusCode.httpsStatusCode200);
		    customReport.customizedReport(HttpStatusCode.httpsStatusCode200,getvideoreportapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString(),statusValue, driver, sTestcaseName);
		    //
		    customReport.customizedReport(getvideoreportapiresponse.get("username").toString(),loginapiresponse1.get("username").toString(),statusValue, driver, sTestcaseName);
		    customReport.customizedReport(getvideoreportapiresponse.get(IAPIConstantCodes.APIResponseFirstName).toString(),loginapiresponse1.get(IAPIConstantCodes.APIResponseFirstName).toString(),statusValue, driver, sTestcaseName);
		    customReport.customizedReport(getvideoreportapiresponse.get(IAPIConstantCodes.APIResponseLastName).toString(),loginapiresponse1.get(IAPIConstantCodes.APIResponseLastName).toString(),statusValue, driver, sTestcaseName);
		    customReport.customizedReport(getvideoreportapiresponse.get("emailAddress").toString(),loginapiresponse1.get(IAPIConstantCodes.APIResponseEMAIL).toString(),statusValue, driver, sTestcaseName);
		    customReport.checkinglist(statusValue); 
		} 
	
		
		@Test(description="To verify video report upload video as account admin and watch video as media viewer and get video report api as mediaviewer of a video ",groups = {GETVideoReport})
		public void TC06_GET_VideoReport_API_As_MediaViewer_withoutQueryparams_Negative (ITestContext context) throws Exception
		{
			 
			logger.info("API Level Code is excuting");
			sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

			UserServices userservices = new UserServices();
		    loginapiresponse = userservices.doLoginUpdate(apiutils.userJson(sUserName), surl);
			System.out.println("response code is"+loginapiresponse.get("Statuscode"));
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
			logger.info("Login API response Code :::"+loginapiresponse);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
		
			UploadVideoService uploadvidoeservice = new UploadVideoService();
			apibeanfactory.UploadVideoBean(adduploadvideobean);
			adduploadvideobean.setUploader(sUserName);
			loginapiresponse.put("Mandatory","Yes");
			loginapiresponse.put("fileName", "Yes");
					
			uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
			logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			 
			//login as media Viewer
			JSONObject loginapiresponse1 = userservices.doLoginUpdate(apiutils.userJson(IUsersList.Get_Mediaviewer), surl);
			 System.out.println("response code is"+loginapiresponse1.get("Statuscode"));
			 assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
			 logger.info("Login API response Code :::"+loginapiresponse1);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
				
			String videoTitle=adduploadvideobean.getTitle();
			
			//quryparams
			loginapiresponse.put("videoIds", "No");
			loginapiresponse.put("after", "No");
			loginapiresponse.put("before", "No");
			 
			
			HidingLevelAnalyticsApi hidinglevelapiresponse=new HidingLevelAnalyticsApi();
		    ArrayList<String> getvideoreportapiresponse=hidinglevelapiresponse.GetVideoReportinvalid(loginapiresponse1);
		    assertionapiresponse.verifyAssert_httpCode(getvideoreportapiresponse.get(0).toString(), HttpStatusCode.httpsStatus401);
		    customReport.customizedReport(HttpStatusCode.httpsStatus401, getvideoreportapiresponse.get(0).toString(),statusValue, driver, sTestcaseName);
		    customReport.checkinglist(statusValue); 
		} 
		
		@Test(description="To verify video report using get video report api of a video which is completely played with query params 'videoIds' in get video report api ",groups = {GETVideoReport})
		public void TC07_GET_VideoReport_API_WhenCompletevideo_IsPlayed_withQueryparams_videoIds_Positive (ITestContext context) throws Exception
		{
			 
			logger.info("API Level Code is excuting");
			sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

			UserServices userservices = new UserServices();
		    loginapiresponse = userservices.doLoginUpdate(apiutils.userJson(sUserName), surl);
			System.out.println("response code is"+loginapiresponse.get("Statuscode"));
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
			logger.info("Login API response Code :::"+loginapiresponse);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
		
			UploadVideoService uploadvidoeservice = new UploadVideoService();
			apibeanfactory.UploadVideoBean(adduploadvideobean);
			adduploadvideobean.setUploader(sUserName);
			loginapiresponse.put("Mandatory","Yes");
			loginapiresponse.put("fileName", "Yes");
					
			uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
			logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			 
			 //Selenium Code
	        launchURL(surl);       
	        customReport.reporter("Application launch with URL : ", surl);
	        homePage = loginPage.loginAs(sUserName, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
	        AvengerVideoCommentsPage avengervideovommentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
	        avengervideovommentspage.click_settingsLink();
	        avengervideovommentspage.click_details();
	        AvengerEditVideoSettingsPage avengervideoeditseetingpage=avengervideovommentspage.clickBasic_Settings();
	        avengervideoeditseetingpage.getPublishDate1();
	        avengervideoeditseetingpage.clickSave();
	        avengervideoeditseetingpage.click_OkButton();
	        customReport.customizedReport(true, avengervideovommentspage.getVideoButton().contains("play"), statusValue, driver, sTestcaseName);
	        avengervideovommentspage.clickPlayButton();
	        customReport.customizedReport(false, avengervideovommentspage.getVideoStatus().contains("is-not-playing"), statusValue, driver, sTestcaseName);
	        Thread.sleep(30000);
	        customReport.customizedReport(false, avengervideovommentspage.getVideoButton().contains("pause"), statusValue, driver, sTestcaseName);
	        avengervideovommentspage.refreshBrowser();
	        Thread.sleep(90000);
			
			String videoTitle=adduploadvideobean.getTitle();
			
			//quryparams
			loginapiresponse.put("videoIds","Yes");
			loginapiresponse.put(IAPIConstantCodes.APIVIDEOID,uploadvidoeapiresponse.get(IAPIConstantCodes.APIVIDEOID));
			
			loginapiresponse.put("after", "No");
			loginapiresponse.put("before", "No");
					
		   GetVideoReportAPI videoreportapiresponse=new GetVideoReportAPI();
		   JSONObject getvideoreportapiresponse=videoreportapiresponse.GetVideoReport(loginapiresponse,videoTitle);
		   assertionapiresponse.verifyAssert_httpCode(getvideoreportapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString(), HttpStatusCode.httpsStatusCode200);
		    customReport.customizedReport(HttpStatusCode.httpsStatusCode200,getvideoreportapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString(),statusValue, driver, sTestcaseName);
		    
		    //
		    customReport.customizedReport(getvideoreportapiresponse.get(IAPIConstantCodes.APIVIDEOID).toString(),uploadvidoeapiresponse.get(IAPIConstantCodes.APIVIDEOID).toString(),statusValue, driver, sTestcaseName);
		    customReport.checkinglist(statusValue);   
		}
		
		@Test(description="To verify video report using get video report api of a video which is completely played with query params 'videoIds' in get video report api and assert with viewing time ",groups = {GETVideoReport})
		public void TC08_GET_VideoReport_API_WhenCompletevideo_IsPlayed_withQueryparams_videoIds_assertwithviewingtime_Positive (ITestContext context) throws Exception
		{
			 
			logger.info("API Level Code is excuting");
			sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

			UserServices userservices = new UserServices();
		    loginapiresponse = userservices.doLoginUpdate(apiutils.userJson(sUserName), surl);
			System.out.println("response code is"+loginapiresponse.get("Statuscode"));
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
			logger.info("Login API response Code :::"+loginapiresponse);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
		
			UploadVideoService uploadvidoeservice = new UploadVideoService();
			apibeanfactory.UploadVideoBean(adduploadvideobean);
			adduploadvideobean.setUploader(sUserName);
			loginapiresponse.put("Mandatory","Yes");
			loginapiresponse.put("fileName", "Yes");
					
			uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
			logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			 
			//Selenium Code
	        launchURL(surl);       
	        customReport.reporter("Application launch with URL : ", surl);
	        homePage = loginPage.loginAs(sUserName, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
	        AvengerVideoCommentsPage avengervideovommentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
	        avengervideovommentspage.click_settingsLink();
	        avengervideovommentspage.click_details();
	        AvengerEditVideoSettingsPage avengervideoeditseetingpage=avengervideovommentspage.clickBasic_Settings();
	        avengervideoeditseetingpage.getPublishDate1();
	        avengervideoeditseetingpage.clickSave();
	        avengervideoeditseetingpage.click_OkButton();
	        customReport.customizedReport(true, avengervideovommentspage.getVideoButton().contains("play"), statusValue, driver, sTestcaseName);
	        avengervideovommentspage.clickPlayButton();
	        customReport.customizedReport(false, avengervideovommentspage.getVideoStatus().contains("is-not-playing"), statusValue, driver, sTestcaseName);
	        Thread.sleep(30000);
	        customReport.customizedReport(false, avengervideovommentspage.getVideoButton().contains("pause"), statusValue, driver, sTestcaseName);
	        avengervideovommentspage.refreshBrowser();
	        Thread.sleep(90000);
			
			String videoTitle=adduploadvideobean.getTitle();
			
			//quryparams
			loginapiresponse.put("videoIds","Yes");
			loginapiresponse.put(IAPIConstantCodes.APIVIDEOID,uploadvidoeapiresponse.get(IAPIConstantCodes.APIVIDEOID));
			
			loginapiresponse.put("after", "No");
			loginapiresponse.put("before", "No");
					
		   GetVideoReportAPI videoreportapiresponse=new GetVideoReportAPI();
		   JSONObject getvideoreportapiresponse=videoreportapiresponse.GetVideoReport(loginapiresponse,videoTitle);
		   assertionapiresponse.verifyAssert_httpCode(getvideoreportapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString(), HttpStatusCode.httpsStatusCode200);
		    customReport.customizedReport(HttpStatusCode.httpsStatusCode200,getvideoreportapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString(),statusValue, driver, sTestcaseName);
		    //assert with viewing time
		    customReport.customizedReport(getvideoreportapiresponse.get("viewingTime").toString(),"00:00:27",statusValue, driver, sTestcaseName);
		    customReport.checkinglist(statusValue);   
		} 
		*/
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
	/*public static String getGMTTime(){
		 SimpleDateFormat gmtDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		 gmtDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

		 //Current Date Time in GMT
		 Date date = new Date();

	     date.setSeconds(date.getSeconds()- 102);
		 
		 String time= gmtDateFormat.format(date);
		 return time.replace("GMT","");
	}*/

}

	
	
