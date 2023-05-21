package com.vbrick.apiusercreation;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.vbrick.Exception.CustomReport;
import com.vbrick.Exception.Reasons;
import com.vbrick.avenger.apibeans.AddUserApiBean;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AddUserBeanPage;
import com.vbrick.avenger.dao.CreatePasswordBeanPage;
import com.vbrick.avenger.pageobjects.AvengerAddUserPage;
import com.vbrick.avenger.pageobjects.AvengerConfirmPasswordPage;
import com.vbrick.avenger.pageobjects.AvengerEditRootAccountPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerUserDashboardPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;
import com.vbrick.avenger.setup.UserCreationUtility;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.service.IEnvSetupService;
import com.vbricks.avenger.serviceimpl.UserAccountService;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.ConstantsValue;
import com.vbricks.avenger.utils.HttpStatusCode;

public class MediaViewer_UserCreation_API_1_40 extends TestBase{
	private static Logger logger = Logger.getLogger(MediaViewer_UserCreation_API_1_40.class);

	/**
	 * 
	 * @throws Exception
	 */
	private WebDriver driver;

	private AvengerLoginPage loginPage;
	private AvengerHomePage homePage;
	private List<String> statusValue;
	private CustomReport customReport;
	private Reasons reasons;
	private String sTestcaseName;
	private ResourceBundle bundle;
	private Locale locale;
	private BeanFactory accountBeansFactory;
	private AvengerUserDashboardPage avengeruserdashboardpage;
	private AvengerAddUserPage avengeradduserpage;
	private AddUserBeanPage adduserbeanpage;
	private AvengerConfirmPasswordPage avengerconfirmpasswordpage;
	private CreatePasswordBeanPage createpasswordbeanpage;
	private BasePage basePage;
	private AvengerEditRootAccountPage editrootaccountpage;
	private UserCreationUtility userCreationUtility;
	private UserAccountService useraccountservice ;
	public ApiBeanFactory apibeanfactory;
	 private ApiUtils apiutils;
	 private HashMap<String,String> loginapiresponse;
		private HashMap<String,String> uploadvidoeapiresponse;
		private AddUserApiBean adduploadvideobean;
		private HashMap<String,String> createuserdetailsbean;
		private AssertionAPIResponse assertionapiresponse;

	//  HashMap<String, String> failedmap= new HashMap<String, String>();
	@BeforeClass(alwaysRun=true)
	public void beforeClass(ITestContext ctx) throws Exception {
		reasons= new Reasons("");
		statusValue=new ArrayList<String>();
		customReport = new CustomReport();
		accountBeansFactory=new BeanFactory();
		adduserbeanpage=new AddUserBeanPage();
		createpasswordbeanpage=new CreatePasswordBeanPage();
		locale = new Locale(language);
		basePage = new  BasePage();
		createpasswordbeanpage= new CreatePasswordBeanPage();
		String suiteName = ctx.getCurrentXmlTest().getSuite().getName();
		logger.info("The suite name in Before class is "+suiteName);
		userCreationUtility= new UserCreationUtility(suiteName);
		 apiutils=new ApiUtils();
		 assertionapiresponse=new AssertionAPIResponse();
		}

	@BeforeMethod(alwaysRun=true)
	@Parameters(value = {"sbrowser","sgrid"/* , "surl", "sUserName", "sPassword"*/})
	public void setUP(@Optional(SBROWSER)String sbrowser,@Optional(SVERSION) String sgrid/*, String sUserName,String sPassword*/) throws MalformedURLException 
	{
		logger.info("Version value is"+sgrid);
		driver = initializeDriver(sbrowser,sgrid/*, surl ,sversion,sPlatform*/ );
		customReport.reset();
		logger.info("The driver value is "+driver);
		bundle=ResourceBundle.getBundle("ResourceBundle.BundleFile",locale);
		logger.info("value in bundle is"+bundle.getKeys());
		basePage= new BasePage(driver,customReport, new BasePage());
		loginPage = basePage.avengerLoginPage(driver,customReport, basePage);
		driver.manage().window().maximize();
		accountBeansFactory.CreatePasswordBean(createpasswordbeanpage);
		accountBeansFactory.AddUserBean(adduserbeanpage);
	
		}
	
