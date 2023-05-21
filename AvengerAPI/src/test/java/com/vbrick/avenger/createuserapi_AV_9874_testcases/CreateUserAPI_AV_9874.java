package com.vbrick.avenger.createuserapi_AV_9874_testcases;


import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.testng.Assert;
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
import com.vbrick.avenger.apibeans.AddUserApiBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AddUserBeanPage;
import com.vbrick.avenger.dao.AvengerGroupsBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerEditRootAccountPage;
import com.vbrick.avenger.pageobjects.AvengerGroupsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerUserDashboardPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IUserAccountsService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.CreateGroupAPI;
import com.vbricks.avenger.serviceimpl.DeleteGroupAPI;
import com.vbricks.avenger.serviceimpl.UserAccountService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.ConstantsValue;
import com.vbricks.avenger.utils.HttpStatusCode;

public class CreateUserAPI_AV_9874 extends TestBase {
		
	private static Logger logger = Logger.getLogger(CreateUserAPI_AV_9874.class);
	private AvengerLoginPage loginPage;
	private List<String> statusValue;
	private CustomReport customReport;
	@SuppressWarnings("unused")
	private Reasons reasons;
	private String sTestcaseName;
	private ResourceBundle bundle;
	private Locale locale;
	private BeanFactory accountBeansFactory;
	private MailinatorBeanPage mailinatorbeanpage;
	private AddGroupBean addgroupbean;
	private ReadAndWriteToJSON readgriduserdata;
	private Map<String, String> userdata;
	private BasePage basePage;
	public ApiBeanFactory apibeanfactory;
	private ApiUtils apiutils;
	private AvengerHomePage avengerHomePage;
	private AvengerUserDashboardPage avengeruserdashboardpage;
	private AvengerGroupsBeanPage avengergroupsbeanpage;
	private AvengerEditRootAccountPage avengereditrootaccountpage; 
	private HashMap<String, String> creategroupapirespone;
	private HashMap<String,String> loginapiresponse;
	private HashMap<String,String> createuserdetailsbean;
	private AssertionAPIResponse assertionapiresponse;
	@BeforeClass(alwaysRun=true)
	public void beforeClass() throws Exception {
		reasons= new Reasons("");
		statusValue=new ArrayList<String>();
		customReport = new CustomReport();
		accountBeansFactory=new BeanFactory();
		mailinatorbeanpage=new MailinatorBeanPage();
		new FileUploadBeanPage();
		locale = new Locale(language);
		readgriduserdata = new ReadAndWriteToJSON();
		addgroupbean=new AddGroupBean();
		userdata = readgriduserdata.readGridUserData(this.getClass().getSimpleName()); 
		sUserName = userdata.get("Username");
		sPassword = userdata.get("Password");
		apiutils=new ApiUtils();
		assertionapiresponse=new AssertionAPIResponse();
		avengergroupsbeanpage = new AvengerGroupsBeanPage();
 	}
	
	@BeforeMethod(alwaysRun=true)
	@Parameters(value = {"sbrowser","sgrid"})
	public void setUP(@Optional(SBROWSER)String sbrowser,@Optional(SVERSION) String sgrid) throws MalformedURLException 
	{
		customReport.reset();
		driver = initializeDriver(sbrowser,sgrid);
		logger.info("The driver value is "+driver);
		bundle=ResourceBundle.getBundle("ResourceBundle.BundleFile",locale);
		logger.info("value in bundle is"+bundle.getKeys());
		basePage= new BasePage(driver,customReport, new BasePage());
		loginPage = basePage.avengerLoginPage(driver,customReport, basePage);
		driver.manage().window().maximize();
		accountBeansFactory.MailinatorBean(mailinatorbeanpage);
		accountBeansFactory.AvengergroupsBean(avengergroupsbeanpage);
		
	}
	
