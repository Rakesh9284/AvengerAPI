package com.vbrick.apidatadeletion;
 

	

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.vbrick.Exception.CustomReport;
import com.vbrick.Exception.Reasons;
import com.vbrick.avenger.apibeansfactory.ApiBeanFactory;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.setup.TestBase;
import com.vbricks.avenger.api.verifications.AssertionAPIResponse;
import com.vbricks.avenger.service.IAPIConstantCodes;
import com.vbricks.avenger.serviceimpl.DMEdeviceslistAPI;
import com.vbricks.avenger.serviceimpl.DeleteDMEAPI;
import com.vbricks.avenger.serviceimpl.GetEventListAPI;
import com.vbricks.avenger.serviceimpl.UserServices;
import com.vbricks.avenger.serviceimpl.ZonesListAPI;
import com.vbricks.avenger.utils.ApiUtils;
import com.vbricks.avenger.utils.HttpStatusCode;

public class DeletionEventAPI extends TestBase {

	private static Logger logger = Logger.getLogger(DeletionEventAPI.class);
	private AvengerLoginPage loginPage;
	private AvengerHomePage homePage;
	private List<String> statusValue;
	private CustomReport customReport;
	@SuppressWarnings("unused")
	private Reasons reasons;
	private String sTestcaseName;
	private ResourceBundle bundle;
	private Locale locale;
	private ReadAndWriteToJSON readgriduserdata;
	private Map<String, String> userdata;
    private ApiUtils apiutils;
    private HashMap<String, String> eventlistapirespone;
	public ApiBeanFactory apibeanfactory;
 	private HashMap<String, String> loginapiresponse;
	private AssertionAPIResponse assertionapiresponse;

	@BeforeClass(alwaysRun = true)
	public void beforeClass() throws Exception {
		reasons = new Reasons("");
		statusValue = new ArrayList<String>();
		customReport = new CustomReport();
		locale = new Locale(language);
		readgriduserdata = new ReadAndWriteToJSON();
		userdata = readgriduserdata.readGridUserData(this.getClass().getSimpleName()); 
		sUserName = userdata.get("Username");
		sPassword = userdata.get("Password");
		apibeanfactory = new ApiBeanFactory();
		assertionapiresponse =new AssertionAPIResponse();
		apiutils=new ApiUtils();
	}

