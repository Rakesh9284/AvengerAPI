package com.vbrick.avenger.pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.RootAccountPropertyPage2;
import com.vbrick.avenger.dao.ManualRootAccountBeanPage2;
import com.vbrick.avenger.dao.RootAccountBeanPage2;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerRootAccountPage2 extends WebElements{
	
	By firstName = By.name(RootAccountPropertyPage2.rootaccountpg2_firstname.getProperty());
	By lastName = By.name(RootAccountPropertyPage2.rootaccountpg2_lastname.getProperty());
	By emailAddress = By.name(RootAccountPropertyPage2.rootaccountpg2_emailaddress.getProperty());
	By addressLine1 = By.xpath(RootAccountPropertyPage2.rootaccountpg2_addressline1.getProperty());
	By addressLine2 = By.xpath(RootAccountPropertyPage2.rootaccountpg2_addressline2.getProperty());
	By country = By.xpath(RootAccountPropertyPage2.rootaccountpg2_country.getProperty());
	By state = By.xpath(RootAccountPropertyPage2.rootaccountpg2_state.getProperty());
	By city =By.xpath(RootAccountPropertyPage2.rootaccountpg2_city.getProperty());
	By postalCode = By.xpath(RootAccountPropertyPage2.rootaccountpg2_postalcode.getProperty());
	By phoneno = By.xpath(RootAccountPropertyPage2.rootaccountpg2_phoneno.getProperty());
	By preferredlanguage=By.xpath(RootAccountPropertyPage2.rootaccountpg2_createrootaccountbuttonlocator.getProperty());
	By createRootAccountbuttonlocator=By.xpath(RootAccountPropertyPage2.rootaccountpg2_createrootaccountbuttonlocator.getProperty());
	
	private WebDriver driver;
	
	private CustomReport customReport;
	private BasePage basePage;
	
	public AvengerRootAccountPage2(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		
		super(driver);
		this.driver=driver;

		this.customReport = customReport;
		this.basePage=basePage;
	}
	
	
	     public void addRootAccount(RootAccountBeanPage2 accountBeansPage)
      {
    	    enterText(firstName, accountBeansPage.getFirstName());
    	    customReport.reporter("Enter text in First Name", accountBeansPage.getFirstName());
			enterText(lastName,  accountBeansPage.getLastName());
    	    customReport.reporter("Enter text in last Name", accountBeansPage.getLastName());

			enterText(emailAddress,  accountBeansPage.getEmailAddress());
    	    customReport.reporter("Enter text in Email Address", accountBeansPage.getEmailAddress());

			enterText(addressLine1, accountBeansPage.getAddressLine1());
    	    customReport.reporter("Enter text in Address 1", accountBeansPage.getAddressLine1());

			enterText(addressLine2, accountBeansPage.getAddressLine2());
    	    customReport.reporter("Enter text in Address 2", accountBeansPage.getAddressLine2());

	        selectValuefromDropDown(country, accountBeansPage.getCountry());
    	    customReport.reporter("Select value in Country", accountBeansPage.getCountry());

			enterText(city, accountBeansPage.getCity());
    	    customReport.reporter("Enter text in City", accountBeansPage.getCity());

			enterText(postalCode, accountBeansPage.getPostalCode());
    	    customReport.reporter("Enter text in Postal Code is", accountBeansPage.getPostalCode());

			enterText(phoneno, accountBeansPage.getPhoneNumber());
    	    customReport.reporter("Enter text in Phone Number is", accountBeansPage.getPhoneNumber());

			 click(createRootAccountbuttonlocator);
             customReport.reporter("Clicked on Root Account Button","");
		 
	  }
      
	     public void addRootAccount(ManualRootAccountBeanPage2 accountBeansPage)
	      {
	    	    enterText(firstName, accountBeansPage.getFirstName());
	    	    customReport.reporter("Enter text in First Name", accountBeansPage.getFirstName());
				enterText(lastName,  accountBeansPage.getLastName());
	    	    customReport.reporter("Enter text in last Name", accountBeansPage.getLastName());

				enterText(emailAddress,  accountBeansPage.getEmailAddress());
	    	    customReport.reporter("Enter text in Email Address", accountBeansPage.getEmailAddress());

				enterText(addressLine1, accountBeansPage.getAddressLine1());
	    	    customReport.reporter("Enter text in Address 1", accountBeansPage.getAddressLine1());

				enterText(addressLine2, accountBeansPage.getAddressLine2());
	    	    customReport.reporter("Enter text in Address 2", accountBeansPage.getAddressLine2());

		        selectValuefromDropDown(country, accountBeansPage.getCountry());
	    	    customReport.reporter("Select value in Country", accountBeansPage.getCountry());


				enterText(city, accountBeansPage.getCity());
	    	    customReport.reporter("Enter text in City", accountBeansPage.getCity());

				enterText(postalCode, accountBeansPage.getPostalCode());
	    	    customReport.reporter("Enter text in Postal Code is", accountBeansPage.getPostalCode());

				enterText(phoneno, accountBeansPage.getPhoneNumber());
	    	    customReport.reporter("Enter text in Phone Number is", accountBeansPage.getPhoneNumber());

			    click(createRootAccountbuttonlocator);
		         customReport.reporter("Clicked on Root Account Button" ,"");
				
	  }

}
