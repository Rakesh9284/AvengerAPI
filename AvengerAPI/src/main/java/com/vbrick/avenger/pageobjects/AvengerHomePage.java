package com.vbrick.avenger.pageobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.vbrick.Exception.CustomReport;
import com.vbrick.Exception.ReasonsTimeOutException;
import com.vbrick.avenger.ObjProperty.AddUserPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerDMENetworkStatisticsPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerDashboardPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerDevicesPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerEditVideoSettingsPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerFeaturesPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerGroupsPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerHomePropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerLoginPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerMediaPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerPresentationProfilesPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerRecordingPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerSystemSettingsPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerUploadPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerUserDashBoardPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerUserPasswordParamatersPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerVideoCommentsPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerZonesPropertyPage;
import com.vbrick.avenger.ObjProperty.CreateChildAccountPropertyPage;
import com.vbrick.avenger.ObjProperty.ManageAccountPage;
import com.vbrick.avenger.ObjProperty.MediaSettingsPropertyPage;
import com.vbrick.avenger.dao.AvengerFeaturesBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.funUtil.FileOperation;
import com.vbrick.avenger.funUtil.TypeCasting;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerHomePage extends WebElements {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// --- Variable Declaration
	private WebDriver driver;
	
	private static Logger logger = Logger.getLogger(AvengerHomePage.class);
	CustomReport customReport;
	private BasePage basePage;
	
	By videoanalyticsCSVbutton=By.xpath("//a[contains(@href,'analytics')]");
	By carsoealsliderlocator=By.xpath("//*[@id='feature-carousel']/a[@ng-click='prev()']");
	// -- Object Properties -----
	By addVideoLocator = By.xpath(AvengerHomePropertyPage.homepg_addVideoLocator.getProperty());
	By addAccountLocator = By.xpath(ManageAccountPage.mngAccount_addAccountLocator.getProperty()); 
	By adminlink =By.linkText(AvengerHomePropertyPage.homepg_adminlink.getProperty());	
	By userslink =By.xpath(AvengerHomePropertyPage.homepg_userslink.getProperty());
	By usersdropdown=By.xpath(AvengerHomePropertyPage.homepg_userdropdwn.getProperty());
	By licensed =By.xpath(AvengerHomePropertyPage.homepg_licensed.getProperty());
	By unlicensed =By.xpath(AvengerHomePropertyPage.homepg_unlicensed.getProperty());
	By total =By.xpath(AvengerHomePropertyPage.homepg_total.getProperty());
	By accountlicensed =By.xpath(AvengerHomePropertyPage.homepg_accountlicensed.getProperty());
	By unusedlicenses =By.xpath(AvengerHomePropertyPage.homepg_unusedlicenses.getProperty());
	By usersaccountlicensed=By.xpath(AvengerHomePropertyPage.homepg_usersaccountlicensed.getProperty());
	By groupslink =By.xpath(AvengerHomePropertyPage.homepg_groupslink.getProperty());	
	By deletegroup =By.xpath(AvengerHomePropertyPage.delete_Groups.getProperty());
	By deletecategories =By.xpath(AvengerHomePropertyPage.delete_Categories.getProperty());
	By deletePresentationProfiles =By.xpath(AvengerHomePropertyPage.delete_PresentationProfiles.getProperty());
	By deleteDevices =By.xpath(AvengerHomePropertyPage.delete_Devices.getProperty());
	By clickdeleteDevice =By.xpath(AvengerHomePropertyPage.clickdelete_Devices.getProperty());
	By confirmdeletecategory =By.xpath(AvengerHomePropertyPage.confirm_delete_Category.getProperty());
	By confirmdeletepresentationprofile =By.xpath(AvengerHomePropertyPage.confirm_delete_Presentationprofile.getProperty());
	By confirmdeletedevice =By.xpath(AvengerHomePropertyPage.confirm_delete_Device.getProperty());
	By deletecategory =By.xpath(AvengerHomePropertyPage.delete_Groups.getProperty());
	By deletechannel =By.xpath(AvengerHomePropertyPage.delete_Channel.getProperty());
	By confirmdeletegroup =By.xpath(AvengerHomePropertyPage.confirm_delete_Groups.getProperty());
	By confirmdeletechannel =By.xpath(AvengerHomePropertyPage.confirm_delete_Channel.getProperty());
	By channelslink =By.xpath(AvengerHomePropertyPage.homepg_channelslink.getProperty());
	By loginlink =By.linkText(AvengerHomePropertyPage.homepg_loginlink.getProperty());	
	By systemsettingslink =By.linkText(AvengerHomePropertyPage.homepg_settingslink.getProperty());	
	By userpasswordtext=By.xpath(AvengerUserPasswordParamatersPropertyPage.avengeruserpasswordparameters_userpasswordtext.getProperty());
	By createbuttonlocator=By.xpath(AddUserPropertyPage.adduser_createbuttonlocator.getProperty());	
	By adduserlink=By.linkText(AvengerUserDashBoardPropertyPage.avengeruserdashboard_adduserlink.getProperty());	
	By creategroupinputlocator=By.xpath(AvengerGroupsPropertyPage.avengergrouppage_newgroupname.getProperty());
	By mediasettings=By.xpath(AvengerHomePropertyPage.homepg_mediasettingslink.getProperty());	
	By categorieslinklocator=By.xpath(MediaSettingsPropertyPage.mediasettings_categorieslinklocator.getProperty());	
	By logoutlocator = By.xpath(AvengerHomePropertyPage.homepg_logoutlocator.getProperty());    
	By AddaDevicebuttonlocator=By.xpath(AvengerDevicesPropertyPage.avengerdevicespage_AddaDevicebuttonlocator.getProperty());
	By fileUploadbuttonlocator=By.xpath(AvengerHomePropertyPage.homepg_uploadlocator.getProperty());
	By homepg_addFilelocator=By.xpath(AvengerHomePropertyPage.homepg_addFile.getProperty());
	By allaccountsbreadcrumb=By.xpath(AvengerHomePropertyPage.homepg_allaccountsbreadcrumb.getProperty());
	By invalidfileuploaderrorlocator=By.xpath(AvengerHomePropertyPage.homepg_fileuploaderrorlocator.getProperty());
	By upload=By.name("upload");
	By uploadPogress=By.xpath("//*[@role='progressbar']/span");
	By progresserror=By.xpath("");
	By notificationbuttonlocator=By.xpath(AvengerHomePropertyPage.homepg_notificationbuttonlocator.getProperty());
	By markallasreadlocator=By.xpath(AvengerHomePropertyPage.homepg_markallasreadlocator.getProperty());
	By dashboardlinklocator = By.xpath(AvengerHomePropertyPage.homepg_dashboardlink.getProperty());
	By usernameLocator = By.xpath(AvengerLoginPropertyPage.loginpg_usernameLocator.getProperty());
	By titlelocator = By.name(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_titlelocator.getProperty());
	By accountname = By.name(CreateChildAccountPropertyPage.createchildAccount_accountname.getProperty());
	By securitylink=By.xpath(AvengerSystemSettingsPropertyPage.avengersystemsettingspage_securitylinkLocator.getProperty());
	By markallasreadbuttonlocator=By.xpath("//*[@ng-click='markAllAsRead()']");
	By environmentlink = By.xpath(AvengerSystemSettingsPropertyPage.avengersystemsettingspage_environmentlinklocator.getProperty());
	By apikeyslink=By.xpath(AvengerSystemSettingsPropertyPage.avengersystemsettingspage_apikeyslinkLocator.getProperty());
	By roleslink =By.xpath(AvengerHomePropertyPage.homepg_roleslink.getProperty());	
	By canceluploadyesbuttonlocator=By.xpath("//div[@class='cancel-upload']/h4/button[2]");
	By canceluploadnobuttonlocator=By.xpath("//div[@class='cancel-upload']/h4/button[1]");
	By deviceslinklocator=By.xpath(AvengerHomePropertyPage.homepg_deviceslinklocator.getProperty());
	By uploadtablink=By.xpath(AvengerUploadPropertyPage.uploadpg_Uploadtab.getProperty());
	By alldeviceslink = By.xpath(AvengerHomePropertyPage.homepg_alldeviceslink.getProperty());
	By uploadingfileaddbuttonlocator=By.xpath("//*[@class='util-uppercase upload-text']/../..//button[contains(@class,'upload')]");
	By dismissuploads=By.xpath("//*[@ng-click='dismissAllUploads()']");
	By settingslink=By.xpath("//*[contains(@class,'cogwheel')]/..");
	By postsomething=By.xpath("//*[contains(@aria-label,'What are you working on?')]");
	By myProfilePagelink=By.xpath("//*[contains(@class,'list-group')]/..//*[contains(@ui-sref,'portal.userProfile')]");
	By rootaccountxpathlocator=By.xpath("//*[@ng-show='account.parentAccountId']/..");
	By Editsettingsbuttonlocator=By.xpath("//*[@class='upload-file']/h4/a");
	By dashboardpagelocator=By.xpath(AvengerFeaturesPropertyPage.avengerfeaturespage_dashboardlink.getProperty());
	By mediatablink = By.xpath(AvengerDashboardPropertyPage.Avengerdashboardpage_mediatablocator.getProperty());
	By uploadVideoByUrllocator = By.xpath(AvengerHomePropertyPage.homepg_addVideoByUrllink.getProperty());
	By addUrlTextlocator = By.xpath(AvengerHomePropertyPage.homepg_addUrl.getProperty());
	By addUrlButtonlocator = By.xpath(AvengerHomePropertyPage.homepg_addUrlbutton.getProperty());
	By addEncodingTypelocator = By.xpath(AvengerHomePropertyPage.homepg_addEncodingType.getProperty());
	By webAddressLabellocator =By.xpath(AvengerHomePropertyPage.homepg_webAddresslabel.getProperty());
	By videoTypelabellocator = By.xpath(AvengerHomePropertyPage.homepg_videoTypelabel.getProperty());
	By encodingTypelabellocator = By.xpath(AvengerHomePropertyPage.homepg_encodingTypelabel.getProperty());
	By homebuttonlocator = By.xpath(AvengerHomePropertyPage.homepg_homebuttonlocator.getProperty());
	By videotitlelocator = By.xpath(AvengerHomePropertyPage.homepg_videotitlelocatoroncarosel.getProperty());
	By presentationprofiletab = By.xpath(AvengerHomePropertyPage.homepg_Presentationprofiletablocator.getProperty());
	By AddaPresentationprofilelocator = By.xpath(AvengerPresentationProfilesPropertyPage.avengerpresentationprofilespage_AddaPresentationprofilelocator.getProperty());
	By zoneslocator = By.xpath(AvengerHomePropertyPage.homepg_zoneslocator.getProperty());
	By addzonebuttonlocator = By.xpath(AvengerZonesPropertyPage.avengerzonespage_addzonebuttonlocator.getProperty());
	By accountstablocator = By.xpath(AvengerHomePropertyPage.homepg_accountstablocator.getProperty());
	By reportsLinklocator=By.xpath(AvengerHomePropertyPage.homepg_reportslinklocator.getProperty());
	By librarieslinklocator = By.xpath(MediaSettingsPropertyPage.mediasettings_librarieslinklocator.getProperty());
	By importfileslocator = By.xpath(AvengerHomePropertyPage.homepg_importfileslocator.getProperty());
	By mediasearchtextboxlocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_mediasearchtextboxlocator.getProperty());
	By mediasearchbuttonlocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_mediasearchbuttonlocator.getProperty());
	By webexbuttonlocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_webexbuttonlocator.getProperty());
	By webexusernametextbox=By.xpath(AvengerMediaPropertyPage.avengermediapage_webexloginusernametextbox.getProperty());
	By webexpasswordtextbox=By.xpath(AvengerMediaPropertyPage.avengermediapage_webexloginpasswordtextbox.getProperty());
	By webexloginsubmitbutton=By.xpath(AvengerMediaPropertyPage.avengermediapage_webexloginsubmitbutton.getProperty());
	By dmenetworkstatisticslocator=By.xpath(AvengerHomePropertyPage.homepg_dmenetworkstatisticslocator.getProperty());
	By adddmebuttonlocator=By.xpath(AvengerDMENetworkStatisticsPropertyPage.dmenetworkstatisticspage_adddmebuttonlocator.getProperty());
	By settingslinklocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_settingslinklocator.getProperty());
	By multinodearuntimelocator = By.xpath("(//*[contains(@ng-show,'runtime.runTime.isOnline')]/button)[1]");
	By controlpanellocator =By.xpath(AvengerHomePropertyPage.controlpanellocator.getProperty());
	By revcreatebutton=By.cssSelector(AvengerMediaPropertyPage.avengermediapage_revcreatebutton.getProperty());
	By revcreatetext=By.xpath(AvengerMediaPropertyPage.avengermediapage_revcreatetext.getProperty());
	By unreadnotificationslocator=By.xpath(AvengerHomePropertyPage.homepg_unreadnotificationslocator.getProperty());
	By seeallnotificationbuttonlocator=By.xpath(AvengerHomePropertyPage.homepg_seeallnotificationbuttonlocator.getProperty());
    By allnotificationslocator=By.xpath(AvengerHomePropertyPage.homepg_allnotificationslocator.getProperty());
	By controlpaneldropdownlocator =By.xpath(AvengerHomePropertyPage.controlpaneldropdownlocator.getProperty());
	By homepg_carosel =By.xpath(AvengerHomePropertyPage.homepg_carosel.getProperty());
	By homepg_videosinCategory =By.xpath(AvengerHomePropertyPage.homepg_videosincategory.getProperty());
	By useragreement_agreebuttonlocator=By.xpath(AvengerHomePropertyPage.homepg_useragreementagreebuttonlocator.getProperty());
	By useragreement_declinebuttonlocator=By.xpath(AvengerHomePropertyPage.homepg_useragreementdeclinebuttonlocator.getProperty());
    By helplinklocator=By.xpath(AvengerHomePropertyPage.homepg_helplinklocator.getProperty());
    By dmemanagementlocator=By.xpath(AvengerHomePropertyPage.homepg_dmemanagementlocator.getProperty());
    By Myprofilelinklocator=By.xpath("//*[contains(text(),' My Profile ')]");
    By userprofiledropdownlocator=By.xpath("//vb-profile-picture/..");//("//*[contains(@class,'dropdown-toggle main-nav-btn btn')]");
    
    
	public AvengerDashboardPage avengerDashboardPage()
	{
		customReport.reporter("Avenger Dashboard Page is Clicked","");
		waitForElementPresent(logoutlocator);
		return basePage.avengerDashboardPage(driver,  customReport, basePage);
	}

   public String verify_userName(String label)
   {
	   By usernamelocator=By.xpath("//span[contains(text(),'"+label+"')]");
       return getText(usernamelocator);
   }

	public AvengerLoginPage click_logout()
	{
		clickUsingSwitch(userprofiledropdownlocator);
		customReport.reporter("Clicked on User Log Out Button","");
		By logoutbuttonlocator=By.xpath("//*[@class='glyphicons power theme-header-txt']");
		waitForElementPresent(logoutbuttonlocator);
		clickUsingSwitch(logoutbuttonlocator);
		customReport.reporter("Clicked on Log Out Button","");
		waitForElementPresent(usernameLocator);
		return basePage.avengerLoginPage(driver,  customReport, basePage);
	}



	/**
	 * AvengerHomePage method is used to instantiate the parameters with current object.
	 * @param driver
	 * @param driverWait
	 * @param customReport
	 */

	public AvengerHomePage(WebDriver driver,CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		this.customReport= customReport;
		this.basePage = basePage;
	}

	static int i=1;
	public String uploadingFile(FileUploadBeanPage fileuploadbeanpage) 
	{
		String fileName = null;

		if(driver.toString().contains("safari"))
		{
			fileName="SafariVideo"+i;
			logger.info("@@@File name"+fileName);
			fileuploadbeanpage.setFilename(fileName);
			enterText(mediasearchtextboxlocator,fileName);
			clickEnter(mediasearchbuttonlocator);
			By SafariVideo=By.xpath("//div/a[contains(text(),'"+fileName+"')]");
			click(SafariVideo);
			i=i+1;
		}
		else{
			fileuploadbeanpage.setFilename(FileOperation.getFileName(fileuploadbeanpage.getFilesourcepath()));
			logger.info("File Name Uploaded is"+fileuploadbeanpage.getFilename());
			click(uploadingfileaddbuttonlocator);
			if(isDisplayedWithoutException(dismissuploads)){
				click(dismissuploads);
			}
			pause(3000);
			enterText_FileUpload(upload, fileuploadbeanpage.getFilesourcepath());
			logger.info("File Uploaded Name is"+fileuploadbeanpage.getFilename());
			FilenameUtils.removeExtension(fileuploadbeanpage.getFilename());
			logger.info("After Removing extension in File"+FilenameUtils.removeExtension(fileuploadbeanpage.getFilename()));
			fileuploadbeanpage.setFilename(FilenameUtils.removeExtension(fileuploadbeanpage.getFilename()));
			By fileuploadname=By.xpath("//*[@class='upload-file']/h4/a");
			logger.info("File Upload Name is"+getText(fileuploadname));
			By videouploadedlocator = By.xpath("//span[@class='glyphicons circle_ok']");
			waitForElementPresent(videouploadedlocator);
			fileName= getText(fileuploadname);
			customReport.reporter("File Uploaded Successfully",fileName);
		}
		return fileName;
	}


	public AvengerEditVideoSettingsPage click_EditSettings(FileUploadBeanPage fileuploadbeanpage)
	{
		By videocompletedlocator = By.xpath("//span[contains(@class,'glyphicons circle_ok')]");
		waitForElementPresent(videocompletedlocator);
		logger.info("Click on Edit Settings");
		click(Editsettingsbuttonlocator);
		customReport.reporter("Edit Settings Link is Clicked","");
		waitForElementPresent(titlelocator);
	   customReport.reporter("Edit Video Settings Page is Displayed","");
		return basePage.avengerEditVideoSettingsPage(driver,  customReport, basePage);
	}


	public ArrayList<String> uploadingmultipleFile(FileUploadBeanPage fileuploadbeanpage,String... args) 
	{
		By uploadfileName;
		ArrayList<String> getText = null;
		ArrayList <String> filepath= new ArrayList<String>();
		for(String arg: args) {
			filepath.add(arg);
		}

		ArrayList<By>fileuploadname =new ArrayList<By>();
		String wmvvideosPath=filepath.get(0);
		String wmvvideosPath1=filepath.get(1);
		logger.info("WMV Videos Path is"+filepath.get(0));
		logger.info("WMV Videos Path1 is"+filepath.get(1));
		String value="",value2=""; 
		for(int i=0;i<filepath.size();i++)
		{
			fileuploadbeanpage.setSfilename(filepath);
			logger.info("File Name Uploaded is"+fileuploadbeanpage.getSfilename().get(i));
			click(fileUploadbuttonlocator);
			enterText_FileUpload(upload, wmvvideosPath);
			pause(5000);
			click(fileUploadbuttonlocator);

		}
		while((!value.equalsIgnoreCase("100% Complete")))
		{
			while((!value2.equalsIgnoreCase("100% Complete"))){
				value2=driver.findElements(uploadPogress).get(2).getAttribute("textContent");
			}
			value=driver.findElements(uploadPogress).get(0).getAttribute("textContent");
		}
		click(fileUploadbuttonlocator);
		for(int j=0;j<filepath.size();j++){
			uploadfileName=By.xpath("//*[contains(text(),'"+FileOperation.getFileName(fileuploadbeanpage.getSfilename().get(j))+"')]");
			getText= new ArrayList<String>();
			getText.add(getText(uploadfileName));
		}
		return getText;

	}

	public String get_UploadfileButton()
	{
		return getText(fileUploadbuttonlocator);

	}

	public String cancelFileUpload(String wmvvideosPath,FileUploadBeanPage fileuploadbeanpage)
	{
		fileuploadbeanpage.setFilename(FileOperation.getFileName(wmvvideosPath));
		By canceluploadbuttonlocator=By.xpath("//*[@ng-click='cancelUpload(video)']");
		logger.info("file upload bean page file name is"+fileuploadbeanpage.getFilename());
		By fileuploadname=By.xpath("//*[contains(text(),'"+fileuploadbeanpage.getFilename()+"')]");
		logger.info("File Name Uploaded is"+fileuploadbeanpage.getFilename());
		click(fileUploadbuttonlocator);
		click(dismissuploads);
		logger.info("Clicked on dismiss Uploads");
		logger.info("file source path is"+fileuploadbeanpage.getFilesourcepath());
		enterText_FileUpload(upload, fileuploadbeanpage.getFilesourcepath());
		click(canceluploadbuttonlocator);
		return String.valueOf(elements(fileuploadname));
	}


	public String get_invalidFileUploadErrorText(String wmvvideosPath)
	{
		logger.info("String video upload path"+wmvvideosPath);
		click(fileUploadbuttonlocator);
		enterText_FileUpload(upload, wmvvideosPath);
		logger.info("Error Text for Invalid File Upload is"+getText(invalidfileuploaderrorlocator));
		return getText(invalidfileuploaderrorlocator);
	}





	/**
	 *   gridButtonPresent is used to check whether gridButtonPresent on the screen it returns a boolean value
	 * @return : Checks whether the Grid button is Visible on the AvengerHomePage. 
	 */
	public boolean addVideoButtonPresent() {
		return waitForElementPresent(addVideoLocator);
	}

	/**
	 * Will open a new Tab consisting of the Number of Options.
	 * @return : Home page Object
	 * @throws InterruptedException 
	 */
	/*public AvengerHomePage clickMore() {
		if (waitForElementPresent(addVideoLocator, 
				getpageTitle())) {

			click(addMoreLink);
			logger.info("More Link is clicked");
			customReport.reporter("Link is Clicked","");
		}
		return basePage.avengerHomePage(driver,  customReport, basePage);
	}

	*//**
	 * This will click the Link for the Accounts.
	 * @return : the object of Manage Account page.
	 * @throws InterruptedException
	 *//*
	public AvengerManageAccountPage clickAccounts()  {
		click(clickAccountsLocator); 
		logger.info("Account Link is Clicked");
		if(waitForElementPresent(addAccountLocator))
		{
			logger.info("The Add Account Button is Visible");
			customReport.reporter("The AddAccount button is visible after the link was clicked",""); 
		}

		return basePage.avengerManageAccountPage(driver,  customReport, basePage);
	}
*/
	/**
	 * 
	 */

	public String clickSettingsLink()
	{
		  String sflag="false";
		   clickUsingSwitch(settingslink);
		   logger.info("Setting link clicked");
	//Clicking on setting will direct to dashboard page or reports page based on user action corrective measures are taken to handle in below try catch code
		   try{
		   waitForElementPresent(userslink);
		   }
		   catch(ReasonsTimeOutException e)
		   {
			   Reporter.log("@@@@@Settings link clicked but not there in dashboad page");
			   logger.error("The settings link is clicked as per in reason time out exception");
			   clickUsingSwitch(settingslink); 
		   }
		   return sflag;
		 
	}

	public boolean verifyAdminLink()
	{
		return locatorsVisibilityAsPerRoles(settingslink);
	}

	public boolean verifyAccountsTab()
	{
		return locatorsVisibilityAsPerRoles(accountstablocator);
	}

	
	public boolean checkLogoutButton()
	{
		Boolean sflag=false;
		if( waitForElementPresent(logoutlocator))
		{	
			sflag=true;
			customReport.reporter("Logout button is  found on the page", "");
		}
		else
			sflag=false;

		return sflag;
	}

	public void clickUsersDropdown()
	{
		click(userslink);
		logger.info("The User Drop Down is Clicked");
	}
	
	public AvengerUserDashboardPage clickUsersLink() {
		// TODO Auto-generated method stub
		clickUsingSwitch(userslink);
		pause(5000);
		click(usersdropdown);
		waitForElementPresent(adduserlink);
		customReport.reporter("Users  link is clicked","");
		return basePage.avengerUserDashBoardPage(driver,  customReport, basePage);
	}

	public AvengerUserDashboardPage clickUsers() {
		// TODO Auto-generated method stub
		click(usersdropdown);
		waitForElementPresent(adduserlink);
		customReport.reporter("Users  link is clicked","");
		return basePage.avengerUserDashBoardPage(driver,  customReport, basePage);
	}
	
	
	
	public void clickLoginLink()
	{
		logger.info("Login link is clicked");
		click(loginlink);
		customReport.reporter("Login link is clicked","");

	}

	public AvengerGroupsPage clickGroupsLink() {
		// TODO Auto-generated method stub
		waitForElementPresent(userslink);
		clickUsingSwitch(userslink);
		logger.info("Users  link is clicked");
		click(groupslink);
		customReport.reporter("Groups  link is clicked","");
		waitForElementPresent(creategroupinputlocator);
		return basePage.avengerGroupsPage(driver,  customReport, basePage);
	}
	
	 
	
	public AvengerGroupsPage clickmediasettingsLink() {
		// TODO Auto-generated method stub
		waitForElementPresent(mediasettings);
		clickUsingSwitch(mediasettings);
		logger.info("mediasettings  link is clicked");
		click(categorieslinklocator);
		customReport.reporter("categorieslinklocator  link is clicked","");
		//waitForElementPresent(creategroupinputlocator);
		return basePage.avengerGroupsPage(driver,  customReport, basePage);
	}
	
	public AvengerGroupsPage deleteAllGroups() throws InterruptedException {
		
		logger.info("Deleting All Groups");		
		By alldeletegroup= By.xpath("//*[contains(@class,'btn btn-sm btn-admin btn-white btn-icon-left')]");
		 JavascriptExecutor jse1 = (JavascriptExecutor) driver;
			int j;
			
			for(j=0;j<=40;j++) {
			jse1.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			Thread.sleep(5000);
			}
			int a=elements(alldeletegroup);
			
		for(int i=0;i<a;i++)
		{ 
		 
			click(alldeletegroup);
			pause(3000);
			click(confirmdeletegroup);
			pause(3000);
		}

		customReport.reporter("Groups  link is clicked","");
		return basePage.avengerGroupsPage(driver,  customReport, basePage);
	}
 
 public AvengerGroupsPage deleteAllCategories() {
		
		logger.info("Deleting All Categories");
		int a=elements(deletecategories);
		for(int i=0;i<a;i++)
		{
			click(deletecategories);
			pause(3000);
			click(confirmdeletecategory);
			pause(3000);
		}
		
		customReport.reporter("Groups  link is clicked","");
		return basePage.avengerGroupsPage(driver,  customReport, basePage);
	}

