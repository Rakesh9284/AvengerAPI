package com.vbrick.avenger.pageobjects;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerPlayerSettingsPropertyPage;
import com.vbrick.avenger.dao.AvengerPlayerSettingsBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerPlayerSettingsPage extends WebElements{

	private static Logger logger = Logger.getLogger(AvengerPlayerSettingsPage.class);
	private WebDriver driver;
	private CustomReport customReport;
	
	private BasePage basePage;
	
	By windowsfirstpreference = By.name(AvengerPlayerSettingsPropertyPage.avengerplayersettingspropertypage_windowsfirstpreferencelocator.getProperty());
	By windowssecondpreference = By.name(AvengerPlayerSettingsPropertyPage.avengerplayersettingspropertypage_windowssecondpreferencelocator.getProperty());
	By windowsthirdpreference = By.name(AvengerPlayerSettingsPropertyPage.avengerplayersettingspropertypage_windowsthirdpreferencelocator.getProperty());
	By macfirstpreference = By.name(AvengerPlayerSettingsPropertyPage.avengerplayersettingspropertypage_macfirstpreferencelocator.getProperty());
	By macsecondtpreference = By.name(AvengerPlayerSettingsPropertyPage.avengerplayersettingspropertypage_macsecondpreferencelocator.getProperty());
	By macthirdpreference = By.name(AvengerPlayerSettingsPropertyPage.avengerplayersettingspropertypage_macthirdpreferencelocator.getProperty());
	By macplayerselectederrorlocator = By.xpath(AvengerPlayerSettingsPropertyPage.avengerplayersettingspropertypage_macplayerselectederrorlocator.getProperty());
	By windowsplayerselectederrorlocator = By.xpath(AvengerPlayerSettingsPropertyPage.avengerplayersettingspropertypage_windowsplayerselectederrorlocator.getProperty());
	By macrankrequirederrorlocator = By.xpath(AvengerPlayerSettingsPropertyPage.avengerplayersettingspropertypage_macrankrequirederrorlocator.getProperty());
	By windowsrankrequirederrorlocator = By.xpath(AvengerPlayerSettingsPropertyPage.avengerplayersettingspropertypage_windowsrankrequirederrorlocator.getProperty());
	By savebuttonlocator  =By.xpath(AvengerPlayerSettingsPropertyPage.avengerplayersettingspropertypage_savebuttonlocator.getProperty());
	By playertextlocator = By.xpath(AvengerPlayerSettingsPropertyPage.avengerplayersettingspropertypage_playertextlocator.getProperty());
	
	public AvengerPlayerSettingsPage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		
		this.customReport = customReport;
		this.basePage=basePage;
	}
	
	public boolean verifyPlayerSettingsVisible()
	{
		return isDisplayed(windowsfirstpreference);
	}

	public Map<String, String> getPlayerSettings()
	{
		Map<String, String> defalutvaluesmap = new HashMap<String, String>();
		defalutvaluesmap.put("WinFirstPreference", getDropDownSelectedOption(windowsfirstpreference));
		defalutvaluesmap.put("WinSecondPreference", getDropDownSelectedOption(windowssecondpreference));
		defalutvaluesmap.put("WinThirdPreference", getDropDownSelectedOption(windowsthirdpreference));
		defalutvaluesmap.put("MacFirstPreference", getDropDownSelectedOption(macfirstpreference));
		defalutvaluesmap.put("MacSecondPreference", getDropDownSelectedOption(macsecondtpreference));
		defalutvaluesmap.put("MacThirdPreference", getDropDownSelectedOption(macthirdpreference));
		return defalutvaluesmap;
	}
	
	public void editPlayerPreferences(AvengerPlayerSettingsBeanPage avengerplayersettingsbeanpage)
	{
		selectValuefromDropDown(windowsfirstpreference,  avengerplayersettingsbeanpage.getWindowsfirstpreference());
		selectValuefromDropDown(windowssecondpreference,  avengerplayersettingsbeanpage.getWindowssecondpreference());
		selectValuefromDropDown(windowsthirdpreference,  avengerplayersettingsbeanpage.getWindowsthirdpreference());
		selectValuefromDropDown(macfirstpreference,  avengerplayersettingsbeanpage.getMacfirstpreference());
		selectValuefromDropDown(macsecondtpreference,  avengerplayersettingsbeanpage.getMacsecondpreference());
		selectValuefromDropDown(macthirdpreference,  avengerplayersettingsbeanpage.getMacthirdpreference());
		customReport.reporter("Edited player preferences", "");
	}
	
	public String getPlayerAlreadySelectedErrorTextMac()
	{
		return getText(macplayerselectederrorlocator);
	}
	
	public String getPlayerAlreadySelectedErrorTextWindows()
	{
		return getText(windowsplayerselectederrorlocator);
	}
	
	public String getRankRequiredErrorTextMac()
	{
		return getText(macrankrequirederrorlocator);
	}
	
	public String getRankRequiredErrorTextWindows()
	{
		return getText(windowsrankrequirederrorlocator);
	}
	
	public void clickSaveButton()
	{
		click(savebuttonlocator);
		customReport.reporter("SaveButtonClicked", "");
		waitForElementPresent(windowsfirstpreference); 
	}
	
	public Map<String,String> getPreferedPlayerText()
	{
		Map<String, String> playertextmap = new HashMap<String, String>();
		playertextmap.put("WindowsText", getTextMultiple(playertextlocator,  1));
		playertextmap.put("MacText", getTextMultiple(playertextlocator,  2));
		return playertextmap;
	}
	
}
