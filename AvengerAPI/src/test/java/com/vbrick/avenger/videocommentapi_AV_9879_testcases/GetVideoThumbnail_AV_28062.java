package com.vbrick.avenger.videocommentapi_AV_9879_testcases;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbrick.avenger.apibeans.AddVideoCommentBean;
import com.vbrick.avenger.apibeans.VideoAccessControlBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerContentRestrictionPage;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerEditVideoSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerSystemSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerUserPasswordParametersPage;
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.service.IVideoAccessControlService;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.serviceimpl.VideoAccessControlServiceAPI;
import com.vbricks.avenger.serviceimpl.VideoDetailsAPI;
import com.vbricks.avenger.serviceimpl.GetVideoStatusAPI;
import com.vbricks.avenger.serviceimpl.GetVideoThumbnailAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoImageAPI;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.ConstantsValue;
import com.vbricks.avenger.utils.HttpStatusCode;

public class GetVideoThumbnail_AV_28062 extends TestBase {

	private static Logger logger = Logger.getLogger(GetVideoThumbnail_AV_28062.class);
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
	private HashMap<String, String> uploadvidoeapiresponse;
	private AssertionAPIResponse assertionapiresponse;
	private VideoAccessControlBean accesscontrolbeagPage;
	private HashMap<String, String> uploadImageapiresponse;
	private AvengerSystemSettingsPage avengerSystemSettingsPage;
	private AvengerContentRestrictionPage avengercontentrestrictionpage;
	private AvengerUserPasswordParametersPage avengerUserPasswordParametersPage;
	private AvengerHomePage avengerHomePage;
	private ApiUtils apiutils;

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
		accesscontrolbeagPage = new VideoAccessControlBean();
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

