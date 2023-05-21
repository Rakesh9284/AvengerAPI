package com.vbrick.avenger.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.dao.MailinatorBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;


public class Mailinator extends WebElements{
	private static Logger logger = Logger.getLogger(Mailinator.class);
	WebDriver driver;
	CustomReport customReport;
	private BasePage basePage;

	public Mailinator(WebDriver driver, CustomReport customReport,BasePage basePage)
	{
		super(driver);
		this.driver=driver;
		this.customReport=customReport;

		this.basePage=basePage;
	}
	
	By completeregistrationlocator=By.xpath("html/body/a");
    By mailinatortextbox=By.id("inboxfield");
	By mailinatorgobuttonlocator=By.xpath("//*[contains(@onclick,'changeInbox')]");
	By mailinatorframeid=By.id("html_msg_body");
	By mailinatoremailbody=By.xpath("(//table/tbody)[3]");
	By registrationlink = By.xpath("//a[contains(@href,'user-confirmation')]");
	
	
	public AvengerConfirmPasswordPage returnUserConfirmationURL(String emailId,String value,MailinatorBeanPage mailbean) {
		if(driver!=null)
		{
		
			if(emailId.contains("@mailinator.com"))
			{
				By userlink=By.xpath("//*[contains(text(),'"+value+"')]");
			    enterText(mailinatortextbox, emailId);
			    click(mailinatorgobuttonlocator);
				pause(5000);
				waitForElementPresent(userlink);
				click(userlink);
				pause(5000);
				switchFrame(mailinatorframeid);
				mailbean.setEmaillink(getText(completeregistrationlocator));
				logger.info("Value of the Email Link is"+mailbean.getEmaillink());
				navigateBrowser(getText(completeregistrationlocator));
				driver.switchTo().defaultContent();
			}
			if(emailId.contains("@sharklasers.com"))
			{
				By userlink=By.xpath("//*[contains(text(),'system@vbrick.com')]/..//*[starts-with(text(),'"+value+"')]");
				uncheck_Checkbox(By.id("use-alias"));
				driver.findElement((By.id("inbox-id"))).click();
				driver.findElement(By.xpath("//span[@class='editable button edit-in-progress']/input")).clear();
				logger.info("Email id is@@@@@@@"+emailId.split("@")[0]);
				driver.findElement(By.xpath("//span[@class='editable button edit-in-progress']/input")).sendKeys(emailId.split("@")[0]);
				driver.findElement(By.xpath("//span[@id='inbox-id']/button[1]")).click();
				pause(5000);
				waitForElementPresent(userlink);
				driver.findElement(userlink).click();
				pause(5000);
				mailbean.setEmaillink(driver.findElement(registrationlink).getAttribute("href"));
				logger.info("Value of the Email Link is"+mailbean.getEmaillink());
				driver.navigate().to(driver.findElement(registrationlink).getAttribute("href"));
								
			}
		}
		else
		{
			logger.info("Driver is null");
		}
		return basePage.avengerConfirmPasswordPage(driver,  customReport, basePage);
	
		}

	public AvengerForgotPasswordPage returnforgotConfirmationURL(String emailId,String value,MailinatorBeanPage mailbean) {
			if(driver!=null)
		
			{
				if(emailId.contains("@mailinator.com"))
				{
				By userlink=By.xpath("//*[contains(text(),'"+value+"')]");
				enterText(mailinatortextbox, emailId);
				click(mailinatorgobuttonlocator);
	            pause(5000);
			     click(userlink);	
				pause(3000);
				switchFrame(mailinatorframeid);
				pause(3000);
				logger.info("the url value is"+driver.findElement(completeregistrationlocator).getText());
				String emailbodydata = getText(mailinatoremailbody);
				mailbean.setEmailbody(emailbodydata);
				logger.info("Email Body Value is"+emailbodydata);
				mailbean.setEmaillink(getText(completeregistrationlocator));
				logger.info("Value of the Email Link is"+mailbean.getEmaillink());
				navigateBrowser(mailbean.getEmaillink());
				driver.switchTo().defaultContent();
				}
			else if(emailId.contains("@sharklasers.com"))
			{
				logger.info("value is"+value);
				By userlink=By.xpath("//*[contains(text(),'system@vbrick.com')]/..//*[contains(text(),'"+value+"')]");
				By registrationlink = By.xpath("//a[contains(@href,'reset-password')]");
				uncheck_Checkbox(By.id("use-alias"));
				
				driver.findElement((By.id("inbox-id"))).click();
				driver.findElement(By.xpath("//span[@class='editable button edit-in-progress']/input")).clear();
				logger.info("Email id is@@@@@@@"+emailId.split("@")[0]);
				driver.findElement(By.xpath("//span[@class='editable button edit-in-progress']/input")).sendKeys(emailId.split("@")[0]);
				driver.findElement(By.xpath("//span[@id='inbox-id']/button[1]")).click();
				pause(45000);
				waitForElementPresent(userlink);
				driver.findElement(userlink).click();
				pause(5000);
				By emailbody=By.xpath("//*[@class='email_body']");
				String emailbodydata = driver.findElement(emailbody).getText();
				mailbean.setEmailbody(emailbodydata);
				logger.info("Email Body Value is"+emailbodydata);
				mailbean.setEmaillink(driver.findElement(registrationlink).getAttribute("href"));
				logger.info("Value of the Email Link is"+mailbean.getEmaillink());
				driver.navigate().to(driver.findElement(registrationlink).getAttribute("href"));
			}

		}
		else
		{
			logger.info("Driver is null");
		}
		return basePage.avengerForgotPasswordPage(driver,  customReport, basePage);
	
		}
	
