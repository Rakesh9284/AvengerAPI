package com.vbrick.avenger.environmentsetup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

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
import com.vbrick.avenger.dao.AddCustomDeviceBeanPage;
import com.vbrick.avenger.dao.AddNewDmeBeanPage;
import com.vbrick.avenger.dao.AddPresentationprofileBeanPage;
import com.vbrick.avenger.funUtil.DateTime;
import com.vbrick.avenger.funUtil.ReadAndWriteToJSON;
import com.vbrick.avenger.pageobjects.AvengerAddCustomDevicePage;
import com.vbrick.avenger.pageobjects.AvengerAddPresentationProfilePage;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerDevicesPage;
import com.vbrick.avenger.pageobjects.AvengerEventDetailsPage;
import com.vbrick.avenger.pageobjects.AvengerEventsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerPresentationProfilesPage;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.setup.TestBase;

public class EventPrerequisites extends TestBase{
	private static Logger logger = Logger.getLogger(EventPrerequisites.class);
	private AvengerLoginPage loginPage;
	private AvengerHomePage homePage;
	private List<String> statusValue;
	private CustomReport customReport;
	private String sTestcaseName;
    private ResourceBundle bundle;
	private Locale locale;
    private BeanFactory accountBeansFactory;
    private AvengerDevicesPage avengerdevicespage;
    private AddNewDmeBeanPage  addnewdmebeanpage;
    private ReadAndWriteToJSON readgriduserdata;
    private Map<String, String> userdata;
    private BasePage basePage;
    private AvengerAddCustomDevicePage avengeraddcustomdevicepage;
    private AddCustomDeviceBeanPage addcustomdevicebeanpage;
    private AvengerPresentationProfilesPage avengerpresentationprofilespage;
    private AvengerAddPresentationProfilePage avengeraddpresentationprofilepage;
	private AddPresentationprofileBeanPage addpresentationprofilebeanpage;
	private AvengerDashboardPage avengerdashboardpage;
	private AvengerEventsPage avengereventspage;
	private AvengerEventDetailsPage avengereventdetailspage;
    
    @BeforeClass(alwaysRun=true)
	public void beforeClass() throws Exception {
		
		statusValue=new ArrayList<String>();
		accountBeansFactory=new BeanFactory();
		addnewdmebeanpage=new AddNewDmeBeanPage(); 
		customReport = new CustomReport();
		locale = new Locale(language);
		readgriduserdata = new ReadAndWriteToJSON();
		userdata = readgriduserdata.readGridUserData(this.getClass().getSimpleName()); //("user47");
		sUserName = userdata.get("Username");
		sPassword = userdata.get("Password");
		addcustomdevicebeanpage = new AddCustomDeviceBeanPage();
		addpresentationprofilebeanpage = new AddPresentationprofileBeanPage();
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
    	basePage= new BasePage(driver,customReport, new BasePage());
    	loginPage = basePage.avengerLoginPage(driver,customReport, basePage);
    	driver.manage().window().maximize();
    	accountBeansFactory.AddNewDmeBean(addnewdmebeanpage);
    	addcustomdevicebeanpage = new AddCustomDeviceBeanPage();
    	accountBeansFactory.createPresentationProfile(addpresentationprofilebeanpage);
    }
    
    static int i=1;
    @Test(description="Creation of Custom Device",groups={"Sanity","Acceptance","BusinessScenario","DeviceAdmin"})
	public void AT_verify_CreationOfCustomDevice_Sprint29_AV5362()
	{
		sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		launchURL(surl);
		homePage = loginPage.loginAs(sUserName, sPassword);
		homePage.clickSettingsLink();
		avengerdevicespage = homePage.clickAllDevicesLink();
		avengerdevicespage.click_AddDevice();
		avengeraddcustomdevicepage = avengerdevicespage.click_CustomDevice();
		addcustomdevicebeanpage.setDevicename("newcustomdevice"+i);
		avengeraddcustomdevicepage.createNewCustomDevice(addcustomdevicebeanpage);
		avengerdevicespage = avengeraddcustomdevicepage.click_CreateButton(addcustomdevicebeanpage);
		customReport.customizedReport(addcustomdevicebeanpage.getDevicename(),avengerdevicespage.verify_Device(addcustomdevicebeanpage.getDevicename()), statusValue, driver, sTestcaseName);
		customReport.checkinglist(statusValue);
		writedatatofile("newcustomdevice"+i);
	}

