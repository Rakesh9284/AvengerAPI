package com.vbrick.avenger.pageobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerAddNewDmeDevicePropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerDevicesPropertyPage;
import com.vbrick.avenger.dao.AddNewDmeBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerAddNewDmeDevicePage extends WebElements {

	private WebDriver driver;
	private CustomReport customReport;
	
	private BasePage basePage;
	
	
	
	By devicenamelocator=By.xpath(AvengerAddNewDmeDevicePropertyPage.avengeraddnewdmedevicepropertypage_devicenamelocator.getProperty());
	By macaddresslocator=By.name(AvengerAddNewDmeDevicePropertyPage.avengeraddnewdmedevicepropertypage_macaddresslocator.getProperty());
	By createbuttonlocator=By.xpath(AvengerAddNewDmeDevicePropertyPage.avengeraddnewdmedevicepropertypage_createbuttonlocator.getProperty());
	By AddaDevicebuttonlocator=By.xpath(AvengerDevicesPropertyPage.avengerdevicespage_AddaDevicebuttonlocator.getProperty());
	By updatedmebuttonlocator=By.xpath(AvengerDevicesPropertyPage.avengerdevicespage_updatedmebuttonlocator.getProperty());
	By streamnamelocator=By.xpath(AvengerAddNewDmeDevicePropertyPage.avengeraddnewdmedevicepropertypage_streamname.getProperty());
	By streamurllocator = By.xpath(AvengerAddNewDmeDevicePropertyPage.avengeraddnewdmedevicepropertypage_streamurl.getProperty());
	By encodingtypelocator = By.xpath(AvengerAddNewDmeDevicePropertyPage.avengeraddnewdmedevicepropertypage_encodingtype.getProperty());
	By ismulticast = By.xpath(AvengerAddNewDmeDevicePropertyPage.avengeraddnewdmedevicepropertypage_ismulticast.getProperty());
	By addurllocator = By.xpath(AvengerAddNewDmeDevicePropertyPage.avengeraddnewdmedevicepropertypage_addurl.getProperty());
	By createurlstreamnamelocator = By.xpath(AvengerAddNewDmeDevicePropertyPage.avengeraddnewdmedevicepropertypage_createurlstreamnamelocator.getProperty());
	By vodcheckboxlocator=By.xpath(AvengerAddNewDmeDevicePropertyPage.avengeraddnewdmedevicepropertypage_vodplaybackcheckboxlocator.getProperty());
  	By prepositioncheckboxlocator=By.xpath(AvengerAddNewDmeDevicePropertyPage.avengeraddnewdmedevicepropertypage_prepositionContentcheckboxlocator.getProperty());
	By hideprepositioncheckboxlocator=By.xpath(AvengerAddNewDmeDevicePropertyPage.avengeraddnewdmedevicepropertypage_prepositionContentcheckboxhide.getProperty());
	By dmestreams=By.xpath(AvengerAddNewDmeDevicePropertyPage.avengeraddnewdmedevicepropertypage_dmestreams.getProperty());
	By streamcancelbutton=By.xpath(AvengerAddNewDmeDevicePropertyPage.avengeraddnewdmedevicepropertypage_streamscancelbutton.getProperty());
	By dmecreationcancelbutton=By.xpath(AvengerAddNewDmeDevicePropertyPage.avengeraddnewdmedevicepropertypage_dmedevicecreationcancelbutton.getProperty());
	By dmeerrorpopupacceptbutton=By.xpath(AvengerAddNewDmeDevicePropertyPage.avengeraddnewdmedevicepropertypage_dmeerromsgpopokbutton.getProperty());
	
	private static Logger logger = Logger.getLogger(AvengerAddNewDmeDevicePage.class);
	
	public AvengerAddNewDmeDevicePage(WebDriver driver,  
			CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		this.customReport = customReport;
		this.basePage = basePage;
	}
	/**
	 * Used to Click on create Lib button
	 * 
	 * @param stextValue
	 *            : Enter the text you need.
	 */
         
	public void createNewDme(AddNewDmeBeanPage addnewdmebeanpage)
	{
		pause(5000);
		enterText(devicenamelocator, addnewdmebeanpage.getDevicename());
		By presetstatus=By.xpath("//button[contains(text(),'"+addnewdmebeanpage.getDevicestatus()+"')]");
		click(presetstatus);
		enterText(macaddresslocator, addnewdmebeanpage.getMacaddress());
		if(addnewdmebeanpage.getVideostreamstab().equals("Advanced"))
		{
		By advancedtablocator = By.xpath(AvengerAddNewDmeDevicePropertyPage.avengeraddnewdmedevicepropertypage_advancedtab.getProperty());
		click(advancedtablocator);
		clickDisplayedElement(addurllocator);
		enterText(streamnamelocator, addnewdmebeanpage.getStreamname());
		enterText(streamurllocator, addnewdmebeanpage.getStreamurl());
		selectValuefromDropDown(encodingtypelocator, addnewdmebeanpage.getEncodingtype());
		
		}
		else 
		{
			By createUrls=By.xpath("//*[text()='"+addnewdmebeanpage.getVideostreamstab()+"']");
			click(createUrls);
			clickDisplayedElement(addurllocator);
			enterText(createurlstreamnamelocator, addnewdmebeanpage.getStreamname());
		}
		
		customReport.reporter("Entered valid data for dme", "");
		
	}
	

	public HashMap<String, String> getDmeData(AddNewDmeBeanPage addnewdmebeanpage)
	{
		HashMap<String, String> dmedata= new HashMap<String, String>();
		dmedata.put("devicename", getAttribute(devicenamelocator, "value"));
		dmedata.put("macaddress", getAttribute(macaddresslocator, "value"));
		dmedata.put("vodstoragedevice",String.valueOf(isSelected(vodcheckboxlocator)));
		if(addnewdmebeanpage.getVideostreamstab().equals("Advanced"))
		{
			By advancedtablocator = By.xpath(AvengerAddNewDmeDevicePropertyPage.avengeraddnewdmedevicepropertypage_advancedtab.getProperty());
			click(advancedtablocator);
			dmedata.put("streamurl", getAttribute(streamurllocator, "value"));
			dmedata.put("encodingtype", getAttribute(encodingtypelocator, "value"));
			dmedata.put("isMulticast",String.valueOf(isSelected(ismulticast)));
		}
		return dmedata;

	}

	
	
	public void clickAdvancedTab()
	{
		By advancedtablocator = By.xpath(AvengerAddNewDmeDevicePropertyPage.avengeraddnewdmedevicepropertypage_advancedtab.getProperty());
		click(advancedtablocator);
	}
	
	public boolean isHlsSelected()
	{
		By hlslocator=By.xpath("//*[@name='hasHls']");
		return isSelected(hlslocator);
	}
	 public void enterAddUrlStream(AddNewDmeBeanPage addnewdmebeanpage,int index)
     {
		 clickDisplayedElement(addurllocator);
		 enterTextMultiple(createurlstreamnamelocator, addnewdmebeanpage.getStreamname(),index);
   By enablehlslocator=By.xpath("");
    }
     
	
	public AvengerDevicesPage clickCreateButton(AddNewDmeBeanPage addnewdmebeanpage)
	{
		submit(createbuttonlocator);
		return basePage.avengerDevicesPage(driver, customReport, basePage);
	}
	
	public AvengerDevicesPage editNewDme(AddNewDmeBeanPage addnewdmebeanpage)
	{
		pause(5000);
		enterText(devicenamelocator, addnewdmebeanpage.getDevicename());
		By presetstatus=By.xpath("//button[contains(text(),'"+addnewdmebeanpage.getDevicestatus()+"')]");
		click(presetstatus);
		enterText(macaddresslocator, addnewdmebeanpage.getMacaddress());
		click(updatedmebuttonlocator);
		By createddmelocator = By.xpath("//*[contains(text(),'"+addnewdmebeanpage.getDevicename()+"')]");
		waitForElementPresent(createddmelocator);
		return basePage.avengerDevicesPage(driver, customReport, basePage);
	}
	
	public boolean devicenameTextBoxDisplayed()
    {
    	  if(isDisplayed(devicenamelocator)==true)
      		customReport.reporter("Device Name Text Box Displayed Successfully","");
      	else
      		customReport.reporter("Device Name Text Box Not Displayed Successfully","");
	  return isDisplayedWithoutException(devicenamelocator);
    }

      public boolean macaddressTextBoxDisplayed()
      {
    	if(isDisplayed(macaddresslocator)==true)
    		customReport.reporter("Mac Address Text Box Displayed Successfully","");
    	else
    		customReport.reporter("Mac Address Text Box Not Displayed Successfully","");
    	  return isDisplayedWithoutException(macaddresslocator);
      } 
      
      public Map<String, String> get_DmeDeviceInformation()
      {
    	 waitForElementPresent(devicenamelocator);
    	  Map<String,String> dmedevicedata= new HashMap<String, String>();
    	 pause(5000);
    	  logger.info("Device name after edit is"+getAttribute(devicenamelocator, "value"));
    	  dmedevicedata.put("devicename",getAttribute(devicenamelocator, "value"));
    	  dmedevicedata.put("macaddress",getAttribute(macaddresslocator, "value"));
     	  return dmedevicedata;
      }
     
      public void clickAddURL()
      {
    	  clickDisplayedElement(addurllocator);
    	  customReport.reporter("Add url button is clicked", "");
      }
        
      public void enterVideoStream(AddNewDmeBeanPage addnewdmebeanpage,int index)
      {
    	enterTextMultiple(streamnamelocator, addnewdmebeanpage.getStreamname(),index);
  		enterTextMultiple(streamurllocator, addnewdmebeanpage.getStreamurl(),index);
  		selectValuefromDropDown_multiple(encodingtypelocator, addnewdmebeanpage.getEncodingtype(),index);
  		click_Checkbox(ismulticast,index);
      }
      
      public void addmultipleURLtoDME(AddNewDmeBeanPage addnewdmebeanpage,int index){
    	  enterTextMultiple(streamnamelocator, addnewdmebeanpage.getStreamname(), index);
    	  enterTextMultiple(streamurllocator, addnewdmebeanpage.getStreamurl(), index);
    	  selectValuefromDropDown_multiple(encodingtypelocator, addnewdmebeanpage.getEncodingtype(), index);
    	  customReport.reporter("Entered valid data for adding multiple url", "");
    	 }
      
      public void addStreamToExistingDevice(AddNewDmeBeanPage addnewdmebeanpage,int index){
    	  enterTextDisplayedElement(streamnamelocator, addnewdmebeanpage.getStreamname());
    	  enterTextMultiple(streamurllocator, addnewdmebeanpage.getStreamurl(), index);
    	  selectValuefromDropDown_multiple(encodingtypelocator, addnewdmebeanpage.getEncodingtype(), index);
    	  customReport.reporter("Entered valid data for adding multiple url", "");
    	 }
      
      
      public Map<String,String> getDeviceStreamInformation(int index)
      {
      Map<String, String>    dmeurlinfo= new HashMap<String, String>();
      dmeurlinfo.put("streamname", getAttributeMultiple(streamnamelocator, "value", index));
      dmeurlinfo.put("streamurl", getAttributeMultiple(streamurllocator, "value", index));
      dmeurlinfo.put("encodingtype", getDropDownSelectedOptionMultiple(encodingtypelocator, index));
      return dmeurlinfo;
      }
      
      public boolean checkingvodstoragecheckbox(AddNewDmeBeanPage addnewdmebeanpage){
    	  By vodcheckboxlocator=By.id("isVideoStorageDevice");
    	   return getAttribute(vodcheckboxlocator,"className").contains("active");
    	 }
      public String verifylabelinDMEPage(String labelname){
    	  
    	  By dmepagelabletextlocator=By.xpath("//*[contains(text(),'"+labelname+"')]");
    	  return getText(dmepagelabletextlocator); 
    	 }
      
      public int clickRemoveDevice()
      {
    	  By removedevice = By.xpath("//*[contains(@ng-click,'remove')]");
    	  click(removedevice);
    	  pause(2000);
    	  return elements(streamnamelocator);
      }
      
      public void checkVODCheckBox()
      {
    	 click(vodcheckboxlocator);
      }
     
      public Map<String,String> getErrorText() {
    	  By streamnameerrormessage=By.xpath("//*[not(contains(@class,'ng-hide'))][contains(@ng-show,'streamName.$error.duplicate')]");
    	  By urlerrormessagelocator=By.xpath("//*[not(contains(@class,'ng-hide'))][contains(@ng-show,'url.$error.duplicate')]");
    	  Map<String,String> errormsgmap= new HashMap<String,String>();
    	  errormsgmap.put("streamerrormessage", getText(streamnameerrormessage));
    	  errormsgmap.put("urlerrormessage", getText(urlerrormessagelocator));
    	  return errormsgmap;
    	 }
      
      public void deleteURL() 
      {
    	  By deleteurllocator=By.xpath("//*[@ ng-click='remove($index)']");
    	  click(deleteurllocator);
      }
 
      public ArrayList<String> getEncodingtypeDropDownValues(AddNewDmeBeanPage addnewdmebeanpage) {
    	  ArrayList<String> dropdownvalues=new ArrayList<String>();
    	  dropdownvalues=getDropdownValue(encodingtypelocator);
    	  return dropdownvalues;
    	 }
      public boolean getMulicastcheckbox(){
    	  return isSelected(ismulticast);
    	   
    	 }

    	 public boolean selectingmulticastcheckbox()
    	 {
    	  check_Checkbox(ismulticast);
    	  return isSelected(ismulticast);
    	 }
    	 
    	 public boolean verify_StreamNameDisabled()
    	 {
    		 return isDisplayedWithoutException(streamnamelocator);
    	 }
    	 
    	 public void activateOrInactivateDevice(AddNewDmeBeanPage beanpage)
 		{
 			By statuslocator = By.xpath("//button[contains(text(),'"+beanpage.getDevicestatus()+"')]");
 			click(statuslocator);
 		}

    	 public boolean selectingmulticastcheckboxmultiple(int index)
    	 {
    	  check_CheckboxMultiple(ismulticast,index);
    	  return isSelected(ismulticast);
    	 }
    	
    	 public boolean deSelectingmulticastcheckboxmultiple(int index)
    	 {
    	  uncheck_CheckboxMultiple(ismulticast,index);
    	  return isSelected(ismulticast);
    	 }
		public void dropDownMultipleSelection(AddNewDmeBeanPage addnewdmebeanpage, int i) {
			getDropDownSelectedOptionMultiple(encodingtypelocator, i);
	        	
		}

		 public String streamnameDisplayed()
	      {
	    	  return String.valueOf(elements(streamnamelocator));
	      }
	
		 public String verifylabel(String text)
		 {
 	     By labellocator=By.xpath("//label[text()='"+text+"']");
         return getText(labellocator);
         }
		
		 public Map<String, String> getlabels() {
 			By alldevicesbuttonlocator=By.xpath("//div[@class='combined-toolbar responsive']/div/a");
 			Map<String, String> labelsmap=new HashMap<String, String>();
 			labelsmap.put("alldevices", getText(alldevicesbuttonlocator));
 			labelsmap.put("addurl", getText(addurllocator));
 			return labelsmap;
 		}
		 public String getDisplayedElement(String diplayelementname)
		 {
			 By displayelementname=By.xpath("//*[contains(text(),'"+diplayelementname+"')]");
			return  getDisplayedElementText(displayelementname);
		 }
		 
		 public String verify_PrepositionContentCheckBox(){
			 return getAttribute(prepositioncheckboxlocator, "class");
		 }
		 
		 public void unCheckVODCheckbox(){
			 click(vodcheckboxlocator);
		 }
		 
		 public boolean prepositioncheckboxDisaplayed(){
			
			 return isDisplayedWithoutException(hideprepositioncheckboxlocator);
			 
		 }
		 
		 public void uncheckPrepositioncheckbox(){
			 click(prepositioncheckboxlocator);

		 }
		 
		 public String getelements(String diplayelementname){
			 By displayelementname=By.xpath("//*[contains(text(),'"+diplayelementname+"')]");
			 return String.valueOf(elements(displayelementname));
		 }
		 
		 public ArrayList<String> getdmestreamnames(){
			 return getAllWebElementValueslist(dmestreams);
		 }
		 
		 public boolean verifycanceldeletebutton(){
			 logger.info("Verified for the Stream cancel button");
			 return isDisplayedWithoutException(streamcancelbutton);
		 }
		 
		 public AvengerDevicesPage clickcancelbutton(){
			 click(dmecreationcancelbutton);
			 logger.info("Clicked on Cancel button on DME creation page");
			 return basePage.avengerDevicesPage(driver, customReport, basePage);
		 }
		 
		 public void click_OKbuttonerrormsg(){
			 click(dmeerrorpopupacceptbutton);
			 logger.info("Clicked on OK for error message");
		 }
		 
		 public int verifySteamsfordevice(){
			return elements(dmestreams);
		 }
		 
}

