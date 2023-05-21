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
import org.openqa.selenium.By;
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
import com.vbrick.avenger.apibeans.AddVideoLinkBean;
import com.vbrick.avenger.apibeans.PUTMigrateVideoBean;
import com.vbrick.avenger.apibeans.VideoAccessControlBean;
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
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.service.IVideoAccessControlService;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.serviceimpl.VideoAccessControlServiceAPI;
import com.vbricks.avenger.serviceimpl.VideoDetailsAPI;
import com.vbricks.avenger.serviceimpl.AddVideoLinkAPI;
import com.vbricks.avenger.serviceimpl.CreateCategoryAPI;
import com.vbricks.avenger.serviceimpl.CreateTeamAPI;
import com.vbricks.avenger.serviceimpl.GetVideoStatusAPI;
import com.vbricks.avenger.serviceimpl.MigratVideoAPI;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.ConstantsValue;
import com.vbricks.avenger.utils.HttpStatusCode;

public class GetVideoDetails_AV_10175 extends TestBase {

	private static Logger logger = Logger.getLogger(GetVideoDetails_AV_10175.class);
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
	private AddCategoryBean addcategorybean;
	private Map<String, String> userdata;
	private BasePage basePage;
	private AddUploadVideoBean adduploadvideobean;
	public ApiBeanFactory apibeanfactory;
	public AddVideoCommentBean addvideocommentbean;
	private AddTeamBean addteambean;
	private HashMap<String, String> loginapiresponse;
	private HashMap<String, String> loginapiresponse_role1;
	private HashMap<String, String> createcategoryapirespone;
	private HashMap<String, String> createcategoryapirespone1;
	private HashMap<String, String> loginapiresponse_role2;
	private HashMap<String, String> uploadvidoeapiresponse;
	private HashMap<String, String> createteamapirespone;
	private AssertionAPIResponse assertionapiresponse;
	private VideoAccessControlBean accesscontrolbeagPage;
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
		addteambean=new AddTeamBean();
		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		adduploadvideobean = new AddUploadVideoBean();
		addcategorybean=new AddCategoryBean();
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