    @Test(description = "create a presentation profile1 to 30 " ,invocationCount=30)
    public void AT_verify_CreatePresentationProfile_UsedInEvents1_30()
    {
    	sTestcaseName = new Object(){}.getClass().getEnclosingMethod().getName();
    	launchURL(surl);
    	homePage = loginPage.loginAs(sUserName, sPassword);
    	homePage.clickSettingsLink();
    	avengerdevicespage =  homePage.clickAllDevicesLink();
    	/*avengerdevicespage.click_AddDevice();*/
    	/*avengeraddcustomdevicepage = avengerdevicespage.click_CustomDevice();
    	avengeraddcustomdevicepage.createNewCustomDevice(addcustomdevicebeanpage);
    	avengeraddcustomdevicepage.click_CreateButton(addcustomdevicebeanpage);*/
    	avengerpresentationprofilespage= homePage.click_PresentationProfilesTab();
    	avengeraddpresentationprofilepage = avengerpresentationprofilespage.click_AddPresentationprofile();
    	addcustomdevicebeanpage.setDevicename("newcustomdevice"+i);
    	addpresentationprofilebeanpage.setVideosource(addcustomdevicebeanpage.getDevicename());
    	addpresentationprofilebeanpage.setDestinationdevicename(addcustomdevicebeanpage.getDevicename());
    	addpresentationprofilebeanpage.setName("newpresentationprofile"+i);
    	avengeraddpresentationprofilepage.addPresentationProfile(addpresentationprofilebeanpage);
    	avengerpresentationprofilespage = avengeraddpresentationprofilepage.click_createButton();
    	customReport.customizedReport(addpresentationprofilebeanpage.getName(), avengerpresentationprofilespage.check_PresentationProfile(addpresentationprofilebeanpage), statusValue, driver, sTestcaseName);
    	avengerdashboardpage= avengerpresentationprofilespage.click_DashBoardLink();
    	avengereventspage = avengerdashboardpage.click_EventsTab();
    	Map<String,String> date= DateTime.yyyyMMDDMAP();
    	avengereventdetailspage = avengereventspage.click_Date(date.get("currentdate"));
    	avengereventdetailspage.click_ChangePresentationButton();
    	customReport.customizedReport(addpresentationprofilebeanpage.getName(), avengereventdetailspage.verify_PresentationProfile(addpresentationprofilebeanpage), statusValue, driver, sTestcaseName);
    	customReport.checkinglist(statusValue);    	
    	writedatatofile("newpresentationprofile"+i);
    }
	
    	public  void writedatatofile(String customdevicename) {
    		try {
     
     
    			File file = new File("E:\\AvengerUI-7.5.10Cleanupcode-29-5-15\\Avenger-Automation-UI\\AvengerUIAutomation\\eventdata.txt");
     
    			// if file doesnt exists, then create it
    			if (!file.exists()) {
    				file.createNewFile();
    			}
     
    			FileWriter fw = new FileWriter(file.getAbsoluteFile());
    			BufferedWriter bw = new BufferedWriter(fw);
    			bw.write(customdevicename);
    			bw.close();
     
    			customReport.reporter("Writing event data completed", "");
    			
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    
    
	@AfterMethod(alwaysRun=true)
	public void browserClose(ITestResult result)
	{
		i++;
		logger.info("In After method class");
		statusValue.clear();
		if(!result.isSuccess()){
			Reporter.log("Screen shot for failed Test Case" +customReport.AssertionresultOutput(driver, sTestcaseName));
		}
		browserQuit();
	}
}
