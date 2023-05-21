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
import org.testng.annotations.AfterClass;
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
import com.vbrick.avenger.apibeans.CreateEmbeddedContentBean;
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
import com.vbrick.avenger.pageobjects.AvengerContentRestrictionPage;
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
import com.vbrick.avenger.pageobjects.AvengerSystemSettingsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;
import com.vbrick.avenger.videocommentapi_AV_9879_testcases.EventListApi_Av_11123;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.ICreateEmbeddedContentService;
import com.vbricks.avenger.service.ICreateEventService;
import com.vbricks.avenger.service.IUserAccountsService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.CreateEventsAPI;
import com.vbricks.avenger.serviceimpl.EmbeddedContentAPI;
import com.vbricks.avenger.serviceimpl.GetEventDetailsAPI;
import com.vbricks.avenger.serviceimpl.GetEventStatusAPI;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class PostEmbeddedContent_AV28828 extends TestBase {
	private static Logger logger = Logger.getLogger(PostEmbeddedContent_AV28828.class);
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
	private CreateEmbeddedContentBean createembeddedcontentbean;
	private CreateEmbeddedContentBean createembeddedcontentbean1;
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
		createembeddedcontentbean=new CreateEmbeddedContentBean();
		createembeddedcontentbean1=new CreateEmbeddedContentBean();
		avengergroupsbeanpage = new AvengerGroupsBeanPage();
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
		
	@Test(description="To Verify and create webcast and checking embedded content and while creating event embedded content should enable ( Create a new Embedded URL) ",groups = {PostEmbeddedContentWebcast})
	public void TC01_Createanew_EmbeddedURL_PostEmbeddedContentWebcast_withallvaliddata_Positve_AV28828(ITestContext context)
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
		   createEventBean.setEmbeddedContent("Yes");//we are calling embedded content 
		   createEventBean.setIsEnabled("true");
		  
		   //Create event
			CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		    JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1, createEventBean);
		   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		   customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
		   
		  apibeanfactory.EmbeddedContentbean(createembeddedcontentbean);
		  createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
		  createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
	 
		  EmbeddedContentAPI embeddedcontentAPI=new EmbeddedContentAPI();
		  JSONObject embeddedcontentAPIresponse=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean);
		  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
		 //Assertion with name
		  assertionapiresponse.verifyAssert_httpCode(createembeddedcontentbean.getName(),embeddedcontentAPIresponse.get("name").toString() );  
		  customReport.customizedReport(createembeddedcontentbean.getName(),embeddedcontentAPIresponse.get("name").toString() ,statusValue, driver, sTestcaseName);
		 
		
		  
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
	        customReport.customizedReport(createembeddedcontentbean.getName(), avengereventspage.verify_text(createembeddedcontentbean.getName()),statusValue, driver, sTestcaseName);
			customReport.checkinglist(statusValue);
	  	  
	}

	
	@Test(description="To Verify and create webcast and checking embedded content and verifying it with valid code( Create a new Embedded URL)",groups = {PostEmbeddedContentWebcast})
	public void TC02_Createanew_EmbeddedURL_PostEmbeddedContentWebcast_verifywithvalid_Code_Positve_AV28828(ITestContext context)
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
		   createEventBean.setEmbeddedContent("Yes");//we are calling embedded content 
		   createEventBean.setIsEnabled("true");
		  
		   //Create event
			CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
		   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		   customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
		   
		  apibeanfactory.EmbeddedContentbean(createembeddedcontentbean);
		  createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
		  createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
		 		 
		  EmbeddedContentAPI embeddedcontentAPI=new EmbeddedContentAPI();
		  JSONObject embeddedcontentAPIresponse=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean);
		  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
		 //Assertion with valid code
		  assertionapiresponse.verifyAssert_httpCode(createembeddedcontentbean.getCode(),embeddedcontentAPIresponse.get("code").toString() );  
		  customReport.customizedReport(createembeddedcontentbean.getCode(),embeddedcontentAPIresponse.get("code").toString() ,statusValue, driver, sTestcaseName);
		 
		
		  
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
	        customReport.customizedReport(createembeddedcontentbean.getName(), avengereventspage.verify_text(createembeddedcontentbean.getName()),statusValue, driver, sTestcaseName);
			customReport.checkinglist(statusValue);
	       
	}
	
	@Test(description="To Verify and create webcast and checking embedded content and verifying it with name as empty( Create a new Embedded URL)",groups = {PostEmbeddedContentWebcast})
	public void TC03_Createanew_SharedURL_PostEmbeddedContentWebcast_withnameasempty_Negative_AV28828(ITestContext context)
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
		   createEventBean.setEmbeddedContent("Yes");//we are calling embedded content 
		   createEventBean.setIsEnabled("true");
		  
		   //Create event
			CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
		   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		   customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
		   
		  createembeddedcontentbean.setName("");
		  createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
		  createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
		 
		  EmbeddedContentAPI embeddedcontentAPI=new EmbeddedContentAPI();
		  JSONObject embeddedcontentAPIresponse=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean);
		  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatus500);  
		  customReport.customizedReport(HttpStatusCode.httpsStatus500,embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
		  
       
	}	
	
	@Test(description="To Verify and create webcast and checking embedded content and verifying it with IsEnabled( Create a new Embedded URL)",groups = {PostEmbeddedContentWebcast})
	public void TC04_Createanew_SharedURL_PostEmbeddedContentWebcast_verifywith_Isenabled_Positive_AV28828(ITestContext context)
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
		  createEventBean.setPresentationProfileId( presentationProfileId[0]);
		  
		//EventAdminId String
		   String  eventadminIdArray[]= {loginapiresponse1.get("id").toString()};
		   createEventBean.setEventAdminId(eventadminIdArray);
		   createEventBean.setEmbeddedContent("Yes");//we are calling embedded content 
		   createEventBean.setIsEnabled("true");
		  
		   //Create event
			CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
		   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		   customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
		   
		  apibeanfactory.EmbeddedContentbean(createembeddedcontentbean);
		  createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
		  createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
		 
		  EmbeddedContentAPI embeddedcontentAPI=new EmbeddedContentAPI();
		  JSONObject embeddedcontentAPIresponse=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean);
		  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
		 //Assertion with Isenabed
		  assertionapiresponse.verifyAssert_httpCode("false",embeddedcontentAPIresponse.get("isEnabled").toString());  
		  customReport.customizedReport("false",embeddedcontentAPIresponse.get("isEnabled").toString(),statusValue, driver, sTestcaseName);
		 
		
		  
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
	        customReport.customizedReport(createembeddedcontentbean.getName(), avengereventspage.verify_text(createembeddedcontentbean.getName()),statusValue, driver, sTestcaseName);
			customReport.checkinglist(statusValue);
	}
	
	@Test(description="To Verify and create webcast and checking embedded content and SendMultipleEmbeddedcontentfile(Create a new Embedded URL) ",groups = {PostEmbeddedContentWebcast})
	public void TC05_Createanew_SharedURL_PostEmbeddedContentWebcast__urlPushEnabled_SendMultipleEmbeddedcontentfile_Positive_AV28828(ITestContext context)
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
		   createEventBean.setEmbeddedContent("Yes");//we are calling embedded content 
		   createEventBean.setIsEnabled("true");
		  
		   //Create event
			CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
		   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		   customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
		   
		  apibeanfactory.EmbeddedContentbean(createembeddedcontentbean);
		  createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
		  createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
		  
		  EmbeddedContentAPI embeddedcontentAPI=new EmbeddedContentAPI();
		  JSONObject embeddedcontentAPIresponse=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean);
		  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
		  
		  
		  apibeanfactory.EmbeddedContentbean(createembeddedcontentbean1);
		  createembeddedcontentbean1.setCode(ICreateEmbeddedContentService.code);
		  createembeddedcontentbean1.setIcon(ICreateEmbeddedContentService.icon[1]);
		 
		  JSONObject embeddedcontentAPIresponse1=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean1);
		  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse1.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,embeddedcontentAPIresponse1.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
		  		 
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
	      //assertion with name
	        customReport.customizedReport(createembeddedcontentbean.getName(), avengereventspage.verify_text(createembeddedcontentbean.getName()),statusValue, driver, sTestcaseName);
	        //verifying it with name for 2 file
			//assertion with name
	        customReport.customizedReport(createembeddedcontentbean1.getName(), avengereventspage.verify_text(createembeddedcontentbean1.getName()),statusValue, driver, sTestcaseName);
			customReport.checkinglist(statusValue);
	}
	 
	@Test(description="To Verify and create webcast and checking embedded content and Withmorethan5MulltipleEmbeddedcontentfile(Create a new Embedded URL)",groups = {PostEmbeddedContentWebcast})
	public void TC06_Createanew_SharedURL_PostEmbeddedContentWebcast__EmbeddedContentEnabled_Withmorethan5Mulltiplefile_Negative_AV28828(ITestContext context)
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
			   createEventBean.setEmbeddedContent("Yes");//we are calling embedded content 
			   createEventBean.setIsEnabled("true");
			  
		  
		   //Create event
			CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
		   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		   customReport.customizedReport(HttpStatusCode.httpsStatusCode200,createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
		   EmbeddedContentAPI embeddedcontentAPI=new EmbeddedContentAPI();
		   int i;
		   for(i=1;i<=5;i++)
			{
			 apibeanfactory.EmbeddedContentbean(createembeddedcontentbean);
			 createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
			 createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
				 
	     JSONObject embeddedcontentAPIresponse=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean);
		 assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
		 customReport.customizedReport(HttpStatusCode.httpsStatusCode200,embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
			}
		   
		     apibeanfactory.EmbeddedContentbean(createembeddedcontentbean);
			 createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
			 createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
		 ArrayList<String> embeddedcontentAPIresponse1=embeddedcontentAPI.PostEmbeddedContentAPIdisable(loginapiresponse1, createembeddedcontentbean); 
		 assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse1.get(0).toString(), HttpStatusCode.httpsStatus400);  
		 customReport.customizedReport(HttpStatusCode.httpsStatus400,embeddedcontentAPIresponse1.get(0).toString(),statusValue, driver, sTestcaseName);
			
		
		   //selenium code
			  launchURL(surl);
		        customReport.reporter("Application launch with URL : ", surl);
		        homePage = loginPage.loginAs(sUserName, sPassword);
		        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
		        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
		        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
		        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
		        //it is scrolling down just to verify the embedded contents 
		        avengereventspage.scroll_embeddedcontent();	
		        customReport.checkinglist(statusValue);
	}
	
	
	@Test(description="To Verify and create webcast and checking embedded content and SendDuplicateNAme (Create a new Embedded URL)",groups = {PostEmbeddedContentWebcast})
	public void TC07_Createanew_PostEmbeddedContentWebcast__EmbeddedContentEnabled_SendDuplicateNAme_Negative_AV28828(ITestContext context)
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
		   createEventBean.setEmbeddedContent("Yes");//we are calling embedded content 
		   createEventBean.setIsEnabled("true");
		   
		   //Create event
			CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
		   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		   customReport.customizedReport(HttpStatusCode.httpsStatusCode200,createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
		  
		 for(int i=1;i<=2;i++)
		  {
		  
		  createembeddedcontentbean.setName("EmbeddedcontentSame");
		  createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
		  createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
		  
		  EmbeddedContentAPI embeddedcontentAPI=new EmbeddedContentAPI();
		   JSONObject embeddedcontentAPIresponse=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean);
		  if(i==2)
		  {
		   assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatus500);  
		  customReport.customizedReport(HttpStatusCode.httpsStatus500, embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
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
	        customReport.customizedReport("EmbeddedcontentSame", avengereventspage.verify_text(createembeddedcontentbean.getName()),statusValue, driver, sTestcaseName);
	        customReport.checkinglist(statusValue);
	}
	
	@Test(description="Login through Api and create shared url and Embedded content isEnabledthroughUI",groups = {PostEmbeddedContentWebcast})
	public void TC08_Createanew_SharedURL_PostEmbeddedContentWebcast_isEnabledthroughUI_Positive_AV14992(ITestContext context)
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
		   createEventBean.setEmbeddedContent("Yes");//we are calling embedded content 
		   createEventBean.setIsEnabled("true"); 
		   
		   
		   //Create event
			CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
		   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		   customReport.customizedReport(HttpStatusCode.httpsStatusCode200,createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
		   
		  apibeanfactory.EmbeddedContentbean(createembeddedcontentbean);
		  createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
		  createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
			 
		  EmbeddedContentAPI embeddedcontentAPI=new EmbeddedContentAPI();
		  JSONObject embeddedcontentAPIresponse=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean);
		  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
		
		   
		  
		  //selenium 
		    launchURL(surl);
	        customReport.reporter("Application launch with URL : ", surl);  
	        homePage = loginPage.loginAs(sUserName, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
	        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	      //assertion with name
	        customReport.customizedReport(createembeddedcontentbean.getName(), avengereventspage.verify_text(createembeddedcontentbean.getName()),statusValue, driver, sTestcaseName);
	        homePage.click_logout();
	        //selenium code for functionality of enable and disable check box of embedded content
			Disable_embeddedcontentUI();
			Enable_embeddedcontentUI();
	        customReport.checkinglist(statusValue);
	}	 
	
  @Test(description="Login as event admin and create Embedded content  and check with the user who is not event admin/host(Create a new Embedded URL)",groups = {PostEmbeddedContentWebcast})
	public void TC09_Createanew_PostEmbeddedContentWebcast_withallvaliddata_userwhoisnoteventadminorhost_Negative_AV28828(ITestContext context)
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
		 
		 
		   //login as media viewer
		   JSONObject loginapiresponse = userservices.doLoginUpdate(apiutils.userJson(IUsersList.GetCategoryListmediaviewer), surl);
			System.out.println("response code is"+loginapiresponse.get("Statuscode"));
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
			 logger.info("Login API response Code :::"+loginapiresponse);
			 customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
			 loginapiresponse.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
				   
			 apibeanfactory.EmbeddedContentbean(createembeddedcontentbean);
			 createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
			 createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
				
			EmbeddedContentAPI embeddedcontentAPI=new EmbeddedContentAPI();
		    JSONObject embeddedcontentAPIresponse=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse,createembeddedcontentbean);
		    assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatus401);  
			customReport.customizedReport(HttpStatusCode.httpsStatus401,embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
		  
	} 
	
	@Test(description="Login as event admin and create Embedded content  and check with icon(Create a new Shared URL)",groups = {PostEmbeddedContentWebcast})
	public void TC10_Createanew_PostEmbeddedContentWebcast_withallvaliddata_withIcon_Positive_AV28828(ITestContext context) throws InterruptedException
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
		   createEventBean.setEmbeddedContent("Yes");//we are calling embedded content 
		   createEventBean.setIsEnabled("true");
		  
		   //Create event
			CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
		   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		   customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));		   
		 
		   apibeanfactory.EmbeddedContentbean(createembeddedcontentbean);
		  createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
		  createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[3]);
		 
		  EmbeddedContentAPI embeddedcontentAPI=new EmbeddedContentAPI();
		  JSONObject embeddedcontentAPIresponse=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean);
		  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
		 //Assertion with icon
		  assertionapiresponse.verifyAssert_httpCode(createembeddedcontentbean.getIcon(),embeddedcontentAPIresponse.get("icon").toString() );  
		  customReport.customizedReport(createembeddedcontentbean.getIcon(),embeddedcontentAPIresponse.get("icon").toString() ,statusValue, driver, sTestcaseName);
		 
		
		  
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
	        customReport.customizedReport(createembeddedcontentbean.getName(), avengereventspage.verify_text(createembeddedcontentbean.getName()),statusValue, driver, sTestcaseName);
			customReport.checkinglist(statusValue);
	}
	@Test(description="To Verify and create webcast and checking embedded content and verifying it with invalid code( Create a new Embedded URL)",groups = {PostEmbeddedContentWebcast})
	public void TC11_Createanew_EmbeddedURL_PostEmbeddedContentWebcast_verifywith_INvalid_Code_Negative_AV28828(ITestContext context)
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
		   createEventBean.setEmbeddedContent("Yes");//we are calling embedded content 
		   createEventBean.setIsEnabled("true");
		  
		   //Create event
			CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
		   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		   customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));  
		 
		   apibeanfactory.EmbeddedContentbean(createembeddedcontentbean);
		  createembeddedcontentbean.setCode(IAPIConstantCodes.GOOGLEADDRESS);
		  createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
		 
		  EmbeddedContentAPI embeddedcontentAPI=new EmbeddedContentAPI();
		  JSONObject embeddedcontentAPIresponse=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean);
		  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatus500);  
		  customReport.customizedReport(HttpStatusCode.httpsStatus500,embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);  
		  customReport.checkinglist(statusValue);
	}
	
	@Test(description="To Verify and create webcast and checking embedded content and verifying it with invalid Icon( Create a new Embedded URL)",groups = {PostEmbeddedContentWebcast})
	public void TC12_Createanew_EmbeddedURL_PostEmbeddedContentWebcast_verifywith_INvalid_Icon_Negative_AV28828(ITestContext context)
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
		   createEventBean.setEmbeddedContent("Yes");//we are calling embedded content 
		   createEventBean.setIsEnabled("true");
		  
		   //Create event
			CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
		   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		   customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName); 
		   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
		  
		   apibeanfactory.EmbeddedContentbean(createembeddedcontentbean);
		  createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
		  createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[0]);
		  
		  EmbeddedContentAPI embeddedcontentAPI=new EmbeddedContentAPI();
		  JSONObject embeddedcontentAPIresponse=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean);
		  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatus500);  
		  customReport.customizedReport(HttpStatusCode.httpsStatus500,embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);  
		  customReport.checkinglist(statusValue);
	}
	
	@Test(description="Login with admin account and while creating a webcast set Embedded Content as Disabled",groups = {PostEmbeddedContentWebcast})
	public void TC13_Create_AnEvent_set_EmbeddedContentDisabled_Negative_AV28828(ITestContext context)
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
		   createEventBean.setEmbeddedContent("Yes");//we are calling embedded content 
		   createEventBean.setIsEnabled("false");
		  
		   
		   //Create event
			CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
		   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		   customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		   String eventid=createeventapirespone.get("eventId").toString();
		   
		  apibeanfactory.EmbeddedContentbean(createembeddedcontentbean);
		  createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
		  createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[3]);
		  
		  loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
		 
		  EmbeddedContentAPI embeddedcontentAPI=new EmbeddedContentAPI();
		  ArrayList<String> embeddedcontentAPIresponse=embeddedcontentAPI.PostEmbeddedContentAPIdisable(loginapiresponse1,createembeddedcontentbean);
		  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(0).toString(), HttpStatusCode.httpsStatus401);  
		  customReport.customizedReport(HttpStatusCode.httpsStatus401,embeddedcontentAPIresponse.get(0).toString() ,statusValue, driver, sTestcaseName);

		  
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
	        customReport.customizedReport(createEventBean.getIsEnabled(),avengereventspage.embeddedcontentenabledordisabledUI(),statusValue, driver, sTestcaseName);
			customReport.checkinglist(statusValue); 
	}
	
	
	@Test(description="To Verify and create webcast and create a new webcast and before starting webcast send post push content url and after starting webcast End webcast and then send embedded content url link ( Create a new Embedded URL) ",groups = {PostEmbeddedContentWebcast})
	public void TC14_Createanew_EmbeddedURL_PostEmbeddedContentWebcast_beforeStartingWebcast_andsendembeddedcontenturl_endEvent_andagiansendembeddedcontenturl_withallvaliddata_Positve_AV29749(ITestContext context)
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
		   createEventBean.setEmbeddedContent("Yes");//we are calling embedded content 
		   createEventBean.setIsEnabled("true");
		  
		   //Create event
			CreateEventsAPI createEventsAPI=new CreateEventsAPI();
		    JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1, createEventBean);
		   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
		   customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
		   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
		   
		  apibeanfactory.EmbeddedContentbean(createembeddedcontentbean);
		  createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
		  createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
	 
		  EmbeddedContentAPI embeddedcontentAPI=new EmbeddedContentAPI();
		  JSONObject embeddedcontentAPIresponse=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean);
		  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
		 //Assertion with name
		  assertionapiresponse.verifyAssert_httpCode(createembeddedcontentbean.getName(),embeddedcontentAPIresponse.get("name").toString() );  
		  customReport.customizedReport(createembeddedcontentbean.getName(),embeddedcontentAPIresponse.get("name").toString() ,statusValue, driver, sTestcaseName);
		 
		//start webcast  using api  
		GetEventStatusAPI starteventAPI=new GetEventStatusAPI();		
		JSONObject  starteventapiresponse=starteventAPI.starteventjsonobject(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
			  
	  //broadcast Event
		 starteventAPI.broadcasteventjsonobject(loginapiresponse1);
			 
	 //end Event
	   starteventAPI.endevent(loginapiresponse1);
			 
	 //Embedded content Url link
	   apibeanfactory.EmbeddedContentbean(createembeddedcontentbean);
	   createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
	   createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
	  
	  JSONObject embeddedcontentAPIresponse1=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean);
	  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse1.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
	  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,embeddedcontentAPIresponse1.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);		 
		  
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
	        customReport.customizedReport(createembeddedcontentbean.getName(), avengereventspage.verify_text(createembeddedcontentbean.getName()),statusValue, driver, sTestcaseName);
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
//this is enable feature from contentrestrictionpage
 
