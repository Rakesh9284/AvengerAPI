package com.vbrick.avenger.videocommentapi_AV_9879_testcases;
 

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
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
import com.vbrick.avenger.apibeans.AddUserApiBean;
import com.vbrick.avenger.apibeans.AddVideoCommentBean;
import com.vbrick.avenger.apibeans.CreateEventBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AddCustomDeviceBeanPage;
import com.vbrick.avenger.dao.AddUserBeanPage;
import com.vbrick.avenger.dao.AvengerGroupsBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerApprovalProcessPage;
import com.vbrick.avenger.pageobjects.AvengerEditRootAccountPage;
import com.vbrick.avenger.pageobjects.AvengerGroupsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerMediaSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerUserDashboardPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IUserAccountsService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.ApprovaltemplatesAPI;
import com.vbricks.avenger.serviceimpl.UserAccountService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.ConstantsValue;
import com.vbricks.avenger.utils.HttpStatusCode;
public class GetApprovaltemplatesAPI extends TestBase{

	private static Logger logger = Logger.getLogger(GetApprovaltemplatesAPI.class);
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
	public ApiBeanFactory apibeanfactory;
    public AddVideoCommentBean addvideocommentbean;
	private HashMap<String, String> loginapiresponse;
    private AssertionAPIResponse assertionapiresponse;
	private AvengerUserDashboardPage avengeruserdashboardpage;
	private HashMap<String, String> approvallistapirespone;
	private ApiUtils apiutils; 
    private AvengerHomePage avengerHomePage;
	private AvengerMediaSettingsPage avengermediasettingpage;
	private AvengerApprovalProcessPage approvalprocesspage;
	private HashMap<String,String> createuserdetailsbean;
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
		apibeanfactory = new ApiBeanFactory();
		addvideocommentbean=new AddVideoCommentBean();
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
		driver.manage().window().maximize();
		accountBeansFactory.MailinatorBean(mailinatorbeanpage);
	}

	@Test(description="To Verify the ApprovalTemplateList using ApprovalTemplateList API with Account Admin",groups = {GETAPPROVALTEMPLATES})
	public void TC_01_GET_ApprovalTemplateList_api_check_With_AccountAdmin_Positive(ITestContext context ) {
		
		create_ApprovalProcessFromUI(sUserName);
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		ApprovaltemplatesAPI approvaltemplatesAPI=new ApprovaltemplatesAPI();
		approvallistapirespone = approvaltemplatesAPI.getApprovalList(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(approvallistapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+approvallistapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, approvallistapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+approvallistapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		String fromattedJSON=apiutils.formatingapiresponse(approvallistapirespone.get("approvalnames"));
		ArrayList<String> myList = new ArrayList<String>(Arrays.asList(fromattedJSON.split(",")));
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage = loginPage.loginAs(sUserName, sPassword);
		avengerHomePage.clickSettingsLink();
		avengermediasettingpage= avengerHomePage.clickMediaSettingsLink();
		approvalprocesspage =avengermediasettingpage.click_ApprovalProcesses();		
		ArrayList<String> myList2 =approvalprocesspage.getALLApprovalProcess();		 
		customReport.customizedReport(true,apiutils.containsAll(myList,myList2), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	 
	}
	 
	@Test(description="To Verify the ApprovalTemplateList using ApprovalTemplateList API with Account Admin",groups = {GETAPPROVALTEMPLATES})
	public void TC_02_GET_ApprovalTemplateList_api_check_With_MediaAdmin_Positive(ITestContext context ) {
		
		create_ApprovalProcessFromUI(IUsersList.GetApprovalTemplatemediaadmin);
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.GetApprovalTemplatemediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		ApprovaltemplatesAPI approvaltemplatesAPI=new ApprovaltemplatesAPI();
		approvallistapirespone = approvaltemplatesAPI.getApprovalList(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(approvallistapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+approvallistapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, approvallistapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+approvallistapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		String fromattedJSON=apiutils.formatingapiresponse(approvallistapirespone.get("approvalnames"));
		ArrayList<String> myList = new ArrayList<String>(Arrays.asList(fromattedJSON.split(",")));
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage = loginPage.loginAs(IUsersList.GetApprovalTemplatemediaadmin, sPassword);
		avengerHomePage.clickSettingsLink();
		avengermediasettingpage= avengerHomePage.clickMediaSettingsLink();
		approvalprocesspage =avengermediasettingpage.click_ApprovalProcesses();		
		ArrayList<String> myList2 =approvalprocesspage.getALLApprovalProcess();		 
		customReport.customizedReport(true,apiutils.containsAll(myList,myList2), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="To Verify the ApprovalTemplateList using ApprovalTemplateList API with Account Admin",groups = {GETAPPROVALTEMPLATES})
	public void TC_03_GET_ApprovalTemplateList_api_check_With_MediaViewer_Positive(ITestContext context ) {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.GetApprovalTemplatemediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		ApprovaltemplatesAPI approvaltemplatesAPI=new ApprovaltemplatesAPI();
		approvallistapirespone = approvaltemplatesAPI.getApprovalList(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(approvallistapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+approvallistapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, approvallistapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+approvallistapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		customReport.customizedReport("0",approvallistapirespone.get("approvallistlength"), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	 
	}
	
	@Test(description="To Verify the ApprovalTemplateList using ApprovalTemplateList API with Account Admin",groups = {GETAPPROVALTEMPLATES})
	public void TC_04_GET_ApprovalTemplateList_api_check_With_EventAdmin_Positive(ITestContext context ) {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.GetApprovalTemplateeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		ApprovaltemplatesAPI approvaltemplatesAPI=new ApprovaltemplatesAPI();
		approvallistapirespone = approvaltemplatesAPI.getApprovalList(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(approvallistapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+approvallistapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, approvallistapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+approvallistapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		customReport.customizedReport("0",approvallistapirespone.get("approvallistlength"), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	 
	}
	
	@Test(description="To Verify the ApprovalTemplateList using ApprovalTemplateList API with Account Admin",groups = {GETAPPROVALTEMPLATES})
	public void TC_05_GET_ApprovalTemplateList_api_check_With_MediaContributor_Positive(ITestContext context ) {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.GetApprovalTemplatemediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		ApprovaltemplatesAPI approvaltemplatesAPI=new ApprovaltemplatesAPI();
		approvallistapirespone = approvaltemplatesAPI.getApprovalList(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(approvallistapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+approvallistapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, approvallistapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+approvallistapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		customReport.customizedReport("0",approvallistapirespone.get("approvallistlength"), statusValue, driver, sTestcaseName);
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
	
	public void create_ApprovalProcessFromUI(String username){
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("Test Case Started Here ----------- "+sTestcaseName);
		ArrayList<String> roles = new ArrayList<String>();
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

		HashMap<String,String> usercreateApiResponseData=useraccountservice.createUserAPI(responsedata, ConstantsValue.inputType[0], ConstantsValue.validData, createuserdetailsbean);
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
		avengereditrootaccountpage=avengeruserdashboardpage.clicknewuser_link(adduserbean);
		HashMap<String, String> uservalues=avengereditrootaccountpage.get_allUserfieldValues();
		//String mediacontributorusername=adduserbean.getUsername();
		avengerHomePage.click_logout();
	//	String mediacontributorpassword=adduserbean.getPassword();
		
	    homePage = loginPage.loginAs(sUserName, sPassword);
	    homePage.clickSettingsLink();
		AvengerMediaSettingsPage avengermediasettingspage=homePage.clickMediaSettingsLink();
		AvengerApprovalProcessPage avengerapprovalprocesspage=avengermediasettingspage.click_ApprovalProcesses();
		avengerapprovalprocesspage.click_CreateApprovalProcessButton();
		String randomalphabet=RandomStringUtils.randomAlphabetic(6);
		avengerapprovalprocesspage.addApprovalProcessName(randomalphabet);
		avengerapprovalprocesspage.addusersRequiringApproval(adduserbean);
		adduserbean.setFirstname(username.toString());
		adduserbean.setLastname(username.toString());
		avengerapprovalprocesspage.addusersForApproval(adduserbean);
		avengerapprovalprocesspage.pause(1000);
		avengerapprovalprocesspage.clickcreateapprovalProcessSubmitButtonLocator();
		avengerapprovalprocesspage.verify_Label(randomalphabet);
		avengerapprovalprocesspage.pause(5000);
		homePage.click_logout();
	}	
	
}