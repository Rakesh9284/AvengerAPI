
package com.vbrick.avenger.pageobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerCategoriesPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerEditVideoSettingsPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerEventDetailsPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerEventWebCastPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerEventsPropertyPage;
import com.vbrick.avenger.dao.AddPresentationprofileBeanPage;
import com.vbrick.avenger.dao.AddUserBeanPage;
import com.vbrick.avenger.dao.AvengerEventDetailsBeanPage;
import com.vbrick.avenger.dao.EventPollBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerEventDetailsPage extends WebElements {
	private static final long serialVersionUID = 1L;
	// --- Variable Declaration
	private WebDriver driver;
	
	private static Logger logger = Logger.getLogger(AvengerEventDetailsPage.class);
	private CustomReport customReport;
	private BasePage basePage;
	private Map<String,String> map;
	By startdatelocator=By.xpath(AvengerEventDetailsPropertyPage.avengereventsdetailspage_startdatelocator.getProperty());
	By enddatelocator=By.xpath(AvengerEventDetailsPropertyPage.avengereventsdetailspage_enddatelocator.getProperty());
	By starttimelocator=By.xpath(AvengerEventDetailsPropertyPage.avengereventsdetailspage_starttimelocator.getProperty());
	By starttimeminuteslocator=By.xpath("(//*[contains(@aria-label,'minutes')])[1]");
	By starttimemeridianlocator=By.xpath("(//*[contains(@name,'Time')]//button)[1]");
	By endtimeminuteslocator=By.xpath("(//*[contains(@aria-label,'minutes')])[2]");
	By endtimemeridianlocator=By.xpath("(//*[contains(@name,'Time')]//button)[2]");
	By endtimelocator=By.xpath(AvengerEventDetailsPropertyPage.avengereventsdetailspage_endtimelocator.getProperty());
	By titlelocator = By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_titlelocator.getProperty());
	By descriptionlocator = By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_descriptionlocator.getProperty());
	By hostidlocator = By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_hostidlocator.getProperty());
	By savebuttonlocator = By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_savebuttonlocator.getProperty());
	By disabledsavebuttonlocator = By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_disabledsavebuttonlocator.getProperty());
	By monthviewlocator=By.xpath(AvengerEventsPropertyPage.avengereventspage_monthviewlocator.getProperty());
	By deleteeventlocator=By.xpath(AvengerEventDetailsPropertyPage.avengereventsdetailspage_deleteeventlocator.getProperty());
	By deleteeventpopuplocator=By.xpath(AvengerCategoriesPropertyPage.deletecategorypopuplocator.getProperty());
	By enableguestaccesslocator = By.name(AvengerEventDetailsPropertyPage.avengereventsdetailspage_enableguestaccesslocator.getProperty());
	By passwordlocator = By.name(AvengerEventDetailsPropertyPage.avengereventsdetailspage_passwordlocator.getProperty());
	By presentationerror = By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_presentationrequirederrorlocator.getProperty());
	By eventtitleerrorlocator = By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_eventtitleerrorlocator.getProperty());
	By hostiderrorlocator = By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_hostiderrorlocator.getProperty());
	By presentationprofilelocator = By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_presentationprofilelocator.getProperty());
	By videosourcelocator = By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_videosourcelocator.getProperty());
	By presentationconflicterror = By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_presentationconflicterror.getProperty());
	By deleteevennotpopuplocator=By.xpath(AvengerCategoriesPropertyPage.deletecategorypopupnolocator.getProperty());
	By webcamandscreensharelocator = By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_webcamandscreensharelocator.getProperty());
	By startwebcastbuttonlocator = By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_startwebcastbuttonlocator.getProperty());
	By cancelbuttonlocator = By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_cancelbuttonlocator.getProperty());
	By inprogresstextlocator = By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_inprogresstextlocator.getProperty());
	By joinwebcastbuttonlocator = By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_joinwebcastbuttonlocator.getProperty());
	By viewinvitationtextlocator = By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailpage_viewinvitationtextlocator.getProperty());
	By okbuttonlocator = By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailpage_okbuttonlocator.getProperty());
	By allavailablegroupslocator = By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailpage_allavailablegroupslocator.getProperty());
	By allavailableuserslocator = By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailpage_allavailableuserslocator.getProperty());
	By addpollbutton=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailpage_addpollbutton.getProperty());
    By pollquestion=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailpage_pollquestion.getProperty());
    By pollanswer1=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailpage_pollanswer1.getProperty());
    By pollanswer2=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailpage_pollanswer2.getProperty());
    By pollanswer3=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailpage_pollanswer3.getProperty());
    By pollanswer4=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailpage_pollanswer4.getProperty());
    By allowmultipleansweryes=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailpage_allowmultipleansweryes.getProperty());
    By allowmultipleanswerno=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailpage_allowmultipleanswerno.getProperty());
    By deletepollbutton=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailpage_deletepollbutton.getProperty());
    By pollquestionerrorrequired=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailpage_pollquestionerrorrequired.getProperty());
    By pollanswererrorrequired=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailpage_pollanswererrorrequired.getProperty());
    By pollanswerfields=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailpage_pollanswerfields.getProperty());
    By groupuserteampagetextboxlocator=By.xpath(AvengerEventDetailsPropertyPage.groupuserteampagetextboxlocator.getProperty());
    By groupuserteampluslocator=By.xpath(AvengerEventDetailsPropertyPage.groupuserteampluslocator.getProperty());
    By donelocator=By.xpath(AvengerEventDetailsPropertyPage.donelocator.getProperty());
    By groupuserteammiminuslocator=By.xpath(AvengerEventDetailsPropertyPage.groupuserteammiminuslocator.getProperty());
    By attendeeslogo = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_attendeeslogo.getProperty());
    By addpolleventmoderator=By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_addpollforeventmoderator.getProperty());
    By createpollbuttoneventmoderator=By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_eventmoderatorpollcreatebutton.getProperty());
    By manualcontrollocator=By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_manualcontrolbutton.getProperty());
    By listingpasswordtextbox=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_listingpasswordlocator.getProperty());
    By startbroadcastlocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_startbroadcastlocator.getProperty());
    By defaultthumbnail = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastspage_defaultthumbnail.getProperty());
    By uploadbackgroundImage = By.name(AvengerEventWebCastPropertyPage.avengereventwebcastspage_uploadBackgroundImage.getProperty());
    By removebackgroundImage = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastspage_removeBackgroundImagebutton.getProperty());
    By imageafterstartingwebcast = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastspage_imagestartwebcast.getProperty());
    By enablePresentaionProfileDownload=By.name(AvengerEventWebCastPropertyPage.avengereventwebcastspage_enablePresentaionDownload.getProperty());
    By addToMyCalenderLocator=By.cssSelector(AvengerEventWebCastPropertyPage.avengereventwebcastpage_addToMyCalender.getProperty());
    By reportslocator = By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_reportsbutton.getProperty());
    By downloadsbutton=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_downloadbutton.getProperty());
	By csvlocator  = By.xpath("//*[contains(@href,'summary/csv')]");
	By allowParticipantSelfSelectAnonymousQuestionlocator=By.name("allowParticipantSelfSelectAnonymousQuestion");
	By acceptpopupforeventcreation=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_accepteventcreationpopupokbutton.getProperty());
	By privateentities=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_listingprivateentities.getProperty());
    By lobbytimelocator=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_lobbytimelocator.getProperty());
    By videosourcetypelocator=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_videosourcetypelocator.getProperty());
    By presenterlocator=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_presenterlocator.getProperty());
    By shortcutlocator=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_shortcutlocator.getProperty());
    By vcsipaddresslocator=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_vcsipaddress.getProperty());
    By tagslocator=By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_tagslocator.getProperty());
    By categorieslocator=By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_categorieslocator.getProperty());
    By unlistwebcastlocator=By.xpath("//*[contains(@ng-model,'webcast.unlisted')]");
    By allpreprodusersandgroups=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_preprodusersandgroups.getProperty());
    By allprivateusersandgroups=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailpage_allprivateusersandgroups.getProperty());
    By endEventLocator=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailpage_endEventButtonLocator.getProperty());
    By endEventOKLocator=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailpage_endEventOKButtonLocator.getProperty());
    By enablepreproduction=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailpage_enablepreproduction.getProperty());
    By preproductiontime=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailpage_preproductiontime.getProperty());
    By accepteventchanges=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_accepteventchanges.getProperty());
    By Broadcast=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_Broadcast.getProperty());
    By startpreproductionbutton=By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_startpreproductionbutton.getProperty());
    public AvengerEventDetailsPage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		
		this.customReport= customReport;
		this.basePage = basePage;
	}

	public void enter_startDate(AvengerEventDetailsBeanPage avengereventdetailsbeanpage)
	{
		enterText(startdatelocator,  avengereventdetailsbeanpage.getStartdate());
		customReport.reporter("Entered Start Date as",avengereventdetailsbeanpage.getStartdate());
	}

	public void enter_endDate(AvengerEventDetailsBeanPage avengereventdetailsbeanpage)
	{
		enterText(enddatelocator,  avengereventdetailsbeanpage.getEnddate());
		customReport.reporter("Entered End Date as",avengereventdetailsbeanpage.getEnddate());
	}

	public void enter_startTime(AvengerEventDetailsBeanPage avengereventdetailsbeanpage)
	{
		enterText(starttimelocator,  avengereventdetailsbeanpage.getStarttime());
		customReport.reporter("Entered Start Time is",avengereventdetailsbeanpage.getStarttime());
	}

	public void change_Host(AvengerEventDetailsBeanPage avengereventdetailsbeanpage)
	{
		selectValuefromDropDown(hostidlocator,  avengereventdetailsbeanpage.getHost());
		customReport.reporter("Entered host ", "");
	}
	

	public String get_Host(String hostName) {
		
		return getText(By.xpath("(//*[contains(@name,'eventAdmins')])//access-entity-assigned-row//*[contains(text(),'"+hostName+"')][1]"));
	}
	
	public void enter_EndTime(AvengerEventDetailsBeanPage avengereventdetailsbeanpage)
	{
		enterText(endtimelocator,  avengereventdetailsbeanpage.getEndtime());
		customReport.reporter("Entered End Date as",avengereventdetailsbeanpage.getStartdate());
	}
	public String get_startDate()
	{
		customReport.reporter("Start Date is",getAttribute(startdatelocator, "value"));
		return getAttribute(startdatelocator, "value");

	}

	public String get_endDate()
	{
		customReport.reporter("End Date is",getAttribute(enddatelocator, "value"));
		return getAttribute(enddatelocator, "value");
	}

	public String get_startTime()
	{
		customReport.reporter("Start Time is",getAttribute(starttimelocator, "value"));
		return getAttribute(starttimelocator, "value");
	}

	public String get_EndTime()
	{
		customReport.reporter("End  Time is",getAttribute(endtimelocator, "value"));
		return  getAttribute(endtimelocator, "value");
	}

	public void click_ChangePresentationButton()
	{
	
		customReport.reporter("Change button is clicked", "");
	}

	public String verify_PresentationProfile(AddPresentationprofileBeanPage addpresentationprofilebeanpage)
	{
		By presentationprofile=By.xpath("//*[contains(text(),'"+addpresentationprofilebeanpage.getName()+"')]");
		return getText(presentationprofile);

	}

	public void enterEventDescription(String eventDescription)
	{
		By descFrame=By.xpath("//*[contains(@class,'wysiwyg')]");
		By desceditor=By.cssSelector("body");
		switchFrame(descFrame);
		enterText_withoutClear(desceditor, eventDescription);
		frameDefaultContent();
	}
	
	private String eventname=null;
	private static int i=1;
	public void create_Event(AvengerEventDetailsBeanPage avengereventdetailsbeanpage,AddPresentationprofileBeanPage addpresentationprofilebeanpage)
	{
		eventname=avengereventdetailsbeanpage.getTitle();
		logger.info("The event page title is"+eventname+i++);
		By presentationlocator = By.xpath("//*[contains(text(),'"+addpresentationprofilebeanpage.getName()+"')]");
		pause(5000);
		click(presentationlocator);
		enterText(titlelocator,  avengereventdetailsbeanpage.getTitle());
		enterText(starttimelocator,  avengereventdetailsbeanpage.getStarttime());
		enterText(endtimelocator,  avengereventdetailsbeanpage.getEndtime());
		pause(7000);
		enterEventDescription(avengereventdetailsbeanpage.getDescription());
		selectValuefromDropDown(hostidlocator,  avengereventdetailsbeanpage.getHost());
		By listingtypelocator = By.xpath("//button[contains(text(),'"+avengereventdetailsbeanpage.getListingtype()+"')]");
		waitForElementPresent(listingtypelocator);
		pause(5000);
		clickUsingSwitch(listingtypelocator);
		By enablecommentslocator = By.xpath("//*[contains(@name,'Chat')][contains(text(),'");
		clickUsingSwitch(enablecommentslocator);
		By enableqalocactor=By.xpath("//*[contains(@name,'QuestionAndAnswer')]/..//*[contains(text(),'");
		clickUsingSwitch(enableqalocactor);
		try{
		/*if(isDisplayed(allowcommentstohost))
		{
			click(allowcommentstohost);
		}*/
		/*if(isDisplayed(allowanonymouscommentslocator))
		{
			click(allowanonymouscommentslocator);
		}
		*/}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		avengereventdetailsbeanpage.setStartdate(getAttribute(startdatelocator, "value"));
		avengereventdetailsbeanpage.setEnddate(getAttribute(enddatelocator, "value"));
		avengereventdetailsbeanpage.setStarttime(getAttribute(starttimelocator, "value"));
		avengereventdetailsbeanpage.setEndtime(getAttribute(endtimelocator, "value"));
		customReport.reporter("Event Name is",avengereventdetailsbeanpage.getTitle());
	}

	
	   

	public AvengerEventsPage click_SaveButton(String ...ar)
	{
		pause(5000);
		clickUsingSwitch(savebuttonlocator);
		customReport.reporter("Clicked on Event Submit Button","");
		customReport.reporter("Save button is clicked", "");
		pause(5000);
		waitForElementPresent(acceptpopupforeventcreation);
		logger.info("Event Creation popup is visible on the page");
		click(acceptpopupforeventcreation);
		logger.info("Clicked on OK for Event Creation popup ");
		return basePage.avengerEventsPage(driver,  customReport, basePage);
	}
	

	public void click_DeleteButton(String command)
	{
		click(deleteeventlocator);	
		if(command.equals("Y"))
		{
			click(deleteeventpopuplocator);
			waitForElementPresent(monthviewlocator);

		}
		else if (command.equals("N")) {
			click(deleteevennotpopuplocator);
			waitForElementPresent(deleteeventlocator);
		}


	}
	
	public boolean verifyEventDeleteButton()
	{
		return locatorsVisibilityAsPerRoles(deleteeventlocator);
	}

	public boolean verifyEventEditButton()
	{
		return locatorsVisibilityAsPerRoles(titlelocator);
	}

	public void selectStartDate(String startdate)
	{
		click(startdatelocator);
		customReport.reporter("Clicked on start date", "");
		pause(5000);
		WebElement datetable = driver.findElement(By.xpath("//li/table/tbody"));

		List <WebElement> coloumns = datetable.findElements(By.tagName("td"));

		for (WebElement cell: coloumns){ 


			if (cell.getText().equals(startdate)){ 
				logger.info("Entered into particular the cell ");
				cell.findElement(By.xpath("//*[@ng-click='select(dt.date)']/span[@class='ng-binding'][contains(text(),'"+startdate+"')]")).click(); 
				logger.info("Selected Start date "+getAttribute(enddatelocator, "value"));
				break; 

			}

		}

	}

	public String check_EventReservationMessage(AddPresentationprofileBeanPage addpresentationprofilebeanpage,  AvengerEventDetailsBeanPage avengereventdetailsbeanpage)
	{
		Map<String, String> eventdetailsmap= new HashMap<String, String>();
		enterText(starttimelocator,  avengereventdetailsbeanpage.getStarttime());
		customReport.reporter("Entered Start Time", "");
		customReport.reporter("Clicked change presentation button", "");
		By reservationnumber = By.xpath("//*[contains(text(),'"+addpresentationprofilebeanpage.getName()+"')]/../td[2]/a/span[2]");
		String number = getText(reservationnumber);
		By reservationlocator = By.xpath("//*[contains(text(),'"+addpresentationprofilebeanpage.getName()+"')]/../td[2]/a/span[1]");
		click(reservationlocator);
		eventdetailsmap.put("number", number);	
		return number;
	}

	public Map<String, String> get_ListForEventReservationOrConflict(AvengerEventDetailsBeanPage avengereventdetailsbeanpage,int value)
	{
		map = new HashMap<String, String>();

		By eventtitlelocator = By.xpath("//*[contains(@ng-show,'event.conflict')]/..");
		By hostlocator = By.xpath("//*[contains(@ng-show,'event.conflict')]/../../p[3]");
		By eventtimelocator = By.xpath("//*[contains(@ng-show,'event.conflict')]/../../p[2]");
		map.put("EventTitle", getTextMultiple(eventtitlelocator,value));
		map.put("EventHost", getTextMultiple(hostlocator,value));
		map.put("DateTime", getTextMultiple(eventtimelocator,value));
		return map;
	}

	public HashMap<String, String> get_EventData()
	{
		HashMap<String, String> eventdata= new HashMap<String, String>();
		eventdata.put("title", getAttribute(titlelocator, "value"));
		eventdata.put("startdate", getAttribute(startdatelocator, "value"));
		eventdata.put("starttime", getAttribute(starttimelocator, "value"));
		eventdata.put("enddate", getAttribute(enddatelocator,"value"));
		eventdata.put("endtime", getAttribute(endtimelocator, "value"));
		//eventdata.put("presentationprofile", getText(presentationprofilelocator)); //Added custom report line for Video Source Type Validation
		eventdata.put("shortcutName", getAttribute(shortcutlocator, "value"));
		return eventdata; 
	}
	
	public HashMap<String, String> get_EventDataforwebcamandscreenshare()
	{
		HashMap<String, String> eventdata= new HashMap<String, String>();
		eventdata.put("title", getAttribute(titlelocator, "value"));
		eventdata.put("startdate", getAttribute(startdatelocator, "value"));
		eventdata.put("starttime", getAttribute(starttimelocator, "value"));
		eventdata.put("enddate", getAttribute(enddatelocator,"value"));
		eventdata.put("endtime", getAttribute(endtimelocator, "value"));
		eventdata.put("presenter",getAttribute(presenterlocator));
		eventdata.put("shortcutName", getAttribute(shortcutlocator, "value"));
		return eventdata; 
	}
	
