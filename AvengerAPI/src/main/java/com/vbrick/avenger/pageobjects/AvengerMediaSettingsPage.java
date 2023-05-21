package com.vbrick.avenger.pageobjects;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerApprovalProcessPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerCategoriesPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerFeaturesPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerHomePropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerLibrariesPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerPlayerSettingsPropertyPage;
import com.vbrick.avenger.ObjProperty.MediaSettingsPropertyPage;
import com.vbrick.avenger.ObjProperty.TranscodingPropertyPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerMediaSettingsPage extends WebElements {
	By categorieslinklocator = By.xpath(MediaSettingsPropertyPage.mediasettings_categorieslinklocator.getProperty());
	By featureslinklocator = By.xpath(MediaSettingsPropertyPage.mediasettings_featureslinklocator.getProperty());
	By librarieslinklocator = By.xpath(MediaSettingsPropertyPage.mediasettings_librarieslinklocator.getProperty());
	By transcodinglinklocator = By.xpath(MediaSettingsPropertyPage.mediasettings_transcodinglinklocator.getProperty());
	By createlibrarybuttonlocator = By.xpath(AvengerLibrariesPropertyPage.library_createlibrarybuttonlocator.getProperty());
	By addpresetbuttonlocator =By.linkText(TranscodingPropertyPage.transcoding_addpresetbuttonlocator.getProperty());
	By enablecommentslocator = By.name(AvengerFeaturesPropertyPage.avengerfeaturespage_enablecomments.getProperty());
	By createcategorybuttonlocator = By.xpath(AvengerCategoriesPropertyPage.avengercategoriespage_createcategorybuttonlocator.getProperty());
	By savechangesbuttonlocator=By.xpath(AvengerFeaturesPropertyPage.avengerfeaturespage_savechangesbuttonlocator.getProperty());
	By playersettingslinklocator = By.xpath(MediaSettingsPropertyPage.medaisettings_playersettingslinklocator.getProperty());
	By windowsfirstpreference = By.name(AvengerPlayerSettingsPropertyPage.avengerplayersettingspropertypage_windowsfirstpreferencelocator.getProperty());
	By userslink =By.xpath(AvengerHomePropertyPage.homepg_userslink.getProperty());
	By expirationmanagementlinklocator = By.xpath(MediaSettingsPropertyPage.mediasettings_expirationmanagementlinklocator.getProperty());
	By approvalprocesseslinklocator = By.xpath(MediaSettingsPropertyPage.mediasettings_approvalprocesseslinklocator.getProperty());
	By approvalprocessesQueuelinklocator = By.xpath(MediaSettingsPropertyPage.mediasettings_approvalprocessesQueuelinklocator.getProperty());
	By createapprovalprocessbuttonlocator=By.xpath(AvengerApprovalProcessPropertyPage.avengerapprovalprocess_createapporvalprocessbuttonlocator.getProperty());
    
	 private static Logger logger = Logger.getLogger(AvengerMediaSettingsPage.class);

	private WebDriver driver;
	
	private CustomReport customReport;	
	private BasePage basePage;

	public AvengerMediaSettingsPage(WebDriver driver,  
			CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		
		this.customReport = customReport;
		this.basePage=basePage;
	}
	
	public AvengerCategoriesPage click_CategoriesLinkLocator() {
		click(categorieslinklocator);
		customReport.reporter("Categories link is Clicked","");
		waitForElementPresent(createcategorybuttonlocator);
		return basePage.avengerCategoriesPage(driver,  customReport, basePage);
	}

	public boolean verifyCategoriesLink()
	{
		return locatorsVisibilityAsPerRoles(categorieslinklocator);
	}

	
	public AvengerFeaturesPage click_FeaturesLinkLocator() {
		click(featureslinklocator);
		customReport.reporter("clicked on features button","");
		return basePage.avengerFeaturesPage(driver,  customReport, basePage);
	
	}

	public AvengerApprovalProcessPage click_ApprovalProcesses() {
		click(approvalprocesseslinklocator);
    	waitForElementPresent(createapprovalprocessbuttonlocator);
		return basePage.avengerApprovalProcessPage(driver,  customReport, basePage);
	
	}
	
	public AvengerApprovalProcessQueuePage click_ApprovalProcessesQueue() {
		waitForElementPresent(approvalprocessesQueuelinklocator);
		click(approvalprocessesQueuelinklocator);
		return basePage.avengerApprovalProcessQueuePage(driver,  customReport, basePage);
	
	}

	
	public boolean verifyFeaturesLink()
	{
		return locatorsVisibilityAsPerRoles(featureslinklocator);
	}

	
	public AvengerLibrariesPage click_LibrariesLinkLocator() {
        clickUsingSwitch(userslink);
		click(librarieslinklocator);
		waitForElementPresent(createlibrarybuttonlocator);
		logger.info("The Libary page is loaded");
		customReport.reporter("Clicked on Library link", "");
		return basePage.avengerLibrariesPage(driver,  customReport, basePage);
	}

     public boolean verify_LibrariesLinkLocator()
      {
	   return locatorsVisibilityAsPerRoles(librarieslinklocator);
      }
	
	public AvengerTranscodingPage click_TranscodingLinkLocator() {
		pause(5000);
		click(transcodinglinklocator);
		waitForElementPresent(addpresetbuttonlocator);
		customReport.reporter("Clicked on Transcoding Link","");
		return basePage.avengerTranscodingPage(driver,  customReport, basePage);
    	
	}
	
	public boolean verify_TranscodinglinkLocator()
	{
		return locatorsVisibilityAsPerRoles(transcodinglinklocator);
	}
	
	public boolean verify_ApprovalProcess()
	{
		return locatorsVisibilityAsPerRoles(approvalprocesseslinklocator);
	}
	
	public AvengerPlayerSettingsPage click_PlayerSettingsLink()
	{
		click(playersettingslinklocator);
		customReport.reporter("Clicked on Transcoding Link","");
		waitForElementPresent(windowsfirstpreference);
		return basePage.avengerPlayerSettingsPage(driver,  customReport, basePage);
		
	}
	
	public boolean verify_PlayerSettingsLink()
	{
		return locatorsVisibilityAsPerRoles(playersettingslinklocator);
	}

	public Map<String, String> Verify_LabelsMediasettingsTab() {
		
		By recordinglocator=By.xpath("//div/a[contains(@ui-sref,'media-settings.recording')]");
		
		Map<String , String>  labelsmap=new HashMap<String, String>();
		labelsmap.put("collections", getText(librarieslinklocator));
		labelsmap.put("categories", getText(categorieslinklocator));
		labelsmap.put("playersettings", getText(playersettingslinklocator));
		labelsmap.put("recording", getText(recordinglocator));
		labelsmap.put("Transcoding", getText(transcodinglinklocator));
		labelsmap.put("features", getText(featureslinklocator));
		
		return labelsmap;
	}
	
	public AvengerExpirationManagementPage click_expirationManagementLink() {
        click(expirationmanagementlinklocator);
        return basePage.AvengerExpirationManagementPage(driver,  customReport, basePage);
	}

 


}
	
