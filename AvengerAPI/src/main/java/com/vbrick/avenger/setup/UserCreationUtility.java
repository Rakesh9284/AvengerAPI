package com.vbrick.avenger.setup;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.vbrick.FailedTestCasesRerun.Retry;
import com.vbrick.avenger.dao.AddUserBeanPage;
import com.vbrick.avenger.dao.CreatePasswordBeanPage;
import com.vbrick.avenger.pageobjects.AvengerAddUserPage;
import com.vbrick.avenger.pageobjects.AvengerConfirmPasswordPage;
import com.vbrick.avenger.pageobjects.AvengerEditRootAccountPage;
import com.vbrick.avenger.pageobjects.AvengerHomePage;
import com.vbrick.avenger.pageobjects.AvengerLoginPage;
import com.vbrick.avenger.pageobjects.AvengerUserDashboardPage;

public class UserCreationUtility implements Setup{

	private static Logger logger = Logger.getLogger(UserCreationUtility.class);
	@SuppressWarnings("unused")
	private AvengerLoginPage loginPage;
	private AvengerUserDashboardPage avengeruserdashboardpage;
	private AvengerConfirmPasswordPage avengerconfirmpasswordpage;
	private CreatePasswordBeanPage createpasswordbeanpage;
	private AvengerEditRootAccountPage editrootaccountpage;
	private AvengerAddUserPage avengeradduserpage;
	String sSuiteName;
	
	public UserCreationUtility(String suiteName) {
		// TODO Auto-generated constructor stub
		this.sSuiteName =suiteName ;
		logger.info("The suite name in the constructor is"+suiteName);
	}
	
	public void gridUserCreation(AvengerHomePage homePage, AddUserBeanPage adduserbeanpage, AvengerAddUserPage avengeradduserpage, String uservalue){
		createpasswordbeanpage=new CreatePasswordBeanPage();
		logger.info("The Suite Name in the Method is"+sSuiteName);
		ArrayList<String> roles = new ArrayList<String>();
		roles.add("Account Admin");
		homePage.clickSettingsLink();
		avengeruserdashboardpage=homePage.clickUsersLink();
		adduserbeanpage.setUsername(uservalue);
		adduserbeanpage.setFirstname(uservalue);
		adduserbeanpage.setLastname(uservalue);
		adduserbeanpage.setContactemail(uservalue+adduserbeanpage.getDomainname());
		/*if(Retry.retryCount>=1)
		{
		if(avengeruserdashboardpage.verifyDeletionOfuser(adduserbeanpage).equals("1"))
		    {
			avengeruserdashboardpage.deleteUser(adduserbeanpage);
			avengeruserdashboardpage.confirmDelete("Y");
		    }
		}*/
		avengeradduserpage=avengeruserdashboardpage.clickAdduser();  
		adduserbeanpage.setUsername(uservalue);
		adduserbeanpage.setFirstname(uservalue);
		adduserbeanpage.setLastname(uservalue);
		adduserbeanpage.setContactemail(uservalue+adduserbeanpage.getDomainname());
		avengeruserdashboardpage= avengeradduserpage.addUseraccountRoles(adduserbeanpage, roles);
		editrootaccountpage=avengeruserdashboardpage.clicknewuser_link(adduserbeanpage);
		avengerconfirmpasswordpage=editrootaccountpage.click_UserConfirmationURL();
		createpasswordbeanpage.setPassword(SANITYPASSWORD);
		createpasswordbeanpage.setConfirmpassword(SANITYPASSWORD);
		createpasswordbeanpage.setSecurityquestion("What is your favorite book?");
		createpasswordbeanpage.setTypeyouranswer("YoucanWIn");
		loginPage= avengerconfirmpasswordpage.passwordCreation(createpasswordbeanpage);
		homePage.clickSettingsLink();
	}
	
	
	public void gridUserCreation_Account(AvengerHomePage homePage, AddUserBeanPage adduserbeanpage, AvengerAddUserPage avengeradduserpage, String uservalue){
		createpasswordbeanpage=new CreatePasswordBeanPage();
		logger.info("The Suite Name in the Method is"+sSuiteName);
		ArrayList<String> roles = new ArrayList<String>();
		roles.add("Account Admin");
	//	homePage.clickSettingsLink();
		avengeruserdashboardpage=homePage.clickUsersLink();
		adduserbeanpage.setUsername(uservalue);
		adduserbeanpage.setFirstname(uservalue);
		adduserbeanpage.setLastname(uservalue);
		adduserbeanpage.setContactemail(uservalue+adduserbeanpage.getDomainname());
		if(Retry.retryCount>=1)
		{
		if(avengeruserdashboardpage.verifyDeletionOfuser(adduserbeanpage).equals("1"))
		    {
			avengeruserdashboardpage.deleteUser(adduserbeanpage);
			avengeruserdashboardpage.confirmDelete("Y");
		    }
		}
		avengeradduserpage=avengeruserdashboardpage.clickAdduser();  
		adduserbeanpage.setUsername(uservalue);
		adduserbeanpage.setFirstname(uservalue);
		adduserbeanpage.setLastname(uservalue);
		adduserbeanpage.setContactemail(uservalue+adduserbeanpage.getDomainname());
		avengeruserdashboardpage= avengeradduserpage.addUseraccountRoles(adduserbeanpage, roles);
		editrootaccountpage=avengeruserdashboardpage.clicknewuser_link(adduserbeanpage);
		avengerconfirmpasswordpage=editrootaccountpage.click_UserConfirmationURL();
		createpasswordbeanpage.setPassword(SANITYPASSWORD);
		createpasswordbeanpage.setConfirmpassword(SANITYPASSWORD);
		createpasswordbeanpage.setSecurityquestion("What was your first car?");
		createpasswordbeanpage.setTypeyouranswer("BMW");
		loginPage= avengerconfirmpasswordpage.passwordCreation(createpasswordbeanpage);
		homePage.clickSettingsLink();
	}
	
