package com.vbrick.avenger.beansfactory;

import org.apache.log4j.Logger;

import com.vbrick.avenger.dao.FieldValidations;
import com.vbrick.avenger.dao.ManualRootAccountBeanPage;
import com.vbrick.avenger.dao.ManualRootAccountBeanPage2;
import com.vbrick.avenger.setup.Configuration;

public class SetupFactory extends FieldValidations{

	private static Logger logger = Logger.getLogger(SetupFactory.class);
	
	public void ManualRootAccountByBeans(ManualRootAccountBeanPage beansPage,String environment,String surl) {

		String accountName="";
		String userName="";
		String password="";
		String confirmPassword="";
		String emailAddress="";
		String emailServerAddress="";
		String port="";
		String emailServerUserName="";
		String emailServerPassword="";
		String fromaddress="";
		String firstname="";
		String title="";
		String phonenumber="";
		String lastName="";
		beansPage.setAccountHostName(surl.replace("http://", ""));
		
		if (environment.equalsIgnoreCase("Manual"))
		{
		logger.info(" Intializing Manual Environment Values");
		 accountName = "RootAccount";
	//	 accountHostName = "ec2-54-227-75-253.compute-1.amazonaws.com";
		 userName = "rootqa";
		 password = "vbqa";
		 confirmPassword = "vbqa";
		 emailAddress = "david.dehora@vbrick.com";
		 emailServerAddress = "qa-u1204-mail-01.lab.vb.loc";
		 port = "25";
		 emailServerUserName = "";
		 emailServerPassword = "";
		 fromaddress = "system@vbrick.com";
		 lastName="dehora";
		 firstname="david";
		 title="manual title";
		 phonenumber="98764646";
		}
		

		else if (environment.equalsIgnoreCase("Manual2"))
		{
		logger.info(" Intializing Manual Environment Values");
		 accountName = "RootAccount";
		// accountHostName = "avenger.qa.vb.loc";
		 userName = "rootqa";
		 password = "vbqa";
		 confirmPassword = "vbqa";
		 emailAddress = "david.dehora@vbrick.com";
		 emailServerAddress = "qa-u1204-mail-01.lab.vb.loc";
		 port = "25";
		 emailServerUserName = "";
		 emailServerPassword = "";
		 fromaddress = "system@vbrick.com";
		 lastName="dehora";
		 firstname="david";
		 title="manual title";
		 phonenumber="98764646";
		}
		
		else if (environment.equalsIgnoreCase("Automation"))
		{
			logger.info(" Intializing Automation Environment Values");
		 accountName = "RootAccount";
	//	 accountHostName = "ec2-54-234-19-105.compute-1.amazonaws.com";
		 userName = "rootqa";
		 password = "vbqa";
		 confirmPassword = "vbqa";
		 emailAddress = "arjun.abhesheke@vbrick.com";
		 emailServerAddress = "qa-u1204-mail-01.lab.vb.loc";
		 port = "25";
		 emailServerUserName = "";
		 emailServerPassword = "";
		 fromaddress = "system@vbrick.com";
		 lastName="abhesheke";
		 firstname="arjun";
		 title="Automation title";
		 phonenumber="98764";
		}
		
		else if (environment.equalsIgnoreCase("CI"))
		{
		logger.info(" Intializing CI Environment Values");
		 accountName = "RootAccount";
	//	 accountHostName = "10.0.0.93";
		 userName = "rootqa";
		 password = "vbqa";
		 confirmPassword = "vbqa";
		 emailAddress = "arjun.abhesheke@vbrick.com";
		 emailServerAddress = "qa-u1204-mail-01.lab.vb.loc";
		 port = "25";
		 emailServerUserName = "";
		 emailServerPassword = "";
		 fromaddress = "system@vbrick.com";
		 lastName="abhesheke";
		 firstname="arjun";
		 title="Automation title";
		 phonenumber="98764";
		
		}
		
		else if (environment.equalsIgnoreCase("Any"))
		{
			logger.info(" Intializing Any Environment Values");
		 accountName = "RootAccount";
	//	 accountHostName = "ec2-54-234-19-105.compute-1.amazonaws.com";
		 userName = Configuration.getAutomationUsername();
		 password = Configuration.getAutomationPassword();
		 confirmPassword = Configuration.getAutomationPassword();
		 emailAddress = "arjun.abhesheke@vbrick.com";
		 emailServerAddress = "qa-u1204-mail-01.lab.vb.loc";
		 port = "25";
		 emailServerUserName = "";
		 emailServerPassword = "";
		 fromaddress = "system@vbrick.com";
		 lastName="abhesheke";
		 firstname="arjun";
		 title="Automation title";
		 phonenumber="98764";
		
		}
		
		
		beansPage.setAccountName(accountName);
		beansPage.setLastName(lastName);
	//	beansPage.setAccountHostName(accountHostName);
		beansPage.setUserName(userName);
		beansPage.setPassword(password);
		beansPage.setConfirmPassword(confirmPassword);
		beansPage.setEmailAddress(emailAddress);
		beansPage.setEmailServerAddress(emailServerAddress);
		beansPage.setPort(port);
		beansPage.setEmailServerUserName(emailServerUserName);
		beansPage.setEmailServerPassword(emailServerPassword);
		beansPage.setFromAddress(fromaddress);
		beansPage.setFirstname(firstname);
		beansPage.setTitle(title);
		beansPage.setPhonenumber(phonenumber);
	}

