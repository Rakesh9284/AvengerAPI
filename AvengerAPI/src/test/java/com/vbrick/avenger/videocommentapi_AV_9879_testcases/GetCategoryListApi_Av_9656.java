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
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbrick.avenger.apibeans.AddVideoCommentBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerCategoriesPage;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
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
import com.vbricks.avenger.service.IGetCategoryService;
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.CreateCategoryAPI;
import com.vbricks.avenger.serviceimpl.EditVideoRatingAPI;
import com.vbricks.avenger.serviceimpl.GetCategoryListAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.ConstantsValue;
import com.vbricks.avenger.utils.HttpStatusCode;
public class GetCategoryListApi_Av_9656 extends TestBase{

	private static Logger logger = Logger.getLogger(GetCategoryListApi_Av_9656.class);
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
	private AddCategoryBean addcategorybean;
	private ReadAndWriteToJSON readgriduserdata;
	private Map<String, String> userdata;
	private BasePage basePage;
	private AddUploadVideoBean adduploadvideobean;
	public ApiBeanFactory apibeanfactory;
    public AddVideoCommentBean addvideocommentbean;
	private HashMap<String, String> loginapiresponse;
	private HashMap<String, String> uploadvidoeapiresponse;
	private AssertionAPIResponse assertionapiresponse;
	private HashMap<String, String> createcategoryapirespone;
	private HashMap<String, String> childcategoryapirespone;
	private HashMap<String, String> getcategoryapirespone;
	private ApiUtils apiutils; 
	private AvengerHomePage avengerHomePage;
	private AvengerMediaSettingsPage avengermediasettingpage;
	private AvengerCategoriesPage avengercategoriespage;
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
		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		addvideocommentbean=new AddVideoCommentBean();
		addcategorybean=new AddCategoryBean();
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

	@Test(description="To Verify the CategoryList using CategoryList API with Account Admin ",groups = {GETCATEGORYLISTAPI})
	public void TC_01_GET_CategoryList_api_check_With_AccountAdmin_Positive(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
			
		HashMap<String,String > linktoMainCategroies=new HashMap<String,String >();
		linktoMainCategroies.put(ICreateCategoryAPI.name, ICreateCategoryAPI.childcategoryname);
		linktoMainCategroies.put(ICreateCategoryAPI.parentCategoryId, createcategoryapirespone.get(ICreateCategoryAPI.cID));
		
		JSONObject subcategroiesjson=createcategoryAPI.createChildCategoryJson(linktoMainCategroies);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse,subcategroiesjson);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		String childcategoryname = subcategroiesjson.get("name").toString();
				
