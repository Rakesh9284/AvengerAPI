package com.vbrick.avenger.videocommentapi_AV_9879_testcases;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;
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
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbrick.avenger.apibeans.AddVideoCommentBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IEditVideoRatingService;
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.EditVideoRatingAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class EditVideoRating_AV_9880 extends TestBase {

	private static Logger logger = Logger.getLogger(EditVideoRating_AV_9880.class);
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
	private AddUploadVideoBean adduploadvideobean;
	public ApiBeanFactory apibeanfactory;
	public AddVideoCommentBean addvideocommentbean;
	private HashMap<String, String> loginapiresponse;
	private HashMap<String, String> loginapiresponse_mediaviewer;
	private HashMap<String, String> loginapiresponse_mediaadmin;
	private HashMap<String, String> loginapiresponse_mediacontributor;
	private HashMap<String, String> loginapiresponse_eventadmin;
	private HashMap<String, String> uploadvidoeapiresponse;
	private AssertionAPIResponse assertionapiresponse;
	private ApiUtils apiutils;
	HashMap<String, String> editvideoratingapirespone;

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
		assertionapiresponse = new AssertionAPIResponse();
		apiutils = new ApiUtils();

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
		accountBeansFactory.MailinatorBean(mailinatorbeanpage);

	}

	@Test(description = "Account Admin User giving video Rating 1 on valid video", groups = { EDITVIDEORATINGAPI })
	public void TC_01_PUT_VideoRating_api_check_AccountAdmin_Is_VideoRating_As_1_Positive() {

		logger.info("API Level Code is excuting");
	 	sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200,loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::"+ uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode,uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver, sTestcaseName);
		
		EditVideoRatingAPI videorating = new EditVideoRatingAPI();
		editvideoratingapirespone = videorating.editVideoRating(uploadvidoeapiresponse,videorating.userRating(IEditVideoRatingService.ratingInt1));		
		assertionapiresponse.verifyAssert_httpCode(editvideoratingapirespone.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.clickVideoBasicinformationButton();
		customReport.customizedReport(IEditVideoRatingService.ratingStr1, commentspage.getRatingofExistingUser(),statusValue, driver, sTestcaseName);
		customReport.customizedReport(true, commentspage.getNOOfRatings().contains("1"), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue); 

	}

	 @Test(description = "Account Admin User giving video Rating 2 on valid video", groups = { EDITVIDEORATINGAPI })
	public void TC_02_PUT_VideoRating_api_check_AccountAdmin_Is_VideoRating_As_2_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200,loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver, sTestcaseName);
		
		logger.info("Login API response Code :::" + loginapiresponse);
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::"+ uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode,uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver, sTestcaseName);
		
		EditVideoRatingAPI videorating = new EditVideoRatingAPI();
		editvideoratingapirespone = videorating.editVideoRating(uploadvidoeapiresponse,videorating.userRating(IEditVideoRatingService.ratingInt2));
		assertionapiresponse.verifyAssert_httpCode(editvideoratingapirespone.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.clickVideoBasicinformationButton();
		customReport.customizedReport("2", commentspage.getRatingofExistingUser(), statusValue, driver, sTestcaseName);
		customReport.customizedReport(true, commentspage.getNOOfRatings().contains("1"), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);

	}

	 @Test(description = "Account Admin User giving video Rating 3 on valid video", groups = { EDITVIDEORATINGAPI })
	 public void TC_03_PUT_VideoRating_api_check_AccountAdmin_Is_VideoRating_As_3_Positive() {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200,loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver, sTestcaseName);
		
		logger.info("Login API response Code :::" + loginapiresponse);
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::"+ uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode,uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver, sTestcaseName);
		
		EditVideoRatingAPI videorating = new EditVideoRatingAPI();
		editvideoratingapirespone = videorating.editVideoRating(uploadvidoeapiresponse,videorating.userRating(IEditVideoRatingService.ratingInt3));
		assertionapiresponse.verifyAssert_httpCode(editvideoratingapirespone.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.clickVideoBasicinformationButton();
		customReport.customizedReport("3", commentspage.getRatingofExistingUser(), statusValue, driver, sTestcaseName);
		customReport.customizedReport(true, commentspage.getNOOfRatings().contains("1"), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = "Account Admin User giving video Rating 4 on valid video", groups = { EDITVIDEORATINGAPI })
	public void TC_04_PUT_VideoRating_api_check_AccountAdmin_Is_VideoRating_As_4_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200,loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver, sTestcaseName);
		
		logger.info("Login API response Code :::" + loginapiresponse);
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::"+ uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode,uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver, sTestcaseName);
		
		EditVideoRatingAPI videorating = new EditVideoRatingAPI();
		editvideoratingapirespone = videorating.editVideoRating(uploadvidoeapiresponse,videorating.userRating(IEditVideoRatingService.ratingInt4));
		assertionapiresponse.verifyAssert_httpCode(editvideoratingapirespone.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.clickVideoBasicinformationButton();
		customReport.customizedReport("4", commentspage.getRatingofExistingUser(), statusValue, driver, sTestcaseName);
		customReport.customizedReport(true, commentspage.getNOOfRatings().contains("1"), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);

	}

	@Test(description = "Account Admin User giving video Rating 5 on valid video", groups = { EDITVIDEORATINGAPI })
	public void TC_05_PUT_VideoRating_api_check_AccountAdmin_Is_VideoRating_As_5_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200,loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver, sTestcaseName);
		
		logger.info("Login API response Code :::" + loginapiresponse);
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::"+ uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode,uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver, sTestcaseName);
		
		EditVideoRatingAPI videorating = new EditVideoRatingAPI();
		editvideoratingapirespone = videorating.editVideoRating(uploadvidoeapiresponse,videorating.userRating(IEditVideoRatingService.ratingInt5));
		assertionapiresponse.verifyAssert_httpCode(editvideoratingapirespone.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.clickVideoBasicinformationButton();
		customReport.customizedReport("5", commentspage.getRatingofExistingUser(), statusValue, driver, sTestcaseName);
		customReport.customizedReport(true, commentspage.getNOOfRatings().contains("1"), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);

	}

	@Test(description = "Edit Rating API with Mulitple users -AccountAdmin Rating  as 1 and  Media Viewer Rating as 2  on Valid Video", groups = {EDITVIDEORATINGAPI })
	public void TC_06_PUT_VideoRating_AccountAdmin_Rating1_And_MediaViewer_Rating2_ValidVideo_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200,loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		String Title = adduploadvideobean.getTitle();
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::"+ uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode,uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver, sTestcaseName);
		Assert.assertEquals(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
		
		EditVideoRatingAPI videorating = new EditVideoRatingAPI();
		editvideoratingapirespone = videorating.editVideoRating(uploadvidoeapiresponse,videorating.userRating(IEditVideoRatingService.ratingInt1));
		assertionapiresponse.verifyAssert_httpCode(editvideoratingapirespone.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		loginapiresponse_mediaviewer = userservices.doLogin(apiutils.userJson(IUsersList.EditVideoRatingmediaviewer),surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediaviewer.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200,loginapiresponse_mediaviewer.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver,sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle(Title);
		loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIResponseVIDEOID,uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
		HashMap<String, String> editvideoratingapirespone_mediaviewer = editvideoratingapirespone = videorating.editVideoRating(loginapiresponse_mediaviewer,videorating.userRating(IEditVideoRatingService.ratingInt2));
		assertionapiresponse.verifyAssert_httpCode(editvideoratingapirespone_mediaviewer.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);

		// selenium code
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.clickVideoBasicinformationButton();
		customReport.customizedReport(true, commentspage.getNOOfRatings().contains("2"), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = "Edit Rating API with Mulitple users -AccountAdmin Rating  as 1 and  MediaAdmin Rating as 3 on Valid Video", groups = {EDITVIDEORATINGAPI })
	public void TC_07_PUT_VideoRating_AccountAdmin_Rating1_And_MediaAdmin_Rating3_ValidVideo_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200,loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver, sTestcaseName);
		
		logger.info("Login API response Code :::" + loginapiresponse);
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		String Title = adduploadvideobean.getTitle();
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::"+ uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode,uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver, sTestcaseName);
		
		EditVideoRatingAPI videorating = new EditVideoRatingAPI();
		editvideoratingapirespone = videorating.editVideoRating(uploadvidoeapiresponse,videorating.userRating(IEditVideoRatingService.ratingInt1));
		assertionapiresponse.verifyAssert_httpCode(editvideoratingapirespone.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		UserServices userservices_meidaadmin = new UserServices();
		loginapiresponse_mediaadmin = userservices_meidaadmin.doLogin(apiutils.userJson(IUsersList.EditVideoRatingmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediaadmin.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200,loginapiresponse_mediaadmin.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver,sTestcaseName);
		
		logger.info("Login API response Code :::" + loginapiresponse);
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle(Title);
		loginapiresponse_mediaadmin.put(IAPIConstantCodes.APIResponseVIDEOID,uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
		HashMap<String, String> editvideoratingapirespone_mediaadmin = editvideoratingapirespone = videorating.editVideoRating(loginapiresponse_mediaadmin,videorating.userRating(IEditVideoRatingService.ratingInt3));
		assertionapiresponse.verifyAssert_httpCode(editvideoratingapirespone_mediaadmin.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.clickVideoBasicinformationButton();
		customReport.customizedReport(true, commentspage.getNOOfRatings().contains("2"), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = "Edit Rating API with Mulitple users -AccountAdmin Rating  as 1 and  MediaContributor Rating as 5 on Valid Video", groups = {EDITVIDEORATINGAPI })
	public void TC_08_PUT_VideoRating_AccountAdmin_Rating1_And_Mediacontributor_Rating5_ValidVideo_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200,loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		String Title = adduploadvideobean.getTitle();
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::"+ uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode,uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver, sTestcaseName);
		
		EditVideoRatingAPI videorating = new EditVideoRatingAPI();
		editvideoratingapirespone = videorating.editVideoRating(uploadvidoeapiresponse,videorating.userRating(IEditVideoRatingService.ratingInt1));
		assertionapiresponse.verifyAssert_httpCode(editvideoratingapirespone.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		loginapiresponse_mediacontributor = userservices.doLogin(apiutils.userJson(IUsersList.EditVideoRatingmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediacontributor.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200,loginapiresponse_mediacontributor.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver,sTestcaseName);
		
		logger.info("Login API response Code :::" + loginapiresponse);
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle(Title);
		loginapiresponse_mediacontributor.put(IAPIConstantCodes.APIResponseVIDEOID,uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
		HashMap<String, String> editvideoratingapirespone_mediacontributor = editvideoratingapirespone = videorating.editVideoRating(loginapiresponse_mediacontributor,videorating.userRating(IEditVideoRatingService.ratingInt5));
		assertionapiresponse.verifyAssert_httpCode(editvideoratingapirespone_mediacontributor.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.clickVideoBasicinformationButton();
		customReport.customizedReport(true, commentspage.getNOOfRatings().contains("2"), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = "Edit Rating API with Mulitple users -AccountAdmin Rating  as 5 and  MediaContributor Rating as 4 on Valid Video", groups = {EDITVIDEORATINGAPI })
	public void TC_09_PUT_VideoRating_AccountAdmin_Rating5_And_Mediacontributor_Rating4_ValidVideo_Positive(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200,loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver, sTestcaseName);
		
		logger.info("Login API response Code :::" + loginapiresponse);
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		String Title = adduploadvideobean.getTitle();
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::"+ uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode,uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver, sTestcaseName);
		
		EditVideoRatingAPI videorating = new EditVideoRatingAPI();
		editvideoratingapirespone = videorating.editVideoRating(uploadvidoeapiresponse,videorating.userRating(IEditVideoRatingService.ratingInt5));
		assertionapiresponse.verifyAssert_httpCode(editvideoratingapirespone.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		loginapiresponse_mediacontributor = userservices.doLogin(apiutils.userJson(IUsersList.EditVideoRatingmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediacontributor.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200,loginapiresponse_mediacontributor.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver,sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle(Title);
		loginapiresponse_mediacontributor.put(IAPIConstantCodes.APIResponseVIDEOID,uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
		
		HashMap<String, String> editvideoratingapirespone_mediacontributor = editvideoratingapirespone = videorating.editVideoRating(loginapiresponse_mediacontributor,videorating.userRating(IEditVideoRatingService.ratingInt4));
		assertionapiresponse.verifyAssert_httpCode(editvideoratingapirespone_mediacontributor.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.clickVideoBasicinformationButton();
		customReport.customizedReport(true, commentspage.getNOOfRatings().contains("2"), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = "Edit Rating API with Mulitple users -AccountAdmin Rating  as 4 and  Event Admin Rating as 4 on Valid Video", groups = {EDITVIDEORATINGAPI })
	public void TC_10_PUT_VideoRating_AccountAdmin_Rating4_And_EventAdmin_Rating4_ValidVideo_Positive(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200,loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver, sTestcaseName);
		
		logger.info("Login API response Code :::" + loginapiresponse);
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		String Title = adduploadvideobean.getTitle();
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::"+ uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode,uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver, sTestcaseName);
		
		EditVideoRatingAPI videorating = new EditVideoRatingAPI();
		editvideoratingapirespone = videorating.editVideoRating(uploadvidoeapiresponse,videorating.userRating(IEditVideoRatingService.ratingInt4));
		assertionapiresponse.verifyAssert_httpCode(editvideoratingapirespone.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		loginapiresponse_eventadmin = userservices.doLogin(apiutils.userJson(IUsersList.EditVideoRatingeventadmin),surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200,loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setTitle(Title);
		loginapiresponse_eventadmin.put(IAPIConstantCodes.APIResponseVIDEOID,uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
		HashMap<String, String> editvideoratingapirespone_mediacontributor = editvideoratingapirespone = videorating.editVideoRating(loginapiresponse_eventadmin,videorating.userRating(IEditVideoRatingService.ratingInt4));
		assertionapiresponse.verifyAssert_httpCode(editvideoratingapirespone_mediacontributor.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode200);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.clickVideoBasicinformationButton();
		customReport.customizedReport(true, commentspage.getNOOfRatings().contains("2"), statusValue, driver,sTestcaseName);
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
