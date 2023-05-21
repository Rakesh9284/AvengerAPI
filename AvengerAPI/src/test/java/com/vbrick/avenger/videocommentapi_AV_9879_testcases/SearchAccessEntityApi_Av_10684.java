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
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
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
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbrick.avenger.apibeans.AddVideoCommentBean;
import com.vbrick.avenger.apibeans.CreateEventBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AvengerGroupsBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.LibraryBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerEditRootAccountPage;
import com.vbrick.avenger.pageobjects.AvengerGroupsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLibrariesPage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerMediaSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerUserDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.ICreateCategoryAPI;
import com.vbricks.avenger.service.IEditVideoRatingService;
import com.vbricks.avenger.service.IGetCategoryService;
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.CreateCategoryAPI;
import com.vbricks.avenger.serviceimpl.CreateGroupAPI;
import com.vbricks.avenger.serviceimpl.CustomFieldsAPI;
import com.vbricks.avenger.serviceimpl.DMEdeviceslistAPI;
import com.vbricks.avenger.serviceimpl.EditVideoRatingAPI;
import com.vbricks.avenger.serviceimpl.GetCategoryListAPI;
import com.vbricks.avenger.serviceimpl.PresentationProfilesdetailsAPI;
import com.vbricks.avenger.serviceimpl.SearchAccessEntityAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.ConstantsValue;
import com.vbricks.avenger.utils.HttpStatusCode;
public class SearchAccessEntityApi_Av_10684 extends TestBase{

	private static Logger logger = Logger.getLogger(SearchAccessEntityApi_Av_10684.class);
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
	private AvengerEditRootAccountPage avengereditrootaccountpage; 
	private Map<String, String> userdata;
	private AddGroupBean addgroupbean;
	private BasePage basePage;
	private AddUploadVideoBean adduploadvideobean;
	private AvengerUserDashboardPage avengeruserdashboardpage;
	public ApiBeanFactory apibeanfactory;
    public AddVideoCommentBean addvideocommentbean;
	private AvengerGroupsPage avengergroupspage;
    private CreateEventBean createEventBean;
	private HashMap<String, String> loginapiresponse;
	private HashMap<String, String> loginapiresponse1;
	private HashMap<String, String> uploadvidoeapiresponse;
	 private AssertionAPIResponse assertionapiresponse;
	 private HashMap<String, String> createcategoryapirespone;
	 private HashMap<String, String> creategroupapirespone;
	 private HashMap<String, String> childcategoryapirespone;
	 private AvengerGroupsBeanPage avengergroupsbeanpage;
	 private HashMap<String, String> searchaccessentityapirespone;
	 private AvengerMediaSettingsPage avengermediasettingspage;
     private AvengerLibrariesPage avengerlibrarypage;
     private LibraryBeanPage librarybeanpage;
     private AvengerHomePage avengerHomePage;
	 private AvengerMediaSettingsPage avengermediasettingpage;
	 private ApiUtils apiutils; 
	@BeforeClass(alwaysRun = true)
	public void beforeClass() throws Exception {
		reasons = new Reasons("");
		statusValue = new ArrayList<String>();
		customReport = new CustomReport();
		accountBeansFactory = new BeanFactory();
		mailinatorbeanpage = new MailinatorBeanPage();
		librarybeanpage=new LibraryBeanPage();
		new FileUploadBeanPage();
		locale = new Locale(language);
		readgriduserdata = new ReadAndWriteToJSON();
		userdata = readgriduserdata.readGridUserData(this.getClass().getSimpleName()); // ("user52");
		sUserName = userdata.get("Username");
		sPassword = userdata.get("Password");
		addgroupbean=new AddGroupBean();
		createEventBean = new CreateEventBean();
		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		addvideocommentbean=new AddVideoCommentBean();
		assertionapiresponse =new AssertionAPIResponse();
		apiutils=new ApiUtils();
		avengergroupsbeanpage=new AvengerGroupsBeanPage() ;
		 

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
		avengerlibrarypage=new AvengerLibrariesPage(driver, customReport, basePage);
		loginPage = basePage.avengerLoginPage(driver, customReport, basePage);
		driver.manage().window().maximize();
		accountBeansFactory.MailinatorBean(mailinatorbeanpage);
		accountBeansFactory.AvengergroupsBean(avengergroupsbeanpage);

	}

