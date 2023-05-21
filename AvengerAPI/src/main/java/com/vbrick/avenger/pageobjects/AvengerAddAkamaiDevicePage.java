package com.vbrick.avenger.pageobjects;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerAddAkamaiDevicePropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerCategoriesPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerDevicesPropertyPage;
import com.vbrick.avenger.dao.AddAkamaiDeviceBeanPage;
import com.vbrick.avenger.dao.AvengerAddZoneBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerAddAkamaiDevicePage extends WebElements{
	
	private WebDriver driver;
	private CustomReport customReport;
	private BasePage basePage;
	private static Logger logger = Logger.getLogger(AvengerAddAkamaiDevicePage.class);
	
	By akamainamelocator = By.xpath(AvengerAddAkamaiDevicePropertyPage.avangeraddakamaidevicepropertypage_akamaidevicename.getProperty());
	By akamaihostlocator = By.xpath(AvengerAddAkamaiDevicePropertyPage.avangeraddakamaidevicepropertypage_akamaidevicehostname.getProperty());
	By akamaisecuredeliverycheckboxlocator = By.xpath(AvengerAddAkamaiDevicePropertyPage.avangeraddakamaidevicepropertypage_akamaisecuredelivercheckbox.getProperty());
	By akamaisavebuttonlocator = By.xpath(AvengerAddAkamaiDevicePropertyPage.avangeraddakamaidevicepropertypage_akamaicreatebuttonlocator.getProperty());
	By securedeliverylocator = By.name(AvengerAddAkamaiDevicePropertyPage.avangeraddakamaidevicepropertypage_securedeliverylocator.getProperty());
	By deletecategorypopuplocator=By.xpath(AvengerCategoriesPropertyPage.deletecategorypopuplocator.getProperty());
	By AddaDevicebuttonlocator=By.xpath(AvengerDevicesPropertyPage.avengerdevicespage_AddaDevicebuttonlocator.getProperty());
	
	public AvengerAddAkamaiDevicePage(WebDriver driver,  
			CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		this.customReport = customReport;
		this.basePage = basePage;
	}
	
	public void createnewakamaidevice(AddAkamaiDeviceBeanPage addakamiadevicebeanpage ){
		enterText(akamainamelocator, addakamiadevicebeanpage.getAkamaidevicename());
		customReport.reporter("Entered akamai device name", getAttribute(akamainamelocator, "value"));
		enterText(akamaihostlocator, addakamiadevicebeanpage.getAkamaidevicehostname());
		customReport.reporter("Entered akamai host name", getAttribute(akamaihostlocator, "value"));
		
	}
	public void clickSaveButton()
	{
		click(akamaisavebuttonlocator);
	    logger.info("Clicked on the Save Button for Akamai Device");
	}
	
	
	public String get_AkamaiDeviceStatus(String status)
	{
		By statuslocator=By.xpath("//button[contains(text(),'"+status+"')]");
	    return getAttribute(statuslocator,"class");
	}
	
   public boolean verify_SecureDeliveryCheckBox()
   {
	 return isSelected(securedeliverylocator);
   }
   
   public ArrayList<String> get_akamaidevicedetails(AddAkamaiDeviceBeanPage addakamiadevicebeanpage){
	   ArrayList<String> akamaidevicedetails = new ArrayList<String>();
	  By akamaidevicehostlocator=By.xpath("//*[contains(text(),'"+addakamiadevicebeanpage.getAkamaidevicename()+"')]/../..//*[contains(@on,'deviceType')]");
	  By akamaidevicename = By.xpath("//*[contains(text(),'"+addakamiadevicebeanpage.getAkamaidevicename()+"')]");
	  By akamaidevicetype=By.xpath("//*[contains(text(),'"+addakamiadevicebeanpage.getAkamaidevicename()+"')]/../../div[2]");
	  By akamaidevicestatus=By.xpath("//*[contains(text(),'"+addakamiadevicebeanpage.getAkamaidevicename()+"')]/../../div[4]/div[1]");
	  
	  akamaidevicedetails.add(getText(akamaidevicename));
	  akamaidevicedetails.add(getText(akamaidevicetype));
	  akamaidevicedetails.add(getText(akamaidevicehostlocator));
	  akamaidevicedetails.add(getText(akamaidevicestatus));
	   
	   return akamaidevicedetails;
	   
   }
   
   
   public void click_CreatedAkamaiDevice(AddAkamaiDeviceBeanPage addakamiadevicebeanpage){
	   By createdakamaidevicename = By.xpath("//*[contains(text(),'"+addakamiadevicebeanpage.getAkamaidevicename()+"')]");
	   click(createdakamaidevicename);
   }
   
   public ArrayList<String> get_deleteAkamaiErrormessageResons(AvengerAddZoneBeanPage avengeraddzonebeanpage, String bundle){
	   ArrayList<String> deleteakamaierrormessagedetails= new ArrayList<String>();
	   By akamaideleteerrormessage = By.xpath("//h4/span/..");
	   By akamaideltereasonmsg=By.xpath("//p//span[@ng-show='hasProfilesOrZones']");
	   By akamaizone=By.xpath( "//*[contains(@class,'modal-header')]/..//*[contains(text(),'"+bundle+"')]");
	   By akamaiselectedzone=By.xpath("(//*[contains(@class,'modal-header')]/..//*[contains(text(),'"+avengeraddzonebeanpage.getZonename()+"')])[2]");
	   
	   deleteakamaierrormessagedetails.add(getText(akamaideleteerrormessage));
	   deleteakamaierrormessagedetails.add(getText(akamaideltereasonmsg));
	   deleteakamaierrormessagedetails.add(getText(akamaizone));
	   deleteakamaierrormessagedetails.add(getText(akamaiselectedzone));
	   
	   return deleteakamaierrormessagedetails;
   }
   
  
   
   }
   
	

