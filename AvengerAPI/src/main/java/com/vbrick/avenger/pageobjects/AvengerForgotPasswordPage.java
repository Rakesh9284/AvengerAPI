package com.vbrick.avenger.pageobjects;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerForgotPasswordPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerLoginPropertyPage;
import com.vbrick.avenger.dao.AddUserBeanPage;
import com.vbrick.avenger.dao.AvengerForgotPasswordBeanPage;
import com.vbrick.avenger.dao.CreatePasswordBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerForgotPasswordPage extends WebElements {
	private WebDriver driver;
	private CustomReport customReport;
	
	private BasePage basePage;
	
	
	By securityanswerlocator=By.xpath(AvengerForgotPasswordPropertyPage.avengerforgotpasswordpage_securityanswerlocator.getProperty());
	By passwordlocator=By.xpath(AvengerForgotPasswordPropertyPage.avengerforgotpasswordpage_passwordlocator.getProperty());
	By confirmpasswordlocator=By.xpath(AvengerForgotPasswordPropertyPage.avengerforgotpasswordpage_confirmpasswordlocator.getProperty());
	By submitbuttonlocator=By.xpath(AvengerForgotPasswordPropertyPage.avengerforgotpasswordpage_submitbuttonlocator.getProperty());
	By securityquestionlabel=By.xpath(AvengerForgotPasswordPropertyPage.avengerforgotpasswordpage_securityquestiontext.getProperty());
	By securityanswererrortext=By.xpath(AvengerForgotPasswordPropertyPage.avengerforgotpasswordpage_securityanswererrortext.getProperty());
    By forgotpassworduserNameLocator=By.name(AvengerLoginPropertyPage.forgotpg_forgotpassworduserNameLocator.getProperty());
	By forgotpg_SubmitbuttonLocator=By.xpath(AvengerForgotPasswordPropertyPage.forgotpg_SubmitbuttonLocator.getProperty());
	By forgot_emailSendMessagelocator=By.xpath(AvengerForgotPasswordPropertyPage.forgotpg_emailSendMessagelocator.getProperty());
	By forgot_SignInlocator=By.xpath(AvengerForgotPasswordPropertyPage.forgot_SignInlocator.getProperty());
	By usernameLocator = By.xpath(AvengerLoginPropertyPage.loginpg_usernameLocator.getProperty());
	By securityanswerblankerrortextlocator=By.xpath(AvengerForgotPasswordPropertyPage.securityanswerblankerrortextlocator.getProperty());
	

	

	
	private static Logger logger = Logger.getLogger(AvengerForgotPasswordPage.class);

	public AvengerForgotPasswordPage(WebDriver driver,  
			CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		
		this.customReport = customReport;
		this.basePage=basePage;
		logger.info("Avenger Forgot Password Page");
	}

	
	
	
	public String enter_forgotPassworddatawithoutanswer(CreatePasswordBeanPage createpasswordbeanpage,AvengerForgotPasswordBeanPage avengerforgotpasswordbeanpage)
	{
		pause(5000);
		
		logger.info("value for type your answer is "+createpasswordbeanpage.getTypeyouranswer());
		waitForElementPresent(securityanswerlocator);
		waitForElementPresent(confirmpasswordlocator);
		enterText(passwordlocator, avengerforgotpasswordbeanpage.getPassword());
		enterText(confirmpasswordlocator, avengerforgotpasswordbeanpage.getConfirmpassword() );
		enterText(securityanswerlocator,  createpasswordbeanpage.getTypeyouranswer());
		clearWebElementTextusingbackspace(securityanswerlocator,  createpasswordbeanpage.getTypeyouranswer().length());
		createpasswordbeanpage.setPassword(avengerforgotpasswordbeanpage.getPassword());
		customReport.reporter("Forgot Password Data has been entered with secuirty answer left blank","");
		return getText(securityanswerblankerrortextlocator);
	}
	
	public void enter_forgotPassworddata(CreatePasswordBeanPage createpasswordbeanpage,AvengerForgotPasswordBeanPage avengerforgotpasswordbeanpage)
	{
		logger.info("value for type your answer is "+createpasswordbeanpage.getTypeyouranswer());
		waitForElementPresent(securityanswerlocator);
		waitForElementPresent(confirmpasswordlocator);
		pause(5000);
		waitForElementPresent(securityanswerlocator);
		waitForElementPresent(confirmpasswordlocator);
		enterText(securityanswerlocator,  createpasswordbeanpage.getTypeyouranswer());
		enterText(passwordlocator, avengerforgotpasswordbeanpage.getPassword());
		enterText(confirmpasswordlocator, avengerforgotpasswordbeanpage.getConfirmpassword() );
		click(submitbuttonlocator);
		pause(5000);
		customReport.reporter("Forgot Password Data has been entered","");
		
	}
	
	public boolean verifySecurityQuestionText()
	{
		boolean flag=false;
		if(elements(securityquestionlabel)>0){
			customReport.reporter("Security Question  Text  is  available", "");
			flag=true;
	}
		return flag;
	}
	
	
	public boolean verifyPasswordLocator()
	{
		boolean flag=false;
		if(elements(passwordlocator)>0){
			customReport.reporter("Password Locator Text Box is  available", "");
			flag=true;
	}
		return flag;
	}
	
	public Boolean verify_SecurityQuestion(String securityquestion)
	{
		Boolean sflag=false; 
		By securityquestionvalue=By.xpath("//*[contains(text(),'"+securityquestion+"')]");
		   if(waitForElementPresent(securityquestionvalue)==true)
		   {
			     sflag=true;
			     logger.info("Security Question is"+getText(securityquestionvalue));
		   }
		   else
		   {
			   sflag=false;
		   }
			   return sflag;
	}
	
	public String get_securityquestionerrortext()
	{
		logger.info("Security Question Error Text is"+getText(securityanswererrortext));
		return " "+getText(securityanswererrortext);
	}
	
	public String get_securityQuestionLabel()
	{
		logger.info("Value in Security Question Label is"+getText(securityquestionlabel));
		return getText(securityquestionlabel);
	}

	public void enterUsername_ClickSubmit(AddUserBeanPage adduserbeanpage) {
		waitForElementPresent(forgotpassworduserNameLocator);
		pause(5000);
		click(forgotpassworduserNameLocator);
		enterText(forgotpassworduserNameLocator,  adduserbeanpage.getUsername());
		logger.info("User name entered in forgot password page");
		customReport.reporter("User name entered in forgotpassword page", "");
		waitForElementPresent(forgotpg_SubmitbuttonLocator);
		click(forgotpg_SubmitbuttonLocator);
		pause(5000);
	}
	
	public String verifyEmailSendMessage()
	{
		logger.info("The Verify Email Text"+getText(forgot_emailSendMessagelocator));
		return " "+getText(forgot_emailSendMessagelocator);
		
	}
	
	public void click_SignIn()
	{
		waitForElementPresent(forgot_SignInlocator);
		click(forgot_SignInlocator);
		logger.info("Sign Button in forgot password page Cliked");
	}
	public String get_passwordComplexityerror(CreatePasswordBeanPage createpasswordbeanpage,AvengerForgotPasswordBeanPage avengerforgotpasswordbeanpage,String label)
	{
		By passwordcomplexityerrorlocator=By.xpath("//*[contains(text(),'"+label+"')]");
		waitForElementPresent(securityanswerlocator);
		waitForElementPresent(confirmpasswordlocator);
		pause(4000);
		enterText(passwordlocator, avengerforgotpasswordbeanpage.getPassword());
		enterText(confirmpasswordlocator, avengerforgotpasswordbeanpage.getConfirmpassword());
		enterText(securityanswerlocator,  createpasswordbeanpage.getTypeyouranswer());
		createpasswordbeanpage.setPassword(avengerforgotpasswordbeanpage.getPassword());
	 	logger.info("Error Text is"+getText(passwordcomplexityerrorlocator));
		return getText(passwordcomplexityerrorlocator);
	}

	public String verify_label(String labelname,AvengerForgotPasswordBeanPage avengerforgotpasswordbeanpage)
	{
		pause(5000);
		By passwordlocator=By.xpath("//input[@name='password']");
		enterText(passwordlocator, avengerforgotpasswordbeanpage.getPassword() );
		By errormessagelocator=By.xpath("//*[contains(text(),'"+labelname+"')]");
		pause(2000);
		return getText(errormessagelocator);
	
	}
	
	
}