	@Test(description="To Verify SearchAccessEntity API with Account Admin without giving any parameters",groups = {SEARCHACCESSENTITYAPI})
	public void TC_01_GET_SearchAccessEntity_withoutgiving_parameters_api_check_With_AccountAdmin_Positive(ITestContext context ) throws InterruptedException  {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		 

		SearchAccessEntityAPI searchaccessEntityAPI=new SearchAccessEntityAPI();
		JSONObject searchaccessentityapirespone = searchaccessEntityAPI.searchAccessEntitywithoutqueryparams(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseHttpCode).toString()+searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseHttpCode).toString()+searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		String apiCount=searchaccessentityapirespone.get("totalEntities").toString();
		 
				
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		
		avengeruserdashboardpage=avengerHomePage.clickUsersLink();			
		ArrayList<String> uiUserEntitiyList =avengeruserdashboardpage.get_AllUsers();
		Collections.sort(uiUserEntitiyList);
		int uiuserCount = uiUserEntitiyList.size();
		logger.info("@@@UI User Entities list count is " +uiuserCount);
		
		 
		avengergroupspage =avengerHomePage.clickChannelsLink();
		ArrayList<String> uichannelEntityList=avengerlibrarypage.get_AllLibraries();
		Collections.sort(uichannelEntityList);
		int uichannelCount = uichannelEntityList.size();
		logger.info("@@@UI Channel Entities list count is " +uichannelCount);
		
		avengergroupspage =avengerHomePage.clickGroupsLink();		
		ArrayList<String> uiGrpEntitiyList =avengergroupspage.get_AllGroups();
		Collections.sort(uiGrpEntitiyList);
		int uigrpCount = uiGrpEntitiyList.size();
		logger.info("@@@UI Group Entities list count is " +uigrpCount);
		
		int Count = uiuserCount + uigrpCount+uichannelCount;
		String uiCount=Integer.toString(Count);
		
