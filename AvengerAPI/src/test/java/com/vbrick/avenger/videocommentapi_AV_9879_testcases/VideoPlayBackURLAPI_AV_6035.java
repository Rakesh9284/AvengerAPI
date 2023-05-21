package com.vbrick.avenger.videocommentapi_AV_9879_testcases;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
import com.vbrick.avenger.ObjProperty.AvengerLibraryInformationPropertyPage;
import com.vbrick.avenger.apibeans.AddCategoryBean;
import com.vbrick.avenger.apibeans.AddGroupBean;
import com.vbrick.avenger.apibeans.AddTeamBean;
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbrick.avenger.apibeans.AddVideoCommentBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.LibraryBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerEditVideoSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerFeaturesPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLibrariesPage;
import com.vbrick.avenger.pageobjects.AvengerLibraryInformationPage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerMediaSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerUserDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbrick.avenger.uploadvideoapi_AV_6015_testcases.UploadVideos_AV_6015;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUserAccountsService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.service.IVideoPlayBackURLService;
import com.vbricks.avenger.serviceimpl.CreateCategoryAPI;
import com.vbricks.avenger.serviceimpl.CreateGroupAPI;
import com.vbricks.avenger.serviceimpl.CreateTeamAPI;
import com.vbricks.avenger.serviceimpl.PlayBackURLAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class VideoPlayBackURLAPI_AV_6035 extends TestBase{
	
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
	private AvengerLibrariesPage avengerlibrarypage;
	private AvengerLibraryInformationPage avengerlibraryinfopage;
	private ReadAndWriteToJSON readgriduserdata;
	private AddCategoryBean addcategorybean;
	private Map<String, String> userdata;
	private BasePage basePage;
	private AddTeamBean addteambean;
	private AddUploadVideoBean adduploadvideobean;
	public ApiBeanFactory apibeanfactory;
	private HashMap<String, String> createteamapirespone;
	private AddGroupBean addgroupbean;
	public AddVideoCommentBean addvideocommentbean;
	private AvengerHomePage avengerHomePage;
	private AvengerUserDashboardPage avengeruserdashboardpage;
	private AvengerMediaSettingsPage avengermediasettingspage;
	private AvengerFeaturesPage avengerfeaturespage;
	private ApiUtils apiutils;
	private HashMap<String, String> loginapiresponse;
	private HashMap<String, String> loginapiresponse1;
	private HashMap<String, String> uploadvidoeapiresponse;
	private HashMap<String, String> createcategoryapirespone;
	private AssertionAPIResponse assertionapiresponse;
	private HashMap<String, String> creategroupapirespone;
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
		librarybeanpage = new LibraryBeanPage();
		adduploadvideobean = new AddUploadVideoBean();
		addgroupbean=new AddGroupBean();
		addcategorybean=new AddCategoryBean();
		addteambean=new AddTeamBean();
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
		accountBeansFactory.LibraryBeanByPage(librarybeanpage);

	}
	
	@Test(description = "To verify the Video PlayBackURLAPI With Account Admin  Valid VideoId",groups = {VIDEOPLAYBACKAPI})
	public void TC_01_GET_VideoPlayBackURLAPI_api_check_with_AccountAdmin_Positive( ) {
		check_embedCheckobox();

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() { 	}.getClass().getEnclosingMethod().getName();
		
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

		PlayBackURLAPI videoPlayBackURLAPIAPI=new PlayBackURLAPI();
	    HashMap<String, String> videoplaybackapirespone = videoPlayBackURLAPIAPI.getVideoPlayback(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(videoplaybackapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videoplaybackapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, videoplaybackapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videoplaybackapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);	 
 
	    logger.info("Selenium Code is excuting");		 
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.title")),statusValue, driver, sTestcaseName);
		commentspage.clickVideoBasicinformationButton();
		customReport.customizedReport(adduploadvideobean.getDescription(),commentspage.verify_videodescription(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.description")), statusValue, driver,sTestcaseName);		 
		commentspage.click_Sharing();
		commentspage.click_Embed();
		commentspage.click_Option();
		String [] embedcode=commentspage.get_showembedcodetextarea().split(" ");
		String url = null;
		for (String string : embedcode) {
             if(string.contains("src"))
			url=string;
        }
		url.replaceAll("\"", "");
		String embedurl=url.replaceAll("\"", "").replace("src="," ");
		logger.info("Url Value is"+embedurl);
		//playbakckURL
        logger.info("JSON ---->"+apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.playbackUrl"));
		logger.info("EMEDURL------->"+embedurl);
		customReport.customizedReport(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.playbackUrl"), embedurl.split("&")[0],statusValue, driver, sTestcaseName);	 
		customReport.checkinglist(statusValue);
		
	}
	
  	@Test(description = "To verify the Video PlayBackURLAPI With MediaAdmin  Valid VideoId",groups = {VIDEOPLAYBACKAPI})
  	public void TC_02_GET_VideoPlayBackURLAPI_api_check_with_MediaAdmin_Positive( ) {
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() { 	}.getClass().getEnclosingMethod().getName();
		
	    UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.PlayBackURLmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.PlayBackURLmediaadmin);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
		//to assign a category to video
		CreateCategoryAPI createcategoryAPI=new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson=createcategoryAPI.createCategoryJson(addcategorybean);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse,categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		String CategoryIds[] = {createcategoryapirespone.get("categoryId")};
		adduploadvideobean.setCategoryIds(CategoryIds);
			
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);	   
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		PlayBackURLAPI videoPlayBackURLAPIAPI=new PlayBackURLAPI();
	    HashMap<String, String> videoplaybackapirespone = videoPlayBackURLAPIAPI.getVideoPlayback(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(videoplaybackapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videoplaybackapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, videoplaybackapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videoplaybackapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);	 
 
	    String Category1 = apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.categories[*].name");
	    String Category = Category1.replaceAll("\\[","").replaceAll("\\]", "").replaceAll("\\\"", "");
	    	    
	    logger.info("Selenium Code is excuting");		 
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage avengervideovommentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, avengervideovommentspage.verify_VideoTitleText(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.title")),statusValue, driver, sTestcaseName);
		avengervideovommentspage.clickVideoBasicinformationButton();
		customReport.customizedReport(adduploadvideobean.getDescription(),avengervideovommentspage.verify_videodescription(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.description")), statusValue, driver,sTestcaseName);
		customReport.customizedReport(Category,avengervideovommentspage.verifycategory(Category), statusValue, driver,sTestcaseName);	
		avengervideovommentspage.click_Sharing();
		avengervideovommentspage.click_Embed();
		avengervideovommentspage.click_Option();
		String [] embedcode=avengervideovommentspage.get_showembedcodetextarea().split(" ");
		String url = null;
		for (String string : embedcode) {
             if(string.contains("src"))
			url=string;
        }
		url.replaceAll("\"", "");
		String embedurl=url.replaceAll("\"", "").replace("src="," ");
		logger.info("Url Value is"+embedurl);
		//playbakckURL
		customReport.customizedReport(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.playbackUrl"), embedurl.split("&")[0],statusValue, driver, sTestcaseName);		
		customReport.checkinglist(statusValue);	
	}
  	
  	@Test(description = "To verify the Video PlayBackURLAPI for a private video With Media Viewer by assigning MV to a group having Media Admin access",groups = {VIDEOPLAYBACKAPI})
  	public void TC_03_GET_VideoPlayBackURLAPI_api_check_with_MediaViewer_PrivateVideo_bygivingaccess_Positive( ) throws InterruptedException {
  		
  		//deleting the assigned groups if any for the Media Viewer user
  		delete_assignedGroupsForUser(IUsersList.PlayBackURLmediaviewer1);
  		
  		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() { 	}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.PlayBackURLmediaviewer1), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		//getting the userIds
		CreateGroupAPI creategroupAPI1=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		String[] userIds = {loginapiresponse1.get("userId")};
		addgroupbean.setUserids(userIds);
									
		//getting roleids
		HashMap<String, String> userroleapirespone = userservices.getRolesApi(loginapiresponse,IAPIConstantCodes.MEDIAADMIN);
		String roleid=apiutils.formatingapiresponse(userroleapirespone.get(IAPIConstantCodes.ROLEID));
		String[] roleIds = {userroleapirespone.get("roleId")};
		addgroupbean.setRoleids(roleIds);
								
		JSONObject creategroupjson1=creategroupAPI1.createGroupJson(addgroupbean);
		creategroupapirespone = creategroupAPI1.createGroup(loginapiresponse,creategroupjson1);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		Thread.sleep(3000);
				
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.PlayBackURLmediaviewer1);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);
	    loginapiresponse1.put("Mandatory", "No");
		loginapiresponse1.put("fileName", "No");
		String accesscontrolforuser = IUsersList.PlayBackURLmediaviewer;
		adduploadvideobean.setAccesscontrolforuser(accesscontrolforuser);
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse1, Setup.NOTIFICATION1PATH,adduploadvideobean);

		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		PlayBackURLAPI videoPlayBackURLAPIAPI=new PlayBackURLAPI();
	    HashMap<String, String> videoplaybackapirespone = videoPlayBackURLAPIAPI.getVideoPlayback(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(videoplaybackapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videoplaybackapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, videoplaybackapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videoplaybackapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);	 
	    	    
	  //deleting the assigned groups if any for the Media Viewer user
	  	delete_assignedGroupsForUser(IUsersList.PlayBackURLmediaviewer1);
	  	
	    logger.info("Selenium Code is excuting");		 
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage avengervideovommentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, avengervideovommentspage.verify_VideoTitleText(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.title")),statusValue, driver, sTestcaseName);
		avengervideovommentspage.clickVideoBasicinformationButton();
		customReport.customizedReport(adduploadvideobean.getDescription(),avengervideovommentspage.verify_videodescription(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.description")), statusValue, driver,sTestcaseName);		 
		avengervideovommentspage.click_Sharing();
		avengervideovommentspage.click_Embed();
		avengervideovommentspage.click_Option();
		String [] embedcode=avengervideovommentspage.get_showembedcodetextarea().split(" ");
		String url = null;
		for (String string : embedcode) {
             if(string.contains("src"))
			url=string;
        }
		url.replaceAll("\"", "");
		String embedurl=url.replaceAll("\"", "").replace("src="," ");
		logger.info("Url Value is"+embedurl);
		//playbakckURL
		customReport.customizedReport(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.playbackUrl"), embedurl.split("&")[0],statusValue, driver, sTestcaseName);	 
		customReport.checkinglist(statusValue);	
	}

  	
  	@Test(description = "To verify the Video PlayBackURLAPI With Media Contirbutor  Valid VideoId",groups = {VIDEOPLAYBACKAPI})
  	public void TC_04_GET_VideoPlayBackURLAPI_api_check_with_MediaContributor_Positive( ) {
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() { 	}.getClass().getEnclosingMethod().getName();
		
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.PlayBackURLmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.PlayBackURLmediacontributor);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
    
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		PlayBackURLAPI videoPlayBackURLAPIAPI=new PlayBackURLAPI();
	    HashMap<String, String> videoplaybackapirespone = videoPlayBackURLAPIAPI.getVideoPlayback(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(videoplaybackapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videoplaybackapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, videoplaybackapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videoplaybackapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);	 
 
	    logger.info("Selenium Code is excuting");		 
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage avengervideovommentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, avengervideovommentspage.verify_VideoTitleText(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.title")),statusValue, driver, sTestcaseName);
		avengervideovommentspage.clickVideoBasicinformationButton();
		customReport.customizedReport(adduploadvideobean.getDescription(),avengervideovommentspage.verify_videodescription(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.description")), statusValue, driver,sTestcaseName);		 
		avengervideovommentspage.click_Sharing();
		avengervideovommentspage.click_Embed();
		avengervideovommentspage.click_Option();
		String [] embedcode=avengervideovommentspage.get_showembedcodetextarea().split(" ");
		String url = null;
		for (String string : embedcode) {
             if(string.contains("src"))
			url=string;
        }
		url.replaceAll("\"", "");
		String embedurl=url.replaceAll("\"", "").replace("src="," ");
		logger.info("Url Value is"+embedurl);
		//playbakckURL
		customReport.customizedReport(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.playbackUrl"), embedurl.split("&")[0],statusValue, driver, sTestcaseName);	 		
		customReport.checkinglist(statusValue);		
	}
  	
  	@Test(description = "To verify the Video PlayBackURLAPI for a private video With Account Admin",groups = {VIDEOPLAYBACKAPI})
  	public void TC_05_GET_VideoPlayBackURLAPI_api_check_with_AccountAdmin_PrivateVideo_Positive( ) {
  		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() { 	}.getClass().getEnclosingMethod().getName();
		
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
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		String accesscontrolforuser = IUsersList.PlayBackURLmediaviewer;
		adduploadvideobean.setAccesscontrolforuser(accesscontrolforuser);
		
		//to assign a category to video
		CreateCategoryAPI createcategoryAPI = new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson = createcategoryAPI.createCategoryJson(addcategorybean);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse, categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(
		createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+ createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+ createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);

		String CategoryIds[] = { createcategoryapirespone.get("categoryId") };
		adduploadvideobean.setCategoryIds(CategoryIds);
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);		
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		PlayBackURLAPI videoPlayBackURLAPIAPI=new PlayBackURLAPI();
	    HashMap<String, String> videoplaybackapirespone = videoPlayBackURLAPIAPI.getVideoPlayback(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(videoplaybackapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videoplaybackapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, videoplaybackapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videoplaybackapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);	 
	    
	    String Category1 = apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.categories[*].name");
	    String Category = Category1.replaceAll("\\[","").replaceAll("\\]", "").replaceAll("\\\"", "");
	    
	    logger.info("Selenium Code is excuting");		 
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage avengervideovommentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, avengervideovommentspage.verify_VideoTitleText(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.title")),statusValue, driver, sTestcaseName);
		avengervideovommentspage.clickVideoBasicinformationButton();
		customReport.customizedReport(adduploadvideobean.getDescription(),avengervideovommentspage.verify_videodescription(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.description")), statusValue, driver,sTestcaseName);
		customReport.customizedReport(Category,avengervideovommentspage.verifycategory(Category), statusValue, driver,sTestcaseName);				
		avengervideovommentspage.click_Sharing();
		avengervideovommentspage.click_Embed();
		avengervideovommentspage.click_Option();
		String [] embedcode=avengervideovommentspage.get_showembedcodetextarea().split(" ");
		String url = null;
		for (String string : embedcode) {
             if(string.contains("src"))
			url=string;
        }
		url.replaceAll("\"", "");
		String embedurl=url.replaceAll("\"", "").replace("src="," ");
		logger.info("Url Value is"+embedurl);
		//playbakckURL
		customReport.customizedReport(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.playbackUrl"), embedurl.split("&")[0],statusValue, driver, sTestcaseName);		 
		customReport.checkinglist(statusValue);	
	}


  	@Test(description = "To verify the Video PlayBackURLAPI With Event Host with All Users Video",groups = {VIDEOPLAYBACKAPI})
	public void TC_06_GET_VideoPlayBackURLAPI_api_check_with_EventHost_AllUsers_Positive( ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() { 	}.getClass().getEnclosingMethod().getName();
		
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
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[2]);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
		//to assign a category to video
		CreateCategoryAPI createcategoryAPI = new CreateCategoryAPI();
		apibeanfactory.CreateCategoryBean(addcategorybean);
		JSONObject categroiesjson = createcategoryAPI.createCategoryJson(addcategorybean);
		createcategoryapirespone = createcategoryAPI.createcategory(loginapiresponse, categroiesjson);
		assertionapiresponse.verifyAssert_httpCode(
		createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+ createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,createcategoryapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+ createcategoryapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),
		statusValue, driver, sTestcaseName);

		String CategoryIds[] = { createcategoryapirespone.get("categoryId") };
		adduploadvideobean.setCategoryIds(CategoryIds);
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		PlayBackURLAPI videoPlayBackURLAPIAPI=new PlayBackURLAPI();	
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.PlayBackURLeventhost), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		uploadvidoeapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, loginapiresponse1.get(IAPIConstantCodes.APIRequestAccessToken));				
	    HashMap<String, String> videoplaybackapirespone = videoPlayBackURLAPIAPI.getVideoPlayback(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(videoplaybackapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videoplaybackapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, videoplaybackapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videoplaybackapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);	 
	    
	    String Category1 = apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.categories[*].name");
	    String Category = Category1.replaceAll("\\[","").replaceAll("\\]", "").replaceAll("\\\"", "");
	    
	    logger.info("Selenium Code is excuting");
		 
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.title")),statusValue, driver, sTestcaseName);
		commentspage.clickVideoBasicinformationButton();
		customReport.customizedReport(adduploadvideobean.getDescription(),commentspage.verify_videodescription(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.description")), statusValue, driver,sTestcaseName);
		customReport.customizedReport(Category,commentspage.verifycategory(Category), statusValue, driver,sTestcaseName);			
		commentspage.click_Sharing();
		commentspage.click_Embed();
		commentspage.click_Option();
		String [] embedcode=commentspage.get_showembedcodetextarea().split(" ");
		String url = null;
		for (String string : embedcode) {
             if(string.contains("src"))
			url=string;
        }
		url.replaceAll("\"", "");
		String embedurl=url.replaceAll("\"", "").replace("src="," ");
		logger.info("Url Value is"+embedurl);
		//playbakckURL
        logger.info("JSON ---->"+apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.playbackUrl"));
		logger.info("EMEDURL------->"+embedurl);
		customReport.customizedReport(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.playbackUrl"), embedurl.split("&")[0],statusValue, driver, sTestcaseName);		 
		customReport.checkinglist(statusValue);
		
	}
  	
  	@Test(description = "To verify the Video PlayBackURLAPI With Account Admin with All Users Video",groups = {VIDEOPLAYBACKAPI})
	public void TC_07_GET_VideoPlayBackURLAPI_api_check_with_AccountAdmin_AllUsers_Positive( ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() { 	}.getClass().getEnclosingMethod().getName();
		
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
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[2]);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		PlayBackURLAPI videoPlayBackURLAPIAPI=new PlayBackURLAPI();					
	    HashMap<String, String> videoplaybackapirespone = videoPlayBackURLAPIAPI.getVideoPlayback(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(videoplaybackapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videoplaybackapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, videoplaybackapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videoplaybackapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);	 
 
	    logger.info("Selenium Code is excuting");	 
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.title")),statusValue, driver, sTestcaseName);
		commentspage.clickVideoBasicinformationButton();
		customReport.customizedReport(adduploadvideobean.getDescription(),commentspage.verify_videodescription(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.description")), statusValue, driver,sTestcaseName);	 
		commentspage.click_Sharing();
		commentspage.click_Embed();
		commentspage.click_Option();
		String [] embedcode=commentspage.get_showembedcodetextarea().split(" ");
		String url = null;
		for (String string : embedcode) {
             if(string.contains("src"))
			url=string;
        }
		url.replaceAll("\"", "");
		String embedurl=url.replaceAll("\"", "").replace("src="," ");
		logger.info("Url Value is"+embedurl);
		//playbakckURL
        logger.info("JSON ---->"+apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.playbackUrl"));
		logger.info("EMEDURL------->"+embedurl);
		customReport.customizedReport(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.playbackUrl"), embedurl.split("&")[0],statusValue, driver, sTestcaseName);		 
		customReport.checkinglist(statusValue);
		
	}

  	@Test(description = "To verify the Video PlayBackURLAPI With Media Viewer public video",groups = {VIDEOPLAYBACKAPI})
	public void TC_08_GET_VideoPlayBackURLAPI_api_check_with_MediaViewer_Public_Positive( ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() { 	}.getClass().getEnclosingMethod().getName();
		
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

		PlayBackURLAPI videoPlayBackURLAPIAPI=new PlayBackURLAPI();		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.PlayBackURLmediaviewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		uploadvidoeapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, loginapiresponse1.get(IAPIConstantCodes.APIRequestAccessToken));				
	    HashMap<String, String> videoplaybackapirespone = videoPlayBackURLAPIAPI.getVideoPlayback(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(videoplaybackapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videoplaybackapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, videoplaybackapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videoplaybackapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);	 
 
	    logger.info("Selenium Code is excuting");		 
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.title")),statusValue, driver, sTestcaseName);
		commentspage.clickVideoBasicinformationButton();
		customReport.customizedReport(adduploadvideobean.getDescription(),commentspage.verify_videodescription(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.description")), statusValue, driver,sTestcaseName);	 
		commentspage.click_Sharing();
		commentspage.click_Embed();
		commentspage.click_Option();
		String [] embedcode=commentspage.get_showembedcodetextarea().split(" ");
		String url = null;
		for (String string : embedcode) {
             if(string.contains("src"))
			url=string;
        }
		url.replaceAll("\"", "");
		String embedurl=url.replaceAll("\"", "").replace("src="," ");
		logger.info("Url Value is"+embedurl);
		//playbakckURL
        logger.info("JSON ---->"+apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.playbackUrl"));
		logger.info("EMEDURL------->"+embedurl);
		customReport.customizedReport(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.playbackUrl"), embedurl.split("&")[0],statusValue, driver, sTestcaseName);		 
		customReport.checkinglist(statusValue);
		
	}
	
  	@Test(description = "To verify the Video PlayBackURLAPI With Account Admin  InValid VideoId",groups = {VIDEOPLAYBACKAPI})
	public void TC_09_GET_VideoPlayBackURLAPI_api_check_with_AccountAdmin_InValidVideo_Negative( ) {


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
		uploadvidoeapiresponse.put(IAPIConstantCodes.APIResponseVIDEOID, IVideoPlayBackURLService.invalidVideoId);
		
		PlayBackURLAPI videoPlayBackURLAPIAPI=new PlayBackURLAPI();
		HashMap<String, String> videoplaybackapirespone = videoPlayBackURLAPIAPI.getVideoPlayback(uploadvidoeapiresponse);
		assertionapiresponse.verifyAssert_httpCode(videoplaybackapirespone.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus404);
		customReport.customizedReport(HttpStatusCode.httpsStatus404, videoplaybackapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);	
		customReport.checkinglist(statusValue);	
    		    
	}

  	@Test(description = "To verify the Video PlayBackURLAPI With Event Admin public video",groups = {VIDEOPLAYBACKAPI})
	public void TC_10_GET_VideoPlayBackURLAPI_api_check_with_EventAdmin_Public_Positive( ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() { 	}.getClass().getEnclosingMethod().getName();
		
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

		PlayBackURLAPI videoPlayBackURLAPIAPI=new PlayBackURLAPI();	
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.PlayBackURLeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		uploadvidoeapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, loginapiresponse1.get(IAPIConstantCodes.APIRequestAccessToken));				
	    HashMap<String, String> videoplaybackapirespone = videoPlayBackURLAPIAPI.getVideoPlayback(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(videoplaybackapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videoplaybackapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, videoplaybackapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videoplaybackapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);	 
 
	    logger.info("Selenium Code is excuting");	 
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.title")),statusValue, driver, sTestcaseName);
		commentspage.clickVideoBasicinformationButton();
		customReport.customizedReport(adduploadvideobean.getDescription(),commentspage.verify_videodescription(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.description")), statusValue, driver,sTestcaseName);
		commentspage.click_Sharing();
		commentspage.click_Embed();
		commentspage.click_Option();
		String [] embedcode=commentspage.get_showembedcodetextarea().split(" ");
		String url = null;
		for (String string : embedcode) {
             if(string.contains("src"))
			url=string;
        }
		url.replaceAll("\"", "");
		String embedurl=url.replaceAll("\"", "").replace("src="," ");
		logger.info("Url Value is"+embedurl);
		//playbakckURL
        logger.info("JSON ---->"+apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.playbackUrl"));
		logger.info("EMEDURL------->"+embedurl);
		customReport.customizedReport(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.playbackUrl"), embedurl.split("&")[0],statusValue, driver, sTestcaseName);		 
		customReport.checkinglist(statusValue);
		
	}

  	@Test(description = "To verify the Video PlayBackURLAPI With Event Admin  Valid VideoId",groups = {VIDEOPLAYBACKAPI})
  	public void TC_11_GET_VideoPlayBackURLAPI_api_check_with_EventAdmin_Negative( ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {	}.getClass().getEnclosingMethod().getName();

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
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[1]);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
 
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		HashMap<String,String>loginapiresponse_eventadmin = userservices.doLogin(apiutils.userJson(IUsersList.PlayBackURLeventadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_eventadmin.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse_eventadmin.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
		PlayBackURLAPI videoPlayBackURLAPIAPI=new PlayBackURLAPI();
		HashMap<String, String> videoplaybackapirespone = videoPlayBackURLAPIAPI.getVideoPlayback(loginapiresponse_eventadmin);
		assertionapiresponse.verifyAssert_httpCode(videoplaybackapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, videoplaybackapirespone.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);	 
	  	customReport.checkinglist(statusValue);		
		
	}	
  	
  	@Test(description = "To verify the Video PlayBackURLAPI With Media Contributor with All Users Video",groups = {VIDEOPLAYBACKAPI})
	public void TC_12_GET_VideoPlayBackURLAPI_api_check_with_MediaContributor_AllUsers_Positive( ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() { 	}.getClass().getEnclosingMethod().getName();
		
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
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[2]);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
  		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);

		PlayBackURLAPI videoPlayBackURLAPIAPI=new PlayBackURLAPI();
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.PlayBackURLmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		uploadvidoeapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, loginapiresponse1.get(IAPIConstantCodes.APIRequestAccessToken));				
	    HashMap<String, String> videoplaybackapirespone = videoPlayBackURLAPIAPI.getVideoPlayback(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(videoplaybackapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videoplaybackapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, videoplaybackapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videoplaybackapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);	 
 
	    logger.info("Selenium Code is excuting");		 
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.title")),statusValue, driver, sTestcaseName);
		commentspage.clickVideoBasicinformationButton();
		customReport.customizedReport(adduploadvideobean.getDescription(),commentspage.verify_videodescription(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.description")), statusValue, driver,sTestcaseName);		 
		commentspage.click_Sharing();
		commentspage.click_Embed();
		commentspage.click_Option();
		String [] embedcode=commentspage.get_showembedcodetextarea().split(" ");
		String url = null;
		for (String string : embedcode) {
             if(string.contains("src"))
			url=string;
        }
		url.replaceAll("\"", "");
		String embedurl=url.replaceAll("\"", "").replace("src="," ");
		logger.info("Url Value is"+embedurl);
		//playbakckURL
        logger.info("JSON ---->"+apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.playbackUrl"));
		logger.info("EMEDURL------->"+embedurl);
		customReport.customizedReport(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.playbackUrl"), embedurl.split("&")[0],statusValue, driver, sTestcaseName);		 
		customReport.checkinglist(statusValue);
		
	}

  	@Test(description = "To verify video PlayBackURLAPI with Account Admin and with AA as team contributor and with All Teams access control",groups = {VIDEOPLAYBACKAPI})
  	public void TC_13_GET_VideoPlayBackURLAPI_api_check_with_AccountAdmin_userasteamcontributor_TeamsVideo_Positive( ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {		}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
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
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[3]);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
		
		String accesscontrolforteam = addteambean.getName();
		adduploadvideobean.setAccesscontrolforteam(accesscontrolforteam+"a");
		
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		loginapiresponse.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));		
		PlayBackURLAPI videoPlayBackURLAPIAPI=new PlayBackURLAPI();
		HashMap<String, String> videoplaybackapirespone = videoPlayBackURLAPIAPI.getVideoPlayback(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(videoplaybackapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videoplaybackapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, videoplaybackapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videoplaybackapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);	 
	 
		logger.info("Selenium Code is excuting");			 
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.title")),statusValue, driver, sTestcaseName);
		commentspage.clickVideoBasicinformationButton();
		customReport.customizedReport(adduploadvideobean.getDescription(),commentspage.verify_videodescription(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.description")), statusValue, driver,sTestcaseName);		 
		commentspage.click_Sharing();
		commentspage.click_Embed();
		commentspage.click_Option();
		String [] embedcode=commentspage.get_showembedcodetextarea().split(" ");
		String url = null;
		for (String string : embedcode) {
	    if(string.contains("src"))
		url=string;
	     }
		url.replaceAll("\"", "");
		String embedurl=url.replaceAll("\"", "").replace("src="," ");
		logger.info("Url Value is"+embedurl);
		//playbakckURL
	    logger.info("JSON ---->"+apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.playbackUrl"));
		logger.info("EMEDURL------->"+embedurl);
		customReport.customizedReport(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.playbackUrl"), embedurl.split("&")[0],statusValue, driver, sTestcaseName);		 
		customReport.checkinglist(statusValue);
 
	}
  	

  	@Test(description = "To verify video PlayBackURLAPI with Media Admin and with All Teams access control and with media admin in a group assigned to a team with team admin",groups = {VIDEOPLAYBACKAPI})
  	public void TC_14_GET_VideoPlayBackURLAPI_check_with_MediaAdmin_TeamsVideo_MAaddedtogroup_havingteamadminaccess_Positive() {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.PlayBackURLmediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse1);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		CreateGroupAPI creategroupAPI=new CreateGroupAPI();
		apibeanfactory.CreateGroupBean(addgroupbean);
		String[] userIds = {loginapiresponse1.get("userId")};
		addgroupbean.setUserids(userIds);
		
		JSONObject creategroupjson=creategroupAPI.createGroupJson(addgroupbean);
		creategroupapirespone = creategroupAPI.createGroup(loginapiresponse,creategroupjson);
		assertionapiresponse.verifyAssert_httpCode(creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, creategroupapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		CreateTeamAPI createteamAPI=new CreateTeamAPI();
		apibeanfactory.CreateTeamBean(addteambean);
		addteambean.setTeamMembers(true);	
		
		String[] groupids = {creategroupapirespone.get("groupId")};
		addteambean.setGroupids(groupids);
		addteambean.setTeammemberasuser("");
		addteambean.setTeammemberasgroup(groupids[0]);
				
		JSONObject creatteamjson=createteamAPI.createTeamJson(addteambean);
		createteamapirespone = createteamAPI.createTeam(loginapiresponse,creatteamjson);
		assertionapiresponse.verifyAssert_httpCode(createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, createteamapirespone.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();		
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(IUsersList.PlayBackURLmediaadmin);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[3]);
		String teamname=addteambean.getName();
		String accesscontrolforteam = teamname;
		adduploadvideobean.setAccesscontrolforteam(accesscontrolforteam+"a");
				
	    loginapiresponse1.put("Mandatory", "No");
		loginapiresponse1.put("fileName", "No");
		
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse1, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
				
		loginapiresponse1.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));	
		PlayBackURLAPI videoPlayBackURLAPIAPI=new PlayBackURLAPI();		
		uploadvidoeapiresponse.put(IAPIConstantCodes.APIRequestAccessToken, loginapiresponse1.get(IAPIConstantCodes.APIRequestAccessToken));
				
	    HashMap<String, String> videoplaybackapirespone = videoPlayBackURLAPIAPI.getVideoPlayback(uploadvidoeapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(videoplaybackapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videoplaybackapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, videoplaybackapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videoplaybackapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);	 
 		
	    logger.info("Selenium Code is excuting");			 
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.searchMedia(adduploadvideobean.getTitle());
		AvengerVideoCommentsPage commentspage = avengerdashboardpage.clickVideo(adduploadvideobean.getTitle());
		customReport.customizedReport(true, commentspage.verify_VideoTitleText(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.title")),statusValue, driver, sTestcaseName);
		commentspage.clickVideoBasicinformationButton();
		customReport.customizedReport(adduploadvideobean.getDescription(),commentspage.verify_videodescription(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.description")), statusValue, driver,sTestcaseName);		 
		commentspage.click_Sharing();
		commentspage.click_Embed();
		commentspage.click_Option();
		String [] embedcode=commentspage.get_showembedcodetextarea().split(" ");
		String url = null;
		for (String string : embedcode) {
	    if(string.contains("src"))
		url=string;
	     }
		url.replaceAll("\"", "");
		String embedurl=url.replaceAll("\"", "").replace("src="," ");
		logger.info("Url Value is"+embedurl);
		//playbakckURL
	    logger.info("JSON ---->"+apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.playbackUrl"));
		logger.info("EMEDURL------->"+embedurl);
		customReport.customizedReport(apiutils.JsonParsing(videoplaybackapirespone.get(IAPIConstantCodes.APIRESPONSEJSON),"$.video.playbackUrl"), embedurl.split("&")[0],statusValue, driver, sTestcaseName);		 
		customReport.checkinglist(statusValue);
 
	}
  	

 	@Test(description = "To verify that video PlayBackURLAPI throws error when Media Contributor tries to access Teams Video",groups = {VIDEOPLAYBACKAPI})
  	public void TC_15_GET_VideoPlayBackURLAPI_api_check_with_MediaContributor_TeamsVideo_Negative( ) {

		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {		}.getClass().getEnclosingMethod().getName();

		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
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
		String accesscontrolforteam = addteambean.getName();
		adduploadvideobean.setAccesscontrolforteam(accesscontrolforteam);
		
		UploadVideoService uploadvidoeservice = new UploadVideoService();
		apibeanfactory.UploadVideoBean(adduploadvideobean);
		adduploadvideobean.setUploader(sUserName);
		adduploadvideobean.setEnableComments(IAPIConstantCodes.TRUE);
		adduploadvideobean.setIsActive(IAPIConstantCodes.TRUE);
		adduploadvideobean.setEnableRatings(IAPIConstantCodes.TRUE);
		adduploadvideobean.setVideoAccessControl(IUploadVideoService.videoAccessControl[3]);
		loginapiresponse.put(IUploadVideoService.videoAccessControl[3], accesscontrolforteam);
	    loginapiresponse.put("Mandatory", "No");
		loginapiresponse.put("fileName", "No");
				
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);

		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		assertionapiresponse.verifyAssert_httpCode(uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
						
		HashMap<String,String>loginapiresponse_mediacontributor = userservices.doLogin(apiutils.userJson(IUsersList.PlayBackURLmediacontributor), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse_mediacontributor.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse_mediacontributor);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse_mediacontributor.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		loginapiresponse_mediacontributor.put(IAPIConstantCodes.APIResponseVIDEOID, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseVIDEOID));
		
		PlayBackURLAPI videoPlayBackURLAPIAPI=new PlayBackURLAPI();
		HashMap<String, String> videoplaybackapirespone = videoPlayBackURLAPIAPI.getVideoPlayback(loginapiresponse_mediacontributor);
		assertionapiresponse.verifyAssert_httpCode(videoplaybackapirespone.get(IAPIConstantCodes.APIResponseHttpCode) , HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, videoplaybackapirespone.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);	 
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
	
	public void delete_assignedGroupsForUser(String username) {
		
		launchURL(surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengeruserdashboardpage=avengerHomePage.clickUsersLink();
		avengeruserdashboardpage.clicknewuser_link(username);
		avengeruserdashboardpage.get_AllAssignedgroupsandDelete();
		avengerHomePage.click_logout();
	}
	
	public void check_embedCheckobox() {
		
		launchURL(surl);
		avengerHomePage=loginPage.loginAs(sUserName,sPassword);
		avengerHomePage.clickSettingsLink();
		avengermediasettingspage=avengerHomePage.clickMediaSettingsLink();
		avengerfeaturespage=avengermediasettingspage.click_FeaturesLinkLocator();
		boolean isembedcheckboxchecked = avengerfeaturespage.verify_embedcheckbox();
		if(isembedcheckboxchecked == true) {
			logger.info("Embed checkbox checked");
		}
		else if(isembedcheckboxchecked == false){
			logger.info("Embed checkbox is not checked");
			avengerfeaturespage.checkEmbedCode();
			
		}
			
		avengerHomePage.click_logout();
	}
	
	
	
	}

	

