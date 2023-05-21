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
import com.vbrick.avenger.apibeans.AddDMEBean;
import com.vbrick.avenger.apibeans.AddPlaylistBean;
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbrick.avenger.apibeans.AddVideoCommentBean;
import com.vbrick.avenger.apibeans.DeleteVideoCommentBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerFeaturesPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerMediaSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.ICreateDevice;
import com.vbricks.avenger.service.IDeleteDmeDevice;
import com.vbricks.avenger.service.IDeleteVideoService;
import com.vbricks.avenger.service.IMegratVideoService;
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IUserVideoWatchingStatusService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.service.IVideoAccessControlService;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.serviceimpl.CreateDME_DeviceAPI;
import com.vbricks.avenger.serviceimpl.DeleteDmeDevicesAPI;
import com.vbricks.avenger.serviceimpl.DeleteVideoCommentsAPI;
import com.vbricks.avenger.serviceimpl.DeleteVideosAPI;
import com.vbricks.avenger.serviceimpl.EditVideoComments;
import com.vbricks.avenger.serviceimpl.VideoCommentsAPI;
import com.vbricks.avenger.serviceimpl.GetVideoStatusAPI;
import com.vbricks.avenger.serviceimpl.SearchVideoAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoImageAPI;
import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.serviceimpl.UserVideoCompletionAPI;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.ConstantsValue;
import com.vbricks.avenger.utils.HttpStatusCode;

import groovy.json.JsonSlurper;

public class DeleteDMEDevicesAPI extends TestBase {

	private static Logger logger = Logger.getLogger(DeleteDMEDevicesAPI.class);
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
	private AddPlaylistBean addplaylistbean;
	private FileUploadBeanPage fileuploadbeanpage;
	private AvengerFeaturesPage avengerfeaturespage;
	private ReadAndWriteToJSON readgriduserdata;
	private Map<String, String> userdata;
	private BasePage basePage;
	private AddUploadVideoBean adduploadvideobean;
	public ApiBeanFactory apibeanfactory;
	public AddDMEBean addDmeBean;
    public AddVideoCommentBean addvideocommentbean;
    private DeleteVideoCommentBean deleteVideoCommentBean;
    private HashMap<String, String> loginapiresponse1;
	private HashMap<String, String> loginapiresponse;
	private HashMap<String, String> uploadvidoeapiresponse;
	private HashMap<String, String> loginapiresponse_eventadmin;
	private HashMap<String, String> loginapiresponse_mediaviewer;
	 private ApiUtils apiutils;
	 private AssertionAPIResponse assertionapiresponse;
	