	@BeforeMethod(alwaysRun = true)
	@Parameters(value = { "sbrowser", "sgrid" })
	public void setUP(@Optional(SBROWSER) String sbrowser, @Optional(SVERSION) String sgrid)
			throws MalformedURLException {
		customReport.reset();
	}
   //Added test cases for event deletion.
	@Test(description = "To Delete Events",groups = {DELETEEVENT})
	public void TC_01_DELETE_Events(ITestContext context) {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
		
	 	UserServices userservices = new UserServices();	
		
				
		loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		GetEventListAPI getEventListAPI=new GetEventListAPI();
		eventlistapirespone = getEventListAPI.getEventListID(loginapiresponse,IAPIConstantCodes.DATELIMIT);
	    assertionapiresponse.verifyAssert_httpCode(eventlistapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+eventlistapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, eventlistapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+eventlistapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		String fromattedJSON=apiutils.formatingapiresponse(eventlistapirespone.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> myList = new ArrayList<String>(Arrays.asList(fromattedJSON.split(",")));
		
		for (String string : myList) {
			System.out.println("@array list "+string);
			 loginapiresponse.put(IAPIConstantCodes.APIEVENTID,string);
		  // HashMap<String, String> createeventapirespone = createEventsAPI.createEvent(loginapiresponse,createEventBean);
	  		// Delete Event
		HashMap<String, String> deleteeventapiresponse= new HashMap<String, String>();
		com.vbricks.avenger.serviceimpl.DeleteEventAPI deleteEventAPI = new com.vbricks.avenger.serviceimpl.DeleteEventAPI();
		deleteeventapiresponse = deleteEventAPI.deleteEvents(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(
				deleteeventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deleteeventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),
				HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,
				deleteeventapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deleteeventapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,
				sTestcaseName);
		}
		customReport.checkinglist(statusValue);
		
	}
	
	
	@Test(description = "To Delete DME's",groups = {DELETEDME})
	public void TC_01_DELETE_DMEs(ITestContext context) {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	 	UserServices userservices = new UserServices();	
	 	System.out.println("username is"+apiutils.userJson(sUserName));
	 	System.out.println("URL is"+surl);
	 	loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
	 	System.out.println("login api response"+loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		DMEdeviceslistAPI getDMEListAPI=new DMEdeviceslistAPI();
		eventlistapirespone = getDMEListAPI.getdmedevicesID(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(eventlistapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+eventlistapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, eventlistapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+eventlistapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		String fromattedJSON=apiutils.formatingapiresponse(eventlistapirespone.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> myList = new ArrayList<String>(Arrays.asList(fromattedJSON.split(",")));
		
		for (String string : myList) {
			System.out.println("@array list "+string);
			 loginapiresponse.put(IAPIConstantCodes.DMEDEVICEID,string);
	  		// Delete Event
			 DeleteDMEAPI deleteEventAPI = new DeleteDMEAPI();
			 HashMap<String, String> deletedmeapiresponse = deleteEventAPI.deleteDMEs(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(
				deletedmeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deletedmeapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),
				HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,
				deletedmeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deletedmeapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,
				sTestcaseName);
		}
		customReport.checkinglist(statusValue);
		
	}
			
	@Test(description = "To Delete Zones",groups = {DELETEDME})
	public void TC_01_DELETE_Zones(ITestContext context) {
		
		logger.info("API Level Code is excuting");
		sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	 	UserServices userservices = new UserServices();	
	 	System.out.println("username is"+apiutils.userJson(sUserName));
	 	System.out.println("URL is"+surl);
	 	loginapiresponse = userservices.doLogin(apiutils.userJson(sUserName), surl);
	 	System.out.println("login api response"+loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode), HttpStatusCode.httpsStatusCode200);
		logger.info("Login API response Code :::" + loginapiresponse);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200, loginapiresponse.get(IAPIConstantCodes.APIResponseHttpCode),statusValue, driver, sTestcaseName);
		
		ZonesListAPI getzoneListAPI=new ZonesListAPI();
		eventlistapirespone = getzoneListAPI.getZonesID(loginapiresponse);
	    assertionapiresponse.verifyAssert_httpCode(eventlistapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+eventlistapirespone.get(IAPIConstantCodes.APIResponseStatusInfo), HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK, eventlistapirespone.get(IAPIConstantCodes.APIResponseHttpCode)+eventlistapirespone.get(IAPIConstantCodes.APIResponseStatusInfo),statusValue, driver, sTestcaseName);
		
		String fromattedJSON=apiutils.formatingapiresponse(eventlistapirespone.get(IAPIConstantCodes.APIResponseJSON));
		ArrayList<String> myList = new ArrayList<String>(Arrays.asList(fromattedJSON.split(",")));
		System.out.println("@@@@@my list data"+myList);
		
	/*	for (String string : myList) {
			System.out.println("@array list "+string);
			 loginapiresponse.put(IAPIConstantCodes.DMEDEVICEID,string);
	  		// Delete Event
			 DeleteDMEAPI deleteEventAPI = new DeleteDMEAPI();
			 HashMap<String, String> deletedmeapiresponse = deleteEventAPI.deleteDMEs(loginapiresponse);
		assertionapiresponse.verifyAssert_httpCode(
				deletedmeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deletedmeapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo),
				HttpStatusCode.httpsStatusCode200OK);
		customReport.customizedReport(HttpStatusCode.httpsStatusCode200OK,
				deletedmeapiresponse.get(IAPIConstantCodes.APIResponseHttpCode) + deletedmeapiresponse.get(IAPIConstantCodes.APIResponseStatusInfo), statusValue, driver,
				sTestcaseName);
		}
	*/	customReport.checkinglist(statusValue);
		
	}
	
	
	
	@AfterMethod(alwaysRun = true)
	public void browserClose(ITestResult result) {
		logger.info("In After method class");
		statusValue.clear();
	}
	

}
