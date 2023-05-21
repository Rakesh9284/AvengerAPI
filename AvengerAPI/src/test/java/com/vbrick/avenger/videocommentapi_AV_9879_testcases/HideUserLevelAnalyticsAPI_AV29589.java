package com.vbrick.avenger.videocommentapi_AV_9879_testcases;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

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
import com.vbricks.avenger.serviceimpl.AddGuestRegistrationAPI;
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

public class HideUserLevelAnalyticsAPI_AV29589 extends TestBase {
	/*
	 * If any test case you want to add please make sure to change the priority of this method disableHideUserlevelUI()
	 * 
	 */
	
	private static Logger logger = Logger.getLogger(HideUserLevelAnalyticsAPI_AV29589.class);
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
	private AddNewDmeBeanPage addnewdmedevicebeanpage;
    public AddVideoCommentBean addvideocommentbean;
	private AssertionAPIResponse assertionapiresponse;
	private AvengerGroupsPage avengergroupspage;
	private AvengerGroupsBeanPage avengergroupsbeanpage;
	private HashMap<String, String> createcategoryapirespone;
	private HashMap<String, String> childcategoryapirespone;
	private AvengerDevicesPage avengerdevicespage;
	private AvengerEventDetailsPage avengereventdetailspage;
	private AvengerPresentationProfilesPage avengerpresentationprofilespage;
	private AvengerAddCustomDevicePage avengeraddcustomdevicepage;
	private AvengerAddPresentationProfilePage avengeraddpresentationprofilepage;
	private AddPresentationprofileBeanPage addpresentationprofilebeanpage;
    private HashMap<String, String> eventlistapirespone;
	private ApiUtils apiutils; 
	private AddCustomDeviceBeanPage addcustomdevicebeanpage;
	private AvengerHomePage avengerHomePage;
	private CreateEventBean createEventBean;
	private CreateEmbeddedContentBean createembeddedcontentbean;
	private CreateEmbeddedContentBean createembeddedcontentbean1;
	private AvengerEventWebCastPage avengereventwebcastpage;
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
		createEventBean = new CreateEventBean();
		createembeddedcontentbean=new CreateEmbeddedContentBean();
		createembeddedcontentbean1=new CreateEmbeddedContentBean();
		avengergroupsbeanpage = new AvengerGroupsBeanPage();
		apibeanfactory = new ApiBeanFactory();
		addvideocommentbean=new AddVideoCommentBean();
		assertionapiresponse =new AssertionAPIResponse();
		apiutils=new ApiUtils();
		addpresentationprofilebeanpage = new AddPresentationprofileBeanPage();
		addnewdmedevicebeanpage = new AddNewDmeBeanPage();
		addcustomdevicebeanpage = new AddCustomDeviceBeanPage();
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
		avengereventwebcastpage=new AvengerEventWebCastPage(driver,customReport,basePage);
		driver.manage().window().maximize();
		accountBeansFactory.MailinatorBean(mailinatorbeanpage);
		accountBeansFactory.AvengergroupsBean(avengergroupsbeanpage);
		accountBeansFactory.createPresentationProfile(addpresentationprofilebeanpage);
		accountBeansFactory.AddNewDmeBean(addnewdmedevicebeanpage);
		accountBeansFactory.addCustomDeviceBean(addcustomdevicebeanpage);
	}
	
	@Test(description=" To Verify Get Video Report using get video report api login as account admin check wheather hide user level analytics is enable  in UI",groups = {GETVideoReport})
	public void TC01_GET_VideoReport_API_HideUserLevelAnalytics_Negative_AV29589 (ITestContext context)
	{
		enableHideUserlevelUI();	 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
	    loginapiresponse = userservices.doLoginUpdate(apiutils.userJson(sUserName), surl);
		System.out.println("response code is"+loginapiresponse.get("Statuscode"));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::"+loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
	
       
		HidingLevelAnalyticsApi hidinglevelapiresponse=new HidingLevelAnalyticsApi();
	    ArrayList<String> getvideoreportapiresponse=hidinglevelapiresponse.GetVideoReportinvalid(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(getvideoreportapiresponse.get(0).toString(), HttpStatusCode.httpsStatus401);
	    customReport.customizedReport(HttpStatusCode.httpsStatus401, getvideoreportapiresponse.get(0).toString(),statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue); 
	}
		
	@Test(description="To Verify Get Video Watch Report using get video watch report api login as account admin check wheather hide user level analytics is enable  in UI ",groups = {GetVideoWatchReport})
	public void TC02_GET_VideoWatchReport_API_HideUserLevelAnalytics_Negative_AV29589 (ITestContext context)
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
		loginapiresponse.put(IAPIConstantCodes.APIVIDEOID,uploadvidoeapiresponse.get(IAPIConstantCodes.APIVIDEOID));
		loginapiresponse.put(IAPIConstantCodes.APIResponseUSERID, loginapiresponse.get("id"));
		
	    UserVideoCompletionAPI userVideoCompletionAPI = new UserVideoCompletionAPI();
        HashMap<String, String> videoCompletation = userVideoCompletionAPI.videoCompletion(loginapiresponse);
        assertionapiresponse.verifyAssert_httpCode(videoCompletation.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
        customReport.customizedReport(HttpStatusCode.httpsStatus401, videoCompletation.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);             
        customReport.checkinglist(statusValue);
 		
	}
		
	@Test(description=" To Verify Get Users By Login Date using Get Users By Login Date api login as account admin check wheather hide user level analytics is enable  in UI ",groups = {GetUsersByLoginDate})
	public void TC03_GET_UsersByLoginDate_API_HideUserLevelAnalytics_Negative_AV29589 (ITestContext context)
	{
		 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
	    loginapiresponse = userservices.doLoginUpdate(apiutils.userJson(sUserName), surl);
		System.out.println("response code is"+loginapiresponse.get("Statuscode"));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::"+loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
		loginapiresponse.put("sortfield", "Yes");
		loginapiresponse.put("sortorder", "No");
		loginapiresponse.put(IAPIConstantCodes.sortField, IAPIConstantCodes.LastLogin);
		
		
		HidingLevelAnalyticsApi hidinglevelapiresponse=new HidingLevelAnalyticsApi();
	    ArrayList<String> getvideoreportapiresponse=hidinglevelapiresponse.GetUsersByLoginDateInvalid(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(getvideoreportapiresponse.get(0).toString(), HttpStatusCode.httpsStatus401);
	    customReport.customizedReport(HttpStatusCode.httpsStatus401, getvideoreportapiresponse.get(0).toString(),statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);
		 
	}
	
	@Test(description="To Verify (Deprecated) Get Webcast Attendees Report using get (Deprecated) Get Webcast Attendees Report api login as account admin check wheather hide user level analytics is enable  in UI",groups = {GetWebcastAttendeesReportDeprecated})
	public void TC04_GET_WebcastAttendeesReport_Deprecated_API_HideUserLevelAnalytics_Negative_AV29589 (ITestContext context)
	{
		 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
	    loginapiresponse = userservices.doLoginUpdate(apiutils.userJson(sUserName), surl);
		System.out.println("response code is"+loginapiresponse.get("Statuscode"));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::"+loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
	
	
	    createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_10);
		apibeanfactory.EventBean(createEventBean);
		  
		//PresentationProfileId UI String
	    String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
		  
	    //EventAdminId String
		String  eventadminIdArray[]= {loginapiresponse.get("id").toString()};
	   createEventBean.setEventAdminId(eventadminIdArray);
		
	   //Create event
	   CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse, createEventBean);
	   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
	   customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
	   loginapiresponse.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
		
		
		HidingLevelAnalyticsApi hidinglevelapiresponse=new HidingLevelAnalyticsApi();
		ArrayList<String> getvideoreportapiresponse=hidinglevelapiresponse.GetWebcastAttendeesReportDeprecatedinvalid(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(getvideoreportapiresponse.get(0).toString(), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, getvideoreportapiresponse.get(0).toString(),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		    
	}
	
	 @Test(description="To Verify Get Webcast Attendees Report using get Webcast Attendees Report api  login as account admin check wheather hide user level analytics is enable  in UI ",groups = {GetWebcastAttendeesReport})
	 public void TC05_GET_WebcastAttendeesReport_API_HideUserLevelAnalytics_Positive_AV29589 (ITestContext context) throws Exception
	{
			 
	 logger.info("API Level Code is excuting");
	 sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

	   UserServices userservices = new UserServices();
	   loginapiresponse = userservices.doLoginUpdate(apiutils.userJson(sUserName), surl);
	   System.out.println("response code is"+loginapiresponse.get("Statuscode"));
	   assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
	   logger.info("Login API response Code :::"+loginapiresponse);
       customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
		
	   createEventBean.setDays(ICreateEventService.setDay_00);
       createEventBean.setHours(ICreateEventService.setMinutes_10);
       apibeanfactory.EventBean(createEventBean);
			  
	//PresentationProfileId UI String
       String presentationProfileId[]={create_presentationProfileUI()};
       createEventBean.setPresentationProfileId(presentationProfileId[0]);
			  
    //EventAdminId String
	   String  eventadminIdArray[]= {loginapiresponse.get("id").toString()};
       createEventBean.setEventAdminId(eventadminIdArray);
			
   //Create event
	  CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	  JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse, createEventBean);
	  assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
	  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
	  loginapiresponse.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
			

	//selenium
	   launchURL(surl);
	   customReport.reporter("Application launch with URL : ", surl);
	   homePage = loginPage.loginAs(sUserName, sPassword);
	   AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	   AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	   AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	   avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
	   avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	   AvengerEventWebCastPage avengereventwebcastpage=avengereventdetailspage.start_Webcast();    
	   avengereventwebcastpage.clickStartBroadCast();
	   avengereventwebcastpage.click_uablestartrecordingwebcast();
	   
	   GetEventStatusAPI eventstatusAPI= new GetEventStatusAPI();
	
	   //end webcast using api
	   HashMap<String, String> endeventapiresponse=eventstatusAPI.endevent(loginapiresponse);
	   assertionapiresponse.verifyAssert_httpCode(endeventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+endeventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	   customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, endeventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+endeventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	     
	    //queryparams
	    loginapiresponse.put("runnumber","No");
	    loginapiresponse.put("scrollid", "No");
	     
	    Thread.sleep(60000);
	   HidingLevelAnalyticsApi hidinglevelapiresponse=new HidingLevelAnalyticsApi();
	   JSONObject getvideoreportapiresponse=hidinglevelapiresponse.GetWebcastAttendeesReport(loginapiresponse);
	   assertionapiresponse.verifyAssert_httpCode(getvideoreportapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+getvideoreportapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	   customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, getvideoreportapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+getvideoreportapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	   //assert it with totals are returned
	   customReport.customizedReport(true, getvideoreportapiresponse.containsKey("totalSessions"), statusValue, driver, sTestcaseName);
	   customReport.customizedReport(true, getvideoreportapiresponse.containsKey("totalPublicCDNTime"), statusValue, driver, sTestcaseName);
	   customReport.customizedReport(true, getvideoreportapiresponse.containsKey("totalECDNTime"), statusValue, driver, sTestcaseName);
	   //totalSessions
	   customReport.customizedReport("1", getvideoreportapiresponse.get("totalSessions").toString(), statusValue, driver, sTestcaseName);
	   //hostCount
	   customReport.customizedReport("1", getvideoreportapiresponse.get("hostCount").toString(), statusValue, driver, sTestcaseName);
	   //attendeeCount
	   customReport.customizedReport("0", getvideoreportapiresponse.get("attendeeCount").toString(),statusValue, driver, sTestcaseName);
	   //assert  sessions array is not
	   customReport.customizedReport(false, getvideoreportapiresponse.containsKey("sessions"), statusValue, driver, sTestcaseName);
	   customReport.checkinglist(statusValue);
			 
		} 
	
	 @Test(description="To  Get Webcast Attendees realtime using get Webcast Attendees realtime api  ",groups = {GetWebcastAttendeesrealtime})
	 public void TC06_GET_WebcastAttendees_Realtime_API_HideUserLevelAnalytics_Positive_AV29589 (ITestContext context) throws Exception
	   {
			 
			logger.info("API Level Code is excuting");
			sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

			UserServices userservices = new UserServices();
		    loginapiresponse = userservices.doLoginUpdate(apiutils.userJson(sUserName), surl);
			System.out.println("response code is"+loginapiresponse.get("Statuscode"));
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
			logger.info("Login API response Code :::"+loginapiresponse);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
		
		
		    createEventBean.setDays(ICreateEventService.setDay_00);
			createEventBean.setHours(ICreateEventService.setMinutes_10);
			apibeanfactory.EventBean(createEventBean);
			  
			//PresentationProfileId UI String
		    String presentationProfileId[]={create_presentationProfileUI()};
			createEventBean.setPresentationProfileId(presentationProfileId[0]);
			  
		    //EventAdminId String
			String  eventadminIdArray[]= {loginapiresponse.get("id").toString()};
		   createEventBean.setEventAdminId(eventadminIdArray);
			
		   //Create event
		   CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse, createEventBean);
		   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		   customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		   loginapiresponse.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
			
		 //selenium
	   launchURL(surl);
	   customReport.reporter("Application launch with URL : ", surl);
	   homePage = loginPage.loginAs(sUserName, sPassword);
	   AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	   AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	   AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	   avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
	   avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	   AvengerEventWebCastPage avengereventwebcastpage=avengereventdetailspage.start_Webcast();    
	   avengereventwebcastpage.clickStartBroadCast();
	   avengereventwebcastpage.click_uablestartrecordingwebcast();
	
	    //start webcast  using api  
		GetEventStatusAPI eventstatusAPI=new GetEventStatusAPI();		
	    JSONObject  starteventapiresponse=eventstatusAPI.starteventjsonobject(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    
	    // eventstatusAPI.broadcasteventjsonobject(loginapiresponse);
	   
	     //Join web cast as attendes1 
	     AddGuestRegistrationAPI addguestregistraionapi=new AddGuestRegistrationAPI();
	     JSONObject guestregistrationapiresponse=addguestregistraionapi.guestregistrationjsonobject(loginapiresponse);
	     assertionapiresponse.verifyAssert_httpCode(guestregistrationapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+guestregistrationapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		 customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, guestregistrationapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+guestregistrationapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	  
		// attendes1();
		 
	    //bean
	    CreateRealTimeAtteendesBean createrealtimeatteendesbean=new CreateRealTimeAtteendesBean();
	    createrealtimeatteendesbean.setSortDirection(ICreateRealTimeAtteendes.sortDirection[0]);
	    createrealtimeatteendesbean.setSortField(ICreateRealTimeAtteendes.sortField[0]);
	    createrealtimeatteendesbean.setCount("50");
	    createrealtimeatteendesbean.setAttendeeDetails(ICreateRealTimeAtteendes.attendeeDetails[0]);
	    createrealtimeatteendesbean.setStatus(ICreateRealTimeAtteendes.status[1]);
	    
	
	    HidingLevelAnalyticsApi hidinglevelapiresponse=new HidingLevelAnalyticsApi();
		//1st time 
	    hidinglevelapiresponse.WebcastAttendeesrealtime(loginapiresponse,createrealtimeatteendesbean);
		Thread.sleep(60000);
	    
		//2nd time
		JSONObject getvideoreportapiresponse=hidinglevelapiresponse.WebcastAttendeesrealtime(loginapiresponse,createrealtimeatteendesbean);
		assertionapiresponse.verifyAssert_httpCode(getvideoreportapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+getvideoreportapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, getvideoreportapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+getvideoreportapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	   
		customReport.customizedReport(true, getvideoreportapiresponse.containsKey("moderatorCount"),statusValue, driver, sTestcaseName);
		customReport.customizedReport(true, getvideoreportapiresponse.containsKey("total"),statusValue, driver, sTestcaseName);
		customReport.customizedReport("active", getvideoreportapiresponse.get("status").toString(),statusValue, driver, sTestcaseName);
		customReport.customizedReport(false, getvideoreportapiresponse.containsKey("attendees"),statusValue, driver, sTestcaseName);
		
		//hostCount
		 customReport.customizedReport("1", getvideoreportapiresponse.get("hostCount").toString(), statusValue, driver, sTestcaseName);
		//total 
		customReport.customizedReport("1", getvideoreportapiresponse.get("total").toString(),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue); 
	   }	
		 
	 
	 
	 
	 
	 @Test(priority= 8)
	 public void disableHideUserlevelUI() {
		   launchURL(surl);
		   customReport.reporter("Application launch with URL : ", surl);
		    homePage = loginPage.loginAs(sUserName, sPassword);
		    homePage.clickSettingsLink();
		    AvengerSystemSettingsPage avengersystemsettingpage=homePage.clickSystemSettingsLink();
		    AvengerContentRestrictionPage avengercontentrestrictionpage=avengersystemsettingpage.click_ContentRestrictionLinkLocator();
		    avengercontentrestrictionpage.uncheck_hidinglevelanalytics();
		    avengercontentrestrictionpage.click_save();
		    homePage.click_logout();     
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
	
	 
	
	public String create_presentationProfileUI(){
	  	launchURL(surl);
    	homePage = loginPage.loginAs(sUserName, sPassword);
    	homePage.clickSettingsLink();
    	avengerdevicespage =  homePage.clickAllDevicesLink();
    	avengerdevicespage.click_AddDevice();
    	avengeraddcustomdevicepage = avengerdevicespage.click_CustomDevice();
    	avengeraddcustomdevicepage.createNewCustomDevice(addcustomdevicebeanpage);
    	avengeraddcustomdevicepage.click_CreateButton(addcustomdevicebeanpage);
    	avengerpresentationprofilespage= homePage.click_PresentationProfilesTab();
    	avengeraddpresentationprofilepage = avengerpresentationprofilespage.click_AddPresentationprofile();
    	addpresentationprofilebeanpage.setVideosource(addcustomdevicebeanpage.getDevicename());
    	addpresentationprofilebeanpage.setDestinationdevicename(addcustomdevicebeanpage.getDevicename());
    	avengeraddpresentationprofilepage.addPresentationProfile(addpresentationprofilebeanpage);
    	avengeraddpresentationprofilepage.click_createButton();
    		 
    	avengerpresentationprofilespage.click_PresentationProfile(addpresentationprofilebeanpage);
    	String url=homePage.getCurrentURL();
		logger.info("url.split -"+url.split("/")[8]);
	 	//browserQuit();
	 	homePage.click_logout();
		return url.split("/")[8];
	
	}
	 
@Parameters(value = { "sbrowser", "sgrid" })
public void attendes1() throws InterruptedException {
	customReport.reset();
    driver=initializeDriver(sbrowser);
	logger.info("The driver value is " + driver);
	bundle = ResourceBundle.getBundle("ResourceBundle.BundleFile", locale);
	logger.info("value in bundle is" + bundle.getKeys());
	basePage = new BasePage(driver, customReport, new BasePage());
	loginPage = basePage.avengerLoginPage(driver, customReport, basePage);
	driver.manage().window().maximize();
	
    launchURL(surl);
    customReport.reporter("Application launch with URL : ", surl);
    homePage = loginPage.loginAs(IUsersList.Get_Mediaviewer, sPassword);
    AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
    AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
    AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
    avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
    avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
    Thread.sleep(5000);
    
    
}
public void enableHideUserlevelUI() {
    launchURL(surl);
    customReport.reporter("Application launch with URL : ", surl);
    homePage = loginPage.loginAs(sUserName, sPassword);
    homePage.clickSettingsLink();
    AvengerSystemSettingsPage avengersystemsettingpage=homePage.clickSystemSettingsLink();
    AvengerContentRestrictionPage avengercontentrestrictionpage=avengersystemsettingpage.click_ContentRestrictionLinkLocator();
    avengercontentrestrictionpage.check_hidinglevelanalytics();
    avengercontentrestrictionpage.click_save();
    homePage.click_logout();
   }


}	
	
