package com.vbrick.avenger.setup;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.funUtil.ConfiguringActiveDevices;
import com.vbrick.avenger.pageobjects.AvengerAccountsPage;
import com.vbrick.avenger.pageobjects.AvengerAddAkamaiDevicePage;
import com.vbrick.avenger.pageobjects.AvengerAddCustomDevicePage;
import com.vbrick.avenger.pageobjects.AvengerAddLdapDevicePage;
import com.vbrick.avenger.pageobjects.AvengerAddNewDmeDevicePage;
import com.vbrick.avenger.pageobjects.AvengerAddPresentationProfilePage;
import com.vbrick.avenger.pageobjects.AvengerAddUserPage;
import com.vbrick.avenger.pageobjects.AvengerAddVbrickDevicePage;
import com.vbrick.avenger.pageobjects.AvengerAddZonePage;
import com.vbrick.avenger.pageobjects.AvengerAddingUsersintoGroupPage;
import com.vbrick.avenger.pageobjects.AvengerApiKeysPage;
import com.vbrick.avenger.pageobjects.AvengerApprovalProcessPage;
import com.vbrick.avenger.pageobjects.AvengerApprovalProcessQueuePage;
import com.vbrick.avenger.pageobjects.AvengerCategoriesPage;
import com.vbrick.avenger.pageobjects.AvengerConfirmPasswordPage;
import com.vbrick.avenger.pageobjects.AvengerContentRestrictionPage;
import com.vbrick.avenger.pageobjects.AvengerControlPanelPage;
import com.vbrick.avenger.pageobjects.AvengerCreateChildAccountPage;
import com.vbrick.avenger.pageobjects.AvengerDMENetworkStatisticsPage;
import com.vbrick.avenger.pageobjects.AvengerDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerDevicesPage;
import com.vbrick.avenger.pageobjects.AvengerEditRootAccountPage;
import com.vbrick.avenger.pageobjects.AvengerEditVideoSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerEmailServerDetailsPage;
import com.vbrick.avenger.pageobjects.AvengerEventDetailsPage;
import com.vbrick.avenger.pageobjects.AvengerEventReportsPage;
import com.vbrick.avenger.pageobjects.AvengerEventWebCastPage;
import com.vbrick.avenger.pageobjects.AvengerEventsPage;
import com.vbrick.avenger.pageobjects.AvengerExpirationManagementPage;
import com.vbrick.avenger.pageobjects.AvengerFeaturesPage;
import com.vbrick.avenger.pageobjects.AvengerForgotPasswordPage;
import com.vbrick.avenger.pageobjects.AvengerGroupsPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerHomePageSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerImportGroupfromLdapPage;
import com.vbrick.avenger.pageobjects.AvengerInviteURLGuestUserLoginPage;
import com.vbrick.avenger.pageobjects.AvengerLibrariesPage;
import com.vbrick.avenger.pageobjects.AvengerLibraryInformationPage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerManageAccountPage;
import com.vbrick.avenger.pageobjects.AvengerManageCustomFieldsPage;
import com.vbrick.avenger.pageobjects.AvengerMediaPage;
import com.vbrick.avenger.pageobjects.AvengerMediaSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerMessagePage;
import com.vbrick.avenger.pageobjects.AvengerPlayerSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerPlaylistPage;
import com.vbrick.avenger.pageobjects.AvengerPresentationProfilesPage;
import com.vbrick.avenger.pageobjects.AvengerRecordingPage;
import com.vbrick.avenger.pageobjects.AvengerReportsPage;
import com.vbrick.avenger.pageobjects.AvengerRolesPage;
import com.vbrick.avenger.pageobjects.AvengerRootAccountPage;
import com.vbrick.avenger.pageobjects.AvengerRootAccountPage2;
import com.vbrick.avenger.pageobjects.AvengerSystemMessagepage;
import com.vbrick.avenger.pageobjects.AvengerSystemSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerTranscodingGlobalSettingsPage;
import com.vbrick.avenger.pageobjects.AvengerTranscodingPage;
import com.vbrick.avenger.pageobjects.AvengerTranscodingPresetPage;
import com.vbrick.avenger.pageobjects.AvengerUIBrandingPage;
import com.vbrick.avenger.pageobjects.AvengerUploadsPage;
import com.vbrick.avenger.pageobjects.AvengerUserDashboardPage;
import com.vbrick.avenger.pageobjects.AvengerUserPasswordParametersPage;
import com.vbrick.avenger.pageobjects.AvengerVideoBulkEditPage;
import com.vbrick.avenger.pageobjects.AvengerVideoCommentsPage;
import com.vbrick.avenger.pageobjects.AvengerVideoNotificationPage;
import com.vbrick.avenger.pageobjects.AvengerVideosInLibrariesPage;
import com.vbrick.avenger.pageobjects.AvengerVideosPage;
import com.vbrick.avenger.pageobjects.AvengerViewAttendeePage;
import com.vbrick.avenger.pageobjects.AvengerWebexpage;
import com.vbrick.avenger.pageobjects.AvengerZonesPage;
import com.vbrick.avenger.pageobjects.Mailinator;


