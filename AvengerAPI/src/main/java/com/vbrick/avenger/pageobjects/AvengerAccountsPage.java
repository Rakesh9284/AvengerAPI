package com.vbrick.avenger.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerAccountsPropertyPage;
import com.vbrick.avenger.ObjProperty.CreateChildAccountPropertyPage;
import com.vbrick.avenger.dao.CreateChildAccountBeansPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerAccountsPage extends WebElements{

	private WebDriver driver;
	
	CustomReport customReport;
	private BasePage basePage;
	private static Logger logger = Logger.getLogger(AvengerAccountsPage.class);

	public AvengerAccountsPage(WebDriver driver,CustomReport customReport, BasePage basePage) {
		super(driver);
		this.driver = driver;
		this.customReport= customReport;
		this.basePage=basePage;
	}

	By parentaccountlocator  =By.xpath(AvengerAccountsPropertyPage.avengeraccountspage_parentaccountlocator.getProperty()); 
	By editsettingbutton=By.xpath(AvengerAccountsPropertyPage.avengeraccountspage_editsettingbutton.getProperty());
	By accountname = By.name(CreateChildAccountPropertyPage.createchildAccount_accountname.getProperty());
	By allaccountsbreadcrumb = By.xpath(AvengerAccountsPropertyPage.avengeraccountspage_allaccountsbreadcrumb.getProperty());
	By billingtab = By.xpath(AvengerAccountsPropertyPage.avengeraccountspage_billingtab.getProperty());
	By parentaccount = By.xpath(AvengerAccountsPropertyPage.avengeraccountspage_parentaccountlocator.getProperty());
	By delteaccountconformationmessage = By.xpath(AvengerAccountsPropertyPage.avengeraccountspage_deleteaccountconformationtext.getProperty());
	By deleteaccounterrormessage = By.xpath(AvengerAccountsPropertyPage.avengeraccountspage_deleteaccounterrormessage.getProperty());
	By deletesubaccounterrormsg = By.xpath(AvengerAccountsPropertyPage.avengeraccountspage_deletesubaccounterrormessage.getProperty());
	By acceptsubaccounterrormsgbutton = By.xpath(AvengerAccountsPropertyPage.avengeraccountspage_Okbuttonforsubaccounterrormsg.getProperty());
	
	public  AvengerCreateChildAccountPage click_addchildaccount()
	{
		By  addaccountbuttonlocator = By.xpath(AvengerAccountsPropertyPage.avengeraccountspage_addaccountlocator.getProperty());
		click(addaccountbuttonlocator);
		customReport.reporter("Clicked on Child Account Link","");
		return basePage.avengerCreateChildAccount(driver, customReport, basePage);
	}

	public boolean verify_addchildaccount()
	{
		By  addaccountbuttonlocator = By.xpath(AvengerAccountsPropertyPage.avengeraccountspage_addaccountlocator.getProperty());
		return locatorsVisibilityAsPerRoles(addaccountbuttonlocator);
	}
	
	public String verify_childaccountcreation(CreateChildAccountBeansPage accountBeansPage)
	{
		By newuserlink=By.xpath("//tr[contains(@ng-class,'[account.status]')]/th[1]//*[contains(text(),'"+accountBeansPage.getAccountname()+"')]");
		return getText(newuserlink);
	}

	public AvengerCreateChildAccountPage click_newChildAccountCreation(CreateChildAccountBeansPage accountBeansPage)
	{
		By newuserlink=By.xpath("//tr[contains(@ng-class,'[account.status]')]/th[1]//*[contains(text(),'"+accountBeansPage.getAccountname()+"')]");
		waitForElementPresent(newuserlink);
		customReport.reporter("New Child Account Created Link is Clicked",getText(newuserlink));
		clickUsingSwitch(newuserlink);
		By userAccount=By.xpath("//a[contains(text(),'"+accountBeansPage.getAccountname()+"')]");
		waitForElementPresent(userAccount);
		logger.info("Account created and has been selected");
		return basePage.avengerCreateChildAccount(driver, customReport, basePage);
		
	}
	
	public String get_allAccountsNavigation()
	{
		logger.info("All Accounts Values are"+getText(allaccountsbreadcrumb));
		click(allaccountsbreadcrumb);
		return getText(parentaccountlocator);
	}

	public String get_AccountStatus(String value)
	{
		By accountstatus=By.xpath("//*[contains(text(),'"+value+"')]/../../th[2]");
		logger.info("Account Status Value is "+getText(accountstatus));
		return getText(accountstatus);
	}
	
	public void click_accountEditButton(CreateChildAccountBeansPage accountBeansPage)
	{
		By editbuttonlink=By.xpath("//*[text()='"+accountBeansPage.getAccountname()+"']/../../th[3]/div[1]/a");
		click(editbuttonlink);
		customReport.reporter("Child Account Edit Button is clicked","");
		waitForElementPresent(accountname);
	}
	
	public AvengerCreateChildAccountPage click_EditsettingsButton()
	{
		click(editsettingbutton);
		logger.info("Clicked on Account Edit Button");
		waitForElementPresent(accountname);
		return basePage.avengerCreateChildAccount(driver, customReport, basePage);
	}

	public void clickbillingtab(){
		pause(5000);
		click(billingtab);
		logger.info("Clicked on edit button for account");
	}
	
	public void clickaccountdeletebutton(CreateChildAccountBeansPage accountBeansPage){
		
		By accountdeletebutton=By.xpath("//*[@class='status']//*[contains(text(),'"+accountBeansPage.getAccountname()+"')]/../..//*[contains(@class,'glyphicons bin')]/..");
		waitForElement(accountdeletebutton);
		logger.info("Delete button is visible for account");
		click(accountdeletebutton);
		logger.info("Clicked on Account Delete Button");
	}
	
	public void clickAccountDropDown(CreateChildAccountBeansPage accountBeansPage){
		By accountdropdown = By.xpath("//*[contains(text(),'"+accountBeansPage.getAccountname()+"')]//*[@class='caret']");
		waitForElement(accountdropdown);
		logger.info("Account Dropdown is visible"+accountBeansPage.getAccountname());
		clickUsingSwitch(accountdropdown);
		logger.info("Clicked on account dropdown locator");
		pause(3000);
	}
	
	public void clickrootaccountfromdropdown(String accountname){
		pause(3000);
		By rootqaaccountdropdown = By.xpath("//*[contains(text(),'"+accountname+"')]//*[@class='caret']");
		waitForElement(parentaccount);
		logger.info("Rootqa is visible");
		click(parentaccount);
		logger.info("Clicked on rootqa account");
		waitForElement(rootqaaccountdropdown);
		logger.info("RootQaAcount dropdown is Visible");
	}
	
	public void clickaccountdeleteconformationalter(String option){
		if (option.equalsIgnoreCase("close")){
			By acceptbutton = By.xpath("//*[@class='modal-footer']//*[@type='submit']");
			click(acceptbutton);
			logger.info("Clicked on accept alert button");
		}else
		{
			By cancelbutton=By.xpath("//*[@class='modal-footer']//*[contains(@ng-show,'cancelText')]");
			click(cancelbutton);
			logger.info("Clicked on cancel alert button");
		}
	
	}
	
	public String verifyDelteAccountConformationAlter(){
		return getText(delteaccountconformationmessage);
	}
	
	public String verifydeleteaccounterrormessage(){
		return getText(deleteaccounterrormessage);
	}
	
	public String verifyerrormessagefordeletesubaccount(){
		return getText(deletesubaccounterrormsg);
	}
	
	public void acceptsubaccounterrormsg(){
		waitForElement(acceptsubaccounterrormsgbutton);
		logger.info("OK button is visible on sub account error message");
		click(acceptsubaccounterrormsgbutton);
		logger.info("Clicked on ok button for subaccount error message");
	}
	
	public String verifydeletedaccount(CreateChildAccountBeansPage accountBeansPage){
		
		By accountname=By.xpath("//tr[contains(@ng-class,'[account.status]')]/th[1]//*[contains(text(),'"+accountBeansPage.getAccountname()+"')]");
		logger.info("Account name :"+accountBeansPage.getAccountname());
		return String.valueOf(elements(accountname));
	}
	
	public void selectRootQAFromDropdown(CreateChildAccountBeansPage accountBeansPage,String accountname){
		clickAccountDropDown(accountBeansPage);
		clickrootaccountfromdropdown(accountname);
	}
	
	public int verify_childaccountpresent(CreateChildAccountBeansPage accountBeansPage){
		By newuserlink=By.xpath("//tr[contains(@ng-class,'[account.status]')]/th[1]//*[contains(text(),'"+accountBeansPage.getAccountname()+"')]");
		return	elements(newuserlink);
	}
	
}