public AvengerGroupsPage deleteAllPresentationProfiles() {
	
	logger.info("Deleting All PresentationProfiles");
	int a=elements(deletePresentationProfiles);
	for(int i=0;i<a;i++)
	{
		click(deletePresentationProfiles);
		pause(3000);
		click(confirmdeletepresentationprofile);
		pause(3000);
	}
	customReport.reporter("Groups  link is clicked","");
	return basePage.avengerGroupsPage(driver,  customReport, basePage);
}

public AvengerGroupsPage deleteAllDevices() {
	
	logger.info("Deleting All Devices");
	int a=elements(deleteDevices);
	for(int i=0;i<a;i++)
	{
		click(deleteDevices);
		pause(3000);	
		click(clickdeleteDevice);
		pause(3000);
		click(confirmdeletedevice);
		pause(3000);
	}
	return basePage.avengerGroupsPage(driver,  customReport, basePage);
}
	
	public AvengerGroupsPage clickChannelsLink() {
		// TODO Auto-generated method stub
		waitForElementPresent(userslink);
		clickUsingSwitch(userslink);
		logger.info("Users  link is clicked");
		click(channelslink);
		customReport.reporter("Channels  link is clicked","");
		return basePage.avengerGroupsPage(driver,  customReport, basePage);
	}
	
	public AvengerGroupsPage clickCategoriesLink() {
		// TODO Auto-generated method stub
		waitForElementPresent(userslink);
		clickUsingSwitch(mediasettings);
		logger.info("mediasettings  link is clicked");
		click(categorieslinklocator);
		customReport.reporter("categories  link is clicked","");
		return basePage.avengerGroupsPage(driver,  customReport, basePage);
	}
	
	public AvengerGroupsPage deleteAllChannels() {
		
		logger.info("Deleting All Groups");
		int a=elements(deletechannel);
		for(int i=0;i<a;i++)
		{
			click(deletechannel);
			pause(3000);
			click(confirmdeletechannel);
			pause(3000);
		}
		
		customReport.reporter("Groups  link is clicked","");
		return basePage.avengerGroupsPage(driver,  customReport, basePage);
	}

	

	public boolean checkGroupsPageOpens()
	{
		waitForElementPresent(creategroupinputlocator);
		customReport.reporter(" Groups Page Opened Successfully.","");
		return waitForElementPresent(creategroupinputlocator);
	}


	

	public AvengerEditVideoSettingsPage click_EditVideoSettings(FileUploadBeanPage fileuploadbeanpage)
	{
		fileuploadbeanpage.setFilename(FileOperation.getFileName(fileuploadbeanpage.getFilesourcepath()));
		FilenameUtils.removeExtension(fileuploadbeanpage.getFilename());
		By editvideosettinglocator=By.xpath("//*[contains(text(),'"+FilenameUtils.removeExtension(fileuploadbeanpage.getFilename())+"')]/../a");
		pause(3000);
		click(editvideosettinglocator);
		customReport.reporter("Edit Video Settings is Clicked","");
		waitForElementPresent(titlelocator);
		customReport.reporter("Edit Setting page is displayed", "");
		return basePage.avengerEditVideoSettingsPage(driver,  customReport, basePage);

	}

	public String verify_RootAccount()
	{
		return  getText(rootaccountxpathlocator);
	}

	public AvengerVideoNotificationPage click_notificationButton()
	{
		if(elements(notificationbuttonlocator)==1)
		{
			click(notificationbuttonlocator);
			customReport.reporter("Clicked on Notification Menu Button","");
		}
		else
		{
			logger.error("No notification available to click");
		}
		return basePage.avengerVideoNotificationPage(driver,  customReport, basePage);

	}
	
	public AvengerVideoNotificationPage click_notificationbutton()
	{
			click(notificationbuttonlocator);
			customReport.reporter("Clicked on Notification Menu Button","");
			
		return basePage.avengerVideoNotificationPage(driver,  customReport, basePage);

	}
	public String get_unreadnotificationcount()
	{
		By videostatusxpath=By.xpath("//*[contains(text(),'No Unread Notifications')]");
		return getText(videostatusxpath);
	}
	
	public AvengerVideoNotificationPage click_markallasread()
	{
			click(markallasreadlocator);
			customReport.reporter("Read all the notifications","");
			
		return basePage.avengerVideoNotificationPage(driver,  customReport, basePage);

	}

	public boolean verify_logoutButton()
	{
		return waitForElementPresent(logoutlocator);
	}

	public String get_NotificationCount()
	{
		String notificationcount="";
		if(isDisplayed(notificationbuttonlocator)==false)
		{
			logger.info("Notification count is 0");
			notificationcount="0";
		}
		else
		{
			notificationcount=getText(notificationbuttonlocator);
			logger.info("Notification value is"+getText(notificationbuttonlocator));
		}
		logger.info("Notification Count value is"+notificationcount);
		return notificationcount;
	}

	public int check_AdminLinkVisibility()
	{
		return elements(adminlink);

	}

	public AvengerRolesPage click_Roles()
	{
		clickUsingSwitch(userslink);
		clickUsingSwitch(roleslink);
		return basePage.avengerRolesPage(driver,  customReport, basePage);

	}

	public boolean verify_RolesLink()
	{
		click(userslink);
		return locatorsVisibilityAsPerRoles(roleslink);
	}
	
	
	public AvengerDashboardPage click_avengerDashboardPage()
	{
		waitForElementPresent(dashboardpagelocator);
		click(dashboardpagelocator);
		customReport.reporter("Avenger Dashboard Page is Clicked","");
		waitForElementPresent(mediatablink);
		return basePage.avengerDashboardPage(driver,  customReport, basePage);
	}

	public boolean verifyUsersLink()
	{
		return locatorsVisibilityAsPerRoles(userslink);
	}
	public boolean verifyUserDropDownRoles()
	{
		return locatorsVisibilityAsPerRoles(usersdropdown);
	}
	
	public boolean verify_GroupsLink()
	{
		return locatorsVisibilityAsPerRoles(groupslink);
	}
	public boolean verify_SettingsLink()
	{
		return locatorsVisibilityAsPerRoles(systemsettingslink);
	}
	public boolean verify_DevicesLink()
	{
		return locatorsVisibilityAsPerRoles(deviceslinklocator);
	}
	public boolean verify_uploadingFile()
	{
		return locatorsVisibilityAsPerRoles(uploadingfileaddbuttonlocator);
	}

	public boolean verify_ReportsLink()
	{
		return locatorsVisibilityAsPerRoles(reportsLinklocator);
	}
	
	public AvengerReportsPage click_ReportsLink()
	{
    	click(reportsLinklocator);
    	waitForElementPresent(videoanalyticsCSVbutton);
    	logger.info("Videos text is visible on the page");
		return basePage.avengerReportsPage(driver,  customReport, basePage);
  	}
	
	  public boolean verify_LibrariesLinkLocator()
      {
	   return locatorsVisibilityAsPerRoles(librarieslinklocator);
      }
	
	
	public boolean verify_IsCaroselPresent()
	{
		By corasallocator = By.xpath(AvengerHomePropertyPage.homepg_corasalloctor.getProperty());
		boolean caroselState=waitForElementPresent(corasallocator);
		customReport.reporter("Carosel Present in Screen ::"+caroselState,"");
		return caroselState;
	}


	public void click_CaroselRightSlider(String noOfVideos)
	{
		By rightnavigationlocator = By.xpath("//a[2][contains(@class,'carousel-control')][not(contains(@class,'ng-hide'))]/span");

		waitForElementPresent(rightnavigationlocator);
		int number = Integer.parseInt(noOfVideos);
		logger.info("The value for the Number is"+number);

		for(int i=0;i<=number-1;i++)
		{
			pause(2000);
			logger.info("loop values are"+i);
			click(rightnavigationlocator);
		}
	}

	public String checkTitleText_OnCaroseal(String titleName)
	{
		By carosealtextlocator = By.xpath("//*[@id='feature-carousel']//*[contains(text(),'"+titleName+"')]");
		return getText(carosealtextlocator);
	}

	public void click_LeftNavigation_OnCarosel(String noOfVideos)
	{
		By leftnavigationlocator = By.xpath("//a[@class='left carousel-control animate-if']/a[1]/span");

		int number = Integer.parseInt(noOfVideos);
		logger.info("The value for the Number is"+number);

		for(int i=0;i<number;i++)
		{
			pause(2000);
			logger.info("loop values are"+i);
			click(leftnavigationlocator);
		}
	}


	public AvengerVideoCommentsPage clickTitle_OnCarosel()
	{
		By playbuttonlocator = By.xpath("//span[@class='glyphicons play']");
		click(videotitlelocator	);
		logger.info("The Play Button is Present"+waitForElementPresent(playbuttonlocator));
		waitForElementPresent(playbuttonlocator);
		return basePage.avengerVideoCommentsPage(driver,  customReport, basePage);
	}

	public void playVideo_OnCaroseal(String videotitle)
  	{
  		By videoplaybutton=By.xpath("//a[contains(text(),'"+videotitle+"')]/../../..//div[@class='circle']/span");
  		waitForElementPresent(videoplaybutton);
  		customReport.reporter("Video play button is visible on the page", "");
  		click(videoplaybutton);
  		customReport.reporter("Video Play Button Clicked on Caroseal", "");
  		logger.info("The Play Button is Clicked");
  	}

	public boolean verify_pauseButton_OnCaroseal()
	{
		By videopplayerpausebuttonlocator = By.xpath("//span[@class='glyphicons pause']");
		return isDisplayed(videopplayerpausebuttonlocator);
	}

	public void moveSlider_Right()
	{
		By sliderlocator = By.xpath("(//*[contains(@class,'glyphicon-chevron-right')])[2]");
		click(sliderlocator);
		logger.info("The Slider is Clicked Right");
		pause(5000);
	}

	public void moveSlider_Left()
	{
		By sliderlocator = By.xpath("(//*[contains(@class,'glyphicon-chevron-left')])[2]");
		click(sliderlocator);
		logger.info("The Slider is Clicked Left");
		pause(5000);

	}
	
	public String getRecentlyAddedVideoTitle()
	{
		By recentvideotitlelocator = By.xpath(AvengerHomePropertyPage.homepg_recentvideotitlelocator.getProperty());

		List<WebElement> allAddedVideosText = getAllWebElementValues(recentvideotitlelocator);
		logger.info("The value for the "+allAddedVideosText.size());
		String title = allAddedVideosText.get(0).findElement(recentvideotitlelocator).getText();
		logger.info("The value for the Title is"+title);
		return title;
	}

	public AvengerVideoCommentsPage click_RecentlyVideoTitle()
	{
		By recentvideotitlelocator = By.xpath(AvengerHomePropertyPage.homepg_recentvideotitlelocator.getProperty());
		By videoplaybuttonlocator  = By.xpath(AvengerHomePropertyPage.videocommentpg_playbuttonlocator.getProperty());

		List<WebElement> allAddedVideosText = getAllWebElementValues(recentvideotitlelocator);
		logger.info("The value for the "+allAddedVideosText.size());
		allAddedVideosText.get(0).findElement(recentvideotitlelocator).click();
		pause(7000);
		logger.info("The title is Clicked");
		waitForElementPresent(videoplaybuttonlocator);
		return basePage.avengerVideoCommentsPage(driver,  customReport, basePage);
	}

	public String getRecentlyAddedVideoDatTime(int index)
	{
		By recentvideodatetimelocator = By.xpath(AvengerHomePropertyPage.homepg_recentvideodatetimelocator.getProperty());
		List<WebElement> allAddedVideosDateTime = getAllWebElementValues(recentvideodatetimelocator);
		logger.info("Size is"+allAddedVideosDateTime.size());
		return getTextMultiple(recentvideodatetimelocator,  index);
	}

	public boolean verifyAttribute(int index)
	{
		By recentvideodatetimelocator = By.xpath(AvengerHomePropertyPage.homepg_recentvideodatetimelocator.getProperty());
		List<WebElement> allAddedVideosDateTime = getAllWebElementValues(recentvideodatetimelocator);
		logger.info("Size is"+allAddedVideosDateTime.size());
		return isDisplayed(allAddedVideosDateTime.get(index));
	}
	
	public void addVideoByUrl(FileUploadBeanPage fileUploadBeanPage)
  	{
		By clearvideolinks = By.xpath(AvengerHomePropertyPage.homepg_dismissallvideolinks.getProperty());
  		click(fileUploadbuttonlocator);
  		customReport.reporter("The Add Video Button is Clicked","");
  		click(uploadVideoByUrllocator);
  		customReport.reporter("The Add Urls Button is Clicked","");
  		if(isDisplayedWithoutException(clearvideolinks)){
  			click(clearvideolinks);
  		}
  		enterText(addUrlTextlocator, fileUploadBeanPage.getVideoUrl());
  		customReport.reporter("The Url is added to the Url-Text",fileUploadBeanPage.getVideoUrl());
  		selectValuefromDropDown(addEncodingTypelocator, fileUploadBeanPage.getEncodingType());
  		customReport.reporter("The Encoding Type is Selected from the dropdown",fileUploadBeanPage.getEncodingType());
  		By videotype=By.xpath("//*[contains(text(),'"+fileUploadBeanPage.getVideoType()+"')]");
  		click(videotype);
  		submit(addUrlButtonlocator);
  		customReport.reporter("The Add Button is Clicked","");
  	}
	
	public String getVideoUploadUrl(String uploadedVideoUrlName)
  	{
  		By videouploadedlocator = By.xpath("//*[contains(text(),'"+uploadedVideoUrlName+"')]/../a");
  		return getText(videouploadedlocator);
  	}
	
	public AvengerEditVideoSettingsPage clickMediaUrl(String urlName)
  	{
  		By videouploadedlocator = By.xpath("//*[contains(text(),'"+urlName+"')]/../a");
  		By saveandexitbuttonlocator=By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_saveandexitbuttonlocator.getProperty());
  		waitForElement(videouploadedlocator);
  		click(videouploadedlocator);
  		customReport.reporter("Clicked on video url uploaded","");
  		waitForElement(saveandexitbuttonlocator);
  		return basePage.avengerEditVideoSettingsPage(driver,  customReport, basePage);
  	}
	
	public Map<String,String> verify_AllElements_ForAddVideoUsingUrl()
  	{
  		click(fileUploadbuttonlocator);
  		customReport.reporter("The Add Video Button is Clicked","");
  		click(uploadVideoByUrllocator);
  		customReport.reporter("The Add Urls Button is Clicked","");
  		Map<String,String> allElements = new HashMap<String, String>();
  		allElements.put("webAddress",getText(webAddressLabellocator ));
  		allElements.put("videoType",getText(videoTypelabellocator));
  		allElements.put("encodingType",getText(encodingTypelabellocator));
  		return allElements;
  	}

	public AvengerAccountsPage clickAccountsTab()
	{
		pause(7000);
		By accountstablocator = By.xpath(AvengerHomePropertyPage.homepg_accountstablocator.getProperty());
		waitForElementEnable(accountstablocator);
		clickUsingSwitch(accountstablocator);
		customReport.reporter("Accounts tab is clicked", "");
		return basePage.avengerAccountsPage(driver,  customReport, basePage);
	}
	
	public  boolean verifyMediaSettingsLink() {
		// TODO Auto-generated method stub
		return locatorsVisibilityAsPerRoles(mediasettings);
	}
	
	public AvengerSystemSettingsPage clickSystemSettingsLink() {
		
		pause(5000);
		click(systemsettingslink);
		logger.info("System Settings  link is clicked");
		return basePage.avengerSystemSettingsPage(driver,  customReport, basePage);
	}

	public  boolean verify_SystemSettingsLink() {
		// TODO Auto-generated method stub
		return locatorsVisibilityAsPerRoles(systemsettingslink);
	}
	
	public  AvengerMediaSettingsPage clickMediaSettingsLink() {
		
		pause(5000);
		clickUsingSwitch(mediasettings);
		customReport.reporter("Media Settings Link is clicked","");
		return basePage.avengerMediaSettingsPage(driver,  customReport, basePage);
	}

	
	public  AvengerDevicesPage clickAllDevicesLink() {
		
		pause(5000);
		click(deviceslinklocator);
		customReport.reporter("Devices Link is clicked","");
		pause(2000);
		click(alldeviceslink);
		customReport.reporter("All Devices is clicked", "");
		waitForElementPresent(AddaDevicebuttonlocator);
		customReport.reporter("Devices Page is Displayed Successfully","");
		return basePage.avengerDevicesPage(driver,  customReport, basePage);
	}
	
	public AvengerPresentationProfilesPage click_PresentationProfilesTab()
	{
		pause(3000);
		click(deviceslinklocator);
		customReport.reporter("Devices Link is clicked","");
		pause(2000);
		click(presentationprofiletab);
		customReport.reporter("Presentation Profile is clicked", "");
		waitForElementPresent(AddaPresentationprofilelocator);
		return basePage.avengerPresentationProfilesPage(driver,  customReport, basePage);
	}
	
