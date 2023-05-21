package com.vbrick.avenger.environmentsetup;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.beansfactory.BeanFactory;
import com.vbrick.avenger.dao.AddApikeyBeanPage;
import com.vbrick.avenger.dao.AddNewDmeBeanPage;
import com.vbrick.avenger.dao.AddNewVbrickDeviceBeanPage;
import com.vbrick.avenger.dao.AddPresentationprofileBeanPage;
import com.vbrick.avenger.dao.AddUserBeanPage;
import com.vbrick.avenger.dao.AvengerAddZoneBeanPage;
import com.vbrick.avenger.dao.AvengerEventDetailsBeanPage;
import com.vbrick.avenger.dao.AvengerRecordingBeanPage;
import com.vbrick.avenger.dao.CreatePasswordBeanPage;
import com.vbrick.avenger.funUtil.ConfiguringActiveDevices;
import com.vbrick.avenger.funUtil.DateTime;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerAddNewDmeDevicePage;
import com.vbrick.avenger.pageobjects.AvengerAddPresentationProfilePage;
import com.vbrick.avenger.pageobjects.AvengerAddUserPage;
import com.vbrick.avenger.pageobjects.AvengerAddVbrickDevicePage;
import com.vbrick.avenger.pageobjects.AvengerAddZonePage;
import com.vbrick.avenger.pageobjects.AvengerApiKeysPage;
import com.vbrick.avenger.pageobjects.AvengerConfirmPasswordPage;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerDevicesPage;
import com.vbrick.avenger.pageobjects.AvengerEditRootAccountPage;
import com.vbrick.avenger.pageobjects.AvengerEventDetailsPage;
import com.vbrick.avenger.pageobjects.AvengerEventsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerMediaSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerPresentationProfilesPage;
import com.vbrick.avenger.pageobjects.AvengerRecordingPage;
import com.vbrick.avenger.pageobjects.AvengerSystemSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerUserDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerUserPasswordParametersPage;
import com.vbrick.avenger.pageobjects.AvengerZonesPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;



public class SetupForConfiguringActiveDevices extends TestBase{

	private static Logger logger = Logger.getLogger(SetupForConfiguringActiveDevices.class);
	private AvengerLoginPage loginPage;
	private AvengerHomePage homePage;
	private List<String> statusValue;
	private CustomReport customReport;
	private Map<String,String> dmedetailsmap;
	private Map<String,String> encoderdetailsmap;
	private Map<String,String> date;
	private String sTestcaseName;
	private ResourceBundle bundle;
	private Locale locale;
	private BeanFactory accountBeansFactory;
	private AvengerDevicesPage avengerdevicespage;
	private AddNewDmeBeanPage  addnewdmebeanpage;
	private AvengerAddNewDmeDevicePage avengeraddnewdmepage;
	private ConfiguringActiveDevices configuringActiveDevices;
	private AvengerSystemSettingsPage avengerSystemSettingsPage;
	private AvengerApiKeysPage apiKeysPage;
	private AddApikeyBeanPage addApikeyBeanPage;
	private AvengerAddVbrickDevicePage addVbrickDevicePage;
	private AddNewVbrickDeviceBeanPage addNewVbrickDeviceBeanPage;
	private AvengerZonesPage avengerZonesPage;
	private AvengerAddZoneBeanPage addFirstZoneBeanPage;
	private AvengerAddZoneBeanPage addSecondZoneBeanPage;
	private AvengerAddZonePage addZonePage;
	private AvengerPresentationProfilesPage avengerPresentationProfilesPage;
	private AvengerAddPresentationProfilePage addPresentationProfilePage;
	private AddPresentationprofileBeanPage addPresentationprofileBeanPage;
	private AvengerDashboardPage avengerDashboardPage;
	private AvengerEventsPage avengerEventsPage;
	private AvengerEventDetailsPage avengerEventDetailsPage;
	private AvengerEventDetailsBeanPage avengerEventDetailsBeanPage;
	private AvengerUserDashboardPage avengerUserDashBoardPage;
	private AvengerAddUserPage addUserPage;
	private AddUserBeanPage addUserBeanPage;
	private AvengerEditRootAccountPage avengerEditRootAccountPage;
	private AvengerConfirmPasswordPage avengerConfirmPasswordPage;
	private CreatePasswordBeanPage createPasswordBeanPage;
	private AvengerMediaSettingsPage avengermediasettingspage; 
	private AvengerRecordingPage avengerrecordingpage;
	private AvengerRecordingBeanPage avengerrecordingbeanpage;
	private AvengerSystemSettingsPage avengersystemsettingspage;
	private AvengerUserPasswordParametersPage avengeruserpasswordparameterspage;
	private BasePage basePage;
	

