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
import com.vbricks.avenger.service.ICreateEmbeddedContentService;
import com.vbricks.avenger.service.ICreateEventService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.CreateEventsAPI;
import com.vbricks.avenger.serviceimpl.EmbeddedContentAPI;
import com.vbricks.avenger.serviceimpl.GetEventDetailsAPI;
//import com.vbricks.avenger.serviceimpl.PushContentAPI;
//import com.vbricks.avenger.serviceimpl.StartEventAPI;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class GetEmbeddedContent_AV28828 extends TestBase {

	private static Logger logger = Logger.getLogger(GetEmbeddedContent_AV28828.class);
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
		createembeddedcontentbean=new   CreateEmbeddedContentBean();
		createembeddedcontentbean1=new  CreateEmbeddedContentBean();
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
	
	@Test(description="To Verify and create a new webcast and it should be filled with all the valid json elements and  Get all json elements  in response body of Embedded content and get with name (Get Shared URLs API)",groups = {GETEmbeddedContentWebcast})
	public void TC01_Createanew_GetSharedURLsAPI_GETEmbeddedContentWebcast_WithNAme_Positive_AV28828(ITestContext context)
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
		  loginapiresponse1.put(IAPIConstantCodes.SharedURLID,embeddedcontentAPIresponse.get("id"));
		  
		 
		  JSONObject getembeddedcontentAPIresponse=embeddedcontentAPI.GET_EmbeddedContentAPI(loginapiresponse1);
		  assertionapiresponse.verifyAssert_httpCode(getembeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,getembeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
		  //Assertion with name
		  assertionapiresponse.verifyAssert_httpCode(createembeddedcontentbean.getName(),getembeddedcontentAPIresponse.get("name").toString() );  
		  customReport.customizedReport(getembeddedcontentAPIresponse.get("name").toString() ,createembeddedcontentbean.getName(),statusValue, driver, sTestcaseName);
		  
			//selenium code
		    launchURL(surl);
	        customReport.reporter("Application launch with URL : ", surl);
	        homePage = loginPage.loginAs(sUserName, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
	        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	        customReport.customizedReport(createembeddedcontentbean.getName(), avengereventspage.verify_text(createembeddedcontentbean.getName()),statusValue, driver, sTestcaseName);
			customReport.checkinglist(statusValue);
	
	}
	
		
	@Test(description="To Verify and create a new webcast and it should be filled with all the valid json elements and Get all json elements  in response body of Embedded content and get with code(Get Shared URLs API)",groups = {GETEmbeddedContentWebcast})
	public void TC02_Createanew_GetSharedURLsAPI_GETEmbeddedContentWebcast_WithCode_Positive_AV28828(ITestContext context)
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
		  loginapiresponse1.put(IAPIConstantCodes.SharedURLID,embeddedcontentAPIresponse.get("id"));
		  
		 
		  JSONObject getembeddedcontentAPIresponse=embeddedcontentAPI.GET_EmbeddedContentAPI(loginapiresponse1);
		  assertionapiresponse.verifyAssert_httpCode(getembeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,getembeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
		  //Assertion with code
		  assertionapiresponse.verifyAssert_httpCode(createembeddedcontentbean.getCode(),getembeddedcontentAPIresponse.get("code").toString() );  
		  customReport.customizedReport(getembeddedcontentAPIresponse.get("code").toString() ,createembeddedcontentbean.getCode(),statusValue, driver, sTestcaseName);
		  
		 
		//selenium code
		  launchURL(surl);
	        customReport.reporter("Application launch with URL : ", surl);
	        homePage = loginPage.loginAs(sUserName, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
	        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	        customReport.customizedReport(createembeddedcontentbean.getName(), avengereventspage.verify_text(createembeddedcontentbean.getName()),statusValue, driver, sTestcaseName);
			customReport.checkinglist(statusValue);
	}

	@Test(description="To Verify and create a new webcast and it should be filled with all the valid json elements and  Get all json elements  in response body of Embedded content and get with ICON(Get Shared URLs API)",groups = {GETEmbeddedContentWebcast})
	public void TC03_Createanew_GetSharedURLsAPI_GETEmbeddedContentWebcast_WithIcon_Positive_AV28828(ITestContext context)
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
		  loginapiresponse1.put(IAPIConstantCodes.SharedURLID,embeddedcontentAPIresponse.get("id"));
		  
		 
		  JSONObject getembeddedcontentAPIresponse=embeddedcontentAPI.GET_EmbeddedContentAPI(loginapiresponse1);
		  assertionapiresponse.verifyAssert_httpCode(getembeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,getembeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
		  //Assertion with icon
		  assertionapiresponse.verifyAssert_httpCode(createembeddedcontentbean.getIcon(),getembeddedcontentAPIresponse.get("icon").toString() );  
		  customReport.customizedReport(getembeddedcontentAPIresponse.get("icon").toString() ,createembeddedcontentbean.getIcon(),statusValue, driver, sTestcaseName);
		  
		 
		//selenium code
		  launchURL(surl);
	        customReport.reporter("Application launch with URL : ", surl);
	        homePage = loginPage.loginAs(sUserName, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
	        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	        customReport.customizedReport(createembeddedcontentbean.getName(), avengereventspage.verify_text(createembeddedcontentbean.getName()),statusValue, driver, sTestcaseName);
			customReport.checkinglist(statusValue);
	}

	@Test(description="To Verify and create a new webcast and it should be filled with all the valid json elements and  Get all json elements  in response body of Embedded content and get with IsEnabled(Get Shared URLs API)",groups = {GETEmbeddedContentWebcast})
	public void TC04_Createanew_GetSharedURLsAPI_GETEmbeddedContentWebcast_WithIsenabled_Positive_AV28828(ITestContext context)
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
		   createembeddedcontentbean.setIsEnabled("false");
		   
		  EmbeddedContentAPI embeddedcontentAPI=new EmbeddedContentAPI();
		  JSONObject embeddedcontentAPIresponse=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean);
		  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
		  loginapiresponse1.put(IAPIConstantCodes.SharedURLID,embeddedcontentAPIresponse.get("id"));
		  
		 
		  JSONObject getembeddedcontentAPIresponse=embeddedcontentAPI.GET_EmbeddedContentAPI(loginapiresponse1);
		  assertionapiresponse.verifyAssert_httpCode(getembeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,getembeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
		  //Assertion with isenabled
		  assertionapiresponse.verifyAssert_httpCode(createembeddedcontentbean.getIsEnabled(),getembeddedcontentAPIresponse.get("isEnabled").toString() );  
		  customReport.customizedReport(getembeddedcontentAPIresponse.get("isEnabled").toString() ,createembeddedcontentbean.getIsEnabled(),statusValue, driver, sTestcaseName);
		  
		 
		//selenium code
		  launchURL(surl);
	        customReport.reporter("Application launch with URL : ", surl);
	        homePage = loginPage.loginAs(sUserName, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
	        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	        customReport.customizedReport(createembeddedcontentbean.getName(), avengereventspage.verify_text(createembeddedcontentbean.getName()),statusValue, driver, sTestcaseName);
			customReport.checkinglist(statusValue);
	}
	
	@Test(description="To Verify and create a new webcast and it should be filled with all the valid json elements and Get all json elements in response body of Embedded content and get with ID(Get Shared URLs API)",groups = {GETEmbeddedContentWebcast})
	public void TC05_Createanew_GetSharedURLsAPI_GETEmbeddedContentWebcast_WithID_Positive_AV28828(ITestContext context)
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
		  loginapiresponse1.put(IAPIConstantCodes.SharedURLID,embeddedcontentAPIresponse.get("id"));
		  
		 
		  JSONObject getembeddedcontentAPIresponse=embeddedcontentAPI.GET_EmbeddedContentAPI(loginapiresponse1);
		  assertionapiresponse.verifyAssert_httpCode(getembeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,getembeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
		  //Assertion with id
		  assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.SharedURLID).toString(),getembeddedcontentAPIresponse.get("id").toString() );  
		  customReport.customizedReport(getembeddedcontentAPIresponse.get("id").toString() ,loginapiresponse1.get(IAPIConstantCodes.SharedURLID).toString(),statusValue, driver, sTestcaseName);
		  
		 
		//selenium code
		  launchURL(surl);
	        customReport.reporter("Application launch with URL : ", surl);
	        homePage = loginPage.loginAs(sUserName, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
	        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	        customReport.customizedReport(createembeddedcontentbean.getName(), avengereventspage.verify_text(createembeddedcontentbean.getName()),statusValue, driver, sTestcaseName);
			customReport.checkinglist(statusValue);
	}

	@Test(description="To Verify and create a new webcast and it should be filled with all the valid json elements and Get all json elements in WithInValid_eventID(Get Shared URLs API)",groups = {GETEmbeddedContentWebcast})
	public void TC06_Createanew_GetSharedURLsAPI_GETEmbeddedContentWebcast_WithInValid_eventID_Positive_AV28828(ITestContext context)
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
		  loginapiresponse1.put(IAPIConstantCodes.SharedURLID,embeddedcontentAPIresponse.get("id"));
		  
		  //invalid event id 
		  loginapiresponse1.put(IAPIConstantCodes.APIEVENTID,embeddedcontentAPIresponse.get("id"));
		 
		  JSONObject getembeddedcontentAPIresponse=embeddedcontentAPI.GET_EmbeddedContentAPI_invalid(loginapiresponse1);
		  assertionapiresponse.verifyAssert_httpCode(getembeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatus401);  
		  customReport.customizedReport(HttpStatusCode.httpsStatus401,getembeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
		  customReport.checkinglist(statusValue);
	}

	@Test(description="To Verify and create a new webcast and it should be filled with all the valid json elements and Get all json elements in With Mediaviewer(Get Shared URLs API)",groups = {GETEmbeddedContentWebcast})
	public void TC07_Createanew_GetSharedURLsAPI_GETEmbeddedContentWebcast_WithMediaViewer_Negative_AV28828(ITestContext context)
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
		  
		  JSONObject loginapiresponse = userservices.doLoginUpdate(apiutils.userJson(IUsersList.Get_Mediaviewer), surl);
			System.out.println("response code is"+loginapiresponse.get("Statuscode"));
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
			 logger.info("Login API response Code :::"+loginapiresponse);
			 customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
			 loginapiresponse.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
		 
		  JSONObject getembeddedcontentAPIresponse=embeddedcontentAPI.GET_EmbeddedContentAPI_invalid(loginapiresponse);
		  assertionapiresponse.verifyAssert_httpCode(getembeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatus401);  
		  customReport.customizedReport(HttpStatusCode.httpsStatus401,getembeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
			customReport.checkinglist(statusValue);
	
	}
	
	@Test(description="To Verify and create a new webcast and it should be filled with all the valid json elements and  Get all json elements  in response body of (MultipleembeddedContent) Embedded content and get with name (Get Shared URLs API)",groups = {GETEmbeddedContentWebcast})
	public void TC08_Createanew_GetSharedURLsAPI_GETEmbeddedContentWebcast_MultipleembeddedContent_VerifyWithNAme_Positive_AV28828(ITestContext context)
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
		 
			//bean1 
		   apibeanfactory.EmbeddedContentbean(createembeddedcontentbean);
		   createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
		   createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
		 
		  JSONObject embeddedcontentAPIresponse=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean);
		  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
			
		  //bean2
		   apibeanfactory.EmbeddedContentbean(createembeddedcontentbean1);
		   createembeddedcontentbean1.setCode(ICreateEmbeddedContentService.code);
		   createembeddedcontentbean1.setIcon(ICreateEmbeddedContentService.icon[1]);
		 
		  JSONObject embeddedcontentAPIresponse1=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean1);
		  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse1.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,embeddedcontentAPIresponse1.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
			
		  
		  JSONObject getembeddedcontentAPIresponse=embeddedcontentAPI.GETMultipleEmbeddedContentAPI(loginapiresponse1);
		  assertionapiresponse.verifyAssert_httpCode(getembeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,getembeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
		
		  //Assertion with name
		  assertionapiresponse.verifyAssert_httpCode(createembeddedcontentbean.getName(),getembeddedcontentAPIresponse.get("name").toString() );  
		  customReport.customizedReport(getembeddedcontentAPIresponse.get("name").toString() ,createembeddedcontentbean.getName(),statusValue, driver, sTestcaseName);
		 
		  //Assertion with name1
		  assertionapiresponse.verifyAssert_httpCode(createembeddedcontentbean1.getName(),getembeddedcontentAPIresponse.get("name1").toString() );  
		  customReport.customizedReport(getembeddedcontentAPIresponse.get("name1").toString() ,createembeddedcontentbean1.getName(),statusValue, driver, sTestcaseName);
		
		  
			//selenium code
		    launchURL(surl);
	        customReport.reporter("Application launch with URL : ", surl);
	        homePage = loginPage.loginAs(sUserName, sPassword);
	        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
	        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
	        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
	        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
	        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
	        customReport.customizedReport(createembeddedcontentbean.getName(), avengereventspage.verify_text(createembeddedcontentbean.getName()),statusValue, driver, sTestcaseName);
			customReport.customizedReport(createembeddedcontentbean1.getName(), avengereventspage.verify_text(createembeddedcontentbean1.getName()),statusValue, driver, sTestcaseName);
			customReport.checkinglist(statusValue);
	
	}
	
	@Test(description="Create a new webcast and send embedded content and GETWEBCAST Details",groups = {GETEmbeddedContentWebcast})
	public void TC09_Createanew_GETWebcast_embeddedContent__Positive_AV28828(ITestContext context)
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
		  loginapiresponse1.put(IAPIConstantCodes.SharedURLID,embeddedcontentAPIresponse.get("id"));
		  
		 
		  /*JSONObject getembeddedcontentAPIresponse=embeddedcontentAPI.GET_EmbeddedContentAPI(loginapiresponse1);
		  assertionapiresponse.verifyAssert_httpCode(getembeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,getembeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
		  //Assertion with name
		  assertionapiresponse.verifyAssert_httpCode(createembeddedcontentbean.getName(),getembeddedcontentAPIresponse.get("name").toString() );  
		  customReport.customizedReport(getembeddedcontentAPIresponse.get("name").toString() ,createembeddedcontentbean.getName(),statusValue, driver, sTestcaseName);
		   */
		  
		  GetEventDetailsAPI geteventdetailsapi=new GetEventDetailsAPI();
		  JSONObject geteventdetailsresponse=geteventdetailsapi.eventDetail_jsonbject(loginapiresponse1);
		  assertionapiresponse.verifyAssert_httpCode(geteventdetailsresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
		  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,geteventdetailsresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
		  assertionapiresponse.verifyAssert_httpCode(createembeddedcontentbean.getName(),geteventdetailsresponse.get("EmbeddedContentName").toString()); 
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
