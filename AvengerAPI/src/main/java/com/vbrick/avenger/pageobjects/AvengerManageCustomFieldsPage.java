package com.vbrick.avenger.pageobjects;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerManageCustomFieldsPropertyPage;
import com.vbrick.avenger.dao.AvengerManageCustomFieldsBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerManageCustomFieldsPage extends WebElements{
	private static Logger logger = Logger.getLogger(AvengerManageCustomFieldsPage.class);

	By addfilebuttonlocator = By.xpath(AvengerManageCustomFieldsPropertyPage.managecustomfields_addfiled.getProperty());
	By nametextboxlocator =  By.xpath(AvengerManageCustomFieldsPropertyPage.managecustomfields_nametextbox.getProperty());
	By widgetdropdown = By.xpath(AvengerManageCustomFieldsPropertyPage.managecustomfields_widgetdropdownloactor.getProperty());
	By submitbutton = By.xpath(AvengerManageCustomFieldsPropertyPage.managecustomfields_submitbutton.getProperty());
	By widgetoptiontextbox=By.xpath(AvengerManageCustomFieldsPropertyPage.managecustomfields_widgetpicklistoptiontextbox.getProperty());
	By widgetoptionadd=By.xpath(AvengerManageCustomFieldsPropertyPage.managecustomfields_widgetoptionaddbutton.getProperty());
	By customfieldbackbutton=By.xpath(AvengerManageCustomFieldsPropertyPage.managecustomfields_customfieldbackbutton.getProperty());
	
	private WebDriver driver;
	CustomReport customReport;
	private BasePage basePage;
	
	public AvengerManageCustomFieldsPage(WebDriver driver,CustomReport customReport, BasePage basePage) {
		super(driver);
		this.driver = driver;
		this.customReport= customReport;
		this.basePage=basePage;
	}
	
	public void click_addfieldbutton(){
		click(addfilebuttonlocator);
		waitForElement(nametextboxlocator);
		logger.info("Name Textbox for custom field is visible");
	}
	
	 public String verifycustomfields(String lablename){
		 By customfield=By.xpath("//label[contains(text(),'"+lablename+"')]");
		 return getText(customfield);
	 }
	
	public void setcustmofieldname(AvengerManageCustomFieldsBeanPage customfieldsbean){
		enterText(nametextboxlocator, customfieldsbean.getCustomfieldname());
		logger.info("The text entered for custom field name "+customfieldsbean.getCustomfieldname());
	}
	
	public void selectwidgetvalue(AvengerManageCustomFieldsBeanPage customfieldsbean){
		
		selectValuefromDropDown(widgetdropdown, customfieldsbean.getWidgetvalue());
		if(customfieldsbean.getWidgetvalue().contains("Pick List"))
		{
			enterText(widgetoptiontextbox,customfieldsbean.getWidgetoptionvalue());
			logger.info("The picklist option value is "+customfieldsbean.getWidgetoptionvalue());
			click(widgetoptionadd);
		}
	}
	
	public void selectwidgetvalue1(AvengerManageCustomFieldsBeanPage customfieldsbean){
		
		selectValuefromDropDown(widgetdropdown, customfieldsbean.getWidgetvalue());
		if(customfieldsbean.getWidgetvalue().contains("Pick List"))
		{
			enterText(widgetoptiontextbox,customfieldsbean.getWidgetoptionvalue()+"a");
			logger.info("The picklist option value is "+customfieldsbean.getWidgetoptionvalue());
			click(widgetoptionadd);
		}
	}
	
	public void addoptionvalueforpicklist(AvengerManageCustomFieldsBeanPage customfieldsbean){
		enterText(widgetoptiontextbox,customfieldsbean.getWidgetoptionvalue());
		logger.info("The second option value for pick is "+customfieldsbean.getWidgetoptionvalue());
		click(widgetoptionadd);
		}

	public void click_submitbutton(){
		submit(submitbutton);
		logger.info("Clicked on custom fields submit button");
	}
	
	public String verify_Label(String labelname){
		By labellocator = By.xpath("//*[contains(text(),'"+labelname+"')]");
	  	  return  getText(labellocator);
	}
	
	public void clearcustomfieldname(){
		clearWebElementTextusingbackspace(nametextboxlocator,getAttribute(nametextboxlocator, "value").length());
		
	}
	
	public LinkedHashMap<String, String> verifycustomfieldsdetails(){
		LinkedHashMap<String, String> customfieldsdetails= new LinkedHashMap<String, String>();
		customfieldsdetails.put("customfieldname", getAttribute(nametextboxlocator, "value"));
		customfieldsdetails.put("widgetdropdown", getCurrentValueFromDropdown(widgetdropdown));
		
		return customfieldsdetails;
	}
	
	public void click_RequiredOrDisaplayPublicOptions(String label,String option){
		int number;
		if(option.equalsIgnoreCase("yes"))
			number=1;
		else 
			number=2;
		By YesOrNoOptionslocator=By.xpath("(//*[contains(text(),'"+label+"')]/../..//*[@role='radio'])["+number+"]");
		click(YesOrNoOptionslocator);
	}
	
	public String verify_RequiredOrDisaplayPublicOptions(String label,String option){
	int number;
		if(option.equalsIgnoreCase("yes"))
			number=1;
		else 
			number=2;
		By YesOrNoOptionslocator=By.xpath("(//*[contains(text(),'"+label+"')]/../..//*[@role='radio'])["+number+"]");
		return getAttribute(YesOrNoOptionslocator, "class");
	}
	
	public void click_customfieldeditbutton(AvengerManageCustomFieldsBeanPage customfieldsbean){
		By customfieldedit =  By.xpath("//*[contains(text(),'"+customfieldsbean.getCustomfieldname()+"')]/../..//*[contains(@class,'gridlist-toolbar')]//*[contains(@ui-sref,'edit')]");
		click(customfieldedit);
		logger.info("Clicked on custom field edit button");
	}
	
	public void click_customfieldeditbutton1(String cfName){
		By customfieldedit =  By.xpath("//*[contains(text(),'"+cfName+"')]");
		click(customfieldedit);
		logger.info("Clicked on custom field edit button");
	}
	
	public LinkedHashMap<String, String> verify_createdcustomfield(AvengerManageCustomFieldsBeanPage customfieldsbean){
		LinkedHashMap<String,String> createdcustomfieldvalues = new LinkedHashMap<String, String>();
		
		By widgetoption = By.xpath("//*[contains(text(),'"+ customfieldsbean.getCustomfieldname()+"')]/../..//*[contains(text(),'"+customfieldsbean.getWidgetvalue()+"')]");
		By requiredcheckbox = By.xpath("//*[contains(text(),'"+ customfieldsbean.getCustomfieldname()+"')]/../..//*[contains(@ng-show,'field.required')]/..");
		By publicdisplaycheckbox = By.xpath("//*[contains(text(),'"+customfieldsbean.getCustomfieldname()+"')]/../..//*[contains(@ng-show,'field.displayedToUsers')]/..");
		createdcustomfieldvalues.put("widgetoption",getText(widgetoption));
		createdcustomfieldvalues.put("requiredcheckboxlocator", getAttribute(requiredcheckbox, "className"));
		createdcustomfieldvalues.put("publicdisplay", getAttribute(publicdisplaycheckbox, "className"));	
		logger.info("@@requiredcheckbox@@"+ getAttribute(requiredcheckbox, "className"));
		logger.info("-publicdisplaycheckbox---"+ getAttribute(publicdisplaycheckbox, "className"));
		return createdcustomfieldvalues;
	}

	public void click_editforcreatedcustomfield(AvengerManageCustomFieldsBeanPage customfieldsbean){
		By editbutton=By.xpath("//*[contains(text(),'"+customfieldsbean.getCustomfieldname()+"')]/../..//*[contains(@class,'gridlist-toolbar')]/a");
		waitForElement(editbutton);
		click(editbutton);
		logger.info("Clicked on edit for recently creayed custom fields");
		waitForElement(nametextboxlocator);
	}
	
	public boolean verify_WidgetDropdown(){
		return isDisplayedWithoutException(widgetdropdown);
	}
	
	public void deletecustomfield(AvengerManageCustomFieldsBeanPage customfieldsbean, String option){
		By deletebuttonlocator = By.xpath("//*[contains(text(),'"+customfieldsbean.getCustomfieldname()+"')]/../..//*[contains(@class,'gridlist-toolbar')]/button");
		By acceptorcancelbutton = By.xpath("//*[@class='modal-footer']//*[contains(@ng-show,'"+option+"')]");	
		pause(3000);
		click(deletebuttonlocator);
		logger.info("clicked on custom filed delete button");
		waitForElement(acceptorcancelbutton);
		click(acceptorcancelbutton);
		logger.info("clicked on accept or cancel option for custom field"+ acceptorcancelbutton );
		waitForElement(addfilebuttonlocator);
	}
	
	public boolean verify_deletedcustomfield(AvengerManageCustomFieldsBeanPage customfieldsbean){
		
		return isDisplayedWithoutException(containstext_xpath(customfieldsbean.getCustomfieldname()));
	}
	
	public void clickcustomfiledbackbutton(){
		click(customfieldbackbutton);
	}
	
	 public ArrayList<String> getcustomfieldvalues(){
		 
		 By customfieldnames= By.xpath("//*[contains(@class,'type-bold')]");	 
		 waitForElement(customfieldnames);
		 List<WebElement> customfieldvalues=getAllWebElementValues(customfieldnames);
		 logger.info("The size of the custom fields element is"+customfieldvalues.size());
		 ArrayList<String> customfieldorder = new ArrayList<String>();
		 for(int i=0;i<=customfieldvalues.size()-1;i++){
			 customfieldorder.add(customfieldvalues.get(i).getText());
		 }
	  return customfieldorder;
	 }
	 
     public String verify_customfieldvaluesdisplayyes(String label){
    	 
    	 By labellocator=By.xpath("//*[contains(text(),'"+label+"')]/..");
    	 return getAttribute(labellocator, "innerHTML");
     }
     
     public String get_customFieldType(String cfType){
		 By cfTypeLocator=By.xpath("//*[contains(@class,'vb-input-field')]");
		 return getText(cfTypeLocator);
	 }
     
     public boolean verify_customfieldvaluesNotdisplayed(String label){
    	 By labellocator=By.xpath("//*[contains(text(),'"+label+"')]");
    	return isDisplayedWithoutException(labellocator);
     }
     
     public String getpicklistoptionvalue(AvengerManageCustomFieldsBeanPage customfieldsbean){
		 By picklistoptionvalue=By.xpath("//*[contains(@name,'"+customfieldsbean.getCustomfieldname()+"')]/../select");
		 return getAttribute(picklistoptionvalue);
		 
     }
	 
	 public void click_sortedcustomefiled(AvengerManageCustomFieldsBeanPage customfieldsbean, String option){
		 By customfieldsortbuttonlocator=By.xpath("//*[contains(text(),'"+customfieldsbean.getCustomfieldname()+"')]/../..//button[contains(@ng-click,'"+option+"')]");
		 click(customfieldsortbuttonlocator);
	 }
	 
public void deleteallcustomfields(){
		 
		 
		 int deleteCount =elements(By.xpath("//*[contains(text(),'Delete')]"));
		 By deletebuttonlocator = By.xpath("(//*[contains(text(),'Delete')])[1]");
		 By acceptorcancelbutton = By.xpath("//*[contains(@class,'modal-footer')]/button[3]");
		 if (isDisplayedWithoutException(deletebuttonlocator))
		 {
		 for(int i=0;i<deleteCount;i++)
		 {
		 	 pause(2000);
			 click(deletebuttonlocator);
			 waitForElement(acceptorcancelbutton);
			 logger.info("clicked on custom filed delete button");
			 click(acceptorcancelbutton);
			 logger.info("clicked on accept or cancel option for custom field"+ acceptorcancelbutton );
			 waitForElement(addfilebuttonlocator);
		 }
		 }
	 }

	public int get_countOfElements()
	{
		int elementCount=driver.findElements(By.xpath("//div[contains(@ref,'eCenterContainer')]//*[contains(@role,'row')]")).size();
		return elementCount;
	}
	 
	 public void deletevaluesfromtextbox(){
		 clearWebElementTextusingbackspaceUsingAttribute(widgetoptiontextbox);
		 logger.info("text box is cleared");
	 }
	 
	 public void selectpictlistvaluewithoutoptions(AvengerManageCustomFieldsBeanPage customfieldsbean){
		 selectValuefromDropDown(widgetdropdown, customfieldsbean.getWidgetvalue());
		 logger.info("The value selected for type of custom field is " + customfieldsbean.getWidgetvalue());
	 }
	 
	 public boolean verifyCustomFieldSaveButton(){
		 return isEnabled(submitbutton);
	 }
	 
	 public void enterpicklistoptionwithoutadding(AvengerManageCustomFieldsBeanPage customfieldsbean){
		 enterText(widgetoptiontextbox,customfieldsbean.getWidgetoptionvalue());
			logger.info("The second option value for pick is "+customfieldsbean.getWidgetoptionvalue());
	 }
	 
	 public void deletpicklistoption(AvengerManageCustomFieldsBeanPage customfieldsbean){
		By deletepicklistoption = By.xpath("//*[contains(text(),'"+customfieldsbean.getWidgetoptionvalue()+"')]/..//*[contains(@ng-click,'removeOption')]"); 
		click(deletepicklistoption);
		logger.info("Deleted Picklist option");
	 }
}