	public String verifyDMEUploadFailEmail(String emailId,String value,MailinatorBeanPage mailbean) {
		String dmeuploadfailedstring="";
		if(driver!=null)
		{
		
			if(emailId.contains("@mailinator.com"))
			{
			By userlink=By.xpath("//*[contains(text(),'"+value+"')]");
			enterText(mailinatortextbox, emailId);
			click(mailinatorgobuttonlocator);
			waitForElementPresent(userlink);
			click(userlink);
			pause(5000);
			switchFrame(mailinatorframeid);
			getText(completeregistrationlocator);
            driver.switchTo().defaultContent();
		     return dmeuploadfailedstring;
			}
			if(emailId.contains("@sharklasers.com"))
			{
				logger.info("value is"+value);
				By userlink=By.xpath("//*[contains(text(),'system@vbrick.com')]/..//*[starts-with(text(),'"+value+"')]");
				By registrationlink = By.xpath("//a[contains(@href,'user-confirmation')]");
				uncheck_Checkbox(By.id("use-alias"));
				
				driver.findElement((By.id("inbox-id"))).click();
				driver.findElement(By.xpath("//span[@class='editable button edit-in-progress']/input")).clear();
				logger.info("Email id is@@@@@@@"+emailId.split("@")[0]);
				driver.findElement(By.xpath("//span[@class='editable button edit-in-progress']/input")).sendKeys(emailId.split("@")[0]);
				driver.findElement(By.xpath("//span[@id='inbox-id']/button[1]")).click();
				pause(25000);
				waitForElementPresent(userlink);
				driver.findElement(userlink).click();
				pause(25000);
				dmeuploadfailedstring=driver.findElement(By.xpath("//*[@class='email_body']")).getText();
			}
		}
		else
		{
			logger.info("Driver is null");
		}
		return dmeuploadfailedstring;
	
		}
	
	public void returnPendingVideoApprovalURL(String emailId,String value,String emailtype) {
		if(driver!=null)
		{
           logger.info("Email Type is"+emailtype);
           logger.info("value is"+value);
           logger.info("Email id is"+emailId);
			pause(45000);
			if(emailtype.contains("mailinator"))
			{
			By userlink=By.xpath("//*[contains(text(),'"+value+"')]");
			enterText(mailinatortextbox, emailId);
            click(mailinatorgobuttonlocator);
			waitForElementPresent(userlink);
            click(userlink);
			switchFrame(mailinatorframeid);
			pause(3000);
			waitForElementPresent(mailinatoremailbody);
			String emailbodydata = getText(mailinatoremailbody);
			logger.info("Email Body Value is"+emailbodydata);
			navigateBrowser(getText(completeregistrationlocator));
			driver.switchTo().defaultContent();
			}
		else if(emailtype.contains("sharklasers"))
		{
			logger.info("value is"+value);
			By userlink=By.xpath("(//*[contains(text(),'system@vbrick.com')]/..//*[contains(text(),'"+value+"')])[1]");
			By approvevideolink = By.xpath("//*[@class='email_body']/pre/a");
			uncheck_Checkbox(By.id("use-alias"));
			
			driver.findElement((By.id("inbox-id"))).click();
			driver.findElement(By.xpath("//span[@class='editable button edit-in-progress']/input")).clear();
			logger.info("Email id is@@@@@@@"+emailId.split("@")[0]);
			driver.findElement(By.xpath("//span[@class='editable button edit-in-progress']/input")).sendKeys(emailId.split("@")[0]);
			driver.findElement(By.xpath("//span[@id='inbox-id']/button[1]")).click();
			pause(45000);
			waitForElementPresent(userlink);
			driver.findElement(userlink).click();
			pause(5000);
			By emailbody=By.xpath("//*[@class='email_body']");
			String emailbodydata = driver.findElement(emailbody).getText();
			logger.info("Email Body Value is"+emailbodydata);
			driver.navigate().to(driver.findElement(approvevideolink).getAttribute("href"));
		}

	}

	}

