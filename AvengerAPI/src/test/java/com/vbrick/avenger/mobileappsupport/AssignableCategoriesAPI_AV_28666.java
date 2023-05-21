package com.vbrick.avenger.mobileappsupport;

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
import org.openqa.selenium.By;
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
import com.vbrick.avenger.apibeans.AddGroupBean;
import com.vbrick.avenger.apibeans.AddTeamBean;
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AvengerEditVideoSettingsBeanPage;
import com.vbrick.avenger.dao.AvengerGroupsBeanPage;
import com.vbrick.avenger.dao.AvengerManageCustomFieldsBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.LibraryBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerCategoriesPage;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerEditVideoSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerGroupsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLibrariesPage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerManageCustomFieldsPage;
import com.vbrick.avenger.pageobjects.AvengerMediaSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerSystemSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerUserDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.service.IVideoAccessControlService;
import com.vbricks.avenger.serviceimpl.AssignableCategoriesAPI;
import com.vbricks.avenger.serviceimpl.CreateCategoryAPI;
import com.vbricks.avenger.serviceimpl.CreateGroupAPI;
import com.vbricks.avenger.serviceimpl.CreateTeamAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class AssignableCategoriesAPI_AV_28666 extends TestBase {

	private static Logger logger = Logger.getLogger(AssignableCategoriesAPI_AV_28666.class);
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
	private HashMap<String, String> createcategoryapirespone;
	private HashMap<String, String> createcategoryapirespone1;
	private AvengerSystemSettingsPage avengersystemsettingspage;
	private AvengerManageCustomFieldsPage avengercustomefieldspage;
	private HashMap<String, String> createteamapirespone;
	private HashMap<String, String> createteamapirespone1;	
	private AddTeamBean addteambean;
	private AvengerManageCustomFieldsBeanPage customfieldsbean;
	private AvengerEditVideoSettingsBeanPage avengereditvideosettingsbeanpage;
	private HashMap<String, String> creategroupapirespone;
	private AvengerLibrariesPage avengerlibrarypage;
	private AddCategoryBean addcategorybean;
	private ReadAndWriteToJSON readgriduserdata;
	private AddGroupBean addgroupbean;
	private Map<String, String> userdata;
	private BasePage basePage;
	private AddUploadVideoBean adduploadvideobean;
	private LibraryBeanPage librarybeanpage;
	public ApiBeanFactory apibeanfactory;
	private AssertionAPIResponse assertionapiresponse;
	private AvengerGroupsBeanPage avengergroupsbeanpage;
	private AvengerGroupsPage avengergroupspage;
	private AvengerUserDashboardPage avengeruserdashboardpage;
	private HashMap<String, String> loginapiresponse;
	private HashMap<String, String> loginapiresponse1;
	private HashMap<String, String> uploadvidoeapiresponse;
    private ApiUtils apiutils;
	@BeforeClass(alwaysRun = true)
	public void beforeClass(ITestContext context) throws Exception {
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
		addteambean=new AddTeamBean();
		avengereditvideosettingsbeanpage = new AvengerEditVideoSettingsBeanPage();
		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();

		avengergroupsbeanpage = new AvengerGroupsBeanPage();
		apibeanfactory = new ApiBeanFactory();
		addcategorybean=new AddCategoryBean();
		addgroupbean=new AddGroupBean();
		assertionapiresponse = new AssertionAPIResponse();
		librarybeanpage = new LibraryBeanPage();
		apiutils=new ApiUtils();
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
		loginPage = basePage.avengerLoginPage(driver, customReport, basePage);
		driver.manage().window().maximize();
		accountBeansFactory.customfieldsBean(customfieldsbean);
		accountBeansFactory.AvengerEditVideoSettingsBeanByPage(avengereditvideosettingsbeanpage);
		accountBeansFactory.MailinatorBean(mailinatorbeanpage);
		accountBeansFactory.AvengergroupsBean(avengergroupsbeanpage);
		accountBeansFactory.LibraryBeanByPage(librarybeanpage);

	}
	
	@Test(description="To Verify the AssignableCategories using AssignableCategories API with Account Admin when there are no Restricted Categories ",groups = {CREATECATEGORYAPI})
	public void TC_01_GET_AssignableCategories_api_check_With_AccountAdmin_Positive(ITestContext context ) throws InterruptedException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UploadVideosmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
 		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.UploadVideosmediaadmin);
		adduploadvideobean.setDescription("");
		adduploadvideobean.setEnableComments("");
		adduploadvideobean.setEnableDownloads("");
		adduploadvideobean.setEnableRatings("");
		adduploadvideobean.setIsActive("");
		adduploadvideobean.setVideoAccessControl(null);
		adduploadvideobean.setCategories(null);
		adduploadvideobean.setCategoryIds(null);
		adduploadvideobean.setTags(null);
		adduploadvideobean.setTitle("Catcre");		
		loginapiresponse.put("Mandatory", "Yes");
		loginapiresponse.put("fileName", "Yes");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);	
		
		CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		AssignableCategoriesAPI assignableCategoriesAPI = new AssignableCategoriesAPI();
		HashMap<String, String> getAssignableCategoriesapiresponse = assignableCategoriesAPI.getAssignableCategories(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(getAssignableCategoriesapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getAssignableCategoriesapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		String fromattedJSONforuser=apiutils.formatingapiresponse(getAssignableCategoriesapiresponse.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> apiassignedCategoriesList = new ArrayList<String>(Arrays.asList(fromattedJSONforuser.split(",")));
		Collections.sort(apiassignedCategoriesList);
		int apiCategoriesCount = apiassignedCategoriesList.size();
		logger.info("API User Entities list count is " +apiCategoriesCount);
		
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.clickVideoBasicinformationButton();
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
		homePage.clickSettingsLink();
		AvengerMediaSettingsPage avengermediasettingspage=homePage.clickMediaSettingsLink();
		AvengerCategoriesPage avengercategoriesspage=avengermediasettingspage.click_CategoriesLinkLocator();
		ArrayList<String> uiassignedCategoriesList = avengercategoriesspage.get_AllCategories();
		Collections.sort(uiassignedCategoriesList);
		int uiCategoriesCount = uiassignedCategoriesList.size();
		logger.info("@@@UI User Entities list count is " +uiCategoriesCount);
		
		if (apiCategoriesCount == uiCategoriesCount) {
			customReport.customizedReport(true,apiutils.containsAll(apiassignedCategoriesList, uiassignedCategoriesList), statusValue, driver, sTestcaseName);
			customReport.customizedReport(true,apiutils.containsAll(apiassignedCategoriesList, uiassignedCategoriesList), statusValue, driver, sTestcaseName);
			logger.info("@@@API Count is" +apiCategoriesCount+ "@@@and UI Count is" +uiCategoriesCount);
		}
		else {
		customReport.customizedReport(apiCategoriesCount,uiCategoriesCount, statusValue, driver, sTestcaseName);
		}
		customReport.checkinglist(statusValue);			
	}
	
	@Test(description="To Verify the AssignableCategories using AssignableCategories API with Account Admin when THERE IS a Restricted Categories ",groups = {CREATECATEGORYAPI})
	public void TC_02_GET_AssignableCategories_api_check_With_AccountAdmin_Positive(ITestContext context ) throws InterruptedException {
		//Account Admin can see both restricted & Non-restricted categories
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UploadVideosmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
 		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.UploadVideosmediaadmin);
		adduploadvideobean.setDescription("");
		adduploadvideobean.setEnableComments("");
		adduploadvideobean.setEnableDownloads("");
		adduploadvideobean.setEnableRatings("");
		adduploadvideobean.setIsActive("");
		adduploadvideobean.setVideoAccessControl(null);
		adduploadvideobean.setCategories(null);
		adduploadvideobean.setCategoryIds(null);
		adduploadvideobean.setTags(null);
		adduploadvideobean.setTitle("Catcre");		
		loginapiresponse.put("Mandatory", "Yes");
		loginapiresponse.put("fileName", "Yes");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);	
		
		CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson=createcategoryAPI.createCategoryJsonforrestrictedcategory(addcategorybean);
		createcategoryapirespone = createcategoryAPI.createrestrictedcategory(loginapiresponse,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		AssignableCategoriesAPI assignableCategoriesAPI = new AssignableCategoriesAPI();
		HashMap<String, String> getAssignableCategoriesapiresponse = assignableCategoriesAPI.getAssignableCategories(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(getAssignableCategoriesapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getAssignableCategoriesapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		String fromattedJSONforuser=apiutils.formatingapiresponse(getAssignableCategoriesapiresponse.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> apiassignedCategoriesList = new ArrayList<String>(Arrays.asList(fromattedJSONforuser.split(",")));
		Collections.sort(apiassignedCategoriesList);
		int apiCategoriesCount = apiassignedCategoriesList.size();
		logger.info("API User Entities list count is " +apiCategoriesCount);
		
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.clickVideoBasicinformationButton();
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
		homePage.clickSettingsLink();
		AvengerMediaSettingsPage avengermediasettingspage=homePage.clickMediaSettingsLink();
		AvengerCategoriesPage avengercategoriesspage=avengermediasettingspage.click_CategoriesLinkLocator();
		ArrayList<String> uiassignedCategoriesList = avengercategoriesspage.get_AllCategories();
		Collections.sort(uiassignedCategoriesList);
		int uiCategoriesCount = uiassignedCategoriesList.size();
		logger.info("@@@UI User Entities list count is " +uiCategoriesCount);
		
		if (apiCategoriesCount == uiCategoriesCount) {
			customReport.customizedReport(true,apiutils.containsAll(apiassignedCategoriesList, uiassignedCategoriesList), statusValue, driver, sTestcaseName);
			customReport.customizedReport(true,apiutils.containsAll(apiassignedCategoriesList, uiassignedCategoriesList), statusValue, driver, sTestcaseName);
			logger.info("@@@API Count is" +apiCategoriesCount+ "@@@and UI Count is" +uiCategoriesCount);
		}
		else {
		customReport.customizedReport(apiCategoriesCount,uiCategoriesCount, statusValue, driver, sTestcaseName);
		}
		customReport.checkinglist(statusValue);			
	}
	
	@Test(description="To Verify the AssignableCategories using AssignableCategories API with Media Contributor",groups = {CREATECATEGORYAPI})
	public void TC_03_GET_AssignableCategories_api_check_With_MediaContributor_Positive(ITestContext context ) throws InterruptedException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		
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
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.GetAssignableCategoriesMC), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);		
		
		AssignableCategoriesAPI assignableCategoriesAPI = new AssignableCategoriesAPI();
		HashMap<String, String> getAssignableCategoriesapiresponse = assignableCategoriesAPI.getAssignableCategories(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(getAssignableCategoriesapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getAssignableCategoriesapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		String fromattedJSONforuser=apiutils.formatingapiresponse(getAssignableCategoriesapiresponse.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> apiassignedCategoriesList = new ArrayList<String>(Arrays.asList(fromattedJSONforuser.split(",")));
		Collections.sort(apiassignedCategoriesList);
		int apiCategoriesCount = apiassignedCategoriesList.size();
		logger.info("API User Entities list count is " +apiCategoriesCount);

 		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.GetAssignableCategoriesMC);
		adduploadvideobean.setDescription("");
		adduploadvideobean.setTitle("APIIA");
		adduploadvideobean.setEnableComments("");
		adduploadvideobean.setEnableDownloads("");
		adduploadvideobean.setEnableRatings("");
		adduploadvideobean.setIsActive("");
		adduploadvideobean.setVideoAccessControl(null);
		adduploadvideobean.setCategories(null);
		adduploadvideobean.setCategoryIds(null);
		adduploadvideobean.setTags(null);		
		loginapiresponse.put("Mandatory", "Yes");
		loginapiresponse.put("fileName", "Yes");
				
		loginapiresponse.put("accesstoken", loginapiresponse1.get("accesstoken"));
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
				

		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(IUsersList.GetAssignableCategoriesMC, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia("APIIA");
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo("APIIA");
		commentspage.clickVideoBasicinformationButton();
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
		avengeruserdashboardpage = commentspage.clickoncategorypicker();
		avengereditvideosettingspage.selectanycategory(apiassignedCategoriesList.get(0).toString());
		customReport.customizedReport(apiassignedCategoriesList.get(0).toString(), avengereditvideosettingspage.verify_label(apiassignedCategoriesList.get(0).toString()),statusValue, driver, sTestcaseName);
		customReport.customizedReport(true,apiassignedCategoriesList.contains(apiassignedCategoriesList.get(0).toString()) ,statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);			
	}
			
	@Test(description="To Verify the AssignableCategories using AssignableCategories API with Media Viewer",groups = {CREATECATEGORYAPI})
	public void TC_04_GET_AssignableCategories_api_check_With_MediaViewer_Positive(ITestContext context ) throws InterruptedException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson1(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
			
 		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);
		String accesscontrolforuser = IUsersList.GetAssignableCategoriesMV;
		adduploadvideobean.setAccesscontrolforuser(accesscontrolforuser);		
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);	
		
		CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.GetAssignableCategoriesMV), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);
		
		AssignableCategoriesAPI assignableCategoriesAPI = new AssignableCategoriesAPI();
		HashMap<String, String> getAssignableCategoriesapiresponse = assignableCategoriesAPI.getAssignableCategories(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(getAssignableCategoriesapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getAssignableCategoriesapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		String fromattedJSONforuser=apiutils.formatingapiresponse(getAssignableCategoriesapiresponse.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> apiassignedCategoriesList = new ArrayList<String>(Arrays.asList(fromattedJSONforuser.split(",")));
		Collections.sort(apiassignedCategoriesList);
	
		
		//Verification through UI
				logger.info("Selenium Code is excuting");
				launchURL(surl);
				customReport.reporter("Application launch with URL : ", surl);
				homePage = loginPage.loginAs(IUsersList.GetAssignableCategoriesMV, sPassword);
				AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
				avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
				AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
				commentspage.clickVideoBasicinformationButton();
				commentspage.click_settingsLink();
				commentspage.click_details();
				AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
				avengeruserdashboardpage = commentspage.clickoncategorypicker();
				avengereditvideosettingspage.selectanycategory(apiassignedCategoriesList.get(0).toString());
				customReport.customizedReport(apiassignedCategoriesList.get(0).toString(), avengereditvideosettingspage.verify_label(apiassignedCategoriesList.get(0).toString()),statusValue, driver, sTestcaseName);
				customReport.customizedReport(true,apiassignedCategoriesList.contains(apiassignedCategoriesList.get(0).toString()) ,statusValue, driver, sTestcaseName);
				 
	}
	
	
	@Test(description="To Verify the AssignableCategories using AssignableCategories API with Account Admin when there are no Restricted Categories ",groups = {CREATECATEGORYAPI})
	public void TC_05_GET_AssignableCategories_api_check_Without_Authorization(ITestContext context ) throws InterruptedException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UploadVideosmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
 		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.UploadVideosmediaadmin);
		adduploadvideobean.setDescription("");
		adduploadvideobean.setEnableComments("");
		adduploadvideobean.setEnableDownloads("");
		adduploadvideobean.setEnableRatings("");
		adduploadvideobean.setIsActive("");
		adduploadvideobean.setVideoAccessControl(null);
		adduploadvideobean.setCategories(null);
		adduploadvideobean.setCategoryIds(null);
		adduploadvideobean.setTags(null);
		adduploadvideobean.setTitle("Catcre");		
		loginapiresponse.put("Mandatory", "Yes");
		loginapiresponse.put("fileName", "Yes");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);	
		
		CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		AssignableCategoriesAPI assignableCategoriesAPI = new AssignableCategoriesAPI();
		HashMap<String, String> getAssignableCategoriesapiresponse = assignableCategoriesAPI.getAssignableCategorieswithoutauthorization(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(getAssignableCategoriesapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, getAssignableCategoriesapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
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
	