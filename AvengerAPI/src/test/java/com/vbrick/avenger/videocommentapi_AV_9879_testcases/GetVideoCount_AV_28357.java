package com.vbrick.avenger.videocommentapi_AV_9879_testcases;
 

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.RandomStringUtils;
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
import com.vbrick.avenger.apibeans.AddGroupBean;
import com.vbrick.avenger.apibeans.AddTeamBean;
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbrick.avenger.apibeans.AddVideoCommentBean;
import com.vbrick.avenger.apibeans.CreateEventBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AddCustomDeviceBeanPage;
import com.vbrick.avenger.dao.AddPresentationprofileBeanPage;
import com.vbrick.avenger.dao.AddUserBeanPage;
import com.vbrick.avenger.dao.AvengerEditVideoSettingsBeanPage;
import com.vbrick.avenger.dao.AvengerGroupsBeanPage;
import com.vbrick.avenger.dao.CreatePasswordBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.LibraryBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerAddCustomDevicePage;
import com.vbrick.avenger.pageobjects.AvengerAddPresentationProfilePage;
import com.vbrick.avenger.pageobjects.AvengerAddUserPage;
import com.vbrick.avenger.pageobjects.AvengerConfirmPasswordPage;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerDevicesPage;
import com.vbrick.avenger.pageobjects.AvengerEditRootAccountPage;
import com.vbrick.avenger.pageobjects.AvengerEditVideoSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerEventDetailsPage;
import com.vbrick.avenger.pageobjects.AvengerGroupsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLibrariesPage;
import com.vbrick.avenger.pageobjects.AvengerLibraryInformationPage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerMediaPage;
import com.vbrick.avenger.pageobjects.AvengerMediaSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerPresentationProfilesPage;
import com.vbrick.avenger.pageobjects.AvengerUserDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerVideosInLibrariesPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.CreateGroupAPI;
import com.vbricks.avenger.serviceimpl.CreateTeamAPI;
import com.vbricks.avenger.serviceimpl.EditGroupAPI;
import com.vbricks.avenger.serviceimpl.GetChannelsForUserAPI;
import com.vbricks.avenger.serviceimpl.TeamListAPI;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;
public class GetVideoCount_AV_28357 extends TestBase{

