package com.vbrick.avenger.pageobjects;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerEventDetailsPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerEventWebCastPropertyPage;
import com.vbrick.avenger.dao.EventPollBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerEventReportsPage extends WebElements{

	WebDriver driver;
	CustomReport customReport;
	private BasePage basePage;

	private static Logger logger = Logger.getLogger(AvengerEventReportsPage.class);
	
	By totalresponses=By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_totalresponseslocator.getProperty());
	By pollresultscsvbutton=By.xpath("//*[contains(@href,'poll-results/csv')]");
	By closebuttoneventreportpage=By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_closebuttoneventreportpage.getProperty());
	By reportattendeeinternalcount=By.xpath("(//footer)[1]/div[1]/span");
	By reportattendeeinternalcounttime=By.xpath("(//footer)[2]/div[1]/span");
	By reportattendeeguestcount=By.xpath("(//footer)[1]/div[2]/span");
	By reportattendeeguestcounttime=By.xpath("(//footer)[2]/div[2]/span");
	By attendeetrendscsv=By.xpath("//*[contains(@href,'summary/csv')]");
	By averageviewingtime=By.xpath("//*[contains(@data,'eventSummary.averageTime')]/h2");	
	By totalviewingtime=By.xpath("//*[contains(@data,'eventSummary.attendeesDuration')]/h2");
	By guestcount=By.xpath("(//footer)[1]/div[2]/span");
	By guesttime=By.xpath("(//footer)[2]/div[2]/span");
	By internaltime=By.xpath("(//footer)[2]/div[1]/span");
	By downloadsbutton=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_downloadbutton.getProperty());
	
	public AvengerEventReportsPage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		
		this.customReport = customReport;
		this.basePage=basePage;
	}
	
	
	public String verify_label(String label)
	{
		By labelvalue=By.xpath("//*[contains(text(),'"+label+"')]");
	   return getText(labelvalue);
	}
	
	public String getPollAnswerCount(EventPollBeanPage eventPollBeanPage, int option)
	{
		pause(5000);
		By pollanswercount = By.xpath("(//*[contains(text(),'"+eventPollBeanPage.getPollquestion()+"')]/../..//*[contains(@ng-repeat,'poll.answers')]/span)["+option+"]");
		return getText(pollanswercount);
	}
	
	public String getEventReportDetails(int option)
	{
		By reportitemlocator=By.xpath("//*[@class='report-item-inner']");
		return getTextMultiple(reportitemlocator,  option);
	}
	
	public String getTotalResponses(String index)
	{
		By totalresponse=By.xpath("(//*[contains(text(),'Total Responses')])["+index+"]/../span");
		logger.info("The total respose is "+getText(totalresponse));
		return getText(totalresponse); 
	}
	
	public void click_pollResultsCSVButton()
	{
		pause(4000);
		click(downloadsbutton);
		pause(4000);
		click(pollresultscsvbutton);
	}
	
	public Map<String, String> getEventReportDetails()
	{
		By totalattendeeslocator=By.xpath("//*[contains(@data,'eventSummary.userCount')]/h2");
		By averageviewingtime=By.xpath("//*[contains(@data,'eventSummary.averageTime')]/h2");
		By viewbyzoneseries=By.xpath("//*[contains(@data,'Zone')]");
		By viewbytimeseries=By.xpath("//*[contains(@data,'viewsByTime')]");
		Map<String, String> eventreportmap=new HashMap<String, String>();
		eventreportmap.put("totalattendees", getText(totalattendeeslocator));
		eventreportmap.put("averageviewingtime", getText(averageviewingtime));
		eventreportmap.put("zoneseries",String.valueOf(isDisplayed(viewbyzoneseries)));
		eventreportmap.put("timeseries",String.valueOf(isDisplayed(viewbytimeseries)));
		eventreportmap.put("internal",getText(reportattendeeinternalcount));
		eventreportmap.put("totalviewingtime",getText(totalviewingtime));
		eventreportmap.put("guesttime",getText(guesttime));
		eventreportmap.put("guestcount",getText(guestcount));
		eventreportmap.put("internaltime",getText(internaltime));
		return eventreportmap;
		
	}
	
	public void clickcloseeventreportpage(){
		click(closebuttoneventreportpage) ;
	 }


	public LinkedHashMap<String, String> guestUserReportDetails() {
		// TODO Auto-generated method stub
		LinkedHashMap<String,String> guestreportdetails=new LinkedHashMap<String, String>();
		guestreportdetails.put("internal",getText(reportattendeeinternalcount));
		guestreportdetails.put("guestlogged",getText(reportattendeeguestcount));
		guestreportdetails.put("internalloggedtime",getText(reportattendeeinternalcounttime));
		guestreportdetails.put("guestloggedtime",getText(reportattendeeguestcounttime));
		guestreportdetails.put("averagetime",getText(averageviewingtime));
		return guestreportdetails;
	}


	public void click_attendeetrendscsv() {
		// TODO Auto-generated method stub
		click(downloadsbutton);
		pause(4000);
		waitForElementPresent(attendeetrendscsv);
		click(attendeetrendscsv);
		logger.info("Clicked on CSV Trends");
	}
}