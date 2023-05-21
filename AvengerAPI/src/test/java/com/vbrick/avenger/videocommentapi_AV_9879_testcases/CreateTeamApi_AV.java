package com.vbrick.avenger.videocommentapi_AV_9879_testcases;
 

import java.net.MalformedURLException;
import java.util.ArrayList;
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
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.ConstantsValue;
import com.vbricks.avenger.utils.HttpStatusCode;
public class CreateTeamApi_AV extends TestBase{

	private static Logger logger = Logger.getLogger(CreateTeamApi_AV.class);
	private AvengerLoginPage loginPage;
	private AvengerHomePage avengerHomePage;
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
	private AddTeamBean addteambean;
	public ApiBeanFactory apibeanfactory;
    public AddVideoCommentBean addvideocommentbean;
	private AddGroupBean addgroupbean;
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

	@Test(description="To Verify the CreateTeam using CreateTeam API with Account Admin with only mandatory field name",groups = {CREATETEAMAPI})
	public void TC_01_POST_CreateTeam_api_check_With_AccountAdmin_Positive(ITestContext context )  {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		addteambean.setTeamMembers(false);
		addteambean.setDescription("");
		JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengermediasettingpage= avengerHomePage.clickMediaSettingsLink();
        avengerlibrarypage =avengermediasettingpage.click_LibrariesLinkLocator();
        LibraryBeanPage librarybeanpage=new LibraryBeanPage();
        librarybeanpage.setLibraryname(addteambean.getName());
        customReport.customizedReport(true, avengerlibrarypage.verifyLibraryCreation(librarybeanpage).contains(librarybeanpage.getLibraryname()), statusValue, driver, sTestcaseName);  
        customReport.checkinglist(statusValue);
        
	}
	
	@Test(description="To Verify the CreateTeam using CreateTeam API with Media Admin with name, description and userids ",groups = {CREATETEAMAPI})
	public void TC_02_POST_CreateTeam_api_check_With_MediaAdmin_Positive(ITestContext context )  {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateTeammediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
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
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengermediasettingpage= avengerHomePage.clickMediaSettingsLink();
        avengerlibrarypage =avengermediasettingpage.click_LibrariesLinkLocator();
        LibraryBeanPage librarybeanpage=new LibraryBeanPage();
        librarybeanpage.setLibraryname(addteambean.getName());
        customReport.customizedReport(true, avengerlibrarypage.verifyLibraryCreation(librarybeanpage).contains(librarybeanpage.getLibraryname()), statusValue, driver, sTestcaseName);  
        customReport.checkinglist(statusValue);
	}
	
