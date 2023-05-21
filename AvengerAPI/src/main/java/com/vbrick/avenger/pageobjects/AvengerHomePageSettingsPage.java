package com.vbrick.avenger.pageobjects;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerHomePageSettingsPropertyPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;
import com.vbrick.avenger.dao.AvengerEditVideoSettingsBeanPage;
public class AvengerHomePageSettingsPage extends WebElements {
	private static Logger logger = Logger.getLogger(AvengerHomePageSettingsPage.class);
	
	
	By firstcarouseldropdown = By.xpath(AvengerHomePageSettingsPropertyPage.homepagesetting_firstcarouseldropdrown.getProperty());
	By secondcarouseldropdown = By.xpath(AvengerHomePageSettingsPropertyPage.homepagesetting_secondcarouseldropdrown.getProperty());
	By thirdcarouseldropdown = By.xpath(AvengerHomePageSettingsPropertyPage.homepagesetting_thirdcarouseldropdrown.getProperty());
	By fourthcarouseldropdown = By.xpath(AvengerHomePageSettingsPropertyPage.homepagesetting_fourthcarouseldropdrown.getProperty());
	By fifthcarouseldropdown = By.xpath(AvengerHomePageSettingsPropertyPage.homepagesetting_fifthcarouseldropdrown.getProperty());
	By savebutton = By.xpath(AvengerHomePageSettingsPropertyPage.homepagesetting_savebutton.getProperty());
	By acceptsavealert=By.xpath(AvengerHomePageSettingsPropertyPage.homepagesetting_acceptalert.getProperty());
	
	private WebDriver driver;
	CustomReport customReport;
	private BasePage basePage;
	
	
	public AvengerHomePageSettingsPage(WebDriver driver,CustomReport customReport, BasePage basePage) {
		super(driver);
		this.driver = driver;
		this.customReport= customReport;
		this.basePage=basePage;
	}
	
	public  LinkedHashMap<String, ArrayList<String>>verify_carouseldropdownvalues(){
		LinkedHashMap<String, ArrayList<String>> carouseldropdownvalues = new LinkedHashMap<String, ArrayList<String>>();
		carouseldropdownvalues.put("firstcarousel", getDropdownValue(firstcarouseldropdown));
		carouseldropdownvalues.put("secondcarousel", getDropdownValue(secondcarouseldropdown));
		carouseldropdownvalues.put("thirdcarousel", getDropdownValue(thirdcarouseldropdown));
		carouseldropdownvalues.put("fourthcarousel", getDropdownValue(fourthcarouseldropdown));
		carouseldropdownvalues.put("fifthcarousel", getDropdownValue(fifthcarouseldropdown));
		return carouseldropdownvalues;
	}
	
	public  LinkedHashMap<String, String>verify_selectedcarouseldropdownvalues(){
		LinkedHashMap<String, String> selecteddropdownvalues = new LinkedHashMap<String, String>();
		selecteddropdownvalues.put("firstcarousel", getDropDownSelectedOption(firstcarouseldropdown));
		selecteddropdownvalues.put("secondcarousel", getDropDownSelectedOption(secondcarouseldropdown));
		selecteddropdownvalues.put("thirdcarousel", getDropDownSelectedOption(thirdcarouseldropdown));
		selecteddropdownvalues.put("fourthcarousel", getDropDownSelectedOption(fourthcarouseldropdown));
		selecteddropdownvalues.put("fifthcarousel", getDropDownSelectedOption(fifthcarouseldropdown));
		
		return selecteddropdownvalues;
	}

	public void click_savebutton(){
		click(savebutton);
		logger.info("clicked on Homepage settings save button ");
	}
	
	public void clickSaveChanges()
	{
		waitForElement(savebutton);
		click(savebutton);
		customReport.reporter("Save Changes Button is Clicked","");
		By settingssavedalertlocator=By.xpath("//*[@class='modal-footer']//*[@type='submit']");
		waitForElement(settingssavedalertlocator);
		if(elements(settingssavedalertlocator)==1)
		click(settingssavedalertlocator);
		customReport.reporter("Save Changes Button is Clicked","");
	}
	 public void selectfirstcarasoueldropdownvalue(String dropdownvalue)
	 {
		 selectValuefromDropDown(firstcarouseldropdown,dropdownvalue);
	 }
	 public void selectsecondcarasoueldropdownvalue(String dropdownvalue)
	 {
		 selectValuefromDropDown(secondcarouseldropdown,dropdownvalue);
	 }
	 public void selectthirdcarasoueldropdownvalue(String dropdownvalue)
	 {
		 selectValuefromDropDown(thirdcarouseldropdown,dropdownvalue);
	 }
	 public void selectfourthcarasoueldropdownvalue(String dropdownvalue)
	 {
		 selectValuefromDropDown(fourthcarouseldropdown,dropdownvalue);
	 }
	 public void selectfifthcarasoueldropdownvalue(String dropdownvalue)
	 {
		 selectValuefromDropDown(fifthcarouseldropdown,dropdownvalue);
	 }
	 public void pickCategory(AvengerEditVideoSettingsBeanPage editvideosettingsbean, int value)
		{
		    By carousel = By.name("category"+value);
			logger.info("The locator value is"+carousel);
			enterText(carousel,  editvideosettingsbean.getCategories());
			pause(3000);
			click(carousel);
			clickEnter(carousel);
		}

	 
}
