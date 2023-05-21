package com.vbrick.avenger.videocommentapi_AV_9879_testcases;
 

import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
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
import com.vbrick.avenger.apibeans.AddCategoryBean;
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbrick.avenger.apibeans.AddVideoCommentBean;
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
import com.vbrick.avenger.pageobjects.AvengerAddNewDmeDevicePage;
import com.vbrick.avenger.pageobjects.AvengerAddPresentationProfilePage;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerDevicesPage;
import com.vbrick.avenger.pageobjects.AvengerEventDetailsPage;
import com.vbrick.avenger.pageobjects.AvengerEventsPage;
import com.vbrick.avenger.pageobjects.AvengerGroupsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerPresentationProfilesPage;
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.ICreateCategoryAPI;
import com.vbricks.avenger.service.ICreateEventService;
import com.vbricks.avenger.service.IEditVideoRatingService;
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUserAccountsService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.CreateCategoryAPI;
import com.vbricks.avenger.serviceimpl.CreateEventsAPI;
import com.vbricks.avenger.serviceimpl.CustomFieldsAPI;
import com.vbricks.avenger.serviceimpl.DMEdeviceslistAPI;
import com.vbricks.avenger.serviceimpl.EditVideoRatingAPI;
import com.vbricks.avenger.serviceimpl.GetCategoryListAPI;
import com.vbricks.avenger.serviceimpl.GetEventDetailsAPI;
import com.vbricks.avenger.serviceimpl.GetEventListAPI;
import com.vbricks.avenger.serviceimpl.PresentationProfilesdetailsAPI;
import com.vbricks.avenger.serviceimpl.SearchAccessEntityAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.ConstantsValue;
import com.vbricks.avenger.utils.HttpStatusCode;
public class GetEventDetailsApi_Av_10174 extends TestBase{

	private static Logger logger = Logger.getLogger(GetEventDetailsApi_Av_10174.class);
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
	private AddCategoryBean addcategorybean;
	private Map<String, String> userdata;
    private ApiUtils apiutils;
	private AvengerGroupsPage avengergroupspage;
	private AvengerGroupsBeanPage avengergroupsbeanpage;
	private AvengerDevicesPage avengerdevicespage;
	private AvengerEventDetailsPage avengereventdetailspage;
	private AvengerPresentationProfilesPage avengerpresentationprofilespage;
	private AvengerAddPresentationProfilePage avengeraddpresentationprofilepage;
	private AddPresentationprofileBeanPage addpresentationprofilebeanpage;
	private AvengerAddNewDmeDevicePage avengeraddnewdmedevicepage;
	private AvengerAddCustomDevicePage avengeraddcustomdevicepage;
	private AddNewDmeBeanPage addnewdmedevicebeanpage;
	private AvengerDashboardPage avengerdashboardpage;
	private BasePage basePage;
	private AddCustomDeviceBeanPage addcustomdevicebeanpage;
	private CreateEventBean createEventBean;
	public ApiBeanFactory apibeanfactory;
 	private HashMap<String, String> loginapiresponse;
 	private HashMap<String, String> loginapiresponse1;
	private HashMap<String, String> eventDetailapirespone;
	private HashMap<String,String> loginapiresponse_mediaviewer;
	private HashMap<String, String> createcategoryapirespone;
	private HashMap<String,String> loginapiresponse_eventadmin;
	private HashMap<String,String> loginapiresponse_mediaContributor;
	private HashMap<String,String> loginapiresponse_eventhost;
    private AssertionAPIResponse assertionapiresponse;
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
		avengergroupsbeanpage = new AvengerGroupsBeanPage();
		createEventBean = new CreateEventBean();
		apibeanfactory = new ApiBeanFactory();
		addcategorybean=new AddCategoryBean();
		addpresentationprofilebeanpage = new AddPresentationprofileBeanPage();
		addnewdmedevicebeanpage = new AddNewDmeBeanPage();
 		assertionapiresponse =new AssertionAPIResponse();
		addcustomdevicebeanpage = new AddCustomDeviceBeanPage();
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

