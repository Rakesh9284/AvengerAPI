package com.vbrick.avenger.videocommentapi_AV_9879_testcases;

import java.net.MalformedURLException;
import java.util.ArrayList;
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
import com.vbrick.avenger.apibeans.AddGroupBean;
import com.vbrick.avenger.apibeans.AddTeamBean;
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbrick.avenger.apibeans.AddVideoCommentBean;
import com.vbrick.avenger.apibeans.CreateEventBean;
import com.vbrick.avenger.apibeans.VideoAccessControlBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AddCustomDeviceBeanPage;
import com.vbrick.avenger.dao.AddNewDmeBeanPage;
import com.vbrick.avenger.dao.AddPresentationprofileBeanPage;
import com.vbrick.avenger.dao.AddUserBeanPage;
import com.vbrick.avenger.dao.AvengerEventDetailsBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.LibraryBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerAddCustomDevicePage;
import com.vbrick.avenger.pageobjects.AvengerAddPresentationProfilePage;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerDevicesPage;
import com.vbrick.avenger.pageobjects.AvengerEditVideoSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerEventDetailsPage;
import com.vbrick.avenger.pageobjects.AvengerEventsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLibrariesPage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerPresentationProfilesPage;
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbrick.avenger.videocommentapi_AV_9879_testcases.SubmitVideosComments_AV_9879;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.ICreateEventService;
import com.vbricks.avenger.service.IDeleteVideoService;
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUserAccountsService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.service.IVideoAccessControlService;
import com.vbricks.avenger.serviceimpl.CreateEventsAPI;
import com.vbricks.avenger.serviceimpl.CreateGroupAPI;
import com.vbricks.avenger.serviceimpl.CreateTeamAPI;
import com.vbricks.avenger.serviceimpl.DeleteBackgroundImageAPI;
import com.vbricks.avenger.serviceimpl.DeleteVideosAPI;
import com.vbricks.avenger.serviceimpl.EditGroupAPI;
import com.vbricks.avenger.serviceimpl.UploadBackgroundImgForWebcastAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.serviceimpl.VideoAccessControlServiceAPI;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.ConstantsValue;
import com.vbricks.avenger.utils.HttpStatusCode;

public class DeleteBackgroundImage_AV18674 extends TestBase {