public  AvengerDevicesPage click_Devicestab() {
		
		pause(5000);
		click(deviceslinklocator);
		customReport.reporter("Devices Link is clicked","");
		pause(2000);
		click(alldeviceslink);
		customReport.reporter("All Devices is clicked", "");
		waitForElementPresent(AddaDevicebuttonlocator);
		customReport.reporter("Devices Page is Displayed Successfully","");
		return basePage.avengerDevicesPage(driver,  customReport, basePage);
	}
   public void click_DevicesLinkTab()
   {
	   pause(3000);
		click(deviceslinklocator);
		customReport.reporter("Devices Link is clicked","");
   }
   public AvengerPresentationProfilesPage click_PresentationProfileTab()
   {
		pause(2000);
		click(presentationprofiletab);
		customReport.reporter("Presentation Profile is clicked", "");
		waitForElementPresent(AddaPresentationprofilelocator);
		return basePage.avengerPresentationProfilesPage(driver,  customReport, basePage);
   }

   public boolean verify_PresentationProfileTab()
   {
		return locatorsVisibilityAsPerRoles(presentationprofiletab);
   }
   
   public AvengerDevicesPage clickDmeManagement()
	 {
		 click(dmemanagementlocator);
		 return basePage.avengerDevicesPage(driver, customReport, basePage);
	 }
   
   public AvengerZonesPage clickZonesTab()
	{
		pause(3000);
		click(deviceslinklocator);
		customReport.reporter("Devices Link is clicked","");
		pause(2000);
		click(zoneslocator);
		customReport.reporter("Zones Profile is clicked", "");
		waitForElementPresent(addzonebuttonlocator);
		return basePage.avengerZonesPage(driver,  customReport, basePage);
	}
	
	public boolean verify_ZonesTab()
	{
		return locatorsVisibilityAsPerRoles(zoneslocator);
	}
	
	public void clickMediaSettingsTab()
	{
		pause(5000);
		click(mediasettings);
		customReport.reporter("Media Settings Link is clicked","");
		pause(2000);
	}
	
	
	public AvengerRecordingPage clickRecordingsTab()
	{
		
		By recordinglocator = By.xpath(AvengerHomePropertyPage.homepg_recordinglocator.getProperty());
		By primarydmelocator = By.xpath(AvengerRecordingPropertyPage.avengerrecordingpage_primarydmelocator.getProperty());
		click(recordinglocator);
		customReport.reporter("Recording tab is clicked", "");
		waitForElementPresent(primarydmelocator);
		return basePage.avengerRecordingPage(driver,  customReport, basePage);
	}
	
	public boolean verify_RecordingsTab()
	{
		By recordinglocator = By.xpath(AvengerHomePropertyPage.homepg_recordinglocator.getProperty());
		return locatorsVisibilityAsPerRoles(recordinglocator);
	}
	
	public String getCurrentUrl() {
		  
		  String value =getCurrentURL();
		      String[] data=value.split("#");
		      return data[0]; 
		  
		 }
	public String verify_Label(String labelname)
  	{
  		By labellocator = By.xpath("//*[contains(text(),'"+labelname+"')]");
  	  return  getText(labellocator);
  	}
	
	
	public String Verify_Frenchlabel(){
		
		By frenchlocator=By.xpath("//a[@ ui-sref='portal.scheduledEvents']/../../div[3]/a[3]");
		
		return getText(frenchlocator);
		
	}
	
	
	public void clickFileUploadButton() {
		waitForElementPresent(uploadingfileaddbuttonlocator);
		customReport.reporter("File upload button is visible on the page", "");
		click(uploadingfileaddbuttonlocator);
		waitForElement(uploadVideoByUrllocator);
	}
	
	public void click_SliderTVChannels(int i)
	{
		
		logger.info("In slider method");
		By rightsliderlocator=By.xpath("(//*[contains(@class,'glyphicon-chevron-right')])[3]");
		logger.info("The slider is displayed :::"+isDisplayed(rightsliderlocator));
		click(rightsliderlocator);
		logger.info("Slider Clicked for tv channels");
		}
	
	public void click_upcomingEventSlider(int i)
	{
		
		logger.info("In slider method");
		By rightsliderlocator=By.xpath("(//*[contains(@class,'glyphicon-chevron-right')])[4]");
		logger.info("The slider is displayed :::"+isDisplayed(rightsliderlocator));
		click(rightsliderlocator);
		logger.info("Slider Clicked for tv channels");
		}
	
	public List<String> getAllVideosInSlider(){
		By totalvideoslocator=By.xpath("(//*[@class='container-fluid carousel-rows'])[2]//h4/a");
		  List<WebElement> webelement = getAllWebElementValues(totalvideoslocator);
		  List<String> videosnameslist = new ArrayList<String>();
		  
		  for(WebElement ele:webelement)
		  {
		   logger.info("EVENt DATA:-" +ele.getText());
		   videosnameslist.add(ele.getText());
		  }
		  logger.info("The size is"+videosnameslist.size());
		  return videosnameslist;
		 }
	
	public boolean verify_AccountsTab()
	{
		return locatorsVisibilityAsPerRoles(accountstablocator);
	}
	
	public void click_deviceslink()
	{
		pause(3000);
		click(deviceslinklocator);
		customReport.reporter("Devices Link is clicked","");
	}
	public String getlabelstext(String text){
		By devicesvalueslocator=By.xpath("//div[@class='list-group']//a[contains(text(),'"+text+"')]");
		return getText(devicesvalueslocator);
	}
	
	public String getUploadDateTime(FileUploadBeanPage beanPage)
	{
		logger.info("File name is"+beanPage.getFilename());
		By uploaddatetime = By.xpath("//*[contains(text(),'"+beanPage.getFilename()+"')]/ancestor::*//p[contains(@ng-show,'status.complete')]");
		logger.info("Date and time is"+getText(uploaddatetime));
		return getText(uploaddatetime);
	}
	
	public AvengerEditVideoSettingsPage clickVideoUrl(FileUploadBeanPage beanPage)
	 {
	  By uploadedurl = By.xpath("//*[contains(text(),'"+beanPage.getVideoUrl()+"')]/../a[contains(@ui-sref,'video.settings')]");
	  click(uploadedurl);
	  return basePage.avengerEditVideoSettingsPage(driver,  customReport, basePage);
	 }
	
	public String stopFileUpload(FileUploadBeanPage fileuploadbeanpage) 
	{
		String fileName = null;
	if(driver.toString().contains("safari"))
	{

		fileuploadbeanpage.setFilename(FileOperation.getFileName(fileuploadbeanpage.getFilesourcepath()));
	
	}
	else{
		fileuploadbeanpage.setFilename(FileOperation.getFileName(fileuploadbeanpage.getFilesourcepath()));
		logger.info("File Name Uploaded is"+fileuploadbeanpage.getFilename());
		click(uploadingfileaddbuttonlocator);
		click(dismissuploads);
		enterText_FileUpload(upload, fileuploadbeanpage.getFilesourcepath());
		String value=driver.findElements(uploadPogress).get(0).getAttribute("textContent");
		logger.info("File Uploaded Name is"+fileuploadbeanpage.getFilename());
		FilenameUtils.removeExtension(fileuploadbeanpage.getFilename());
		logger.info("After Removing extension in File"+FilenameUtils.removeExtension(fileuploadbeanpage.getFilename()));
		fileuploadbeanpage.setFilename(FilenameUtils.removeExtension(fileuploadbeanpage.getFilename()));
		By fileuploadname=By.xpath("//*[@class='upload-file']/a");
		logger.info("File Upload Name is"+getText(fileuploadname));
     	fileName= getText(fileuploadname);
	}
	return fileName;
	}
	
	public void click_homeLink()
	{
		pause(5000);
		waitForElementEnable(homebuttonlocator);
		click(homebuttonlocator);
		customReport.reporter("VBrick Logo is clicked","");
	}
	
	
	public boolean verify_VideoOnCarosel(String svideotitle)
	{
		boolean videoCarosel=false;
  		By noofVideosinCarsel=By.xpath("//*[@id='feature-carousel']/ol/li");
  		int videosSize=elements(noofVideosinCarsel);
  		click(carsoealsliderlocator);
		for (int i=videosSize;i>=1;i--)
  		{
			By uploadvideotilecarsoeallocator=By.xpath("(//h1)["+i+"]");
			String carvideo=getAttribute(uploadvideotilecarsoeallocator);
			logger.info("Title---->"+svideotitle);
  			if(carvideo.contains(svideotitle))
  			{
  				videoCarosel=true;
  				logger.info("Element Visible");
  				customReport.reporter("Element Visible on carasel", "");
  				break;
  			}
  			else
  				click(carsoealsliderlocator);
  		}
		logger.info("The video is visible on carsoal"+videoCarosel);
		return videoCarosel;
	}
	
	public void click_fileUploadAddButton()
	{
		click(uploadingfileaddbuttonlocator);
	}
	
     public boolean verify_importFiles()
    {
	  return isDisplayed(importfileslocator);
     }
     
     public void click_importfiles(){
    	 click(importfileslocator);
    	 logger.info("Clicked on Import file button");
    	 waitForElement(webexbuttonlocator);
     }
     
     public void click_webexbutton(){
    	 click(webexbuttonlocator);
    	 logger.info("Clicked on WebEx button");
     }
     
     public AvengerWebexpage loginintowebex(AvengerFeaturesBeanPage avngerwebexbeanpage){
    	 enterText(webexusernametextbox, avngerwebexbeanpage.getWebexusername());
    	 logger.info("Entered value for webex username"+ avngerwebexbeanpage.getWebexusername());
    	 enterText(webexpasswordtextbox, avngerwebexbeanpage.getWebexpassword());
    	 logger.info("Entered value for webex password"+ avngerwebexbeanpage.getWebexpassword());
    	 click(webexloginsubmitbutton);
    	 logger.info("Clicked on WebEx loginbutton");
    	 return basePage.avengerwebexpage(driver, customReport, basePage);
     }
     
     public AvengerDMENetworkStatisticsPage clickDMENetworkStatistics(){
    	 pause(3000);
 		click(deviceslinklocator);
 		customReport.reporter("Devices Link is clicked","");
 		pause(2000);
 		click(dmenetworkstatisticslocator);
 		customReport.reporter("Dme Network Statistics Profile is clicked", "");
 		waitForElementPresent(adddmebuttonlocator);
 		return basePage.avengerdmenetworkstatisticspage(driver, customReport, basePage);
     }
     
     public void uploadfail(FileUploadBeanPage fileuploadbeanpage){
    	 fileuploadbeanpage.setFilename(FileOperation.getFileName(fileuploadbeanpage.getFilesourcepath()));
			logger.info("File Name Uploaded is"+fileuploadbeanpage.getFilename());
			click(uploadingfileaddbuttonlocator);
			if(isDisplayedWithoutException(dismissuploads)){
				click(dismissuploads);
			}
			
			enterText_FileUpload(upload, fileuploadbeanpage.getFilesourcepath());
			logger.info("File Uploaded Name is"+fileuploadbeanpage.getFilename());
			FilenameUtils.removeExtension(fileuploadbeanpage.getFilename());
			logger.info("After Removing extension in File"+FilenameUtils.removeExtension(fileuploadbeanpage.getFilename()));
			fileuploadbeanpage.setFilename(FilenameUtils.removeExtension(fileuploadbeanpage.getFilename()));
			By fileuploadname=By.xpath("//*[@class='upload-file']/h4/a");
			logger.info("File Upload Name is"+getText(fileuploadname));
		 }
     
     public AvengerVideoCommentsPage click_Faileduploadvideo(FileUploadBeanPage fileuploadbeanpage)
 	{
 		By videocompletedlocator = By.xpath("//*[@class='notification-tab']//*[@class='glyphicons circle_exclamation_mark']");
 		waitForElementPresent(videocompletedlocator);
 		click(videocompletedlocator);
 		logger.info("Click on Failed upload video title");
 		waitForElement(settingslinklocator);
 		return basePage.avengerVideoCommentsPage(driver, customReport, basePage);
 	}
     
    public AvengerControlPanelPage click_controlPanel()
    {
    	click(controlpanellocator);
    	logger.info("Clicked on Control Panel Tab");
    	waitForElementPresent(controlpaneldropdownlocator);
    	click(controlpaneldropdownlocator);
    	waitForElementPresent(multinodearuntimelocator);
      return basePage.avengercontrolPanelpage(driver, customReport, basePage);
    }
    
    public boolean verify_webexbuttondisplay(){
   	 
   	 return isDisplayedWithoutException(webexbuttonlocator);
    }
    
    public void click_clearcompletedtext(){
    	By clearcompletetext = By.xpath("(//*[contains(text(),'Clear completed')])[3]");
    	if(isDisplayedWithoutException(clearcompletetext)){
    	click(clearcompletetext);
    	}
    	else {
			logger.info("clear complete text is not visible");
		}
    	
    }
    
    public void click_Revcreationbutton(){
    	
    	click(revcreatebutton);
    	logger.info("Clicked on Rev Create Button");
    }
    
    public String verifyRevCreateText(){
    	return getText(revcreatetext);
    }
    
    public boolean verify_uploadurlbutton(){
    	 return isDisplayedWithoutException(uploadVideoByUrllocator);
    }
  
    
    
    public Map<String, Integer> get_usersDetail()
    	    {
    	        Map<String, Integer>usersDetail=new HashMap<String, Integer>();
    	        
    	        int licensedCount = TypeCasting.stringtoInteger(getText(licensed).split(": ")[1]);
    	        
    	        int unlicensedCount = TypeCasting.stringtoInteger(getText(unlicensed).split(": ")[1]);
    	        
    	        int totalCount = TypeCasting.stringtoInteger(getText(total).split(": ")[1]);
     	        
    	        int accountlicensedCount = TypeCasting.stringtoInteger(getText(accountlicensed).split(": ")[1]);
      	       
    	        int unusedlicensesCount = TypeCasting.stringtoInteger(getText(unusedlicenses).split(": ")[1]);
    	      	        
    	        usersDetail.put("Licensed",licensedCount);
    	        usersDetail.put("UnLicensed",unlicensedCount);
    	        usersDetail.put("Total",totalCount);
    	        usersDetail.put("Account Licensed",accountlicensedCount);
    	        usersDetail.put("Unused Licensed",unusedlicensesCount);
    	       
    	        return  usersDetail;      
    	    }
    
    public Map<String, String> get_usersdata()
    {
        Map<String, String>usersdata=new HashMap<String, String>();
        usersdata.put("Licensed",getText(licensed));
        usersdata.put("UnLicensed",getText(unlicensed));
        usersdata.put("Total",getText(total));
        usersdata.put("Account Licensed",getText(accountlicensed));
        usersdata.put("Unused Licensed",getText(unusedlicenses));
        return  usersdata;      
    }
    
    public ArrayList<String> get_carosel(){
        return getAllWebElementValueslist(homepg_carosel);
        }
    
    public ArrayList<String> get_videosinCategory(){
        return getAllWebElementValueslist(homepg_videosinCategory);
        }
    public void clickUserAgreeMent_AgreeButton() {

		click(useragreement_agreebuttonlocator);
	}
	public AvengerLoginPage clickUserAgreeMent_declineButton() {

		click(useragreement_declinebuttonlocator);
		return basePage.avengerLoginPage(driver, customReport, basePage);
	}
	
	public void click_helpLink()
	{
		pause(2000);
		click(helplinklocator);
		logger.info("Clicked on the help link locator");
	}
	
	public void clickQuestionmarklink(){
		By questionmarkHelp=By.xpath("//div[contains(@ng-show,'helpLinks.hasCustomLinks')]/button");
		click(questionmarkHelp);
		logger.info("Clicked on the help link locator");
	}
    	
	public void click_createdHelpLink(String linktext){
		By linktextLocator=By.xpath("//ul[contains(@class,'dropdown-menu')]//a[contains(text(),'"+linktext+"')]");
		click(linktextLocator);
	}
	
	public boolean verifyUserAgreeMent() {
		return isDisplayedWithoutException(useragreement_agreebuttonlocator);
	}
	
	public AvengerLibrariesPage click_LibrariesLinkLocator() {
			pause(2000);
	       clickUsingSwitch(userslink);
	       click(librarieslinklocator);
	        logger.info("The Libary page is loaded");
	        customReport.reporter("Clicked on Library link", "");
	        return basePage.avengerLibrariesPage(driver,  customReport, basePage);
	    }
	
	public ArrayList<String> get_UnreadNotifications() throws InterruptedException
	{

		ArrayList<String> unreadnotifications = new ArrayList<String>();
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		int j;
		
		for(j=0;j<=5;j++) {
		jse1.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(5000);
		}
		for (WebElement username : getAllWebElementValues(unreadnotificationslocator)) 
		{
			logger.info("User  Value is"+username.getText());
			unreadnotifications.add(username.getText());	
		}
		return unreadnotifications;
	}
	
	public ArrayList<String> get_AllNotifications() throws InterruptedException
	{

		click(seeallnotificationbuttonlocator);
		ArrayList<String> allnotifications = new ArrayList<String>();
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		int j;
		
		for(j=0;j<=5;j++) {
		jse1.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(5000);
		}
		for (WebElement username : getAllWebElementValues(allnotificationslocator)) 
		{
			logger.info("User  Value is"+username.getText());
			allnotifications.add(username.getText());	
		}
		return allnotifications;
	}
	
	public void clicksubscriptions() {
	     By media=By.xpath("//*[contains(text(),'Media')]");
	     click(media);
	     By Mysubscriptions=By.xpath("//span[contains(text(), 'My Subscriptions')]");
	     click(Mysubscriptions);
	}
	public void clickmanagesubscriptions() {
	    By managesubscriptions=By.xpath("//*[contains(text(), 'Manage Subscriptions')]");
	    click(managesubscriptions);
	}
	public void clickcategories() {
	    By categories=By.xpath("//button/span[contains(text(), 'Categories')]");
	    click(categories);
	}
	public int countofsubscribedcategories() {
		 By categorieslist= By.xpath("//span[contains(text(),'API_Category')]");//("//*[contains(@col-id,'label') and contains(@role,'gridcell')]");	 
		 waitForElement(categorieslist);
		 List<WebElement> channellist=getAllWebElementValues(categorieslist);
		 logger.info("The size of the custom fields element is"+channellist.size());
		 ArrayList<String> subscribedcategoriescount = new ArrayList<String>();
		 for(int i=0;i<=channellist.size()-1;i++){
			 subscribedcategoriescount.add(channellist.get(i).getText());
		 }
	  return subscribedcategoriescount.size();
	    
	}
	public void clickchannels() {
	    By channels=By.xpath("//button/span[contains(text(), ' Channels ')]");
	    click(channels);
	}
	
	public int countofsubscribedchannels() {
		 By categorieslist= By.xpath("//div[contains(text(),'API_Team')]");//("//*[contains(@col-id,'label') and contains(@role,'gridcell')]");	 
		 waitForElement(categorieslist);
		 List<WebElement> channellist=getAllWebElementValues(categorieslist);
		 logger.info("The size of the custom fields element is"+channellist.size());
		 ArrayList<String> subscribedchannelscount = new ArrayList<String>();
		 for(int i=0;i<=channellist.size()-1;i++){
			 subscribedchannelscount.add(channellist.get(i).getText());
		 }
	  return subscribedchannelscount.size();
	    
	}
	
	public ArrayList<String> getlistofcategoriessubscribed(){
		 
		 By categorieslist= By.xpath("//*[contains(@col-id,'label') and contains(@role,'gridcell')]");	 
		 waitForElement(categorieslist);
		 List<WebElement> channellist=getAllWebElementValues(categorieslist);
		 logger.info("The size of the custom fields element is"+channellist.size());
		 ArrayList<String> list = new ArrayList<String>();
		 for(int i=0;i<=channellist.size()-1;i++){
			 list.add(channellist.get(i).getText());
		 }
	  return list;
	 }
	
	public ArrayList<String> getlistofchannelssubscribed(){
		 
		 By categorieslist= By.xpath("//*[contains(@col-id,'label') and contains(@role,'gridcell')]");	 
		 waitForElement(categorieslist);
		 List<WebElement> channellist=getAllWebElementValues(categorieslist);
		 logger.info("The size of the custom fields element is"+channellist.size());
		 ArrayList<String> list = new ArrayList<String>();
		 for(int i=0;i<=channellist.size()-1;i++){
			 list.add(channellist.get(i).getText());
		 }
	  return list;
	 }
	 public void click_viewallchannels()
 	 {
 		By viewallchannellocator=By.xpath("//*[contains(@class,'all-teams-section')]//*[contains(@uisref,'portal.team')]");
 		click(viewallchannellocator);
 	 }

	 public int nocountofsubscribedcategories() {
		    By subscribedcategories=By.xpath("//strong[contains(text(), 'API_Category')]");
		    List<WebElement>subscribedcategoriescount=driver.findElements(subscribedcategories);
		    return subscribedcategoriescount.size();
		    
		}
		 
		public int nocountofsubscribedchannels() {
		    By subscribedcategories=By.xpath("//div[contains(text(), 'API_Team')]");
		    List<WebElement>subscribedcategoriescount=driver.findElements(subscribedcategories);
		    return subscribedcategoriescount.size();
		    
		}

		public void click_userprofiledropdown() {
			 
			click(userprofiledropdownlocator);
			logger.info("Clicked on user profile");
		}
	
		 public void click_Myprofile() throws Exception 
	 	 {
	 		click(Myprofilelinklocator);
	 		logger.info("Clicked on my profile");
	 		Thread.sleep(5000);
	 		 
	 	 }

 

 

}

