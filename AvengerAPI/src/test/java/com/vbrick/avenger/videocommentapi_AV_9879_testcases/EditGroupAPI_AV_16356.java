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
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AddCustomDeviceBeanPage;
import com.vbrick.avenger.dao.AddNewDmeBeanPage;
import com.vbrick.avenger.dao.AddPresentationprofileBeanPage;
import com.vbrick.avenger.dao.AvengerGroupsBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerGroupsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.CreateGroupAPI;
import com.vbricks.avenger.serviceimpl.EditGroupAPI;
import com.vbricks.avenger.serviceimpl.EditTeamAPI;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class EditGroupAPI_AV_16356 extends TestBase {

	private static Logger logger = Logger.getLogger(EditGroupAPI_AV_16356.class);
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
	private ApiUtils apiutils;
	private AvengerGroupsPage avengergroupspage;
	private AvengerGroupsBeanPage avengergroupsbeanpage;
	private AddPresentationprofileBeanPage addpresentationprofilebeanpage;
	private AddPresentationprofileBeanPage addeditpresentationprofilebeanpage;
	private AddNewDmeBeanPage addnewdmedevicebeanpage;
	private BasePage basePage;
	private AddCustomDeviceBeanPage addcustomdevicebeanpage;
	private AddCustomDeviceBeanPage addeditcustomdevicebeanpage;
	public ApiBeanFactory apibeanfactory;
	private HashMap<String, String> loginapiresponse;
	private HashMap<String, String> loginapiresponse1;
	private HashMap<String, String> editGroupapiresponse;
	private HashMap<String, String> loginapiresponsemediacontributor;
	private AssertionAPIResponse assertionapiresponse;
	private AddGroupBean addgroupbean;
	private AddGroupBean addgroupbean1;
	private HashMap<String, String> creategroupapirespone;
	private HashMap<String, String> creategroupapirespone1;

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
		apibeanfactory = new ApiBeanFactory();
		addgroupbean=new AddGroupBean();
		addgroupbean1=new AddGroupBean();
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

	@Test(description = "To Verify the EditGroup for groupname by giving existing groupname using EditGroup API with Account Admin",groups = {EDITGROUPAPI})
	public void TC_01_PUT_EditGroup_Api_check_With_AccountAdmin_Update_GroupName_Negative(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		apibeanfactory.CreateGroupBean(addgroupbean1);
		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
		JSONObject creategroupjson1=creategroupAPI.createGroupJson(addgroupbean1);
		creategroupapirespone = creategroupAPI.createGroup(loginapiresponse,creategroupjson);
		creategroupapirespone1 = creategroupAPI.createGroup(loginapiresponse,creategroupjson1);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone1.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		// Edit Group
		EditGroupAPI editGroupAPI = new EditGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		
		//addgroupbean.setName("APIEDITName"+apiutils.randomString(5));
		addgroupbean.setName(addgroupbean1.getName());
		
		loginapiresponse.put("groupId", creategroupapirespone.get(IAPIConstantCodes.APIGROUPID));
		JSONObject creategroupjsonedit=creategroupAPI.createGroupJson(addgroupbean);
		
		editGroupapiresponse = editGroupAPI.editGroup(loginapiresponse, creategroupjsonedit);	
		assertionapiresponse.verifyAssert_httpCode(editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode500);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode500, editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.customizedReport(editGroupapiresponse.get(IAPIConstantCodes.APIResponseJsonErrorCode), HttpStatusCode.code, statusValue, driver, sTestcaseName);
		customReport.customizedReport(editGroupapiresponse.get(IAPIConstantCodes.APIResponseJsonErrorDetail), HttpStatusCode.detail1, statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
			
	}

	
	@Test(description="To Verify editGroup for userid update using EditGroup API with Account Admin ",groups = {EDITGROUPAPI})
	public void TC_02_PUT_EditGroup_Api_check_With_AccountAdmin_Update_userid_Positive(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		//getting the userIds
		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		String[] userIds = {loginapiresponse.get("userId")};
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
		
	    //editing existing group userid		
		loginapiresponse.put("groupId", creategroupapirespone.get(IAPIConstantCodes.APIGROUPID));
		loginapiresponsemediacontributor = userservices.doLogin(apiutils.userJson(IUsersList.EditEventmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponsemediacontributor.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponsemediacontributor.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponsemediacontributor);
		
		userIds = new String[] {loginapiresponsemediacontributor.get("userId")};
		addgroupbean.setUserids(userIds);
				
		EditGroupAPI editGroupAPI = new EditGroupAPI();
		JSONObject creategroupjsonedit=creategroupAPI.createGroupJson(addgroupbean);

		editGroupapiresponse = editGroupAPI.editGroup(loginapiresponse, creategroupjsonedit);
		assertionapiresponse.verifyAssert_httpCode(
		editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + editGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),
		HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + editGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);	
	    
		//editing of existing group userid ended		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengergroupspage =avengerHomePage.clickGroupsLink();
		avengergroupsbeanpage=new AvengerGroupsBeanPage();
		avengergroupsbeanpage.setNewgroup(addgroupbean.getName());
        customReport.customizedReport(true, avengergroupspage.verifyGroupsCreation(avengergroupsbeanpage).contains(avengergroupsbeanpage.getNewgroup()), statusValue, driver, sTestcaseName);  
        customReport.checkinglist(statusValue);
	}
	
		
	@Test(description="To Verify editGroup for roleid update using EditGroup API with Account Admin ",groups = {EDITGROUPAPI})
	public void TC_03_PUT_EditGroup_Api_check_With_AccountAdmin_Update_Roleid_Positive(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		//getting the userIds
		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		String[] userIds = {loginapiresponse.get("userId")};
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
		HashMap<String, String> userroleapiresponemediaadmin = userServices.getRolesApi(loginapiresponse,IAPIConstantCodes.MEDIAADMIN);
	    String roleidedit=apiutils.formatingapiresponse(userroleapiresponemediaadmin.get(IAPIConstantCodes.ROLEID));
	    
	    roleIds = new String[] {userroleapiresponemediaadmin.get("roleId")};
	    addgroupbean.setRoleids(roleIds);
        
		EditGroupAPI editGroupAPI = new EditGroupAPI();
		JSONObject creategroupjsonedit=creategroupAPI.createGroupJson(addgroupbean);

		editGroupapiresponse = editGroupAPI.editGroup(loginapiresponse, creategroupjsonedit);
		assertionapiresponse.verifyAssert_httpCode(
		editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + editGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),
		HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + editGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);	
	    
		//editing of existing group roleid ended			
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengergroupspage =avengerHomePage.clickGroupsLink();
		avengergroupsbeanpage=new AvengerGroupsBeanPage();
		avengergroupsbeanpage.setNewgroup(addgroupbean.getName());
        customReport.customizedReport(true, avengergroupspage.verifyGroupsCreation(avengergroupsbeanpage).contains(avengergroupsbeanpage.getNewgroup()), statusValue, driver, sTestcaseName);  
        customReport.checkinglist(statusValue);
	}
		
	@Test(description = "To Verify the EditGroup API throws error when Media Admin tries to update the group",groups = {EDITGROUPAPI})
	public void TC_04_PUT_EditGroup_Api_check_With_MediaAdmin_Update_GroupName_Negative(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
		creategroupapirespone = creategroupAPI.createGroup(loginapiresponse,creategroupjson);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		//Login with Media admin
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.EditGroupMediAdmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);
				
		// Edit Group
		EditGroupAPI editGroupAPI = new EditGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		addgroupbean.setName("APIEDITName"+apiutils.randomString(5));
		
		loginapiresponse1.put("groupId", creategroupapirespone.get(IAPIConstantCodes.APIGROUPID));
		JSONObject creategroupjsonedit=creategroupAPI.createGroupJson(addgroupbean);

		editGroupapiresponse = editGroupAPI.editGroup(loginapiresponse1, creategroupjsonedit);		
		assertionapiresponse.verifyAssert_httpCode(editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	
	}
	
	@Test(description = "To Verify if Edit Group API response is succesful when Event Admin tries to update the group when assigned to a group",groups = {EDITGROUPAPI})
	public void TC_05_PUT_EditGroup_Api_check_With_EventAdmin_assignedtoagroup_havingAAaccess_Positive(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
		creategroupapirespone = creategroupAPI.createGroup(loginapiresponse,creategroupjson);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		//assigning event admin to a new group having account admin access
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.EditGroupEventAdmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);

		//getting the userIds
		CreateGroupAPI creategroupAPI1=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		String[] userIds = {loginapiresponse1.get("userId")};
		addgroupbean.setUserids(userIds);
								
		//getting roleids
		HashMap<String, String> userroleapirespone = userservices.getRolesApi(loginapiresponse,IAPIConstantCodes.ACCOUNTADMIN);
		String roleid=apiutils.formatingapiresponse(userroleapirespone.get(IAPIConstantCodes.ROLEID));
		String[] roleIds = {userroleapirespone.get("roleId")};
		addgroupbean.setRoleids(roleIds);
						
		JSONObject creategroupjson1=creategroupAPI1.createGroupJson(addgroupbean);
		creategroupapirespone1 = creategroupAPI1.createGroup(loginapiresponse,creategroupjson1);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone1.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		// Edit Group with event admin
		EditGroupAPI editGroupAPI = new EditGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		addgroupbean.setName("APIEDITName"+apiutils.randomString(5));
		
		loginapiresponse1.put("groupId", creategroupapirespone.get(IAPIConstantCodes.APIGROUPID));
		JSONObject creategroupjsonedit=creategroupAPI.createGroupJson(addgroupbean);
			
		editGroupapiresponse = editGroupAPI.editGroup(loginapiresponse1, creategroupjsonedit);		
		assertionapiresponse.verifyAssert_httpCode(editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + editGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + editGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);

		//Removing the assigned user event admin from Account Admin group
	    addgroupbean.setName("APIEDITName"+apiutils.randomString(5));
	    addgroupbean.setUserids(null);
	    addgroupbean.setRoleids(null);
   	    EditGroupAPI editGroupAPI1 = new EditGroupAPI();
		loginapiresponse.put("groupId", creategroupapirespone1.get(IAPIConstantCodes.APIGROUPID));
		JSONObject creategroupjsonedit1=creategroupAPI1.createGroupJson(addgroupbean);

		editGroupapiresponse = editGroupAPI.editGroup(loginapiresponse, creategroupjsonedit1);
		assertionapiresponse.verifyAssert_httpCode(editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + editGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),
		HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + editGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengergroupspage =avengerHomePage.clickGroupsLink();
		avengergroupsbeanpage=new AvengerGroupsBeanPage();
		avengergroupsbeanpage.setNewgroup(addgroupbean.getName());
        customReport.customizedReport(true, avengergroupspage.verifyGroupsCreation(avengergroupsbeanpage).contains(avengergroupsbeanpage.getNewgroup()), statusValue, driver, sTestcaseName);  
        customReport.checkinglist(statusValue);
	
	}
	
	@Test(description = "To Verify the EditGroup API throws error when Event Host tries to update the group",groups = {EDITGROUPAPI})
	public void TC_06_PUT_EditGroup_Api_check_With_EventHost_Update_GroupName_Negative(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
		creategroupapirespone = creategroupAPI.createGroup(loginapiresponse,creategroupjson);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		//Login with Event Host		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.EditGroupEventHost), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);
				
		// Edit Group
		EditGroupAPI editGroupAPI = new EditGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		addgroupbean.setName("APIEDITName"+apiutils.randomString(5));
		
		loginapiresponse1.put("groupId", creategroupapirespone.get(IAPIConstantCodes.APIGROUPID));
		JSONObject creategroupjsonedit=creategroupAPI.createGroupJson(addgroupbean);

		editGroupapiresponse = editGroupAPI.editGroup(loginapiresponse1, creategroupjsonedit);		
		assertionapiresponse.verifyAssert_httpCode(editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	
	}

	
	@Test(description = "To Verify the EditGroup API throws error when Media Contributor tries to update the group",groups = {EDITGROUPAPI})
	public void TC_07_PUT_EditGroup_Api_check_With_MediaContributor_Update_GroupName_Negative(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
		creategroupapirespone = creategroupAPI.createGroup(loginapiresponse,creategroupjson);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Login with Media Contributor		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.EditGroupMediaContributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);
				
		// Edit Group
		EditGroupAPI editGroupAPI = new EditGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		addgroupbean.setName("APIEDITName"+apiutils.randomString(5));
		
		loginapiresponse1.put("groupId", creategroupapirespone.get(IAPIConstantCodes.APIGROUPID));
		JSONObject creategroupjsonedit=creategroupAPI.createGroupJson(addgroupbean);

		editGroupapiresponse = editGroupAPI.editGroup(loginapiresponse1, creategroupjsonedit);		
		assertionapiresponse.verifyAssert_httpCode(editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	
	}

	@Test(description = "To Verify the EditGroup API throws error when Media Viewer tries to update the group",groups = {EDITGROUPAPI})
	public void TC_08_PUT_EditGroup_Api_check_With_MediaViewer_Update_GroupName_Negative(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
		creategroupapirespone = creategroupAPI.createGroup(loginapiresponse,creategroupjson);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		//Login with Event Host
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.EditGroupMediaViewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);
				
		// Edit Group
		EditGroupAPI editGroupAPI = new EditGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		addgroupbean.setName("APIEDITName"+apiutils.randomString(5));
		
		loginapiresponse1.put("groupId", creategroupapirespone.get(IAPIConstantCodes.APIGROUPID));
		JSONObject creategroupjsonedit=creategroupAPI.createGroupJson(addgroupbean);

		editGroupapiresponse = editGroupAPI.editGroup(loginapiresponse1, creategroupjsonedit);		
		assertionapiresponse.verifyAssert_httpCode(editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	
	}
	
	@Test(description="To Verify editGroup API with multiple userids and roleids ",groups = {EDITGROUPAPI})
	public void TC_09_PUT_EditGroup_Api_check_With_AccountAdmin_byprovidingmultiple_useridsandroleids_Positive(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
	
		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
	
		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
		creategroupapirespone = creategroupAPI.createGroup(loginapiresponse,creategroupjson);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
	    //editing existing group userid	
		loginapiresponse.put("groupId", creategroupapirespone.get(IAPIConstantCodes.APIGROUPID));		
		loginapiresponsemediacontributor = userservices.doLogin(apiutils.userJson(IUsersList.EditEventmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponsemediacontributor.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponsemediacontributor.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponsemediacontributor);
		
		EditGroupAPI editGroupAPI = new EditGroupAPI();				
		String[] userIds = {loginapiresponse.get("userId"),loginapiresponsemediacontributor.get("userId")};
		addgroupbean.setUserids(userIds);
		
		UserServices userServices=new UserServices();
		HashMap<String, String> userroleapirespone = userServices.getRolesApi(loginapiresponse,IAPIConstantCodes.ACCOUNTADMIN);	    	    
	    HashMap<String, String> userroleapirespone1 = userServices.getRolesApi(loginapiresponse,IAPIConstantCodes.MEDIAADMIN);
	    				
		String[] roleIds = {userroleapirespone.get("roleId"),userroleapirespone1.get("roleId")};
		addgroupbean.setRoleids(roleIds);
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
		avengergroupspage =avengerHomePage.clickGroupsLink();
		avengergroupsbeanpage=new AvengerGroupsBeanPage();
		avengergroupsbeanpage.setNewgroup(addgroupbean.getName());
        customReport.customizedReport(true, avengergroupspage.verifyGroupsCreation(avengergroupsbeanpage).contains(avengergroupsbeanpage.getNewgroup()), statusValue, driver, sTestcaseName);  
        customReport.checkinglist(statusValue);
				
	}
	
	
	@Test(description = "To Verify the EditGroup by giving invalid groupid with Account Admin",groups = {EDITGROUPAPI})
	public void TC_10_PUT_EditGroup_Api_check_With_AccountAdmin_byprovidinginvalidgroupid_Negative(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
	
		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
		creategroupapirespone = creategroupAPI.createGroup(loginapiresponse,creategroupjson);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			
		// Edit Group
		EditGroupAPI editGroupAPI = new EditGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		addgroupbean.setName("APIEDITName"+apiutils.randomString(5));
				
		loginapiresponse.put("groupId", creategroupapirespone.get(IAPIConstantCodes.APIGROUPID)+"invalid");
		JSONObject creategroupjsonedit=creategroupAPI.createGroupJson(addgroupbean);			
		editGroupapiresponse = editGroupAPI.editGroup(loginapiresponse, creategroupjsonedit);		
		assertionapiresponse.verifyAssert_httpCode(editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatus404);
		customReport.customizedReport(HttpStatusCode.httpsStatus404, editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
			
	}

	@Test(description="To Verify editGroup throws error when few valid and few invalid userids are given ",groups = {EDITGROUPAPI})
	public void TC_11_PUT_EditGroup_Api_check_With_AccountAdmin_byprovidinginvaliduserid_Negative(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);

		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
		creategroupapirespone = creategroupAPI.createGroup(loginapiresponse,creategroupjson);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
	    //editing existing group userid		
		loginapiresponse.put("groupId", creategroupapirespone.get(IAPIConstantCodes.APIGROUPID));		
		loginapiresponsemediacontributor = userservices.doLogin(apiutils.userJson(IUsersList.EditEventmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponsemediacontributor.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponsemediacontributor.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponsemediacontributor);
		
		EditGroupAPI editGroupAPI = new EditGroupAPI();	
		String[] userIds = {loginapiresponse.get("userId"),loginapiresponsemediacontributor.get("userId")+"a"};
		addgroupbean.setUserids(userIds);
		
		UserServices userServices=new UserServices();
		HashMap<String, String> userroleapirespone = userServices.getRolesApi(loginapiresponse,IAPIConstantCodes.ACCOUNTADMIN);	    	    
	    HashMap<String, String> userroleapirespone1 = userServices.getRolesApi(loginapiresponse,IAPIConstantCodes.MEDIAADMIN);
	    				
		String[] roleIds = {userroleapirespone.get("roleId"),userroleapirespone1.get("roleId")};
		addgroupbean.setRoleids(roleIds);
		JSONObject creategroupjsonedit=creategroupAPI.createGroupJson(addgroupbean);

		editGroupapiresponse = editGroupAPI.editGroup(loginapiresponse, creategroupjsonedit);
		assertionapiresponse.verifyAssert_httpCode(editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatus500);
		customReport.customizedReport(HttpStatusCode.httpsStatus500, editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.customizedReport( HttpStatusCode.errordetail, editGroupapiresponse.get(IAPIConstantCodes.APIResponseJsonErrorCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description = "To Verify the EditGroup for groupname without giving mandatory field with Account Admin",groups = {EDITGROUPAPI})
	public void TC_12_PUT_EditGroup_Api_check_With_AccountAdmin_withoutmandatoryfield_Negative(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		apibeanfactory.CreateGroupBean(addgroupbean1);
		
		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
		JSONObject creategroupjson1=creategroupAPI.createGroupJson(addgroupbean1);
		creategroupapirespone = creategroupAPI.createGroup(loginapiresponse,creategroupjson);
		creategroupapirespone1 = creategroupAPI.createGroup(loginapiresponse,creategroupjson1);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone1.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		// Edit Group
		EditGroupAPI editGroupAPI = new EditGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		
		//addgroupbean.setName("APIEDITName"+apiutils.randomString(5));
		addgroupbean.setName("");
		
		loginapiresponse.put("groupId", creategroupapirespone.get(IAPIConstantCodes.APIGROUPID));
		JSONObject creategroupjsonedit=creategroupAPI.createGroupJson(addgroupbean);
			
		editGroupapiresponse = editGroupAPI.editGroup(loginapiresponse, creategroupjsonedit);		
		assertionapiresponse.verifyAssert_httpCode(editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatus500);
		customReport.customizedReport(HttpStatusCode.httpsStatus500, editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.customizedReport(editGroupapiresponse.get(IAPIConstantCodes.APIResponseJsonErrorCode), HttpStatusCode.missingCode, statusValue, driver, sTestcaseName);
		customReport.customizedReport(editGroupapiresponse.get(IAPIConstantCodes.APIResponseJsonErrorDetail), HttpStatusCode.missingDetail, statusValue, driver, sTestcaseName);
        customReport.checkinglist(statusValue);
			
	}

	@Test(description="To Verify editGroup with only mandatory fields update with Account Admin ",groups = {EDITGROUPAPI})
	public void TC_13_PUT_EditGroup_Api_check_With_AccountAdmin_withNameUpdate_Positive(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		//getting the userIds
		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
			
		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
		creategroupapirespone = creategroupAPI.createGroup(loginapiresponse,creategroupjson);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
	    //addgroupbean.setUserids(loginapiresponsemediacontributor.get("userId"));
		addgroupbean.setName("APIEDITName"+apiutils.randomString(5));
		
		EditGroupAPI editGroupAPI = new EditGroupAPI();
		loginapiresponse.put("groupId", creategroupapirespone.get(IAPIConstantCodes.APIGROUPID));
		JSONObject creategroupjsonedit=creategroupAPI.createGroupJson(addgroupbean);

		editGroupapiresponse = editGroupAPI.editGroup(loginapiresponse, creategroupjsonedit);
		assertionapiresponse.verifyAssert_httpCode(
		editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + editGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),
		HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + editGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);
	
	    //editing of existing group userid ended			
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengergroupspage =avengerHomePage.clickGroupsLink();
		avengergroupsbeanpage=new AvengerGroupsBeanPage();
		avengergroupsbeanpage.setNewgroup(addgroupbean.getName());
        customReport.customizedReport(true, avengergroupspage.verifyGroupsCreation(avengergroupsbeanpage).contains(avengergroupsbeanpage.getNewgroup()), statusValue, driver, sTestcaseName);  
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
	
}