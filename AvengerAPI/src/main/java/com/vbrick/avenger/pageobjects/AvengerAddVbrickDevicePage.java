
  package com.vbrick.avenger.pageobjects;
  
  import java.util.HashMap; import java.util.Map;
  
  import org.apache.log4j.Logger; import org.openqa.selenium.By; import
  org.openqa.selenium.WebDriver;
  
  
  import com.vbrick.Exception.CustomReport; import
  com.vbrick.avenger.ObjProperty.AvengerAddVbrickDevicePropertyPage; import
  com.vbrick.avenger.ObjProperty.AvengerDevicesPropertyPage;
import com.vbrick.avenger.dao.AddNewDmeBeanPage;
import
  com.vbrick.avenger.dao.AddNewVbrickDeviceBeanPage; import
  com.vbrick.avenger.funUtil.WebElements; import
  com.vbrick.avenger.setup.BasePage;
  
  public class AvengerAddVbrickDevicePage extends WebElements {
  
  private WebDriver driver; private CustomReport customReport; private BasePage basePage; 
  
  By devicenamelocator=By.xpath(AvengerAddVbrickDevicePropertyPage.avengeraddvbrickdevicepage_devicenamelocator.getProperty());
  By macaddresslocator=By.xpath(AvengerAddVbrickDevicePropertyPage.avengeraddvbrickdevicepage_macaddresslocator.getProperty());
  By createbuttonlocator=By.xpath(AvengerAddVbrickDevicePropertyPage.avengeraddvbrickdevicepage_createbuttonlocator.getProperty());
  By AddaDevicebuttonlocator=By.xpath(AvengerDevicesPropertyPage.avengerdevicespage_AddaDevicebuttonlocator.getProperty());
  By updateDevicebuttonlocator=By.xpath(AvengerDevicesPropertyPage. avengerdevicespage_updatedmebuttonlocator.getProperty());
  By streamnamelocator=By.name(AvengerAddVbrickDevicePropertyPage. avengeraddvbrickdevicepage_streamname.getProperty());  
  By streamurllocator =  By.xpath(AvengerAddVbrickDevicePropertyPage. avengeraddvbrickdevicepage_streamurl.getProperty());
  By encodingtypelocator =By.xpath(AvengerAddVbrickDevicePropertyPage. avengeraddvbrickdevicepage_encodingtype.getProperty()); 
  By ismulticast = By.xpath(AvengerAddVbrickDevicePropertyPage. avengeraddvbrickdevicepage_ismulticast.getProperty()); 
  By addurllocator =By.xpath(AvengerAddVbrickDevicePropertyPage.avengeraddvbrickdevicepage_addurl.getProperty()); 
  By canceldeltebutton=By.xpath(AvengerAddVbrickDevicePropertyPage. avengeraddvbrickdevicepage_canceldeletebutton.getProperty());
  
  
  private static Logger logger = Logger.getLogger(AvengerAddVbrickDevicePage.class);
   
  public AvengerAddVbrickDevicePage(WebDriver driver, CustomReport customReport,BasePage basePage)
   { 
	  super(driver);
	  this.driver = driver;
      this.customReport = customReport; this.basePage=basePage;
      
   } 
  
  public void  createNewVbrickDevice(AddNewVbrickDeviceBeanPage addnewvbrickdevicebeanpage)
 
  {
  
  waitForElementPresent(createbuttonlocator);
  enterText(devicenamelocator, addnewvbrickdevicebeanpage.getDevicename());
  By presetstatus=By.xpath("//button[contains(text(),'"+addnewvbrickdevicebeanpage .getDevicestatus()+"')]");
  click(presetstatus);
  enterText(macaddresslocator,
  addnewvbrickdevicebeanpage.getMacaddress());
  click(addurllocator);
  enterText(streamnamelocator, addnewvbrickdevicebeanpage.getStreamname());
  enterText(streamurllocator, addnewvbrickdevicebeanpage.getStreamurl());
  selectValuefromDropDown(encodingtypelocator, addnewvbrickdevicebeanpage.getEncodingtype()); 
  check_Checkbox(ismulticast);
  
  }
  
  public AvengerDevicesPage clickCreateButton(AddNewVbrickDeviceBeanPage addnewvbrickdevicebeanpage)
   { 
	 submit(createbuttonlocator);
     logger.info("Clicked on Create Button"); 
     By createddevicelocator =  By.xpath("//*[contains(text(),'"+addnewvbrickdevicebeanpage.getDevicename()+ "')]");
     waitForElementPresent(createddevicelocator); 
     return basePage.avengerDevicesPage(driver, customReport, basePage);
   }
  
  
  public AvengerDevicesPage editNewVbrickDevice(AddNewVbrickDeviceBeanPage addnewvbrickdevicebeanpage)
   
  {
  By  presetstatus=By.xpath("//button[contains(text(),'"+addnewvbrickdevicebeanpage .getDevicestatus()+"')]");
  pause(5000);
  enterText(devicenamelocator,
  addnewvbrickdevicebeanpage.getDevicename()); click(presetstatus);
  enterText(macaddresslocator, addnewvbrickdevicebeanpage.getMacaddress());
  click(createbuttonlocator); logger.info("Clicked on Create Button"); 
  By createddevicelocator = By.xpath("//*[contains(text(),'"+addnewvbrickdevicebeanpage.getDevicename()+ "')]");  
  waitForElementPresent(createddevicelocator); return
  basePage.avengerDevicesPage(driver, customReport, basePage); 
  }
  
  
 /**
	 * Used to Click on create Lib button
	 * 
	 * @param stextValue : Enter the text you need.
	 */
		  
		  public void createNewVbrickDevice(AddNewDmeBeanPage addnewdmebeanpage) {
		  enterText(devicenamelocator, addnewdmebeanpage.getDevicename());
		  enterText(macaddresslocator, addnewdmebeanpage.getMacaddress());
		  click(createbuttonlocator); }
		  
		  public boolean devicenameTextBoxDisplayed() {
		  if(isDisplayed(devicenamelocator)==true)
		  customReport.reporter("Device Name Text Box Displayed Successfully",""); 
		  else
		  customReport.reporter("Device Name Text Box Not Displayed Successfully","");
		  return isDisplayed(devicenamelocator); 
		  }
		  
		  public boolean macaddressTextBoxDisplayed() {
		  if(isDisplayed(macaddresslocator)==true)
		  customReport.reporter("Mac Address Text Box Displayed Successfully",""); 
		  else
		  customReport.reporter("Mac Address Text Box Not Displayed Successfully","");
		  return isDisplayed(macaddresslocator);
		  }
		  
		  public Map<String, String> get_VbrickDeviceInformation() {
		  waitForElementPresent(devicenamelocator);
		  Map<String,String> dmedevicedata=new HashMap<String, String>();
		  pause(5000);
		  logger.info("Device name after edit is"+getAttribute(devicenamelocator,"value"));  
		  dmedevicedata.put("devicename",getAttribute(devicenamelocator,"value"));  
		  dmedevicedata.put("macaddress",getAttribute(macaddresslocator, "value"));
		  return dmedevicedata; 
		  }
		  
		  public void clickAddURL()
		  { click(addurllocator);
		  customReport.reporter("Add url button is clicked", "");
		  
		  }
		  
		  public void addmultipleURLtoEncoder(AddNewVbrickDeviceBeanPage addnewvbrickdevicebeanpage, int index)
		  
		  {
			  
		  enterTextMultiple(streamnamelocator,addnewvbrickdevicebeanpage.getStreamname(), index);	  
		  enterTextMultiple(streamurllocator,addnewvbrickdevicebeanpage.getStreamurl(), index);		  
		  selectValuefromDropDown_multiple(encodingtypelocator,addnewvbrickdevicebeanpage.getEncodingtype(), index);		  
		  customReport.reporter("Entered valid data for adding multiple url", "");		  
		  }
		  
		  public Map<String,String> getDeviceStreamInformation(int index)
		  
		  { 
		  Map<String,String> encoderurlinfo= new HashMap<String, String>();	  
		  encoderurlinfo.put("streamname", getAttributeMultiple(streamnamelocator,"value", index));	  
		  encoderurlinfo.put("streamurl",getAttributeMultiple(streamurllocator, "value", index));	  
		  encoderurlinfo.put("encodingtype",getDropDownSelectedOptionMultiple(encodingtypelocator, index));	  
		  return  encoderurlinfo;
		 
		  }
		  
		  public void clickRemoveDevice()
		  
		  { 
		  By removedevicelocator=By.xpath(AvengerAddVbrickDevicePropertyPage. avengeraddvbrickdevicepage_removedevicelocator.getProperty());
		  click(removedevicelocator);
		  waitForElement(canceldeltebutton);
		  }
		  
		  public void deleteURL() 
		  
		  {
		  By  deleteurllocator=By.xpath(AvengerAddVbrickDevicePropertyPage. avengeraddvbrickdevicepage_deleteurllocator.getProperty());
		  click(deleteurllocator); 
		  }
		  
		  
		  public String verify_label(String label)
		  
		  { 
			  By labellocator =By.xpath("//*[contains(text(),'"+label+"')]");
			  return getText(labellocator);
		  }
		  
		  public void activateOrInactivateDevice(AddNewVbrickDeviceBeanPage beanpage) {
		  By statuslocator = By.xpath("//button[contains(text(),'"+beanpage.getDevicestatus()+"')]");
		  click(statuslocator);
		  }
		  
		  public Map<String, String> getAllLabels()
		  
		  {
			  
		  By alldevicesbuttonlocator=By. xpath("//div[@class='combined-toolbar responsive']/div/a");
		  Map<String, String> labelsmap=new HashMap<String, String>();
		  labelsmap.put("alldevices",getText(alldevicesbuttonlocator));
		  labelsmap.put("addurl", getText(addurllocator));
		  return labelsmap;
		  
		  }
		  
		  public String verifylabel(String text) 
		  
		  {   
		   By labellocator=By.xpath("//label[text()='"+text+"']");
		   return  getText(labellocator);
		  } 
		  }
		 