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
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
import com.vbrick.avenger.pageobjects.AvengerCategoriesPage;
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
import com.vbricks.avenger.service.IUserAccountsService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.CreateCategoryAPI;
import com.vbricks.avenger.serviceimpl.CreateEventsAPI;
import com.vbricks.avenger.serviceimpl.CreateTeamAPI;
import com.vbricks.avenger.serviceimpl.CustomFieldsAPI;
import com.vbricks.avenger.serviceimpl.DMEdeviceslistAPI;
import com.vbricks.avenger.serviceimpl.DeleteCategoryAPI;
import com.vbricks.avenger.serviceimpl.DeleteTeamAPI;
import com.vbricks.avenger.serviceimpl.EditAccessControlEntitiesforeventAPI;
import com.vbricks.avenger.serviceimpl.EditEventAPI;
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

public class DeleteCategory extends TestBase {

	private static Logger logger = Logger.getLogger(DeleteCategory.class);
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
	private AvengerDevicesPage avengerdevicespage;
	private AvengerEventDetailsPage avengereventdetailspage;
	private AvengerPresentationProfilesPage avengerpresentationprofilespage;
	private AvengerAddPresentationProfilePage avengeraddpresentationprofilepage;
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
	private HashMap<String, String> loginapiresponse_mediaadmin;
	private AvengerMediaSettingsPage avengermediasettingpage;
	private HashMap<String, String> loginapiresponse_mediacontributor;
	private HashMap<String, String> loginapiresponse_eventadmin;
	private HashMap<String, String> loginapiresponse_mediaviewer;
	private HashMap<String, String> loginapiresponse1;
	private AvengerLibrariesPage avengerlibrarypage;
	private AddCategoryBean addcategorybean;
	private HashMap<String, String> deleteTeamapiresponse;
	private AssertionAPIResponse assertionapiresponse;
	private AddTeamBean addteambean;
	private HashMap<String, String> createteamapirespone;
	private HashMap<String, String> createcategoryapirespone;
	private HashMap<String,String> deletecategoryapirespone;
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
		avengergroupsbeanpage = new AvengerGroupsBeanPage();
		createEventBean = new CreateEventBean();
		apibeanfactory = new ApiBeanFactory();
		addcategorybean=new AddCategoryBean();
		addteambean=new AddTeamBean();
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