	public String ReturnEmailBodyData(String emailId,String emailsubject)
	{
		String emailbodydata="";
		if(driver!=null)
		{
			logger.info("value is"+emailsubject);
			logger.info("Email id is"+emailId);
			waitForElementPresent(mailinatortextbox);
			if(MAILINATOR_URL.contains("mailinator"))
			{
				By userlink=By.xpath("//*[contains(text(),'"+emailsubject+"')][1]");
				enterText(mailinatortextbox, emailId);
				click(mailinatorgobuttonlocator);
				waitForElementPresent(userlink);
				click(userlink);
				waitForElementPresent(mailinatorframeid);
				switchFrame(mailinatorframeid);
				waitForElementPresent(mailinatoremailbody);
				emailbodydata = getText(mailinatoremailbody);
				logger.info("Email Body Value is"+emailbodydata);
				navigateBrowser(getText(completeregistrationlocator));
				driver.switchTo().defaultContent();
			}

			else 
			{
				logger.info("value is"+emailsubject);
				By userlink=By.xpath("(//*[contains(text(),'system@vbrick.com')]/..//*[contains(text(),'"+emailsubject+"')])[1]");
				By approvevideolink = By.xpath("//*[@class='email_body']/pre/a");
				uncheck_Checkbox(By.id("use-alias"));

				driver.findElement((By.id("inbox-id"))).click();
				driver.findElement(By.xpath("//span[@class='editable button edit-in-progress']/input")).clear();
				logger.info("Email id is@@@@@@@"+emailId.split("@")[0]);
				driver.findElement(By.xpath("//span[@class='editable button edit-in-progress']/input")).sendKeys(emailId.split("@")[0]);
				driver.findElement(By.xpath("//span[@id='inbox-id']/button[1]")).click();
				pause(45000);
				waitForElementPresent(userlink);
				driver.findElement(userlink).click();
				pause(5000);
				By emailbody=By.xpath("//*[@class='email_body']");
				emailbodydata = driver.findElement(emailbody).getText();
				logger.info("Email Body Value is"+emailbodydata);
				driver.navigate().to(driver.findElement(approvevideolink).getAttribute("href"));
			}
		}
		return emailbodydata;
	}
	
	public String ReturnEmailBody(String emailId,String emailsubject,String emailtype,String accountname)
	{
		String emailbodydata="";
		if(driver!=null)
		{
			logger.info("Email Type is"+emailtype);
			logger.info("value is"+emailsubject);
			logger.info("Email id is"+emailId);
			pause(10000);
			if(emailtype.contains("mail"))
			{
				By userlink=By.xpath("//*[contains(text(),'"+accountname+"')]/..");
				enterText(mailinatortextbox, emailId);
				click(mailinatorgobuttonlocator);
				waitForElementPresent(userlink);
				clickWithoutJavaScript(userlink);
				switchFrame(mailinatorframeid);
				waitForElement(mailinatoremailbody);
				pause(3000);
				emailbodydata = getText(mailinatoremailbody);
				logger.info("Email Body Value is"+emailbodydata);
				driver.switchTo().defaultContent();
			}

			else 
			{
				logger.info("value is"+emailsubject);
				By userlink=By.xpath("(//*[contains(text(),'system@vbrick.com')]/..//*[contains(text(),'"+emailsubject+"')])[1]");
				By approvevideolink = By.xpath("//*[@class='email_body']/pre/a");
				uncheck_Checkbox(By.id("use-alias"));

				driver.findElement((By.id("inbox-id"))).click();
				driver.findElement(By.xpath("//span[@class='editable button edit-in-progress']/input")).clear();
				logger.info("Email id is@@@@@@@"+emailId.split("@")[0]);
				driver.findElement(By.xpath("//span[@class='editable button edit-in-progress']/input")).sendKeys(emailId.split("@")[0]);
				driver.findElement(By.xpath("//span[@id='inbox-id']/button[1]")).click();
				pause(45000);
				waitForElementPresent(userlink);
				driver.findElement(userlink).click();
				pause(5000);
				By emailbody=By.xpath("//*[@class='email_body']");
				emailbodydata = driver.findElement(emailbody).getText();
				logger.info("Email Body Value is"+emailbodydata);
			}
		}
		return emailbodydata;
	}


