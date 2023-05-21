package com.vbrick.avenger.videocommentapi_AV_9879_testcases;

import java.net.MalformedURLException;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;
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
import com.vbricks.avenger.serviceimpl.GetEventStatusAPI;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class EndEvent extends TestBase {

	private static Logger logger = Logger.getLogger(EndEvent.class);
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
		accountBeansFactory.AvengergroupsBean(avengergroupsbeanpage);
		accountBeansFactory.createPresentationProfile(addpresentationprofilebeanpage);
		accountBeansFactory.AddNewDmeBean(addnewdmedevicebeanpage);
		accountBeansFactory.addCustomDeviceBean(addcustomdevicebeanpage);

	}

	@Test(description = "To verify End WebCast API with Account Admin ",groups = {ENDWEBCAST})
	public void TC_01_DELETE_End_WebCast_AccoutAdmin_Positive() throws ParseException {

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
	  
        //Start Event
	    createeventapirespone.put(IAPIConstantCodes.APIRequestAccessToken,loginapiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
        logger.info("Executing Start Event API");
        GetEventStatusAPI starteventapi = new GetEventStatusAPI();
        HashMap<String, String> starteventapiresponse = starteventapi.startevent(createeventapirespone);
        assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+starteventapiresponse.get("statusInfo"),statusValue, driver, sTestcaseName);
        
        //End Event  
        logger.info("Executing End Event API");
        HashMap<String, String> endevent = starteventapi.endevent(createeventapirespone);
        assertionapiresponse.verifyAssert_httpCode(endevent.get(IAPIConstantCodes.APIResponseHttpCode)+endevent.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,endevent.get(IAPIConstantCodes.APIResponseHttpCode) + endevent.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
	} 
	
	@Test(description = "To verify EndWebCast API with Event Admin when event is created by Account Admin",groups = {GETEVENTSTATUS})
	public void TC_02_EndEventAPI_with_EventAdmin_Negative() throws ParseException {
		//create an event with AA and End with EA ***Event Admin can end an event only when he is given as Event Host of the webcast*** (In this case, EA is not an event host So, EA cannot end the Event)

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
	  
	    //Start Event
	    createeventapirespone.put(IAPIConstantCodes.APIRequestAccessToken,loginapiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
        logger.info("Start Event API started");     
        GetEventStatusAPI starteventapi = new GetEventStatusAPI();
        HashMap<String, String> starteventapiresponse = starteventapi.startevent(createeventapirespone);
        assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+starteventapiresponse.get("statusInfo"),statusValue, driver, sTestcaseName);
        
        loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.EndWebCast_EventAdmin), surl);
        assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
        logger.info("Login API response Code :::" + loginapiresponse1);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		 
        //End Event
        logger.info("Executing End Event API");
        loginapiresponse1.put("eventId", createeventapirespone.get("eventId"));
        HashMap<String, String> endevent = starteventapi.endevent(loginapiresponse1);
        assertionapiresponse.verifyAssert_httpCode(endevent.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
        customReport.customizedReport(HttpStatusCode.httpsStatus401,endevent.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify EndWebCast API with Event Host when event is created by Account Admin",groups = {GETEVENTSTATUS})
	public void TC_03_EndEventAPI_with_EventHost_Negative() throws ParseException {
		//create an event with AA and End with EH ***Event Host can End the event only when he is given as Event Host of the webcast*** (In this case, EH is not an event host So, EH cannot End the Event)

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
	  
	    //Start Event
	    createeventapirespone.put(IAPIConstantCodes.APIRequestAccessToken,loginapiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
        logger.info("Start Event API started");     
        GetEventStatusAPI starteventapi = new GetEventStatusAPI();
        HashMap<String, String> starteventapiresponse = starteventapi.startevent(createeventapirespone);
        assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+starteventapiresponse.get("statusInfo"),statusValue, driver, sTestcaseName);
        
        loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.EndWebCast_EventHost), surl);
        assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
        logger.info("Login API response Code :::" + loginapiresponse1);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		 
        //End Event
        logger.info("Executing End Event API");
        loginapiresponse1.put("eventId", createeventapirespone.get("eventId"));
        HashMap<String, String> endevent = starteventapi.endevent(loginapiresponse1);
        assertionapiresponse.verifyAssert_httpCode(endevent.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
        customReport.customizedReport(HttpStatusCode.httpsStatus401,endevent.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
	   
	}
	
	@Test(description = "To verify EndWebCast API with Media Admin when event is created by Account Admin",groups = {GETEVENTSTATUS})
	public void TC_04_EndEventAPI_with_MediaAdmin_Negative() throws ParseException {
		//create an event with AA and End with MA ***Media Admin can End the event only when he is given as Event Host of the webcast*** (In this case, MA is not an event host So, EH cannot End the Event)

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
	  
	    //Start Event
	    createeventapirespone.put(IAPIConstantCodes.APIRequestAccessToken,loginapiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
        logger.info("Start Event API started");     
        GetEventStatusAPI starteventapi = new GetEventStatusAPI();
        HashMap<String, String> starteventapiresponse = starteventapi.startevent(createeventapirespone);
        assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+starteventapiresponse.get("statusInfo"),statusValue, driver, sTestcaseName);
        
        loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.EndWebCast_MediaAdmin), surl);
        assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
        logger.info("Login API response Code :::" + loginapiresponse1);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		 
        //End Event
        logger.info("Executing End Event API");
        loginapiresponse1.put("eventId", createeventapirespone.get("eventId"));
        HashMap<String, String> endevent = starteventapi.endevent(loginapiresponse1);
        assertionapiresponse.verifyAssert_httpCode(endevent.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
        customReport.customizedReport(HttpStatusCode.httpsStatus401,endevent.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify EndWebCast API with Media Viewer when event is created by Account Admin",groups = {GETEVENTSTATUS})
	public void TC_05_EndEventAPI_with_MediaViewer_Negative() throws ParseException {
		//create an event with AA and End with MV ***MV cannot End the Event***

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
	  
	    //Start Event
	    createeventapirespone.put(IAPIConstantCodes.APIRequestAccessToken,loginapiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
        logger.info("Start Event API started");     
        GetEventStatusAPI starteventapi = new GetEventStatusAPI();
        HashMap<String, String> starteventapiresponse = starteventapi.startevent(createeventapirespone);
        assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+starteventapiresponse.get("statusInfo"),statusValue, driver, sTestcaseName);
        
        loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.EndWebCast_MediaViewer), surl);
        assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
        logger.info("Login API response Code :::" + loginapiresponse1);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		 
        //End Event
        logger.info("Executing End Event API");
        loginapiresponse1.put("eventId", createeventapirespone.get("eventId"));
        HashMap<String, String> endevent = starteventapi.endevent(loginapiresponse1);
        assertionapiresponse.verifyAssert_httpCode(endevent.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
        customReport.customizedReport(HttpStatusCode.httpsStatus401,endevent.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify EndWebCast API with Account Admin when event is created by Event Admin",groups = {GETEVENTSTATUS})
	public void TC_06_EndEventAPI_with_AccountAdmin_Positive() throws ParseException {
		//create an event with EA and End with AA ***Account Admin can End any Event***

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
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.EndWebCast_EventAdmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.EndWebCast_EventAdmin), surl);
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
	     
		//Start Event
	    createeventapirespone.put(IAPIConstantCodes.APIRequestAccessToken,loginapiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
        logger.info("Start Event API started");     
        GetEventStatusAPI starteventapi = new GetEventStatusAPI();
        HashMap<String, String> starteventapiresponse = starteventapi.startevent(createeventapirespone);
        assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+starteventapiresponse.get("statusInfo"),statusValue, driver, sTestcaseName);
        
        loginapiresponse1 = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
        //End Event
        logger.info("Executing End Event API");
        loginapiresponse1.put("eventId", createeventapirespone.get("eventId"));
        HashMap<String, String> endevent = starteventapi.endevent(loginapiresponse1);
        assertionapiresponse.verifyAssert_httpCode(endevent.get(IAPIConstantCodes.APIResponseHttpCode)+endevent.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, endevent.get(IAPIConstantCodes.APIResponseHttpCode)+endevent.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);    
        customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify EndWebCast API with Account Admin when event is created by Event Host",groups = {GETEVENTSTATUS})
	public void TC_07_StartEventAPI_with_AccountAdmin_Positive() throws ParseException {
		//create an event with EH and End with AA ***Account Admin can End any Event***

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
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.EndWebCast_EventHost), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.EndWebCast_EventHost), surl);
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
	  
	   //Start Event
	    createeventapirespone.put(IAPIConstantCodes.APIRequestAccessToken,loginapiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
        logger.info("Start Event API started");     
        GetEventStatusAPI starteventapi = new GetEventStatusAPI();
        HashMap<String, String> starteventapiresponse = starteventapi.startevent(createeventapirespone);
        assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+starteventapiresponse.get("statusInfo"),statusValue, driver, sTestcaseName);
        
        loginapiresponse1 = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
        //End Event
        logger.info("Executing End Event API");
        loginapiresponse1.put("eventId", createeventapirespone.get("eventId"));
        HashMap<String, String> endevent = starteventapi.endevent(loginapiresponse1);
        assertionapiresponse.verifyAssert_httpCode(endevent.get(IAPIConstantCodes.APIResponseHttpCode)+endevent.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, endevent.get(IAPIConstantCodes.APIResponseHttpCode)+endevent.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);    
        customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify EndWebCast API with Event Host when event is created by Account Admin & given EH as Event Host for the webcast",groups = {GETEVENTSTATUS})
	public void TC_08_EndEventAPI_with_EventHost_Positive() throws ParseException {
		//create an event with AA & set EH as an Event Host of the webcast & End the event with respective EH ***Since EH is the Event Host, he can End the event***

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
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.EndWebCast_EventHost), surl);
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
	  
	   //Start Event
	    loginapiresponse.put(IAPIConstantCodes.APIEVENTID,createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
        logger.info("Start Event API started");     
        GetEventStatusAPI starteventapi = new GetEventStatusAPI();
        HashMap<String, String> starteventapiresponse = starteventapi.startevent(loginapiresponse);
        assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+starteventapiresponse.get("statusInfo"),statusValue, driver, sTestcaseName);
        
        loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.EndWebCast_EventHost), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
        //End Event
        logger.info("Executing End Event API");
        loginapiresponse1.put("eventId", createeventapirespone.get("eventId"));
        HashMap<String, String> endevent = starteventapi.endevent(loginapiresponse1);
        assertionapiresponse.verifyAssert_httpCode(endevent.get(IAPIConstantCodes.APIResponseHttpCode)+endevent.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, endevent.get(IAPIConstantCodes.APIResponseHttpCode)+endevent.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);    
        customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify EndWebCast API with Event Admin when event is created by Account Admin & given EA as Event Host for the webcast",groups = {GETEVENTSTATUS})
	public void TC_09_EndEventAPI_with_EventAdmin_Positive() throws ParseException {
		//create an event with AA & set EA as an Event Host & End the event with EA ***Since EA is the Event Host, he can End the event***

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
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.EndWebCast_EventAdmin), surl);
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
	  
	    //Start Event
	    loginapiresponse.put(IAPIConstantCodes.APIEVENTID,createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
        logger.info("Start Event API started");     
        GetEventStatusAPI starteventapi = new GetEventStatusAPI();
        HashMap<String, String> starteventapiresponse = starteventapi.startevent(loginapiresponse);
        assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+starteventapiresponse.get("statusInfo"),statusValue, driver, sTestcaseName);
        
        loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.EndWebCast_EventAdmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
        //End Event
        logger.info("Executing End Event API");
        loginapiresponse1.put("eventId", createeventapirespone.get("eventId"));
        HashMap<String, String> endevent = starteventapi.endevent(loginapiresponse1);
        assertionapiresponse.verifyAssert_httpCode(endevent.get(IAPIConstantCodes.APIResponseHttpCode)+endevent.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, endevent.get(IAPIConstantCodes.APIResponseHttpCode)+endevent.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);    
        customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify EndWebCast API with Media Admin when event is created by Account Admin & given MA as Event Host for the webcast",groups = {GETEVENTSTATUS})
	public void TC_10_EndEventAPI_with_MediaAdmin_Positive() throws ParseException {
		//create an event with AA & set MA as an Event Host & End the event with EH ***Since MA is the Event Host, he can End the event***

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
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.EndWebCast_MediaAdmin), surl);
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
	  
	   //Start Event
	    loginapiresponse.put(IAPIConstantCodes.APIEVENTID,createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
        logger.info("Start Event API started");     
        GetEventStatusAPI starteventapi = new GetEventStatusAPI();
        HashMap<String, String> starteventapiresponse = starteventapi.startevent(loginapiresponse);
        assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+starteventapiresponse.get("statusInfo"),statusValue, driver, sTestcaseName);
        
        loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.EndWebCast_MediaAdmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
        //End Event
        logger.info("Executing End Event API");
        loginapiresponse1.put("eventId", createeventapirespone.get("eventId"));
        HashMap<String, String> endevent = starteventapi.endevent(loginapiresponse1);
        assertionapiresponse.verifyAssert_httpCode(endevent.get(IAPIConstantCodes.APIResponseHttpCode)+endevent.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, endevent.get(IAPIConstantCodes.APIResponseHttpCode)+endevent.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);    
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