public void Enable_embeddedcontentUI() {
	  launchURL(surl);
      customReport.reporter("Application launch with URL : ", surl);
      homePage = loginPage.loginAs(sUserName, sPassword);
      homePage.clickSettingsLink();
      AvengerSystemSettingsPage avengersystemsettingpage=homePage.clickSystemSettingsLink();
      AvengerContentRestrictionPage avengercontentrestrictionpage=avengersystemsettingpage.click_ContentRestrictionLinkLocator();;
      avengercontentrestrictionpage.checkAllowEmbeddedContent();
      avengercontentrestrictionpage.click_save();
      homePage.click_logout();
}


//this is disable feature from contentrestrictionpage
public void Disable_embeddedcontentUI() {
	  launchURL(surl);
    customReport.reporter("Application launch with URL : ", surl);
    homePage = loginPage.loginAs(sUserName, sPassword);
    homePage.clickSettingsLink();
    AvengerSystemSettingsPage avengersystemsettingpage=homePage.clickSystemSettingsLink();
    AvengerContentRestrictionPage avengercontentrestrictionpage=avengersystemsettingpage.click_ContentRestrictionLinkLocator();;
    avengercontentrestrictionpage.uncheckAllowEmbeddedContent();
    avengercontentrestrictionpage.click_save();
    homePage.click_logout();
}
	




}
