
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
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.DateTime;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
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
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.ICreateEventService;
import com.vbricks.avenger.service.IUserAccountsService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.CreateCategoryAPI;
import com.vbricks.avenger.serviceimpl.CreateEventsAPI;
import com.vbricks.avenger.serviceimpl.CreateGroupAPI;
import com.vbricks.avenger.serviceimpl.EditGroupAPI;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class CreateEventApi_Av_9877 extends TestBase {

	private static Logger logger = Logger.getLogger(CreateEventApi_Av_9877.class);
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
		accountBeansFactory.AvengergroupsBean(avengergroupsbeanpage);
		accountBeansFactory.createPresentationProfile(addpresentationprofilebeanpage);
		accountBeansFactory.AddNewDmeBean(addnewdmedevicebeanpage);
		accountBeansFactory.addCustomDeviceBean(addcustomdevicebeanpage);

	}

	@Test(description = "To verify Create Public Event with Account Admin with only mandatoryfields and with more than one Event Host ",groups = {CREATEEVENTAPI})
	public void TC_01_POST_CreateEvent_withonly_mandatoryfields_Public_withmultipleeventhost_AccoutAdmin_Positive() throws ParseException {

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
        customReport.customizedReport("Presentation",avengereventdetailspage.verify_videosourcetype(),statusValue, driver, sTestcaseName);
        customReport.customizedReport("15",avengereventdetailspage.getLobbyTime(), statusValue, driver, sTestcaseName);       
        customReport.checkinglist(statusValue);

	}
	
	@Test(description = "To Verify Create Private Event_with Event Host with only mandatory fields with single event admin as eventhost ",groups = {CREATEEVENTAPI})
	public void TC_02_POST_CreateEvent_api_check_CreateEvent_Private_with_EventHost_withonlymandatoryfields_Positive() {
		
		logger.info("API Level Code is executing");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();		
		createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_30);
		apibeanfactory.EventBean(createEventBean);
		
		//EventAdminId
		HashMap<String,String>eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
				
		//GroupIds UI
		String groupIdArray[]={create_groupfromUI()};
		createEventBean.setGroupIds(groupIdArray);	
		
		//UserIds
		HashMap<String,String>userIds = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String userIdArray[]={userIds.get(IUserAccountsService.USERID)};
		createEventBean.setUserIds(userIdArray);
				
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		createEventBean.setDescription("");
		createEventBean.setShortcut("");
		createEventBean.setAccessControl("Private");
		
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
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
        customReport.customizedReport(createteamapirespone.get(IUserAccountsService.EVENTID),avengereventdetailspage.getvideoID(), statusValue, driver, sTestcaseName);
        customReport.customizedReport("Presentation",avengereventdetailspage.verify_videosourcetype(),statusValue, driver, sTestcaseName);
        customReport.customizedReport(true,avengereventdetailspage.verify_eventlisting("Private"),statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To Verify Create All Users Event_with Media Admin with only mandatory fields ",groups = {CREATEEVENTAPI})
	public void TC_03_POST_CreateEvent_withmediaadmin_AllUsers_withonlymandatoryfields_Positive() {
		
		logger.info("API Level Code is executing");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();			
		createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_30);
		apibeanfactory.EventBean(createEventBean);
		
		//EventAdminId
		HashMap<String,String>eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
				
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		createEventBean.setDescription("");
		createEventBean.setShortcut("");
		createEventBean.setAccessControl("AllUsers");
		createEventBean.setVideoAddress("rajitha.dronadala@vb.webex.com");
		createEventBean.setPresentationProfileId("");
		
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
	    logger.info("Selenium Code is excuting");
	   
        launchURL(surl);
        customReport.reporter("Application launch with URL : ", surl);
        homePage = loginPage.loginAs(sUserName, sPassword);
        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());      
        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
        HashMap<String,String> eventDetails=avengereventdetailspage.get_EventDataforVideoAddress();       
        ArrayList<String> ehUsers = avengereventdetailspage.get_EventHostUsers();
        
        customReport.customizedReport(createEventBean.getTitle(),eventDetails.get(IUserAccountsService.TITLE), statusValue, driver, sTestcaseName);
        customReport.customizedReport(createteamapirespone.get(IUserAccountsService.EVENTID),avengereventdetailspage.getvideoID(), statusValue, driver, sTestcaseName);
        customReport.customizedReport(true,ehUsers.contains("apieau10 apieau10"), statusValue, driver, sTestcaseName);
        customReport.customizedReport(true,avengereventdetailspage.verify_eventlisting("All Users"),statusValue, driver, sTestcaseName);
        customReport.customizedReport(createEventBean.getVideoAddress(), eventDetails.get(ICreateEventService.VideoAddress),statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
	}

	@Test(description = "To Verify Create Private Event_with Event Admin with all the fields ",groups = {CREATEEVENTAPI})
	public void TC_04_POST_CreateEvent_witheventadmin_Private_withallthefields_Positive() throws ParseException {
		
		logger.info("API Level Code is executing");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();			
		createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_10);
		apibeanfactory.EventBean(createEventBean);
		
		//EventAdminId
		HashMap<String,String>eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
				
		//UserIds
		HashMap<String,String>userIds = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String userIdArray[]={userIds.get(IUserAccountsService.USERID)};
		createEventBean.setUserIds(userIdArray);
		
		//ModeratorIds
		HashMap<String,String>moderatorIds = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(moderatorIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String moderatorArray[]={moderatorIds.get(IUserAccountsService.USERID)};
		createEventBean.setModeratorIds(moderatorArray);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);				
		
		createEventBean.setAutomatedWebcast("false");
		createEventBean.setAnonymousQuestionsEnabled("true");
		createEventBean.setIsPublic("true");
		createEventBean.setPollsEnabled("true");
		createEventBean.setChatEnabled("true");
		createEventBean.setQuestionAndAnswerEnabled("true");
		createEventBean.setAccessControl("Private");
		createEventBean.setLobbytime("25");
		createEventBean.setEstimatedAttendees("10");
		createEventBean.setUnlisted("true");
		
		// assign category to an event
		CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse1,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
							
		String CategoryIds[] = {createcategoryapirespone.get("categoryId")};
		createEventBean.setCategoryIds(CategoryIds);
				
		//assign tag to an event
		String tagids[] = {"tag check1","tag2"};
		createEventBean.setTags(tagids);
		
		//set preproduction values. For this create a new user and group
		createEventBean.setPreproduction("Yes");
		
		//API Preproduction duration should be given in timespan format which is dd:mm:hh:ss
		createEventBean.setDuration("00:00:25:00");
		
		//UserIds
		HashMap<String,String>userIds1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String userIdArray1[]={userIds1.get(IUserAccountsService.USERID)};
		createEventBean.setPUserIds(userIdArray1);
		
		//GroupIds UI
		String groupIdArray[]={create_groupfromUI()};
		createEventBean.setPGroupIds(groupIdArray);
			
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
	    	    
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
        customReport.customizedReport(createteamapirespone.get(IUserAccountsService.EVENTID),avengereventdetailspage.getvideoID(), statusValue, driver, sTestcaseName);        
        customReport.customizedReport(true,avengereventdetailspage.verify_eventlisting("Private"),statusValue, driver, sTestcaseName);        
        customReport.customizedReport(createEventBean.getLobbytime(),avengereventdetailspage.getLobbyTime(),statusValue, driver, sTestcaseName);        
        customReport.customizedReport(createEventBean.getShortcut(),eventDetails.get(ICreateEventService.Shortcut) ,statusValue, driver, sTestcaseName);        
        customReport.customizedReport(true,avengereventdetailspage.get_EventHostUsers().contains("apieau10 apieau10"), statusValue, driver, sTestcaseName);       
        customReport.customizedReport(true,avengereventdetailspage.getAllModerators().contains("apimau10 apimau10"), statusValue, driver, sTestcaseName);        
        customReport.customizedReport(true,avengereventdetailspage.get_allprivateusersandgroups().contains("apimvu10 apimvu10"),statusValue, driver, sTestcaseName);        
        customReport.customizedReport(true,avengereventdetailspage.get_allPreprodUsersandGroups().contains("apiehu11 apiehu11"),statusValue, driver, sTestcaseName);       
        customReport.customizedReport(true,avengereventdetailspage.get_allPreprodUsersandGroups().contains(avengergroupsbeanpage.getNewgroup()),statusValue, driver, sTestcaseName);     
        customReport.customizedReport(true, avengereventdetailspage.verify_unlisted(), statusValue, driver, sTestcaseName);       
	    customReport.customizedReport(true, avengereventdetailspage.get_Tags().contains("tag check1"), statusValue, driver, sTestcaseName);	    
	    customReport.customizedReport(true, avengereventdetailspage.get_Tags().contains("tag2"), statusValue, driver, sTestcaseName);
	    customReport.customizedReport("Presentation",avengereventdetailspage.verify_videosourcetype(),statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true, avengereventdetailspage.get_Categories().contains(addcategorybean.getName()), statusValue, driver, sTestcaseName);	   
        customReport.checkinglist(statusValue);
	}


	@Test(description = "To Verify Create public Event_with account Admin with only mandatory fields and with vide source as vcsipaddress",groups = {CREATEEVENTAPI})
	public void TC_05_POST_CreateEvent_accountadmin_Public_withonlymandatoryfields_eventhostaseventadmin_withvcsipaddress_Positive() {
		
		logger.info("API Level Code is executing");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();	
		createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_30);
		apibeanfactory.EventBean(createEventBean);
		
		//EventAdminId
		HashMap<String,String>eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
				
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		createEventBean.setShortcut("");
		createEventBean.setDescription("");
		createEventBean.setPresentationProfileId("");
		createEventBean.setVideoAddress("rajitha.dronadala@vb.webex.com");
		createEventBean.setUnlisted("false");
		
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
	    logger.info("Selenium Code is excuting");
	   
        launchURL(surl);
        customReport.reporter("Application launch with URL : ", surl);
        homePage = loginPage.loginAs(sUserName, sPassword);
        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());       
        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
        HashMap<String,String> eventDetails=avengereventdetailspage.get_EventDataforVideoAddress();       
        ArrayList<String> ehUsers = avengereventdetailspage.get_EventHostUsers();
        
        customReport.customizedReport(createEventBean.getTitle(),eventDetails.get(IUserAccountsService.TITLE), statusValue, driver, sTestcaseName);
        customReport.customizedReport(createteamapirespone.get(IUserAccountsService.EVENTID),avengereventdetailspage.getvideoID(), statusValue, driver, sTestcaseName);
        customReport.customizedReport(true,avengereventdetailspage.verify_eventlisting("Public"),statusValue, driver, sTestcaseName);
        customReport.customizedReport(createEventBean.getVideoAddress(), eventDetails.get(ICreateEventService.VideoAddress),statusValue, driver, sTestcaseName);
        customReport.customizedReport(false, avengereventdetailspage.verify_unlisted(), statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
	}


	@Test(description = "To Verify Create Public Event_with Event Admin with media contributor as event admin ",groups = {CREATEEVENTAPI})
	public void TC_06_POST_CreateEvent_Public_with_EventAdmin_MediaContributor_as_EventAdmin_Negative() {
				
		logger.info("API Level Code is executing");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();			
		createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_30);
		apibeanfactory.EventBean(createEventBean);
		
		//EventAdminId
		HashMap<String,String>eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
		
		//createEventBean.setPresentationProfileId("6c6b06bb-717f-47f7-9ede-92fbc7fbf678");							
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus500);
		customReport.customizedReport(HttpStatusCode.httpsStatus500, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify Create Public Event with Account Admin with more than three Event Host ",groups = {CREATEEVENTAPI})
	public void TC_07_POST_CreateEvent_Public_withmorethanthreeeventhost_AccoutAdmin_Negative() throws ParseException {

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
		HashMap<String,String> eventAdminId2 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventhost), surl);
		HashMap<String,String> eventAdminId3 = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId2.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId3.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID),eventAdminId1.get(IUserAccountsService.USERID),eventAdminId2.get(IUserAccountsService.USERID),eventAdminId3.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
	
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus500);
		customReport.customizedReport(HttpStatusCode.httpsStatus500, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.customizedReport(createteamapirespone.get(IAPIConstantCodes.APIResponseJsonErrorCode), HttpStatusCode.eventCode1, statusValue, driver, sTestcaseName);
		customReport.customizedReport(createteamapirespone.get(IAPIConstantCodes.APIResponseJsonErrorDetail), HttpStatusCode.eventErrorDetail3, statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);

	}

	@Test(description = "To Verify Create Public Event with account  Admin without giving mandatory field title ",groups = {CREATEEVENTAPI})
	public void TC_08_POST_CreateEvent_Public_with_AccountAdmin_wihtoutgiving_mandatoryfield_title_Negative() {
				
		logger.info("API Level Code is executing");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();	
		
		createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_30);
		apibeanfactory.EventBean(createEventBean);
		
		//EventAdminId
		HashMap<String,String>eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
		
		//createEventBean.setPresentationProfileId("6c6b06bb-717f-47f7-9ede-92fbc7fbf678");							
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
		createEventBean.setTitle("");
		
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus500);
		customReport.customizedReport(HttpStatusCode.httpsStatus500, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To Verify Create Public Event with account  Admin without giving mandatory field event admin ",groups = {CREATEEVENTAPI})
	public void TC_09_POST_CreateEvent_Public_with_AccountAdmin_wihtoutgiving_mandatoryfield_eventadmin_Negative() {
				
		logger.info("API Level Code is executing");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();	

		UserServices userservices = new UserServices();	
		
		createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_30);
		apibeanfactory.EventBean(createEventBean);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		createEventBean.setVideoAddress("rajitha.dronadala@vb.webex.com");
		createEventBean.setPresentationProfileId("");
		createEventBean.setEventAdminId(null);
		
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus500);
		customReport.customizedReport(HttpStatusCode.httpsStatus500, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
		
	@Test(description = "To Verify Create Public Event with account  Admin without giving mandatory field access control ",groups = {CREATEEVENTAPI})
	public void TC_10_POST_CreateEvent_Public_with_AccountAdmin_wihtoutgiving_mandatoryfield_accesscontrol_Negative() {
		
		logger.info("API Level Code is executing");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();		

		UserServices userservices = new UserServices();			
		createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_30);
		apibeanfactory.EventBean(createEventBean);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
		
		//EventAdminId
		HashMap<String,String>eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
							
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		createEventBean.setVideoAddress("rajitha.dronadala@vb.webex.com");
		createEventBean.setPresentationProfileId("");
		createEventBean.setAccessControl("");
		
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus500);
		customReport.customizedReport(HttpStatusCode.httpsStatus500, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.customizedReport(createteamapirespone.get(IAPIConstantCodes.APIResponseJsonErrorCode), HttpStatusCode.eventCode1, statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To Verify Create Public Event with account  Admin by giving invalid presentation profile id ",groups = {CREATEEVENTAPI})
	public void TC_11_POST_CreateEvent_Public_with_AccountAdmin_bygiving_invalid_presentationprofileid_Negative() {
				
		logger.info("API Level Code is executing");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();		

		UserServices userservices = new UserServices();			
		createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_30);
		apibeanfactory.EventBean(createEventBean);
		
		//EventAdminId
		HashMap<String,String>eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		//PresentationProfileId UI
		createEventBean.setPresentationProfileId("6c6b06bb-717f-47f7-9ede-92fbc7fbf678invalid");							
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus500);
		customReport.customizedReport(HttpStatusCode.httpsStatus500, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To Verify Create Public Event with account  Admin by giving shortcut name that is being used by another event",groups = {CREATEEVENTAPI})
	public void TC_12_POST_CreateEvent_Public_with_AccountAdmin_bygivingshortcutname_whichisalreadyinuse_Negative() {		
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
	 	UserServices userservices = new UserServices();	
		createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_10);
		AddPresentationprofileBeanPage addvideosource = new AddPresentationprofileBeanPage();
		apibeanfactory.EventBean1(createEventBean);
		
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
		createEventBean.setShortcut("APIautoshortcutreusevcl");
		createEventBean.setVideoSourceType(ICreateEventService.videoSourceType[1]);
		createEventBean.setPresenterId(loginapiresponse.get("userId"));
		
		//create Event..
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent1(loginapiresponse, createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
	    
	    //Creating another Event with same shortcut		
		createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_30);
		apibeanfactory.EventBean(createEventBean);
		
		//EventAdminId
		HashMap<String,String>eventAdminId2 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId2.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray1[]={eventAdminId2.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray1);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
		createEventBean.setShortcut("APIautoshortcutreusevcl");
					
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		//create Event
		CreateEventsAPI createEventsAPI1=new CreateEventsAPI();
		HashMap<String, String> createeventapirespone1 = createEventsAPI1.createEvent(loginapiresponse,createEventBean);
		customReport.customizedReport( HttpStatusCode.missingCode, createeventapirespone1.get(IAPIConstantCodes.APIResponseJsonErrorCode), statusValue, driver, sTestcaseName);
		customReport.customizedReport( HttpStatusCode.eventErrorDetail2, createeventapirespone1.get(IAPIConstantCodes.APIResponseJsonErrorDetail), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);

	}
	
	@Test(description = "To Verify Create AllUsers Unlisted Event_with Media Admin with all the fields except for pre prod ",groups = {CREATEEVENTAPI})
	public void TC_13_POST_CreateEvent_withmediaadmin_AllUsers_withallthefieldsexceptforrpreprod_Unlisted_Positive() {
		
		logger.info("API Level Code is executing");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();			
		createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_30);
		apibeanfactory.EventBean(createEventBean);
		
		//EventAdminId
		HashMap<String,String>eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
	
		//ModeratorIds
		HashMap<String,String>moderatorIds = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(moderatorIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String moderatorArray[]={moderatorIds.get(IUserAccountsService.USERID)};
		createEventBean.setModeratorIds(moderatorArray);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		createEventBean.setAutomatedWebcast("false");
		createEventBean.setAnonymousQuestionsEnabled("true");
		createEventBean.setIsPublic("true");
		createEventBean.setPollsEnabled("true");
		createEventBean.setChatEnabled("true");
		createEventBean.setQuestionAndAnswerEnabled("true");
		createEventBean.setAccessControl("AllUsers");
		createEventBean.setLobbytime("25");
		createEventBean.setEstimatedAttendees("10");
		createEventBean.setUnlisted("true");
		
		// assign category to an event
		CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse1,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
							
		String CategoryIds[] = {createcategoryapirespone.get("categoryId")};
		createEventBean.setCategoryIds(CategoryIds);
				
		//assign tag to an event
		String tagids[] = {"tag1"};
		createEventBean.setTags(tagids);
				
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
	    
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
        customReport.customizedReport(createteamapirespone.get(IUserAccountsService.EVENTID),avengereventdetailspage.getvideoID(), statusValue, driver, sTestcaseName);
        customReport.customizedReport(true,avengereventdetailspage.verify_eventlisting("All Users"),statusValue, driver, sTestcaseName);
        customReport.customizedReport(createEventBean.getLobbytime(),avengereventdetailspage.getLobbyTime(), statusValue, driver, sTestcaseName);
        customReport.customizedReport(createEventBean.getShortcut(),eventDetails.get(ICreateEventService.Shortcut) ,statusValue, driver, sTestcaseName);
        customReport.customizedReport(true, avengereventdetailspage.verify_unlisted(), statusValue, driver, sTestcaseName);
        customReport.customizedReport(true,ehUsers.contains("apieau10 apieau10"), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true, avengereventdetailspage.get_Tags().contains("tag1"), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true, avengereventdetailspage.get_Categories().contains(addcategorybean.getName()), statusValue, driver, sTestcaseName);
	    customReport.customizedReport("Presentation",avengereventdetailspage.verify_videosourcetype(),statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To Verify Create Public Event with account Admin by assigning media viewer to the group having media admin access ",groups = {CREATEEVENTAPI})
	public void TC_14_POST_CreateEvent_Public_with_MediaViewer_byassigningtoeventadmingroup_nolobbytime_Positive() {
		
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		//deleting the assigned groups if any for the Media Viewer user
		delete_assignedGroupsForUser(IUsersList.CreateEventmediaviewer1);
		
		logger.info("API Level Code is executing");
		UserServices userservices = new UserServices();	
		
		createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_30);
		apibeanfactory.EventBean(createEventBean);
		
		//EventAdminId
		HashMap<String,String>eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
					
		//UserIds
		HashMap<String,String>userIds = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String userIdArray[]={userIds.get(IUserAccountsService.USERID)};
		createEventBean.setUserIds(userIdArray);
		
		//ModeratorIds
		HashMap<String,String>moderatorIds = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(moderatorIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String moderatorArray[]={moderatorIds.get(IUserAccountsService.USERID)};
		createEventBean.setModeratorIds(moderatorArray);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//assigning mediaviewer to a group having access
		loginapiresponse2 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaviewer1), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse2.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse2.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse2);

		//getting the userIds
		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		String[] userIds2 = {loginapiresponse2.get("userId")};
		addgroupbean.setUserids(userIds2);
								
		//getting roleids
		UserServices userServices=new UserServices();
		HashMap<String, String> userroleapirespone = userServices.getRolesApi(loginapiresponse1,IAPIConstantCodes.ACCOUNTADMIN);
		String roleid=apiutils.formatingapiresponse(userroleapirespone.get(IAPIConstantCodes.ROLEID));
		String[] roleIds = {userroleapirespone.get("roleId")};
		addgroupbean.setRoleids(roleIds);
						
		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
		creategroupapirespone = creategroupAPI.createGroup(loginapiresponse1,creategroupjson);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		createEventBean.setAutomatedWebcast("false");
		createEventBean.setAnonymousQuestionsEnabled("true");
		createEventBean.setIsPublic("true");
		createEventBean.setPollsEnabled("true");
		createEventBean.setChatEnabled("true");
		createEventBean.setQuestionAndAnswerEnabled("true");
		createEventBean.setAccessControl("AllUsers");
		createEventBean.setLobbytime("No");
		createEventBean.setVideoAddress("rajitha.dronadala@vb.webex.com");
		createEventBean.setEstimatedAttendees("10");
		createEventBean.setUnlisted("false");
		createEventBean.setPresentationProfileId("");
		
		// assign category to an event
		CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse1,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
							
		String CategoryIds[] = {createcategoryapirespone.get("categoryId")};
		createEventBean.setCategoryIds(CategoryIds);
				
		//assign tag to an event
		String tagids[] = {"tag1"};
		createEventBean.setTags(tagids);
		
		//set preproduction values. For this create a new user and group
		createEventBean.setPreproduction("Yes");
		//API Preproduction duration should be given in timespan format which is dd:mm:hh:ss
		createEventBean.setDuration("00:00:25:00");
		
		//UserIds
		HashMap<String,String>userIds1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String userIdArray1[]={userIds1.get(IUserAccountsService.USERID)};
		createEventBean.setPUserIds(userIdArray1);
		
		//GroupIds UI
		String groupIdArray[]={create_groupfromUI()};
		createEventBean.setPGroupIds(groupIdArray);
			
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
	    
	   //deleting the assigned groups if any for the Media Viewer user
	    delete_assignedGroupsForUser(IUsersList.CreateEventmediaviewer1);
		
	    logger.info("Selenium Code is excuting");
	    launchURL(surl);
        customReport.reporter("Application launch with URL : ", surl);
        homePage = loginPage.loginAs(sUserName, sPassword);
        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());      
        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
        HashMap<String,String> eventDetails=avengereventdetailspage.get_EventDataforVideoAddress();        
        ArrayList<String> ehUsers = avengereventdetailspage.get_EventHostUsers();
        
        customReport.customizedReport(createEventBean.getTitle(),eventDetails.get(IUserAccountsService.TITLE), statusValue, driver, sTestcaseName);
        customReport.customizedReport(createteamapirespone.get(IUserAccountsService.EVENTID),avengereventdetailspage.getvideoID(), statusValue, driver, sTestcaseName);
        customReport.customizedReport(true,avengereventdetailspage.verify_eventlisting("All Users"),statusValue, driver, sTestcaseName);
        customReport.customizedReport("15", avengereventdetailspage.getLobbyTime(), statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
	}

	@Test(description = "To Verify error is thrown when media contributor tries to create an event ",groups = {CREATEEVENTAPI})
	public void TC_15_POST_CreateEvent_Public_with_MediaContributor_Negative() {
				
		logger.info("API Level Code is executing");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();		

		UserServices userservices = new UserServices();			
		createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_30);
		apibeanfactory.EventBean(createEventBean);
		
		//EventAdminId
		HashMap<String,String>eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
		
		//createEventBean.setPresentationProfileId("6c6b06bb-717f-47f7-9ede-92fbc7fbf678");							
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To Verify error is thrown when media viewer tries to create an event ",groups = {CREATEEVENTAPI})
	public void TC_16_POST_CreateEvent_Public_with_MediaViewer_Negative() {
				
		logger.info("API Level Code is executing");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();			
		createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_30);
		apibeanfactory.EventBean(createEventBean);
		
		//EventAdminId
		HashMap<String,String>eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
		
		//createEventBean.setPresentationProfileId("6c6b06bb-717f-47f7-9ede-92fbc7fbf678");						
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);				
	
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	
	@Test(description = "To verify that an error is thrown when lobby time is greater than the pre prodcution time ",groups = {CREATEEVENTAPI})
	public void TC_17_POST_CreateEvent_with_lobbytime_greaterthan_preproductiontime_Negative() throws ParseException {

		logger.info("API Level Code is executing");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();			
		createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_10);
		apibeanfactory.EventBean(createEventBean);
		
		//EventAdminId
		HashMap<String,String>eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
				
		//UserIds
		HashMap<String,String>userIds = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String userIdArray[]={userIds.get(IUserAccountsService.USERID)};
		createEventBean.setUserIds(userIdArray);
		
		//ModeratorIds
		HashMap<String,String>moderatorIds = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(moderatorIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String moderatorArray[]={moderatorIds.get(IUserAccountsService.USERID)};
		createEventBean.setModeratorIds(moderatorArray);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
						
		createEventBean.setAutomatedWebcast("false");
		createEventBean.setAnonymousQuestionsEnabled("true");
		createEventBean.setIsPublic("true");
		createEventBean.setPollsEnabled("true");
		createEventBean.setChatEnabled("true");
		createEventBean.setQuestionAndAnswerEnabled("true");
		createEventBean.setAccessControl("Private");
		createEventBean.setLobbytime("15");
		createEventBean.setEstimatedAttendees("10");
		createEventBean.setUnlisted("true");
	
		// assign category to an event
		CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse1,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
							
		String CategoryIds[] = {createcategoryapirespone.get("categoryId")};
		createEventBean.setCategoryIds(CategoryIds);		
		
		//assign tag to an event
		String tagids[] = {"tag check1","tag2"};
		createEventBean.setTags(tagids);
		
		//set preproduction values. For this create a new user and group
		createEventBean.setPreproduction("Yes");
		
		//API Preproduction duration should be given in timespan format which is dd:mm:hh:ss
		createEventBean.setDuration("00:00:00:30");
		
		//UserIds
		HashMap<String,String>userIds1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String userIdArray1[]={userIds1.get(IUserAccountsService.USERID)};
		createEventBean.setPUserIds(userIdArray1);
		
		//GroupIds UI
		String groupIdArray[]={create_groupfromUI()};
		createEventBean.setPGroupIds(groupIdArray);
			
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
		customReport.customizedReport( HttpStatusCode.eventCode1,createteamapirespone.get(IAPIConstantCodes.APIResponseJsonErrorCode), statusValue, driver, sTestcaseName);
		customReport.customizedReport(HttpStatusCode.eventErrorDetail1,createteamapirespone.get(IAPIConstantCodes.APIResponseJsonErrorDetail), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify that events are created succesfully with different shortcuts, same PP and with overlap times",groups = {CREATEEVENTAPI})
	public void TC_18_POST_Create2Events_with_differentshortcut_samePP_withoverlaptimes_Negative() throws ParseException {

		logger.info("API Level Code is executing");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();			
		createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_30);
		apibeanfactory.EventBean(createEventBean);
				
		//EventAdminId
		HashMap<String,String>eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
				
		//UserIds
		HashMap<String,String>userIds = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String userIdArray[]={userIds.get(IUserAccountsService.USERID)};
		createEventBean.setUserIds(userIdArray);
		
		//ModeratorIds
		HashMap<String,String>moderatorIds = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(moderatorIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String moderatorArray[]={moderatorIds.get(IUserAccountsService.USERID)};
		createEventBean.setModeratorIds(moderatorArray);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
							
		createEventBean.setAutomatedWebcast("false");
		createEventBean.setAnonymousQuestionsEnabled("true");
		createEventBean.setIsPublic("true");
		createEventBean.setPollsEnabled("true");
		createEventBean.setChatEnabled("true");
		createEventBean.setQuestionAndAnswerEnabled("true");
		createEventBean.setAccessControl("Public");
		createEventBean.setLobbytime("10");
			
		//set preproduction values. For this create a new user and group
		createEventBean.setPreproduction("Yes");
		
		//API Preproduction duration should be given in timespan format which is dd:mm:hh:ss
		createEventBean.setDuration("00:00:10:00");
		
		//UserIds
		HashMap<String,String>userIds1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String userIdArray1[]={userIds1.get(IUserAccountsService.USERID)};
		createEventBean.setPUserIds(userIdArray1);
		
		//GroupIds UI
		String groupIdArray[]={create_groupfromUI()};
		createEventBean.setPGroupIds(groupIdArray);
		
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
		
		//Create another event with vc sip address 
		createEventBean.setTitle("Event2");
		createEventBean.setShortcut("apishortcutdifferent");
		CreateEventsAPI createEventsAPI1=new CreateEventsAPI();
		HashMap<String, String> createteamapirespone1 = createEventsAPI1.createEvent(loginapiresponse,createEventBean);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus500);
		customReport.customizedReport(HttpStatusCode.httpsStatus500, createteamapirespone1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
	    customReport.checkinglist(statusValue);
	}

	
	@Test(description = "To verify that error is thorwn when tried to create 2 events with same shortcut,one with PP and one with VC sip address ",groups = {CREATEEVENTAPI})
	public void TC_19_POST_Create2Events_with_sameshortcut_onewithPP_andonewithVC_withoverlaptimes_Negative() throws ParseException {

		logger.info("API Level Code is executing");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();			
		createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_30);
		apibeanfactory.EventBean(createEventBean);		
		
		//EventAdminId
		HashMap<String,String>eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
				
		//UserIds
		HashMap<String,String>userIds = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String userIdArray[]={userIds.get(IUserAccountsService.USERID)};
		createEventBean.setUserIds(userIdArray);
		
		//ModeratorIds
		HashMap<String,String>moderatorIds = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(moderatorIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String moderatorArray[]={moderatorIds.get(IUserAccountsService.USERID)};
		createEventBean.setModeratorIds(moderatorArray);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	
		createEventBean.setPollsEnabled("true");
		createEventBean.setChatEnabled("true");
		createEventBean.setQuestionAndAnswerEnabled("false");
		createEventBean.setShortcut("shortcut");
		createEventBean.setLobbytime("10");
			
		//set preproduction values. For this create a new user and group
		createEventBean.setPreproduction("Yes");
		
		//API Preproduction duration should be given in timespan format which is dd:mm:hh:ss
		createEventBean.setDuration("00:00:10:00");
		
		//UserIds
		HashMap<String,String>userIds1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String userIdArray1[]={userIds1.get(IUserAccountsService.USERID)};
		createEventBean.setPUserIds(userIdArray1);
		
		//GroupIds UI
		String groupIdArray[]={create_groupfromUI()};
		createEventBean.setPGroupIds(groupIdArray);
		
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
		
		//Create another event with vc sip address 
		createEventBean.setTitle("Event1");
		createEventBean.setPresentationProfileId("");
		createEventBean.setVideoAddress("rajitha.dronadala@vb.webex.com");
		createEventBean.setShortcut("shortcut");
		CreateEventsAPI createEventsAPI1=new CreateEventsAPI();
		HashMap<String, String> createteamapirespone1 = createEventsAPI1.createEvent(loginapiresponse,createEventBean);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus500);
		customReport.customizedReport(HttpStatusCode.httpsStatus500, createteamapirespone1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.customizedReport(createteamapirespone1.get(IAPIConstantCodes.APIResponseJsonErrorCode), HttpStatusCode.missingCode, statusValue, driver, sTestcaseName);
		customReport.customizedReport(createteamapirespone1.get(IAPIConstantCodes.APIResponseJsonErrorDetail), HttpStatusCode.eventErrorDetail4, statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);
	}

	@Test(description = "To Verify that create webcast API throws an error when invalid format is given for VC Sip address ",groups = {CREATEEVENTAPI})
	public void TC_20_POST_CreateEvent_withinvalidformat_forVCSipaddress_Negative() {
		
		logger.info("API Level Code is executing");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();			
		createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_30);
		apibeanfactory.EventBean(createEventBean);
		
		//EventAdminId
		HashMap<String,String>eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
				
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		createEventBean.setDescription("");
		createEventBean.setShortcut("");
		createEventBean.setAccessControl("AllUsers");
		createEventBean.setVideoAddress("rajitha.dronadala#vb.webex.com");
		createEventBean.setPresentationProfileId("");
		
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus500);
		customReport.customizedReport(HttpStatusCode.httpsStatus500, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
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
	 	homePage.click_logout();
		return url.split("/")[8];
	}
}