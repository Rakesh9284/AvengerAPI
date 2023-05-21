package com.vbrick.avenger.mobileappsupport;
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
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.service.IVideoAccessControlService;
import com.vbricks.avenger.serviceimpl.CreateCategoryAPI;
import com.vbricks.avenger.serviceimpl.DeleteVideosAPI;
import com.vbricks.avenger.serviceimpl.GetCategoryListAPI;
import com.vbricks.avenger.serviceimpl.SearchSuggestionsAPI;
import com.vbricks.avenger.serviceimpl.VideoSearchAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.serviceimpl.VideoListAPI;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class SearchSuggestionsAPI_AV_27524 extends TestBase {

	private static Logger logger = Logger.getLogger(SearchSuggestionsAPI_AV_27524.class);
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
    private HashMap<String, String> getsuggestionsapirespone;
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

	    @Test(description = "To verify Search Suggestions using Search Suggestions API with accountadmin",groups = {SEARCHSUGGESTIONSAPI})
	    public void TC_01_POST_SearchSuggestions_api() throws InterruptedException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Upload Video (1)
		for (int i=1;i<=10;i++) {
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
		}
		loginapiresponse.put("q", "ama"); 
	
		SearchSuggestionsAPI searchsuggestionsapiresponse = new SearchSuggestionsAPI();
		HashMap<String, String> SearchSuggestionsAPI = searchsuggestionsapiresponse.SearchSuggestionsAPI(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(SearchSuggestionsAPI.get(IAPIConstantCodes.APIResponseHttpCode)+SearchSuggestionsAPI.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, SearchSuggestionsAPI.get(IAPIConstantCodes.APIResponseHttpCode)+SearchSuggestionsAPI.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		String fromattedJSON=apiutils.formatingapiresponse(SearchSuggestionsAPI.get("apivideolist"));
		ArrayList<String> apisuggestionList = new ArrayList<String>(Arrays.asList(fromattedJSON.split(",")));
		
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchSuggestions("ama");
		ArrayList<String> uiSuggestionList = avengerdashboardpage.get_allassignedgroups();
		customReport.customizedReport(true,apiutils.containsAll(apisuggestionList,uiSuggestionList), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	   
	    }
	    
	    @Test(description = "To verify if Search Suggestions being Case Sensitive (AV-28054) with accountadmin",groups = {SEARCHSUGGESTIONSAPI})
	    public void TC_02_POST_SearchSuggestions_api_Verifying_with_UpperCase() throws InterruptedException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Upload Video (1)
		for (int i=1;i<=10;i++) {
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("casesensitive "+RandomStringUtils.randomNumeric(3));
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
		}
		
		loginapiresponse.put("q", "CAS"); 
		SearchSuggestionsAPI searchsuggestionsapiresponse = new SearchSuggestionsAPI();
		HashMap<String, String> SearchSuggestionsAPI = searchsuggestionsapiresponse.SearchSuggestionsAPI(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(SearchSuggestionsAPI.get(IAPIConstantCodes.APIResponseHttpCode)+SearchSuggestionsAPI.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, SearchSuggestionsAPI.get(IAPIConstantCodes.APIResponseHttpCode)+SearchSuggestionsAPI.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	  
		String fromattedJSON=apiutils.formatingapiresponse(SearchSuggestionsAPI.get("apivideolist"));
		ArrayList<String> apisuggestionList = new ArrayList<String>(Arrays.asList(fromattedJSON.split(",")));
		
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchSuggestions("CAS");
		ArrayList<String> uiSuggestionList = avengerdashboardpage.get_allassignedgroups();
		customReport.customizedReport(true,apiutils.containsAll(apisuggestionList,uiSuggestionList), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	    
	    }

	    @Test(description = "To verify if Search Suggestions with only 1 character (Invalid request: Please provide 2-15 character input) with accountadmin",groups = {SEARCHSUGGESTIONSAPI})
	    public void TC_03_POST_SearchSuggestions_api_Verifying_with_1_Input_Character() throws InterruptedException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Upload Video (1)
		for (int i=1;i<=10;i++) {
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("onlyoneinputcharacter "+RandomStringUtils.randomNumeric(3));
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
		}
		
		loginapiresponse.put("q", "o"); 
		SearchSuggestionsAPI searchsuggestionsapiresponse = new SearchSuggestionsAPI();
		HashMap<String, String> SearchSuggestionsAPI = searchsuggestionsapiresponse.SearchSuggestionsAPI(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(SearchSuggestionsAPI.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus400);
		customReport.customizedReport(HttpStatusCode.httpsStatus400, SearchSuggestionsAPI.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	    
	    }

	    @Test(description = "To verify if Search Suggestions with more than 15 character (Invalid request: Please provide 2-15 character input) with accountadmin",groups = {SEARCHSUGGESTIONSAPI})
	    public void TC_04_POST_SearchSuggestions_api_Verifying_with_more_than_15_Input_Character() throws InterruptedException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Upload Video (1)
		for (int i=1;i<=10;i++) {
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("morethan15inputcharacters "+RandomStringUtils.randomNumeric(3));
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
		}
		
		loginapiresponse.put("q", "morethan15inputc"); 	
		SearchSuggestionsAPI searchsuggestionsapiresponse = new SearchSuggestionsAPI();
		HashMap<String, String> SearchSuggestionsAPI = searchsuggestionsapiresponse.SearchSuggestionsAPI(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(SearchSuggestionsAPI.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus400);
		customReport.customizedReport(HttpStatusCode.httpsStatus400, SearchSuggestionsAPI.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	   
	    }

	    
	    @Test(description = "To verify if Search Suggestions when there are no videos with the respective input string with accountadmin",groups = {SEARCHSUGGESTIONSAPI})
	    public void TC_05_POST_SearchSuggestions_api_Verifying_when_No_Such_Results() throws InterruptedException {

	    	logger.info("API Level Code is excuting");
			sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
			UserServices userservices = new UserServices();
			loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
			logger.info("Login API response Code :::" + loginapiresponse);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
	    	loginapiresponse.put("q", "zqrtrwerewr"); 
	    	SearchSuggestionsAPI searchsuggestionsapiresponse = new SearchSuggestionsAPI();
			HashMap<String, String> SearchSuggestionsAPI = searchsuggestionsapiresponse.SearchSuggestionsAPI(loginapiresponse);
			assertionapiresponse.verifyAssert_httpCode(SearchSuggestionsAPI.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, SearchSuggestionsAPI.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		   
	    }
	    
	    @Test(description = "To verify if Search Suggestions when user not authenticated with accountadmin",groups = {SEARCHSUGGESTIONSAPI})
	    public void TC_06_POST_SearchSuggestions_api_Verifying_when_User_Not_Authenticated() throws InterruptedException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Upload Video (1)
		for (int i=1;i<=10;i++) {
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle("notauthenticated "+RandomStringUtils.randomNumeric(3));
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
        uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		}
		
		loginapiresponse.put("q", "notauthen"); 
		SearchSuggestionsAPI searchsuggestionsapiresponse = new SearchSuggestionsAPI();
		HashMap<String, String> SearchSuggestionsAPI = searchsuggestionsapiresponse.SearchSuggestionsAPIwithoutauthorization(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(SearchSuggestionsAPI.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, SearchSuggestionsAPI.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		String fromattedJSON=apiutils.formatingapiresponse(SearchSuggestionsAPI.get("apivideolist"));
		ArrayList<String> apisuggestionList = new ArrayList<String>(Arrays.asList(fromattedJSON.split(",")));
		int apilist=apisuggestionList.size()-1;
		
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(IUsersList.SearchSuggestions, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchSuggestions("notauthen");
		ArrayList<String> uiSuggestionList = avengerdashboardpage.get_allassignedgroups();
		 int uilist=uiSuggestionList.size();
		customReport.customizedReport(apilist,uilist, statusValue, driver, sTestcaseName);
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
