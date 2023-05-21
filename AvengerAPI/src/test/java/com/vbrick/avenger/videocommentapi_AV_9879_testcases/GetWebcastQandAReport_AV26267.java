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
import com.vbrick.avenger.dao.AddUserBeanPage;
import com.vbrick.avenger.dao.AvengerEventDetailsBeanPage;
import com.vbrick.avenger.dao.AvengerEventInviteURLGuestUserLoginBeanPage;
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
import com.vbrick.avenger.pageobjects.AvengerEventWebCastPage;
import com.vbrick.avenger.pageobjects.AvengerEventsPage;
import com.vbrick.avenger.pageobjects.AvengerGroupsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerInviteURLGuestUserLoginPage;
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
import com.vbricks.avenger.serviceimpl.GetEventQandAReportAPI;
import com.vbricks.avenger.serviceimpl.PresentationProfilesdetailsAPI;
import com.vbricks.avenger.serviceimpl.SearchAccessEntityAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.ConstantsValue;
import com.vbricks.avenger.utils.HttpStatusCode;
public class GetWebcastQandAReport_AV26267 extends TestBase{

	private static Logger logger = Logger.getLogger(GetWebcastQandAReport_AV26267.class);
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
	private String myBrowser;
	private BasePage basePage,basePage2;
	private AddCustomDeviceBeanPage addcustomdevicebeanpage; 
	private AvengerEventWebCastPage avengereventwebcastpage;
	private AvengerInviteURLGuestUserLoginPage avengerInviteURLGuestUserLoginPage;
	private CreateEventBean createEventBean;
	public ApiBeanFactory apibeanfactory;
 	private HashMap<String, String> loginapiresponse;
 	private HashMap<String, String> loginapiresponse1;
	private HashMap<String, String> eventDetailapirespone; 
	private AvengerEventInviteURLGuestUserLoginBeanPage eventguestuserloginbeanpage;
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

	@Test(description="To Verify the EventDetail using EventDetail API with Account Admin",groups = {GETWEBCASTQANDAREPORTAPI})
	public void TC_01_GET_EventDetail_api_check_With_AccountAdmin_Positive(ITestContext context ) throws ParseException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();		

		UserServices userservices = new UserServices();			
		createEventBean.setDays(ICreateEventService.setDay_00);
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
		
		//createEventBean.setAutomatedWebcast("true");
		createEventBean.setAnonymousQuestionsEnabled("true");
		createEventBean.setIsPublic("true");
		createEventBean.setChatEnabled("true");
		createEventBean.setQuestionAndAnswerEnabled("true");
		createEventBean.setLobbytime("20");
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
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
			
