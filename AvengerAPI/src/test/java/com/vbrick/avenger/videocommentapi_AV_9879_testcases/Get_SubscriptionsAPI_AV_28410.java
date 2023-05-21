package com.vbrick.avenger.videocommentapi_AV_9879_testcases;
 

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
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
import com.vbrick.avenger.apibeans.AddTeamBean;
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbrick.avenger.apibeans.AddVideoCommentBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AvengerGroupsBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.LibraryBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerCategoriesPage;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerGroupsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLibrariesPage;
import com.vbrick.avenger.pageobjects.AvengerFeaturesPage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerMediaSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.ICreateCategoryAPI;
import com.vbricks.avenger.service.IEditVideoRatingService;
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUserAccountsService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.CreateCategoryAPI;
import com.vbricks.avenger.serviceimpl.CreateGroupAPI;
import com.vbricks.avenger.serviceimpl.CreateTeamAPI;
import com.vbricks.avenger.serviceimpl.EditGroupAPI;
import com.vbricks.avenger.serviceimpl.EditVideoRatingAPI;
import com.vbricks.avenger.serviceimpl.SubscriptionsAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.ConstantsValue;
import com.vbricks.avenger.utils.HttpStatusCode;
public class Get_SubscriptionsAPI_AV_28410 extends TestBase{

