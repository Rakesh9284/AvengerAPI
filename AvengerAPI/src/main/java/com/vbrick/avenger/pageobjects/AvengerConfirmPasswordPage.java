package com.vbrick.avenger.pageobjects;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerHomePropertyPage;
import com.vbrick.avenger.ObjProperty.CreatePasswordPropertyPage;
import com.vbrick.avenger.dao.CreatePasswordBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;
public class AvengerConfirmPasswordPage extends WebElements{
	
private static Logger logger = Logger.getLogger(AvengerConfirmPasswordPage.class);
    By password=By.name(CreatePasswordPropertyPage.createPassword_password.getProperty());	
    By confirmpassword=By.name(CreatePasswordPropertyPage.createPassword_confirmpassword.getProperty());	
    By securityquestion=By.xpath(CreatePasswordPropertyPage.createPassword_securityquestion.getProperty());	
    By typeyouranswer=By.xpath(CreatePasswordPropertyPage.createPassword_typeyouranswer.getProperty());	
    By submitbuttonlocator=By.xpath(CreatePasswordPropertyPage.createPassword_submitbuttonlocator.getProperty());	
    By createPassword_complexityerrortext=By.xpath(CreatePasswordPropertyPage.createPassword_complexityerrortext.getProperty());
    By passwordlabeltext=By.xpath(CreatePasswordPropertyPage.createPassword_passwordlabeltext.getProperty());
    By confirmpasswordlabeltext=By.xpath(CreatePasswordPropertyPage.createPassword_confirmpasswordlabeltext.getProperty());
    By securityquestionlabeltext=By.xpath(CreatePasswordPropertyPage.createPassword_securityquestionlabeltext.getProperty());
    By typeyouranswerlabeltext=By.xpath(CreatePasswordPropertyPage.createPassword_typeyouranswerlabeltext.getProperty());
    By passworddonotmatcherrortext=By.xpath(CreatePasswordPropertyPage.createPassword_passwordonotmatcherrortext.getProperty());
	By  logoutlocator= By.xpath(AvengerHomePropertyPage.homepg_logoutlocator.getProperty());    
	
   private  WebDriver driver;
	
	private CustomReport customReport;
	private BasePage basePage;
	
	public AvengerConfirmPasswordPage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver=driver;

		this.customReport = customReport;
		this.basePage=basePage;
	}
	
	public List<String> checkdropdownvalues()
	{
		List<String> listvalue= new ArrayList<String>();
		listvalue=getDropdownValue(securityquestion);
		for (String temp : listvalue) {
        logger.info("Security Questions drop down values are"+ temp);
		}
		return listvalue;
	}
	
	public AvengerLoginPage passwordCreation(CreatePasswordBeanPage beanspage) 
	 {
	  By confirmpasswordlocator = By.xpath("//*[contains(@class,'modal-footer')]/button[3]");
	  waitForElementPresent(password);
	  enterText(password, beanspage.getPassword());
	  enterText(confirmpassword,  beanspage.getConfirmpassword());
	  enterText(typeyouranswer,  beanspage.getTypeyouranswer());
	  selectValuefromDropDown(securityquestion,  beanspage.getSecurityquestion());
	  click(submitbuttonlocator);
	  pause(3000);
	  click(confirmpasswordlocator);
	  customReport.reporter("passwordCreated Successfully","");
	  waitForElementPresent(logoutlocator);
	  return basePage.avengerLoginPage(driver,  customReport, basePage);
	 }
	
	public String confirmPasswordasDifferent(CreatePasswordBeanPage beanspage) 
	{
		waitForElementPresent(password);
		enterText(password, beanspage.getPassword());
		enterText(confirmpassword,  beanspage.getConfirmpassword());
		return getText(passworddonotmatcherrortext);
	}
	
	 public boolean submitbuttonisEnable()
     {
    	 boolean sflag=false;
    	 if(isEnabled(submitbuttonlocator)==true)
    	 {
    		sflag=true;
    		 customReport.reporter("Submit button is enabled","");
    	 }
    	 else
    	 {
    		 sflag=false;
    		 customReport.reporter("Submit button is disabled","");
    	 }
		 return sflag;
     }
    
	
     public String getpasswordLabeltext()
     {
		 return getText(passwordlabeltext);
     }
	
     public String submitbuttontext()
     {
    	 logger.info("value of submit button is"+getText(submitbuttonlocator));
    	 return getText(submitbuttonlocator);
     }
     
     public String getconfirmpasswordlabeltext()
     {
		 return getText(confirmpasswordlabeltext);
     }
	 
     public String getsecurityquestionlabeltext()
     {
		 return getText(securityquestionlabeltext);
     }
     
     public String gettypeyouranswerlabeltext()
     {
		 return getText(typeyouranswerlabeltext);
     }
	
	public String password_complexityerrortext(CreatePasswordBeanPage beanspage,String errortext) {
		enterText(password, beanspage.getInvalidpassword());
		By errortextlocator=By.xpath("//*[contains(text(),'"+errortext+"')]");
		pause(2000);
		return " "+getText(errortextlocator);
	}
}