	@Test(description = "To Verify the VideoDetails using VideosDetails API with Account Admin by giving all the fields ",groups = {GETVIDEODETAILSAPI})
	public void TC_01_GET_GetVideoDetailsAPI_api_check_with_AccountAdmin_withallthefields_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue,driver, sTestcaseName);
								
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		
		//to assign multiple categories to video
		CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
				
		CreateCategoryAPI createcategoryAPI1=new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson1=createcategoryAPI1.createCategoryJson(addcategorybean);
		createcategoryapirespone1 = createcategoryAPI1.createcategory(loginapiresponse,categroiesjson1);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone1.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone1.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone1.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone1.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
				
		String CategoryIds[] = {createcategoryapirespone.get("name"),createcategoryapirespone1.get("name")};
		adduploadvideobean.setCategories(CategoryIds);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setUploader(IUsersList.UploadVideosmediaadmin);
		adduploadvideobean.setIs360("true");
		adduploadvideobean.setUnlisted("true");
		String tags[] = {"tag check","tag2"};
		adduploadvideobean.setTags(tags);		
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");				
		
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		VideoDetailsAPI videodetailsAPI = new VideoDetailsAPI();
		HashMap<String, String> getGetVideoDetailAPIresponse = videodetailsAPI.getVideoDetails(uploadvidoeapiresponse);

		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.VIDEOTITLE));
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.VIDEOTITLE));
		commentspage.clickVideoBasicinformationButton();
		LinkedHashMap<String, String> getvideodetailsfrmUI = commentspage.videodetails(getGetVideoDetailAPIresponse.get(IVideoAccessControlService.videoTitle));
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(getGetVideoDetailAPIresponse.get(IVideoAccessControlService.videoTitle)),statusValue, driver, sTestcaseName); // title
		customReport.customizedReport(getGetVideoDetailAPIresponse.get(IVideoAccessControlService.videoid), commentspage.getvideoID(), statusValue,driver, sTestcaseName); // VideoId
		customReport.customizedReport(true,commentspage.verifycategory(CategoryIds[0]).equalsIgnoreCase(CategoryIds[0]), statusValue, driver,sTestcaseName);
		customReport.customizedReport(true,commentspage.verifycategory(CategoryIds[1]).equalsIgnoreCase(CategoryIds[1]), statusValue, driver,sTestcaseName);
		customReport.customizedReport(tags[0], commentspage.get_Tags().get(0), statusValue, driver, sTestcaseName);
		customReport.customizedReport(tags[1], commentspage.get_Tags().get(1), statusValue, driver, sTestcaseName);
		customReport.customizedReport(true,commentspage.verifyIfVideoIs360(), statusValue, driver,sTestcaseName);
		customReport.customizedReport(true,commentspage.verifyIfVideoIsUnlisted(), statusValue, driver,sTestcaseName);		
		By uploaderlocator = commentspage.containstext_xpath1("apimau7 apimau7");
		customReport.customizedReport(true,commentspage.getAttribute(uploaderlocator).contains("apimau7 apimau7"),statusValue, driver, sTestcaseName);	
		customReport.customizedReport(adduploadvideobean.getDescription(),getvideodetailsfrmUI.get("description") , statusValue, driver, sTestcaseName);
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
		customReport.customizedReport("Active",commentspage.get_videostatus(), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduploadvideobean.getEnableRatings().equals("true"), avengereditvideosettingspage.verifyEnableRatingsSelected().contains("checked"),statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduploadvideobean.getEnableDownloads().equals("true"), avengereditvideosettingspage.verifyEnabledownloadsSelected().contains("checked"),statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduploadvideobean.getEnableComments().equals("true"), avengereditvideosettingspage.verifyEnableCommentsSelected().contains("checked"),statusValue, driver, sTestcaseName);
		customReport.customizedReport("true",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[0]),statusValue, driver, sTestcaseName);
		customReport.customizedReport("API",getGetVideoDetailAPIresponse.get("sourceType"), statusValue, driver, sTestcaseName);
		customReport.customizedReport("0",getGetVideoDetailAPIresponse.get("totalViews"), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
	}

	@Test(description = "To Verify the VideoDetails using VideoDetails API with Media admin ",groups = {GETVIDEODETAILSAPI})
	public void TC_02_GET_GetPublicVideoDetailsAPI_api_check_with_MediaAdmin_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.VideoDetailmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue,driver, sTestcaseName);

		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.VideoDetailmediaadmin);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);
		String accesscontrolforuser = IUsersList.UploadVideosmediaviewer;
		adduploadvideobean.setAccesscontrolforuser(accesscontrolforuser);
		String tags[] = {"tag check1","tag3"};
		adduploadvideobean.setTags(tags);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setEnableDownloads("true");		
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		VideoDetailsAPI videodetailsAPI = new VideoDetailsAPI();		
		HashMap<String, String> getGetVideoDetailAPIresponse = videodetailsAPI.getVideoDetails(uploadvidoeapiresponse);

		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.VIDEOTITLE));
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.VIDEOTITLE));
		commentspage.clickVideoBasicinformationButton();
		LinkedHashMap<String, String> getvideodetailsfrmUI = commentspage.videodetails(getGetVideoDetailAPIresponse.get(IVideoAccessControlService.videoTitle));
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(getGetVideoDetailAPIresponse.get(IVideoAccessControlService.videoTitle)),statusValue, driver, sTestcaseName); // title
		customReport.customizedReport(getGetVideoDetailAPIresponse.get(IVideoAccessControlService.videoid), commentspage.getvideoID(), statusValue,driver, sTestcaseName); // VideoId		
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();		
		customReport.customizedReport("Active",commentspage.get_videostatus(), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduploadvideobean.getEnableDownloads().equals("true"), avengereditvideosettingspage.verifyEnabledownloadsSelected().contains("checked"),statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduploadvideobean.getEnableComments().equals("true"), avengereditvideosettingspage.verifyEnableCommentsSelected().contains("checked"),statusValue, driver, sTestcaseName);
		customReport.customizedReport("false",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[0]),statusValue, driver, sTestcaseName);
		customReport.customizedReport("false",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[5]),statusValue, driver, sTestcaseName);
		customReport.customizedReport("API",getGetVideoDetailAPIresponse.get("sourceType"), statusValue, driver, sTestcaseName);
		customReport.customizedReport("0",getGetVideoDetailAPIresponse.get("totalViews"), statusValue, driver, sTestcaseName);
		customReport.customizedReport("apimvu7 apimvu7",commentspage.verify_Label1(accesscontrolforuser), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);

	}

	@Test(description = "To Verify the All Users VideoDetail using VideosDetail API with Media Contributor ",groups = {GETVIDEODETAILSAPI})
	public void TC_03_GET_GetAllUsers_VideosDetailsAPI_api_check_with_MediaContributor_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.VideoDetailmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue,driver, sTestcaseName);

		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.VideoDetailmediacontributor);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setVideoAccessControl("All Users");		
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		VideoDetailsAPI videodetailsAPI = new VideoDetailsAPI();
		HashMap<String, String> getGetVideoDetailAPIresponse = videodetailsAPI.getVideoDetails(uploadvidoeapiresponse);

		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.VIDEOTITLE));
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.VIDEOTITLE));
		commentspage.clickVideoBasicinformationButton();
		LinkedHashMap<String, String> getvideodetailsfrmUI = commentspage.videodetails(getGetVideoDetailAPIresponse.get(IVideoAccessControlService.videoTitle));
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(getGetVideoDetailAPIresponse.get(IVideoAccessControlService.videoTitle)),statusValue, driver, sTestcaseName); // title
		customReport.customizedReport(getGetVideoDetailAPIresponse.get(IVideoAccessControlService.videoid), commentspage.getvideoID(), statusValue,driver, sTestcaseName); // VideoId		
		By uploaderlocator = commentspage.containstext_xpath1(IUsersList.VideoDetailmediacontributor);
		customReport.customizedReport(true,commentspage.getAttribute(uploaderlocator).contains("apimcu40 apimcu40"),statusValue, driver, sTestcaseName);		
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();		
		customReport.customizedReport("Active",commentspage.get_videostatus(), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduploadvideobean.getEnableComments().equals("true"), avengereditvideosettingspage.verifyEnableCommentsSelected().contains("checked"),statusValue, driver, sTestcaseName);
		customReport.customizedReport("True",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[5]),statusValue, driver, sTestcaseName);
		customReport.customizedReport("API",getGetVideoDetailAPIresponse.get("sourceType"), statusValue, driver, sTestcaseName);
		customReport.customizedReport("0",getGetVideoDetailAPIresponse.get("totalViews"), statusValue, driver, sTestcaseName);		
		customReport.checkinglist(statusValue);
	}


	@Test(description = "To Verify the VideoDetails using VideoDetails API with Event Admin, Event Host for a private video",groups = {GETVIDEODETAILSAPI})
	public void TC_04_GET_GetPrivateVideoDetailsAPI_api_check_with_eventadminandeventhost_Negative() {

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
		adduploadvideobean.setVideoAccessControl("Private");		
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		loginapiresponse_role1 = userservices.doLogin(apiutils.userJson(IUsersList.VideoDetaileventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_role1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_role1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse_role1);
		
		loginapiresponse_role2 = userservices.doLogin(apiutils.userJson(IUsersList.VideoDetaileventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_role2.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_role2.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse_role2);
					
		loginapiresponse_role1.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_role1.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));		
		loginapiresponse_role2.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_role2.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));	
			
		VideoDetailsAPI videodetailsAPI = new VideoDetailsAPI();		
		HashMap<String, String> getGetVideoDetailAPIresponse = videodetailsAPI.getVideoDetails(loginapiresponse_role1);
		assertionapiresponse.verifyAssert_httpCode(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		HashMap<String, String> getGetVideoDetailAPIresponse1 = videodetailsAPI.getVideoDetails(loginapiresponse_role2);
		assertionapiresponse.verifyAssert_httpCode(getGetVideoDetailAPIresponse1.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, getGetVideoDetailAPIresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);	
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To Verify the VideoDetails using VideoDetails API with Media Contributor for All Teams video",groups = {GETVIDEODETAILSAPI})
	public void TC_05_GET_GetTeamsVideoDetailsAPI_api_check_with_MediaContributor_Negative() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue,driver, sTestcaseName);
		
		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		addteambean.setTeamMembers(true);
		
		String[] userIds = {loginapiresponse.get("userId")};
		addteambean.setUserids(userIds);
		addteambean.setTeammemberasuser(userIds[0]);
		addteambean.setTeammemberasgroup("");

		JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		loginapiresponse.put("channelId", createteamapirespone.get(IAPIConstantCodes.APITEAMID));	
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
		String teamname = addteambean.getName();
		String accesscontrolforteam = teamname;
		adduploadvideobean.setAccesscontrolforteam(accesscontrolforteam);
		
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		loginapiresponse_role1 = userservices.doLogin(apiutils.userJson(IUsersList.VideoDetailmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_role1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_role1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse_role1);
										
		loginapiresponse_role1.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_role1.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
		
		VideoDetailsAPI videodetailsAPI = new VideoDetailsAPI();		
		HashMap<String, String> getGetVideoDetailAPIresponse = videodetailsAPI.getVideoDetails(loginapiresponse_role1);
		assertionapiresponse.verifyAssert_httpCode(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);			
		customReport.checkinglist(statusValue);
	}

	@Test(description = "To Verify the VideoDetails using VideoDetails API with Media Viewer",groups = {GETVIDEODETAILSAPI})
	public void TC_06_GET_GetAllUsers_VideoDetails_check_with_MediaViewer_Positive() {

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
		adduploadvideobean.setVideoAccessControl("All Users");		
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		loginapiresponse_role1 = userservices.doLogin(apiutils.userJson(IUsersList.VideoDetailmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_role1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_role1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse_role1);
				
		loginapiresponse_role1.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
		loginapiresponse_role1.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
					
		VideoDetailsAPI videodetailsAPI = new VideoDetailsAPI();		
		HashMap<String, String> getGetVideoDetailAPIresponse = videodetailsAPI.getVideoDetails(loginapiresponse_role1);
		assertionapiresponse.verifyAssert_httpCode(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.VIDEOTITLE));
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.VIDEOTITLE));
		commentspage.clickVideoBasicinformationButton();
		LinkedHashMap<String, String> getvideodetailsfrmUI = commentspage.videodetails(getGetVideoDetailAPIresponse.get(IVideoAccessControlService.videoTitle));
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(getGetVideoDetailAPIresponse.get(IVideoAccessControlService.videoTitle)),statusValue, driver, sTestcaseName); // title
		customReport.customizedReport(getGetVideoDetailAPIresponse.get(IVideoAccessControlService.videoid), commentspage.getvideoID(), statusValue,driver, sTestcaseName); // VideoId		
		customReport.checkinglist(statusValue);
	}

	@Test(description = "To Verify the VideoDetails using VideosDetails API with Account Admin by giving only mandatory fields ",groups = {GETVIDEODETAILSAPI})
	public void TC_07_GET_GetVideoDetailsAPI_api_check_with_AccountAdmin_withonlymandatoryfieds_Positive() {

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
		adduploadvideobean.setDescription("");
		adduploadvideobean.setTitle("");
		adduploadvideobean.setEnableComments("");
		adduploadvideobean.setEnableDownloads("");
		adduploadvideobean.setEnableRatings("");
		adduploadvideobean.setIsActive("");
		adduploadvideobean.setVideoAccessControl(null);
		adduploadvideobean.setCategories(null);
		adduploadvideobean.setCategoryIds(null);
		adduploadvideobean.setTags(null);
		adduploadvideobean.setIs360("");
		adduploadvideobean.setUnlisted("");		
		loginapiresponse.put("Mandatory", "Yes");
		loginapiresponse.put("fileName", "Yes");
		
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		VideoDetailsAPI videodetailsAPI = new VideoDetailsAPI();
		HashMap<String, String> getGetVideoDetailAPIresponse = videodetailsAPI.getVideoDetails(uploadvidoeapiresponse);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.VIDEOTITLE));
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.VIDEOTITLE));
		commentspage.clickVideoBasicinformationButton();
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(getGetVideoDetailAPIresponse.get(IVideoAccessControlService.videoTitle)),statusValue, driver, sTestcaseName); // title
		customReport.customizedReport(getGetVideoDetailAPIresponse.get(IVideoAccessControlService.videoid), commentspage.getvideoID(), statusValue,driver, sTestcaseName); // VideoId
		customReport.customizedReport(false,commentspage.verifyIfVideoIsUnlisted(), statusValue, driver,sTestcaseName);
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
		customReport.customizedReport("Inactive",commentspage.get_videostatus(), statusValue, driver, sTestcaseName);
		customReport.customizedReport("false",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[0]),statusValue, driver, sTestcaseName);
		customReport.customizedReport("false",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[5]),statusValue, driver, sTestcaseName);
		customReport.customizedReport("API",getGetVideoDetailAPIresponse.get("sourceType"), statusValue, driver, sTestcaseName);
		customReport.customizedReport("0",getGetVideoDetailAPIresponse.get("totalViews"), statusValue, driver, sTestcaseName);
		commentspage.clickCancel();
		commentspage.click_settingsLink();
		commentspage.click_deleteButton();
		customReport.checkinglist(statusValue);

	}
	
	@Test(description = "To Verify the VideoDetail API with Media Admin as team contributor",groups = {GETVIDEODETAILSAPI})
	public void TC_08_GET_GetVideosDetailsAPI_api_check_with_MediaAdmin_asteamcontributor_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.VideoDetailmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue,driver, sTestcaseName);
		
		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		addteambean.setTeamMembers(true);
		String[] userIds = {loginapiresponse.get("userId")};
		addteambean.setUserids(userIds);
		addteambean.setTeammemberasuser(userIds[0]);
		addteambean.setTeammemberasgroup("");	
	
		JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);

		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.VideoDetailmediaadmin);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.FALSE);
		adduploadvideobean.setVideoAccessControl("Private");
		loginapiresponse.put("channelId", createteamapirespone.get(IAPIConstantCodes.APITEAMID));		
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
        logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
        customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
         
        HashMap<String,String> loginapiresponse_mediaadmin = userservices.doLogin((apiutils.userJson(IUsersList.VideoDetailmediaadmin)), surl);
        loginapiresponse_mediaadmin.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
        loginapiresponse_mediaadmin.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
 		
        VideoAccessControlServiceAPI videoaccesscontrolservice=new VideoAccessControlServiceAPI();
 		accesscontrolbeagPage.setTeamcanEdit("true");
 		accesscontrolbeagPage.setTeamid(loginapiresponse.get("channelId"));
 		accesscontrolbeagPage.setTeamtype("Channel");		
 		String teamname=addteambean.getName(); 		
        JSONObject json=videoaccesscontrolservice.createJsonVideoAccessControl(accesscontrolbeagPage, IVideoAccessControlService.jsonFlagTeam);
        videoaccesscontrolservice.videoAccessControl(loginapiresponse_mediaadmin,json);
         
        VideoDetailsAPI videodetailsAPI = new VideoDetailsAPI();
 		HashMap<String, String> getGetVideoDetailAPIresponse = videodetailsAPI.getVideoDetails(uploadvidoeapiresponse);		
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(IUsersList.VideoDetailmediaadmin, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.VIDEOTITLE));
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.VIDEOTITLE));
		commentspage.clickVideoBasicinformationButton();
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(getGetVideoDetailAPIresponse.get(IVideoAccessControlService.videoTitle)),statusValue, driver, sTestcaseName); // title
		customReport.customizedReport(getGetVideoDetailAPIresponse.get(IVideoAccessControlService.videoid), commentspage.getvideoID(), statusValue,driver, sTestcaseName); // VideoId
		By uploaderlocator = commentspage.containstext_xpath1("apimau36 apimau36");
		customReport.customizedReport(true,commentspage.getAttribute(uploaderlocator).contains("apimau36 apimau36"),statusValue, driver, sTestcaseName);		
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();		
		customReport.customizedReport("Active",commentspage.get_videostatus(), statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduploadvideobean.getEnableRatings().equals("true"), avengereditvideosettingspage.verifyEnableRatingsSelected().contains("active"),statusValue, driver, sTestcaseName);
		customReport.customizedReport("false",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[0]),statusValue, driver, sTestcaseName);
		customReport.customizedReport("false",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[5]),statusValue, driver, sTestcaseName);
		customReport.customizedReport("API",getGetVideoDetailAPIresponse.get("sourceType"), statusValue, driver, sTestcaseName);
		customReport.customizedReport("0",getGetVideoDetailAPIresponse.get("totalViews"), statusValue, driver, sTestcaseName);
		customReport.customizedReport(teamname,commentspage.verify_Label1(teamname), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To Verify the Teams VideoDetails API without giving authorization token ",groups = {GETVIDEODETAILSAPI})
	public void TC_09_GET_GetTeamsVideoDetails_Teams_API_check_without_givingauthorizationtoken_Negative() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), statusValue,driver, sTestcaseName);
		
		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		addteambean.setTeamMembers(true);
		String[] userIds = {loginapiresponse.get("userId")};
		addteambean.setUserids(userIds);
		addteambean.setTeammemberasuser(userIds[0]);
		addteambean.setTeammemberasgroup("");
			
		JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);

		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.FALSE);
		adduploadvideobean.setVideoAccessControl("Private");
		loginapiresponse.put("channelId", createteamapirespone.get(IAPIConstantCodes.APITEAMID));		
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
        logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
        customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
         
         HashMap<String,String> loginapiresponse_mediaadmin = userservices.doLogin(apiutils.userJson(sUserName), surl);
         loginapiresponse_mediaadmin.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
         loginapiresponse_mediaadmin.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
 		
         VideoAccessControlServiceAPI videoaccesscontrolservice=new VideoAccessControlServiceAPI();
 		 accesscontrolbeagPage.setTeamcanEdit("true");
 		 accesscontrolbeagPage.setTeamid(loginapiresponse.get("channelId"));
 		 accesscontrolbeagPage.setTeamtype("Channel"); 		
 		 String teamname=addteambean.getName();
 		 JSONObject json=videoaccesscontrolservice.createJsonVideoAccessControl(accesscontrolbeagPage, IVideoAccessControlService.jsonFlagTeam);
         videoaccesscontrolservice.videoAccessControl(loginapiresponse_mediaadmin,json);
         
         VideoDetailsAPI videodetailsAPI = new VideoDetailsAPI();
 		 HashMap<String, String> getGetVideoDetailAPIresponse = videodetailsAPI.getVideoDetails1(uploadvidoeapiresponse);		
 		 logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		 assertionapiresponse.verifyAssert_httpCode(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatus401);
		 customReport.customizedReport(HttpStatusCode.httpsStatus401,getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		 customReport.checkinglist(statusValue);
	}
	
	
	@Test(description = "To Verify the VideoDetails API without giving authorization token for public video ",groups = {GETVIDEODETAILSAPI})
	public void TC_10_GET_GetVideoDetailsAPI_forpublicvideo_withoutgiving_authorizationtoken_Positive() {

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
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
						
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		VideoDetailsAPI videodetailsAPI = new VideoDetailsAPI();
		HashMap<String, String> getGetVideoDetailAPIresponse = videodetailsAPI.getVideoDetails1(uploadvidoeapiresponse);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.VIDEOTITLE));
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.VIDEOTITLE));
		commentspage.clickVideoBasicinformationButton();
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(getGetVideoDetailAPIresponse.get(IVideoAccessControlService.videoTitle)),statusValue, driver, sTestcaseName); // title
		customReport.customizedReport(getGetVideoDetailAPIresponse.get(IVideoAccessControlService.videoid), commentspage.getvideoID(), statusValue,driver, sTestcaseName); // VideoId
		customReport.checkinglist(statusValue);

	}
	
	@Test(description = "To Verify the VideoDetails API without giving authorization token for private video ",groups = {GETVIDEODETAILSAPI})
	public void TC_11_GET_GetVideoDetailsAPI_forprivatevideo_withoutgiving_authorizationtoken_Negative() {

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
		String accesscontrolforuser = IUsersList.UploadVideosmediaviewer;
		adduploadvideobean.setAccesscontrolforuser(accesscontrolforuser);			
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
						
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		VideoDetailsAPI videodetailsAPI = new VideoDetailsAPI();
		HashMap<String, String> getGetVideoDetailAPIresponse = videodetailsAPI.getVideoDetails1(uploadvidoeapiresponse);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);

	}
	/*@Test(description = " To verify legacy view count through getvideo details api using Migrate Video APi and get total Views ",groups = {GETVIDEODETAILSAPI})
	public void TC_11_Get_VideoDetails_api_check_Migrate_LegacyView_asValid_Count_put_MigrateVideoApi_And_GetTotalViews_Positive_AV29567(ITestContext context)
	{
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
		
		//login as mediaadmin
		HashMap<String, String>	loginapiresponse_mediaadmin = userservices.doLogin(apiutils.userJson(IMegratVideoService.mediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediaadmin.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse_mediaadmin);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_mediaadmin.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		PUTMigrateVideoBean migratevideobean=new PUTMigrateVideoBean();
		migratevideobean.setOwner("Yes");
		migratevideobean.setUserId(loginapiresponse_mediaadmin.get(IAPIConstantCodes.APIResponseUSERID));
		migratevideobean.setownerUserName(loginapiresponse_mediaadmin.get(IAPIConstantCodes.APIUSERNAME));
		migratevideobean.setEmail(loginapiresponse_mediaadmin.get(IAPIConstantCodes.APIUSEREMAIL));
		migratevideobean.setLegacyViewCount(apiutils.randomNumericals());
		 
		
		MigratVideoAPI migratvideoAPI=new MigratVideoAPI();
	    HashMap<String, String> migratvideoapirespone = migratvideoAPI.migratVideo(uploadvidoeapiresponse,migratevideobean); 	
	    assertionapiresponse.verifyAssert_httpCode(migratvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+migratvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, migratvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+migratvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
	    
	    VideoDetailsAPI videodetailsAPI = new VideoDetailsAPI();
	    HashMap<String, String> getGetVideoDetailAPIresponse = videodetailsAPI.getVideoDetails(uploadvidoeapiresponse);		
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		customReport.customizedReport(migratevideobean.getLegacyViewCount(),getGetVideoDetailAPIresponse.get("totalViews"),statusValue, driver, sTestcaseName);
	    
	    logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		String viewcount_seachvideopage=avengerdashboardpage.getviews_searchvideoPage(migratevideobean.getLegacyViewCount());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.clickVideoBasicinformationButton();
        LinkedHashMap<String, String> videimporteddetails=commentspage.getinformationvideodetails();
        customReport.customizedReport(getGetVideoDetailAPIresponse.get("totalViews"),viewcount_seachvideopage,statusValue, driver, sTestcaseName);
        customReport.customizedReport(getGetVideoDetailAPIresponse.get("totalViews"),videimporteddetails.get(IAPIConstantCodes.videoTotalViews),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);		
	    
	}
	
	@Test(description = " To verify legacy view count through getvideo details api using Upload Video APi and get total Views ",groups = {GETVIDEODETAILSAPI})
	public void TC_12_Get_VideoDetails_api_check_Migrate_LegacyView_asValid_Count_post_UploadVideoApi_And_GetTotalViews_Positive_AV29567(ITestContext context) {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		
		//Upload Video
 		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);	
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);
		adduploadvideobean.setLegacyViewCount(apiutils.randomNumericals());
		
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "Yes");
														
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);		
		
		
		VideoDetailsAPI videodetailsAPI = new VideoDetailsAPI();
	    HashMap<String, String> getGetVideoDetailAPIresponse = videodetailsAPI.getVideoDetails(uploadvidoeapiresponse);		
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduploadvideobean.getLegacyViewCount(),getGetVideoDetailAPIresponse.get("totalViews"),statusValue, driver, sTestcaseName);
		
		//Verification through UI 
	    logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		String viewcount_seachvideopage=avengerdashboardpage.getviews_searchvideoPage(adduploadvideobean.getLegacyViewCount());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.clickVideoBasicinformationButton();
        LinkedHashMap<String, String> videimporteddetails=commentspage.getinformationvideodetails();
        customReport.customizedReport(getGetVideoDetailAPIresponse.get("totalViews"),viewcount_seachvideopage,statusValue, driver, sTestcaseName);
        customReport.customizedReport(getGetVideoDetailAPIresponse.get("totalViews"),videimporteddetails.get(IAPIConstantCodes.videoTotalViews),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);		
	}
	
	@Test(description = "To verify legacy view count through getvideo details api using Migrate Video APi and get total Views",groups = {PostAddVideoLink})
	public void  TC_13_Get_VideoDetails_api_check_Migrate_LegacyView_asValid_Count_post_AddVideoLinkApi_And_GetTotalViews_Positive_AV29567() throws Exception {

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
		addvideolinkbean.setLegacyViewCount(apiutils.randomNumericals());
		
		
		AddVideoLinkAPI addvideolinkapi=new AddVideoLinkAPI();
		HashMap<String, String> addvideolinkapiresponse=addvideolinkapi.addVideolink(loginapiresponse, addvideolinkbean);
		assertionapiresponse.verifyAssert_httpCode(addvideolinkapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, addvideolinkapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		addvideolinkapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, loginapiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
		
		VideoDetailsAPI videodetailsAPI = new VideoDetailsAPI();
		HashMap<String, String> getGetVideoDetailAPIresponse = videodetailsAPI.getVideoDetails(addvideolinkapiresponse);		
	    logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		customReport.customizedReport(addvideolinkbean.getLegacyViewCount(),getGetVideoDetailAPIresponse.get("totalViews"),statusValue, driver, sTestcaseName);
		    
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
        customReport.customizedReport(getGetVideoDetailAPIresponse.get("totalViews"),viewcount_seachvideopage,statusValue, driver, sTestcaseName);
        customReport.customizedReport(getGetVideoDetailAPIresponse.get("totalViews"),videimporteddetails.get(IAPIConstantCodes.videoTotalViews),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);		
	    
	}
	
	@Test(description = " To verify legacy view count through getvideo details api using Migrate Video APi and watch video in UI and get total Views updated",groups = {GETVIDEODETAILSAPI})
	public void TC_14_Get_VideoDetails_api_check_Migrate_LegacyView_asValid_Count_put_MigrateVideoApi_And_WatchVideo_GetTotalViews_Positive_AV29567(ITestContext context) throws Exception
	{
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
		
		//login as mediaadmin
		HashMap<String, String>	loginapiresponse_mediaadmin = userservices.doLogin(apiutils.userJson(IMegratVideoService.mediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediaadmin.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse_mediaadmin);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_mediaadmin.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		PUTMigrateVideoBean migratevideobean=new PUTMigrateVideoBean();
		migratevideobean.setOwner("Yes");
		migratevideobean.setUserId(loginapiresponse_mediaadmin.get(IAPIConstantCodes.APIResponseUSERID));
		migratevideobean.setownerUserName(loginapiresponse_mediaadmin.get(IAPIConstantCodes.APIUSERNAME));
		migratevideobean.setEmail(loginapiresponse_mediaadmin.get(IAPIConstantCodes.APIUSEREMAIL));
		migratevideobean.setLegacyViewCount("97");
		 
		
		MigratVideoAPI migratvideoAPI=new MigratVideoAPI();
	    HashMap<String, String> migratvideoapirespone = migratvideoAPI.migratVideo(uploadvidoeapiresponse,migratevideobean); 	
	    assertionapiresponse.verifyAssert_httpCode(migratvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+migratvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, migratvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+migratvideoapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
	    	    
	    logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle()); 
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		Thread.sleep(53000);
		commentspage.clickPlayButton();
		Thread.sleep(30000);
		commentspage.refreshBrowser();
		Thread.sleep(120000);
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle()); 
		String viewcount_seachvideopage=avengerdashboardpage.getviews_searchvideoPage("98");
		AvengerVideoCommentsPage commentpage =avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentpage.clickVideoBasicinformationButton();
        LinkedHashMap<String, String> videimporteddetails=commentpage.getwebeximportedvideodetails();
        
        
        VideoDetailsAPI videodetailsAPI = new VideoDetailsAPI();
	    HashMap<String, String> getGetVideoDetailAPIresponse = videodetailsAPI.getVideoDetails(uploadvidoeapiresponse);		
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		customReport.customizedReport("98",getGetVideoDetailAPIresponse.get("totalViews"),statusValue, driver, sTestcaseName);
             
		//assertion with UI 
		customReport.customizedReport(true,getGetVideoDetailAPIresponse.get("totalViews").equals(viewcount_seachvideopage),statusValue, driver, sTestcaseName);
        customReport.customizedReport(true,videimporteddetails.get(IAPIConstantCodes.videoTotalViews).contains(getGetVideoDetailAPIresponse.get("totalViews")),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);		
	    
	}
	@Test(description = "To verify legacy view count through getvideo details api using Upload Video APi and watch video in UI and get total Views updated",groups = {GETVIDEODETAILSAPI})
	public void TC_15_Get_VideoDetails_api_check_Migrate_LegacyView_asValid_Count_post_UploadVideoApi_And_WatchVideo_GetTotalViews_Positive_AV29567(ITestContext context) throws Exception {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		//Upload Video
 		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);	
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);
		adduploadvideobean.setLegacyViewCount("97");
		
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "Yes");
														
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
		
	    logger.info("Selenium Code is excuting");
        launchURL(surl);
	    customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle()); 
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		Thread.sleep(53000);
		commentspage.clickPlayButton();
		Thread.sleep(30000);
		commentspage.refreshBrowser();
		Thread.sleep(120000);
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle()); 
		String viewcount_seachvideopage=avengerdashboardpage.getviews_searchvideoPage("98");
		AvengerVideoCommentsPage commentpage =avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentpage.clickVideoBasicinformationButton();
        LinkedHashMap<String, String> videimporteddetails=commentpage.getwebeximportedvideodetails();
	        
	        
        VideoDetailsAPI videodetailsAPI = new VideoDetailsAPI();
	    HashMap<String, String> getGetVideoDetailAPIresponse = videodetailsAPI.getVideoDetails(uploadvidoeapiresponse);		
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		customReport.customizedReport("98",getGetVideoDetailAPIresponse.get("totalViews"),statusValue, driver, sTestcaseName);
             
		//assertion with UI 
		customReport.customizedReport(true,getGetVideoDetailAPIresponse.get("totalViews").equals(viewcount_seachvideopage),statusValue, driver, sTestcaseName);
	    customReport.customizedReport(true,videimporteddetails.get(IAPIConstantCodes.videoTotalViews).contains(getGetVideoDetailAPIresponse.get("totalViews")),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);		
		    
		}
		
	@Test(description = "To verify legacy view count through getvideo details api using Add Video Link APi and watch video in UI and get total Views updated",groups = {PostAddVideoLink})
	public void TC_16_Get_VideoDetails_api_check_Migrate_LegacyView_asValid_Count_post_AddVideoLinkApi_And_WatchVideo_GetTotalViews_Positive_AV29567() throws Exception {

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
		addvideolinkbean.setLegacyViewCount("97");
		
		
		AddVideoLinkAPI addvideolinkapi=new AddVideoLinkAPI();
		HashMap<String, String> addvideolinkapiresponse=addvideolinkapi.addVideolink(loginapiresponse, addvideolinkbean);
		assertionapiresponse.verifyAssert_httpCode(addvideolinkapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, addvideolinkapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		addvideolinkapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, loginapiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
		
  	    
	    logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(addvideolinkbean.getTitle()); 
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(addvideolinkbean.getTitle());
		commentspage.clickPlayButton();
		Thread.sleep(30000);
		commentspage.refreshBrowser();
		Thread.sleep(120000);
		avengerdashboardpage.searchMedia(addvideolinkbean.getTitle()); 
		String viewcount_seachvideopage=avengerdashboardpage.getviews_searchvideoPage("98");
		AvengerVideoCommentsPage commentpage =avengerdashboardpage.clickVideo(addvideolinkbean.getTitle());
		commentpage.clickVideoBasicinformationButton();
		LinkedHashMap<String, String> videimporteddetails=commentspage.getinformationvideodetails();
        
        VideoDetailsAPI videodetailsAPI = new VideoDetailsAPI();
		HashMap<String, String> getGetVideoDetailAPIresponse = videodetailsAPI.getVideoDetails(addvideolinkapiresponse);		
	    logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) + getGetVideoDetailAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		customReport.customizedReport("98",getGetVideoDetailAPIresponse.get("totalViews"),statusValue, driver, sTestcaseName);
             
		//assertion with UI 
		customReport.customizedReport(true,getGetVideoDetailAPIresponse.get("totalViews").equals(viewcount_seachvideopage),statusValue, driver, sTestcaseName);
        customReport.customizedReport(true,videimporteddetails.get(IAPIConstantCodes.videoTotalViews).contains(getGetVideoDetailAPIresponse.get("totalViews")),statusValue, driver, sTestcaseName);
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
