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
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AddCustomDeviceBeanPage;
import com.vbrick.avenger.dao.AddPresentationprofileBeanPage;
import com.vbrick.avenger.dao.AvengerGroupsBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerAddCustomDevicePage;
import com.vbrick.avenger.pageobjects.AvengerAddPresentationProfilePage;
import com.vbrick.avenger.pageobjects.AvengerDevicesPage;
import com.vbrick.avenger.pageobjects.AvengerEventDetailsPage;
import com.vbrick.avenger.pageobjects.AvengerGroupsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLibrariesPage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerMediaSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerPresentationProfilesPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.CreateGroupAPI;
import com.vbricks.avenger.serviceimpl.CreateTeamAPI;
import com.vbricks.avenger.serviceimpl.EditGroupAPI;
import com.vbricks.avenger.serviceimpl.TeamListAPI;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;
public class GetTeamsApi_AV_ extends TestBase{

	private static Logger logger = Logger.getLogger(GetTeamsApi_AV_.class);
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
	private AddTeamBean addteambean;
	private ReadAndWriteToJSON readgriduserdata;
	private Map<String, String> userdata;
	private BasePage basePage;
	private AddUploadVideoBean adduploadvideobean;
	public ApiBeanFactory apibeanfactory;
    public AddVideoCommentBean addvideocommentbean;
	private HashMap<String, String> loginapiresponse;
	private HashMap<String, String> loginapiresponse1;
	private HashMap<String, String> uploadvidoeapiresponse;
	private AddGroupBean addgroupbean;
	private HashMap<String, String> createteamapirespone;
	private HashMap<String, String> loginapiresponseeventadmin;
	private HashMap<String, String> deleteTeamapiresponse;
	private HashMap<String, String> deleteGroupapiresponse;
	private HashMap<String, String> editTeamapiresponse;
	private AssertionAPIResponse assertionapiresponse;
	private AvengerGroupsPage avengergroupspage;
	private AvengerGroupsBeanPage avengergroupsbeanpage;
	private HashMap<String, String> createcategoryapirespone;
	private HashMap<String, String> childcategoryapirespone;
	private AvengerDevicesPage avengerdevicespage;
	private AvengerEventDetailsPage avengereventdetailspage;
	private AvengerPresentationProfilesPage avengerpresentationprofilespage;
	private HashMap<String, String> editGroupapirespone;
	private AvengerAddCustomDevicePage avengeraddcustomdevicepage;
	private AvengerAddPresentationProfilePage avengeraddpresentationprofilepage;
	private AddPresentationprofileBeanPage addpresentationprofilebeanpage;
    private HashMap<String, String> teamlistapirespone;
	private ApiUtils apiutils; 
	private AddCustomDeviceBeanPage addcustomdevicebeanpage;
	private HashMap<String, String> creategroupapirespone;
	private AvengerHomePage avengerHomePage;
	private AvengerMediaSettingsPage avengermediasettingpage;
	private AvengerLibrariesPage avengerlibrarypage;
	private CreateEventBean createEventBean;
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
		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		addgroupbean=new AddGroupBean();
		addteambean=new AddTeamBean();
		addvideocommentbean=new AddVideoCommentBean();
		assertionapiresponse =new AssertionAPIResponse();
		apiutils=new ApiUtils();
		avengergroupsbeanpage = new AvengerGroupsBeanPage();
	 
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

	}

	@Test(description="To Verify the TeamList using TeamList API with Account Admin by giving team members list and making user as team admin",groups = {GETTEAMLISTAPI})
	public void TC_01_GET_TeamList_api_check_With_AccountAdmin_givingteammembers_userasteamadmin_Positive(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.GetTeamsmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
				
		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		String userIds[] = {loginapiresponse.get("userId")};
		addteambean.setTeammemberasuser(userIds[0]);
			
		//GroupIds UI
		String groupids[]={create_groupfromUI()};
		
		//addteambean.setGroupids(groupids);
		addteambean.setTeammemberasgroup(groupids[0]);		
		addteambean.setTeamMembers(true);
		
		JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		loginapiresponse.put("teamname",addteambean.getName());
        loginapiresponse.put("size", "1000"); 
               
     	TeamListAPI teamListAPI=new TeamListAPI();
		teamlistapirespone = teamListAPI.getTeamList(loginapiresponse);
		List<String> teamlistfromJson=apiutils.jsonArrayParse(teamlistapirespone.get("jsonteamResponse"));
		assertionapiresponse.verifyAssert_httpCode(teamlistapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+teamlistapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, teamlistapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+teamlistapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		   
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengermediasettingpage= avengerHomePage.clickMediaSettingsLink();
        avengerlibrarypage =avengermediasettingpage.click_LibrariesLinkLocator();
        ArrayList<String> myList2 =avengerlibrarypage.get_allTeamList();	
        customReport.customizedReport(true,apiutils.containsAll(teamlistfromJson,myList2), statusValue, driver, sTestcaseName);
		System.out.println("UIandAPIteamsizesimultaneously--"+myList2.size()+"-------------"+teamlistfromJson.size());
		customReport.customizedReport("2",teamlistapirespone.get("TeamMembersSize"), statusValue, driver, sTestcaseName);
		logger.info("Created Team name is " +addteambean.getName());
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description="To Verify the TeamList using TeamList API with Media Admin by giving team members list and making group as team admin",groups = {GETTEAMLISTAPI})
	public void TC_02_GET_TeamList_api_check_With_MediaAdmin_givingteammembers_groupasteamadmin_Positive(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.GetTeamsmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
								
		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		String userIds[] = {loginapiresponse.get("userId")};
		addteambean.setUserids(userIds);
		addteambean.setTeammemberasuser(userIds[0]);		
			
		//GroupIds UI
		String groupids[]={create_groupfromUI()};
		addteambean.setGroupids(groupids);
		addteambean.setTeammemberasgroup(groupids[0]);		
		addteambean.setTeamMembers(true);
		
		JSONObject creatteamjson=createteamAPI.createTeamJson2(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		loginapiresponse.put("teamname",addteambean.getName());
        loginapiresponse.put("size", "1000"); 
                
     	TeamListAPI teamListAPI=new TeamListAPI();
		teamlistapirespone = teamListAPI.getTeamList(loginapiresponse);
		List<String> teamlistfromJson=apiutils.jsonArrayParse(teamlistapirespone.get("jsonteamResponse"));
		assertionapiresponse.verifyAssert_httpCode(teamlistapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+teamlistapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, teamlistapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+teamlistapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		   
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengermediasettingpage= avengerHomePage.clickMediaSettingsLink();
        avengerlibrarypage =avengermediasettingpage.click_LibrariesLinkLocator();
        ArrayList<String> myList2 =avengerlibrarypage.get_allTeamList();	
        customReport.customizedReport(true,apiutils.containsAll(teamlistfromJson,myList2), statusValue, driver, sTestcaseName);
		System.out.println("UIandAPIteamsizesimultaneously--"+myList2.size()+"-------------"+teamlistfromJson.size());
		customReport.customizedReport("2",teamlistapirespone.get("TeamMembersSize"), statusValue, driver, sTestcaseName);
		logger.info("Created Team name is " +addteambean.getName());
		customReport.checkinglist(statusValue);
		
	}
		

	
	@Test(description="To Verify the TeamList using TeamList API with eventadmin",groups = {GETTEAMLISTAPI})
	public void TC_03_GET_TeamList_api_check_With_EventAdmin_Negative(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.GetTeamseventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		TeamListAPI teamListAPI=new TeamListAPI();
		teamlistapirespone = teamListAPI.getTeamList(loginapiresponse);
		List<String> teamlistfromJson=apiutils.jsonArrayParse(teamlistapirespone.get("jsonteamResponse"));
	    assertionapiresponse.verifyAssert_httpCode(teamlistapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, teamlistapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="To Verify the TeamList using TeamList API with media contributor",groups = {GETTEAMLISTAPI})
	public void TC_04_GET_TeamList_api_check_With_MediaContributor_Negative(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.GetTeamsmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		TeamListAPI teamListAPI=new TeamListAPI();
		teamlistapirespone = teamListAPI.getTeamList(loginapiresponse);
		List<String> teamlistfromJson=apiutils.jsonArrayParse(teamlistapirespone.get("jsonteamResponse"));
	    assertionapiresponse.verifyAssert_httpCode(teamlistapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, teamlistapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="To Verify the TeamList using TeamList API with MediaViewer",groups = {GETTEAMLISTAPI})
	public void TC_05_GET_TeamList_api_check_With_MediaViewer_Negative(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.GetTeamsmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		TeamListAPI teamListAPI=new TeamListAPI();
		teamlistapirespone = teamListAPI.getTeamList(loginapiresponse);
		List<String> teamlistfromJson=apiutils.jsonArrayParse(teamlistapirespone.get("jsonteamResponse"));
	    assertionapiresponse.verifyAssert_httpCode(teamlistapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, teamlistapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
		customReport.checkinglist(statusValue);
	 }
	
	@Test(description="To Verify the TeamList using TeamList API with Account Admin with only one user as team member",groups = {GETTEAMLISTAPI})
	public void TC_06_GET_TeamList_api_check_With_AccountAdmin_withonlyonemember_asteammember_Positive(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.GetTeamsmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
				
		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		String userIds[] = {loginapiresponse1.get("userId")};
		addteambean.setTeammemberasuser(userIds[0]);		
		addteambean.setTeamMembers(true);
		
		JSONObject creatteamjson=createteamAPI.createTeamJson3(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		loginapiresponse.put("teamname",addteambean.getName());
        loginapiresponse.put("size", "2000"); 
              
     	TeamListAPI teamListAPI=new TeamListAPI();
		teamlistapirespone = teamListAPI.getTeamList(loginapiresponse);
		List<String> teamlistfromJson=apiutils.jsonArrayParse(teamlistapirespone.get("jsonteamResponse"));
		teamlistfromJson.contains(loginapiresponse.get("teamname"));
		assertionapiresponse.verifyAssert_httpCode(teamlistapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+teamlistapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, teamlistapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+teamlistapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		   
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengermediasettingpage= avengerHomePage.clickMediaSettingsLink();
        avengerlibrarypage =avengermediasettingpage.click_LibrariesLinkLocator();
        ArrayList<String> myList2 =avengerlibrarypage.get_allTeamList();	
        customReport.customizedReport(true,apiutils.containsAll(teamlistfromJson,myList2), statusValue, driver, sTestcaseName);
		System.out.println("UIandAPIteamsizesimultaneously--"+myList2.size()+"-------------"+teamlistfromJson.size());
		customReport.customizedReport("1",teamlistapirespone.get("TeamMembersSize"), statusValue, driver, sTestcaseName);
		logger.info("Created Team name is " +addteambean.getName());
		customReport.checkinglist(statusValue);
	
	}
	
		@Test(description="To Verify the TeamList using TeamList API with eventhost",groups = {GETEVENTSLISTAPI})
		public void TC_07_GET_TeamList_api_check_With_EventHost_Negative(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.GetTeamseventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		TeamListAPI teamListAPI=new TeamListAPI();
		teamlistapirespone = teamListAPI.getTeamList(loginapiresponse);
		List<String> teamlistfromJson=apiutils.jsonArrayParse(teamlistapirespone.get("jsonteamResponse"));
	    assertionapiresponse.verifyAssert_httpCode(teamlistapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, teamlistapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
		customReport.checkinglist(statusValue);
		
		}
		
				
		@Test(description="To Verify the TeamList using TeamList API with Account Admin with page size",groups = {GETTEAMLISTAPI})
		public void TC_08_GET_TeamList_api_check_With_AccountAdmin_withpagesize_Positive(ITestContext context ) {

			logger.info("API Level Code is excuting");
			sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
			
			UserServices userservices = new UserServices();
			loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			logger.info("Login API response Code :::" + loginapiresponse);
					
			CreateTeamAPI createteamAPI=new CreateTeamAPI();
			apibeanfactory.CreateTeamBean(addteambean);
			String userIds[] = {loginapiresponse.get("userId")};
			addteambean.setTeammemberasuser(userIds[0]);			
			addteambean.setTeamMembers(true);
			
			JSONObject creatteamjson=createteamAPI.createTeamJson3(addteambean);
			createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
			assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			
			String name=addteambean.getName();			        
	        loginapiresponse.put("teamname",name);
	        loginapiresponse.put("size", "2"); 
	        	        
	     	TeamListAPI teamListAPI=new TeamListAPI();
			teamlistapirespone = teamListAPI.getTeamList(loginapiresponse);
			List<String> teamlistfromJson=apiutils.jsonArrayParse(teamlistapirespone.get("jsonteamResponse"));
			int apiCount = teamlistfromJson.size();
			assertionapiresponse.verifyAssert_httpCode(teamlistapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+teamlistapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, teamlistapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+teamlistapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
			   
			launchURL(surl);
			customReport.reporter("Application launch with URL : ", surl);
			avengerHomePage=loginPage.loginAs(sUserName,sPassword);
			avengerHomePage.clickSettingsLink();
			avengermediasettingpage= avengerHomePage.clickMediaSettingsLink();
	        avengerlibrarypage =avengermediasettingpage.click_LibrariesLinkLocator();
	        ArrayList<String> myList2 =avengerlibrarypage.get_allTeamList();	
			customReport.customizedReport(true,apiutils.containsAll(teamlistfromJson,myList2), statusValue, driver, sTestcaseName);
			customReport.customizedReport(2,apiCount, statusValue, driver, sTestcaseName);
			customReport.customizedReport("0",teamlistapirespone.get("TeamMembersSize"), statusValue, driver, sTestcaseName);
			customReport.checkinglist(statusValue);
			
		}

		@Test(description="To Verify the TeamList using TeamList API with Media Viewer after assigning to group",groups = {GETTEAMLISTAPI})
		public void TC_09_GET_TeamList_api_check_With_MediaViewer_afterassigningtogroup_Positive(ITestContext context ) {

			logger.info("API Level Code is excuting");
			sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
			UserServices userservices = new UserServices();
					
			//Creating a group and assigning Media Viewer to a group having account admin access
			loginapiresponse1 = userservices.doLogin(apiutils.userJson(sUserName), surl);
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			logger.info("Login API response Code :::" + loginapiresponse1);
					
			loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.GetTeamsmediaviewer1), surl);
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			logger.info("Login API response Code :::" + loginapiresponse);
			
			//getting the userIds
			CreateGroupAPI creategroupAPI=new CreateGroupAPI();
			apibeanfactory.CreateGroupBean(addgroupbean);
			String userIds[] = {loginapiresponse.get("userId")};
			addgroupbean.setUserids(userIds);
								
			//getting roleids
			HashMap<String, String> userroleapirespone = userservices.getRolesApi(loginapiresponse1,IAPIConstantCodes.ACCOUNTADMIN);
			String roleid=apiutils.formatingapiresponse(userroleapirespone.get(IAPIConstantCodes.ROLEID));
			String[] roleIds = {userroleapirespone.get("roleId")};
			addgroupbean.setRoleids(roleIds);
						
			JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
			creategroupapirespone = creategroupAPI.createGroup(loginapiresponse1,creategroupjson);
			assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
										
			CreateTeamAPI createteamAPI=new CreateTeamAPI();
			apibeanfactory.CreateTeamBean(addteambean);
			addteambean.setTeammemberasuser(userIds[0]);
							
			//GroupIds UI
			String groupids[]={create_groupfromUI()};
			addteambean.setTeammemberasgroup(groupids[0]);
			addteambean.setTeamMembers(true);
			
			JSONObject creatteamjson=createteamAPI.createTeamJson2(addteambean);
			createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
			assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
								
			loginapiresponse.put("teamname",addteambean.getName());
	        loginapiresponse.put("size", "100"); 
	        	        
	     	TeamListAPI teamListAPI=new TeamListAPI();
			teamlistapirespone = teamListAPI.getTeamList(loginapiresponse);
			List<String> teamlistfromJson=apiutils.jsonArrayParse(teamlistapirespone.get("jsonteamResponse"));
			assertionapiresponse.verifyAssert_httpCode(teamlistapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+teamlistapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, teamlistapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+teamlistapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
			   
			launchURL(surl);
			customReport.reporter("Application launch with URL : ", surl);
			avengerHomePage=loginPage.loginAs(sUserName,sPassword);
			avengerHomePage.clickSettingsLink();
			avengermediasettingpage= avengerHomePage.clickMediaSettingsLink();
	        avengerlibrarypage =avengermediasettingpage.click_LibrariesLinkLocator();
	        ArrayList<String> myList2 =avengerlibrarypage.get_allTeamList();	
	        customReport.customizedReport(true,apiutils.containsAll(teamlistfromJson,myList2), statusValue, driver, sTestcaseName);
			System.out.println("UIandAPIteamsizesimultaneously--"+myList2.size()+"-------------"+teamlistfromJson.size());
			customReport.customizedReport(true,myList2.containsAll(teamlistfromJson), statusValue, driver, sTestcaseName);
			logger.info("Created Team name is " +addteambean.getName());
			customReport.checkinglist(statusValue);
			
			
		}
		
		@Test(description="To Verify the TeamList using TeamList API with Account Admin with group having media viewer role",groups = {GETTEAMLISTAPI})
		public void TC_10_GET_TeamList_api_check_With_AccountAdmin_Positive(ITestContext context ) {

			logger.info("API Level Code is excuting");
			sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
			
			UserServices userservices = new UserServices();
			loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			logger.info("Login API response Code :::" + loginapiresponse);
					
			CreateTeamAPI createteamAPI=new CreateTeamAPI();
			apibeanfactory.CreateTeamBean(addteambean);			
			
			//addteambean.setUserids(loginapiresponse.get("userId"));			
			//create group and assign media viewer role to it			
			//getting the userIds
			CreateGroupAPI creategroupAPI=new CreateGroupAPI();
			apibeanfactory.CreateGroupBean(addgroupbean);
			String userIds[] = {loginapiresponse.get("userId")};
			addgroupbean.setUserids(userIds);
								
			//getting roleids
			UserServices userServices=new UserServices();
		    HashMap<String, String> userroleapirespone = userServices.getRolesApi(loginapiresponse,IAPIConstantCodes.ACCOUNTADMIN);
		    String roleid=apiutils.formatingapiresponse(userroleapirespone.get(IAPIConstantCodes.ROLEID));
		    String[] roleIds = {userroleapirespone.get("roleId")};
			addgroupbean.setRoleids(roleIds);
						
			JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
			creategroupapirespone = creategroupAPI.createGroup(loginapiresponse,creategroupjson);
			assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			
		    //editing existing group roleid		
			loginapiresponse.put("groupId", creategroupapirespone.get(IAPIConstantCodes.APIGROUPID));
			loginapiresponse.put("grpname", creategroupapirespone.get("Name"));
			String[] grpIds = {loginapiresponse.get("groupId")};
			
			HashMap<String, String> userroleapiresponemediaviewer = userServices.getRolesApi(loginapiresponse,IAPIConstantCodes.MEDIAVIEWER);
		    String roleidedit=apiutils.formatingapiresponse(userroleapiresponemediaviewer.get(IAPIConstantCodes.ROLEID));
		    roleIds = new String[] {userroleapirespone.get("roleId")};
			addgroupbean.setRoleids(roleIds);
						
			EditGroupAPI editGroupAPI = new EditGroupAPI();
			JSONObject creategroupjsonedit=creategroupAPI.createGroupJson(addgroupbean);
			
			editGroupapirespone = editGroupAPI.editGroup(loginapiresponse, creategroupjsonedit);
			assertionapiresponse.verifyAssert_httpCode(
			editGroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) + editGroupapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),
			HttpStatusCode.httpsStatusCode200OK);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editGroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) + editGroupapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);
			
			//assigning media viewer role to group ended			
			addteambean.setGroupids(grpIds);
			JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
			createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
			assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
	
	        loginapiresponse.put("teamname",addteambean.getName());
	        loginapiresponse.put("size", "100"); 
	        	        
	     	TeamListAPI teamListAPI=new TeamListAPI();
			teamlistapirespone = teamListAPI.getTeamList(loginapiresponse);
			List<String> teamlistfromJson=apiutils.jsonArrayParse(teamlistapirespone.get("jsonteamResponse"));
			assertionapiresponse.verifyAssert_httpCode(teamlistapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+teamlistapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, teamlistapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+teamlistapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
			   
			launchURL(surl);
			customReport.reporter("Application launch with URL : ", surl);
			avengerHomePage=loginPage.loginAs(sUserName,sPassword);
			avengerHomePage.clickSettingsLink();
			avengermediasettingpage= avengerHomePage.clickMediaSettingsLink();
	        avengerlibrarypage =avengermediasettingpage.click_LibrariesLinkLocator();
	        ArrayList<String> myList2 =avengerlibrarypage.get_allTeamList();	
			customReport.customizedReport(true,apiutils.containsAll(teamlistfromJson,myList2), statusValue, driver, sTestcaseName);
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
	
	
	}