		GetCategoryListAPI getcategoryListAPI=new GetCategoryListAPI();
		getcategoryapirespone = getcategoryListAPI.getcategory(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(getcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+getcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, getcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+getcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		String fromattedJSON=apiutils.formatingapiresponse(getcategoryapirespone.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> myList = new ArrayList<String>(Arrays.asList(fromattedJSON.split(",")));
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengermediasettingpage= avengerHomePage.clickMediaSettingsLink();
		avengercategoriespage=avengermediasettingpage.click_CategoriesLinkLocator();
		ArrayList<String> myList2 =avengercategoriespage.get_AllCategories();
		myList2.add(IGetCategoryService.unCategorized);	 
		customReport.customizedReport(true,apiutils.containsAll(myList,myList2), statusValue, driver, sTestcaseName);
		customReport.customizedReport(true,myList2.contains(childcategoryname), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);		
		
	}
	
	@Test(description="To Verify the CategoryList using CategoryList API with Media Admin ",groups = {GETCATEGORYLISTAPI})
	public void TC_02_GET_CategoryList_api_check_With_MediaAdmin_Positive(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.GetCategoryListmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
				
		HashMap<String,String > linktoMainCategroies=new HashMap<String,String >();
		linktoMainCategroies.put(ICreateCategoryAPI.name, ICreateCategoryAPI.childcategoryname);
		linktoMainCategroies.put(ICreateCategoryAPI.parentCategoryId, createcategoryapirespone.get(ICreateCategoryAPI.cID));
		
		JSONObject subcategroiesjson=createcategoryAPI.createChildCategoryJson(linktoMainCategroies);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse,subcategroiesjson);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		String childcategoryname = subcategroiesjson.get("name").toString();
		System.out.print(childcategoryname);
		
		GetCategoryListAPI getcategoryListAPI=new GetCategoryListAPI();
		getcategoryapirespone = getcategoryListAPI.getcategory(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(getcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+getcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, getcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+getcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		String fromattedJSON=apiutils.formatingapiresponse(getcategoryapirespone.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> myList = new ArrayList<String>(Arrays.asList(fromattedJSON.split(",")));
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage=loginPage.loginAs(IUsersList.GetCategoryListmediaadmin,sPassword);
		avengerHomePage.clickSettingsLink();
		avengermediasettingpage= avengerHomePage.clickMediaSettingsLink();
		avengercategoriespage=avengermediasettingpage.click_CategoriesLinkLocator();
		ArrayList<String> myList2 =avengercategoriespage.get_AllCategories();
		myList2.add(IGetCategoryService.unCategorized);	 
		customReport.customizedReport(true,apiutils.containsAll(myList,myList2), statusValue, driver, sTestcaseName);
		customReport.customizedReport(true,myList2.contains(childcategoryname), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);		
		
	}
	
	@Test(description="To Verify the CategoryList using CategoryList API with Event Admin ",groups = {GETCATEGORYLISTAPI})
	public void TC_03_GET_CategoryList_api_check_With_EventAdmin_Positive(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.GetCategoryListmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		GetCategoryListAPI getcategoryListAPI=new GetCategoryListAPI();
		getcategoryapirespone = getcategoryListAPI.getcategory(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(getcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+getcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, getcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+getcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		String fromattedJSON=apiutils.formatingapiresponse(getcategoryapirespone.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> myList = new ArrayList<String>(Arrays.asList(fromattedJSON.split(",")));
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengermediasettingpage= avengerHomePage.clickMediaSettingsLink();
		avengercategoriespage=avengermediasettingpage.click_CategoriesLinkLocator();
		ArrayList<String> myList2 =avengercategoriespage.get_AllCategories();
		myList2.add(IGetCategoryService.unCategorized);	 
		customReport.customizedReport(true,apiutils.containsAll(myList,myList2), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="To Verify the CategoryList using CategoryList API with Media Contributor ",groups = {GETCATEGORYLISTAPI})
	public void TC_04_GET_CategoryList_api_check_With_MediaContributor_Positive(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.GetCategoryListmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		GetCategoryListAPI getcategoryListAPI=new GetCategoryListAPI();
		getcategoryapirespone = getcategoryListAPI.getcategory(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(getcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+getcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, getcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+getcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
		String fromattedJSON=apiutils.formatingapiresponse(getcategoryapirespone.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> myList = new ArrayList<String>(Arrays.asList(fromattedJSON.split(",")));
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengermediasettingpage= avengerHomePage.clickMediaSettingsLink();
		avengercategoriespage=avengermediasettingpage.click_CategoriesLinkLocator();
		ArrayList<String> myList2 =avengercategoriespage.get_AllCategories();
		myList2.add(IGetCategoryService.unCategorized);	 
		customReport.customizedReport(true,apiutils.containsAll(myList,myList2), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description="To Verify the CategoryList using CategoryList API with Media Viewer",groups = {GETCATEGORYLISTAPI})
	public void TC_05_GET_CategoryList_api_check_With_MediaViewer_Positive(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.GetCategoryListmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		GetCategoryListAPI getcategoryListAPI=new GetCategoryListAPI();
		getcategoryapirespone = getcategoryListAPI.getcategory(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(getcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+getcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, getcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+getcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
		String fromattedJSON=apiutils.formatingapiresponse(getcategoryapirespone.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> myList = new ArrayList<String>(Arrays.asList(fromattedJSON.split(",")));
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengermediasettingpage= avengerHomePage.clickMediaSettingsLink();
		avengercategoriespage=avengermediasettingpage.click_CategoriesLinkLocator();
		ArrayList<String> myList2 =avengercategoriespage.get_AllCategories();
		myList2.add(IGetCategoryService.unCategorized);	 
		customReport.customizedReport(true,apiutils.containsAll(myList,myList2), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description="To Verify the CategoryList using CategoryList API with Event Host ",groups = {GETCATEGORYLISTAPI})
	public void TC_06_GET_CategoryList_api_check_With_EventHost_Positive(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.GetCategoryListeventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		GetCategoryListAPI getcategoryListAPI=new GetCategoryListAPI();
		getcategoryapirespone = getcategoryListAPI.getcategory(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(getcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+getcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, getcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+getcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
		String fromattedJSON=apiutils.formatingapiresponse(getcategoryapirespone.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> myList = new ArrayList<String>(Arrays.asList(fromattedJSON.split(",")));
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengermediasettingpage= avengerHomePage.clickMediaSettingsLink();
		avengercategoriespage=avengermediasettingpage.click_CategoriesLinkLocator();
		ArrayList<String> myList2 =avengercategoriespage.get_AllCategories();
		myList2.add(IGetCategoryService.unCategorized);	 
		customReport.customizedReport(true,apiutils.containsAll(myList,myList2), statusValue, driver, sTestcaseName);
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