	public void ManualRootAccountByBeans2(ManualRootAccountBeanPage2 beansPage,String environment) {

		String firstName="";
		String lastName="";
		String emailAddress="";
		String addressLine1="";
		String addressLine2="";
		String country="";
		String state="";
		String city="";
		String postalCode="";
		String phoneNumber="";
		
		
		if(environment.equalsIgnoreCase("Manual"))
		{
		logger.info(" Intializing Manual Environment Page2");	
		firstName = "David";
		lastName = "De Hora";
		emailAddress = "david.dehora@vbrick.com";
		 addressLine1 = "addressline1";
		 addressLine2 = "addressline2";
		 country = "United States";
		 state = "Virginia";
		city = "Herndon";
		 postalCode = "20171";
		 phoneNumber = "203-265-0044";
		}
		
		else if(environment.equalsIgnoreCase("Manual2"))
		{
		logger.info(" Intializing Manual Environment Page2");	
		firstName = "David";
		lastName = "De Hora";
		emailAddress = "david.dehora@vbrick.com";
		 addressLine1 = "addressline1";
		 addressLine2 = "addressline2";
		 country = "United States";
		 state = "Virginia";
		city = "Herndon";
		 postalCode = "20171";
		 phoneNumber = "203-265-0044";
		}
		
		else if(environment.equalsIgnoreCase("Automation"))
		{
		 logger.info(" Intializing Automation Environment Page2");	
		 firstName = "arjun";
		 lastName = "abhesheke";
		 emailAddress = "arjun.abhesheke@vbrick.com";
		 addressLine1 = "addressline1";
		 addressLine2 = "addressline2";
		 country = "United States";
		 state = "Virginia";
		city = "Herndon";
		 postalCode = "20171";
		 phoneNumber = "203-265-0044";
		}
		
		else if(environment.equalsIgnoreCase("CI"))
		{
		 logger.info(" Intializing CI Environment Page2");	
		 firstName = "arjun";
		 lastName = "abhesheke";
		 emailAddress = "arjun.abhesheke@vbrick.com";
		 addressLine1 = "addressline1";
		 addressLine2 = "addressline2";
		 country = "United States";
		 state = "Virginia";
		city = "Herndon";
		 postalCode = "20171";
		 phoneNumber = "203-265-0044";
		}
		
		else if(environment.equalsIgnoreCase("Any"))
		{
		 logger.info(" Intializing Any Environment Page2");	
		 firstName = "arjun";
		 lastName = "abhesheke";
		 emailAddress = "arjun.abhesheke@vbrick.com";
		 addressLine1 = "addressline1";
		 addressLine2 = "addressline2";
		 country = "United States";
		 state = "Virginia";
		city = "Herndon";
		 postalCode = "20171";
		 phoneNumber = "203-265-0044";
		}
		else if(firstName.equalsIgnoreCase(""))
		{
			logger.info("************Environment Variable not setPlease set it to Automation or CI or Manual*******");
		}
		beansPage.setFirstName(firstName);
		beansPage.setLastName(lastName);
		beansPage.setEmailAddress(emailAddress);
		beansPage.setAddressLine1(addressLine1);
		beansPage.setAddressLine2(addressLine2);
		beansPage.setCountry(country);
		beansPage.setState(state);
		beansPage.setCity(city);
		beansPage.setPostalCode(postalCode);
		beansPage.setPhoneNumber(phoneNumber);
	}

}