		logger.info("@@@UI Users and Groups Entities list count is " +uiCount);
		customReport.customizedReport(apiCount,uiCount, statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);			
	}
	
	
	@Test(description="To Verify the SearchAccessEntity API for user with Media Admin ",groups = {SEARCHACCESSENTITYAPI})
	public void TC_02_GET_SearchAccessEntity_using_user_api_check_With_MediaAdmin_Positive(ITestContext context ) throws InterruptedException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.SearchAccessEntitymediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Media Admin Login API response Code :::" + loginapiresponse);
		String params = "Yes";
		String qParams = "No";
		String count = "No";
		
		loginapiresponse.put(IAPIConstantCodes.QUERY, IAPIConstantCodes.user);

		SearchAccessEntityAPI searchaccessEntityAPI=new SearchAccessEntityAPI();
		searchaccessentityapirespone = searchaccessEntityAPI.searchAccessEntity(loginapiresponse,params,qParams,count);
	    assertionapiresponse.verifyAssert_httpCode(searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		String fromattedJSONforuser=apiutils.formatingapiresponse(searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> apiuserEntityList = new ArrayList<String>(Arrays.asList(fromattedJSONforuser.split(",")));
		Collections.sort(apiuserEntityList);
		int apiuserCount = apiuserEntityList.size();
		logger.info("API User Entities list count is " +apiuserCount);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengeruserdashboardpage=avengerHomePage.clickUsersLink();
		ArrayList<String> uiUserEntitiyList =avengeruserdashboardpage.get_AllUsers();
		Collections.sort(uiUserEntitiyList);
		int uiuserCount = uiUserEntitiyList.size();
		logger.info("UI User Entities list count is " +uiuserCount);
		if (apiuserCount == uiuserCount) {
			customReport.customizedReport(true,apiutils.containsAll(apiuserEntityList, uiUserEntitiyList), statusValue, driver, sTestcaseName);
			logger.info("API Count is" +apiuserCount+ "and UI Count is" +uiuserCount);		
		}
		else {
		customReport.customizedReport(apiuserCount,uiuserCount, statusValue, driver, sTestcaseName);
		}
		customReport.checkinglist(statusValue);
	} 
	
	@Test(description="To Verify the SearchAccessEntity API for group with Event Admin ",groups = {SEARCHACCESSENTITYAPI})
	public void TC_03_GET_SearchAccessEntity_using_group_api_check_With_EventAdmin_Positive(ITestContext context ) throws InterruptedException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.SearchAccessEntitymediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Event Admin Login API response Code :::" + loginapiresponse);
		String params = "Yes";
		String qParams = "No";
		String count = "No";
		
		loginapiresponse.put(IAPIConstantCodes.QUERY, IAPIConstantCodes.group);
				
		SearchAccessEntityAPI searchaccessEntityAPI=new SearchAccessEntityAPI();
		searchaccessentityapirespone = searchaccessEntityAPI.searchAccessEntity(loginapiresponse,params,qParams,count);
	    assertionapiresponse.verifyAssert_httpCode(searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		String fromattedJSONforgroup=apiutils.formatingapiresponse(searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseJSON1));
		ArrayList<String> apigroupEntityList = new ArrayList<String>(Arrays.asList(fromattedJSONforgroup.split(",")));
		Collections.sort(apigroupEntityList);
		int apigroupCount = apigroupEntityList.size();
		logger.info("API groups Entities list count is " +apigroupCount);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		
		avengergroupspage =avengerHomePage.clickGroupsLink();
		ArrayList<String> uiGrpEntitiyList =avengergroupspage.get_AllGroups();
		Collections.sort(uiGrpEntitiyList);
		int uigrpCount = uiGrpEntitiyList.size();
		logger.info("UI Group Entities list count is " +uigrpCount);
		
		if (apigroupCount == uigrpCount) {
			
			customReport.customizedReport(true,apiutils.containsAll(apigroupEntityList, uiGrpEntitiyList), statusValue, driver, sTestcaseName);
			logger.info("API Count is" +apigroupCount+ "and UI Count is" +uigrpCount);
					
		}
		
		else {
			
		customReport.customizedReport(apigroupCount,uigrpCount, statusValue, driver, sTestcaseName);
		
		}
		
		customReport.checkinglist(statusValue);
							
	} 
	
	
	@Test(description="To Verify the SearchAccessEntity API with Media Contributor by providing search string for user entity",groups = {SEARCHACCESSENTITYAPI})
	public void TC_04_GET_SearchAccessEntity_using_searchstring_withuserentity_MediaContributor_Positive(ITestContext context ) throws InterruptedException {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.SearchAccessEntitymediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Media Admin Login API response Code :::" + loginapiresponse);
		String params = "Yes";
		String qParams = "User";
		String count = "No";
		
		loginapiresponse.put(IAPIConstantCodes.QUERY, IAPIConstantCodes.user);
		loginapiresponse.put(IAPIConstantCodes.APIUSERNAME, loginapiresponse.get(IAPIConstantCodes.APIUSERNAME));
		
		SearchAccessEntityAPI searchaccessEntityAPI=new SearchAccessEntityAPI();
		searchaccessentityapirespone = searchaccessEntityAPI.searchAccessEntity(loginapiresponse,params,qParams,count);
	    assertionapiresponse.verifyAssert_httpCode(searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		String fromattedJSONforuser=apiutils.formatingapiresponse(searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> apiuserEntityList = new ArrayList<String>(Arrays.asList(fromattedJSONforuser.split(",")));
		String groupnamefromapi=apiuserEntityList.get(0);
		int apiuserCount = apiuserEntityList.size();
		logger.info("API User Entities list count is " +apiuserCount);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		
		avengeruserdashboardpage=avengerHomePage.clickUsersLink();
		ArrayList<String> uiuserEntitiyList = avengeruserdashboardpage.searchUserstr(loginapiresponse.get(IAPIConstantCodes.APIUSERNAME));
		String groupnamefromUI=apiuserEntityList.get(0);
		int uiuserCount = uiuserEntitiyList.size();
		logger.info("UI User Entities list count is " +uiuserCount);
		
		if (apiuserCount == uiuserCount) {
			
			customReport.customizedReport(true,apiutils.containsAll(apiuserEntityList, uiuserEntitiyList), statusValue, driver, sTestcaseName);
			logger.info("API Count is" +apiuserCount+ "and UI Count is" +uiuserCount);
					
		}
		
		else {
			
		customReport.customizedReport(apiuserCount,uiuserCount, statusValue, driver, sTestcaseName);
		
		}
		
		customReport.checkinglist(statusValue);
							
	}
	
	@Test(description="To Verify the SearchAccessEntity API with Event Host by providing search string for group entity",groups = {SEARCHACCESSENTITYAPI})
	public void TC_05_GET_SearchAccessEntity_using_searchstring_withgroupentity_EventHost_Positive(ITestContext context ) throws InterruptedException {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.SearchAccessEntityeventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Event Host Login API response Code :::" + loginapiresponse);
		String params = "Yes";
		String qParams = "Group";
		String count = "No";
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);

		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
		creategroupapirespone = creategroupAPI.createGroup(loginapiresponse1,creategroupjson);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
		
		//GroupIds UI
		String groupname = addgroupbean.getName();
		loginapiresponse.put(IAPIConstantCodes.QUERY, IAPIConstantCodes.group);
		loginapiresponse.put(IAPIConstantCodes.groupname, groupname);
		
		SearchAccessEntityAPI searchaccessEntityAPI=new SearchAccessEntityAPI();
		searchaccessentityapirespone = searchaccessEntityAPI.searchAccessEntity(loginapiresponse,params,qParams,count);
	    assertionapiresponse.verifyAssert_httpCode(searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		String fromattedJSONforgroup=apiutils.formatingapiresponse(searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseJSON1));
		ArrayList<String> apigroupEntityList = new ArrayList<String>(Arrays.asList(fromattedJSONforgroup.split(",")));
		Collections.sort(apigroupEntityList);
		String groupnamefromapi=apigroupEntityList.get(0);
		int apigroupCount = apigroupEntityList.size();
		logger.info("API groups Entities list count is " +apigroupCount);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		
		avengergroupspage =avengerHomePage.clickGroupsLink();
		ArrayList<String> uiGrpEntitiyList = avengergroupspage.searchGroupstr(loginapiresponse.get(IAPIConstantCodes.groupname));
		Collections.sort(uiGrpEntitiyList);
		String groupnamefromUI=uiGrpEntitiyList.get(0);
		int uigrpCount = uiGrpEntitiyList.size();
		logger.info("UI Group Entities list count is " +uigrpCount);
		
		if (groupnamefromapi.equals(groupnamefromUI)) {

			customReport.customizedReport(true,groupnamefromapi.equals(groupnamefromUI), statusValue, driver, sTestcaseName);
			logger.info("API Count is::" +apigroupCount+ "and UI Count is::" +uigrpCount);
					
		}
		else {
			
		customReport.customizedReport(apigroupCount,uigrpCount, statusValue, driver, sTestcaseName);
		
		}
		
		customReport.checkinglist(statusValue);		
		
	}
	
	@Test(description="To Verify the SearchAccessEntity API with Media Viewer by providing search string for user entity which does not exist",groups = {SEARCHACCESSENTITYAPI})
	public void TC_06_GET_SearchAccessEntity_using_searchstring_whichdoesnotexist_withuserentity_MediaViewer_Positive(ITestContext context ) throws InterruptedException {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.SearchAccessEntitymediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Media Admin Login API response Code :::" + loginapiresponse);
		String params = "Yes";
		String qParams = "User";
		String count = "No";
		
		loginapiresponse.put(IAPIConstantCodes.QUERY, IAPIConstantCodes.user);
		loginapiresponse.put(IAPIConstantCodes.APIUSERNAME, "invalid");
		
		SearchAccessEntityAPI searchaccessEntityAPI=new SearchAccessEntityAPI();
		searchaccessentityapirespone = searchaccessEntityAPI.searchAccessEntity(loginapiresponse,params,qParams,count);
	    assertionapiresponse.verifyAssert_httpCode(searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		int apiuserCount = 0;
		if(searchaccessentityapirespone.containsKey("Zerorecords")){
			
			logger.info("API User Entities list count is " +apiuserCount);
		}
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		
		avengeruserdashboardpage=avengerHomePage.clickUsersLink();
		ArrayList<String> uiuserEntitiyList = avengeruserdashboardpage.searchUserstr(loginapiresponse.get(IAPIConstantCodes.APIUSERNAME));
		int uiuserCount = uiuserEntitiyList.size();
		logger.info("UI User Entities list count is " +uiuserCount);
		
		if ((apiuserCount == uiuserCount) && (apiuserCount==0)) {
			
			logger.info("API Count is" +apiuserCount+ "and UI Count is" +uiuserCount);
					
		}
		
		else {
			
		customReport.customizedReport(apiuserCount,uiuserCount, statusValue, driver, sTestcaseName);
		
		}
		
		customReport.checkinglist(statusValue);		
		
	}
	
	@Test(description="To Verify the SearchAccessEntity API with Media Viewer by providing search string for group entity with count parameter",groups = {SEARCHACCESSENTITYAPI})
	public void TC_07_GET_SearchAccessEntity_using_searchstring_withgroupentity_MediaViewer_Count_Positive(ITestContext context ) throws InterruptedException {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.SearchAccessEntitymediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Event Host Login API response Code :::" + loginapiresponse);
		String params = "Yes";
		String qParams = "No";
		String count = "Yes";
		
		loginapiresponse.put(IAPIConstantCodes.QUERY, IAPIConstantCodes.group);
		loginapiresponse.put(IAPIConstantCodes.COUNT, "2");

		SearchAccessEntityAPI searchaccessEntityAPI=new SearchAccessEntityAPI();
		searchaccessentityapirespone = searchaccessEntityAPI.searchAccessEntity(loginapiresponse,params,qParams,count);
	    assertionapiresponse.verifyAssert_httpCode(searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		String fromattedJSONforgroup=apiutils.formatingapiresponse(searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseJSON1));
		ArrayList<String> apigroupEntityList = new ArrayList<String>(Arrays.asList(fromattedJSONforgroup.split(",")));
		Collections.sort(apigroupEntityList);
		int apigroupCount = apigroupEntityList.size();
		logger.info("API groups Entities list count is " +apigroupCount);
		logger.info("API groups Entities list is " +apigroupEntityList);					
		customReport.customizedReport(2,apigroupCount, statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	
					
	}
	
	@Test(description="To Verify the SearchAccessEntity API with Media Contributor by providing search string for user entity with count",groups = {SEARCHACCESSENTITYAPI})
	public void TC_08_GET_SearchAccessEntity_using_searchstring_withuser_entity_MediaContributor_Count_Positive(ITestContext context ) throws InterruptedException {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.SearchAccessEntitymediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Media Admin Login API response Code :::" + loginapiresponse);
		String params = "Yes";
		String qParams = "User";
		String count = "Yes";
		
		loginapiresponse.put(IAPIConstantCodes.QUERY, IAPIConstantCodes.user);
		loginapiresponse.put(IAPIConstantCodes.APIUSERNAME, "apiaau");
		loginapiresponse.put(IAPIConstantCodes.COUNT, "3");
		
		SearchAccessEntityAPI searchaccessEntityAPI=new SearchAccessEntityAPI();
		searchaccessentityapirespone = searchaccessEntityAPI.searchAccessEntity(loginapiresponse,params,qParams,count);
	    assertionapiresponse.verifyAssert_httpCode(searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		String fromattedJSONforuser=apiutils.formatingapiresponse(searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> apiuserEntityList = new ArrayList<String>(Arrays.asList(fromattedJSONforuser.split(",")));
		int apiuserCount = apiuserEntityList.size();
		logger.info("API User Entities list count is " +apiuserCount);
		logger.info("API User Entities list is " +apiuserEntityList);		
		customReport.customizedReport(3,apiuserCount, statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);					
			
	}
	
	@Test(description="To Verify the SearchAccessEntity API with Account Admin by providing search string for user entity",groups = {SEARCHACCESSENTITYAPI})
	public void TC_09_GET_SearchAccessEntity_using_searchstring_withuserentity_AccountAdmin_Positive(ITestContext context ) throws InterruptedException {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		String params = "Yes";
		String qParams = "User";
		String count = "No";
		
		loginapiresponse.put(IAPIConstantCodes.QUERY, IAPIConstantCodes.user);
		loginapiresponse.put(IAPIConstantCodes.APIUSERNAME, "apiehu");
		
		SearchAccessEntityAPI searchaccessEntityAPI=new SearchAccessEntityAPI();
		searchaccessentityapirespone = searchaccessEntityAPI.searchAccessEntity(loginapiresponse,params,qParams,count);
	    assertionapiresponse.verifyAssert_httpCode(searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		String fromattedJSONforuser=apiutils.formatingapiresponse(searchaccessentityapirespone.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> apiuserEntityList = new ArrayList<String>(Arrays.asList(fromattedJSONforuser.split(",")));
		Collections.sort(apiuserEntityList);
		int apiuserCount = apiuserEntityList.size();
		logger.info("API User Entities list count is " +apiuserCount);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		
		avengeruserdashboardpage=avengerHomePage.clickUsersLink();
		ArrayList<String> uiuserEntitiyList = avengeruserdashboardpage.searchUserstr("apiehu");
		int uiuserCount = uiuserEntitiyList.size();
		logger.info("UI User Entities list count is " +uiuserCount);
		if (apiuserCount == uiuserCount) {
			customReport.customizedReport(true,apiutils.containsAll(apiuserEntityList, uiuserEntitiyList), statusValue, driver, sTestcaseName);
			logger.info("API Count is" +apiuserCount+ "and UI Count is" +uiuserCount);		
		}
		else {
		customReport.customizedReport(apiuserCount,uiuserCount, statusValue, driver, sTestcaseName);
		}
		customReport.checkinglist(statusValue);			
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
        return avengergroupsbeanpage.getNewgroup();
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
}
