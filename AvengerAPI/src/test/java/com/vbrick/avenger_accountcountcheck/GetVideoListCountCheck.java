package com.vbrick.avenger_accountcountcheck;
import java.net.MalformedURLException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
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
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbrick.avenger.apibeans.AddVideoCommentBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerEditVideoSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerMediaPage;
import com.vbrick.avenger.pageobjects.AvengerSystemSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerUploadsPage;
import com.vbrick.avenger.pageobjects.AvengerUserPasswordParametersPage;
import com.vbrick.avenger.pageobjects.AvengerVideoBulkEditPage;
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.pageobjects.AvengerVideosPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.service.IVideoAccessControlService;
import com.vbricks.avenger.serviceimpl.CreateCategoryAPI;
import com.vbricks.avenger.serviceimpl.DeleteVideosAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.serviceimpl.VideoListAPI;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class GetVideoListCountCheck extends TestBase {

	private static Logger logger = Logger.getLogger(GetVideoListCountCheck.class);
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
    private AvengerMediaPage avengerMediapage;
	private AvengerUploadsPage avengerUploadsPage;
	private AvengerVideoBulkEditPage avengervideobulkeditpage;
	private AvengerVideosPage avengerVideosPage;
	private BasePage basePage;
	private AddUploadVideoBean adduploadvideobean;
	public ApiBeanFactory apibeanfactory;
	public AddVideoCommentBean addvideocommentbean;
 	private HashMap<String, String> loginapiresponse;
	private HashMap<String, String> uploadvidoeapiresponse;
	private HashMap<String,String> loginapiresponse_mediaviewer;
	private HashMap<String,String> loginapiresponse_eventadmin;
	private HashMap<String,String> loginapiresponse_mediacontributor;
	private HashMap<String,String> loginapiresponse_mediaadmin;
	private HashMap<String, String> createcategoryapirespone;
    private HashMap<String, String> childcategoryapirespone;
	private AddCategoryBean addcategorybean;
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
 
		addcategorybean=new AddCategoryBean();
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
		accountBeansFactory.MailinatorBean(mailinatorbeanpage);

	}

	
	
	@Test(description="To Verify the account count check for GetVideoList API by comparing the Video count b/w UI and API",groups = {GETVIDEOLISTCOUNTCHECK})
	public void TC_01_GET_VideoList_Api_Account_Count_Check() throws InterruptedException {

		
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
		logger.info("Deletion of all existing videos through Bulk Edit option from UI");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerMediapage=avengerdashboardpage.clickMediaTab();
		avengerVideosPage=avengerMediapage.click_AllVideos();
		String beforeVideos = avengerVideosPage.get_totalvideosInAllVideosPage();
		logger.info("Count of videos before bulk deletion :" +beforeVideos);
		
		if (!beforeVideos.equals("0 Videos")) {
		
		avengervideobulkeditpage=avengerVideosPage.clickBulkEditoption();
		avengervideobulkeditpage.checkselectalloption();
		avengervideobulkeditpage.clickBulkEditDeleteOption();
		avengervideobulkeditpage.clickVideoSettingsdailogOKbutton();
		avengervideobulkeditpage.clickVideoSettingsdailogOKbutton();
		Thread.sleep(10000);
		driver.navigate().refresh();
		customReport.customizedReport("(0 Videos)",avengerVideosPage.get_totalvideosInAllVideosPage(),statusValue, driver, sTestcaseName);
		
		}
		
				
		//Upload Video API call
		logger.info("API Level Code is excuting to upload a video");
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
		
		loginapiresponse.put("Mandatory", "Yes");
		loginapiresponse.put("fileName", "Yes");
		
		uploadvidoeapiresponse = uploadvidoeservice.uploadVideos(loginapiresponse, Setup.NOTIFICATION1PATH,adduploadvideobean);
		logger.info("UploadVideo API response Code :::" + uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode));
		customReport.customizedReport(HttpStatusCode.httpsStatusCode, uploadvidoeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		
		//Get Video List API call
		VideoListAPI videolistAPI=new VideoListAPI();
		Thread.sleep(30000);
	    HashMap<String, String> videolistapirespone = videolistAPI.videoList(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(videolistapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videolistapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, videolistapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+videolistapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
	   
	    //to get video title of the videos from the API response
	    
	    String fromattedJSON=apiutils.formatingapiresponse(videolistapirespone.get("responseJSON"));
	    int videoCount = 0;
	    if (fromattedJSON.contains(",")) {
		ArrayList<String> videotitlelist = new ArrayList<String>(Arrays.asList((fromattedJSON.split(","))));
		videoCount = videotitlelist.size();
		}
	    
	    else if (!fromattedJSON.contains(",")) {
	    videoCount = 1;	
	    }
	    
	    String videoCountStr = "";
	    if (videoCount == 1) {
	    	
	    videoCountStr = "One Video";
	    }
	    
	    else if (videoCount > 1) {
	    	
	    videoCountStr = "Issue with API Response";
	    }
	    
		customReport.customizedReport("One Video",videoCountStr,statusValue, driver, sTestcaseName);
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