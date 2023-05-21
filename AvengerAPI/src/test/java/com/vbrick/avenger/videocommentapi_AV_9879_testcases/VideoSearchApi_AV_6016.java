package com.vbrick.avenger.videocommentapi_AV_9879_testcases;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
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
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerEditVideoSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerMediaPage;
import com.vbrick.avenger.pageobjects.AvengerVideoBulkEditPage;
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.pageobjects.AvengerVideosPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.service.IVideoAccessControlService;
import com.vbricks.avenger.serviceimpl.CreateCategoryAPI;
import com.vbricks.avenger.serviceimpl.DeleteVideosAPI;
import com.vbricks.avenger.serviceimpl.VideoSearchAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.serviceimpl.VideoListAPI;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class VideoSearchApi_AV_6016 extends TestBase {

	private static Logger logger = Logger.getLogger(VideoSearchApi_AV_6016.class);
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
	private BasePage basePage;
	private AddUploadVideoBean adduploadvideobean;
	public ApiBeanFactory apibeanfactory;
	public AddVideoCommentBean addvideocommentbean;
 	private HashMap<String, String> loginapiresponse;
	private HashMap<String, String> uploadvidoeapiresponse;
	private HashMap<String,String> loginapiresponse_mediaviewer;
	private HashMap<String,String> loginapiresponse_eventadmin;
	private HashMap<String,String> loginapiresponse_mediacontributor;
	private HashMap<String,String> loginapiresponse_mediaadmin;
    private AssertionAPIResponse assertionapiresponse;
    private HashMap<String, String> createcategoryapirespone;
    private HashMap<String, String> childcategoryapirespone;
    private HashMap<String, String> uploadvidoeapiresponse_mediaadmin;
	private AddCategoryBean addcategorybean;
	private AvengerMediaPage avengerMediapage;
	private AvengerVideoBulkEditPage avengervideobulkeditpage;
	private AvengerVideosPage avengerVideosPage;
	
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
		addcategorybean=new AddCategoryBean();
		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		addvideocommentbean = new AddVideoCommentBean();
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

	@Test(description = "To verify Search Content to specific video using Search Content API with accountadmin",groups = {SEARCHVIDEOAPI})
	public void TC_01_GET_SearchVideo_api_check_SearchVideo_with_AccoutAdmin_Positive() throws Exception {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Create Category
		CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		//Upload Video
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		String[] categorieArray={(String) categroiesjson.get(IAPIConstantCodes.APINAME)};
		adduploadvideobean.setCategories(categorieArray);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
  		loginapiresponse.put(IAPIConstantCodes.SEARCHCATEGORIES, createcategoryapirespone.get(IAPIConstantCodes.CATEGORYTD));
		loginapiresponse.put(IAPIConstantCodes.STATUS, IAPIConstantCodes.ACTIVE);
		loginapiresponse.put(IAPIConstantCodes.COUNT, "1");
		//loginapiresponse.put("q", "");
		loginapiresponse.put("q", adduploadvideobean.getTitle()); 
	 	loginapiresponse.put("Uploaders", sUserName);	 	
	 	loginapiresponse.put("UploaderIds",loginapiresponse.get("userId")) ;
        //Thread.sleep(240000);
	 	
	 	//queryParams path.. :::: {Status=[Active], q=[XXXX], count=[1], categories=[XXXX], UploaderIds=[XXXX]}
		VideoSearchAPI searchvideoAPI=new VideoSearchAPI();
	    HashMap<String, String> searchvideoapirespone = searchvideoAPI.searchVideo(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
 
		assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IVideoAccessControlService.videoTitle),adduploadvideobean.getTitle());
		customReport.customizedReport(adduploadvideobean.getTitle(),searchvideoapirespone.get(IVideoAccessControlService.videoTitle),statusValue, driver, sTestcaseName);
		assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IVideoAccessControlService.videoCount), "1");
		customReport.customizedReport("1", searchvideoapirespone.get(IVideoAccessControlService.videoCount),statusValue, driver, sTestcaseName);
		
	    launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		adduploadvideobean.setTitle(searchvideoapirespone.get(IVideoAccessControlService.videoTitle));
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
		commentspage.click_settingsLink();
		commentspage.click_deleteButton();
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify Search Content to specific video using Search Content API with accountadmin",groups = {SEARCHVIDEOAPI})
	public void TC_02_GET_SearchVideo_with_Count5_api_check_SearchVideo_with_AccoutAdmin_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//queryParams path.. :::: {q=[XXX], count=[5]}		
		loginapiresponse.put(IAPIConstantCodes.COUNT, "5");
		loginapiresponse.put("q", "API"); 
	 
		VideoSearchAPI searchvideoAPI=new VideoSearchAPI();
	    HashMap<String, String> searchvideoapirespone = searchvideoAPI.searchVideo(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);		
		assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IVideoAccessControlService.totalvideos), "5");
		customReport.customizedReport("5", searchvideoapirespone.get(IVideoAccessControlService.totalvideos),statusValue, driver, sTestcaseName);
		
	  }
	
	@Test(description = "To verify Search Content to specific video using Search Content API with accountadmin",groups = {SEARCHVIDEOAPI})
	public void TC_03_GET_SearchVideo_with_InvalidName_api_check_SearchVideo_with_AccoutAdmin_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		loginapiresponse.put("q", "XYZ"); 
		
		//queryParams path.. :::: {q=[XXX]}
		VideoSearchAPI searchvideoAPI=new VideoSearchAPI();
	    HashMap<String, String> searchvideoapirespone = searchvideoAPI.searchVideo(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IVideoAccessControlService.totalvideos), "0");
		customReport.customizedReport("0", searchvideoapirespone.get(IVideoAccessControlService.totalvideos),statusValue, driver, sTestcaseName);
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		adduploadvideobean.setTitle(loginapiresponse.get("q"));
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		customReport.customizedReport("No Results Found", avengerdashboardpage.verify_label2("No Results Found"),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	  }
	
	@Test(description = "To verify Search Content to specific video using Search Content API with media admin",groups = {SEARCHVIDEOAPI})
	public void TC_04_GET_SearchVideo_api_check_SearchVideo_with_Mediaadmin_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.VideoSearchmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Upload Video
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		String tag1=RandomStringUtils.randomAlphanumeric(6);
		String tags[] = {tag1};
		adduploadvideobean.setTags(tags);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		 
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
 		loginapiresponse.put("q", tag1);
 		loginapiresponse.put(IAPIConstantCodes.ExactMatch, "true");
		loginapiresponse.put(IAPIConstantCodes.STATUS, IAPIConstantCodes.ACTIVE);
		loginapiresponse.put(IAPIConstantCodes.COUNT, "1");
	 	loginapiresponse.put(IAPIConstantCodes.SearchField, "tags");
	 	
	 	//queryParams path.. :::: {Status=[Active], q=[XXX XXX], exactMatch=[true], searchField=[tags], count=[1]}		
		VideoSearchAPI searchvideoAPI=new VideoSearchAPI();
	    HashMap<String, String> searchvideoapirespone = searchvideoAPI.searchVideo(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	
		assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IVideoAccessControlService.videoTitle),adduploadvideobean.getTitle());
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IVideoAccessControlService.videoCount), "1");
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
	    launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		adduploadvideobean.setTitle(searchvideoapirespone.get(IVideoAccessControlService.videoTitle));
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
		commentspage.click_settingsLink();
		commentspage.click_deleteButton();
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify Search Content to specific video using Search Content API with media admin",groups = {SEARCHVIDEOAPI})
	public void TC_05_GET_SearchVideo_api_check_SearchVideo_with_Mediaadmin_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.VideoSearchmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Upload Video
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		String tag1=RandomStringUtils.randomAlphanumeric(6);
		String tag2=RandomStringUtils.randomAlphanumeric(6);
		String tags[] = {tag1,tag2};
		adduploadvideobean.setTags(tags);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		 
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);				
 		loginapiresponse.put("q", tag1);
		loginapiresponse.put(IAPIConstantCodes.STATUS, IAPIConstantCodes.ACTIVE);
	 	loginapiresponse.put(IAPIConstantCodes.SearchField, "tags");
	 	
	 	//queryParams path.. :::: {Status=[Active], q=[XXX], searchField=[tags]}		
		VideoSearchAPI searchvideoAPI=new VideoSearchAPI();
	    HashMap<String, String> searchvideoapirespone = searchvideoAPI.searchVideo(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	
	    customReport.customizedReport(true, searchvideoapirespone.get(IVideoAccessControlService.videoTitle).contains(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
	    launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		adduploadvideobean.setTitle(searchvideoapirespone.get(IVideoAccessControlService.videoTitle));
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
		commentspage.click_settingsLink();
		commentspage.click_deleteButton();
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify Search Content to specific video using Search Content API with media admin",groups = {SEARCHVIDEOAPI})
	public void TC_06_GET_SearchVideo_api_check_SearchVideo_with_Mediaadmin_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.VideoSearchmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Upload Video
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		String randomtitle=RandomStringUtils.randomAlphanumeric(6);
		adduploadvideobean.setTitle(randomtitle);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		 
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//queryParams path.. :::: {Status=[Active], q=[check]}		
 		loginapiresponse.put("q", randomtitle);
		loginapiresponse.put(IAPIConstantCodes.STATUS, IAPIConstantCodes.ACTIVE);
		
		VideoSearchAPI searchvideoAPI=new VideoSearchAPI();
	    HashMap<String, String> searchvideoapirespone = searchvideoAPI.searchVideo(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	
	    customReport.customizedReport(true, searchvideoapirespone.get(IVideoAccessControlService.videoTitle).contains(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
	    launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		adduploadvideobean.setTitle(searchvideoapirespone.get(IVideoAccessControlService.videoTitle));
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify Search Content to specific video using Search Content API with Eventadmin",groups = {SEARCHVIDEOAPI})
	public void TC_07_GET_SearchVideo_api_check_SearchVideo_with_Eventadmin_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Create Category
		CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
						
		//Upload Video
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		String[] categorieArray={(String) categroiesjson.get(IAPIConstantCodes.APINAME)};
		adduploadvideobean.setCategories(categorieArray);
		String tag1=RandomStringUtils.randomAlphanumeric(6);
		String tags[] = {tag1};
		adduploadvideobean.setTags(tags);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
			 
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse_eventadmin = userservices.doLogin(apiutils.userJson(IUsersList.VideoSearcheventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_eventadmin.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse_eventadmin);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_eventadmin.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse_eventadmin.put("q", tag1);
		loginapiresponse_eventadmin.put(IAPIConstantCodes.STATUS, IAPIConstantCodes.ACTIVE);
		loginapiresponse_eventadmin.put(IAPIConstantCodes.SearchField, "tags");
		loginapiresponse_eventadmin.put(IAPIConstantCodes.TYPE, "Vod");
		
		//queryParams path.. :::: {Status=[Active], q=[XXX], Type=[Vod], searchField=[tags]}
		VideoSearchAPI searchvideoAPI=new VideoSearchAPI();
	    HashMap<String, String> searchvideoapirespone = searchvideoAPI.searchVideo(loginapiresponse_eventadmin);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);	    
	    customReport.customizedReport(true, searchvideoapirespone.get(IVideoAccessControlService.videoTitle).contains(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
	    launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
		commentspage.click_settingsLink();
		commentspage.click_deleteButton();
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify Search Content to specific video using Search Content API with MediaContributor",groups = {SEARCHVIDEOAPI})
	public void TC_08_GET_SearchVideo_api_check_SearchVideo_with_MediaContributor_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Create Category
		CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
						
		//Upload Video
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		String[] categorieArray={(String) categroiesjson.get(IAPIConstantCodes.APINAME)};
		adduploadvideobean.setCategories(categorieArray);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
			 
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse_mediacontributor = userservices.doLogin(apiutils.userJson(IUsersList.VideoSearchmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediacontributor.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse_mediacontributor);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_mediacontributor.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
 		loginapiresponse_mediacontributor.put(IAPIConstantCodes.SEARCHCATEGORIES, createcategoryapirespone.get(IAPIConstantCodes.CATEGORYTD));
		loginapiresponse_mediacontributor.put(IAPIConstantCodes.STATUS, IAPIConstantCodes.ACTIVE);
		loginapiresponse_mediacontributor.put(IAPIConstantCodes.COUNT, "1");
		
		//queryParams path.. :::: {Status=[Active], count=[1], categories=[XXX]}
		VideoSearchAPI searchvideoAPI=new VideoSearchAPI();
	    HashMap<String, String> searchvideoapirespone = searchvideoAPI.searchVideo(loginapiresponse_mediacontributor);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	 
		assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IVideoAccessControlService.videoTitle),adduploadvideobean.getTitle());
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IVideoAccessControlService.videoCount), "1");
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
	    launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		adduploadvideobean.setTitle(searchvideoapirespone.get(IVideoAccessControlService.videoTitle));
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
		commentspage.click_settingsLink();
		commentspage.click_deleteButton();
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify Search Content to specific video using Search Content API with MediaViewer",groups = {SEARCHVIDEOAPI})
	public void TC_09_GET_SearchVideo_api_check_SearchVideo_with_MediaViewer_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Create Category
		CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
						
		//Upload Video
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		String[] categorieArray={(String) categroiesjson.get(IAPIConstantCodes.APINAME)};
		adduploadvideobean.setCategories(categorieArray);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
			 
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse_mediaviewer = userservices.doLogin(apiutils.userJson(IUsersList.VideoSearchmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediaviewer.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse_mediaviewer);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_mediaviewer.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
 		loginapiresponse_mediaviewer.put(IAPIConstantCodes.SEARCHCATEGORIES, createcategoryapirespone.get(IAPIConstantCodes.CATEGORYTD));
		loginapiresponse_mediaviewer.put(IAPIConstantCodes.STATUS, IAPIConstantCodes.ACTIVE);
		loginapiresponse_mediaviewer.put(IAPIConstantCodes.COUNT, "1");
		
		//queryParams path.. :::: {Status=[Active], count=[1], categories=[XXX]}		
		VideoSearchAPI searchvideoAPI=new VideoSearchAPI();
	    HashMap<String, String> searchvideoapirespone = searchvideoAPI.searchVideo(loginapiresponse_mediaviewer);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	 
		assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IVideoAccessControlService.videoTitle),adduploadvideobean.getTitle());
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IVideoAccessControlService.videoCount), "1");
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
	    launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		adduploadvideobean.setTitle(searchvideoapirespone.get(IVideoAccessControlService.videoTitle));
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
		commentspage.click_settingsLink();
		commentspage.click_deleteButton();
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify Search Content to specific video using Search Content API with accountadmin",groups = {SEARCHVIDEOAPI})
	public void TC_10_GET_SearchVideo_api_check_UploaderSort_with_ASC_Positive() throws InterruptedException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Upload Video
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	    
		AddUploadVideoBean adduploadvideobean1 = new AddUploadVideoBean();
		UserServices userservices_mediaadmin = new UserServices();		
		loginapiresponse_mediaadmin = userservices_mediaadmin.doLogin(apiutils.userJson(IUsersList.VideoSearchmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediaadmin.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse_mediaadmin);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_mediaadmin.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Upload Video
		UploadVideoService uploadvidoeservice_mediaadmin = new UploadVideoService();
		ApiBeanFactory apibeanfactory1=new  ApiBeanFactory();
		apibeanfactory1.UploadVideoBean(adduploadvideobean1);
		
		adduploadvideobean1.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean1.setUploader(IUsersList.VideoSearch);
		adduploadvideobean1.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean1.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean1.setEnableRatings(IAPIConstantCodes.TRUE);
		
		loginapiresponse_mediaadmin.put("Mandatory", "No");
		loginapiresponse_mediaadmin.put("fileName", "No");
		
        uploadvidoeapiresponse_mediaadmin = uploadvidoeservice_mediaadmin.uploadVideos(loginapiresponse_mediaadmin, Setup.NOTIFICATION1PATH,adduploadvideobean1);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse_mediaadmin.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse_mediaadmin.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse_mediaadmin.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
  		loginapiresponse.put("q", "amazon"); 
	 	loginapiresponse.put("sortField", IAPIConstantCodes.uploaderName);	 	
	 	loginapiresponse.put("sortDirection",IAPIConstantCodes.asc) ;
	 	loginapiresponse.put("count","13") ;
	 	
	 	//queryParams path.. :::: {q=[XZZ], sortDirection=[asc], sortField=[uploaderName]}
		VideoSearchAPI searchvideoAPI=new VideoSearchAPI();
	    HashMap<String, String> searchvideoapirespone = searchvideoAPI.searchVideo(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
 
		String fromattedJSON=apiutils.formatingapiresponse(searchvideoapirespone.get("uploadernames"));
		ArrayList<String> myList = new ArrayList<String>(Arrays.asList(fromattedJSON.split(",")));
	   
		
	    launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		adduploadvideobean.setTitle("amazon");
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		avengerdashboardpage.click_UploaderSort();
		ArrayList<String> myList2=avengerdashboardpage.getAllUploaderlist();
		customReport.customizedReport(true, myList2.equals(myList),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify Search Content to specific video using Search Content API with accountadmin",groups = {SEARCHVIDEOAPI})
	public void TC_11_GET_SearchVideo_api_check_UploaderSort_with_Desc_Positive() throws InterruptedException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Upload Video
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	    
		AddUploadVideoBean adduploadvideobean1 = new AddUploadVideoBean();
		UserServices userservices_mediaadmin = new UserServices();
		loginapiresponse_mediaadmin = userservices_mediaadmin.doLogin(apiutils.userJson(IUsersList.VideoSearch), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediaadmin.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse_mediaadmin);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_mediaadmin.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Upload Video
		UploadVideoService uploadvidoeservice_mediaadmin = new UploadVideoService();
		ApiBeanFactory apibeanfactory1=new  ApiBeanFactory();
		apibeanfactory1.UploadVideoBean(adduploadvideobean1);
		
		adduploadvideobean1.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean1.setUploader(IUsersList.VideoSearch);
		adduploadvideobean1.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean1.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean1.setEnableRatings(IAPIConstantCodes.TRUE);

		loginapiresponse_mediaadmin.put("Mandatory", "No");
		loginapiresponse_mediaadmin.put("fileName", "No");
		
        uploadvidoeapiresponse_mediaadmin = uploadvidoeservice_mediaadmin.uploadVideos(loginapiresponse_mediaadmin, Setup.NOTIFICATION3PATH,adduploadvideobean1);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse_mediaadmin.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse_mediaadmin.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse_mediaadmin.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
  		loginapiresponse.put("q", "amazon"); 
	 	loginapiresponse.put("sortField", IAPIConstantCodes.uploaderName);	 	
	 	loginapiresponse.put("sortDirection",IAPIConstantCodes.desc) ;
		loginapiresponse.put("count","13") ;
	 	
	 	//queryParams path.. :::: {q=[amazon], sortDirection=[desc], sortField=[uploaderName]}
		VideoSearchAPI searchvideoAPI=new VideoSearchAPI();
	    HashMap<String, String> searchvideoapirespone = searchvideoAPI.searchVideo(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
 
		String fromattedJSON=apiutils.formatingapiresponse(searchvideoapirespone.get("uploadernames"));
		ArrayList<String> myList = new ArrayList<String>(Arrays.asList(fromattedJSON.split(",")));
	    
	    launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		adduploadvideobean.setTitle("amazon");
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		avengerdashboardpage.click_UploaderSort();
		avengerdashboardpage.click_UploaderSort();
		ArrayList<String> myList2=avengerdashboardpage.getAllUploaderlist();
		customReport.customizedReport(true, myList2.equals(myList),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify Search Content to specific video using Search Content API with accountadmin",groups = {SEARCHVIDEOAPI})
	public void TC_12_GET_SearchVideo_api_check_SearchVideo_with_TimeSortWithasc_AccoutAdmin_Positive() throws InterruptedException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
        apibeanfactory.UploadVideoBean(adduploadvideobean);
        adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
        adduploadvideobean.setUploader(sUserName);
        adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
        adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
        adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
        
        loginapiresponse.put("Mandatory", "No");
        loginapiresponse.put("fileName", "No");
        
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
        logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
        assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
        loginapiresponse.put("q", "amazon"); 
        loginapiresponse.put("sortField", "duration");	 	
	 	loginapiresponse.put("sortDirection",IAPIConstantCodes.asc);
	 	
	 	//queryParams path.. :::: {q=[amazon], sortDirection=[asc], sortField=[duration]}
		VideoSearchAPI searchvideoAPI=new VideoSearchAPI();
	    HashMap<String, String> searchvideoapirespone = searchvideoAPI.searchVideo(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
 
		//String fromattedJSON=apiutils.formatingapiresponse(searchvideoapirespone.get("uploadernames"));
		ArrayList<String> myList = new ArrayList<String>(Arrays.asList((searchvideoapirespone.get("TIMES").split(","))));
	    
	    launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		adduploadvideobean.setTitle("amazon");
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		avengerdashboardpage.click_TimeSort();
		ArrayList<String> myList2=avengerdashboardpage.getAllTimelist();
		customReport.customizedReport(true, myList2.containsAll(myList),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify Search Content to specific video using Search Content API with accountadmin",groups = {SEARCHVIDEOAPI})
	public void TC_13_GET_SearchVideo_api_check_SearchVideo_with_TimeSortWithdesc_AccoutAdmin_Positive() throws InterruptedException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
        apibeanfactory.UploadVideoBean(adduploadvideobean);
        adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
        adduploadvideobean.setUploader(sUserName);
        adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);	
        adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
        adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
        
        loginapiresponse.put("Mandatory", "No");
        loginapiresponse.put("fileName", "No");
        
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
        logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
        assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		
		
		loginapiresponse.put("q", "amazon"); 
	 	loginapiresponse.put("sortField", "duration");	 	
	 	loginapiresponse.put("sortDirection",IAPIConstantCodes.desc);
	 	
	 	//queryParams path.. :::: {q=[amazon], sortDirection=[desc], sortField=[duration]}
		VideoSearchAPI searchvideoAPI=new VideoSearchAPI();
	    HashMap<String, String> searchvideoapirespone = searchvideoAPI.searchVideo(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		ArrayList<String> myList = new ArrayList<String>(Arrays.asList((searchvideoapirespone.get("TIMES").split(","))));
	    
	    launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		adduploadvideobean.setTitle("amazon");
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		avengerdashboardpage.click_TimeSort();
		avengerdashboardpage.click_TimeSort();
		ArrayList<String> myList2=avengerdashboardpage.getAllTimelist();
		customReport.customizedReport(true, myList.containsAll(myList),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	
	@Test(description = "To verify Search Content to specific video using Search Content API with accountadmin",groups = {SEARCHVIDEOAPI})
	public void TC_14_GET_SearchVideo_api_check_SearchVideo_with_VideoSortWithAsc_AccoutAdmin_Positives() throws InterruptedException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
   
		/*launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerMediapage=avengerdashboardpage.clickMediaTab();
		avengerVideosPage=avengerMediapage.click_AllVideos();
		avengervideobulkeditpage=avengerVideosPage.clickBulkEditoption();
		avengervideobulkeditpage.checkselectalloption();
		avengervideobulkeditpage.clickBulkEditDeleteOption();
		avengervideobulkeditpage.clickVideoSettingsdailogOKbutton();
		avengervideobulkeditpage.clickVideoSettingsdailogOKbutton();
		Thread.sleep(10000);
		driver.navigate().refresh();
		homePage.verify_userName(sUserName);
		homePage.click_logout();*/
		
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
        apibeanfactory.UploadVideoBean(adduploadvideobean);
        adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
        adduploadvideobean.setUploader(sUserName);
        adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
        adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
        adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
        
        loginapiresponse.put("Mandatory", "No");
        loginapiresponse.put("fileName", "No");
        
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
          logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
        assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		
		loginapiresponse.put("q", "amazon"); 
	 	loginapiresponse.put("sortField", "title");	 	
	 	loginapiresponse.put("sortDirection",IAPIConstantCodes.asc);
	 	loginapiresponse.put("count", "13");
	 	//queryParams path.. :::: {q=[amazon], sortDirection=[asc], sortField=[title]}
		VideoSearchAPI searchvideoAPI=new VideoSearchAPI();
	    HashMap<String, String> searchvideoapirespone = searchvideoAPI.searchVideo(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

		String fromattedJSON=apiutils.formatingapiresponse(searchvideoapirespone.get("title"));
		ArrayList<String> myList = new ArrayList<String>(Arrays.asList((fromattedJSON.split(","))));
		
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage1 = homePage.avengerDashboardPage();
		adduploadvideobean.setTitle("amazon somes");
		avengerdashboardpage1.searchMedia(adduploadvideobean.getTitle());
		avengerdashboardpage1.click_TitleSort();
		ArrayList<String> myList2=avengerdashboardpage1.getAllVideolist();		
		customReport.customizedReport(true, myList2.equals(myList),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		 
	}
	
	@Test(description = "To verify Search Content to specific video using Search Content API with accountadmin",groups = {SEARCHVIDEOAPI})
	public void TC_15_GET_SearchVideo_api_check_SearchVideo_with_VideoSortWithDesc_AccoutAdmin_Positive() throws InterruptedException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
        apibeanfactory.UploadVideoBean(adduploadvideobean);
        adduploadvideobean.setTitle("amazon "+RandomStringUtils.randomNumeric(3));
        adduploadvideobean.setUploader(sUserName);
        adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
        adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
        adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
        
        loginapiresponse.put("Mandatory", "No");
        loginapiresponse.put("fileName", "No");
        
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
        logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
        assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		loginapiresponse.put("q", "amazon"); 
	 	loginapiresponse.put("sortField", "title");	 	
	 	loginapiresponse.put("sortDirection",IAPIConstantCodes.desc);
	 	loginapiresponse.put("count", "13");
	 	
	 	//queryParams path.. :::: {q=[amazon], sortDirection=[desc], sortField=[title]}
		VideoSearchAPI searchvideoAPI=new VideoSearchAPI();
	    HashMap<String, String> searchvideoapirespone = searchvideoAPI.searchVideo(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
 
		String fromattedJSON=apiutils.formatingapiresponse(searchvideoapirespone.get("title"));
		ArrayList<String> myList = new ArrayList<String>(Arrays.asList((fromattedJSON.split(","))));
	    
	    launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		adduploadvideobean.setTitle("amazon");
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		avengerdashboardpage.click_TitleSort();
		avengerdashboardpage.click_TitleSort();
		ArrayList<String> myList2=avengerdashboardpage.getAllVideolist();
		customReport.customizedReport(true, myList2.equals(myList),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify Search Content to specific video using Search Content API with accountadmin",groups = {SEARCHVIDEOAPI})
	public void TC_16_GET_SearchVideo_api_check_SearchVideo_with_UploadedDateWithAsc_AccoutAdmin_Positive() throws InterruptedException, ParseException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Upload Video
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		adduploadvideobean.setTitle("amazon somes");
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		String randomtitle1=RandomStringUtils.randomAlphanumeric(6);
		loginapiresponse.put("q", randomtitle1); 
	 	loginapiresponse.put("sortField", "whenUploaded");	 	
	 	loginapiresponse.put("sortDirection",IAPIConstantCodes.asc);
	 	
	 	//queryParams path.. :::: {q=[amazon], sortDirection=[asc], sortField=[whenUploaded]}	 	
		VideoSearchAPI searchvideoAPI=new VideoSearchAPI();
	    HashMap<String, ArrayList<String>> searchvideoapirespone = searchvideoAPI.searchVideo_UploadedDate(loginapiresponse);
	    ArrayList<String> apiresponse= searchvideoapirespone.get("response");
	    assertionapiresponse.verifyAssert_httpCode(apiresponse.get(0)+apiresponse.get(1), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, apiresponse.get(0)+apiresponse.get(1),statusValue, driver, sTestcaseName);
 	    ArrayList<String> myList = new ArrayList<String>(searchvideoapirespone.get("whenUploaded"));
 	    
 	    launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		adduploadvideobean.setTitle("amazon");//////////
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		avengerdashboardpage.click_UploadedDateSort();
		//avengerdashboardpage.click_UploadedDateSort();
		ArrayList<String> myList2=avengerdashboardpage.getAllUploadedDatelist();
		customReport.customizedReport(true,myList2.containsAll(myList),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify Search Content to specific video using Search Content API with accountadmin",groups = {SEARCHVIDEOAPI})
	public void TC_17_GET_SearchVideo_api_check_SearchVideo_with_UploadedDateWithDesc_AccoutAdmin_Positive() throws InterruptedException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Upload Video
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		adduploadvideobean.setTitle("amazon somes");

		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//String randomtitle1=RandomStringUtils.randomAlphanumeric(6);
		loginapiresponse.put("q", "amazon somes"); 
	    loginapiresponse.put("sortField", "whenUploaded");	 	
	    loginapiresponse.put("sortDirection",IAPIConstantCodes.desc);
	 	
	 	//queryParams path.. :::: {q=[amazon], sortDirection=[asc], sortField=[whenUploaded]}	 	
		VideoSearchAPI searchvideoAPI=new VideoSearchAPI();
	    HashMap<String, ArrayList<String>> searchvideoapirespone = searchvideoAPI.searchVideo_UploadedDate(loginapiresponse);
	    ArrayList<String> apiresponse= searchvideoapirespone.get("response");
	    assertionapiresponse.verifyAssert_httpCode(apiresponse.get(0)+apiresponse.get(1), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, apiresponse.get(0)+apiresponse.get(1),statusValue, driver, sTestcaseName);
 	    ArrayList<String> myList = new ArrayList<String>(searchvideoapirespone.get("whenUploaded"));
 	    
 	    launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		adduploadvideobean.setTitle("amazon");
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		avengerdashboardpage.click_UploadedDateSort();
		ArrayList<String> myList2=avengerdashboardpage.getAllUploadedDatelist();
		customReport.customizedReport(true,myList2.containsAll(myList),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify Search Content to specific video using Search Content API with accountadmin with uploader search",groups = {SEARCHVIDEOAPI})
	public void TC_18_GET_SearchVideo_api_check_SearchVideo_with_AccoutAdmin_UploaderSearch_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Upload Video
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.VideoSearch);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.FALSE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		String tag1=RandomStringUtils.randomAlphanumeric(6) + ' ' + RandomStringUtils.randomAlphanumeric(6) ;
		String tags[] = {tag1};
		adduploadvideobean.setTags(tags);

	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		loginapiresponse.put(IAPIConstantCodes.STATUS, IAPIConstantCodes.INACTIVE);
		loginapiresponse.put("q",tag1); 
	 	loginapiresponse.put("Uploaders", IUsersList.VideoSearch);	 	

	 	//queryParams path.. :::: {Status=[Active], q=[XXXX], count=[1], categories=[XXXX], Uploaders=[XXXX]}
		VideoSearchAPI searchvideoAPI=new VideoSearchAPI();
	    HashMap<String, String> searchvideoapirespone = searchvideoAPI.searchVideo(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
 
		assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IVideoAccessControlService.videoTitle),adduploadvideobean.getTitle());
		customReport.customizedReport(adduploadvideobean.getTitle(), searchvideoapirespone.get(IVideoAccessControlService.videoTitle),statusValue, driver, sTestcaseName);
		assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IVideoAccessControlService.videoCount), "1");
		customReport.customizedReport("1", searchvideoapirespone.get(IVideoAccessControlService.videoCount),statusValue, driver, sTestcaseName);
		
	    launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		adduploadvideobean.setTitle(searchvideoapirespone.get(IVideoAccessControlService.videoTitle));
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
		commentspage.click_settingsLink();
		commentspage.click_deleteButton();
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify Search video using Search Video API with MC and checking for default unlisted setting which is only fetching listed videos and the videos for which the user has edit access",groups = {SEARCHVIDEOAPI})
	public void TC_19_GET_SearchVideo_api_check_SearchVideo_with_UnlistedVideo_MediaCOntributor_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.VideoSearchmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);		
		
		//Upload Video
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.VideoSearchmediacontributor);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		adduploadvideobean.setUnlisted(IAPIConstantCodes.TRUE);
		String randomtitle=RandomStringUtils.randomAlphanumeric(8);
		adduploadvideobean.setTitle(randomtitle);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse.put(IAPIConstantCodes.STATUS, IAPIConstantCodes.ACTIVE);
		loginapiresponse.put("q", randomtitle); 

	 	//queryParams path.. :::: {Status=[Active], q=[Unlist]}
		VideoSearchAPI searchvideoAPI=new VideoSearchAPI();
	    HashMap<String, String> searchvideoapirespone = searchvideoAPI.searchVideo(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
 		
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IVideoAccessControlService.videoTitle),adduploadvideobean.getTitle());
		customReport.customizedReport(adduploadvideobean.getTitle(), searchvideoapirespone.get(IVideoAccessControlService.videoTitle),statusValue, driver, sTestcaseName);
		assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IVideoAccessControlService.videoCount), "1");
		customReport.customizedReport("1", searchvideoapirespone.get(IVideoAccessControlService.videoCount),statusValue, driver, sTestcaseName);
		
	    launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		adduploadvideobean.setTitle(searchvideoapirespone.get(IVideoAccessControlService.videoTitle));
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
		commentspage.click_settingsLink();
		commentspage.click_deleteButton();
		customReport.checkinglist(statusValue);

	}
	
	@Test(description = "To verify Search video using Search Video API with Media Viewer and checking for default unlisted setting which is only fetching listed videos and the videos for which the user has edit access",groups = {SEARCHVIDEOAPI})
	public void TC_20_GET_SearchVideo_api_check_SearchVideo_with_MediaViewer_Negative() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.VideoSearchmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		//Upload Video
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.VideoSearchmediacontributor);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		adduploadvideobean.setUnlisted(IAPIConstantCodes.TRUE);
		String randomtitle=RandomStringUtils.randomAlphanumeric(8);
		adduploadvideobean.setTitle(randomtitle);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		String randomtitle1=RandomStringUtils.randomAlphanumeric(8);
		adduploadvideobean.setTitle(randomtitle1);
		adduploadvideobean.setUnlisted(IAPIConstantCodes.FALSE);
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
	  	logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
		loginapiresponse_mediaviewer = userservices.doLogin(apiutils.userJson(IUsersList.VideoSearchmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediaviewer.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse_mediaviewer);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_mediaviewer.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse_mediaviewer.put(IAPIConstantCodes.STATUS, IAPIConstantCodes.ACTIVE);
		loginapiresponse_mediaviewer.put("q", randomtitle1); 

	 	//queryParams path.. :::: {Status=[Active], q=[Unlist]}
		VideoSearchAPI searchvideoAPI=new VideoSearchAPI();
	    HashMap<String, String> searchvideoapirespone = searchvideoAPI.searchVideo(loginapiresponse_mediaviewer);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);		
	    customReport.customizedReport("1", searchvideoapirespone.get(IVideoAccessControlService.videoCount),statusValue, driver, sTestcaseName);
	    customReport.customizedReport(adduploadvideobean.getTitle(), searchvideoapirespone.get(IVideoAccessControlService.videoTitle),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);

	}
	
	@Test(description = "To verify Search Content to specific video using Search Content API with media admin with search field as title and without exact match",groups = {SEARCHVIDEOAPI})
	public void TC_21_GET_SearchVideo_api_check_SearchVideo_with_Mediaadmin_SearchFiled_Title_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.VideoSearchmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Upload Video
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		String randomtitle=RandomStringUtils.randomAlphanumeric(8);
		String randomtitle1=RandomStringUtils.randomAlphanumeric(8);
		adduploadvideobean.setTitle(randomtitle + ' ' +randomtitle1);
		String tag1=RandomStringUtils.randomAlphanumeric(8);
		String tags[] = {tag1};
		adduploadvideobean.setTags(tags);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		 
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
 		loginapiresponse.put("q", randomtitle1);
		loginapiresponse.put(IAPIConstantCodes.STATUS, IAPIConstantCodes.ACTIVE);
	 	loginapiresponse.put(IAPIConstantCodes.SearchField, "title");
	 	
	 	//queryParams path.. :::: {Status=[Active], q=[Match], searchField=[title]}
		
		VideoSearchAPI searchvideoAPI=new VideoSearchAPI();
	    HashMap<String, String> searchvideoapirespone = searchvideoAPI.searchVideo(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);	
	    customReport.customizedReport(true, searchvideoapirespone.get(IVideoAccessControlService.videoTitle).contains(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
	    launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		adduploadvideobean.setTitle(searchvideoapirespone.get(IVideoAccessControlService.videoTitle));
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
		commentspage.click_settingsLink();
		commentspage.click_deleteButton();
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