	@Test(description="To Verify the EventDetail using EventDetail API with Account Admin",groups = {GETEVENTDETAILSAPI})
	public void TC_01_GET_EventDetail_api_check_With_AccountAdmin_Positive(ITestContext context ) throws ParseException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();			
		createEventBean.setDays(ICreateEventService.setDay_01);
		createEventBean.setHours(ICreateEventService.setMinutes_10);
		apibeanfactory.EventBean(createEventBean);
		
		//EventAdminId
		HashMap<String,String>eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.GetEventDetailseventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
				
		//UserIds
		HashMap<String,String>userIds = userservices.doLogin(apiutils.userJson(IUsersList.GetEventDetailsmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String userIdArray[]={userIds.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setUserIds(userIdArray);
		
		//ModeratorIds
		HashMap<String,String>moderatorIds = userservices.doLogin(apiutils.userJson(IUsersList.GetEventDetailsmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(moderatorIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String moderatorArray[]={moderatorIds.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setModeratorIds(moderatorArray);		
		createEventBean.setAutomatedWebcast("true");
		createEventBean.setAnonymousQuestionsEnabled("true");
		createEventBean.setIsPublic("true");
		createEventBean.setPollsEnabled("true");
		createEventBean.setChatEnabled("true");
		createEventBean.setQuestionAndAnswerEnabled("true");
		createEventBean.setAccessControl("Private");
		createEventBean.setLobbytime("20");
		createEventBean.setShortcut("geteventdetailsshortcut123d1");
		createEventBean.setEstimatedAttendees("10");
		createEventBean.setUnlisted("true");
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		

		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		// assign category to an event
		CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
							
		String CategoryIds[] = {createcategoryapirespone.get("categoryId")};
		createEventBean.setCategoryIds(CategoryIds);
				
		//assign tag to an event
		String tagids[] = {"multi word","check2"};
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
	    assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		loginapiresponse.put(IAPIConstantCodes.APIEVENTID, createteamapirespone.get(IAPIConstantCodes.APIEVENTID));	
		GetEventDetailsAPI getEventDetailsAPI=new GetEventDetailsAPI();
		eventDetailapirespone = getEventDetailsAPI.eventDetail(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(eventDetailapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+eventDetailapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, eventDetailapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+eventDetailapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
   
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
        String startdateAPI = DateTime.toDate2(createEventBean.getStartDate());
	    String enddateAPI = DateTime.toDate2(createEventBean.getEndDate());
	    	    
        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
        customReport.customizedReport(eventDetailapirespone.get(IAPIConstantCodes.APITITLE),eventDetails.get(IUserAccountsService.TITLE), statusValue, driver, sTestcaseName);
        customReport.customizedReport(eventDetailapirespone.get(IUserAccountsService.EVENTID),avengereventdetailspage.getvideoID(), statusValue, driver, sTestcaseName);
        customReport.customizedReport("10",eventDetailapirespone.get(IUserAccountsService.estimatedattendees), statusValue, driver, sTestcaseName);
        customReport.customizedReport(true, avengereventdetailspage.verify_unlisted(), statusValue, driver, sTestcaseName);
        customReport.customizedReport(true, avengereventdetailspage.get_Tags().contains("multi word"), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true, avengereventdetailspage.get_Tags().contains("check2"), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true, avengereventdetailspage.get_Categories().contains(addcategorybean.getName()), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(startdateAPI, eventDetails.get("startdate"), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(enddateAPI, eventDetails.get("enddate"), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,avengereventdetailspage.verify_eventlisting("Private"),statusValue, driver, sTestcaseName);
	    customReport.customizedReport("Presentation",avengereventdetailspage.verify_videosourcetype(),statusValue, driver, sTestcaseName);
	    customReport.customizedReport(createEventBean.getLobbytime(),avengereventdetailspage.getLobbyTime(), statusValue, driver, sTestcaseName);
        customReport.customizedReport(createEventBean.getShortcut(),eventDetails.get(ICreateEventService.Shortcut) ,statusValue, driver, sTestcaseName);
        customReport.customizedReport(true,avengereventdetailspage.get_EventHostUsers1().contains("apieau20 apieau20"), statusValue, driver, sTestcaseName);
        customReport.customizedReport(true,avengereventdetailspage.getAllModerators().contains("apimau20 apimau20"), statusValue, driver, sTestcaseName);
        customReport.customizedReport(true,avengereventdetailspage.get_allprivateusersandgroups().contains("apimvu20 apimvu20"),statusValue, driver, sTestcaseName);
        customReport.customizedReport("Presentation",avengereventdetailspage.verify_videosourcetype(),statusValue, driver, sTestcaseName);
        customReport.customizedReport(true,avengereventdetailspage.get_allPreprodUsersandGroups().contains(avengergroupsbeanpage.getNewgroup()),statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
	}
	
	@Test(description="To Verify the EventDetail using EventDetail API with Media Admin",groups = {GETEVENTDETAILSAPI})
	public void TC_02_GET_EventDetail_api_check_With_MediaAdmin_Positive(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();			
		createEventBean.setDays(ICreateEventService.setDay_01);
		createEventBean.setHours(ICreateEventService.setMinutes_20);
		apibeanfactory.EventBean(createEventBean);
		
		//EventAdminId
		HashMap<String,String>eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.GetEventDetailseventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
						
		//GroupIds UI
	    String groupIdArray[]={create_groupfromUI()};
		createEventBean.setGroupIds(groupIdArray);
		
		//UserIds
		HashMap<String,String>userIds = userservices.doLogin(apiutils.userJson(IUsersList.GetEventDetailsmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String userIdArray[]={userIds.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setUserIds(userIdArray);
		
		//ModeratorIds
		HashMap<String,String>moderatorIds = userservices.doLogin(apiutils.userJson(IUsersList.GetEventDetailsmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(moderatorIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String moderatorArray[]={moderatorIds.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setModeratorIds(moderatorArray);
				
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.GetEventDetailsmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		loginapiresponse.put(IAPIConstantCodes.APIEVENTID, createteamapirespone.get(IAPIConstantCodes.APIEVENTID));		
		GetEventDetailsAPI getEventDetailsAPI=new GetEventDetailsAPI();
		eventDetailapirespone = getEventDetailsAPI.eventDetail(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(eventDetailapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+eventDetailapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, eventDetailapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+eventDetailapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    
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
        customReport.customizedReport(eventDetailapirespone.get(IAPIConstantCodes.APITITLE),eventDetails.get(IUserAccountsService.TITLE), statusValue, driver, sTestcaseName);
        customReport.customizedReport(eventDetailapirespone.get(IUserAccountsService.EVENTID),avengereventdetailspage.getvideoID(), statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
	}
	
	@Test(description="To Verify the EventDetail using EventDetail API with Event Admin",groups = {GETEVENTDETAILSAPI})
	public void TC_03_GET_EventDetail_api_check_With_EventAdmin_Positive(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();			
		createEventBean.setDays(ICreateEventService.setDay_01);
		createEventBean.setHours(ICreateEventService.setMinutes_30);
		apibeanfactory.EventBean(createEventBean);
		
		//EventAdminId
		HashMap<String,String>eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.GetEventDetailseventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
								
		//GroupIds UI
		 String groupIdArray[]={create_groupfromUI()};
	     createEventBean.setGroupIds(groupIdArray); 
	     
		//UserIds
		HashMap<String,String>userIds = userservices.doLogin(apiutils.userJson(IUsersList.GetEventDetailsmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String userIdArray[]={userIds.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setUserIds(userIdArray);
		
		//ModeratorIds
		HashMap<String,String>moderatorIds = userservices.doLogin(apiutils.userJson(IUsersList.GetEventDetailsmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(moderatorIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String moderatorArray[]={moderatorIds.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setModeratorIds(moderatorArray);		
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.GetEventDetailseventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		loginapiresponse.put(IAPIConstantCodes.APIEVENTID, createteamapirespone.get(IAPIConstantCodes.APIEVENTID));
		
		GetEventDetailsAPI getEventDetailsAPI=new GetEventDetailsAPI();
		eventDetailapirespone = getEventDetailsAPI.eventDetail(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(eventDetailapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+eventDetailapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, eventDetailapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+eventDetailapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    
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
        customReport.customizedReport(eventDetailapirespone.get(IAPIConstantCodes.APITITLE),eventDetails.get(IUserAccountsService.TITLE), statusValue, driver, sTestcaseName);
        customReport.customizedReport(eventDetailapirespone.get(IUserAccountsService.EVENTID),avengereventdetailspage.getvideoID(), statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
	}
	
	@Test(description="To Verify the EventDetail using EventDetail API with MediaContributor",groups = {GETEVENTDETAILSAPI})
	public void TC_04_GET_EventDetail_api_check_With_MediaContributor_Negative(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();	
		
		createEventBean.setDays(ICreateEventService.setDay_01);
		createEventBean.setHours(ICreateEventService.setMinutes_40);
		apibeanfactory.EventBean(createEventBean);
		
		//EventAdminId
		HashMap<String,String>eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.GetEventDetailseventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
										
		//GroupIds UI
		 String groupIdArray[]={create_groupfromUI()};
		 createEventBean.setGroupIds(groupIdArray); 
		 
		//UserIds
		HashMap<String,String>userIds = userservices.doLogin(apiutils.userJson(IUsersList.GetEventDetailsmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String userIdArray[]={userIds.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setUserIds(userIdArray);
		
		//ModeratorIds
		HashMap<String,String>moderatorIds = userservices.doLogin(apiutils.userJson(IUsersList.GetEventDetailsmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(moderatorIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String moderatorArray[]={moderatorIds.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setModeratorIds(moderatorArray);		
		createEventBean.setAccessControl("Private");
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		loginapiresponse_mediaContributor = userservices.doLogin(apiutils.userJson(IUsersList.GetEventDetailsmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediaContributor.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_mediaContributor.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse_mediaContributor);
		
		loginapiresponse_mediaContributor.put(IAPIConstantCodes.APIEVENTID, createteamapirespone.get(IAPIConstantCodes.APIEVENTID));		
		GetEventDetailsAPI getEventDetailsAPI=new GetEventDetailsAPI();
		eventDetailapirespone = getEventDetailsAPI.eventDetail(loginapiresponse_mediaContributor);
	    assertionapiresponse.verifyAssert_httpCode(eventDetailapirespone.get(IAPIConstantCodes.APIResponseHttpCode) + eventDetailapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpStatusCodeInt401null);
	  	customReport.customizedReport(HttpStatusCode.httpStatusCodeInt401null, eventDetailapirespone.get(IAPIConstantCodes.APIResponseHttpCode) + eventDetailapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	  	customReport.checkinglist(statusValue);
	}
	
	@Test(description="To Verify the EventDetail using EventDetail API with MediaViewer",groups = {GETEVENTDETAILSAPI})
	public void TC_05_GET_EventDetail_api_check_With_MediaViewer_Negative(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();	
		
		createEventBean.setDays(ICreateEventService.setDay_01);
		createEventBean.setHours(ICreateEventService.setMinutes_40);
		apibeanfactory.EventBean(createEventBean);
		
		//EventAdminId
		HashMap<String,String>eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.GetEventDetailseventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
										
		//GroupIds UI
		 String groupIdArray[]={create_groupfromUI()};
		 createEventBean.setGroupIds(groupIdArray); 
		 
		//UserIds
		HashMap<String,String>userIds = userservices.doLogin(apiutils.userJson(IUsersList.GetEventDetailsmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String userIdArray[]={userIds.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setUserIds(userIdArray);
		
		//ModeratorIds
		HashMap<String,String>moderatorIds = userservices.doLogin(apiutils.userJson(IUsersList.GetEventDetailsmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(moderatorIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String moderatorArray[]={moderatorIds.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setModeratorIds(moderatorArray);	
		createEventBean.setAccessControl("Private");
				
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		loginapiresponse_mediaviewer = userservices.doLogin(apiutils.userJson(IUsersList.GetEventDetailsmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediaviewer.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_mediaviewer.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse_mediaviewer);		
		loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIEVENTID, createteamapirespone.get(IAPIConstantCodes.APIEVENTID));
		
		GetEventDetailsAPI getEventDetailsAPI=new GetEventDetailsAPI();
		eventDetailapirespone = getEventDetailsAPI.eventDetail(loginapiresponse_mediaviewer);
	    assertionapiresponse.verifyAssert_httpCode(eventDetailapirespone.get(IAPIConstantCodes.APIResponseHttpCode) + eventDetailapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpStatusCodeInt401null);
	  	customReport.customizedReport(HttpStatusCode.httpStatusCodeInt401null, eventDetailapirespone.get(IAPIConstantCodes.APIResponseHttpCode) + eventDetailapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	  	customReport.checkinglist(statusValue);
	}
	
	@Test(description="To Verify the EventDetail using EventDetail API with Event Host",groups = {GETEVENTDETAILSAPI})
	public void TC_06_GET_EventDetail_api_check_With_EventHost_Positive(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();			
		createEventBean.setDays(ICreateEventService.setDay_01);
		createEventBean.setHours(ICreateEventService.setMinutes_30);
		apibeanfactory.EventBean(createEventBean);
		
		//EventAdminId
		HashMap<String,String>eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.GetEventDetailseventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
								
		//GroupIds UI
		 String groupIdArray[]={create_groupfromUI()};
	     createEventBean.setGroupIds(groupIdArray); 
	     
		//UserIds
		HashMap<String,String>userIds = userservices.doLogin(apiutils.userJson(IUsersList.GetEventDetailsmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String userIdArray[]={userIds.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setUserIds(userIdArray);
		
		//ModeratorIds
		HashMap<String,String>moderatorIds = userservices.doLogin(apiutils.userJson(IUsersList.GetEventDetailsmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(moderatorIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String moderatorArray[]={moderatorIds.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setModeratorIds(moderatorArray);	
		createEventBean.setAccessControl("Private");
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
	    loginapiresponse_eventhost = userservices.doLogin(apiutils.userJson(IUsersList.GetEventDetailseventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_eventhost.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_eventhost.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse_eventhost);
		
	    loginapiresponse_eventhost.put(IAPIConstantCodes.APIEVENTID, createteamapirespone.get(IAPIConstantCodes.APIEVENTID));		
		GetEventDetailsAPI getEventDetailsAPI=new GetEventDetailsAPI();
		eventDetailapirespone = getEventDetailsAPI.eventDetail(loginapiresponse_eventhost);
	    assertionapiresponse.verifyAssert_httpCode(eventDetailapirespone.get(IAPIConstantCodes.APIResponseHttpCode) + eventDetailapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpStatusCodeInt401null);
	  	customReport.customizedReport(HttpStatusCode.httpStatusCodeInt401null, eventDetailapirespone.get(IAPIConstantCodes.APIResponseHttpCode) + eventDetailapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	  	customReport.checkinglist(statusValue);
	    
		
	}
	
		@AfterMethod(alwaysRun = true)
	public void browserClose(ITestResult result)
	{
		logger.info("In After method class");
		 statusValue.clear();
		 if(!result.isSuccess()){
		Reporter.log("Screen shot for failed Test Case" +customReport.AssertionresultOutput(driver, sTestcaseName));
		  }
		browserQuit();
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

	