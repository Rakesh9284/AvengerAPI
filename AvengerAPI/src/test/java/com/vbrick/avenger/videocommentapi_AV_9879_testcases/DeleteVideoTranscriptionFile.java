package com.vbrick.avenger.videocommentapi_AV_9879_testcases;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.commons.io.FilenameUtils;
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
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
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
import com.vbricks.avenger.service.IDeleteSupplementalFile;
import com.vbricks.avenger.service.IDeleteTranscriptionFile;
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.service.IVideoAccessControlService;
import com.vbricks.avenger.serviceimpl.DeleteSupplementalFilesAPI;
import com.vbricks.avenger.serviceimpl.DeleteTranscriptionFileAPI;
import com.vbricks.avenger.serviceimpl.GetVideoTranscriptionFile;
import com.vbricks.avenger.serviceimpl.UploadTranscriptionServiceAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.serviceimpl.VideoAccessControlServiceAPI;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class DeleteVideoTranscriptionFile extends TestBase {

	private static Logger logger = Logger.getLogger(DeleteVideoTranscriptionFile.class);
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
	private AssertionAPIResponse assertionapiresponse;
	private HashMap<String, String> loginapiresponse;
	private HashMap<String, String> uploadvidoeapiresponse;
	private HashMap<String, String> uploadtranscriptionlapiresponse;
	private HashMap<String, String> loginapiresponse_eventadmin;
	private HashMap<String, String> loginapiresponse_mediaviewer;
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
		accesscontrolbeagPage = new VideoAccessControlBean();
		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		assertionapiresponse = new AssertionAPIResponse();
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

	@Test(description = " To Delete Single Transcription File of a specific video using Delete Transcription Files API with accountadmin",groups = {DELETETRANS_FILEAPI})
	public void TC_01_Delete_SingleTranscriptionFile_AccountAdmin() throws InterruptedException {
	 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
       
		UploadVideoService uploadvidoeservice = new UploadVideoService();
	    apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setIsActive("true");																																				
		adduploadvideobean.setEnableRatings("true");
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Uploading Transcription File
    	UploadTranscriptionServiceAPI uploadtranscriptionlservice=new UploadTranscriptionServiceAPI();
		HashMap<String,String> transcriptionFiles=uploadtranscriptionlservice.createJsonforTranscriptionFiles(FilenameUtils.getBaseName(SRTFILEPATH3)+".srt","en"); 
		uploadtranscriptionlapiresponse = uploadtranscriptionlservice.uploadTranscriptionFile(uploadvidoeapiresponse, Setup.SRTFILEPATH3,transcriptionFiles );
		logger.info("UploadVideo API response Code :::" + uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		Thread.sleep(10000);
		
		//Deleting Video Transcription Files
		DeleteTranscriptionFileAPI deleteTranscriptionFilesAPI=new DeleteTranscriptionFileAPI();
		HashMap<String,String> deleteVideotranscriptionfile = deleteTranscriptionFilesAPI.deleteVideotranscriptionfile(uploadvidoeapiresponse) ;
		logger.info("UploadVideo API response Code :::" + deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseHttpCode)+deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		AvengerEditVideoSettingsPage avengereditvideosettingspage=commentspage.click_settingsLink();
		avengereditvideosettingspage.clickSupplementMediaTab();
		customReport.customizedReport(0, avengereditvideosettingspage.verifytranscriptionfiles(),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description = " To Delete all Transcription Files of a specific video using Delete Transcription Files API with accountadmin",groups = {DELETETRANS_FILEAPI})
	public void TC_02_Delete_AllTranscriptionFiles_AccountAdmin() throws InterruptedException {
	 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
       
		UploadVideoService uploadvidoeservice = new UploadVideoService();
	    apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setIsActive("true");																																				
		adduploadvideobean.setEnableRatings("true");
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Uploading Transcription File1
    	UploadTranscriptionServiceAPI uploadtranscriptionlservice=new UploadTranscriptionServiceAPI();
		HashMap<String,String> transcriptionFiles=uploadtranscriptionlservice.createJsonforTranscriptionFiles(FilenameUtils.getBaseName(SRTFILEPATH3)+".srt","en"); 
		uploadtranscriptionlapiresponse = uploadtranscriptionlservice.uploadTranscriptionFile(uploadvidoeapiresponse, Setup.SRTFILEPATH3,transcriptionFiles );
		logger.info("UploadVideo API response Code :::" + uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		//Uploading Transcription File2		
    	UploadTranscriptionServiceAPI uploadtranscriptionlservice2=new UploadTranscriptionServiceAPI();
		HashMap<String,String> transcriptionFiles2=uploadtranscriptionlservice2.createJsonforTranscriptionFiles(FilenameUtils.getBaseName(SRTFILEPATH3)+".srt","de"); 
		uploadtranscriptionlapiresponse = uploadtranscriptionlservice2.uploadTranscriptionFile(uploadvidoeapiresponse, Setup.SRTFILEPATH3,transcriptionFiles2 );
		logger.info("UploadVideo API response Code :::" + uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		Thread.sleep(10000);
		
		//Deleting Video Transcription Files
		DeleteTranscriptionFileAPI deleteTranscriptionFilesAPI=new DeleteTranscriptionFileAPI();
		HashMap<String,String> deleteVideotranscriptionfile = deleteTranscriptionFilesAPI.deleteVideotranscriptionfile(uploadvidoeapiresponse) ;
		logger.info("UploadVideo API response Code :::" + deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseHttpCode)+deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		AvengerEditVideoSettingsPage avengereditvideosettingspage=commentspage.click_settingsLink();
		avengereditvideosettingspage.clickSupplementMediaTab();
		customReport.customizedReport(0, avengereditvideosettingspage.verifytranscriptionfiles(),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}	
	
	@Test(description = " To Delete Transcription Files when No Files available with accountadmin",groups = {DELETETRANS_FILEAPI})
	public void TC_03_DeleteTranscriptionFiles_WhenNOFilesAvailable_AccountAdmin() throws InterruptedException {
	 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
       
		UploadVideoService uploadvidoeservice = new UploadVideoService();
	    apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setIsActive("true");																																				
		adduploadvideobean.setEnableRatings("true");
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Deleting Video Transcription Files
		DeleteTranscriptionFileAPI deleteTranscriptionFilesAPI=new DeleteTranscriptionFileAPI();
		HashMap<String,String> deleteVideotranscriptionfile = deleteTranscriptionFilesAPI.deleteVideotranscriptionfilewhenfilenotuploaded(uploadvidoeapiresponse) ;
		logger.info("UploadVideo API response Code :::" + deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseHttpCode)+deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		customReport.customizedReport("There are no files to delete.}", deleteVideotranscriptionfile.get("code") ,statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}	
	
	@Test(description = " To Delete Single Transcription File of a specific video using Delete Transcription Files API with accountadmin",groups = {DELETETRANS_FILEAPI})
	public void TC_04_Delete_TranscriptionFile_WithInvalidVideoIDAccountAdmin() throws InterruptedException {
	 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
       
		UploadVideoService uploadvidoeservice = new UploadVideoService();
	    apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setIsActive("true");																																				
		adduploadvideobean.setEnableRatings("true");
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Uploading Transcription File
    	UploadTranscriptionServiceAPI uploadtranscriptionlservice=new UploadTranscriptionServiceAPI();
		HashMap<String,String> transcriptionFiles=uploadtranscriptionlservice.createJsonforTranscriptionFiles(FilenameUtils.getBaseName(SRTFILEPATH3)+".srt","en"); 
		uploadtranscriptionlapiresponse = uploadtranscriptionlservice.uploadTranscriptionFile(uploadvidoeapiresponse, Setup.SRTFILEPATH3,transcriptionFiles );
		logger.info("UploadVideo API response Code :::" + uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		//Deleting Video Transcription Files
		uploadvidoeapiresponse.put(IAPIConstantCodes.APIResponseVIDEOID, IDeleteTranscriptionFile.invalidVideoId);
		DeleteTranscriptionFileAPI deleteTranscriptionFilesAPI=new DeleteTranscriptionFileAPI();
		HashMap<String,String> deleteVideotranscriptionfile = deleteTranscriptionFilesAPI.deleteVideotranscriptionfile(uploadvidoeapiresponse) ;
		logger.info("UploadVideo API response Code :::" + deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
				
	}
	
	@Test(description = " To Delete Transcription File of a specific video using File ID using Delete Transcription Files API by accountadmin",groups = {DELETETRANS_FILEAPI})
	public void TC_05_Delete_TranscriptionFiles_WithValidFileID_AccountAdmin() throws InterruptedException {
	 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
        
		UploadVideoService uploadvidoeservice = new UploadVideoService();
	    apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setIsActive("true");																																				
		adduploadvideobean.setEnableRatings("true");
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Uploading Transcription File1
    	UploadTranscriptionServiceAPI uploadtranscriptionlservice=new UploadTranscriptionServiceAPI();
		HashMap<String,String> transcriptionFiles=uploadtranscriptionlservice.createJsonforTranscriptionFiles(FilenameUtils.getBaseName(SRTFILEPATH3)+".srt","en"); 
		uploadtranscriptionlapiresponse = uploadtranscriptionlservice.uploadTranscriptionFile(uploadvidoeapiresponse, Setup.SRTFILEPATH3,transcriptionFiles );
		logger.info("UploadVideo API response Code :::" + uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		//Uploading Transcription File2		
    	UploadTranscriptionServiceAPI uploadtranscriptionlservice2=new UploadTranscriptionServiceAPI();
		HashMap<String,String> transcriptionFiles2=uploadtranscriptionlservice2.createJsonforTranscriptionFiles(FilenameUtils.getBaseName(SRTFILEPATH3)+".srt","de"); 
		uploadtranscriptionlapiresponse = uploadtranscriptionlservice2.uploadTranscriptionFile(uploadvidoeapiresponse, Setup.SRTFILEPATH3,transcriptionFiles2 );
		logger.info("UploadVideo API response Code :::" + uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		//Get Video Transcription File IDs		
		GetVideoTranscriptionFile getVideoTranscriptionFileapirespose = new GetVideoTranscriptionFile();
		HashMap<String, String> getVideoTranscriptionFiles = getVideoTranscriptionFileapirespose.getVideoTranscriptionFiles(uploadvidoeapiresponse);
		assertionapiresponse.verifyAssert_httpCode(getVideoTranscriptionFiles.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getVideoTranscriptionFiles.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		//Deleting Video Transcription Files
		uploadvidoeapiresponse.put("fileid", getVideoTranscriptionFiles.get("transcriptionfileid"));
		DeleteTranscriptionFileAPI deleteTranscriptionFilesAPI=new DeleteTranscriptionFileAPI();
		HashMap<String,String> deleteVideotranscriptionfile = deleteTranscriptionFilesAPI.deleteVideotranscriptionfilewithID(uploadvidoeapiresponse) ;
		logger.info("UploadVideo API response Code :::" + deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseHttpCode)+deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		AvengerEditVideoSettingsPage avengereditvideosettingspage=commentspage.click_settingsLink();
		avengereditvideosettingspage.clickSupplementMediaTab();
		customReport.customizedReport(1, avengereditvideosettingspage.verifytranscriptionfiles(),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}	
	
	@Test(description = " To Delete Transcription File of a specific video using File ID using Delete Transcription Files API by accountadmin",groups = {DELETETRANS_FILEAPI})
	public void TC_06_Delete_MultipleTranscriptionFiles_WithValidFileIDs_AccountAdmin() throws InterruptedException {
	 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
       
		UploadVideoService uploadvidoeservice = new UploadVideoService();
	    apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setIsActive("true");																																				
		adduploadvideobean.setEnableRatings("true");
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Uploading Transcription File1
    	UploadTranscriptionServiceAPI uploadtranscriptionlservice=new UploadTranscriptionServiceAPI();
		HashMap<String,String> transcriptionFiles=uploadtranscriptionlservice.createJsonforTranscriptionFiles(FilenameUtils.getBaseName(SRTFILEPATH3)+".srt","en"); 
		uploadtranscriptionlapiresponse = uploadtranscriptionlservice.uploadTranscriptionFile(uploadvidoeapiresponse, Setup.SRTFILEPATH3,transcriptionFiles );
		logger.info("UploadVideo API response Code :::" + uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		//Uploading Transcription File2		
    	UploadTranscriptionServiceAPI uploadtranscriptionlservice2=new UploadTranscriptionServiceAPI();
		HashMap<String,String> transcriptionFiles2=uploadtranscriptionlservice2.createJsonforTranscriptionFiles(FilenameUtils.getBaseName(SRTFILEPATH3)+".srt","de"); 
		uploadtranscriptionlapiresponse = uploadtranscriptionlservice2.uploadTranscriptionFile(uploadvidoeapiresponse, Setup.SRTFILEPATH3,transcriptionFiles2 );
		logger.info("UploadVideo API response Code :::" + uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);		

		//Uploading Transcription File3	
    	UploadTranscriptionServiceAPI uploadtranscriptionlservice3=new UploadTranscriptionServiceAPI();
		HashMap<String,String> transcriptionFiles3=uploadtranscriptionlservice3.createJsonforTranscriptionFiles(FilenameUtils.getBaseName(SRTFILEPATH3)+".srt","tr"); 
		uploadtranscriptionlapiresponse = uploadtranscriptionlservice3.uploadTranscriptionFile(uploadvidoeapiresponse, Setup.SRTFILEPATH3,transcriptionFiles3 );
		logger.info("UploadVideo API response Code :::" + uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);	
		Thread.sleep(10000);
		
		//Get Video Transcription File IDs
		GetVideoTranscriptionFile getVideoTranscriptionFileapirespose = new GetVideoTranscriptionFile();
		HashMap<String, String> getVideoTranscriptionFiles = getVideoTranscriptionFileapirespose.getVideomultipleTranscriptionFiles(uploadvidoeapiresponse);
		assertionapiresponse.verifyAssert_httpCode(getVideoTranscriptionFiles.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getVideoTranscriptionFiles.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		//Deleting Video Transcription Files
		uploadvidoeapiresponse.put("fileid1", getVideoTranscriptionFiles.get("transcriptionfileid1"));
		uploadvidoeapiresponse.put("fileid2", getVideoTranscriptionFiles.get("transcriptionfileid2"));
		uploadvidoeapiresponse.put("fileid3", getVideoTranscriptionFiles.get("transcriptionfileid3"));
		
		DeleteTranscriptionFileAPI deleteTranscriptionFilesAPI=new DeleteTranscriptionFileAPI();
		HashMap<String,String> deleteVideotranscriptionfile = deleteTranscriptionFilesAPI.deleteVideotranscriptionfileswithmultipleIDs(uploadvidoeapiresponse) ;
		logger.info("Delete Video Transcription File API response Code :::" + deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseHttpCode)+deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		logger.info(deleteVideotranscriptionfile);
		
		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		AvengerEditVideoSettingsPage avengereditvideosettingspage=commentspage.click_settingsLink();
		avengereditvideosettingspage.clickSupplementMediaTab();
		customReport.customizedReport(1, avengereditvideosettingspage.verifytranscriptionfiles(),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}	
	
	@Test(description = " To Delete Transcription File of a specific video with Invalid File ID using Delete Transcription Files API by accountadmin",groups = {DELETETRANS_FILEAPI})
	public void TC_07_Delete_TranscriptionFile_WithInValidFileID_AccountAdmin() throws InterruptedException {
	 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
        
		UploadVideoService uploadvidoeservice = new UploadVideoService();
	    apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setIsActive("true");																																				
		adduploadvideobean.setEnableRatings("true");
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Uploading Transcription File
    	UploadTranscriptionServiceAPI uploadtranscriptionlservice=new UploadTranscriptionServiceAPI();
		HashMap<String,String> transcriptionFiles=uploadtranscriptionlservice.createJsonforTranscriptionFiles(FilenameUtils.getBaseName(SRTFILEPATH3)+".srt","en"); 
		uploadtranscriptionlapiresponse = uploadtranscriptionlservice.uploadTranscriptionFile(uploadvidoeapiresponse, Setup.SRTFILEPATH3,transcriptionFiles );
		logger.info("UploadVideo API response Code :::" + uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		//Uploading Transcription File2		
    	UploadTranscriptionServiceAPI uploadtranscriptionlservice2=new UploadTranscriptionServiceAPI();
		HashMap<String,String> transcriptionFiles2=uploadtranscriptionlservice2.createJsonforTranscriptionFiles(FilenameUtils.getBaseName(SRTFILEPATH3)+".srt","de"); 
		uploadtranscriptionlapiresponse = uploadtranscriptionlservice2.uploadTranscriptionFile(uploadvidoeapiresponse, Setup.SRTFILEPATH3,transcriptionFiles2 );
		logger.info("UploadVideo API response Code :::" + uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		//Get Video Transcription File IDs		
		GetVideoTranscriptionFile getVideoTranscriptionFileapirespose = new GetVideoTranscriptionFile();
		HashMap<String, String> getVideoTranscriptionFiles = getVideoTranscriptionFileapirespose.getVideoTranscriptionFiles(uploadvidoeapiresponse);
		assertionapiresponse.verifyAssert_httpCode(getVideoTranscriptionFiles.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getVideoTranscriptionFiles.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		//Deleting Video Transcription Files
		uploadvidoeapiresponse.put("fileid", "engl");
		DeleteTranscriptionFileAPI deleteTranscriptionFilesAPI=new DeleteTranscriptionFileAPI();
		HashMap<String,String> deleteVideotranscriptionfile = deleteTranscriptionFilesAPI.deleteVideotranscriptionfilewithID(uploadvidoeapiresponse) ;
		logger.info("UploadVideo API response Code :::" + deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus400);
		customReport.customizedReport(HttpStatusCode.httpsStatus400, deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}	
	
	@Test(description = " To Delete Single Transcription File of a specific video using Delete Transcription Files API with user having only view access",groups = {DELETETRANS_FILEAPI})
	public void TC_08_Delete_SingleTranscriptionFile_WithOnlyViewAccess_NEGATIVE() throws InterruptedException {
	 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
        
		UploadVideoService uploadvidoeservice = new UploadVideoService();
	    apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setIsActive("true");																																				
		adduploadvideobean.setEnableRatings("true");
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Uploading Transcription File
    	UploadTranscriptionServiceAPI uploadtranscriptionlservice=new UploadTranscriptionServiceAPI();
		HashMap<String,String> transcriptionFiles=uploadtranscriptionlservice.createJsonforTranscriptionFiles(FilenameUtils.getBaseName(SRTFILEPATH3)+".srt","en"); 
		uploadtranscriptionlapiresponse = uploadtranscriptionlservice.uploadTranscriptionFile(uploadvidoeapiresponse, Setup.SRTFILEPATH3,transcriptionFiles );
		logger.info("UploadVideo API response Code :::" + uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		UserServices userservices_mediaviewer = new UserServices();
		loginapiresponse_mediaviewer = userservices_mediaviewer.doLogin(apiutils.userJson(IUsersList.DeleteTranscriptionFile), surl);
		loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIVIDEOID));
		
		//Deleting Video Transcription Files
		DeleteTranscriptionFileAPI deleteTranscriptionFilesAPI=new DeleteTranscriptionFileAPI();
		HashMap<String,String> deleteVideotranscriptionfile = deleteTranscriptionFilesAPI.deleteVideotranscriptionfile(loginapiresponse_mediaviewer) ;
		logger.info("UploadVideo API response Code :::" + deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description = " To Delete Single Transcription File of a specific video using Delete Transcription Files API with user who do not have view access",groups = {DELETETRANS_FILEAPI})
	public void TC_09_Delete_SingleTranscriptionFile_WithNOViewAccess_NEGATIVE() throws InterruptedException {
	 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
       
		UploadVideoService uploadvidoeservice = new UploadVideoService();
	    apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments("true");
		adduploadvideobean.setIsActive("true");																																				
		adduploadvideobean.setEnableRatings("true");
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//Uploading Transcription File
    	UploadTranscriptionServiceAPI uploadtranscriptionlservice=new UploadTranscriptionServiceAPI();
		HashMap<String,String> transcriptionFiles=uploadtranscriptionlservice.createJsonforTranscriptionFiles(FilenameUtils.getBaseName(SRTFILEPATH3)+".srt","en"); 
		uploadtranscriptionlapiresponse = uploadtranscriptionlservice.uploadTranscriptionFile(uploadvidoeapiresponse, Setup.SRTFILEPATH3,transcriptionFiles );
		logger.info("UploadVideo API response Code :::" + uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		UserServices userservices_mediaviewer = new UserServices();
		loginapiresponse_mediaviewer = userservices_mediaviewer.doLogin(apiutils.userJson(IUsersList.DeleteTranscriptionFile), surl);
		loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIVIDEOID));
		
		//Deleting Video Transcription Files
		DeleteTranscriptionFileAPI deleteTranscriptionFilesAPI=new DeleteTranscriptionFileAPI();
		HashMap<String,String> deleteVideotranscriptionfile = deleteTranscriptionFilesAPI.deleteVideotranscriptionfile(loginapiresponse_mediaviewer) ;
		logger.info("UploadVideo API response Code :::" + deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description = " To Delete Single Transcription File of a specific video using Delete Transcription Files API by giving edit access to a user",groups = {DELETETRANS_FILEAPI})
	public void TC_10_Delete_SingleTranscriptionFile_WithEditAccess() throws InterruptedException {
		
		 logger.info("API Level Code is excuting");
         sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();        
         customReport.reporter("Upload Vidoe API TestCase is Runnning.. : ", surl);
         
         UserServices userservices = new UserServices();
         loginapiresponse= userservices.doLogin(apiutils.userJson(sUserName), surl);
         assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
         customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
         logger.info("Login API response Code :::" + loginapiresponse);
        
         UploadVideoService uploadvidoeservice = new UploadVideoService();
         apibeanfactory.UploadVideoBean(adduploadvideobean);
         adduploadvideobean.setUploader(sUserName);
         adduploadvideobean.setVideoAccessControl("Private");
 	     loginapiresponse.put("Mandatory", "No");
 		 loginapiresponse.put("fileName", "No");
         uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
         logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
         customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
         VideoAccessControlServiceAPI videoaccesscontrolservice=new VideoAccessControlServiceAPI();
         accesscontrolbeagPage.setUsercanEdit("true");
         accesscontrolbeagPage.setUsertype("User");
         
  		UserServices userservices_mediaviewer = new UserServices();
 		loginapiresponse_mediaviewer = userservices_mediaviewer.doLogin(apiutils.userJson(IUsersList.DeleteTranscriptionFile), surl);
 		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediaviewer.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
        customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_mediaviewer.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
        logger.info("Login API response Code :::" + loginapiresponse_mediaviewer);
 		
        loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIVIDEOID));
 		
        accesscontrolbeagPage.setUserid(loginapiresponse_mediaviewer.get("userId"));
        videoaccesscontrolservice.videoAccessControl(uploadvidoeapiresponse,videoaccesscontrolservice.createJsonVideoAccessControl(accesscontrolbeagPage, IVideoAccessControlService.jsonFlagUser));
 		
        //Uploading Transcription File
     	UploadTranscriptionServiceAPI uploadtranscriptionlservice=new UploadTranscriptionServiceAPI();
 		HashMap<String,String> transcriptionFiles=uploadtranscriptionlservice.createJsonforTranscriptionFiles(FilenameUtils.getBaseName(SRTFILEPATH3)+".srt","en"); 
 		uploadtranscriptionlapiresponse = uploadtranscriptionlservice.uploadTranscriptionFile(uploadvidoeapiresponse, Setup.SRTFILEPATH3,transcriptionFiles );
 		logger.info("Uploadtranscriptional API response Code :::" + uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
 		assertionapiresponse.verifyAssert_httpCode(uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),HttpStatusCode.httpsStatusCode);
 		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadtranscriptionlapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);

 		Thread.sleep(120000);
 		//Deleting Video Transcription Files
 		DeleteTranscriptionFileAPI deleteTranscriptionFilesAPI=new DeleteTranscriptionFileAPI();
 		HashMap<String,String> deleteVideotranscriptionfile = deleteTranscriptionFilesAPI.deleteVideotranscriptionfile(loginapiresponse_mediaviewer) ;
 		logger.info("Deleting Video Transcription Files :::" + deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseHttpCode));
 		assertionapiresponse.verifyAssert_httpCode(deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
 		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, deleteVideotranscriptionfile.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
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