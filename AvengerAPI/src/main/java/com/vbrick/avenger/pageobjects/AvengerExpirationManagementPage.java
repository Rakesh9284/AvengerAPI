package com.vbrick.avenger.pageobjects;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerExpirationManagementPage  extends WebElements{

	private WebDriver driver;
	private static Logger logger = Logger.getLogger(AvengerExpirationManagementPage.class);
	private CustomReport customReport;
	private BasePage basePage;

	By addexpiryrulelocator=By.xpath("//*[contains(text(),'Add Expiry Rule')]");
	By rulenamelocator=By.xpath("(//*[contains(@aria-label,'Rule Name')])[last()]");
	By ruletypelocator=By.xpath("//*[contains(@aria-label,'Select Type')][last()]");
	By rulenumberofdayslocator=By.xpath("(//*[contains(@aria-label,'Days')])[last()]");
	By ruledeleteonexpirelocator=By.xpath("(//*[contains(text(),' Delete Upon Expiry ')])[last()]/../input");
	By defaultrulecheckbox=By.xpath("(//*[contains(@id,'isDefault0')])");
	By savechangesbuttonlocator=By.xpath("(//*[contains(text(),'Save Changes')]/..)[2]");
	By okpopupbuttonlocator=By.xpath("//*[contains(@class,'modal-footer')]/button[3]");
	By deletelastrulelocator=By.xpath("(//*[contains(@aria-label,'Remove')])[last()]");
	By removelocator=By.xpath("//*[contains(@class,'btn btn-transparent btn-sm')]");
	By cancellocator=By.xpath("(//*[contains(@class,'btn btn-white')]/..)[2]");
	
	public AvengerExpirationManagementPage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		this.customReport= customReport;
		this.basePage = basePage;
	}
	
	public void clickAddExpiryRule()
	{
		if(getAttribute(rulenamelocator,"value").length()!=0)
		{
		click(addexpiryrulelocator);
		logger.info("Clicked on Add expiry Rule Button");
		}
		}
	
	public void createRule(String rulename,String ruletype,String days)
	{
		enterText(rulenamelocator, rulename);
		selectValuefromDropDown(ruletypelocator, ruletype);
	 	enterText(rulenumberofdayslocator, days);
	    logger.info("Rule has been added to the system");	
	}
	
	public void clickSaveChanges()
	{
		click(savechangesbuttonlocator);
	    logger.info("Clicked on Save Changes button");
	}
	
	public void clickOkPopupButton()
	{
		if(isDisplayedWithoutException(okpopupbuttonlocator))
			click(okpopupbuttonlocator);
	}
	
	public void deleteLastRule()
	{
		click(deletelastrulelocator);
		logger.info("Delete last rule locator");
	}
 
 public String verifyDuplicateRuleNamemsg()
 {
	 By duplicaterulenamemsg=By.xpath("//*[contains(@class,'alert-danger')]");
	 return getText(duplicaterulenamemsg);
 }
 
  public ArrayList<String> getAllruleTypeList()
  {
	  return getDropdownValue(ruletypelocator);
  }

  public ArrayList<String> getAllruleTypeDaysDisabled()
  {
	  ArrayList<String> disabledlist= new ArrayList<String>();
	  disabledlist.add(getAttribute(ruletypelocator, "class"));
	  disabledlist.add(getAttribute(rulenumberofdayslocator, "class"));
	  disabledlist.add(getAttribute(rulenamelocator, "value"));
      return disabledlist;  
  }

  public void click_deleteOnExpiry()
  {
	  click(ruledeleteonexpirelocator);
	  logger.info("Clicked on delete expiry rule");
  }
  
  public void click_default()
  {
	  click(defaultrulecheckbox);
	  logger.info("Clicked on default Rule Checkbox");
  }
  
  public void enterRule(String rulename)
	{
		enterText(rulenamelocator, rulename);
	    logger.info("Rule name has been added to the system"+rulename);	
	}
  public void deleteexpirationrule() {
	    if(getAttribute(rulenamelocator, "value").length()!=0) {
	          if (elements(removelocator)>0) {
	        	  {
	              for(int i=0; i<=elements(removelocator);i++) {
	            	  if(getAttribute(rulenamelocator, "value").length()!=0) {
	                 click(removelocator);
	                 click(savechangesbuttonlocator);
	                 click(okpopupbuttonlocator);
	              }
	  }
	  }
	}
	}}
}