	@Test(description="To Verify the CreateTeam using CreateTeam API with Media Contributor and Media Viewer",groups = {CREATETEAMAPI})
	public void TC_03_POST_CreateTeam_api_check_With_MediaContributor_and_MediaViewer_Negative(ITestContext context )  {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateTeammediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateTeammediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);
		
		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		JSONObject creatteamjson1=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse1,creatteamjson1);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="To Verify the CreateTeam using CreateTeam API with Event Admin and event host",groups = {CREATETEAMAPI})
	public void TC_04_POST_CreateTeam_api_check_With_EventAdmin_and_EventHost_Negative(ITestContext context )  {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateTeameventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);

		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		JSONObject creatteamjson1=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse1,creatteamjson1);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description="To Verify the CreateTeam without giving mandatory field team name with Account Admin ",groups = {CREATETEAMAPI})
	public void TC_05_POST_CreateTeam_api_withoutgiving_mandatory_teamname_with_AccountAdmin_Negative(ITestContext context )  {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API responsek Code :::" + loginapiresponse);

		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		addteambean.setDescription("API_Team_Description"+RandomStringUtils.randomAlphanumeric(6));
	    addteambean.setName("");
				
		JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatus500);
		customReport.customizedReport(HttpStatusCode.httpsStatus500, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
	}
	
	@Test(description="To Verify the CreateTeam using CreateTeam API with Account Admin ",groups = {CREATETEAMAPI})
	public void TC_06_POST_CreateTeam_With_AllFields_api_check_With_AccountAdmin_Positive(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		String[] userIds = {loginapiresponse.get("userId")};
		addteambean.setUserids(userIds);
		addteambean.setTeammemberasuser(userIds[0]);
			
		//GroupIds UI
		String groupids[]={create_groupfromUI()};
		addteambean.setGroupids(groupids);
		addteambean.setTeammemberasgroup(groupids[0]);		
		addteambean.setTeamMembers(true);
				
		JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengermediasettingpage= avengerHomePage.clickMediaSettingsLink();
        avengerlibrarypage =avengermediasettingpage.click_LibrariesLinkLocator();
        LibraryBeanPage librarybeanpage=new LibraryBeanPage();
        librarybeanpage.setLibraryname(addteambean.getName());
        customReport.customizedReport(true, avengerlibrarypage.verifyLibraryCreation(librarybeanpage).contains(librarybeanpage.getLibraryname()), statusValue, driver, sTestcaseName);  
        customReport.checkinglist(statusValue);
	}
	
	@Test(description="To Verify the CreateTeam using CreateTeam API with Account Admin with all the fields and with multiple team members ",groups = {CREATETEAMAPI})
	public void TC_07_POST_CreateTeam_With_AllFields_api_check_With_AccountAdmin_withmultipletammembers_Positive(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);
		
		loginapiresponse2 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse2.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse2.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse2);
		
		loginapiresponse3 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse3.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse3.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse3);	
	
		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		String[] userIds = {loginapiresponse.get("userId"),loginapiresponse1.get("userId"),loginapiresponse2.get("userId"),loginapiresponse3.get("userId")};
		addteambean.setUserids(userIds);
		addteambean.setTeammemberasuser(userIds[0]);		
			
		//GroupIds UI
		String groupids[]={create_groupfromUI()};
		addteambean.setGroupids(groupids);
		addteambean.setTeammemberasgroup(groupids[0]);				
		addteambean.setTeamMembers(true);
				
		JSONObject creatteamjson=createteamAPI.createTeamJson1(addteambean,userIds);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengermediasettingpage= avengerHomePage.clickMediaSettingsLink();
        avengerlibrarypage =avengermediasettingpage.click_LibrariesLinkLocator();
        LibraryBeanPage librarybeanpage=new LibraryBeanPage();
        librarybeanpage.setLibraryname(addteambean.getName());
        customReport.customizedReport(true, avengerlibrarypage.verifyLibraryCreation(librarybeanpage).contains(librarybeanpage.getLibraryname()), statusValue, driver, sTestcaseName);  
        customReport.checkinglist(statusValue);
	}
	
	@Test(description="To Verify the CreateTeam by giving existing name to the team name with Account Admin ",groups = {CREATETEAMAPI})
	public void TC_08_POST_CreateTeam_api_giving_existing_teamname_with_AccountAdmin_Negative(ITestContext context )  {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API responsek Code :::" + loginapiresponse);
	
		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		addteambean.setTeamMembers(false);
		addteambean.setDescription("");
		
		JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	    
		addteambean.setName(addteambean.getName());
		JSONObject creatteamjson1=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson1);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatus500);
		customReport.customizedReport(HttpStatusCode.httpsStatus500, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.customizedReport(createteamapirespone.get(IAPIConstantCodes.APIResponseJsonErrorCode), HttpStatusCode.code, statusValue, driver, sTestcaseName);
		customReport.customizedReport(createteamapirespone.get(IAPIConstantCodes.APIResponseJsonErrorDetail), HttpStatusCode.detail, statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
	}
	
	@Test(description="To Verify that Media Viewer is able to CreateTeam when MV is added to a group having Media Admin access",groups = {CREATETEAMAPI})
	public void TC_09_POST_CreateTeam_api_check_With_MediaViewer_whenaddedto_grouphaving_MediaAdminaccess_Positive(ITestContext context )  {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
				
		//assigning mediaviewer to a group having access
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateTeammediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);

		//getting the userIds
		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		String[] userIds = {loginapiresponse1.get("userId")};
		addgroupbean.setUserids(userIds);
						
		//getting roleids
		UserServices userServices=new UserServices();
	    HashMap<String, String> userroleapirespone = userServices.getRolesApi(loginapiresponse,IAPIConstantCodes.MEDIAADMIN);
	    String roleid=apiutils.formatingapiresponse(userroleapirespone.get(IAPIConstantCodes.ROLEID));
	    String[] roleIds = {userroleapirespone.get("roleId")};
		addgroupbean.setRoleids(roleIds);
				
		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
		creategroupapirespone = creategroupAPI.createGroup(loginapiresponse,creategroupjson);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);

		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		addteambean.setTeamMembers(true);				
		addteambean.setUserids(userIds);
		addteambean.setTeammemberasuser(userIds[0]);
		addteambean.setTeammemberasgroup("");
		
		JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Removing the assigned user mediacontributor1 from MediaAdmin group
	    addgroupbean.setName("APIEDITName"+apiutils.randomString(5));
	    addgroupbean.setUserids(null);
	    addgroupbean.setRoleids(null);
   	    EditGroupAPI editGroupAPI = new EditGroupAPI();
		loginapiresponse.put("groupId", creategroupapirespone.get(IAPIConstantCodes.APIGROUPID));
		JSONObject creategroupjsonedit=creategroupAPI.createGroupJson(addgroupbean);

		editGroupapiresponse = editGroupAPI.editGroup(loginapiresponse, creategroupjsonedit);
		assertionapiresponse.verifyAssert_httpCode(
		editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + editGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),
		HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + editGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengermediasettingpage= avengerHomePage.clickMediaSettingsLink();
        avengerlibrarypage =avengermediasettingpage.click_LibrariesLinkLocator();
        LibraryBeanPage librarybeanpage=new LibraryBeanPage();
        librarybeanpage.setLibraryname(addteambean.getName());
        customReport.customizedReport(true, avengerlibrarypage.verifyLibraryCreation(librarybeanpage).contains(librarybeanpage.getLibraryname()), statusValue, driver, sTestcaseName);  
        customReport.checkinglist(statusValue);

	}

	@Test(description="To Verify  CreateTeam with Account Admin by giving few valid and few invalid users and groups ",groups = {CREATETEAMAPI})
	public void TC_10_POST_CreateTeam_With_AccountAdmin_givingfewvalidandfewinvalid_usersandgroups_Positive(ITestContext context ) {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);
		
		loginapiresponse2 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse2.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse2.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse2);
		
		loginapiresponse3 = userservices.doLogin(apiutils.userJson(IUsersList.CreateEventeventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse3.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse3.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse3);		
	
		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		String[] userIds = {loginapiresponse.get("userId"),loginapiresponse1.get("userId"),loginapiresponse2.get("userId"),loginapiresponse3.get("userId")};
		addteambean.setUserids(userIds);
		addteambean.setTeammemberasuser(userIds[0]);
					
		//GroupIds UI
		String groupids[]={create_groupfromUI()};
		addteambean.setGroupids(groupids);
		addteambean.setTeammemberasgroup(groupids[0]+"invalid");				
		addteambean.setTeamMembers(true);
				
		JSONObject creatteamjson=createteamAPI.createTeamJson1(addteambean,userIds);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatus500);
		customReport.customizedReport(HttpStatusCode.httpsStatus500, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.customizedReport(HttpStatusCode.missingCode,createteamapirespone.get(IAPIConstantCodes.APIResponseJsonErrorCode) , statusValue, driver, sTestcaseName);
		customReport.customizedReport(HttpStatusCode.detail2,createteamapirespone.get(IAPIConstantCodes.APIResponseJsonErrorDetail) , statusValue, driver, sTestcaseName);		
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
		avengerHomePage = loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengergroupspage=avengerHomePage.clickGroupsLink();
	    String grpname=avengergroupspage.createNewgroup(avengergroupsbeanpage);
		avengergroupsbeanpage.setNewgroup(grpname);
		avengergroupspage.clickNewGroup(avengergroupsbeanpage);
		String url=avengerHomePage.getCurrentURL();
		logger.info("url.split -"+url.split("/")[8]);
	 	//browserQuit();
		avengerHomePage.click_logout();
		return url.split("/")[8];
	}

}