	@BeforeClass(alwaysRun=true)
	public void beforeClass() throws Exception {

		statusValue=new ArrayList<String>();
		accountBeansFactory=new BeanFactory();
		addnewdmebeanpage=new AddNewDmeBeanPage(); 
		customReport = new CustomReport();
		addApikeyBeanPage = new AddApikeyBeanPage();
		addNewVbrickDeviceBeanPage = new AddNewVbrickDeviceBeanPage();
		addFirstZoneBeanPage = new AvengerAddZoneBeanPage();
		addSecondZoneBeanPage = new AvengerAddZoneBeanPage();
		addPresentationprofileBeanPage = new AddPresentationprofileBeanPage();
		avengerEventDetailsBeanPage = new AvengerEventDetailsBeanPage();
		addUserBeanPage = new AddUserBeanPage();
		createPasswordBeanPage = new CreatePasswordBeanPage();
		avengerrecordingbeanpage= new AvengerRecordingBeanPage();
		locale = new Locale(language);
		
	}
	@BeforeMethod(alwaysRun=true)
	@Parameters(value = {"sbrowser","sgrid"})
	public void setUP(@Optional(SBROWSER)String sbrowser,@Optional(SVERSION) String sgrid) throws MalformedURLException 
	{
		customReport.reset();
		driver = initializeDriver(sbrowser,sgrid);
		logger.info("The driver value is "+driver);
		bundle=ResourceBundle.getBundle("ResourceBundle.BundleFile",locale);
		logger.info("value in bundle is"+bundle.getKeys());
		basePage= new BasePage(driver,customReport, new BasePage());loginPage = basePage.avengerLoginPage(driver,customReport, basePage);
		driver.manage().window().maximize();
		accountBeansFactory.activeDMEBean(addnewdmebeanpage);
		accountBeansFactory.activeEncoderBean(addNewVbrickDeviceBeanPage);
		accountBeansFactory.firstActiveZoneBean(addFirstZoneBeanPage);
		accountBeansFactory.secondActiveZoneBean(addSecondZoneBeanPage);
		accountBeansFactory.createPresentationProfile(addPresentationprofileBeanPage);
		accountBeansFactory.AvengerEventDetailsBean(avengerEventDetailsBeanPage);
		accountBeansFactory.AddUserBean(addUserBeanPage);
		accountBeansFactory.CreatePasswordBean(createPasswordBeanPage);
	}


