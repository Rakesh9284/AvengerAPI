	package com.vbrick.avenger.uploadvideoapi_AV_6015_testcases;

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
import com.vbrick.avenger.apibeans.AddGroupBean;
import com.vbrick.avenger.apibeans.AddTeamBean;
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbrick.avenger.apibeans.VideoAccessControlBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AvengerEditVideoSettingsBeanPage;
import com.vbrick.avenger.dao.AvengerGroupsBeanPage;
import com.vbrick.avenger.dao.AvengerManageCustomFieldsBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.LibraryBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerEditVideoSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerGroupsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLibrariesPage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerManageCustomFieldsPage;
import com.vbrick.avenger.pageobjects.AvengerSystemSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerUserDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IMegratVideoService;
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.service.IVideoAccessControlService;
import com.vbricks.avenger.serviceimpl.CreateCategoryAPI;
import com.vbricks.avenger.serviceimpl.CreateGroupAPI;
import com.vbricks.avenger.serviceimpl.CreateTeamAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.serviceimpl.VideoAccessControlServiceAPI;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class UploadVideos_AV_6015 extends TestBase {

	private static Logger logger = Logger.getLogger(UploadVideos_AV_6015.class);
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
	private HashMap<String, String> createcategoryapirespone;
	private HashMap<String, String> createcategoryapirespone1;
	private AvengerSystemSettingsPage avengersystemsettingspage;
	private AvengerManageCustomFieldsPage avengercustomefieldspage;
	private HashMap<String, String> createteamapirespone;
	private HashMap<String, String> createteamapirespone1;	
	private AddTeamBean addteambean;
	private AvengerManageCustomFieldsBeanPage customfieldsbean;
	private AvengerEditVideoSettingsBeanPage avengereditvideosettingsbeanpage;
	private HashMap<String, String> creategroupapirespone;
	private AvengerLibrariesPage avengerlibrarypage;
	private AddCategoryBean addcategorybean;
	private ReadAndWriteToJSON readgriduserdata;
	private AddGroupBean addgroupbean;
	private Map<String, String> userdata;
	private BasePage basePage;
	private AddUploadVideoBean adduploadvideobean;
	private LibraryBeanPage librarybeanpage;
	public ApiBeanFactory apibeanfactory;
	private AssertionAPIResponse assertionapiresponse;
	private AvengerGroupsBeanPage avengergroupsbeanpage;
	private AvengerGroupsPage avengergroupspage;
	private HashMap<String, String> loginapiresponse;
	private HashMap<String, String> loginapiresponse1;
	private HashMap<String, String> uploadvidoeapiresponse;
	private VideoAccessControlBean accesscontrolbeagPage;
    private ApiUtils apiutils;
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
		customfieldsbean= new AvengerManageCustomFieldsBeanPage();
		addteambean=new AddTeamBean();
		avengereditvideosettingsbeanpage = new AvengerEditVideoSettingsBeanPage();
		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		accesscontrolbeagPage = new VideoAccessControlBean();
		avengergroupsbeanpage = new AvengerGroupsBeanPage();
		apibeanfactory = new ApiBeanFactory();
		addcategorybean=new AddCategoryBean();
		addgroupbean=new AddGroupBean();
		assertionapiresponse = new AssertionAPIResponse();
		librarybeanpage = new LibraryBeanPage();
		apiutils=new ApiUtils();
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
		accountBeansFactory.customfieldsBean(customfieldsbean);
		accountBeansFactory.AvengerEditVideoSettingsBeanByPage(avengereditvideosettingsbeanpage);
		accountBeansFactory.MailinatorBean(mailinatorbeanpage);
		accountBeansFactory.AvengergroupsBean(avengergroupsbeanpage);
		accountBeansFactory.LibraryBeanByPage(librarybeanpage);

	}
	
	@Test(description = "Upload a video with Video upload API with only mandatory fields and with account admin role", groups = {UPLOADVIDEOAPI})			
	public void TC_01_UploadVideo_AccountAdmin_With_Mandatoryfield_Uploader_Positive(ITestContext context) {
	
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
		loginapiresponse.put("Mandatory", "Yes");
		loginapiresponse.put("fileName", "Yes");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
			
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(uploadvidoeapiresponse.get("videoTitle"));
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(uploadvidoeapiresponse.get("videoTitle"));
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(uploadvidoeapiresponse.get("videoTitle")),statusValue, driver, sTestcaseName);
		commentspage.click_settingsLink();
		commentspage.click_deleteButton();
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description = "Upload a video with Video upload API with only mandatory fields and with media admin role",groups = {UPLOADVIDEOAPI}) 		
	public void TC_02_UploadVideoic_MediaAdmin_With_Mandatoryfield_Uploader_Positive(ITestContext context) {
	
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UploadVideosmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
 		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.UploadVideosmediaadmin);
		adduploadvideobean.setDescription("");
		adduploadvideobean.setEnableComments("");
		adduploadvideobean.setEnableDownloads("");
		adduploadvideobean.setEnableRatings("");
		adduploadvideobean.setIsActive("");
		adduploadvideobean.setVideoAccessControl(null);
		adduploadvideobean.setCategories(null);
		adduploadvideobean.setCategoryIds(null);
		adduploadvideobean.setTags(null);
		adduploadvideobean.setTitle("");		
		loginapiresponse.put("Mandatory", "Yes");
		loginapiresponse.put("fileName", "Yes");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);	
		
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(uploadvidoeapiresponse.get("videoTitle"));
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(uploadvidoeapiresponse.get("videoTitle"));
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(uploadvidoeapiresponse.get("videoTitle")),statusValue, driver, sTestcaseName);
		commentspage.click_settingsLink();
		commentspage.click_deleteButton();
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description = "Upload a video with Video upload API with only mandatory fields and with media contributor role",groups = {UPLOADVIDEOAPI}) 			
	public void TC_03_UploadVideo_MediaContributor_With_Mandatoryfield_Uploader_Positive(ITestContext context) {
	
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UploadVideosmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
 		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.UploadVideosmediacontributor);
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
		loginapiresponse.put("Mandatory", "Yes");
		loginapiresponse.put("fileName", "Yes");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
				
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(uploadvidoeapiresponse.get("videoTitle"));
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(uploadvidoeapiresponse.get("videoTitle"));
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(uploadvidoeapiresponse.get("videoTitle")),statusValue, driver, sTestcaseName);
		commentspage.click_settingsLink();
		commentspage.click_deleteButton();
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description = "Upload a video with Video upload API with only mandatory fields and with event admin role", groups = {UPLOADVIDEOAPI})			
	public void TC_04_UploadVideo_EventAdmin_With_Mandatoryfield_Uploader_Negative(ITestContext context) {
	
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UploadVideoseventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
 		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.UploadVideoseventadmin);
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
		loginapiresponse.put("Mandatory", "Yes");
		loginapiresponse.put("fileName", "Yes");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode401);
		customReport.customizedReport( HttpStatusCode.httpsStatusCode401, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description = "Upload a video with Video upload API with only mandatory fields and with event host when assigned to a group having media contributor role", groups = {UPLOADVIDEOAPI})			
	public void TC_05_UploadVideo_eventhost_assignedtoMCgroup_With_Mandatoryfield_Uploader_Positive(ITestContext context) {
	
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();	
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UploadVideoseventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		//adding the event host user to a group		
		//getting the userIds
		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		String[] userIds = {loginapiresponse.get("userId")};
		addgroupbean.setUserids(userIds);
						
		//getting role ids
		UserServices userServices=new UserServices();
	    HashMap<String, String> userroleapirespone = userServices.getRolesApi(loginapiresponse1,IAPIConstantCodes.MEDIACONTRIBUTOR);
	    String roleid=apiutils.formatingapiresponse(userroleapirespone.get(IAPIConstantCodes.ROLEID));
	    String[] roleIds = {userroleapirespone.get("roleId")};
		addgroupbean.setRoleids(roleIds);
				
		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
		creategroupapirespone = creategroupAPI.createGroup(loginapiresponse1,creategroupjson);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Upload Video API call through Evnet host after adding into the group		
 		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.UploadVideoseventhost);
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
		loginapiresponse.put("Mandatory", "Yes");
		loginapiresponse.put("fileName", "Yes");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
				
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(uploadvidoeapiresponse.get("videoTitle"));
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(uploadvidoeapiresponse.get("videoTitle"));
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(uploadvidoeapiresponse.get("videoTitle")),statusValue, driver, sTestcaseName);
		commentspage.click_settingsLink();
		commentspage.click_deleteButton();
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description = "Upload a video with Video upload API with only mandatory fields and with Media Viewer role", groups = {UPLOADVIDEOAPI})			
	public void TC_06_UploadVideo_MediaViewer_With_Mandatoryfield_Uploader_Negative(ITestContext context) {
	
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UploadVideosmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
 		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.UploadVideosmediaviewer);
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
		loginapiresponse.put("Mandatory", "Yes");
		loginapiresponse.put("fileName", "Yes");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode401);
		customReport.customizedReport( HttpStatusCode.httpsStatusCode401, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description = "Videos uploaded API with blank video uploader which is a mandatory field ", groups = {UPLOADVIDEOAPI})			
	public void TC_07_UploadVideos_AV6015_ValidData_With_BlankVideoUploader_Negative(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson1(sUserName), surl);
		Assert.assertNotNull(loginapiresponse);
		logger.info("Login API response Code :::" + loginapiresponse);
	    
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUploadVideoService.blankbUploader);
		loginapiresponse.put("Mandatory", "No");
		
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode400,statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		 
	}


	@Test(description = "Verifying Video Upload API with media contributor when title and desc and status is provided", groups = {UPLOADVIDEOAPI})
	public void TC_08_UploadVideo_MediaContributor_checkingtitleanddescriptionandstatus_Positive(ITestContext context) {
	
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UploadVideosmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
 		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.UploadVideosmediacontributor);
		adduploadvideobean.setEnableComments("");
		adduploadvideobean.setEnableDownloads("");
		adduploadvideobean.setEnableRatings("");
		adduploadvideobean.setVideoAccessControl("Public");
		adduploadvideobean.setCategories(null);
		adduploadvideobean.setCategoryIds(null);
		adduploadvideobean.setTags(null);		
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
			
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
		commentspage.clickVideoBasicinformationButton();
		customReport.customizedReport(adduploadvideobean.getDescription(), commentspage.verify_videodescription(adduploadvideobean.getDescription()), statusValue, driver,sTestcaseName);
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
		customReport.customizedReport(adduploadvideobean.getIsActive().equals("true"),avengereditvideosettingspage.get_VideoStatus().equals("Active"), statusValue, driver,sTestcaseName);
		avengereditvideosettingspage.clickCancel();
		commentspage.click_settingsLink();
		commentspage.click_deleteButton();
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description = "Verifying Video Upload API with media admin when status not provided", groups = {UPLOADVIDEOAPI})
	public void TC_09_UploadVideo_MediaAdmin_checkingstatuswhenvaluenotprovided_Positive(ITestContext context) {
	
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UploadVideosmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
 		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.UploadVideosmediaadmin);
		adduploadvideobean.setCategories(null);
		adduploadvideobean.setVideoAccessControl("");
		adduploadvideobean.setCategoryIds(null);
		adduploadvideobean.setIsActive("");	
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
				
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
		customReport.customizedReport(true,avengereditvideosettingspage.get_VideoStatus().equals("Inactive"), statusValue, driver,sTestcaseName);
		avengereditvideosettingspage.clickCancel();
		commentspage.click_settingsLink();
		commentspage.click_deleteButton();
		customReport.checkinglist(statusValue);
		
	}

	@Test(description = "Verifying Video Upload API with account admin for checking category with name which exists in rev", groups = {UPLOADVIDEOAPI})
	public void TC_10_UploadVideo_AccountAdmin_checkingcategorywithname_categoryexistsinrev_Positive(ITestContext context) {
	
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
		adduploadvideobean.setEnableComments("");
		adduploadvideobean.setEnableDownloads("");
		adduploadvideobean.setEnableRatings("");
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);
		adduploadvideobean.setCategories(null);
		adduploadvideobean.setCategoryIds(null);
		adduploadvideobean.setTags(null);	
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
		//to assign a category to video
		CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
			
		String CategoryIds[] = {createcategoryapirespone.get("name")};
		adduploadvideobean.setCategories(CategoryIds);
								
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
				
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
		commentspage.clickVideoBasicinformationButton();
		customReport.customizedReport(true,commentspage.verifycategory(CategoryIds[0]).equalsIgnoreCase(CategoryIds[0]), statusValue, driver,sTestcaseName);
		commentspage.click_settingsLink();
		commentspage.click_deleteButton();
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description = "Verifying Private Video Upload API with Media admin for checking multiple categories with names which exists in rev", groups = {UPLOADVIDEOAPI})
	public void TC_11_UploadVideo_Private_MediaAdmin_checkingmultiplecategorywithname_categoryexistsinrev_Positive(ITestContext context) {
	
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UploadVideosmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
 		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments("");
		adduploadvideobean.setEnableDownloads("");
		adduploadvideobean.setEnableRatings("");
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);
		String accesscontrolforuser = IUsersList.UploadVideosmediaviewer;
		adduploadvideobean.setAccesscontrolforuser(accesscontrolforuser);
		adduploadvideobean.setCategoryIds(null);
		adduploadvideobean.setTags(null);	
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
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
								
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
				
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
		commentspage.clickVideoBasicinformationButton();
		customReport.customizedReport(true,commentspage.verifycategory(CategoryIds[0]).equalsIgnoreCase(CategoryIds[0]), statusValue, driver,sTestcaseName);
		customReport.customizedReport(true,commentspage.verifycategory(CategoryIds[1]).equalsIgnoreCase(CategoryIds[1]), statusValue, driver,sTestcaseName);
		commentspage.click_settingsLink();
		commentspage.click_deleteButton();
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description = "Verifying Private Video Upload API with account admin for checking with multiple categories wit category ids", groups = {UPLOADVIDEOAPI})
	public void TC_12_UploadVideo_Private_AccountAdmin_withmultiplecategoriids_Positive(ITestContext context) {
	
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
		adduploadvideobean.setEnableComments("");
		adduploadvideobean.setEnableDownloads("");
		adduploadvideobean.setEnableRatings("");
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);
		String accesscontrolforuser = IUsersList.UploadVideosmediaviewer;
		adduploadvideobean.setAccesscontrolforuser(accesscontrolforuser);
		adduploadvideobean.setCategories(null);
		adduploadvideobean.setTags(null);		
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
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
		
		String CategoryIds[] = {createcategoryapirespone.get("categoryId"),createcategoryapirespone1.get("categoryId")};
		String CategoryIds1[] = {createcategoryapirespone.get("name"),createcategoryapirespone1.get("name")};
		
		adduploadvideobean.setCategoryIds(CategoryIds);
								
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
		
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
		commentspage.clickVideoBasicinformationButton();
		customReport.customizedReport(true,commentspage.verifycategory(CategoryIds1[0]).equalsIgnoreCase(CategoryIds1[0]), statusValue, driver,sTestcaseName);
		customReport.customizedReport(true,commentspage.verifycategory(CategoryIds1[1]).equalsIgnoreCase(CategoryIds1[1]), statusValue, driver,sTestcaseName);
		commentspage.click_settingsLink();
		commentspage.click_deleteButton();
		customReport.checkinglist(statusValue);
		
	}

	@Test(description = "Verifying Private Video Upload API with account admin by assigning valid users and groups", groups = {UPLOADVIDEOAPI})
	public void TC_13_UploadVideo_Private_AccountAdmin_withusersandgroups_Positive(ITestContext context) {
	
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
		
		//to assign multiple categories to video
		CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
					
		String CategoryIds[] = {createcategoryapirespone.get("categoryId")};
		adduploadvideobean.setCategoryIds(CategoryIds);
		
		String grpname=create_groupfromUI();
		adduploadvideobean.setAccesscontrolforgroup(grpname);
										
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
				
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
		commentspage.click_settingsLink();
		commentspage.click_details();
		commentspage.clickBasic_Settings();
		customReport.customizedReport("apimvu7 apimvu7",commentspage.verify_Label1(accesscontrolforuser), statusValue, driver,sTestcaseName);
		customReport.customizedReport(grpname,commentspage.verify_Label1(grpname), statusValue, driver,sTestcaseName);
		commentspage.clickCancel();
		commentspage.click_settingsLink();
		commentspage.click_deleteButton();
		customReport.checkinglist(statusValue);
		
	}

	@Test(description = "Verifying AllUsers Video Upload API with account admin with no tags", groups = {UPLOADVIDEOAPI})
	public void TC_14_UploadVideo_AllUsers_AccountAdmin_notags_Positive(ITestContext context) {
	
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
		adduploadvideobean.setVideoAccessControl("All Users");
		adduploadvideobean.setAccesscontrolentities(null);
		adduploadvideobean.setCategories(null);
		adduploadvideobean.setCategoryIds(null);
		adduploadvideobean.setTags(null);
			
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
								
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
			
		//Verification through UI
		logger.info("Selenium Code is excuting");
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
	
	@Test(description = "Upload video through API with account admin with video access control as Teams and account admin is team member",groups = {UPLOADVIDEOAPI})			
	public void TC_15_UploadVideos_AccountAdmin_With_VidoeACcessControlAsTeams_accountadminasteammmember_Negative(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
  		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson1(sUserName), surl);
		logger.info("Login API response Code :::" + loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		addteambean.setTeamMembers(false);
				
		String[] userIds = {loginapiresponse.get("userId")};
		addteambean.setUserids(userIds);

		JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			
 		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setVideoAccessControl("Channels");		
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
		String accesscontrolforteam = addteambean.getName();
		adduploadvideobean.setAccesscontrolforteam(accesscontrolforteam);
		
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode400, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
		customReport.checkinglist(statusValue);
					
	}
	
	@Test(description = "Upload Video API with access control as Teams by giving atleast one team for access control entity with uploader as Media Admin and as team contributor",groups = {UPLOADVIDEOAPI})			
        public void TC_16_UploadVideos_MediaAdmin_With_VidoeACcessControlAsTeams_bygivingatleastoneteam_mediaadminasteamcontributor_Positive(ITestContext context ) throws Exception {

			logger.info("API Level Code is excuting");
			sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	  		UserServices userservices = new UserServices();
	  		
			loginapiresponse = userservices.doLogin(apiutils.userJson1(IUsersList.UploadVideosmediaadmin), surl);
			logger.info("Login API response Code :::" + loginapiresponse);
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			
			CreateTeamAPI createteamAPI=new CreateTeamAPI();
			apibeanfactory.CreateTeamBean(addteambean);
			addteambean.setTeamMembers(true);         	
			
			String[] userIds = {loginapiresponse.get("userId")};
			addteambean.setUserids(userIds);
			addteambean.setTeammemberasuser(loginapiresponse.get("userId"));
			addteambean.setTeammemberasgroup("");	
		
			JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
			createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
			assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			
	 		UploadVideoService uploadvidoeservice = new UploadVideoService();
			apibeanfactory.UploadVideoBean(adduploadvideobean);
			adduploadvideobean.setUploader(IUsersList.UploadVideosmediaadmin); 
			adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
			adduploadvideobean.setEnableRatings(IAPIConstantCodes.FALSE);
			adduploadvideobean.setVideoAccessControl("Public");
			loginapiresponse.put("channelId", createteamapirespone.get(IAPIConstantCodes.APITEAMID));		
			loginapiresponse.put("Mandatory", "No");
			loginapiresponse.put("fileName", "No");
			
			uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
	        logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
	        customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			 
	        HashMap<String,String> loginapiresponse_mediaadmin = userservices.doLogin((apiutils.userJson(IUsersList.UploadVideosmediaadmin)), surl);
	        loginapiresponse_mediaadmin.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
	        loginapiresponse_mediaadmin.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
	 		
	        VideoAccessControlServiceAPI videoaccesscontrolservice=new VideoAccessControlServiceAPI();
	 		accesscontrolbeagPage.setTeamcanEdit("true");
	 		accesscontrolbeagPage.setTeamid(loginapiresponse.get("channelId"));
	 		accesscontrolbeagPage.setTeamtype("Channel");		
	 		String teamname=addteambean.getName(); 		
	        JSONObject json=videoaccesscontrolservice.createJsonVideoAccessControl(accesscontrolbeagPage, IVideoAccessControlService.jsonFlagTeam);
	        videoaccesscontrolservice.videoAccessControl(loginapiresponse_mediaadmin,json);
			
	        //Verification through UI
			logger.info("Selenium Code is excuting");
			launchURL(surl);
			customReport.reporter("Application launch with URL : ", surl);
			homePage = loginPage.loginAs(IUsersList.UploadVideosmediaadmin, sPassword);
			AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
			avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
			AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
			customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
			commentspage.click_settingsLink();
			commentspage.click_details();
			commentspage.clickBasic_Settings();
			customReport.customizedReport(teamname,commentspage.verify_Label1(teamname), statusValue, driver,sTestcaseName);
			commentspage.clickCancel();
			commentspage.click_settingsLink();
			commentspage.click_deleteButton();
			customReport.checkinglist(statusValue);
						
		}
	
	
	@Test(description = "Videos uploaded API with video access control as Teams without giving atleast one team for access control entity",groups = {UPLOADVIDEOAPI})
	public void TC_17_UploadVideos_With_VidoeACcessControlAsTeams_withoutgivingatleastoneteam_Negative(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
  		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson1(sUserName), surl);
		logger.info("Login API response Code :::" + loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			
 		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[3]);
		adduploadvideobean.setAccesscontrolentities(null);
		
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");		
		
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode );
		customReport.customizedReport( HttpStatusCode.httpsStatusCode , uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
					
	}

	@Test(description = "Verify that when Video is uploaded with no video access control then with account admin then private is set by default",groups = {UPLOADVIDEOAPI})			
	public void TC_18_UploadVideos_AccountAdmin_With_NoVidoeACcessControl_Positive(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
  		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson1(sUserName), surl);
		logger.info("Login API response Code :::" + loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
 		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setVideoAccessControl("");
		adduploadvideobean.setIsActive("true");
					
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
				
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
		commentspage.click_settingsLink();
		commentspage.click_details();
		AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
		customReport.customizedReport("false",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[0]),statusValue, driver, sTestcaseName);
		customReport.customizedReport("false",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[5]),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
					
	}
	
	@Test(description = "Public Videos uploaded API with Media Admin with enable ratings as true ",groups = {UPLOADVIDEOAPI})
	public void TC_19_UploadVideos_MediaAdmin_EnableRatingtrue_Public_Positive(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UploadVideosmediaadmin), surl);
		logger.info("Login API response Code :::" + loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
	 	
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.UploadVideosmediaadmin);
		adduploadvideobean.setEnableRatings("true");
		adduploadvideobean.setEnableDownloads("");
		adduploadvideobean.setEnableComments("");	
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
			
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport( HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
		
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
		customReport.customizedReport(adduploadvideobean.getEnableRatings().equals("true"), avengereditvideosettingspage.verifyEnableRatingsSelected().contains("checked"),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = "Private Videos uploaded API with account Admin with enable ratings as False ",groups = {UPLOADVIDEOAPI})
	public void TC_20_UploadVideos_AccountAdmin_EnableRatingfalse_Private_Positive(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson1(sUserName), surl);
		logger.info("Login API response Code :::" + loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
	 	
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);
		String accesscontrolforuser = IUsersList.UploadVideosmediaviewer;
		adduploadvideobean.setAccesscontrolforuser(accesscontrolforuser);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableRatings("false");
		adduploadvideobean.setEnableDownloads("true");
		adduploadvideobean.setEnableComments("");
		
		// GroupIds UI for Edit Access Control
		String groupName = create_groupfromUI();
		adduploadvideobean.setAccesscontrolforgroup(groupName);		
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport( HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
		
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
		customReport.customizedReport(false,avengereditvideosettingspage.verifyEnableRatingsSelected().contains("active"),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = "Public Videos uploaded API with Account Admin with enable downloads as true ",groups = {UPLOADVIDEOAPI})
	public void TC_21_UploadVideos_MediaAdmin_EnableDownloadstrue_Public_Positive(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson1(sUserName), surl);
		logger.info("Login API response Code :::" + loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
	 	
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableRatings("");
		adduploadvideobean.setEnableDownloads("true");
		adduploadvideobean.setEnableComments("");	
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
			
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport( HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
		
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
		customReport.customizedReport(adduploadvideobean.getEnableDownloads().equals("true"), avengereditvideosettingspage.verifyEnabledownloadsSelected().contains("checked"),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = "Private Videos uploaded API with media contributor with enable downloads as False ",groups = {UPLOADVIDEOAPI})
	public void TC_22_UploadVideos_MediaContributor_EnableRatingfalse_Private_Positive(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UploadVideosmediacontributor), surl);
		logger.info("Login API response Code :::" + loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
	 	
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);
		String accesscontrolforuser = IUsersList.UploadVideosmediaviewer;
		adduploadvideobean.setAccesscontrolforuser(accesscontrolforuser);
		adduploadvideobean.setUploader(IUsersList.UploadVideosmediacontributor);
		adduploadvideobean.setEnableRatings("");
		adduploadvideobean.setEnableDownloads("false");
		adduploadvideobean.setEnableComments("");
		
		// GroupIds UI for Edit Access Control
		String groupName = create_groupfromUI();
		adduploadvideobean.setAccesscontrolforgroup(groupName);		
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport( HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
		
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
		customReport.customizedReport(false,avengereditvideosettingspage.verifyEnabledownloadsSelected().contains("active"),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "Public Videos uploaded API with Media Admin with enable comments as true ",groups = {UPLOADVIDEOAPI})
	public void TC_23_UploadVideos_MediaAdmin_EnableCommentstrue_AllUsers_Positive(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UploadVideosmediaadmin), surl);
		logger.info("Login API response Code :::" + loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
	 	
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.UploadVideosmediaadmin);
		adduploadvideobean.setEnableRatings("");
		adduploadvideobean.setEnableDownloads("");
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[2]);	
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport( HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
		
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
		customReport.customizedReport(adduploadvideobean.getEnableComments().equals("true"), avengereditvideosettingspage.verifyEnableCommentsSelected().contains("checked"),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = "Private Videos uploaded API with account admin with enable comments as False ",groups = {UPLOADVIDEOAPI})
	public void TC_24_UploadVideos_AccountAdmin_EnableCommentsfalse_Public_Positive(ITestContext context) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson1(sUserName), surl);
		logger.info("Login API response Code :::" + loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
	 	
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);
		String accesscontrolforuser = IUsersList.UploadVideosmediaviewer;
		adduploadvideobean.setAccesscontrolforuser(accesscontrolforuser);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableRatings("");
		adduploadvideobean.setEnableDownloads("");
		adduploadvideobean.setEnableComments("false");
		
		// GroupIds UI for Edit Access Control
		String groupName = create_groupfromUI();
		adduploadvideobean.setAccesscontrolforgroup(groupName);		
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
			
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport( HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
		
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
		customReport.customizedReport(false, avengereditvideosettingspage.verifyEnableCommentsSelected().contains("active"),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "Verifying Private Video Upload API with media admin by assigning one valid user and one invalid group", groups = {UPLOADVIDEOAPI})
	public void TC_25_UploadVideo_Private_MediaAdmin_withonevaliduser_andoneinvalidgroup_Positive(ITestContext context) {
	
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UploadVideosmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
 		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.UploadVideosmediaadmin);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);
		String accesscontrolforuser = IUsersList.UploadVideosmediaviewer;
		adduploadvideobean.setAccesscontrolforuser(accesscontrolforuser);					
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
		//to assign multiple categories to video
		CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
					
		String CategoryIds[] = {createcategoryapirespone.get("categoryId")};
		adduploadvideobean.setCategoryIds(CategoryIds);
		
		String grpName=create_groupfromUI();
		adduploadvideobean.setAccesscontrolforgroup(grpName+"a");
								
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);		
		
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
		commentspage.click_settingsLink();
		commentspage.click_details();
		commentspage.clickBasic_Settings();
		customReport.customizedReport(1,commentspage.get_countOfElements(), statusValue, driver,sTestcaseName);
		customReport.customizedReport("apimvu7 apimvu7",commentspage.verify_Label3(accesscontrolforuser), statusValue, driver,sTestcaseName);
		commentspage.clickCancel();
		commentspage.click_settingsLink();
		commentspage.click_deleteButton();
		customReport.checkinglist(statusValue);
		
	}

	@Test(description = "Verifying Private Video Upload API with account admin by assigning one invalid user and one valid group", groups = {UPLOADVIDEOAPI})
	public void TC_26_UploadVideo_Private_AccountAdmin_withoneinvaliduser_andonevalidgroup_Positive(ITestContext context) {
	
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
		adduploadvideobean.setAccesscontrolforuser(accesscontrolforuser+"a");		
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
		//to assign multiple categories to video
		CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
					
		String CategoryIds[] = {createcategoryapirespone.get("categoryId")};
		adduploadvideobean.setCategoryIds(CategoryIds);
		
		String grpName=create_groupfromUI();
		adduploadvideobean.setAccesscontrolforgroup(grpName);
									
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
				
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
		commentspage.click_settingsLink();
		commentspage.click_details();
		commentspage.clickBasic_Settings();
		customReport.customizedReport(1,commentspage.get_countOfElements(), statusValue, driver,sTestcaseName);
		customReport.customizedReport(grpName,commentspage.verify_Label1(grpName), statusValue, driver,sTestcaseName);
		commentspage.clickCancel();
		commentspage.click_settingsLink();
	    commentspage.click_deleteButton();
		customReport.checkinglist(statusValue);
		
	}

	@Test(description = "Videos uploaded API with video access control as Teams by giving an invalid team for access control entity with account admin",groups = {UPLOADVIDEOAPI})
	public void TC_27_UploadVideos_AccountAdmin_With_VidoeACcessControlAsTeams_bygivinginvalidteam_Negative(ITestContext context ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
  		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson1(sUserName), surl);
		logger.info("Login API response Code :::" + loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
 		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.UploadVideoseventadmin);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[3]);
		
		// Teamid  Access Control
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
		
		String teamname=addteambean.getName();
		String accesscontrolforteam = teamname;
		adduploadvideobean.setAccesscontrolforteam(accesscontrolforteam+"a");
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode );
		customReport.customizedReport( HttpStatusCode.httpsStatusCode , uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
					
	}
		@Test(description = "Upload 360 Public video with Media Admin", groups = {UPLOADVIDEOAPI})				
		public void TC_28_Upload360Video_Public_MediaAdmin_Positive(ITestContext context) {
		
			logger.info("API Level Code is excuting");
			sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
			customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
			
			UserServices userservices = new UserServices();
			loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UploadVideosmediaadmin), surl);
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			logger.info("Login API response Code :::" + loginapiresponse);
			
	 		UploadVideoService uploadvidoeservice = new UploadVideoService();
			apibeanfactory.UploadVideoBean(adduploadvideobean);
			adduploadvideobean.setUploader(IUsersList.UploadVideosmediaadmin);
			adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);
			adduploadvideobean.setIs360("True");
			adduploadvideobean.setCategoryIds(null);			
			loginapiresponse.put("Mandatory", "No");
			loginapiresponse.put("fileName", "No");
			
			CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
			apibeanfactory.CreateCategoryBean(addcategorybean);
			JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
			createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse,categroiesjson);
			assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
									
			String CategoryIds[] = {createcategoryapirespone.get("name")};
			adduploadvideobean.setCategories(CategoryIds);
			
			uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
			logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			context.setAttribute(sTestcaseName, uploadvidoeapiresponse);			
			
			//Verification through UI
			logger.info("Selenium Code is excuting");
			launchURL(surl);
			customReport.reporter("Application launch with URL : ", surl);
			homePage = loginPage.loginAs(sUserName, sPassword);
			AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
			avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
			AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
			customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
			commentspage.clickVideoBasicinformationButton();
			customReport.customizedReport(true,commentspage.verifycategory(CategoryIds[0]).equalsIgnoreCase(CategoryIds[0]), statusValue, driver,sTestcaseName);
			customReport.customizedReport(true,commentspage.verifyIfVideoIs360(), statusValue, driver,sTestcaseName);
			customReport.checkinglist(statusValue);
			
		}
		
		@Test(description = "Upload Private video with 360 flag as false with Media Contributor role", groups = {UPLOADVIDEOAPI})				
		public void TC_29_UploadVideo_with360false_Private_MediaContributor_Positive(ITestContext context) {
		
			logger.info("API Level Code is excuting");
			sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
			customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
			
			UserServices userservices = new UserServices();
			loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UploadVideosmediacontributor), surl);
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			logger.info("Login API response Code :::" + loginapiresponse);
								
	 		UploadVideoService uploadvidoeservice = new UploadVideoService();
			apibeanfactory.UploadVideoBean(adduploadvideobean);
			adduploadvideobean.setUploader(IUsersList.UploadVideosmediacontributor);
			adduploadvideobean.setIs360("False");
			adduploadvideobean.setVideoAccessControl(null);
			adduploadvideobean.setCategories(null);
			adduploadvideobean.setCategoryIds(null);			
			loginapiresponse.put("Mandatory", "No");
			loginapiresponse.put("fileName", "No");
			
			uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
			logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			context.setAttribute(sTestcaseName, uploadvidoeapiresponse);			
			
			//Verification through UI
			logger.info("Selenium Code is excuting");
			launchURL(surl);
			customReport.reporter("Application launch with URL : ", surl);
			homePage = loginPage.loginAs(sUserName, sPassword);
			AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
			avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
			AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
			customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
			customReport.customizedReport(false,commentspage.verifyIfVideoIs360(), statusValue, driver,sTestcaseName);
			customReport.checkinglist(statusValue);
			
		}	
		
		@Test(description = "Upload video with Media Admin with custom field value when custom field value is not mandatory", groups = {UPLOADVIDEOAPI})
		public void TC_30_UploadVideo_Public_MediaAdmin_withnonmandatecustomfieldvalue_Positive(ITestContext context) {
		
			logger.info("API Level Code is excuting");
			sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
			customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
			UserServices userservices = new UserServices();
			loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UploadVideosmediaadmin), surl);
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			logger.info("Login API response Code :::" + loginapiresponse);
			
			//Adding the custom field			
			launchURL(surl);
			homePage = loginPage.loginAs(sUserName, sPassword);
			homePage.clickSettingsLink();
			avengersystemsettingspage = homePage.clickSystemSettingsLink();
			avengercustomefieldspage=avengersystemsettingspage.click_CustomFields();
			avengercustomefieldspage.deleteallcustomfields();
			customReport.customizedReport(bundle.getString("customfields.nocustomfieldsmsg"),avengercustomefieldspage.verify_Label(bundle.getString("customfields.nocustomfieldsmsg")), statusValue, driver, sTestcaseName);		
			avengercustomefieldspage.click_addfieldbutton();
			avengercustomefieldspage.setcustmofieldname(customfieldsbean);
			avengercustomefieldspage.click_submitbutton();			
			customReport.customizedReport(customfieldsbean.getCustomfieldname(),avengercustomefieldspage.verify_Label(customfieldsbean.getCustomfieldname()), statusValue, driver, sTestcaseName);
			
			String cfName= customfieldsbean.getCustomfieldname();			
			String cfID=get_customFieldIdFromUI(cfName);
		
			//Upload Video
	 		UploadVideoService uploadvidoeservice = new UploadVideoService();
			apibeanfactory.UploadVideoBean(adduploadvideobean);
			adduploadvideobean.setCustomFieldValue("Custom_"+apibeanfactory.randomAlphabetic(3));
			adduploadvideobean.setCustomFieldId(cfID);			
			adduploadvideobean.setUploader(sUserName);
			adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);				
			loginapiresponse.put("Mandatory", "No");
			loginapiresponse.put("fileName", "No");
															
			uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
			logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
						
			//Verification through UI
			logger.info("Selenium Code is excuting");
			launchURL(surl);
			customReport.reporter("Application launch with URL : ", surl);
			homePage = loginPage.loginAs(sUserName, sPassword);
			AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
			avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
			AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
			customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
			commentspage.click_settingsLink();
			commentspage.click_details();
			commentspage.clickBasic_Settings();
			customReport.customizedReport(customfieldsbean.getCustomfieldname(),avengercustomefieldspage.verify_Label(customfieldsbean.getCustomfieldname()), statusValue, driver, sTestcaseName);
			customReport.checkinglist(statusValue);
			
		}
		
		@Test(description = "Verify that Upload Video is rejected when invalid non video format is provided",groups = {UPLOADVIDEOAPI})				
		public void TC_31_UploadVideos_with_nonVideoFormat_Negative(ITestContext context) {

			logger.info("API Level Code is excuting");
			sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	 		
			UserServices userservices = new UserServices();
			loginapiresponse = userservices.doLogin(apiutils.userJson1(sUserName), surl);
			Assert.assertNotNull(loginapiresponse);
			logger.info("Login API response Code :::" + loginapiresponse); 
			
			UploadVideoService uploadvidoeservice = new UploadVideoService();
			apibeanfactory.UploadVideoBean(adduploadvideobean);
			adduploadvideobean.setUploader(sUserName);
			loginapiresponse.put("Mandatory", "No");
			loginapiresponse.put("fileName", "No");
			uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.INVALIDLOGOPATH,adduploadvideobean);
			logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode415);
			customReport.customizedReport( HttpStatusCode.httpsStatusCode415, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			customReport.checkinglist(statusValue);
			 
		}

		@Test(description = "Upload a video with Video upload API with only mandatory fields and with account admin role with a different uploader", groups = {UPLOADVIDEOAPI})				
		public void TC_32_UploadVideo_AccountAdmin_With_Mandatoryfield_differentUploader_Positive(ITestContext context) {
		
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
			adduploadvideobean.setUploader(IUsersList.UploadVideosmediaadmin);
			adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);
			adduploadvideobean.setDescription("");
			adduploadvideobean.setTitle("");
			adduploadvideobean.setEnableComments("");
			adduploadvideobean.setEnableDownloads("");
			adduploadvideobean.setEnableRatings("");
			adduploadvideobean.setCategories(null);
			adduploadvideobean.setCategoryIds(null);
			adduploadvideobean.setTags(null);			
			loginapiresponse.put("Mandatory", "Yes");
			loginapiresponse.put("fileName", "Yes");
					
			uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
			logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
						
			//Verification through UI
			logger.info("Selenium Code is excuting");
			launchURL(surl);
			customReport.reporter("Application launch with URL : ", surl);
			homePage = loginPage.loginAs(sUserName, sPassword);
			AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
			avengerdashboardpage.searchMedia(uploadvidoeapiresponse.get("videoTitle"));
			AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(uploadvidoeapiresponse.get("videoTitle"));
			customReport.customizedReport(true, commentspage.verify_VideoTitleText(uploadvidoeapiresponse.get("videoTitle")),statusValue, driver, sTestcaseName);
			commentspage.clickVideoBasicinformationButton();
			By uploaderlocator = commentspage.containstext_xpath1("apimau7 apimau7");
			customReport.customizedReport(true,commentspage.getAttribute(uploaderlocator).contains("apimau7 apimau7"),statusValue, driver, sTestcaseName);
			commentspage.click_settingsLink();
			commentspage.click_deleteButton();
			customReport.checkinglist(statusValue);
			
		}
			
		@Test(description = "Verify that Upload Video is rejected when invalid video uploader is sent in the request",groups = {UPLOADVIDEOAPI})				
		public void TC_33_UploadVideos_with_Invalid_VideoUploader_Negative(ITestContext context) {

			logger.info("API Level Code is excuting");
			sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	 		
			UserServices userservices = new UserServices();
			loginapiresponse = userservices.doLogin(apiutils.userJson1(sUserName), surl);
			Assert.assertNotNull(loginapiresponse);
			logger.info("Login API response Code :::" + loginapiresponse); 
			
			UploadVideoService uploadvidoeservice = new UploadVideoService();
			apibeanfactory.UploadVideoBean(adduploadvideobean);
			adduploadvideobean.setUploader(IUploadVideoService.invalidUploader);
			loginapiresponse.put("Mandatory", "No");
			loginapiresponse.put("fileName", "No");
			
			uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
			logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode400);
			customReport.customizedReport( HttpStatusCode.httpsStatusCode400, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
			customReport.checkinglist(statusValue);
			 
		}
		
		@Test(description = "Verifying Public Video Upload API with Media admin for checking with multiple categories with one beiong invalid", groups = {UPLOADVIDEOAPI})
		public void TC_34_UploadVideo_Public_MediaAdmin_checkingmultiplecategorywithname_oneinvalidcategory_Positive(ITestContext context) {
		
			logger.info("API Level Code is excuting");
			sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
			customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
			
			UserServices userservices = new UserServices();
			loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UploadVideosmediaadmin), surl);
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			logger.info("Login API response Code :::" + loginapiresponse);
			
	 		UploadVideoService uploadvidoeservice = new UploadVideoService();
			apibeanfactory.UploadVideoBean(adduploadvideobean);
			adduploadvideobean.setUploader(sUserName);
			adduploadvideobean.setEnableComments("");
			adduploadvideobean.setEnableDownloads("");
			adduploadvideobean.setEnableRatings("");
			adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);
			adduploadvideobean.setAccesscontrolforuser("");
			adduploadvideobean.setCategoryIds(null);
			adduploadvideobean.setTags(null);			
			loginapiresponse.put("Mandatory", "No");
			loginapiresponse.put("fileName", "No");
			
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
			
			String CategoryIds[] = {createcategoryapirespone.get("name"),createcategoryapirespone1.get("name")+"a"};
			adduploadvideobean.setCategories(CategoryIds);
									
			uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
			logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			context.setAttribute(sTestcaseName, uploadvidoeapiresponse);		
			
			//Verification through UI
			logger.info("Selenium Code is excuting");
			launchURL(surl);
			customReport.reporter("Application launch with URL : ", surl);
			homePage = loginPage.loginAs(sUserName, sPassword);
			AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
			avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
			AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
			customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
			commentspage.clickVideoBasicinformationButton();
			int catCount = commentspage.get_AllCategoriesOfVideo().size();
			customReport.customizedReport(true,commentspage.verifycategory(CategoryIds[0]).equalsIgnoreCase(CategoryIds[0]), statusValue, driver,sTestcaseName);
			customReport.customizedReport(1,catCount, statusValue, driver,sTestcaseName);
			customReport.checkinglist(statusValue);
			
		}

		@Test(description = "Verifying Public Video Upload API with Media contributor for checking with invalid category id", groups = {UPLOADVIDEOAPI})
		public void TC_35_UploadVideo_Public_MediaContributor_withinvalidcategoryid_Negative(ITestContext context) {
		
			logger.info("API Level Code is excuting");
			sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
			customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
			
			UserServices userservices = new UserServices();
			loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UploadVideosmediaadmin), surl);
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			logger.info("Login API response Code :::" + loginapiresponse);
			
	 		UploadVideoService uploadvidoeservice = new UploadVideoService();
			apibeanfactory.UploadVideoBean(adduploadvideobean);
			adduploadvideobean.setUploader(sUserName);
			adduploadvideobean.setEnableComments("");
			adduploadvideobean.setEnableDownloads("");
			adduploadvideobean.setEnableRatings("");
			adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);
			adduploadvideobean.setCategories(null);
			adduploadvideobean.setTags(null);		
			loginapiresponse.put("Mandatory", "No");
			loginapiresponse.put("fileName", "No");
			
			//to assign category to video
			CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
			apibeanfactory.CreateCategoryBean(addcategorybean);
			JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
			createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse,categroiesjson);
			assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
								
			String CategoryIds[] = {createcategoryapirespone.get("categoryId")+"a"};
			adduploadvideobean.setCategoryIds(CategoryIds);
												
			uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
			logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsstatuscode500);
			customReport.customizedReport( HttpStatusCode.invalidCategory, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseJsonErrorCode),statusValue, driver, sTestcaseName);
			customReport.checkinglist(statusValue);
			
		}
		
		@Test(description = "Upload a public video with Video upload API with only uploader, VideoAccessControl and IsActive fields and with account admin role", groups = {UPLOADVIDEOAPI})				
		public void TC_36_UploadVideo_Public_AccountAdmin_With_UploaderVACandIsActivefields_Positive(ITestContext context) {
			
	
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
			adduploadvideobean.setDescription("");
			adduploadvideobean.setEnableComments("");
			adduploadvideobean.setEnableDownloads("");
			adduploadvideobean.setEnableRatings("");
			adduploadvideobean.setIsActive("true");
			adduploadvideobean.setVideoAccessControl("Public1");
			adduploadvideobean.setAccesscontrolentities(null);
			adduploadvideobean.setCategories(null);
			adduploadvideobean.setCategoryIds(null);
			adduploadvideobean.setTags(null);			
			loginapiresponse.put("Mandatory", "No");
			loginapiresponse.put("fileName", "No");
					
			uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
			logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			context.setAttribute(sTestcaseName, uploadvidoeapiresponse);			
			
			//Verification through UI
			logger.info("Selenium Code is excuting");
			launchURL(surl);
			customReport.reporter("Application launch with URL : ", surl);
			homePage = loginPage.loginAs(sUserName, sPassword);
			AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
			avengerdashboardpage.searchMedia(uploadvidoeapiresponse.get("videoTitle"));
			AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(uploadvidoeapiresponse.get("videoTitle"));
			customReport.customizedReport(true, commentspage.verify_VideoTitleText(uploadvidoeapiresponse.get("videoTitle")),statusValue, driver, sTestcaseName);
			commentspage.click_settingsLink();
			commentspage.click_details();
			AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
			customReport.customizedReport(true, commentspage.getPublicWebcastPwd().equals("pwdispwd"),statusValue, driver, sTestcaseName);
			avengereditvideosettingspage.clickCancel();
			commentspage.click_settingsLink();
			customReport.checkinglist(statusValue);			
				
		}
		
		@Test(description = "Upload a private video with Video upload API with only uploader, VideoAccessControl and IsActive fields and with Media admin role", groups = {UPLOADVIDEOAPI})				
		public void TC_37_UploadVideo_Private_MediaAdmin_With_UploaderVACandIsActivefields_Positive(ITestContext context) {
		
			logger.info("API Level Code is excuting");
			sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
			customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
			
			UserServices userservices = new UserServices();
			loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UploadVideosmediaadmin), surl);
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			logger.info("Login API response Code :::" + loginapiresponse);
			
	 		UploadVideoService uploadvidoeservice = new UploadVideoService();
			apibeanfactory.UploadVideoBean(adduploadvideobean);
			adduploadvideobean.setUploader(IUsersList.UploadVideosmediaadmin);
			adduploadvideobean.setTitle("");
			adduploadvideobean.setDescription("");
			adduploadvideobean.setEnableComments("");
			adduploadvideobean.setEnableDownloads("");
			adduploadvideobean.setEnableRatings("");
			adduploadvideobean.setIsActive("true");
			adduploadvideobean.setVideoAccessControl("Private");
			adduploadvideobean.setAccesscontrolentities(null);
			adduploadvideobean.setCategories(null);
			adduploadvideobean.setCategoryIds(null);
			adduploadvideobean.setTags(null);
			
			String accesscontrolforuser = IUsersList.UploadVideosmediaviewer;
			adduploadvideobean.setAccesscontrolforuser(accesscontrolforuser);
			
			loginapiresponse.put("Mandatory", "No");
			loginapiresponse.put("fileName", "Yes");
					
			uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
			logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
					
			//Verification through UI
			logger.info("Selenium Code is excuting");
			launchURL(surl);
			customReport.reporter("Application launch with URL : ", surl);
			homePage = loginPage.loginAs(sUserName, sPassword);
			AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
			avengerdashboardpage.searchMedia(uploadvidoeapiresponse.get("videoTitle"));
			AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(uploadvidoeapiresponse.get("videoTitle"));
			customReport.customizedReport(true, commentspage.verify_VideoTitleText(uploadvidoeapiresponse.get("videoTitle")),statusValue, driver, sTestcaseName);
			commentspage.click_settingsLink();
			commentspage.click_details();
			AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
			customReport.customizedReport("false",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[0]),statusValue, driver, sTestcaseName);
			customReport.customizedReport("false",avengereditvideosettingspage.verify_accessControls(IUploadVideoService.videoAccessControl[5]),statusValue, driver, sTestcaseName);
			avengereditvideosettingspage.clickCancel();
			commentspage.click_settingsLink();
			commentspage.click_deleteButton();
			customReport.checkinglist(statusValue);
		}
	
		@Test(description = "Upload all users video with Video upload API with only uploader, VideoAccessControl and IsActive andis360 fields and with Media contributor role", groups = {UPLOADVIDEOAPI})				
		public void TC_38_UploadVideo_AllUsers_MediaContributor_With_UploaderVACandIsActiveandis360fields_Positive(ITestContext context) {
		
			logger.info("API Level Code is excuting");
			sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
			customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
			
			UserServices userservices = new UserServices();
			loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UploadVideosmediacontributor), surl);
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			logger.info("Login API response Code :::" + loginapiresponse);
			
			UploadVideoService uploadvidoeservice = new UploadVideoService();
			apibeanfactory.UploadVideoBean(adduploadvideobean);
			adduploadvideobean.setUploader(IUsersList.UploadVideosmediacontributor);
			adduploadvideobean.setDescription("");
			adduploadvideobean.setEnableComments("");
			adduploadvideobean.setEnableDownloads("");
			adduploadvideobean.setEnableRatings("");
			adduploadvideobean.setIsActive("true");
			adduploadvideobean.setIs360("true");
			adduploadvideobean.setVideoAccessControl("AllUsers");
			adduploadvideobean.setAccesscontrolentities(null);
			adduploadvideobean.setCategories(null);
			adduploadvideobean.setCategoryIds(null);
			adduploadvideobean.setTags(null);		
			loginapiresponse.put("Mandatory", "No");
			loginapiresponse.put("fileName", "No");
					
			uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
			logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
						
			//Verification through UI
			logger.info("Selenium Code is excuting");
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
				
		@Test(description = "Verifying Private Video Upload API with media admin by assigning one valid user and one invalid group with Id", groups = {UPLOADVIDEOAPI})
		public void TC_39_UploadVideo_Private_MediaAdmin_withonevaliduser_andoneinvalidgroup_withId_Positive(ITestContext context) {
		
			logger.info("API Level Code is excuting");
			sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
			customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
			
			UserServices userservices = new UserServices();
			loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UploadVideosmediaadmin), surl);
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			logger.info("Login API response Code :::" + loginapiresponse);
			
	 		UploadVideoService uploadvidoeservice = new UploadVideoService();
			apibeanfactory.UploadVideoBean(adduploadvideobean);
			adduploadvideobean.setUploader(IUsersList.UploadVideosmediaadmin);
			adduploadvideobean.setVideoAccessControl("Private1");
			String accesscontrolforuser = IUsersList.UploadVideosmediaviewer;
			
			//get userId
			String userIdOfUser = get_userIdFromUI(accesscontrolforuser);
			adduploadvideobean.setUserId(userIdOfUser);
			adduploadvideobean.setAccesscontrolforuser(accesscontrolforuser);			
			loginapiresponse.put("Mandatory", "No");
			loginapiresponse.put("fileName", "No");
			
			//to assign multiple categories to video
			CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
			apibeanfactory.CreateCategoryBean(addcategorybean);
			JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
			createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse,categroiesjson);
			assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
						
			String CategoryIds[] = {createcategoryapirespone.get("categoryId")};
			adduploadvideobean.setCategoryIds(CategoryIds);
			
			String accesscontrolforgroup=create_groupfromUI();
			
			//get groupId
			String groupIdOfGroup = get_groupIdFromUI(accesscontrolforgroup);
			adduploadvideobean.setGroupId(groupIdOfGroup+"a");
			adduploadvideobean.setAccesscontrolforgroup(accesscontrolforgroup);
									
			uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
			logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
						
			//Verification through UI
			logger.info("Selenium Code is excuting");
			launchURL(surl);
			customReport.reporter("Application launch with URL : ", surl);
			homePage = loginPage.loginAs(sUserName, sPassword);
			AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
			avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
			AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
			customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
			commentspage.click_settingsLink();
			commentspage.click_details();
			commentspage.clickBasic_Settings();
			customReport.customizedReport(1,commentspage.get_countOfElements(), statusValue, driver,sTestcaseName);
			customReport.customizedReport("apimvu7 apimvu7",commentspage.verify_Label(accesscontrolforuser), statusValue, driver,sTestcaseName);
			customReport.checkinglist(statusValue);
			
		}

		@Test(description = "Verifying Private Video Upload API with account admin by assigning one invalid user and one valid group with id", groups = {UPLOADVIDEOAPI})
		public void TC_40_UploadVideo_Private_AccountAdmin_withoneinvaliduser_andonevalidgroup_withId_Positive(ITestContext context) {
		
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
			adduploadvideobean.setVideoAccessControl("Private1");
			String accesscontrolforuser = IUsersList.UploadVideosmediaviewer;
			
			//get userId
			String userIdOfUser = get_userIdFromUI(accesscontrolforuser);
			adduploadvideobean.setUserId(userIdOfUser+"a");
			adduploadvideobean.setAccesscontrolforuser(accesscontrolforuser);
							
			loginapiresponse.put("Mandatory", "No");
			loginapiresponse.put("fileName", "No");
			
			//to assign multiple categories to video
			CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
			apibeanfactory.CreateCategoryBean(addcategorybean);
			JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
			createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse,categroiesjson);
			assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
						
			String CategoryIds[] = {createcategoryapirespone.get("categoryId")};
			adduploadvideobean.setCategoryIds(CategoryIds);
			
			String accesscontrolforgroup=create_groupfromUI();
			
			//get groupId
			String groupIdOfGroup = get_groupIdFromUI(accesscontrolforgroup);
			adduploadvideobean.setGroupId(groupIdOfGroup);
			adduploadvideobean.setAccesscontrolforgroup(accesscontrolforgroup);
												
			uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
			logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			context.setAttribute(sTestcaseName, uploadvidoeapiresponse);
						
			//Verification through UI
			logger.info("Selenium Code is excuting");
			launchURL(surl);
			customReport.reporter("Application launch with URL : ", surl);
			homePage = loginPage.loginAs(sUserName, sPassword);
			AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
			avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
			AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
			customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
			commentspage.click_settingsLink();
			commentspage.click_details();
			AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
			customReport.customizedReport(1,commentspage.get_countOfElements(), statusValue, driver,sTestcaseName);
			customReport.customizedReport(accesscontrolforgroup,commentspage.verify_Label1(accesscontrolforgroup), statusValue, driver,sTestcaseName);
			avengereditvideosettingspage.clickCancel();
			commentspage.click_settingsLink();
			commentspage.click_deleteButton();
			customReport.checkinglist(statusValue);
			
		}
		
		@Test(description = "To verify that upload video is not rejected when one valid and one invalid team is provided",groups = {UPLOADVIDEOAPI})
		public void TC_41_UploadVideos_Teams_AccountAdmin_onevalidandoneinvalidteam_Positive(ITestContext context ) throws Exception {

			logger.info("API Level Code is excuting");
			sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	  		UserServices userservices = new UserServices();
	  		
			loginapiresponse = userservices.doLogin(apiutils.userJson1(sUserName), surl);
			logger.info("Login API response Code :::" + loginapiresponse);
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			
			//For invalid team
			CreateTeamAPI createteamAPI=new CreateTeamAPI();
			apibeanfactory.CreateTeamBean(addteambean);
			addteambean.setTeamMembers(true);         	
			
			String[] userIds = {loginapiresponse.get("userId")};
			addteambean.setUserids(userIds);
			addteambean.setTeammemberasuser(loginapiresponse.get("userId"));
			addteambean.setTeammemberasgroup("");	
		
			JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
			createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
			assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
			//For valid team
			CreateTeamAPI createteamAPI1=new CreateTeamAPI();
			apibeanfactory.CreateTeamBean(addteambean);
			addteambean.setTeamMembers(true);
			
			
			String[] userIds1 = {loginapiresponse.get("userId")};
			addteambean.setUserids(userIds);
			addteambean.setTeammemberasuser(loginapiresponse.get("userId"));
			addteambean.setTeammemberasgroup("");	
		
			JSONObject creatteamjson1=createteamAPI1.createTeamJson(addteambean);
			createteamapirespone1 = createteamAPI1.createTeam(loginapiresponse,creatteamjson1);
			assertionapiresponse.verifyAssert_httpCode(createteamapirespone1.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			
			
	 		UploadVideoService uploadvidoeservice = new UploadVideoService();
			apibeanfactory.UploadVideoBean(adduploadvideobean);
			adduploadvideobean.setUploader(sUserName); 
			adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
			adduploadvideobean.setEnableRatings(IAPIConstantCodes.FALSE);
			adduploadvideobean.setVideoAccessControl("Private");
			loginapiresponse.put("channelId", createteamapirespone1.get(IAPIConstantCodes.APITEAMID));
			loginapiresponse.put("channelId1", createteamapirespone.get(IAPIConstantCodes.APITEAMID)+"123");
			loginapiresponse.put("Mandatory", "No");
			loginapiresponse.put("fileName", "No");
			
			uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
	        logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
	        customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			loginapiresponse.put("videoId", uploadvidoeapiresponse.get("videoId"));
	 		Thread.sleep(240000);
			
	 		//For Invalid team
	 		 VideoAccessControlServiceAPI videoaccesscontrolservice1=new VideoAccessControlServiceAPI();
		 	accesscontrolbeagPage.setTeamcanEdit("true");
		 	accesscontrolbeagPage.setTeamid(loginapiresponse.get("channelId1"));
		 	accesscontrolbeagPage.setTeamtype("Channel");	
		 		
		    String teamname1=addteambean.getName(); 
		    accesscontrolbeagPage.setTeamName(teamname1);
		    JSONObject json1=videoaccesscontrolservice1.createJsonVideoAccessControl(accesscontrolbeagPage, IVideoAccessControlService.jsonFlagTeam);
		    videoaccesscontrolservice1.videoAccessControl(loginapiresponse,json1);
		     
	 	
	 		//For Valid Team
	        VideoAccessControlServiceAPI videoaccesscontrolservice=new VideoAccessControlServiceAPI();
	 		accesscontrolbeagPage.setTeamcanEdit("true");
	 		accesscontrolbeagPage.setTeamid(loginapiresponse.get("channelId"));
	 		accesscontrolbeagPage.setTeamtype("Channel");	
	 		
	 		String teamname=addteambean.getName(); 
	 		accesscontrolbeagPage.setTeamName(teamname);
	        JSONObject json=videoaccesscontrolservice.createJsonVideoAccessControl(accesscontrolbeagPage, IVideoAccessControlService.jsonFlagTeam);
	        videoaccesscontrolservice.videoAccessControl(loginapiresponse,json);
	     
			//Verification through UI
			logger.info("Selenium Code is excuting");
			launchURL(surl);
			customReport.reporter("Application launch with URL : ", surl);
			homePage = loginPage.loginAs(sUserName, sPassword);
			AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
			avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
			AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
			customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
			commentspage.click_settingsLink();
			commentspage.click_details();
			AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
			customReport.customizedReport(1,avengereditvideosettingspage.get_nooflibrariesforavideo(), statusValue, driver,sTestcaseName);
			customReport.customizedReport(teamname,commentspage.verify_Label1(teamname), statusValue, driver,sTestcaseName);
			avengereditvideosettingspage.clickCancel();
			commentspage.click_settingsLink();
			customReport.checkinglist(statusValue);
						
		}
		
		
	@Test(description = "Upload video with Media Contributor with custom field value when custom field value is mandatory", groups = {UPLOADVIDEOAPI})
	public void TC_42_UploadVideo_Public_MediaContributor_withmandatecustomfieldvalue_Positive(ITestContext context) {
	
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UploadVideosmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		//Adding the custom field	
		launchURL(surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		homePage.clickSettingsLink();
		avengersystemsettingspage = homePage.clickSystemSettingsLink();
		avengercustomefieldspage=avengersystemsettingspage.click_CustomFields();
		avengercustomefieldspage.deleteallcustomfields();
		customReport.customizedReport(bundle.getString("customfields.nocustomfieldsmsg"),avengercustomefieldspage.verify_Label(bundle.getString("customfields.nocustomfieldsmsg")), statusValue, driver, sTestcaseName);
		
		avengercustomefieldspage.click_addfieldbutton();
		avengercustomefieldspage.setcustmofieldname(customfieldsbean);
		avengercustomefieldspage.click_RequiredOrDisaplayPublicOptions("Required", "YES");
		avengercustomefieldspage.click_submitbutton();
		
		customReport.customizedReport(customfieldsbean.getCustomfieldname(),avengercustomefieldspage.verify_Label(customfieldsbean.getCustomfieldname()), statusValue, driver, sTestcaseName);
		String cfName= customfieldsbean.getCustomfieldname();
		
		String cfID=get_customFieldIdFromUI(cfName);
	
		//Upload Video
 		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setCustomFieldValue("Custom_"+apibeanfactory.randomAlphabetic(3));
		adduploadvideobean.setCustomFieldId(cfID);		
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);			
		loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
														
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode , uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, uploadvidoeapiresponse);	
		
		//Verification through UI
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
		commentspage.click_settingsLink();
		commentspage.click_details();
		commentspage.clickBasic_Settings();
		customReport.customizedReport(customfieldsbean.getCustomfieldname(),avengercustomefieldspage.verify_Label(customfieldsbean.getCustomfieldname()), statusValue, driver, sTestcaseName);

		//Deleting the added mandatory custom field	
		homePage.clickSettingsLink();
		avengersystemsettingspage = homePage.clickSystemSettingsLink();
		avengercustomefieldspage=avengersystemsettingspage.click_CustomFields();
		avengercustomefieldspage.deleteallcustomfields();
		customReport.customizedReport(bundle.getString("customfields.nocustomfieldsmsg"),avengercustomefieldspage.verify_Label(bundle.getString("customfields.nocustomfieldsmsg")), statusValue, driver, sTestcaseName);	
		customReport.checkinglist(statusValue);
		
	}
		
		@Test(description = "Upload video with Media Contributor with custom field value when custom field value is mandatory and value is not given", groups = {UPLOADVIDEOAPI})
		public void TC_43_UploadVideo_Public_MediaContributor_withmandatecustomfieldvalue_valuenotgiven_Positive(ITestContext context) {
		
			logger.info("API Level Code is excuting");
			sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
			customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
			
			UserServices userservices = new UserServices();
			loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UploadVideosmediacontributor), surl);
			assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			logger.info("Login API response Code :::" + loginapiresponse);
			
			//Adding the custom field		
			launchURL(surl);
			homePage = loginPage.loginAs(sUserName, sPassword);
			homePage.clickSettingsLink();
			avengersystemsettingspage = homePage.clickSystemSettingsLink();
			avengercustomefieldspage=avengersystemsettingspage.click_CustomFields();
			avengercustomefieldspage.deleteallcustomfields();
			customReport.customizedReport(bundle.getString("customfields.nocustomfieldsmsg"),avengercustomefieldspage.verify_Label(bundle.getString("customfields.nocustomfieldsmsg")), statusValue, driver, sTestcaseName);
			
			avengercustomefieldspage.click_addfieldbutton();
			avengercustomefieldspage.setcustmofieldname(customfieldsbean);
			avengercustomefieldspage.click_RequiredOrDisaplayPublicOptions("Required", "YES");
			avengercustomefieldspage.click_submitbutton();
			customReport.customizedReport(customfieldsbean.getCustomfieldname(),avengercustomefieldspage.verify_Label(customfieldsbean.getCustomfieldname()), statusValue, driver, sTestcaseName);
			homePage.click_logout();
			
			//Upload Video
	 		UploadVideoService uploadvidoeservice = new UploadVideoService();
			apibeanfactory.UploadVideoBean(adduploadvideobean);	
			adduploadvideobean.setUploader(sUserName);
			adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);
			
			loginapiresponse.put("Mandatory", "No");
			loginapiresponse.put("fileName", "Yes");
															
			uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
			logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			context.setAttribute(sTestcaseName, uploadvidoeapiresponse);		
			
			//Verification through UI
			logger.info("Selenium Code is excuting");
			launchURL(surl);
			customReport.reporter("Application launch with URL : ", surl);
			homePage = loginPage.loginAs(sUserName, sPassword);
			AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
			avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
			AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
			customReport.customizedReport(true, commentspage.verify_VideoTitleText(adduploadvideobean.getTitle()),statusValue, driver, sTestcaseName);
			commentspage.click_settingsLink();
			commentspage.click_details();
			AvengerEditVideoSettingsPage avengereditvideosettingspage = commentspage.clickBasic_Settings();
			customReport.customizedReport(customfieldsbean.getCustomfieldname(),avengercustomefieldspage.verify_Label(customfieldsbean.getCustomfieldname()), statusValue, driver, sTestcaseName);
			avengereditvideosettingspage.clickCancel();
			commentspage.click_settingsLink();
			commentspage.click_deleteButton();
			
			//to delete mandatory custom field		
			homePage.clickSettingsLink();
			avengersystemsettingspage = homePage.clickSystemSettingsLink();
			avengercustomefieldspage=avengersystemsettingspage.click_CustomFields();
			avengercustomefieldspage.deleteallcustomfields();
			customReport.customizedReport(bundle.getString("customfields.nocustomfieldsmsg"),avengercustomefieldspage.verify_Label(bundle.getString("customfields.nocustomfieldsmsg")), statusValue, driver, sTestcaseName);			
			customReport.checkinglist(statusValue);	
		}
		
		/*@Test(description = "To verify legacy view count with accountadmin using Upload Video Api and migrate legacy View as valid count", groups = {UPLOADVIDEOAPI})
		public void TC_05_Post_UploadVideo_api_check_Migrate_LegacyView_AsValid_Count_Positive_AV29567(ITestContext context) {
		
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
	        LinkedHashMap<String, String> videimporteddetails=commentspage.getwebeximportedvideodetails();
	        customReport.customizedReport(adduploadvideobean.getLegacyViewCount(),viewcount_seachvideopage,statusValue, driver, sTestcaseName);
	        customReport.customizedReport(sUserName+" "+sUserName, videimporteddetails.get(IAPIConstantCodes.videoOwnername),statusValue, driver, sTestcaseName);
	        customReport.customizedReport(adduploadvideobean.getLegacyViewCount(),videimporteddetails.get(IAPIConstantCodes.videoTotalViews),statusValue, driver, sTestcaseName);
			customReport.checkinglist(statusValue);		
		}

		@Test(description = "To verify legacy view count with accountadmin using Upload Video Api and  migrate legacy View as negative count", groups = {UPLOADVIDEOAPI})
		public void TC_06_Post_UploadVideo_api_check_Migrate_LegacyView_AsNegative_Count_Negative_AV29567(ITestContext context) {
		
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
			adduploadvideobean.setLegacyViewCount("-"+apiutils.randomNumericals());
			
			loginapiresponse.put("Mandatory", "No");
			loginapiresponse.put("fileName", "Yes");
															
			uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
			logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			customReport.customizedReport(HttpStatusCode.httpsStatusCode400, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			context.setAttribute(sTestcaseName, uploadvidoeapiresponse);		
			customReport.checkinglist(statusValue);	
		}

		@Test(description = "To verify legacy view count with accountadmin using Upload Video Api and  migrate legacy View as empty", groups = {UPLOADVIDEOAPI})
		public void TC_07_Post_UploadVideo_api_check_Migrate_LegacyView_AsEmpty_Negative_AV29567(ITestContext context) {
		
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
			adduploadvideobean.setLegacyViewCount("  ");
			
			loginapiresponse.put("Mandatory", "No");
			loginapiresponse.put("fileName", "Yes");
															
			uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
			logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
			customReport.customizedReport(HttpStatusCode.httpsStatusCode400, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
			context.setAttribute(sTestcaseName, uploadvidoeapiresponse);		
			customReport.checkinglist(statusValue);	
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
		/*
		 * String url=homePage.getCurrentURL(); logger.info("url.split -"
		 * +url.split("/")[8]); //browserQuit(); homePage.click_logout(); return
		 * url.split("/")[8];
		 */

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
	
	
	public String get_userIdFromUI(String userName) {

		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		homePage.clickSettingsLink();
		AvengerUserDashboardPage avengerdashboardpage = homePage.clickUsersLink();
		avengerdashboardpage.clicknewuser_link(userName);
		String url=homePage.getCurrentURL(); 
	    logger.info("url.split -"+url.split("/")[8]); 
	    homePage.click_logout();
	    return url.split("/")[8];
		
	
	}
	
	public String get_customFieldIdFromUI(String customFieldName) {

		avengercustomefieldspage.click_customfieldeditbutton1(customFieldName);
		String url=homePage.getCurrentURL(); 
	    homePage.click_logout();
	    return url.split("/")[9];
		
	
	}
	
	public String get_groupIdFromUI(String grpName) {

		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		homePage.clickSettingsLink();
		avengergroupspage = homePage.clickGroupsLink();
		String grpname = avengergroupspage.createNewgroup(avengergroupsbeanpage);
		avengergroupsbeanpage.setNewgroup(grpname);
		avengergroupspage.clickNewGroup(avengergroupsbeanpage);
	    String url=homePage.getCurrentURL(); 
	    logger.info("url.split -"+url.split("/")[8]); 
	    //browserQuit(); 
	    homePage.click_logout();
	    return url.split("/")[8];
		
	
	}			
	}

