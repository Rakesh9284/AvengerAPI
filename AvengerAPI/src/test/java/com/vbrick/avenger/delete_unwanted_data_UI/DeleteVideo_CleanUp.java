package com.vbrick.avenger.delete_unwanted_data_UI;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
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

import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AddUserBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.Setup;
import com.vbrick.avenger.setup.TestBase;
import com.vbrick.avenger.videocommentapi_AV_9879_testcases.SubmitVideosComments_AV_9879;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IDeleteVideoService;
import com.vbricks.avenger.service.IUsersList;
import com.vbricks.avenger.serviceimpl.DeleteVideosAPI;

import com.vbricks.avenger.serviceimpl.UploadVideoService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.ConstantsValue;
import com.vbricks.avenger.utils.HttpStatusCode;

public class DeleteVideo_CleanUp extends TestBase {

	private static Logger logger = Logger.getLogger(DeleteVideo_CleanUp.class);
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
	private HashMap<String, String> loginapiresponse_mediaviewer;
	private HashMap<String, String> loginapiresponse_eventadmin;
	private HashMap<String, String> loginapiresponse_mediacontributor;
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
		assertionapiresponse = new AssertionAPIResponse();
		apiutils = new ApiUtils();
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

	@Test(description = "Delete required Video using DeleteVideoAPi along With Account Admin")
	public void TC_01_DELETE_DeleteVideo_api_check_DeleteVideo_with_AccoutAdmin_Positive() throws InterruptedException {

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.clickMediaTab();
		avengerdashboardpage.click(
				By.xpath(".//*[@id='ng-app']/body/div[1]/div/div[2]/div[1]/div/div/div[1]/div[1]/div/a[1]/span"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(8000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		 jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000); 
		
		List<WebElement> lists = avengerdashboardpage
				.getAllWebElementValues(By.xpath("//a[@class='file-name ng-binding']"));
		logger.info("List size si----" + lists.size());
		ArrayList<String> mylist = new ArrayList<String>();
		for (WebElement el : lists) {

			mylist.add(el.getAttribute("href"));
		}

		ArrayList<String> videoIdLists = new ArrayList<String>();
		logger.info("Selenium Code is excuting");
		for (String href : mylist) {

			logger.info("href values ---is " + href.split("/")[5]);
			videoIdLists.add(href.split("/")[5]);
		}
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.DeleteVideomediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,
				driver, sTestcaseName);
		for (String id : videoIdLists) {
			logger.info("videoId deleting ---is " + id);
			loginapiresponse.put("videoId", id);
			DeleteVideosAPI deleteVideosAPI = new DeleteVideosAPI();
			HashMap<String, String> deleteapirespone = deleteVideosAPI.deleteVideos(loginapiresponse);
			assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get("httpCode") + deleteapirespone.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,deleteapirespone.get("httpCode") + deleteapirespone.get("statusInfo"), statusValue, driver,sTestcaseName);
		}
	}

