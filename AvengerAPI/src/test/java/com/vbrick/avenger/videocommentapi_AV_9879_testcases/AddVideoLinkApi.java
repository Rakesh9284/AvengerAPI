package com.vbrick.avenger.videocommentapi_AV_9879_testcases;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;
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
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbrick.avenger.apibeans.AddVideoCommentBean;
import com.vbrick.avenger.apibeans.AddVideoLinkBean;
import com.vbrick.avenger.apibeans.PUTMigrateVideoBean;
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
import com.vbricks.avenger.service.IAddVideoLinkService;
import com.vbricks.avenger.service.IMegratVideoService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.AddVideoLinkAPI;
import com.vbricks.avenger.serviceimpl.MigratVideoAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class AddVideoLinkApi extends TestBase {

	private static Logger logger = Logger.getLogger(AddVideoLinkApi.class);
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
	private AssertionAPIResponse assertionapiresponse;
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

	/*@Test(description = "To verify legacy view count with accountadmin using Add Video Link Api and migrate legacy View as valid count",groups = {PostAddVideoLink})
	public void  TC_08_Post_AddVideoLink_api_check_Migrate_LegacyView_AsValid_Count_Positive_AV29567() throws Exception {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
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
			
		AddVideoLinkBean addvideolinkbean=new AddVideoLinkBean();		
		addvideolinkbean.setTitle(IAddVideoLinkService.VideoTitle);
		addvideolinkbean.setUploader(sUserName);
		addvideolinkbean.setIsActive(IAPIConstantCodes.TRUE);
		addvideolinkbean.setlinkedurl("Yes");
		String url=url();
		addvideolinkbean.setUrl(url);
		addvideolinkbean.setEncodingType(IAddVideoLinkService.encodingType[4]);
		addvideolinkbean.setType(IAddVideoLinkService.typeVod);
		addvideolinkbean.setIsMulticast(IAPIConstantCodes.TRUE);		
		addvideolinkbean.setLegacyViewCount(apiutils.randomNumericals());
		
		
		AddVideoLinkAPI addvideolinkapi=new AddVideoLinkAPI();
		HashMap<String, String> addvideolinkapiresponse=addvideolinkapi.addVideolink(loginapiresponse, addvideolinkbean);
		assertionapiresponse.verifyAssert_httpCode(addvideolinkapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, addvideolinkapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(addvideolinkbean.getTitle());
		String viewcount_seachvideopage=avengerdashboardpage.getviews_searchvideoPage(addvideolinkbean.getLegacyViewCount());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(addvideolinkbean.getTitle());
		commentspage.clickVideoBasicinformationButton();
        LinkedHashMap<String, String> videimporteddetails=commentspage.getinformationvideodetails();
        customReport.customizedReport(addvideolinkbean.getLegacyViewCount(),viewcount_seachvideopage,statusValue, driver, sTestcaseName);
        customReport.customizedReport(sUserName+" "+sUserName, videimporteddetails.get(IAPIConstantCodes.videoOwnername),statusValue, driver, sTestcaseName);
        customReport.customizedReport(addvideolinkbean.getLegacyViewCount(),videimporteddetails.get(IAPIConstantCodes.videoTotalViews),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);		
	    
	}
	@Test(description = "To verify legacy view count with accountadmin using Add Video Link Api for a live video and migrate legacy View as valid count",groups = {PostAddVideoLink})
	public void TC_0_Post_AddVideoLink_api_check_Migrate_LegacyView_forliveVideo_AsValid_Count_Positive_AV29567() throws Exception {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
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
			
		AddVideoLinkBean addvideolinkbean=new AddVideoLinkBean();		
		addvideolinkbean.setTitle(IAddVideoLinkService.VideoTitle);
		addvideolinkbean.setUploader(sUserName);
		
		addvideolinkbean.setlinkedurl("Yes");
		String url=url();
		addvideolinkbean.setUrl(url);
		addvideolinkbean.setEncodingType(IAddVideoLinkService.encodingType[4]);
		addvideolinkbean.setType(IAddVideoLinkService.typeLive);
		addvideolinkbean.setIsMulticast(IAPIConstantCodes.TRUE);		
		addvideolinkbean.setLegacyViewCount(apiutils.randomNumericals());
		
		
		AddVideoLinkAPI addvideolinkapi=new AddVideoLinkAPI();
		HashMap<String, String> addvideolinkapiresponse=addvideolinkapi.addVideolink(loginapiresponse, addvideolinkbean);
		assertionapiresponse.verifyAssert_httpCode(addvideolinkapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, addvideolinkapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(addvideolinkbean.getTitle());
		String viewcount_seachvideopage=avengerdashboardpage.getviews_searchvideoPage(addvideolinkbean.getLegacyViewCount());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(addvideolinkbean.getTitle());
		commentspage.clickVideoBasicinformationButton();
        LinkedHashMap<String, String> videimporteddetails=commentspage.getinformationvideodetails();
        customReport.customizedReport(addvideolinkbean.getLegacyViewCount(),viewcount_seachvideopage,statusValue, driver, sTestcaseName);
        customReport.customizedReport(sUserName+" "+sUserName, videimporteddetails.get(IAPIConstantCodes.videoOwnername),statusValue, driver, sTestcaseName);
        customReport.customizedReport(addvideolinkbean.getLegacyViewCount(),videimporteddetails.get(IAPIConstantCodes.videoTotalViews),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);		
	    
	}

	@Test(description = "To verify legacy view count with accountadmin using Add Video Link Api and migrate legacy View  as negative count ",groups = {MIGRATEVIDEOAPI})
	public void  TC_09_Post_AddVideoLink_api_check_Migrate_LegacyView_AsNegative_Count_Negative_AV29567()throws Exception {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
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
			
		AddVideoLinkBean addvideolinkbean=new AddVideoLinkBean();		
		addvideolinkbean.setTitle(IAddVideoLinkService.VideoTitle);
		addvideolinkbean.setUploader(sUserName);
		
		addvideolinkbean.setlinkedurl("Yes");
		String url=url();
		addvideolinkbean.setUrl(url);
		addvideolinkbean.setEncodingType(IAddVideoLinkService.encodingType[4]);
		addvideolinkbean.setType(IAddVideoLinkService.typeVod);
		addvideolinkbean.setIsMulticast(IAPIConstantCodes.TRUE);		
		addvideolinkbean.setLegacyViewCount("-"+apiutils.randomNumericals());
		
		
		AddVideoLinkAPI addvideolinkapi=new AddVideoLinkAPI();
		HashMap<String, String> addvideolinkapiresponse=addvideolinkapi.addVideolink(loginapiresponse, addvideolinkbean);
		assertionapiresponse.verifyAssert_httpCode(addvideolinkapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus400);
		customReport.customizedReport(HttpStatusCode.httpsStatus400, addvideolinkapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify legacy view count with accountadmin using Add Video Link Api and migrate legacy View  as empty ",groups = {MIGRATEVIDEOAPI})
	public void  TC_10_Post_AddVideoLink_api_check_Migrate_LegacyView_AsEmpty_Negative_AV29567() throws Exception {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
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
			
		AddVideoLinkBean addvideolinkbean=new AddVideoLinkBean();		
		addvideolinkbean.setTitle(IAddVideoLinkService.VideoTitle);
		addvideolinkbean.setUploader(sUserName);
		
		addvideolinkbean.setlinkedurl("Yes");
		String url=url();
		addvideolinkbean.setUrl(url);
		addvideolinkbean.setEncodingType(IAddVideoLinkService.encodingType[4]);
		addvideolinkbean.setType(IAddVideoLinkService.typeVod);
		addvideolinkbean.setIsMulticast(IAPIConstantCodes.TRUE);		
		addvideolinkbean.setLegacyViewCount("   ");
		
		
		AddVideoLinkAPI addvideolinkapi=new AddVideoLinkAPI();
		HashMap<String, String> addvideolinkapiresponse=addvideolinkapi.addVideolink(loginapiresponse, addvideolinkbean);
		assertionapiresponse.verifyAssert_httpCode(addvideolinkapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus500);
		customReport.customizedReport(HttpStatusCode.httpsStatus500, addvideolinkapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	public String url() throws Exception{
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
	    Thread.sleep(15000);
		String url=homePage.getCurrentURL();
		homePage.click_logout();
		logger.info("################Current URL="+url);
		return url;
		
	}*/
	
	
	
	
		//@AfterMethod(alwaysRun = true)
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