package com.vbrick.avenger.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerCategoriesPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerTranscodingGlobalSettingsPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerTranscodingPresetPropertyPage;
import com.vbrick.avenger.ObjProperty.TranscodingPropertyPage;
import com.vbrick.avenger.dao.TranscodingBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerTranscodingPage extends WebElements {
	
	private static Logger logger = Logger.getLogger(AvengerTranscodingPage.class);
	 CustomReport customReport;
	 WebDriver driver;
	 private BasePage basePage;
	 By addpresetbuttonlocator =By.linkText(TranscodingPropertyPage.transcoding_addpresetbuttonlocator.getProperty());
	 By settingsbuttonlocator =By.linkText(TranscodingPropertyPage.transcoding_settingsbuttonlocator.getProperty());
	 By deletecategorypopuplocator=By.xpath(AvengerCategoriesPropertyPage.deletecategorypopuplocator.getProperty());
	 By addvideolabellocator=By.name(AvengerTranscodingGlobalSettingsPropertyPage.avengertranscodingglobalsettingspage_addvideolabellocator.getProperty());
	 By presetlocator =By.xpath(AvengerTranscodingPresetPropertyPage.transcodingpreset_presetlocator.getProperty());
		
	
	 public AvengerTranscodingPage(WebDriver driver,   CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		
		this.customReport=customReport;
		this.basePage=basePage;
	}
	 
	 public AvengerTranscodingPresetPage click_Addpresetbutton()
	 {
		 click(addpresetbuttonlocator);
		 customReport.reporter("Add Preset Button is Clicked","");
		 waitForElementPresent(presetlocator);
		 return basePage.avengerTranscodingPresetPage(driver,  customReport, basePage);
			   
	 }
	 
	 public boolean verify_Addpresetbutton()
	 {
		 return locatorsVisibilityAsPerRoles(addpresetbuttonlocator);
	 }
	 
	 public AvengerTranscodingGlobalSettingsPage click_settingsbutton()
	 {
		 click(settingsbuttonlocator);
		 customReport.reporter("Settings Button is Clicked","");
		 waitForElementPresent(addvideolabellocator);
		 return basePage.avengerTranscodingGlobalSettingsPage(driver,  customReport, basePage);
			   
	 }
	 
	 public boolean verify_settingsbutton()
	 {
		 return locatorsVisibilityAsPerRoles(settingsbuttonlocator);
	 }
	 public void click_newTranscodinglink(TranscodingBeanPage transcodingbeanpage) {
			By transcodinglocator= By.xpath("//a[contains(text(),'"+transcodingbeanpage.getPresetname()+"')]");
			waitForElementPresent(transcodinglocator);
			customReport.reporter("Clicked On New Transcoding link","");
			click(transcodinglocator);
		}
	 
	 
	 public String verifyTranscodingCreation(TranscodingBeanPage transcodingbeanpage) {
			By transcodinglocator= By.xpath("//a[contains(text(),'"+transcodingbeanpage.getPresetname()+"')]");
			waitForElementPresent(transcodinglocator);
			customReport.reporter("Transcoding Created Successfully","");
			return getText(transcodinglocator);
		}
	 
	public void editExistingTranscoding(TranscodingBeanPage transcodingbeanpage)
	{
		By transcodinglocator= By.xpath("//a[contains(text(),'"+transcodingbeanpage.getPresetname()+"')]");
		click(transcodinglocator);
	}
	 
	public void deleteTranscoding(TranscodingBeanPage transcodingbeanpage) {
		
		By transcodingDeletelocator = By.xpath("//a[contains(text(),'"+transcodingbeanpage.getPresetname()+"')]/../../th[2]/button[2]");
		click(transcodingDeletelocator);
		click(deletecategorypopuplocator);
		
	}
	
	public boolean verifyTranscodingDelete(TranscodingBeanPage transcodingbeanpage)
	{
		By transcodinglocator = By.xpath("//a[contains(text(),'"+transcodingbeanpage.getPresetname() + "')]");
		if (elements(transcodinglocator) == 0) {
			logger.info("The Transcoding profile is deleted");
			customReport.reporter("The Transcoding profile is deleted",	transcodingbeanpage.getPresetname());
			return true;
		} else {
			logger.info("The Transcoding profile NOT deleted");
			customReport.reporter("The Transcoding profile is not deleted",	transcodingbeanpage.getPresetname());
			return false;
		}
	}
	
	
	public boolean copyTranscoding(TranscodingBeanPage transcodingbeanpage) {
		By transcodingcopylocator = By.xpath("//a[contains(text(),'"+transcodingbeanpage.getPresetname()+"')]/../../th[2]/button[1]");
		By transcodingcreatingcopylocator = By.xpath("//a[contains(text(),'"+transcodingbeanpage.getPresetname()+" "+"copy"+"')]");
		click(transcodingcopylocator);
		pause(2000);
		if (elements(transcodingcreatingcopylocator) == 1) {
			logger.info("The Transcoding profile is Copyied");
			customReport.reporter("The Transcoding profile is copyied",	transcodingbeanpage.getPresetname());
			return true;
		} else {
			logger.info("The Transcoding profile NOT copied");
			customReport.reporter("The Transcoding profile is not copied",	transcodingbeanpage.getPresetname());
			return false;
		}
	}
	
	public String get_TranscodingStatus(TranscodingBeanPage transcodingbeanpage)
	{
		By presetName=By.xpath("//*[contains(text(),'"+transcodingbeanpage.getPresetname()+"')]/../../td[2]");
	    logger.info("Preset Status is" +getText(presetName));
		return getText(presetName);
		
	}
	
	public void clickeditbutton(String buttonno){
		By addvideoadaptivevideoeditbutton=By.xpath("(//*[contains(@ng-click,'edit')])['+buttonno+']");
		click(addvideoadaptivevideoeditbutton);
	}
}
