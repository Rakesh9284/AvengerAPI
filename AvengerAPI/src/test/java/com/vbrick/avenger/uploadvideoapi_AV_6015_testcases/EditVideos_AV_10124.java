package com.vbrick.avenger.uploadvideoapi_AV_6015_testcases;

import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.time.DateUtils;
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
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AvengerGroupsBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.LibraryBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.DateTime;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerEditVideoSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerFeaturesPage;
import com.vbrick.avenger.pageobjects.AvengerGroupsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLibrariesPage;
import com.vbrick.avenger.pageobjects.AvengerLibraryInformationPage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerMediaSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.ConstantsValue;
import com.vbricks.avenger.utils.HttpStatusCode;

public class EditVideos_AV_10124 extends TestBase {

	private static Logger logger = Logger.getLogger(EditVideos_AV_10124.class);
	private AvengerLoginPage loginPage;
	private AvengerHomePage homePage;
	private AvengerLibrariesPage avengerlibrarypage;
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
	private AddUploadVideoBean adduploadvideobean1;
    public ApiBeanFactory apibeanfactory;
	private AssertionAPIResponse assertionapiresponse;
	private HashMap<String, String> loginapiresponse;
	private HashMap<String, String> loginapiresponse1;
	private HashMap<String, String> uploadvidoeapiresponse, editvidoeapiresponse;
	private ApiUtils apiutils;
	private AvengerGroupsPage avengergroupspage;
	private AvengerGroupsBeanPage avengergroupsbeanpage;
	private LibraryBeanPage librarybeanpage;

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
		adduploadvideobean = new AddUploadVideoBean();
		adduploadvideobean1 = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		assertionapiresponse = new AssertionAPIResponse();
		avengergroupsbeanpage = new AvengerGroupsBeanPage();
		librarybeanpage = new LibraryBeanPage();
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
		accountBeansFactory.LibraryBeanByPage(librarybeanpage);

	}

	@Test(description = "Editing Video Title for Existing Uploaded video's Title",groups = {EDITVIDEOAPI})
	public void TC_01_PUT_EditVideosAPI_AV10124_ValidData_With_EditVideoTitle() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {	}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setEnableDownloads("true");
		adduploadvideobean.setEnableRatings("true");
		adduploadvideobean.setIsActive("true");

		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,driver, sTestcaseName);
		
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");

		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get("httpCode"));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get("httpCode"),statusValue, driver, sTestcaseName);
	 
		loginapiresponse.put("videoId", uploadvidoeapiresponse.get("videoId"));
		adduploadvideobean.setTitle(IUploadVideoService.updateAPIUpdateVideoTitle);

		editvidoeapiresponse = uploadvidoeservice.editVideo(loginapiresponse, adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + editvidoeapiresponse.get("httpCode"));
		assertionapiresponse.verifyAssert_httpCode(editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"), statusValue, driver,sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = "Editing Video Description for Existing Uploaded video's Description",groups = {EDITVIDEOAPI})
	public void TC_02_PUT_EditVideosAPI_AV10124_ValidData_With_EditVideoDescription(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();

		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setVideoAccessControl("Private");

		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,driver, sTestcaseName);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");

		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get("httpCode"));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get("httpCode"),statusValue, driver, sTestcaseName);

		loginapiresponse.put("videoId", uploadvidoeapiresponse.get("videoId"));
		apibeanfactory.EditVideoBean(adduploadvideobean1);
		adduploadvideobean1.setDescription(IUploadVideoService.updateAPIUpdateVideoDesc);
		adduploadvideobean1.setTitle(adduploadvideobean.getTitle());
		
		editvidoeapiresponse = uploadvidoeservice.editVideo(loginapiresponse, adduploadvideobean1);
		logger.info("UploadVideo API response Code :::" + editvidoeapiresponse.get("httpCode"));
		assertionapiresponse.verifyAssert_httpCode(editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"), statusValue, driver,sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.clickVideoBasicinformationButton();
		customReport.customizedReport(adduploadvideobean1.getDescription(),
		commentspage.verify_videodescription(adduploadvideobean1.getDescription()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = "Editing Video Status from true to false Existing Uploaded",groups = {EDITVIDEOAPI})
	public void TC_03_PUT_EditVideosAPI_AV10124_ValidData_With_VideoStatusTrueToFalse(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setEnableDownloads("true");
		adduploadvideobean.setEnableRatings("true");
		adduploadvideobean.setIsActive("true");
		adduploadvideobean.setVideoAccessControl("Private");

		// GroupIds UI for Edit Access Control
		String groupName = create_groupfromUI();
		adduploadvideobean.setAccesscontrolforgroup(groupName);

		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,driver, sTestcaseName);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");

		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get("httpCode"));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get("httpCode"),statusValue, driver, sTestcaseName);
		adduploadvideobean.setIsActive("false");

		loginapiresponse.put("videoId", uploadvidoeapiresponse.get("videoId"));

		editvidoeapiresponse = uploadvidoeservice.editVideo(loginapiresponse, adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + editvidoeapiresponse.get("httpCode"));
		assertionapiresponse.verifyAssert_httpCode(editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"), statusValue, driver,sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());	
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
		customReport.customizedReport(adduploadvideobean.getIsActive().equals("false"),avengereditvideosettingspage.get_VideoStatus().equals("Inactive"), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = "Editing Video Status from false to true Existing Uploaded",groups = {EDITVIDEOAPI})
	public void TC_04_PUT_EditVideosAPI_AV10124_ValidData_With_VideoStatusFalseToTrue(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setEnableDownloads("true");
		adduploadvideobean.setEnableRatings("true");
		adduploadvideobean.setIsActive("true");
		adduploadvideobean.setVideoAccessControl("Private");

		// GroupIds UI for Edit Access Control
		String groupName = create_groupfromUI();
		adduploadvideobean.setAccesscontrolforgroup(groupName);

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,driver, sTestcaseName);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");

		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get("httpCode"));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get("httpCode"),statusValue, driver, sTestcaseName);
		adduploadvideobean.setIsActive("true");

		loginapiresponse.put("videoId", uploadvidoeapiresponse.get("videoId"));
		editvidoeapiresponse = uploadvidoeservice.editVideo(loginapiresponse, adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + editvidoeapiresponse.get("httpCode"));
		assertionapiresponse.verifyAssert_httpCode(editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"), statusValue, driver,sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();	
		customReport.customizedReport(adduploadvideobean.getIsActive().equals("true"),avengereditvideosettingspage.get_VideoStatus().equals("Active"), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = "Editing Video Comments from Enable to Disable on  Existing Uploaded Video ",groups = {EDITVIDEOAPI})
	public void TC_05_PUT_EditVideosAPI_AV10124_ValidData_With_VideoFeatures_CommentsEnableToDisbale(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setEnableDownloads("true");
		adduploadvideobean.setEnableRatings("true");
		adduploadvideobean.setIsActive("true");
		adduploadvideobean.setVideoAccessControl("Private");

		// GroupIds UI for Edit Access Control
		String groupName = create_groupfromUI();
		adduploadvideobean.setAccesscontrolforgroup(groupName);

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,driver, sTestcaseName);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");

		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get("httpCode"));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get("httpCode"),statusValue, driver, sTestcaseName);
		adduploadvideobean.setEnableComments("false");

		loginapiresponse.put("videoId", uploadvidoeapiresponse.get("videoId"));
		editvidoeapiresponse = uploadvidoeservice.editVideo(loginapiresponse, adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + editvidoeapiresponse.get("httpCode"));
		assertionapiresponse.verifyAssert_httpCode(editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"), statusValue, driver,sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();		
		customReport.customizedReport(false,avengereditvideosettingspage.verifyEnableCommentsSelected().contains("active"), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = "Editing Video Comments from Enable to Disable on  Existing Uploaded Video ",groups = {EDITVIDEOAPI})
	public void TC_06_PUT_EditVideosAPI_AV10124_ValidData_With_VideoFeatures_CommentsDiableToEnable(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments("false");
		adduploadvideobean.setEnableDownloads("true");
		adduploadvideobean.setEnableRatings("true");
		adduploadvideobean.setIsActive("true");
		adduploadvideobean.setVideoAccessControl("Private");

		// GroupIds UI for Edit Access Control
		String groupName = create_groupfromUI();
		adduploadvideobean.setAccesscontrolforgroup(groupName);

		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,driver, sTestcaseName);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");

		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get("httpCode"));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get("httpCode"),statusValue, driver, sTestcaseName);

		adduploadvideobean.setEnableComments("true");
		loginapiresponse.put("videoId", uploadvidoeapiresponse.get("videoId"));
		editvidoeapiresponse = uploadvidoeservice.editVideo(loginapiresponse, adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + editvidoeapiresponse.get("httpCode"));
		assertionapiresponse.verifyAssert_httpCode(editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"), statusValue, driver,sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();		
		customReport.customizedReport(true,avengereditvideosettingspage.verifyEnableCommentsSelected().contains("checked"), statusValue, driver,sTestcaseName);	
		customReport.checkinglist(statusValue);
	}

	@Test(description = "Editing Video downloads from Enable to Disable on  Existing Uploaded Video  ",groups = {EDITVIDEOAPI})
	public void TC_07_PUT_EditVideosAPI_AV10124_ValidData_With_VidoeFeatures_DownloadEnableToDisable(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setEnableDownloads("true");
		adduploadvideobean.setEnableRatings("true");
		adduploadvideobean.setIsActive("true");
		adduploadvideobean.setVideoAccessControl("Private");

		// GroupIds UI for Edit Access Control
		String groupName = create_groupfromUI();
		adduploadvideobean.setAccesscontrolforgroup(groupName);

		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,driver, sTestcaseName);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");

		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get("httpCode"));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get("httpCode"),statusValue, driver, sTestcaseName);

		adduploadvideobean.setEnableDownloads("false");
		loginapiresponse.put("videoId", uploadvidoeapiresponse.get("videoId"));
		editvidoeapiresponse = uploadvidoeservice.editVideo(loginapiresponse, adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + editvidoeapiresponse.get("httpCode"));
		assertionapiresponse.verifyAssert_httpCode(editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"), statusValue, driver,sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();	
		customReport.customizedReport(false,avengereditvideosettingspage.verifyEnabledownloadsSelected().contains("active"), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = "Editing Video Download from Disable to Enable on  Existing Uploaded Video",groups = {EDITVIDEOAPI})
	public void TC_08_PUT_EditdVideosAPI_AV10124_ValidData_With_VidoeFeatures_DownloadDisableToEnable(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setEnableDownloads("true");
		adduploadvideobean.setEnableRatings("true");
		adduploadvideobean.setIsActive("true");
		adduploadvideobean.setVideoAccessControl("Private");

		// GroupIds UI for Edit Access Control
		String groupName = create_groupfromUI();
		adduploadvideobean.setAccesscontrolforgroup(groupName);

		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,driver, sTestcaseName);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");

		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get("httpCode"));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get("httpCode"),statusValue, driver, sTestcaseName);

		adduploadvideobean.setEnableDownloads("true");
		loginapiresponse.put("videoId", uploadvidoeapiresponse.get("videoId"));
		editvidoeapiresponse = uploadvidoeservice.editVideo(loginapiresponse, adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + editvidoeapiresponse.get("httpCode"));
		assertionapiresponse.verifyAssert_httpCode(editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"), statusValue, driver,sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();		
		customReport.customizedReport(adduploadvideobean.getEnableDownloads().equals("true"),avengereditvideosettingspage.verifyEnabledownloadsSelected().contains("checked"), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = "Editing Video Rating from enable to Disable on  Existing Uploaded Video",groups = {EDITVIDEOAPI})
	public void TC_09_PUT_EditVideosAPI_AV10124_ValidData_With_VidoeFeatures_RatingsEnableToDisable(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();

		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setEnableDownloads("true");
		adduploadvideobean.setEnableRatings("true");
		adduploadvideobean.setIsActive("true");
		adduploadvideobean.setVideoAccessControl("Private");

		// GroupIds UI for Edit Access Control
		String groupName = create_groupfromUI();
		adduploadvideobean.setAccesscontrolforgroup(groupName);

		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,driver, sTestcaseName);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");

		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get("httpCode"));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get("httpCode"),statusValue, driver, sTestcaseName);

		adduploadvideobean.setEnableRatings("false");
		loginapiresponse.put("videoId", uploadvidoeapiresponse.get("videoId"));
		editvidoeapiresponse = uploadvidoeservice.editVideo(loginapiresponse, adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + editvidoeapiresponse.get("httpCode"));
		assertionapiresponse.verifyAssert_httpCode(editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"), statusValue, driver,sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
		customReport.customizedReport(false,avengereditvideosettingspage.verifyEnableRatingsSelected().contains("active"), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = "Editing Video Rating from Disable to enable on  Existing Uploaded Video",groups = {EDITVIDEOAPI})
	public void TC_10_PUT_EditVideosAPI_AV10124_ValidData_With_VidoeFeatures_RatingsDiableToEnable(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setEnableDownloads("true");
		adduploadvideobean.setEnableRatings("false");
		adduploadvideobean.setIsActive("true");
		adduploadvideobean.setVideoAccessControl("Private");

		// GroupIds UI for Edit Access Control
		String groupName = create_groupfromUI();
		adduploadvideobean.setAccesscontrolforgroup(groupName);

		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,driver, sTestcaseName);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");

		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get("httpCode"));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get("httpCode"),statusValue, driver, sTestcaseName);

		adduploadvideobean.setEnableRatings("true");
		loginapiresponse.put("videoId", uploadvidoeapiresponse.get("videoId"));
		editvidoeapiresponse = uploadvidoeservice.editVideo(loginapiresponse, adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + editvidoeapiresponse.get("httpCode"));
		assertionapiresponse.verifyAssert_httpCode(editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"), statusValue, driver,sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
		customReport.customizedReport(adduploadvideobean.getEnableRatings().equals("true"),avengereditvideosettingspage.verifyEnableRatingsSelected().contains("checked"), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	// @@ACCESS CONTROL
	@Test(description = "Editing Video AccessControl from Public to ALLUser on  Existing Uploaded Video' ",groups = {EDITVIDEOAPI})
	public void TC_11_PUT_EditVideosAPI_AV10124_ValidData_With_VidoeACcessControlFromPublicToALLUSER(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setEnableDownloads("true");
		adduploadvideobean.setEnableRatings("true");
		adduploadvideobean.setIsActive("true");
		adduploadvideobean.setVideoAccessControl("Public");

		// GroupIds UI for Edit Access Control
		String groupName = create_groupfromUI();
		adduploadvideobean.setAccesscontrolforgroup(groupName);

		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,driver, sTestcaseName);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");

		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get("httpCode"));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get("httpCode"),statusValue, driver, sTestcaseName);

		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[4]);
		loginapiresponse.put("videoId", uploadvidoeapiresponse.get("videoId"));
		editvidoeapiresponse = uploadvidoeservice.editVideo(loginapiresponse, adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + editvidoeapiresponse.get("httpCode"));
		assertionapiresponse.verifyAssert_httpCode(editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"), statusValue, driver,sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
		customReport.customizedReport("True",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[5]),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = "Videos uploaded API with metadata associated to it based on IsNotEnableRatings,If IsNotEnableRatings value is provided: video description is set to 'Not EnabledDownload' specified  ",groups = {EDITVIDEOAPI})
	public void TC_12_PUT_EditVideosAPI_AV10124_ValidData_With_VidoeACcessControlAsPublicToPrivate(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setEnableDownloads("true");
		adduploadvideobean.setEnableRatings("true");
		adduploadvideobean.setIsActive("true");
		//adduploadvideobean.setVideoAccessControl("Public");
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);
		
		// GroupIds UI for Edit Access Control
		String groupName = create_groupfromUI();
		adduploadvideobean.setAccesscontrolforgroup(groupName);

		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,driver, sTestcaseName);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");

		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get("httpCode"));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get("httpCode"),statusValue, driver, sTestcaseName);

		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);

		
		loginapiresponse.put("videoId", uploadvidoeapiresponse.get("videoId"));
		editvidoeapiresponse = uploadvidoeservice.editVideo(loginapiresponse, adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + editvidoeapiresponse.get("httpCode"));
		assertionapiresponse.verifyAssert_httpCode(editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"), statusValue, driver,sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
		customReport.customizedReport("false",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[0]),statusValue, driver, sTestcaseName);
		customReport.customizedReport("false",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[5]),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = "Videos uploaded API with metadata associated to it based on IsNotEnableRatings,If IsNotEnableRatings value is provided: video description is set to 'Not EnabledDownload' specified  ",groups = {EDITVIDEOAPI})
	public void TC_13_PUT_EditVideos_AV10124_ValidData_With_VidoeACcessControlPrivateToPublic() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setEnableDownloads("true");
		adduploadvideobean.setEnableRatings("true");
		adduploadvideobean.setIsActive("true");
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);

		// GroupIds UI for Edit Access Control
		String groupName = create_groupfromUI();
		adduploadvideobean.setAccesscontrolforgroup(groupName);

		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,driver, sTestcaseName);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");

		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get("httpCode"));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get("httpCode"),statusValue, driver, sTestcaseName);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);

		loginapiresponse.put("videoId", uploadvidoeapiresponse.get("videoId"));

		editvidoeapiresponse = uploadvidoeservice.editVideo(loginapiresponse, adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + editvidoeapiresponse.get("httpCode"));
		assertionapiresponse.verifyAssert_httpCode(editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"), statusValue, driver,sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();		
		customReport.customizedReport("true",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[0]),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = "Videos uploaded Download' specified  ",groups = {EDITVIDEOAPI})
	public void TC_14_PUT_EditVideosAPI_AV10124_ValidData_With_MutipleTags_EditTheTag() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);

		// GroupIds UI for Edit Access Control
		String groupName = create_groupfromUI();
		adduploadvideobean.setAccesscontrolforgroup(groupName);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,driver, sTestcaseName);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");

		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get("httpCode"));
		Assert.assertEquals(uploadvidoeapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();	
		for (int i = 0; i < avengereditvideosettingspage.get_AllTags().size(); i++) {
        customReport.customizedReport(adduploadvideobean.getTags()[i],avengereditvideosettingspage.get_AllTags().get(i), statusValue, driver, sTestcaseName);
		}
		customReport.checkinglist(statusValue);
	}
	
	/*Adding this case as part of AV-18898....just to check for only mandatory fields...eventhough title is the only mandatory field
	giving 'isactive' and 'videoaccesscontrol' as well because if we don't give these 2 fields video will become inactive by default and 
	videoaccesscontrol will become private. Videoaccess control can be skipped if we want video to be private and similarly 'isactive'
	if we want video to be inactive*/
	
			
	@Test(description = "Editing video access control from public to private with only mandatory fields",groups = {EDITVIDEOAPI})
	public void TC_15_PUT_EditVideosAPI_AV10124_mandatoryfields_EditAccesscontrol_fromPublictoPrivate_AV18898(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setEnableDownloads("true");
		adduploadvideobean.setEnableRatings("true");
		adduploadvideobean.setIsActive("true");

		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);	
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,driver, sTestcaseName);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");

		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get("httpCode"));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get("httpCode"),statusValue, driver, sTestcaseName);

		loginapiresponse.put("videoId", uploadvidoeapiresponse.get("videoId"));
		
		if (!adduploadvideobean.getTitle().equals("") || adduploadvideobean.getTitle() != null)
		adduploadvideobean.setTitle(adduploadvideobean.getTitle()+"Changed");	
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);
		adduploadvideobean.setIsActive("true");
		String accesscontrolforuser = IUsersList.EditVideosmediaviewer;
		adduploadvideobean.setAccesscontrolforuser(accesscontrolforuser);		
		adduploadvideobean.setUploader("");
		adduploadvideobean.setDescription("");
		adduploadvideobean.setEnableComments("");
		adduploadvideobean.setEnableDownloads("");
		adduploadvideobean.setEnableRatings("");
		adduploadvideobean.setCategories(null);
		adduploadvideobean.setCategoryIds(null);
		adduploadvideobean.setTags(null);
		
		editvidoeapiresponse = uploadvidoeservice.editVideo(loginapiresponse, adduploadvideobean);
		logger.info("UploadVideoEdit API response Code :::" + editvidoeapiresponse.get("httpCode"));
		assertionapiresponse.verifyAssert_httpCode(editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"), statusValue, driver,sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
		customReport.customizedReport("false",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[0]),statusValue, driver, sTestcaseName);
		customReport.customizedReport("false",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[5]),statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduploadvideobean.getIsActive().equals("true"),avengereditvideosettingspage.get_VideoStatus().equals("Active"), statusValue, driver,sTestcaseName);
		customReport.customizedReport("apimvu3 apimvu3",commentspage.verify_Label1(accesscontrolforuser), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "Editing video access control from private to public with mandatory fields with Media Admin",groups = {EDITVIDEOAPI})
	public void TC_16_PUT_EditVideosAPI_AV10124_fromPrivatetoPublic_EditAccesscontrol_MediaAdmin_AV18898(ITestContext context) throws InterruptedException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.EditVideosmediaadmin);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setEnableDownloads("true");
		adduploadvideobean.setEnableRatings("true");
		adduploadvideobean.setIsActive("true");
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);
		String accesscontrolforuser = IUsersList.EditVideosmediaviewer;
		adduploadvideobean.setAccesscontrolforuser(accesscontrolforuser);

		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.EditVideosmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,driver, sTestcaseName);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");

		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get("httpCode"));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get("httpCode"),statusValue, driver, sTestcaseName);

		loginapiresponse.put("videoId", uploadvidoeapiresponse.get("videoId"));
		
		if (!adduploadvideobean.getTitle().equals("") || adduploadvideobean.getTitle() != null)
		adduploadvideobean.setTitle(adduploadvideobean.getTitle()+"changed");
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);
		adduploadvideobean.setIsActive("true");	
		adduploadvideobean.setUploader("");
		adduploadvideobean.setDescription("");
		adduploadvideobean.setEnableComments("");
		adduploadvideobean.setEnableDownloads("");
		adduploadvideobean.setEnableRatings("");
		adduploadvideobean.setCategories(null);
		adduploadvideobean.setCategoryIds(null);
		adduploadvideobean.setTags(null);
		
		editvidoeapiresponse = uploadvidoeservice.editVideo(loginapiresponse, adduploadvideobean);
		logger.info("UploadVideoEdit API response Code :::" + editvidoeapiresponse.get("httpCode"));
		assertionapiresponse.verifyAssert_httpCode(editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"), statusValue, driver,sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		Thread.sleep(10000);
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
		customReport.customizedReport("true",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[0]),statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduploadvideobean.getIsActive().equals("true"),avengereditvideosettingspage.get_VideoStatus().equals("Active"), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "Editing only description with mandatory fields and with Media Admin",groups = {EDITVIDEOAPI})
	public void TC_17_PUT_EditVideosAPI_AV10124_editDescription_MediaAdmin_AV18898(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.EditVideosmediaadmin);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setEnableDownloads("true");
		adduploadvideobean.setEnableRatings("true");
		adduploadvideobean.setIsActive("true");

		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);
		String accesscontrolforuser = IUsersList.EditVideosmediaviewer;
		adduploadvideobean.setAccesscontrolforuser(accesscontrolforuser);

		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.EditVideosmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,driver, sTestcaseName);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");

		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get("httpCode"));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get("httpCode"),statusValue, driver, sTestcaseName);

		loginapiresponse.put("videoId", uploadvidoeapiresponse.get("videoId"));	
		adduploadvideobean.setIsActive("true");	
		adduploadvideobean.setUploader("");
		adduploadvideobean.setDescription(adduploadvideobean.getDescription()+"changed");
		adduploadvideobean.setEnableComments("");
		adduploadvideobean.setEnableDownloads("");
		adduploadvideobean.setEnableRatings("");
		adduploadvideobean.setCategories(null);
		adduploadvideobean.setCategoryIds(null);
		adduploadvideobean.setTags(null);
		
		editvidoeapiresponse = uploadvidoeservice.editVideo(loginapiresponse, adduploadvideobean);
		logger.info("UploadVideoEdit API response Code :::" + editvidoeapiresponse.get("httpCode"));
		assertionapiresponse.verifyAssert_httpCode(editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"), statusValue, driver,sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
		customReport.customizedReport("false",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[0]),statusValue, driver, sTestcaseName);
		customReport.customizedReport("false",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[5]),statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduploadvideobean.getIsActive().equals("true"),avengereditvideosettingspage.get_VideoStatus().equals("Active"), statusValue, driver,sTestcaseName);
		customReport.customizedReport(adduploadvideobean.getDescription(), commentspage.verify_videodescription1(adduploadvideobean.getDescription()), statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
			
	}


	@Test(description = "Editing only publish date with mandatory fields and with Media COntributor",groups = {EDITVIDEOAPI})
	public void TC_18_PUT_EditVideosAPI_AV10124_editPublishDate_MediaContributor_AV18898(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.EditVideosmediacontributor);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setEnableDownloads("true");
		adduploadvideobean.setEnableRatings("true");
		
		//As we see publish date only when the video is inactive this is made false
		adduploadvideobean.setIsActive("false"); 	
		
		//to get the current date
		String currDate = DateTime.yyyyMMDD();
	 
		adduploadvideobean.setPublishDate(currDate);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);
		String accesscontrolforuser = IUsersList.EditVideosmediaviewer;
		adduploadvideobean.setAccesscontrolforuser(accesscontrolforuser);

		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.EditVideosmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,driver, sTestcaseName);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");

		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get("httpCode"));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get("httpCode"),statusValue, driver, sTestcaseName);

		loginapiresponse.put("videoId", uploadvidoeapiresponse.get("videoId"));	
		adduploadvideobean.setUploader("");
		adduploadvideobean.setEnableComments("");
		adduploadvideobean.setEnableDownloads("");
		
		//to add 1 day to the current date      
        String  updatedDate = DateTime.addDaysToSystemDate1(1);       
		adduploadvideobean.setPublishDate(updatedDate);
		adduploadvideobean.setExpirationAction("");
		adduploadvideobean.setExpirationDate("");
		adduploadvideobean.setWhenUploaded("");		
		adduploadvideobean.setEnableRatings("");
		adduploadvideobean.setCategories(null);
		adduploadvideobean.setCategoryIds(null);
		adduploadvideobean.setTags(null);
		
		editvidoeapiresponse = uploadvidoeservice.editVideo(loginapiresponse, adduploadvideobean);
		logger.info("UploadVideoEdit API response Code :::" + editvidoeapiresponse.get("httpCode"));
		assertionapiresponse.verifyAssert_httpCode(editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"), statusValue, driver,sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
		String calActDate = avengereditvideosettingspage.getPublishDate1();
		customReport.customizedReport("false",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[0]),statusValue, driver, sTestcaseName);
		customReport.customizedReport("false",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[5]),statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduploadvideobean.getIsActive().equals("true"),avengereditvideosettingspage.get_VideoStatus().equals("Active"), statusValue, driver,sTestcaseName);
		customReport.customizedReport(updatedDate,calActDate, statusValue, driver,sTestcaseName);
		customReport.checkinglist(statusValue);
	
	}
	
	@Test(description = "Editing only comments from disable to enable with mandatory fields and with Account Admin",groups = {EDITVIDEOAPI})
	public void TC_19_PUT_EditVideosAPI_AV10124_editCommentsfromdisabletoenable_AccountAdmin_AV18898(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments("false");
		adduploadvideobean.setEnableDownloads("true");
		adduploadvideobean.setEnableRatings("true");
		adduploadvideobean.setIsActive("true"); 
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);

		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,driver, sTestcaseName);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");

		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get("httpCode"));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get("httpCode"),statusValue, driver, sTestcaseName);

		loginapiresponse.put("videoId", uploadvidoeapiresponse.get("videoId"));
		
		adduploadvideobean.setUploader("");
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setEnableDownloads("");	
		adduploadvideobean.setEnableRatings("");
		adduploadvideobean.setCategories(null);
		adduploadvideobean.setCategoryIds(null);
		adduploadvideobean.setTags(null);
		
		editvidoeapiresponse = uploadvidoeservice.editVideo(loginapiresponse, adduploadvideobean);
		logger.info("UploadVideoEdit API response Code :::" + editvidoeapiresponse.get("httpCode"));
		assertionapiresponse.verifyAssert_httpCode(editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"), statusValue, driver,sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
		customReport.customizedReport("True",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[0]),statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduploadvideobean.getIsActive().equals("true"),avengereditvideosettingspage.get_VideoStatus().equals("Active"), statusValue, driver,sTestcaseName);
		customReport.customizedReport(adduploadvideobean.getEnableComments().equals("true"), avengereditvideosettingspage.verifyEnableCommentsSelected().contains("checked"),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = "Editing only ratings from enable to disable with mandatory fields and with Media Admin",groups = {EDITVIDEOAPI})
	public void TC_20_PUT_EditVideosAPI_AV10124_editRatingsfromenabletodisable_MediaAdmin_AV18898(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.EditVideosmediaadmin);
		adduploadvideobean.setEnableComments("false");
		adduploadvideobean.setEnableDownloads("true");
		adduploadvideobean.setEnableRatings("true");
		adduploadvideobean.setIsActive("true"); 
		
		//API accepts only 'AllUsers' i.e without space and UI has 'All Users' i.e with space
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[4]);

		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.EditVideosmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,driver, sTestcaseName);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");

		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get("httpCode"));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get("httpCode"),statusValue, driver, sTestcaseName);

		loginapiresponse.put("videoId", uploadvidoeapiresponse.get("videoId"));		
		adduploadvideobean.setUploader("");
		adduploadvideobean.setEnableComments("");
		adduploadvideobean.setEnableDownloads("");
		adduploadvideobean.setEnableRatings("false");
		adduploadvideobean.setCategories(null);
		adduploadvideobean.setCategoryIds(null);
		adduploadvideobean.setTags(null);
		
		editvidoeapiresponse = uploadvidoeservice.editVideo(loginapiresponse, adduploadvideobean);
		logger.info("UploadVideoEdit API response Code :::" + editvidoeapiresponse.get("httpCode"));
		assertionapiresponse.verifyAssert_httpCode(editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"), statusValue, driver,sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
		customReport.customizedReport("True",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[5]),statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduploadvideobean.getIsActive().equals("true"),avengereditvideosettingspage.get_VideoStatus().equals("Active"), statusValue, driver,sTestcaseName);
		customReport.customizedReport(adduploadvideobean.getEnableRatings().equals("true"), avengereditvideosettingspage.verifyEnableRatingsSelected().contains("active"),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	
	@Test(description = "Editing downloads from disable to enable for private video with mandatory fields and with Media Contributor",groups = {EDITVIDEOAPI})
	public void TC_21_PUT_EditVideosAPI_AV10124_editDownloadsfromdisabletoenable_MediaContributor_mandatoryfields_Positive(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.EditVideosmediacontributor);
		adduploadvideobean.setEnableComments("false");
		adduploadvideobean.setEnableDownloads("false");
		adduploadvideobean.setEnableRatings("true");
		adduploadvideobean.setIsActive("true"); 		
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);

		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.EditVideosmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,driver, sTestcaseName);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.EditVideosmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get("httpCode"), statusValue,driver, sTestcaseName);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");

		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get("httpCode"));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get("httpCode"),statusValue, driver, sTestcaseName);

		loginapiresponse1.put("videoId", uploadvidoeapiresponse.get("videoId"));		
		adduploadvideobean.setUploader("");
		adduploadvideobean.setEnableComments("");
		adduploadvideobean.setEnableDownloads("true");
		adduploadvideobean.setEnableRatings("");
		adduploadvideobean.setCategories(null);
		adduploadvideobean.setCategoryIds(null);
		adduploadvideobean.setTags(null);
		
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);
		adduploadvideobean.setAccesscontrolforuser(IUsersList.EditVideosmediaviewer);
		
		editvidoeapiresponse = uploadvidoeservice.editVideo(loginapiresponse1, adduploadvideobean);
		logger.info("UploadVideoEdit API response Code :::" + editvidoeapiresponse.get("httpCode"));
		assertionapiresponse.verifyAssert_httpCode(editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"), statusValue, driver,sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();		
		customReport.customizedReport("false",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[0]),statusValue, driver, sTestcaseName);
		customReport.customizedReport("false",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[5]),statusValue, driver, sTestcaseName);
		customReport.customizedReport(adduploadvideobean.getIsActive().equals("true"),avengereditvideosettingspage.get_VideoStatus().equals("Active"), statusValue, driver,sTestcaseName);
		customReport.customizedReport(adduploadvideobean.getEnableDownloads().equals("true"), avengereditvideosettingspage.verifyEnabledownloadsSelected().contains("checked"),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	
	@Test(description = "Editing video access control from AllUsers to private",groups = {EDITVIDEOAPI})
	public void TC_22_PUT_EditVideosAPI_AV10124_ValidData_With_EditAccesscontrol_AV18898(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {	}.getClass().getEnclosingMethod().getName();
	
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setEnableDownloads("true");
		adduploadvideobean.setEnableRatings("true");
		adduploadvideobean.setIsActive("true");
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[2]);

		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,driver, sTestcaseName);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");

		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get("httpCode"));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get("httpCode"),statusValue, driver, sTestcaseName);

		loginapiresponse.put("videoId", uploadvidoeapiresponse.get("videoId"));
		if (!adduploadvideobean.getTitle().equals("") || adduploadvideobean.getTitle() != null)
			adduploadvideobean.setTitle(adduploadvideobean.getTitle());
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);
		adduploadvideobean.setIsActive("true");
		
		editvidoeapiresponse = uploadvidoeservice.editVideo(loginapiresponse, adduploadvideobean);
		logger.info("UploadVideoEdit API response Code :::" + editvidoeapiresponse.get("httpCode"));
		assertionapiresponse.verifyAssert_httpCode(editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"), statusValue, driver,sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
		customReport.customizedReport("false",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[0]),statusValue, driver, sTestcaseName);
		customReport.customizedReport("false",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[5]),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = "Editing video access control from AllUsers to public",groups = {EDITVIDEOAPI})
	public void TC_23_PUT_EditVideosAPI_AV10124_ValidData_With_EditAccesscontrol_AV18898(ITestContext context) throws InterruptedException {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setEnableDownloads("true");
		adduploadvideobean.setEnableRatings("true");
		adduploadvideobean.setIsActive("true");
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[2]);

		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,driver, sTestcaseName);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");

		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get("httpCode"));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get("httpCode"),statusValue, driver, sTestcaseName);

		loginapiresponse.put("videoId", uploadvidoeapiresponse.get("videoId"));
		if (!adduploadvideobean.getTitle().equals("") || adduploadvideobean.getTitle() != null)
			adduploadvideobean.setTitle(adduploadvideobean.getTitle());
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);
		adduploadvideobean.setIsActive("true");
		
		editvidoeapiresponse = uploadvidoeservice.editVideo(loginapiresponse, adduploadvideobean);
		logger.info("UploadVideoEdit API response Code :::" + editvidoeapiresponse.get("httpCode"));
		assertionapiresponse.verifyAssert_httpCode(editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"), statusValue, driver,sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		Thread.sleep(10000);
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
		customReport.customizedReport("True",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[0]),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = "Editing video access control from Private to AllUsers",groups = {EDITVIDEOAPI})
	public void TC_24_PUT_EditVideosAPI_AV10124_ValidData_With_EditAccesscontrol_AV18898(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();	

		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setEnableDownloads("true");
		adduploadvideobean.setEnableRatings("true");
		adduploadvideobean.setIsActive("true");
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);

		// GroupIds UI for Edit Access Control
		String groupName = create_groupfromUI();
		adduploadvideobean.setAccesscontrolforgroup(groupName);
		adduploadvideobean.setAccesscontrolforuser(IUsersList.EditVideosmediaviewer);

		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,driver, sTestcaseName);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");

		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get("httpCode"));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get("httpCode"),statusValue, driver, sTestcaseName);

		loginapiresponse.put("videoId", uploadvidoeapiresponse.get("videoId"));
		apibeanfactory.EditVideoBean(adduploadvideobean1);
		adduploadvideobean1.setTitle(adduploadvideobean.getTitle());
		adduploadvideobean1.setVideoAccessControl(IUploadVideoService.videoAccessControl[4]);
		adduploadvideobean1.setIsActive("true");
		adduploadvideobean1.setAccesscontrolforuser(IUsersList.EditVideosmediaadmin);
		
		editvidoeapiresponse = uploadvidoeservice.editVideo(loginapiresponse, adduploadvideobean1);
		logger.info("UploadVideoEdit API response Code :::" + editvidoeapiresponse.get("httpCode"));
		assertionapiresponse.verifyAssert_httpCode(editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,editvidoeapiresponse.get("httpCode") + editvidoeapiresponse.get("statusInfo"), statusValue, driver,sTestcaseName);

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean1.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean1.getTitle());
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
		customReport.customizedReport("True",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[5]),statusValue, driver, sTestcaseName);
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

	public String create_groupfromUI() {

		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		homePage.clickSettingsLink();
		avengergroupspage = homePage.clickGroupsLink();
		String grpname = avengergroupspage.createNewgroup(avengergroupsbeanpage);
		avengergroupsbeanpage.setNewgroup(grpname);
		avengergroupspage.clickNewGroup(avengergroupsbeanpage);
		homePage.click_logout();
		return grpname;
	}
	
	public String create_teamfromUI() {

		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		homePage.clickSettingsLink();
		avengerlibrarypage = homePage.click_LibrariesLinkLocator();
		String teamname = avengerlibrarypage.createNewteam(librarybeanpage);
		librarybeanpage.setLibraryname(teamname);
		homePage.click_logout();
		return teamname;
	
	}
	
	public String get_teamidfromUI(String teamname) {

		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		homePage.clickSettingsLink();
		avengerlibrarypage = homePage.click_LibrariesLinkLocator();
		String teamid = avengerlibrarypage.getteamid(teamname);
		homePage.click_logout();
		return teamid;
	
	}

}
