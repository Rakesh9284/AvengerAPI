package com.vbrick.avenger.videocommentapi_AV_9879_testcases;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
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
 
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AddUserBeanPage;
import com.vbrick.avenger.dao.AvengerGroupsBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerAddUserPage;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerEditRootAccountPage;
import com.vbrick.avenger.pageobjects.AvengerGroupsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerUserDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbrick.avenger.videocommentapi_AV_9879_testcases.SubmitVideosComments_AV_9879;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IDeleteVideoService;
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUserAccountsService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.CreateGroupAPI;
import com.vbricks.avenger.serviceimpl.CreateTeamAPI;
import com.vbricks.avenger.serviceimpl.DeleteGroupAPI;
import com.vbricks.avenger.serviceimpl.DeleteTeamAPI;
import com.vbricks.avenger.serviceimpl.DeleteVideosAPI;
import com.vbricks.avenger.serviceimpl.GetUserProfileImageAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserDetailsAPI;
import com.vbricks.avenger.serviceimpl.UserNameAPI;
import com.vbricks.avenger.serviceimpl.UserProfileImageAPI;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.ConstantsValue;
import com.vbricks.avenger.utils.HttpStatusCode;

public class UserDetailsWithUserNameApi_AV_9873 extends TestBase {

	private static Logger logger = Logger.getLogger(UserDetailsWithUserNameApi_AV_9873.class);
	private AvengerLoginPage loginPage;
	private AvengerHomePage homePage;
	private HashMap<String, String> usernameapirespone;
	private AvengerAddUserPage adduserpage;
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
    private ApiUtils apiutils;
	private AvengerHomePage avengerHomePage;
	private AvengerUserDashboardPage avengeruserdashboardpage;
	private AvengerGroupsBeanPage avengergroupsbeanpage;
	private BasePage basePage;
	private AddUploadVideoBean adduploadvideobean;
	public ApiBeanFactory apibeanfactory;
	private AddGroupBean addgroupbean;
	private AddTeamBean addteambean;
	public AddVideoCommentBean addvideocommentbean;
 	private HashMap<String, String> loginapiresponse;
 	private HashMap<String, String> loginapiresponse1;
	private HashMap<String, String> uploadvidoeapiresponse;
	private HashMap<String,String> loginapiresponse_mediaviewer;
	private AvengerGroupsPage avengergroupspage;
	private HashMap<String,String> loginapiresponse_eventadmin;
	private HashMap<String, String> creategroupapirespone;
 	private HashMap<String, String> createteamapirespone;
 	private HashMap<String, String> deleteGroupapiresponse;
 	private HashMap<String, String> deleteTeamapiresponse;
	private HashMap<String,String> loginapiresponse_mediacontributor;
    private AssertionAPIResponse assertionapiresponse;
 	private AvengerEditRootAccountPage avengereditrootaccountpage; 
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
		addgroupbean=new AddGroupBean();
		addteambean=new AddTeamBean();
		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		addvideocommentbean = new AddVideoCommentBean();
		assertionapiresponse =new AssertionAPIResponse();
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
		adduserpage=basePage.avengerAddUserpage(driver, customReport, basePage);
		driver.manage().window().maximize();
		accountBeansFactory.MailinatorBean(mailinatorbeanpage);

	}

	@Test(description = "To verify Username   to specific user using Username API with accountadmin",groups = {GETUSERS_USERNAME})
	public void TC_01_GET_UserName_api_check_UserName_with_AccoutAdmin_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);		
		
		loginapiresponse.put(IAPIConstantCodes.APIResponseUserName1, loginapiresponse.get(IAPIConstantCodes.APIUSERNAME));		
		UserNameAPI usernameAPI=new UserNameAPI();
	    HashMap<String, String> usernameapirespone = usernameAPI.getUserName(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(usernameapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+usernameapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, usernameapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+usernameapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	 	    
		// Selenium Level Code
		launchURL(surl);
		avengerHomePage = loginPage.loginAs(sUserName, IUserAccountsService.apigridpwd);
		avengerHomePage.clickSettingsLink();
		avengeruserdashboardpage = avengerHomePage.clickUsersLink();
		AddUserBeanPage adduserbean = new AddUserBeanPage();
		adduserbean.setFirstname(usernameapirespone.get(IUserAccountsService.FIRSTNAME));
		adduserbean.setLastname(usernameapirespone.get(IUserAccountsService.LASTNAME));
 		adduserbean.setContactemail(usernameapirespone.get(IUserAccountsService.CONTACTEMAIL));
 		adduserbean.setUsername(usernameapirespone.get(IUserAccountsService.USERNAME));
		avengereditrootaccountpage = avengeruserdashboardpage.clicknewuser_link1(adduserbean);
		HashMap<String, String> uservalues = avengereditrootaccountpage.get_allUserfieldValues();
		customReport.customizedReport(adduserbean.getFirstname(), uservalues.get(IUserAccountsService.FIRSTNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getLastname(), uservalues.get(IUserAccountsService.LASTNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getUsername(), uservalues.get(IUserAccountsService.USERNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getContactemail(), uservalues.get(IUserAccountsService.CONTACTEMAIL), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify UserName  to specific Media Admin user using UserName API with Account Admin",groups = {GETUSERS_USERNAME})
	public void TC_02_GET_UserName_api_check_UserName_with_MediaAdmin_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);	
	
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.UserNamemediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		logger.info("Media Admin Login API response Code :::" + loginapiresponse1);		
		
		loginapiresponse.put(IAPIConstantCodes.APIResponseUserName1, loginapiresponse1.get(IAPIConstantCodes.APIUSERNAME));			
		UserNameAPI usernameAPI=new UserNameAPI();
	    HashMap<String, String> usernameapirespone = usernameAPI.getUserName(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(usernameapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+usernameapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, usernameapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+usernameapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    
		// Selenium Level Code
		launchURL(surl);
		avengerHomePage = loginPage.loginAs(sUserName, IUserAccountsService.apigridpwd);
		avengerHomePage.clickSettingsLink();
		avengeruserdashboardpage = avengerHomePage.clickUsersLink();
		AddUserBeanPage adduserbean = new AddUserBeanPage();		 
		adduserbean.setFirstname(usernameapirespone.get(IUserAccountsService.FIRSTNAME));
		adduserbean.setLastname(usernameapirespone.get(IUserAccountsService.LASTNAME));
 		adduserbean.setContactemail(usernameapirespone.get(IUserAccountsService.CONTACTEMAIL));
 		adduserbean.setUsername(usernameapirespone.get(IUserAccountsService.USERNAME));
		avengereditrootaccountpage = avengeruserdashboardpage.clicknewuser_link(adduserbean);
		HashMap<String, String> uservalues = avengereditrootaccountpage.get_allUserfieldValues();
		customReport.customizedReport(adduserbean.getFirstname(), uservalues.get(IUserAccountsService.FIRSTNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getLastname(), uservalues.get(IUserAccountsService.LASTNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getUsername(), uservalues.get(IUserAccountsService.USERNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getContactemail(), uservalues.get(IUserAccountsService.CONTACTEMAIL), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify UserName  to specific Media Contributor user using UserName API with Account Admin",groups = {GETUSERS_USERNAME})
	public void TC_03_GET_UserName_api_check_UserDetail_with_MediaContributor_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
	
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.UserNamemediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		logger.info("Media Contributor Login API response Code :::" + loginapiresponse1);		
		
		loginapiresponse.put(IAPIConstantCodes.APIResponseUserName1, loginapiresponse1.get(IAPIConstantCodes.APIUSERNAME));			
		UserNameAPI usernameAPI=new UserNameAPI();
	    HashMap<String, String> usernameapirespone = usernameAPI.getUserName(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(usernameapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+usernameapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, usernameapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+usernameapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    
	    String fromattedJSON=apiutils.formatingapiresponse(usernameapirespone.get(IAPIConstantCodes.APIResponseJSON1));
		ArrayList<String> apiRoleList = new ArrayList<String>(Arrays.asList(fromattedJSON.split(",")));
				
		// Selenium Level Code
		launchURL(surl);
		avengerHomePage = loginPage.loginAs(sUserName, IUserAccountsService.apigridpwd);
		avengerHomePage.clickSettingsLink();
		avengeruserdashboardpage = avengerHomePage.clickUsersLink();
		AddUserBeanPage adduserbean = new AddUserBeanPage();	 
		adduserbean.setFirstname(usernameapirespone.get(IUserAccountsService.FIRSTNAME));
		adduserbean.setLastname(usernameapirespone.get(IUserAccountsService.LASTNAME));
 		adduserbean.setContactemail(usernameapirespone.get(IUserAccountsService.CONTACTEMAIL));
 		adduserbean.setUsername(usernameapirespone.get(IUserAccountsService.USERNAME));
 		adduserbean.setGroupname(usernameapirespone.get(IUserAccountsService.GROUPS));
 		avengereditrootaccountpage = avengeruserdashboardpage.clicknewuser_link(adduserbean);
		HashMap<String, String> uservalues = avengereditrootaccountpage.get_allUserfieldValues();
		ArrayList<String> uiRolelist = avengereditrootaccountpage.get_allassignedroles();
		ArrayList<String> uiRolelist1 = new ArrayList();
		
		for (String roles : uiRolelist) {
			uiRolelist1.add(roles.trim().replace(" ",""));
			logger.info("@@@ after trim "+roles.trim().replace(" ",""));
		}
		logger.info("the array list after trim"+uiRolelist1);
		Collections.sort(uiRolelist1);
		
		//Collections.replaceAll(uiRolelist, "\\s+", "");
		customReport.customizedReport(adduserbean.getFirstname(), uservalues.get(IUserAccountsService.FIRSTNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getLastname(), uservalues.get(IUserAccountsService.LASTNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getUsername(), uservalues.get(IUserAccountsService.USERNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getContactemail(), uservalues.get(IUserAccountsService.CONTACTEMAIL), statusValue, driver, sTestcaseName);
		customReport.customizedReport(true,apiutils.containsAll(apiRoleList, uiRolelist1), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify UserName  to specific Event Admin user using UserName API with Account Admin",groups = {GETUSERS_USERNAME})
	public void TC_04_GET_UserName_api_check_UserName_with_Eventadmin_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		//deleting the assigned groups if any for the Media Viewer user
		delete_assignedGroupsForUser(IUsersList.UserNameeventadmin);
				
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
	
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.UserNameeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		logger.info("Event Admin Login API response Code :::" + loginapiresponse1);	
		
		//assigning the user to a group
		//getting the userIds
		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		String[] userIds = {loginapiresponse1.get("userId")};
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
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
								
		//assigning user to a team				
		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		String[] userIds1 = {loginapiresponse1.get("userId")};
		String[] groupids1 = {creategroupapirespone.get("groupId")};
		addteambean.setUserids(userIds1);
		addteambean.setTeammemberasuser(userIds1[0]);						
		addteambean.setTeammemberasgroup(groupids1[0]);								
		addteambean.setTeamMembers(true);
								
		JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		loginapiresponse.put(IAPIConstantCodes.APIResponseUserName1, loginapiresponse1.get(IAPIConstantCodes.APIUSERNAME));		
		UserNameAPI usernameAPI=new UserNameAPI();
	    HashMap<String, String> usernameapirespone = usernameAPI.getUserName(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(usernameapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+usernameapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, usernameapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+usernameapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
	    String fromattedJSON=apiutils.formatingapiresponse(usernameapirespone.get(IAPIConstantCodes.APIResponseJSON2));
		ArrayList<String> apiGroupList = new ArrayList<String>(Arrays.asList(fromattedJSON.split(",")));
		
		String fromattedJSON1=apiutils.formatingapiresponse(usernameapirespone.get(IAPIConstantCodes.APIResponseJSON3));
		ArrayList<String> apiTeamList = new ArrayList<String>(Arrays.asList(fromattedJSON1.split(",")));
		int teamcount = apiTeamList.size();
							
		//Selenium Level Code
		launchURL(surl);
		avengerHomePage = loginPage.loginAs(sUserName, IUserAccountsService.apigridpwd);
		avengerHomePage.clickSettingsLink();
		avengeruserdashboardpage = avengerHomePage.clickUsersLink();
		AddUserBeanPage adduserbean = new AddUserBeanPage();		 
		adduserbean.setFirstname(usernameapirespone.get(IUserAccountsService.FIRSTNAME));
		adduserbean.setLastname(usernameapirespone.get(IUserAccountsService.LASTNAME));
 		adduserbean.setContactemail(usernameapirespone.get(IUserAccountsService.CONTACTEMAIL));
 		adduserbean.setUsername(usernameapirespone.get(IUserAccountsService.USERNAME));
 		avengereditrootaccountpage = avengeruserdashboardpage.clicknewuser_link(adduserbean);
		HashMap<String, String> uservalues = avengereditrootaccountpage.get_allUserfieldValues();
		ArrayList<String> uiGroupList = avengereditrootaccountpage.get_allassignedgroups();				
		customReport.customizedReport(adduserbean.getFirstname(), uservalues.get(IUserAccountsService.FIRSTNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getLastname(), uservalues.get(IUserAccountsService.LASTNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getUsername(), uservalues.get(IUserAccountsService.USERNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getContactemail(), uservalues.get(IUserAccountsService.CONTACTEMAIL), statusValue, driver, sTestcaseName);
		customReport.customizedReport(true,apiutils.containsAll(apiGroupList, uiGroupList), statusValue, driver, sTestcaseName);
		customReport.customizedReport(1,teamcount, statusValue, driver, sTestcaseName);
		
		// Delete Group
		loginapiresponse.put(IAPIConstantCodes.APIResponseGROUPID, creategroupapirespone.get(IAPIConstantCodes.APIGROUPID));
		DeleteGroupAPI deleteGroupAPI = new DeleteGroupAPI();
		deleteGroupapiresponse = deleteGroupAPI.deleteGroups(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(
		deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);
						
		// Delete Team
		loginapiresponse.put(IAPIConstantCodes.APIResponseTEAMID, createteamapirespone.get(IAPIConstantCodes.APITEAMID));
		DeleteTeamAPI deleteTeamAPI = new DeleteTeamAPI();
		deleteTeamapiresponse = deleteTeamAPI.deleteTeams(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(deleteTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deleteTeamapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,deleteTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deleteTeamapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);				
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify UserName  to specific Media Viewer user using UserName API with Account Admin",groups = {GETUSERS_USERNAME})
	public void TC_05_GET_UserName_api_check_UserName_with_MediaViewer_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
	
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.UserNamemediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		logger.info("Media Viewer Login API response Code :::" + loginapiresponse1);	
		
		loginapiresponse.put(IAPIConstantCodes.APIResponseUserName1, loginapiresponse1.get(IAPIConstantCodes.APIUSERNAME));			
		UserNameAPI usernameAPI=new UserNameAPI();
	    HashMap<String, String> usernameapirespone = usernameAPI.getUserName(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(usernameapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+usernameapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, usernameapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+usernameapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    
	    String fromattedJSON=apiutils.formatingapiresponse(usernameapirespone.get(IAPIConstantCodes.APIResponseJSON1));
		ArrayList<String> apiRoleList = new ArrayList<String>(Arrays.asList(fromattedJSON.split(",")));
		
		// Selenium Level Code
		launchURL(surl);
		avengerHomePage = loginPage.loginAs(sUserName, IUserAccountsService.apigridpwd);
		avengerHomePage.clickSettingsLink();
		avengeruserdashboardpage = avengerHomePage.clickUsersLink();
		AddUserBeanPage adduserbean = new AddUserBeanPage();		 
		adduserbean.setFirstname(usernameapirespone.get(IUserAccountsService.FIRSTNAME));
		adduserbean.setLastname(usernameapirespone.get(IUserAccountsService.LASTNAME));
 		adduserbean.setContactemail(usernameapirespone.get(IUserAccountsService.CONTACTEMAIL));
 		adduserbean.setUsername(usernameapirespone.get(IUserAccountsService.USERNAME));
 		avengereditrootaccountpage = avengeruserdashboardpage.clicknewuser_link(adduserbean);
		HashMap<String, String> uservalues = avengereditrootaccountpage.get_allUserfieldValues();
		ArrayList<String> uiRolelist = avengereditrootaccountpage.get_allassignedroles();
		ArrayList<String> uiRolelist1 = new ArrayList();
		
		for (String roles : uiRolelist) {
			uiRolelist1.add(roles.trim().replace(" ",""));
			logger.info("@@@ after trim "+roles.trim().replace(" ",""));
		}
		logger.info("the array list after trim"+uiRolelist1);
		Collections.sort(uiRolelist1);		
		customReport.customizedReport(adduserbean.getFirstname(), uservalues.get(IUserAccountsService.FIRSTNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getLastname(), uservalues.get(IUserAccountsService.LASTNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getUsername(), uservalues.get(IUserAccountsService.USERNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getContactemail(), uservalues.get(IUserAccountsService.CONTACTEMAIL), statusValue, driver, sTestcaseName);
		customReport.customizedReport(true,apiutils.containsAll(apiRoleList, uiRolelist1), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify UserName   to spicific user using UserName API with InvalidUserName",groups = {GETUSERS_USERNAME})
	public void TC_06_GET_UserName_api_check_UserName_with_InvalidUserName_Negative() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);		
		loginapiresponse.put(IAPIConstantCodes.APIUSERNAME, IAPIConstantCodes.InValidName);
		
		UserNameAPI usernameAPI=new UserNameAPI();
	    HashMap<String, String> usernameapirespone = usernameAPI.getUserName(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(usernameapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus404);
	    customReport.customizedReport(HttpStatusCode.httpsStatus404, usernameapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify UserName  to specific Event Host user using UserName API with Account Admin",groups = {GETUSERS_USERNAME})
	public void TC_07_GET_UserName_api_check_UserName_with_Eventhost_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		//deleting the assigned groups if any for the Media Viewer user
		delete_assignedGroupsForUser(IUsersList.UserNameeventhost);
				
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.UserNameeventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);		
		
		//assigning the user to a group
		//getting the userIds
		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		String[] userIds = {loginapiresponse1.get("userId")};
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
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
								
		//assigning user to a team
					
		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		String[] userIds1 = {loginapiresponse1.get("userId")};
		String[] groupids1 = {creategroupapirespone.get("groupId")};
		addteambean.setUserids(userIds1);
		addteambean.setTeammemberasuser(userIds1[0]);					
		addteambean.setTeammemberasgroup(groupids1[0]);								
		addteambean.setTeamMembers(true);
								
		JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		
		loginapiresponse.put(IAPIConstantCodes.APIResponseUserName1, loginapiresponse1.get(IAPIConstantCodes.APIUSERNAME));		
		UserNameAPI usernameAPI=new UserNameAPI();
	    HashMap<String, String> usernameapirespone = usernameAPI.getUserName(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(usernameapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+usernameapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, usernameapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+usernameapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    
	    String fromattedJSON=apiutils.formatingapiresponse(usernameapirespone.get(IAPIConstantCodes.APIResponseJSON2));
		ArrayList<String> apiGroupList = new ArrayList<String>(Arrays.asList(fromattedJSON.split(",")));
		
		String fromattedJSON1=apiutils.formatingapiresponse(usernameapirespone.get(IAPIConstantCodes.APIResponseJSON3));
		ArrayList<String> apiTeamList = new ArrayList<String>(Arrays.asList(fromattedJSON1.split(",")));
		int teamcount = apiTeamList.size();
		
		// Selenium Level Code
		launchURL(surl);
		avengerHomePage = loginPage.loginAs(sUserName, IUserAccountsService.apigridpwd);
		avengerHomePage.clickSettingsLink();
		avengeruserdashboardpage = avengerHomePage.clickUsersLink();
		AddUserBeanPage adduserbean = new AddUserBeanPage();		 
		adduserbean.setFirstname(usernameapirespone.get(IUserAccountsService.FIRSTNAME));
		adduserbean.setLastname(usernameapirespone.get(IUserAccountsService.LASTNAME));
 		adduserbean.setContactemail(usernameapirespone.get(IUserAccountsService.CONTACTEMAIL));
 		adduserbean.setUsername(usernameapirespone.get(IUserAccountsService.USERNAME));
		avengereditrootaccountpage = avengeruserdashboardpage.clicknewuser_link(adduserbean);
		HashMap<String, String> uservalues = avengereditrootaccountpage.get_allUserfieldValues();
		ArrayList<String> uiGroupList = avengereditrootaccountpage.get_allassignedgroups();
		customReport.customizedReport(adduserbean.getFirstname(), uservalues.get(IUserAccountsService.FIRSTNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getLastname(), uservalues.get(IUserAccountsService.LASTNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getUsername(), uservalues.get(IUserAccountsService.USERNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getContactemail(), uservalues.get(IUserAccountsService.CONTACTEMAIL), statusValue, driver, sTestcaseName);
		customReport.customizedReport(true,apiutils.containsAll(apiGroupList, uiGroupList), statusValue, driver, sTestcaseName);
		customReport.customizedReport(1,teamcount, statusValue, driver, sTestcaseName);
		
		// Delete Group
	    loginapiresponse.put(IAPIConstantCodes.APIResponseGROUPID, creategroupapirespone.get(IAPIConstantCodes.APIGROUPID));
		DeleteGroupAPI deleteGroupAPI = new DeleteGroupAPI();
		deleteGroupapiresponse = deleteGroupAPI.deleteGroups(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(
		deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);
						
		// Delete Team
		loginapiresponse.put(IAPIConstantCodes.APIResponseTEAMID, createteamapirespone.get(IAPIConstantCodes.APITEAMID));
		DeleteTeamAPI deleteTeamAPI = new DeleteTeamAPI();
		deleteTeamapiresponse = deleteTeamAPI.deleteTeams(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(deleteTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deleteTeamapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,deleteTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deleteTeamapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);				
		customReport.checkinglist(statusValue);
	}
	

	@Test(description = "To verify UserName  to specific Media Viewer user using UserName API with Media Admin",groups = {GETUSERS_USERNAME})
	public void TC_08_GET_UserName_api_check_UserName_with_MediaAdmin_Negative() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UserNamemediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);	
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.UserNamemediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		logger.info("Media Viewer Login API response Code :::" + loginapiresponse1);
		
		loginapiresponse.put(IAPIConstantCodes.APIResponseUserName1, loginapiresponse1.get(IAPIConstantCodes.APIUSERNAME));		
		UserNameAPI usernameAPI=new UserNameAPI();
	    HashMap<String, String> usernameapirespone = usernameAPI.getUserName(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(usernameapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
	    customReport.customizedReport(HttpStatusCode.httpsStatus401, usernameapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);		
				
	}
	
	@Test(description = "To verify UserDetails with username and Get user profile image api ",groups = {GETUSERS_USERNAME})
	public void TC_09_GET_UserDetails_withUserName_andGetUserImage_Api_with_AccountAdmin_Positive(ITestContext context) throws Exception {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);		
		 
		UserProfileImageAPI userprofileImageAPI=new UserProfileImageAPI();
		HashMap<String,String> userprofielImageapiresponse = userprofileImageAPI.uploaduserprofileimage(loginapiresponse, Setup.VALIDIMAGEPATH_JPG2,IUploadVideoService.UPLOADIMAGE );
		logger.info("UploadVideo API response Code :::" + userprofielImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(userprofielImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, userprofielImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, userprofielImageapiresponse);	
		
		loginapiresponse.put(IAPIConstantCodes.APIResponseUserName1, loginapiresponse.get(IAPIConstantCodes.APIUSERNAME));		
		UserNameAPI usernameAPI=new UserNameAPI();
		HashMap<String, String> usernameapirespone = usernameAPI.getUserName(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(usernameapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+usernameapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, usernameapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+usernameapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    
	    URI  uri = new URI(usernameapirespone.get(IAPIConstantCodes.profileImageUri).toString());  
        // get the  Path
        String Path = uri.getPath();
	    
	    //assert it with userprofile image
	    customReport.customizedReport(true,Path.contains(".jpg"),statusValue, driver, sTestcaseName);
		 
	    loginapiresponse.put(IAPIConstantCodes.profileImageUri,Path);
	    GetUserProfileImageAPI getuserprofile=new GetUserProfileImageAPI();
	    HashMap<String, String> getuserprofileapiresponse=getuserprofile.GetUserProfile(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(getuserprofileapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getuserprofileapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	    
	    
		// Selenium Level Code
		launchURL(surl);
		avengerHomePage = loginPage.loginAs(sUserName,sPassword);
	    avengerHomePage.click_userprofiledropdown();
	    avengerHomePage.click_Myprofile();
	    customReport.customizedReport(true,  adduserpage.verify_image().contains("<img"),statusValue, driver, sTestcaseName);
	    adduserpage.deleteimage();
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