	@Test(description="Configure DME Device and Encoder and creating event")
	public void AT_Create_SetUpForZoneLogic_MayMilestone() 
	{
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		sUserName="griduser1";
		sPassword="Password@123";
		Map<String, String> zonelogicsetupdetails = new HashMap<String, String>();
		//Configuring DME
		launchURL(DMEURL);
		configuringActiveDevices=basePage.avengerconfiguringDmeDevice(driver, customReport, basePage);
		dmedetailsmap = configuringActiveDevices.configureActiveDME();
		logger.info("DME is Configured");

		//Configuring Encoder
		launchURL(ENCODERURL);
		encoderdetailsmap = configuringActiveDevices.configureActiveEncoder();
		logger.info("Encoder is Configured");

		launchURL(REVDMEURL);
		customReport.reporter("Application launch with URL : ", REVDMEURL);
		homePage = loginPage.loginAs(sUserName,sPassword);
		homePage.clickSettingsLink();
		
	//Changing  session inactivity timeout to 4o minutes
		avengersystemsettingspage=homePage.clickSystemSettingsLink();
		avengeruserpasswordparameterspage=avengersystemsettingspage.click_SecurityLinkLocator();
		avengeruserpasswordparameterspage.enter_maxlogonattemptsallowed("40");
		avengeruserpasswordparameterspage.click_saveButton();
		avengerdevicespage=homePage.clickAllDevicesLink();
		
		//Adding a DME Device With Stream
		avengerdevicespage.click_AddDevice();
		avengeraddnewdmepage=avengerdevicespage.click_AddDmeDevice();
		addnewdmebeanpage.setVideostreamstab("Advanced");
		avengeraddnewdmepage.createNewDme(addnewdmebeanpage);
		avengeraddnewdmepage.checkVODCheckBox();
		avengeraddnewdmepage.clickAddURL();
		zonelogicsetupdetails.put("FirstStreamName", addnewdmebeanpage.getStreamname());
		zonelogicsetupdetails.put("FirstStreamURL", addnewdmebeanpage.getStreamurl());
		addnewdmebeanpage.setStreamname(addnewdmebeanpage.getSecondstreamname());
		addnewdmebeanpage.setStreamurl(addnewdmebeanpage.getSecondstreamingurl());
		zonelogicsetupdetails.put("SecondStreamName", addnewdmebeanpage.getSecondstreamname());
		zonelogicsetupdetails.put("SecondStreamURL", addnewdmebeanpage.getSecondstreamingurl());
		avengeraddnewdmepage.addmultipleURLtoDME(addnewdmebeanpage, 1);
		avengeraddnewdmepage.clickAddURL();
		addnewdmebeanpage.setStreamname(addnewdmebeanpage.getThirdstreamname());
		addnewdmebeanpage.setStreamurl(addnewdmebeanpage.getThirdstreamingurl());
		zonelogicsetupdetails.put("ThirdStreamName", addnewdmebeanpage.getThirdstreamname());
		zonelogicsetupdetails.put("ThirdStreamURL", addnewdmebeanpage.getThirdstreamingurl());
		avengeraddnewdmepage.addmultipleURLtoDME(addnewdmebeanpage, 2);
		avengeraddnewdmepage.clickAddURL();
		addnewdmebeanpage.setStreamname(addnewdmebeanpage.getFourthstreamname());
		addnewdmebeanpage.setStreamurl(addnewdmebeanpage.getFourthstreamingurl());
		zonelogicsetupdetails.put("FourthStreamName",addnewdmebeanpage.getFourthstreamname());
		zonelogicsetupdetails.put("FourthStreamURL", addnewdmebeanpage.getFourthstreamingurl());
		avengeraddnewdmepage.addmultipleURLtoDME(addnewdmebeanpage, 3);
		avengeraddnewdmepage.clickAddURL();
		addnewdmebeanpage.setStreamname(addnewdmebeanpage.getMulticaststreamname());
		addnewdmebeanpage.setStreamurl(addnewdmebeanpage.getMulticaststreamurl());
		zonelogicsetupdetails.put("multicaststreamname",addnewdmebeanpage.getMulticaststreamname());
		zonelogicsetupdetails.put("multicaststreamingurl", addnewdmebeanpage.getMulticaststreamurl());
		avengeraddnewdmepage.addmultipleURLtoDME(addnewdmebeanpage, 4);
		avengerdevicespage = avengeraddnewdmepage.clickCreateButton(addnewdmebeanpage);
		customReport.customizedReport(addnewdmebeanpage.getDevicename(), avengerdevicespage.verify_Device(addnewdmebeanpage.getDevicename()), statusValue, driver, sTestcaseName);
		zonelogicsetupdetails.put("DmeName", addnewdmebeanpage.getDevicename());
		
		//Adding a Encoder
		avengerdevicespage.click_AddDevice();
		addVbrickDevicePage=avengerdevicespage.click_VbrickDevice();
		addVbrickDevicePage.createNewVbrickDevice(addNewVbrickDeviceBeanPage);
		//addVbrickDevicePage.clickRemoveDevice();
		addVbrickDevicePage.clickCreateButton(addNewVbrickDeviceBeanPage);
		customReport.customizedReport(addNewVbrickDeviceBeanPage.getDevicename(), avengerdevicespage.verify_Device(addNewVbrickDeviceBeanPage.getDevicename()), statusValue, driver, sTestcaseName);
		zonelogicsetupdetails.put("EncoderName", addNewVbrickDeviceBeanPage.getDevicename());

		//Creating API Key
		avengerSystemSettingsPage = homePage.clickSystemSettingsLink();
		apiKeysPage = avengerSystemSettingsPage.click_ApikeysLinkLocator();
		zonelogicsetupdetails.put("ApiKey", dmedetailsmap.get("ApiKey"));
		addApikeyBeanPage.setApiname(RandomStringUtils.randomAlphabetic(5));
		addApikeyBeanPage.setApikey(dmedetailsmap.get("ApiKey"));
		apiKeysPage.create_ApiKeys(addApikeyBeanPage);
		customReport.customizedReport(true, apiKeysPage.verify_apikeycreation(addApikeyBeanPage), statusValue, driver, sTestcaseName);
		

		//Verifying log of devices
		avengerdevicespage = homePage.clickAllDevicesLink();
		avengerdevicespage.pause(40000);
		customReport.customizedReport(true, avengerdevicespage.verifyLogOfDevice(addnewdmebeanpage.getDevicename()), statusValue, driver, sTestcaseName);
		avengerdevicespage.closeDeviceLog();
		customReport.customizedReport(true, avengerdevicespage.verifyLogOfDevice(addNewVbrickDeviceBeanPage.getDevicename()), statusValue, driver, sTestcaseName);
		avengerdevicespage.closeDeviceLog();

		//Creating Presentation Profile
		avengerPresentationProfilesPage = homePage.click_PresentationProfilesTab();
		addPresentationProfilePage = avengerPresentationProfilesPage.click_AddPresentationprofile();
		addPresentationprofileBeanPage.setVideosource(addNewVbrickDeviceBeanPage.getDevicename()+" - "+encoderdetailsmap.get("ProgramName"));
		logger.info("Video Source is "+addNewVbrickDeviceBeanPage.getDevicename());
		addPresentationprofileBeanPage.setDestinationdevicename(addnewdmebeanpage.getDevicename());
		addPresentationprofileBeanPage.setName("ActivePresentationProfile");
		addPresentationProfilePage.addPresentationProfile(addPresentationprofileBeanPage);
		//List<String>streamvalues=addPresentationProfilePage.get_StreamNames();
		//logger.info("THe Stream values are"+streamvalues);
		addPresentationProfilePage.addAvailableStreamToAssignedStream(addnewdmebeanpage.getSecondstreamname());
		addPresentationProfilePage.addAvailableStreamToAssignedStream(addnewdmebeanpage.getThirdstreamname());
		addPresentationProfilePage.addAvailableStreamToAssignedStream(addnewdmebeanpage.getFourthstreamname());
		addPresentationProfilePage.addAvailableStreamToAssignedStream(addnewdmebeanpage.getMulticaststreamname());
		avengerPresentationProfilesPage = addPresentationProfilePage.click_createButton();
		customReport.customizedReport(addPresentationprofileBeanPage.getName(), avengerPresentationProfilesPage.check_PresentationProfile(addPresentationprofileBeanPage), statusValue, driver, sTestcaseName);
		zonelogicsetupdetails.put("PPName", addPresentationprofileBeanPage.getName());
		zonelogicsetupdetails.put("ProgramName", encoderdetailsmap.get("ProgramName"));
		
		//Creating Zone and Adding Stream
		avengerZonesPage = homePage.clickZonesTab();
		addZonePage = avengerZonesPage.clickAddZone();
		addFirstZoneBeanPage.setDevicename(addnewdmebeanpage.getDevicename());
		addZonePage.addNewZone(addFirstZoneBeanPage);
		avengerZonesPage = addZonePage.clickCreateButton();
		customReport.customizedReport(addFirstZoneBeanPage.getZonename(), avengerZonesPage.verifyCreatedZone(addFirstZoneBeanPage), statusValue, driver, sTestcaseName);
		avengerZonesPage.clickAddSubZone(addFirstZoneBeanPage);
		addSecondZoneBeanPage.setDevicename(addnewdmebeanpage.getDevicename());
		addZonePage.addNewZone(addSecondZoneBeanPage);
		addZonePage.moveAllAssignedStreamsToAvailableStreams();
		addSecondZoneBeanPage.setStreamname("S2");
		addZonePage.addAvailableStreamToAssignedStream(addSecondZoneBeanPage.getStreamname());
		avengerZonesPage = addZonePage.clickCreateButton();
		avengerZonesPage.clickExpandAll();
	//	customReport.customizedReport(addSecondZoneBeanPage.getZonename(), avengerZonesPage.verifySubZoneCreation(addFirstZoneBeanPage.getZonename()), statusValue, driver, sTestcaseName);
		zonelogicsetupdetails.put("MainZoneName", addFirstZoneBeanPage.getZonename());
		zonelogicsetupdetails.put("SubZoneName", addSecondZoneBeanPage.getZonename());
		zonelogicsetupdetails.put("IPAddress", addFirstZoneBeanPage.getIpaddress());
		
		//Adding Active DME as Recording Device
		avengermediasettingspage=homePage.clickMediaSettingsLink();
		avengerrecordingpage=homePage.clickRecordingsTab();
		avengerrecordingbeanpage.setPrimarydmedevice(addnewdmebeanpage.getDevicename());
		avengerrecordingpage.selectPrimaryDme(avengerrecordingbeanpage);
		avengerrecordingpage.clickSaveButton();
		
		//Creating User and password for Host in Event
		List<String> roles=new ArrayList<String>();
		roles.add("Account Admin");
		avengerUserDashBoardPage = homePage.clickUsersLink();
		addUserPage = avengerUserDashBoardPage.clickAdduser();
		avengerUserDashBoardPage = addUserPage.addUseraccount(addUserBeanPage);
		addUserPage = avengerUserDashBoardPage.click_AddedUserForEditing(addUserBeanPage);
		addUserPage.add_Roles(roles);
		avengerUserDashBoardPage =  addUserPage.click_SaveButton(addUserBeanPage);
		customReport.customizedReport(addUserBeanPage.getUsername(), avengerUserDashBoardPage.verifycreationofuser(addUserBeanPage), statusValue, driver, sTestcaseName);
		avengerEditRootAccountPage=avengerUserDashBoardPage.clicknewuser_link(addUserBeanPage);
		avengerConfirmPasswordPage=avengerEditRootAccountPage.click_UserConfirmationURL();
		loginPage= avengerConfirmPasswordPage.passwordCreation(createPasswordBeanPage);
		zonelogicsetupdetails.put("HostUserName", addUserBeanPage.getUsername());
		zonelogicsetupdetails.put("HostPassword", createPasswordBeanPage.getPassword());
		homePage.click_logout();
		homePage = loginPage.loginAs(sUserName, sPassword);

		//Creating Event
		avengerDashboardPage = homePage.click_avengerDashboardPage();
		avengerEventsPage = avengerDashboardPage.click_EventsTab();
		date= DateTime.yyyyMMDDMAP();
		avengerEventDetailsPage = avengerEventsPage.click_Date(date.get("currentdate"));
		avengerEventDetailsBeanPage.setHost(addUserBeanPage.getFirstname()+" "+addUserBeanPage.getLastname());
		avengerEventDetailsBeanPage.setTitle("ActiveEvent");
		avengerEventDetailsPage.create_Event(avengerEventDetailsBeanPage, addPresentationprofileBeanPage);
		avengerEventDetailsBeanPage.setEndtime(DateTime.addHoursToSystemTime(6));
		avengerEventDetailsPage.enter_EndTime(avengerEventDetailsBeanPage);
		avengerEventDetailsPage.click_SaveButton();
		customReport.customizedReport(avengerEventDetailsBeanPage.getTitle(), avengerEventsPage.verifyEventCreation(avengerEventDetailsBeanPage), statusValue, driver, sTestcaseName);
		zonelogicsetupdetails.put("EventName",avengerEventDetailsBeanPage.getTitle());
		ReadAndWriteToJSON.writeZoneSetupDataToJson(zonelogicsetupdetails);
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