	private static Logger logger = Logger.getLogger(DeleteBackgroundImage_AV18674.class);
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
	private CreateEventBean createEventBean;
	private MailinatorBeanPage mailinatorbeanpage;
	private ReadAndWriteToJSON readgriduserdata;
	private AvengerEventDetailsPage avengereventdetailspage;
	private Map<String, String> userdata;
    private ApiUtils apiutils;
	private BasePage basePage;
	public ApiBeanFactory apibeanfactory;
	public AddVideoCommentBean addvideocommentbean;
 	private HashMap<String, String> loginapiresponse;
 	private HashMap<String, String> loginapiresponse1;
	private AddNewDmeBeanPage addnewdmedevicebeanpage;
	private AddPresentationprofileBeanPage addpresentationprofilebeanpage;
	private AvengerDevicesPage avengerdevicespage;
	private AddCustomDeviceBeanPage addcustomdevicebeanpage;
	private AvengerPresentationProfilesPage avengerpresentationprofilespage;
	private HashMap<String, String> uploadBackgroundImgForWebcastresponse;
	private AvengerAddPresentationProfilePage avengeraddpresentationprofilepage;
	private AvengerAddCustomDevicePage avengeraddcustomdevicepage;
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
		createEventBean = new CreateEventBean();
		addpresentationprofilebeanpage = new AddPresentationprofileBeanPage();
		addnewdmedevicebeanpage = new AddNewDmeBeanPage();
		apibeanfactory = new ApiBeanFactory();
		assertionapiresponse = new AssertionAPIResponse();
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
		accountBeansFactory.createPresentationProfile(addpresentationprofilebeanpage);
		accountBeansFactory.AddNewDmeBean(addnewdmedevicebeanpage);
		accountBeansFactory.addCustomDeviceBean(addcustomdevicebeanpage);

	}

	@Test(description = "Delete background image of a public webcast through account admin",groups = {DELETEBACKGROUNDIMAGEOFAWEBCAST})
	public void TC_01_Delete_backgroundimage_ofapublicwebcast_with_accoutAdmin_Positive(ITestContext context) {
		
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
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventbackgroundimgeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//create Event..
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    createeventapirespone.put("accesstoken", loginapiresponse.get("accesstoken"));
	    assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
        
	    UploadBackgroundImgForWebcastAPI uploadBackgroundImgtoWebcastAPI=new UploadBackgroundImgForWebcastAPI();	 
	    uploadBackgroundImgForWebcastresponse = uploadBackgroundImgtoWebcastAPI.uploadBackgroundImageForWebcast(createeventapirespone, Setup.VALIDIMAGEPATH_JPG2,ICreateEventService.BACKGROUNDIMAGE );
		logger.info("UploadVideo API response Code :::" + uploadBackgroundImgForWebcastresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadBackgroundImgForWebcastresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadBackgroundImgForWebcastresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadBackgroundImgForWebcastresponse);

		DeleteBackgroundImageAPI deletebackgroundimageAPI=new DeleteBackgroundImageAPI();
	    HashMap<String, String> deletebackgroundimageapiresponse = deletebackgroundimageAPI.deletebackgroundimage(createeventapirespone);
	    assertionapiresponse.verifyAssert_httpCode(deletebackgroundimageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deletebackgroundimageapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deletebackgroundimageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deletebackgroundimageapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
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
	    customReport.customizedReport(createEventBean.getTitle(),eventDetails.get(IUserAccountsService.TITLE), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(createeventapirespone.get(IUserAccountsService.EVENTID),avengereventdetailspage.getvideoID(), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,avengereventdetailspage.verify_defaultthumbnail().contains("default-thumbnail"),statusValue, driver, sTestcaseName);
	    customReport.customizedReport(false,avengereventdetailspage.verify_defaultthumbnail().contains(".jpg"),statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,avengereventdetailspage.verify_eventlisting("Public"),statusValue, driver, sTestcaseName);
	    customReport.customizedReport("Presentation",avengereventdetailspage.verify_videosourcetype(),statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);

	}
	
	@Test(description = "Delete background image of a private webcast through Event Admin",groups = {DELETEBACKGROUNDIMAGEOFAWEBCAST})
	public void TC_02_Delete_backgroundimage_ofaprivatewebcast_with_eventadmin_Positive(ITestContext context) {
		
		logger.info("API Level Code is executing");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();			
		createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_30);
		apibeanfactory.EventBean(createEventBean);
		
		//EventAdminId
		HashMap<String,String>eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventbackgroundimgeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		//PresentationProfileId UI
		String presentationProfileId[]={create_presentationProfileUI()};
		createEventBean.setPresentationProfileId(presentationProfileId[0]);
		
		//UserIds
		HashMap<String,String>userIds = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventbackgroundimgmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(userIds.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String userIdArray[]={userIds.get(IUserAccountsService.USERID)};
		createEventBean.setUserIds(userIdArray);
				
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventbackgroundimgeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);				
		createEventBean.setDescription("");
		createEventBean.setShortcut("");
		createEventBean.setAccessControl("Private");
		
		//create Event..
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    createeventapirespone.put("accesstoken", loginapiresponse.get("accesstoken"));
	    assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
        
	    UploadBackgroundImgForWebcastAPI uploadBackgroundImgtoWebcastAPI=new UploadBackgroundImgForWebcastAPI();		 
	    uploadBackgroundImgForWebcastresponse = uploadBackgroundImgtoWebcastAPI.uploadBackgroundImageForWebcast(createeventapirespone, Setup.VALIDIMAGEPATH_JPG3,ICreateEventService.BACKGROUNDIMAGE );
		logger.info("UploadVideo API response Code :::" + uploadBackgroundImgForWebcastresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadBackgroundImgForWebcastresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadBackgroundImgForWebcastresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadBackgroundImgForWebcastresponse);
		
		DeleteBackgroundImageAPI deletebackgroundimageAPI=new DeleteBackgroundImageAPI();
	    HashMap<String, String> deletebackgroundimageapiresponse = deletebackgroundimageAPI.deletebackgroundimage(createeventapirespone);
	    assertionapiresponse.verifyAssert_httpCode(deletebackgroundimageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deletebackgroundimageapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deletebackgroundimageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deletebackgroundimageapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
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
	    customReport.customizedReport(createEventBean.getTitle(),eventDetails.get(IUserAccountsService.TITLE), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(createeventapirespone.get(IUserAccountsService.EVENTID),avengereventdetailspage.getvideoID(), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,avengereventdetailspage.verify_defaultthumbnail().contains("default-thumbnail"),statusValue, driver, sTestcaseName);
	    customReport.customizedReport(false,avengereventdetailspage.verify_defaultthumbnail().contains(".jpg"),statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,avengereventdetailspage.verify_eventlisting("Private"),statusValue, driver, sTestcaseName);
	    customReport.customizedReport("Presentation",avengereventdetailspage.verify_videosourcetype(),statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);

	}
	
	@Test(description = "Delete background image of a All Users webcast through Media admin",groups = {DELETEBACKGROUNDIMAGEOFAWEBCAST})
	public void TC_03_Delete_backgroundimage_ofaAllUserswebcast_with_MediaAdmin_Positive(ITestContext context) {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
	 	UserServices userservices = new UserServices();	
		createEventBean.setDays(ICreateEventService.setDay_00);
		createEventBean.setHours(ICreateEventService.setMinutes_10);
		apibeanfactory.EventBean(createEventBean);
		
		//EventAdminId
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventbackgroundimgeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);		
		createEventBean.setPresentationProfileId("");
		createEventBean.setVideoAddress("rajitha.dronadala@vb.webex.com");
		createEventBean.setAccessControl("AllUsers");
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventbackgroundimgmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
	
		//create Event..
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    createeventapirespone.put("accesstoken", loginapiresponse.get("accesstoken"));
	    assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
        
	    UploadBackgroundImgForWebcastAPI uploadBackgroundImgtoWebcastAPI=new UploadBackgroundImgForWebcastAPI();		 
	    uploadBackgroundImgForWebcastresponse = uploadBackgroundImgtoWebcastAPI.uploadBackgroundImageForWebcast(createeventapirespone, Setup.VALIDIMAGEPATH_JPG2,ICreateEventService.BACKGROUNDIMAGE );
		logger.info("UploadVideo API response Code :::" + uploadBackgroundImgForWebcastresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadBackgroundImgForWebcastresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadBackgroundImgForWebcastresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadBackgroundImgForWebcastresponse);
		
		DeleteBackgroundImageAPI deletebackgroundimageAPI=new DeleteBackgroundImageAPI();
	    HashMap<String, String> deletebackgroundimageapiresponse = deletebackgroundimageAPI.deletebackgroundimage(createeventapirespone);
	    assertionapiresponse.verifyAssert_httpCode(deletebackgroundimageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deletebackgroundimageapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deletebackgroundimageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deletebackgroundimageapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
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
	    customReport.customizedReport(createEventBean.getTitle(),eventDetails.get(IUserAccountsService.TITLE), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(createeventapirespone.get(IUserAccountsService.EVENTID),avengereventdetailspage.getvideoID(), statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,avengereventdetailspage.verify_defaultthumbnail().contains("default-thumbnail"),statusValue, driver, sTestcaseName);
	    customReport.customizedReport(false,avengereventdetailspage.verify_defaultthumbnail().contains(".jpg"),statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,avengereventdetailspage.verify_eventlisting("All Users"),statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);

	}
	
	@Test(description = "Delete background image of a public webcast through Media Contributor",groups = {DELETEBACKGROUNDIMAGEOFAWEBCAST})
	public void TC_04_Delete_backgroundimage_ofapublicwebcast_with_mediaContributor_Positive(ITestContext context) {
		
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
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventbackgroundimgeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		//create Event..
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    createeventapirespone.put("accesstoken", loginapiresponse.get("accesstoken"));
	    assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
        
	    UploadBackgroundImgForWebcastAPI uploadBackgroundImgtoWebcastAPI=new UploadBackgroundImgForWebcastAPI();		 
	    uploadBackgroundImgForWebcastresponse = uploadBackgroundImgtoWebcastAPI.uploadBackgroundImageForWebcast(createeventapirespone, Setup.VALIDIMAGEPATH_JPG3,ICreateEventService.BACKGROUNDIMAGE );
		logger.info("UploadVideo API response Code :::" + uploadBackgroundImgForWebcastresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadBackgroundImgForWebcastresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadBackgroundImgForWebcastresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadBackgroundImgForWebcastresponse);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventbackgroundimgmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse1.put("eventId", createeventapirespone.get("eventId"));
		
		DeleteBackgroundImageAPI deletebackgroundimageAPI=new DeleteBackgroundImageAPI();
	    HashMap<String, String> deletebackgroundimageapiresponse = deletebackgroundimageAPI.deletebackgroundimage(loginapiresponse1);
	    assertionapiresponse.verifyAssert_httpCode(deletebackgroundimageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, deletebackgroundimageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);

	}
	
	@Test(description = "Delete background image of a public webcast through Event Host",groups = {DELETEBACKGROUNDIMAGEOFAWEBCAST})
	public void TC_05_Delete_backgroundimage_ofapublicwebcast_with_eventHost_Positive(ITestContext context) {
		
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
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventbackgroundimgeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		//create Event..
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    createeventapirespone.put("accesstoken", loginapiresponse.get("accesstoken"));
	    assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
        
	    UploadBackgroundImgForWebcastAPI uploadBackgroundImgtoWebcastAPI=new UploadBackgroundImgForWebcastAPI();
		 
	    uploadBackgroundImgForWebcastresponse = uploadBackgroundImgtoWebcastAPI.uploadBackgroundImageForWebcast(createeventapirespone, Setup.VALIDIMAGEPATH_JPG2,ICreateEventService.BACKGROUNDIMAGE );
		logger.info("UploadVideo API response Code :::" + uploadBackgroundImgForWebcastresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadBackgroundImgForWebcastresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadBackgroundImgForWebcastresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadBackgroundImgForWebcastresponse);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventbackgroundimgeventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse1.put("eventId", createeventapirespone.get("eventId"));
		
		DeleteBackgroundImageAPI deletebackgroundimageAPI=new DeleteBackgroundImageAPI();
	    HashMap<String, String> deletebackgroundimageapiresponse = deletebackgroundimageAPI.deletebackgroundimage(loginapiresponse1);
	    assertionapiresponse.verifyAssert_httpCode(deletebackgroundimageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, deletebackgroundimageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);

	}
	
	@Test(description = "Delete background image of a public webcast through Media Viewer",groups = {DELETEBACKGROUNDIMAGEOFAWEBCAST})
	public void TC_06_Delete_backgroundimage_ofapublicwebcast_with_mediaViewer_Positive(ITestContext context) {
		
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
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventbackgroundimgeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		//create Event..
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    createeventapirespone.put("accesstoken", loginapiresponse.get("accesstoken"));
	    assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
        
	    UploadBackgroundImgForWebcastAPI uploadBackgroundImgtoWebcastAPI=new UploadBackgroundImgForWebcastAPI();		 
	    uploadBackgroundImgForWebcastresponse = uploadBackgroundImgtoWebcastAPI.uploadBackgroundImageForWebcast(createeventapirespone, Setup.VALIDIMAGEPATH_JPG2,ICreateEventService.BACKGROUNDIMAGE );
		logger.info("UploadVideo API response Code :::" + uploadBackgroundImgForWebcastresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadBackgroundImgForWebcastresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadBackgroundImgForWebcastresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadBackgroundImgForWebcastresponse);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse1.put("eventId", createeventapirespone.get("eventId"));
		
		DeleteBackgroundImageAPI deletebackgroundimageAPI=new DeleteBackgroundImageAPI();
	    HashMap<String, String> deletebackgroundimageapiresponse = deletebackgroundimageAPI.deletebackgroundimage(loginapiresponse1);
	    assertionapiresponse.verifyAssert_httpCode(deletebackgroundimageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, deletebackgroundimageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);

	}
	
	@Test(description = "Delete background image of a public webcast through Event Host when event host is added as a event host",groups = {DELETEBACKGROUNDIMAGEOFAWEBCAST})
	public void TC_07_Delete_backgroundimage_ofapublicwebcast_with_eventHost_Positive(ITestContext context) {
		
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
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventbackgroundimgeventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
	
		//create Event..
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    createeventapirespone.put("accesstoken", loginapiresponse.get("accesstoken"));
	    assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);       
	    
	    UploadBackgroundImgForWebcastAPI uploadBackgroundImgtoWebcastAPI=new UploadBackgroundImgForWebcastAPI();		 
	    uploadBackgroundImgForWebcastresponse = uploadBackgroundImgtoWebcastAPI.uploadBackgroundImageForWebcast(createeventapirespone, Setup.VALIDIMAGEPATH_JPG2,ICreateEventService.BACKGROUNDIMAGE );
		logger.info("UploadVideo API response Code :::" + uploadBackgroundImgForWebcastresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadBackgroundImgForWebcastresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadBackgroundImgForWebcastresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadBackgroundImgForWebcastresponse);
		
		eventAdminId.put("eventId", createeventapirespone.get("eventId"));		
		DeleteBackgroundImageAPI deletebackgroundimageAPI=new DeleteBackgroundImageAPI();
		HashMap<String, String> deletebackgroundimageapiresponse = deletebackgroundimageAPI.deletebackgroundimage(eventAdminId);
		assertionapiresponse.verifyAssert_httpCode(deletebackgroundimageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deletebackgroundimageapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, deletebackgroundimageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deletebackgroundimageapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
			
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
		customReport.customizedReport(createEventBean.getTitle(),eventDetails.get(IUserAccountsService.TITLE), statusValue, driver, sTestcaseName);
		customReport.customizedReport(createeventapirespone.get(IUserAccountsService.EVENTID),avengereventdetailspage.getvideoID(), statusValue, driver, sTestcaseName);
		customReport.customizedReport(true,avengereventdetailspage.verify_defaultthumbnail().contains("default-thumbnail"),statusValue, driver, sTestcaseName);
		customReport.customizedReport(false,avengereventdetailspage.verify_defaultthumbnail().contains(".jpg"),statusValue, driver, sTestcaseName);
		customReport.customizedReport(true,avengereventdetailspage.verify_eventlisting("Public"),statusValue, driver, sTestcaseName);
		customReport.customizedReport("Presentation",avengereventdetailspage.verify_videosourcetype(),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);

	}
	
	@Test(description = "Delete background image of a public webcast through account admin by giving invalid event id",groups = {DELETEBACKGROUNDIMAGEOFAWEBCAST})
	public void TC_08_Delete_backgroundimage_ofapublicwebcast_with_accoutAdmin_bygivinginvalideventid_Negative(ITestContext context) {
		
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
		HashMap<String,String> eventAdminId = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventbackgroundimgeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(eventAdminId.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		String eventadminIdArray[]={eventAdminId.get(IUserAccountsService.USERID)};
		createEventBean.setEventAdminId(eventadminIdArray);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
	
		//create Event..
		CreateEventsAPI createEventsAPI=new CreateEventsAPI();
	    HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	    createeventapirespone.put("accesstoken", loginapiresponse.get("accesstoken"));
	    assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createeventapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createeventapirespone.get("statusInfo"),statusValue, driver, sTestcaseName);
        
	    UploadBackgroundImgForWebcastAPI uploadBackgroundImgtoWebcastAPI=new UploadBackgroundImgForWebcastAPI();		 
	    uploadBackgroundImgForWebcastresponse = uploadBackgroundImgtoWebcastAPI.uploadBackgroundImageForWebcast(createeventapirespone, Setup.VALIDIMAGEPATH_JPG2,ICreateEventService.BACKGROUNDIMAGE );
		logger.info("UploadVideo API response Code :::" + uploadBackgroundImgForWebcastresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadBackgroundImgForWebcastresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadBackgroundImgForWebcastresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadBackgroundImgForWebcastresponse);
		
		eventAdminId.put("eventId", createeventapirespone.get("eventId")+"invalid");
	
		DeleteBackgroundImageAPI deletebackgroundimageAPI=new DeleteBackgroundImageAPI();
		HashMap<String, String> deletebackgroundimageapiresponse = deletebackgroundimageAPI.deletebackgroundimage(eventAdminId);
		assertionapiresponse.verifyAssert_httpCode(deletebackgroundimageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus404);
		customReport.customizedReport(HttpStatusCode.httpsStatus404, deletebackgroundimageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@AfterMethod(alwaysRun=true)
	public void browserClose(ITestResult result)
	{
		logger.info("In After method class");
		 statusValue.clear();
		 if(!result.isSuccess()){
		Reporter.log("Screen shot for failed Test Case" +customReport.AssertionresultOutput(driver, sTestcaseName));
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
	 	homePage.click_logout();
		return url.split("/")[8];
	
	}
	
}
 