		logger.info("Selenium Code is excuting");
        launchURL(surl);
        customReport.reporter("Application launch with URL : ", surl);
        homePage = loginPage.loginAs(sUserName, sPassword);
        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());
        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
        avengereventdetailspage.pause(8000);
		avengereventwebcastpage=avengereventdetailspage.start_Webcast();
		avengereventwebcastpage.clickStartBroadCast();
		AvengerEventWebCastPage avengereventwebcasthostpage=avengereventwebcastpage.getDriverContorl(driver, customReport);
		String eventurl=avengereventdetailspage.getCurrentURL();
		logger.info("New Event URL is: "+eventurl);
	
		createNewDriver(eventurl);
		eventguestuserloginbeanpage=new AvengerEventInviteURLGuestUserLoginBeanPage();
		AvengerInviteURLGuestUserLoginPage signinregistered = new AvengerInviteURLGuestUserLoginPage(secondDriver, customReport, basePage);
		signinregistered.click_licensedSigninLink();
		homePage = loginPage.loginAs(IUsersList.VideoPendingeventadmin, sPassword);

		//Question 1 asked by Event Attendee
		avengereventwebcastpage = basePage2.avengerEventWebCastPage(secondDriver, customReport, basePage2);
		AvengerEventWebCastPage avengereventwebcastattendeepage=avengereventwebcastpage.getDriverContorl(driver, customReport);
		avengereventwebcastattendeepage.pause(10000);
		avengereventwebcastattendeepage.clickQuestion();

		// 2.Existing Q&A section is relabeled to "My Questions"
		customReport.customizedReport(true,avengereventwebcastattendeepage.verify_label(bundle.getString("eventwebcastpg.myquestions.text")).contains(bundle.getString("eventwebcastpg.myquestions.text")) , statusValue, driver, eventurl);

		//3.Attendees can view questions/responses a moderator or event admin has published to all attendee
		String question1=avengerEventdetailsbeanpage.getAskaquestion();
		avengereventwebcastattendeepage.addQuestion("Do you Agree with him?");
		avengereventwebcastattendeepage.clickQuestionSubmitButton();
		avengereventwebcasthostpage.clickQuestion();
		avengereventwebcasthostpage.clickQuestionResponse("Do you Agree with him?", bundle.getString("eventwebcastpg.questionreplyreply"));
		avengereventwebcasthostpage.replyQuestion(bundle.getString("eventwebcastpg.attendeeviewhostreply"));
		avengereventwebcasthostpage.check_publishtoall();
		avengereventwebcasthostpage.click_replyQuestionSaveButton();

		// API Code to GET Webcast Q&A Report		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createteamapirespone.get(IAPIConstantCodes.APIEVENTID));
		 
		GetEventQandAReportAPI getEventQandAReportAPI = new GetEventQandAReportAPI();
		HashMap<String, String> response = getEventQandAReportAPI.eventreport(loginapiresponse1);
		response.put(IAPIConstantCodes.APIEVENTID, createteamapirespone.get(IAPIConstantCodes.APIEVENTID));
		assertionapiresponse.verifyAssert_httpCode(response.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(true,response.get(IAPIConstantCodes.APIRESPONSEJSON).contains("whenReplied"), statusValue, driver, sTestcaseName);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, response.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="To Verify the EventDetail using EventDetail API with Account Admin when No Response for the Question",groups = {GETWEBCASTQANDAREPORTAPI})
	public void TC_02_GET_EventDetail_api_check_With_AccountAdmin_When_No_Response_ForTheQuestion_Positive(ITestContext context ) throws ParseException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();			
		createEventBean.setDays(ICreateEventService.setDay_00);
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
		createEventBean.setAnonymousQuestionsEnabled("true");
		createEventBean.setIsPublic("true");
		createEventBean.setChatEnabled("true");
		createEventBean.setQuestionAndAnswerEnabled("true");
		createEventBean.setLobbytime("20");
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
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
	
		logger.info("Selenium Code is excuting");
        launchURL(surl);
        customReport.reporter("Application launch with URL : ", surl);
        homePage = loginPage.loginAs(sUserName, sPassword);
        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());
        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
        avengereventdetailspage.pause(8000);
		avengereventwebcastpage=avengereventdetailspage.start_Webcast();
		avengereventwebcastpage.clickStartBroadCast();
		AvengerEventWebCastPage avengereventwebcasthostpage=avengereventwebcastpage.getDriverContorl(driver, customReport);
		String eventurl=avengereventdetailspage.getCurrentURL();
		logger.info("New Event URL is: "+eventurl);

		createNewDriver(eventurl);
		eventguestuserloginbeanpage=new AvengerEventInviteURLGuestUserLoginBeanPage();
		AvengerInviteURLGuestUserLoginPage signinregistered = new AvengerInviteURLGuestUserLoginPage(secondDriver, customReport, basePage);
		signinregistered.click_licensedSigninLink();
		homePage = loginPage.loginAs(IUsersList.VideoPendingeventadmin, sPassword);

		//Question 1 asked by Event Attendee
		avengereventwebcastpage = basePage2.avengerEventWebCastPage(secondDriver, customReport, basePage2);
		AvengerEventWebCastPage avengereventwebcastattendeepage=avengereventwebcastpage.getDriverContorl(driver, customReport);
		avengereventwebcastattendeepage.pause(10000);
		avengereventwebcastattendeepage.clickQuestion();

		// 2.Existing Q&A section is relabeled to "My Questions"
		customReport.customizedReport(true,avengereventwebcastattendeepage.verify_label(bundle.getString("eventwebcastpg.myquestions.text")).contains(bundle.getString("eventwebcastpg.myquestions.text")) , statusValue, driver, eventurl);

		//3.Attendees can view questions/responses a moderator or event admin has published to all attendee
		String question1=avengerEventdetailsbeanpage.getAskaquestion();
		avengereventwebcastattendeepage.addQuestion("Do you Agree with him?");
		avengereventwebcastattendeepage.clickQuestionSubmitButton();
		
		// API Code to GET Webcast Q&A Report
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createteamapirespone.get(IAPIConstantCodes.APIEVENTID));
				 
		GetEventQandAReportAPI getEventQandAReportAPI = new GetEventQandAReportAPI();
		HashMap<String, String> response = getEventQandAReportAPI.eventreport(loginapiresponse1);
		response.put(IAPIConstantCodes.APIEVENTID, createteamapirespone.get(IAPIConstantCodes.APIEVENTID));
		assertionapiresponse.verifyAssert_httpCode(response.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(true,response.get(IAPIConstantCodes.APIRESPONSEJSON).contains("whenReplied"), statusValue, driver, sTestcaseName);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, response.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
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
	public void createNewDriver(String url)
	{
		
		secondDriver = secondDriverInitialization(myBrowser);
		basePage2 = new BasePage(secondDriver, customReport, new BasePage());
		loginPage = basePage2.avengerLoginPage(secondDriver, customReport, basePage2);
		secondDriver.manage().window().maximize();
		secondDriver.get(url);

	}
	}	