	private static Logger logger = Logger.getLogger(GetVideoCount_AV_28357.class);
	private AvengerLoginPage loginPage;
	private AvengerHomePage homePage;
	private List<String> statusValue;
	private AvengerMediaPage avengermediapage;
	private LibraryBeanPage librarybeanpage;
	private CustomReport customReport;
	@SuppressWarnings("unused")
	private Reasons reasons;
	private String sTestcaseName;
	private AvengerMediaSettingsPage avengermediasettingspage;
	private AvengerLibraryInformationPage avengerlibraryinformationpage;
	private FileUploadBeanPage fileuploadbeanpage;
	private ResourceBundle bundle;
	private Locale locale;
	private BeanFactory accountBeansFactory;
	private MailinatorBeanPage mailinatorbeanpage;
	private AddTeamBean addteambean;
	private ReadAndWriteToJSON readgriduserdata;
	private Map<String, String> userdata;
	private BasePage basePage;
	private AddUploadVideoBean adduploadvideobean;
	public ApiBeanFactory apibeanfactory;
    public AddVideoCommentBean addvideocommentbean;
	private HashMap<String, String> loginapiresponse;
	private HashMap<String, String> loginapiresponse1;
	private HashMap<String, String> uploadvidoeapiresponse;
	private AddGroupBean addgroupbean;
	private HashMap<String, String> createteamapirespone;
	private HashMap<String, String> loginapiresponseeventadmin;
	private HashMap<String, String> deleteTeamapiresponse;
	private HashMap<String, String> deleteGroupapiresponse;
	private HashMap<String, String> editTeamapiresponse;
	private AssertionAPIResponse assertionapiresponse;
	private AvengerGroupsPage avengergroupspage;
	private AvengerEditVideoSettingsBeanPage avengereditvideosettingsbeanpage;
	private AvengerGroupsBeanPage avengergroupsbeanpage;
	private HashMap<String, String> createcategoryapirespone;
	private HashMap<String, String> childcategoryapirespone;
	private AvengerDevicesPage avengerdevicespage;
	private AvengerEventDetailsPage avengereventdetailspage;
	private AvengerPresentationProfilesPage avengerpresentationprofilespage;
	private HashMap<String, String> editGroupapirespone;
	private AvengerAddCustomDevicePage avengeraddcustomdevicepage;
	private AvengerEditVideoSettingsBeanPage avengereditvideosettingspage;
	private AvengerAddPresentationProfilePage avengeraddpresentationprofilepage;
	private AddPresentationprofileBeanPage addpresentationprofilebeanpage;
    private HashMap<String, String> teamlistapirespone;
	private ApiUtils apiutils; 
	private AddUserBeanPage adduserbeanpage;
	private AddCustomDeviceBeanPage addcustomdevicebeanpage;
	private HashMap<String, String> creategroupapirespone;
	private AvengerHomePage avengerHomePage;
	private AvengerMediaSettingsPage avengermediasettingpage;
	private AvengerLibrariesPage avengerlibrarypage;
	private CreateEventBean createEventBean;
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
		createEventBean = new CreateEventBean();		
		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		addgroupbean=new AddGroupBean();
		addteambean=new AddTeamBean();
		fileuploadbeanpage=new FileUploadBeanPage();
		addvideocommentbean=new AddVideoCommentBean();
		assertionapiresponse =new AssertionAPIResponse();
		apiutils=new ApiUtils();
		avengergroupsbeanpage = new AvengerGroupsBeanPage();
	 
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

	}

	@Test(description="Get Video Count Via Get Channel API For User by Account Admin",groups = {GETCHANNELFORUSER})
	public void TC_01_Get_VideoCount_With_GetChannelForUser_api_check_with_AccountAdmins_Positive(ITestContext context) throws InterruptedException
	{
	sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
	logger.info("Test Case Execution Started:::::::::::"+sTestcaseName);
	
	launchURL(surl);
	homePage = loginPage.loginAs(sUserName, sPassword);
	AvengerDashboardPage dashboardpage=homePage.avengerDashboardPage();
	AvengerMediaPage avengerMediapage=dashboardpage.clickMediaDropDown();
	AvengerLibraryInformationPage avengerlibraryinformatonpage=dashboardpage.click_CreateNewTeam();
	LibraryBeanPage librarybeanpage=new LibraryBeanPage();
	accountBeansFactory.LibraryBeanByPage(librarybeanpage);
	avengerlibraryinformatonpage.enter_libraryNameText(librarybeanpage);
	
	AddUserBeanPage adduserbeanpage=new AddUserBeanPage();
	adduserbeanpage.setFirstname(sUserName);
	adduserbeanpage.setLastname(sUserName);
	adduserbeanpage.setUsername(sUserName);
	avengerlibraryinformatonpage.addUsersToLibrary(adduserbeanpage);
	avengerlibraryinformatonpage.click_TeamRole(adduserbeanpage);
	avengerlibraryinformatonpage.click_TeamMember(adduserbeanpage, 1);
	logger.info("Library Name is"+librarybeanpage.getLibraryname());
	avengerlibraryinformatonpage.clickCreateLibraryInformation();
	dashboardpage.refreshBrowser();
	dashboardpage.pause(5000);
	dashboardpage.clickMediaDropDown();
	dashboardpage.click_ViewAllChannels();//new line added
	AvengerVideosInLibrariesPage avengervideosinlibrariespage=avengerMediapage.click_Createdlibrary(librarybeanpage.getLibraryname());
	
	fileuploadbeanpage.setFilesourcepath(getFilePath(NOTIFICATION1PATH));
	homePage.uploadingFile(fileuploadbeanpage);
	dashboardpage.pause(5000);
	dashboardpage.refreshBrowser();
	
	logger.info("API Level Code is excuting");
	sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
	UserServices userservices = new UserServices();
	loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
	assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
	customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
	logger.info("Login API response Code :::" + loginapiresponse);
			
	//Video Count Check for Get Channel For User	
	GetChannelsForUserAPI getChannelsForUserapiresponse = new GetChannelsForUserAPI();
	JSONObject channeldetailsapiresponse  = getChannelsForUserapiresponse.channelDetailsforvideocountUpdated(loginapiresponse,librarybeanpage.getLibraryname());
    //System.out.println(" the json response is"+channeldetailsapiresponse); 
     //System.out.println("json value"+channeldetailsapiresponse.get("videoCount"));
     //System.out.println("countt....."+channeldetailsapiresponse.get("videoCount").toString());
    customReport.customizedReport(true,channeldetailsapiresponse.get("videoCount").toString().contains("1"), statusValue, driver, sTestcaseName);
    customReport.checkinglist(statusValue);
	
	}
	
	@Test(description="Get Video Count Via Get Channel API For User when multiple videos are uploaded in the channel by Account Admin",groups = {GETCHANNELFORUSER})
	public void TC_02_Get_VideoCount_For_MultipleVideos_With_GetChannelForUser_api_check_with_AccountAdmins_Positive(ITestContext context) throws InterruptedException
	{
	sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
	logger.info("Test Case Execution Started:::::::::::"+sTestcaseName);
	
	launchURL(surl);
	homePage = loginPage.loginAs(sUserName, sPassword);
	AvengerDashboardPage dashboardpage=homePage.avengerDashboardPage();
	AvengerMediaPage avengerMediapage=dashboardpage.clickMediaDropDown();
	AvengerLibraryInformationPage avengerlibraryinformatonpage=dashboardpage.click_CreateNewTeam();
	
	LibraryBeanPage librarybeanpage=new LibraryBeanPage();
	accountBeansFactory.LibraryBeanByPage(librarybeanpage);
	avengerlibraryinformatonpage.enter_libraryNameText(librarybeanpage);
	AddUserBeanPage adduserbeanpage=new AddUserBeanPage();
	adduserbeanpage.setFirstname(sUserName);
	adduserbeanpage.setLastname(sUserName);
	adduserbeanpage.setUsername(sUserName);
	avengerlibraryinformatonpage.addUsersToLibrary(adduserbeanpage);
	avengerlibraryinformatonpage.click_TeamRole(adduserbeanpage);
	avengerlibraryinformatonpage.click_TeamMember(adduserbeanpage, 1);
	logger.info("Library Name is"+librarybeanpage.getLibraryname());
	avengerlibraryinformatonpage.clickCreateLibraryInformation();
	dashboardpage.refreshBrowser();
	dashboardpage.pause(5000);
	dashboardpage.clickMediaDropDown();
	dashboardpage.click_ViewAllChannels();//new line added
	AvengerVideosInLibrariesPage avengervideosinlibrariespage=avengerMediapage.click_Createdlibrary(librarybeanpage.getLibraryname());
	
	fileuploadbeanpage.setFilesourcepath(getFilePath(NOTIFICATION1PATH));
	homePage.uploadingFile(fileuploadbeanpage);
	dashboardpage.pause(5000);
	dashboardpage.refreshBrowser();
	dashboardpage.pause(5000);
	fileuploadbeanpage.setFilesourcepath(getFilePath(NOTIFICATION1PATH));
	homePage.uploadingFile(fileuploadbeanpage);
	
	logger.info("API Level Code is excuting");
	sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
	UserServices userservices = new UserServices();
	loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
	assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
	customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
	logger.info("Login API response Code :::" + loginapiresponse);
			
	//Video Count Check for Get Channel For User	
	GetChannelsForUserAPI getChannelsForUserapiresponse = new GetChannelsForUserAPI();
	JSONObject channeldetailsapiresponse  = getChannelsForUserapiresponse.channelDetailsforvideocountUpdated(loginapiresponse,librarybeanpage.getLibraryname());
    customReport.customizedReport(true,channeldetailsapiresponse.get("videoCount").toString().contains("2"), statusValue, driver, sTestcaseName);
    customReport.checkinglist(statusValue);
	
	}
	
	@Test(description="Get Video Count Via Get Channel API For User by Media Viewer who has only view access to the channel",groups = {GETCHANNELFORUSER}) 
	//Should get the count of only active videos according to the story..though we have uploaded 2 videos, only 1 is active. so 1 video count should be returned
	public void TC_03_Get_VideoCount_With_GetChannelForUser_api_check_with_MediaViewer_HavingOnlyViewAccess_Positive(ITestContext context) throws InterruptedException
	{
	sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
	logger.info("Test Case Execution Started:::::::::::"+sTestcaseName);
	
	launchURL(surl);
	homePage = loginPage.loginAs(sUserName, sPassword);
	AvengerDashboardPage dashboardpage=homePage.avengerDashboardPage();
	AvengerMediaPage avengerMediapage=dashboardpage.clickMediaDropDown();
	AvengerLibraryInformationPage avengerlibraryinformatonpage=dashboardpage.click_CreateNewTeam();
	
	LibraryBeanPage librarybeanpage=new LibraryBeanPage();
	accountBeansFactory.LibraryBeanByPage(librarybeanpage);
	avengerlibraryinformatonpage.enter_libraryNameText(librarybeanpage);
	AddUserBeanPage adduserbeanpage=new AddUserBeanPage();
	adduserbeanpage.setFirstname(sUserName);
	adduserbeanpage.setLastname(sUserName);
	adduserbeanpage.setUsername(sUserName);
	avengerlibraryinformatonpage.addUsersToLibrary(adduserbeanpage);
	avengerlibraryinformatonpage.click_TeamRole(adduserbeanpage);
	avengerlibraryinformatonpage.click_TeamMember(adduserbeanpage, 1);	
	adduserbeanpage.setFirstname("apimvu21");
	adduserbeanpage.setLastname("apimvu21");
	adduserbeanpage.setUsername("apimvu21");
	avengerlibraryinformatonpage.addUsersToLibrary(adduserbeanpage);
	avengerlibraryinformatonpage.click_TeamRole(adduserbeanpage);
	avengerlibraryinformatonpage.click_TeamMember(adduserbeanpage, 3);
	
	logger.info("Library Name is"+librarybeanpage.getLibraryname());
	avengerlibraryinformatonpage.clickCreateLibraryInformation();
	dashboardpage.refreshBrowser();
	dashboardpage.pause(5000);
	dashboardpage.clickMediaDropDown();
	dashboardpage.click_ViewAllChannels();//new line added
	AvengerVideosInLibrariesPage avengervideosinlibrariespage=avengerMediapage.click_Createdlibrary(librarybeanpage.getLibraryname());
	
	fileuploadbeanpage.setFilesourcepath(getFilePath(NOTIFICATION1PATH));
	homePage.uploadingFile(fileuploadbeanpage);
	dashboardpage.pause(5000);
	dashboardpage.refreshBrowser();
	dashboardpage.pause(5000);
	
	fileuploadbeanpage.setFilesourcepath(getFilePath(NOTIFICATION1PATH));
	homePage.uploadingFile(fileuploadbeanpage);
	AvengerEditVideoSettingsPage avengereditvideosettingspage= homePage.click_EditSettings(fileuploadbeanpage);
	AvengerEditVideoSettingsBeanPage editvideosettingsbean=new AvengerEditVideoSettingsBeanPage();
	accountBeansFactory.AvengerEditVideoSettingsBeanByPage(editvideosettingsbean);
	avengereditvideosettingspage.clickActive();
	avengereditvideosettingspage.click_saveButton(editvideosettingsbean);
	
	logger.info("API Level Code is excuting");
	sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
	UserServices userservices = new UserServices();
	loginapiresponse = userservices.doLogin(apiutils.userJson("apimvu21"), surl);
	assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
	customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
	logger.info("Login API response Code :::" + loginapiresponse);
			
	//Video Count Check for Get Channel For User	
	GetChannelsForUserAPI getChannelsForUserapiresponse = new GetChannelsForUserAPI();
	JSONObject channeldetailsapiresponse  = getChannelsForUserapiresponse.channelDetailsforvideocountUpdated(loginapiresponse,librarybeanpage.getLibraryname());
    customReport.customizedReport(true,channeldetailsapiresponse.get("videoCount").toString().contains("1"), statusValue, driver, sTestcaseName);
    customReport.checkinglist(statusValue);
	
	}
	
	@Test(description="Get Video Count Via Get Channel API For User by Media Viewer who has Edit access to the channel",groups = {GETCHANNELFORUSER}) 
	//Should get the count of both active & inactive videos according to the story..since mediaviewer has edit rights to the channel, all the active & inactive video count should be returned
	public void TC_04_Get_VideoCount_With_GetChannelForUser_api_check_with_MediaViewer_HavingOnlyViewAccess_Positive(ITestContext context) throws InterruptedException
	{
	sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
	logger.info("Test Case Execution Started:::::::::::"+sTestcaseName);
	
	launchURL(surl);
	homePage = loginPage.loginAs(sUserName, sPassword);
	AvengerDashboardPage dashboardpage=homePage.avengerDashboardPage();
	AvengerMediaPage avengerMediapage=dashboardpage.clickMediaDropDown();
	AvengerLibraryInformationPage avengerlibraryinformatonpage=dashboardpage.click_CreateNewTeam();
	LibraryBeanPage librarybeanpage=new LibraryBeanPage();
	accountBeansFactory.LibraryBeanByPage(librarybeanpage);
	avengerlibraryinformatonpage.enter_libraryNameText(librarybeanpage);
	
	AddUserBeanPage adduserbeanpage=new AddUserBeanPage();
	adduserbeanpage.setFirstname(sUserName);
	adduserbeanpage.setLastname(sUserName);
	adduserbeanpage.setUsername(sUserName);
	avengerlibraryinformatonpage.addUsersToLibrary(adduserbeanpage);
	avengerlibraryinformatonpage.click_TeamRole(adduserbeanpage);
	avengerlibraryinformatonpage.click_TeamMember(adduserbeanpage, 1);
	
	avengerlibraryinformatonpage.enter_libraryNameText(librarybeanpage);
	adduserbeanpage.setFirstname("apimvu21");
	adduserbeanpage.setLastname("apimvu21");
	adduserbeanpage.setUsername("apimvu21");
	avengerlibraryinformatonpage.addUsersToLibrary(adduserbeanpage);
	avengerlibraryinformatonpage.click_TeamRole(adduserbeanpage);
	avengerlibraryinformatonpage.click_TeamMember(adduserbeanpage, 1);
	
	logger.info("Library Name is"+librarybeanpage.getLibraryname());
	avengerlibraryinformatonpage.clickCreateLibraryInformation();
	dashboardpage.refreshBrowser();
	dashboardpage.pause(5000);
	dashboardpage.clickMediaDropDown();
	dashboardpage.click_ViewAllChannels();//new line added
	AvengerVideosInLibrariesPage avengervideosinlibrariespage=avengerMediapage.click_Createdlibrary(librarybeanpage.getLibraryname());
	String channelid=avengervideosinlibrariespage.getCurrentUrl();
	
	fileuploadbeanpage.setFilesourcepath(getFilePath(NOTIFICATION1PATH));
	homePage.uploadingFile(fileuploadbeanpage);
	dashboardpage.pause(5000);
	dashboardpage.refreshBrowser();
	dashboardpage.pause(5000);
	
	fileuploadbeanpage.setFilesourcepath(getFilePath(NOTIFICATION1PATH));
	homePage.uploadingFile(fileuploadbeanpage);
	AvengerEditVideoSettingsPage avengereditvideosettingspage= homePage.click_EditSettings(fileuploadbeanpage);
	AvengerEditVideoSettingsBeanPage editvideosettingsbean=new AvengerEditVideoSettingsBeanPage();
	accountBeansFactory.AvengerEditVideoSettingsBeanByPage(editvideosettingsbean);
	avengereditvideosettingspage.clickActive();
	avengereditvideosettingspage.click_saveButton(editvideosettingsbean);
	
	logger.info("API Level Code is excuting");
	sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
	UserServices userservices = new UserServices();
	loginapiresponse = userservices.doLogin(apiutils.userJson("apimvu21"), surl);
	assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
	customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
	logger.info("Login API response Code :::" + loginapiresponse);
			
	//Video Count Check for Get Channel For User	
	GetChannelsForUserAPI getChannelsForUserapiresponse = new GetChannelsForUserAPI();
	JSONObject channeldetailsapiresponse  = getChannelsForUserapiresponse.channelDetailsforvideocountUpdated(loginapiresponse,librarybeanpage.getLibraryname());
	customReport.customizedReport(true,channeldetailsapiresponse.get("videoCount").toString().contains("2"), statusValue, driver, sTestcaseName);
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
