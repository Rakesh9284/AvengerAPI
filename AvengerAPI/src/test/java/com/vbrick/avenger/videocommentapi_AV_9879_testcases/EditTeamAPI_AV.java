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
import com.vbrick.avenger.apibeans.EventAccessControlBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AddCustomDeviceBeanPage;
import com.vbrick.avenger.dao.AddNewDmeBeanPage;
import com.vbrick.avenger.dao.AddPresentationprofileBeanPage;
import com.vbrick.avenger.dao.AvengerEventDetailsBeanPage;
import com.vbrick.avenger.dao.AvengerGroupsBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.LibraryBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerAddCustomDevicePage;
import com.vbrick.avenger.pageobjects.AvengerAddNewDmeDevicePage;
import com.vbrick.avenger.pageobjects.AvengerAddPresentationProfilePage;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerDevicesPage;
import com.vbrick.avenger.pageobjects.AvengerEventDetailsPage;
import com.vbrick.avenger.pageobjects.AvengerEventsPage;
import com.vbrick.avenger.pageobjects.AvengerGroupsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLibrariesPage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerMediaSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerPresentationProfilesPage;
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.ICreateCategoryAPI;
import com.vbricks.avenger.service.ICreateEventService;
import com.vbricks.avenger.service.IEditVideoRatingService;
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.CreateCategoryAPI;
import com.vbricks.avenger.serviceimpl.CreateEventsAPI;
import com.vbricks.avenger.serviceimpl.CreateGroupAPI;
import com.vbricks.avenger.serviceimpl.CreateTeamAPI;
import com.vbricks.avenger.serviceimpl.CustomFieldsAPI;
import com.vbricks.avenger.serviceimpl.DMEdeviceslistAPI;
import com.vbricks.avenger.serviceimpl.EditAccessControlEntitiesforeventAPI;
import com.vbricks.avenger.serviceimpl.EditEventAPI;
import com.vbricks.avenger.serviceimpl.EditGroupAPI;
import com.vbricks.avenger.serviceimpl.EditTeamAPI;
import com.vbricks.avenger.serviceimpl.EditVideoRatingAPI;
import com.vbricks.avenger.serviceimpl.GetCategoryListAPI;
import com.vbricks.avenger.serviceimpl.GetEventDetailsAPI;
import com.vbricks.avenger.serviceimpl.GetEventListAPI;
import com.vbricks.avenger.serviceimpl.PresentationProfilesdetailsAPI;
import com.vbricks.avenger.serviceimpl.SearchAccessEntityAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.ConstantsValue;
import com.vbricks.avenger.utils.HttpStatusCode;

public class EditTeamAPI_AV extends TestBase {