	@BeforeClass(alwaysRun = true)
	public void beforeClass() throws Exception {
		reasons = new Reasons("");
		statusValue = new ArrayList<String>();
		customReport = new CustomReport();
		accountBeansFactory = new BeanFactory();
		mailinatorbeanpage = new MailinatorBeanPage();
		fileuploadbeanpage = new FileUploadBeanPage();
		locale = new Locale(language);
		readgriduserdata = new ReadAndWriteToJSON();
		userdata = readgriduserdata.readGridUserData(this.getClass().getSimpleName()); // ("user52");
		sUserName = userdata.get("Username");
		sPassword = userdata.get("Password");

		adduploadvideobean = new AddUploadVideoBean();
		apibeanfactory = new ApiBeanFactory();
		adduploadvideobean = new AddUploadVideoBean();
		addDmeBean = new AddDMEBean();
		addvideocommentbean=new AddVideoCommentBean();
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
	@Test(description="DELETE DME Device API by Account Admin",groups = {DELETEDMEDEVICE})
	public void TC_01_DleteDMEDevice_api_check_with_AccountAdmin_Positive(ITestContext context) {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		apibeanfactory.createDMEBean(addDmeBean);
		CreateDME_DeviceAPI createDME_DeviceAPI = new CreateDME_DeviceAPI();
		HashMap<String, String> CreatingaDeviceapiresponse = createDME_DeviceAPI.CreatingaDevice(loginapiresponse, createDME_DeviceAPI.createDevice(addDmeBean.getName(), addDmeBean.getMacAddress(), addDmeBean.getIsActive()));
		assertionapiresponse.verifyAssert_httpCode(CreatingaDeviceapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, CreatingaDeviceapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		DeleteDmeDevicesAPI deleteDmeDevicesAPI = new DeleteDmeDevicesAPI();
		HashMap<String, String> DeleteDmeDevicesAPIapiresponse = deleteDmeDevicesAPI.deleteDMEdevice(CreatingaDeviceapiresponse);
		assertionapiresponse.verifyAssert_httpCode(DeleteDmeDevicesAPIapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+DeleteDmeDevicesAPIapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, DeleteDmeDevicesAPIapiresponse.get(IAPIConstantCodes.APIResponseHttpCode)+DeleteDmeDevicesAPIapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
}
	
	@Test(description="DELETE DME Device API by Account Admin with Invalid deviceId",groups = {DELETEDMEDEVICE})
	public void TC_02_DeleteDMEDevice_api_check_with_AccountAdmin_ByGivingInvalid_deviceId_Negative(ITestContext context) {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);
		
		DeleteDmeDevicesAPI deleteDmeDevicesAPI = new DeleteDmeDevicesAPI();
		loginapiresponse.put("deviceId", IDeleteDmeDevice.invalidDeviceID);
		HashMap<String, String> DeleteDmeDevicesAPIapiresponse = deleteDmeDevicesAPI.deleteDMEdevice(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(DeleteDmeDevicesAPIapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, DeleteDmeDevicesAPIapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		//customReport.customizedReport(CreatingaDeviceapiresponse.get(IAPIConstantCodes.APIResponseJsonErrorDetail), HttpStatusCode.errordetailforDME, statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);	

}
	
	@Test(description="DELETE DME Device API by a user who doesn't have access",groups = {DELETEDMEDEVICE})
	public void TC_03_DeleteDMEDevice_api_check_with_MediaViewer_Negative(ITestContext context) {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		apibeanfactory.createDMEBean(addDmeBean);;
		CreateDME_DeviceAPI createDME_DeviceAPI = new CreateDME_DeviceAPI();
		HashMap<String, String> CreatingaDeviceapiresponse = createDME_DeviceAPI.CreatingaDevice(loginapiresponse, createDME_DeviceAPI.createDevice(addDmeBean.getName(), addDmeBean.getMacAddress(), addDmeBean.getIsActive()));
		assertionapiresponse.verifyAssert_httpCode(CreatingaDeviceapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, CreatingaDeviceapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	
		loginapiresponse1 = userservices.doLogin(apiutils.userJson(IUsersList.DeleteDME_MediaViewer), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse1.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse1);
		loginapiresponse1.put(IAPIConstantCodes.DEVICEID, CreatingaDeviceapiresponse.get(IAPIConstantCodes.DEVICEID));

		DeleteDmeDevicesAPI deleteDmeDevicesAPI = new DeleteDmeDevicesAPI();
		HashMap<String, String> DeleteDmeDevicesAPIapiresponse = deleteDmeDevicesAPI.deleteDMEdevice(loginapiresponse1);
		assertionapiresponse.verifyAssert_httpCode(DeleteDmeDevicesAPIapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, DeleteDmeDevicesAPIapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
	}
	
	@Test(description="DELETE DME Device API without Authorization Token",groups = {DELETEDMEDEVICE})
	public void TC_04_DeleteDMEDevice_api_check_with_MediaViewer_without_Authorization_Negative(ITestContext context) throws InterruptedException {
 
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		apibeanfactory.createDMEBean(addDmeBean);
		CreateDME_DeviceAPI createDME_DeviceAPI = new CreateDME_DeviceAPI();
		HashMap<String, String> CreatingaDeviceapiresponse = createDME_DeviceAPI.CreatingaDevice(loginapiresponse, createDME_DeviceAPI.createDevice(addDmeBean.getName(), addDmeBean.getMacAddress(), addDmeBean.getIsActive()));
		assertionapiresponse.verifyAssert_httpCode(CreatingaDeviceapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, CreatingaDeviceapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
	
		DeleteDmeDevicesAPI deleteDmeDevicesAPI = new DeleteDmeDevicesAPI();
		HashMap<String, String> DeleteDmeDevicesAPIapiresponse = deleteDmeDevicesAPI.deleteDMEdevicewithoutauthorization(CreatingaDeviceapiresponse);
		assertionapiresponse.verifyAssert_httpCode(DeleteDmeDevicesAPIapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatus401);
		customReport.customizedReport(HttpStatusCode.httpsStatus401, DeleteDmeDevicesAPIapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
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