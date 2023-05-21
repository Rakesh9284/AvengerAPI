package com.vbrick.avenger.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerApiKeysPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerContentRestrictionPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerEmailServerDetailsPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerSystemSettingsPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerUserPasswordParamatersPropertyPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerSystemSettingsPage extends WebElements{
	private static Logger logger = Logger.getLogger(AvengerSystemSettingsPage.class);
	By securitylink=By.xpath(AvengerSystemSettingsPropertyPage.avengersystemsettingspage_securitylinkLocator.getProperty());
	By apikeyslink=By.xpath(AvengerSystemSettingsPropertyPage.avengersystemsettingspage_apikeyslinkLocator.getProperty());
	By basic= By.xpath(AvengerUserPasswordParamatersPropertyPage.avengeruserpasswordparameters_basic.getProperty());
	By addkeylink=By.xpath(AvengerApiKeysPropertyPage.avengerapikeyspage_addkeylinkbuttonlocator.getProperty());
	By environmentlink = By.xpath(AvengerSystemSettingsPropertyPage.avengersystemsettingspage_environmentlinklocator.getProperty());
	By brandinglink = By.xpath(AvengerSystemSettingsPropertyPage.avengersystemsettingspage_brandinglinklocator.getProperty());
	By savechangesbuttonlocator = By.xpath(AvengerEmailServerDetailsPropertyPage.savechangesbuttonlocator.getProperty());
	By customfieldlocator=By.xpath(AvengerSystemSettingsPropertyPage.avengersystemsettingspage_customfiledslinklocator.getProperty());
	By homepagesettings=By.xpath(AvengerSystemSettingsPropertyPage.avengersystemsettingspage_homepage.getProperty());
	By systemMessageLocator=By.xpath(AvengerSystemSettingsPropertyPage.avengersystemsettingspage_systemMessageLocator.getProperty());
	By contentrestrictionlink=By.xpath( AvengerContentRestrictionPropertyPage.avengercontentrestrictionpage_contentrestrictionlinkLocator.getProperty());
	By contentrestrictionpagename=By.xpath(AvengerContentRestrictionPropertyPage.avengercontentrestrictionpage_contentrestrictionpagename.getProperty());
	private WebDriver driver;
	
	private CustomReport customReport;
	private BasePage basePage;

	public AvengerSystemSettingsPage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver=driver;

		this.customReport = customReport;
		this.basePage=basePage;

		// TODO Auto-generated constructor stub
	}
	
	public AvengerContentRestrictionPage click_ContentRestrictionLinkLocator() {

		logger.info("contentrestriction link displayed"+isDisplayed(contentrestrictionlink));
		click(contentrestrictionlink);
		customReport.reporter("Clicked on content restriction link", "");
		waitForElementPresent(contentrestrictionpagename);
		return basePage.avengercontentrestrictionPage(driver,  customReport, basePage);
	} 

	public AvengerUserPasswordParametersPage click_SecurityLinkLocator() {

		logger.info("security link displayed"+isDisplayed(securitylink));
		click(securitylink);
		customReport.reporter("Clicked on Security link", "");
		waitForElementPresent(basic);
		return basePage.avengerUserPasswordParametersPage(driver,  customReport, basePage);
	}
	public boolean verify_SecurityLinkLocator()
	{
		return locatorsVisibilityAsPerRoles(securitylink);
	}

	public AvengerApiKeysPage click_ApikeysLinkLocator() {

		click(apikeyslink);
		customReport.reporter("Clicked on Api Keys link", "");
		waitForElementPresent(addkeylink);
		return basePage.avengerApiKeysPage(driver,  customReport, basePage);
	}
	
	public boolean verify_ApiKeysLink()
	{
		return locatorsVisibilityAsPerRoles(apikeyslink);

	}
	public AvengerEmailServerDetailsPage click_EnvironmentLinkLocator() {

		click(environmentlink);
		customReport.reporter("Clicked on environment link", "");
		waitForElementPresent(savechangesbuttonlocator);
		return basePage.avengerEmailServerDetailsPage(driver,  customReport, basePage);
	}

	public boolean verify_EnvironmentLink()
	{
		return locatorsVisibilityAsPerRoles(environmentlink);
	
	}
	public boolean check_EnvironmentTabDisplayed()
	{
		return isDisplayedWithoutException(environmentlink);
	}

	public AvengerUIBrandingPage click_Branding()
	{
		By bradForm = By.name("brandingForm");
		waitForElementPresent(brandinglink);
		logger.info("The Branding Link is Present"+waitForElementPresent(brandinglink));
		click(brandinglink);
        customReport.reporter("Clicked on Branding Tab","");
        waitForElementPresent(bradForm);
    	return basePage.avengerUIBrandingPage(driver,  customReport, basePage);
	}
	
	public boolean verify_BrandingLink()
	{
		return locatorsVisibilityAsPerRoles(brandinglink);
	}

	public AvengerManageCustomFieldsPage click_CustomFields(){
		click(customfieldlocator);
		return basePage.avengermanagecustomfieldspage(driver,  customReport, basePage);
	}
	
	public AvengerHomePageSettingsPage click_homepagesettings(){
		click(homepagesettings);
		return basePage.avengerhomepagesettingspage(driver, customReport, basePage);
	}
	By formName=By.name("form");
	public AvengerSystemMessagepage click_SystemMessageLink(){
		click(systemMessageLocator);
		waitForElementPresent(formName);
		return basePage.avengersystemMessagePage(driver, customReport, basePage);
	}
	
	public boolean verify_SystemMessagelink(){
		return isDisplayedWithoutException(systemMessageLocator);
	}


}