	private static Logger logger = Logger.getLogger(Get_SubscriptionsAPI_AV_28410.class);
	private AvengerLoginPage loginPage;
	private AvengerHomePage avengerHomePage;
	private List<String> statusValue;
	private CustomReport customReport;
	@SuppressWarnings("unused")
	private Reasons reasons;
	private String sTestcaseName;
	private AddCategoryBean addcategorybean;
	private HashMap<String, String> createcategoryapirespone;
	private ResourceBundle bundle;
	private Locale locale;
	private BeanFactory accountBeansFactory;
	private MailinatorBeanPage mailinatorbeanpage;
	private ReadAndWriteToJSON readgriduserdata;
	private Map<String, String> userdata;
	private BasePage basePage;
	private AddTeamBean addteambean;
	public ApiBeanFactory apibeanfactory;
    public AddVideoCommentBean addvideocommentbean;
	private AddGroupBean addgroupbean;
	private AvengerHomePage homePage;
	private HashMap<String, String> loginapiresponse;
	private HashMap<String,String >editGroupapiresponse;
	private HashMap<String, String> loginapiresponse1;
	private HashMap<String, String> loginapiresponse2;
	private HashMap<String, String> loginapiresponse3;
	private HashMap<String, String> uploadvidoeapiresponse;
	private HashMap<String, String> creategroupapirespone;
	 private AssertionAPIResponse assertionapiresponse;
	 private HashMap<String, String> createteamapirespone;
	 private HashMap<String, String> childcategoryapirespone;
	 private ApiUtils apiutils; 
	 private AvengerMediaSettingsPage avengermediasettingpage;
	 private AvengerLibrariesPage avengerlibrarypage;
	 private AvengerGroupsPage avengergroupspage;
	private AvengerGroupsBeanPage avengergroupsbeanpage;
 	@BeforeClass(alwaysRun = true)
	public void beforeClass() throws Exception {
		reasons = new Reasons("");
		statusValue = new ArrayList<String>();
		customReport = new CustomReport();
		accountBeansFactory = new BeanFactory();
		mailinatorbeanpage = new MailinatorBeanPage();
		new FileUploadBeanPage();
		locale = new Locale(language);
		addgroupbean=new AddGroupBean();
		readgriduserdata = new ReadAndWriteToJSON();
		userdata = readgriduserdata.readGridUserData(this.getClass().getSimpleName()); // ("user52");
		sUserName = userdata.get("Username");
		sPassword = userdata.get("Password");
		apibeanfactory = new ApiBeanFactory();
		addcategorybean=new AddCategoryBean();
		addteambean=new AddTeamBean();
		assertionapiresponse =new AssertionAPIResponse();
		apiutils=new ApiUtils();
		avengergroupsbeanpage = new AvengerGroupsBeanPage();
		 
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

	}
	@Test(description="To Verify GET Subscription API with Account Admin when subscribed only to a channel",groups = {GETSUBSCRIPTIONS})
	public void TC_01_GET_Subscriptions_api_check_With_AccontAdmin_Positive(ITestContext context ) throws Throwable  {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		addteambean.setTeamMembers(true);
				
		String[] userIds = {loginapiresponse.get("userId")};
		addteambean.setUserids(userIds);
		addteambean.setTeammemberasuser(userIds[0]);
		addteambean.setTeammemberasgroup("");
		
		JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		// Subscribing a Channel
		SubscriptionsAPI SubscriptionsAPI = new SubscriptionsAPI();
		HashMap<String, String> subscribingapiresponse = SubscriptionsAPI.subscribingtoaChannel(loginapiresponse, SubscriptionsAPI.subscribingaChannel(createteamapirespone.get("channelId"), "Channel"));
		assertionapiresponse.verifyAssert_httpCode(subscribingapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, subscribingapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
	
		//Get Subscriptions 
		HashMap<String, String> Getsubscriptionsapiresponse = SubscriptionsAPI.getSubscriptions(loginapiresponse);
		logger.info("Channels List :::::" + Getsubscriptionsapiresponse );
		assertionapiresponse.verifyAssert_httpCode(Getsubscriptionsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, Getsubscriptionsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
		String fromattedJSONforuser=apiutils.formatingapiresponse(Getsubscriptionsapiresponse.get(IAPIConstantCodes.APIResponseJSON));
        ArrayList<String> subscriptionslistforchannels = new ArrayList<String>(Arrays.asList(fromattedJSONforuser.split(",")));
        Collections.sort(subscriptionslistforchannels);
        int APICountofchannels = subscriptionslistforchannels.size();
        
        String fromattedJSONforuser1=apiutils.formatingapiresponse(Getsubscriptionsapiresponse.get(IAPIConstantCodes.APIResponseJSON1));
        ArrayList<String> subscriptionslistforcategories = new ArrayList<String>(Arrays.asList(fromattedJSONforuser1.split(",")));
        Collections.sort(subscriptionslistforcategories);
        int APICountofcategories = subscriptionslistforcategories.size();
        int TotalAPICount = APICountofchannels + APICountofcategories;
        
        assertionapiresponse.verifyAssert_httpCode(Getsubscriptionsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200, Getsubscriptionsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver, sTestcaseName);
       
        logger.info("Selenium Code is excuting");
        launchURL(surl);
        customReport.reporter("Application launch with URL : ", surl);
        homePage=loginPage.loginAs(sUserName,sPassword);
        homePage.clicksubscriptions();
        homePage.clickmanagesubscriptions();
        homePage.clickchannels();
        int UIcountofchannels=homePage.countofsubscribedchannels();
        homePage.clickcategories();
        int UIcountofcategories=homePage.countofsubscribedcategories();
        int TotalUIcount = UIcountofchannels + UIcountofcategories;       
        customReport.customizedReport(APICountofchannels, UIcountofchannels, statusValue, driver, sTestcaseName);
        customReport.customizedReport(APICountofcategories, UIcountofcategories, statusValue, driver, sTestcaseName);
        customReport.customizedReport(TotalAPICount, TotalUIcount, statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);    
	
	}
	
	@Test(description="To Verify GET Subscription API with Account Admin when there are NO Subscriptions ",groups = {GETSUBSCRIPTIONS}) //According to the story, the response should be 200OK with an empty string
	public void TC_02_GET_Subscriptions_api_check_With_AccontAdmin_with_NO_Subscriptions(ITestContext context ) throws Throwable  {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.GETSUBSCRIPTIONS_MediaViewer), surl);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		//Get Subscriptions 
		SubscriptionsAPI SubscriptionsAPI = new SubscriptionsAPI();
		HashMap<String, String> Getsubscriptionsapiresponse = SubscriptionsAPI.getSubscriptions(loginapiresponse);
		logger.info("Channels List :::::" + Getsubscriptionsapiresponse );
		assertionapiresponse.verifyAssert_httpCode(Getsubscriptionsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, Getsubscriptionsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
		String fromattedJSONforuser=apiutils.formatingapiresponse(Getsubscriptionsapiresponse.get(IAPIConstantCodes.APIResponseJSON));
        ArrayList<String> subscriptionslistforchannels = new ArrayList<String>(Arrays.asList(fromattedJSONforuser.split(",")));
        Collections.sort(subscriptionslistforchannels);
        String dataforchannels=subscriptionslistforchannels.get(0);
        int APICountofchannels=0;
        if(dataforchannels.equals("")) {
        System.out.println("NO Data Available in channels");
        }
        else {
        	 APICountofchannels=subscriptionslistforchannels.size();
        }
        
        String fromattedJSONforuser1=apiutils.formatingapiresponse(Getsubscriptionsapiresponse.get(IAPIConstantCodes.APIResponseJSON1));
        ArrayList<String> subscriptionslistforcategories = new ArrayList<String>(Arrays.asList(fromattedJSONforuser1.split(",")));
        Collections.sort(subscriptionslistforcategories);
        String dataforcategories =subscriptionslistforcategories.get(0);
        int APICountofcategories = 0;
        if(dataforcategories.equals("")) {
            System.out.println("NO Data Available in categories");
            }
        else {
        	  APICountofcategories = subscriptionslistforcategories.size();
        }
         
        int TotalAPICount =  APICountofchannels + APICountofcategories;

        assertionapiresponse.verifyAssert_httpCode(Getsubscriptionsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200, Getsubscriptionsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver, sTestcaseName);
        System.out.println("Count of categories subscriptions::"+APICountofchannels);
        
        //Validation through UI
        logger.info("Selenium Code is excuting");
        launchURL(surl);
        customReport.reporter("Application launch with URL : ", surl);
        homePage=loginPage.loginAs(IUsersList.GETSUBSCRIPTIONS_MediaViewer,sPassword);
        homePage.clicksubscriptions();
        homePage.clickmanagesubscriptions();
        homePage.clickchannels();
        int UIcountofchannels=homePage.nocountofsubscribedchannels();
        homePage.clickcategories();
        int UIcountofcategories=homePage.nocountofsubscribedcategories();
        int TotalUIcount = UIcountofchannels + UIcountofcategories;
        customReport.customizedReport(APICountofchannels, UIcountofchannels, statusValue, driver, sTestcaseName);
        customReport.customizedReport(APICountofcategories, UIcountofcategories, statusValue, driver, sTestcaseName);
        customReport.customizedReport(TotalAPICount, TotalUIcount, statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);    
	
	}
	
	@Test(description="To Verify GET Subscription API without Authentication",groups = {GETSUBSCRIPTIONS})
	public void TC_03_GET_Subscriptions_api_check_When_Not_Authenticated_NEGATIVE(ITestContext context ) throws Throwable  {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);

		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		addteambean.setTeamMembers(true);
				
		String[] userIds = {loginapiresponse.get("userId")};
		addteambean.setUserids(userIds);
		addteambean.setTeammemberasuser(userIds[0]);
		addteambean.setTeammemberasgroup("");
		
		JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		// Subscribing to a Channel
		SubscriptionsAPI SubscriptionsAPI = new SubscriptionsAPI();
		HashMap<String, String> subscribingapiresponse = SubscriptionsAPI.subscribingtoaChannel(loginapiresponse, SubscriptionsAPI.subscribingaChannel(createteamapirespone.get("channelId"), "Channel"));
		assertionapiresponse.verifyAssert_httpCode(subscribingapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, subscribingapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Get Subscriptions 
		HashMap<String, String> Getsubscriptionsapiresponse = SubscriptionsAPI.getSubscriptionswithoutAuthorization(loginapiresponse);
		logger.info("Channels List :::::" + Getsubscriptionsapiresponse );
		assertionapiresponse.verifyAssert_httpCode(Getsubscriptionsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, Getsubscriptionsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	
	}
	
	/*@Test(description="To Verify GET Subscription API when Subscriptions are turned off at Account Level",groups = {GETSUBSCRIPTIONS})
	public void TC_04_GET_Subscriptions_api_check_When_Subscriptions_are_turnedoff_NEGATIVE(ITestContext context ) throws Throwable  {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);

		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		addteambean.setTeamMembers(true);
				
		String[] userIds = {loginapiresponse.get("userId")};
		addteambean.setUserids(userIds);
		addteambean.setTeammemberasuser(userIds[0]);
		addteambean.setTeammemberasgroup("");
		
		JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		// Subscribing to a Channel
		SubscriptionsAPI SubscriptionsAPI = new SubscriptionsAPI();
		HashMap<String, String> subscribingapiresponse = SubscriptionsAPI.subscribingtoaChannel(loginapiresponse, SubscriptionsAPI.subscribingaChannel(createteamapirespone.get("channelId"), "Channel"));
		assertionapiresponse.verifyAssert_httpCode(subscribingapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, subscribingapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		// Disabling Subscriptions on account, (Media Settings > Features > Allow users to subscribe to channels and categories = Uncheck)
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		homePage.clickSettingsLink();
		AvengerMediaSettingsPage avengermediasettingpage=homePage.clickMediaSettingsLink();
		AvengerFeaturesPage avengerfeaturespage=avengermediasettingpage.click_FeaturesLinkLocator();
		avengerfeaturespage.enableSubscriptions();
				
		//Get Subscriptions 
		HashMap<String, String> Getsubscriptionsapiresponse = SubscriptionsAPI.getSubscriptionswithoutAuthorization(loginapiresponse);
		logger.info("Channels List :::::" + Getsubscriptionsapiresponse );
		assertionapiresponse.verifyAssert_httpCode(Getsubscriptionsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, Getsubscriptionsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	
	}*/
	
	@Test(description="To Verify GET Subscription API with Account Admin when subscribed only to a category",groups = {GETSUBSCRIPTIONS})
	public void TC_05_GET_Subscriptions_api_check_With_AccontAdmin_Positive(ITestContext context ) throws Throwable  {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);

		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		// Subscribing a Category
		SubscriptionsAPI SubscriptionsAPI = new SubscriptionsAPI();
		HashMap<String, String> subscribingapiresponse = SubscriptionsAPI.subscribingtoaChannel(loginapiresponse, SubscriptionsAPI.subscribingaChannel(createcategoryapirespone.get("categoryId"), "Category"));
		assertionapiresponse.verifyAssert_httpCode(subscribingapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, subscribingapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Get Subscriptions 
		HashMap<String, String> Getsubscriptionsapiresponse = SubscriptionsAPI.getSubscriptions(loginapiresponse);
		logger.info("Channels List :::::" + Getsubscriptionsapiresponse );
		assertionapiresponse.verifyAssert_httpCode(Getsubscriptionsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, Getsubscriptionsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	

		String fromattedJSONforuser=apiutils.formatingapiresponse(Getsubscriptionsapiresponse.get(IAPIConstantCodes.APIResponseJSON));
        ArrayList<String> subscriptionslistforchannels = new ArrayList<String>(Arrays.asList(fromattedJSONforuser.split(",")));
        Collections.sort(subscriptionslistforchannels);
        int APICountofchannels = subscriptionslistforchannels.size();
        
        String fromattedJSONforuser1=apiutils.formatingapiresponse(Getsubscriptionsapiresponse.get(IAPIConstantCodes.APIResponseJSON1));
        ArrayList<String> subscriptionslistforcategories = new ArrayList<String>(Arrays.asList(fromattedJSONforuser1.split(",")));
        Collections.sort(subscriptionslistforcategories);
        int APICountofcategories = subscriptionslistforcategories.size();
        int TotalAPICount = APICountofchannels + APICountofcategories;
        
        assertionapiresponse.verifyAssert_httpCode(Getsubscriptionsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200, Getsubscriptionsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver, sTestcaseName);
        
        logger.info("Selenium Code is excuting");
        launchURL(surl);
        customReport.reporter("Application launch with URL : ", surl);
        homePage=loginPage.loginAs(sUserName,sPassword);
        homePage.clicksubscriptions();
        homePage.clickmanagesubscriptions();
        homePage.clickchannels();
        int UIcountofchannels=homePage.countofsubscribedchannels();
        homePage.clickcategories();
        int UIcountofcategories=homePage.countofsubscribedcategories();
        int TotalUIcount = UIcountofchannels + UIcountofcategories;
        customReport.customizedReport(APICountofchannels, UIcountofchannels, statusValue, driver, sTestcaseName);
        customReport.customizedReport(APICountofcategories, UIcountofcategories, statusValue, driver, sTestcaseName);
        customReport.customizedReport(TotalAPICount, TotalUIcount, statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);    
	
	
	}
	
	@Test(description="To Verify GET Subscription API with Account Admin when subscribed both channel & category",groups = {GETSUBSCRIPTIONS})
	public void TC_06_GET_Subscriptions_api_check_With_AccontAdmin_Positive(ITestContext context ) throws Throwable  {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		addteambean.setTeamMembers(true);
				
		String[] userIds = {loginapiresponse.get("userId")};
		addteambean.setUserids(userIds);
		addteambean.setTeammemberasuser(userIds[0]);
		addteambean.setTeammemberasgroup("");
		
		JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		// Subscribing a Channel
		SubscriptionsAPI SubscriptionsAPI = new SubscriptionsAPI();
		HashMap<String, String> subscribingapiresponse = SubscriptionsAPI.subscribingtoaChannel(loginapiresponse, SubscriptionsAPI.subscribingaChannel(createteamapirespone.get("channelId"), "Channel"));
		assertionapiresponse.verifyAssert_httpCode(subscribingapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, subscribingapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	
		CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		// Subscribing a Category
		HashMap<String, String> subscribingapiresponse1 = SubscriptionsAPI.subscribingtoaChannel(loginapiresponse, SubscriptionsAPI.subscribingaChannel(createcategoryapirespone.get("categoryId"), "Category"));
		assertionapiresponse.verifyAssert_httpCode(subscribingapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, subscribingapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Get Subscriptions 
		HashMap<String, String> Getsubscriptionsapiresponse = SubscriptionsAPI.getSubscriptions(loginapiresponse);
		logger.info("List :::::" + Getsubscriptionsapiresponse );
		assertionapiresponse.verifyAssert_httpCode(Getsubscriptionsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, Getsubscriptionsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	

		String fromattedJSONforuser=apiutils.formatingapiresponse(Getsubscriptionsapiresponse.get(IAPIConstantCodes.APIResponseJSON));
        ArrayList<String> subscriptionslistforchannels = new ArrayList<String>(Arrays.asList(fromattedJSONforuser.split(",")));
        Collections.sort(subscriptionslistforchannels);
        int APICountofchannels = subscriptionslistforchannels.size();
        System.out.println("count of channels API::"+APICountofchannels);
        
        String fromattedJSONforuser1=apiutils.formatingapiresponse(Getsubscriptionsapiresponse.get(IAPIConstantCodes.APIResponseJSON1));
        ArrayList<String> subscriptionslistforcategories = new ArrayList<String>(Arrays.asList(fromattedJSONforuser1.split(",")));
        Collections.sort(subscriptionslistforcategories);
        int APICountofcategories = subscriptionslistforcategories.size();
        System.out.println("count of categories API::"+APICountofcategories);
        int TotalAPICount = APICountofchannels + APICountofcategories;
        
        assertionapiresponse.verifyAssert_httpCode(Getsubscriptionsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200, Getsubscriptionsapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver, sTestcaseName);
        System.out.println("Count of categories subscriptions::"+APICountofchannels);
        
        logger.info("Selenium Code is excuting");
        launchURL(surl);
        customReport.reporter("Application launch with URL : ", surl);
        homePage=loginPage.loginAs(sUserName,sPassword);
        homePage.clicksubscriptions();
        homePage.clickmanagesubscriptions();
        homePage.clickchannels();
        int UIcountofchannels=homePage.countofsubscribedchannels();
        System.out.println("count of channels UI::"+UIcountofchannels);
        homePage.clickcategories();
        int UIcountofcategories=homePage.countofsubscribedcategories();
        System.out.println("count of categories UI::"+UIcountofcategories);
        int TotalUIcount = UIcountofchannels + UIcountofcategories;
        customReport.customizedReport(APICountofchannels, UIcountofchannels, statusValue, driver, sTestcaseName);
        customReport.customizedReport(APICountofcategories, UIcountofcategories, statusValue, driver, sTestcaseName);
        customReport.customizedReport(TotalAPICount, TotalUIcount, statusValue, driver, sTestcaseName);
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
		browserQuit(driver);
	}
}