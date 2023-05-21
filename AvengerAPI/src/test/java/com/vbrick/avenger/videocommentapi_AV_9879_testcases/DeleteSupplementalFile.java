package com.vbrick.avenger.videocommentapi_AV_9879_testcases;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.io.FilenameUtils;
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
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUserAccountsService;
import com.vbricks.avenger.service.IUserVideoWatchingStatusService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.service.IVideoAccessControlService;
import com.vbricks.avenger.serviceimpl.DeleteSupplementalFilesAPI;
import com.vbricks.avenger.serviceimpl.GetVideoSupplementalFiles;
import com.vbricks.avenger.serviceimpl.UploadSupplementalServiceAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.serviceimpl.VideoAccessControlServiceAPI;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class DeleteSupplementalFile extends TestBase {

	private static Logger logger = Logger.getLogger(DeleteSupplementalFile.class);
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
	private HashMap<String, String> loginapiresponse1;
	private HashMap<String, String> uploadsupplementalapiresponse;
	private HashMap<String, String> uploadsupplementalapiresponse2;
	private HashMap<String, String> uploadsupplementalapiresponse3;
	private HashMap<String, String> uploadvidoeapiresponse;
	private HashMap<String, String> loginapiresponse_eventadmin;
	private HashMap<String, String> loginapiresponse_mediaviewer;
	private HashMap<String, String> loginapiresponse_mediaviewer1;
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

	@Test(description = " To Delete Supplemental File with accountadmin",groups = {DELETESUPPL_FILEAPI})
	public void TC_01_DeleteSupplementalFiles_AccountAdmin() throws InterruptedException {
	 
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
       
		//Upload Supplemenetal File
		UploadSupplementalServiceAPI uploadsupplementalservice=new UploadSupplementalServiceAPI();
		HashMap<String,String> supplementalFiles=uploadsupplementalservice.createJsonforSupplementalFiles(FilenameUtils.getBaseName(TEXTDOCUMENTPATH)+".txt"); 
 		uploadsupplementalapiresponse = uploadsupplementalservice.uploadSupplementalFile(uploadvidoeapiresponse, Setup.TEXTDOCUMENTPATH,supplementalFiles );
		logger.info("UploadVideo API response Code :::" + uploadsupplementalapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadsupplementalapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		//Delete Supplemental Files
		DeleteSupplementalFilesAPI deleteSupplementalFilesAPI=new DeleteSupplementalFilesAPI();
		HashMap<String,String> deleteSupplementalFilesAPIresponse = deleteSupplementalFilesAPI.deleteVideosupplementalfile(uploadvidoeapiresponse) ;
		logger.info("UploadVideo API response Code :::" + deleteSupplementalFilesAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(deleteSupplementalFilesAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deleteSupplementalFilesAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, deleteSupplementalFilesAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
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
		customReport.customizedReport(0, avengereditvideosettingspage.verifysupplementalfiles1(),statusValue, driver, sTestcaseName);
 		customReport.checkinglist(statusValue);
	}
	
	@Test(description = " To Delete Supplemental File with accountadmin",groups = {DELETESUPPL_FILEAPI})
	public void TC_02_DeleteSupplementalFiles_Invalid_VideoID_AccountAdmin(  ) throws InterruptedException {
	 
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
       
		//Upload Supplemenetal File
		UploadSupplementalServiceAPI uploadsupplementalservice=new UploadSupplementalServiceAPI();
		HashMap<String,String> supplementalFiles=uploadsupplementalservice.createJsonforSupplementalFiles(FilenameUtils.getBaseName(TEXTDOCUMENTPATH)+".txt"); 
 		uploadsupplementalapiresponse = uploadsupplementalservice.uploadSupplementalFile(uploadvidoeapiresponse, Setup.TEXTDOCUMENTPATH,supplementalFiles );
		logger.info("UploadVideo API response Code :::" + uploadsupplementalapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadsupplementalapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		//Delete Supplemental Files		 
		 loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.DeleteCommentMediaViewer), surl);
	     assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		 customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		 loginapiresponse1.put(IAPIConstantCodes.APIResponseVIDEOID, IDeleteSupplementalFile.invalidVideoId);
		 
		 DeleteSupplementalFilesAPI deleteSupplementalFilesAPI=new DeleteSupplementalFilesAPI();
		 HashMap<String,String> deleteSupplementalFilesAPIresponse = deleteSupplementalFilesAPI.deleteVideosupplementalfile(loginapiresponse1) ;
		 logger.info("DeleteSupplementalFile API response Code :::" + deleteSupplementalFilesAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		 assertionapiresponse.verifyAssert_httpCode(deleteSupplementalFilesAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		 customReport.customizedReport(HttpStatusCode.httpsStatus401, deleteSupplementalFilesAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		 customReport.checkinglist(statusValue);
		
	}
	
	@Test(description = " To Delete Supplemental File with accountadmin",groups = {DELETESUPPL_FILEAPI})
	public void TC_03_DeleteSupplementalFiles_No_SupplementalFile_Available_AccountAdmin(  ) throws InterruptedException {
	 
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
       
		//Delete Supplemental Files
		DeleteSupplementalFilesAPI deleteSupplementalFilesAPI=new DeleteSupplementalFilesAPI();
		HashMap<String,String> deleteSupplementalFilesAPIresponse = deleteSupplementalFilesAPI.deleteVideosupplementalfilewhenfilenotuploaded(uploadvidoeapiresponse) ;
		logger.info("DeleteSupplementalfile API response Code :::" + deleteSupplementalFilesAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(deleteSupplementalFilesAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deleteSupplementalFilesAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, deleteSupplementalFilesAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		customReport.customizedReport("There are no files to delete.}", deleteSupplementalFilesAPIresponse.get("code") ,statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		
	}
	
	@Test(description = " To Delete a specific Supplemental File with ID by accountadmin",groups = {DELETESUPPL_FILEAPI})
	public void TC_04_DeleteSupplementalFiles_With_ID_AccountAdmin() throws InterruptedException {
		 
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
       
		//Uploadiing "2" Supplemenetal Files and deleting "1" file with ID
		UploadSupplementalServiceAPI uploadsupplementalservice=new UploadSupplementalServiceAPI();
		HashMap<String,String> supplementalFiles=uploadsupplementalservice.createJsonforSupplementalFiles(FilenameUtils.getBaseName(TEXTDOCUMENTPATH)+".txt"); 
 		uploadsupplementalapiresponse = uploadsupplementalservice.uploadSupplementalFile(uploadvidoeapiresponse, Setup.TEXTDOCUMENTPATH,supplementalFiles );
		logger.info("UploadSupplementalFile1 API response Code :::" + uploadsupplementalapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadsupplementalapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		//Upload Supplemenetal File (2nd file)
		UploadSupplementalServiceAPI uploadsupplementalservice2=new UploadSupplementalServiceAPI();
		HashMap<String,String> supplementalFiles2 = uploadsupplementalservice2.createJsonforSupplementalFiles(FilenameUtils.getBaseName(WORDDOCUMENTPATH)+".txt"); 
		uploadsupplementalapiresponse2 = uploadsupplementalservice2.uploadSupplementalFile(uploadvidoeapiresponse, Setup.TEXTDOCUMENTPATH,supplementalFiles );
		logger.info("UploadSupplementalFile2 API response Code :::" + uploadsupplementalapiresponse2.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadsupplementalapiresponse2.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
				
		//Get Supplemental Files
		GetVideoSupplementalFiles getvideosupplemetalfileapiresponse = new GetVideoSupplementalFiles();
		HashMap<String, String> getvideosupplemetalfilesapiresponse = getvideosupplemetalfileapiresponse.getVideoSupplementalFiles(uploadvidoeapiresponse);
		assertionapiresponse.verifyAssert_httpCode(getvideosupplemetalfilesapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getvideosupplemetalfilesapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		uploadvidoeapiresponse.put("fileIds", getvideosupplemetalfilesapiresponse.get("supplementalfileid"));
				
		//Delete Supplemental Files
		DeleteSupplementalFilesAPI deleteSupplementalFilesAPI=new DeleteSupplementalFilesAPI();
		HashMap<String,String> deleteSupplementalFilesAPIresponse = deleteSupplementalFilesAPI.deleteVideosupplementalfilewithID(uploadvidoeapiresponse, getvideosupplemetalfilesapiresponse.get(("supplementalfileid")));
		assertionapiresponse.verifyAssert_httpCode(deleteSupplementalFilesAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deleteSupplementalFilesAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, deleteSupplementalFilesAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
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
		customReport.customizedReport(1, avengereditvideosettingspage.verifysupplementalfiles1(),statusValue, driver, sTestcaseName);
 		customReport.checkinglist(statusValue);
	}
	
	@Test(description = " To Delete a specific Supplemental File with ID by accountadmin",groups = {DELETESUPPL_FILEAPI})
	public void TC_05_Delete_Multiple_SupplementalFiles_With_IDs_AccountAdmin() throws InterruptedException {
		 
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
       
		//Uploadiing "3" Supplemenetal Files and deleting "2" files with IDs
		UploadSupplementalServiceAPI uploadsupplementalservice=new UploadSupplementalServiceAPI();
		HashMap<String,String> supplementalFiles=uploadsupplementalservice.createJsonforSupplementalFiles(FilenameUtils.getBaseName(TEXTDOCUMENTPATH)+".txt"); 
 		uploadsupplementalapiresponse = uploadsupplementalservice.uploadSupplementalFile(uploadvidoeapiresponse, Setup.TEXTDOCUMENTPATH,supplementalFiles );
		logger.info("UploadVideo API response Code :::" + uploadsupplementalapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadsupplementalapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		//Upload Supplemenetal File (2nd file)
		UploadSupplementalServiceAPI uploadsupplementalservice2=new UploadSupplementalServiceAPI();
		HashMap<String,String> supplementalFiles2 = uploadsupplementalservice2.createJsonforSupplementalFiles(FilenameUtils.getBaseName(TEXTDOCUMENTPATH)+".txt"); 
		uploadsupplementalapiresponse2 = uploadsupplementalservice2.uploadSupplementalFile(uploadvidoeapiresponse, Setup.TEXTDOCUMENTPATH,supplementalFiles );
		logger.info("UploadVideo API response Code :::" + uploadsupplementalapiresponse2.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadsupplementalapiresponse2.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
					
		//Get Supplemental Files
		GetVideoSupplementalFiles getvideosupplemetalfileapiresponse = new GetVideoSupplementalFiles();
		HashMap<String, String> getvideosupplemetalfilesapiresponse = getvideosupplemetalfileapiresponse.getVideomultipleSupplementalFiles(uploadvidoeapiresponse);
		assertionapiresponse.verifyAssert_httpCode(getvideosupplemetalfilesapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getvideosupplemetalfilesapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		uploadvidoeapiresponse.put("fileId1", getvideosupplemetalfilesapiresponse.get("supplementalfileid"));
		uploadvidoeapiresponse.put("fileId2", getvideosupplemetalfilesapiresponse.get("supplementalfileid2"));
				
		//Delete Supplemental Files
		DeleteSupplementalFilesAPI deleteSupplementalFilesAPI=new DeleteSupplementalFilesAPI();
		HashMap<String,String> deleteSupplementalFilesAPIresponse = deleteSupplementalFilesAPI.deletemultipleVideocommentswithID(uploadvidoeapiresponse, getvideosupplemetalfilesapiresponse.get(("supplementalfileid")));
		assertionapiresponse.verifyAssert_httpCode(deleteSupplementalFilesAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode)+deleteSupplementalFilesAPIresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, deleteSupplementalFilesAPIresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
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
		customReport.customizedReport(0, avengereditvideosettingspage.verifysupplementalfiles1(),statusValue, driver, sTestcaseName);
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