	@Test(description="Account Admin Creation of User 1 -40" ,dataProvider="dataprovider1")
	public void AT_verify_userCreation_1to40( Hashtable<String, String> data) 
	{	
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
 		 logger.info("data is size:::"+data.size());
		ApiBeanFactory bFactory = new ApiBeanFactory();
		AddUserApiBean userbean = new AddUserApiBean();
		bFactory.AddUserBean(userbean);
		userbean.setFirstname(data.get("apiuser"));
		userbean.setLastname(data.get("apiuser"));
        //Selenium Level Code
		launchURL(surl,driver);
		homePage = loginPage.loginAs(IEnvSetupService.apigriduser5,IEnvSetupService.apigridpwd);
		homePage.clickSettingsLink();
		avengeruserdashboardpage=homePage.clickUsersLink();
		editrootaccountpage=avengeruserdashboardpage.clicknewuser_link(userbean);
		avengerconfirmpasswordpage=editrootaccountpage.click_UserConfirmationURL();
		createpasswordbeanpage.setPassword(SANITYPASSWORD);
		createpasswordbeanpage.setConfirmpassword(SANITYPASSWORD);
		createpasswordbeanpage.setSecurityquestion("In what city does your nearest sibling live?");
		createpasswordbeanpage.setTypeyouranswer("HYDERABAD"); 
		loginPage= avengerconfirmpasswordpage.passwordCreation(createpasswordbeanpage);
		homePage.click_logout();
		 
	 
		
	}
	
	@DataProvider (name="dataprovider1")
	public Object[][] userdetails(){
		customReport.reporter("Application launch with URL : ", surl);	
		Object[][] data = new Object[75][1];
		String user="apiuser";
		
		for(int i=0;i<=74;i++){
		Hashtable<String,String> usernamelist=new Hashtable<String,String>();
		UserServices userservices = new UserServices();
 		loginapiresponse = userservices.doLogin(apiutils.userJson(IEnvSetupService.apigriduser5), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) ,statusValue, driver, sTestcaseName);
		logger.info("Login API response Code :::" + loginapiresponse);

		HashMap<String, String> responsedata = userservices.getRolesApi(loginapiresponse, ConstantsValue.mediaViewer);    
		UserAccountService useraccountservice = new UserAccountService();
		ApiBeanFactory bFactory = new ApiBeanFactory();
		AddUserApiBean userbean = new AddUserApiBean();
		bFactory.AddUserBean(userbean);
		userbean.setFirstname(IEnvSetupService.apimediviewuser+i);
		userbean.setUsername(IEnvSetupService.apimediviewuser+i);
		userbean.setLastname(IEnvSetupService.apimediviewuser+i); 
		userbean.setContactemail(IEnvSetupService.apimediviewuser+i+"@gmail.com" ); 		
		 
		// Convert bean to map..................
		ObjectMapper mapper = new ObjectMapper();
		createuserdetailsbean = mapper.convertValue(userbean, HashMap.class);

		HashMap<String,String> usercreateApiResponseData=useraccountservice.createUserAPI(responsedata, ConstantsValue.inputType[0], ConstantsValue.validData, createuserdetailsbean);
        Assert.assertEquals(usercreateApiResponseData.get( IAPIConstantCodes.APIResponseHttpCode)+usercreateApiResponseData.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
	    Assert.assertNotNull(usercreateApiResponseData.get("userId"));
 	    usernamelist.put(user, createuserdetailsbean.get("username"));
	    data[i][0] = usernamelist;
		}
	    
	   
   return data;
	}
 
	
 
	
	@AfterMethod(alwaysRun=true)
	public void browserClose(ITestResult result)
	{
		logger.info("In After method class");
		statusValue.clear();
		if(!result.isSuccess()){
			Reporter.log("Screen shot for failed Test Case" +customReport.AssertionresultOutput(driver, sTestcaseName));
		}
		browserQuit(driver);
	}
}
