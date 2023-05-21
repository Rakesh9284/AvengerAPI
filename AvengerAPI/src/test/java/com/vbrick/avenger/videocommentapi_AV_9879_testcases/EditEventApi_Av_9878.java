package com.vbrick.avenger.videocommentapi_AV_9879_testcases;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
import com.vbrick.avenger.apibeans.AddGroupBean;

import com.vbrick.avenger.apibeans.AddVideoCommentBean;
import com.vbrick.avenger.apibeans.CreateEventBean;
import com.vbrick.avenger.apibeans.EventAccessControlBean;
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
import com.vbrick.avenger.funUtil.DateTime;
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
import com.vbrick.avenger.pageobjects.AvengerUserDashboardPage;
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
import com.vbricks.avenger.service.IVideoAccessControlService;
import com.vbricks.avenger.serviceimpl.CreateCategoryAPI;
import com.vbricks.avenger.serviceimpl.CreateEventsAPI;
import com.vbricks.avenger.serviceimpl.CreateGroupAPI;
import com.vbricks.avenger.serviceimpl.CustomFieldsAPI;
import com.vbricks.avenger.serviceimpl.DMEdeviceslistAPI;
import com.vbricks.avenger.serviceimpl.EditAccessControlEntitiesforeventAPI;
import com.vbricks.avenger.serviceimpl.EditEventAPI;
import com.vbricks.avenger.serviceimpl.EditGroupAPI;
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

public class EditEventApi_Av_9878 extends TestBase {