	public String ReturnEmailBody(String emailId,String emailsubject)
	{
		String emailbodydata="";
		if(driver!=null)
		{
			logger.info("value is"+emailsubject);
			logger.info("Email id is"+emailId);
			waitForElementPresent(mailinatortextbox);
			if(MAILINATOR_URL.contains("mailinator"))
			{
				By userlink=By.xpath("//*[contains(text(),'"+emailsubject+"')]");
				enterText(mailinatortextbox, emailId);
				click(mailinatorgobuttonlocator);
				waitForElementPresent(userlink);
				click(userlink);
				pause(3000);
				switchFrame(mailinatorframeid);
				waitForElementPresent(mailinatoremailbody);
				pause(5000);
				emailbodydata = getText(mailinatoremailbody);
				logger.info("Email Body Value is"+emailbodydata);
				driver.switchTo().defaultContent();
			}

			else 
			{
				logger.info("value is"+emailsubject);
				By userlink=By.xpath("(//*[contains(text(),'system@vbrick.com')]/..//*[contains(text(),'"+emailsubject+"')])[1]");
				By approvevideolink = By.xpath("//*[@class='email_body']/pre/a");
				uncheck_Checkbox(By.id("use-alias"));

				driver.findElement((By.id("inbox-id"))).click();
				driver.findElement(By.xpath("//span[@class='editable button edit-in-progress']/input")).clear();
				logger.info("Email id is@@@@@@@"+emailId.split("@")[0]);
				driver.findElement(By.xpath("//span[@class='editable button edit-in-progress']/input")).sendKeys(emailId.split("@")[0]);
				driver.findElement(By.xpath("//span[@id='inbox-id']/button[1]")).click();
				pause(45000);
				waitForElementPresent(userlink);
				driver.findElement(userlink).click();
				pause(5000);
				By emailbody=By.xpath("//*[@class='email_body']");
				emailbodydata = driver.findElement(emailbody).getText();
				logger.info("Email Body Value is"+emailbodydata);
			}
		}
		return emailbodydata;
	}

	public String ReturnEmailBodyDataValues(String emailId,String emailsubject)
	{
		String emailbodydata="";
		if(driver!=null)
		{
			logger.info("value is"+emailsubject);
			logger.info("Email id is"+emailId);
			waitForElementPresent(mailinatortextbox);
			if(MAILINATOR_URL.contains("mailinator"))
			{
				By userlink=By.xpath("(//*[contains(text(),'"+emailsubject+"')])[1]");
				enterText(mailinatortextbox, emailId);
				click(mailinatorgobuttonlocator);
				waitForElementPresent(userlink);
				pause(3000);
				clickWithoutJavaScript(userlink);
				pause(3000);
				switchFrame(mailinatorframeid);
				waitForElementPresent(mailinatoremailbody);
				pause(5000);
				emailbodydata = getText(mailinatoremailbody);
				logger.info("Email Body Value is"+emailbodydata);
				driver.switchTo().defaultContent();
			}

			else 
			{
				logger.info("value is"+emailsubject);
				By userlink=By.xpath("(//*[contains(text(),'system@vbrick.com')]/..//*[contains(text(),'"+emailsubject+"')])[1]");
				By approvevideolink = By.xpath("//*[@class='email_body']/pre/a");
				uncheck_Checkbox(By.id("use-alias"));

				driver.findElement((By.id("inbox-id"))).click();
				driver.findElement(By.xpath("//span[@class='editable button edit-in-progress']/input")).clear();
				logger.info("Email id is@@@@@@@"+emailId.split("@")[0]);
				driver.findElement(By.xpath("//span[@class='editable button edit-in-progress']/input")).sendKeys(emailId.split("@")[0]);
				driver.findElement(By.xpath("//span[@id='inbox-id']/button[1]")).click();
				pause(45000);
				waitForElementPresent(userlink);
				driver.findElement(userlink).click();
				pause(5000);
				By emailbody=By.xpath("//*[@class='email_body']");
				emailbodydata = driver.findElement(emailbody).getText();
				logger.info("Email Body Value is"+emailbodydata);
			}
		}
		return emailbodydata;
	}
	
}