	private static Logger logger = Logger.getLogger(EditTeamAPI_AV.class);
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
	private ApiUtils apiutils;
	private AvengerGroupsPage avengergroupspage;
	private AvengerGroupsBeanPage avengergroupsbeanpage;
	private AddGroupBean addgroupbean;
	private HashMap<String, String> creategroupapirespone;
	private AvengerDevicesPage avengerdevicespage;
	private AvengerEventDetailsPage avengereventdetailspage;
	private AvengerPresentationProfilesPage avengerpresentationprofilespage;
	private AvengerAddPresentationProfilePage avengeraddpresentationprofilepage;
	private AvengerMediaSettingsPage avengermediasettingpage;
	private AddPresentationprofileBeanPage addpresentationprofilebeanpage;
	private AddPresentationprofileBeanPage addeditpresentationprofilebeanpage;
	private AvengerAddCustomDevicePage avengeraddcustomdevicepage;
	private AddNewDmeBeanPage addnewdmedevicebeanpage;
	private BasePage basePage;
	private AddCustomDeviceBeanPage addcustomdevicebeanpage;
	private AddCustomDeviceBeanPage addeditcustomdevicebeanpage;
	private CreateEventBean createEventBean;
	public ApiBeanFactory apibeanfactory;
	private HashMap<String, String> loginapiresponse;
	private HashMap<String, String> loginapiresponse1;
	private HashMap<String, String> editTeamapiresponse;
	private HashMap<String, String> loginapiresponsemediacontributor;
	private AvengerLibrariesPage avengerlibrarypage;
	private AssertionAPIResponse assertionapiresponse;
	private AddTeamBean addteambean;
	private AddTeamBean addteambean1;
	private HashMap<String, String> createteamapirespone;
	private HashMap<String, String> createteamapirespone1;
	private HashMap<String, String >editGroupapiresponse;

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
		avengergroupsbeanpage = new AvengerGroupsBeanPage();
		addgroupbean=new AddGroupBean();
		createEventBean = new CreateEventBean();
		apibeanfactory = new ApiBeanFactory();
		addteambean=new AddTeamBean();
		addteambean1=new AddTeamBean();
		addpresentationprofilebeanpage = new AddPresentationprofileBeanPage();
		addeditpresentationprofilebeanpage = new AddPresentationprofileBeanPage();
		addnewdmedevicebeanpage = new AddNewDmeBeanPage();
		assertionapiresponse = new AssertionAPIResponse();
		addcustomdevicebeanpage = new AddCustomDeviceBeanPage();
		addeditcustomdevicebeanpage = new AddCustomDeviceBeanPage();
		apiutils = new ApiUtils();
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
		accountBeansFactory.createPresentationProfile(addpresentationprofilebeanpage);
		accountBeansFactory.createPresentationProfile(addeditpresentationprofilebeanpage);
		accountBeansFactory.AddNewDmeBean(addnewdmedevicebeanpage);
		accountBeansFactory.addCustomDeviceBean(addcustomdevicebeanpage);
		accountBeansFactory.addCustomDeviceBean(addeditcustomdevicebeanpage);
	}

	@Test(description = "To Verify the EditTeam using EditTeam API with Account Admin with only mandatory fields",groups = {EDITTEAMAPI})
	public void TC_01_PUT_EditTeam_api_check_With_AccountAdmin_withonlymandatoryfields_Update(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		
		//addteambean.setDescription("ApiDescriptionEdit" + apiutils.randomString(5));
		JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
 	
		// Edit Team
		EditTeamAPI editTeamAPI = new EditTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		addteambean.setName("APIEDITName"+apiutils.randomString(5));
		addteambean.setDescription("");

		loginapiresponse.put("channelId", createteamapirespone.get(IAPIConstantCodes.APITEAMID));
		JSONObject creatteamjsonedit=createteamAPI.createTeamJson(addteambean);		
	
		editTeamapiresponse = editTeamAPI.editTeam(loginapiresponse, creatteamjsonedit);	
		assertionapiresponse.verifyAssert_httpCode(editTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + editTeamapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + editTeamapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage=loginPage.loginAs(sUserName,sPassword);
		homePage.clickSettingsLink();
		avengermediasettingpage= homePage.clickMediaSettingsLink();
        avengerlibrarypage =avengermediasettingpage.click_LibrariesLinkLocator();
        LibraryBeanPage librarybeanpage=new LibraryBeanPage();
        librarybeanpage.setLibraryname(addteambean.getName());
        customReport.customizedReport(true, avengerlibrarypage.verifyLibraryCreation(librarybeanpage).contains(librarybeanpage.getLibraryname()), statusValue, driver, sTestcaseName);  
        customReport.checkinglist(statusValue);
	
	}
	
	@Test(description = "To Verify the EditTeam using EditTeam API with Account Admin with only userid update",groups = {EDITTEAMAPI})
	public void TC_02_PUT_EditTeam_api_check_With_AccountAdmin_Update_assigneduserupdate_Positive(ITestContext context) {

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
		addteambean.setUserids(userIds);
			
		//GroupIds UI
		String groupids[]={create_groupfromUI()};
		addteambean.setGroupids(groupids);
			
        JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
       
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
 
		loginapiresponse.put("channelId", createteamapirespone.get(IAPIConstantCodes.APITEAMID));
		
		loginapiresponsemediacontributor = userservices.doLogin(apiutils.userJson(IUsersList.EditEventmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponsemediacontributor.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponsemediacontributor.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponsemediacontributor);
		userIds = new String[] {loginapiresponsemediacontributor.get("userId")};
        addteambean.setUserids(userIds);
		JSONObject creatteamjsonedit=createteamAPI.createTeamJson(addteambean);
				
		// Edit Team
		EditTeamAPI editTeamAPI = new EditTeamAPI();
		editTeamapiresponse = editTeamAPI.editTeam(loginapiresponse, creatteamjsonedit);
		assertionapiresponse.verifyAssert_httpCode(editTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + editTeamapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + editTeamapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage=loginPage.loginAs(sUserName,sPassword);
		homePage.clickSettingsLink();
		avengermediasettingpage= homePage.clickMediaSettingsLink();
        avengerlibrarypage =avengermediasettingpage.click_LibrariesLinkLocator();
        LibraryBeanPage librarybeanpage=new LibraryBeanPage();
        librarybeanpage.setLibraryname(addteambean.getName());
        customReport.customizedReport(true, avengerlibrarypage.verifyLibraryCreation(librarybeanpage).contains(librarybeanpage.getLibraryname()), statusValue, driver, sTestcaseName);  
        customReport.checkinglist(statusValue);		
		
	}
	
	@Test(description = "To Verify the EditTeam using EditTeam API with Account Admin with both userid and groupid update with Media Admin",groups = {EDITTEAMAPI})
	public void TC_03_PUT_EditTeam_api_check_With_MediaAdmin_Update_assigneduserandgroup_Positive(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.EditTeammediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		String userIds[] = {loginapiresponse.get("userId")};
		addteambean.setUserids(userIds);
			
		//GroupIds UI
		String groupids[]={create_groupfromUI()};
		addteambean.setGroupids(groupids);
			
        JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
       
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
 
		loginapiresponse.put("channelId", createteamapirespone.get(IAPIConstantCodes.APITEAMID));		
		loginapiresponsemediacontributor = userservices.doLogin(apiutils.userJson(IUsersList.EditEventmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponsemediacontributor.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponsemediacontributor.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponsemediacontributor);

		userIds = new String[] {loginapiresponsemediacontributor.get("userId")};
        addteambean.setUserids(userIds);
		
		//GroupIds UI
		String groupidsedit[]={create_groupfromUI()};
		addteambean.setGroupids(groupidsedit);
		
		JSONObject creatteamjsonedit=createteamAPI.createTeamJson(addteambean);
				
		// Edit Team
		EditTeamAPI editTeamAPI = new EditTeamAPI();
		editTeamapiresponse = editTeamAPI.editTeam(loginapiresponse, creatteamjsonedit);
		assertionapiresponse.verifyAssert_httpCode(editTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + editTeamapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + editTeamapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage=loginPage.loginAs(sUserName,sPassword);
		homePage.clickSettingsLink();
		avengermediasettingpage= homePage.clickMediaSettingsLink();
        avengerlibrarypage =avengermediasettingpage.click_LibrariesLinkLocator();
        LibraryBeanPage librarybeanpage=new LibraryBeanPage();
        librarybeanpage.setLibraryname(addteambean.getName());
        customReport.customizedReport(true, avengerlibrarypage.verifyLibraryCreation(librarybeanpage).contains(librarybeanpage.getLibraryname()), statusValue, driver, sTestcaseName);  
        customReport.checkinglist(statusValue);		
		
	}
	
	@Test(description="To Verify that Event admin does not have access to edit the team using EditTeam API ",groups = {EDITTEAMAPI})
	public void TC_04_PUT_EditTeam_api_check_With_EventAdmin_Negative(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
 
		//Login with event admin		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.EditTeameventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);
		
		// Edit Team
		EditTeamAPI editTeamAPI = new EditTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		addteambean.setName("APIEDITName"+apiutils.randomString(5));
		addteambean.setDescription("");

		loginapiresponse1.put("channelId", createteamapirespone.get(IAPIConstantCodes.APITEAMID));
		JSONObject creatteamjsonedit=createteamAPI.createTeamJson(addteambean);
		
		editTeamapiresponse = editTeamAPI.editTeam(loginapiresponse1, creatteamjsonedit);		
		assertionapiresponse.verifyAssert_httpCode(editTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, editTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
		
	}
	
	@Test(description = "To Verify Event Host is able to edit the team when assigned to a group having account admin role",groups = {EDITTEAMAPI})
	public void TC_05_PUT_EditTeam_api_check_With_EventHost_Update_assignedtoAAgroup_Positive(ITestContext context) throws InterruptedException {

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
		addteambean.setUserids(userIds);
		 
		//Creating a group and assigning EventHost to a group having account admin access		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.EditTeameventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);

		//getting the userIds
		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		userIds = new String[] {loginapiresponse1.get("userId")};
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
		
		String[] groupids = {creategroupapirespone.get("groupId")};			
		addteambean.setGroupids(groupids);			
        JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
       
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		userIds = new String[] {loginapiresponse.get("userId")};
        addteambean.setUserids(userIds);
        loginapiresponse1.put("channelId", createteamapirespone.get(IAPIConstantCodes.APITEAMID));		
		JSONObject creatteamjsonedit=createteamAPI.createTeamJson(addteambean);		
		Thread.sleep(4000);
		
		// Edit Team
		EditTeamAPI editTeamAPI = new EditTeamAPI();
		editTeamapiresponse = editTeamAPI.editTeam(loginapiresponse1, creatteamjsonedit);	
		assertionapiresponse.verifyAssert_httpCode(editTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + editTeamapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + editTeamapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);				
			
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage=loginPage.loginAs(sUserName,sPassword);
		homePage.clickSettingsLink();
		avengermediasettingpage= homePage.clickMediaSettingsLink();
        avengerlibrarypage =avengermediasettingpage.click_LibrariesLinkLocator();
        LibraryBeanPage librarybeanpage=new LibraryBeanPage();
        librarybeanpage.setLibraryname(addteambean.getName());
        customReport.customizedReport(true, avengerlibrarypage.verifyLibraryCreation(librarybeanpage).contains(librarybeanpage.getLibraryname()), statusValue, driver, sTestcaseName);  
        customReport.checkinglist(statusValue);	
		
	}
	
	@Test(description="To Verify that Media Contributor does not have access to edit the team using EditTeam API ",groups = {EDITTEAMAPI})
	public void TC_06_PUT_EditTeam_api_check_With_MediaContributor_Negative(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
 
		//Login with event admin		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.EditTeammediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);
		
		// Edit Team
		EditTeamAPI editTeamAPI = new EditTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		addteambean.setName("APIEDITName"+apiutils.randomString(5));
		addteambean.setDescription("");

		loginapiresponse1.put("channelId", createteamapirespone.get(IAPIConstantCodes.APITEAMID));
		JSONObject creatteamjsonedit=createteamAPI.createTeamJson(addteambean);
		
		editTeamapiresponse = editTeamAPI.editTeam(loginapiresponse1, creatteamjsonedit);		
		assertionapiresponse.verifyAssert_httpCode(editTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, editTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
		
	}
	

	@Test(description="To Verify that Media Viewer does not have access to edit the team using EditTeam API ",groups = {EDITTEAMAPI})
	public void TC_07_PUT_EditTeam_api_check_With_MediaViewer_Negative(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
 
		//Login with event admin		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.EditTeammediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);
		
		// Edit Team
		EditTeamAPI editTeamAPI = new EditTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		addteambean.setName("APIEDITName"+apiutils.randomString(5));
		addteambean.setDescription("");

		loginapiresponse1.put("channelId", createteamapirespone.get(IAPIConstantCodes.APITEAMID));
		JSONObject creatteamjsonedit=createteamAPI.createTeamJson(addteambean);
		
		editTeamapiresponse = editTeamAPI.editTeam(loginapiresponse1, creatteamjsonedit);		
		assertionapiresponse.verifyAssert_httpCode(editTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, editTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
		
	}
	
	@Test(description = "To Verify that EditTeam API gives error when trying to edit the team name with an existing team name",groups = {EDITTEAMAPI})
	public void TC_08_PUT_EditTeam_api_check_With_AccountAdmin_updateteamname_withexistingteamname_Negative(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		apibeanfactory.CreateTeamBean(addteambean1);
		JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
		JSONObject creatteamjson1=createteamAPI.createTeamJson(addteambean1);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		createteamapirespone1 = createteamAPI.createTeam(loginapiresponse,creatteamjson1);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone1.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		// Edit Team
		EditTeamAPI editTeamAPI = new EditTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		addteambean.setName(addteambean1.getName());
		
		loginapiresponse.put("channelId", createteamapirespone.get(IAPIConstantCodes.APITEAMID));
		JSONObject creatteamjsonedit=createteamAPI.createTeamJson(addteambean);
			
		editTeamapiresponse = editTeamAPI.editTeam(loginapiresponse, creatteamjsonedit);		
		assertionapiresponse.verifyAssert_httpCode(editTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatus500);
		customReport.customizedReport(HttpStatusCode.httpsStatus500, editTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.customizedReport(editTeamapiresponse.get(IAPIConstantCodes.APIResponseJsonErrorCode), HttpStatusCode.code, statusValue, driver, sTestcaseName);
		customReport.customizedReport(editTeamapiresponse.get(IAPIConstantCodes.APIResponseJsonErrorDetail), HttpStatusCode.detail, statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
                	
	}
	
	@Test(description = "To Verify the EditTeam API is succesful when multiple userids and groupids are given",groups = {EDITTEAMAPI})
	public void TC_09_PUT_EditTeam_api_check_With_MediaAdmin_Update_assigneduserandgroup_Positive(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.EditTeammediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		
		String[] userIds = {loginapiresponse.get("userId")};
        addteambean.setUserids(userIds);
	
        JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
       
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
 
		loginapiresponse.put("channelId", createteamapirespone.get(IAPIConstantCodes.APITEAMID));		
		loginapiresponsemediacontributor = userservices.doLogin(apiutils.userJson(IUsersList.EditEventmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponsemediacontributor.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponsemediacontributor.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponsemediacontributor);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.EditEventmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);

		userIds = new String[] {loginapiresponsemediacontributor.get("userId"), loginapiresponse.get("userId")};
        addteambean.setUserids(userIds);
		
		//GroupIds UI
		String groupidsedit[]={create_groupfromUI(), create_groupfromUI()};		
		addteambean.setGroupids(groupidsedit);		
		JSONObject creatteamjsonedit=createteamAPI.createTeamJson(addteambean);
				
		// Edit Team
		EditTeamAPI editTeamAPI = new EditTeamAPI();
		editTeamapiresponse = editTeamAPI.editTeam(loginapiresponse, creatteamjsonedit);
		assertionapiresponse.verifyAssert_httpCode(editTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + editTeamapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + editTeamapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage=loginPage.loginAs(sUserName,sPassword);
		homePage.clickSettingsLink();
		avengermediasettingpage= homePage.clickMediaSettingsLink();
        avengerlibrarypage =avengermediasettingpage.click_LibrariesLinkLocator();
        LibraryBeanPage librarybeanpage=new LibraryBeanPage();
        librarybeanpage.setLibraryname(addteambean.getName());
        customReport.customizedReport(true, avengerlibrarypage.verifyLibraryCreation(librarybeanpage).contains(librarybeanpage.getLibraryname()), statusValue, driver, sTestcaseName);  
        customReport.checkinglist(statusValue);		
		
	}
	
	@Test(description = "To Verify that EditTeam API throws error when invalid team id is given",groups = {EDITTEAMAPI})
	public void TC_10_PUT_EditTeam_api_check_With_AccountAdmin_withinvalidteamid_Negative(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		// Edit Team
		EditTeamAPI editTeamAPI = new EditTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		addteambean.setDescription("ApiDescriptionEdit" + apiutils.randomString(5));

		loginapiresponse.put("channelId", createteamapirespone.get(IAPIConstantCodes.APITEAMID)+"invalid");
		JSONObject creatteamjsonedit=createteamAPI.createTeamJson(addteambean);
			
		editTeamapiresponse = editTeamAPI.editTeam(loginapiresponse, creatteamjsonedit);		
		assertionapiresponse.verifyAssert_httpCode(editTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, editTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	
	}


	@Test(description = "To Verify that EditTeam API throws error when trying to update without giving mandatory fields",groups = {EDITTEAMAPI})
	public void TC_11_PUT_EditTeam_api_check_With_AccountAdmin_withoutmandatoryfields_Negative(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
 		
		// Edit Team
		EditTeamAPI editTeamAPI = new EditTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		addteambean.setName("");
		addteambean.setDescription("ApiDescriptionEdit" + apiutils.randomString(5));

		loginapiresponse.put("channelId", createteamapirespone.get(IAPIConstantCodes.APITEAMID));
		JSONObject creatteamjsonedit=createteamAPI.createTeamJson(addteambean);
			
		editTeamapiresponse = editTeamAPI.editTeam(loginapiresponse, creatteamjsonedit);		
		assertionapiresponse.verifyAssert_httpCode(editTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatus500);
		customReport.customizedReport(HttpStatusCode.httpsStatus500, editTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		customReport.customizedReport(editTeamapiresponse.get(IAPIConstantCodes.APIResponseJsonErrorCode), HttpStatusCode.missingCode, statusValue, driver, sTestcaseName);
		customReport.customizedReport(editTeamapiresponse.get(IAPIConstantCodes.APIResponseJsonErrorDetail), HttpStatusCode.missingDetail, statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
	
	}

	@Test(description = "To Verify the EditTeam using EditTeam API with Account Admin with existing name update and adding new description",groups = {EDITTEAMAPI})
	public void TC_12_PUT_EditTeam_api_check_With_AccountAdmin_withonlymandatoryfields_Update(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		addteambean.setDescription("");
		JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName); 
		
		// Edit Team
		EditTeamAPI editTeamAPI = new EditTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		addteambean.setName("APIEDITName"+apiutils.randomString(5));
		
		loginapiresponse.put("channelId", createteamapirespone.get(IAPIConstantCodes.APITEAMID));
		JSONObject creatteamjsonedit=createteamAPI.createTeamJson(addteambean);
			
		editTeamapiresponse = editTeamAPI.editTeam(loginapiresponse, creatteamjsonedit);		
		assertionapiresponse.verifyAssert_httpCode(editTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + editTeamapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + editTeamapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage=loginPage.loginAs(sUserName,sPassword);
		homePage.clickSettingsLink();
		avengermediasettingpage= homePage.clickMediaSettingsLink();
        avengerlibrarypage =avengermediasettingpage.click_LibrariesLinkLocator();
        LibraryBeanPage librarybeanpage=new LibraryBeanPage();
        librarybeanpage.setLibraryname(addteambean.getName());
        customReport.customizedReport(true, avengerlibrarypage.verifyLibraryCreation(librarybeanpage).contains(librarybeanpage.getLibraryname()), statusValue, driver, sTestcaseName);  
        customReport.checkinglist(statusValue);
	
	}

	@Test(description = "To Verify the EditTeam using EditTeam API with Account Admin with description update",groups = {EDITTEAMAPI})
	public void TC_13_PUT_EditTeam_api_check_With_AccountAdmin_Update_Description_Positive(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.EditTeammediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		addteambean.setDescription("ApiDescription" + apiutils.randomString(5));
		JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
 
		loginapiresponse.put("channelId", createteamapirespone.get(IAPIConstantCodes.APITEAMID));
		
		// Edit Team
		EditTeamAPI editTeamAPI = new EditTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		addteambean.setDescription("ApiDescriptionEdit" + apiutils.randomString(5));
		JSONObject creatteamjsonedit=createteamAPI.createTeamJson(addteambean);
		
		editTeamapiresponse = editTeamAPI.editTeam(loginapiresponse, creatteamjsonedit);
		assertionapiresponse.verifyAssert_httpCode(editTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + editTeamapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + editTeamapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage=loginPage.loginAs(sUserName,sPassword);
		homePage.clickSettingsLink();
		avengermediasettingpage= homePage.clickMediaSettingsLink();
        avengerlibrarypage =avengermediasettingpage.click_LibrariesLinkLocator();
        LibraryBeanPage librarybeanpage=new LibraryBeanPage();
        librarybeanpage.setLibraryname(addteambean.getName());
        customReport.customizedReport(true, avengerlibrarypage.verifyLibraryCreation(librarybeanpage).contains(librarybeanpage.getLibraryname()), statusValue, driver, sTestcaseName);  
        customReport.checkinglist(statusValue);
	}
	
	public void TC_14_PUT_EditTeam_api_check_With_MediaAdmin_Update_invaliduseridandvalidgroupid_Negative(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.EditTeammediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
        JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
       
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		loginapiresponse.put("channelId", createteamapirespone.get(IAPIConstantCodes.APITEAMID));
		
		loginapiresponsemediacontributor = userservices.doLogin(apiutils.userJson(IUsersList.EditEventmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponsemediacontributor.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponsemediacontributor.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponsemediacontributor);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.EditEventmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);

		String userIds[] = {loginapiresponse.get("userId")+"a"};
		addteambean.setTeammemberasuser(userIds[0]);
		addteambean.setTeamMembers(true);
		
		String groupids[]={create_groupfromUI()};
		addteambean.setTeammemberasgroup(groupids[0]);			
		addteambean.setTeamMembers(true);			
		JSONObject creatteamjsonedit=createteamAPI.createTeamJson(addteambean);
				
		// Edit Team
		EditTeamAPI editTeamAPI = new EditTeamAPI();
		editTeamapiresponse = editTeamAPI.editTeam(loginapiresponse, creatteamjsonedit);
		assertionapiresponse.verifyAssert_httpCode(editTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatus500);
		customReport.customizedReport(HttpStatusCode.httpsStatus500, editTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.customizedReport(HttpStatusCode.missingCode,editTeamapiresponse.get(IAPIConstantCodes.APIResponseJsonErrorCode),statusValue, driver, sTestcaseName);
		customReport.customizedReport(HttpStatusCode.detail3,editTeamapiresponse.get(IAPIConstantCodes.APIResponseJsonErrorDetail), statusValue, driver, sTestcaseName);		
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description = "To Verify the EditTeam API throws error when invalid groupid and valid userid are given",groups = {EDITTEAMAPI})
	public void TC_15_PUT_EditTeam_api_check_With_MediaAdmin_Update_invalidgroupandvaliduserid_Negative(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.EditTeammediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
        JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
       
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		loginapiresponse.put("channelId", createteamapirespone.get(IAPIConstantCodes.APITEAMID));
		
		loginapiresponsemediacontributor = userservices.doLogin(apiutils.userJson(IUsersList.EditEventmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponsemediacontributor.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponsemediacontributor.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponsemediacontributor);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.EditEventmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		String userIds[] = {loginapiresponse.get("userId")};
		addteambean.setTeammemberasuser(userIds[0]);
		addteambean.setTeamMembers(true);
		
		String groupids[]={create_groupfromUI()+"a"};
		addteambean.setTeammemberasgroup(groupids[0]);			
		addteambean.setTeamMembers(true);			
		JSONObject creatteamjsonedit=createteamAPI.createTeamJson(addteambean);
				
		// Edit Team
		EditTeamAPI editTeamAPI = new EditTeamAPI();
		editTeamapiresponse = editTeamAPI.editTeam(loginapiresponse, creatteamjsonedit);
		assertionapiresponse.verifyAssert_httpCode(editTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatus500);
		customReport.customizedReport(HttpStatusCode.httpsStatus500, editTeamapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.customizedReport( HttpStatusCode.missingCode, editTeamapiresponse.get(IAPIConstantCodes.APIResponseJsonErrorCode),statusValue, driver, sTestcaseName);
		customReport.customizedReport( HttpStatusCode.detail2, editTeamapiresponse.get(IAPIConstantCodes.APIResponseJsonErrorDetail),statusValue, driver, sTestcaseName);
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
		homePage = loginPage.loginAs(sUserName,sPassword);
		homePage.clickSettingsLink();
		avengergroupspage=homePage.clickGroupsLink();
		accountBeansFactory.AvengergroupsBean(avengergroupsbeanpage);
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
