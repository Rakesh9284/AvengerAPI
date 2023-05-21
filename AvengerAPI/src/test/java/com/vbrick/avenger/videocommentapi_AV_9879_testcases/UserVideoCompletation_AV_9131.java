package com.vbrick.avenger.videocommentapi_AV_9879_testcases;


import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
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
import com.vbrick.avenger.apibeans.VideoAccessControlBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.DateTime;
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
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUserAccountsService;
import com.vbricks.avenger.service.IUserVideoWatchingStatusService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.service.IVideoAccessControlService;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.serviceimpl.UserVideoCompletionAPI;
import com.vbricks.avenger.serviceimpl.VideoAccessControlServiceAPI;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class UserVideoCompletation_AV_9131 extends TestBase {

       private static Logger logger = Logger.getLogger(UserVideoCompletation_AV_9131.class);
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
       private HashMap<String, String> loginapiresponse1;
       private HashMap<String, String> uploadvidoeapiresponse;
       private HashMap<String,String> loginapiresponse_mediaviewer;
       private HashMap<String,String> loginapiresponse_eventadmin;
       private HashMap<String,String> loginapiresponse_mediacomtributor;
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

       @Test(description = "To verify Account Admin is able to watch Video using UserVideoCompletation API ",groups = {GETVIDEOWATCHINGSTATUSAPI})
       public void TC_01_GET_UserVideoCompletation_api_check_with_AccoutAdmin_Positive(ITestContext context) throws InterruptedException {

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
              adduploadvideobean.setEnableDownloads(IAPIConstantCodes.TRUE);
              adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);
              
      	      loginapiresponse.put("Mandatory", "No");
      	      loginapiresponse.put("fileName", "No");
              
              uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
              logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
              assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
              customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
              context.setAttribute("sTestcaseName", uploadvidoeapiresponse);
       
              //Selenium Code
              launchURL(surl);       
              customReport.reporter("Application launch with URL : ", surl);
              homePage = loginPage.loginAs(sUserName, sPassword);
              AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
              avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
              AvengerVideoCommentsPage avengervideovommentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
              customReport.customizedReport(true, avengervideovommentspage.getVideoButton().contains("play"), statusValue, driver, sTestcaseName);
              avengervideovommentspage.clickPlayButton();
              customReport.customizedReport(false, avengervideovommentspage.getVideoStatus().contains("is-not-playing"), statusValue, driver, sTestcaseName);
              Thread.sleep(30000);
              customReport.customizedReport(false, avengervideovommentspage.getVideoButton().contains("pause"), statusValue, driver, sTestcaseName);
              avengervideovommentspage.refreshBrowser();
              Thread.sleep(90000);
              
              loginapiresponse1 = userservices.doLogin(apiutils.userJson(sUserName), surl);
              assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
              logger.info("Login API response Code :::" + loginapiresponse1);
              customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
              uploadvidoeapiresponse.put(IAPIConstantCodes.APIResponseUSERID, loginapiresponse1.get(IAPIConstantCodes.APIResponseUSERID));
              uploadvidoeapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, loginapiresponse1.get(IAPIConstantCodes.APIRequestAccessToken));
                          
              UserVideoCompletionAPI userVideoCompletionAPI = new UserVideoCompletionAPI();
              HashMap<String, String> videoCompletation = userVideoCompletionAPI.videoCompletion(uploadvidoeapiresponse);
              assertionapiresponse.verifyAssert_httpCode(videoCompletation.get(IAPIConstantCodes.APIResponseHttpCode)+videoCompletation.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
              customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, videoCompletation.get(IAPIConstantCodes.APIResponseHttpCode)+videoCompletation.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);             
              customReport.customizedReport(loginapiresponse1.get(IAPIConstantCodes.APIResponseUSERID), videoCompletation.get("userId"), statusValue, driver, sTestcaseName);
              customReport.customizedReport(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID), videoCompletation.get("videoId"), statusValue, driver, sTestcaseName);
              customReport.customizedReport("true", videoCompletation.get("completed"), statusValue, driver, sTestcaseName);
              String datefromAPI =  videoCompletation.get("whenCompleted");
              String [] splitDate = datefromAPI.split("T");
              String date = splitDate[0];              
              customReport.customizedReport(DateTime.getYYYYMMDD(), date, statusValue, driver, sTestcaseName);              
              customReport.checkinglist(statusValue);
       }  
       
       @Test(description = "To verify Media Admin is able to wathing Video using UserVideoCompletation API ",groups = {GETVIDEOWATCHINGSTATUSAPI})
       public void TC_02_GET_UserVideoCompletation_api_check_with_MediaAdmin_Positive(ITestContext context) throws InterruptedException {
       
              logger.info("API Level Code is excuting");
              sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

              UserServices userservices = new UserServices();
              loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UserVideoWatchStatusmediaadmin), surl);
              assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
              logger.info("Login API response Code :::" + loginapiresponse);
              customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
              
              UploadVideoService uploadvidoeservice = new UploadVideoService();
              apibeanfactory.UploadVideoBean(adduploadvideobean);
              adduploadvideobean.setUploader(IUsersList.UserVideoWatchStatusmediaadmin);
              adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
              adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
              adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
              adduploadvideobean.setEnableDownloads(IAPIConstantCodes.TRUE);
              adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);
              
              loginapiresponse.put("Mandatory", "No");
              loginapiresponse.put("fileName", "No");
              
              uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
              uploadvidoeapiresponse.put(IAPIConstantCodes.APIResponseUSERID, loginapiresponse.get(IAPIConstantCodes.APIResponseUSERID));
              logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
              assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
              customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
              context.setAttribute("sTestcaseName", uploadvidoeapiresponse);
              
              //Selenium Code
              launchURL(surl);       
              customReport.reporter("Application launch with URL : ", surl);
              homePage = loginPage.loginAs(IUsersList.UserVideoWatchStatusmediaadmin, sPassword);
              AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
              avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
              AvengerVideoCommentsPage avengervideovommentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
              customReport.customizedReport(true, avengervideovommentspage.getVideoButton().contains("play"), statusValue, driver, sTestcaseName);
              avengervideovommentspage.clickPlayButton();
              customReport.customizedReport(false, avengervideovommentspage.getVideoStatus().contains("is-not-playing"), statusValue, driver, sTestcaseName);
              Thread.sleep(30000);
              customReport.customizedReport(false, avengervideovommentspage.getVideoButton().contains("pause"), statusValue, driver, sTestcaseName);
              avengervideovommentspage.refreshBrowser();
              Thread.sleep(90000);
              
              loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.UserVideoWatchStatusmediaadmin), surl);
              assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
              logger.info("Login API response Code :::" + loginapiresponse1);
              customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
              uploadvidoeapiresponse.put(IAPIConstantCodes.APIResponseUSERID, loginapiresponse1.get(IAPIConstantCodes.APIResponseUSERID));
              uploadvidoeapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, loginapiresponse1.get(IAPIConstantCodes.APIRequestAccessToken));             
                           
              UserVideoCompletionAPI userVideoCompletionAPI = new UserVideoCompletionAPI();
              HashMap<String, String> videoCompletation = userVideoCompletionAPI.videoCompletion(uploadvidoeapiresponse);
              assertionapiresponse.verifyAssert_httpCode(videoCompletation.get(IAPIConstantCodes.APIResponseHttpCode)+videoCompletation.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
              customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, videoCompletation.get(IAPIConstantCodes.APIResponseHttpCode)+videoCompletation.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
              customReport.checkinglist(statusValue);
       }
       
       
       @Test(description = "To verify Event Admin is able to wathing Video using UserVideoCompletation API ",groups = {GETVIDEOWATCHINGSTATUSAPI})
       public void TC_03_GET_UserVideoCompletation_api_check_with_EventAdmin_Positive(ITestContext context) throws InterruptedException {
       
              logger.info("API Level Code is excuting");	
              sTestcaseName = new Object() {    }.getClass().getEnclosingMethod().getName();
       
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
              adduploadvideobean.setVideoAccessControl("Private");
              
      	      loginapiresponse.put("Mandatory", "No");
    		  loginapiresponse.put("fileName", "No");
    		
              uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
              logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
              assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
              customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
              
              UserServices userservices_eventadmin = new UserServices();
              loginapiresponse_eventadmin = userservices_eventadmin.doLogin(apiutils.userJson(IUsersList.UserVideoWatchStatuseventadmin), surl);
              loginapiresponse_eventadmin.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
              loginapiresponse_eventadmin.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
              loginapiresponse_eventadmin.put(IAPIConstantCodes.APIRequestAccessToken, loginapiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
              
              VideoAccessControlServiceAPI videoaccesscontrolservice = new VideoAccessControlServiceAPI();
              accesscontrolbeagPage.setUsercanEdit(IAPIConstantCodes.TRUE);
              accesscontrolbeagPage.setUserid(loginapiresponse_eventadmin.get(IAPIConstantCodes.APIResponseUSERID));
              accesscontrolbeagPage.setUsertype(IAPIConstantCodes.USER);
              JSONObject json = videoaccesscontrolservice.createJsonVideoAccessControl(accesscontrolbeagPage, IVideoAccessControlService.jsonFlagUser);
              videoaccesscontrolservice.videoAccessControl(loginapiresponse_eventadmin, json);
             
              //Selenium Code
              launchURL(surl);       
              customReport.reporter("Application launch with URL : ", surl);
              homePage = loginPage.loginAs(IUsersList.UserVideoWatchStatuseventadmin, sPassword);
              AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
              avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
              AvengerVideoCommentsPage avengervideovommentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
              customReport.customizedReport(true, avengervideovommentspage.getVideoButton().contains("play"), statusValue, driver, sTestcaseName);
              avengervideovommentspage.clickPlayButton();
              customReport.customizedReport(false, avengervideovommentspage.getVideoStatus().contains("is-not-playing"), statusValue, driver, sTestcaseName);
              Thread.sleep(30000);
              customReport.customizedReport(false, avengervideovommentspage.getVideoButton().contains("pause"), statusValue, driver, sTestcaseName);
              avengervideovommentspage.refreshBrowser();
              Thread.sleep(90000);
              
              loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.UserVideoWatchStatuseventadmin), surl);
              assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
              logger.info("Login API response Code :::" + loginapiresponse1);
              customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
              uploadvidoeapiresponse.put(IAPIConstantCodes.APIResponseUSERID, loginapiresponse1.get(IAPIConstantCodes.APIResponseUSERID));
              uploadvidoeapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, loginapiresponse1.get(IAPIConstantCodes.APIRequestAccessToken));
              
              UserVideoCompletionAPI userVideoCompletionAPI = new UserVideoCompletionAPI();
              HashMap<String, String> videoCompletation = userVideoCompletionAPI.videoCompletion(uploadvidoeapiresponse);
              assertionapiresponse.verifyAssert_httpCode(videoCompletation.get(IAPIConstantCodes.APIResponseHttpCode)+videoCompletation.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
              customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, videoCompletation.get(IAPIConstantCodes.APIResponseHttpCode)+videoCompletation.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
              customReport.checkinglist(statusValue);
       }
       
       @Test(description = "To verify MediaContributor is able to wathing Video using UserVideoCompletation API ",groups = {GETVIDEOWATCHINGSTATUSAPI})
       public void TC_04_GET_UserVideoCompletation_api_check_with_MediaContributor_Positive(ITestContext context) throws InterruptedException {
       
              logger.info("API Level Code is excuting");
              sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

              UserServices userservices = new UserServices();
              loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.UserVideoWatchStatusmediacontributor), surl);
              assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
              logger.info("Login API response Code :::" + loginapiresponse);
              customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
              
              UploadVideoService uploadvidoeservice = new UploadVideoService();
              apibeanfactory.UploadVideoBean(adduploadvideobean);
              adduploadvideobean.setUploader(IUsersList.UserVideoWatchStatusmediacontributor);
              adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
              adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
              adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
              adduploadvideobean.setEnableDownloads(IAPIConstantCodes.TRUE);
              adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);
              
      	      loginapiresponse.put("Mandatory", "No");
    		  loginapiresponse.put("fileName", "No");
              
              uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
              uploadvidoeapiresponse.put(IAPIConstantCodes.APIResponseUSERID, loginapiresponse.get(IAPIConstantCodes.APIResponseUSERID));
              logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
              assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
              customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
              context.setAttribute("sTestcaseName", uploadvidoeapiresponse);
              
              //Selenium Code
              launchURL(surl);       
              customReport.reporter("Application launch with URL : ", surl);
              homePage = loginPage.loginAs(IUsersList.UserVideoWatchStatusmediacontributor, sPassword);
              AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
              avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
              AvengerVideoCommentsPage avengervideovommentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
              customReport.customizedReport(true, avengervideovommentspage.getVideoButton().contains("play"), statusValue, driver, sTestcaseName);
              avengervideovommentspage.clickPlayButton();
              customReport.customizedReport(false, avengervideovommentspage.getVideoStatus().contains("is-not-playing"), statusValue, driver, sTestcaseName);
              Thread.sleep(30000);
              customReport.customizedReport(false, avengervideovommentspage.getVideoButton().contains("pause"), statusValue, driver, sTestcaseName);
              avengervideovommentspage.refreshBrowser();
              Thread.sleep(90000);
              
              loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.UserVideoWatchStatusmediacontributor), surl);
              assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
              logger.info("Login API response Code :::" + loginapiresponse1);
              customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
              uploadvidoeapiresponse.put(IAPIConstantCodes.APIResponseUSERID, loginapiresponse1.get(IAPIConstantCodes.APIResponseUSERID));
              uploadvidoeapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, loginapiresponse1.get(IAPIConstantCodes.APIRequestAccessToken));
                           
              UserVideoCompletionAPI userVideoCompletionAPI = new UserVideoCompletionAPI();
              HashMap<String, String> videoCompletation = userVideoCompletionAPI.videoCompletion(uploadvidoeapiresponse);
              assertionapiresponse.verifyAssert_httpCode(videoCompletation.get(IAPIConstantCodes.APIResponseHttpCode)+videoCompletation.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
              customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, videoCompletation.get(IAPIConstantCodes.APIResponseHttpCode)+videoCompletation.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
              customReport.checkinglist(statusValue);
       }
       
       @Test(description = "To verify MediaViewer is able to wathing Video using UserVideoCompletation API ",groups = {GETVIDEOWATCHINGSTATUSAPI})
       public void TC_05_GET_UserVideoCompletation_api_check_with_MediaViewer_Positive(ITestContext context) throws InterruptedException {
       
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
              adduploadvideobean.setVideoAccessControl("Private");
              
      	      loginapiresponse.put("Mandatory", "No");
    		  loginapiresponse.put("fileName", "No");
    		
              uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
              logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
              assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
              customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
              
              UserServices userservices_eventadmin = new UserServices();
              loginapiresponse_mediaviewer = userservices_eventadmin.doLogin(apiutils.userJson(IUsersList.UserVideoWatchStatusmediaviewer), surl);
              loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIResponseBaseUrl, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseBaseUrl));
              loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
              loginapiresponse_mediaviewer.put(IAPIConstantCodes.APIRequestAccessToken, loginapiresponse.get(IAPIConstantCodes.APIRequestAccessToken));
              
              VideoAccessControlServiceAPI videoaccesscontrolservice = new VideoAccessControlServiceAPI();
              accesscontrolbeagPage.setUsercanEdit(IAPIConstantCodes.TRUE);
              accesscontrolbeagPage.setUserid(loginapiresponse_mediaviewer.get(IAPIConstantCodes.APIResponseUSERID));
              accesscontrolbeagPage.setUsertype(IAPIConstantCodes.USER);
              JSONObject json = videoaccesscontrolservice.createJsonVideoAccessControl(accesscontrolbeagPage, IVideoAccessControlService.jsonFlagUser);
              videoaccesscontrolservice.videoAccessControl(loginapiresponse_mediaviewer, json);

              //Selenium Code
              launchURL(surl);       
              customReport.reporter("Application launch with URL : ", surl);
              homePage = loginPage.loginAs(IUsersList.UserVideoWatchStatusmediaviewer, sPassword);
              AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
              avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
              AvengerVideoCommentsPage avengervideovommentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
              customReport.customizedReport(true, avengervideovommentspage.getVideoButton().contains("play"), statusValue, driver, sTestcaseName);
              avengervideovommentspage.clickPlayButton();
              customReport.customizedReport(false, avengervideovommentspage.getVideoStatus().contains("is-not-playing"), statusValue, driver, sTestcaseName);
              Thread.sleep(30000);
              customReport.customizedReport(false, avengervideovommentspage.getVideoButton().contains("pause"), statusValue, driver, sTestcaseName);
              avengervideovommentspage.refreshBrowser();
              Thread.sleep(90000);
              
              loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.UserVideoWatchStatusmediaviewer), surl);
              assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
              logger.info("Login API response Code :::" + loginapiresponse1);
              customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
              uploadvidoeapiresponse.put(IAPIConstantCodes.APIResponseUSERID, loginapiresponse1.get(IAPIConstantCodes.APIResponseUSERID));
              uploadvidoeapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, loginapiresponse1.get(IAPIConstantCodes.APIRequestAccessToken));
              
              UserVideoCompletionAPI userVideoCompletionAPI = new UserVideoCompletionAPI();
              HashMap<String, String> videoCompletation = userVideoCompletionAPI.videoCompletion(uploadvidoeapiresponse);
              assertionapiresponse.verifyAssert_httpCode(videoCompletation.get(IAPIConstantCodes.APIResponseHttpCode)+videoCompletation.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
              customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, videoCompletation.get(IAPIConstantCodes.APIResponseHttpCode)+videoCompletation.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
              customReport.checkinglist(statusValue);
       }
              
       @Test(description = "To verify AccountAdmin is not able to wathing Video using UserVideoCompletation API ",groups = {GETVIDEOWATCHINGSTATUSAPI})
       public void TC_06_GET_UserVideoCompletation_api_check_with_AccountAdmin_Postive(ITestContext context) throws InterruptedException {
       
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
              adduploadvideobean.setEnableDownloads(IAPIConstantCodes.TRUE);
              adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[0]);
              
      	      loginapiresponse.put("Mandatory", "No");
      	      loginapiresponse.put("fileName", "No");
              
              uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
              uploadvidoeapiresponse.put(IAPIConstantCodes.APIResponseUSERID, loginapiresponse.get(IAPIConstantCodes.APIResponseUSERID));
              logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
              assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
              customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
              context.setAttribute("sTestcaseName", uploadvidoeapiresponse);
       
              //Selenium Code
              launchURL(surl);       
              customReport.reporter("Application launch with URL : ", surl);
              homePage = loginPage.loginAs(sUserName, sPassword);
              AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
              avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
              AvengerVideoCommentsPage avengervideovommentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
              logger.info("here3");
              customReport.customizedReport(true, avengervideovommentspage.getVideoButton().contains("play"), statusValue, driver, sTestcaseName);
              avengervideovommentspage.clickPlayButton();
              logger.info("here4");
              customReport.customizedReport(false, avengervideovommentspage.getVideoStatus().contains("is-not-playing"), statusValue, driver, sTestcaseName);
              Thread.sleep(30000);
              logger.info("here5");
              customReport.customizedReport(false, avengervideovommentspage.getVideoButton().contains("pause"), statusValue, driver, sTestcaseName);
              avengervideovommentspage.refreshBrowser();
              Thread.sleep(90000);
              logger.info("here2");
              loginapiresponse1 = userservices.doLogin(apiutils.userJson(sUserName), surl);
              assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
              logger.info("Login API response Code :::" + loginapiresponse1);
              customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
              uploadvidoeapiresponse.put(IAPIConstantCodes.APIResponseUSERID, loginapiresponse1.get(IAPIConstantCodes.APIResponseUSERID));
              uploadvidoeapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, loginapiresponse1.get(IAPIConstantCodes.APIRequestAccessToken));
              logger.info("here1");
              UserVideoCompletionAPI userVideoCompletionAPI = new UserVideoCompletionAPI();
              HashMap<String, String> videoCompletation = userVideoCompletionAPI.videoCompletion(uploadvidoeapiresponse);
              assertionapiresponse.verifyAssert_httpCode(videoCompletation.get(IAPIConstantCodes.APIResponseHttpCode)+videoCompletation.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
              customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, videoCompletation.get(IAPIConstantCodes.APIResponseHttpCode)+videoCompletation.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
              customReport.checkinglist(statusValue);
       }  
       
       @Test(description = "To verify AccountAdmin is able to wathing Video with Invalidvideoidvalid using UserVideoCompletation API ",groups = {GETVIDEOWATCHINGSTATUSAPI})
       public void TC_07_GET_UserVideoCompletation_api_check_with_Invalidvideoidvalid_AccountAdmin_Negative(ITestContext context) {
       
              logger.info("API Level Code is excuting");
              sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
       
              UserServices userservices = new UserServices();
              loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
              assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
              logger.info("Login API response Code :::" + loginapiresponse);
              customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

              loginapiresponse.put(IAPIConstantCodes.APIResponseVIDEOID, IUserVideoWatchingStatusService.invalidVideoId);
              loginapiresponse.put(IAPIConstantCodes.APIResponseUSERID, loginapiresponse.get(IAPIConstantCodes.APIResponseUSERID));
              
              UserVideoCompletionAPI userVideoCompletionAPI = new UserVideoCompletionAPI();
              HashMap<String, String> videoCompletation = userVideoCompletionAPI.videoCompletion(loginapiresponse);
              assertionapiresponse.verifyAssert_httpCode(videoCompletation.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
              customReport.customizedReport(HttpStatusCode.httpsStatus401, videoCompletation.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
              customReport.checkinglist(statusValue);
       }
       
       @Test(description = "To verify AccountAdmin is able to wathing Video with Invaliduseridvalid using UserVideoCompletation API ",groups = {GETVIDEOWATCHINGSTATUSAPI} )
       public void TC_08_GET_UserVideoCompletation_api_check_with_Invaliduseridvalid_AccountAdmin_Negative(ITestContext context) {

              logger.info("API Level Code is excuting");
              sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
       
              UserServices userservices = new UserServices();
              loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
              assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
              logger.info("Login API response Code :::" + loginapiresponse);
              customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

              loginapiresponse.put(IAPIConstantCodes.APIResponseVIDEOID, IUserVideoWatchingStatusService.invalidVideoId);
              loginapiresponse.put(IAPIConstantCodes.APIResponseUSERID, IUserVideoWatchingStatusService.invaliduserId);
              
              UserVideoCompletionAPI userVideoCompletionAPI = new UserVideoCompletionAPI();
              HashMap<String, String> videoCompletation = userVideoCompletionAPI.videoCompletion(loginapiresponse);
              assertionapiresponse.verifyAssert_httpCode(videoCompletation.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
              customReport.customizedReport(HttpStatusCode.httpsStatus401, videoCompletation.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
              customReport.checkinglist(statusValue);
       }
      
       @AfterMethod(alwaysRun=true)
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

