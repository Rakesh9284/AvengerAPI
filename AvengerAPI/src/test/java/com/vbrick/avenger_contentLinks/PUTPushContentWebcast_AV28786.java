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
import org.json.simple.parser.ParseException;
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
import com.vbricks.avenger.service.ICreateEventService;
import com.vbricks.avenger.service.ICreatePushContentService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.CreateEventsAPI;
import com.vbricks.avenger.serviceimpl.GetEventStatusAPI;
import com.vbricks.avenger.serviceimpl.PushContentAPI;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class PUTPushContentWebcast_AV28786 extends TestBase{

	private static Logger logger = Logger.getLogger(PUTPushContentWebcast_AV28786.class);
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
	private CreatePushContentBean createpushcontentbean;
	private CreatePushContentBean createpushcontentbean1;
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
		createpushcontentbean=new CreatePushContentBean();
		createpushcontentbean1=new CreatePushContentBean();
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

	 @Test(description="To Verify and create a new webcast and  and use put api and in request body send isEnabled as True (Activate/Deactivate a Shared URL) ",groups ={PUTPushContentWebcast})
		public void TC01_ActivateDeactivate_SharedURL_PUTPushContentWebcast_IsEnabledTrue_Positive_AV28786(ITestContext context) 
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

			   //Create event
				CreateEventsAPI createEventsAPI=new CreateEventsAPI();
			   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
			   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
			   customReport.customizedReport(HttpStatusCode.httpsStatusCode200,createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
			   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
			   
			   //start webcast  using api  
			   GetEventStatusAPI starteventAPI=new GetEventStatusAPI();		
			   JSONObject  starteventapiresponse=starteventAPI.starteventjsonobject(loginapiresponse1);
			   assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
			   customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
			   
			  apibeanfactory.PushContentbean(createpushcontentbean);
			  createpushcontentbean.setLink(IAPIConstantCodes.GOOGLEADDRESS);
			  createpushcontentbean.setPushMethod(ICreatePushContentService.pushMethod[0]);
			  createpushcontentbean.setIsEnabled(IAPIConstantCodes.TRUE);
			 
			  PushContentAPI pushcontentAPI=new PushContentAPI();
			  ArrayList<String> pushcontentAPIresponse=pushcontentAPI.PushContentWebCastAPI(loginapiresponse1, createpushcontentbean);
			  assertionapiresponse.verifyAssert_httpCode(pushcontentAPIresponse.get(1).toString(), HttpStatusCode.httpsStatusCode200);  
			  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, pushcontentAPIresponse.get(1).toString(),statusValue, driver, sTestcaseName);
			  loginapiresponse1.put(IAPIConstantCodes.SharedURLID,pushcontentAPIresponse.get(0));
			
			  //put is enabled as true
			  createpushcontentbean.setIsEnabled(IAPIConstantCodes.TRUE);
			  
			  ArrayList<String> putpushcontentAPIresponsestatus=pushcontentAPI.Put_PushContentAPI_Status(loginapiresponse1,createpushcontentbean);
			  assertionapiresponse.verifyAssert_httpCode(putpushcontentAPIresponsestatus.get(0).toString(), HttpStatusCode.httpsStatusCode200);  
			  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, putpushcontentAPIresponsestatus.get(0).toString(),statusValue, driver, sTestcaseName);
			  customReport.customizedReport("true", createpushcontentbean.getIsEnabled().toString(),statusValue, driver, sTestcaseName);
			
			  //selenium code 
			    launchURL(surl);
		        customReport.reporter("Application launch with URL : ", surl);
		        homePage = loginPage.loginAs(sUserName, sPassword);
		        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
		        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
		        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
		        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
		        avengereventwebcastpage.verify_eventlabel(createEventBean.getTitle());
		        avengereventwebcastpage.click_sendalinkurltoallattendes();
		        assertionapiresponse.verifyAssert_httpCode(createpushcontentbean.getIsEnabled(),avengereventwebcastpage.verify_pushlabel().get(0).toString());
		        customReport.customizedReport(avengereventwebcastpage.verify_pushlabel().get(0).toString(),createpushcontentbean.getIsEnabled(),statusValue, driver, sTestcaseName);         
		        customReport.checkinglist(statusValue);
		}

		@Test(description="To Verify and create a new webcast and  and use put api and in request body send isEnabled as false (Activate/Deactivate a Shared URL) ",groups = {PUTPushContentWebcast})
		public void TC02_ActivateDeactivate_SharedURL_PUTPushContentWebcast_ISEnableFalse_Positve_AV28786(ITestContext context) throws ParseException
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

			   //Create event
				CreateEventsAPI createEventsAPI=new CreateEventsAPI();
			   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
			   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
			   customReport.customizedReport(HttpStatusCode.httpsStatusCode200,createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
			   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
			   
			   //start webcast  using api  
			   GetEventStatusAPI starteventAPI=new GetEventStatusAPI();		
			   JSONObject  starteventapiresponse=starteventAPI.starteventjsonobject(loginapiresponse1);
			   assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
			   customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
			    
			  apibeanfactory.PushContentbean(createpushcontentbean);
			  createpushcontentbean.setLink(IAPIConstantCodes.GOOGLEADDRESS);
			  createpushcontentbean.setPushMethod(ICreatePushContentService.pushMethod[0]);
			  createpushcontentbean.setIsEnabled(IAPIConstantCodes.TRUE);
			 
			  PushContentAPI pushcontentAPI=new PushContentAPI();
			  ArrayList<String> pushcontentAPIresponse=pushcontentAPI.PushContentWebCastAPI(loginapiresponse1,createpushcontentbean);
			  assertionapiresponse.verifyAssert_httpCode(pushcontentAPIresponse.get(1).toString(), HttpStatusCode.httpsStatusCode200);  
			  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, pushcontentAPIresponse.get(1).toString(),statusValue, driver, sTestcaseName);
			 loginapiresponse1.put(IAPIConstantCodes.SharedURLID,pushcontentAPIresponse.get(0));
			
			 //put is enabled as false
			  createpushcontentbean.setIsEnabled(IAPIConstantCodes.FALSE);
			  
			  ArrayList<String> putpushcontentAPIresponsestatus=pushcontentAPI.Put_PushContentAPI_Status(loginapiresponse1,createpushcontentbean);
			  assertionapiresponse.verifyAssert_httpCode(putpushcontentAPIresponsestatus.get(0).toString(), HttpStatusCode.httpsStatusCode200);  
			  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, putpushcontentAPIresponsestatus.get(0).toString(),statusValue, driver, sTestcaseName);
			  customReport.customizedReport("false", createpushcontentbean.getIsEnabled().toString(),statusValue, driver, sTestcaseName);
			  
			 //selenium code 
			    launchURL(surl);
		        customReport.reporter("Application launch with URL : ", surl);
		        homePage = loginPage.loginAs(sUserName, sPassword);
		        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
		        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
		        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
		        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
		        avengereventwebcastpage.verify_eventlabel(createEventBean.getTitle());
		        avengereventwebcastpage.click_sendalinkurltoallattendes();
		        assertionapiresponse.verifyAssert_httpCode(createpushcontentbean.getIsEnabled(),avengereventwebcastpage.verify_pushlabel().get(0).toString());
		        customReport.customizedReport(avengereventwebcastpage.verify_pushlabel().get(0).toString(),createpushcontentbean.getIsEnabled(),statusValue, driver, sTestcaseName);         
		        customReport.checkinglist(statusValue); 
		}
		
		@Test(description="To Verify and create a new webcast and  and use put api and in request body send isEnabled as false as EventAdmin (Activate/Deactivate a Shared URL) ",groups = {PUTPushContentWebcast})
		public void TC03_ActivateDeactivate_SharedURL_PUTPushContentWebcast_ISEnableFalse_AsEventAdmin_Positve_AV14992(ITestContext context) throws ParseException
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
			  
			  //login  as event admin 
			JSONObject loginapiresponse = userservices.doLoginUpdate(apiutils.userJson(IUsersList.GetEventDetailseventadmin), surl);
		    System.out.println("response code is"+loginapiresponse.get("Statuscode"));
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("Statuscode").toString(), HttpStatusCode.httpsStatusCode200);
			logger.info("Login API response Code :::"+loginapiresponse);
			  
			 //EventAdminId String
			   String  eventadminIdArray[]= {loginapiresponse.get("id").toString()};
			   createEventBean.setEventAdminId(eventadminIdArray);

			   //Create event
				CreateEventsAPI createEventsAPI=new CreateEventsAPI();
			   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
			   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
			   customReport.customizedReport(HttpStatusCode.httpsStatusCode200,createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
			   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
			   
			   //start webcast  using api  
			 GetEventStatusAPI starteventAPI=new GetEventStatusAPI();		
			 JSONObject  starteventapiresponse=starteventAPI.starteventjsonobject(loginapiresponse1);
			 assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
			 customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
			   
			  apibeanfactory.PushContentbean(createpushcontentbean);
			  createpushcontentbean.setLink(IAPIConstantCodes.GOOGLEADDRESS);
			  createpushcontentbean.setPushMethod(ICreatePushContentService.pushMethod[0]);
			  createpushcontentbean.setIsEnabled(IAPIConstantCodes.TRUE);
			 
			  PushContentAPI pushcontentAPI=new PushContentAPI();
			  ArrayList<String> pushcontentAPIresponse=pushcontentAPI.PushContentWebCastAPI(loginapiresponse1,createpushcontentbean);
			  assertionapiresponse.verifyAssert_httpCode(pushcontentAPIresponse.get(1).toString(), HttpStatusCode.httpsStatusCode200);  
			  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, pushcontentAPIresponse.get(1).toString(),statusValue, driver, sTestcaseName);
		 

			  
				 customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("Statuscode").toString(),statusValue, driver, sTestcaseName);
				 loginapiresponse.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
				 loginapiresponse.put(IAPIConstantCodes.SharedURLID,pushcontentAPIresponse.get(0));
				 
              //put is enables as false as event admin	
			  createpushcontentbean.setIsEnabled(IAPIConstantCodes.FALSE);
			  
			  ArrayList<String> putpushcontentAPIresponsestatus=pushcontentAPI.Put_PushContentAPI_Status(loginapiresponse,createpushcontentbean);
			  assertionapiresponse.verifyAssert_httpCode(putpushcontentAPIresponsestatus.get(0).toString(), HttpStatusCode.httpsStatusCode200);  
			  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, putpushcontentAPIresponsestatus.get(0).toString(),statusValue, driver, sTestcaseName);
			  customReport.customizedReport("false", createpushcontentbean.getIsEnabled().toString(),statusValue, driver, sTestcaseName);  
			  
			 //selenium code 
			    launchURL(surl);
		        customReport.reporter("Application launch with URL : ", surl);
		        homePage = loginPage.loginAs(IUsersList.GetEventDetailseventadmin, sPassword);
		        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
		        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
		        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
		        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
		        avengereventwebcastpage.verify_eventlabel(createEventBean.getTitle());
		        avengereventwebcastpage.click_sendalinkurltoallattendes();
		        assertionapiresponse.verifyAssert_httpCode(createpushcontentbean.getIsEnabled(),avengereventwebcastpage.verify_pushlabel().get(0).toString());
		        customReport.customizedReport(avengereventwebcastpage.verify_pushlabel().get(0).toString(),createpushcontentbean.getIsEnabled(),statusValue, driver, sTestcaseName);         
		        customReport.checkinglist(statusValue);  
		}
		
	
		@Test(description="To Verify and create a new webcast and  and use put api and IS enabled as invalid (Activate/Deactivate a Shared URL) ",groups = {PUTPushContentWebcast})
		public void TC04_ActivateDeactivate_SharedURL_PUTPushContentWebcast_IsenabledAsInvalid_Negative_AV28786(ITestContext context) throws ParseException
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

			   //Create event
				CreateEventsAPI createEventsAPI=new CreateEventsAPI();
			   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
			   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
			   customReport.customizedReport(HttpStatusCode.httpsStatusCode200,createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
			 loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
			   
			   //start webcast  using api  
			 GetEventStatusAPI starteventAPI=new GetEventStatusAPI();		
			 JSONObject  starteventapiresponse=starteventAPI.starteventjsonobject(loginapiresponse1);
			 assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
			 customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
			   
			  apibeanfactory.PushContentbean(createpushcontentbean);
			  createpushcontentbean.setLink(IAPIConstantCodes.GOOGLEADDRESS);
			  createpushcontentbean.setPushMethod(ICreatePushContentService.pushMethod[0]);
			  createpushcontentbean.setIsEnabled(IAPIConstantCodes.TRUE);
			 
			  PushContentAPI pushcontentAPI=new PushContentAPI();
			  ArrayList<String> pushcontentAPIresponse=pushcontentAPI.PushContentWebCastAPI(loginapiresponse1, createpushcontentbean);
			  assertionapiresponse.verifyAssert_httpCode(pushcontentAPIresponse.get(1).toString(), HttpStatusCode.httpsStatusCode200);  
			  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, pushcontentAPIresponse.get(1).toString(),statusValue, driver, sTestcaseName);
			 loginapiresponse1.put(IAPIConstantCodes.SharedURLID,pushcontentAPIresponse.get(0));
			
			 //put is enabled as invalid
			  createpushcontentbean1.setIsEnabled(IAPIConstantCodes.ACTIVE);
			  
			  ArrayList<String> putpushcontentAPIresponsestatus=pushcontentAPI.Put_PushContentAPI_Status(loginapiresponse1,createpushcontentbean1);
			  assertionapiresponse.verifyAssert_httpCode(putpushcontentAPIresponsestatus.get(0).toString(), HttpStatusCode.httpsStatus500);  
			  customReport.customizedReport(HttpStatusCode.httpsStatus500, putpushcontentAPIresponsestatus.get(0).toString(),statusValue, driver, sTestcaseName);
			  customReport.checkinglist(statusValue);
}
		
		@Test(description="To Verify and create a new webcast and  and use put api and in request body edit/put pushmethod as Invalid (Edit Shared URL) ",groups = {PUTPushContentWebcast})
		public void TC05Edit_SharedURL_PUTPushContentWebcast_PushMethodAsInvalid_Negative_AV28786(ITestContext context) 
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
			  
			   //Create event
				CreateEventsAPI createEventsAPI=new CreateEventsAPI();
			   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
			   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
			   customReport.customizedReport(HttpStatusCode.httpsStatusCode200,createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
			 loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
			   
		      //start webcast  using api  
			 GetEventStatusAPI starteventAPI=new GetEventStatusAPI();		
			 JSONObject  starteventapiresponse=starteventAPI.starteventjsonobject(loginapiresponse1);
			 assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
			 customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
			   
			   
			   
			  apibeanfactory.PushContentbean(createpushcontentbean);
			  createpushcontentbean.setLink(IAPIConstantCodes.GOOGLEADDRESS);
			  createpushcontentbean.setPushMethod(ICreatePushContentService.pushMethod[0]);
			  createpushcontentbean.setIsEnabled(IAPIConstantCodes.TRUE);
			 
			  PushContentAPI pushcontentAPI=new PushContentAPI();
			  ArrayList<String> pushcontentAPIresponse=pushcontentAPI.PushContentWebCastAPI(loginapiresponse1, createpushcontentbean);
			  assertionapiresponse.verifyAssert_httpCode(pushcontentAPIresponse.get(1).toString(), HttpStatusCode.httpsStatusCode200);  
			  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, pushcontentAPIresponse.get(1).toString(),statusValue, driver, sTestcaseName);
			 loginapiresponse1.put(IAPIConstantCodes.SharedURLID,pushcontentAPIresponse.get(0));
			 
			  //edit push method as invalid
			 createpushcontentbean.setName(createpushcontentbean.getName());
			  createpushcontentbean.setLink(IAPIConstantCodes.GOOGLEADDRESS);
			  createpushcontentbean.setPushMethod(IAPIConstantCodes.APINAME);
			  createpushcontentbean.setIsEnabled(IAPIConstantCodes.TRUE);
			  
			  JSONObject putpushcontentAPIresponseid=pushcontentAPI.Put_PushContentWebCastAPI_Id(loginapiresponse1, createpushcontentbean);
			  assertionapiresponse.verifyAssert_httpCode(putpushcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode500);  
			  customReport.customizedReport(HttpStatusCode.httpsStatus500, putpushcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
			  customReport.checkinglist(statusValue);
		}
		
		@Test(description="To Verify and create a new webcast and  and use put api and in request body update all the data given (Edit Shared URL) ",groups = {PUTPushContentWebcast})
		public void TC06_Edit_SharedURL_PUTPushContentWebcast_UpdateAlldata_Positive_AV28786(ITestContext context) 
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
			  
			   //Create event
				CreateEventsAPI createEventsAPI=new CreateEventsAPI();
			   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
			   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
			   customReport.customizedReport(HttpStatusCode.httpsStatusCode200,createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
			   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
			   
			   //start webcast  using api  
			   GetEventStatusAPI starteventAPI=new GetEventStatusAPI();		
				 JSONObject  starteventapiresponse=starteventAPI.starteventjsonobject(loginapiresponse1);
				 assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
				 customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
				   
			  apibeanfactory.PushContentbean(createpushcontentbean);
			  createpushcontentbean.setLink(IAPIConstantCodes.GOOGLEADDRESS);
			  createpushcontentbean.setPushMethod(ICreatePushContentService.pushMethod[1]);
			  createpushcontentbean.setIsEnabled(IAPIConstantCodes.TRUE);
			 
			  PushContentAPI pushcontentAPI=new PushContentAPI();
			  ArrayList<String> pushcontentAPIresponse=pushcontentAPI.PushContentWebCastAPI(loginapiresponse1, createpushcontentbean);
			  assertionapiresponse.verifyAssert_httpCode(pushcontentAPIresponse.get(1).toString(), HttpStatusCode.httpsStatusCode200);  
			  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, pushcontentAPIresponse.get(1).toString(),statusValue, driver, sTestcaseName);
			 loginapiresponse1.put(IAPIConstantCodes.SharedURLID,pushcontentAPIresponse.get(0));
			 
			  //Edit all the objects with valid data
			  apibeanfactory.PushContentbean(createpushcontentbean);
			  createpushcontentbean.setLink(ICreatePushContentService.link);
			  createpushcontentbean.setPushMethod(ICreatePushContentService.pushMethod[0]);
			  createpushcontentbean.setIsEnabled(IAPIConstantCodes.FALSE);
			 
			 
			  JSONObject putpushcontentAPIresponseid=pushcontentAPI.Put_PushContentWebCastAPI_Id(loginapiresponse1,createpushcontentbean);
			  assertionapiresponse.verifyAssert_httpCode(putpushcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
			  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, putpushcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
			  customReport.customizedReport(putpushcontentAPIresponseid.get("name").toString(),createpushcontentbean.getName(), statusValue, driver, sTestcaseName);
			  customReport.customizedReport(putpushcontentAPIresponseid.get("link").toString(),createpushcontentbean.getLink(), statusValue, driver, sTestcaseName);
			  customReport.customizedReport(putpushcontentAPIresponseid.get("pushMethod").toString(),createpushcontentbean.getPushMethod(), statusValue, driver, sTestcaseName);
			  customReport.customizedReport(putpushcontentAPIresponseid.get("isEnabled").toString(),createpushcontentbean.getIsEnabled(), statusValue, driver, sTestcaseName);
			  
			  
			  
			  
			  //selenium code 
			  launchURL(surl);
		        customReport.reporter("Application launch with URL : ", surl);
		        homePage = loginPage.loginAs(sUserName, sPassword);
		        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
		        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
		        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
		        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
		        avengereventwebcastpage.click_EditButton();
		        avengereventwebcastpage.click_AttendeesEngagements();
		        //assertion with name
		        customReport.customizedReport(avengereventwebcastpage.verify_label(createpushcontentbean.getName()),putpushcontentAPIresponseid.get("name").toString(), statusValue, driver, sTestcaseName);
		       //assertion with is enabled 
		        avengereventwebcastpage.click_sendalinkurltoallattendes();
		        customReport.customizedReport(avengereventwebcastpage.verify_pushlabel().get(0).toString(),putpushcontentAPIresponseid.get("isEnabled").toString(),statusValue, driver, sTestcaseName);         
		        customReport.checkinglist(statusValue);
		}
		
		@Test(description="To Verify and create a new webcast and  and use put api and in request body update all data as empty (Edit Shared URL) ",groups = {PUTPushContentWebcast})
		public void TC07_Edit_SharedURL_PUTPushContentWebcast_AlldataAsEmpty_Negative_AV28786(ITestContext context) 
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
			  
			   //Create event
				CreateEventsAPI createEventsAPI=new CreateEventsAPI();
			   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
			   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
			   customReport.customizedReport(HttpStatusCode.httpsStatusCode200,createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
			 loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
			   
			/* //start webcast  using api  
			 GetEventStatusAPI starteventAPI=new GetEventStatusAPI();		
			 JSONObject  starteventapiresponse=starteventAPI.starteventjsonobject(loginapiresponse1);
			 assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
			 customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);*/
			   
			  apibeanfactory.PushContentbean(createpushcontentbean);
			  createpushcontentbean.setLink(IAPIConstantCodes.GOOGLEADDRESS);
			  createpushcontentbean.setPushMethod(ICreatePushContentService.pushMethod[0]);
			  createpushcontentbean.setIsEnabled(IAPIConstantCodes.TRUE);
			 
			  PushContentAPI pushcontentAPI=new PushContentAPI();
			  ArrayList<String> pushcontentAPIresponse=pushcontentAPI.PushContentWebCastAPI(loginapiresponse1,createpushcontentbean);
			  assertionapiresponse.verifyAssert_httpCode(pushcontentAPIresponse.get(1).toString(), HttpStatusCode.httpsStatusCode200);  
			  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, pushcontentAPIresponse.get(1).toString(),statusValue, driver, sTestcaseName);
			 loginapiresponse1.put(IAPIConstantCodes.SharedURLID,pushcontentAPIresponse.get(0));
			  
			  //Edit all the objects as empty
			  createpushcontentbean1.setName("");
			  createpushcontentbean1.setLink("");
			  createpushcontentbean1.setPushMethod( "");
			  createpushcontentbean1.setIsEnabled("");
			 
			  JSONObject putpushcontentAPIresponseid=pushcontentAPI.Put_PushContentWebCastAPI_Id(loginapiresponse1,createpushcontentbean1);
			  assertionapiresponse.verifyAssert_httpCode(putpushcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode500);  
			  customReport.customizedReport(HttpStatusCode.httpsStatus500, putpushcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
			  customReport.checkinglist(statusValue);
		}
		
		@Test(description="To Verify and create a new webcast and  and use put in request body with all valid json element and invalid sharedurlid (Edit Shared URL) ",groups = {PUTPushContentWebcast})
		public void TC08_Edit_SharedURL_PUTPushContentWebcast_Invalidsharedurlid_Negative_AV28786(ITestContext context) 
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
			  
			   //Create event
				CreateEventsAPI createEventsAPI=new CreateEventsAPI();
			   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
			   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
			   customReport.customizedReport(HttpStatusCode.httpsStatusCode200,createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
			 loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
			   
			 
			  apibeanfactory.PushContentbean(createpushcontentbean);
			  createpushcontentbean.setLink(IAPIConstantCodes.GOOGLEADDRESS);
			  createpushcontentbean.setPushMethod(ICreatePushContentService.pushMethod[0]);
			  createpushcontentbean.setIsEnabled(IAPIConstantCodes.TRUE);
			 
			  PushContentAPI pushcontentAPI=new PushContentAPI();
			  ArrayList<String> pushcontentAPIresponse=pushcontentAPI.PushContentWebCastAPI(loginapiresponse1, createpushcontentbean);
			  assertionapiresponse.verifyAssert_httpCode(pushcontentAPIresponse.get(1).toString(), HttpStatusCode.httpsStatusCode200);  
			  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, pushcontentAPIresponse.get(1).toString(),statusValue, driver, sTestcaseName);
			//invalid sharedurlid
			  loginapiresponse1.put(IAPIConstantCodes.SharedURLID,createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
			 
			  apibeanfactory.PushContentbean(createpushcontentbean1);
			  createpushcontentbean1.setLink(ICreatePushContentService.Link);
			  createpushcontentbean1.setPushMethod(ICreatePushContentService.pushMethod[0]);
			  createpushcontentbean1.setIsEnabled(IAPIConstantCodes.TRUE);
			 
			 //Edit with invalid sharedurlid
			  ArrayList<String> putpushcontentAPIresponseid=pushcontentAPI.Put_PushContentWebCastAPI_IdInvalid(loginapiresponse1,createpushcontentbean1);
			  assertionapiresponse.verifyAssert_httpCode(putpushcontentAPIresponseid.get(0).toString(), HttpStatusCode.httpsStatus401);  
			  customReport.customizedReport(HttpStatusCode.httpsStatus401, putpushcontentAPIresponseid.get(0).toString(),statusValue, driver, sTestcaseName);
			  customReport.checkinglist(statusValue);
		 
		        
		}
		
		@Test(description="To Verify and create a new webcast and  and use put api and in request body add space before in name and after &same as do for link  (Edit Shared URL) ",groups = {PUTPushContentWebcast})
		public void TC09_Edit_SharedURL_PUTPushContentWebcast_UpdateAlldata_enteringspacebeforeandafterinnameandlink_Positive_AV29407(ITestContext context) 
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
			   createEventBean.setPushContent("Yes");
			   createEventBean.setIsEnabled("true");
			   
			  
			   //Create event
				CreateEventsAPI createEventsAPI=new CreateEventsAPI();
			   JSONObject createeventapirespone = createEventsAPI.createEvent_Jsonobject(loginapiresponse1,createEventBean);
			   assertionapiresponse.verifyAssert_httpCode(createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
			   customReport.customizedReport(HttpStatusCode.httpsStatusCode200,createeventapirespone.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
			   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
			   
			 //start webcast  using api  
			   GetEventStatusAPI starteventAPI=new GetEventStatusAPI();		
				 JSONObject  starteventapiresponse=starteventAPI.starteventjsonobject(loginapiresponse1);
				 assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
				 customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
				   
			   
			  apibeanfactory.PushContentbean(createpushcontentbean);
			  createpushcontentbean.setLink(IAPIConstantCodes.GOOGLEADDRESS);
			  createpushcontentbean.setPushMethod(ICreatePushContentService.pushMethod[0]);
			  createpushcontentbean.setIsEnabled(IAPIConstantCodes.TRUE);
			 
			  PushContentAPI pushcontentAPI=new PushContentAPI();
			  ArrayList<String> pushcontentAPIresponse=pushcontentAPI.PushContentWebCastAPI(loginapiresponse1, createpushcontentbean);
			  assertionapiresponse.verifyAssert_httpCode(pushcontentAPIresponse.get(1).toString(), HttpStatusCode.httpsStatusCode200);  
			  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, pushcontentAPIresponse.get(1).toString(),statusValue, driver, sTestcaseName);
			 loginapiresponse1.put(IAPIConstantCodes.SharedURLID,pushcontentAPIresponse.get(0));
			 
			  //edited spaces before name and after name and similarly done for link
			  apibeanfactory.PushContentbean(createpushcontentbean1);
			  createpushcontentbean1.setLink(ICreatePushContentService.Linkwithspace);
			  createpushcontentbean1.setPushMethod(ICreatePushContentService.pushMethod[1]);
			  createpushcontentbean1.setIsEnabled(IAPIConstantCodes.FALSE);
			 
			 
			  JSONObject putpushcontentAPIresponseid=pushcontentAPI.Put_PushContentWebCastAPI_Id(loginapiresponse1,createpushcontentbean1);
			  assertionapiresponse.verifyAssert_httpCode(putpushcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
			  customReport.customizedReport(HttpStatusCode.httpsStatusCode200, putpushcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
			  customReport.customizedReport(putpushcontentAPIresponseid.get("name").toString(),createpushcontentbean1.getName(), statusValue, driver, sTestcaseName);
			  customReport.customizedReport(putpushcontentAPIresponseid.get("link").toString(),createpushcontentbean1.getLink(), statusValue, driver, sTestcaseName);
			  
			  
			//selenium code 
			  launchURL(surl);
		        customReport.reporter("Application launch with URL : ", surl);
		        homePage = loginPage.loginAs(sUserName, sPassword);
		        AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		        AvengerEventsPage  avengereventspage= avengerdashboardpage.click_EventsTab();
		        AvengerEventDetailsBeanPage avengerEventdetailsbeanpage=new AvengerEventDetailsBeanPage();
		        avengerEventdetailsbeanpage.setTitle(createEventBean.getTitle()); 
		        avengereventdetailspage=avengereventspage.clickCreatedEvent(avengerEventdetailsbeanpage);
		        avengereventwebcastpage.click_EditButton();
		        avengereventwebcastpage.click_AttendeesEngagements();
		        //assertion with name
		        customReport.customizedReport(avengereventwebcastpage.verify_label(createpushcontentbean1.getName()),putpushcontentAPIresponseid.get("name").toString(), statusValue, driver, sTestcaseName); 
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