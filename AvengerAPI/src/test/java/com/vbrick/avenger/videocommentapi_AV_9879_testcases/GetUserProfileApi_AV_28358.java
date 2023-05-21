package com.vbrick.avenger.videocommentapi_AV_9879_testcases;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
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
import com.vbrick.avenger.apibeans.AddGroupBean;
import com.vbrick.avenger.apibeans.AddTeamBean;
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbrick.avenger.apibeans.AddVideoCommentBean;
 
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AddUserBeanPage;
import com.vbrick.avenger.dao.AvengerGroupsBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerAddUserPage;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerEditRootAccountPage;
import com.vbrick.avenger.pageobjects.AvengerGroupsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerUserDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbrick.avenger.videocommentapi_AV_9879_testcases.SubmitVideosComments_AV_9879;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IDeleteVideoService;
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUserAccountsService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.CreateGroupAPI;
import com.vbricks.avenger.serviceimpl.CreateTeamAPI;
import com.vbricks.avenger.serviceimpl.DeleteGroupAPI;
import com.vbricks.avenger.serviceimpl.DeleteTeamAPI;
import com.vbricks.avenger.serviceimpl.DeleteVideosAPI;
import com.vbricks.avenger.serviceimpl.GetUserProfileImageAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoImageAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserDetailsAPI;
import com.vbricks.avenger.serviceimpl.UserNameAPI;
import com.vbricks.avenger.serviceimpl.UserProfileImageAPI;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.serviceimpl.VideoSearchAPI;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.ConstantsValue;
import com.vbricks.avenger.utils.HttpStatusCode;

public class GetUserProfileApi_AV_28358 extends TestBase {

