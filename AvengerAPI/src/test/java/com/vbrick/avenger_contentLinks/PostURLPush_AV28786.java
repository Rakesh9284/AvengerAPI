package com.vbrick.avenger_contentLinks;

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
import org.json.simple.parser.ParseException;
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
import com.vbrick.avenger.apibeans.AddVideoCommentBean;
import com.vbrick.avenger.apibeans.CreateEventBean;
import com.vbrick.avenger.apibeans.CreatePushContentBean;
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
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerDevicesPage;
import com.vbrick.avenger.pageobjects.AvengerEventDetailsPage;
import com.vbrick.avenger.pageobjects.AvengerEventWebCastPage;
import com.vbrick.avenger.pageobjects.AvengerEventsPage;
import com.vbrick.avenger.pageobjects.AvengerFeaturesPage;
import com.vbrick.avenger.pageobjects.AvengerGroupsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerMediaSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerPresentationProfilesPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;
import com.vbrick.avenger.videocommentapi_AV_9879_testcases.EventListApi_Av_11123;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.ICreateEventService;
import com.vbricks.avenger.service.ICreatePushContentService;
import com.vbricks.avenger.service.IUserAccountsService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.CreateEventsAPI;
import com.vbricks.avenger.serviceimpl.GetEventStatusAPI;
import com.vbricks.avenger.serviceimpl.PushContentAPI;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

  public class PostURLPush_AV28786 extends TestBase {
	private static Logger logger = Logger.getLogger(PostURLPush_AV28786.class);
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
	private HashMap<String, String> loginapiresponse;
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
	private CreatePushContentBean createpushcontentbean;
	private CreatePushContentBean createpushcontentbean1;
	private AvengerEventWebCastPage avengereventwebcastpage;
	
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
		createpushcontentbean=new CreatePushContentBean();
		createpushcontentbean1=new CreatePushContentBean();
		avengergroupsbeanpage = new AvengerGroupsBeanPage();
		apibeanfactory = new ApiBeanFactory();
		apibeanfactory = new ApiBeanFactory();
		addvideocommentbean=new AddVideoCommentBean();
		assertionapiresponse =new AssertionAPIResponse();
		apiutils=new ApiUtils();
		
		addpresentationprofilebeanpage = new AddPresentationprofileBeanPage();
		addnewdmedevicebeanpage = new AddNewDmeBeanPage();
		addcustomdevicebeanpage = new AddCustomDeviceBeanPage();		
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
		
	@Test(description="To Verify and create a new webcast and it should be filled with all the valid data and Send a Link / URL to all Attendees it should be enabled (Create a new Shared URL)",groups = {PostPushContentWebcast})
	public void TC01_Createanew_SharedURL_PostPushContentWebcast_withallvaliddata_Positve_AV28786(ITestContext context)
	{
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		JSONObject loginapiresponse1 = userservices.doLoginUpdate(apiutils.userJson(sUserName), surl);
		System.out.println("response code is"+loginapiresponse1.get("Statuscode"));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
		 logger.info("Login API response Code :::"+loginapiresponse1);
		 customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
	  
		  createEventBean.setDays(ICreateEventService.setDay_00);
		  createEventBean.setHours(ICreateEventService.setMinutes_10);
		  apibeanfactory.EventBean(createEventBean);
		  
		  //PresentationProfileId UI String
		  String presentationProfileId[]={create_presentationProfileUI()};
		  createEventBean.setPresentationProfileId(presentationProfileId[0]);
		  
		 //EventAdminId String
		   String  eventadminIdArray[]= {loginapiresponse1.get("id").toString()};
		   createEventBean.setEventAdminId(eventadminIdArray);
		  
		   //Create event
			CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
		   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		   customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
		   
		  apibeanfactory.PushContentbean(createpushcontentbean);
		  createpushcontentbean.setLink(IAPIConstantCodes.GOOGLEADDRESS);
		  createpushcontentbean.setPushMethod(ICreatePushContentService.pushMethod[0]);
		  createpushcontentbean.setIsEnabled(IAPIConstantCodes.TRUE);
		 
		  PushContentAPI pushcontentAPI=new PushContentAPI();
		  ArrayList<String> pushcontentAPIresponse=pushcontentAPI.PushContentWebCastAPI(loginapiresponse1,createpushcontentbean);
		  assertionapiresponse.verifyAssert_httpCode(pushcontentAPIresponse.get(1).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, pushcontentAPIresponse.get(1).toString(),statusValue, driver, sTestcaseName);
		  logger.info("@@@@Shared URL ID: "+pushcontentAPIresponse.get(0));  
		  
		   //selenium code 
		    launchURL(surl);
	        customReport.reporter("Application launch with URL : ", surl);
	        homePage = loginPage.loginAs(sUserName, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
	        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	       //assertion with name
	        customReport.customizedReport(createpushcontentbean.getName(), avengereventspage.verify_text(createpushcontentbean.getName()),statusValue, driver, sTestcaseName);
			customReport.checkinglist(statusValue);
       
	}

	
	@Test(description="To Verify and create a new webcast and it should be filled with all the valid data and Send a Link / URL to all Attendees it should be enabled  and pushmethod as ATend(Create a new Shared URL)",groups = {PostPushContentWebcast})
	public void TC02_Createanew_SharedURL_PostPushContentWebcast_withPushMethodAS_ATEND_Positve_AV28786(ITestContext context)
	{
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		JSONObject loginapiresponse1 = userservices.doLoginUpdate(apiutils.userJson(sUserName), surl);
		System.out.println("response code is"+loginapiresponse1.get("Statuscode"));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
		 logger.info("Login API response Code :::"+loginapiresponse1);
		 customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
	  
		  createEventBean.setDays(ICreateEventService.setDay_00);
		  createEventBean.setHours(ICreateEventService.setMinutes_10);
		 
		  apibeanfactory.EventBean(createEventBean);
		  
		  //PresentationProfileId UI String
 		  String presentationProfileId[]={create_presentationProfileUI()};
		  createEventBean.setPresentationProfileId(presentationProfileId[0]);
		  
		 //EventAdminId String
		   String  eventadminIdArray[]= {loginapiresponse1.get("id").toString()};
		   createEventBean.setEventAdminId(eventadminIdArray);
		  
		   //Create event
			CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
		   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		   customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
		    
		    apibeanfactory.PushContentbean(createpushcontentbean);
		  createpushcontentbean.setLink(IAPIConstantCodes.GOOGLEADDRESS);
		  createpushcontentbean.setPushMethod(ICreatePushContentService.pushMethod[1]);//as Atend
		  createpushcontentbean.setIsEnabled(IAPIConstantCodes.TRUE);
		 
		  PushContentAPI pushcontentAPI=new PushContentAPI();
		  ArrayList<String> pushcontentAPIresponse=pushcontentAPI.PushContentWebCastAPI(loginapiresponse1,createpushcontentbean);
		  assertionapiresponse.verifyAssert_httpCode(pushcontentAPIresponse.get(1).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, pushcontentAPIresponse.get(1).toString(),statusValue, driver, sTestcaseName);
		  logger.info("@@@@Shared URL ID: "+pushcontentAPIresponse.get(0));  
	        
		  //start webcast  using api  
		   GetEventStatusAPI starteventAPI=new GetEventStatusAPI();		
		   JSONObject  starteventapiresponse=starteventAPI.starteventjsonobject(loginapiresponse1);
		   assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		   customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		  
		//selenium code
		    launchURL(surl);
	        customReport.reporter("Application launch with URL : ", surl);
	        homePage = loginPage.loginAs(sUserName, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
	        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	        avengereventwebcastpage.click_sendalinkurltoallattendes();
	        customReport.customizedReport(createpushcontentbean.getName(), avengereventspage.verify_text(createpushcontentbean.getName()),statusValue, driver, sTestcaseName);   
	        avengereventwebcastpage.clickStartBroadCast();
	        avengereventwebcastpage.click_uablestartrecordingwebcast();
	        avengereventwebcastpage.click_endEvent();
	        customReport.customizedReport(createpushcontentbean.getLink(), avengereventspage.getlinkUI(),statusValue, driver, sTestcaseName);   
	        customReport.checkinglist(statusValue);
	       
	}
	
	@Test(description="To Verify and create a new webcast and it should be filled with all the valid data and pushcontent with all valid data and name as empty and link as empty  (Create a new Shared URL)",groups = {PostPushContentWebcast})
	public void TC03_Createanew_SharedURL_PostPushContentWebcast_withallvaliddata_nameasemptyandlinkasempty_Negative_AV28786(ITestContext context)
	{
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		JSONObject loginapiresponse1 = userservices.doLoginUpdate(apiutils.userJson(sUserName), surl);
		System.out.println("response code is"+loginapiresponse1.get("Statuscode"));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
		 logger.info("Login API response Code :::"+loginapiresponse1);
		 customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
	  
		  createEventBean.setDays(ICreateEventService.setDay_00);
		  createEventBean.setHours(ICreateEventService.setMinutes_10);
		  apibeanfactory.EventBean(createEventBean);
		  
		  //PresentationProfileId UI String
		  String presentationProfileId[]={create_presentationProfileUI()};
		  createEventBean.setPresentationProfileId(presentationProfileId[0]);
		  
		 //EventAdminId String
		   String  eventadminIdArray[]= {loginapiresponse1.get("id").toString()};
		   createEventBean.setEventAdminId(eventadminIdArray);
		  
		   //Create event
			CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
		   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		   customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		    loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
		   
		  //apibeanfactory.PushContentbean(createpushcontentbean);
		   createpushcontentbean.setName("");
		  createpushcontentbean.setLink("");
		  createpushcontentbean.setPushMethod(ICreatePushContentService.pushMethod[0]);
		  createpushcontentbean.setIsEnabled(IAPIConstantCodes.TRUE);
		 
		  PushContentAPI pushcontentAPI=new PushContentAPI();
		  ArrayList<String> pushcontentAPIresponse=pushcontentAPI.PushContentWebCastAPI(loginapiresponse1,createpushcontentbean);
		  assertionapiresponse.verifyAssert_httpCode(pushcontentAPIresponse.get(1).toString(), HttpStatusCode.httpsStatus500);  
		  customReport.customizedReport(HttpStatusCode.httpsStatus500, pushcontentAPIresponse.get(1).toString(),statusValue, driver, sTestcaseName);
		  logger.info("@@@@Shared URL ID: "+pushcontentAPIresponse.get(0));  
		  
		   //selenium code
		  launchURL(surl);
	        customReport.reporter("Application launch with URL : ", surl);
	        homePage = loginPage.loginAs(sUserName, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
	        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	        //it is scrolling down just to verify the push content
	        avengereventspage.scroll_embeddedcontent();	
	        
       
	}	
	
	@Test(description="To Verify and create a new webcast and it should be filled with all the valid data and Send a Link / URL to all Attendees if content link is enabled ",groups = {PostPushContentWebcast})
	public void TC04_Createanew_SharedURL_PostPushContentWebcast__urlPushEnabled_isenabled_AsTrue_Positive_AV28786(ITestContext context)
	{
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		JSONObject loginapiresponse1 = userservices.doLoginUpdate(apiutils.userJson(sUserName), surl);
		System.out.println("response code is"+loginapiresponse1.get("Statuscode"));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
		 logger.info("Login API response Code :::"+loginapiresponse1);
		 customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
	  
		  createEventBean.setDays(ICreateEventService.setDay_00);
		  createEventBean.setHours(ICreateEventService.setMinutes_10);
		  apibeanfactory.EventBean(createEventBean);
		  
		  //PresentationProfileId UI String
		  String presentationProfileId[]={create_presentationProfileUI()};
		  createEventBean.setPresentationProfileId(presentationProfileId[0]);
		  
		 //EventAdminId String
		   String  eventadminIdArray[]= {loginapiresponse1.get("id").toString()};
		   createEventBean.setEventAdminId(eventadminIdArray);
		  
		   //Create event
			CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
		   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		   customReport.customizedReport(HttpStatusCode.httpsStatusCode200,createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		    loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
		   
		  apibeanfactory.PushContentbean(createpushcontentbean);
		  createpushcontentbean.setLink(IAPIConstantCodes.GOOGLEADDRESS);
		  createpushcontentbean.setPushMethod(ICreatePushContentService.pushMethod[0]);
		  createpushcontentbean.setIsEnabled(IAPIConstantCodes.TRUE);
		 
		  PushContentAPI pushcontentAPI=new PushContentAPI();
		  ArrayList<String> pushcontentAPIresponse=pushcontentAPI.PushContentWebCastAPI(loginapiresponse1,createpushcontentbean);
		  assertionapiresponse.verifyAssert_httpCode(pushcontentAPIresponse.get(1).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, pushcontentAPIresponse.get(1).toString(),statusValue, driver, sTestcaseName);
		  
		  //selenium code 
		    launchURL(surl);
	        customReport.reporter("Application launch with URL : ", surl);
	        homePage = loginPage.loginAs(sUserName, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
	        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	        //verifying it with name
	        customReport.customizedReport(createpushcontentbean.getName(), avengereventspage.verify_text(createpushcontentbean.getName()),statusValue, driver, sTestcaseName);
	        customReport.checkinglist(statusValue);
	        
	}
	
	@Test(description="To Verify and create a new webcast and it should be filled with all the valid data and Send a Link / URL to all Attendees and SendMultiplePushcontentfile ",groups = {PostPushContentWebcast})
	public void TC05_Createanew_SharedURL_PostPushContentWebcast__urlPushEnabled_SendMultiplePushcontentfile_Positive_AV28786(ITestContext context)
	{
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		JSONObject loginapiresponse1 = userservices.doLoginUpdate(apiutils.userJson(sUserName), surl);
		System.out.println("response code is"+loginapiresponse1.get("Statuscode"));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
		 logger.info("Login API response Code :::"+loginapiresponse1);
		 customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
	  
		  createEventBean.setDays(ICreateEventService.setDay_00);
		  createEventBean.setHours(ICreateEventService.setMinutes_10);
		  apibeanfactory.EventBean(createEventBean);
		  
		  //PresentationProfileId UI String
		  String presentationProfileId[]={create_presentationProfileUI()};
		  createEventBean.setPresentationProfileId(presentationProfileId[0]);
		  
		 //EventAdminId String
		   String  eventadminIdArray[]= {loginapiresponse1.get("id").toString()};
		   createEventBean.setEventAdminId(eventadminIdArray);
		  
		   //Create event
			CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
		   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		   customReport.customizedReport(HttpStatusCode.httpsStatusCode200,createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		    loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
		  
			apibeanfactory.PushContentbean(createpushcontentbean);
		  createpushcontentbean.setLink(IAPIConstantCodes.GOOGLEADDRESS);
		  createpushcontentbean.setPushMethod(ICreatePushContentService.pushMethod[0]);
		  createpushcontentbean.setIsEnabled(IAPIConstantCodes.TRUE);
		 
		  PushContentAPI pushcontentAPI=new PushContentAPI();
		  ArrayList<String> pushcontentAPIresponse=pushcontentAPI.PushContentWebCastAPI(loginapiresponse1,createpushcontentbean);
		  assertionapiresponse.verifyAssert_httpCode(pushcontentAPIresponse.get(1).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, pushcontentAPIresponse.get(1).toString(),statusValue, driver, sTestcaseName);
	   
          apibeanfactory.PushContentbean(createpushcontentbean1);
		  createpushcontentbean1.setLink(IAPIConstantCodes.GOOGLEADDRESS);
		  createpushcontentbean1.setPushMethod(ICreatePushContentService.pushMethod[1]);
		  createpushcontentbean1.setIsEnabled(IAPIConstantCodes.TRUE);
		  
		  ArrayList<String> pushcontentAPIresponse1=pushcontentAPI.PushContentWebCastAPI(loginapiresponse1, createpushcontentbean1);
		  assertionapiresponse.verifyAssert_httpCode(pushcontentAPIresponse1.get(1).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, pushcontentAPIresponse1.get(1).toString(),statusValue, driver, sTestcaseName);
	
		 
		//selenium code 
		    launchURL(surl);
	        customReport.reporter("Application launch with URL : ", surl);
	        homePage = loginPage.loginAs(sUserName, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
	        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	        //verifying it with name for 1 file
	        customReport.customizedReport(createpushcontentbean.getName(), avengereventspage.verify_text(createpushcontentbean.getName()),statusValue, driver, sTestcaseName);
	        //verifying it with name for 2 file
	        customReport.customizedReport(createpushcontentbean1.getName(), avengereventspage.verify_text(createpushcontentbean1.getName()),statusValue, driver, sTestcaseName);
	}
	 
	@Test(description="To Verify and create a new webcast and it should be filled with all the valid data and Send a Link / URL to all Attendees and Withmorethan5MulltipleUrl ",groups = {PostPushContentWebcast})
	public void TC06_Createanew_SharedURL_PostPushContentWebcast__urlPushEnabled_Withmorethan5MulltipleUrl_Negative_AV28786(ITestContext context)
	{
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		JSONObject loginapiresponse1 = userservices.doLoginUpdate(apiutils.userJson(sUserName), surl);
		System.out.println("response code is"+loginapiresponse1.get("Statuscode"));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
		 logger.info("Login API response Code :::"+loginapiresponse1);
		 customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
	  
		  createEventBean.setDays(ICreateEventService.setDay_00);
		  createEventBean.setHours(ICreateEventService.setMinutes_10);
		  apibeanfactory.EventBean(createEventBean);
		  
		  //PresentationProfileId UI String
		  String presentationProfileId[]={create_presentationProfileUI()};
		  createEventBean.setPresentationProfileId(presentationProfileId[0]);
		  
		 //EventAdminId String
		   String  eventadminIdArray[]= {loginapiresponse1.get("id").toString()};
		   createEventBean.setEventAdminId(eventadminIdArray);
		  
		   //Create event
			CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
		   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		   customReport.customizedReport(HttpStatusCode.httpsStatusCode200,createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		    loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
		  
		    PushContentAPI pushcontentAPI=new PushContentAPI();
		    int i;
		   for(i=1;i<=5;i++)
			{
			   apibeanfactory.PushContentbean(createpushcontentbean);
		  createpushcontentbean.setLink(IAPIConstantCodes.GOOGLEADDRESS);
		  createpushcontentbean.setPushMethod(ICreatePushContentService.pushMethod[0]);
		  createpushcontentbean.setIsEnabled(IAPIConstantCodes.TRUE);
		 
		  ArrayList<String> pushcontentAPIresponse=pushcontentAPI.PushContentWebCastAPI(loginapiresponse1,createpushcontentbean);
		  assertionapiresponse.verifyAssert_httpCode(pushcontentAPIresponse.get(1).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, pushcontentAPIresponse.get(1).toString(),statusValue, driver, sTestcaseName);
			}
		  
		   
		    apibeanfactory.PushContentbean(createpushcontentbean);
			createpushcontentbean.setLink(IAPIConstantCodes.GOOGLEADDRESS);
			createpushcontentbean.setPushMethod(ICreatePushContentService.pushMethod[0]);
			createpushcontentbean.setIsEnabled(IAPIConstantCodes.TRUE);
		    ArrayList<String> pushcontentAPIresponse1=pushcontentAPI.PushContentWebCastAPI(loginapiresponse1,createpushcontentbean);
		    assertionapiresponse.verifyAssert_httpCode(pushcontentAPIresponse1.get(1).toString(), HttpStatusCode.httpsStatus401);  
			 customReport.customizedReport(HttpStatusCode.httpsStatus401, pushcontentAPIresponse1.get(1).toString(),statusValue, driver, sTestcaseName);
		  
		   //selenium code
			  launchURL(surl);
		        customReport.reporter("Application launch with URL : ", surl);
		        homePage = loginPage.loginAs(sUserName, sPassword);
		        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
		        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
		        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
		        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
		        //it is scrolling down just to verify the push contents 
		        avengereventspage.scroll_embeddedcontent();	 
	}
	
	
	@Test(description="To Verify and create a new webcast and it should be filled with all the valid data and Send a Link / URL to all Attendees and SendDuplicateNAme ",groups = {PostPushContentWebcast})
	public void TC07_Createanew_SharedURL_PostPushContentWebcast__urlPushEnabled_SendDuplicateNAme_Negative_AV28786(ITestContext context)
	{
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		JSONObject loginapiresponse1 = userservices.doLoginUpdate(apiutils.userJson(sUserName), surl);
		System.out.println("response code is"+loginapiresponse1.get("Statuscode"));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
		 logger.info("Login API response Code :::"+loginapiresponse1);
		 customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
	  
		  createEventBean.setDays(ICreateEventService.setDay_00);
		  createEventBean.setHours(ICreateEventService.setMinutes_10);
		  apibeanfactory.EventBean(createEventBean);
		  
		  //PresentationProfileId UI String
		  String presentationProfileId[]={create_presentationProfileUI()};
		  createEventBean.setPresentationProfileId(presentationProfileId[0]);
		  
		 //EventAdminId String
		   String  eventadminIdArray[]= {loginapiresponse1.get("id").toString()};
		   createEventBean.setEventAdminId(eventadminIdArray);
		  
		   //Create event
			CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
		   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		   customReport.customizedReport(HttpStatusCode.httpsStatusCode200,createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		    loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
		  
		 for(int i=1;i<=2;i++)
		  {
		  
		  createpushcontentbean.setName("PushcontentSame");
		  createpushcontentbean.setLink(IAPIConstantCodes.GOOGLEADDRESS);
		  createpushcontentbean.setPushMethod(ICreatePushContentService.pushMethod[0]);
		  createpushcontentbean.setIsEnabled(IAPIConstantCodes.TRUE);
		  
		  PushContentAPI pushcontentAPI=new PushContentAPI();
		  ArrayList<String> pushcontentAPIresponse=pushcontentAPI.PushContentWebCastAPI(loginapiresponse1,createpushcontentbean);
		  if(i==2)
		  {
		   assertionapiresponse.verifyAssert_httpCode(pushcontentAPIresponse.get(1).toString(), HttpStatusCode.httpsStatus500);  
		  customReport.customizedReport(HttpStatusCode.httpsStatus500, pushcontentAPIresponse.get(1).toString(),statusValue, driver, sTestcaseName);
		  }
		  }
		   //selenium code
		  launchURL(surl);
	        customReport.reporter("Application launch with URL : ", surl);
	        homePage = loginPage.loginAs(sUserName, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
	        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	        //it is scrolling down just to verify the push contents 
	        avengereventspage.scroll_embeddedcontent();	
	
	}
	
	@Test(description="Login through Api and create shared url and do  URL content is Enabled through UI",groups = {PostPushContentWebcast})
	public void TC08_Createanew_SharedURL_PostPushContentWebcast__urlContent_isEnabledthroughUI_Positive_AV14992(ITestContext context)
	{
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		JSONObject loginapiresponse1 = userservices.doLoginUpdate(apiutils.userJson(sUserName), surl);
		System.out.println("response code is"+loginapiresponse1.get("Statuscode"));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
		 logger.info("Login API response Code :::"+loginapiresponse1);
		 customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
	  
		  createEventBean.setDays(ICreateEventService.setDay_00);
		  createEventBean.setHours(ICreateEventService.setMinutes_10);
		  apibeanfactory.EventBean(createEventBean);
		  
		  //PresentationProfileId UI String
		  String presentationProfileId[]={create_presentationProfileUI()};
		  createEventBean.setPresentationProfileId(presentationProfileId[0]);
		  
		 //EventAdminId String
		   String  eventadminIdArray[]= {loginapiresponse1.get("id").toString()};
		   createEventBean.setEventAdminId(eventadminIdArray);
		   
		 
		   //Create event
			CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
		   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		   customReport.customizedReport(HttpStatusCode.httpsStatusCode200,createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		    loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
		   
		  apibeanfactory.PushContentbean(createpushcontentbean);
		  createpushcontentbean.setLink(IAPIConstantCodes.GOOGLEADDRESS);
		  createpushcontentbean.setPushMethod(ICreatePushContentService.pushMethod[0]);
		  createpushcontentbean.setIsEnabled(IAPIConstantCodes.TRUE);
		 
		  PushContentAPI pushcontentAPI=new PushContentAPI();
		  ArrayList<String> pushcontentAPIresponse=pushcontentAPI.PushContentWebCastAPI(loginapiresponse1,createpushcontentbean);
		  assertionapiresponse.verifyAssert_httpCode(pushcontentAPIresponse.get(1).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, pushcontentAPIresponse.get(1).toString(),statusValue, driver, sTestcaseName);
		
		  
		 
		  //selenium code for functionality of enable and disable check box of pushcontent from UI
		    launchURL(surl);
	        customReport.reporter("Application launch with URL : ", surl);
	        homePage = loginPage.loginAs(sUserName, sPassword);
	        homePage.clickSettingsLink();
	        AvengerMediaSettingsPage avengermediasettingpage=homePage.clickMediaSettingsLink();
	        AvengerFeaturesPage avengerfeaturespage=avengermediasettingpage.click_FeaturesLinkLocator();
	        avengerfeaturespage.enablesharelink();
	        avengerfeaturespage.disablesharelink();
	        avengerfeaturespage.enablesharelink();
	        homePage.click_logout();
	        homePage = loginPage.loginAs(sUserName, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
	        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	        customReport.customizedReport(createpushcontentbean.getName(), avengereventspage.verify_text(createpushcontentbean.getName()),statusValue, driver, sTestcaseName);   
	        customReport.checkinglist(statusValue);
	}	 
	
	@Test(description="Login as event admin and create shared url and check with the user who is not event admin/host(Create a new Shared URL)",groups = {PostPushContentWebcast})
	public void TC09_Createanew_SharedURL_PostPushContentWebcast_withallvaliddata_userwhoisnoteventadminorhost_Negative_AV28786(ITestContext context)
	{
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		JSONObject loginapiresponse1 = userservices.doLoginUpdate(apiutils.userJson(IUsersList.EventListeventadmin), surl);
		System.out.println("response code is"+loginapiresponse1.get("Statuscode"));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
		 logger.info("Login API response Code :::"+loginapiresponse1);
		 customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
	  
		  createEventBean.setDays(ICreateEventService.setDay_00);
		  createEventBean.setHours(ICreateEventService.setMinutes_10);
		  apibeanfactory.EventBean(createEventBean);
		  
		  //PresentationProfileId UI String
		  String presentationProfileId[]={create_presentationProfileUI()};
		  createEventBean.setPresentationProfileId(presentationProfileId[0]);
		  
		 //EventAdminId String
		   String  eventadminIdArray[]= {loginapiresponse1.get("id").toString()};
		   createEventBean.setEventAdminId(eventadminIdArray);
		  
		   //Create event
			CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
		   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		   customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		    loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
		 
		   //login as media viewer
		   JSONObject loginapiresponse = userservices.doLoginUpdate(apiutils.userJson(IUsersList.GetCategoryListmediaviewer), surl);
			System.out.println("response code is"+loginapiresponse.get("Statuscode"));
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
			 logger.info("Login API response Code :::"+loginapiresponse);
			 customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
		     
				   
		  apibeanfactory.PushContentbean(createpushcontentbean);
		  createpushcontentbean.setLink(IAPIConstantCodes.GOOGLEADDRESS);
		  createpushcontentbean.setPushMethod(ICreatePushContentService.pushMethod[0]);
		  createpushcontentbean.setIsEnabled(IAPIConstantCodes.TRUE);
		 
		  PushContentAPI pushcontentAPI=new PushContentAPI();
		  ArrayList<String> pushcontentAPIresponse=pushcontentAPI.PushContentWebCastAPI(loginapiresponse,createpushcontentbean);
		  assertionapiresponse.verifyAssert_httpCode(pushcontentAPIresponse.get(1).toString(), HttpStatusCode.httpsStatus401);  
		  customReport.customizedReport(HttpStatusCode.httpsStatus401, pushcontentAPIresponse.get(1).toString(),statusValue, driver, sTestcaseName);
		  logger.info("@@@@Shared URL ID: "+pushcontentAPIresponse.get(0));  
		  
	} 
	
	@Test(description="Login as account admin and create shared url and check with the mediaviewer PushMethod as End (Create a new Shared URL)",groups = {PostPushContentWebcast})
	public void TC10_Createanew_SharedURL_PostPushContentWebcast_withallvaliddata_withmediaviewerPushMethod_AsEnd_Positive_AV29084(ITestContext context) throws InterruptedException
	{
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		JSONObject loginapiresponse1 = userservices.doLoginUpdate(apiutils.userJson(sUserName), surl);
		System.out.println("response code is"+loginapiresponse1.get("Statuscode"));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
		 logger.info("Login API response Code :::"+loginapiresponse1);
		 customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
	  
		//login as media viewer
		   JSONObject loginapiresponse = userservices.doLoginUpdate(apiutils.userJson(IUsersList.CustomFieldmediaviewer1), surl);
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
		   String  eventadminIdArray[]= {loginapiresponse1.get("id").toString()};
		   createEventBean.setEventAdminId(eventadminIdArray);
		   
		  
		   //Create event
			CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
		   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		   customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		    loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
		 
		    
		    //start webcast  using api  
			 GetEventStatusAPI starteventAPI=new GetEventStatusAPI();		
			 JSONObject  starteventapiresponse=starteventAPI.starteventjsonobject(loginapiresponse1);
			 assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
			 customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
			   
		  apibeanfactory.PushContentbean(createpushcontentbean);
		  createpushcontentbean.setLink(IAPIConstantCodes.GOOGLEADDRESS);
		  createpushcontentbean.setPushMethod(ICreatePushContentService.pushMethod[1]);
		  createpushcontentbean.setIsEnabled(IAPIConstantCodes.TRUE);
		 
		  PushContentAPI pushcontentAPI=new PushContentAPI();
		  ArrayList<String> pushcontentAPIresponse=pushcontentAPI.PushContentWebCastAPI(loginapiresponse1,createpushcontentbean);
		  assertionapiresponse.verifyAssert_httpCode(pushcontentAPIresponse.get(1).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, pushcontentAPIresponse.get(1).toString(),statusValue, driver, sTestcaseName);
		  logger.info("@@@@Shared URL ID: "+pushcontentAPIresponse.get(0));  
	 
		  
			//selenium code
		    launchURL(surl);
	        customReport.reporter("Application launch with URL : ", surl);
	        homePage = loginPage.loginAs(IUsersList.CustomFieldmediaviewer1, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
	        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	        avengereventwebcastpage.click_sendalinkurltoallattendes();
	        customReport.customizedReport(createpushcontentbean.getName(), avengereventspage.verify_text(createpushcontentbean.getName()),statusValue, driver, sTestcaseName);   
	        avengereventwebcastpage.clickStartBroadCast();
	        avengereventwebcastpage.click_uablestartrecordingwebcast();
	        avengereventwebcastpage.click_endEvent();
	        customReport.customizedReport(createpushcontentbean.getLink(), avengereventspage.getlinkUI(),statusValue, driver, sTestcaseName);   
	        customReport.checkinglist(statusValue);
		  
	}
	@Test(description="Login with admin account and while creating a webcast set Send a Link / URL to all Attendees to Disabled ",groups = {PostPushContentWebcast})
	public void TC11_Create_AnEvent_set_SendaLinkURLtoallAttendeestoDisabled_Negative_AV28786(ITestContext context)
	{
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		JSONObject loginapiresponse1 = userservices.doLoginUpdate(apiutils.userJson(sUserName), surl);
		System.out.println("response code is"+loginapiresponse1.get("Statuscode"));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
		 logger.info("Login API response Code :::"+loginapiresponse1);
		 customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
	  
		  createEventBean.setDays(ICreateEventService.setDay_00);
		  createEventBean.setHours(ICreateEventService.setMinutes_10);
		  createEventBean.setIsEnabled(IAPIConstantCodes.FALSE);
		  createEventBean.setPushContent("Yes");
		  apibeanfactory.EventBean(createEventBean);
		  
		  //PresentationProfileId UI String
		  String presentationProfileId[]={create_presentationProfileUI()};
		  createEventBean.setPresentationProfileId(presentationProfileId[0]);
		  
		 //EventAdminId String
		   String  eventadminIdArray[]= {loginapiresponse1.get("id").toString()};
		   createEventBean.setEventAdminId(eventadminIdArray);
		   createEventBean.setPushContent("Yes");
		   createEventBean.setIsEnabled("false");
		   
		   
		   //Create event
			CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
		   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		   customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		    loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
		   
		  apibeanfactory.PushContentbean(createpushcontentbean);
		  createpushcontentbean.setLink(IAPIConstantCodes.GOOGLEADDRESS);
		  createpushcontentbean.setPushMethod(ICreatePushContentService.pushMethod[0]);
		  createpushcontentbean.setIsEnabled(IAPIConstantCodes.TRUE);
		 
		  PushContentAPI pushcontentAPI=new PushContentAPI();
		  ArrayList<String> pushcontentAPIresponse=pushcontentAPI.PushContentWebCastAPI(loginapiresponse1,createpushcontentbean);
		  assertionapiresponse.verifyAssert_httpCode(pushcontentAPIresponse.get(1).toString(), HttpStatusCode.httpsStatus401);  
		  customReport.customizedReport(HttpStatusCode.httpsStatus401, pushcontentAPIresponse.get(1).toString(),statusValue, driver, sTestcaseName);
		  logger.info("@@@@Shared URL ID: "+pushcontentAPIresponse.get(0));  
		  
		//selenium code
		  launchURL(surl);
	        customReport.reporter("Application launch with URL : ", surl);
	        homePage = loginPage.loginAs(sUserName, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
	        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	        avengereventspage.pushcontentenabledordisabledUI();
	        customReport.customizedReport(createEventBean.getIsEnabled(),avengereventspage.pushcontentenabledordisabledUI(),statusValue, driver, sTestcaseName);   
	        customReport.checkinglist(statusValue);
       
	}
	
	@Test(description="To Verify and create a new webcast and before starting webcast send post push content url and after starting webcast End webcast and then send push content url link",groups = {PostPushContentWebcast})
	public void TC12_Createanew_PostPushContentWebcast_beforeStartingWebcast_andsendpushcontenturl_endEvent_andagiansendpushcontenturl_withallvaliddata_Positve_AV29749(ITestContext context)
	{
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		JSONObject loginapiresponse1 = userservices.doLoginUpdate(apiutils.userJson(sUserName), surl);
		System.out.println("response code is"+loginapiresponse1.get("Statuscode"));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
		 logger.info("Login API response Code :::"+loginapiresponse1);
		 customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
	  
		  createEventBean.setDays(ICreateEventService.setDay_00);
		  createEventBean.setHours(ICreateEventService.setMinutes_10);
		  apibeanfactory.EventBean(createEventBean);
		  
		  //PresentationProfileId UI String
		  String presentationProfileId[]={create_presentationProfileUI()};
		  createEventBean.setPresentationProfileId(presentationProfileId[0]);
		  
		 //EventAdminId String
		   String  eventadminIdArray[]= {loginapiresponse1.get("id").toString()};
		   createEventBean.setEventAdminId(eventadminIdArray);
		  
		   //Create event
			CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
		   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		   customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
		   
		  apibeanfactory.PushContentbean(createpushcontentbean);
		  createpushcontentbean.setLink(IAPIConstantCodes.GOOGLEADDRESS);
		  createpushcontentbean.setPushMethod(ICreatePushContentService.pushMethod[0]);
		  createpushcontentbean.setIsEnabled(IAPIConstantCodes.TRUE);
		 
		  PushContentAPI pushcontentAPI=new PushContentAPI();
		  ArrayList<String> pushcontentAPIresponse=pushcontentAPI.PushContentWebCastAPI(loginapiresponse1,createpushcontentbean);
		  assertionapiresponse.verifyAssert_httpCode(pushcontentAPIresponse.get(1).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, pushcontentAPIresponse.get(1).toString(),statusValue, driver, sTestcaseName);
		  logger.info("@@@@Shared URL ID: "+pushcontentAPIresponse.get(0));  
		  
		//start webcast  using api  
		 GetEventStatusAPI starteventAPI=new GetEventStatusAPI();		
		 JSONObject  starteventapiresponse=starteventAPI.starteventjsonobject(loginapiresponse1);
		 assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		 customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		  
		 //broadcast Event
		 starteventAPI.broadcasteventjsonobject(loginapiresponse1);
		 
		 //end Event
		 starteventAPI.endevent(loginapiresponse1);
		 
		 //Push Content Url link
		  apibeanfactory.PushContentbean(createpushcontentbean);
		  createpushcontentbean.setLink(IAPIConstantCodes.GOOGLEADDRESS);
		  createpushcontentbean.setPushMethod(ICreatePushContentService.pushMethod[0]);
		  createpushcontentbean.setIsEnabled(IAPIConstantCodes.TRUE);
		 
		 
		  ArrayList<String> pushcontentAPIresponse1=pushcontentAPI.PushContentWebCastAPI(loginapiresponse1,createpushcontentbean);
		  assertionapiresponse.verifyAssert_httpCode(pushcontentAPIresponse1.get(1).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, pushcontentAPIresponse1.get(1).toString(),statusValue, driver, sTestcaseName);
		  logger.info("@@@@Shared URL ID: "+pushcontentAPIresponse1.get(0));  
		  
		   //selenium code 
		    launchURL(surl);
	        customReport.reporter("Application launch with URL : ", surl);
	        homePage = loginPage.loginAs(sUserName, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
	        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	       //assertion with name
	        customReport.customizedReport(createpushcontentbean.getName(), avengereventspage.verify_text(createpushcontentbean.getName()),statusValue, driver, sTestcaseName);
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

}