//	public String verify_videosourcetype()
//	{
//		return getText(videosourcetypelocator);
//	}
	
	public String verify_videosourcetype(){
		Select select = new Select(driver.findElement(By.xpath("//select[contains(@id,'ewVideoSource')]")));
		WebElement option = select.getFirstSelectedOption();
		String defaultItem = option.getText();
		return defaultItem;
	}
	
	public HashMap<String, String> get_EventDataforVideoAddress()
	{
		HashMap<String, String> eventdata= new HashMap<String, String>();
		eventdata.put("title", getAttribute(titlelocator, "value"));
		eventdata.put("startdate", getAttribute(startdatelocator, "value"));
		eventdata.put("starttime", getAttribute(starttimelocator, "value")+" "+getAttribute(starttimeminuteslocator, "value")+" "+getText(starttimemeridianlocator));
		eventdata.put("enddate", getAttribute(enddatelocator,"value"));
		eventdata.put("endtime", getAttribute(endtimelocator, "value")+" "+getAttribute(endtimeminuteslocator, "value")+" "+getText(endtimemeridianlocator));
		eventdata.put("VcSipAddress", getAttribute(vcsipaddresslocator, "value"));
		return eventdata; 
	}
	
	public HashMap<String, String> get_EventData_Optional()
	{
		HashMap<String, String> eventdata= new HashMap<String, String>();
		eventdata.put("shortcutName", getAttribute(shortcutlocator, "value"));
		return eventdata; 
	}
	
	public String getLobbyTime()
	{
		return getText(lobbytimelocator);
	}
	
	public ArrayList<String> get_Categories()
	{

		return getAllWebElementValueslist(categorieslocator);
	}
	
	public ArrayList<String> get_Tags()
	{

		return getAllWebElementValueslist(tagslocator);
	}
	

	public ArrayList<String> get_EventHostUsers()
	{
		By eventhostUsers = hostidlocator;
		return getAllWebElementValueslist(eventhostUsers);
	}
	public ArrayList<String> get_EventHostUsers1()
	{
		By eventhostUsers = By.xpath("//*[contains(@name,'eventAdmins')]//access-entity-assigned-row//*[contains(@class,'description')]/span[1]");
		return getAllWebElementValueslist(eventhostUsers);
	}
	

	public String verifycategory(String newcategory) {
		By categorytextlocator=By.xpath("(//*[contains(text(),'"+newcategory+"')])");
		return getText(categorytextlocator);
	}
	
	public String get_EstimatedAttendees()
	{
		pause(3000);
		By estAttendees = By.xpath("//input[@name='estimatedAttendees']");
		String attendees = getAttribute(estAttendees,"value");
		return attendees;
	}

	public String getvideoID()
	{
		pause(3000);
		String[] currenturl = getCurrentURL().split("/");
		pause(10000);
		logger.info("Video id is"+currenturl[currenturl.length-1]);
		return currenturl[currenturl.length-1];
	}
	

	public String removeEventTitle(AvengerEventDetailsBeanPage avengereventdetailsbeanpage)
	{
		enterText(titlelocator,  avengereventdetailsbeanpage.getTitle());
		customReport.reporter("Removed title", "");
		return getText(eventtitleerrorlocator);

	}

	public void removeHost(AvengerEventDetailsBeanPage avengereventdetailsbeanpage)
	{
		selectValuefromDropDown(hostidlocator,  avengereventdetailsbeanpage.getHost());
		customReport.reporter("Removed host", "");
	
	}

	public boolean check_SaveButtonEnabled()
	{
		return isEnabled(savebuttonlocator);
	}
	
	public boolean check_SaveButtonDisabled(){
		return  isDisplayedWithoutException(disabledsavebuttonlocator);
	}

	public String check_PresentationProfileSelected(AddPresentationprofileBeanPage addpresentationprofilebeanpage)
	{
		By listingtypelocator = By.xpath("//button[contains(text(),'All Users')]");
		waitForElementPresent(listingtypelocator);
		click(listingtypelocator);
		customReport.reporter("Change button clicked", "");
		By presentationlocator = By.xpath("//*[contains(text(),'"+addpresentationprofilebeanpage.getName()+"')]");
		customReport.reporter("Change button clicked", "");
		click(presentationlocator);
		return getText(presentationprofilelocator);
	}

	public Map<String,Boolean> verifyProfileAvailability(AddPresentationprofileBeanPage addpresentationprofilebeanpage,AvengerEventDetailsBeanPage avengereventdetailsbeanpage)
	{
		Map<String, Boolean> presentationmap=new HashMap<String, Boolean>();

		enterText(starttimelocator,  avengereventdetailsbeanpage.getStarttime());
		customReport.reporter("Entered Start Time", "");
		By presentationprofile = By.xpath("//*[contains(text(),'"+addpresentationprofilebeanpage.getName()+"')]");
		presentationmap.put("presentationprofile", isDisplayedWithoutException(presentationprofile));
		By availabilitylocator = By.xpath("//*[contains(text(),'"+addpresentationprofilebeanpage.getName()+"')]/../td/div/div[2]/*[contains(@ng-show,'profile.available')]");
		presentationmap.put("available", isDisplayedWithoutException(availabilitylocator));
		By notavailablelocator = By.xpath("//*[contains(text(),'"+addpresentationprofilebeanpage.getName()+"')]/../td/div/div[2]/span[2]");
		presentationmap.put("notavailable", isDisplayedWithoutException(notavailablelocator));
		return presentationmap;
	}

	public Map<String,String> view_PresentationConflictErrorMessage(AddPresentationprofileBeanPage addpresentationprofilebeanpage)
	{
		Map<String,String> conflictingerrormap = new HashMap<String, String>();
		conflictingerrormap.put("ErrorMessage",getText(presentationconflicterror));
		By conflictbuttonlocator = By.xpath("//*[contains(text(),'"+addpresentationprofilebeanpage.getName()+"')]/../../th[3]/div[1]/button[2]");
		conflictingerrormap.put("ConflictButtonText", getText(conflictbuttonlocator));
		return conflictingerrormap;

	}

	public void click_ViewConflictButton(AddPresentationprofileBeanPage addpresentationprofilebeanpage)
	{

		By conflictbuttonlocator = By.xpath("//*[contains(text(),'"+addpresentationprofilebeanpage.getName()+"')]/../td[2]/a/span[1]");
		click(conflictbuttonlocator);
	}

	public boolean verify_StartWebcastPresent()
	{
		return isDisplayedWithoutException(startwebcastbuttonlocator);
	}
	
	public boolean verify_JoinWebcastPresent()
	{
		return isDisplayed(joinwebcastbuttonlocator);
	}

	public AvengerEventsPage click_CancelButton()
	{
		clickUsingSwitch(cancelbuttonlocator);
		customReport.reporter("Cancel Button Clicked", "");
		return basePage.avengerEventsPage(driver,  customReport, basePage);
	}

	public AvengerEventWebCastPage start_Webcast()
	{

		By recordbuttonlocator = By.xpath("(//*[@class='glyphicons record']/..)[2]");
		clickWithoutJavaScript(startwebcastbuttonlocator);
		customReport.reporter("Start Webcast Button is clicked", "");
		waitForElementPresent(startbroadcastlocator);
		logger.info("BroadCast button is visible after clicking Start webcast");
		return basePage.avengerEventWebCastPage(driver,  customReport, basePage);
	}

	public AvengerEventWebCastPage start_WebcastErrorMessage()
	{
		By leavewebcastlocator = By.xpath(AvengerEventWebCastPropertyPage.avengereventwebcastpage_leavewebcastlocator.getProperty());
		click(startwebcastbuttonlocator);
		customReport.reporter("Start Webcast Button is clicked", "");
		return basePage.avengerEventWebCastPage(driver,  customReport, basePage);
	}
	
	public String returnInProgressText()
	{
		return getText(inprogresstextlocator);
	}

	public String deleteButtonVisibility()
	{
		return String.valueOf(elements(deleteeventlocator));
	}
	public AvengerEventWebCastPage join_Webcast()
	{
		pause(5000);
		return basePage.avengerEventWebCastPage(driver,  customReport, basePage);
	}
	public AvengerEventWebCastPage Click_join_Webcast()
	{
		click(joinwebcastbuttonlocator);
		customReport.reporter("Join Webcast Button is clicked", "");
		return basePage.avengerEventWebCastPage(driver,  customReport, basePage);
	}

	public void editEventDetails(AvengerEventDetailsBeanPage avengereventdetailsbeanpage)
	{
		enterText(titlelocator,  avengereventdetailsbeanpage.getTitle());
		customReport.reporter("Changed title is", getAttribute(titlelocator, "value"));
	}

	public Map<String, String> verifyEventDetailsEditable()
	{
		map =new HashMap<String, String>();
		map.put("EventTitle",String.valueOf(elements(titlelocator)));
		map.put("EventDescription",String.valueOf(elements(descriptionlocator)));
		map.put("Startdate", String.valueOf(elements(startdatelocator)));
		map.put("Enddate", String.valueOf(elements(enddatelocator)));
		map.put("Starttime",String.valueOf(elements(starttimelocator)));
		map.put("EndTime", String.valueOf(elements(endtimelocator)));
		map.put("Host",String.valueOf(elements(hostidlocator)));
		return map;
	}

	public Map<String, String> getEventDetailsFromJoinPage()
	{
		By eventdetailslocator = By.xpath("//*[@ng-show='webcast.description']/../div/div");

		Map<String,String> map =new HashMap<String, String>();
		map.put("EventTitle",getTextMultiple(eventdetailslocator, 0));
		map.put("EventDescription",getTextMultiple(eventdetailslocator, 1));
		map.put("Startdate", getTextMultiple(eventdetailslocator, 2));
		map.put("Enddate", getTextMultiple(eventdetailslocator, 3));
		return map;	

	}

	public void clickViewInvitationText()
	{
		click(viewinvitationtextlocator);
		customReport.reporter("ViewInvitationText button is clicked", "");
		pause(5000);
	}
	
	public String getWebcastlink(){
	
		return getText(viewinvitationtextlocator);
	}

	public Map<String, String> getViewInvitationTextDetails(AvengerEventDetailsBeanPage avengereventdetailsbeanpage)
	{
		Map<String, String> map =new HashMap<String, String>();
		By eventtitlelocator=By.xpath("//resource-schedule-dialog//*[contains(@class,'exclamation')]/..");
		map.put("EventTitle",getText(eventtitlelocator));

		By eventdescription= By.xpath("//*[contains(text(),'"+avengereventdetailsbeanpage.getDescription()+"')]");
		map.put("EventDescription",getText(eventdescription));
		By invitationlinklocator = By.xpath("//a");

		map.put("InvitationLink",driver.findElement(invitationlinklocator).getAttribute("href"));
		logger.info("InvitationLink" +driver.findElement(invitationlinklocator).getAttribute("href"));
        click(invitationlinklocator);
		return map;
	}



	public AvengerEventDetailsPage getDriverContorl(WebDriver driver,  CustomReport customReport)
	{
		return basePage.avengerEventDetailsPage(driver,  customReport, basePage);
	}

	public String uploadPPT(AvengerEventDetailsBeanPage beanpage)
	{
		By pptupload = By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailpage_pptuploadlocator.getProperty());
		logger.info("file path is"+beanpage.getPPTPath());
		enterText_FileUpload(pptupload,  beanpage.getPPTPath());  
		pause(15000);
        By pptlocator=By.xpath("//*[contains(@class,'selected-ppt-file')]");
        return getText(pptlocator);
	}

	public String getPPTText()
	{
		pause(25000);
		By ppttextlocator = By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailpage_ppttextlocator.getProperty());
		return getText(ppttextlocator);
	}

	public void uncheckCommentsCheckBox(AvengerEventDetailsBeanPage avengerEventDetailsBeanPage)
	{
		By enablecommentslocator = By.xpath("//*[contains(@name,'ChatEnabled')][contains(text(),'");
		uncheck_Checkbox(enablecommentslocator);
	}

	
	public void click_Comments()
	{
		By commentextlocator=By.xpath("//*[@ng-show='allowCommentDownload()']");
		click(commentextlocator);
	}

	public String getpptextensionerrorText(){
		By ppterrortext=By.xpath("//*[@ng-show='eventForm.presentationFile.$error.fileType']");
		return getText(ppterrortext);
	}
	public void deletePPT(){
		By deletepptlocator=By.xpath("//*[@ng-click='removePresentationFile()']");
		click(deletepptlocator);
		customReport.reporter("deleted ppt", "");
		pause(5000);
	}
	public boolean verifypptDeletion(AvengerEventDetailsBeanPage avengereventdetailsbeanpage) {
		By pptlocator=By.xpath("//*[@ng-show='webcast.presentationFile']");
		return isDisplayedWithoutException(pptlocator);
	}

	public String getInactiveDeviceErrorText()
	{
		By inactivedeviceerrorlocator = By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailpage_inactivedeviceerrormessage.getProperty());
		return getAttribute(inactivedeviceerrorlocator, "innerHTML");
	}

	public String checkPresentationInactiveMessage(String ppname)
	{
		By inactivemessage = By.xpath("//*[contains(text(),'"+ppname+"')]/../td/div/div[2]/span[3]");
		return getAttribute(inactivemessage, "uib-tooltip");
	}

	public String checkWhetherEncoderStarting()
	{
		By encoderstartlocator = By.xpath("//*[contains(text(),'Encoder')]");
		return getText(encoderstartlocator);
	}

	public void getBrowserSwitchControl(String text)
	{
		By textlocator = By.xpath("//*[contains(text(),'"+text+"')]");
		browserSwitch(text);
	}
	
	public AvengerEventReportsPage clickReports()
	{
		
		click(reportslocator);
		return basePage.avengerEventReportsPage(driver,  customReport, basePage);
	}
	
	public void clickCSV()
	{
		pause(4000);	
		click(downloadsbutton);
			pause(4000);
			waitForElementPresent(csvlocator);
	    	click(csvlocator);
		
	}

	public void click_usersTab()
	{
		By clickusers=By.xpath("//*[@ng-show='webcast.userIds.length']/..");
		click(clickusers);
	}
	
	public void add_UsersGroups(ArrayList<String> userslist)
	{
		enterText(groupuserteampagetextboxlocator,  userslist.get(0));
		click(groupuserteampagetextboxlocator);
		pause(3000);
		click(groupuserteampluslocator);
		click(donelocator);
	}    
	  
	public void remove_Users(ArrayList<String> userslist)
	{
		enterText(groupuserteampagetextboxlocator,  userslist.get(0));
		click(groupuserteampagetextboxlocator);
		click(donelocator);
		clickUsingSwitch(groupuserteammiminuslocator);
	}    
	
	
	public void clickGroupsTab()
	{
		By clickgroups=By.xpath("//*[@ng-show='webcast.groupIds.length']/..");
		click(clickgroups);
	}
	public void add_Groups(ArrayList<String> groupslist)
	{
		enterText(groupuserteampagetextboxlocator,  groupslist.get(0));
		customReport.reporter("Group name entered in text box is", groupslist.get(0));
		click(groupuserteampagetextboxlocator);
		customReport.reporter("Clicked on Groupuserteamtextbox","");
		clickUsingSwitch(groupuserteampluslocator);
		customReport.reporter("Clicked on Group plus locator","");
		clickUsingSwitch(donelocator);
		customReport.reporter("Clicked on Done Locator","");
	}
	
	public void entergroupsorusersorteam(String groupslist)
	{
	enterText(groupuserteampagetextboxlocator,  groupslist);
	click(groupuserteampagetextboxlocator);
	}
	
	/**
	 * These methods Return all the groups which are available in Available Groups Section.
	 * @return 
	 */
	public List<String> get_allAvailableGroups()
	{
		List<WebElement> allavailablegroups=getAllWebElementValues(allavailablegroupslocator);
		List<String> availablegroups=new ArrayList<String>();
		for (WebElement webElement : allavailablegroups) {
			logger.info("The Groups text are"+webElement.getText());
			availablegroups.add(webElement.getText());
		}
		return availablegroups;
	}
	
	/**
	 * These methods Return all the Users which are available in Available Groups Section.
	 * @return 
	 */
	public List<String> get_allAvailableUsers()
	{
		List<WebElement> allavailableusers=getAllWebElementValues(allavailableuserslocator);
		List<String> availableusers=new ArrayList<String>();
		pause(6000);
		for (WebElement webElement : allavailableusers) {
			availableusers.add(webElement.getText());
			logger.info("users in available users"+webElement.getText());
		}
		return availableusers;
	}

	/**
	 * These methods Return all the Users which are available in Assigned Groups Section.
	 * @return 
	 */
	
	public List<String> get_allPreprodUsersandGroups() {
		return getAllWebElementValueslist(allpreprodusersandgroups);
	}
	
	public List<String> get_allprivateusersandgroups() {
		return getAllWebElementValueslist(allprivateusersandgroups);
	}
	

	public boolean verifyEventTitle()
	{
		return isDisplayed(titlelocator);
	}
	public void click_EnablePollButton(String pollbuttonStatus)
	{
	By pollButtonLocator=By.xpath("(//*[contains(text(),'"+pollbuttonStatus+"')])[2]");
		click(pollButtonLocator);
	}
	public void click_EnableChatButton(String pollbuttonStatus)
	{
	By pollButtonLocator=By.xpath("(//*[contains(text(),'"+pollbuttonStatus+"')])[3]");
		click(pollButtonLocator);
	}
	public void click_EnableQuestionandAnswers(String pollbuttonStatus)
	{
	By pollButtonLocator=By.xpath("(//*[contains(text(),'"+pollbuttonStatus+"')])[4]");
		click(pollButtonLocator);
	}
	
	/**
	 * method is used to Click on AddPoll Button in Events Page.
	 */
	public void clickAddPollButton()
	{
		click(addpollbutton);
	}

	public void addPoll(EventPollBeanPage eventpollbeanpage,int index)
	{
		enterTextMultiple(pollquestion,  eventpollbeanpage.getPollquestion(), index);
		enterTextMultiple(pollanswer1,  eventpollbeanpage.getPollanswer1(), index);
		enterTextMultiple(pollanswer2,  eventpollbeanpage.getPollanswer2(), index);
		enterTextMultiple(pollanswer3,  eventpollbeanpage.getPollanswer3(), index);
		enterTextMultiple(pollanswer4,  eventpollbeanpage.getPollanswer4(), index);
		if(eventpollbeanpage.getAllowmultipleanswer().equals("yes"))
		clickMultiple(allowmultipleansweryes,  index);	
		else
		clickMultiple(allowmultipleanswerno,  index);	
	}
	public Map<String, String> getenablePollDetails(int index)
	{
		Map<String,String> polldetails= new HashMap<String, String>();
		polldetails.put("pollquestion",getAttributeMultiple(pollquestion, "value",  index));
		polldetails.put("pollanswer1",getAttributeMultiple(pollanswer1, "value",  index));
		polldetails.put("pollanswer2",getAttributeMultiple(pollanswer2, "value",  index));
		polldetails.put("pollanswer3",getAttributeMultiple(pollanswer3, "value",  index));
		polldetails.put("pollanswer4",getAttributeMultiple(pollanswer4, "value",  index));
		polldetails.put("allowmultipleansweryes",String.valueOf(isSelectedMultiple(allowmultipleansweryes,  index)));	
		return polldetails;
	}
	
	public ArrayList<String> verify_RequiredFields()
	{
		ArrayList<String> errorlist= new ArrayList<String>();
		clearWebElementTextusingbackspace(pollquestion,  getAttribute(pollquestion, "value").length());
		clearWebElementTextusingbackspace(pollanswer1,  getAttribute(pollanswer1, "value").length());
		clearWebElementTextusingbackspace(pollanswer2,  getAttribute(pollanswer2, "value").length());
		clearWebElementTextusingbackspace(pollanswer3,  getAttribute(pollanswer3, "value").length());
		clearWebElementTextusingbackspace(pollanswer4,  getAttribute(pollanswer4, "value").length());
		errorlist.add(String.valueOf(elements(pollanswerfields)));
		return errorlist;
	}
	
	public String getBackGroundColor()
	{
		By color =By.xpath("//*[@ng-form='poll.form']");
    	return getAttribute(color, "class");
	}
	public void deletepoll(int index)
	{
		clickMultiple(deletepollbutton,  index);
	}
	public int verifyDeletePoll()
	{
		return elements(pollquestion);
	}
	public ArrayList<String> verifyPollVisibility(EventPollBeanPage beanPage)
	{
		ArrayList<String> pollverification = new ArrayList<String>();
		
		By pollquestion = By.xpath("//*[contains(text(),'"+beanPage.getPollquestion()+"')]");
		getTagName(pollquestion);
		getTagName(pollanswer1);
				
	   return pollverification;		
		}	
	
	public String verify_label(String label)
	{
		By labelvalue=By.xpath("//*[contains(text(),'"+label+"')]");
	   return getText(labelvalue);
	}
	
	
	public void searchUser(AddUserBeanPage addUserBeanPage)
	{
		By searchuser = By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_availableuserorgroupsearch.getProperty());
		enterTextDisplayedElement(searchuser,  addUserBeanPage.getFirstname());
		pause(3000);
	}
	
	public void clickAllowCommentsToHost()
	{
		By allowcommentstohost = By.xpath(AvengerEventDetailsPropertyPage.avengereventdetailspage_allowcommentstohost.getProperty());
		click(allowcommentstohost);
		logger.info("Comments to only host is enabled");
		
	}
	
	public String verifyAutomatedWebcast(String automatedwebcast){
		By automatedwebcaststatus = By.xpath("//*[@ng-model='webcast.automatedWebcast' and  text()='"+automatedwebcast+"']");
		return getAttribute(automatedwebcaststatus,"class");
		
	}
	
	public void  clickAutomatedWebcast(String automatedwebcast){
		By automatedwebcaststatus = By.xpath("//*[@ng-model='webcast.automatedWebcast' and  text()='"+automatedwebcast+"']");
		clickUsingSwitch(automatedwebcaststatus);
		
	}

	public void allowParticipantSelfSelectAnonymousQuestion()
	{
		click(allowParticipantSelfSelectAnonymousQuestionlocator);
	}
	
	public void add_EventModeratorsAsAccountAdmin(AddUserBeanPage adduserbeanpage)
	{
		By eventmoderatorlocator=By.xpath("//*[@ng-model='insight.query']");
		By doneButtonLocator = By.xpath("(//*[@ng-model='insight.query']/../button)[1]");
		enterText(eventmoderatorlocator,  adduserbeanpage.getFirstname()+" "+adduserbeanpage.getLastname());
		pause(5000);
		clickEnter(eventmoderatorlocator);
		clickUsingSwitch(eventmoderatorlocator);
		clickUsingSwitch(eventmoderatorlocator);
		clickUsingSwitch(eventmoderatorlocator);
		By userlocator=By.xpath("//*[contains(text(),'"+adduserbeanpage.getFirstname()+"')]/../../..//span[@class='glyphicons plus']/..");
		waitForElement(userlocator);
		pause(5000);
		clickUsingSwitch(userlocator);
		pause(5000);
		clickUsingSwitch(doneButtonLocator);
        logger.info("Clicked on Done BUtton Locator");
	}
	
	public void add_EventModeratorsAsMediaViewer(AddUserBeanPage adduserbeanpage)
	{
		By eventmoderatorlocator=By.xpath("//*[@ng-model='insight.query']");
		By doneButtonLocator = By.xpath("(//*[@ng-model='insight.query']/../button)[1]");
		By userlocator=By.xpath("//*[contains(text(),'"+adduserbeanpage.getFirstname()+"')]/../../..//span[@class='glyphicons plus']/..");
		enterText(eventmoderatorlocator,  adduserbeanpage.getFirstname()+" "+adduserbeanpage.getLastname());
		pause(5000);
		clickEnter(eventmoderatorlocator);
		clickUsingSwitch(eventmoderatorlocator);
		clickUsingSwitch(eventmoderatorlocator);
		clickUsingSwitch(eventmoderatorlocator);
		waitForElement(userlocator);
		pause(5000);
		clickUsingSwitch(userlocator);
		pause(5000);
		clickUsingSwitch(doneButtonLocator);
        logger.info("Clicked on Done BUtton Locator");
	}
	
	public void clickAddPollButtonForEventModerator(){
		click(addpolleventmoderator);
	}
	
	 public void click_EventModeratorPollCreatebutton(){
		 click(createpollbuttoneventmoderator);
	 }
	 
	 public String verifydeletegroup(String labelname){
		 By labellocator = By.xpath("//*[contains(text(),'"+labelname+"')]");
		 return String.valueOf(elements(labellocator));
	 }
	 
	 public AvengerEventWebCastPage click_ManualControl()
	 {
		 click(manualcontrollocator);
		 customReport.reporter("Clicked on Manual Control", "");
		 return basePage.avengerEventWebCastPage(driver,  customReport, basePage);
	 }

	 public boolean verifyManualcontrolforautomatedwebcast(){
			return wait(manualcontrollocator);
		 }
	 
	 public String verify_EventListingtype(String listingstatus){

		 By Listingstatuslocator = By.xpath("//button[contains(text(),'"+listingstatus+"')]");
		 return getAttribute(Listingstatuslocator);
	 }

	 public void setEventListingPassword(String password){
		 waitForElement(listingpasswordtextbox);
		 logger.info("Listing Password text box is visible ");
		 enterText(listingpasswordtextbox, password);
	 }

	 public void deleteEventListingPassword(){
		 clearWebElementTextusingbackspace(listingpasswordtextbox, 13);
		 logger.info("Cleared event listing password textbox");
	 }
	 
	 public boolean verify_eventlisting(String listingstatus){
		 By Listingstatuslocator = By.xpath("//button[contains(@class,'active') and contains(text(),'"+listingstatus+"')]");
		 return isDisplayedWithoutException(Listingstatuslocator);
	 }
	 
	 public boolean verify_unlisted(){
		 By unlistedcheckbox = By.xpath("//input[contains(@id,'ewUnlisted')]");
		 return isSelected(unlistedcheckbox);
	 }
	 
	 public String verify_defaultthumbnail()
		{
		By videobackgroundimage=By.xpath("//*[contains(@class,'preview-image')]");
		pause(5000);
		return getAttribute(videobackgroundimage,"src");
		
		}
	 
	 
	 public void uploadBackgroundImage(String path)
		{
			logger.info("file path is"+path);
			enterText_FileUpload(uploadbackgroundImage, path);  
			pause(15000);

		}
	 public void click_removebackgroundImageButton()
		{
			click(removebackgroundImage);
			customReport.reporter("Change button is clicked", "");
		}
	 public void clickListingTypeStatus(String libraryStatus)
	 {
			By statuslocator = By.xpath("//*[contains(text(),'Listing Type')]/..//*[contains(text(),'"+libraryStatus+"')]");
			click(statuslocator);
			customReport.reporter("clicked on Listing Type access control",libraryStatus);
	 }
	 public void click_FillSpace(String Space)
	 {
		 By backgroundSize = By.xpath("//*[contains(text(),'"+Space+"')]");
		 click(backgroundSize);
		 customReport.reporter("Selected Background Image size as:", Space);
	 }
	
	 public void clickControlSlides(String username)
		{
		    By controlslides = By.xpath("//div[contains(text(),'"+username+"')]/..//button[@ng-model='insight.moderatorWithSlideControl']");
		    pause(5000);
			click(controlslides);
			customReport.reporter("Control Slides button is clicked", "");	
		}
	 
	 public String get_ControlSlidesStatus(String username)
		{
		    By controlslides = By.xpath("//div[contains(text(),'"+username+"')]/..//button[@ng-model='insight.moderatorWithSlideControl']");
		    
			return getAttribute(controlslides,"class");
		}
	

	 public void clickOk()
	 {
			click(okbuttonlocator);
			 pause(5000);
			customReport.reporter("Ok button is clicked on Start Webcast enforce Message", "");	
		}
	 
	 public AvengerEventsPage clickEnforceSaveOk()
	 {
		 	
		 	logger.info("Error Message ok button is visible on the page");
			click(okbuttonlocator);
			logger.info("Clicked on OK button popup for error message");
			waitForElementPresent(acceptpopupforeventcreation);
			logger.info("save button popup is visible on the page");
			click(acceptpopupforeventcreation);
			waitForElementPresent(acceptpopupforeventcreation);
			clickUsingSwitch(acceptpopupforeventcreation);
			logger.info("Clicked on event cration save button popup");	
			return basePage.avengerEventsPage(driver, customReport, basePage);
		}
	 
	 public void clickQAButtonCSVDownload()
	    {
	    	By questioncsvlocator=By.xpath("//*[contains(@class,'question_mark')]/..");
	        click(questioncsvlocator);
	    }
	 
	 public void enablePresentaionProfileDownload(){
		 
		 check_Checkbox(enablePresentaionProfileDownload);
		 logger.info("Checked on ppt download");
	 }
	 
	 public void clickAddToMyCalender(){
		 
		 click(addToMyCalenderLocator);
		 pause(6000);
		 logger.info("Clicked on Add To My Calender ");
	 }
	 
	 public void click_SaveButtonWithoutverifymonthview()
		{
			clickUsingSwitch(savebuttonlocator);
			customReport.reporter("Clicked on Event Submit Button","");
			customReport.reporter("Save button is clicked", "");
		}
		public AvengerEventWebCastPage start_Webcastwithoutwaitstartboardcast()
		{
	
			By recordbuttonlocator = By.xpath("(//*[@class='glyphicons record']/..)[2]");
			clickUsingSwitch(startwebcastbuttonlocator);
			customReport.reporter("Start Webcast Button is clicked", "");
			logger.info("BroadCast button is visible after clicking Start webcast");
			return basePage.avengerEventWebCastPage(driver,  customReport, basePage);
		}
		
		 public void click_endEventButton()
			{
			    pause(10000);
			    click(endEventLocator);
				customReport.reporter("Clicked on End Event Button","");
			}
		 
		 public void click_endEventOKButton()
			{
				click(endEventOKLocator);
				customReport.reporter("Clicked on OK for End Event Button","");
			}
		
		public boolean verifyPPTAvalible(){
			By replacePPTTextLocator=By.xpath("//*[contains(text(),'Replace File')]");
			return isDisplayed(replacePPTTextLocator);
		}
		
		
	    public ArrayList<String> getAllModerators(){
		     By moderatorslocator=By.xpath("(//*[@class='cdk-virtual-scroll-content-wrapper'])[4]//div[@class='pP8HT__descriptionCell']/span[1]");
		     return getAllWebElementValueslist(moderatorslocator);
		}
		  
		public void clickUnlistthisWebcastCheckbox(){
			check_Checkbox(unlistwebcastlocator);
		}
		public void preproductionenable() throws InterruptedException
		{
			Thread.sleep(5000);
			driver.findElement (By.xpath("//*[@name='preProductionEnabled']")).click();
		   driver.findElement(By.xpath("//*[contains(@name,'preProdDuration')]"));
			
		}
		public void enablepreproduction(String ...ar)
		{
			pause(5000);
			clickUsingSwitch(savebuttonlocator);
			customReport.reporter("Clicked on Event Submit Button","");
			customReport.reporter("Save button is clicked", "");
			pause(5000);
			waitForElementPresent(accepteventchanges);
			logger.info("Event changes popup is visible on the page");
			click(accepteventchanges);
			logger.info("accepted popup for eventchanges");
			click(startpreproductionbutton);
}
		public void enter_starttimeminutes(AvengerEventDetailsBeanPage avengereventdetailsbeanpage)
		{
			enterText(starttimeminuteslocator,  avengereventdetailsbeanpage.getStarttime());
			customReport.reporter("Entered Start Time as",avengereventdetailsbeanpage.getStarttime());
		}
}