	public void mediaViewerUserCreation(AvengerHomePage homePage, AddUserBeanPage adduserbeanpage, AvengerAddUserPage avengeradduserpage, String uservalue){
		createpasswordbeanpage=new CreatePasswordBeanPage();
		homePage.clickSettingsLink();
		avengeruserdashboardpage=homePage.clickUsersLink();
		adduserbeanpage.setUsername(uservalue);
		adduserbeanpage.setFirstname(uservalue);
		adduserbeanpage.setLastname(uservalue);
		adduserbeanpage.setContactemail(uservalue+adduserbeanpage.getDomainname());
		if(Retry.retryCount>=1)
		{
		if(avengeruserdashboardpage.verifyDeletionOfuser(adduserbeanpage).equals("1"))
		{
			avengeruserdashboardpage.deleteUser(adduserbeanpage);
			avengeruserdashboardpage.confirmDelete("Y");
		}
		}
		avengeradduserpage=avengeruserdashboardpage.clickAdduser();  
		adduserbeanpage.setUsername(uservalue);
		adduserbeanpage.setFirstname(uservalue);
		adduserbeanpage.setLastname(uservalue);
		adduserbeanpage.setContactemail(uservalue+adduserbeanpage.getDomainname());
		avengeruserdashboardpage= avengeradduserpage.addUseraccount(adduserbeanpage);
		editrootaccountpage=avengeruserdashboardpage.clicknewuser_link(adduserbeanpage);
		avengerconfirmpasswordpage=editrootaccountpage.click_UserConfirmationURL();
		createpasswordbeanpage.setPassword(SANITYPASSWORD);
		createpasswordbeanpage.setConfirmpassword(SANITYPASSWORD);
		createpasswordbeanpage.setSecurityquestion("What was your first car?");
		createpasswordbeanpage.setTypeyouranswer("BMW");
		loginPage= avengerconfirmpasswordpage.passwordCreation(createpasswordbeanpage);
		
	}
	
	public AvengerConfirmPasswordPage createUserwithSpecifiedRole(AvengerHomePage homePage,AddUserBeanPage adduserbeanpage,List<String> roles,CreatePasswordBeanPage createpasswordbeanpage)
	{
		homePage.clickSettingsLink();
		avengeruserdashboardpage=homePage.clickUsersLink();
		avengeradduserpage=avengeruserdashboardpage.clickAdduser();    
		avengeruserdashboardpage= avengeradduserpage.addUseraccountRoles(adduserbeanpage,roles);
		editrootaccountpage=avengeruserdashboardpage.clicknewuser_link(adduserbeanpage);
		avengerconfirmpasswordpage=editrootaccountpage.click_UserConfirmationURL();
		avengerconfirmpasswordpage.passwordCreation(createpasswordbeanpage);
	   return avengerconfirmpasswordpage;
	}
	
}
