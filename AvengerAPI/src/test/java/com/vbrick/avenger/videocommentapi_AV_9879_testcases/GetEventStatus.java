package com.vbrick.avenger.videocommentapi_AV_9879_testcases;

import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.ParseException;
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
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
import com.vbrick.avenger.apibeans.AddCategoryBean;
import com.vbrick.avenger.apibeans.AddGroupBean;
import com.vbrick.avenger.apibeans.AddVideoCommentBean;
import com.vbrick.avenger.apibeans.CreateEventBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AddCustomDeviceBeanPage;
import com.vbrick.avenger.dao.AddNewDmeBeanPage;
import com.vbrick.avenger.dao.AddPresentationprofileBeanPage;
import com.vbrick.avenger.dao.AvengerEventDetailsBeanPage;
import com.vbrick.avenger.dao.AvengerGroupsBeanPage;
import com.vbrick.avenger.dao.AvengerSystemMessageBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.DateTime;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.pageobjects.AvengerAddCustomDevicePage;
import com.vbrick.avenger.pageobjects.AvengerAddPresentationProfilePage;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerDevicesPage;
import com.vbrick.avenger.pageobjects.AvengerEditRootAccountPage;
import com.vbrick.avenger.pageobjects.AvengerEventDetailsPage;
import com.vbrick.avenger.pageobjects.AvengerEventWebCastPage;
import com.vbrick.avenger.pageobjects.AvengerEventsPage;
import com.vbrick.avenger.pageobjects.AvengerGroupsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerPresentationProfilesPage;
import com.vbrick.avenger.pageobjects.AvengerUserDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.ICreateEventService;
import com.vbricks.avenger.service.IUserAccountsService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.service.IVideoAccessControlService;
import com.vbricks.avenger.serviceimpl.CreateCategoryAPI;
import com.vbricks.avenger.serviceimpl.CreateEventsAPI;
import com.vbricks.avenger.serviceimpl.CreateGroupAPI;
import com.vbricks.avenger.serviceimpl.EditGroupAPI;
import com.vbricks.avenger.serviceimpl.GetEventStatusAPI;
//import com.vbricks.avenger.serviceimpl.StartEventAPI;
import com.vbricks.avenger.serviceimpl.GetEventStatusAPI;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class GetEventStatus extends TestBase {

	private static Logger logger = Logger.getLogger(GetEventStatus.class);
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
    private ApiUtils apiutils;
	private AvengerGroupsPage avengergroupspage;
	private AvengerGroupsBeanPage avengergroupsbeanpage;
	private AvengerHomePage avengerHomePage;
	private AddGroupBean addgroupbean;
	private AvengerDevicesPage avengerdevicespage;
	private AvengerEventDetailsPage avengereventdetailspage;
	private AvengerPresentationProfilesPage avengerpresentationprofilespage;
	private AvengerAddPresentationProfilePage avengeraddpresentationprofilepage;
	private AddPresentationprofileBeanPage addpresentationprofilebeanpage;
	private AvengerUserDashboardPage avengeruserdashboardpage;
	private AvengerAddCustomDevicePage avengeraddcustomdevicepage;
	private AvengerEditRootAccountPage avengereditrootaccountpage;
	private AddNewDmeBeanPage addnewdmedevicebeanpage;
	private BasePage basePage;
	private AddCategoryBean addcategorybean;
	private AddCustomDeviceBeanPage addcustomdevicebeanpage;
	private HashMap<String, String> creategroupapirespone;
	private CreateEventBean createEventBean;
	public ApiBeanFactory apibeanfactory;
 	private HashMap<String, String> loginapiresponse;
 	private HashMap<String, String> loginapiresponse1;
 	private HashMap<String, String> loginapiresponse2;
	private HashMap<String, String> createcategoryapirespone;
	private HashMap<String,String >editGroupapiresponse;
	private AssertionAPIResponse assertionapiresponse;
	private AvengerEventWebCastPage avengereventwebcastpage;
	
	@BeforeClass(alwaysRun = true)
	public void beforeClass() throws Exception {
		reasons = new Reasons("");
		statusValue = new ArrayList<String>();
		customReport = new CustomReport();
		accountBeansFactory = new BeanFactory();
		mailinatorbeanpage = new MailinatorBeanPage();
		addgroupbean=new AddGroupBean();
		new FileUploadBeanPage();
		locale = new Locale(language);
		readgriduserdata = new ReadAndWriteToJSON();
		userdata = readgriduserdata.readGridUserData(this.getClass().getSimpleName()); // ("user52");
		sUserName = userdata.get("Username");
		sPassword = userdata.get("Password");
		avengergroupsbeanpage = new AvengerGroupsBeanPage();
		createEventBean = new CreateEventBean();
		apibeanfactory = new ApiBeanFactory();
		addcategorybean=new AddCategoryBean();
		addpresentationprofilebeanpage = new AddPresentationprofileBeanPage();
		addnewdmedevicebeanpage = new AddNewDmeBeanPage();
 		assertionapiresponse =new AssertionAPIResponse();
		addcustomdevicebeanpage = new AddCustomDeviceBeanPage();
		avengergroupsbeanpage = new AvengerGroupsBeanPage();
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
		avengereventwebcastpage=new AvengerEventWebCastPage(driver, customReport, basePage);
		driver.manage().window().maximize();
		accountBeansFactory.MailinatorBean(mailinatorbeanpage);
		accountBeansFactory.AvengergroupsBean(avengergroupsbeanpage);
		accountBeansFactory.createPresentationProfile(addpresentationprofilebeanpage);
		accountBeansFactory.AddNewDmeBean(addnewdmedevicebeanpage);
		accountBeansFactory.addCustomDeviceBean(addcustomdevicebeanpage);

	}

	@Test(description = "To verify Scheduled event status with Account Admin ",groups = {GETEVENTSTATUS})
	public void TC_01_GET_EventStatus_Scheduled_AccoutAdmin_Positive() throws ParseException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
	 	UserServices userservices = new UserServices();
	    createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_10);
		apibeanfactory.EventBean(createEventBean);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
			
		//EventAdminId
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		HashMap<String,String> eventAdminId1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID),eventAdminId1.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
		createEventBean.setDescription("");
		createEventBean.setShortcut("");
		
		//create Event..
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
	  
	    logger.info("Selenium Code is excuting");
        launchURL(surl);
        customReport.reporter("Application launch with URL : ", surl);
        homePage = loginPage.loginAs(sUserName, sPassword);
        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());
        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
       
        HashMap<String,String> eventDetails=avengereventdetailspage.get_EventData();       
        ArrayList<String> ehUsers = avengereventdetailspage.get_EventHostUsers();               
        customReport.customizedReport(createEventBean.getTitle(),eventDetails.get(IUserAccountsService.TITLE), statusValue, driver, sTestcaseName);
        customReport.customizedReport(createeventapirespone.get(IUserAccountsService.EVENTID),avengereventdetailspage.getvideoID(), statusValue, driver, sTestcaseName);
        customReport.customizedReport(true,ehUsers.contains("apimau10 apimau10"), statusValue, driver, sTestcaseName);
        customReport.customizedReport(true,ehUsers.contains("apieau10 apieau10"), statusValue, driver, sTestcaseName);
        customReport.customizedReport(true,avengereventdetailspage.verify_eventlisting("Public"),statusValue, driver, sTestcaseName);
        //customReport.customizedReport("15",eventDetails.get(ICreateEventService.lobbyTime) ,statusValue, driver, sTestcaseName);
        customReport.customizedReport("Presentation",avengereventdetailspage.verify_videosourcetype(),statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
        
       //get event status 
       logger.info("get event status");
       GetEventStatusAPI getGeteventStatusAPI = new GetEventStatusAPI();
       HashMap<String, String> getGeteventStatusAPIresponse = getGeteventStatusAPI.getEventDetails(createeventapirespone);
       logger.info("Get event status code :::" + createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode));
       assertionapiresponse.verifyAssert_httpCode(getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
       customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	}
	
	@Test(description = "To verify Inprogress event status with Account Admin ",groups = {GETEVENTSTATUS})
	public void TC_02_GET_EventStatus_Inprogress_withpreproductionEnabled_AccoutAdmin_Positive() throws Exception {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
	 	UserServices userservices = new UserServices();
	 	 createEventBean.setDays(ICreateEventService.setDay_00);
		 createEventBean.setHours(ICreateEventService.setMinutes_15);
		 apibeanfactory.EventBean2(createEventBean);
		
		
	    
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
			
		//EventAdminId
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		HashMap<String,String> eventAdminId1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID),eventAdminId1.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
		createEventBean.setDescription("");
		createEventBean.setShortcut("");
		createEventBean.setPreproduction("Yes");
		createEventBean.setDuration("00:15:00");
		
		
		//create Event..
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
	  
	    logger.info("Selenium Code is excuting");
        launchURL(surl);
        customReport.reporter("Application launch with URL : ", surl);
        homePage = loginPage.loginAs(sUserName, sPassword);
        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());
        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
        HashMap<String,String> eventDetails=avengereventdetailspage.get_EventData();
        ArrayList<String> ehUsers = avengereventdetailspage.get_EventHostUsers();
        customReport.customizedReport(createEventBean.getTitle(),eventDetails.get(IUserAccountsService.TITLE), statusValue, driver, sTestcaseName);
        customReport.customizedReport(createeventapirespone.get(IUserAccountsService.EVENTID),avengereventdetailspage.getvideoID(), statusValue, driver, sTestcaseName);
        customReport.customizedReport(true,ehUsers.contains("apimau10 apimau10"), statusValue, driver, sTestcaseName);
        customReport.customizedReport(true,ehUsers.contains("apieau10 apieau10"), statusValue, driver, sTestcaseName);
        customReport.customizedReport(true,avengereventdetailspage.verify_eventlisting("Public"),statusValue, driver, sTestcaseName);
        //customReport.customizedReport("15",eventDetails.get(ICreateEventService.lobbyTime) ,statusValue, driver, sTestcaseName);
        customReport.customizedReport("Presentation",avengereventdetailspage.verify_videosourcetype(),statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
       
       // get event status 
       logger.info("selenium code is executing to change the status to inprogress using preproduction");
       GetEventStatusAPI getGeteventStatusAPI = new GetEventStatusAPI();
       HashMap<String, String> getGeteventStatusAPIresponse = getGeteventStatusAPI.getEventDetails(createeventapirespone);
       avengereventdetailspage.preproductionenable();
       avengereventdetailspage.enablepreproduction();
       
       logger.info("get event status");
       GetEventStatusAPI getGeteventStatusAPI1 = new GetEventStatusAPI();
       HashMap<String, String> getGeteventStatusAPIresponse1 = getGeteventStatusAPI.getEventDetails(createeventapirespone);
       logger.info("Get event status code :::" + createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode));
       assertionapiresponse.verifyAssert_httpCode(getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
       customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	}
	
	@Test(description = "To verify InProgress event status with Account Admin ",groups = {GETEVENTSTATUS})
	public void TC_03_StartEventAPI_InProgress_AccoutAdmin_Positive() throws ParseException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
	 	UserServices userservices = new UserServices();
	    createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_10);
		apibeanfactory.EventBean(createEventBean);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
			
		//EventAdminId
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		HashMap<String,String> eventAdminId1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID),eventAdminId1.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
		createEventBean.setDescription("");
		createEventBean.setShortcut("");
		
		//create Event..
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
	  
	    logger.info("Selenium Code is excuting");
        launchURL(surl);
        customReport.reporter("Application launch with URL : ", surl);
        homePage = loginPage.loginAs(sUserName, sPassword);
        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());
        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
       
        HashMap<String,String> eventDetails=avengereventdetailspage.get_EventData();       
        ArrayList<String> ehUsers = avengereventdetailspage.get_EventHostUsers();               
        customReport.customizedReport(createEventBean.getTitle(),eventDetails.get(IUserAccountsService.TITLE), statusValue, driver, sTestcaseName);
        customReport.customizedReport(createeventapirespone.get(IUserAccountsService.EVENTID),avengereventdetailspage.getvideoID(), statusValue, driver, sTestcaseName);
        customReport.customizedReport(true,ehUsers.contains("apimau10 apimau10"), statusValue, driver, sTestcaseName);
        customReport.customizedReport(true,ehUsers.contains("apieau10 apieau10"), statusValue, driver, sTestcaseName);
        customReport.customizedReport(true,avengereventdetailspage.verify_eventlisting("Public"),statusValue, driver, sTestcaseName);
        //customReport.customizedReport("15",eventDetails.get(ICreateEventService.lobbyTime) ,statusValue, driver, sTestcaseName);
        customReport.customizedReport("Presentation",avengereventdetailspage.verify_videosourcetype(),statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
        loginapiresponse = new HashMap<String, String>();
        createeventapirespone.putAll(eventAdminId1);
             
        //Start Event
        logger.info("Start Event API started");
        GetEventStatusAPI starteventapi = new GetEventStatusAPI();
        HashMap<String, String> getstatus = starteventapi.startevent(createeventapirespone);
        assertionapiresponse.verifyAssert_httpCode(getstatus.get(IAPIConstantCodes.APIResponseHttpCode)+getstatus.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
       
        logger.info("getting event status");
        GetEventStatusAPI getGeteventStatusAPI = new GetEventStatusAPI();
        HashMap<String, String> getGeteventStatusAPIresponse = getGeteventStatusAPI.getEventDetails(createeventapirespone);
        logger.info("Get event status code :::" + createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode));
        assertionapiresponse.verifyAssert_httpCode(getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
 	}
 	
	@Test(description = "To verify Broadcasting event status with Account Admin ",groups = {GETEVENTSTATUS})
	public void TC_04_GET_EventStatus_Broadcasting_withBroadcastAPI_AccoutAdmin_Positive() throws ParseException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
	 	UserServices userservices = new UserServices();
	    createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_30);
		apibeanfactory.EventBean(createEventBean);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
			
		//EventAdminId
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		HashMap<String,String> eventAdminId1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID),eventAdminId1.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
		createEventBean.setDescription("");
		createEventBean.setShortcut("");
		
		//create Event..
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
	  
	    logger.info("Selenium Code is excuting");
        launchURL(surl);
        customReport.reporter("Application launch with URL : ", surl);
        homePage = loginPage.loginAs(sUserName, sPassword);
        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());
        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
        
        HashMap<String,String> eventDetails=avengereventdetailspage.get_EventData();        
        ArrayList<String> ehUsers = avengereventdetailspage.get_EventHostUsers();               
        customReport.customizedReport(createEventBean.getTitle(),eventDetails.get(IUserAccountsService.TITLE), statusValue, driver, sTestcaseName);
        customReport.customizedReport(createeventapirespone.get(IUserAccountsService.EVENTID),avengereventdetailspage.getvideoID(), statusValue, driver, sTestcaseName);
        customReport.customizedReport(true,ehUsers.contains("apimau10 apimau10"), statusValue, driver, sTestcaseName);
        customReport.customizedReport(true,ehUsers.contains("apieau10 apieau10"), statusValue, driver, sTestcaseName);
        customReport.customizedReport(true,avengereventdetailspage.verify_eventlisting("Public"),statusValue, driver, sTestcaseName);
       // customReport.customizedReport("15",eventDetails.get(ICreateEventService.lobbyTime) ,statusValue, driver, sTestcaseName);
        customReport.customizedReport("Presentation",avengereventdetailspage.verify_videosourcetype(),statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
        loginapiresponse = new HashMap<String, String>();
        createeventapirespone.putAll(eventAdminId1);
        
        
        //Start Event
        logger.info("Executing Start Event API");
        GetEventStatusAPI starteventapi = new GetEventStatusAPI();
        HashMap<String, String> getstatus = starteventapi.startevent(createeventapirespone);
        assertionapiresponse.verifyAssert_httpCode(getstatus.get(IAPIConstantCodes.APIResponseHttpCode)+getstatus.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
        logger.info("Executing Broadcast Event API");
        
        HashMap<String, String> endevent = starteventapi.broadcastevent(createeventapirespone);
        assertionapiresponse.verifyAssert_httpCode(getstatus.get(IAPIConstantCodes.APIResponseHttpCode)+getstatus.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);       
        
        logger.info("getting event status");
        GetEventStatusAPI getGeteventStatusAPI = new GetEventStatusAPI();  
        HashMap<String, String> getGeteventStatusAPIresponse = getGeteventStatusAPI.getEventDetails(createeventapirespone);
        logger.info("Get event status code :::" + createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode));
        assertionapiresponse.verifyAssert_httpCode(getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	
	}
	
	@Test(description = "To verify Completed event status with Account Admin ",groups = {GETEVENTSTATUS})
	public void TC_05_GET_EventStatus_Completed_withEndWebcastAPI_AccoutAdmin_Positive() throws ParseException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
	 	UserServices userservices = new UserServices();
	    createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_10);
		apibeanfactory.EventBean(createEventBean);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
			
		//EventAdminId
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		HashMap<String,String> eventAdminId1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID),eventAdminId1.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
		createEventBean.setDescription("");
		createEventBean.setShortcut("");
		
		//create Event..
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
	  
	    logger.info("Selenium Code is excuting");
        launchURL(surl);
        customReport.reporter("Application launch with URL : ", surl);
        homePage = loginPage.loginAs(sUserName, sPassword);
        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());
        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
       
        HashMap<String,String> eventDetails=avengereventdetailspage.get_EventData();       
        ArrayList<String> ehUsers = avengereventdetailspage.get_EventHostUsers();               
        customReport.customizedReport(createEventBean.getTitle(),eventDetails.get(IUserAccountsService.TITLE), statusValue, driver, sTestcaseName);
        customReport.customizedReport(createeventapirespone.get(IUserAccountsService.EVENTID),avengereventdetailspage.getvideoID(), statusValue, driver, sTestcaseName);
        customReport.customizedReport(true,ehUsers.contains("apimau10 apimau10"), statusValue, driver, sTestcaseName);
        customReport.customizedReport(true,ehUsers.contains("apieau10 apieau10"), statusValue, driver, sTestcaseName);
        customReport.customizedReport(true,avengereventdetailspage.verify_eventlisting("Public"),statusValue, driver, sTestcaseName);
        //customReport.customizedReport("15",eventDetails.get(ICreateEventService.lobbyTime) ,statusValue, driver, sTestcaseName);
        customReport.customizedReport("Presentation",avengereventdetailspage.verify_videosourcetype(),statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
        loginapiresponse = new HashMap<String, String>();
        createeventapirespone.putAll(eventAdminId1);
               
        //Start Event
        logger.info("Executing Start Event API");
        GetEventStatusAPI starteventapi = new GetEventStatusAPI();
        HashMap<String, String> getstatus = starteventapi.startevent(createeventapirespone);
        assertionapiresponse.verifyAssert_httpCode(getstatus.get(IAPIConstantCodes.APIResponseHttpCode)+getstatus.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
        logger.info("Executing End Event API");
       
        HashMap<String, String> endevent = starteventapi.endevent(createeventapirespone);
        assertionapiresponse.verifyAssert_httpCode(getstatus.get(IAPIConstantCodes.APIResponseHttpCode)+getstatus.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
        
        logger.info("getting event status");
        GetEventStatusAPI getGeteventStatusAPI = new GetEventStatusAPI();
        HashMap<String, String> getGeteventStatusAPIresponse = getGeteventStatusAPI.getEventDetails(createeventapirespone);
        logger.info("Get event status code :::" + createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode));
        assertionapiresponse.verifyAssert_httpCode(getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	}

	@Test(description = "To verify Scheduled event status with Account Admin ",groups = {GETEVENTSTATUS})
	public void TC_06_GET_EventStatus_Scheduled_EventAdmin_Positive() throws ParseException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
	 	UserServices userservices = new UserServices();
	    createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_10);
		apibeanfactory.EventBean(createEventBean);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
			
		//EventAdminId
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		HashMap<String,String> eventAdminId1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId1.get(IAPIConstantCodes.APIResponseHttpCode), 
		HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID),eventAdminId1.get(IUserAccountsService.USERID)};
		
		createEventBean.setEventAdminId(eventadminIdArray);	
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), 
        HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
		createEventBean.setDescription("");
		createEventBean.setShortcut("");
		
		//create Event..
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
	  
	    logger.info("Selenium Code is excuting");
	    launchURL(surl);
	    customReport.reporter("Application launch with URL : ", surl);
	    homePage = loginPage.loginAs(sUserName, sPassword);
	    AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	    AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	    AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	    avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());
	    avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	   
	    HashMap<String,String> eventDetails=avengereventdetailspage.get_EventData();	    
	    ArrayList<String> ehUsers = avengereventdetailspage.get_EventHostUsers();	            
	    customReport.customizedReport(createEventBean.getTitle(),eventDetails.get(IUserAccountsService.TITLE), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(createeventapirespone.get(IUserAccountsService.EVENTID),avengereventdetailspage.getvideoID(), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,ehUsers.contains("apimau10 apimau10"), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,ehUsers.contains("apieau10 apieau10"), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,avengereventdetailspage.verify_eventlisting("Public"),statusValue, driver, sTestcaseName);
	   // customReport.customizedReport("15",eventDetails.get(ICreateEventService.lobbyTime) ,statusValue, driver, sTestcaseName);
	    customReport.customizedReport("Presentation",avengereventdetailspage.verify_videosourcetype(),statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);
	    
	   //get event status  
	   logger.info("get event status");
	   GetEventStatusAPI getGeteventStatusAPI = new GetEventStatusAPI();
	   HashMap<String, String> getGeteventStatusAPIresponse = getGeteventStatusAPI.getEventDetails(createeventapirespone);
	   logger.info("Get event status code :::" + createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode));
	   assertionapiresponse.verifyAssert_httpCode(getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
	   customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	}
	
	@Test(description = "To verify InProgress event status with Account Admin ",groups = {GETEVENTSTATUS})
	public void TC_07_StartEventAPI_InProgress_EventAdmin_Positive() throws ParseException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
	 	UserServices userservices = new UserServices();
	    createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_10);
		apibeanfactory.EventBean(createEventBean);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
			
		//EventAdminId
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		HashMap<String,String> eventAdminId1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID),eventAdminId1.get(IUserAccountsService.USERID)};
		
		createEventBean.setEventAdminId(eventadminIdArray);		
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		createEventBean.setDescription("");
		createEventBean.setShortcut("");
		
		//create Event..
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
	  
	    logger.info("Selenium Code is excuting");
	    launchURL(surl);
	    customReport.reporter("Application launch with URL : ", surl);
	    homePage = loginPage.loginAs(sUserName, sPassword);
	    AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	    AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	    AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	    avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());
	    avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	   
	    HashMap<String,String> eventDetails=avengereventdetailspage.get_EventData();	    
	    ArrayList<String> ehUsers = avengereventdetailspage.get_EventHostUsers();	            
	    customReport.customizedReport(createEventBean.getTitle(),eventDetails.get(IUserAccountsService.TITLE), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(createeventapirespone.get(IUserAccountsService.EVENTID),avengereventdetailspage.getvideoID(), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,ehUsers.contains("apimau10 apimau10"), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,ehUsers.contains("apieau10 apieau10"), statusValue, driver, sTestcaseName);
	    customReport.customizedReport("Presentation",avengereventdetailspage.verify_videosourcetype(),statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,avengereventdetailspage.verify_eventlisting("Public"),statusValue, driver, sTestcaseName);
	   // customReport.customizedReport("15",eventDetails.get(ICreateEventService.lobbyTime) ,statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);
	    loginapiresponse = new HashMap<String, String>();
	    createeventapirespone.putAll(eventAdminId1);
	        
	    //Start Event
	    logger.info("Start Event API started");
	    GetEventStatusAPI starteventapi = new GetEventStatusAPI();
	    HashMap<String, String> getstatus = starteventapi.startevent(createeventapirespone);
	    assertionapiresponse.verifyAssert_httpCode(getstatus.get(IAPIConstantCodes.APIResponseHttpCode)+getstatus.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	   
	    logger.info("getting event status");
	    GetEventStatusAPI getGeteventStatusAPI = new GetEventStatusAPI();
	    HashMap<String, String> getGeteventStatusAPIresponse = getGeteventStatusAPI.getEventDetails(createeventapirespone);
	    logger.info("Get event status code :::" + createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode));
	    assertionapiresponse.verifyAssert_httpCode(getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    
	}
	
	@Test(description = "To verify Broadcasting event status with Account Admin ",groups = {GETEVENTSTATUS})
	public void TC_08_GET_EventStatus_Broadcasting_withBroadcastAPI_EventAdmin_Positive() throws ParseException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
	 	UserServices userservices = new UserServices();
	    createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_30);
		apibeanfactory.EventBean(createEventBean);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
			
		//EventAdminId
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		HashMap<String,String> eventAdminId1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);		
		assertionapiresponse.verifyAssert_httpCode(eventAdminId1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID),eventAdminId1.get(IUserAccountsService.USERID)};
		
		createEventBean.setEventAdminId(eventadminIdArray);		
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);	
		createEventBean.setDescription("");
		createEventBean.setShortcut("");
		
		//create Event..
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
	  
	    logger.info("Selenium Code is excuting");
	    launchURL(surl);
	    customReport.reporter("Application launch with URL : ", surl);
	    homePage = loginPage.loginAs(sUserName, sPassword);
	    AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	    AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	    AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	    avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());
	    avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	   
	    HashMap<String,String> eventDetails=avengereventdetailspage.get_EventData();	    
	    ArrayList<String> ehUsers = avengereventdetailspage.get_EventHostUsers();	            
	    customReport.customizedReport(createEventBean.getTitle(),eventDetails.get(IUserAccountsService.TITLE), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(createeventapirespone.get(IUserAccountsService.EVENTID),avengereventdetailspage.getvideoID(), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,ehUsers.contains("apimau10 apimau10"), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,ehUsers.contains("apieau10 apieau10"), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,avengereventdetailspage.verify_eventlisting("Public"),statusValue, driver, sTestcaseName);
	   // customReport.customizedReport("15",eventDetails.get(ICreateEventService.lobbyTime) ,statusValue, driver, sTestcaseName);
	    customReport.customizedReport("Presentation",avengereventdetailspage.verify_videosourcetype(),statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);
	    loginapiresponse = new HashMap<String, String>();
	    createeventapirespone.putAll(eventAdminId1);
	        
	    //Start Event
	    logger.info("Executing Start Event API");
	    GetEventStatusAPI starteventapi = new GetEventStatusAPI();
	    HashMap<String, String> getstatus = starteventapi.startevent(createeventapirespone);
	    assertionapiresponse.verifyAssert_httpCode(getstatus.get(IAPIConstantCodes.APIResponseHttpCode)+getstatus.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    logger.info("Executing Broadcast Event API");
	    
	    HashMap<String, String> endevent = starteventapi.broadcastevent(createeventapirespone);
	    assertionapiresponse.verifyAssert_httpCode(getstatus.get(IAPIConstantCodes.APIResponseHttpCode)+getstatus.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    
	    logger.info("getting event status");
	    GetEventStatusAPI getGeteventStatusAPI = new GetEventStatusAPI();
	    HashMap<String, String> getGeteventStatusAPIresponse = getGeteventStatusAPI.getEventDetails(createeventapirespone);
	    logger.info("Get event status code :::" + createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode));
	    assertionapiresponse.verifyAssert_httpCode(getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

	}
	
	@Test(description = "To verify Completed event status with Account Admin ",groups = {GETEVENTSTATUS})
	public void TC_09_GET_EventStatus_Completed_withEndWebcastAPI_EventAdmin_Positive() throws ParseException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
	 	UserServices userservices = new UserServices();
	    createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_10);
		apibeanfactory.EventBean(createEventBean);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
			
		//EventAdminId
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		HashMap<String,String> eventAdminId1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID),eventAdminId1.get(IUserAccountsService.USERID)};
		
		createEventBean.setEventAdminId(eventadminIdArray);		
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		createEventBean.setDescription("");
		createEventBean.setShortcut("");
		
		//create Event..
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
	  
	    logger.info("Selenium Code is excuting");
	    launchURL(surl);
	    customReport.reporter("Application launch with URL : ", surl);
	    homePage = loginPage.loginAs(sUserName, sPassword);
	    AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	    AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	    AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	    avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());
	    avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	   
	    HashMap<String,String> eventDetails=avengereventdetailspage.get_EventData();	    
	    ArrayList<String> ehUsers = avengereventdetailspage.get_EventHostUsers();	            
	    customReport.customizedReport(createEventBean.getTitle(),eventDetails.get(IUserAccountsService.TITLE), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(createeventapirespone.get(IUserAccountsService.EVENTID),avengereventdetailspage.getvideoID(), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,ehUsers.contains("apimau10 apimau10"), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,ehUsers.contains("apieau10 apieau10"), statusValue, driver, sTestcaseName);
	    customReport.customizedReport("Presentation",avengereventdetailspage.verify_videosourcetype(),statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,avengereventdetailspage.verify_eventlisting("Public"),statusValue, driver, sTestcaseName);
	   // customReport.customizedReport("15",eventDetails.get(ICreateEventService.lobbyTime) ,statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);
	    loginapiresponse = new HashMap<String, String>();
	    createeventapirespone.putAll(eventAdminId1);    
	    
	    //Start Event
	    logger.info("Executing Start Event API");
	    GetEventStatusAPI starteventapi = new GetEventStatusAPI();
	    HashMap<String, String> getstatus = starteventapi.startevent(createeventapirespone);
	    assertionapiresponse.verifyAssert_httpCode(getstatus.get(IAPIConstantCodes.APIResponseHttpCode)+getstatus.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    logger.info("Executing End Event API");
	   
	    HashMap<String, String> endevent = starteventapi.endevent(createeventapirespone);
	    assertionapiresponse.verifyAssert_httpCode(getstatus.get(IAPIConstantCodes.APIResponseHttpCode)+getstatus.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    
	    logger.info("getting event status");
	    GetEventStatusAPI getGeteventStatusAPI = new GetEventStatusAPI();
	    HashMap<String, String> getGeteventStatusAPIresponse = getGeteventStatusAPI.getEventDetails(createeventapirespone);
	    logger.info("Get event status code :::" + createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode));
	    assertionapiresponse.verifyAssert_httpCode(getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	}
	
	@Test(description = "To verify Scheduled event status with Account Admin ",groups = {GETEVENTSTATUS})
	public void TC_10_GET_EventStatus_Scheduled_EventHost_Positive() throws ParseException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
	 	UserServices userservices = new UserServices();
	    createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_10);
		apibeanfactory.EventBean(createEventBean);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
			
		//EventAdminId
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		HashMap<String,String> eventAdminId1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID),eventAdminId1.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);	
		createEventBean.setDescription("");
		createEventBean.setShortcut("");
		
		//create Event..
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
	  
	    logger.info("Selenium Code is excuting");
	    launchURL(surl);
	    customReport.reporter("Application launch with URL : ", surl);
	    homePage = loginPage.loginAs(sUserName, sPassword);
	    AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	    AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	    AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	    avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());
	    avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	   
	    HashMap<String,String> eventDetails=avengereventdetailspage.get_EventData();	    
	    ArrayList<String> ehUsers = avengereventdetailspage.get_EventHostUsers();
	    customReport.customizedReport(createEventBean.getTitle(),eventDetails.get(IUserAccountsService.TITLE), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(createeventapirespone.get(IUserAccountsService.EVENTID),avengereventdetailspage.getvideoID(), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,ehUsers.contains("apimau10 apimau10"), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,ehUsers.contains("apieau10 apieau10"), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,avengereventdetailspage.verify_eventlisting("Public"),statusValue, driver, sTestcaseName);
	    //customReport.customizedReport("15",eventDetails.get(ICreateEventService.lobbyTime) ,statusValue, driver, sTestcaseName);
	    customReport.customizedReport("Presentation",avengereventdetailspage.verify_videosourcetype(),statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);
	    
	   //get event status 	    
	   logger.info("get event status");
	   GetEventStatusAPI getGeteventStatusAPI = new GetEventStatusAPI();
	   HashMap<String, String> getGeteventStatusAPIresponse = getGeteventStatusAPI.getEventDetails(createeventapirespone);
	   logger.info("Get event status code :::" + createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode));
	   assertionapiresponse.verifyAssert_httpCode(getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
	   customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	}
	
	@Test(description = "To verify InProgress event status with Account Admin ",groups = {GETEVENTSTATUS})
	public void TC_11_StartEventAPI_InProgress_EventHost_Positive() throws ParseException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
	 	UserServices userservices = new UserServices();
	    createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_10);
		apibeanfactory.EventBean(createEventBean);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
			
		//EventAdminId
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		HashMap<String,String> eventAdminId1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID),eventAdminId1.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
		createEventBean.setDescription("");
		createEventBean.setShortcut("");
		
		//create Event..
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
	  
	    logger.info("Selenium Code is excuting");
	    launchURL(surl);
	    customReport.reporter("Application launch with URL : ", surl);
	    homePage = loginPage.loginAs(sUserName, sPassword);
	    AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	    AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	    AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	    avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());
	    avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	    
	    HashMap<String,String> eventDetails=avengereventdetailspage.get_EventData();	    
	    ArrayList<String> ehUsers = avengereventdetailspage.get_EventHostUsers();	            
	    customReport.customizedReport(createEventBean.getTitle(),eventDetails.get(IUserAccountsService.TITLE), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(createeventapirespone.get(IUserAccountsService.EVENTID),avengereventdetailspage.getvideoID(), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,ehUsers.contains("apimau10 apimau10"), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,ehUsers.contains("apieau10 apieau10"), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,avengereventdetailspage.verify_eventlisting("Public"),statusValue, driver, sTestcaseName);
	    customReport.customizedReport("Presentation",avengereventdetailspage.verify_videosourcetype(),statusValue, driver, sTestcaseName);
	   // customReport.customizedReport("15",eventDetails.get(ICreateEventService.lobbyTime) ,statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);
	    loginapiresponse = new HashMap<String, String>();
	    createeventapirespone.putAll(eventAdminId1);
	    	    
	    //Start Event
	    logger.info("Start Event API started");
	    GetEventStatusAPI starteventapi = new GetEventStatusAPI();
	    HashMap<String, String> getstatus = starteventapi.startevent(createeventapirespone);
	    assertionapiresponse.verifyAssert_httpCode(getstatus.get(IAPIConstantCodes.APIResponseHttpCode)+getstatus.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	   
	    logger.info("getting event status");
	    GetEventStatusAPI getGeteventStatusAPI = new GetEventStatusAPI();
	    HashMap<String, String> getGeteventStatusAPIresponse = getGeteventStatusAPI.getEventDetails(createeventapirespone);
	    logger.info("Get event status code :::" + createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode));
	    assertionapiresponse.verifyAssert_httpCode(getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		}
	
	@Test(description = "To verify Broadcasting event status with Account Admin ",groups = {GETEVENTSTATUS})
	public void TC_12_GET_EventStatus_Broadcasting_withBroadcastAPI_EventHost_Positive() throws ParseException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
	 	UserServices userservices = new UserServices();
	    createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_30);
		apibeanfactory.EventBean(createEventBean);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
			
		//EventAdminId
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		HashMap<String,String> eventAdminId1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID),eventAdminId1.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
		createEventBean.setDescription("");
		createEventBean.setShortcut("");
		
		//create Event..
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
	  
	    logger.info("Selenium Code is excuting");
	    launchURL(surl);
	    customReport.reporter("Application launch with URL : ", surl);
	    homePage = loginPage.loginAs(sUserName, sPassword);
	    AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	    AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	    AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	    avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());
	    avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	    
	    HashMap<String,String> eventDetails=avengereventdetailspage.get_EventData();	    
	    ArrayList<String> ehUsers = avengereventdetailspage.get_EventHostUsers();	            
	    customReport.customizedReport(createEventBean.getTitle(),eventDetails.get(IUserAccountsService.TITLE), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(createeventapirespone.get(IUserAccountsService.EVENTID),avengereventdetailspage.getvideoID(), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,ehUsers.contains("apimau10 apimau10"), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,ehUsers.contains("apieau10 apieau10"), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,avengereventdetailspage.verify_eventlisting("Public"),statusValue, driver, sTestcaseName);
	    customReport.customizedReport("Presentation",avengereventdetailspage.verify_videosourcetype(),statusValue, driver, sTestcaseName);
	    //customReport.customizedReport("15",eventDetails.get(ICreateEventService.lobbyTime) ,statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);
	    loginapiresponse = new HashMap<String, String>();
	    createeventapirespone.putAll(eventAdminId1);
	        
	    //Start Event
	    logger.info("Executing Start Event API");
	    GetEventStatusAPI starteventapi = new GetEventStatusAPI();
	    HashMap<String, String> getstatus = starteventapi.startevent(createeventapirespone);
	    assertionapiresponse.verifyAssert_httpCode(getstatus.get(IAPIConstantCodes.APIResponseHttpCode)+getstatus.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    logger.info("Executing Broadcast Event API");
	   
	    HashMap<String, String> endevent = starteventapi.broadcastevent(createeventapirespone);
	    assertionapiresponse.verifyAssert_httpCode(getstatus.get(IAPIConstantCodes.APIResponseHttpCode)+getstatus.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    
	    logger.info("getting event status");
	    GetEventStatusAPI getGeteventStatusAPI = new GetEventStatusAPI();
	    HashMap<String, String> getGeteventStatusAPIresponse = getGeteventStatusAPI.getEventDetails(createeventapirespone);
	    logger.info("Get event status code :::" + createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode));
	    assertionapiresponse.verifyAssert_httpCode(getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

	}
	
	@Test(description = "To verify Completed event status with Account Admin ",groups = {GETEVENTSTATUS})
	public void TC_13_GET_EventStatus_Completed_withEndWebcastAPI_EventHost_Positive() throws ParseException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
	 	UserServices userservices = new UserServices();
	    createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_10);
		apibeanfactory.EventBean(createEventBean);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
			
		//EventAdminId
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		HashMap<String,String> eventAdminId1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID),eventAdminId1.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
		createEventBean.setDescription("");
		createEventBean.setShortcut("");
		
		//create Event..
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
	  
	    logger.info("Selenium Code is excuting");
	    launchURL(surl);
	    customReport.reporter("Application launch with URL : ", surl);
	    homePage = loginPage.loginAs(sUserName, sPassword);
	    AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	    AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	    AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	    avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());
	    avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	    
	    HashMap<String,String> eventDetails=avengereventdetailspage.get_EventData();	    
	    ArrayList<String> ehUsers = avengereventdetailspage.get_EventHostUsers();	            
	    customReport.customizedReport(createEventBean.getTitle(),eventDetails.get(IUserAccountsService.TITLE), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(createeventapirespone.get(IUserAccountsService.EVENTID),avengereventdetailspage.getvideoID(), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,ehUsers.contains("apimau10 apimau10"), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,ehUsers.contains("apieau10 apieau10"), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,avengereventdetailspage.verify_eventlisting("Public"),statusValue, driver, sTestcaseName);
	   // customReport.customizedReport("15",eventDetails.get(ICreateEventService.lobbyTime) ,statusValue, driver, sTestcaseName);
	    customReport.customizedReport("Presentation",avengereventdetailspage.verify_videosourcetype(),statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);
	    loginapiresponse = new HashMap<String, String>();
	    createeventapirespone.putAll(eventAdminId1);
	        
	   //Start Event
	    logger.info("Executing Start Event API");
	    GetEventStatusAPI starteventapi = new GetEventStatusAPI();
	    HashMap<String, String> getstatus = starteventapi.startevent(createeventapirespone);
	    assertionapiresponse.verifyAssert_httpCode(getstatus.get(IAPIConstantCodes.APIResponseHttpCode)+getstatus.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    logger.info("Executing End Event API");
	   
	    HashMap<String, String> endevent = starteventapi.endevent(createeventapirespone);
	    assertionapiresponse.verifyAssert_httpCode(getstatus.get(IAPIConstantCodes.APIResponseHttpCode)+getstatus.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    
	    logger.info("getting event status");
	    GetEventStatusAPI getGeteventStatusAPI = new GetEventStatusAPI();
	    HashMap<String, String> getGeteventStatusAPIresponse = getGeteventStatusAPI.getEventDetails(createeventapirespone);
	    logger.info("Get event status code :::" + createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode));
	    assertionapiresponse.verifyAssert_httpCode(getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGeteventStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	}
	
	
	@Test(description = "To verify Get event status with admin account and get htmlcomment verify with font as bold",groups = {GETEVENTSTATUS})
	public void TC_01_GET_GetWebcastCommentsLog_withfont_asbold_Positive_AV29356() throws Exception {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
	 	UserServices userservices = new UserServices();
	    createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_10);
		apibeanfactory.EventBean(createEventBean);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
			
		//EventAdminId
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
				
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
		createEventBean.setDescription("");
		createEventBean.setShortcut("");
		
		//create Event..
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
	    loginapiresponse.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));        

	    
	    
	    logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
		AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
		avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());
		avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
		avengereventdetailspage.start_Webcast();
		Thread.sleep(7000);
		avengereventwebcastpage.clickChatButton();
		String comment=apiutils.submiteventcomment();
		String htmlcomment=avengereventwebcastpage.enterCommentwithfonts(comment,IAPIConstantCodes.Bold);
		   
		GetEventStatusAPI geteventstatusapi= new GetEventStatusAPI();
	    JSONObject geteventstatusresponse=geteventstatusapi.getWebcastComments(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(geteventstatusresponse.get(IAPIConstantCodes.APIResponseHttpCode)+geteventstatusresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, geteventstatusresponse.get(IAPIConstantCodes.APIResponseHttpCode)+geteventstatusresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
	
	    //assert it with htmlcomment if it is bold
	    customReport.customizedReport(htmlcomment,geteventstatusresponse.get("htmlComment").toString(),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	
	@Test(description = "To verify Get event status with admin account and get htmlcomment verify with font as italic  ",groups = {GETEVENTSTATUS})
	public void TC_02_GET_GetWebcastCommentsLog_withfont_asItalic_Positive_AV29356() throws Exception {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
	 	UserServices userservices = new UserServices();
	    createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_10);
		apibeanfactory.EventBean(createEventBean);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
			
		//EventAdminId
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
				
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
		createEventBean.setDescription("");
		createEventBean.setShortcut("");
		
		//create Event..
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    loginapiresponse.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));        
        
       	   
	    logger.info("Selenium Code is excuting");
	    launchURL(surl);
	    customReport.reporter("Application launch with URL : ", surl);
	    homePage = loginPage.loginAs(sUserName, sPassword);
	    AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	    AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	    AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	    avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());
	    avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	    avengereventdetailspage.start_Webcast();
	    Thread.sleep(7000);
	    avengereventwebcastpage.clickChatButton();
        String htmlcomment=avengereventwebcastpage.enterCommentwithfonts(apiutils.submiteventcomment(),IAPIConstantCodes.Italic);
		   
	    GetEventStatusAPI geteventstatusapi= new GetEventStatusAPI();
	    JSONObject geteventstatusresponse=geteventstatusapi.getWebcastComments(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(geteventstatusresponse.get(IAPIConstantCodes.APIResponseHttpCode)+geteventstatusresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, geteventstatusresponse.get(IAPIConstantCodes.APIResponseHttpCode)+geteventstatusresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
	
	    //assert it with htmlcomment if it is italic
	    customReport.customizedReport(htmlcomment,geteventstatusresponse.get("htmlComment").toString(),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify Get event status with admin account and get htmlcomment verify with font as underline ",groups = {GETEVENTSTATUS})
	public void TC_03_GET_GetWebcastCommentsLog_withfont_asUnderline_Positive_AV29356() throws Exception {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
	 	UserServices userservices = new UserServices();
	    createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_10);
		apibeanfactory.EventBean(createEventBean);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
			
		//EventAdminId
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
				
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
		createEventBean.setDescription("");
		createEventBean.setShortcut("");
		
		//create Event..
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    loginapiresponse.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));        
		 
	    
	    logger.info("Selenium Code is excuting");
	    launchURL(surl);
	    customReport.reporter("Application launch with URL : ", surl);
	    homePage = loginPage.loginAs(sUserName, sPassword);
	    AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	    AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	    AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	    avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());
	    avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	    avengereventdetailspage.start_Webcast();
	    Thread.sleep(7000);
	    avengereventwebcastpage.clickChatButton();
	    String htmlcomment=avengereventwebcastpage.enterCommentwithfonts(apiutils.submiteventcomment(),IAPIConstantCodes.Underline);
		   
	    GetEventStatusAPI geteventstatusapi= new GetEventStatusAPI();
	    JSONObject geteventstatusresponse=geteventstatusapi.getWebcastComments(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(geteventstatusresponse.get(IAPIConstantCodes.APIResponseHttpCode)+geteventstatusresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, geteventstatusresponse.get(IAPIConstantCodes.APIResponseHttpCode)+geteventstatusresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);

	    //assert it with htmlcomment if it is underline
	    customReport.customizedReport(htmlcomment,geteventstatusresponse.get(IAPIConstantCodes.htmlComment).toString(),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify Get event status with admin account and get comment and verify it",groups = {GETEVENTSTATUS})
	public void TC_04_GET_GetWebcastCommentsLog_withcomment_Positive_AV29356() throws Exception {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
	 	UserServices userservices = new UserServices();
	    createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_10);
		apibeanfactory.EventBean(createEventBean);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
			
		//EventAdminId
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
				
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
		createEventBean.setDescription("");
		createEventBean.setShortcut("");
		
		//create Event..
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    loginapiresponse.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));        
		 
	    
	    logger.info("Selenium Code is excuting");
	    launchURL(surl);
	    customReport.reporter("Application launch with URL : ", surl);
	    homePage = loginPage.loginAs(sUserName, sPassword);
	    AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	    AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	    AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	    avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());
	    avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	    avengereventdetailspage.start_Webcast();
	    Thread.sleep(7000);
	    avengereventwebcastpage.clickChatButton();
	    Map<String, String> commentdetails=avengereventwebcastpage.enterComment(apiutils.submiteventcomment());

	    GetEventStatusAPI geteventstatusapi= new GetEventStatusAPI();
	    JSONObject geteventstatusresponse=geteventstatusapi.getWebcastComments(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(geteventstatusresponse.get(IAPIConstantCodes.APIResponseHttpCode)+geteventstatusresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, geteventstatusresponse.get(IAPIConstantCodes.APIResponseHttpCode)+geteventstatusresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
	
	    //assert it with comment
	    customReport.customizedReport(commentdetails.get(IAPIConstantCodes.COMMENT).toString(),geteventstatusresponse.get(IAPIConstantCodes.COMMENT).toString(),statusValue, driver, sTestcaseName);
       	customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify Get event status with admin account and get comment date and verify it",groups = {GETEVENTSTATUS})
	public void TC_05_GET_GetWebcastCommentsLog_withcommentdate_Positive_AV29356() throws Exception {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
	 	UserServices userservices = new UserServices();
	    createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_10);
		apibeanfactory.EventBean(createEventBean);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
			
		//EventAdminId
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
				
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
		createEventBean.setDescription("");
		createEventBean.setShortcut("");
		
		//create Event..
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    loginapiresponse.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));        
		 
	    
	    logger.info("Selenium Code is excuting");
	    launchURL(surl);
	    customReport.reporter("Application launch with URL : ", surl);
	    homePage = loginPage.loginAs(sUserName, sPassword);
	    AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	    AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	    AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	    avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());
	    avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	    avengereventdetailspage.start_Webcast();
	    Thread.sleep(7000);
	    avengereventwebcastpage.clickChatButton();
	    Map<String, String> commentdetails=avengereventwebcastpage.enterComment(apiutils.submiteventcomment()); 

	    GetEventStatusAPI geteventstatusapi= new GetEventStatusAPI();
	    JSONObject geteventstatusresponse=geteventstatusapi.getWebcastComments(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(geteventstatusresponse.get(IAPIConstantCodes.APIResponseHttpCode)+geteventstatusresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, geteventstatusresponse.get(IAPIConstantCodes.APIResponseHttpCode)+geteventstatusresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		
	    //assert it with comment date
	    customReport.customizedReport(commentdetails.get("commentdate"),geteventstatusresponse.get("date").toString(),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	@Test(description = "To verify Get event status with admin account and try with comment as empty get comment and verify it",groups = {GETEVENTSTATUS})
	public void TC_06_GET_GetWebcastCommentsLog_withcomment_AsEmpty_Positive_AV29356() throws Exception {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
	 	UserServices userservices = new UserServices();
	    createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_10);
		apibeanfactory.EventBean(createEventBean);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
			
		//EventAdminId
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
				
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
		createEventBean.setDescription("");
		createEventBean.setShortcut("");
		
		//create Event..
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    loginapiresponse.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));        
		 
	    GetEventStatusAPI geteventstatusapi= new GetEventStatusAPI();
	    JSONObject starteventresponse=geteventstatusapi.starteventjsonobject(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(starteventresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	   
	    JSONArray geteventstatusresponse=geteventstatusapi.getWebcastCommentsasempty(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(geteventstatusresponse.get(0)+geteventstatusresponse.get(1).toString(),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, geteventstatusresponse.get(0)+geteventstatusresponse.get(1).toString(),statusValue, driver, sTestcaseName);
	  
		//assert it with comment as empty
		customReport.customizedReport(2,geteventstatusresponse.size(),statusValue, driver, sTestcaseName);	
		customReport.checkinglist(statusValue);
	   
	}

	
public void testsplit(){
		
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
		AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	
		String title=driver.getCurrentUrl();
		String [] eventTitle= title.split("/");
	    String	eventId=	eventTitle[5];
	}
	
	public String create_groupfromUI(){
			launchURL(surl);
			customReport.reporter("Application launch with URL : ", surl);
			homePage = loginPage.loginAs(sUserName,sPassword);
			homePage.clickSettingsLink();
			avengergroupspage=homePage.clickGroupsLink();
		    String grpname=avengergroupspage.createNewgroup(avengergroupsbeanpage);
			avengergroupsbeanpage.setNewgroup(grpname);
			avengergroupspage.clickNewGroup(avengergroupsbeanpage);
			String url=homePage.getCurrentURL();
			logger.info("url.split -"+url.split("/")[8]);
		 	//browserQuit();
			homePage.click_logout();
			return url.split("/")[8];
		}
	
	public void delete_assignedGroupsForUser(String username) {
		
		launchURL(surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengeruserdashboardpage=avengerHomePage.clickUsersLink();
		avengeruserdashboardpage.clicknewuser_link(username);
		avengeruserdashboardpage.get_AllAssignedgroupsandDelete();
		avengerHomePage.click_logout();
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