public class BasePage {

	private static Logger logger = Logger.getLogger(BasePage.class);
	private WebDriver driver;
	private CustomReport customReport;
	private BasePage basePage;
	private AvengerCreateChildAccountPage avengerCreateChildAccountPage;
	private AvengerLoginPage avengerLoginPage;
	private AvengerHomePage avengerHomePage;
	//private BasePage basePage;
	private AvengerAccountsPage avengerAccountsPage;
	private AvengerAddCustomDevicePage addCustomDevicePage;
	private AvengerAddingUsersintoGroupPage addingUsersintoGroupPage;
	private AvengerAddLdapDevicePage addLdapDevicePage;
	private AvengerAddNewDmeDevicePage addNewDmeDevicePage;
	private AvengerAddPresentationProfilePage addPresentationProfilePage;
	private AvengerAddUserPage addUserPage;
	private AvengerAddVbrickDevicePage addVbrickDevicePage;
	private AvengerAddZonePage addZonePage;
	private AvengerApiKeysPage apiKeysPage;
	private AvengerContentRestrictionPage contentrestrictionpage;
	private AvengerCategoriesPage avengerCategoriesPage;
	private AvengerExpirationManagementPage avengerexpirationmanagementpage;
	private AvengerConfirmPasswordPage avengerConfirmPasswordPage;
	private AvengerDashboardPage avengerDashboardPage;
	private AvengerDevicesPage avengerDevicesPage;
	private AvengerEditRootAccountPage avengerEditRootAccountPage;
	private AvengerEditVideoSettingsPage avengerEditVideoSettingsPage;
	private AvengerEmailServerDetailsPage avengerEmailServerDetailsPage;
	private AvengerEventDetailsPage avengerEventDetailsPage;
	private AvengerEventsPage avengerEventsPage;
	private AvengerEventWebCastPage avengerEventWebCastPage;
	private AvengerFeaturesPage avengerFeaturesPage;
	private AvengerForgotPasswordPage avengerForgotPasswordPage;
	private AvengerGroupsPage avengerGroupsPage;
	private AvengerImportGroupfromLdapPage avengerImportGroupfromLdapPage;
	private AvengerLibrariesPage avengerLibrariesPage;
	private AvengerLibraryInformationPage avengerLibraryInformationPage;
	private AvengerManageAccountPage avengerManageAccountPage;
	private AvengerMediaPage avengerMediaPage;
	private AvengerMediaSettingsPage avengerMediaSettingsPage;
	private AvengerMessagePage avengerMessagePage;
	private AvengerPlayerSettingsPage avengerPlayerSettingsPage;
	private AvengerPlaylistPage avengerPlaylistPage;
	private AvengerPresentationProfilesPage avengerPresentationProfilesPage;
	private AvengerRecordingPage avengerRecordingPage;
	private AvengerReportsPage avengerReportsPage;
	private AvengerRolesPage avengerRolesPage;
	private AvengerRootAccountPage avengerRootAccountPage;
	private AvengerRootAccountPage2 avengerRootAccountPage2;
	private AvengerSystemSettingsPage avengerSystemSettingsPage;
	private AvengerTranscodingGlobalSettingsPage avengerTranscodingGlobalSettingsPage;
	private AvengerTranscodingPage avengerTranscodingPage;
	private AvengerTranscodingPresetPage avengerTranscodingPresetPage;
	private AvengerUIBrandingPage avengerUIBrandingPage;
	private AvengerUploadsPage avengerUploadsPage;
	private AvengerUserDashboardPage avengerUserDashboardPage;
	private AvengerUserPasswordParametersPage avengerUserPasswordParametersPage;
	private AvengerVideoCommentsPage avengerVideoCommentsPage;
	private AvengerVideoNotificationPage avengerVideoNotificationPage;
	private AvengerVideosInLibrariesPage avengerVideosInLibrariesPage;
	private AvengerVideosPage avengerVideosPage;
	private AvengerViewAttendeePage avengerViewAttendeePage;
	private AvengerZonesPage avengerZonesPage;
	private Mailinator mailinator;
	private AvengerEventReportsPage avengereventreportspage;
	private AvengerApprovalProcessPage avengerapporvalprocesspage;
	private AvengerApprovalProcessQueuePage avengerapporvalprocessqueuepage;
	private AvengerAddAkamaiDevicePage avengeraddakamaidevicepage;
	private AvengerWebexpage avengerWebexpage;
	private AvengerDMENetworkStatisticsPage avengerDMEnetworkstatisticspage;
	private AvengerManageCustomFieldsPage avengermanagercustomdevicepage;
	private AvengerControlPanelPage avengercontrolpanelpage;
	private AvengerInviteURLGuestUserLoginPage avengereventguestuserloginpage;
	private AvengerHomePageSettingsPage avengerhomepagesettingspage;
	private ConfiguringActiveDevices avengerconfiguringactivedevices;
	private AvengerSystemMessagepage avengersystemMessagePage;
	private AvengerVideoBulkEditPage avengervideobulkeditpage;
	
