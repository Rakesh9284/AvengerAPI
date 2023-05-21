package com.vbrick.avenger.pageobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerUIBrandingPage extends WebElements{
	private static Logger logger = Logger.getLogger(AvengerUIBrandingPage.class);

	private WebDriver driver;
	
	private CustomReport customReport;
	private BasePage basePage;

	By primarycolorinputtextboxlocator=By.name("primaryColorInput");
	By secondarycolorinputtextboxlocator=By.name("secondaryColorInput");
	By primarycolordisplay=By.xpath("//*[contains(@ng-style,'branding.primaryColor')]");
	By secondarycolordisplay=By.xpath("//*[contains(@ng-style,'branding.secondaryColor')]");
	By customizedefaulthelplinktextboxlocator=By.name("helpUri");
	By resetdefaultbuttonlocator=By.xpath("//*[contains(@ng-click,'branding.helpUri')]");
	By addHelpLinkLocator=By.xpath("//*[contains(@ng-click,'addHelpLink()')]");
	By additionalHelpLinksDropdownlocator=By.xpath("//select[contains(@ng-model,'link.type')]");
	By linkLabelFieldLocator=By.xpath("//*[contains(@ng-model,'link.label')]");
	By linkValueLocator=By.xpath("//*[contains(@ng-model,'link.value')]");
	
	public AvengerUIBrandingPage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver=driver;

		this.customReport = customReport;
		this.basePage=basePage;
		// TODO Auto-gener ated constructor stub
	}


	public String verify_label(String labelname)
	{
		By labellocator=By.xpath("//*[contains(text(),'"+labelname+"')]");
		return   getText(labellocator);
	}

   public void enter_PrimaryColorTextBox(String primarycolorvalue)
   {
	   enterText(primarycolorinputtextboxlocator,  primarycolorvalue);
       customReport.reporter("Entered value for Primary Color is",primarycolorvalue);
   }
   
   public void enter_SecondaryColorTextBox(String secondarycolorvalue)
   {
	   enterText(secondarycolorinputtextboxlocator,  secondarycolorvalue);
       customReport.reporter("Entered value for Secondary Color is",secondarycolorvalue);
   }
   
   
   
   public Map<String, String> getprimaytextboxvalue()
   {
	   Map<String, String> colortextboxvalues=new HashMap<String, String>();
	   colortextboxvalues.put("primarycolor",getAttribute(primarycolorinputtextboxlocator, "value"));
	   colortextboxvalues.put("secondarycolor",getAttribute(secondarycolorinputtextboxlocator, "value"));
	   colortextboxvalues.put("primarycolordisplay",getAttribute(primarycolordisplay,"style"));
	   colortextboxvalues.put("secondarycolordisplay",getAttribute(secondarycolordisplay,"style"));
	   logger.info("primary value"+getAttribute(primarycolordisplay,"style"));
	   return colortextboxvalues;
   }
   
   public void movePrimaryColorPickerTool()
   {
	   By sourcelocator=By.xpath("(//*[contains(@class,'farbtastic-overlay')])[1]");
	   moveMouseRandomly(sourcelocator);
   }
   
   public void moveSecondaryColorPickerTool()
   {
	   By sourcelocator=By.xpath("(//*[contains(@class,'farbtastic-overlay')])[2]");
	 moveMouseRandomly(sourcelocator);
   }
   
   public void closeColorPickerTool()
   {
	   By closebutton = By.xpath("//*[not(contains(@class,'ng-hide'))]/button[contains(@class,'close-picker')]");
	   click(closebutton);
   }
   
   //This Method is to be Changed
   public void click_SaveButton()
   {
	   By savechangeslocator =By.xpath("//*[contains(text(),'Save Changes')]/..");
	   click(savechangeslocator);
	   customReport.reporter("Clicked on Save Changes Button","");
	   pause(5000);
	   By clickokbutton=By.xpath("//*[contains(@ng-show,'actionText')]");
	   click(clickokbutton);
   }
   
   public void invalidFile(String filepath)
   {
	  By fileuploadlocator=By.xpath("//*[@name='logoFileName']/..//input[@type='file']");
	  logger.info("add files");
	  enterText_FileUpload(fileuploadlocator, filepath);
   }
   
   public void validFile(String filepath)
   {
	    By fileuploadlocator=By.xpath("//*[@name='logoFileName']/..//input[@type='file']");
		logger.info("add files");
		enterText_FileUpload(fileuploadlocator, filepath);
   }
   
	public void clickStatus(String text)
	{
		By activeButtonlocator = By.xpath("//button[text()='"+text+"']");
		click(activeButtonlocator);
	}
	
	public void clickSave()
	{
		   By savechangeslocator =By.xpath(/*"//form/div[2]/div/button"*/"//form[@name='brandingForm']/div/div[3]/div/button");
		   submit(savechangeslocator);
		   customReport.reporter("Clicked on Save Changes Button","");
	}
	
	public String verifySuccessMessage()
	{
		By savechangeslocator =By.xpath(/*"//form/div[2]/div/button"*/"//form[@name='brandingForm']/div/div[3]/div/button");
		waitForElementPresentVisible(savechangeslocator);
		By settingssavedlocator=By.xpath("//*[@class='modal-header']/../div[2]");
		String popupmessage=getText(settingssavedlocator);
		By savepopuplocator=By.xpath("//*[@ng-click='close()']");
		if(elements(savepopuplocator)==1)
		click(savepopuplocator);
		return popupmessage;
	}
	
	public boolean verifyClearImageButton()
	{
		By clearImagebuttonlocator =By.xpath("//*[@ng-click='removeLogoImageFile()']");
        return isDisplayed(clearImagebuttonlocator);		
	}
	
	public String clickClearImageButton()
	{
		By clearImagebuttonlocator =By.xpath("//*[@ng-click='removeLogoImageFile()']");
		By noLogoSeletectTextlocator = By.xpath("//input[@name='logoFileName']/..");
		pause(2000);
		click(clearImagebuttonlocator);		
		return getText(noLogoSeletectTextlocator);
	}
	
	public void clickPrimaryColourButton()
	{
		By primarybutton = By.xpath("//*[contains(@ng-style,'branding.primaryColor')]");
		click(primarybutton);
		customReport.reporter("Primary colour button is clicked", "");
	}
	
	public void clickSecondaryColourButton()
	{
		By secondarybutton = By.xpath("//*[contains(@ng-style,'branding.secondaryColor')]");
		click(secondarybutton);
		customReport.reporter("Primary colour button is clicked", "");
	}
	
	public String brandingLabel(String labelname)
	{
		By labellocator=By.xpath("//h4[contains(text(),'"+labelname+"')]");
		return  getText(labellocator);
	}

  public String get_ErrorText()
  {
	  By errorlocator=By.xpath("//*[contains(@class,'error-field')]");
	  return getText(errorlocator);
  }
  
  public void enter_Customizedefaulthelplink(String customizehelplinkdata)
  {
	  enterText(customizedefaulthelplinktextboxlocator, customizehelplinkdata);
  }

  public String get_customizeddefaulthelplink()
  {
	  pause(2000);
	   return getAttribute(customizedefaulthelplinktextboxlocator,"value");
  }
   
  public void click_ResetDefaultlinkButton()
  {
	  click(resetdefaultbuttonlocator);
	  logger.info("Clicked on reset default link");
  }
  
  public void click_addHelpLink(){
	  click(addHelpLinkLocator);
	  logger.info("Clicked on Add Help link");
  }
  
  public ArrayList<String> getAdditionalLinkDropDownValues(){
	  
	 return getDropdownValue(additionalHelpLinksDropdownlocator);
	  
  }
  
  public String getLabelFieldPlaceHolder(){  
	return  getAttribute(linkLabelFieldLocator, "placeholder");
  }
  
  public void entertextinLabelField(String value){
	  enterText(linkLabelFieldLocator, value);
  }
 
  public void selectValueFormAdditionalHelpLink(String selectedValue){
	  selectValuefromDropDown(additionalHelpLinksDropdownlocator, selectedValue);
  }
  
  public void enterTextinLinkValue(String value){
	  enterText(linkValueLocator, value);
  }
  
  public void clearLabelTextBox(){
	  clearWebElementText(linkLabelFieldLocator);
  }
  

  public void clearLinkvalueTextBox(){
	  clearWebElementText(linkValueLocator);
  }
  
  
  public String verify_errormsgtext(String labelname)
	{
		By labellocator=By.xpath("//*[contains(@ng-class,'helpLinkForm')]//label[contains(text(),'"+labelname+"')]");
		return   getText(labellocator);
	}
}




