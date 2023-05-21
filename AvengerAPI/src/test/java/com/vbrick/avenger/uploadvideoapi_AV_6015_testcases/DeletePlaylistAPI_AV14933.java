package com.vbrick.avenger.uploadvideoapi_AV_6015_testcases;
 
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
import com.vbrick.avenger.apibeans.AddPlaylistBean;
import com.vbrick.avenger.apibeans.AddTeamBean;
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbrick.avenger.apibeans.CreateEventBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AddCustomDeviceBeanPage;
import com.vbrick.avenger.dao.AddNewDmeBeanPage;
import com.vbrick.avenger.dao.AddNewPlaylistBeanPage;
import com.vbrick.avenger.dao.AddPresentationprofileBeanPage;
import com.vbrick.avenger.dao.AvengerGroupsBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLibrariesPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerAddCustomDevicePage;
import com.vbrick.avenger.pageobjects.AvengerAddPresentationProfilePage;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerDevicesPage;
import com.vbrick.avenger.pageobjects.AvengerEventDetailsPage;
import com.vbrick.avenger.pageobjects.AvengerGroupsPage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerMediaPage;
import com.vbrick.avenger.pageobjects.AvengerMediaSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerPlaylistPage;
import com.vbrick.avenger.pageobjects.AvengerPresentationProfilesPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.CreateGroupAPI;
import com.vbricks.avenger.serviceimpl.CreatePlaylistAPI;
import com.vbricks.avenger.serviceimpl.DeleteGroupAPI;
import com.vbricks.avenger.serviceimpl.DeletePlaylistAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class DeletePlaylistAPI_AV14933 extends TestBase {

	private static Logger logger = Logger.getLogger(CreatePlaylist_AV_14931.class);
	private AvengerLoginPage loginPage;
	private AvengerHomePage homePage;
	private AvengerMediaPage avengermediapage;
	private AvengerPlaylistPage avengerplaylistpage;
	private List<String> statusValue;
	private CustomReport customReport;
	@SuppressWarnings("unused")
	private Reasons reasons;
	private String sTestcaseName;
	private ResourceBundle bundle;
	private Locale locale;
	private BeanFactory accountBeansFactory;
	private AddPlaylistBean addplaylistbean;
	private MailinatorBeanPage mailinatorbeanpage;
	public ApiBeanFactory apibeanfactory;
	private HashMap<String, String> logoutuserapiresponse;
	private AddNewPlaylistBeanPage addnewplaylistbeanpage;
	private HashMap<String, String> deletePlaylistapiresponse;
	private HashMap<String, String> createplaylistapirespone;
	private FileUploadBeanPage fileuploadbeanpage;
	private ReadAndWriteToJSON readgriduserdata;
	private Map<String, String> userdata;
	private BasePage basePage;
	private AddUploadVideoBean adduploadvideobean;
	private AssertionAPIResponse assertionapiresponse;
	private HashMap<String, String> authenticationapiresponse;
	private HashMap<String, String> loginapiresponse;
	private HashMap<String, String> loginapiresponse1;
	private HashMap<String, String> uploadvidoeapiresponse;
    private ApiUtils apiutils;
    private AvengerHomePage avengerHomePage;
	

    @BeforeClass(alwaysRun = true)
	public void beforeClass(ITestContext context) throws Exception {
		reasons = new Reasons("");
		statusValue = new ArrayList<String>();
		customReport = new CustomReport();
		accountBeansFactory = new BeanFactory();
		mailinatorbeanpage = new MailinatorBeanPage();
		fileuploadbeanpage = new FileUploadBeanPage();
		locale = new Locale(language);
		readgriduserdata = new ReadAndWriteToJSON();
		userdata = readgriduserdata.readGridUserData(this.getClass().getSimpleName()); // ("user52");
		sUserName = userdata.get("Username");
		sPassword = userdata.get("Password");
		adduploadvideobean = new AddUploadVideoBean();
		addplaylistbean = new AddPlaylistBean();
		apibeanfactory = new ApiBeanFactory();
		assertionapiresponse = new AssertionAPIResponse();
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

	@Test(description = "To Verify the DeletePlaylist using DeleteplaylistAPI with Account Admin",groups = {DELETEPLAYLISTAPI })
	public void TC_01_DELETE_DeletePlaylist_api_check_With_AccountAdmin_Positive(ITestContext context) {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
 		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		loginapiresponse.put("Mandatory", "Yes");
		loginapiresponse.put("fileName", "Yes");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
		
		//playlist creation		
		CreatePlaylistAPI createPlaylistAPI=new CreatePlaylistAPI();
		apibeanfactory.CreatePlaylistBean(addplaylistbean);
		
		//getting videoid
		addplaylistbean.setVideoids(uploadvidoeapiresponse.get("videoId"));
		JSONObject createplaylistjson=createPlaylistAPI.createPlaylistJson(addplaylistbean);
		createplaylistapirespone = createPlaylistAPI.createPlaylist(loginapiresponse,createplaylistjson);
		assertionapiresponse.verifyAssert_httpCode(createplaylistapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createplaylistapirespone.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
 
		loginapiresponse.put(IAPIConstantCodes.APIResponsePLAYLISTID, createplaylistapirespone.get(IAPIConstantCodes.APIPLAYLISTID));
  
		// Delete Playlist
		DeletePlaylistAPI deletePlaylistAPI = new DeletePlaylistAPI();
		deletePlaylistapiresponse = deletePlaylistAPI.deletePlaylists(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(deletePlaylistapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deletePlaylistapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,deletePlaylistapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deletePlaylistapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
		
		//verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengermediapage =avengerdashboardpage.clickMediaTab();
		avengerplaylistpage = avengermediapage.clickPlaylistTab();
		customReport.customizedReport("0" ,String.valueOf(avengerplaylistpage.verifyPlaylistPresent(addplaylistbean.getName())), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	        
	}
	
	@Test(description = "To Verify the DeletePlaylist using DeleteplaylistAPI with Media Admin",groups = {DELETEPLAYLISTAPI })
	public void TC_02_DELETE_DeletePlaylist_api_check_With_MediaAdmin_Positive(ITestContext context) {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreatePlaylistMediaContributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
 		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		loginapiresponse.put("Mandatory", "Yes");
		loginapiresponse.put("fileName", "Yes");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
		
		//playlist creation with Media Admin		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.CreatePlaylistMediAdmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);
		
		CreatePlaylistAPI createPlaylistAPI=new CreatePlaylistAPI();
		apibeanfactory.CreatePlaylistBean(addplaylistbean);
		
		//getting videoid
		addplaylistbean.setVideoids(uploadvidoeapiresponse.get("videoId"));
		JSONObject createplaylistjson=createPlaylistAPI.createPlaylistJson(addplaylistbean);
		createplaylistapirespone = createPlaylistAPI.createPlaylist(loginapiresponse1,createplaylistjson);
		assertionapiresponse.verifyAssert_httpCode(createplaylistapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createplaylistapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
 
		loginapiresponse1.put(IAPIConstantCodes.APIResponsePLAYLISTID, createplaylistapirespone.get(IAPIConstantCodes.APIPLAYLISTID));
  
		// Delete Playlist
		DeletePlaylistAPI deletePlaylistAPI = new DeletePlaylistAPI();
		deletePlaylistapiresponse = deletePlaylistAPI.deletePlaylists(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(deletePlaylistapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deletePlaylistapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,deletePlaylistapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deletePlaylistapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
		
		//verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(IUsersList.CreatePlaylistMediAdmin, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengermediapage =avengerdashboardpage.clickMediaTab();
		avengerplaylistpage = avengermediapage.clickPlaylistTab();
		customReport.customizedReport("0" ,String.valueOf(avengerplaylistpage.verifyPlaylistPresent(addplaylistbean.getName())), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	        
	}
	
	@Test(description = "To Verify the DeletePlaylist using DeleteplaylistAPI with Event Admin",groups = {DELETEPLAYLISTAPI })
	public void TC_03_DELETE_DeletePlaylist_api_check_With_EventAdmin_Positive(ITestContext context) {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreatePlaylistMediaContributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
 		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		loginapiresponse.put("Mandatory", "Yes");
		loginapiresponse.put("fileName", "Yes");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
		
		//playlist creation with Event Admin		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.CreatePlaylistEventAdmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);
		
		CreatePlaylistAPI createPlaylistAPI=new CreatePlaylistAPI();
		apibeanfactory.CreatePlaylistBean(addplaylistbean);
		
		//getting videoid
		addplaylistbean.setVideoids(uploadvidoeapiresponse.get("videoId"));
		JSONObject createplaylistjson=createPlaylistAPI.createPlaylistJson(addplaylistbean);
		createplaylistapirespone = createPlaylistAPI.createPlaylist(loginapiresponse1,createplaylistjson);
		assertionapiresponse.verifyAssert_httpCode(createplaylistapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createplaylistapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
 
		loginapiresponse1.put(IAPIConstantCodes.APIResponsePLAYLISTID, createplaylistapirespone.get(IAPIConstantCodes.APIPLAYLISTID));
  
		// Delete Playlist
		DeletePlaylistAPI deletePlaylistAPI = new DeletePlaylistAPI();
		deletePlaylistapiresponse = deletePlaylistAPI.deletePlaylists(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(deletePlaylistapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deletePlaylistapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,deletePlaylistapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deletePlaylistapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
		
		//verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(IUsersList.CreatePlaylistEventAdmin, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengermediapage =avengerdashboardpage.clickMediaTab();
		avengerplaylistpage = avengermediapage.clickPlaylistTab();
		customReport.customizedReport("0" ,String.valueOf(avengerplaylistpage.verifyPlaylistPresent(addplaylistbean.getName())), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	        
	}
	
	@Test(description = "To Verify the DeletePlaylist using DeleteplaylistAPI with Event Host",groups = {DELETEPLAYLISTAPI })
	public void TC_04_DELETE_DeletePlaylist_api_check_With_EventHost_Positive(ITestContext context) {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreatePlaylistMediaContributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
 		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		loginapiresponse.put("Mandatory", "Yes");
		loginapiresponse.put("fileName", "Yes");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
		
		//playlist creation with Event Host		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.CreatePlaylistEventHost), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);
		
		CreatePlaylistAPI createPlaylistAPI=new CreatePlaylistAPI();
		apibeanfactory.CreatePlaylistBean(addplaylistbean);
		
		//getting videoid
		addplaylistbean.setVideoids(uploadvidoeapiresponse.get("videoId"));
		JSONObject createplaylistjson=createPlaylistAPI.createPlaylistJson(addplaylistbean);
		createplaylistapirespone = createPlaylistAPI.createPlaylist(loginapiresponse1,createplaylistjson);
		assertionapiresponse.verifyAssert_httpCode(createplaylistapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createplaylistapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
 
		loginapiresponse1.put(IAPIConstantCodes.APIResponsePLAYLISTID, createplaylistapirespone.get(IAPIConstantCodes.APIPLAYLISTID));
  
		// Delete Playlist
		DeletePlaylistAPI deletePlaylistAPI = new DeletePlaylistAPI();
		deletePlaylistapiresponse = deletePlaylistAPI.deletePlaylists(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(deletePlaylistapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deletePlaylistapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,deletePlaylistapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deletePlaylistapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
		
		//verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(IUsersList.CreatePlaylistEventHost, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengermediapage =avengerdashboardpage.clickMediaTab();
		avengerplaylistpage = avengermediapage.clickPlaylistTab();
		customReport.customizedReport("0" ,String.valueOf(avengerplaylistpage.verifyPlaylistPresent(addplaylistbean.getName())), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	        
	}
	
	
	@Test(description = "To Verify the DeletePlaylist using DeleteplaylistAPI with Media Viewer",groups = {DELETEPLAYLISTAPI })
	public void TC_05_DELETE_DeletePlaylist_api_check_With_MediaViewer_Positive(ITestContext context) {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();		
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreatePlaylistMediaContributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
 		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		loginapiresponse.put("Mandatory", "Yes");
		loginapiresponse.put("fileName", "Yes");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
		
		//playlist creation with Medai Viewer		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.CreatePlaylistMediaViewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);
		
		CreatePlaylistAPI createPlaylistAPI=new CreatePlaylistAPI();
		apibeanfactory.CreatePlaylistBean(addplaylistbean);
		
		//getting videoid
		addplaylistbean.setVideoids(uploadvidoeapiresponse.get("videoId"));
		JSONObject createplaylistjson=createPlaylistAPI.createPlaylistJson(addplaylistbean);
		createplaylistapirespone = createPlaylistAPI.createPlaylist(loginapiresponse1,createplaylistjson);
		assertionapiresponse.verifyAssert_httpCode(createplaylistapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createplaylistapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
 
		loginapiresponse1.put(IAPIConstantCodes.APIResponsePLAYLISTID, createplaylistapirespone.get(IAPIConstantCodes.APIPLAYLISTID));
  
		// Delete Playlist
		DeletePlaylistAPI deletePlaylistAPI = new DeletePlaylistAPI();
		deletePlaylistapiresponse = deletePlaylistAPI.deletePlaylists(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(deletePlaylistapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deletePlaylistapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,deletePlaylistapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deletePlaylistapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
		
		//verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(IUsersList.CreatePlaylistMediaViewer, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengermediapage =avengerdashboardpage.clickMediaTab();
		avengerplaylistpage = avengermediapage.clickPlaylistTab();
		customReport.customizedReport("0" ,String.valueOf(avengerplaylistpage.verifyPlaylistPresent(addplaylistbean.getName())), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	        
	}
	
	@Test(description = "To Verify the DeletePlaylist using DeleteplaylistAPI with Media Contributor",groups = {DELETEPLAYLISTAPI })
	public void TC_06_DELETE_DeletePlaylist_api_check_With_Mediacontributor_Positive(ITestContext context) {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.CreatePlaylistMediAdmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
 		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		loginapiresponse.put("Mandatory", "Yes");
		loginapiresponse.put("fileName", "Yes");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
		
		//playlist creation with Media Contributor		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.CreatePlaylistMediaContributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);
		
		CreatePlaylistAPI createPlaylistAPI=new CreatePlaylistAPI();
		apibeanfactory.CreatePlaylistBean(addplaylistbean);
		
		//getting videoid
		addplaylistbean.setVideoids(uploadvidoeapiresponse.get("videoId"));
		JSONObject createplaylistjson=createPlaylistAPI.createPlaylistJson(addplaylistbean);
		createplaylistapirespone = createPlaylistAPI.createPlaylist(loginapiresponse1,createplaylistjson);
		assertionapiresponse.verifyAssert_httpCode(createplaylistapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createplaylistapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
 
		loginapiresponse1.put(IAPIConstantCodes.APIResponsePLAYLISTID, createplaylistapirespone.get(IAPIConstantCodes.APIPLAYLISTID));
  
		// Delete Playlist
		DeletePlaylistAPI deletePlaylistAPI = new DeletePlaylistAPI();
		deletePlaylistapiresponse = deletePlaylistAPI.deletePlaylists(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(deletePlaylistapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deletePlaylistapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,deletePlaylistapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deletePlaylistapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
		
		//verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(IUsersList.CreatePlaylistMediaContributor, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengermediapage =avengerdashboardpage.clickMediaTab();
		avengerplaylistpage = avengermediapage.clickPlaylistTab();
		customReport.customizedReport("0" ,String.valueOf(avengerplaylistpage.verifyPlaylistPresent(addplaylistbean.getName())), statusValue, driver, sTestcaseName);
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