	private static Logger logger = Logger.getLogger(GetUserProfileApi_AV_28358.class);
	private AvengerLoginPage loginPage;
	private AvengerHomePage homePage;
	private AvengerAddUserPage adduserpage;
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
	private AvengerHomePage avengerHomePage;
	private AvengerUserDashboardPage avengeruserdashboardpage;
	private AvengerGroupsBeanPage avengergroupsbeanpage;
	private BasePage basePage;
	private AddUploadVideoBean adduploadvideobean;
	public ApiBeanFactory apibeanfactory;
	private AddGroupBean addgroupbean;
	private AddTeamBean addteambean;
	public AddVideoCommentBean addvideocommentbean;
 	private HashMap<String, String> loginapiresponse;
 	private HashMap<String, String> loginapiresponse1;
	private HashMap<String, String> uploadvidoeapiresponse;
	private HashMap<String,String> loginapiresponse_mediaviewer;
	private AvengerGroupsPage avengergroupspage;
	private HashMap<String,String> loginapiresponse_eventadmin;
	private HashMap<String, String> creategroupapirespone;
 	private HashMap<String, String> createteamapirespone;
 	private HashMap<String, String> deleteGroupapiresponse;
 	private HashMap<String, String> deleteTeamapiresponse;
	private HashMap<String,String> loginapiresponse_mediacontributor;
    private AssertionAPIResponse assertionapiresponse;
 	private AvengerEditRootAccountPage avengereditrootaccountpage; 
 	private JSONObject searchvideoapirespone;
	
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
		addgroupbean=new AddGroupBean();
		addteambean=new AddTeamBean();
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
		adduserpage=basePage.avengerAddUserpage(driver, customReport, basePage);
		driver.manage().window().maximize();
		accountBeansFactory.MailinatorBean(mailinatorbeanpage);

	}

	/*@Test(description = "To verify Userprofile using Get user profile image api and upload video in owners section profile image uri is given  with that uri try to get profile  ",groups = {GetUserProfile})
	public void TC_01_GET_UserProfile_Image_api_with_AccountAdmin_Positive(ITestContext context) throws Exception {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);		
		 
		UserProfileImageAPI userprofileImageAPI=new UserProfileImageAPI();
		HashMap<String,String> userprofielImageapiresponse = userprofileImageAPI.uploaduserprofileimage(loginapiresponse, Setup.VALIDIMAGEPATH_JPG2,IUploadVideoService.UPLOADIMAGE );
		logger.info("UploadVideo API response Code :::" + userprofielImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(userprofielImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, userprofielImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, userprofielImageapiresponse);	
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		loginapiresponse.put("Mandatory","Yes");
		loginapiresponse.put("fileName", "Yes");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
	 
        loginapiresponse.put("q",adduploadvideobean.getTitle()); 
		
		//queryParams path.. :::: {q=[XXX]}
		VideoSearchAPI searchvideoAPI=new VideoSearchAPI();
	    searchvideoapirespone = searchvideoAPI.searchVideo_OwnerUpdated(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode).toString(), HttpStatusCode.httpsStatusCode200);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200, searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode).toString(),statusValue, driver, sTestcaseName);
      
	    System.out.println("@@@@@@@@@NEW:::::"+path_profileImageUri());
	  
	    loginapiresponse.put(IAPIConstantCodes.profileImageUri, path_profileImageUri());
	    GetUserProfileImageAPI getuserprofile=new GetUserProfileImageAPI();
	    HashMap<String, String> getuserprofileapiresponse=getuserprofile.GetUserProfile(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(getuserprofileapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getuserprofileapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	    
	    
		// Selenium Level Code
		launchURL(surl);
		avengerHomePage = loginPage.loginAs(sUserName,sPassword);
	    avengerHomePage.click_userprofiledropdown();
	    avengerHomePage.click_Myprofile();
	    customReport.customizedReport(true,  adduserpage.verify_image().contains("<img"),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}

	@Test(description = "To verify Userprofile using Get user profile image api of media admin upload video  in owners section profile image uri is given  with that uri try to get profile  ",groups = {GetUserProfile})
	public void TC_02_GET_UserProfile_Image_forMediaAdmin_api_with_AccountAdmin_Positive(ITestContext context) throws Exception {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);		
		 
		//login as media admin
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.ApproveVideomediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);	
		
		//upload user profile image for media admin
		UserProfileImageAPI userprofileImageAPI=new UserProfileImageAPI();
		HashMap<String,String> userprofielImageapiresponse = userprofileImageAPI.uploaduserprofileimage(loginapiresponse1, Setup.VALIDIMAGEPATH_JPG2,IUploadVideoService.UPLOADIMAGE );
		logger.info("UploadVideo API response Code :::" + userprofielImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(userprofielImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, userprofielImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, userprofielImageapiresponse);	
		
		//upload video with account admin and set uploader as media admin
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.ApproveVideomediaadmin);
		loginapiresponse.put("Mandatory","Yes");
		loginapiresponse.put("fileName", "Yes");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
	 
        loginapiresponse.put("q",adduploadvideobean.getTitle()); 
		
		//queryParams path.. :::: {q=[XXX]}
        //Search video as account admin
		VideoSearchAPI searchvideoAPI=new VideoSearchAPI();
	    searchvideoapirespone = searchvideoAPI.searchVideo_OwnerUpdated(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode).toString(), HttpStatusCode.httpsStatusCode200);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200, searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode).toString(),statusValue, driver, sTestcaseName);
      
	    System.out.println("@@@@@@@@@NEW:::::"+path_profileImageUri());
	  
	    //get profileImageUri path from owner field in search video
	    loginapiresponse1.put(IAPIConstantCodes.profileImageUri, path_profileImageUri());
	    
	    //get profile using media admin
	    GetUserProfileImageAPI getuserprofile=new GetUserProfileImageAPI();
	    HashMap<String, String> getuserprofileapiresponse=getuserprofile.GetUserProfile(loginapiresponse1);
	    assertionapiresponse.verifyAssert_httpCode(getuserprofileapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getuserprofileapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	    
	    
		// Selenium Level Code
		launchURL(surl);
		avengerHomePage = loginPage.loginAs(IUsersList.ApproveVideomediaadmin,sPassword);
	    avengerHomePage.click_userprofiledropdown();
	    avengerHomePage.click_Myprofile();
	    customReport.customizedReport(true,  adduserpage.verify_image().contains("<img"),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	 
	
	@Test(description = "To verify Userprofile using Get user profile image api of media viewer upload video  in owners section profile image uri is given  with that uri try to get profile  ",groups = {GetUserProfile})
	public void TC_03_GET_UserProfile_Image_ofMediaViewer_api_with_AccountAdmin_Positive(ITestContext context) throws Exception {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);		
		 
		//login as media viewer
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.Get_Mediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);	
		
		//upload user profile image for media viewer
		UserProfileImageAPI userprofileImageAPI=new UserProfileImageAPI();
		HashMap<String,String> userprofielImageapiresponse = userprofileImageAPI.uploaduserprofileimage(loginapiresponse1, Setup.VALIDIMAGEPATH_JPG2,IUploadVideoService.UPLOADIMAGE );
		logger.info("UploadVideo API response Code :::" + userprofielImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(userprofielImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, userprofielImageapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		context.setAttribute(sTestcaseName, userprofielImageapiresponse);	
		
		//upload video with account admin and set uploader as media viewer
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.Get_Mediaviewer);
		loginapiresponse.put("Mandatory","Yes");
		loginapiresponse.put("fileName", "Yes");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
	 
        loginapiresponse.put("q",adduploadvideobean.getTitle()); 
		
		//queryParams path.. :::: {q=[XXX]}
        //Search video as account admin
		VideoSearchAPI searchvideoAPI=new VideoSearchAPI();
	    searchvideoapirespone = searchvideoAPI.searchVideo_OwnerUpdated(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode).toString(), HttpStatusCode.httpsStatusCode200);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200, searchvideoapirespone.get(IAPIConstantCodes.APIResponseHttpCode).toString(),statusValue, driver, sTestcaseName);
      
	    System.out.println("@@@@@@@@@NEW:::::"+path_profileImageUri());
	  
	    //get profileImageUri path from owner field in search video
	    loginapiresponse1.put(IAPIConstantCodes.profileImageUri, path_profileImageUri());
	    
	    //get profile using media viewer
	    GetUserProfileImageAPI getuserprofile=new GetUserProfileImageAPI();
	    HashMap<String, String> getuserprofileapiresponse=getuserprofile.GetUserProfile(loginapiresponse1);
	    assertionapiresponse.verifyAssert_httpCode(getuserprofileapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200, getuserprofileapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	    
	    
		// Selenium Level Code
		launchURL(surl);
		avengerHomePage = loginPage.loginAs(IUsersList.Get_Mediaviewer,sPassword);
	    avengerHomePage.click_userprofiledropdown();
	    avengerHomePage.click_Myprofile();
	    customReport.customizedReport(true,  adduserpage.verify_image().contains("<img"),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	*/
	 
		
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
	
	/*public String path_profileImageUri() {
		  
		// uri  object
	        URI uri = null;
	  
	        try {
	            // create a URI
	            uri = new URI(searchvideoapirespone.get(IAPIConstantCodes.profileImageUri).toString());
	  
	            // get the  Path
	            String _Path = uri.getPath();
	  
	        }
	        // if any error occurs
	        catch (URISyntaxException e) {
	            // display the error
	            System.out.println("URI Exception =" + e.getMessage());
	        }
	return uri.getPath();
	
	}
	*/
	
 
}