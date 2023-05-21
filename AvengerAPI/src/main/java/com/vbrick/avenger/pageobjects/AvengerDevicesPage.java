package com.vbrick.avenger.pageobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerAddLdapDevicePropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerAddNewCustomDevicePropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerAddNewDmeDevicePropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerAddVbrickDevicePropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerCategoriesPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerDevicesPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerPresentationProfilesPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerZonesPropertyPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerDevicesPage extends WebElements {
	private WebDriver driver;
	private CustomReport customReport;
	
	private BasePage basePage;
	By AddaDevicebuttonlocator=By.xpath(AvengerDevicesPropertyPage.avengerdevicespage_AddaDevicebuttonlocator.getProperty());
	By AddaDmelinklocator=By.xpath(AvengerDevicesPropertyPage.avengerdevicespage_AddaDmelinklocator.getProperty());
	By AddaVbricklinklocator=By.xpath(AvengerDevicesPropertyPage.avengerdevicespage_AddaVbricklinklocator.getProperty());
	By AddaLdaplinklocator=By.xpath(AvengerDevicesPropertyPage.avengerdevicespage_AddaLdaplinklocator.getProperty());
	By deletecategorypopuplocator=By.xpath(AvengerCategoriesPropertyPage.deletecategorypopuplocator.getProperty());
	By ldapconnectornamelocator=By.xpath(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_ldapconnectornamelocator.getProperty());
	By AddaPresentationprofilelocator = By.xpath(AvengerPresentationProfilesPropertyPage.avengerpresentationprofilespage_AddaPresentationprofilelocator.getProperty());
	By devicenamelocator=By.xpath(AvengerAddNewDmeDevicePropertyPage.avengeraddnewdmedevicepropertypage_devicenamelocator.getProperty());
	By macaddresslocator=By.xpath(AvengerAddVbrickDevicePropertyPage.avengeraddvbrickdevicepage_macaddresslocator.getProperty());
	By addzonebuttonlocator = By.xpath(AvengerZonesPropertyPage.avengerzonespage_addzonebuttonlocator.getProperty());
	By addcustomdevicelocator = By.xpath(AvengerDevicesPropertyPage.avengerdevicespage_addacustomdevicelocator.getProperty());
	By customdevicenamelocator = By.xpath(AvengerAddNewCustomDevicePropertyPage.avengeraddnewcustomdevicepage_devicenamelocator.getProperty());
	By akamaidevicelocator = By.xpath(AvengerDevicesPropertyPage.avengerdevicespage_addakamaidevicelocator.getProperty());
	
	private static Logger logger = Logger.getLogger(AvengerDevicesPage.class);

	public AvengerDevicesPage(WebDriver driver,  
			CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		
		this.customReport = customReport;
		this.basePage=basePage;
	}

	public void click_AddDevice()
	{
		click(AddaDevicebuttonlocator);
		customReport.reporter("Add Device Button is Clicked","");
	}

	public boolean verify_AddDevice()
	{
		return locatorsVisibilityAsPerRoles(AddaDevicebuttonlocator);
	}

	public AvengerAddNewDmeDevicePage click_AddDmeDevice()
	{
		click(AddaDmelinklocator);
		customReport.reporter("Add Dme link  is Clicked","");
		waitForElementPresent(devicenamelocator);
		return basePage.avengerAddDmeDevicePage(driver,  customReport, basePage);
	}
	public boolean verify_AddDmeDevice()
	{
		return locatorsVisibilityAsPerRoles(AddaDmelinklocator);
	}

	public AvengerAddVbrickDevicePage click_VbrickDevice()
	{
		click(AddaVbricklinklocator);
		customReport.reporter("VBrick Device  link  is Clicked","");
		waitForElementPresent(macaddresslocator);
		return basePage.avengerAddVbrickDevicePage(driver,  customReport, basePage);
	}

	public boolean verify_VbrickDevice()
	{
		return locatorsVisibilityAsPerRoles(AddaVbricklinklocator);
	}
	public AvengerAddLdapDevicePage click_LdapDevice()
	{
		click(AddaLdaplinklocator);
		customReport.reporter("LDAP Device  link  is Clicked","");
		waitForElementPresent(ldapconnectornamelocator);
		return basePage.avengerAddLdapDevicePage(driver,  customReport, basePage);
	}

	public AvengerAddCustomDevicePage click_CustomDevice()
	{
		click(addcustomdevicelocator);
		customReport.reporter("Custom Device link  is Clicked","");
		waitForElementPresent(customdevicenamelocator);
		return basePage.avengerAddCustomDevicePage(driver,  customReport, basePage);
	}

	public boolean verify_CustomDevice()
	{
		return locatorsVisibilityAsPerRoles(addcustomdevicelocator);
	}
	
	public String verify_Device(String verifypreset)
	{
		By devicename=By.xpath("//*[text()='"+verifypreset+"']");
		waitForElementPresent(devicename);
		customReport.reporter("Device Created Successfully",verifypreset);
		return getText(devicename);   
	}



	public String delete_Device(String deletedevicename)
	{
		By devicename = By.xpath("//*[contains(text(),'"+deletedevicename+"')]/../..//*[@class='dropdown']/button");
		By devicedeletebutton=By.xpath("//*[contains(text(),'"+deletedevicename+"')]/../..//*[contains(text(),'Delete')]");
		click(devicename);
		click(devicedeletebutton);
		pause(5000);
		click(deletecategorypopuplocator);
		waitForElementPresent(AddaDevicebuttonlocator);
		return String.valueOf(elements(devicename));   
	}

	public String get_DeviceStatus(String devicename)
	{
		By devicestatusxpath=By.xpath("//*[contains(text(),'"+devicename+"')]/../../div[4]//div[not(contains(@class,'ng-hide'))]");
		logger.info("Device Status is"+getText(devicestatusxpath));
		return getText(devicestatusxpath);
	}

	public String get_Reboottext(String devicename)
	{
		By reboottooltip=By.xpath("//*[contains(text(),'"+devicename+"')]/../../div[5]//*[@ng-show='showRebootButton(device)']");
		logger.info("The value Reboot text is"+getText(reboottooltip));
		return getText(reboottooltip);
	}

	public ArrayList<String> get_AlldetailsForDevice()
	{
		ArrayList<String> tableheaderlist= new ArrayList<String>();
		for(int i=1;i<=5;i++)
		{
			By tableheader=By.xpath("//*[@class='file-list']/div[1]/div["+i+"]");
			tableheaderlist.add(getText(tableheader));
		}
		for (int i = 0; i < tableheaderlist.size(); i++) {
			logger.info("Value in table header list"+tableheaderlist.get(i));
		}
		return tableheaderlist;  
	}

	public AvengerAddLdapDevicePage click_CreatedLDAPDevice(String verifypreset)
	{
		pause(5000);
		By devicename=By.xpath("//*[contains(text(),'"+verifypreset+"')]");	
		waitForElementPresent(devicename);
		click(devicename);
		customReport.reporter("Clicked on Created LDAP Device",verifypreset);
		waitForElementPresent(ldapconnectornamelocator);
		return basePage.avengerAddLdapDevicePage(driver,  customReport, basePage);
	}
	
	public AvengerAddCustomDevicePage click_CreatedCustomDevice(String verifypreset)
	{
		pause(5000);
		By devicename=By.xpath("//*[contains(text(),'"+verifypreset+"')]");	
		waitForElementPresent(devicename);
		click(devicename);
		customReport.reporter("Clicked on Created custom device",verifypreset);
		return basePage.avengerAddCustomDevicePage(driver,  customReport, basePage);
	}

	 public AvengerAddNewDmeDevicePage click_Device(String verifypreset)
		{
			if(driver.toString().contains("InternetExplorer")){
				logger.info("click device for IE browser");
			By devicename=By.xpath("//*[text()='"+verifypreset+"']");
			waitForElementPresent(devicename);
			customReport.reporter("Clicked on Created Device ","");
	      scrollClick(devicename);
			}
			else{
				logger.info("click device for Firefox or chrome");
				By devicename=By.xpath("//*[text()='"+verifypreset+"']");
				waitForElementPresent(devicename);
				customReport.reporter("Clicked on Created Device ","");
		    	click(devicename);
			}
			return basePage.avengerAddDmeDevicePage(driver,  customReport, basePage);
		}
	 
	 public AvengerAddVbrickDevicePage click_VbrickDevice(String verifypreset)
		{
			if(driver.toString().contains("InternetExplorer")){
				logger.info("click device for IE browser");
			By devicename=By.xpath("//*[text()='"+verifypreset+"']");
			waitForElementPresent(devicename);
			customReport.reporter("Clicked on Created Device ","");
	      scrollClick(devicename);
			}
			else{
				logger.info("click device for Firefox or chrome");
				By devicename=By.xpath("//*[text()='"+verifypreset+"']");
				waitForElementPresent(devicename);
				customReport.reporter("Clicked on Created Device ","");
		    	click(devicename);
			}
			return basePage.avengerAddVbrickDevicePage(driver,  customReport, basePage);
		}
	 
	
	 

	public boolean verifyLogOfDevice(String devicename)
	{
		
		By clickdropdownlocator=By.xpath("//*[contains(text(),'"+devicename+"')]/../..//button[contains(@class,'more-icon')]");
		click(clickdropdownlocator);
		
		By viewloglocator = By.xpath("//*[contains(text(),'"+devicename+"')]/../..//*[contains(@ng-click,'openLog')]");
		By deviceaddedlocator = By.xpath("//*[contains(text(),'Succeeded')]");
		
		click(viewloglocator);
		return isDisplayedWithoutException(deviceaddedlocator);

	}

	public void closeDeviceLog()
	{
		By closeloglocator = By.xpath("//*[contains(@class,'close pull')]");
		click(closeloglocator);
	}

	public void clickDeleteDevice(String deletedevicename)
	{
		 By devicename = By.xpath("//*[contains(text(),'"+deletedevicename+"')]/../..//*[@class='dropdown']/button");
		 By devicedeletebutton=By.xpath("//*[contains(text(),'"+deletedevicename+"')]/../..//*[contains(@ng-click,'deleteDevice')]");
		click(devicename);
		pause(5000);
		click(devicedeletebutton);
	}
	
	public Map<String, String> deleteDeviceDetails(String PPorZone)
	{
		By devicedeleteconflictmessage = By.xpath("//*[contains(@class,'modal-header')]/h4");
		By devicePPorZonemessage = By.xpath("//*[contains(@class,'modal-body conflicted')]");
		By pporzone = By.xpath("//*[not(contains(@class,'ng-hide'))]/a[contains(text(),'"+PPorZone+"')]");
		
		Map<String, String> devicedetailsmap = new HashMap<String, String>();
		pause(2000);
		devicedetailsmap.put("ErrorMessage", getText(devicedeleteconflictmessage));
		devicedetailsmap.put("PPDetailsMessage", getText(devicePPorZonemessage));
		devicedetailsmap.put("PPorZone", getText(pporzone));
		
		
		return devicedetailsmap;
	}
	
	
	public Map<String, String> deleteDeviceRecordingDetails(String devicename)
	{
		By devicedeleteconflictmessage = By.xpath("//*[contains(@class,'conflicted')]/..//h4");
		By recordinginf = By.xpath("//*[contains(@class,'conflicted')]/p/span[1]");
		Map<String, String> devicedetailsmap = new HashMap<String, String>();
		
		devicedetailsmap.put("ErrorMessage", getText(devicedeleteconflictmessage));
		devicedetailsmap.put("RecordingMessage", getText(recordinginf));
		
		return devicedetailsmap;
	}
	
	public void clickOKButton()
	{
		By okbuttonlocator = By.xpath("//button[@ng-click='close()']");
		clickUsingSwitch(okbuttonlocator);
	}
	
	public String getDeviceStatus(String devicename)
	{
		By devicestatuslocator = By.xpath("//*[contains(text(),'"+devicename+"')]/../../div[4]//div[not(contains(@class,'ng-hide'))]");
		return getText(devicestatuslocator);
	}
	
	public void openDeviceLog(String devicename)
	{
		By viewloglocator = By.xpath("//*[text()='"+devicename+"']/../..//button[contains(@ng-click,'openLog')]");
		click(viewloglocator);
	
	}
	
	public void OpenLogDetails()
	{
		By logdetailslocator = By.xpath("//*[contains(@ng-click,'showDetails')]");
		click(logdetailslocator);
	}
	
	public String verify_label(String label)
	{
		By labeltext=By.xpath("//*[contains(text(),'"+label+"')]");
		return getText(labeltext);
	}
	
	public String delete_Devices(String deletepreset)
	{
		By devicename=By.xpath("//*[contains(text(),'"+deletepreset+"')]/../..//*[contains(@ng-click,'deleteDevice')]");
			int devicenumbers= elements(devicename);
			return String.valueOf(devicenumbers);   
	}
	
	public AvengerAddAkamaiDevicePage click_AddAkamaiDevice(){
		click(akamaidevicelocator);
		customReport.reporter("Add Akamai device link  is Clicked","");
		return basePage.avengerAddAkamaiDevicePage(driver, customReport, basePage);
	}
	
	
}
