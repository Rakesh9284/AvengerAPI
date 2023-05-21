package com.vbrick.avenger.videocommentapi_AV_9879_testcases;
 

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbrick.avenger.apibeans.AddVideoCommentBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AvengerGroupsBeanPage;
import com.vbrick.avenger.dao.AvengerManageCustomFieldsBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerManageCustomFieldsPage;
import com.vbrick.avenger.pageobjects.AvengerSystemSettingsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.CreateGroupAPI;
import com.vbricks.avenger.serviceimpl.CustomFieldsAPI;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;
public class CustomField_Av_11101 extends TestBase{

	private static Logger logger = Logger.getLogger(CustomField_Av_11101.class);
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
	private HashMap<String, String> loginapiresponse1;
	private AvengerSystemSettingsPage avengersystemsettingspage;
	private AddGroupBean addgroupbean;
	private HashMap<String, String> creategroupapirespone;
	private AvengerManageCustomFieldsPage avengercustomefieldspage;
	private AvengerManageCustomFieldsBeanPage customfieldsbean;
	private AssertionAPIResponse assertionapiresponse;
	private HashMap<String, String> customfieldapirespone;
	private ApiUtils apiutils; 
	private AvengerHomePage avengerHomePage;
	private AvengerSystemSettingsPage avengersystemsettingpage;
	private AvengerManageCustomFieldsPage avengercustomfieldpage;
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
		readgriduserdata = new ReadAndWriteToJSON();
		userdata = readgriduserdata.readGridUserData(this.getClass().getSimpleName()); // ("user52");
		sUserName = userdata.get("Username");
		sPassword = userdata.get("Password");
		customfieldsbean= new AvengerManageCustomFieldsBeanPage();
		addgroupbean=new AddGroupBean();
		new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		addvideocommentbean=new AddVideoCommentBean();
		assertionapiresponse =new AssertionAPIResponse();
		avengergroupsbeanpage = new AvengerGroupsBeanPage();
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
		accountBeansFactory.customfieldsBean(customfieldsbean);
		loginPage = basePage.avengerLoginPage(driver, customReport, basePage);
		driver.manage().window().maximize();
		accountBeansFactory.MailinatorBean(mailinatorbeanpage);
		accountBeansFactory.AvengergroupsBean(avengergroupsbeanpage);
		
	}

	@Test(description="To Verify CustomField using CustomField API with AA by creating default custom field-Non Mandatory/Publicly displayed/type is text",groups = {GETCUSTOMFILEDSAPI})
	public void TC_01_GET_CustomField_api_check_With_AccountAdmin_defaultCustomField_Positive(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		launchURL(surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		homePage.clickSettingsLink();
		avengersystemsettingspage = homePage.clickSystemSettingsLink();
		avengercustomefieldspage=avengersystemsettingspage.click_CustomFields();
		avengercustomefieldspage.deleteallcustomfields();
		customReport.customizedReport(bundle.getString("customfields.nocustomfieldsmsg"),avengercustomefieldspage.verify_Label(bundle.getString("customfields.nocustomfieldsmsg")), statusValue, driver, sTestcaseName);
		
		//default custom field1 - Non Mandatory/Publicly displayed/type is text
		avengercustomefieldspage.click_addfieldbutton();
		avengercustomefieldspage.setcustmofieldname(customfieldsbean);
		avengercustomefieldspage.click_submitbutton();
		customReport.customizedReport(customfieldsbean.getCustomfieldname(),avengercustomefieldspage.verify_Label(customfieldsbean.getCustomfieldname()), statusValue, driver, sTestcaseName);
		String customField = customfieldsbean.getCustomfieldname();
		homePage.click_logout();
				
		CustomFieldsAPI customfieldsAPI=new CustomFieldsAPI();
		customfieldapirespone = customfieldsAPI.getcustomfield(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(customfieldapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+customfieldapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, customfieldapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+customfieldapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		String fromattedJSON=apiutils.formatingapiresponse(customfieldapirespone.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> apiCustomList = new ArrayList<String>(Arrays.asList(fromattedJSON.split(",")));		
		String apiCFID = apiutils.formatingapiresponse(customfieldapirespone.get(IAPIConstantCodes.APIResponseJSON2));
				
		//Selenium Level Code
		launchURL(surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengersystemsettingpage=avengerHomePage.clickSystemSettingsLink();
		avengercustomfieldpage=avengersystemsettingpage.click_CustomFields();
		ArrayList<String> uiCustomList=avengercustomfieldpage.getcustomfieldvalues();
		customReport.customizedReport(true,apiutils.containsAll(apiCustomList,uiCustomList), statusValue, driver, sTestcaseName);
		customReport.customizedReport(1,avengercustomfieldpage.get_countOfElements(), statusValue, driver, sTestcaseName);
		avengercustomfieldpage.click_customfieldeditbutton1(customField);
		customReport.customizedReport(true,avengercustomefieldspage.verify_RequiredOrDisaplayPublicOptions("Required","no").contains("active"), statusValue, driver, sTestcaseName);
		customReport.customizedReport(true,avengercustomefieldspage.verify_RequiredOrDisaplayPublicOptions("Publicly Displayed","yes").contains("active"), statusValue, driver, sTestcaseName);
		customReport.customizedReport("Text",avengercustomefieldspage.get_customFieldType("Text"), statusValue, driver, sTestcaseName);
		String url=homePage.getCurrentURL();
		String uiCFID = url.split("/")[9];
		customReport.customizedReport(apiCFID,uiCFID, statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description="To Verify CustomField using CustomField API with AA by creating default custom field-Non Mandatory/Not publicly displayed/type is picklist",groups = {GETCUSTOMFILEDSAPI})
	public void TC_02_GET_CustomField_api_check_With_AccountAdmin_customField_NMNPP_Positive(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		launchURL(surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		homePage.clickSettingsLink();
		avengersystemsettingspage = homePage.clickSystemSettingsLink();
		avengercustomefieldspage=avengersystemsettingspage.click_CustomFields();
		avengercustomefieldspage.deleteallcustomfields();
		customReport.customizedReport(bundle.getString("customfields.nocustomfieldsmsg"),avengercustomefieldspage.verify_Label(bundle.getString("customfields.nocustomfieldsmsg")), statusValue, driver, sTestcaseName);
		
		//custom field2 - Non Mandatory/Not publicly displayed/type is picklist
		avengercustomefieldspage.click_addfieldbutton();
		accountBeansFactory.customfieldsBean(customfieldsbean);			
		avengercustomefieldspage.setcustmofieldname(customfieldsbean);
		customfieldsbean.setWidgetvalue("Pick List");
		avengercustomefieldspage.selectwidgetvalue(customfieldsbean);
		avengercustomefieldspage.click_RequiredOrDisaplayPublicOptions("Publicly Displayed", "NO");
		avengercustomefieldspage.click_submitbutton();
		customReport.customizedReport(customfieldsbean.getCustomfieldname(),avengercustomefieldspage.verify_Label(customfieldsbean.getCustomfieldname()), statusValue, driver, sTestcaseName);
		
		String customField = customfieldsbean.getCustomfieldname();
		homePage.click_logout();
				
		CustomFieldsAPI customfieldsAPI=new CustomFieldsAPI();
		customfieldapirespone = customfieldsAPI.getcustomfield(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(customfieldapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+customfieldapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, customfieldapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+customfieldapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		String fromattedJSON=apiutils.formatingapiresponse(customfieldapirespone.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> apiCustomList = new ArrayList<String>(Arrays.asList(fromattedJSON.split(",")));
		
		String apiCFID = apiutils.formatingapiresponse(customfieldapirespone.get(IAPIConstantCodes.APIResponseJSON2));
		
		String fromattedJSON1=apiutils.formatingapiresponse(customfieldapirespone.get(IAPIConstantCodes.APIResponseJSON1));
		ArrayList<String> apiCFPickListOptions = new ArrayList<String>(Arrays.asList(fromattedJSON1.split(",")));
		
		//Selenium Level Code
		launchURL(surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengersystemsettingpage=avengerHomePage.clickSystemSettingsLink();
		avengercustomfieldpage=avengersystemsettingpage.click_CustomFields();
		ArrayList<String> uiCustomList=avengercustomfieldpage.getcustomfieldvalues();
		System.out.println("size value is"+apiCustomList.size());
		System.out.println("apiCustomList is"+apiCustomList);
		System.out.println("uiCustomList is"+uiCustomList);
		customReport.customizedReport(true,uiCustomList.contains(apiCustomList.get(0)), statusValue, driver, sTestcaseName);
		customReport.customizedReport(1,avengercustomfieldpage.get_countOfElements(), statusValue, driver, sTestcaseName);
		avengercustomfieldpage.click_customfieldeditbutton1(customField);
		customReport.customizedReport(true,avengercustomefieldspage.verify_RequiredOrDisaplayPublicOptions("Required","no").contains("active"), statusValue, driver, sTestcaseName);
		customReport.customizedReport(true,avengercustomefieldspage.verify_RequiredOrDisaplayPublicOptions("Publicly Displayed","no").contains("active"), statusValue, driver, sTestcaseName);
		customReport.customizedReport("Pick List",avengercustomefieldspage.get_customFieldType("Pick List"), statusValue, driver, sTestcaseName);
		customReport.customizedReport(customfieldsbean.getWidgetoptionvalue(),apiCFPickListOptions.get(0), statusValue, driver, sTestcaseName);
		String url=homePage.getCurrentURL();
		String uiCFID = url.split("/")[9];
		customReport.customizedReport(apiCFID,uiCFID, statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		 
	}
	
	@Test(description="To Verify CustomField using CustomField API with AA by creating default custom field-Mandatory/Publicly displayed/type is text",groups = {GETCUSTOMFILEDSAPI})
	public void TC_03_GET_CustomField_api_check_With_AccountAdmin_customField_MPT_Positive(){

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		launchURL(surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		homePage.clickSettingsLink();
		avengersystemsettingspage = homePage.clickSystemSettingsLink();
		avengercustomefieldspage=avengersystemsettingspage.click_CustomFields();
		avengercustomefieldspage.deleteallcustomfields();
		customReport.customizedReport(bundle.getString("customfields.nocustomfieldsmsg"),avengercustomefieldspage.verify_Label(bundle.getString("customfields.nocustomfieldsmsg")), statusValue, driver, sTestcaseName);
		
		//custom field3 - Mandatory/Publicly displayed/type is text
		avengercustomefieldspage.click_addfieldbutton();
		accountBeansFactory.customfieldsBean(customfieldsbean);			
		avengercustomefieldspage.setcustmofieldname(customfieldsbean);
		avengercustomefieldspage.click_RequiredOrDisaplayPublicOptions("Publicly Displayed", "YES");
		avengercustomefieldspage.click_RequiredOrDisaplayPublicOptions("Required", "YES");
		avengercustomefieldspage.click_submitbutton();
		customReport.customizedReport(customfieldsbean.getCustomfieldname(),avengercustomefieldspage.verify_Label(customfieldsbean.getCustomfieldname()), statusValue, driver, sTestcaseName);
		String customField = customfieldsbean.getCustomfieldname();
		homePage.click_logout();
				
		CustomFieldsAPI customfieldsAPI=new CustomFieldsAPI();
		customfieldapirespone = customfieldsAPI.getcustomfield(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(customfieldapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+customfieldapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, customfieldapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+customfieldapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		String fromattedJSON=apiutils.formatingapiresponse(customfieldapirespone.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> apiCustomList = new ArrayList<String>(Arrays.asList(fromattedJSON.split(",")));
		
		String apiCFID = apiutils.formatingapiresponse(customfieldapirespone.get(IAPIConstantCodes.APIResponseJSON2));
		
		//Selenium Level Code
		launchURL(surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengersystemsettingpage=avengerHomePage.clickSystemSettingsLink();
		avengercustomfieldpage=avengersystemsettingpage.click_CustomFields();
		ArrayList<String> uiCustomList=avengercustomfieldpage.getcustomfieldvalues();
		customReport.customizedReport(true,apiutils.containsAll(apiCustomList,uiCustomList), statusValue, driver, sTestcaseName);
		customReport.customizedReport(1,avengercustomfieldpage.get_countOfElements(), statusValue, driver, sTestcaseName);
		avengercustomfieldpage.click_customfieldeditbutton1(customField);
		customReport.customizedReport(true,avengercustomefieldspage.verify_RequiredOrDisaplayPublicOptions("Required","yes").contains("active"), statusValue, driver, sTestcaseName);
		customReport.customizedReport(true,avengercustomefieldspage.verify_RequiredOrDisaplayPublicOptions("Publicly Displayed","yes").contains("active"), statusValue, driver, sTestcaseName);
		customReport.customizedReport("Text",avengercustomefieldspage.get_customFieldType("Text"), statusValue, driver, sTestcaseName);
		String url=homePage.getCurrentURL();
		String uiCFID = url.split("/")[9];
		customReport.customizedReport(apiCFID,uiCFID, statusValue, driver, sTestcaseName);
		
		//Deleting the added mandatory custom field		
		homePage.clickSettingsLink();
		avengersystemsettingspage = homePage.clickSystemSettingsLink();
		avengercustomefieldspage=avengersystemsettingspage.click_CustomFields();
		avengercustomefieldspage.deleteallcustomfields();
		customReport.customizedReport(bundle.getString("customfields.nocustomfieldsmsg"),avengercustomefieldspage.verify_Label(bundle.getString("customfields.nocustomfieldsmsg")), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);

	}
	
	@Test(description="To Verify CustomField using CustomField API with AA by creating default custom field-Mandatory/Publicly displayed/type is picklist",groups = {GETCUSTOMFILEDSAPI})
	public void TC_04_GET_CustomField_api_check_With_AccountAdmin_customField_MPT_Positive(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		launchURL(surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		homePage.clickSettingsLink();
		avengersystemsettingspage = homePage.clickSystemSettingsLink();
		avengercustomefieldspage=avengersystemsettingspage.click_CustomFields();
		avengercustomefieldspage.deleteallcustomfields();
		customReport.customizedReport(bundle.getString("customfields.nocustomfieldsmsg"),avengercustomefieldspage.verify_Label(bundle.getString("customfields.nocustomfieldsmsg")), statusValue, driver, sTestcaseName);
		
		//custom field4 - Mandatory/Publicly displayed/type is picklist
		avengercustomefieldspage.click_addfieldbutton();
		accountBeansFactory.customfieldsBean(customfieldsbean);				
		avengercustomefieldspage.setcustmofieldname(customfieldsbean);
		customfieldsbean.setWidgetvalue("Pick List");
		avengercustomefieldspage.selectwidgetvalue(customfieldsbean);
		avengercustomefieldspage.click_RequiredOrDisaplayPublicOptions("Publicly Displayed", "YES");
		avengercustomefieldspage.click_RequiredOrDisaplayPublicOptions("Required", "YES");
		avengercustomefieldspage.click_submitbutton();
		customReport.customizedReport(customfieldsbean.getCustomfieldname(),avengercustomefieldspage.verify_Label(customfieldsbean.getCustomfieldname()), statusValue, driver, sTestcaseName);
		String customField = customfieldsbean.getCustomfieldname();
		homePage.click_logout();
				
		CustomFieldsAPI customfieldsAPI=new CustomFieldsAPI();
		customfieldapirespone = customfieldsAPI.getcustomfield(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(customfieldapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+customfieldapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, customfieldapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+customfieldapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		String fromattedJSON=apiutils.formatingapiresponse(customfieldapirespone.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> apiCustomList = new ArrayList<String>(Arrays.asList(fromattedJSON.split(",")));
		
		String apiCFID = apiutils.formatingapiresponse(customfieldapirespone.get(IAPIConstantCodes.APIResponseJSON2));
		
		String fromattedJSON1=apiutils.formatingapiresponse(customfieldapirespone.get(IAPIConstantCodes.APIResponseJSON1));
		ArrayList<String> apiCFPickListOptions = new ArrayList<String>(Arrays.asList(fromattedJSON1.split(",")));
		
		//Selenium Level Code
		launchURL(surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengersystemsettingpage=avengerHomePage.clickSystemSettingsLink();
		avengercustomfieldpage=avengersystemsettingpage.click_CustomFields();
		ArrayList<String> uiCustomList=avengercustomfieldpage.getcustomfieldvalues();
		customReport.customizedReport(true,apiutils.containsAll(apiCustomList,uiCustomList), statusValue, driver, sTestcaseName);
		customReport.customizedReport(1,avengercustomfieldpage.get_countOfElements(), statusValue, driver, sTestcaseName);
		avengercustomfieldpage.click_customfieldeditbutton1(customField);
		customReport.customizedReport(true,avengercustomefieldspage.verify_RequiredOrDisaplayPublicOptions("Required","yes").contains("active"), statusValue, driver, sTestcaseName);
		customReport.customizedReport(true,avengercustomefieldspage.verify_RequiredOrDisaplayPublicOptions("Publicly Displayed","yes").contains("active"), statusValue, driver, sTestcaseName);
		customReport.customizedReport("Pick List",avengercustomefieldspage.get_customFieldType("Pick List"), statusValue, driver, sTestcaseName);
		customReport.customizedReport(customfieldsbean.getWidgetoptionvalue(),apiCFPickListOptions.get(0), statusValue, driver, sTestcaseName);
		String url=homePage.getCurrentURL();
		String uiCFID = url.split("/")[9];
		customReport.customizedReport(apiCFID,uiCFID, statusValue, driver, sTestcaseName);
		
		//Deleting the added mandatory custom field		
		homePage.clickSettingsLink();
		avengersystemsettingspage = homePage.clickSystemSettingsLink();
		avengercustomefieldspage=avengersystemsettingspage.click_CustomFields();
		avengercustomefieldspage.deleteallcustomfields();
		customReport.customizedReport(bundle.getString("customfields.nocustomfieldsmsg"),avengercustomefieldspage.verify_Label(bundle.getString("customfields.nocustomfieldsmsg")), statusValue, driver, sTestcaseName);		
		customReport.checkinglist(statusValue);
		 
	}
	
	@Test(description="To Verify CustomField using CustomField API with AA by creating default custom field-Non Mandatory/Publicly displayed/type is picklist with 2 options",groups = {GETCUSTOMFILEDSAPI})
	public void TC_05_GET_CustomField_api_check_With_AccountAdmin_customField_NMPP_Positive(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		launchURL(surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		homePage.clickSettingsLink();
		avengersystemsettingspage = homePage.clickSystemSettingsLink();
		avengercustomefieldspage=avengersystemsettingspage.click_CustomFields();
		avengercustomefieldspage.deleteallcustomfields();
		customReport.customizedReport(bundle.getString("customfields.nocustomfieldsmsg"),avengercustomefieldspage.verify_Label(bundle.getString("customfields.nocustomfieldsmsg")), statusValue, driver, sTestcaseName);
		
		//custom field5 - Non Mandatory/Publicly displayed/type is picklist with 2 options
		avengercustomefieldspage.click_addfieldbutton();
		accountBeansFactory.customfieldsBean(customfieldsbean);					
		avengercustomefieldspage.setcustmofieldname(customfieldsbean);
		customfieldsbean.setWidgetvalue("Pick List");
		avengercustomefieldspage.selectwidgetvalue(customfieldsbean);//option1
		avengercustomefieldspage.selectwidgetvalue1(customfieldsbean);//option2
		avengercustomefieldspage.click_submitbutton();
		customReport.customizedReport(customfieldsbean.getCustomfieldname(),avengercustomefieldspage.verify_Label(customfieldsbean.getCustomfieldname()), statusValue, driver, sTestcaseName);
		String customField = customfieldsbean.getCustomfieldname();
		homePage.click_logout();
				
		CustomFieldsAPI customfieldsAPI=new CustomFieldsAPI();
		customfieldapirespone = customfieldsAPI.getcustomfield(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(customfieldapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+customfieldapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, customfieldapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+customfieldapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		String fromattedJSON=apiutils.formatingapiresponse(customfieldapirespone.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> apiCustomList = new ArrayList<String>(Arrays.asList(fromattedJSON.split(",")));
		
		String apiCFID = apiutils.formatingapiresponse(customfieldapirespone.get(IAPIConstantCodes.APIResponseJSON2));
		
		String fromattedJSON1=apiutils.formatingapiresponse(customfieldapirespone.get(IAPIConstantCodes.APIResponseJSON1));
		ArrayList<String> apiCFPickListOptions = new ArrayList<String>(Arrays.asList(fromattedJSON1.split(",")));
		
		//Selenium Level Code
		launchURL(surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengersystemsettingpage=avengerHomePage.clickSystemSettingsLink();
		avengercustomfieldpage=avengersystemsettingpage.click_CustomFields();
		ArrayList<String> uiCustomList=avengercustomfieldpage.getcustomfieldvalues();
		customReport.customizedReport(true,apiutils.containsAll(apiCustomList,uiCustomList), statusValue, driver, sTestcaseName);
		customReport.customizedReport(1,avengercustomfieldpage.get_countOfElements(), statusValue, driver, sTestcaseName);
		avengercustomfieldpage.click_customfieldeditbutton1(customField);
		customReport.customizedReport(true,avengercustomefieldspage.verify_RequiredOrDisaplayPublicOptions("Required","no").contains("active"), statusValue, driver, sTestcaseName);
		customReport.customizedReport(true,avengercustomefieldspage.verify_RequiredOrDisaplayPublicOptions("Publicly Displayed","yes").contains("active"), statusValue, driver, sTestcaseName);
		customReport.customizedReport("Pick List",avengercustomefieldspage.get_customFieldType("Pick List"), statusValue, driver, sTestcaseName);
		customReport.customizedReport(customfieldsbean.getWidgetoptionvalue(),apiCFPickListOptions.get(0), statusValue, driver, sTestcaseName);
		customReport.customizedReport(customfieldsbean.getWidgetoptionvalue()+"a",apiCFPickListOptions.get(1), statusValue, driver, sTestcaseName);
		String url=homePage.getCurrentURL();
		String uiCFID = url.split("/")[9];
		customReport.customizedReport(apiCFID,uiCFID, statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		 
	}
	
	
	@Test(description="To Verify the CustomField using CustomField API with Media Admin ",groups = {GETCUSTOMFILEDSAPI})
	public void TC_06_GET_CustomField_api_check_With_MediaAdmin_Negative(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
			
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CustomFieldmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CustomFieldsAPI customfieldsAPI=new CustomFieldsAPI();
		customfieldapirespone = customfieldsAPI.getcustomfield(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(customfieldapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, customfieldapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description="To Verify the CustomField using CustomField API with Event Admin ",groups = {GETCUSTOMFILEDSAPI})
	public void TC_07_GET_CustomField_api_check_With_EventAdmin_Negative(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CustomFieldeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CustomFieldsAPI customfieldsAPI=new CustomFieldsAPI();
		customfieldapirespone = customfieldsAPI.getcustomfield(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(customfieldapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, customfieldapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description="To Verify the CustomField using CustomField API with MediaContributor ",groups = {GETCUSTOMFILEDSAPI})
	public void TC_08_GET_CustomField_api_check_With_MediaContributor_Negative(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CustomFieldmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CustomFieldsAPI customfieldsAPI=new CustomFieldsAPI();
		customfieldapirespone = customfieldsAPI.getcustomfield(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(customfieldapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, customfieldapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);	
				 
	}
	
	@Test(description="To Verify the CustomField using CustomField API with MediaViewer",groups = {GETCUSTOMFILEDSAPI})
	public void TC_09_GET_CustomField_api_check_With_MediaViewer_Negative(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CustomFieldmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CustomFieldsAPI customfieldsAPI=new CustomFieldsAPI();
		customfieldapirespone = customfieldsAPI.getcustomfield(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(customfieldapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, customfieldapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="To Verify the CustomField using CustomField API with EventHost",groups = {GETCUSTOMFILEDSAPI})
	public void TC_10_GET_CustomField_api_check_With_EventHost_Negative(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson1(IUsersList.CustomFieldeventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CustomFieldsAPI customfieldsAPI=new CustomFieldsAPI();
		customfieldapirespone = customfieldsAPI.getcustomfield(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(customfieldapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, customfieldapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="To Verify CustomField using CustomField API with Media Viewer after assigning to a group having access",groups = {GETCUSTOMFILEDSAPI})
	public void TC_11_GET_CustomField_api_check_With_MediaViewer_afterAssigningtoaGroup_Positive(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson1(IUsersList.CustomFieldmediaviewer1), surl);
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
	    HashMap<String, String> userroleapirespone = userServices.getRolesApi(loginapiresponse1,IAPIConstantCodes.ACCOUNTADMIN);
	    String roleid=apiutils.formatingapiresponse(userroleapirespone.get(IAPIConstantCodes.ROLEID));
	    String[] roleIds = {userroleapirespone.get("roleId")};
		addgroupbean.setRoleids(roleIds);
				
		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
		creategroupapirespone = creategroupAPI.createGroup(loginapiresponse1,creategroupjson);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		//Adding 5 different types of custom fields	
		String [] customfield=new String[50];
		launchURL(surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		homePage.clickSettingsLink();
		avengersystemsettingspage = homePage.clickSystemSettingsLink();
		avengercustomefieldspage=avengersystemsettingspage.click_CustomFields();
		avengercustomefieldspage.deleteallcustomfields();
		customReport.customizedReport(bundle.getString("customfields.nocustomfieldsmsg"),avengercustomefieldspage.verify_Label(bundle.getString("customfields.nocustomfieldsmsg")), statusValue, driver, sTestcaseName);
		
		//default custom field1 - Non Mandatory/Publicly displayed/type is text
		avengercustomefieldspage.click_addfieldbutton();
		avengercustomefieldspage.setcustmofieldname(customfieldsbean);
		avengercustomefieldspage.click_submitbutton();
		customReport.customizedReport(customfieldsbean.getCustomfieldname(),avengercustomefieldspage.verify_Label(customfieldsbean.getCustomfieldname()), statusValue, driver, sTestcaseName);
		customfield[0] = customfieldsbean.getCustomfieldname();
		
		//custom field2 - Non Mandatory/Not publicly displayed/type is picklist
		avengercustomefieldspage.click_addfieldbutton();
		accountBeansFactory.customfieldsBean(customfieldsbean);
		
		avengercustomefieldspage.setcustmofieldname(customfieldsbean);
		customfieldsbean.setWidgetvalue("Pick List");
		avengercustomefieldspage.selectwidgetvalue(customfieldsbean);
		avengercustomefieldspage.click_RequiredOrDisaplayPublicOptions("Publicly Displayed", "NO");
		avengercustomefieldspage.click_submitbutton();
		customReport.customizedReport(customfieldsbean.getCustomfieldname(),avengercustomefieldspage.verify_Label(customfieldsbean.getCustomfieldname()), statusValue, driver, sTestcaseName);
		customfield[1] = customfieldsbean.getCustomfieldname();
		
		//custom field3 - Mandatory/Publicly displayed/type is text
		avengercustomefieldspage.click_addfieldbutton();
		accountBeansFactory.customfieldsBean(customfieldsbean);		
		avengercustomefieldspage.setcustmofieldname(customfieldsbean);
		avengercustomefieldspage.click_RequiredOrDisaplayPublicOptions("Publicly Displayed", "YES");
		avengercustomefieldspage.click_RequiredOrDisaplayPublicOptions("Required", "YES");
		avengercustomefieldspage.click_submitbutton();
		customReport.customizedReport(customfieldsbean.getCustomfieldname(),avengercustomefieldspage.verify_Label(customfieldsbean.getCustomfieldname()), statusValue, driver, sTestcaseName);
		customfield[2] = customfieldsbean.getCustomfieldname();
		
		//custom field4 - Mandatory/Publicly displayed/type is picklist
		avengercustomefieldspage.click_addfieldbutton();
		accountBeansFactory.customfieldsBean(customfieldsbean);		
		avengercustomefieldspage.setcustmofieldname(customfieldsbean);
		customfieldsbean.setWidgetvalue("Pick List");
		avengercustomefieldspage.selectwidgetvalue(customfieldsbean);
		avengercustomefieldspage.click_RequiredOrDisaplayPublicOptions("Publicly Displayed", "YES");
		avengercustomefieldspage.click_RequiredOrDisaplayPublicOptions("Required", "YES");
		avengercustomefieldspage.click_submitbutton();
		customReport.customizedReport(customfieldsbean.getCustomfieldname(),avengercustomefieldspage.verify_Label(customfieldsbean.getCustomfieldname()), statusValue, driver, sTestcaseName);
		customfield[3] = customfieldsbean.getCustomfieldname();
		
		//custom field5 - Non Mandatory/Publicly displayed/type is picklist with 2 options
		avengercustomefieldspage.click_addfieldbutton();
		accountBeansFactory.customfieldsBean(customfieldsbean);			
		avengercustomefieldspage.setcustmofieldname(customfieldsbean);
		customfieldsbean.setWidgetvalue("Pick List");
		avengercustomefieldspage.selectwidgetvalue(customfieldsbean);//option1
		avengercustomefieldspage.selectwidgetvalue1(customfieldsbean);//option2
		avengercustomefieldspage.click_submitbutton();
		customReport.customizedReport(customfieldsbean.getCustomfieldname(),avengercustomefieldspage.verify_Label(customfieldsbean.getCustomfieldname()), statusValue, driver, sTestcaseName);
		customfield[3] = customfieldsbean.getCustomfieldname();
	    homePage.click_logout();
		
		//ended adding custom fields
		
		CustomFieldsAPI customfieldsAPI=new CustomFieldsAPI();
		customfieldapirespone = customfieldsAPI.getcustomfield(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(customfieldapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+customfieldapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, customfieldapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+customfieldapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		String fromattedJSON=apiutils.formatingapiresponse(customfieldapirespone.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> apiCustomList = new ArrayList<String>(Arrays.asList(fromattedJSON.split(",")));
		
		//Selenium Level Code
		launchURL(surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengersystemsettingpage=avengerHomePage.clickSystemSettingsLink();
		avengercustomfieldpage=avengersystemsettingpage.click_CustomFields();
		ArrayList<String> uiCustomList=avengercustomfieldpage.getcustomfieldvalues();
		customReport.customizedReport(true,apiutils.containsAll(apiCustomList,uiCustomList), statusValue, driver, sTestcaseName);
		
		//Deleting the added mandatory custom field		
		homePage.clickSettingsLink();
		avengersystemsettingspage = homePage.clickSystemSettingsLink();
		avengercustomefieldspage=avengersystemsettingspage.click_CustomFields();
		avengercustomefieldspage.deleteallcustomfields();
		customReport.customizedReport(bundle.getString("customfields.nocustomfieldsmsg"),avengercustomefieldspage.verify_Label(bundle.getString("customfields.nocustomfieldsmsg")), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		 
	}
	
	@Test(description="To delete all the existing custom fields at a time",groups = {GETCUSTOMFILEDSAPI})
	public void TC_12_Delete_alltheexisting_customfields_Positive(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		launchURL(surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		homePage.clickSettingsLink();
		avengersystemsettingspage = homePage.clickSystemSettingsLink();
		avengercustomefieldspage=avengersystemsettingspage.click_CustomFields();
		avengercustomefieldspage.deleteallcustomfields();
		customReport.customizedReport(bundle.getString("customfields.nocustomfieldsmsg"),avengercustomefieldspage.verify_Label(bundle.getString("customfields.nocustomfieldsmsg")), statusValue, driver, sTestcaseName);
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