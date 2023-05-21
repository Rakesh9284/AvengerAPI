package com.vbrick.avenger.pageobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerHomePropertyPage;
import com.vbrick.avenger.ObjProperty.CreateChildAccountPropertyPage;
import com.vbrick.avenger.dao.CreateChildAccountBeansPage;
import com.vbrick.avenger.funUtil.DateTime;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerCreateChildAccountPage extends WebElements {
	private WebDriver driver;
	private BasePage basePage;

	private static Logger logger = Logger.getLogger(AvengerCreateChildAccountPage.class);
	By accountname = By.name(CreateChildAccountPropertyPage.createchildAccount_accountname.getProperty());
	By accounthostname=By.name(CreateChildAccountPropertyPage.createchildAccount_accounthostname.getProperty());
	By firstname = By.xpath(CreateChildAccountPropertyPage.createchildAccount_firstname.getProperty());
	By lastname = By.name(CreateChildAccountPropertyPage.createchildAccount_lastname.getProperty());
	By contactemail = By.name(CreateChildAccountPropertyPage.createchildAccount_contactemail.getProperty());
	By addressline1 = By.xpath(CreateChildAccountPropertyPage.createchildAccount_addressline1.getProperty());
	By addressline2 = By.xpath(CreateChildAccountPropertyPage.createchildAccount_addressline2.getProperty());
	By country =By.xpath(CreateChildAccountPropertyPage.createchildAccount_country.getProperty());
	By state = By.xpath(CreateChildAccountPropertyPage.createchildAccount_state.getProperty());
	By city = By.xpath(CreateChildAccountPropertyPage.createchildAccount_city.getProperty());
	By postalcode =By.xpath(CreateChildAccountPropertyPage.createchildAccount_postalcode.getProperty());
	By phoneno = By.xpath(CreateChildAccountPropertyPage.createchildAccount_phoneno.getProperty());
	By createbuttonlocator = By.xpath(CreateChildAccountPropertyPage.createchildAccount_createbuttonlocator.getProperty());
	By addaccountbuttonlocator = By.xpath(CreateChildAccountPropertyPage.createchildAccount_addaccountlocator.getProperty());
	By accountinformation=By.xpath(CreateChildAccountPropertyPage.createchildAccount_accountinformation.getProperty());
	By accounthostnameerrortext=By.xpath(CreateChildAccountPropertyPage.createchildAccount_hostnameerror.getProperty());
	By accountnameerrortext=By.xpath(CreateChildAccountPropertyPage.createchildAccount_accountnameerror.getProperty());
	By lastnameerrortext=By.xpath(CreateChildAccountPropertyPage.createchildAccount_lastnameerror.getProperty());
	By emailerrortext=By.xpath(CreateChildAccountPropertyPage.createchildAccount_emailerror.getProperty());
	By mediasettings=By.xpath(AvengerHomePropertyPage.homepg_mediasettingslink.getProperty());	
	By preferredlanguage=By.xpath(CreateChildAccountPropertyPage.createchildAccount_preferredlanguage.getProperty());
	By savebuttonlocator=By.xpath(CreateChildAccountPropertyPage.createchildAccount_savebuttonlocator.getProperty());
	By cancelbuttonlocator=By.xpath(CreateChildAccountPropertyPage.createchildAccount_cancelbuttonlocator.getProperty());   
	By maximumactiveusers=By.name(CreateChildAccountPropertyPage.createchildAccount_maximumactiveuserslocator.getProperty());
	By billingaddresscheckbox=By.xpath(CreateChildAccountPropertyPage.createchildAccount_billingaddresscheckbox.getProperty());
	By billingfirstnametext=By.xpath(CreateChildAccountPropertyPage.createchildAccount_billinginfofirstname.getProperty());
	By billinglastnametext=By.xpath(CreateChildAccountPropertyPage.createchildAccount_billinginfolastname.getProperty());
	By billingcontactemailtext=By.xpath(CreateChildAccountPropertyPage.createchildAccount_billinginfocontactemail.getProperty());
	By billingaddressline1text=By.xpath(CreateChildAccountPropertyPage.createchildAccount_billinginfoaadressline1.getProperty());
	By billingaddressline2text=By.xpath(CreateChildAccountPropertyPage.createchildAccount_billinginfoaadressline2.getProperty());
	By billingcountry=By.xpath(CreateChildAccountPropertyPage.createchildAccount_billinginfocountry.getProperty());
	By billingstate=By.xpath(CreateChildAccountPropertyPage.createchildAccount_billinginfostate.getProperty());
	By billingcity=By.xpath(CreateChildAccountPropertyPage.createchildAccount_billinginfocity.getProperty());
	By billingpostalcode=By.xpath(CreateChildAccountPropertyPage.createchildAccount_billinginfopostalcode.getProperty());
	By billingphonenumber=By.xpath(CreateChildAccountPropertyPage.createchildAccount_billinginfophonenumber.getProperty());
	By footersavebutton=By.xpath(CreateChildAccountPropertyPage.createchildAccount_billinginfofootersavebutton.getProperty());
	By Licensedtable=By.xpath(CreateChildAccountPropertyPage.createchildAccount_Licensedhoursetable.getProperty());
	By allocatingbutton=By.xpath(CreateChildAccountPropertyPage.createchildAccount_allocatinghoursbutton.getProperty());
	By allocatingpopup=By.xpath(CreateChildAccountPropertyPage.createchildAccount_allocatingpopup.getProperty());
	By expiredatetextbox=By.xpath(CreateChildAccountPropertyPage.createchildAccount_allocatingexpiredatetextbox.getProperty());
	By allocatingpopupsavebutton=By.xpath(CreateChildAccountPropertyPage.createchildAccount_allocatingpoupsavebutton.getProperty());
	By allocatinghoursfrom=By.name(CreateChildAccountPropertyPage.createchildAccount_allocatinghoursfrom.getProperty());
	By allocatinghoursto=By.name(CreateChildAccountPropertyPage.createchildAccount_allocatinghoursto.getProperty());
	By timezonelocator=By.name(CreateChildAccountPropertyPage.createchildAccount_timezone.getProperty());
	By licenseCountlocator=By.xpath(CreateChildAccountPropertyPage.createchildAccount_licensecount.getProperty());
	By licencetypehourslocator=By.xpath("(//*[contains(@ng-model,'account.licenseType')])[2]");
	By licencetypeuserslocator=By.xpath("(//*[contains(@ng-model,'account.licenseType')])[1]");
	By licenceusercountlocator=By.xpath("//*[@name='licenseCount']");
	
	
	private CustomReport customReport;

	public AvengerCreateChildAccountPage(WebDriver driver,CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver=driver;
		this.customReport = customReport;
		this.basePage = basePage;
	}


	public void addChildAccount(CreateChildAccountBeansPage accountBeansPage)
	{
		enterText(accountname, accountBeansPage.getAccountname());
		logger.info("Account Name is"+accountBeansPage.getAccountname());
		enterText(accounthostname,  accountBeansPage.getAccounthostname());
		enterText(maximumactiveusers,  accountBeansPage.getMaximumactiveusers());
		enterText(firstname,  accountBeansPage.getFirstname());
		enterText(lastname, accountBeansPage.getLastname());
		enterText(contactemail, accountBeansPage.getContactemail());
		enterText(addressline1, accountBeansPage.getAddressline1());
		enterText(addressline2, accountBeansPage.getAddressline2());
		enterText(state, accountBeansPage.getState());
		enterText(city, accountBeansPage.getCity());
		enterText(postalcode, accountBeansPage.getPostalcode());
		enterText(phoneno, accountBeansPage.getPhoneno());
		selectValuefromDropDown(country,  accountBeansPage.getCountry());
		selectValuefromDropDown(preferredlanguage,  accountBeansPage.getPreferredlanguage());
	}

	public AvengerAccountsPage click_CreateButton(CreateChildAccountBeansPage beanpage)
	{
		submit(createbuttonlocator);
		By createdaccount = By.xpath("//tr[contains(@ng-class,'[account.status]')]/th[1]//*[contains(text(),'"+beanpage.getAccountname()+"')]");
        pause(30000);
		By accountstablocator = By.xpath(AvengerHomePropertyPage.homepg_accountstablocator.getProperty());
		click(accountstablocator);	
		waitForElementPresent(createdaccount); 
		customReport.reporter("Child Account Created Successfully","");
		return basePage.avengerAccountsPage(driver,  customReport, basePage); 
	}




	public String addChildAccount_deletefield(CreateChildAccountBeansPage accountBeansPage,By locatortodelete, By locatorxpath)
	{
		enterText(accountname, accountBeansPage.getAccountname());
		enterText(accounthostname,  accountBeansPage.getAccounthostname());
		enterText(firstname,  accountBeansPage.getFirstname());
		enterText(lastname, accountBeansPage.getLastname());
		enterText(contactemail, accountBeansPage.getContactemail());
		enterText(addressline1, accountBeansPage.getAddressline1());
		enterText(addressline2, accountBeansPage.getAddressline2());
		enterText(state, accountBeansPage.getState());
		enterText(city, accountBeansPage.getCity());
		enterText(postalcode, accountBeansPage.getPostalcode());
		enterText(phoneno, accountBeansPage.getPhoneno());
		selectValuefromDropDown(country,  accountBeansPage.getCountry());
		logger.info("Length  is"+ getAttribute(locatortodelete,"value").length());
		clearWebElementTextusingbackspace(locatortodelete,getAttribute(locatortodelete,"value").length());
		return getText(locatorxpath); 
	}

	public void addChildAccount_deletefieldvalues(CreateChildAccountBeansPage accountBeansPage,By locatortodelete)
	{

		enterText(accountname, accountBeansPage.getAccountname());
		enterText(accounthostname,  accountBeansPage.getAccounthostname());
		enterText(firstname,  accountBeansPage.getFirstname());
		enterText(lastname, accountBeansPage.getLastname());
		enterText(contactemail, accountBeansPage.getContactemail());
		enterText(addressline1, accountBeansPage.getAddressline1());
		enterText(addressline2, accountBeansPage.getAddressline2());
		enterText(state, accountBeansPage.getState());
		enterText(city, accountBeansPage.getCity());
		enterText(postalcode, accountBeansPage.getPostalcode());
		enterText(phoneno, accountBeansPage.getPhoneno());
		selectValuefromDropDown(country,  accountBeansPage.getCountry());
		logger.info("Length  is"+ getAttribute(locatortodelete,"value").length());
		clearWebElementTextusingbackspace(locatortodelete,getAttribute(locatortodelete,"value").length());
		click(createbuttonlocator);      
	}

	public void checkLicensedGuestAccessCheckBox(String guestaccess){
		/*By guestaccesscheckbox = By.xpath("//*[contains(text(),'"+guestaccess+"')]/../div//*[contains(@class,'form-control')]");
		check_Checkbox(guestaccesscheckbox);
		logger.info("License Guest Access check box is cheked Successfully");
	*/}
	
	public ArrayList<String> get_countryDropDownlist()
	{
		return getDropdownValue(country);
	}

	public boolean verify_AccountNameandHostNameUneditable()
	{
		boolean sflag=false;
		isEnabled(accountname);
		isEnabled(accounthostname);
		logger.info("Account Name is Enabled"+isEnabled(accountname));
		logger.info("Account Host Name is Enabled"+isEnabled(accounthostname));
		if(isEnabled(accountname)&& isEnabled(accounthostname)==true)
		{
			customReport.reporter("Account Name and Account Host Name are in Enable Mode","");
			sflag=true;
		}
		else
			customReport.reporter("Account Name and Account Host Name are in Disable Mode","");
		return sflag;

	}

	public String verify_accountHostNameError(CreateChildAccountBeansPage accountBeansPage)
	{
		By hostnameerrortext=By.xpath("//*[contains(text(),'(e.g. videoportal.mywebsite.com)')]");
		enterText(accountname, accountBeansPage.getAccountname());
		enterText(accounthostname,  accountBeansPage.getAccounthostname());
		return getText(hostnameerrortext);
	}

	public Map<String, String> get_allChildAccountData()
	{
		Map<String, String> childaccountdata=new HashMap<String, String>();

		childaccountdata.put("accountname",getAttribute(accountname, "value"));
		childaccountdata.put("accounthostname",getAttribute(accounthostname, "value"));
		childaccountdata.put("firstname",getAttribute(firstname, "value"));
		childaccountdata.put("lastname",getAttribute(lastname, "value"));
		childaccountdata.put("contactemail",getAttribute(contactemail, "value"));
		childaccountdata.put("addressline1",getAttribute(addressline1, "value"));
		childaccountdata.put("addressline2",getAttribute(addressline2, "value"));
		childaccountdata.put("country",getDropDownSelectedOption(country));
		childaccountdata.put("state",getAttribute(state, "value"));
		childaccountdata.put("city",getAttribute(city, "value"));
		childaccountdata.put("postalcode",getAttribute(postalcode, "value"));
		childaccountdata.put("phonenumber",getAttribute(phoneno, "value"));
		childaccountdata.put("preferredlanguage",getDropDownSelectedOption(preferredlanguage));
		return childaccountdata;
	}

	public void EditChildAccount(CreateChildAccountBeansPage accountBeansPage)
	{
		enterText(accountname, accountBeansPage.getAccountname());
		enterText(accounthostname,  accountBeansPage.getAccounthostname());
		enterText(firstname,  accountBeansPage.getFirstname());
		enterText(lastname, accountBeansPage.getLastname());
		enterText(contactemail, accountBeansPage.getContactemail());
		enterText(addressline1, accountBeansPage.getAddressline1());
		enterText(addressline2, accountBeansPage.getAddressline2());
		enterText(state, accountBeansPage.getState());
		enterText(city, accountBeansPage.getCity());
		enterText(postalcode, accountBeansPage.getPostalcode());
		enterText(phoneno, accountBeansPage.getPhoneno());
		selectValuefromDropDown(country,  accountBeansPage.getCountry());
		selectValuefromDropDown(preferredlanguage,  accountBeansPage.getPreferredlanguage());
	}

	public AvengerAccountsPage click_SaveButton()
	{
		submit(savebuttonlocator);
		customReport.reporter("Saving  Child Account Created  Data ","");
		waitForElementPresent(mediasettings); 
        return basePage.avengerAccountsPage(driver,  customReport, basePage);
	}

	public void click_CancelButton()
	{
		click(cancelbuttonlocator);

	}

	public boolean verifyChildAccountPageLoad()
	{
		return isDisplayed(accounthostname);
	}

	public String ErrorMessageDuplicateAccount(){
		submit(createbuttonlocator);
		By accountnameerrorlocator=By.xpath(".//*[@validation='accountNameInUse']");
		return getText(accountnameerrorlocator);

	}


	public Map<String, String> Verify_Labels() {
		
		By accountbuttonlocator=By.xpath("//div[@class='combined-toolbar responsive']/div/a");
		
		Map<String,String> labelsmap=new HashMap<String, String>();
		
		labelsmap.put("Account", getText(accountbuttonlocator));
		
		return labelsmap;
	}
	
	public void checkcheckboxforbillindaddress(){
		check_Checkbox(billingaddresscheckbox);
	}
	
	public void add_BillingInformation(CreateChildAccountBeansPage billinginfobeanpage){
		
		enterText(billingfirstnametext, billinginfobeanpage.getFirstname());
		enterText(billinglastnametext,  billinginfobeanpage.getLastname());
		enterText(billingcontactemailtext, billinginfobeanpage.getContactemail());
		enterText(billingaddressline1text, billinginfobeanpage.getAddressline1());
		enterText(billingaddressline2text, billinginfobeanpage.getAddressline2());
		selectValuefromDropDown(billingcountry,  billinginfobeanpage.getCountry());
		enterText(billingstate, billinginfobeanpage.getState());
		enterText(billingcity, billinginfobeanpage.getCity());
		enterText(billingpostalcode, billinginfobeanpage.getPostalcode());
		enterText(billingphonenumber, billinginfobeanpage.getPhoneno());
		
	}
	
	public Map<String, String> verify_AllocatingViewingHoursPopUp(){
		Map<String,String> allocatingpopupdetails=new HashMap<String, String>();
		allocatingpopupdetails.put("allocatinghoursfrom",getCurrentValueFromDropdown(allocatinghoursfrom).trim());
		allocatingpopupdetails.put("hoursallocated", getText(allocatinghoursto));
		allocatingpopupdetails.put("expires", getAttribute(expiredatetextbox,"value"));
		return allocatingpopupdetails;
	}
	
	public Map<String, String> verify_LicensedHoursTable(String date){
    		Map<String,String> licensedhourstable=new HashMap<String, String>();
    		By licensedhoursintable=By.xpath("//*[contains(text(),'"+date+"')]/../td[1]");
    		By expirydateintable=By.xpath("//*[contains(text(),'"+date+"')]");
    		By usedtimeintable=By.xpath("//*[contains(text(),'"+date+"')]/../td[3]");
    		licensedhourstable.put("licensedhours", getText(licensedhoursintable));
	    	licensedhourstable.put("expiresdate", getText(expirydateintable));
		    licensedhourstable.put("usedtime", getText(usedtimeintable));
		return licensedhourstable;
	}
	
	public Map<String, String> get_LicensedHoursRowData(int rownum)
	{
		pause(5000);
		Map<String,String> licensedhourstable=new HashMap<String, String>();
		By licensedhours=By.xpath("//*[contains(text(),"+rownum+")]/../td[1]");
    	By expiresdate=By.xpath("//*[contains(text(),"+rownum+")]/../td[2]");
    	By usedhours=By.xpath("//*[contains(text(),"+rownum+")]/../td[3]");
    	licensedhourstable.put("licensedhours", getText(licensedhours));
    	licensedhourstable.put("expiresdate", getText(expiresdate));
    	licensedhourstable.put("usedhours", getText(usedhours));
        return 	licensedhourstable;	
	}
	
	public Map<String, Boolean> verify_LicensedHoursTableEditable(String date){
		By licensedhoursintable=By.xpath("//*[contains(text(),'"+date+"')]/../td[1]");
		By expirydateintable=By.xpath("//*[contains(text(),'"+date+"')]");
		By usedtimeintable=By.xpath("//*[contains(text(),'"+date+"')]/../td[3]");
		Map<String,Boolean> editlicensedhourstable=new HashMap<String, Boolean>();
		editlicensedhourstable.put("licensedhours", fieldEditable(licensedhoursintable));
		editlicensedhourstable.put("expiresdate", fieldEditable(expirydateintable));
		editlicensedhourstable.put("usedtime", fieldEditable(usedtimeintable));
		return editlicensedhourstable;
	}

	public String getselectedTimeZone(){
		return getDropDownSelectedOption(timezonelocator);
	}
	
	public String getLicenseCount(){
		logger.info(getAttribute(licenseCountlocator));
		return getAttribute(licenseCountlocator);
	}
	
	public void clickLicenceTypeHoursLocator() {
		click(licencetypehourslocator);
		logger.info("Licence Type Hours button clicked");
	}
	
	public void clickLicenceTypeUsers() {
		click(licencetypeuserslocator);
		logger.info("Licence Type Hours button clicked");
	}
	
	public String enterLicensedUserCount(String value)
	{
		String usercountvalue= getAttribute(licenceusercountlocator, "value");
		enterText(licenceusercountlocator, value);
		return usercountvalue;
	}
	
	public boolean verify_Allocatingviewinghoursbutton(){
		return isDisplayedWithoutException(allocatingbutton);
	}
	
	public String verify_userlicenseEnabledordisabled()
	{
		return getAttribute(licencetypeuserslocator, "disabled");
	}
	
}
