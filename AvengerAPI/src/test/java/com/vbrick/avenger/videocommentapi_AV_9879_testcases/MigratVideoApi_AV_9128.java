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
import com.vbricks.avenger.service.IMegratVideoService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.MigratVideoAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class MigratVideoApi_AV_9128 extends TestBase {

	private static Logger logger = Logger.getLogger(MigratVideoApi_AV_9128.class);
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

	@Test(description = "To verify  Video upload with accountadmin  using Migrat Content API with mediaadmin",groups = {MIGRATEVIDEOAPI})
	public void TC_01_PUT_MigratVideo_api_check_MigrateVideo_from_AccoutAdmin_to_mediaadminPositive() {

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
		
		PUTMigrateVideoBean migratevideobean=new PUTMigrateVideoBean();
		migratevideobean.setUserName(IMegratVideoService.mediaadmin);
	
		MigratVideoAPI migratvideoAPI=new MigratVideoAPI();
	   	String whenUploaded=apiutils.UTCdatetimeformatMigrateVideo("1");
	   	migratevideobean.setWhenUploaded(whenUploaded);
	   	
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
		commentspage.clickVideoBasicinformationButton();
        LinkedHashMap<String, String> videimporteddetails=commentspage.getwebeximportedvideodetails();
     	customReport.customizedReport(IMegratVideoService.mediaadmin+" "+IMegratVideoService.mediaadmin, videimporteddetails.get(IAPIConstantCodes.VIDEOUPLOADER),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);		
	    
	}
	@Test(description = "To verify  Video upload with accountadmin  using Migrat Content API with mediacontributor",groups = {MIGRATEVIDEOAPI})
	public void TC_02_PUT_MigratVideo_api_check_MigrateVideo_from_AccoutAdmin_to_mediacontributor_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);		
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		PUTMigrateVideoBean migratevideobean=new PUTMigrateVideoBean();
		migratevideobean.setUserName(IMegratVideoService.mediacontributor);
		
		MigratVideoAPI migratvideoAPI=new MigratVideoAPI();
	   	String whenUploaded=apiutils.UTCdatetimeformatMigrateVideo("1");
	   	migratevideobean.setWhenUploaded(whenUploaded);
	   	
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
		commentspage.clickVideoBasicinformationButton();
        LinkedHashMap<String, String> videimporteddetails=commentspage.getwebeximportedvideodetails();
     	customReport.customizedReport(IMegratVideoService.mediacontributor+" "+IMegratVideoService.mediacontributor, videimporteddetails.get(IAPIConstantCodes.VIDEOUPLOADER),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify  Video upload with mediaadmin  using Migrat Content API with accountadmin",groups = {MIGRATEVIDEOAPI})
	public void TC_03_PUT_MigratVideo_api_check_MigrateVideo_from_mediaadmin_to_accountadmin_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.MigrateVideomediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.MigrateVideomediaadmin);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	
		PUTMigrateVideoBean migratevideobean=new PUTMigrateVideoBean();
		migratevideobean.setUserName(IMegratVideoService.accountadmin);
		
		MigratVideoAPI migratvideoAPI=new MigratVideoAPI();
	   	String whenUploaded=apiutils.UTCdatetimeformatMigrateVideo("1");
	   	migratevideobean.setWhenUploaded(whenUploaded);
	   	
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
		commentspage.clickVideoBasicinformationButton();
        LinkedHashMap<String, String> videimporteddetails=commentspage.getwebeximportedvideodetails();
     	customReport.customizedReport(IMegratVideoService.accountadmin+" "+IMegratVideoService.accountadmin, videimporteddetails.get(IAPIConstantCodes.VIDEOUPLOADER),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = "To verify  Video upload with mediaadmin  using Migrat Content API with mediacontributor",groups = {MIGRATEVIDEOAPI})
	public void TC_04_PUT_MigratVideo_api_check_MigrateVideo_from_MediaAdmin_to_mediacontributor_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.MigrateVideomediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.MigrateVideomediaadmin);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		PUTMigrateVideoBean migratevideobean=new PUTMigrateVideoBean();
		migratevideobean.setUserName(IMegratVideoService.mediacontributor);
		
		MigratVideoAPI migratvideoAPI=new MigratVideoAPI();
	   	String whenUploaded=apiutils.UTCdatetimeformatMigrateVideo("1");
	   	migratevideobean.setWhenUploaded(whenUploaded);
	   	
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
		commentspage.clickVideoBasicinformationButton();
        LinkedHashMap<String, String> videimporteddetails=commentspage.getwebeximportedvideodetails();
     	customReport.customizedReport(IMegratVideoService.mediacontributor+" "+IMegratVideoService.mediacontributor, videimporteddetails.get(IAPIConstantCodes.VIDEOUPLOADER),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);	
	    
	}
	
	@Test(description = "To verify  Video upload with mediacontributor  using Migrat Content API with accountadmin",groups = {MIGRATEVIDEOAPI})
	public void TC_05_PUT_MigratVideo_api_check_MigrateVideo_from_mediacontributor_to_accountadmin_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.MigrateVideomediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.MigrateVideomediacontributor);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		PUTMigrateVideoBean migratevideobean=new PUTMigrateVideoBean();
		migratevideobean.setUserName(IMegratVideoService.accountadmin);
		
		MigratVideoAPI migratvideoAPI=new MigratVideoAPI();
	   	String whenUploaded=apiutils.UTCdatetimeformatMigrateVideo("1");
	   	migratevideobean.setWhenUploaded(whenUploaded);
	   	
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
		commentspage.clickVideoBasicinformationButton();
        LinkedHashMap<String, String> videimporteddetails=commentspage.getwebeximportedvideodetails();
     	customReport.customizedReport(IMegratVideoService.accountadmin+" "+IMegratVideoService.accountadmin, videimporteddetails.get(IAPIConstantCodes.VIDEOUPLOADER),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);	
	}
	
	@Test(description = "To verify  Video upload with mediacontributor  using Migrate Content API with mediaadmin",groups = {MIGRATEVIDEOAPI})
	public void TC_06_PUT_MigratVideo_api_check_MigrateVideo_from_mediacontributor_to_mediaadminPositive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.MigrateVideomediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.MigrateVideomediacontributor);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		PUTMigrateVideoBean migratevideobean=new PUTMigrateVideoBean();
		migratevideobean.setUserName(IMegratVideoService.mediaadmin);
		
		MigratVideoAPI migratvideoAPI=new MigratVideoAPI();
	   	String whenUploaded=apiutils.UTCdatetimeformatMigrateVideo("1");
	   	migratevideobean.setWhenUploaded(whenUploaded);
	   	
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
		commentspage.clickVideoBasicinformationButton();
        LinkedHashMap<String, String> videimporteddetails=commentspage.getwebeximportedvideodetails();
  		customReport.customizedReport(IMegratVideoService.mediaadmin+" "+IMegratVideoService.mediaadmin, videimporteddetails.get(IAPIConstantCodes.VIDEOUPLOADER),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);	
	}
	@Test(description = "To verify  Video upload with accountadmin  using Migrat Content API with Eventadmin",groups = {MIGRATEVIDEOAPI})
	public void TC_07_PUT_MigratVideo_api_check_MigrateVideo_from_AccoutAdmin_to_Eventadmin_Negative() {

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
		
		PUTMigrateVideoBean migratevideobean=new PUTMigrateVideoBean();
		migratevideobean.setUserName(IMegratVideoService.evenadmin);
		
		MigratVideoAPI migratvideoAPI=new MigratVideoAPI();
	   	String whenUploaded=apiutils.UTCdatetimeformatMigrateVideo("1");
	   	migratevideobean.setWhenUploaded(whenUploaded);
	   	
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
		commentspage.clickVideoBasicinformationButton();
        LinkedHashMap<String, String> videimporteddetails=commentspage.getwebeximportedvideodetails();
    	customReport.customizedReport(IMegratVideoService.evenadmin+" "+IMegratVideoService.evenadmin, videimporteddetails.get(IAPIConstantCodes.VIDEOUPLOADER),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);	
	
	}
	
	@Test(description = "To verify  Video upload with accountadmin  using Migrat Content API with mediaviewer",groups = {MIGRATEVIDEOAPI})
	public void TC_08_PUT_MigratVideo_api_check_MigrateVideo_from_AccoutAdmin_to_mediaviewer_Negative() {

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
		
		PUTMigrateVideoBean migratevideobean=new PUTMigrateVideoBean();
		migratevideobean.setUserName(IMegratVideoService.mediaviewer);
		
		MigratVideoAPI migratvideoAPI=new MigratVideoAPI();
	   	String whenUploaded=apiutils.UTCdatetimeformatMigrateVideo("1");
	   	migratevideobean.setWhenUploaded(whenUploaded);
	   	
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
		commentspage.clickVideoBasicinformationButton();
        LinkedHashMap<String, String> videimporteddetails=commentspage.getwebeximportedvideodetails();
 		customReport.customizedReport(IMegratVideoService.mediaviewer+" "+IMegratVideoService.mediaviewer, videimporteddetails.get(IAPIConstantCodes.VIDEOUPLOADER),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);		    
	}
	
	//Made username and whenuploaded fields optional in 7.31 and so changing TC_09 and TC_11 as positive cases which should now pass
	@Test(description = "To verify  Video upload with accountadmin  using Migrat Content API with Null",groups = {MIGRATEVIDEOAPI})
	public void TC_09_PUT_MigratVideo_api_check_MigrateVideo_from_AccoutAdmin_to_Null_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		PUTMigrateVideoBean migratevideobean=new PUTMigrateVideoBean();
		migratevideobean.setUserName(IMegratVideoService.Null);
		
		MigratVideoAPI migratvideoAPI=new MigratVideoAPI();
	   	String whenUploaded=apiutils.UTCdatetimeformatMigrateVideo("1");
	   	migratevideobean.setWhenUploaded(whenUploaded);
	   	
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
		customReport.checkinglist(statusValue);		    
	}
	
	@Test(description = "To verify  Video upload with accountadmin  using Migrat Content API with TimeWrongFormat",groups = {MIGRATEVIDEOAPI})
	public void TC_10_PUT_MigratVideo_api_check_MigrateVideo_from_AccoutAdmin_to_TimeWrongFormat_Negative() {

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
		
		PUTMigrateVideoBean migratevideobean=new PUTMigrateVideoBean();
		migratevideobean.setUserName(IMegratVideoService.whenUploadedwrongformat);
		
		MigratVideoAPI migratvideoAPI=new MigratVideoAPI();
	   	String whenUploaded=apiutils.UTCdatetimeformatMigrateVideo("1");
	   	migratevideobean.setWhenUploaded(whenUploaded);
	   	
	    HashMap<String, String> migratvideoapirespone = migratvideoAPI.migratVideo(uploadvidoeapiresponse,migratevideobean); 
	    assertionapiresponse.verifyAssert_httpCode(migratvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus500);
	    customReport.customizedReport(HttpStatusCode.httpsStatus500, migratvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);
	}
	
	//Made username and whenuploaded fields optional in 7.31 and so changing TC_09 and TC_11 as positive cases which should now pass
	@Test(description = "To verify  Video upload with accountadmin  using Migrat Content API by keeping whenuploaded Null",groups = {MIGRATEVIDEOAPI})
	public void TC_11_PUT_MigratVideo_api_check_MigrateVideo_from_AccoutAdmin_notgiving_whenuploaded_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		PUTMigrateVideoBean migratevideobean=new PUTMigrateVideoBean();
		migratevideobean.setUserName(IMegratVideoService.mediaviewer);
		
		MigratVideoAPI migratvideoAPI=new MigratVideoAPI();
	   	String whenUploaded="";
	   	migratevideobean.setWhenUploaded(whenUploaded);
	   	
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
		customReport.checkinglist(statusValue);		    
	}

	@Test(description = "To verify  Video upload with accountadmin  using Migrat Content API and migrate owner to mediaadmin and Legacy view as valid count ",groups = {MIGRATEVIDEOAPI})
	public void TC_01_PUT_MigratVideo_api_check_MigrateVideo_Owner_from_AccoutAdmin_to_mediaadmin_AndLegacyView_asValid_Count_Positive_AV29567() {

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
		
	    
	    logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		String viewcount_seachvideopage=avengerdashboardpage.getviews_searchvideoPage(migratevideobean.getLegacyViewCount());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.clickVideoBasicinformationButton();
        LinkedHashMap<String, String> videimporteddetails=commentspage.getwebeximportedvideodetails();
        customReport.customizedReport(migratevideobean.getLegacyViewCount(),viewcount_seachvideopage,statusValue, driver, sTestcaseName);
        customReport.customizedReport(IMegratVideoService.mediaadmin+" "+IMegratVideoService.mediaadmin, videimporteddetails.get(IAPIConstantCodes.videoOwnername),statusValue, driver, sTestcaseName);
        customReport.customizedReport(migratevideobean.getLegacyViewCount(),videimporteddetails.get(IAPIConstantCodes.videoTotalViews),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);		
	    
	}
	
	@Test(description = "To verify  Video upload with accountadmin  using Migrat Content API and migrate owner to mediaviewer and Legacy view as valid count ",groups = {MIGRATEVIDEOAPI})
	public void TC_02_PUT_MigratVideo_api_check_MigrateVideo_Owner_from_AccoutAdmin_to_mediaviewer_AndLegacyView_asValid_Count_Positive_AV29567() {

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
		
		//login as mediaviewer
		HashMap<String, String>	loginapiresponse_mediaviewer = userservices.doLogin(apiutils.userJson(IMegratVideoService.mediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediaviewer.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse_mediaviewer);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_mediaviewer.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		PUTMigrateVideoBean migratevideobean=new PUTMigrateVideoBean();
		migratevideobean.setOwner("Yes");
		migratevideobean.setUserId(loginapiresponse_mediaviewer.get(IAPIConstantCodes.APIResponseUSERID));
		migratevideobean.setownerUserName(loginapiresponse_mediaviewer.get(IAPIConstantCodes.APIUSERNAME));
		migratevideobean.setEmail(loginapiresponse_mediaviewer.get(IAPIConstantCodes.APIUSEREMAIL));
	 	String date=apiutils.minusdaysUTCformat("1");
	 	String [] splitDate = date.split("T");
        String publishdate = splitDate[0];
        migratevideobean.setPublishDate(publishdate);
		migratevideobean.setLegacyViewCount(apiutils.randomNumericals());
		
		 
		
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
		String viewcount_seachvideopage=avengerdashboardpage.getviews_searchvideoPage(migratevideobean.getLegacyViewCount());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		commentspage.clickVideoBasicinformationButton();
        LinkedHashMap<String, String> videimporteddetails=commentspage.getwebeximportedvideodetails();
        customReport.customizedReport(migratevideobean.getLegacyViewCount(),viewcount_seachvideopage,statusValue, driver, sTestcaseName);
        customReport.customizedReport(IMegratVideoService.mediaviewer+" "+IMegratVideoService.mediaviewer, videimporteddetails.get(IAPIConstantCodes.videoOwnername),statusValue, driver, sTestcaseName);
        customReport.customizedReport(migratevideobean.getLegacyViewCount(),videimporteddetails.get(IAPIConstantCodes.videoTotalViews),statusValue, driver, sTestcaseName);
        /*commentspage.click_settingsLink();
        AvengerEditVideoSettingsPage avengereditvideosttingspage=commentspage.click_details();
        customReport.customizedReport(true,migratevideobean.getPublishDate().contains(avengereditvideosttingspage.getPublishDate()),statusValue, driver, sTestcaseName);*/
		customReport.checkinglist(statusValue);		
	    
	}
	
	
	@Test(description = "To verify  Video upload with accountadmin  using Migrat Content API and Legacy view as negative count ",groups = {MIGRATEVIDEOAPI})
	public void TC_03_PUT_MigratVideo_api_check_MigrateVideo_AndLegacyView_asNegative_Count_Negative_AV29567() {

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
		 
		PUTMigrateVideoBean migratevideobean=new PUTMigrateVideoBean();
		migratevideobean.setLegacyViewCount("-"+apiutils.randomNumericals());
		 
		MigratVideoAPI migratvideoAPI=new MigratVideoAPI();
	    HashMap<String, String> migratvideoapirespone = migratvideoAPI.migratVideo(uploadvidoeapiresponse,migratevideobean); 	
	    assertionapiresponse.verifyAssert_httpCode(migratvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus400);
	    customReport.customizedReport(HttpStatusCode.httpsStatus400, migratvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	    customReport.checkinglist(statusValue);	
	}
	
	@Test(description = "To verify  Video upload with accountadmin  using Migrat Content API and Legacy view as empty ",groups = {MIGRATEVIDEOAPI})
	public void TC_04_PUT_MigratVideo_api_check_MigrateVideo_AndLegacyView_asEmpty_Negative_AV29567() {

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
		 
		PUTMigrateVideoBean migratevideobean=new PUTMigrateVideoBean();
		migratevideobean.setLegacyViewCount("   ");
		 
		MigratVideoAPI migratvideoAPI=new MigratVideoAPI();
	    HashMap<String, String> migratvideoapirespone = migratvideoAPI.migratVideo(uploadvidoeapiresponse,migratevideobean); 	
	    assertionapiresponse.verifyAssert_httpCode(migratvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus500);
	    customReport.customizedReport(HttpStatusCode.httpsStatus500, migratvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
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