package com.vbrick.avenger.videocommentapi_AV_9879_testcases;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import com.vbricks.avenger.utils.ApiUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
import com.vbrick.avenger.apibeans.AddCategoryBean;
import com.vbrick.avenger.apibeans.AddGroupBean;
import com.vbrick.avenger.apibeans.CreateEventBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AddCustomDeviceBeanPage;
import com.vbrick.avenger.dao.AddNewDmeBeanPage;
import com.vbrick.avenger.dao.AddPresentationprofileBeanPage;
import com.vbrick.avenger.dao.AvengerEventDetailsBeanPage;
import com.vbrick.avenger.dao.AvengerGroupsBeanPage;
import com.vbrick.avenger.dao.AvengerSystemMessageBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.DateTime;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.pageobjects.AvengerAddCustomDevicePage;
import com.vbrick.avenger.pageobjects.AvengerAddPresentationProfilePage;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerDevicesPage;
import com.vbrick.avenger.pageobjects.AvengerEditRootAccountPage;
import com.vbrick.avenger.pageobjects.AvengerEventDetailsPage;
import com.vbrick.avenger.pageobjects.AvengerEventsPage;
import com.vbrick.avenger.pageobjects.AvengerExpirationManagementPage;
import com.vbrick.avenger.pageobjects.AvengerFeaturesPage;
import com.vbrick.avenger.pageobjects.AvengerGroupsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerMediaSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerPresentationProfilesPage;
import com.vbrick.avenger.pageobjects.AvengerUserDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.ICreateEventService;
import com.vbricks.avenger.service.IUserAccountsService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.service.IVideoAccessControlService;
import com.vbricks.avenger.serviceimpl.CreateCategoryAPI;
import com.vbricks.avenger.serviceimpl.CreateEventsAPI;
import com.vbricks.avenger.serviceimpl.CreateGroupAPI;
import com.vbricks.avenger.serviceimpl.EditGroupAPI;
import com.vbricks.avenger.serviceimpl.GetEventStatusAPI;
import com.vbricks.avenger.serviceimpl.GetExpirationRuleAPI;
import com.vbricks.avenger.serviceimpl.GetEventStatusAPI;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class GetExpirationRulesAPI_AV_28681 extends TestBase {

	private static Logger logger = Logger.getLogger(GetExpirationRulesAPI_AV_28681.class);
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
	private AvengerHomePage avengerHomePage;
	private AddGroupBean addgroupbean;
	private AvengerDevicesPage avengerdevicespage;
	private AvengerEventDetailsPage avengereventdetailspage;
	private AvengerPresentationProfilesPage avengerpresentationprofilespage;
	private AvengerAddPresentationProfilePage avengeraddpresentationprofilepage;
	private AddPresentationprofileBeanPage addpresentationprofilebeanpage;
	private AvengerUserDashboardPage avengeruserdashboardpage;
	private AvengerAddCustomDevicePage avengeraddcustomdevicepage;
	private AvengerEditRootAccountPage avengereditrootaccountpage;
	private AddNewDmeBeanPage addnewdmedevicebeanpage;
	private BasePage basePage;
	private AddCategoryBean addcategorybean;
	private AddCustomDeviceBeanPage addcustomdevicebeanpage;
	private HashMap<String, String> creategroupapirespone;
	private CreateEventBean createEventBean;
	private FileUploadBeanPage fileuploadbeanpage;
	public ApiBeanFactory apibeanfactory;
	private AvengerMediaSettingsPage avengermediasettingspage;
 	private HashMap<String, String> loginapiresponse;
 	private HashMap<String, String> loginapiresponse1;
 	private HashMap<String, String> loginapiresponse2;
	private HashMap<String, String> createcategoryapirespone;
	private HashMap<String,String >editGroupapiresponse;
	private AssertionAPIResponse assertionapiresponse;
	private AvengerExpirationManagementPage avengerExpirationManagementPage;
	@BeforeClass(alwaysRun=true)
	public void beforeClass() throws Exception {
		
		statusValue=new ArrayList<String>();
		customReport = new CustomReport();
		accountBeansFactory=new BeanFactory();
		fileuploadbeanpage=new FileUploadBeanPage();
		locale = new Locale(language);
		readgriduserdata = new ReadAndWriteToJSON();
		userdata = readgriduserdata.readGridUserData(this.getClass().getSimpleName()); //("user42");
		sUserName = userdata.get("Username");
		sPassword = userdata.get("Password");
		apiutils=new ApiUtils();
		assertionapiresponse =new AssertionAPIResponse();
	}

	@BeforeMethod(alwaysRun=true)
	@Parameters(value = {"sbrowser","sgrid"/* , "surl", "sUserName", "sPassword"*/})
	public void setUP(@Optional(SBROWSER)String sbrowser,@Optional(SVERSION) String sgrid/*, String sUserName,String sPassword*/) throws MalformedURLException 
	{
		logger.info("Version value is"+sgrid);
		driver = initializeDriver(sbrowser,sgrid/*, surl ,sversion,sPlatform*/ );
		customReport.reset(); statusValue.clear();
		logger.info("The driver value is "+driver);
		bundle=ResourceBundle.getBundle("ResourceBundle.BundleFile",locale);
		logger.info("value in bundle is"+bundle.getKeys());
		basePage= new BasePage(driver,customReport, new BasePage());
		loginPage = basePage.avengerLoginPage(driver,customReport, basePage);
		driver.manage().window().maximize();
	
	}
	@Test(description = "To GET Expiration Rules by GET Expiration Rule API with Account Admin",groups = {GETEVENTSTATUS})
	public void TC_01_GET_ExpirationRule_with_AccoutAdmin_Positive() throws ParseException, InterruptedException {
	
		// With Number of Days before Expiry, Delete Upon Expiry is ON & Default = True
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("Test cases Started------------------------"+sTestcaseName);
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName,sPassword);
		homePage.clickSettingsLink();
		avengermediasettingspage=homePage.clickMediaSettingsLink();
		AvengerFeaturesPage avengerfeaturespage=avengermediasettingspage.click_FeaturesLinkLocator();
		avengerfeaturespage.checkexpirationRulesCheckbox();
		avengerfeaturespage.clickSaveChanges();
		homePage.clickMediaSettingsLink();
		AvengerExpirationManagementPage avengerexpirationmanagementpage=avengermediasettingspage.click_expirationManagementLink();
		//delete expiration rule
		avengerexpirationmanagementpage.deleteexpirationrule();
		Thread.sleep(3000);
		//Create expiration rule.
		String rulename=RandomStringUtils.randomAlphabetic(5);
		avengerexpirationmanagementpage.createRule(rulename,bundle.getString("numberofdaysbeforeexpiry.txt"),"1");
		avengerexpirationmanagementpage.click_default();
		avengerexpirationmanagementpage.click_deleteOnExpiry();
		avengerexpirationmanagementpage.clickSaveChanges();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

	   //GET Expiration Rules API
		 GetExpirationRuleAPI GetExpirationRuleAPIResponse = new GetExpirationRuleAPI();
	     JSONObject jsonobj = GetExpirationRuleAPIResponse.getexpirationrules(loginapiresponse);
	     System.out.println("the object value is"+jsonobj.get("ruleId"));
	     System.out.println("the object value is"+jsonobj.get("numberOfDays"));
	     Assert.assertEquals(rulename, jsonobj.get("ruleName"));
	     Assert.assertEquals(jsonobj.get("isDefault"), true);
	     Assert.assertEquals(jsonobj.get("numberOfDays").toString(),"1");
	     Assert.assertEquals(jsonobj.get("expiryRuleType"), "DaysAfterUpload");
	     Assert.assertEquals(jsonobj.get("deleteOnExpire"), true);
	     customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To GET Expiration Rules by GET Expiration Rule API with Account Admin",groups = {GETEVENTSTATUS})
	public void TC_02_GET_ExpirationRule_with_AccoutAdmin_Positive() throws ParseException, InterruptedException {
	
		// With Number of Days before Expiry, Delete Upon Expiry is OFF & Default = True
		
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("Test cases Started------------------------"+sTestcaseName);
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName,sPassword);
		homePage.clickSettingsLink();
		avengermediasettingspage=homePage.clickMediaSettingsLink();
		AvengerFeaturesPage avengerfeaturespage=avengermediasettingspage.click_FeaturesLinkLocator();
		avengerfeaturespage.checkexpirationRulesCheckbox();
		avengerfeaturespage.clickSaveChanges();
		homePage.clickMediaSettingsLink();
		AvengerExpirationManagementPage avengerexpirationmanagementpage=avengermediasettingspage.click_expirationManagementLink();
		//delete expiration rule
		avengerexpirationmanagementpage.deleteexpirationrule();
		Thread.sleep(3000);
		//Create expiration rule.
		String rulename=RandomStringUtils.randomAlphabetic(5);
		avengerexpirationmanagementpage.createRule(rulename,bundle.getString("numberofdaysbeforeexpiry.txt"),"1");
		avengerexpirationmanagementpage.click_default();
		avengerexpirationmanagementpage.clickSaveChanges();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

	   //GET Expiration Rules API
		 GetExpirationRuleAPI GetExpirationRuleAPIResponse = new GetExpirationRuleAPI();
	     JSONObject jsonobj = GetExpirationRuleAPIResponse.getexpirationrules(loginapiresponse);
	     System.out.println("the object value is"+jsonobj.get("ruleId"));
	     System.out.println("the object value is"+jsonobj.get("numberOfDays"));
	     Assert.assertEquals(rulename, jsonobj.get("ruleName"));
	     Assert.assertEquals(jsonobj.get("isDefault"), true);
	     Assert.assertEquals(jsonobj.get("numberOfDays").toString(),"1");
	     Assert.assertEquals(jsonobj.get("expiryRuleType"), "DaysAfterUpload");
	     Assert.assertEquals(jsonobj.get("deleteOnExpire"), false);
	     customReport.checkinglist(statusValue);	
	}
	
	@Test(description = "To GET Expiration Rules by GET Expiration Rule API with Account Admin",groups = {GETEVENTSTATUS})
	public void TC_03_GET_ExpirationRule_with_AccoutAdmin_Positive() throws ParseException, InterruptedException {
	
		// Number of Days without Views, Delete Upon Expiry is OFF & Default = True
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("Test cases Started------------------------"+sTestcaseName);
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName,sPassword);
		homePage.clickSettingsLink();
		avengermediasettingspage=homePage.clickMediaSettingsLink();
		AvengerFeaturesPage avengerfeaturespage=avengermediasettingspage.click_FeaturesLinkLocator();
		avengerfeaturespage.checkexpirationRulesCheckbox();
		avengerfeaturespage.clickSaveChanges();
		homePage.clickMediaSettingsLink();
		AvengerExpirationManagementPage avengerexpirationmanagementpage=avengermediasettingspage.click_expirationManagementLink();
		//delete expiration rule
		avengerexpirationmanagementpage.deleteexpirationrule();
		Thread.sleep(3000);
		//Create expiration rule.
		String rulename=RandomStringUtils.randomAlphabetic(5);
		avengerexpirationmanagementpage.createRule(rulename,bundle.getString("numberofdayswithoutviews.txt"),"1");
		avengerexpirationmanagementpage.click_default();
		avengerexpirationmanagementpage.clickSaveChanges();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

	   //GET Expiration Rules API
		 GetExpirationRuleAPI GetExpirationRuleAPIResponse = new GetExpirationRuleAPI();
	     JSONObject jsonobj = GetExpirationRuleAPIResponse.getexpirationrules(loginapiresponse);
	     System.out.println("the object value is"+jsonobj.get("ruleId"));
	     System.out.println("the object value is"+jsonobj.get("numberOfDays"));
	     Assert.assertEquals(rulename, jsonobj.get("ruleName"));
	     Assert.assertEquals(jsonobj.get("isDefault"), true);
	     Assert.assertEquals(jsonobj.get("numberOfDays").toString(),"1");
	     Assert.assertEquals(jsonobj.get("expiryRuleType"), "DaysWithoutViews");
	     Assert.assertEquals(jsonobj.get("deleteOnExpire"), false);
	     customReport.checkinglist(statusValue);	
	}	
	
	@Test(description = "To GET Expiration Rules by GET Expiration Rule API with Account Admin",groups = {GETEVENTSTATUS})
	public void TC_04_GET_ExpirationRule_with_AccoutAdmin_Positive() throws ParseException, InterruptedException {
	
		// Number of Days without Views, Delete Upon Expiry is ON & Default = true
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("Test cases Started------------------------"+sTestcaseName);
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName,sPassword);
		homePage.clickSettingsLink();
		avengermediasettingspage=homePage.clickMediaSettingsLink();
		AvengerFeaturesPage avengerfeaturespage=avengermediasettingspage.click_FeaturesLinkLocator();
		avengerfeaturespage.checkexpirationRulesCheckbox();
		avengerfeaturespage.clickSaveChanges();
		homePage.clickMediaSettingsLink();
		AvengerExpirationManagementPage avengerexpirationmanagementpage=avengermediasettingspage.click_expirationManagementLink();
		//delete expiration rule
		avengerexpirationmanagementpage.deleteexpirationrule();
		Thread.sleep(3000);
		//Create expiration rule.
		String rulename=RandomStringUtils.randomAlphabetic(5);
		avengerexpirationmanagementpage.createRule(rulename,bundle.getString("numberofdayswithoutviews.txt"),"1");
		avengerexpirationmanagementpage.click_default();
		avengerexpirationmanagementpage.click_deleteOnExpiry();
		avengerexpirationmanagementpage.clickSaveChanges();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

	   //GET Expiration Rules API
		 GetExpirationRuleAPI GetExpirationRuleAPIResponse = new GetExpirationRuleAPI();
	     JSONObject jsonobj = GetExpirationRuleAPIResponse.getexpirationrules(loginapiresponse);
	     System.out.println("the object value is"+jsonobj.get("ruleId"));
	     System.out.println("the object value is"+jsonobj.get("numberOfDays"));
	     Assert.assertEquals(rulename, jsonobj.get("ruleName"));
	     Assert.assertEquals(jsonobj.get("isDefault"), true);
	     Assert.assertEquals(jsonobj.get("numberOfDays").toString(),"1");
	     Assert.assertEquals(jsonobj.get("expiryRuleType"), "DaysWithoutViews");
	     Assert.assertEquals(jsonobj.get("deleteOnExpire"), true);
	     customReport.checkinglist(statusValue);	
	}
	
	@Test(description = "To GET Expiration Rules by GET Expiration Rule API with Account Admin",groups = {GETEVENTSTATUS})
	public void TC_05_GET_ExpirationRule_with_AccoutAdmin_Positive() throws ParseException, InterruptedException {
	
		// Number of Days without Views, Delete Upon Expiry is ON & Default = false
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("Test cases Started------------------------"+sTestcaseName);
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName,sPassword);
		homePage.clickSettingsLink();
		avengermediasettingspage=homePage.clickMediaSettingsLink();
		AvengerFeaturesPage avengerfeaturespage=avengermediasettingspage.click_FeaturesLinkLocator();
		avengerfeaturespage.checkexpirationRulesCheckbox();
		avengerfeaturespage.clickSaveChanges();
		homePage.clickMediaSettingsLink();
		AvengerExpirationManagementPage avengerexpirationmanagementpage=avengermediasettingspage.click_expirationManagementLink();
		//delete expiration rule
		avengerexpirationmanagementpage.deleteexpirationrule();
		Thread.sleep(3000);
		//Create expiration rule.
		String rulename=RandomStringUtils.randomAlphabetic(5);
		avengerexpirationmanagementpage.createRule(rulename,bundle.getString("numberofdayswithoutviews.txt"),"1");
		avengerexpirationmanagementpage.click_deleteOnExpiry();
		avengerexpirationmanagementpage.clickSaveChanges();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

	   //GET Expiration Rules API
		 GetExpirationRuleAPI GetExpirationRuleAPIResponse = new GetExpirationRuleAPI();
	     JSONObject jsonobj = GetExpirationRuleAPIResponse.getexpirationrules(loginapiresponse);
	     System.out.println("the object value is"+jsonobj.get("ruleId"));
	     System.out.println("the object value is"+jsonobj.get("numberOfDays"));
	     Assert.assertEquals(rulename, jsonobj.get("ruleName"));
	     Assert.assertEquals(jsonobj.get("isDefault"), false);
	     Assert.assertEquals(jsonobj.get("numberOfDays").toString(),"1");
	     Assert.assertEquals(jsonobj.get("expiryRuleType"), "DaysWithoutViews");
	     Assert.assertEquals(jsonobj.get("deleteOnExpire"), true);
	     customReport.checkinglist(statusValue);	
	}
	
	@Test(description = "To GET Expiration Rules by GET Expiration Rule API with Account Admin",groups = {GETEVENTSTATUS})
	public void TC_06_GET_ExpirationRule_with_AccoutAdmin_Positive() throws ParseException, InterruptedException {
	
		// Number of Days without Views, Delete Upon Expiry is OFF & Default = false
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("Test cases Started------------------------"+sTestcaseName);
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName,sPassword);
		homePage.clickSettingsLink();
		avengermediasettingspage=homePage.clickMediaSettingsLink();
		AvengerFeaturesPage avengerfeaturespage=avengermediasettingspage.click_FeaturesLinkLocator();
		avengerfeaturespage.checkexpirationRulesCheckbox();
		avengerfeaturespage.clickSaveChanges();
		homePage.clickMediaSettingsLink();
		AvengerExpirationManagementPage avengerexpirationmanagementpage=avengermediasettingspage.click_expirationManagementLink();
		//delete expiration rule
		avengerexpirationmanagementpage.deleteexpirationrule();
		Thread.sleep(3000);
		//Create expiration rule.
		String rulename=RandomStringUtils.randomAlphabetic(5);
		avengerexpirationmanagementpage.createRule(rulename,bundle.getString("numberofdayswithoutviews.txt"),"1");
		avengerexpirationmanagementpage.clickSaveChanges();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

	   //GET Expiration Rules API
		 GetExpirationRuleAPI GetExpirationRuleAPIResponse = new GetExpirationRuleAPI();
	     JSONObject jsonobj = GetExpirationRuleAPIResponse.getexpirationrules(loginapiresponse);
	     System.out.println("the object value is"+jsonobj.get("ruleId"));
	     System.out.println("the object value is"+jsonobj.get("numberOfDays"));
	     Assert.assertEquals(rulename, jsonobj.get("ruleName"));
	     Assert.assertEquals(jsonobj.get("isDefault"), false);
	     Assert.assertEquals(jsonobj.get("numberOfDays").toString(),"1");
	     Assert.assertEquals(jsonobj.get("expiryRuleType"), "DaysWithoutViews");
	     Assert.assertEquals(jsonobj.get("deleteOnExpire"), false);
	     customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To GET Expiration Rules by GET Expiration Rule API with Account Admin",groups = {GETEVENTSTATUS})
	public void TC_07_GET_ExpirationRule_with_AccoutAdmin_Positive() throws ParseException, InterruptedException {
	
		// Number of Days before Expiry, Delete Upon Expiry is OFF & Default = false
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("Test cases Started------------------------"+sTestcaseName);
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName,sPassword);
		homePage.clickSettingsLink();
		avengermediasettingspage=homePage.clickMediaSettingsLink();
		AvengerFeaturesPage avengerfeaturespage=avengermediasettingspage.click_FeaturesLinkLocator();
		avengerfeaturespage.checkexpirationRulesCheckbox();
		avengerfeaturespage.clickSaveChanges();
		homePage.clickMediaSettingsLink();
		AvengerExpirationManagementPage avengerexpirationmanagementpage=avengermediasettingspage.click_expirationManagementLink();
		//delete expiration rule
		avengerexpirationmanagementpage.deleteexpirationrule();
		Thread.sleep(3000);
		//Create expiration rule.
		String rulename=RandomStringUtils.randomAlphabetic(5);
		avengerexpirationmanagementpage.createRule(rulename,bundle.getString("numberofdaysbeforeexpiry.txt"),"1");
		avengerexpirationmanagementpage.clickSaveChanges();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

	   //GET Expiration Rules API
		 GetExpirationRuleAPI GetExpirationRuleAPIResponse = new GetExpirationRuleAPI();
	     JSONObject jsonobj = GetExpirationRuleAPIResponse.getexpirationrules(loginapiresponse);
	     System.out.println("the object value is"+jsonobj.get("ruleId"));
	     System.out.println("the object value is"+jsonobj.get("numberOfDays"));
	     Assert.assertEquals(rulename, jsonobj.get("ruleName"));
	     Assert.assertEquals(jsonobj.get("isDefault"), false);
	     Assert.assertEquals(jsonobj.get("numberOfDays").toString(),"1");
	     Assert.assertEquals(jsonobj.get("expiryRuleType"), "DaysAfterUpload");
	     Assert.assertEquals(jsonobj.get("deleteOnExpire"), false);
	     customReport.checkinglist(statusValue);
	}
	@Test(description = "To GET Expiration Rules by GET Expiration Rule API with Account Admin",groups = {GETEVENTSTATUS})
	public void TC_08_GET_ExpirationRule_with_AccoutAdmin_Positive() throws ParseException, InterruptedException {
	
		// Number of Days before Expiry, Delete Upon Expiry is ON & Default = false
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("Test cases Started------------------------"+sTestcaseName);
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName,sPassword);
		homePage.clickSettingsLink();
		avengermediasettingspage=homePage.clickMediaSettingsLink();
		AvengerFeaturesPage avengerfeaturespage=avengermediasettingspage.click_FeaturesLinkLocator();
		avengerfeaturespage.checkexpirationRulesCheckbox();
		avengerfeaturespage.clickSaveChanges();
		homePage.clickMediaSettingsLink();
		AvengerExpirationManagementPage avengerexpirationmanagementpage=avengermediasettingspage.click_expirationManagementLink();
		//delete expiration rule
		avengerexpirationmanagementpage.deleteexpirationrule();
		Thread.sleep(3000);
		//Create expiration rule.
		String rulename=RandomStringUtils.randomAlphabetic(5);
		avengerexpirationmanagementpage.createRule(rulename,bundle.getString("numberofdaysbeforeexpiry.txt"),"1");
		avengerexpirationmanagementpage.click_deleteOnExpiry();
		avengerexpirationmanagementpage.clickSaveChanges();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

	   //GET Expiration Rules API
		 GetExpirationRuleAPI GetExpirationRuleAPIResponse = new GetExpirationRuleAPI();
	     JSONObject jsonobj = GetExpirationRuleAPIResponse.getexpirationrules(loginapiresponse);
	     System.out.println("the object value is"+jsonobj.get("ruleId"));
	     System.out.println("the object value is"+jsonobj.get("numberOfDays"));
	     Assert.assertEquals(rulename, jsonobj.get("ruleName"));
	     Assert.assertEquals(jsonobj.get("isDefault"), false);
	     Assert.assertEquals(jsonobj.get("numberOfDays").toString(),"1");
	     Assert.assertEquals(jsonobj.get("expiryRuleType"), "DaysAfterUpload");
	     Assert.assertEquals(jsonobj.get("deleteOnExpire"), true);
	     customReport.checkinglist(statusValue);
	}
}