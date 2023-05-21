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
import com.vbricks.avenger.serviceimpl.GetEventStatusAPI;
//import com.vbricks.avenger.serviceimpl.PushContentAPI;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class PUTEmbeddedContent_AV28828 extends TestBase{

	private static Logger logger = Logger.getLogger(PUTEmbeddedContent_AV28828.class);
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
		createembeddedcontentbean=new CreateEmbeddedContentBean();
		createembeddedcontentbean1=new CreateEmbeddedContentBean();
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

	 @Test(description="To Verify and create a new webcast and in  embedded request body put is enabled as true then start webcast and verify is enabled (Activate/Deactivate a Embed URL) ",groups ={PUTEmbeddedContentWebcast})
		public void TC01_ActivateDeactivate_EmbedURL_PUTEmbeddedContentWebcast_IsEnabledAsTrue_andthenStartWebbcast_Positive_AV28828(ITestContext context) 
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
			   String eventid=createeventapirespone.get("eventId").toString();
			   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
			   
			 
			   apibeanfactory.EmbeddedContentbean(createembeddedcontentbean);
			   createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
			   createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
			 
			  EmbeddedContentAPI embeddedcontentAPI=new EmbeddedContentAPI();
			  JSONObject embeddedcontentAPIresponse=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean);
			  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
			  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
			  loginapiresponse1.put(IAPIConstantCodes.SharedURLID,embeddedcontentAPIresponse.get("id"));
			  
			  //set is enabled as true
			  createembeddedcontentbean.setName(createembeddedcontentbean.getName());
			  createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
			  createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
			  createembeddedcontentbean.setIsEnabled("true");
			  
			  
			  JSONObject putembeddedcontentAPIresponseid=embeddedcontentAPI.Put_EmbeddedContentAPI_Id(loginapiresponse1,createembeddedcontentbean);
			  assertionapiresponse.verifyAssert_httpCode(putembeddedcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
			  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,putembeddedcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
			  //assertion with Isenabled as true
			  assertionapiresponse.verifyAssert_httpCode("true",putembeddedcontentAPIresponseid.get("isEnabled").toString());  
			  customReport.customizedReport("true",putembeddedcontentAPIresponseid.get("isEnabled").toString(),statusValue, driver, sTestcaseName);
			 
			  
			  //start webcast  using api  
			   GetEventStatusAPI starteventAPI=new GetEventStatusAPI();		
				 JSONObject  starteventapiresponse=starteventAPI.starteventjsonobject(loginapiresponse1);
				 assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
				 customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
				   
			  
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
		        avengereventwebcastpage.click_Embeddedcontent();
		        assertionapiresponse.verifyAssert_httpCode(putembeddedcontentAPIresponseid.get("isEnabled").toString(),avengereventwebcastpage.verify_embeddedlabel().get(0).toString());
		        customReport.customizedReport(avengereventwebcastpage.verify_embeddedlabel().get(0).toString(),putembeddedcontentAPIresponseid.get("isEnabled").toString(),statusValue, driver, sTestcaseName);  
		        customReport.checkinglist(statusValue);
		        
		        
			  
		}
	 
		 @Test(description="To Verify and create a new webcast and in  embedded request body put is enabled as false then start webcast and verify is enabled  (Activate/Deactivate a Embed URL) ",groups ={PUTEmbeddedContentWebcast})
		public void TC02_ActivateDeactivate_EmbedURL_PUTEmbeddedContentWebcast_IsEnabledAsfalse_andthenStartwebcast_Positive_AV28828(ITestContext context) 
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
			   String eventid=createeventapirespone.get("eventId").toString();
			   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
			   	   
			   apibeanfactory.EmbeddedContentbean(createembeddedcontentbean);
			   createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
			   createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
			 
			  EmbeddedContentAPI embeddedcontentAPI=new EmbeddedContentAPI();
			  JSONObject embeddedcontentAPIresponse=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean);
			  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
			  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
			  loginapiresponse1.put(IAPIConstantCodes.SharedURLID,embeddedcontentAPIresponse.get("id"));
			  
			  //set is enabled as false
			  createembeddedcontentbean.setName(createembeddedcontentbean.getName());
			  createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
			  createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
			  createembeddedcontentbean.setIsEnabled("false");
			  
			  JSONObject putembeddedcontentAPIresponseid=embeddedcontentAPI.Put_EmbeddedContentAPI_Id(loginapiresponse1,createembeddedcontentbean);
			  assertionapiresponse.verifyAssert_httpCode(putembeddedcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
			  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,putembeddedcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
			  assertionapiresponse.verifyAssert_httpCode("false",putembeddedcontentAPIresponseid.get("isEnabled").toString() );  
			  customReport.customizedReport("false",putembeddedcontentAPIresponseid.get("isEnabled").toString(),statusValue, driver, sTestcaseName);
			  
			  
			  //start webcast  using api  
			   GetEventStatusAPI starteventAPI=new GetEventStatusAPI();		
			  JSONObject  starteventapiresponse=starteventAPI.starteventjsonobject(loginapiresponse1);
			  assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
			  customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
			 
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
		        avengereventwebcastpage.click_Embeddedcontent();
		        assertionapiresponse.verifyAssert_httpCode(putembeddedcontentAPIresponseid.get("isEnabled").toString(),avengereventwebcastpage.verify_embeddedlabel().get(0).toString());
		        customReport.customizedReport(avengereventwebcastpage.verify_embeddedlabel().get(0).toString(),putembeddedcontentAPIresponseid.get("isEnabled").toString(),statusValue, driver, sTestcaseName);  
		        customReport.checkinglist(statusValue);
		}
		
		 @Test(description="To Verify and create a new webcast and in  embedded request body (put) editname   (Activate/Deactivate a Embed URL) ",groups ={PUTEmbeddedContentWebcast})
			public void TC03_ActivateDeactivate_EmbedURL_PUTEmbeddedContentWebcast_editname_Positive_AV28828(ITestContext context) 
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
				   String eventid=createeventapirespone.get("eventId").toString();
				   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
				   
				 //start webcast  using api  
				   GetEventStatusAPI starteventAPI=new GetEventStatusAPI();		
					 JSONObject  starteventapiresponse=starteventAPI.starteventjsonobject(loginapiresponse1);
					 assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
					 customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
					   
				   apibeanfactory.EmbeddedContentbean(createembeddedcontentbean);
				   createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
				   createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
				 
				  EmbeddedContentAPI embeddedcontentAPI=new EmbeddedContentAPI();
				  JSONObject embeddedcontentAPIresponse=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean);
				  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
				  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
				  loginapiresponse1.put(IAPIConstantCodes.SharedURLID,embeddedcontentAPIresponse.get("id"));
				  
				  //edit the name
				  createembeddedcontentbean.setName("EDITED EMBED");
				  createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
				  createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
				  createembeddedcontentbean.setIsEnabled("false");
				  
				  JSONObject putembeddedcontentAPIresponseid=embeddedcontentAPI.Put_EmbeddedContentAPI_Id(loginapiresponse1,createembeddedcontentbean);
				  assertionapiresponse.verifyAssert_httpCode(putembeddedcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
				  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,putembeddedcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
				  
				  //assertion with edited name
				  assertionapiresponse.verifyAssert_httpCode(createembeddedcontentbean.getName(),putembeddedcontentAPIresponseid.get("name").toString());  
				  customReport.customizedReport(putembeddedcontentAPIresponseid.get("name").toString(),createembeddedcontentbean.getName(),statusValue, driver, sTestcaseName);
				  
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
			        avengereventwebcastpage.click_EditButton();
			        avengereventwebcastpage.click_AttendeesEngagements();
			        customReport.customizedReport(createembeddedcontentbean.getName(), avengereventspage.verify_text(createembeddedcontentbean.getName()),statusValue, driver, sTestcaseName);
					customReport.checkinglist(statusValue);
			      	  
			}
		
		
		
		 @Test(description="To Verify and create a new webcast and in  embedded request body (put) editcode   (Activate/Deactivate a Embed URL) ",groups ={PUTEmbeddedContentWebcast})
			public void TC04_ActivateDeactivate_EmbedURL_PUTEmbeddedContentWebcast_editcode_Positive_AV28828(ITestContext context) 
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
				   String eventid=createeventapirespone.get("eventId").toString();
				   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
				   
				 //start webcast  using api  
				   GetEventStatusAPI starteventAPI=new GetEventStatusAPI();		
					 JSONObject  starteventapiresponse=starteventAPI.starteventjsonobject(loginapiresponse1);
					 assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
					 customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
					   
				   apibeanfactory.EmbeddedContentbean(createembeddedcontentbean);
				   createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
				   createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
				 
				  EmbeddedContentAPI embeddedcontentAPI=new EmbeddedContentAPI();
				  JSONObject embeddedcontentAPIresponse=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean);
				  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
				  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
				  loginapiresponse1.put(IAPIConstantCodes.SharedURLID,embeddedcontentAPIresponse.get("id"));
				  
				  //edit the code
				  createembeddedcontentbean.setName(createembeddedcontentbean.getName());
				  createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code1);
				  createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
				  createembeddedcontentbean.setIsEnabled("false");
				  
				  JSONObject putembeddedcontentAPIresponseid=embeddedcontentAPI.Put_EmbeddedContentAPI_Id(loginapiresponse1,createembeddedcontentbean);
				  assertionapiresponse.verifyAssert_httpCode(putembeddedcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
				  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,putembeddedcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
				 //assertion with edited code
				  assertionapiresponse.verifyAssert_httpCode(createembeddedcontentbean.getCode(),putembeddedcontentAPIresponseid.get("code").toString());  
				  customReport.customizedReport(putembeddedcontentAPIresponseid.get("code").toString(),createembeddedcontentbean.getCode(),statusValue, driver, sTestcaseName);
				  
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
			        avengereventwebcastpage.click_EditButton();
			        avengereventwebcastpage.click_AttendeesEngagements();
			        customReport.customizedReport(createembeddedcontentbean.getName(), avengereventspage.verify_text(createembeddedcontentbean.getName()),statusValue, driver, sTestcaseName);
					customReport.checkinglist(statusValue);
			      	  
			}
		 
		 @Test(description="To Verify and create a new webcast and in  embedded request body (put) editicon   (Activate/Deactivate a Embed URL) ",groups ={PUTEmbeddedContentWebcast})
			public void TC05_ActivateDeactivate_EmbedURL_PUTEmbeddedContentWebcast_editicon_Positive_AV28828(ITestContext context) 
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
				   String eventid=createeventapirespone.get("eventId").toString();
				   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
				   
				 //start webcast  using api  
				   GetEventStatusAPI starteventAPI=new GetEventStatusAPI();		
					 JSONObject  starteventapiresponse=starteventAPI.starteventjsonobject(loginapiresponse1);
					 assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
					 customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
					   
				   apibeanfactory.EmbeddedContentbean(createembeddedcontentbean);
				   createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
				   createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
				 
				  EmbeddedContentAPI embeddedcontentAPI=new EmbeddedContentAPI();
				  JSONObject embeddedcontentAPIresponse=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean);
				  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
				  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
				  loginapiresponse1.put(IAPIConstantCodes.SharedURLID,embeddedcontentAPIresponse.get("id"));
				  
				  //edit the icon
				  createembeddedcontentbean.setName(createembeddedcontentbean.getName());
				  createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
				  createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[3]);
				  createembeddedcontentbean.setIsEnabled("false");
				  
				  JSONObject putembeddedcontentAPIresponseid=embeddedcontentAPI.Put_EmbeddedContentAPI_Id(loginapiresponse1,createembeddedcontentbean);
				  assertionapiresponse.verifyAssert_httpCode(putembeddedcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
				  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,putembeddedcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
				 //assertion with edited code
				  assertionapiresponse.verifyAssert_httpCode(createembeddedcontentbean.getIcon(),putembeddedcontentAPIresponseid.get("icon").toString());  
				  customReport.customizedReport(putembeddedcontentAPIresponseid.get("icon").toString(),createembeddedcontentbean.getIcon(),statusValue, driver, sTestcaseName);
				  
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
			        avengereventwebcastpage.click_EditButton();
			        avengereventwebcastpage.click_AttendeesEngagements();
			        customReport.customizedReport(createembeddedcontentbean.getName(), avengereventspage.verify_text(createembeddedcontentbean.getName()),statusValue, driver, sTestcaseName);
					customReport.checkinglist(statusValue);
			      	  
			}
		
		 @Test(description="To Verify and create a new webcast and in  embedded request body (put) edit all json elments   (Activate/Deactivate a Embed URL) ",groups ={PUTEmbeddedContentWebcast})
			public void TC06_ActivateDeactivate_EmbedURL_PUTEmbeddedContentWebcast_editAllJsonElements_Positive_AV28828(ITestContext context) 
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
				   String eventid=createeventapirespone.get("eventId").toString();
				   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
				   
				 //start webcast  using api  
				   GetEventStatusAPI starteventAPI=new GetEventStatusAPI();		
					 JSONObject  starteventapiresponse=starteventAPI.starteventjsonobject(loginapiresponse1);
					 assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
					 customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
					   
				    
				   apibeanfactory.EmbeddedContentbean(createembeddedcontentbean);
				   createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
				   createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
				 
				  EmbeddedContentAPI embeddedcontentAPI=new EmbeddedContentAPI();
				  JSONObject embeddedcontentAPIresponse=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean);
				  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
				  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
				  loginapiresponse1.put(IAPIConstantCodes.SharedURLID,embeddedcontentAPIresponse.get("id"));
				  
				  //edit all the json elements
				  apibeanfactory.EmbeddedContentbean(createembeddedcontentbean);
				  createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code1);
				  createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[3]);
				  createembeddedcontentbean.setIsEnabled("true");
				  
				  JSONObject putembeddedcontentAPIresponseid=embeddedcontentAPI.Put_EmbeddedContentAPI_Id(loginapiresponse1,createembeddedcontentbean);
				  assertionapiresponse.verifyAssert_httpCode(putembeddedcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);
				  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,putembeddedcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
				 //assertion with all the json elements
				  assertionapiresponse.verifyAssert_httpCode(createembeddedcontentbean.getName(),putembeddedcontentAPIresponseid.get("name").toString());  
				  customReport.customizedReport(putembeddedcontentAPIresponseid.get("name").toString(),createembeddedcontentbean.getName(),statusValue, driver, sTestcaseName);
				  assertionapiresponse.verifyAssert_httpCode(createembeddedcontentbean.getCode(),putembeddedcontentAPIresponseid.get("code").toString());  
				  customReport.customizedReport(putembeddedcontentAPIresponseid.get("code").toString(),createembeddedcontentbean.getCode(),statusValue, driver, sTestcaseName);
				  assertionapiresponse.verifyAssert_httpCode(createembeddedcontentbean.getIcon(),putembeddedcontentAPIresponseid.get("icon").toString());  
				  customReport.customizedReport(putembeddedcontentAPIresponseid.get("icon").toString(),createembeddedcontentbean.getIcon(),statusValue, driver, sTestcaseName);
				  assertionapiresponse.verifyAssert_httpCode("true",putembeddedcontentAPIresponseid.get("isEnabled").toString() );  
				  customReport.customizedReport("true",putembeddedcontentAPIresponseid.get("isEnabled").toString(),statusValue, driver, sTestcaseName);
		       	
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
			        avengereventwebcastpage.click_EditButton();
			        avengereventwebcastpage.click_AttendeesEngagements();
			        customReport.customizedReport(createembeddedcontentbean.getName(), avengereventspage.verify_text(createembeddedcontentbean.getName()),statusValue, driver, sTestcaseName);
			        avengereventwebcastpage.click_Embeddedcontent();
			        assertionapiresponse.verifyAssert_httpCode(putembeddedcontentAPIresponseid.get("isEnabled").toString(),avengereventwebcastpage.verify_embeddedlabel().get(0).toString());
			        customReport.customizedReport(avengereventwebcastpage.verify_embeddedlabel().get(0).toString(),putembeddedcontentAPIresponseid.get("isEnabled").toString(),statusValue, driver, sTestcaseName);  
			        customReport.checkinglist(statusValue);
			}
	 @Test(description="To Verify and create a new webcast and in  embedded request body (put) edit all json elments As empty  (Activate/Deactivate a Embed URL) ",groups ={PUTEmbeddedContentWebcast})
			public void TC07_ActivateDeactivate_EmbedURL_PUTEmbeddedContentWebcast_editAllJsonElements_AsEmpty_Negative_AV28828(ITestContext context) 
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
				   String eventid=createeventapirespone.get("eventId").toString();
				   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
				   
				 //start webcast  using api  
				   GetEventStatusAPI starteventAPI=new GetEventStatusAPI();		
					 JSONObject  starteventapiresponse=starteventAPI.starteventjsonobject(loginapiresponse1);
					 assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
					 customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
					   
				   apibeanfactory.EmbeddedContentbean(createembeddedcontentbean);
				   createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
				   createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
				 
				  EmbeddedContentAPI embeddedcontentAPI=new EmbeddedContentAPI();
				  JSONObject embeddedcontentAPIresponse=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean);
				  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
				  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
				  loginapiresponse1.put(IAPIConstantCodes.SharedURLID,embeddedcontentAPIresponse.get("id"));
				  
				  //edit all the json elements
				  createembeddedcontentbean1.setName("");
				  createembeddedcontentbean1.setCode("");
				  createembeddedcontentbean1.setIcon("");
				  createembeddedcontentbean1.setIsEnabled("");
				  
				  JSONObject putembeddedcontentAPIresponseid=embeddedcontentAPI.Put_EmbeddedContentAPI_Id(loginapiresponse1,createembeddedcontentbean1);
				  assertionapiresponse.verifyAssert_httpCode(putembeddedcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatus400);
				  customReport.customizedReport(HttpStatusCode.httpsStatus400,putembeddedcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
				  customReport.checkinglist(statusValue);
		       	
			}
		 
		 @Test(description="To Verify and create a new webcast and in  embedded request body (put) editcode as invalid  (Activate/Deactivate a Embed URL) ",groups ={PUTEmbeddedContentWebcast})
			public void TC08_ActivateDeactivate_EmbedURL_PUTEmbeddedContentWebcast_editcode_AsInvalid_Negative_AV28828(ITestContext context) 
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
				   String eventid=createeventapirespone.get("eventId").toString();
				   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
				   
				 //start webcast  using api  
				   GetEventStatusAPI starteventAPI=new GetEventStatusAPI();		
					 JSONObject  starteventapiresponse=starteventAPI.starteventjsonobject(loginapiresponse1);
					 assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
					 customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
					   
				   apibeanfactory.EmbeddedContentbean(createembeddedcontentbean);
				   createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
				   createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
				 
				  EmbeddedContentAPI embeddedcontentAPI=new EmbeddedContentAPI();
				  JSONObject embeddedcontentAPIresponse=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean);
				  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
				  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
				  loginapiresponse1.put(IAPIConstantCodes.SharedURLID,embeddedcontentAPIresponse.get("id"));
				  
				  //edit the code
				  createembeddedcontentbean.setName(createembeddedcontentbean.getName());
				  createembeddedcontentbean.setCode(IAPIConstantCodes.GOOGLEADDRESS);
				  createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
				  createembeddedcontentbean.setIsEnabled("false");
				  
				  JSONObject putembeddedcontentAPIresponseid=embeddedcontentAPI.Put_EmbeddedContentAPI_Id(loginapiresponse1,createembeddedcontentbean);
				  assertionapiresponse.verifyAssert_httpCode(putembeddedcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatus400);
				  customReport.customizedReport(HttpStatusCode.httpsStatus400,putembeddedcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
				  customReport.checkinglist(statusValue);
			}
		
		 @Test(description="To Verify and create a new webcast and in  embedded request body (put) editIcon as invalid  (Activate/Deactivate a Embed URL) ",groups ={PUTEmbeddedContentWebcast})
			public void TC09_ActivateDeactivate_EmbedURL_PUTEmbeddedContentWebcast_editIcon_AsInvalid_Negative_AV28828(ITestContext context) 
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
				   String eventid=createeventapirespone.get("eventId").toString();
				   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
				
				   //start webcast  using api  
				   GetEventStatusAPI starteventAPI=new GetEventStatusAPI();		
					 JSONObject  starteventapiresponse=starteventAPI.starteventjsonobject(loginapiresponse1);
					 assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
					 customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
					   
				   apibeanfactory.EmbeddedContentbean(createembeddedcontentbean);
				   createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
				   createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
				 
				  EmbeddedContentAPI embeddedcontentAPI=new EmbeddedContentAPI();
				  JSONObject embeddedcontentAPIresponse=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean);
				  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
				  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
				  loginapiresponse1.put(IAPIConstantCodes.SharedURLID,embeddedcontentAPIresponse.get("id"));
				  
				  //edit the icon
				  createembeddedcontentbean1.setName(createembeddedcontentbean.getName());
				  createembeddedcontentbean1.setCode(ICreateEmbeddedContentService.code);
				  createembeddedcontentbean1.setIcon(ICreateEmbeddedContentService.icon[0]);
				  createembeddedcontentbean1.setIsEnabled("false");
				  
				  JSONObject putembeddedcontentAPIresponseid=embeddedcontentAPI.Put_EmbeddedContentAPI_Id(loginapiresponse1,createembeddedcontentbean1);
				  assertionapiresponse.verifyAssert_httpCode(putembeddedcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatus400);
				  customReport.customizedReport(HttpStatusCode.httpsStatus400,putembeddedcontentAPIresponseid.get(IAPIConstantCodes.APIResponseStatusInfo).toString(),statusValue, driver, sTestcaseName);
				  customReport.checkinglist(statusValue);
			}
		

		 @Test(description="To Verify and create a new webcast and in embedded request body (put) edit multiple embedded content at any given time(Activate/Deactivate a Embed URL) ",groups ={PUTEmbeddedContentWebcast})
			public void TC10_ActivateDeactivate_EmbedURL_PUTEmbeddedContentWebcast_multipleembeddedcontent_edit_OneAstrueAndOneAsfalse_Positive_AV28828(ITestContext context) 
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
				   String eventid=createeventapirespone.get("eventId").toString();
				   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
				   
				 //start webcast  using api  
				   GetEventStatusAPI starteventAPI=new GetEventStatusAPI();		
					 JSONObject  starteventapiresponse=starteventAPI.starteventjsonobject(loginapiresponse1);
					 assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
					 customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
					   
				   EmbeddedContentAPI embeddedcontentAPI=new EmbeddedContentAPI();
				   int i;
				   for(i=0;i<2;i++) {
				   apibeanfactory.EmbeddedContentbean(createembeddedcontentbean);
				   createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
				   createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
				   
				  JSONObject embeddedcontentAPIresponse=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean);
				  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
				  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
				  loginapiresponse1.put(IAPIConstantCodes.SharedURLID,embeddedcontentAPIresponse.get("id"));
				   
				  //edit the icon
				  if(i==0)
				  {
					  createembeddedcontentbean.setName(createembeddedcontentbean.getName());
					  createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
					  createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
					  createembeddedcontentbean.setIsEnabled("false");
				  }
				  if(i==1)
				  {
					  createembeddedcontentbean.setName(createembeddedcontentbean.getName());
					  createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
					  createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
					  createembeddedcontentbean.setIsEnabled("true");
				  }
				  
				  JSONObject putembeddedcontentAPIresponseid=embeddedcontentAPI.Put_EmbeddedContentAPI_Id(loginapiresponse1,createembeddedcontentbean);
				  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
				  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
				  if(i==0)
				  {
					  assertionapiresponse.verifyAssert_httpCode("false",putembeddedcontentAPIresponseid.get("isEnabled").toString() );  
					  customReport.customizedReport("false",putembeddedcontentAPIresponseid.get("isEnabled").toString(),statusValue, driver, sTestcaseName);
				  }
				  if(i==1)
				  {
					  assertionapiresponse.verifyAssert_httpCode("true",putembeddedcontentAPIresponseid.get("isEnabled").toString() );  
					  customReport.customizedReport("true",putembeddedcontentAPIresponseid.get("isEnabled").toString(),statusValue, driver, sTestcaseName);
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
				        //avengereventwebcastpage.click_Eventdetailsbutton();
				        avengereventwebcastpage.verify_eventlabel(createEventBean.getTitle());
				        avengereventwebcastpage.click_Embeddedcontent();
				       ArrayList<String> label=avengereventwebcastpage.verify_embeddedlabel();
				       //asserting for 1 embedded content file
				        assertionapiresponse.verifyAssert_httpCode("false",label.get(0).toString());
				        customReport.customizedReport(label.get(0).toString(),"false",statusValue, driver, sTestcaseName);
				        //asserting for 2 embedded content file
				        assertionapiresponse.verifyAssert_httpCode("true",label.get(1).toString());
				        customReport.customizedReport(label.get(1).toString(),"true",statusValue, driver, sTestcaseName);  
				        customReport.checkinglist(statusValue);
				        
			}
		
		 @Test(description="To Verify and create a new webcast and in  embedded request body put is enabled as true and verify is enabled  ",groups ={PUTEmbeddedContentWebcast})
			public void TC011_ActivateDeactivate_EmbedURL_PUTEmbeddedContentWebcast_IsEnabledAsTrue_Positive_AV28828(ITestContext context) 
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
				   String eventid=createeventapirespone.get("eventId").toString();
				   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
				       
				   apibeanfactory.EmbeddedContentbean(createembeddedcontentbean);
				   createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
				   createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
				 
				  EmbeddedContentAPI embeddedcontentAPI=new EmbeddedContentAPI();
				  JSONObject embeddedcontentAPIresponse=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean);
				  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
				  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
				  loginapiresponse1.put(IAPIConstantCodes.SharedURLID,embeddedcontentAPIresponse.get("id"));
				  
				  
				  //start webcast  using api  
				   GetEventStatusAPI starteventAPI=new GetEventStatusAPI();		
				   JSONObject  starteventapiresponse=starteventAPI.starteventjsonobject(loginapiresponse1);
				   assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
				   customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
				  //set is enabled as true
				 
				  createembeddedcontentbean.setIsEnabled("true");
				  
				  ArrayList<String>  putembeddedcontentAPIresponsestatus=embeddedcontentAPI.PutEmbeddedContentAPI_Status(loginapiresponse1,createembeddedcontentbean);
				  assertionapiresponse.verifyAssert_httpCode(putembeddedcontentAPIresponsestatus.get(0).toString(), HttpStatusCode.httpsStatusCode200);
				  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,putembeddedcontentAPIresponsestatus.get(0).toString(),statusValue, driver, sTestcaseName);
				   
				   
				  
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
			        avengereventwebcastpage.click_Embeddedcontent();
			        assertionapiresponse.verifyAssert_httpCode(createembeddedcontentbean.getIsEnabled(),avengereventwebcastpage.verify_embeddedlabel().get(0).toString());
			        customReport.customizedReport(avengereventwebcastpage.verify_embeddedlabel().get(0).toString(),createembeddedcontentbean.getIsEnabled(),statusValue, driver, sTestcaseName);  
			        customReport.checkinglist(statusValue);
			  
			        
				  
			}
		 
		 @Test(description="To Verify and create a new webcast and in  embedded request body put is enabled as False and verify is enabled  ",groups ={PUTEmbeddedContentWebcast})
			public void TC12_ActivateDeactivate_EmbedURL_PUTEmbeddedContentWebcast_IsEnabledAsFalse_Positive_AV28828(ITestContext context) 
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
				   String eventid=createeventapirespone.get("eventId").toString();
				   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
				  
				    
				   apibeanfactory.EmbeddedContentbean(createembeddedcontentbean);
				   createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
				   createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
				 
				  EmbeddedContentAPI embeddedcontentAPI=new EmbeddedContentAPI();
				  JSONObject embeddedcontentAPIresponse=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean);
				  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
				  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
				  loginapiresponse1.put(IAPIConstantCodes.SharedURLID,embeddedcontentAPIresponse.get("id"));
				  
				  
				  //start webcast  using api  
			    GetEventStatusAPI starteventAPI=new GetEventStatusAPI();		
				JSONObject  starteventapiresponse=starteventAPI.starteventjsonobject(loginapiresponse1);
			    assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
				customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
					    
				  //set is enabled as false
				 
				  createembeddedcontentbean.setIsEnabled("false");
				  
				  ArrayList<String>  putembeddedcontentAPIresponsestatus=embeddedcontentAPI.PutEmbeddedContentAPI_Status(loginapiresponse1,createembeddedcontentbean);
				  assertionapiresponse.verifyAssert_httpCode(putembeddedcontentAPIresponsestatus.get(0).toString(), HttpStatusCode.httpsStatusCode200);
				  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,putembeddedcontentAPIresponsestatus.get(0).toString(),statusValue, driver, sTestcaseName);
				   
			 
				  
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
			        avengereventwebcastpage.click_Embeddedcontent();
			        assertionapiresponse.verifyAssert_httpCode(createembeddedcontentbean.getIsEnabled(),avengereventwebcastpage.verify_embeddedlabel().get(0).toString());
			        customReport.customizedReport(avengereventwebcastpage.verify_embeddedlabel().get(0).toString(),createembeddedcontentbean.getIsEnabled(),statusValue, driver, sTestcaseName);  
			        customReport.checkinglist(statusValue);
				  
			}
		 
		 @Test(description="To Verify and create a new webcast and in  embedded request body put is enabled as true or false without starting event ",groups ={PUTEmbeddedContentWebcast})
			public void TC013_ActivateDeactivate_EmbedURL_PUTEmbeddedContentWebcast_IsEnabledAsTrue_withoutstartingevent_Negative_AV28828(ITestContext context) 
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
				   String eventid=createeventapirespone.get("eventId").toString();
				   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
				   
				  
				   apibeanfactory.EmbeddedContentbean(createembeddedcontentbean);
				   createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
				   createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
				 
				  EmbeddedContentAPI embeddedcontentAPI=new EmbeddedContentAPI();
				  JSONObject embeddedcontentAPIresponse=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean);
				  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
				  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
				  loginapiresponse1.put(IAPIConstantCodes.SharedURLID,embeddedcontentAPIresponse.get("id"));
				  
				  //set is enabled as false
				 
				  createembeddedcontentbean.setIsEnabled("false");
				  
				  ArrayList<String>  putembeddedcontentAPIresponsestatus=embeddedcontentAPI.PutEmbeddedContentAPI_Status(loginapiresponse1,createembeddedcontentbean);
				  assertionapiresponse.verifyAssert_httpCode(putembeddedcontentAPIresponsestatus.get(0).toString(), HttpStatusCode.httpsStatus401);
				  customReport.customizedReport(HttpStatusCode.httpsStatus401,putembeddedcontentAPIresponsestatus.get(0).toString(),statusValue, driver, sTestcaseName);
				  customReport.checkinglist(statusValue);
				  
			}
		 
  @Test(description="To Verify and create a new webcast and in  embedded request body put is enabled as empty",groups ={PUTEmbeddedContentWebcast})
   public void TC014_ActivateDeactivate_EmbedURL_PUTEmbeddedContentWebcast_IsEnabled_Asempty_Negative_AV28828(ITestContext context) 
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
				   String eventid=createeventapirespone.get("eventId").toString();
				   loginapiresponse1.put(IAPIConstantCodes.APIEVENTID, createeventapirespone.get(IAPIConstantCodes.APIEVENTID));
				
				  //start webcast  using api  
				   GetEventStatusAPI starteventAPI=new GetEventStatusAPI();		
					 JSONObject  starteventapiresponse=starteventAPI.starteventjsonobject(loginapiresponse1);
					 assertionapiresponse.verifyAssert_httpCode(starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
					 customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, starteventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode).toString()+starteventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
					   
				   apibeanfactory.EmbeddedContentbean(createembeddedcontentbean);
				   createembeddedcontentbean.setCode(ICreateEmbeddedContentService.code);
				   createembeddedcontentbean.setIcon(ICreateEmbeddedContentService.icon[1]);
				 
				  EmbeddedContentAPI embeddedcontentAPI=new EmbeddedContentAPI();
				  JSONObject embeddedcontentAPIresponse=embeddedcontentAPI.PostEmbeddedContentAPI(loginapiresponse1,createembeddedcontentbean);
				  assertionapiresponse.verifyAssert_httpCode(embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString(), HttpStatusCode.httpsStatusCode200);  
				  customReport.customizedReport(HttpStatusCode.httpsStatusCode200,embeddedcontentAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo).toString() ,statusValue, driver, sTestcaseName);
				  loginapiresponse1.put(IAPIConstantCodes.SharedURLID,embeddedcontentAPIresponse.get("id"));
				  
				  //set is enabled as empty
				 
				  createembeddedcontentbean.setIsEnabled(" ");
				  
				  ArrayList<String>  putembeddedcontentAPIresponsestatus=embeddedcontentAPI.PutEmbeddedContentAPI_Status(loginapiresponse1,createembeddedcontentbean);
				  assertionapiresponse.verifyAssert_httpCode(putembeddedcontentAPIresponsestatus.get(0).toString(), HttpStatusCode.httpsStatus500);
				  customReport.customizedReport(HttpStatusCode.httpsStatus500,putembeddedcontentAPIresponsestatus.get(0).toString(),statusValue, driver, sTestcaseName);
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