package com.vbrick.avenger.pageobjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerVideoBulkEditPropertyPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerVideoBulkEditPage extends WebElements{

	private static Logger logger = Logger.getLogger(AvengerVideoBulkEditPage.class);
	CustomReport customReport;
	WebDriver driver;
	private BasePage basePage;


	By bulkeditsettingslocator=By.xpath(AvengerVideoBulkEditPropertyPage.avengervideobulkeditpage_settingsoptionlocator.getProperty());
	By statusdropdownlocator=By.xpath(AvengerVideoBulkEditPropertyPage.avengervideobulkeditpage_statusdropdownlocator.getProperty());
	By saveButtonLocator=By.xpath(AvengerVideoBulkEditPropertyPage.avengervideobulkeditpage_savebuttonlocator.getProperty());
	By videosettingsdialogsavebuttonlocator=By.xpath(AvengerVideoBulkEditPropertyPage.avengervideobulkeditpage_videosettingsdialogsavebuttonlocator.getProperty());
	By videosettingsdialogokbuttonlocator=By.xpath(AvengerVideoBulkEditPropertyPage.avengervideobulkeditpage_videosettingsdailogokbuttonlocator.getProperty());
	By puglishdropdownlocator=By.name(AvengerVideoBulkEditPropertyPage.avengervideobulkeditpage_publishdropdownlocator.getProperty());
	By publishdatefieldtextboxlocator=By.name(AvengerVideoBulkEditPropertyPage.avengervideobulkeditpage_publishdatetextboxlocator.getProperty());
	By expirationdropdownlocator=By.name(AvengerVideoBulkEditPropertyPage.avengervideobulkeditpage_expirationdropdownlocator.getProperty());
	By expirationdatefieldtextboxlocator=By.name(AvengerVideoBulkEditPropertyPage.avengervideobulkeditpage_expirationdatetextboxlocator.getProperty());
	By bulkeditdeleteoptionlocator=By.xpath(AvengerVideoBulkEditPropertyPage.avengervideobulkeditpage_deleteoptionlocator.getProperty());
	By accesscontroldropdownlocator=By.xpath(AvengerVideoBulkEditPropertyPage.avengervideobulkeditpage_accesscontroldropdownlocator.getProperty());
	By accesscontrollistdropdownlocator=By.xpath(AvengerVideoBulkEditPropertyPage.avengervideobulkeditpage_accesscontrollistdropdownlocator.getProperty());
	By tagsdropdownlocator=By.xpath(AvengerVideoBulkEditPropertyPage.avengervideobulkeditpage_tagsdropdownlocator.getProperty());
	By downloadsdropdownlocator=By.xpath(AvengerVideoBulkEditPropertyPage.avengervideobulkeditpage_downloaddropdownlocator.getProperty());
	By commentsdropdownlocator=By.xpath(AvengerVideoBulkEditPropertyPage.avengervideobulkeditpage_commentsdropdownlocator.getProperty());
	By ratingdropdownlocator=By.xpath(AvengerVideoBulkEditPropertyPage.avengervideobulkeditpage_ratingsdropdownlocator.getProperty());
	By categoriesdropdownlocator=By.xpath(AvengerVideoBulkEditPropertyPage.avengervideobulkeditpage_categoriesdropdownlocator.getProperty());
	By selectallcheckbox=By.xpath(AvengerVideoBulkEditPropertyPage.avengervideobulkeditpage_selectallcheckboxlocator.getProperty());
	By bulkeditbackbutton=By.xpath(AvengerVideoBulkEditPropertyPage.avengervideobulkeditpage_backbuttonlocator.getProperty());
	
	
	public AvengerVideoBulkEditPage(WebDriver driver,   CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		this.customReport=customReport;
		this.basePage=basePage;
	}


	public void clickbulkeditsettingoption(){
		click(bulkeditsettingslocator);
	}

	public void clickVideoCheckBox(String videoname){

		By videoCheckBox=By.xpath("//*[contains(text(),'"+videoname+"')]/../..//button[contains(@class,'btn btn-checkbox')]/span");
		check_Checkbox(videoCheckBox);
		logger.info("Video checkbox selected ");
	}

		
	public void selectStatusDropDownValue(String selectedValue){
		selectValuefromDropDown(statusdropdownlocator, selectedValue);
	}

	public void clickSaveButton(){
		click(saveButtonLocator);
		logger.info("Bulk Edit Page Save button clicked");
		pause(1000);
	}

	public void clickVideoSettingsdailogSAVEbutton(){
		click(videosettingsdialogsavebuttonlocator);
		logger.info("Video Settings Dailog Save button clicked");
		pause(1000);
	}

	public void clickVideoSettingsdailogOKbutton(){
		click(videosettingsdialogokbuttonlocator);
		logger.info("Video Settings Dailog OK button clicked");
		pause(1000);
	}

	public void selectPublishDropdownValue(String selectedValue){
		waitForElement(puglishdropdownlocator);
		selectValuefromDropDown(puglishdropdownlocator, selectedValue);
	}

	public void enterDateinpublishdateField(String date) {
		enterText(publishdatefieldtextboxlocator, date);
		pause(2000);
	}

	public void selectExpirationDropdownvalue(String selectedValue){
		waitForElement(expirationdropdownlocator);
		selectValuefromDropDown(expirationdropdownlocator, selectedValue);
	}

	public void enterDateinexpirationdateField(String date) {
		enterText(expirationdatefieldtextboxlocator, date);
		pause(2000);
	}

	public void clickBulkEditDeleteOption(){
		click(bulkeditdeleteoptionlocator);
		logger.info("Bulk Edit Delete Option Clicked ");
	}

	public ArrayList<String> getallStatusDropDownValues(){
		ArrayList<String> expetcedStatusDropDownValues=new ArrayList<String>();
		expetcedStatusDropDownValues=getDropdownValue(statusdropdownlocator);
		Collections.sort(expetcedStatusDropDownValues);
		return expetcedStatusDropDownValues;
	}
	
	public ArrayList<String> getallaccesscontroldropdownvalues() {
		ArrayList<String> expetcedaccesscontrolDropDownValues=new ArrayList<String>();
		expetcedaccesscontrolDropDownValues=getDropdownValue(accesscontroldropdownlocator);
		Collections.sort(expetcedaccesscontrolDropDownValues);
		System.out.println("expetcedaccesscontrolDropDownValues "+expetcedaccesscontrolDropDownValues);
		return expetcedaccesscontrolDropDownValues;
	}
	public ArrayList<String> getallaccesscontrollistdropdownvalues() {
		ArrayList<String> expetcedaccesscontrollistDropDownValues=new ArrayList<String>();
		expetcedaccesscontrollistDropDownValues=getDropdownValue(accesscontrollistdropdownlocator);
		Collections.sort(expetcedaccesscontrollistDropDownValues);
		System.out.println("expetcedaccesscontrollistDropDownValues application "+expetcedaccesscontrollistDropDownValues);
		return expetcedaccesscontrollistDropDownValues;
	}
	
	public ArrayList<String> getallcategoriesdropdownvalues() {
		ArrayList<String> expetcdcategoriesDropDownValues=new ArrayList<String>();
		expetcdcategoriesDropDownValues=getDropdownValue(categoriesdropdownlocator);
		Collections.sort(expetcdcategoriesDropDownValues);
		return expetcdcategoriesDropDownValues;
	}
	
	
	public ArrayList<String> getdwonloaddropdownvalues() {
		ArrayList<String> downloadDropDownValues=new ArrayList<String>();
		downloadDropDownValues=getDropdownValue(downloadsdropdownlocator);
		Collections.sort(downloadDropDownValues);
		return downloadDropDownValues;
	}
	
	public ArrayList<String> getTagdropdownvalues() {
		ArrayList<String> tagDropDownValues=new ArrayList<String>();
		tagDropDownValues=getDropdownValue(tagsdropdownlocator);
		Collections.sort(tagDropDownValues);
		return tagDropDownValues;
	}
	
	public ArrayList<String> getcommentdropdownvalues() {
		ArrayList<String> commentDropDownValues=new ArrayList<String>();
		commentDropDownValues=getDropdownValue(commentsdropdownlocator);
		Collections.sort(commentDropDownValues);
		return commentDropDownValues;
	}
	
	public ArrayList<String> getratingdropdownvalues() {
		ArrayList<String> ratingDropDownValues=new ArrayList<String>();
		ratingDropDownValues=getDropdownValue(ratingdropdownlocator);
		Collections.sort(ratingDropDownValues);
		return ratingDropDownValues;
	}
	
	public void selectValueFromAccessControlDropdown(String label){
		selectValuefromDropDown(accesscontroldropdownlocator, label);
	}
	
	
	public boolean verifybulkeditdeleteoption(){
		return isDisplayed(bulkeditdeleteoptionlocator);
	}
	
	public void clickbulkeditbackoption(){
		 click(bulkeditbackbutton);
		 logger.info("Bulk edit back option clicked");
		 
	}
	
	public void checkselectalloption(){
		
		{
			check_Checkbox(selectallcheckbox);
			logger.info("Select All checkbox checked");
		}
		
	}
	
	public void uncheckselectalloption(){
		uncheck_Checkbox(selectallcheckbox);
		logger.info("Select All checkbox unchecked");
	}
	public String Checkedallcheckboxes(String videoname){
		By videoCheckBox=By.xpath("//*[contains(text(),'"+videoname+"')]/../..//button[contains(@class,'btn btn-checkbox')]");
		return getAttribute(videoCheckBox,"class");
	}
	
	public boolean verifySavebuttonstatus(){
		By savebuttonlocator=By.xpath("//*[contains(@class,'btn btn-cancel')]/../button[contains(@disabled,'disabled')]");
		waitForElementPresent(saveButtonLocator);
		return isDisplayedWithoutException(savebuttonlocator);
	}
	
	public void clickcancel(){
		
	}
	
}