	@Test(description = "To Verify the DeleteCategory using API with Account Admin",groups = {DELETECATEGORY})
	public void TC_01_DELETE_DeletCategory_api_check_With_AccountAdmin_Positive(ITestContext context) throws InterruptedException {
		
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
		loginapiresponse.put(IAPIConstantCodes.CategoryID, createcategoryapirespone.get(IAPIConstantCodes.CategoryID));
  
		// Delete Category
		DeleteCategoryAPI deleteCategoryAPI = new DeleteCategoryAPI();
		deletecategoryapirespone = deleteCategoryAPI.deleteCategory(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(deletecategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode) + deletecategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,deletecategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode) + deletecategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage=loginPage.loginAs(sUserName,sPassword);
		homePage.clickSettingsLink();
		avengermediasettingpage= homePage.clickMediaSettingsLink();
		avengercategoriespage=avengermediasettingpage.click_CategoriesLinkLocator();
		Thread.sleep(40000);
        List<WebElement> categoryList=driver.findElements(By.xpath(" //div[@class='vb-droppable ui-droppable']/div/div[4]/a[@class='ng-binding']"));
        List<String> actualList=new ArrayList<String>();
         
        for(int i=0;i<categoryList.size();i++)
        {
        	actualList.add(categoryList.get(i).getText());
        }
        
		customReport.customizedReport(false,actualList.contains(addcategorybean.getName()), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To Verify the DeleteCategory using DeleteTeam API with Media Admin",groups = {DELETECATEGORY})
	public void TC_02_DELETE_DeleteCategory_api_check_With_MediaAdmin_Positive(ITestContext context) throws InterruptedException {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.DeleteCategorymediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		loginapiresponse.put(IAPIConstantCodes.CategoryID, createcategoryapirespone.get(IAPIConstantCodes.CategoryID));
  
		// Delete Category
		DeleteCategoryAPI deleteCategoryAPI = new DeleteCategoryAPI();
		deletecategoryapirespone = deleteCategoryAPI.deleteCategory(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(deletecategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode) + deletecategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,deletecategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode) + deletecategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage=loginPage.loginAs(sUserName,sPassword);
		homePage.clickSettingsLink();
		avengermediasettingpage= homePage.clickMediaSettingsLink();
		avengercategoriespage=avengermediasettingpage.click_CategoriesLinkLocator();
		Thread.sleep(40000);
        List<WebElement> categoryList=driver.findElements(By.xpath(" //div[@class='vb-droppable ui-droppable']/div/div[4]/a[@class='ng-binding']"));
        List<String> actualList=new ArrayList<String>();
         
        for(int i=0;i<categoryList.size();i++)
        {
        	actualList.add(categoryList.get(i).getText());
        }
        
		customReport.customizedReport(false,actualList.contains(addcategorybean.getName()), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To Verify the DeleteCategory using DeleteCategory API with Media Contributor",groups = {DELETECATEGORY})
	public void TC_03_DELETE_DeleteCategory_api_check_With_MediaContributor_Positive(ITestContext context) {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.DeleteCategorymediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
            
		UserServices userservices_mediacontributor = new UserServices();
		loginapiresponse_mediacontributor = userservices_mediacontributor.doLogin(apiutils.userJson(IUsersList.DeleteCategorymediacontributor), surl);  
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediacontributor.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_mediacontributor.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse_mediacontributor);
		
		loginapiresponse_mediacontributor.put(IAPIConstantCodes.CategoryID, createcategoryapirespone.get(IAPIConstantCodes.CategoryID));
		
		// Delete Category
		DeleteCategoryAPI deleteCategoryAPI = new DeleteCategoryAPI();
		deletecategoryapirespone = deleteCategoryAPI.deleteCategory(loginapiresponse_mediacontributor);assertionapiresponse.verifyAssert_httpCode(deletecategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401,deletecategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
				
	}
	
	@Test(description = "To Verify the DeleteCategory using DeleteCategory API with EventAdmin",groups = {DELETECATEGORY})
	public void TC_04_DELETE_DeleteCategory_api_check_With_EventAdmin_Positive(ITestContext context) {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.DeleteCategorymediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
            
		UserServices userservices_eventadmin = new UserServices();
		loginapiresponse_eventadmin = userservices_eventadmin.doLogin(apiutils.userJson(IUsersList.DeleteCategoryeventadmin), surl);  
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_eventadmin.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_eventadmin.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse_eventadmin);
		
		loginapiresponse_eventadmin.put(IAPIConstantCodes.CategoryID, createcategoryapirespone.get(IAPIConstantCodes.CategoryID));
		
		// Delete Category
		DeleteCategoryAPI deleteCategoryAPI = new DeleteCategoryAPI();
		deletecategoryapirespone = deleteCategoryAPI.deleteCategory(loginapiresponse_eventadmin);assertionapiresponse.verifyAssert_httpCode(deletecategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401,deletecategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
				
	}
	
	@Test(description = "To Verify the DeletCategory using DeletCategory API with mediaviewer",groups = {DELETECATEGORY})
	public void TC_05_DELETE_DeletCategory_api_check_With_MediaViewer_Update_Title_Positive(ITestContext context) {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.DeleteCategorymediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
            
		UserServices userservices_mediaviewer = new UserServices();
		loginapiresponse_mediaviewer = userservices_mediaviewer.doLogin(apiutils.userJson(IUsersList.DeleteCategorymediaviewer), surl);  
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediaviewer.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_mediaviewer.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse_mediaviewer);
		
		loginapiresponse_mediaviewer.put(IAPIConstantCodes.CategoryID, createcategoryapirespone.get(IAPIConstantCodes.CategoryID));
		
		// Delete Category
		DeleteCategoryAPI deleteCategoryAPI = new DeleteCategoryAPI();
		deletecategoryapirespone = deleteCategoryAPI.deleteCategory(loginapiresponse_mediaviewer);assertionapiresponse.verifyAssert_httpCode(deletecategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401,deletecategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver,sTestcaseName);
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