	private static Logger logger = Logger.getLogger(EditEventApi_Av_9878.class);
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
	private AddGroupBean addgroupbean;
	private AvengerDevicesPage avengerdevicespage;
	private AvengerEventDetailsPage avengereventdetailspage;
	private AvengerPresentationProfilesPage avengerpresentationprofilespage;
	private AvengerAddPresentationProfilePage avengeraddpresentationprofilepage;
	private AddPresentationprofileBeanPage addpresentationprofilebeanpage;
	private AddPresentationprofileBeanPage addeditpresentationprofilebeanpage;
	private AvengerAddCustomDevicePage avengeraddcustomdevicepage;
	private AddNewDmeBeanPage addnewdmedevicebeanpage;
	private BasePage basePage;
	private AddCustomDeviceBeanPage addcustomdevicebeanpage;
	private AddCustomDeviceBeanPage addeditcustomdevicebeanpage;
	private CreateEventBean createEventBean;
	public ApiBeanFactory apibeanfactory;
	private HashMap<String, String> loginapiresponse;
	private HashMap<String, String> loginapiresponse1;
 	private HashMap<String, String> loginapiresponse2;
	private HashMap<String, String> editEventapirespone;
	private HashMap<String, String> creategroupapirespone;
	private HashMap<String, String> createcategoryapirespone;
	private HashMap<String,String >editGroupapiresponse;
	private AvengerHomePage avengerHomePage;
	private AvengerUserDashboardPage avengeruserdashboardpage;
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
		addgroupbean=new AddGroupBean();
		addcategorybean=new AddCategoryBean();
		createEventBean = new CreateEventBean();
		apibeanfactory = new ApiBeanFactory();
		addpresentationprofilebeanpage = new AddPresentationprofileBeanPage();
		addeditpresentationprofilebeanpage = new AddPresentationprofileBeanPage();
		addnewdmedevicebeanpage = new AddNewDmeBeanPage();
		assertionapiresponse = new AssertionAPIResponse();
		addcustomdevicebeanpage = new AddCustomDeviceBeanPage();
		addeditcustomdevicebeanpage = new AddCustomDeviceBeanPage();
		apiutils = new ApiUtils();
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
		accountBeansFactory.createPresentationProfile(addeditpresentationprofilebeanpage);
		accountBeansFactory.AddNewDmeBean(addnewdmedevicebeanpage);
		accountBeansFactory.addCustomDeviceBean(addcustomdevicebeanpage);
		accountBeansFactory.addCustomDeviceBean(addeditcustomdevicebeanpage);
	}
	
	@Test(description = "To Verify the EditEvent using EditEvent API with Account Admin with only mandatory fields and updating title,start and end dates",groups = {EDITEVENTAPI})
	public void TC_01_PUT_EditEvent_AccountAdmin_withmandatoryfields_Update_Titlestartdateandenddate_Positive(ITestContext context) throws ParseException {

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
		String presentationProfileId[] = { create_presentationProfileUI() };
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
				
		//GroupIds UI
		String groupIdArray[]={create_groupfromUI()};
		createEventBean.setGroupIds(groupIdArray);	
		
		//UserIds
		HashMap<String,String>userIds = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String userIdArray[]={userIds.get(IUserAccountsService.USERID)};
		createEventBean.setUserIds(userIdArray);
				
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		createEventBean.setDescription("");
		createEventBean.setAccessControl("Private");
		createEventBean.setShortcut("");
						
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
		
	    loginapiresponse.put(IAPIConstantCodes.APIEVENTID, createteamapirespone.get(IAPIConstantCodes.APIEVENTID));	    
		createEventBean.setDays(ICreateEventService.setDay_01);
		createEventBean.setHours(ICreateEventService.setMinutes_30);
		apibeanfactory.EventBean(createEventBean);		
		createEventBean.setDescription("");
		createEventBean.setShortcut("");
		
		//EventAdminId
		createEventBean.setEventAdminId(eventadminIdArray);
				
		//PresentationProfileId UI
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
				
		// Edit Event
		EditEventAPI editEventAPI = new EditEventAPI();
		editEventapirespone = editEventAPI.EditEvent(loginapiresponse, createEventBean);
		assertionapiresponse.verifyAssert_httpCode(editEventapirespone.get(IAPIConstantCodes.APIResponseHttpCode) + editEventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editEventapirespone.get(IAPIConstantCodes.APIResponseHttpCode) + editEventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);
		
		 logger.info("Selenium Code is excuting");
	     launchURL(surl);
	     customReport.reporter("Application launch with URL : ", surl);
	     homePage = loginPage.loginAs(sUserName, sPassword);
	     AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	     AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	     AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	     logger.info("THe title is@@@"+createEventBean.getTitle());	    
	     avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());
	     avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	     HashMap<String,String> eventDetails=avengereventdetailspage.get_EventData();
	     String startdateAPI = DateTime.toDate2(createEventBean.getStartDate());
	     String enddateAPI = DateTime.toDate2(createEventBean.getEndDate());     	     
	     customReport.customizedReport(createEventBean.getTitle(),eventDetails.get(IUserAccountsService.TITLE), statusValue, driver, sTestcaseName);
	     customReport.customizedReport(startdateAPI, eventDetails.get("startdate"), statusValue, driver, sTestcaseName);
	     customReport.customizedReport(enddateAPI, eventDetails.get("enddate"), statusValue, driver, sTestcaseName);
	     customReport.customizedReport(true,avengereventdetailspage.verify_eventlisting("Public"),statusValue, driver, sTestcaseName);
	     customReport.customizedReport("Presentation",avengereventdetailspage.verify_videosourcetype(),statusValue, driver, sTestcaseName);
	     customReport.checkinglist(statusValue);
        
	}
	
	@Test(description = "To Verify the EditEvent with Media Admin with only mandatory fields and with VC Sip address and updating userids and groupids",groups = {EDITEVENTAPI})
	public void TC_02_PUT_EditEvent_MediaAdmin_withmandatoryfieldsandVCaddress_Update_useridsandgroupids_Positive(ITestContext context) throws ParseException {

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
		
		//GroupIds UI
		String groupIdArray[]={create_groupfromUI()};
		createEventBean.setGroupIds(groupIdArray);	
		
		//UserIds
		HashMap<String,String>userIds = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String userIdArray[]={userIds.get(IUserAccountsService.USERID)};
		createEventBean.setUserIds(userIdArray);
				
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);	
		createEventBean.setDescription("");
		createEventBean.setAccessControl("Private");
		createEventBean.setShortcut("");
		createEventBean.setPresentationProfileId("");
		createEventBean.setVideoAddress("rajitha.dronadala@vb.webex.com");
						
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
		
	    loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createteamapirespone.get(IAPIConstantCodes.APIEVENTID));
		
		//GroupIds UI
		String groupIdArray1[]={create_groupfromUI()};
		createEventBean.setGroupIds(groupIdArray1);	
				
		//UserIds
		HashMap<String,String>userIds1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String userIdArray1[]={userIds1.get(IUserAccountsService.USERID)};
		createEventBean.setUserIds(userIdArray1);
			
		// Edit Event
		EditEventAPI editEventAPI = new EditEventAPI();
		editEventapirespone = editEventAPI.EditEvent(loginapiresponse1, createEventBean);
		assertionapiresponse.verifyAssert_httpCode(editEventapirespone.get(IAPIConstantCodes.APIResponseHttpCode) + editEventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editEventapirespone.get(IAPIConstantCodes.APIResponseHttpCode) + editEventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);
		
		 logger.info("Selenium Code is excuting");
	     launchURL(surl);
	     customReport.reporter("Application launch with URL : ", surl);
	     homePage = loginPage.loginAs(sUserName, sPassword);
	     AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	     AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	     AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	     logger.info("THe title is@@@"+createEventBean.getTitle());	    
	     avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());
	     avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	     HashMap<String,String> eventDetails=avengereventdetailspage.get_EventDataforVideoAddress();    	     
	     customReport.customizedReport(createEventBean.getTitle(),eventDetails.get(IUserAccountsService.TITLE), statusValue, driver, sTestcaseName);
	     customReport.customizedReport(true,avengereventdetailspage.verify_eventlisting("Private"),statusValue, driver, sTestcaseName);
	     customReport.customizedReport(true,avengereventdetailspage.get_allprivateusersandgroups().contains("apimcu10 apimcu10"),statusValue, driver, sTestcaseName);
	     customReport.customizedReport(true,avengereventdetailspage.get_allprivateusersandgroups().contains(avengergroupsbeanpage.getNewgroup()),statusValue, driver, sTestcaseName);
	     customReport.customizedReport(createEventBean.getVideoAddress(), eventDetails.get(ICreateEventService.VideoAddress),statusValue, driver, sTestcaseName);
	     customReport.customizedReport("15", avengereventdetailspage.getLobbyTime(), statusValue, driver, sTestcaseName);
	     customReport.checkinglist(statusValue);
        
		
	}

	@Test(description = "To Verify the EditEvent with Event Host with only mandatory fields and presentation profile and eventadmin id for public event",groups = {EDITEVENTAPI})
	public void TC_03_PUT_EditEvent_EventHost_public_fromallfieldstomandatoryfieldsafteredit_Update_eventadminidandPPfromVCaddress_eventadminaseventhost_Positive(ITestContext context) throws ParseException, InterruptedException {

		logger.info("API Level Code is executing");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();			
		createEventBean.setDays(ICreateEventService.setDay_01);
		createEventBean.setHours(ICreateEventService.setMinutes_10);
		apibeanfactory.EventBean(createEventBean);
		
		//EventAdminId
		HashMap<String,String>eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		//UserIds
		HashMap<String,String>userIds = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String userIdArray[]={userIds.get(IUserAccountsService.USERID)};
		createEventBean.setUserIds(userIdArray);		
		createEventBean.setVideoAddress("rajitha.dronadala@vb.webex.com");
		createEventBean.setPresentationProfileId("");
		createEventBean.setLobbytime("20");
		createEventBean.setAutomatedWebcast("true");
		createEventBean.setAnonymousQuestionsEnabled("true");
		createEventBean.setIsPublic("true");
		createEventBean.setPollsEnabled("true");
		createEventBean.setChatEnabled("true");
		createEventBean.setQuestionAndAnswerEnabled("true");
		createEventBean.setEstimatedAttendees("10");
		createEventBean.setUnlisted("true");
									
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//set preproduction values. For this create a new user and group
		createEventBean.setPreproduction("Yes");
		
		//API Preproduction duration should be given in timespan format which is dd:mm:hh:ss
		createEventBean.setDuration("00:00:25:00");
				
		//UserIds
		HashMap<String,String>userIds1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediacontributor), surl);
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
		
	    loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createteamapirespone.get(IAPIConstantCodes.APIEVENTID));
		//PresentationProfileId UI
		String presentationProfileId1[] = { create_presentationProfileUI1()};
		createEventBean.setPresentationProfileId(presentationProfileId1[0]);
					
		//EventAdminId
		HashMap<String,String>eventAdminId1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray1[]={eventAdminId1.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray1);	
		createEventBean.setVideoAddress("");
		createEventBean.setAutomatedWebcast("");
		createEventBean.setAnonymousQuestionsEnabled("");
		createEventBean.setIsPublic("");
		createEventBean.setPollsEnabled("");
		createEventBean.setChatEnabled("");
		createEventBean.setQuestionAndAnswerEnabled("");
		createEventBean.setEstimatedAttendees("");
		createEventBean.setUnlisted("");
		createEventBean.setShortcut("");
		createEventBean.setDescription("");
		createEventBean.setLobbytime("No");		
		createEventBean.setPreproduction("No");
		
			
		// Edit Event
		EditEventAPI editEventAPI = new EditEventAPI();
		editEventapirespone = editEventAPI.EditEvent(loginapiresponse1, createEventBean);
		assertionapiresponse.verifyAssert_httpCode(editEventapirespone.get(IAPIConstantCodes.APIResponseHttpCode) + editEventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editEventapirespone.get(IAPIConstantCodes.APIResponseHttpCode) + editEventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);
		
		 logger.info("Selenium Code is excuting");
	     launchURL(surl);
	     customReport.reporter("Application launch with URL : ", surl);
	     homePage = loginPage.loginAs(sUserName, sPassword);
	     AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	     AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	     AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	     logger.info("THe title is@@@"+createEventBean.getTitle());	    
	     avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());
	     avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	     HashMap<String,String> eventDetails=avengereventdetailspage.get_EventData();    	     
	     customReport.customizedReport(createEventBean.getTitle(),eventDetails.get(IUserAccountsService.TITLE), statusValue, driver, sTestcaseName);
	     customReport.customizedReport(true,avengereventdetailspage.verify_eventlisting("Public"),statusValue, driver, sTestcaseName);
	     customReport.customizedReport(true,avengereventdetailspage.get_EventHostUsers().contains("apieau10 apieau10"), statusValue, driver, sTestcaseName);
	     customReport.customizedReport(false,avengereventdetailspage.get_allAvailableUsers().contains("apiehu10 apiehu10"), statusValue, driver, sTestcaseName);
	     customReport.customizedReport(false,avengereventdetailspage.get_allAvailableUsers().contains("apimcu10 apimcu10"), statusValue, driver, sTestcaseName);
	     customReport.customizedReport("15", avengereventdetailspage.getLobbyTime(), statusValue, driver, sTestcaseName);
	     customReport.customizedReport("Presentation",avengereventdetailspage.verify_videosourcetype(),statusValue, driver, sTestcaseName);
	     customReport.customizedReport("",eventDetails.get(ICreateEventService.Shortcut) ,statusValue, driver, sTestcaseName);
	     customReport.checkinglist(statusValue);
	}
	
	
	@Test(description = "To Verify the EditEvent with Event Admin with more than three event admins",groups = {EDITEVENTAPI})
	public void TC_04_PUT_EditEvent_EventAdmin_public_morethanthreeeventadmin_Negative(ITestContext context)  {

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
		String presentationProfileId[] = { create_presentationProfileUI() };
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
							
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);							
		createEventBean.setDescription("");
		createEventBean.setAccessControl("Public");	

		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
		
	    loginapiresponse.put(IAPIConstantCodes.APIEVENTID, createteamapirespone.get(IAPIConstantCodes.APIEVENTID));	    
	    HashMap<String,String> eventAdminId1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		HashMap<String,String> eventAdminId2 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		HashMap<String,String> eventAdminId3 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventhost), surl);
		HashMap<String,String> eventAdminId4 = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId2.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId3.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId4.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray1[]={eventAdminId1.get(IUserAccountsService.USERID),eventAdminId2.get(IUserAccountsService.USERID),eventAdminId3.get(IUserAccountsService.USERID),eventAdminId4.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray1);
	    
		// Edit Event
		EditEventAPI editEventAPI = new EditEventAPI();
		editEventapirespone = editEventAPI.EditEvent(loginapiresponse, createEventBean);
		assertionapiresponse.verifyAssert_httpCode(editEventapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus500);
		customReport.customizedReport(HttpStatusCode.httpsStatus500, editEventapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.customizedReport(editEventapirespone.get(IAPIConstantCodes.APIResponseJsonErrorCode), HttpStatusCode.eventCode1, statusValue, driver, sTestcaseName);
		customReport.customizedReport(editEventapirespone.get(IAPIConstantCodes.APIResponseJsonErrorDetail), HttpStatusCode.eventErrorDetail3, statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
 	
	}
	
	@Test(description = "To Verify the EditEvent with Event Host and event host not as event admin",groups = {EDITEVENTAPI})
	public void TC_05_PUT_EditEvent_EventHost_public_Negative(ITestContext context)  {

		logger.info("API Level Code is executing");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		//deleting the assigned groups if any for the Media Viewer user
		delete_assignedGroupsForUser(IUsersList.CreateEventeventhost);

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
		String presentationProfileId[] = { create_presentationProfileUI() };
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
							
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
							
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
		
	    loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createteamapirespone.get(IAPIConstantCodes.APIEVENTID));
	    createEventBean.setDescription("description changed");
	    
		// Edit Event
		EditEventAPI editEventAPI = new EditEventAPI();
		editEventapirespone = editEventAPI.EditEvent(loginapiresponse1, createEventBean);
		assertionapiresponse.verifyAssert_httpCode(editEventapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, editEventapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);	
		customReport.checkinglist(statusValue);		
	}
	
	@Test(description = "To Verify the EditEvent with Media Viewer by assigning to a group having event admin access,update from private to public and include pre prod for edit",groups = {EDITEVENTAPI})
	public void TC_06_PUT_EditEvent_MediaViewer_assigningtoeventadmingroup_privatetopublic_includepreprodforedit_Positive(ITestContext context) throws ParseException {

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
		
		//PresentationProfileId UI
		String presentationProfileId1[] = { create_presentationProfileUI1()};
		createEventBean.setPresentationProfileId(presentationProfileId1[0]);
		
		//UserIds
		HashMap<String,String>userIds = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String userIdArray[]={userIds.get(IUserAccountsService.USERID)};
		createEventBean.setUserIds(userIdArray);		
		createEventBean.setPollsEnabled("false");
		createEventBean.setChatEnabled("true");
		createEventBean.setQuestionAndAnswerEnabled("true");
		createEventBean.setAccessControl("Private");
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//assigning mediaviewer to a group having access
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaviewer1), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		//getting the userIds
		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		String[] userIds2 = {loginapiresponse.get("userId")};
		addgroupbean.setUserids(userIds2);
										
		//getting roleids
		UserServices userServices=new UserServices();
		HashMap<String, String> userroleapirespone = userServices.getRolesApi(loginapiresponse1,IAPIConstantCodes.EVENTADMIN);
		String roleid=apiutils.formatingapiresponse(userroleapirespone.get(IAPIConstantCodes.ROLEID));
		String[] roleIds = {userroleapirespone.get("roleId")};
		addgroupbean.setRoleids(roleIds);
								
		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
		creategroupapirespone = creategroupAPI.createGroup(loginapiresponse1,creategroupjson);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse1,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
		
	    loginapiresponse.put(IAPIConstantCodes.APIEVENTID, createteamapirespone.get(IAPIConstantCodes.APIEVENTID));	    
		createEventBean.setPreproduction("Yes");
		createEventBean.setAccessControl("Public");

		//set preproduction values. For this create a new user and group
		createEventBean.setPreproduction("Yes");
		
		//API Preproduction duration should be given in timespan format which is dd:mm:hh:ss
		createEventBean.setDuration("00:00:25:00");
				
		//UserIds
		HashMap<String,String>userIds1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String userIdArray1[]={userIds1.get(IUserAccountsService.USERID)};
		createEventBean.setPUserIds(userIdArray1);
		
		//GroupIds UI
		String groupIdArray[]={create_groupfromUI()};
		createEventBean.setPGroupIds(groupIdArray);
		
		// Edit Event
		EditEventAPI editEventAPI = new EditEventAPI();
		editEventapirespone = editEventAPI.EditEvent(loginapiresponse, createEventBean);
		assertionapiresponse.verifyAssert_httpCode(editEventapirespone.get(IAPIConstantCodes.APIResponseHttpCode) + editEventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editEventapirespone.get(IAPIConstantCodes.APIResponseHttpCode) + editEventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);
		
		//Removing the assigned user mediaviewer from Event Admin group
	    addgroupbean.setUserids(null);
	    addgroupbean.setRoleids(null);
   	    EditGroupAPI editGroupAPI = new EditGroupAPI();
		loginapiresponse1.put("groupId", creategroupapirespone.get(IAPIConstantCodes.APIGROUPID));
		JSONObject creategroupjsonedit=creategroupAPI.createGroupJson(addgroupbean);

		editGroupapiresponse = editGroupAPI.editGroup(loginapiresponse1, creategroupjsonedit);
		assertionapiresponse.verifyAssert_httpCode(
		editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + editGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + editGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,
		sTestcaseName);
		
		 logger.info("Selenium Code is excuting");
	     launchURL(surl);
	     customReport.reporter("Application launch with URL : ", surl);
	     homePage = loginPage.loginAs(sUserName, sPassword);
	     AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	     AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	     AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	     logger.info("THe title is@@@"+createEventBean.getTitle());	    
	     avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());
	     avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	     HashMap<String,String> eventDetails=avengereventdetailspage.get_EventData();     	     
	     customReport.customizedReport(createEventBean.getTitle(),eventDetails.get(IUserAccountsService.TITLE), statusValue, driver, sTestcaseName);
	     customReport.customizedReport(true,avengereventdetailspage.verify_eventlisting("Public"),statusValue, driver, sTestcaseName);
	     customReport.customizedReport(true,avengereventdetailspage.get_EventHostUsers().contains("apiehu11 apiehu11"), statusValue, driver, sTestcaseName);
	     customReport.customizedReport("Presentation",avengereventdetailspage.verify_videosourcetype(),statusValue, driver, sTestcaseName);
	     customReport.checkinglist(statusValue);
        
		
	}
	
	@Test(description = "To Verify the EditEvent with Media contributor for an All Users Event",groups = {EDITEVENTAPI})
	public void TC_07_PUT_EditEvent_MediaContributor_AllUsers_Negative(ITestContext context)  {

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
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		createEventBean.setPresentationProfileId("");
		createEventBean.setVideoAddress("rajitha.dronadala@vb.webex.com");
		createEventBean.setAccessControl("AllUsers");
		
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
		
	    loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createteamapirespone.get(IAPIConstantCodes.APIEVENTID));
	    createEventBean.setDescription("description changed");
	    
		// Edit Event
		EditEventAPI editEventAPI = new EditEventAPI();
		editEventapirespone = editEventAPI.EditEvent(loginapiresponse1, createEventBean);
		assertionapiresponse.verifyAssert_httpCode(editEventapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, editEventapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
	}

	@Test(description = "To Verify the EditEvent with Media Viewer for an private event",groups = {EDITEVENTAPI})
	public void TC_08_PUT_EditEvent_MediaViewer_private_Negative(ITestContext context)  {

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
		String presentationProfileId[] = { create_presentationProfileUI() };
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
							
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
		createEventBean.setAccessControl("Private");
		
		//UserIds
		HashMap<String,String>userIds1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String userIdArray1[]={userIds1.get(IUserAccountsService.USERID)};
		createEventBean.setUserIds(userIdArray1);
										
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
		
	    loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createteamapirespone.get(IAPIConstantCodes.APIEVENTID));
	    createEventBean.setDescription("description changed");
	    
		// Edit Event
		EditEventAPI editEventAPI = new EditEventAPI();
		editEventapirespone = editEventAPI.EditEvent(loginapiresponse1, createEventBean);
		assertionapiresponse.verifyAssert_httpCode(editEventapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, editEventapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description = "To Verify the EditEvent with Media Admin ,update from public to private and update categories and tags and update from PP to VC",groups = {EDITEVENTAPI})
	public void TC_09_PUT_EditEvent_MediaAdmin_publictoprivate_updatecategoriesandtags_updatefromPPtoVC_Positive(ITestContext context) throws ParseException {

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
		
		//PresentationProfileId UI
		String presentationProfileId1[] = { create_presentationProfileUI1()};
		createEventBean.setPresentationProfileId(presentationProfileId1[0]);
		createEventBean.setAccessControl("Public");
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);	
				
		//assign tag to an event
		String tagids[] = {"tag1"};
		createEventBean.setTags(tagids);
		
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
		
	    loginapiresponse.put(IAPIConstantCodes.APIEVENTID, createteamapirespone.get(IAPIConstantCodes.APIEVENTID));	    
		createEventBean.setPreproduction("No");
		createEventBean.setAccessControl("Private");
		createEventBean.setPresentationProfileId("");
		createEventBean.setVideoAddress("rajitha.dronadala@vb.webex.com");
	
		//UserIds
		HashMap<String,String>userIds = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String userIdArray[]={userIds.get(IUserAccountsService.USERID)};	
		createEventBean.setUserIds(userIdArray);
		
		//assign category to an event
		CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
									
		String CategoryIds[] = {createcategoryapirespone.get("categoryId")};
		createEventBean.setCategoryIds(CategoryIds);
		
		//assign tag to an event
		String tagids1[] = {"tag2","tag3"};
		createEventBean.setTags(tagids1);
		
		// Edit Event
		EditEventAPI editEventAPI = new EditEventAPI();
		editEventapirespone = editEventAPI.EditEvent(loginapiresponse, createEventBean);
		assertionapiresponse.verifyAssert_httpCode(editEventapirespone.get(IAPIConstantCodes.APIResponseHttpCode) + editEventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editEventapirespone.get(IAPIConstantCodes.APIResponseHttpCode) + editEventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);	
				
		 logger.info("Selenium Code is excuting");
	     launchURL(surl);
	     customReport.reporter("Application launch with URL : ", surl);
	     homePage = loginPage.loginAs(sUserName, sPassword);
	     AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	     AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	     AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	     logger.info("THe title is@@@"+createEventBean.getTitle());
	    
	     avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());
	     avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	     HashMap<String,String> eventDetails=avengereventdetailspage.get_EventDataforVideoAddress();    	     
	     customReport.customizedReport(createEventBean.getTitle(),eventDetails.get(IUserAccountsService.TITLE), statusValue, driver, sTestcaseName);
	     customReport.customizedReport(true,avengereventdetailspage.verify_eventlisting("Private"),statusValue, driver, sTestcaseName);
	     customReport.customizedReport(true,avengereventdetailspage.get_allprivateusersandgroups().contains("apimvu10 apimvu10"),statusValue, driver, sTestcaseName);
	     customReport.customizedReport(createEventBean.getVideoAddress(), eventDetails.get(ICreateEventService.VideoAddress),statusValue, driver, sTestcaseName);
	     customReport.customizedReport(true, avengereventdetailspage.get_Tags().contains("tag2"), statusValue, driver, sTestcaseName);
	     customReport.customizedReport(true, avengereventdetailspage.get_Tags().contains("tag3"), statusValue, driver, sTestcaseName);
	     customReport.customizedReport(true, avengereventdetailspage.get_Categories().contains(addcategorybean.getName()), statusValue, driver, sTestcaseName);
	     customReport.checkinglist(statusValue);
     
	}
	
	@Test(description = "To Verify the EditEvent with Event Admin without providing all the mandatory fields,event admin",groups = {EDITEVENTAPI})
	public void TC_10_PUT_EditEvent_EventAdmin_withoutproviding_allmandatoryfields_Negative(ITestContext context)  {

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
		String presentationProfileId[] = { create_presentationProfileUI() };
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
							
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);					
		createEventBean.setAccessControl("Public");
									
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
		
	    loginapiresponse.put(IAPIConstantCodes.APIEVENTID, createteamapirespone.get(IAPIConstantCodes.APIEVENTID));
	    createEventBean.setDescription("description changed");
	    createEventBean.setEventAdminId(null);
	    
		// Edit Event
		EditEventAPI editEventAPI = new EditEventAPI();
		editEventapirespone = editEventAPI.EditEvent(loginapiresponse, createEventBean);
		assertionapiresponse.verifyAssert_httpCode(editEventapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus500);
		customReport.customizedReport(HttpStatusCode.httpsStatus500, editEventapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description = "To Verify the EditEvent with Account Admin without providing all the mandatory fields,title",groups = {EDITEVENTAPI})
	public void TC_11_PUT_EditEvent_AccountAdmin_withoutproviding_allmandatoryfields_title_Negative(ITestContext context)  {

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
		String presentationProfileId[] = { create_presentationProfileUI() };
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
							
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);					
		createEventBean.setAccessControl("Public");
									
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
		
	    loginapiresponse.put(IAPIConstantCodes.APIEVENTID, createteamapirespone.get(IAPIConstantCodes.APIEVENTID));
	    createEventBean.setDescription("description changed");
	    createEventBean.setTitle("");
	    
		// Edit Event
		EditEventAPI editEventAPI = new EditEventAPI();
		editEventapirespone = editEventAPI.EditEvent(loginapiresponse, createEventBean);
		assertionapiresponse.verifyAssert_httpCode(editEventapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus500);
		customReport.customizedReport(HttpStatusCode.httpsStatus500, editEventapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description = "To Verify the EditEvent with Account Admin, update from public to private by giving atleast one invalid userid",groups = {EDITEVENTAPI})
	public void TC_12_PUT_EditEvent_AccountAdmin_frompublictoprivate_withvalidandinvaliduserids_Negative(ITestContext context)  {

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
		String presentationProfileId[] = { create_presentationProfileUI() };
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
							
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);				
		createEventBean.setAccessControl("Public");
									
		//create Event
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createteamapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
		
	    loginapiresponse.put(IAPIConstantCodes.APIEVENTID, createteamapirespone.get(IAPIConstantCodes.APIEVENTID));
	    createEventBean.setDescription("description changed");
	    createEventBean.setAccessControl("Private");
	    
	    //UserIds
	  	HashMap<String,String>userIds = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaviewer), surl);
	  	HashMap<String,String>userIds1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediacontributor), surl);
	  	assertionapiresponse.verifyAssert_httpCode(userIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
	  	assertionapiresponse.verifyAssert_httpCode(userIds1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
	  	String userIdArray[]={userIds.get(IUserAccountsService.USERID),userIds.get(IUserAccountsService.USERID) + 'a'};
	  	createEventBean.setUserIds(userIdArray);
	    
		//Edit Event
		EditEventAPI editEventAPI = new EditEventAPI();
		editEventapirespone = editEventAPI.EditEvent(loginapiresponse, createEventBean);
		assertionapiresponse.verifyAssert_httpCode(editEventapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus500);
		customReport.customizedReport(HttpStatusCode.httpsStatus500, editEventapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
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

	public String create_groupfromUI() {

		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		homePage.clickSettingsLink();
		avengergroupspage = homePage.clickGroupsLink();
		String grpname = avengergroupspage.createNewgroup(avengergroupsbeanpage);
		avengergroupsbeanpage.setNewgroup(grpname);
		avengergroupspage.clickNewGroup(avengergroupsbeanpage);
		String url = homePage.getCurrentURL();
		logger.info("url.split -" + url.split("/")[8]);
		homePage.click_logout();
		return url.split("/")[8];

	}

	public String create_presentationProfileUI() {
		launchURL(surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		homePage.clickSettingsLink();
		avengerdevicespage = homePage.clickAllDevicesLink();
		avengerdevicespage.click_AddDevice();
		avengeraddcustomdevicepage = avengerdevicespage.click_CustomDevice();
		avengeraddcustomdevicepage.createNewCustomDevice(addcustomdevicebeanpage);
		avengeraddcustomdevicepage.click_CreateButton(addcustomdevicebeanpage);
		avengerpresentationprofilespage = homePage.click_PresentationProfilesTab();
		avengeraddpresentationprofilepage = avengerpresentationprofilespage.click_AddPresentationprofile();
		addeditpresentationprofilebeanpage.setVideosource(addcustomdevicebeanpage.getDevicename());
		addeditpresentationprofilebeanpage.setDestinationdevicename(addcustomdevicebeanpage.getDevicename());
		avengeraddpresentationprofilepage.addPresentationProfile(addeditpresentationprofilebeanpage);
		avengeraddpresentationprofilepage.click_createButton();
		avengerpresentationprofilespage.click_PresentationProfile(addeditpresentationprofilebeanpage);
		String url = homePage.getCurrentURL();
		logger.info("url.split -" + url.split("/")[8]);
		homePage.click_logout();
		return url.split("/")[8];
	}

	public String create_presentationProfileUI1() {
		launchURL(surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		homePage.clickSettingsLink();
		avengerdevicespage = homePage.clickAllDevicesLink();
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
		String url = homePage.getCurrentURL();
		logger.info("url.split -" + url.split("/")[8]);
		// browserQuit();
		homePage.click_logout();
		return url.split("/")[8];

	}
	
public void delete_assignedGroupsForUser(String username)  {
		
		launchURL(surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengeruserdashboardpage=avengerHomePage.clickUsersLink();
		avengeruserdashboardpage.clicknewuser_link(username);
		avengeruserdashboardpage.get_AllAssignedgroupsandDelete();
		avengerHomePage.click_logout();
	}
}