	public BasePage()
	{
		logger.info("Creation of Constructer of basepage");
	}
	
	public BasePage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		this.driver=driver;
		this.customReport=customReport;
		this.basePage=basePage;
	}

	public AvengerContentRestrictionPage avengercontentrestrictionPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(contentrestrictionpage==null){
			contentrestrictionpage = new AvengerContentRestrictionPage(driver,  customReport, basePage);
		}
		return contentrestrictionpage;
	}
	
	
	public AvengerAccountsPage avengerAccountsPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerAccountsPage==null){
			avengerAccountsPage = new AvengerAccountsPage(driver,  customReport,basePage);
		}
		return avengerAccountsPage;
	}
	
	
	public AvengerCreateChildAccountPage avengerCreateChildAccount(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerCreateChildAccountPage==null){
			avengerCreateChildAccountPage=new AvengerCreateChildAccountPage(driver, customReport,basePage);
		}
		return avengerCreateChildAccountPage;
	}
	
	public AvengerLoginPage avengerLoginPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerLoginPage==null){
			avengerLoginPage=new AvengerLoginPage(driver,customReport,basePage);
		}
		return avengerLoginPage;
	}
	
	public AvengerHomePage avengerHomePage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerHomePage==null){
			avengerHomePage=new AvengerHomePage(driver,  customReport,basePage);
		}
		return avengerHomePage;
	}
	
	public AvengerAddCustomDevicePage avengerAddCustomDevicePage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(addCustomDevicePage==null){
			addCustomDevicePage = new AvengerAddCustomDevicePage(driver,customReport,basePage);
		}
		return addCustomDevicePage;
	}
	
	public AvengerAddingUsersintoGroupPage avengerAddUsersIntoGroupsPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(addingUsersintoGroupPage==null){
			addingUsersintoGroupPage = new AvengerAddingUsersintoGroupPage(driver,  customReport,basePage);
		}
		return addingUsersintoGroupPage;
	}
	
	public AvengerAddLdapDevicePage avengerAddLdapDevicePage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(addLdapDevicePage==null){
			addLdapDevicePage = new AvengerAddLdapDevicePage(driver,  customReport,basePage);
		}
		return addLdapDevicePage;
	}
	
	public AvengerAddNewDmeDevicePage avengerAddDmeDevicePage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(addNewDmeDevicePage==null){
			addNewDmeDevicePage = new AvengerAddNewDmeDevicePage(driver,  customReport,basePage);
		}
		return addNewDmeDevicePage;
	}
	
	public AvengerAddPresentationProfilePage avengerAddPresentationProfilePage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(addPresentationProfilePage==null){
			addPresentationProfilePage = new AvengerAddPresentationProfilePage(driver,  customReport, basePage);
		}
		return addPresentationProfilePage;
	}
	
	public AvengerAddUserPage avengerAddUserpage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(addUserPage==null){
			addUserPage = new AvengerAddUserPage(driver,  customReport, basePage);
		}
		return addUserPage;
	}
	
	public AvengerAddVbrickDevicePage avengerAddVbrickDevicePage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(addVbrickDevicePage==null){
			addVbrickDevicePage = new AvengerAddVbrickDevicePage(driver,  customReport, basePage);
		}
		return addVbrickDevicePage;
	}
	
	public AvengerAddZonePage avengerAddZonePage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(addZonePage==null){
			addZonePage = new AvengerAddZonePage(driver,  customReport, basePage);
		}
		return addZonePage;
	}
	
	public AvengerApiKeysPage avengerApiKeysPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(apiKeysPage==null){
			apiKeysPage = new AvengerApiKeysPage(driver,  customReport, basePage);
		}
		return apiKeysPage;
	}
	
	public AvengerCategoriesPage avengerCategoriesPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerCategoriesPage==null){
			avengerCategoriesPage = new AvengerCategoriesPage(driver,  customReport, basePage);
		}
		return avengerCategoriesPage;
	}
	
	public AvengerConfirmPasswordPage avengerConfirmPasswordPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerConfirmPasswordPage==null){
			avengerConfirmPasswordPage = new AvengerConfirmPasswordPage(driver,  customReport, basePage);
		}
		return avengerConfirmPasswordPage;
	}
	
	public AvengerDashboardPage avengerDashboardPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerDashboardPage==null){
			avengerDashboardPage = new AvengerDashboardPage(driver,  customReport, basePage);
		}
		return avengerDashboardPage;
	}
	
	public AvengerDevicesPage avengerDevicesPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerDevicesPage==null){
			avengerDevicesPage = new AvengerDevicesPage(driver,  customReport, basePage);
		}
		return avengerDevicesPage;
	}
	
	public AvengerEditRootAccountPage avengerEditRootAccountPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerEditRootAccountPage==null){
			avengerEditRootAccountPage = new AvengerEditRootAccountPage(driver,  customReport, basePage);
		}
		return avengerEditRootAccountPage;
	}
	
	public AvengerEditVideoSettingsPage avengerEditVideoSettingsPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerEditVideoSettingsPage==null){
			avengerEditVideoSettingsPage = new AvengerEditVideoSettingsPage(driver,  customReport, basePage);
		}
		return avengerEditVideoSettingsPage;
	}
	
	public AvengerEmailServerDetailsPage avengerEmailServerDetailsPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerEmailServerDetailsPage==null){
			avengerEmailServerDetailsPage = new AvengerEmailServerDetailsPage(driver,  customReport, basePage);
		}
		return avengerEmailServerDetailsPage;
	}
	
	public AvengerEventDetailsPage avengerEventDetailsPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerEventDetailsPage==null){
			avengerEventDetailsPage = new AvengerEventDetailsPage(driver,  customReport, basePage);
		}
		return avengerEventDetailsPage;
	}
	
	public AvengerEventsPage avengerEventsPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerEventsPage==null){
			avengerEventsPage = new AvengerEventsPage(driver,  customReport, basePage);
		}
		return avengerEventsPage;
	}
	
	public AvengerEventWebCastPage avengerEventWebCastPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerEventWebCastPage==null){
			avengerEventWebCastPage = new AvengerEventWebCastPage(driver,  customReport, basePage);
		}
		return avengerEventWebCastPage;
	}
	
	public AvengerFeaturesPage avengerFeaturesPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerFeaturesPage==null){
			avengerFeaturesPage = new AvengerFeaturesPage(driver,  customReport, basePage);
		}
		return avengerFeaturesPage;
	}
	
	public AvengerForgotPasswordPage avengerForgotPasswordPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerForgotPasswordPage==null){
			avengerForgotPasswordPage = new AvengerForgotPasswordPage(driver,  customReport, basePage);
		}
		return avengerForgotPasswordPage;
	}
	
	public AvengerGroupsPage avengerGroupsPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerGroupsPage==null){
			avengerGroupsPage = new AvengerGroupsPage(driver,  customReport, basePage);
		}
		return avengerGroupsPage;
	}
	
	public AvengerImportGroupfromLdapPage avengerImportGroupsFromLdapPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerImportGroupfromLdapPage==null){
			avengerImportGroupfromLdapPage = new AvengerImportGroupfromLdapPage(driver,  customReport, basePage);
		}
		return avengerImportGroupfromLdapPage;
	}
	
	public AvengerLibrariesPage avengerLibrariesPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerLibrariesPage==null){
			avengerLibrariesPage = new AvengerLibrariesPage(driver,  customReport, basePage);
		}
		return avengerLibrariesPage;
	}
	
	public AvengerLibraryInformationPage avengerLibraryInformationPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerLibraryInformationPage==null){
			avengerLibraryInformationPage = new AvengerLibraryInformationPage(driver,  customReport, basePage);
		}
		return avengerLibraryInformationPage;
	}
	
	public AvengerManageAccountPage avengerManageAccountPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerManageAccountPage==null){
			avengerManageAccountPage = new AvengerManageAccountPage(driver,  customReport, basePage);
		}
		return avengerManageAccountPage;
	}
	
	public AvengerMediaPage avengerMediaPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerMediaPage==null){
			avengerMediaPage = new AvengerMediaPage(driver,  customReport, basePage);
		}
		return avengerMediaPage;
	}
	
	public AvengerMediaSettingsPage avengerMediaSettingsPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerMediaSettingsPage==null){
			avengerMediaSettingsPage = new AvengerMediaSettingsPage(driver,  customReport, basePage);
		}
		return avengerMediaSettingsPage;
	}
	
	public AvengerMessagePage avengerMessagePage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerMessagePage==null){
			avengerMessagePage = new AvengerMessagePage(driver,  customReport, basePage);
		}
		return avengerMessagePage;
	}
	
	public AvengerPlayerSettingsPage avengerPlayerSettingsPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerPlayerSettingsPage==null){
			avengerPlayerSettingsPage = new AvengerPlayerSettingsPage(driver,  customReport, basePage);
		}
		return avengerPlayerSettingsPage;
	}
	
	public AvengerPlaylistPage avengerPlaylistPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerPlaylistPage==null){
			avengerPlaylistPage = new AvengerPlaylistPage(driver,  customReport, basePage);
		}
		return avengerPlaylistPage;
	}
	
	public AvengerPresentationProfilesPage avengerPresentationProfilesPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerPresentationProfilesPage==null){
			avengerPresentationProfilesPage = new AvengerPresentationProfilesPage(driver,  customReport, basePage);
		}
		return avengerPresentationProfilesPage;
	}
	
	public AvengerRecordingPage avengerRecordingPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerRecordingPage==null){
			avengerRecordingPage = new AvengerRecordingPage(driver,  customReport, basePage);
		}
		return avengerRecordingPage;
	}
	
	public AvengerReportsPage avengerReportsPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerReportsPage==null){
			avengerReportsPage = new AvengerReportsPage(driver,  customReport, basePage);
		}
		return avengerReportsPage;
	}
	
	public AvengerRolesPage avengerRolesPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerRolesPage==null){
			avengerRolesPage = new AvengerRolesPage(driver,  customReport, basePage);
		}
		return avengerRolesPage;
	}
	
	public AvengerRootAccountPage avengerRootAccountPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerRootAccountPage==null){
			avengerRootAccountPage = new AvengerRootAccountPage(driver,  customReport, basePage);
		}
		return avengerRootAccountPage;
	}
	
	public AvengerRootAccountPage2 avengerRootAccountPage2(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerRootAccountPage2==null){
			avengerRootAccountPage2 = new AvengerRootAccountPage2(driver,  customReport, basePage);
		}
		return avengerRootAccountPage2;
	}
	
	public AvengerSystemSettingsPage avengerSystemSettingsPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerSystemSettingsPage==null){
			avengerSystemSettingsPage = new AvengerSystemSettingsPage(driver,  customReport, basePage);
		}
		return avengerSystemSettingsPage;
	}
	
	public AvengerTranscodingGlobalSettingsPage avengerTranscodingGlobalSettingsPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerTranscodingGlobalSettingsPage==null){
			avengerTranscodingGlobalSettingsPage = new AvengerTranscodingGlobalSettingsPage(driver,  customReport, basePage);
		}
		return avengerTranscodingGlobalSettingsPage;
	}
	
	public AvengerTranscodingPage avengerTranscodingPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerTranscodingPage==null){
			avengerTranscodingPage = new AvengerTranscodingPage(driver,  customReport, basePage);
		}
		return avengerTranscodingPage;
	}
	
	public AvengerTranscodingPresetPage avengerTranscodingPresetPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerTranscodingPresetPage==null){
			avengerTranscodingPresetPage = new AvengerTranscodingPresetPage(driver,  customReport, basePage);
		}
		return avengerTranscodingPresetPage;
	}
	
	public AvengerUIBrandingPage avengerUIBrandingPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerUIBrandingPage==null){
			avengerUIBrandingPage = new AvengerUIBrandingPage(driver,  customReport, basePage);
		}
		return avengerUIBrandingPage;
	}
	
	public AvengerUploadsPage avengerUploadsPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerUploadsPage==null){
			avengerUploadsPage = new AvengerUploadsPage(driver,  customReport, basePage);
		}
		return avengerUploadsPage;
	}
	
	public AvengerUserDashboardPage avengerUserDashBoardPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerUserDashboardPage==null){
			avengerUserDashboardPage = new AvengerUserDashboardPage(driver,  customReport, basePage);
		}
		return avengerUserDashboardPage;
	}
	
	public AvengerUserPasswordParametersPage avengerUserPasswordParametersPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerUserPasswordParametersPage==null){
			avengerUserPasswordParametersPage = new AvengerUserPasswordParametersPage(driver,  customReport, basePage);
		}
		return avengerUserPasswordParametersPage;
	}
	
	public AvengerVideoCommentsPage avengerVideoCommentsPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerVideoCommentsPage==null){
			avengerVideoCommentsPage = new AvengerVideoCommentsPage(driver,  customReport, basePage);
		}
		return avengerVideoCommentsPage;
	}
	
	public AvengerVideoNotificationPage avengerVideoNotificationPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerVideoNotificationPage==null){
			avengerVideoNotificationPage = new AvengerVideoNotificationPage(driver,  customReport, basePage);
		}
		return avengerVideoNotificationPage;
	}
	
	public AvengerVideosInLibrariesPage avengerVideosInLibrariesPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerVideosInLibrariesPage==null){
			avengerVideosInLibrariesPage = new AvengerVideosInLibrariesPage(driver,  customReport, basePage);
		}
		return avengerVideosInLibrariesPage;
	}
	
	public AvengerVideosPage avengerVideosPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerVideosPage==null){
			avengerVideosPage = new AvengerVideosPage(driver,  customReport, basePage);
		}
		return avengerVideosPage;
	}
	
	public AvengerViewAttendeePage avengerViewAttendeePage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerViewAttendeePage==null){
			avengerViewAttendeePage = new AvengerViewAttendeePage(driver, customReport, basePage);
		}
		return avengerViewAttendeePage;
	}
	
	public AvengerZonesPage avengerZonesPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerZonesPage==null){
			avengerZonesPage = new AvengerZonesPage(driver,  customReport, basePage);
		}
		return avengerZonesPage;
	}
	
	public Mailinator mailinator(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(mailinator==null){
			mailinator = new Mailinator(driver,  customReport, basePage);
		}
		return mailinator;
	}

	public AvengerEventReportsPage avengerEventReportsPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengereventreportspage==null){
			avengereventreportspage = new AvengerEventReportsPage(driver,  customReport, basePage);
		}
		return avengereventreportspage;
	}
	
	public AvengerApprovalProcessPage avengerApprovalProcessPage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerapporvalprocesspage==null){
			avengerapporvalprocesspage = new AvengerApprovalProcessPage(driver,  customReport, basePage);
		}
		return avengerapporvalprocesspage;
	}
	public AvengerApprovalProcessQueuePage avengerApprovalProcessQueuePage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerapporvalprocessqueuepage==null){
			avengerapporvalprocessqueuepage = new AvengerApprovalProcessQueuePage(driver,  customReport, basePage);
		}
		return avengerapporvalprocessqueuepage;
	}
	
	public AvengerAddAkamaiDevicePage avengerAddAkamaiDevicePage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengeraddakamaidevicepage==null){
			avengeraddakamaidevicepage = new AvengerAddAkamaiDevicePage(driver,  customReport, basePage);
		}
		return avengeraddakamaidevicepage;
	}
	
	public AvengerWebexpage avengerwebexpage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerWebexpage==null){
			avengerWebexpage = new AvengerWebexpage(driver,  customReport, basePage);
		}
		return avengerWebexpage;
	}
	
	public AvengerDMENetworkStatisticsPage avengerdmenetworkstatisticspage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengerDMEnetworkstatisticspage==null){
			avengerDMEnetworkstatisticspage = new AvengerDMENetworkStatisticsPage(driver,  customReport, basePage);
		}
		return avengerDMEnetworkstatisticspage;
	}
	
	public AvengerManageCustomFieldsPage avengermanagecustomfieldspage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengermanagercustomdevicepage==null){
			avengermanagercustomdevicepage = new AvengerManageCustomFieldsPage(driver,  customReport, basePage);
		}
		return avengermanagercustomdevicepage;
	}
	
	public AvengerControlPanelPage avengercontrolPanelpage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		if(avengercontrolpanelpage==null){
			avengercontrolpanelpage = new AvengerControlPanelPage(driver,  customReport, basePage);
		}
		return avengercontrolpanelpage;
	}
	
	public AvengerInviteURLGuestUserLoginPage avengerinviteurlguestuserloginpage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
	if(avengereventguestuserloginpage==null){
		avengereventguestuserloginpage= new AvengerInviteURLGuestUserLoginPage(driver, customReport, basePage);
	}
	return avengereventguestuserloginpage;
	}
	
	public AvengerHomePageSettingsPage avengerhomepagesettingspage(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
	if(avengerhomepagesettingspage==null){
		avengerhomepagesettingspage= new AvengerHomePageSettingsPage(driver,customReport, basePage);
	}
	return avengerhomepagesettingspage;
	}
	
	public ConfiguringActiveDevices avengerconfiguringDmeDevice(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
	if(avengerconfiguringactivedevices==null){
		avengerconfiguringactivedevices= new ConfiguringActiveDevices(driver,customReport, basePage);
	}
	return avengerconfiguringactivedevices;
	}
	
	public AvengerSystemMessagepage avengersystemMessagePage(WebDriver driver,CustomReport customReport,BasePage basepage){
		
		if(avengersystemMessagePage==null) {
			avengersystemMessagePage=new AvengerSystemMessagepage(driver, customReport, basepage);
		}
		return avengersystemMessagePage;
	}
	
	public AvengerVideoBulkEditPage avengervideobulkeditpage(WebDriver driver,CustomReport customReport,BasePage basepage){
		
		if(avengervideobulkeditpage==null) {
			avengervideobulkeditpage=new AvengerVideoBulkEditPage(driver, customReport, basepage);
		}
		return avengervideobulkeditpage;
	}

public AvengerExpirationManagementPage AvengerExpirationManagementPage(WebDriver driver,CustomReport customReport,BasePage basepage){
		
		if(avengerexpirationmanagementpage==null) {
			avengerexpirationmanagementpage=new AvengerExpirationManagementPage(driver, customReport, basepage);
		}
		return avengerexpirationmanagementpage;
	}
}
