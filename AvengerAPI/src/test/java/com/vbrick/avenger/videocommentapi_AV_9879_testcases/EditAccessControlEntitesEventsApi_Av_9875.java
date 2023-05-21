package com.vbrick.avenger.videocommentapi_AV_9879_testcases;
 

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;
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
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
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
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.CreateCategoryAPI;
import com.vbricks.avenger.serviceimpl.CreateEventsAPI;
import com.vbricks.avenger.serviceimpl.CustomFieldsAPI;
import com.vbricks.avenger.serviceimpl.DMEdeviceslistAPI;
import com.vbricks.avenger.serviceimpl.EditAccessControlEntitiesforeventAPI;
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
public class EditAccessControlEntitesEventsApi_Av_9875 extends TestBase{

	private static Logger logger = Logger.getLogger(EditAccessControlEntitesEventsApi_Av_9875.class);
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
	private CreateEventBean createEventBean;
	private AddUploadVideoBean adduploadvideobean;
	private EventAccessControlBean eventAccessControlBean;
	public ApiBeanFactory apibeanfactory;
    public AddVideoCommentBean addvideocommentbean;
	private HashMap<String, String> loginapiresponse;
	private HashMap<String, String> uploadvidoeapiresponse;
	private AssertionAPIResponse assertionapiresponse;
	private HashMap<String, String> createcategoryapirespone;
	private HashMap<String, String> childcategoryapirespone;
	private HashMap<String, String> eventDetailapirespone;
	private HashMap<String, String> loginapiresponse_mediaContributor;
	private HashMap<String, String> loginapiresponse_mediaviewer;
	private HashMap<String, String> eventEditAccessapirespone;
	private AvengerGroupsPage avengergroupspage; 
	private AvengerDevicesPage avengerdevicespage;
    private AvengerEventDetailsPage avengereventdetailspage;
	private AvengerPresentationProfilesPage avengerpresentationprofilespage;
	private AvengerAddPresentationProfilePage avengeraddpresentationprofilepage;
	private AddPresentationprofileBeanPage addpresentationprofilebeanpage;
	private AvengerAddNewDmeDevicePage avengeraddnewdmedevicepage;
	private AvengerAddCustomDevicePage avengeraddcustomdevicepage;
	private AddNewDmeBeanPage addnewdmedevicebeanpage;
	private AvengerDashboardPage avengerdashboardpage;
	private AddCustomDeviceBeanPage addcustomdevicebeanpage;
	private AvengerGroupsBeanPage avengergroupsbeanpage;
	private ApiUtils apiutils;
	
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
		eventAccessControlBean =new EventAccessControlBean();
		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		addvideocommentbean=new AddVideoCommentBean();
		assertionapiresponse =new AssertionAPIResponse();	
		apiutils=new ApiUtils();
		createEventBean = new CreateEventBean();
		addpresentationprofilebeanpage = new AddPresentationprofileBeanPage();
		addnewdmedevicebeanpage = new AddNewDmeBeanPage();
 		assertionapiresponse =new AssertionAPIResponse();
		addcustomdevicebeanpage = new AddCustomDeviceBeanPage();

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
		accountBeansFactory.MailinatorBean(mailinatorbeanpage);
		accountBeansFactory.AvengergroupsBean(avengergroupsbeanpage);
		accountBeansFactory.createPresentationProfile(addpresentationprofilebeanpage);
		accountBeansFactory.AddNewDmeBean(addnewdmedevicebeanpage);
		accountBeansFactory.addCustomDeviceBean(addcustomdevicebeanpage);
	
	}

	@Test(description="To Verify the EditAccessEvent using EditAccessEvent API with Account Admin",groups = {EDITACCESSCONTROL_EVENTSAPI})
	public void TC_01_GET_EditAccessControlEntitesEvents_api_check_With_AccountAdmin_Positive(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		HashMap<String,String>eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.EditAccessControlEntitiesEventsmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String userid[] = {eventAdminId.get(IAPIConstantCodes.APIResponseUSERID)};
		
		//username
		String userName[] = {eventAdminId.get(IAPIConstantCodes.APIUSERNAME)};
		
		//GroupIds UI
		String groupIdArray[]={create_groupfromUI()};				
		createEventBean.setDays(ICreateEventService.setDay_04);
		createEventBean.setHours(ICreateEventService.setMinutes_10);
		apibeanfactory.EventBean(createEventBean);		
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
			
	    //Groupid	
	     createEventBean.setGroupIds(groupIdArray);
				
		//EventAdminId
		HashMap<String,String> eventAdminId_loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId_loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId_loginapiresponse.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
				
		//UserIds
		HashMap<String,String>userIds_loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds_loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
	    String userIdArray[]={userIds_loginapiresponse.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setUserIds(userIdArray);
				
		//ModeratorIds
		HashMap<String,String>moderatorIds_loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(moderatorIds_loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String moderatorArray[]={moderatorIds_loginapiresponse.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setModeratorIds(moderatorArray);		
		createEventBean.setAccessControl(IAPIConstantCodes.PRIVATE);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createteamapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
		loginapiresponse.put(IAPIConstantCodes.APIEVENTID, createteamapirespone.get(IAPIConstantCodes.APIEVENTID));		
		apibeanfactory.EditAccessEventBean(eventAccessControlBean);
		eventAccessControlBean.setUserIds(userid);
		eventAccessControlBean.setUsernames(userName);
		eventAccessControlBean.setGroupIds(groupIdArray);
		EditAccessControlEntitiesforeventAPI editAccessControlEntitiesforeventAPI=new EditAccessControlEntitiesforeventAPI();
		eventEditAccessapirespone = editAccessControlEntitiesforeventAPI.EditAccessControl(loginapiresponse, eventAccessControlBean);
	    assertionapiresponse.verifyAssert_httpCode(eventEditAccessapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+eventEditAccessapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, eventEditAccessapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+eventEditAccessapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	
		logger.info("Selenium Code is excuting");
        launchURL(surl);
        customReport.reporter("Application launch with URL : ", surl);
        homePage = loginPage.loginAs(IUsersList.EditAccessControlEntitiesEventsmediaviewer, sPassword);
        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());
        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
        customReport.customizedReport(createEventBean.getTitle(),avengereventdetailspage.verify_label(createEventBean.getTitle()), statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);

 }
	
	@Test(description="To Verify the EditAccessEvent using EditAccessEvent API with Media Admin",groups = {EDITACCESSCONTROL_EVENTSAPI})
	public void TC_02_GET_EditAccessControlEntitesEvents_api_check_With_MediaAdmin_Positive(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		//userid
		HashMap<String,String>eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.EditAccessControlEntitiesEventsmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String userid[] = {eventAdminId.get(IAPIConstantCodes.APIResponseUSERID)};
				
		//username
		String userName[] = {eventAdminId.get(IAPIConstantCodes.APIUSERNAME)};
	
		//GroupIds UI
		String groupIdArray[]={create_groupfromUI()};
				
		createEventBean.setDays(ICreateEventService.setDay_04);
		createEventBean.setHours(ICreateEventService.setMinutes_20);
		apibeanfactory.EventBean(createEventBean);
		
		//PresentationProfileId UI
	    String presentationProfileId[]={create_presentationProfileUI()};
	    createEventBean.setPresentationProfileId(presentationProfileId[0]);
		
	    //Groupid	
	    createEventBean.setGroupIds(groupIdArray);
						
	   //EventAdminId
		HashMap<String,String> eventAdminId_loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
	    assertionapiresponse.verifyAssert_httpCode(eventAdminId_loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId_loginapiresponse.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
						
	    //UserIds
		HashMap<String,String>userIds_loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds_loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
	    String userIdArray[]={userIds_loginapiresponse.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setUserIds(userIdArray);
						
	   //ModeratorIds
		HashMap<String,String>moderatorIds_loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(moderatorIds_loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String moderatorArray[]={moderatorIds_loginapiresponse.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setModeratorIds(moderatorArray);		
		createEventBean.setAccessControl(IAPIConstantCodes.PRIVATE);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.EditAccessControlEntitiesEventsmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    logger.info("Create Event API response Code :::" + createeventapirespone);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));		
		apibeanfactory.EditAccessEventBean(eventAccessControlBean);
		eventAccessControlBean.setUserIds(userid);
		eventAccessControlBean.setUsernames(userName);
		eventAccessControlBean.setGroupIds(groupIdArray);
		EditAccessControlEntitiesforeventAPI editAccessControlEntitiesforeventAPI=new EditAccessControlEntitiesforeventAPI();
		eventEditAccessapirespone = editAccessControlEntitiesforeventAPI.EditAccessControl(loginapiresponse, eventAccessControlBean);
	    assertionapiresponse.verifyAssert_httpCode(eventEditAccessapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+eventEditAccessapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, eventEditAccessapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+eventEditAccessapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	
		logger.info("Selenium Code is excuting");
        launchURL(surl);
        customReport.reporter("Application launch with URL : ", surl);
        homePage = loginPage.loginAs(IUsersList.EditAccessControlEntitiesEventsmediaviewer, sPassword);
        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());
        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
        customReport.customizedReport(createEventBean.getTitle(),avengereventdetailspage.verify_label(createEventBean.getTitle()), statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
	}
	

	@Test(description="To Verify the EditAccessEvent using EditAccessEvent API with Event Admin",groups = {EDITACCESSCONTROL_EVENTSAPI})
	public void TC_03_GET_EditAccessControlEntitesEvents_api_check_With_EventAdmin_Positive(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();		
		//userid
		HashMap<String,String>eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.EditAccessControlEntitiesEventsmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String userid[] = {eventAdminId.get(IAPIConstantCodes.APIResponseUSERID)};
		
		//username
		String userName[] = {eventAdminId.get(IAPIConstantCodes.APIUSERNAME)};

		//GroupIds UI
		String groupIdArray[]={create_groupfromUI()};
		
		createEventBean.setDays(ICreateEventService.setDay_04);
		createEventBean.setHours(ICreateEventService.setMinutes_30);
		apibeanfactory.EventBean(createEventBean);
		
		//PresentationProfileId UI
	    String presentationProfileId[]={create_presentationProfileUI()};
	    createEventBean.setPresentationProfileId(presentationProfileId[0]);
			
	    //Groupid	
	    createEventBean.setGroupIds(groupIdArray);
						
	   //EventAdminId
		HashMap<String,String> eventAdminId_loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
	    assertionapiresponse.verifyAssert_httpCode(eventAdminId_loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId_loginapiresponse.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
						
	    //UserIds
		HashMap<String,String>userIds_loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds_loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
	    String userIdArray[]={userIds_loginapiresponse.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setUserIds(userIdArray);
						
	   //ModeratorIds
		HashMap<String,String>moderatorIds_loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(moderatorIds_loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String moderatorArray[]={moderatorIds_loginapiresponse.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setModeratorIds(moderatorArray);
		createEventBean.setAccessControl(IAPIConstantCodes.PRIVATE);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.EditAccessControlEntitiesEventsapieau), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    logger.info("Login API response Code :::" + createeventapirespone);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));		
		apibeanfactory.EditAccessEventBean(eventAccessControlBean);
		eventAccessControlBean.setUserIds(userid);
		eventAccessControlBean.setUsernames(userName);
		eventAccessControlBean.setGroupIds(groupIdArray);

		EditAccessControlEntitiesforeventAPI editAccessControlEntitiesforeventAPI=new EditAccessControlEntitiesforeventAPI();
		eventEditAccessapirespone = editAccessControlEntitiesforeventAPI.EditAccessControl(loginapiresponse, eventAccessControlBean);
	    assertionapiresponse.verifyAssert_httpCode(eventEditAccessapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+eventEditAccessapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, eventEditAccessapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+eventEditAccessapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	
		logger.info("Selenium Code is excuting");
        launchURL(surl);
        customReport.reporter("Application launch with URL : ", surl);
        homePage = loginPage.loginAs(IUsersList.EditAccessControlEntitiesEventsmediaviewer, sPassword);
        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle());
        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
        customReport.customizedReport(createEventBean.getTitle(),avengereventdetailspage.verify_label(createEventBean.getTitle()), statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
	}
	
	@Test(description="To Verify the EditAccessEvent using EditAccessEvent API with Media Contributor",groups = {EDITACCESSCONTROL_EVENTSAPI})
	public void TC_04_GET_EditAccessControlEntitesEvents_api_check_With_MediaContributor_Negative(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();		
		//userid
		 HashMap<String,String>eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.EditAccessControlEntitiesEventsmediacontributor), surl);
		 assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		 String userid[] = {eventAdminId.get(IAPIConstantCodes.APIResponseUSERID)};
				
		//username
		String userName[] = {eventAdminId.get(IAPIConstantCodes.APIUSERNAME)};

		//GroupIds UI
		String groupIdArray[]={create_groupfromUI()};		
			
		createEventBean.setDays(ICreateEventService.setDay_04);
		createEventBean.setHours(ICreateEventService.setMinutes_40);
		apibeanfactory.EventBean(createEventBean);
		
		//PresentationProfileId UI
	    String presentationProfileId[]={create_presentationProfileUI()};
	    createEventBean.setPresentationProfileId(presentationProfileId[0]);
		
	    //Groupid	
	    createEventBean.setGroupIds(groupIdArray);
						
	   //EventAdminId
		HashMap<String,String> eventAdminId_loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
	    assertionapiresponse.verifyAssert_httpCode(eventAdminId_loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId_loginapiresponse.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
						
	    //UserIds
		HashMap<String,String>userIds_loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds_loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
	    String userIdArray[]={userIds_loginapiresponse.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setUserIds(userIdArray);
						
	   //ModeratorIds
		HashMap<String,String>moderatorIds_loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(moderatorIds_loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String moderatorArray[]={moderatorIds_loginapiresponse.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setModeratorIds(moderatorArray);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    logger.info("Create API response Code :::" + createeventapirespone);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse_mediaContributor = userservices.doLogin(apiutils.userJson(IUsersList.EditAccessControlEntitiesEventsmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediaContributor.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_mediaContributor.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse_mediaContributor);		
				
		loginapiresponse_mediaContributor.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
		
		apibeanfactory.EditAccessEventBean(eventAccessControlBean);
		eventAccessControlBean.setUserIds(userid);
		eventAccessControlBean.setUsernames(userName);
		eventAccessControlBean.setGroupIds(groupIdArray);
		EditAccessControlEntitiesforeventAPI editAccessControlEntitiesforeventAPI=new EditAccessControlEntitiesforeventAPI();
		eventEditAccessapirespone = editAccessControlEntitiesforeventAPI.EditAccessControl(loginapiresponse_mediaContributor, eventAccessControlBean);
	    assertionapiresponse.verifyAssert_httpCode(eventEditAccessapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, eventEditAccessapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="To Verify the EditAccessEvent using EditAccessEvent API with Media Viewer",groups = {EDITACCESSCONTROL_EVENTSAPI})
	public void TC_05_GET_EditAccessControlEntitesEvents_api_check_With_MediaViewer_Negative(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();		
        
		//userid
		HashMap<String,String>eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.EditAccessControlEntitiesEventsmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String userid[] = {eventAdminId.get(IAPIConstantCodes.APIResponseUSERID)};
		
		//username
		String userName[] = {eventAdminId.get(IAPIConstantCodes.APIUSERNAME)};
		
		//GroupIds UI
		String groupIdArray[]={create_groupfromUI()};
			
		createEventBean.setDays(ICreateEventService.setDay_04);
		createEventBean.setHours(ICreateEventService.setMinutes_10);
		apibeanfactory.EventBean(createEventBean);	
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
	
	    //Groupid	
	     createEventBean.setGroupIds(groupIdArray);
				
		//EventAdminId
		HashMap<String,String> eventAdminId_loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId_loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId_loginapiresponse.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
				
		//UserIds
		HashMap<String,String>userIds_loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds_loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
	    String userIdArray[]={userIds_loginapiresponse.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setUserIds(userIdArray);
				
		//ModeratorIds
		HashMap<String,String>moderatorIds_loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(moderatorIds_loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String moderatorArray[]={moderatorIds_loginapiresponse.get(IAPIConstantCodes.APIResponseUSERID)};
		createEventBean.setModeratorIds(moderatorArray);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse_mediaviewer = userservices.doLogin(apiutils.userJson(IUsersList.EditAccessControlEntitiesEventsmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediaviewer.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_mediaviewer.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse_mediaviewer);
				
		loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
		
		apibeanfactory.EditAccessEventBean(eventAccessControlBean);
		eventAccessControlBean.setUserIds(userid);
		eventAccessControlBean.setUsernames(userName);
		eventAccessControlBean.setGroupIds(groupIdArray);
		EditAccessControlEntitiesforeventAPI editAccessControlEntitiesforeventAPI=new EditAccessControlEntitiesforeventAPI();
		eventEditAccessapirespone = editAccessControlEntitiesforeventAPI.EditAccessControl(loginapiresponse_mediaviewer, eventAccessControlBean);
	    assertionapiresponse.verifyAssert_httpCode(eventEditAccessapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, eventEditAccessapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
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
	