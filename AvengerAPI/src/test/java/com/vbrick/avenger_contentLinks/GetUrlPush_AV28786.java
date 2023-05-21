package com.vbrick.avenger_contentLinks;

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
import com.vbrick.avenger.pageobjects.AvengerGroupsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerPresentationProfilesPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.ICreateEventService;
import com.vbricks.avenger.service.ICreatePushContentService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.CreateEventsAPI;
import com.vbricks.avenger.serviceimpl.PushContentAPI;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class GetUrlPush_AV28786 extends TestBase {

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
	private AddUploadVideoBean adduploadvideobean;
	public ApiBeanFactory apibeanfactory;
	private AddNewDmeBeanPage addnewdmedevicebeanpage;
    public AddVideoCommentBean addvideocommentbean;
	private HashMap<String, String> uploadvidoeapiresponse;
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
		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		
		adduploadvideobean = new AddUploadVideoBean();
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
	
	@Test(description="To Verify and create a new webcast and it should be filled with all the valid data and Get all the data in response body of push content (Get Shared URLs API)",groups = {GETPushContentWebcast})
	public void TC01_Createanew_GetSharedURLsAPI_GETPushContentWebcast__Positve_AV28786(ITestContext context)
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
		  ArrayList<String> pushcontentAPIresponse=pushcontentAPI.PushContentWebCastAPI(loginapiresponse1, createpushcontentbean);
		  assertionapiresponse.verifyAssert_httpCode(pushcontentAPIresponse.get(1).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, pushcontentAPIresponse.get(1).toString(),statusValue, driver, sTestcaseName);
		  logger.info("@@@@Shared URL ID: "+pushcontentAPIresponse.get(0));  
		
		  
		 JSONObject getpushcontentAPIresponse=pushcontentAPI.GET_PushContentAPI(loginapiresponse1);
		 assertionapiresponse.verifyAssert_httpCode(getpushcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		 customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getpushcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		 customReport.customizedReport(true, createpushcontentbean.getLink(), getpushcontentAPIresponse.get("link").toString(),statusValue, driver, sTestcaseName);
		 customReport.customizedReport(true, createpushcontentbean.getName(), getpushcontentAPIresponse.get("name").toString(),statusValue, driver, sTestcaseName);
		 customReport.customizedReport(true, createpushcontentbean.getPushMethod(), getpushcontentAPIresponse.get("pushMethod").toString(),statusValue, driver, sTestcaseName);
		 customReport.customizedReport(true, createpushcontentbean.getIsEnabled(), getpushcontentAPIresponse.get("isEnabled").toString(),statusValue, driver, sTestcaseName);
		 customReport.customizedReport(true, pushcontentAPIresponse.get(0).toString(), getpushcontentAPIresponse.get("id").toString(),statusValue, driver, sTestcaseName);
		 
			//selenium code
		  launchURL(surl);
	        customReport.reporter("Application launch with URL : ", surl);
	        homePage = loginPage.loginAs(sUserName, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
	        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	        customReport.customizedReport(avengereventwebcastpage.verify_label(createpushcontentbean.getName()),getpushcontentAPIresponse.get("name").toString(), statusValue, driver, sTestcaseName); 
	        customReport.checkinglist(statusValue);
		 
	
	}
	
		
	@Test(description="To Verify and create a new webcast and it should be filled with all the valid data and get the pushcontent files without eventid (Get Shared URLs API)",groups = {GETPushContentWebcast})
	public void TC02_Createanew_GetSharedURLsAPI_GETPushContentWebcast_Invalideventid_Negative_AV28786(ITestContext context)
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
		  logger.info("@@@@Shared URL ID: "+pushcontentAPIresponse.get(0));  
		  
		  //Get with invalid event id
		  loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID)+"1");
		 JSONObject getpushcontentAPIresponse=pushcontentAPI.GET_PushContentAPI_invalid(loginapiresponse1);
		 assertionapiresponse.verifyAssert_httpCode(getpushcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode. httpsStatus401);
		 customReport.customizedReport(HttpStatusCode. httpsStatus401, getpushcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
	
		 
	}

	@Test(description="To Verify and do login as admin account and get push content by eventadmin account (Get Shared URLs API)",groups = {GETPushContentWebcast})
	public void TC03_Createanew_GetSharedURLsAPI_GETPushContentWebcast_AsEventadmin_Positve_AV28786(ITestContext context)
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
		  
		 //EventAdminId
		   String  eventadminIdArray[]= {loginapiresponse1.get("id").toString()};
		   createEventBean.setEventAdminId(eventadminIdArray);
		    
		   //login  as event admin 
		    JSONObject loginapiresponse = userservices.doLoginUpdate(apiutils.userJson(IUsersList.GetEventDetailseventadmin), surl);
			System.out.println("response code is"+loginapiresponse.get("Statuscode"));
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
			 logger.info("Login API response Code :::"+loginapiresponse);
			 customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
		  		   
		 //EventAdminId as event admin account
		   String  eventadminIdArray1[]={loginapiresponse.get("id").toString()};
		   createEventBean.setEventAdminId(eventadminIdArray1);
		   
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
		  logger.info("@@@@Shared URL ID: "+pushcontentAPIresponse.get(0));  
		  
		  //Get eith event admin 
		  loginapiresponse.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
		 
		  JSONObject getpushcontentAPIresponse=pushcontentAPI.GET_PushContentAPI(loginapiresponse);
		 assertionapiresponse.verifyAssert_httpCode(getpushcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		 customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getpushcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		 customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getpushcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		 customReport.customizedReport(true, createpushcontentbean.getLink(), getpushcontentAPIresponse.get("link").toString(),statusValue, driver, sTestcaseName);
		 customReport.customizedReport(true, createpushcontentbean.getName(), getpushcontentAPIresponse.get("name").toString(),statusValue, driver, sTestcaseName);
		 customReport.customizedReport(true, createpushcontentbean.getPushMethod(), getpushcontentAPIresponse.get("pushMethod").toString(),statusValue, driver, sTestcaseName);
		 customReport.customizedReport(true, createpushcontentbean.getIsEnabled(), getpushcontentAPIresponse.get("isEnabled").toString(),statusValue, driver, sTestcaseName);
		 customReport.customizedReport(true, pushcontentAPIresponse.get(0).toString(), getpushcontentAPIresponse.get("id").toString(),statusValue, driver, sTestcaseName);
		 
		 
		 
		 
		 
		//selenium code
		  launchURL(surl);
	        customReport.reporter("Application launch with URL : ", surl);
	        homePage = loginPage.loginAs(IUsersList.GetEventDetailseventadmin, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
	        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	        customReport.customizedReport(avengereventwebcastpage.verify_label(createpushcontentbean.getName()),getpushcontentAPIresponse.get("name").toString(), statusValue, driver, sTestcaseName); 
	        customReport.checkinglist(statusValue);
	}

	@Test(description="To Verify and create a new webcast and it should be filled edit Name and get Name(Get Shared URLs API)",groups = {GETPushContentWebcast})
	public void TC04_Createanew_GetSharedURLsAPI_GETPushContentWebcast_GETNAME_Positve_AV28786(ITestContext context)
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
		  ArrayList<String> pushcontentAPIresponse=pushcontentAPI.PushContentWebCastAPI(loginapiresponse1, createpushcontentbean);
		  assertionapiresponse.verifyAssert_httpCode(pushcontentAPIresponse.get(1).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, pushcontentAPIresponse.get(1).toString(),statusValue, driver, sTestcaseName);
		  logger.info("@@@@Shared URL ID: "+pushcontentAPIresponse.get(0));  
		  loginapiresponse1.put(IAPIConstantCodes.SharedURLID, pushcontentAPIresponse.get(0).toString());
		  
		  //Name
		  createpushcontentbean1.setName(ICreatePushContentService.PushContentName);
		  createpushcontentbean1.setLink(IAPIConstantCodes.GOOGLEADDRESS);
		  createpushcontentbean1.setPushMethod(ICreatePushContentService.pushMethod[0]);
		  createpushcontentbean1.setIsEnabled(IAPIConstantCodes.TRUE);
		  
		  JSONObject putpushcontentAPIresponseid=pushcontentAPI.Put_PushContentWebCastAPI_Id(loginapiresponse1, createpushcontentbean1);
		  assertionapiresponse.verifyAssert_httpCode(putpushcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, putpushcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		  
		  
		 JSONObject getpushcontentAPIresponse=pushcontentAPI.GET_PushContentAPI(loginapiresponse1);
		 assertionapiresponse.verifyAssert_httpCode(getpushcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		 customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getpushcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		 customReport.customizedReport(true, createpushcontentbean1.getName(), getpushcontentAPIresponse.get("name").toString(),statusValue, driver, sTestcaseName);
		 	 
			//selenium code
		  launchURL(surl);
	        customReport.reporter("Application launch with URL : ", surl);
	        homePage = loginPage.loginAs(IUsersList.GetEventDetailseventadmin, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
	        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	        customReport.customizedReport(avengereventwebcastpage.verify_label(createpushcontentbean1.getName()),getpushcontentAPIresponse.get("name").toString(), statusValue, driver, sTestcaseName); 
	        customReport.checkinglist(statusValue);
	}	
	
	@Test(description="To Verify and create a new webcast and it should be filled edit push method as LINK and Get the Link (Get Shared URLs API)",groups = {GETPushContentWebcast})
	public void TC05_Createanew_GetSharedURLsAPI_GETPushContentWebcast_GETLINK_Positve__AV28786(ITestContext context)
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
		  logger.info("@@@@Shared URL ID: "+pushcontentAPIresponse.get(0));  
		  
		  
		 /*//LINK
		  apibeanfactory.PushContentbean(createpushcontentbean1);
		  createpushcontentbean1.setPushMethod(ICreatePushContentService.pushMethod[0]);
		  createpushcontentbean1.setIsEnabled(IAPIConstantCodes.TRUE);
		  createpushcontentbean1.setLink(ICreatePushContentService.link);
		 
		  loginapiresponse1.put(IAPIConstantCodes.SharedURLID, pushcontentAPIresponse.get(0).toString());
		  
		  JSONObject putpushcontentAPIresponseid=pushcontentAPI.Put_PushContentWebCastAPI_Id(loginapiresponse1, createpushcontentbean1);
		  assertionapiresponse.verifyAssert_httpCode(putpushcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, putpushcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		  */
		  
		 JSONObject getpushcontentAPIresponse=pushcontentAPI.GET_PushContentAPI(loginapiresponse1);
		 assertionapiresponse.verifyAssert_httpCode(getpushcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		 customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getpushcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		 customReport.customizedReport(true, createpushcontentbean.getLink().toString(), getpushcontentAPIresponse.get("link").toString(),statusValue, driver, sTestcaseName);
		 	 
		 

			//selenium code
		  launchURL(surl);
	        customReport.reporter("Application launch with URL : ", surl);
	        homePage = loginPage.loginAs(IUsersList.GetEventDetailseventadmin, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
	        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	        customReport.customizedReport(avengereventwebcastpage.verify_label(createpushcontentbean.getName()),getpushcontentAPIresponse.get("name").toString(), statusValue, driver, sTestcaseName); 
	        customReport.checkinglist(statusValue);
	
	}	
	
	@Test(description="To Verify and create a new webcast and it should be filled edit ISEnabled and get is enabled (Get Shared URLs API)",groups = {GETPushContentWebcast})
	public void TC06_Createanew_GetSharedURLsAPI_GETPushContentWebcast_GETISENABLED_Positve__AV28786(ITestContext context)
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
		  createpushcontentbean.setPushMethod(ICreatePushContentService.pushMethod[1]);
		  createpushcontentbean.setIsEnabled(IAPIConstantCodes.TRUE);
		 
		  PushContentAPI pushcontentAPI=new PushContentAPI();
		  ArrayList<String> pushcontentAPIresponse=pushcontentAPI.PushContentWebCastAPI(loginapiresponse1,createpushcontentbean);
		  assertionapiresponse.verifyAssert_httpCode(pushcontentAPIresponse.get(1).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, pushcontentAPIresponse.get(1).toString(),statusValue, driver, sTestcaseName);
		  loginapiresponse1.put(IAPIConstantCodes.SharedURLID, pushcontentAPIresponse.get(0).toString());
		  
		  
		/*  //ISEnabled  
		  apibeanfactory.PushContentbean(createpushcontentbean1);
		  createpushcontentbean1.setLink(ICreatePushContentService.link);
		  createpushcontentbean1.setPushMethod(ICreatePushContentService.pushMethod[0]);
		  createpushcontentbean1.setIsEnabled(IAPIConstantCodes.FALSE);
		  
		  JSONObject putpushcontentAPIresponseid=pushcontentAPI.Put_PushContentWebCastAPI_Id(loginapiresponse1,createpushcontentbean1);
		  assertionapiresponse.verifyAssert_httpCode(putpushcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, putpushcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);*/
		  
		  
		 JSONObject getpushcontentAPIresponse=pushcontentAPI.GET_PushContentAPI(loginapiresponse1);
		 assertionapiresponse.verifyAssert_httpCode(getpushcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		 customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getpushcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		 customReport.customizedReport(true, createpushcontentbean.getIsEnabled().toString(), getpushcontentAPIresponse.get("isEnabled").toString(),statusValue, driver, sTestcaseName);
 
		 

			//selenium code
		  launchURL(surl);
	        customReport.reporter("Application launch with URL : ", surl);
	        homePage = loginPage.loginAs(IUsersList.GetEventDetailseventadmin, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
	        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	        customReport.customizedReport(avengereventwebcastpage.verify_label(createpushcontentbean.getName()),getpushcontentAPIresponse.get("name").toString(), statusValue, driver, sTestcaseName); 
	        customReport.checkinglist(statusValue);
	
	}	
	
	@Test(description="To Verify and create a new webcast and it should be filled edit push method as ATEND (Get Shared URLs API)",groups = {GETPushContentWebcast})
	public void TC07_Createanew_GetSharedURLsAPI_GETPushContentWebcast_GETATEND_Positve__AV28786(ITestContext context)
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
		  ArrayList<String> pushcontentAPIresponse=pushcontentAPI.PushContentWebCastAPI(loginapiresponse1, createpushcontentbean);
		  assertionapiresponse.verifyAssert_httpCode(pushcontentAPIresponse.get(1).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, pushcontentAPIresponse.get(1).toString(),statusValue, driver, sTestcaseName);
		  loginapiresponse1.put(IAPIConstantCodes.SharedURLID, pushcontentAPIresponse.get(0).toString());
		  
		//PushMethod  
		  apibeanfactory.PushContentbean(createpushcontentbean1);
		  createpushcontentbean1.setLink(ICreatePushContentService.link);
		  createpushcontentbean1.setPushMethod(ICreatePushContentService.pushMethod[1]);
		  createpushcontentbean1.setIsEnabled(IAPIConstantCodes.TRUE);
		 
		  
		 JSONObject putpushcontentAPIresponseid=pushcontentAPI.Put_PushContentWebCastAPI_Id(loginapiresponse1, createpushcontentbean1);
		  assertionapiresponse.verifyAssert_httpCode(putpushcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, putpushcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		  
		  
		 JSONObject getpushcontentAPIresponse=pushcontentAPI.GET_PushContentAPI(loginapiresponse1);
		 assertionapiresponse.verifyAssert_httpCode(getpushcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		 customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getpushcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		 customReport.customizedReport(true, createpushcontentbean1.getPushMethod(), getpushcontentAPIresponse.get("pushMethod").toString(),statusValue, driver, sTestcaseName);


			//selenium code
		  launchURL(surl);
	        customReport.reporter("Application launch with URL : ", surl);
	        homePage = loginPage.loginAs(IUsersList.GetEventDetailseventadmin, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
	        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	        customReport.customizedReport(avengereventwebcastpage.verify_label(createpushcontentbean1.getName()),getpushcontentAPIresponse.get("name").toString(), statusValue, driver, sTestcaseName); 
	        customReport.checkinglist(statusValue);
	
		 
	}	
	
	@Test(description="To Verify and create a new webcast and it should be filled Get id (Get Shared URLs API)",groups = {GETPushContentWebcast})
	public void TC08_Createanew_GetSharedURLsAPI_GETPushContentWebcast_GETID_Positve_AV28786(ITestContext context)
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
		  ArrayList<String> pushcontentAPIresponse=pushcontentAPI.PushContentWebCastAPI(loginapiresponse1, createpushcontentbean);
		  assertionapiresponse.verifyAssert_httpCode(pushcontentAPIresponse.get(1).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, pushcontentAPIresponse.get(1).toString(),statusValue, driver, sTestcaseName);
		  logger.info("@@@@Shared URL ID: "+pushcontentAPIresponse.get(0));  
		  loginapiresponse1.put(IAPIConstantCodes.SharedURLID, pushcontentAPIresponse.get(0));
		  
		//PushMethod  
		  apibeanfactory.PushContentbean(createpushcontentbean1);
		  createpushcontentbean1.setLink(ICreatePushContentService.link);
		  createpushcontentbean1.setPushMethod(ICreatePushContentService.pushMethod[1]);
		  createpushcontentbean1.setIsEnabled(IAPIConstantCodes.TRUE);
		 
		JSONObject putpushcontentAPIresponseid=pushcontentAPI.Put_PushContentWebCastAPI_Id(loginapiresponse1, createpushcontentbean1);
		  assertionapiresponse.verifyAssert_httpCode(putpushcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, putpushcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		  
		  
		 JSONObject getpushcontentAPIresponse=pushcontentAPI.GET_PushContentAPI(loginapiresponse1);
		 assertionapiresponse.verifyAssert_httpCode(getpushcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		 customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getpushcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		 customReport.customizedReport(true, pushcontentAPIresponse.get(0).toString(), getpushcontentAPIresponse.get("id").toString(),statusValue, driver, sTestcaseName);
	

			//selenium code
		  launchURL(surl);
	        customReport.reporter("Application launch with URL : ", surl);
	        homePage = loginPage.loginAs(IUsersList.GetEventDetailseventadmin, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
	        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	        customReport.customizedReport(avengereventwebcastpage.verify_label(createpushcontentbean1.getName()),getpushcontentAPIresponse.get("name").toString(), statusValue, driver, sTestcaseName); 
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