	@Test(description = "To Verify the VideoThumbnail using VideoThumbnail API with Account Admin (Getting default Image-simpliLearn Time and material image)",groups = {GETVIDEOTHUMBNAIL})
	public void TC_01_GET_GetVideoThumbnailAPI_api_check_with_AccountAdmin_Positive_DefaultImage(ITestContext context) throws InterruptedException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue,driver, sTestcaseName);

		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		Thread.sleep(50000);
		GetVideoThumbnailAPI getGetVideoThumbnailAPI = new GetVideoThumbnailAPI();
		HashMap<String, String> getGetVideoStatusAPIresponse = getGetVideoThumbnailAPI.getVideoThumbnail(uploadvidoeapiresponse);
		logger.info("UploadVideo API response Code :::" + getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = "To Verify the VideoThumbnail using VideoThumbnail API with Account Admin (Getting Image which we have uploaded - TOYOTO image)",groups = {GETVIDEOTHUMBNAIL})
	public void TC_02_GET_GetVideoThumbnailAPI_api_check_with_AccountAdmin_Positive_UploadedImage(ITestContext context) throws InterruptedException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue,driver, sTestcaseName);

		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		Thread.sleep(50000);
		UploadVideoImageAPI uploadVideoImageAPI=new UploadVideoImageAPI();
		uploadImageapiresponse = uploadVideoImageAPI.uploadVideoImageFile(uploadvidoeapiresponse, Setup.VALIDIMAGEPATH_PNG,IUploadVideoService.UPLOADIMAGE );
		logger.info("UploadVideo API response Code :::" + uploadImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadImageapiresponse);
	
		GetVideoThumbnailAPI getGetVideoThumbnailAPI = new GetVideoThumbnailAPI();	
		HashMap<String, String> getGetVideoStatusAPIresponse = getGetVideoThumbnailAPI.getVideoThumbnail(uploadvidoeapiresponse);
		logger.info("UploadVideo API response Code :::" + getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}

		@Test(description = "To Get the VideoThumbnail using VideosThumbnail API when video is private with MEDIAVIEWER",groups = {GETVIDEOTHUMBNAIL}) //Should return dummy image
		public void TC_03_GET_GET_GetVideoThumbnailAPI_api_check_with_Unauthorized_User_Negative() throws InterruptedException {
			logger.info("API Level Code is excuting");
			sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

			UserServices userservices = new UserServices();
			loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
			logger.info("Login API response Code :::" + loginapiresponse);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue,driver, sTestcaseName);

			UploadVideoService uploadvidoeservice = new UploadVideoService();
			apibeanfactory.UploadVideoBean(adduploadvideobean);
			adduploadvideobean.setUploader(sUserName);
			adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		    loginapiresponse.put("Mandatory", "No");
			loginapiresponse.put("fileName", "No");
			adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);
			uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
			logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

			Thread.sleep(50000);
			GetVideoThumbnailAPI getGetVideoThumbnailAPI = new GetVideoThumbnailAPI();
			HashMap<String,String> mediaviewerlogin = userservices.doLogin(apiutils.userJson(IUsersList.GetVideoThumbnail), surl);
			uploadvidoeapiresponse.put("accesstoken", mediaviewerlogin.get("accesstoken"));
			HashMap<String, String> getGetVideoStatusAPIresponse = getGetVideoThumbnailAPI.getVideoThumbnail(uploadvidoeapiresponse);
			logger.info("UploadVideo API response Code :::" + getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			assertionapiresponse.verifyAssert_httpCode(getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
			customReport.checkinglist(statusValue);
		}
	
		@Test(description = "To Get the VideoThumbnail using VideosThumbnail API when video doesn't Exists",groups = {GETVIDEOTHUMBNAIL}) //Should return dummy image
		public void TC_04_GET_GetVideoThumbnailAPI_api_check_when_Video_DoesntExist_Negative() throws InterruptedException {
			logger.info("API Level Code is excuting");
			sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

			UserServices userservices = new UserServices();
			loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
			logger.info("Login API response Code :::" + loginapiresponse);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue,driver, sTestcaseName);

			UploadVideoService uploadvidoeservice = new UploadVideoService();
			apibeanfactory.UploadVideoBean(adduploadvideobean);
			adduploadvideobean.setUploader(sUserName);
			adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		    loginapiresponse.put("Mandatory", "No");
			loginapiresponse.put("fileName", "No");
			adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);
			uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
			logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
			Thread.sleep(50000);
			GetVideoThumbnailAPI getGetVideoThumbnailAPI = new GetVideoThumbnailAPI();
			HashMap<String,String> mediaviewerlogin = userservices.doLogin(apiutils.userJson(IUsersList.GetVideoThumbnail), surl);
			uploadvidoeapiresponse.put("accesstoken", mediaviewerlogin.get("accesstoken"));
			uploadvidoeapiresponse.put("videoId", "d9372c33-cdcb-447f-a4d8-9f01dd508888");
			HashMap<String, String> getGetVideoStatusAPIresponse = getGetVideoThumbnailAPI.getVideoThumbnail(uploadvidoeapiresponse);
			logger.info("UploadVideo API response Code :::" + getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			assertionapiresponse.verifyAssert_httpCode(getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
			customReport.checkinglist(statusValue);
		}	
	
		@Test(description = "To Get the VideoThumbnail using VideosThumbnail API without Authorization",groups = {GETVIDEOTHUMBNAIL}) //Should return dummy image.
		public void TC_05_GET_GetVideoThumbnailAPI_api_check_withoutAuthorization_Token_Account_Level_Positive() throws InterruptedException {
			
			logger.info("API Level Code is excuting");
			sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

			UserServices userservices = new UserServices();
			loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
			logger.info("Login API response Code :::" + loginapiresponse);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue,driver, sTestcaseName);

			UploadVideoService uploadvidoeservice = new UploadVideoService();
			apibeanfactory.UploadVideoBean(adduploadvideobean);
			adduploadvideobean.setUploader(sUserName);
			adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		    loginapiresponse.put("Mandatory", "No");
			loginapiresponse.put("fileName", "No");
			adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);
			uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
			logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

			Thread.sleep(50000);
			GetVideoThumbnailAPI getGetVideoThumbnailAPI = new GetVideoThumbnailAPI();
			HashMap<String, String> getGetVideoStatusAPIresponse = getGetVideoThumbnailAPI.getVideoThumbnailwithoutAuthorization(uploadvidoeapiresponse);
			logger.info("UploadVideo API response Code :::" + getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			assertionapiresponse.verifyAssert_httpCode(getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
			customReport.checkinglist(statusValue);			

		}
				
		@Test(description = "To Get the VideoThumbnail using VideosThumbnail API when \"Allow Sharing of Metadata for Private Videos\" (in System Settings�>Security) is enabled in the account",groups = {GETVIDEOTHUMBNAIL}) //Should return actual image since setting as been done accordingly.
		public void TC_06_GET_GetVideoThumbnailAPI_api_check_when_Sharing_of_metadata_is_Checked_at_Account_Level_Positive() throws InterruptedException {
			
			logger.info("Selenium Code is Executing to enable Sharing of MetaData at Account Level");		
			launchURL(surl);
			customReport.reporter("Application launch with URL : ", surl);
			avengerHomePage=loginPage.loginAs(sUserName,sPassword);
			avengerHomePage.clickSettingsLink();
			avengerSystemSettingsPage = avengerHomePage.clickSystemSettingsLink();
			avengercontentrestrictionpage=avengerSystemSettingsPage.click_ContentRestrictionLinkLocator();
			avengercontentrestrictionpage.checkAllowSharingofmetadataprivateVideos();
			avengercontentrestrictionpage.click_save();
			
			logger.info("API Level Code is excuting");
			sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

			UserServices userservices = new UserServices();
			loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
			logger.info("Login API response Code :::" + loginapiresponse);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue,driver, sTestcaseName);

			UploadVideoService uploadvidoeservice = new UploadVideoService();
			apibeanfactory.UploadVideoBean(adduploadvideobean);
			adduploadvideobean.setUploader(sUserName);
			adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		    loginapiresponse.put("Mandatory", "No");
			loginapiresponse.put("fileName", "No");
			adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);
			uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
			logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			uploadvidoeapiresponse.put("baseurl", GuestURL);
			
			Thread.sleep(100000);
			GetVideoThumbnailAPI getGetVideoThumbnailAPI = new GetVideoThumbnailAPI();
			HashMap<String, String> getGetVideoStatusAPIresponse = getGetVideoThumbnailAPI.getVideoThumbnailwithoutAuthorization(uploadvidoeapiresponse);
			logger.info("UploadVideo API response Code :::" + getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			assertionapiresponse.verifyAssert_httpCode(getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoStatusAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
			customReport.checkinglist(statusValue);			

            // Disabling Allow Sharing of Metadata for Private Videos
			avengercontentrestrictionpage.unCheckAllowSharingofmetadataprivateVideos();
			avengercontentrestrictionpage.click_save();
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
