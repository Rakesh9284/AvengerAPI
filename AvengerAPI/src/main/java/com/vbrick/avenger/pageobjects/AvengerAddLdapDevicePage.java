package com.vbrick.avenger.pageobjects;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerAddLdapDevicePropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerDevicesPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerHomePropertyPage;
import com.vbrick.avenger.dao.AddLdapDeviceBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerAddLdapDevicePage extends WebElements {

	
	By AddaDevicebuttonlocator=By.xpath(AvengerDevicesPropertyPage.avengerdevicespage_AddaDevicebuttonlocator.getProperty());
	By ldapconnectornamelocator=By.xpath(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_ldapconnectornamelocator.getProperty());
	By ldapconnectormacaddresslocator=By.xpath(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_ldapconnectormacaddresslocator.getProperty());
	By directorytypelocator=By.xpath(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_directorytypelocator.getProperty());
	By hostlocator=By.xpath(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_hostlocator.getProperty());
	By syncintervallocator=By.xpath(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_syncintervallocator.getProperty());
	By createbuttonlocator=By.xpath(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_createbuttonlocator.getProperty());
	By usernamelocator=By.xpath(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_usernamelocator.getProperty());
	By passwordlocator=By.xpath(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_passwordlocator.getProperty());
	By rootscopelocator=By.xpath(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_rootscopelocator.getProperty());	
	By groupobjectscopelocator=By.name(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_groupobjectscopelocator.getProperty());
	By userobjectscopelocator=By.name(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_userobjectscopelocator.getProperty());
	By usernameattributelocator=By.name(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_usernameattributelocator.getProperty());
	By usertitleattributelocator=By.name(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_usertitleattributelocator.getProperty());
	By userfirstnameattributelocator=By.name(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_userfirstnameattributelocator.getProperty());
	By userlastnameattributelocator=By.name(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_userlastnameattributelocator.getProperty());
	By useremailaddressattributelocator=By.name(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_useremailaddressattributelocator.getProperty());
	By telephonenumberattributelocator=By.name(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_telephonenumberattributelocator.getProperty());
	By ldapconnectornameerrotextlocator=By.xpath(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_ldapconnectornameerrotextlocator.getProperty());
	By ldapconnectormacaddresserrortextlocator=By.xpath(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_ldapconnectormacaddresserrortextlocator.getProperty());
	By membershiplocator=By.name(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_membershiplocator.getProperty());
	By alldevicesbuttonlocator=By.xpath(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_alldevicesbuttonlocator.getProperty());
	By portlocator=By.xpath(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_portlocator.getProperty());
	By sslenabled=By.xpath(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_sslenabled.getProperty());
	By deviceslinklocator=By.xpath(AvengerHomePropertyPage.homepg_deviceslinklocator.getProperty());
	By alldeviceslink = By.xpath(AvengerHomePropertyPage.homepg_alldeviceslink.getProperty());
	By directconnectionstatusactive= By.xpath(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_directconnectionactivebutton.getProperty());
	By directconnectionstatusinactive= By.xpath(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_directconnectioninactivebutton.getProperty());
	By connectornodemacaddress=By.xpath(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_connectornodemacaddress.getProperty());
	By connectornodemacstatus=By.xpath(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_connectornodemacstatus.getProperty());
	By addconnectornodebutton=By.xpath(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_addconnectornodebutton.getProperty());
	By addconnectornodemacaddress2=By.xpath(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_addconnectornodemacaddress2.getProperty());
	By secondmacaddressstatusbutton=By.xpath(AvengerAddLdapDevicePropertyPage.avengeraddldapdevicepropertypage_secondmacaddressstatus.getProperty());
	
	private static Logger logger = Logger.getLogger(AvengerAddLdapDevicePage.class);
	private WebDriver driver;
	private CustomReport customReport;
	private BasePage basePage;
	
	public AvengerAddLdapDevicePage(WebDriver driver, 
			CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		this.customReport = customReport;
		this.basePage = basePage;
	}
	/**
	 *Method to Create LDAP Device
	 */
         
	public void createldapDevice(AddLdapDeviceBeanPage addldapdevicebeanpage)
	{
		if(addldapdevicebeanpage.getDirectconnectionstatus().contains("Inactive"))
		{
			clickUsingSwitch(directconnectionstatusinactive);
			logger.info("The Status of the Device is Inactive");
			enterText(connectornodemacaddress, addldapdevicebeanpage.getConnectionnodemacaddress());
		}
		enterText(ldapconnectornamelocator,  addldapdevicebeanpage.getLdapconnectornamelocator());
		selectValuefromDropDown(directorytypelocator, addldapdevicebeanpage.getDirectorytypelocator() );
		enterText(hostlocator,  addldapdevicebeanpage.getHostlocator());
		if(addldapdevicebeanpage.getSslenabled().equals("true"))
			check_Checkbox(sslenabled);	
		selectValuefromDropDown(syncintervallocator,  addldapdevicebeanpage.getSyncintervallocator());
		enterText(usernamelocator, addldapdevicebeanpage.getUsername() );
		enterText(passwordlocator, addldapdevicebeanpage.getPassword() );
		enterText(rootscopelocator, addldapdevicebeanpage.getRootscope());
		enterText(groupobjectscopelocator,  addldapdevicebeanpage.getGroupobjectscope());
		By status=By.xpath("//button[contains(text(),'"+addldapdevicebeanpage.getStatus()+"')]");
		clickUsingSwitch(status);
		enterText(userobjectscopelocator,  addldapdevicebeanpage.getUserobjectscope());
		enterText(usernameattributelocator, addldapdevicebeanpage.getUserusername() );
		enterText(usertitleattributelocator,  addldapdevicebeanpage.getUsertitle());
		enterText(userfirstnameattributelocator,  addldapdevicebeanpage.getUserfirstname());
		enterText(userlastnameattributelocator,  addldapdevicebeanpage.getUserlastname());
		enterText(useremailaddressattributelocator,  addldapdevicebeanpage.getUseremailaddress());
		enterText(telephonenumberattributelocator,  addldapdevicebeanpage.getUsertelephonenumber());
		
		
		}

	public void click_CreateButton()
	{
		submit(createbuttonlocator);
			waitForElementPresent(AddaDevicebuttonlocator);
		customReport.reporter("Clicked on LDAP Create Button","");
	}
	
	
	  public String ldapDevice_deletefield(AddLdapDeviceBeanPage ldapBeansPage,By locatortodelete, By locatorxpath)
      {
    	    enterText(locatortodelete, ldapBeansPage.getLdapconnectornamelocator());
			logger.info("Length  is"+ getAttribute(locatortodelete,"value").length());
		    clearWebElementTextusingbackspace(locatortodelete,getAttribute(locatortodelete,"value").length());
		    return getText(locatorxpath); 
      }
	  
	 public Map<String, String> get_ldapDeviceValues() 
	 {
		pause(5000);
		waitForElementPresent(ldapconnectornamelocator);
		 Map<String, String> ldapDeviceData= new HashMap<String,String>();
		ldapDeviceData.put("ldapconnectorname",getAttribute(ldapconnectornamelocator, "value"));
		ldapDeviceData.put("directorytype",getAttribute(directorytypelocator, "value"));
		ldapDeviceData.put("host",getAttribute(hostlocator, "value"));
		ldapDeviceData.put("syncinterval",getCurrentValueFromDropdown(syncintervallocator));
		ldapDeviceData.put("username",getAttribute(usernamelocator, "value"));
		ldapDeviceData.put("rootscope",getAttribute(rootscopelocator, "value"));
		ldapDeviceData.put("groupobjectscope",getAttribute(groupobjectscopelocator, "value"));
		ldapDeviceData.put("userobjectscope",getAttribute(userobjectscopelocator, "value"));
		ldapDeviceData.put("usernameattribute",getAttribute(usernameattributelocator, "value"));
		ldapDeviceData.put("usertitleattribute",getAttribute(usertitleattributelocator, "value"));
		ldapDeviceData.put("userfirstnameattribute",getAttribute(userfirstnameattributelocator, "value"));
		ldapDeviceData.put("userlastnameattribute",getAttribute(userlastnameattributelocator, "value"));
		ldapDeviceData.put("useremailaddressattribute",getAttribute(useremailaddressattributelocator, "value"));
		ldapDeviceData.put("telephonenumberattribute",getAttribute(telephonenumberattributelocator, "value"));
		return ldapDeviceData;
	 }
	  
	  public void selectDirectoryType(AddLdapDeviceBeanPage addldapdevicebeanpage)
	  {
		 waitForElementPresent(ldapconnectornamelocator);
		  enterText(ldapconnectornamelocator,  addldapdevicebeanpage.getLdapconnectornamelocator());
		selectValuefromDropDown(directorytypelocator, addldapdevicebeanpage.getDirectorytypelocator() );
		customReport.reporter("The value is selected from the dropdwon", addldapdevicebeanpage.getDirectorytypelocator());	
	  }
	public Map<String, String> getAllLabels() {
			
			Map<String, String> labelsmap=new HashMap<String, String>();
			labelsmap.put("alldevices", getText(alldevicesbuttonlocator));
			labelsmap.put("port", getText(portlocator));
		return labelsmap;
	}

	public HashMap<String, String> verifyDirectConnectionStatusInactive(){
		 
				HashMap<String, String> inactiveconnectornodemacaddressdetails = new HashMap<String, String>();
				inactiveconnectornodemacaddressdetails.put("directconnectionstatus", getAttribute(directconnectionstatusactive, "class"));
				inactiveconnectornodemacaddressdetails.put("macaddress1", getAttribute(connectornodemacaddress, "value"));
				inactiveconnectornodemacaddressdetails.put("macaddress2", getAttribute(addconnectornodemacaddress2, "value"));
				return inactiveconnectornodemacaddressdetails;
	}
	
	public String verifyconnectionnodemacaddressstatus(){
		return getAttribute(connectornodemacstatus, "class");
	}
	
	public void clickCreatedLDAPDevice(AddLdapDeviceBeanPage addldapdevicebeanpage){
		By createdcustomdevicelocator = By.xpath("//*[contains(text(),'"+addldapdevicebeanpage.getLdapconnectornamelocator()+"')]");
		click(createdcustomdevicelocator);
		waitForElement(createbuttonlocator);
	}
	 
	public void addconnectornode(AddLdapDeviceBeanPage addldapdevicebeanpage){
		click(addconnectornodebutton);
		logger.info("clicked on add connector node button");
		enterText(addconnectornodemacaddress2, addldapdevicebeanpage.getConnectionnodemacaddress());
	}
	
	public String verifyDirectConnectionStatusActive(){
		
		return getAttribute(directconnectionstatusactive, "class");
	}
	
	public void selectconnectornodemaccaddressinactivesattus(){
		click(secondmacaddressstatusbutton);
	
	}
	
	public String verify_2MacAddressStatus(){
		return getAttribute(secondmacaddressstatusbutton, "class");
				
	}
}