	@Test(description="To create account admin user with only mandatory fields",groups = {CREATEUSERAPI})
	public void TC_01_POST_Create_Account_Adminuser_withonlymandatoryfields_Postive() 
	{
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Add NewUser API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
 		
		HashMap<String, String> responsedata = userservices.getRolesApi(loginapiresponse, ConstantsValue.accountAdmin);
		UserAccountService useraccountservice = new UserAccountService();
		ApiBeanFactory bFactory = new ApiBeanFactory();
		AddUserApiBean userbean = new AddUserApiBean();
		bFactory.AddUserBean(userbean);
		// Convert bean to map
		
		ObjectMapper mapper = new ObjectMapper();
		createuserdetailsbean = mapper.convertValue(userbean, HashMap.class);
		
		HashMap<String,String> usercreateApiResponseData=useraccountservice.createUserAPI(responsedata, ConstantsValue.inputType[0], ConstantsValue.mandatoryFields, createuserdetailsbean);
        Assert.assertEquals(usercreateApiResponseData.get( IAPIConstantCodes.APIResponseHttpCode)+usercreateApiResponseData.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    Assert.assertNotNull(usercreateApiResponseData.get(IUserAccountsService.USERID));
	 
        //Selenium Level Code
		launchURL(surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengeruserdashboardpage=avengerHomePage.clickUsersLink();
		AddUserBeanPage adduserbean=new AddUserBeanPage();
		adduserbean.setFirstname(createuserdetailsbean.get(IUserAccountsService.FIRSTNAME));
		adduserbean.setLastname(createuserdetailsbean.get(IUserAccountsService.LASTNAME));
		adduserbean.setUsername(createuserdetailsbean.get(IUserAccountsService.USERNAME));
		adduserbean.setPhoneno(createuserdetailsbean.get(IUserAccountsService.PHONENO));
		adduserbean.setUserrole(createuserdetailsbean.get(IUserAccountsService.USERROLE));
		adduserbean.setContactemail(createuserdetailsbean.get(IUserAccountsService.CONTACTEMAIL));
		adduserbean.setTitle(createuserdetailsbean.get(IUserAccountsService.TITLE));
		String userStatus=avengeruserdashboardpage.get_UserStatus(createuserdetailsbean.get(IUserAccountsService.USERNAME));
		avengereditrootaccountpage=avengeruserdashboardpage.searchandclicknewuser_link(adduserbean);
		HashMap<String, String> uservalues=avengereditrootaccountpage.get_allUserfieldValues();
		ArrayList<String> roles=avengereditrootaccountpage.get_allassignedroles();
		int noofRoles = roles.size();
		customReport.customizedReport(adduserbean.getLastname(), uservalues.get(IUserAccountsService.LASTNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getUsername(), uservalues.get(IUserAccountsService.USERNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(1,noofRoles, statusValue, driver, sTestcaseName);
		customReport.customizedReport(true,roles.contains("Account Admin"), statusValue, driver, sTestcaseName);
		customReport.customizedReport("Unlicensed",userStatus, statusValue, driver, sTestcaseName); 
		customReport.checkinglist(statusValue);
		
	}
	
	
	@Test(description="To create Media Admin user with only mamdantory fields ",groups = {CREATEUSERAPI})
	public void TC_02_POST_Create_User_withoutrole_withonlymandatoryfields_Postive() {
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();		
		customReport.reporter("Add NewUser API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
 		
		HashMap<String, String> responsedata = userservices.getRolesApi(loginapiresponse, ConstantsValue.mediaAdmin);
		UserAccountService useraccountservice = new UserAccountService();
		ApiBeanFactory bFactory = new ApiBeanFactory();
		AddUserApiBean userbean = new AddUserApiBean();
		bFactory.AddUserBean(userbean);
		// Convert bean to map
		ObjectMapper mapper = new ObjectMapper();
		createuserdetailsbean = mapper.convertValue(userbean, HashMap.class);

		HashMap<String,String> usercreateApiResponseData=useraccountservice.createUserAPI(responsedata, ConstantsValue.inputType[0], ConstantsValue.mandatoryFieldswithoutrole, createuserdetailsbean);
        Assert.assertEquals(usercreateApiResponseData.get( IAPIConstantCodes.APIResponseHttpCode)+usercreateApiResponseData.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    Assert.assertNotNull(usercreateApiResponseData.get(IUserAccountsService.USERID));
	 	 
        //Selenium Level Code
		launchURL(surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengeruserdashboardpage=avengerHomePage.clickUsersLink();
		AddUserBeanPage adduserbean=new AddUserBeanPage();
		adduserbean.setLastname(createuserdetailsbean.get(IUserAccountsService.LASTNAME));
		adduserbean.setUsername(createuserdetailsbean.get(IUserAccountsService.USERNAME));
		adduserbean.setContactemail(createuserdetailsbean.get(IUserAccountsService.CONTACTEMAIL));
		adduserbean.setLanguage("None");
		adduserbean.setLanguage(createuserdetailsbean.get(IUserAccountsService.LANGUAGE));
		String userStatus=avengeruserdashboardpage.get_UserStatus(createuserdetailsbean.get(IUserAccountsService.USERNAME));
		avengereditrootaccountpage=avengeruserdashboardpage.searchandclicknewuser_link(adduserbean);
		HashMap<String, String> uservalues=avengereditrootaccountpage.get_allUserfieldValues();
		ArrayList<String> roles=avengereditrootaccountpage.get_allassignedroles();
		int noofRoles = roles.size();
		customReport.customizedReport(adduserbean.getLastname(), uservalues.get(IUserAccountsService.LASTNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getUsername(), uservalues.get(IUserAccountsService.USERNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(1,noofRoles, statusValue, driver, sTestcaseName);
		customReport.customizedReport(true,roles.contains("Media Viewer"), statusValue, driver, sTestcaseName);
		customReport.customizedReport("Unlicensed",userStatus, statusValue, driver, sTestcaseName); 
		customReport.checkinglist(statusValue);
			
	}

	
	@Test(description="To create Event Admin user with mandatory fields",groups = {CREATEUSERAPI})
	public void TC_03_POST_Create_Event_Admin_User_withmandatoryfields_Postive() {
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();		
		customReport.reporter("Add NewUser API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
 		
		HashMap<String, String> responsedata = userservices.getRolesApi(loginapiresponse, ConstantsValue.eventAdmin);
		UserAccountService useraccountservice = new UserAccountService();
		ApiBeanFactory bFactory = new ApiBeanFactory();
		AddUserApiBean userbean = new AddUserApiBean();
		bFactory.AddUserBean(userbean);
		// Convert bean to map	
		ObjectMapper mapper = new ObjectMapper();
		createuserdetailsbean = mapper.convertValue(userbean, HashMap.class);

		HashMap<String,String> usercreateApiResponseData=useraccountservice.createUserAPI(responsedata, ConstantsValue.inputType[0], ConstantsValue.mandatoryFields, createuserdetailsbean);
        Assert.assertEquals(usercreateApiResponseData.get( IAPIConstantCodes.APIResponseHttpCode)+usercreateApiResponseData.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    Assert.assertNotNull(usercreateApiResponseData.get(IUserAccountsService.USERID));
	 	 
        //Selenium Level Code
		launchURL(surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengeruserdashboardpage=avengerHomePage.clickUsersLink();
		AddUserBeanPage adduserbean=new AddUserBeanPage();
		adduserbean.setLastname(createuserdetailsbean.get(IUserAccountsService.LASTNAME));
		adduserbean.setUsername(createuserdetailsbean.get(IUserAccountsService.USERNAME));
		adduserbean.setContactemail(createuserdetailsbean.get(IUserAccountsService.CONTACTEMAIL));
		adduserbean.setLanguage("None");
		adduserbean.setLanguage(createuserdetailsbean.get(IUserAccountsService.LANGUAGE));
		String userStatus=avengeruserdashboardpage.get_UserStatus(createuserdetailsbean.get(IUserAccountsService.USERNAME));
		avengereditrootaccountpage=avengeruserdashboardpage.searchandclicknewuser_link(adduserbean);
		HashMap<String, String> uservalues=avengereditrootaccountpage.get_allUserfieldValues();
		ArrayList<String> roles=avengereditrootaccountpage.get_allassignedroles();
		int noofRoles = roles.size();
		customReport.customizedReport(adduserbean.getLastname(), uservalues.get(IUserAccountsService.LASTNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getUsername(), uservalues.get(IUserAccountsService.USERNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(1,noofRoles, statusValue, driver, sTestcaseName);
		customReport.customizedReport(true,roles.contains("Event Admin"), statusValue, driver, sTestcaseName);
		customReport.customizedReport("None",uservalues.get("preferredlanguage"), statusValue, driver, sTestcaseName);
		customReport.customizedReport("Unlicensed",userStatus, statusValue, driver, sTestcaseName); 
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description="To create Event Host user with only mandatory fields",groups = {CREATEUSERAPI})
	public void TC_04_POST_Create_Event_Host_User_withonlymandatoryfields_Postive() {
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();		
		customReport.reporter("Add NewUser API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
 		
		HashMap<String, String> responsedata = userservices.getRolesApi(loginapiresponse, ConstantsValue.eventHost);
		UserAccountService useraccountservice = new UserAccountService();
		ApiBeanFactory bFactory = new ApiBeanFactory();
		AddUserApiBean userbean = new AddUserApiBean();
		bFactory.AddUserBean(userbean);
		// Convert bean to map		
		ObjectMapper mapper = new ObjectMapper();
		createuserdetailsbean = mapper.convertValue(userbean, HashMap.class);

		HashMap<String,String> usercreateApiResponseData=useraccountservice.createUserAPI(responsedata, ConstantsValue.inputType[0], ConstantsValue.mandatoryFields, createuserdetailsbean);
        Assert.assertEquals(usercreateApiResponseData.get( IAPIConstantCodes.APIResponseHttpCode)+usercreateApiResponseData.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    Assert.assertNotNull(usercreateApiResponseData.get(IUserAccountsService.USERID));
	 	 
        //Selenium Level Code
		launchURL(surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengeruserdashboardpage=avengerHomePage.clickUsersLink();
		AddUserBeanPage adduserbean=new AddUserBeanPage();
		adduserbean.setLastname(createuserdetailsbean.get(IUserAccountsService.LASTNAME));
		adduserbean.setUsername(createuserdetailsbean.get(IUserAccountsService.USERNAME));
		adduserbean.setContactemail(createuserdetailsbean.get(IUserAccountsService.CONTACTEMAIL));
		adduserbean.setLanguage("None");
		adduserbean.setLanguage(createuserdetailsbean.get(IUserAccountsService.LANGUAGE));
		String userStatus=avengeruserdashboardpage.get_UserStatus(createuserdetailsbean.get(IUserAccountsService.USERNAME));
		avengereditrootaccountpage=avengeruserdashboardpage.searchandclicknewuser_link(adduserbean);
		HashMap<String, String> uservalues=avengereditrootaccountpage.get_allUserfieldValues();
		ArrayList<String> roles=avengereditrootaccountpage.get_allassignedroles();
		int noofRoles = roles.size();
		customReport.customizedReport(adduserbean.getLastname(), uservalues.get(IUserAccountsService.LASTNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getUsername(), uservalues.get(IUserAccountsService.USERNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(1,noofRoles, statusValue, driver, sTestcaseName);
		customReport.customizedReport(true,roles.contains("Event Host"), statusValue, driver, sTestcaseName);
		customReport.customizedReport("None",uservalues.get("preferredlanguage"), statusValue, driver, sTestcaseName);
		customReport.customizedReport("Unlicensed",userStatus, statusValue, driver, sTestcaseName); 
		customReport.checkinglist(statusValue);
			
	}

	@Test(description="To create media Contributor user with exisitng email id",groups = {CREATEUSERAPI})
	public void TC_05_POST_Create_Media_Contributor_User_withexistingemailid_Negative() 
	{
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Add NewUser API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
 		
		HashMap<String, String> responsedata = userservices.getRolesApi(loginapiresponse, ConstantsValue.mediaContributor);
		UserAccountService useraccountservice = new UserAccountService();
		ApiBeanFactory bFactory = new ApiBeanFactory();
		AddUserApiBean userbean = new AddUserApiBean();
		bFactory.AddUserBean(userbean);
		userbean.setContactemail("rajitha.dronadala@vbrick.com");
		// Convert bean to map
		ObjectMapper mapper = new ObjectMapper();
		createuserdetailsbean = mapper.convertValue(userbean, HashMap.class);

		HashMap<String,String> usercreateApiResponseData=useraccountservice.createUserAPI(responsedata, ConstantsValue.inputType[0], ConstantsValue.validData, createuserdetailsbean);
		assertionapiresponse.verifyAssert_httpCode(usercreateApiResponseData.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus500);
		customReport.customizedReport(HttpStatusCode.httpsStatus500, usercreateApiResponseData.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.customizedReport(usercreateApiResponseData.get(IAPIConstantCodes.APIResponseJsonErrorCode), HttpStatusCode.errorcode1, statusValue, driver, sTestcaseName);
		customReport.customizedReport(usercreateApiResponseData.get(IAPIConstantCodes.APIResponseJsonErrorDetail), HttpStatusCode.detailErrorExistingEmail, statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
	}

	@Test(description="To create Media Viewer user with only mandatory fields",groups = {CREATEUSERAPI})
	public void TC_06_POST_Create_Media_Viewer_User_withonlymandatoryfields_Postive()
	{
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();	
		customReport.reporter("Add NewUser API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);;
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
 		
		HashMap<String, String> responsedata = userservices.getRolesApi(loginapiresponse, ConstantsValue.mediaViewer);
		UserAccountService useraccountservice = new UserAccountService();
		ApiBeanFactory bFactory = new ApiBeanFactory();
		AddUserApiBean userbean = new AddUserApiBean();
		bFactory.AddUserBean(userbean);
		// Convert bean to map
		ObjectMapper mapper = new ObjectMapper();
		createuserdetailsbean = mapper.convertValue(userbean, HashMap.class);

		HashMap<String,String> usercreateApiResponseData=useraccountservice.createUserAPI(responsedata, ConstantsValue.inputType[0], ConstantsValue.mandatoryFields, createuserdetailsbean);
        Assert.assertEquals(usercreateApiResponseData.get( IAPIConstantCodes.APIResponseHttpCode)+usercreateApiResponseData.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    Assert.assertNotNull(usercreateApiResponseData.get(IUserAccountsService.USERID));
	 	 
        //Selenium Level Code
		launchURL(surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengeruserdashboardpage=avengerHomePage.clickUsersLink();
		AddUserBeanPage adduserbean=new AddUserBeanPage();
		adduserbean.setLastname(createuserdetailsbean.get(IUserAccountsService.LASTNAME));
		adduserbean.setUsername(createuserdetailsbean.get(IUserAccountsService.USERNAME));
		adduserbean.setContactemail(createuserdetailsbean.get(IUserAccountsService.CONTACTEMAIL));
		adduserbean.setLanguage(createuserdetailsbean.get(IUserAccountsService.LANGUAGE));
		String userStatus=avengeruserdashboardpage.get_UserStatus(createuserdetailsbean.get(IUserAccountsService.USERNAME));
		avengereditrootaccountpage=avengeruserdashboardpage.searchandclicknewuser_link(adduserbean);
		HashMap<String, String> uservalues=avengereditrootaccountpage.get_allUserfieldValues();
		ArrayList<String> roles=avengereditrootaccountpage.get_allassignedroles();
		int noofRoles = roles.size();
		customReport.customizedReport(adduserbean.getLastname(), uservalues.get(IUserAccountsService.LASTNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getUsername(), uservalues.get(IUserAccountsService.USERNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(1,noofRoles, statusValue, driver, sTestcaseName);
		customReport.customizedReport(true,roles.contains("Media Viewer"), statusValue, driver, sTestcaseName);
		customReport.customizedReport("None",uservalues.get("preferredlanguage"), statusValue, driver, sTestcaseName);
		customReport.customizedReport("Unlicensed",userStatus, statusValue, driver, sTestcaseName); 
		customReport.checkinglist(statusValue);
			
	}

	
	
	@Test(description="To create Account Admin user with all the fields",groups = {CREATEUSERAPI})
	public void TC_07_POST_Create_Account_Admin_User_withallthefields_Postive() {
		
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Add NewUser API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
 		
		HashMap<String, String> responsedata = userservices.getRolesApi(loginapiresponse, ConstantsValue.accountAdmin);		
		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		ApiBeanFactory bFactory = new ApiBeanFactory();
		bFactory.CreateGroupBean(addgroupbean);
		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
		creategroupapirespone = creategroupAPI.createGroup(loginapiresponse,creategroupjson);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		responsedata.put("groupId", creategroupapirespone.get("groupId"));
		
		UserAccountService useraccountservice = new UserAccountService();
		AddUserApiBean userbean = new AddUserApiBean();
		bFactory.AddUserBean(userbean);
		// Convert bean to map
		ObjectMapper mapper = new ObjectMapper();
		createuserdetailsbean = mapper.convertValue(userbean, HashMap.class);
						
		HashMap<String,String> usercreateApiResponseData=useraccountservice.createUserAPI(responsedata, ConstantsValue.inputType[0], ConstantsValue.validDatawithgrp, createuserdetailsbean);
        Assert.assertEquals(usercreateApiResponseData.get( IAPIConstantCodes.APIResponseHttpCode)+usercreateApiResponseData.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    Assert.assertNotNull(usercreateApiResponseData.get(IUserAccountsService.USERID));
	 
        //Selenium Level Code
		launchURL(surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengeruserdashboardpage=avengerHomePage.clickUsersLink();
		AddUserBeanPage adduserbean=new AddUserBeanPage();
		adduserbean.setFirstname(createuserdetailsbean.get(IUserAccountsService.FIRSTNAME));
		adduserbean.setLastname(createuserdetailsbean.get(IUserAccountsService.LASTNAME));
		adduserbean.setUsername(createuserdetailsbean.get(IUserAccountsService.USERNAME));
		adduserbean.setPhoneno(createuserdetailsbean.get(IUserAccountsService.PHONENO));
		adduserbean.setUserrole(createuserdetailsbean.get(IUserAccountsService.USERROLE));
		adduserbean.setContactemail(createuserdetailsbean.get(IUserAccountsService.CONTACTEMAIL));
		adduserbean.setLanguage(createuserdetailsbean.get(IUserAccountsService.LANGUAGE));
		adduserbean.setTitle(createuserdetailsbean.get(IUserAccountsService.TITLE));
		String userStatus=avengeruserdashboardpage.get_UserStatus(createuserdetailsbean.get(IUserAccountsService.USERNAME));
		avengereditrootaccountpage=avengeruserdashboardpage.searchandclicknewuser_link(adduserbean);
		HashMap<String, String> uservalues=avengereditrootaccountpage.get_allUserfieldValues();
		ArrayList<String> roles=avengereditrootaccountpage.get_allassignedroles();
		int noofRoles = roles.size();
		customReport.customizedReport(adduserbean.getLastname(), uservalues.get(IUserAccountsService.LASTNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getFirstname(), uservalues.get(IUserAccountsService.FIRSTNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getTitle(), uservalues.get(IUserAccountsService.TITLE), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getContactemail(), uservalues.get(IUserAccountsService.CONTACTEMAIL), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getUsername(), uservalues.get(IUserAccountsService.USERNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(1,noofRoles, statusValue, driver, sTestcaseName);
		customReport.customizedReport(true,roles.contains("Account Admin"), statusValue, driver, sTestcaseName);
		customReport.customizedReport("Unlicensed",userStatus, statusValue, driver, sTestcaseName); 
		customReport.checkinglist(statusValue);
				
		// Delete Group
		loginapiresponse.put(IAPIConstantCodes.APIResponseGROUPID, creategroupapirespone.get(IAPIConstantCodes.APIGROUPID));
		DeleteGroupAPI deleteGroupAPI = new DeleteGroupAPI();
		HashMap<String, String> deleteGroupapiresponse = deleteGroupAPI.deleteGroups(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deleteGroupapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);
								
	}
	
	@Test(description="To create Account Admin user with all the fields except for role ids and lang as German",groups = {CREATEUSERAPI})
	public void TC_08_POST_Create_Account_Admin_User_withallthefields_langasgerman_Postive() {
		
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Add NewUser API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);		
		
		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		ApiBeanFactory bFactory = new ApiBeanFactory();
		bFactory.CreateGroupBean(addgroupbean);
		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
		HashMap<String, String> responsedata = creategroupAPI.createGroup(loginapiresponse,creategroupjson);
		assertionapiresponse.verifyAssert_httpCode(responsedata.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, responsedata.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UserAccountService useraccountservice = new UserAccountService();
		AddUserApiBean userbean = new AddUserApiBean();
		bFactory.AddUserBean(userbean);
		userbean.setLanguage("de");
		// Convert bean to map
		ObjectMapper mapper = new ObjectMapper();
		createuserdetailsbean = mapper.convertValue(userbean, HashMap.class);
						
		HashMap<String,String> usercreateApiResponseData=useraccountservice.createUserAPI(responsedata, ConstantsValue.inputType[0], ConstantsValue.validDatawithgrp1, createuserdetailsbean);
        Assert.assertEquals(usercreateApiResponseData.get( IAPIConstantCodes.APIResponseHttpCode)+usercreateApiResponseData.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    Assert.assertNotNull(usercreateApiResponseData.get(IUserAccountsService.USERID));
	 
        //Selenium Level Code
		launchURL(surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengeruserdashboardpage=avengerHomePage.clickUsersLink();
		AddUserBeanPage adduserbean=new AddUserBeanPage();
		adduserbean.setFirstname(createuserdetailsbean.get(IUserAccountsService.FIRSTNAME));
		adduserbean.setLastname(createuserdetailsbean.get(IUserAccountsService.LASTNAME));
		adduserbean.setUsername(createuserdetailsbean.get(IUserAccountsService.USERNAME));
		adduserbean.setPhoneno(createuserdetailsbean.get(IUserAccountsService.PHONENO));
		adduserbean.setUserrole(createuserdetailsbean.get(IUserAccountsService.USERROLE));
		adduserbean.setContactemail(createuserdetailsbean.get(IUserAccountsService.CONTACTEMAIL));
		adduserbean.setTitle(createuserdetailsbean.get(IUserAccountsService.TITLE));
		String userStatus=avengeruserdashboardpage.get_UserStatus(createuserdetailsbean.get(IUserAccountsService.USERNAME));
		avengereditrootaccountpage=avengeruserdashboardpage.searchandclicknewuser_link(adduserbean);
		HashMap<String, String> uservalues=avengereditrootaccountpage.get_allUserfieldValues();
		ArrayList<String> roles=avengereditrootaccountpage.get_allassignedroles();
		int noofRoles = roles.size();
		customReport.customizedReport(adduserbean.getLastname(), uservalues.get(IUserAccountsService.LASTNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getContactemail(), uservalues.get(IUserAccountsService.CONTACTEMAIL), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getFirstname(), uservalues.get(IUserAccountsService.FIRSTNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getUsername(), uservalues.get(IUserAccountsService.USERNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(1,noofRoles, statusValue, driver, sTestcaseName);
		customReport.customizedReport(true,roles.contains("Media Viewer"), statusValue, driver, sTestcaseName);
		customReport.customizedReport("German",uservalues.get("preferredlanguage"), statusValue, driver, sTestcaseName);
		customReport.customizedReport("Unlicensed",userStatus, statusValue, driver, sTestcaseName); 
		customReport.checkinglist(statusValue);
				
	}
	
				
	@Test(description = "To Create user without Mandatory filed UserName    ...",groups = {CREATEUSERAPI})
	public void TC_09_POST_CreateUser_with_Blank_UserName_Negative() {

		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();	
		customReport.reporter("Add NewUser API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
 		
		HashMap<String, String> responsedata = userservices.getRolesApi(loginapiresponse, ConstantsValue.mediaContributor);
		UserAccountService useraccountservice = new UserAccountService();
		ApiBeanFactory bFactory = new ApiBeanFactory();
		AddUserApiBean userbean = new AddUserApiBean();
		bFactory.AddUserBean(userbean);
		// Convert bean to map
		ObjectMapper mapper = new ObjectMapper();

		createuserdetailsbean = mapper.convertValue(userbean, HashMap.class);
		HashMap<String, String> usercreateApiResponseData = useraccountservice.createUserAPI(responsedata, ConstantsValue.inputType[1],ConstantsValue.blankUserName, createuserdetailsbean);
		assertionapiresponse.verifyAssert_httpCode(usercreateApiResponseData.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus500);
		customReport.customizedReport(HttpStatusCode.httpsStatus500, usercreateApiResponseData.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.customizedReport(usercreateApiResponseData.get(IAPIConstantCodes.APIResponseJsonErrorCode), HttpStatusCode.errorcode, statusValue, driver, sTestcaseName);
		customReport.customizedReport(usercreateApiResponseData.get(IAPIConstantCodes.APIResponseJsonErrorDetail), HttpStatusCode.detailErrorUserName, statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = "To Create user without Mandatory filed LastName    ...",groups = {CREATEUSERAPI})
	public void TC_10_POST_LastName_with_Blank_LastName_Negative() {
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();		
		customReport.reporter("Add NewUser API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
 				
		HashMap<String, String> responsedata = userservices.getRolesApi(loginapiresponse, ConstantsValue.mediaContributor);
		UserAccountService useraccountservice = new UserAccountService();
		ApiBeanFactory bFactory = new ApiBeanFactory();
		AddUserApiBean userbean = new AddUserApiBean();
		bFactory.AddUserBean(userbean);
		// Convert bean to map
		ObjectMapper mapper = new ObjectMapper();
		createuserdetailsbean = mapper.convertValue(userbean, HashMap.class);
		HashMap<String, String> usercreateApiResponseData = useraccountservice.createUserAPI(responsedata, ConstantsValue.inputType[1],ConstantsValue.blankLastName, createuserdetailsbean);
		assertionapiresponse.verifyAssert_httpCode(usercreateApiResponseData.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus500);
		customReport.customizedReport(HttpStatusCode.httpsStatus500, usercreateApiResponseData.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.customizedReport(usercreateApiResponseData.get(IAPIConstantCodes.APIResponseJsonErrorCode), HttpStatusCode.errorcode, statusValue, driver, sTestcaseName);
		customReport.customizedReport(usercreateApiResponseData.get(IAPIConstantCodes.APIResponseJsonErrorDetail), HttpStatusCode.detailErrorLastName, statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		 
	}

	@Test(description = "To Create user without Optional field ContactEmail    ...",groups = {CREATEUSERAPI})
	public void TC_11_POST_ContactEmail_with_Blank_ContactEmail_Positive() {

		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Add NewUser API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
 		
		
		HashMap<String, String> responsedata = userservices.getRolesApi(loginapiresponse, ConstantsValue.eventAdmin);
		UserAccountService useraccountservice = new UserAccountService();
		ApiBeanFactory bFactory = new ApiBeanFactory();
		AddUserApiBean userbean = new AddUserApiBean();
		bFactory.AddUserBean(userbean);
		// Convert bean to map
		ObjectMapper mapper = new ObjectMapper();

		createuserdetailsbean = mapper.convertValue(userbean, HashMap.class);
		HashMap<String, String> usercreateApiResponseData = useraccountservice.createUserAPI(responsedata, ConstantsValue.inputType[0],ConstantsValue.mandatoryFields,createuserdetailsbean);		
        Assert.assertEquals(usercreateApiResponseData.get( IAPIConstantCodes.APIResponseHttpCode)+usercreateApiResponseData.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    Assert.assertNotNull(usercreateApiResponseData.get(IUserAccountsService.USERID));	    
		customReport.checkinglist(statusValue);
	} 
	
	
	@Test(description="To create Media Viewer user with Media Admin credentials ",groups = {CREATEUSERAPI})
	public void TC_12_POST_Create_Media_Viewer_User_withmediadmincredentials_Postive()
	{
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Add NewUser API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.loginmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
 		
		HashMap<String, String> responsedata = userservices.getRolesApi(loginapiresponse, ConstantsValue.mediaViewer);
		UserAccountService useraccountservice = new UserAccountService();
		ApiBeanFactory bFactory = new ApiBeanFactory();
		AddUserApiBean userbean = new AddUserApiBean();
		bFactory.AddUserBean(userbean);
		// Convert bean to map
		ObjectMapper mapper = new ObjectMapper();
		createuserdetailsbean = mapper.convertValue(userbean, HashMap.class);

		HashMap<String,String> usercreateApiResponseData=useraccountservice.createUserAPI(responsedata, ConstantsValue.inputType[0], ConstantsValue.mandatoryFields, createuserdetailsbean);
		assertionapiresponse.verifyAssert_httpCode(usercreateApiResponseData.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, usercreateApiResponseData.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	 	 
        			
	}

	@Test(description="To create Event Host user with all the fields",groups = {CREATEUSERAPI})
	public void TC_13_POST_Create_Event_Host_User_withallthefields_Postive() {
		
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Add NewUser API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
 		
		HashMap<String, String> responsedata = userservices.getRolesApi(loginapiresponse, ConstantsValue.eventHost);	
		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		ApiBeanFactory bFactory = new ApiBeanFactory();
		bFactory.CreateGroupBean(addgroupbean);
		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
		creategroupapirespone = creategroupAPI.createGroup(loginapiresponse,creategroupjson);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		responsedata.put("groupId", creategroupapirespone.get("groupId"));
		
		UserAccountService useraccountservice = new UserAccountService();
		AddUserApiBean userbean = new AddUserApiBean();
		bFactory.AddUserBean(userbean);
		// Convert bean to map
		ObjectMapper mapper = new ObjectMapper();
		createuserdetailsbean = mapper.convertValue(userbean, HashMap.class);
						
		HashMap<String,String> usercreateApiResponseData=useraccountservice.createUserAPI(responsedata, ConstantsValue.inputType[0], ConstantsValue.validDatawithgrp, createuserdetailsbean);
        Assert.assertEquals(usercreateApiResponseData.get( IAPIConstantCodes.APIResponseHttpCode)+usercreateApiResponseData.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    Assert.assertNotNull(usercreateApiResponseData.get(IUserAccountsService.USERID));
	 
        //Selenium Level Code
		launchURL(surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengeruserdashboardpage=avengerHomePage.clickUsersLink();
		AddUserBeanPage adduserbean=new AddUserBeanPage();
		adduserbean.setFirstname(createuserdetailsbean.get(IUserAccountsService.FIRSTNAME));
		adduserbean.setLastname(createuserdetailsbean.get(IUserAccountsService.LASTNAME));
		adduserbean.setUsername(createuserdetailsbean.get(IUserAccountsService.USERNAME));
		adduserbean.setPhoneno(createuserdetailsbean.get(IUserAccountsService.PHONENO));
		adduserbean.setUserrole(createuserdetailsbean.get(IUserAccountsService.USERROLE));
		adduserbean.setContactemail(createuserdetailsbean.get(IUserAccountsService.CONTACTEMAIL));
		adduserbean.setTitle(createuserdetailsbean.get(IUserAccountsService.TITLE));
		String userStatus=avengeruserdashboardpage.get_UserStatus(createuserdetailsbean.get(IUserAccountsService.USERNAME));
		avengereditrootaccountpage=avengeruserdashboardpage.searchandclicknewuser_link(adduserbean);
		HashMap<String, String> uservalues=avengereditrootaccountpage.get_allUserfieldValues();
		ArrayList<String> roles=avengereditrootaccountpage.get_allassignedroles();
		int noofRoles = roles.size();
		customReport.customizedReport(adduserbean.getLastname(), uservalues.get(IUserAccountsService.LASTNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getContactemail(), uservalues.get(IUserAccountsService.CONTACTEMAIL), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getUsername(), uservalues.get(IUserAccountsService.USERNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(1,noofRoles, statusValue, driver, sTestcaseName);
		customReport.customizedReport(true,roles.contains("Event Host"), statusValue, driver, sTestcaseName);
		customReport.customizedReport("English",uservalues.get("preferredlanguage"), statusValue, driver, sTestcaseName);
		customReport.customizedReport("Unlicensed",userStatus, statusValue, driver, sTestcaseName); 
		customReport.checkinglist(statusValue);
				
	}
	
	@Test(description="To create Media Viewer user with all the fields",groups = {CREATEUSERAPI})
	public void TC_14_POST_Create_Media_Viewer_User_withallthefields_Postive() {
		
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Add NewUser API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
 		
		HashMap<String, String> responsedata = userservices.getRolesApi(loginapiresponse, ConstantsValue.mediaViewer);		
		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		ApiBeanFactory bFactory = new ApiBeanFactory();
		bFactory.CreateGroupBean(addgroupbean);
		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
		creategroupapirespone = creategroupAPI.createGroup(loginapiresponse,creategroupjson);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		responsedata.put("groupId", creategroupapirespone.get("groupId"));
		
		UserAccountService useraccountservice = new UserAccountService();
		AddUserApiBean userbean = new AddUserApiBean();
		bFactory.AddUserBean(userbean);
		// Convert bean to map
		ObjectMapper mapper = new ObjectMapper();
		createuserdetailsbean = mapper.convertValue(userbean, HashMap.class);
						
		HashMap<String,String> usercreateApiResponseData=useraccountservice.createUserAPI(responsedata, ConstantsValue.inputType[0], ConstantsValue.validDatawithgrp, createuserdetailsbean);
        Assert.assertEquals(usercreateApiResponseData.get( IAPIConstantCodes.APIResponseHttpCode)+usercreateApiResponseData.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    Assert.assertNotNull(usercreateApiResponseData.get(IUserAccountsService.USERID));
	 
        //Selenium Level Code
		launchURL(surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengeruserdashboardpage=avengerHomePage.clickUsersLink();
		AddUserBeanPage adduserbean=new AddUserBeanPage();
		adduserbean.setFirstname(createuserdetailsbean.get(IUserAccountsService.FIRSTNAME));
		adduserbean.setLastname(createuserdetailsbean.get(IUserAccountsService.LASTNAME));
		adduserbean.setUsername(createuserdetailsbean.get(IUserAccountsService.USERNAME));
		adduserbean.setPhoneno(createuserdetailsbean.get(IUserAccountsService.PHONENO));
		adduserbean.setUserrole(createuserdetailsbean.get(IUserAccountsService.USERROLE));
		adduserbean.setContactemail(createuserdetailsbean.get(IUserAccountsService.CONTACTEMAIL));
		adduserbean.setTitle(createuserdetailsbean.get(IUserAccountsService.TITLE));
		String userStatus=avengeruserdashboardpage.get_UserStatus(createuserdetailsbean.get(IUserAccountsService.USERNAME));
		avengereditrootaccountpage=avengeruserdashboardpage.searchandclicknewuser_link(adduserbean);
		HashMap<String, String> uservalues=avengereditrootaccountpage.get_allUserfieldValues();
		ArrayList<String> roles=avengereditrootaccountpage.get_allassignedroles();
		int noofRoles = roles.size();
		customReport.customizedReport(adduserbean.getLastname(), uservalues.get(IUserAccountsService.LASTNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getContactemail(), uservalues.get(IUserAccountsService.CONTACTEMAIL), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduserbean.getUsername(), uservalues.get(IUserAccountsService.USERNAME), statusValue, driver, sTestcaseName);
		customReport.customizedReport(1,noofRoles, statusValue, driver, sTestcaseName);
		customReport.customizedReport(true,roles.contains("Media Viewer"), statusValue, driver, sTestcaseName);
		customReport.customizedReport("English",uservalues.get("preferredlanguage"), statusValue, driver, sTestcaseName);
		customReport.customizedReport("Unlicensed",userStatus, statusValue, driver, sTestcaseName); 
		customReport.checkinglist(statusValue);
				
	}
	
	
		@AfterMethod(alwaysRun = true)
	public void browserClose(ITestResult result)
	{
		logger.info("In After method class");
		 statusValue.clear();
		 if(!result.isSuccess()){
		Reporter.log("Screen shot for failed Test Case" +customReport.AssertionresultOutput(driver, sTestcaseName));
		  }
		browserQuit();
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