	@Test(description = "Delete required Video using DeleteVideoAPi along With Account Admin")
	public void TC_02_DELETE_DeleteVideo_api_check_DeleteVideo_with_AccoutAdmin_Positive() throws InterruptedException {

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.clickMediaTab();
		avengerdashboardpage.click(By.xpath(".//*[@id='ng-app']/body/div[1]/div/div[2]/div[1]/div/div/div[1]/div[1]/div/a[1]/span"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(8000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
	
		List<WebElement> lists = avengerdashboardpage
				.getAllWebElementValues(By.xpath("//a[@class='file-name ng-binding']"));
		logger.info("List size si----" + lists.size());
		ArrayList<String> mylist = new ArrayList<String>();
		for (WebElement el : lists) {

			mylist.add(el.getAttribute("href"));
		}

		ArrayList<String> videoIdLists = new ArrayList<String>();
		logger.info("Selenium Code is excuting");
		for (String href : mylist) {

			logger.info("href values ---is " + href.split("/")[5]);
			videoIdLists.add(href.split("/")[5]);
		}
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.DeleteVideomediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,
				driver, sTestcaseName);
		for (String id : videoIdLists) {
			logger.info("videoId deleting ---is " + id);
			loginapiresponse.put("videoId", id);
			DeleteVideosAPI deleteVideosAPI = new DeleteVideosAPI();
			HashMap<String, String> deleteapirespone = deleteVideosAPI.deleteVideos(loginapiresponse);
			assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get("httpCode") + deleteapirespone.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,deleteapirespone.get("httpCode") + deleteapirespone.get("statusInfo"), statusValue, driver,sTestcaseName);
		}
	}

	public void TC_03_DELETE_DeleteVideo_api_check_DeleteVideo_with_AccoutAdmin_Positive() throws InterruptedException {

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.clickMediaTab();
		avengerdashboardpage.click(By.xpath(".//*[@id='ng-app']/body/div[1]/div/div[2]/div[1]/div/div/div[1]/div[1]/div/a[1]/span"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(8000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		
		List<WebElement> lists = avengerdashboardpage
				.getAllWebElementValues(By.xpath("//a[@class='file-name ng-binding']"));
		logger.info("List size si----" + lists.size());
		ArrayList<String> mylist = new ArrayList<String>();
		for (WebElement el : lists) {

			mylist.add(el.getAttribute("href"));
		}

		ArrayList<String> videoIdLists = new ArrayList<String>();
		logger.info("Selenium Code is excuting");
		for (String href : mylist) {

			logger.info("href values ---is " + href.split("/")[5]);
			videoIdLists.add(href.split("/")[5]);
		}
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.DeleteVideomediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,
				driver, sTestcaseName);
		for (String id : videoIdLists) {
			logger.info("videoId deleting ---is " + id);
			loginapiresponse.put("videoId", id);
			DeleteVideosAPI deleteVideosAPI = new DeleteVideosAPI();
			HashMap<String, String> deleteapirespone = deleteVideosAPI.deleteVideos(loginapiresponse);
			assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get("httpCode") + deleteapirespone.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,deleteapirespone.get("httpCode") + deleteapirespone.get("statusInfo"), statusValue, driver,sTestcaseName);
		}
	}

	
	public void TC_04_DELETE_DeleteVideo_api_check_DeleteVideo_with_AccoutAdmin_Positive() throws InterruptedException {

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.clickMediaTab();
		avengerdashboardpage.click(By.xpath(".//*[@id='ng-app']/body/div[1]/div/div[2]/div[1]/div/div/div[1]/div[1]/div/a[1]/span"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(8000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		
		List<WebElement> lists = avengerdashboardpage
				.getAllWebElementValues(By.xpath("//a[@class='file-name ng-binding']"));
		logger.info("List size si----" + lists.size());
		ArrayList<String> mylist = new ArrayList<String>();
		for (WebElement el : lists) {

			mylist.add(el.getAttribute("href"));
		}

		ArrayList<String> videoIdLists = new ArrayList<String>();
		logger.info("Selenium Code is excuting");
		for (String href : mylist) {

			logger.info("href values ---is " + href.split("/")[5]);
			videoIdLists.add(href.split("/")[5]);
		}
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.DeleteVideomediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,
				driver, sTestcaseName);
		for (String id : videoIdLists) {
			logger.info("videoId deleting ---is " + id);
			loginapiresponse.put("videoId", id);
			DeleteVideosAPI deleteVideosAPI = new DeleteVideosAPI();
			HashMap<String, String> deleteapirespone = deleteVideosAPI.deleteVideos(loginapiresponse);
			assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get("httpCode") + deleteapirespone.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,deleteapirespone.get("httpCode") + deleteapirespone.get("statusInfo"), statusValue, driver,sTestcaseName);
		}
	}


	public void TC_06_DELETE_DeleteVideo_api_check_DeleteVideo_with_AccoutAdmin_Positive() throws InterruptedException {

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.clickMediaTab();
		avengerdashboardpage.click(By.xpath(".//*[@id='ng-app']/body/div[1]/div/div[2]/div[1]/div/div/div[1]/div[1]/div/a[1]/span"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(8000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		
		List<WebElement> lists = avengerdashboardpage
				.getAllWebElementValues(By.xpath("//a[@class='file-name ng-binding']"));
		logger.info("List size si----" + lists.size());
		ArrayList<String> mylist = new ArrayList<String>();
		for (WebElement el : lists) {

			mylist.add(el.getAttribute("href"));
		}

		ArrayList<String> videoIdLists = new ArrayList<String>();
		logger.info("Selenium Code is excuting");
		for (String href : mylist) {

			logger.info("href values ---is " + href.split("/")[5]);
			videoIdLists.add(href.split("/")[5]);
		}
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.DeleteVideomediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,
				driver, sTestcaseName);
		for (String id : videoIdLists) {
			logger.info("videoId deleting ---is " + id);
			loginapiresponse.put("videoId", id);
			DeleteVideosAPI deleteVideosAPI = new DeleteVideosAPI();
			HashMap<String, String> deleteapirespone = deleteVideosAPI.deleteVideos(loginapiresponse);
			assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get("httpCode") + deleteapirespone.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,deleteapirespone.get("httpCode") + deleteapirespone.get("statusInfo"), statusValue, driver,sTestcaseName);
		}
	}


	public void TC_07_DELETE_DeleteVideo_api_check_DeleteVideo_with_AccoutAdmin_Positive() throws InterruptedException {

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.clickMediaTab();
		avengerdashboardpage.click(By.xpath(".//*[@id='ng-app']/body/div[1]/div/div[2]/div[1]/div/div/div[1]/div[1]/div/a[1]/span"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(8000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
	
		List<WebElement> lists = avengerdashboardpage
				.getAllWebElementValues(By.xpath("//a[@class='file-name ng-binding']"));
		logger.info("List size si----" + lists.size());
		ArrayList<String> mylist = new ArrayList<String>();
		for (WebElement el : lists) {

			mylist.add(el.getAttribute("href"));
		}

		ArrayList<String> videoIdLists = new ArrayList<String>();
		logger.info("Selenium Code is excuting");
		for (String href : mylist) {

			logger.info("href values ---is " + href.split("/")[5]);
			videoIdLists.add(href.split("/")[5]);
		}
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.DeleteVideomediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,
				driver, sTestcaseName);
		for (String id : videoIdLists) {
			logger.info("videoId deleting ---is " + id);
			loginapiresponse.put("videoId", id);
			DeleteVideosAPI deleteVideosAPI = new DeleteVideosAPI();
			HashMap<String, String> deleteapirespone = deleteVideosAPI.deleteVideos(loginapiresponse);
			assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get("httpCode") + deleteapirespone.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,deleteapirespone.get("httpCode") + deleteapirespone.get("statusInfo"), statusValue, driver,sTestcaseName);
		}
	}

	public void TC_08_DELETE_DeleteVideo_api_check_DeleteVideo_with_AccoutAdmin_Positive() throws InterruptedException {

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.clickMediaTab();
		avengerdashboardpage.click(By.xpath(".//*[@id='ng-app']/body/div[1]/div/div[2]/div[1]/div/div/div[1]/div[1]/div/a[1]/span"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(8000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
	
		List<WebElement> lists = avengerdashboardpage
				.getAllWebElementValues(By.xpath("//a[@class='file-name ng-binding']"));
		logger.info("List size si----" + lists.size());
		ArrayList<String> mylist = new ArrayList<String>();
		for (WebElement el : lists) {

			mylist.add(el.getAttribute("href"));
		}

		ArrayList<String> videoIdLists = new ArrayList<String>();
		logger.info("Selenium Code is excuting");
		for (String href : mylist) {

			logger.info("href values ---is " + href.split("/")[5]);
			videoIdLists.add(href.split("/")[5]);
		}
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.DeleteVideomediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,
				driver, sTestcaseName);
		for (String id : videoIdLists) {
			logger.info("videoId deleting ---is " + id);
			loginapiresponse.put("videoId", id);
			DeleteVideosAPI deleteVideosAPI = new DeleteVideosAPI();
			HashMap<String, String> deleteapirespone = deleteVideosAPI.deleteVideos(loginapiresponse);
			assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get("httpCode") + deleteapirespone.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,deleteapirespone.get("httpCode") + deleteapirespone.get("statusInfo"), statusValue, driver,sTestcaseName);
		}
	}


	public void TC_09_DELETE_DeleteVideo_api_check_DeleteVideo_with_AccoutAdmin_Positive() throws InterruptedException {

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.clickMediaTab();
		avengerdashboardpage.click(By.xpath(".//*[@id='ng-app']/body/div[1]/div/div[2]/div[1]/div/div/div[1]/div[1]/div/a[1]/span"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(8000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
	
		List<WebElement> lists = avengerdashboardpage
				.getAllWebElementValues(By.xpath("//a[@class='file-name ng-binding']"));
		logger.info("List size si----" + lists.size());
		ArrayList<String> mylist = new ArrayList<String>();
		for (WebElement el : lists) {

			mylist.add(el.getAttribute("href"));
		}

		ArrayList<String> videoIdLists = new ArrayList<String>();
		logger.info("Selenium Code is excuting");
		for (String href : mylist) {

			logger.info("href values ---is " + href.split("/")[5]);
			videoIdLists.add(href.split("/")[5]);
		}
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.DeleteVideomediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,
				driver, sTestcaseName);
		for (String id : videoIdLists) {
			logger.info("videoId deleting ---is " + id);
			loginapiresponse.put("videoId", id);
			DeleteVideosAPI deleteVideosAPI = new DeleteVideosAPI();
			HashMap<String, String> deleteapirespone = deleteVideosAPI.deleteVideos(loginapiresponse);
			assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get("httpCode") + deleteapirespone.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,deleteapirespone.get("httpCode") + deleteapirespone.get("statusInfo"), statusValue, driver,sTestcaseName);
		}
	}

	
	public void TC_10_DELETE_DeleteVideo_api_check_DeleteVideo_with_AccoutAdmin_Positive() throws InterruptedException {

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.clickMediaTab();
		avengerdashboardpage.click(By.xpath(".//*[@id='ng-app']/body/div[1]/div/div[2]/div[1]/div/div/div[1]/div[1]/div/a[1]/span"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(8000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		
		List<WebElement> lists = avengerdashboardpage
				.getAllWebElementValues(By.xpath("//a[@class='file-name ng-binding']"));
		logger.info("List size si----" + lists.size());
		ArrayList<String> mylist = new ArrayList<String>();
		for (WebElement el : lists) {

			mylist.add(el.getAttribute("href"));
		}

		ArrayList<String> videoIdLists = new ArrayList<String>();
		logger.info("Selenium Code is excuting");
		for (String href : mylist) {

			logger.info("href values ---is " + href.split("/")[5]);
			videoIdLists.add(href.split("/")[5]);
		}
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.DeleteVideomediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,
				driver, sTestcaseName);
		for (String id : videoIdLists) {
			logger.info("videoId deleting ---is " + id);
			loginapiresponse.put("videoId", id);
			DeleteVideosAPI deleteVideosAPI = new DeleteVideosAPI();
			HashMap<String, String> deleteapirespone = deleteVideosAPI.deleteVideos(loginapiresponse);
			assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get("httpCode") + deleteapirespone.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,deleteapirespone.get("httpCode") + deleteapirespone.get("statusInfo"), statusValue, driver,sTestcaseName);
		}
	}

	@Test(description = "Delete required Video using DeleteVideoAPi along With Account Admin")
	public void TC_05_DELETE_DeleteVideo_api_check_DeleteVideo_with_AccoutAdmin_Positive() throws InterruptedException {

		logger.info("Selenium Code is excuting");
		launchURL(surl);
		customReport.reporter("Application launch with URL : ", surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		AvengerDashboardPage avengerdashboardpage = homePage.avengerDashboardPage();
		avengerdashboardpage.clickMediaTab();
		avengerdashboardpage.click(By.xpath(".//*[@id='ng-app']/body/div[1]/div/div[2]/div[1]/div/div/div[1]/div[1]/div/a[1]/span"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(8000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(10000);
		
		List<WebElement> lists = avengerdashboardpage
				.getAllWebElementValues(By.xpath("//a[@class='file-name ng-binding']"));
		logger.info("List size si----" + lists.size());
		ArrayList<String> mylist = new ArrayList<String>();
		for (WebElement el : lists) {

			mylist.add(el.getAttribute("href"));
		}

		ArrayList<String> videoIdLists = new ArrayList<String>();
		logger.info("Selenium Code is excuting");
		for (String href : mylist) {

			logger.info("href values ---is " + href.split("/")[5]);
			videoIdLists.add(href.split("/")[5]);
		}
		UserServices userservices = new UserServices();
		loginapiresponse = userservices.doLogin(apiutils.userJson(IUsersList.DeleteVideomediaadmin), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get("httpCode"), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get("httpCode"), statusValue,
				driver, sTestcaseName);
		for (String id : videoIdLists) {
			logger.info("videoId deleting ---is " + id);
			loginapiresponse.put("videoId", id);
			DeleteVideosAPI deleteVideosAPI = new DeleteVideosAPI();
			HashMap<String, String> deleteapirespone = deleteVideosAPI.deleteVideos(loginapiresponse);
			assertionapiresponse.verifyAssert_httpCode(deleteapirespone.get("httpCode") + deleteapirespone.get("statusInfo"),HttpStatusCode.httpsStatusCode200OK);
			customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,deleteapirespone.get("httpCode") + deleteapirespone.get("statusInfo"), statusValue, driver,sTestcaseName);
		}
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
