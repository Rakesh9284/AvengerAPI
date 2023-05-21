package com.vbrick.avenger.pageobjects;

import java.util.ArrayList;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerApprovalProcessPropertyPage;
import com.vbrick.avenger.dao.AddUserBeanPage;
import com.vbrick.avenger.dao.AvengerGroupsBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerApprovalProcessPage extends WebElements{
	
	private WebDriver driver;
	private CustomReport customReport;
	private BasePage basePage;
	
	By createapprovalprocessbuttonlocator=By.xpath(AvengerApprovalProcessPropertyPage.avengerapprovalprocess_createapporvalprocessbuttonlocator.getProperty());
	By approvalprocessnamelocator=By.xpath(AvengerApprovalProcessPropertyPage.avengerapprovalprocess_approvalprocessnamelocator.getProperty());
	By groupusersrequiringapprovallocator=By.xpath(AvengerApprovalProcessPropertyPage.avengerapprovalprocess_groupusersrequiringapprovallocator.getProperty());
	By createapprovalprocesssubmitbuttonlocator=By.xpath(AvengerApprovalProcessPropertyPage.avengerapprovalprocess_createapprovalprocesssubmitbuttonlocator.getProperty());
	By approverslocator=By.xpath(AvengerApprovalProcessPropertyPage.avengerapprovalprocess_approverslocator.getProperty());
	By approvalProcessesSearchBox= By.xpath(AvengerApprovalProcessPropertyPage.approvalProcessesSearchBox.getProperty());
	By approvalProcessIcon = By.xpath(AvengerApprovalProcessPropertyPage.approvalProcessIcon.getProperty());
	By aprrovalProcessDeleteButton=By.xpath(AvengerApprovalProcessPropertyPage.aprrovalProcessDeleteButton.getProperty());
	By deletepopuplocator=By.xpath(AvengerApprovalProcessPropertyPage.deletepopuplocator.getProperty());
	By deletepopupNolocator=By.xpath(AvengerApprovalProcessPropertyPage.deletepopupNolocator.getProperty());
	By deleteMessageLocator=By.xpath(AvengerApprovalProcessPropertyPage.deleteMessageLocator.getProperty());
	By deleteVideoListMessageLocator = By.xpath(AvengerApprovalProcessPropertyPage.deleteVideoListMessageLocator.getProperty());
	By deleteVideoListLocator = By.xpath(AvengerApprovalProcessPropertyPage.deleteVideoListLocator.getProperty());
	By approversstepnameLocator = By.name(AvengerApprovalProcessPropertyPage.approversstepnameLocator.getProperty());
			
	public AvengerApprovalProcessPage(WebDriver driver, CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver=driver;

		this.customReport = customReport;
		this.basePage=basePage;
		// TODO Auto-generated constructor stub
	}
	
	public void click_CreateApprovalProcessButton()
	{
		clickUsingSwitch(createapprovalprocessbuttonlocator);
		customReport.reporter("Clicked on Create Approval Process button","");
		waitForElementPresent(approvalprocessnamelocator);
	}
	
	public void addApprovalProcessName(String approvalprocessname)
	{
	enterText(approvalprocessnamelocator, approvalprocessname);
	}

	public void addusersRequiringApproval(AddUserBeanPage adduserbeanpage)
	{
	By doneButtonLocator = By.xpath("(//*[contains(@name,'query')])[1]/..//button");
	waitForElement(groupusersrequiringapprovallocator);
	click(groupusersrequiringapprovallocator);
	waitForElement(doneButtonLocator);
	enterText(groupusersrequiringapprovallocator,  adduserbeanpage.getFirstname()+" "+adduserbeanpage.getLastname());
	clickEnter(groupusersrequiringapprovallocator);
	By userlocator=By.xpath("//*[contains(@class,'ui-match')]");
	waitForElement(userlocator);
	pause(3000);
	clickUsingSwitch(userlocator);
	customReport.reporter("Clicked on selected user requiring approval","");
	click(doneButtonLocator);
	customReport.reporter("Clicked on Done button","");
	}
	
	public void addgroupsRequiringApproval(AvengerGroupsBeanPage avengergroupsbeanpage)
	{
	By doneButtonLocator = By.xpath("(//*[@ng-model='insight.query']/../button)[1]");	
	waitForElement(groupusersrequiringapprovallocator);
	click(groupusersrequiringapprovallocator);
	waitForElement(doneButtonLocator);
	enterText(groupusersrequiringapprovallocator,  avengergroupsbeanpage.getNewgroup());
	clickEnter(groupusersrequiringapprovallocator);
	By grouplocator=By.xpath("//*[contains(text(),'"+avengergroupsbeanpage.getNewgroup()+"')]");
	click(groupusersrequiringapprovallocator);
	waitForElement(grouplocator);
	clickUsingSwitch(grouplocator);
	customReport.reporter("Clicked on selected group requiring approval","");
	clickUsingSwitch(doneButtonLocator);
	customReport.reporter("Clicked on Done button","");
	}
	
	public void addusersForApproval(AddUserBeanPage adduserbeanpage)
	{
			
		enterText(approversstepnameLocator, RandomStringUtils.randomAlphabetic(5));
		By doneButtonLocator = By.xpath("//*[contains(@class,'ui-match')]");
		waitForElement(approverslocator);
		click(approverslocator);	
		enterText(approverslocator,  adduserbeanpage.getFirstname()+" "+adduserbeanpage.getLastname());
		clickEnter(approverslocator);
		By userlocator=By.xpath("//h4[contains(text(),'Approvers')]/../..//span[contains(text(),'"+adduserbeanpage.getFirstname()+" "+adduserbeanpage.getLastname() +"')]");
		click(approverslocator);
		By userlocatorvisible =By.xpath("//span[contains(text(),'"+adduserbeanpage.getFirstname()+"')]/../../..");
		waitForElement(userlocatorvisible);
	    pause(5000);
		clickUsingSwitch(doneButtonLocator);
		customReport.reporter("Clicked on selected user as Aprrover","");
		pause(5000);
		By donebuttonlocator=By.xpath("(//*[contains(@class,'btn btn-close')])[2]");
		clickUsingSwitch(donebuttonlocator);
		pause(10000);
		customReport.reporter("Clicked on Done button","");	
		
	}
	
	public void addgroupsForApproval(AvengerGroupsBeanPage avengergroupsbeanpage)
	{
	By doneButtonLocator = By.xpath("(//*[@ng-model='insight.query']/../button)[2]");
	waitForElement(approverslocator);
	click(approverslocator);	
	waitForElement(doneButtonLocator);
	enterText(approverslocator,  avengergroupsbeanpage.getNewgroup());
	By grouplocator=By.xpath("//*[contains(text(),'"+avengergroupsbeanpage.getNewgroup()+"')]");
	click(approverslocator);
	waitForElement(grouplocator);
	clickUsingSwitch(grouplocator);
	customReport.reporter("Clicked on selected user as Aprrover","");
	clickUsingSwitch(doneButtonLocator);
	customReport.reporter("Clicked on Done button","");
	}
	
	public void clickcreateapprovalProcessSubmitButtonLocator()
	{
		click(createapprovalprocesssubmitbuttonlocator);
		waitForElement(createapprovalprocessbuttonlocator);
	}
	
	public String getApprovalProcessID()
	{
		pause(5000);
		String url=getCurrentURL();
		String[] parts = url.split("approval-processes/");
		String part1 = parts[0]; 
		String part2 = parts[1]; 
		return part2;
	}
	public String verify_Label(String label)
	{
	  By labellocator=By.xpath("//*[contains(text(),'"+label+"')]");
	  return getText(labellocator);
	
	}
	
	/*
	 * Used to search approvalProcess
	 * have to provide approvalProcess Name to be search as an argument
	 * returns count of provalProcessSearched 
	 */
		public int approvalProcessesSearch(String approvalProcess){
			waitForElement(approvalProcessesSearchBox);
		    enterText(approvalProcessesSearchBox,  approvalProcess);
			click(approvalProcessesSearchBox);
			customReport.reporter("Entered Approval process name and clicked in textbox","");
			waitForElement(approvalProcessIcon);
			By approvalProcessValue=By.xpath("//*[contains(text(),'"+approvalProcess+"')]");
			return elements(approvalProcessValue);
		}
		
		public void click_Label(String label)
		{
		  By labellocator=By.xpath("//a[contains(text(),'"+label+"')]");
		  click(labellocator);
		  waitForElement(createapprovalprocesssubmitbuttonlocator);
		}
		
		
		public void delete_approvalProcess(String label)
		{
			click(aprrovalProcessDeleteButton);
			waitForElement(createapprovalprocesssubmitbuttonlocator);
		}
		
		public String verifyDeletePopupForPendingApprovalVideos(){
			return getText(deleteMessageLocator);
		}
		public String verifyVideoListMsg(){
			return getText(deleteVideoListMessageLocator);
		}
		public String verifyVideoListInDeletePopup(){
			return getText(deleteVideoListLocator);
		}
		public String verifyUploaderOfVideoListInDeletePopup(AddUserBeanPage adduserbeanpage){
			By uploaderLocator= By.xpath("//*[contains(text(),'"+adduserbeanpage.getFirstname()+"')]");
			return String.valueOf(elements(uploaderLocator));
		}
		public String clickOkOnDeletePopup(){
			waitForElementPresent(deletepopuplocator);
			click(deletepopuplocator);
			waitForElement(approvalProcessesSearchBox);
			pause(5000);
			return String.valueOf(elements(aprrovalProcessDeleteButton));
		}
	
    public void clickCreatedApprovalProcess(String approvalprocessname)
    {
    	By createdapprovalprocess= By.xpath("//*[contains(text(),'"+approvalprocessname+"')]");
    	click(createdapprovalprocess);
		
    }
    
    public void remove_Approvers()
    {
    	By removeapprover=By.xpath("(//*[@ng-click='removeItem(item)'])[2]");
    	click(removeapprover);
    }
    
    public boolean verifyUsersRequringApproval(String approvalprocessname){
    	By requiringuser = By.xpath("//h4[contains(text(),'Groups/Users Requiring Approval')]/../..//span[contains(@class,'circle glyphicons')]");
    	return isDisplayedWithoutException(requiringuser);
    }
    
     public boolean verifyApproverUser(String approvalprocessname){
    	By approvaluser = By.xpath("//h4[contains(text(),'Approvers')]/../..//span[contains(@class,'circle glyphicons')]");
    	return isDisplayedWithoutException(approvaluser);
    }
     
    public ArrayList<String> getALLApprovalProcess(){
	By approvalprocessLocator = By.xpath("//*[contains(@uisref,'.detail')]");
	waitForElementPresent(approvalprocessLocator);
	ArrayList<String> webelementslist=getAllWebElementValueslist(approvalprocessLocator);
	return webelementslist;
}

}
