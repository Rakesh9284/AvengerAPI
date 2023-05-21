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
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUserVideoWatchingStatusService;
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

public class OwnerFieldInSearchApi extends TestBase {

	private static Logger logger = Logger.getLogger(OwnerFieldInSearchApi.class);
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
 	private HashMap<String, String> loginapiresponse1;
	private HashMap<String, String> uploadvidoeapiresponse;
	private HashMap<String, String> uploadvidoeapiresponse1;
	private HashMap<String,String> loginapiresponse_mediaviewer;
	private HashMap<String,String> loginapiresponse_eventadmin;
	private HashMap<String,String> loginapiresponse_mediacontributor;
	private HashMap<String,String> loginapiresponse_mediaadmin;
    private AssertionAPIResponse assertionapiresponse;
    private HashMap<String, String> createcategoryapirespone;
    private HashMap<String, String> childcategoryapirespone;
    private HashMap<String, String> uploadvidoeapiresponse_mediaadmin;
	private AddCategoryBean addcategorybean;
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

	@Test(description = "To verify Search Content to specific video using Search Content API with accountadmin",groups = {OWNERFIELDINSEARCHAPI})
	public void TC_01_GET_SearchVideo_OwnerField_check_SearchVideo_with_AccoutAdmin_Positive() {

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
		loginapiresponse.put("q", adduploadvideobean.getTitle()); 
	 	loginapiresponse.put("Uploaders", sUserName);
	 	loginapiresponse.put("UploaderIds",loginapiresponse.get("userId")) ;

	 	//queryParams path.. :::: {Status=[Active], q=[XXXX], count=[1], categories=[XXXX], UploaderIds=[XXXX]}
		VideoSearchAPI searchvideoAPI=new VideoSearchAPI();
	    HashMap<String, String> searchvideoapirespone = searchvideoAPI.searchVideo_OwnerField(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
 
		assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IVideoAccessControlService.videoTitle),adduploadvideobean.getTitle());
		customReport.customizedReport(adduploadvideobean.getTitle(), searchvideoapirespone.get(IVideoAccessControlService.videoTitle),statusValue, driver, sTestcaseName);
		assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IVideoAccessControlService.videoCount), "1");
		customReport.customizedReport("1", searchvideoapirespone.get(IVideoAccessControlService.videoCount),statusValue, driver, sTestcaseName);
		
		assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IVideoAccessControlService.videoOwnerId), loginapiresponse.get("userId"));
		customReport.customizedReport(loginapiresponse.get("userId"), searchvideoapirespone.get(IVideoAccessControlService.videoOwnerId),statusValue, driver, sTestcaseName);
		
		assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IVideoAccessControlService.videoOwnerName), "apiaau61");
		customReport.customizedReport("apiaau61", searchvideoapirespone.get(IVideoAccessControlService.videoOwnerName),statusValue, driver, sTestcaseName);
		
		assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IVideoAccessControlService.videoOwnerFullName), "apiaau61 apiaau61");
		customReport.customizedReport("apiaau61 apiaau61", searchvideoapirespone.get(IVideoAccessControlService.videoOwnerFullName),statusValue, driver, sTestcaseName);		
		
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
	
	@Test(description = "To verify Search Content to specific video using Multiple_Username Search Content API with accountadmin",groups = {OWNERFIELDINSEARCHAPI})
	public void TC_02_GET_SearchVideo_OwnerField_check_SearchVideo_Multiple_Username_with_AccoutAdmin_Positive() {

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
		loginapiresponse.put("q", adduploadvideobean.getTitle()); 

	 	//queryParams path.. :::: {Status=[Active], q=[XXXX], count=[1], categories=[XXXX], UploaderIds=[XXXX]}
		VideoSearchAPI searchvideoAPI=new VideoSearchAPI();
		
	    HashMap<String, String> searchvideoapirespone = searchvideoAPI.searchVideo_OwnerField(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

	    UserServices userservices1 = new UserServices();
		loginapiresponse1 = userservices1.doLogin(apiutils.userJson(IUsersList.UploadVideosmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Upload Video
		UploadVideoService uploadvidoeservice1 = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.UploadVideosmediacontributor);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
	    loginapiresponse1.put("Mandatory", "No");
		loginapiresponse1.put("fileName", "No");
        uploadvidoeapiresponse1 = uploadvidoeservice1.uploadVideos(loginapiresponse1, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		loginapiresponse1.put(IAPIConstantCodes.STATUS, IAPIConstantCodes.ACTIVE);
		loginapiresponse1.put(IAPIConstantCodes.COUNT, "1");
		loginapiresponse1.put("q", adduploadvideobean.getTitle()); 
	 	loginapiresponse1.put("Uploaders", sUserName);
	 	loginapiresponse1.put("UploaderIds",loginapiresponse1.get("userId")) ;

	 	//queryParams path.. :::: {Status=[Active], q=[XXXX], count=[1], categories=[XXXX], UploaderIds=[XXXX]}
		VideoSearchAPI searchvideoAPI1=new VideoSearchAPI();
		
	    HashMap<String, String> searchvideoapirespone1 = searchvideoAPI1.searchVideo_OwnerField(loginapiresponse1);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone1.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone1.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchvideoapirespone1.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone1.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    searchvideoapirespone.put(IAPIConstantCodes.APIResponseOWNERNAME2, searchvideoapirespone1.get("ownerName"));
	    searchvideoapirespone.put(IAPIConstantCodes.APIRequestAccessToken, loginapiresponse1.get("accesstoken"));
	    
	    HashMap<String, String> searchvideoapiresponse = searchvideoAPI.VideoSearchAPIwith_UserName_AsParameters(searchvideoapirespone);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchvideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify Search Content to specific video using Search Content API with accountadmin",groups = {OWNERFIELDINSEARCHAPI})
	public void TC_03_GET_SearchVideo_OwnerField_check_SearchVideo_Multiple_UserIds_with_AccoutAdmin_Positive() {

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
		loginapiresponse.put("q", adduploadvideobean.getTitle()); 

	 	//queryParams path.. :::: {Status=[Active], q=[XXXX], count=[1], categories=[XXXX], UploaderIds=[XXXX]}
		VideoSearchAPI searchvideoAPI=new VideoSearchAPI();
		
	    HashMap<String, String> searchvideoapirespone = searchvideoAPI.searchVideo_OwnerField(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

	    UserServices userservices1 = new UserServices();
		loginapiresponse1 = userservices1.doLogin(apiutils.userJson(IUsersList.UploadVideosmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Upload Video
		UploadVideoService uploadvidoeservice1 = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.UploadVideosmediacontributor);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
	    loginapiresponse1.put("Mandatory", "No");
		loginapiresponse1.put("fileName", "No");
        uploadvidoeapiresponse1 = uploadvidoeservice1.uploadVideos(loginapiresponse1, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		loginapiresponse1.put(IAPIConstantCodes.STATUS, IAPIConstantCodes.ACTIVE);
		loginapiresponse1.put(IAPIConstantCodes.COUNT, "1");
		loginapiresponse1.put("q", adduploadvideobean.getTitle()); 
	 	loginapiresponse1.put("Uploaders", sUserName);
	 	loginapiresponse1.put("UploaderIds",loginapiresponse1.get("userId")) ;

	 	//queryParams path.. :::: {Status=[Active], q=[XXXX], count=[1], categories=[XXXX], UploaderIds=[XXXX]}
		VideoSearchAPI searchvideoAPI1=new VideoSearchAPI();
		
	    HashMap<String, String> searchvideoapirespone1 = searchvideoAPI1.searchVideo_OwnerField(loginapiresponse1);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone1.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone1.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchvideoapirespone1.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapirespone1.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    searchvideoapirespone.put(IAPIConstantCodes.APIResponseOWNERID2, searchvideoapirespone1.get("ownerId"));
	    searchvideoapirespone.put(IAPIConstantCodes.APIRequestAccessToken, loginapiresponse1.get("accesstoken"));
	    
	    HashMap<String, String> searchvideoapiresponse = searchvideoAPI.VideoSearchAPIwith_UserId_AsParameters(searchvideoapirespone);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchvideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify Search Content to specific video using Search Content API with accountadmin",groups = {OWNERFIELDINSEARCHAPI})
	public void TC_04_GET_SearchVideo_OwnerField_check_SearchVideo_INVALID_Username_with_AccoutAdmin_Negative() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		VideoSearchAPI searchvideoAPI=new VideoSearchAPI();
	    HashMap<String, String> searchvideoapirespone = searchvideoAPI.searchVideo_OwnerField(loginapiresponse);
	    searchvideoapirespone.put(IAPIConstantCodes.APIRequestAccessToken, loginapiresponse.get("accesstoken"));
	    
	    HashMap<String, String> searchvideoapiresponse = searchvideoAPI.VideoSearchAPIwith_InvalidUserName_AsParameters(searchvideoapirespone);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchvideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify Search Content to specific video using Search Content API with accountadmin",groups = {OWNERFIELDINSEARCHAPI})
	public void TC_05_GET_SearchVideo_OwnerField_check_SearchVideo_INVALID_UserId_with_AccoutAdmin_Negative() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		VideoSearchAPI searchvideoAPI=new VideoSearchAPI();
	    HashMap<String, String> searchvideoapirespone = searchvideoAPI.searchVideo_OwnerField(loginapiresponse);
	    searchvideoapirespone.put(IAPIConstantCodes.APIRequestAccessToken, loginapiresponse.get("accesstoken"));
	    
	    HashMap<String, String> searchvideoapiresponse = searchvideoAPI.VideoSearchAPIwith_InvalidUserId_AsParameters(searchvideoapirespone);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, searchvideoapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+searchvideoapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	    customReport.customizedReport("[]", searchvideoapiresponse.get(IAPIConstantCodes.APIResponseVIDEOS),statusValue, driver, sTestcaseName);
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
}