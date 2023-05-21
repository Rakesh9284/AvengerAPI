
package com.vbrick.avenger.pageobjects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerEditVideoSettingsPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerUploadPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerVideoCommentsPropertyPage;
import com.vbrick.avenger.dao.AvengerEditVideoSettingsBeanPage;
import com.vbrick.avenger.dao.AvengerManageCustomFieldsBeanPage;
import com.vbrick.avenger.dao.FileUploadBeanPage;
import com.vbrick.avenger.funUtil.DateTime;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerEditVideoSettingsPage extends WebElements {
	private static final long serialVersionUID = 1L;
	// --- Variable Declaration
	private WebDriver driver;
	
	private static Logger logger = Logger.getLogger(AvengerEditVideoSettingsPage.class);
	CustomReport customReport;
	private BasePage basePage;


	public AvengerEditVideoSettingsPage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		
		this.customReport= customReport;
		this.basePage=basePage;
	}
	 By searchimagelocator = By.xpath("//*[@class='glyphicons search icon']");
	By titlelocator = By.name(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_titlelocator.getProperty());
	By descriptionlocator=By.id(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_descriptionlocator.getProperty());
	By categorieslocator=By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_categorieslocator.getProperty());
	By librarieslocator=By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_librarieslocator.getProperty());
	By tagslocator=By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_tagslocator.getProperty());
	By saveandexitbuttonlocator=By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_saveandexitbuttonlocator.getProperty());
	By addtagbuttonlocator=By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_addtagbuttonlocator.getProperty());
	By alltagslocator=By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_tagslocator.getProperty());
	By titleerrortextlocator=By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_titleerrortextlocator.getProperty());
	By listviewbuttonlocator=By.xpath(AvengerUploadPropertyPage.uploadpg_listviewbuttonlocator.getProperty());
	By cancelbuttonlocator = By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_cancelbuttonlocator.getProperty());
	By getcategorieslocator=By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_getcategorieslocator.getProperty());
	By blanklibraryerrortext= By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_blanklibraryerrortext.getProperty());
	By enableanonymousviewing= By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_enableanonymousviewing.getProperty());
	By supplementalfiles=By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_supplementalfiles.getProperty());
	By contentitle=By.name(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_contenttitle.getProperty());
	By okbutton=By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_okbutton.getProperty());
	By cancelbutton=By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_cancelbutton.getProperty());
	By supplementfiles=By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_allSupplementalFiles.getProperty());
	By editaccesslocator = By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_editVideoAccess.getProperty());
	By searchdonelocator = By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_searchdonelocator.getProperty());
	By supplementmediatablocator = By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_supplementalMedia.getProperty());
	By enabledownloadcheckbox=By.xpath("//*[contains(@aria-label,'Enable downloads')]");
	By captionlangaugedropdown=By.name(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_captionlanguage.getProperty());
	By captionfileuploadbutton=By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_captionfileuploadbutton.getProperty());
	By generatesrtfiletablocator=By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_generatesrttab.getProperty());
	By sendtranscriptionbutton=By.xpath(AvengerEditVideoSettingsPropertyPage.avngereditvideosettingspage_sendtranscriptionbutton.getProperty());
	By transcriptionerror=By.xpath(AvengerEditVideoSettingsPropertyPage.avngereditvideosettingspage_transcriptionerrormsg.getProperty());
	By expirationdatetextboxlocator=By.name(AvengerEditVideoSettingsPropertyPage.avngereditvideosettingspage_expirationdatetextboxLocator.getProperty());
	By expirationdatedatepicker=By.xpath(AvengerEditVideoSettingsPropertyPage.avngereditvideosettingspage_expirationdatepickerLocator.getProperty());
	By expirationdatepickerselectdate=By.xpath(AvengerEditVideoSettingsPropertyPage.avngereditvideosettingspage_expirationselectdatelocator.getProperty());
	By publishdatefieldtextboxlocator=By.name(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_publishdatefieldtextboxlocator.getProperty());
	By deletewhenvideoexpireslocator=By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_deletewhenvideoexpireslocator.getProperty());
	By deletewhenvideoexpirestextlocator=By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_deletewhenvideoexpirestextlocator.getProperty());
	By publishdatecalendarlocator=By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_publishdatecalendar.getProperty());
	By settingsdropdownlocator=By.xpath("//*[contains(@class,'dropDownButton')]");
    By advancesettingslocator=By.xpath("//span[contains(text(),'Advanced Settings')]");
    By detailslocator=By.xpath("//li/a[contains(@uisref,'portal.video-settings')]");
    By supplementalfiles1=By.xpath("//*[contains(text(),'Supplemental Files')]/../..//a[contains(text(),'Delete')]");
    By confirmzerotranscriptionfiles=By.xpath("//*[contains(@ngmodelgroup,'asTranscriptions')]//span[contains(@class,'glyphicons bin GGIjh__icon')]");
    By getalltranscriptionfileIds=By.xpath("//video-transcription-file//*[(@class='ng-binding')]");
    
    public int verifysupplementalfiles1()
    {
    	return elements(supplementalfiles1);
    }
	
	public void videoTitle(String videoTitle)
	{
		enterText(titlelocator,  videoTitle);

	}
	
	  public int verifytranscriptionfiles()
	    {
	    	return elements(confirmzerotranscriptionfiles);
	    }
		
	
	public void enterVideoDescription(String videoDescription)
	{
		By descFrame=By.xpath("//*[contains(@class,'wysiwyg')]");
		By desceditor=By.cssSelector("body");
		switchFrame(descFrame);
		enterText_withoutClear(desceditor, videoDescription);
		frameDefaultContent();
	}
	
	public void editVideoSettings(AvengerEditVideoSettingsBeanPage editvideosettingsbean)
	{

		waitForElementPresent(saveandexitbuttonlocator);
		waitForElementEnable(saveandexitbuttonlocator);		
		enterText(titlelocator,  editvideosettingsbean.getTitle());
		customReport.reporter("Title Entered in Video Settings Page is",editvideosettingsbean.getTitle());
		enterVideoDescription(editvideosettingsbean.getDescription());
		if(editvideosettingsbean.getLibraries()!="")
		  {
		   By collectioninputlocator = By.xpath("//video-access-control-input//*[contains(@name,'query')]");
		   By selectCollectionslocator = By.xpath("//*[@class='circle glyphicons group']"); 
		   By librarylocator = By.xpath("(//*[contains(text(),'"+editvideosettingsbean.getLibraries()+"')])"); 
	          
		   enterText(collectioninputlocator,  editvideosettingsbean.getLibraries()); 
		   customReport.reporter("Entered Text in Collection Input", editvideosettingsbean.getLibraries());
           click(collectioninputlocator);
            waitForElementPresent(searchimagelocator);
            clickUsingSwitch(librarylocator);
   			clickUsingSwitch(searchdonelocator);
   			customReport.reporter("Selected the value from find itesm", "");
		  }
		
		if(editvideosettingsbean.getCategories()!="")
		{
			enterText(categorieslocator,  editvideosettingsbean.getCategories());
			pause(3000);
			click(categorieslocator);
			clickEnter(categorieslocator);
		}

		if(editvideosettingsbean.getTags()!="")
		{
			enterText(tagslocator,  editvideosettingsbean.getTags());
			customReport.reporter("Tags  Entered in Video Settings Page is",editvideosettingsbean.getTags());
			clickEnter(tagslocator);
		}

		By presetstatus=By.xpath("//button[contains(text(),'"+editvideosettingsbean.getStatus()+"')]");
		
		if(editvideosettingsbean.getStatus()!="")	
		click(presetstatus);
		
		logger.info("anonymous viewing"+editvideosettingsbean.isAnonymousviewing());
		if(editvideosettingsbean.isAnonymousviewing())
		{
			logger.info(" I am in anonymous viewing");
			check_Checkbox(enableanonymousviewing);
		}
		logger.info("Category Selected Value is"+editvideosettingsbean.getCategories());

		}

	public void addSupplementalFiles(AvengerEditVideoSettingsBeanPage editvideosettingsbean)
	{
		logger.info("Adding videos in supplemental files");
		logger.info("Presentation file path is"+editvideosettingsbean.getFilesourcepath());
		enterText_FileUpload(supplementalfiles, editvideosettingsbean.getFilesourcepath());
		logger.info("THe file base name is"+FilenameUtils.getBaseName(editvideosettingsbean.getFilesourcepath()));
		By supplementvideo=By.xpath("//*[contains(text(),'"+FilenameUtils.getBaseName(editvideosettingsbean.getFilesourcepath())+"')]/../..//*[contains(@ng-click,'content.delete')]");
		waitForElementPresent(supplementvideo);
	}	
	
	public String get_titleErrorText()
	{
		clearWebElementText(titlelocator);
		logger.info("Error Text for Title is"+getText(titleerrortextlocator));
		return	getText(titleerrortextlocator);
	}
	public String get_Category()
	{
		pause(5000);
		return getText(getcategorieslocator);	
	}


	public void inputMultipleTags(AvengerEditVideoSettingsBeanPage editvideosettingsbean)
	{
		pause(5000);
		enterText(titlelocator,  editvideosettingsbean.getTitle());
		customReport.reporter("Title Entered in Video Settings Page is",editvideosettingsbean.getTitle());
		for (String tagsvalue : editvideosettingsbean.getTagsarray()) {
			editvideosettingsbean.setTags(tagsvalue);
			enterText(tagslocator,  editvideosettingsbean.getTags());
			customReport.reporter("Tags  Entered in Video Settings Page is",editvideosettingsbean.getTags());
			clickEnter(tagslocator);
		}
		pause(5000);
	}

	public ArrayList<String> get_AllTags()
	{
		waitForElementPresent(alltagslocator);
		ArrayList<String> tagslist=new ArrayList<String>();
		List<WebElement> alltags=getAllWebElementValues(alltagslocator);
		for (WebElement tags : alltags) {
			customReport.reporter("tags in Edit Video Settings Page are",tags.getText().replace("×",""));
			tagslist.add(tags.getText().replace("×","").trim());
		} 
		return tagslist;
	}

	public AvengerVideoCommentsPage clickCancel()
	{
		By settingslinklocator=By.xpath(AvengerVideoCommentsPropertyPage.avengervideocommentspage_settingslinklocator.getProperty());
		click(cancelbuttonlocator);
		customReport.reporter("Cancel Button Clicked", "");
		customReport.reporter("Video Comments Page is displayed", "");
		By alertlocator=By.xpath("//*[contains(@class,'modal-footer')]//*[contains(@type,'submit')]");
		click(alertlocator);
		return basePage.avengerVideoCommentsPage(driver,  customReport, basePage);
	}
	public String verifyLabel(String labelName )
	{
		By LabelTextlocator = By.xpath("//*[contains(text(),'"+labelName+"')]");
		return getText(LabelTextlocator);
	}
	
	public String verifyApprovalRequired(String labelName){
		By ApprovalRequiredLabletextlocator= By.xpath("//*[@class='approval-alt']//span[contains(text(),'"+labelName+"')]");
		return getText(ApprovalRequiredLabletextlocator);
	}

	public Map<String, String> getEditVideoDetailsData(AvengerEditVideoSettingsBeanPage beanpage)
	{
		pause(5000);
		By librarylocator = By.xpath("//*[contains(text(),'"+beanpage.getLibraries()+"')]");
		Map<String, String> videodetails= new HashMap<String, String>();
		videodetails.put("title", getAttribute(titlelocator, "value"));
		videodetails.put("description", getAttribute(descriptionlocator, "value"));
		return videodetails;
	}

	public String getAllLibraries(String libraryName)
	{
		By allLibrarylocator = By.xpath("//video-access-control-input//*[contains(@class,'description')]//span[1]");

		List<WebElement> alllibraryElement = getAllWebElementValues(allLibrarylocator);
		logger.info("The Size for the Playlist is"+alllibraryElement.size());

		for(int i=0;i<alllibraryElement.size();i++)
		{
			if(getTextMultiple(allLibrarylocator, i).equals(libraryName))
			{
				return getTextMultiple(allLibrarylocator,  i);
			}
		}
		return "";
	}	

	public String verifyTitleText()
	{
		By videoPageTitlelocator = By.xpath("//*[@name='title']");
		return getAttribute(videoPageTitlelocator, "value");
	}

	public String blankLibraryErrorText()
	{
		return getText(blanklibraryerrortext); 
	}

	public void removelibrary(AvengerEditVideoSettingsBeanPage librarybeanpage)
	{
		By removebutton=By.xpath("//*[contains(text(),'"+librarybeanpage.getLibraries()+"')]/..//*[@ng-click='removeItem(item)']");
		click(removebutton);
		pause(3000);
		customReport.reporter(librarybeanpage.getLibraries(),"is Removed");
	}

	public AvengerVideoCommentsPage click_saveButton(AvengerEditVideoSettingsBeanPage editvideosettingsbean)
	{
		pause(5000);
		waitForElementPresent(saveandexitbuttonlocator);
		customReport.reporter("video save button is visible on the page","");
		click(saveandexitbuttonlocator);
		customReport.reporter("Save and Exit Button is Clicked","");
		customReport.reporter("Video Settings Saved Successfully","");
		pause(3000);
		By alertlocator=By.xpath("//*[contains(@class,'modal-footer')]//*[contains(@type,'submit')]");
		click(alertlocator);
		return basePage.avengerVideoCommentsPage(driver,  customReport, basePage);
	}
	
	public int get_nooflibrariesforavideo() {
		
		By allLibrarylocatoronteam = By.xpath("//div[@class='pP8HT__descriptionCell']/span[contains(text(),'Team')]"); //("//span[contains(text(),'Team')][@class='ng-binding']");
		List<WebElement> alllibraryElementonteam = getAllWebElementValues(allLibrarylocatoronteam);
		return alllibraryElementonteam.size();
		
		
	}

	public void waitForVideoProcessed()
	{
		By videoprocessinglocator = By.xpath("//*[contains(text(),'Video is still processing, check back later.')]");
		By videouploadinglocator = By.xpath("//*[contains(text(),'Video is still uploading, check back later.')]");

		try{
			if(isDisplayed(videoprocessinglocator)||isDisplayed(videouploadinglocator))
			{
				logger.info("In wait video processing loop");
				for (int i=0; i<5;i++)
				{
					if(elements(videoprocessinglocator)>0 || elements(videouploadinglocator)>0)
					{
						logger.info("In video "+i+"nd processing loop");
						pause(10000);
					}
					else
					{
						logger.info("In break");
						break;
					}

				}
			}
		}catch(Exception e)
		{
			logger.info("Video is processed");
		}


	}

	//API
	public Map<String, String> getvideoDescripton()
	{
		Map<String, String> videodetails= new HashMap<String, String>();
		videodetails.put("description", getAttribute(descriptionlocator, "value"));	
		videodetails.put("title", getAttribute(titlelocator, "value"));	
		return videodetails;
	}
	public void add_Library(AvengerEditVideoSettingsBeanPage editvideosettingsbean)
	{
		By collectioninputlocator = By.xpath("//*[@ng-model='insight.query']");
		By librarySlectionlocator=By.xpath("(//*[@ng-hide='item.assigned'])[1]");
		enterText(collectioninputlocator,  editvideosettingsbean.getLibraries()); 
		click(collectioninputlocator);
		pause(3000);
		click(librarySlectionlocator);
		clickUsingSwitch(searchdonelocator);
	}
	public  String verifybuttonState(String element) {
		By statuslocator=By.xpath("//*[contains(text(),'"+element+"')]");
		String value=  getAttribute(statuslocator,"ng-click");
		String[] data=value.split("=");
		return data[1].trim();
	}


	public void clickInactive() {
		By inactivebuttonlocator=By.xpath("//button[contains(text(),'Inactive')]");
		click(inactivebuttonlocator);
		customReport.reporter("Clicked on Inactive Video Button","");
	}

	public boolean verifyEnableCommentsVisibility()
	{
		By enablecommentslocator = By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_enablecomments.getProperty());
		return isDisplayedWithoutException(enablecommentslocator);
	}

	public boolean verifyEnableRatingsVisibility()
	{
		By enableratingslocator = By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_enableratings.getProperty());
		return isDisplayedWithoutException(enableratingslocator);
	}

	public void disableCommentsOnVideo()
	{
		By enablecommentslocator = By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_enablecomments.getProperty());
		click(enablecommentslocator);
	}

	public void enableCommentsOnVideo()
	{
		By enablecommentslocator = By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_enablecomments.getProperty());
		click(enablecommentslocator);
	}

	public void disableRatingsOnVideo()
	{
		By enableratingslocator = By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_enableratings.getProperty());
		click(enableratingslocator);
	}

	public void enableRatingsOnVideo()
	{
		By enableratingslocator = By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_enableratings.getProperty());
		click(enableratingslocator);
	}

	public String verifyEnableCommentsSelected()
	{
		By enablecommentslocator = By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_enablecomments.getProperty());
		return getAttribute(enablecommentslocator, "class");
	}

	public String verifyEnabledownloadsSelected()
	{
		return getAttribute(enabledownloadcheckbox, "class");
	}

	public String verifyEnableRatingsSelected()
	{
		By enableratingslocator = By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_enableratings.getProperty());
		return getAttribute(enableratingslocator, "class");
	}

	public String get_EnableDownloadsText(){

		By enabledownloadlocator=By.xpath("//*[contains(@ng-show,'enableDownloads')]/div/label");

		return getText(enabledownloadlocator);
		}

	public boolean verifyEnabledownloadtext(){
		return isDisplayedWithoutException(enabledownloadcheckbox);

	}

	public void checkEnableDownloads() {
		check_Checkbox(enabledownloadcheckbox);
	}


	public void uncheckEnableDownloads() {
		if(verifyEnabledownloadsSelected().contains("active")==true);
		click(enabledownloadcheckbox);
	}

	public void click_EditButton(String editvideoname)
	{
		doubleClick(supplementfiles);
		logger.info("Edit Button for the Video is clicked");
	}

	public void enter_EditSupplementFileName(int index,String stextValue)
	{
		enterTextMultiple(contentitle,  stextValue, index);
	}

	public void click_OkButton()
	{
		click(okbutton);
		logger.info("Ok Button is Clicked");
	}
	public void click_CancelButton()
	{
		click(cancelbutton);
		logger.info("Cancel  Button is Clicked");

	}

	public void click_DeleteButton(String editvideoname)
	{
		mouseOverWithClickAndHold(supplementfiles);
		By deletebuttonlocator=By.xpath("//*[contains(text(),'"+editvideoname+"')]/../..//*[contains(@ng-click,'content.delete')]");
		click(deletebuttonlocator);
		logger.info("Delete Button for the Video is clicked");
	}
	
	public String verifyDeleteSupplementVideos(String editvideoname)
	{
		By deletedvideocontent=By.xpath("//*[contains(text(),'"+editvideoname+"')]");
		return String.valueOf(elements(deletedvideocontent));
		}
	

	public void changeStatusOfVideo(FileUploadBeanPage beanPage)
	{
		By videostatus = By.xpath("//*[contains(text(),'"+beanPage.getVideoStatus()+"')]");
		clickEnter(videostatus);
		logger.info("Clicked on status"+ getText(videostatus));
	}
	
	public void clickSave()
	{
		pause(5000);
		submit(saveandexitbuttonlocator);
	}
	 public String get_VideoStatus(){
		 By videostatus=By.xpath(AvengerEditVideoSettingsPropertyPage.avengereditvideosettingspage_videostatus.getProperty());
		 waitForElementPresent(titlelocator);
		 return getText(videostatus);
	 }
	 
	 
	 public String checkSupplementalFileType(String filename)
	 {
	  By filetypexpath=By.xpath("//*[contains(text(),'"+filename+"')]/../..//*[contains(@class,'filetype')]");
	  return (getAttribute(filetypexpath, "class"));
	 }
	 
	 public ArrayList<String> getAlluploadedSupplementalFiles()
	 {
		 By suppFiles=By.xpath("//*[contains(@class,'Dwjyc__fileName')]");
		 List<WebElement> titlevalues=getAllWebElementValues(suppFiles);
		 pause(5000);
		 logger.info("The size of the element is"+titlevalues.size());
		 ArrayList<String> titles=new ArrayList<String>(); 
		 for (WebElement webElement : titlevalues) {
			 titles.add(webElement.getText());
		 }
		 logger.info("The size of the element is"+titles);
	  return titles;
	 }

	 public String getassignedLibrary(String libraryName)
		{
			By allLibrarylocator = By.xpath("//div[contains(text(),'"+libraryName+"')]");
			return getText(allLibrarylocator);
		
		}
		
	 /*
		 * This method is used to select access control from edit video settings page ex:public,private...
		 */
		 public void clickLibraryStatus(String libraryStatus)
		 {
				By statuslocator = By.xpath("//*[contains(@ng-form,'accessControlForm')]//*[contains(text(),'"+libraryStatus+"')]");
				click(statuslocator);
				customReport.reporter("clicked on status for access control",libraryStatus);
		 }
	 
	 public void clickVideoStatus(String videostatus)
	 {
			By statuslocator = By.xpath("//*[contains(text(),'"+videostatus+"')]");
			click(statuslocator);
			customReport.reporter("clicked on status for access control ",videostatus);
	 }
	 
	 public String addGroupaddCollectionaddUser(String type){

		 By collectioninputlocator = By.xpath("//*[@ng-model='insight.query']");
		 By librarylocator = By.xpath("(//*[@ng-hide='item.assigned'])[1]");//("(//*[contains(text(),'"+type+"')])[2]");
		 enterText(collectioninputlocator,  type);
		 clickUsingSwitch(collectioninputlocator);
		 customReport.reporter("Clicked on library search text box","");
		 waitForElementPresent(searchimagelocator);
		 pause(5000);
		 clickUsingSwitch(librarylocator);
		 customReport.reporter("Selected value from search result","");
		 customReport.reporter("clicked on done button","");
		 return getassignedLibrary(type);
	 }
	 
	 public void clickDonelocator()
	 {
		 clickUsingSwitch(searchdonelocator);
	 }

	 public void clickEditAccess(String susername){
		 By editaccesslocator=By.xpath("//*[contains(text(),'"+susername+"')]/..//button[contains(@ng-show,'forceEditAccess')]");
		 waitForElement(editaccesslocator);
		 pause(3000);
		 click(editaccesslocator);
		 customReport.reporter("Clicked on Edit Access","");
	 }
	 
	 public String verify_accessControls(String value)
	 {
		 if (value.equals("Public")) {
		 value = value.toLowerCase();
		 }
		 else if (value.equals("AllInternalUsersACLToggle")) {
			 value = "allInternalUsersACLToggle";
		 }
		 By accesscontrol=By.xpath("//*[contains(@id,'"+value+"')]"); 
		 pause(9000);
		 logger.info("the value of access control"+value);
		 logger.info(String.valueOf(isSelected(accesscontrol)));
		 return String.valueOf(isSelected(accesscontrol));
	 }
	 
	 public void clickAccessControlStatus(String accesscontrol)
	 {
		 //As per the user story AV-23223 and comment from nithya so if you have all users and public off, it means the video is private and you can give specific users/grps/channels view or edit access
		if(!accesscontrol.equalsIgnoreCase("Private"))
		{
		 By statuslocator=By.xpath("//*[contains(text(),'"+accesscontrol+"')]/../../vb-toggle-button"); 
		 pause(5000);
		 click(statuslocator);
		 pause(5000);
		 customReport.reporter("clicked on status for access control",accesscontrol);
		}
	 }

	 public String verify_accessControl()
	 {
	  By accesscontrol=By.xpath("//button[contains(@name,'accessControl') and contains(@class,'activeBtn')]"); 
	  pause(5000);
	  logger.info("The value of video access control is"+getText(accesscontrol));
	 return getText(accesscontrol);
	 }
	

	 public String verifyPendingApprovalLabel(String labelName )
		{
			By LabelTextlocator = By.xpath("//*[label='Status']//*[contains(text(),'"+labelName+"')]");
			return getText(LabelTextlocator);
		}

	 /**
	  * This method is used click advance tab in video edit settings page
	 * @throws InterruptedException 
	  */
	 public void clickSupplementMediaTab() throws InterruptedException{
		 By advancesettingslocator=By.xpath("//span[contains(text(),'Advanced Settings')]");
         click(settingsdropdownlocator);
         By detailslocator=By.xpath("//li/a[contains(@uisref,'portal.video-settings')]");
         click(detailslocator);
         Thread.sleep(10000);
         click(advancesettingslocator);
		  }
	 
	 public String verifyErrorMessageForCaptionLanguage()
		{
			By LabelTextlocator = By.xpath("//p[@class='invalid']");
			return getText(LabelTextlocator);
		}
	 
	 public  ArrayList<String> getCaptionlanguagedropdownvalues(){
			return getDropdownValue(captionlangaugedropdown);
		 }
		 
		 
		 public void selectvaluefromcaptionlanguagedropdown(String sCaptionlanguage){
			 selectValuefromDropDown(captionlangaugedropdown, sCaptionlanguage);
		 }
		 
		 public void uploadcaptionfile(AvengerEditVideoSettingsBeanPage avengereditvideosettingsbeanpage){
			 pause(3000);
			 By by = By.xpath("//input[@type='file'][@name='transcription-file']");
			 enterText_FileUpload(by, avengereditvideosettingsbeanpage.getFilesourcepath());
		 }
		 
		 public boolean getaddfilestate(){
			 return isEnabled(captionfileuploadbutton);
		 }

		 public void captionlanguageselectfileoption(String filename, String option)
		
		 {
		By captionlanguagetoolsselection = By.xpath("//*[contains(text(),'"+filename+"')]/..//a[contains(text(),'"+option+"')]");
		click(captionlanguagetoolsselection);
	}
		 
		 public String verifycustomfields(String lablename){
			 By customfield=By.xpath("//label[contains(text(),'"+lablename+"')]");
			 return getText(customfield);
		 }
	 
		 public boolean verifysavebuttondisabled(){
				By saveButtonDisabled=By.xpath("(//*[@class='section-header']//*[@type='submit'])[2][@disabled='disabled']");
				boolean savebuttonStatus= isDisplayedWithoutException(saveButtonDisabled);
				logger.info("Submit button is Disabled :"+savebuttonStatus);
			return savebuttonStatus;
		 }
		 
		 public boolean verifycustomfieldtextbox(String lablename){
			 By customfieldtextbox = By.xpath("//label[contains(text(),'"+lablename+"')]/../div//*[contains(@name,'customField')]");
			return isDisplayedWithoutException(customfieldtextbox);
		 }
		 
		 public void setdataforpiclistcustimfieldtestbox(AvengerEditVideoSettingsBeanPage avengereditvideosettingsbeanpage,String lablename){
			 By customfieldtextboxlocator = By.xpath("//input[contains(@name,'"+lablename+"')]");
			 enterText(customfieldtextboxlocator, avengereditvideosettingsbeanpage.getCustomfieldtextbox());
		 }
		 
		
		 public ArrayList<String> geteditvideocustomfieldvalues(){
			 
			 By customfieldnames= By.xpath("//*[@class='form-group ng-scope']/label");
			 waitForElement(customfieldnames);
			 List<WebElement> customfieldvalues=getAllWebElementValues(customfieldnames);
			 logger.info("The size of the custom fields element is"+customfieldvalues.size());
			 ArrayList<String> customfieldorder = new ArrayList<String>();
			 for(int i=0;i<=customfieldvalues.size()-1;i++){
			 customfieldorder.add(customfieldvalues.get(i).getText());
			 }
		  return customfieldorder;
		 }
		 
		 public void setvaluefortextcustomfield(String lablename,AvengerManageCustomFieldsBeanPage customfieldsbean){
			 By customfieldtextbox = By.xpath("//label[contains(text(),'"+lablename+"')]/../div//*[contains(@name,'customField')]");
			 enterText(customfieldtextbox, customfieldsbean.getTextvalueinvideocommentspage());
		 }
		 
		 public void selectvaluefrompicklistcustomfield(String labelname, String optionvalue){
			 By picklistcustomfielddropdown = By.xpath("//*[contains(@name,'"+labelname+"')]");
			 click(picklistcustomfielddropdown);
			 logger.info("clicked on "+picklistcustomfielddropdown);
			 By selectvaluefrompicklistdropdown = By.xpath("//*[contains(@name,'"+labelname+"')]/..//*[contains(@value,'"+optionvalue+"')]");
			 pause(2000);
			 click(selectvaluefrompicklistdropdown);
			 logger.info("Selected value is"+optionvalue);
		 }
		 
		 public int verify_ProgressBar()
		 {
			 By progressbar= By.xpath("//*[contains(@role,'progressbar')]/..");
			 return elements(progressbar);
		 }
		 
		 public String verify_saveButtonEnableorDisable()
		 {
        By disabledlocator =By.xpath("(//form[@name='videoForm']//*[@type='submit'])[2]/../button[@disabled='disabled']");
			 return String.valueOf(elements(disabledlocator));
		 }
		 
		 public boolean verify_generateSRTtabPresent(){
			 
			 return isDisplayedWithoutException(generatesrtfiletablocator);
		 }
		 
		 public void clickActive() {
				By activebuttonlocator=By.xpath("//button[contains(text(),'Active')]");
				click(activebuttonlocator);
				customReport.reporter("Clicked on active Video Button","");
			}
		 
		 public void click_generateSRTtab(){
			 click(generatesrtfiletablocator);
			 logger.info("clicked on generate .SRT file tab ");
			 waitForElementPresent(sendtranscriptionbutton);
			 logger.info("Send for Transcription button is visible after clickinf on gerate .SRT file tab");
		 }
		 
		 public void click_sendfortranscription(){
			 click(sendtranscriptionbutton);
			 logger.info("Clicked on Send to Transcription button");
			 waitForElement(transcriptionerror);
			 logger.info("Transcription file with English is visible on the page");
		 }
		 
		 public String verify_errormsgfortranscriptionfile(){
			 pause(5000);
			 return getText(transcriptionerror);
		 }
		 
		 public boolean verify_SendforTranscriptionbutton(){
			 return isDisplayedWithoutException(sendtranscriptionbutton);
		 }
		 public String getExpirationDate() {
			 pause(2000);
	    	 return getAttribute(expirationdatetextboxlocator,"value");
	     }
		 
		 public void enterExpirationDate(){
			 
			 click(expirationdatedatepicker);
			 pause(3000);
			 click(expirationdatepickerselectdate);
		 }
		 
		 public void enterDateinExpirationField(String date) {
			 waitForElementPresent(expirationdatetextboxlocator);
			 customReport.reporter("expiration date text box loactor is visible on the page","");
			 enterText(expirationdatetextboxlocator, date);
			 pause(2000);
		 }
		 
		 public void enterDateinPublishField(String date) {
			 waitForElementPresent(publishdatefieldtextboxlocator);
			 customReport.reporter("publish date field textbox locator is visible on the page","");
			 enterText(publishdatefieldtextboxlocator, date);
			 pause(2000);
		 }
		
		 
		 public String getPublishDate(){
			return getAttribute(publishdatefieldtextboxlocator, "value");
			
		 }
		 
		 
		 public String getPublishDate1() {
			click(publishdatecalendarlocator);
			DateTime calDate = new DateTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);
			String calActiveDate = calDate.addDaysToDate(1);
			return calActiveDate;
			
		 }
		 
		public String get_DeleteWhenVideoExpiresCheckBoxStatus()
		{
			waitForElementPresent(deletewhenvideoexpireslocator);
			 customReport.reporter("Delete video expires loactor is visible on the page","");
			return getAttribute(deletewhenvideoexpireslocator,"class");
		}
		
		public String click_DeleteWhenVideoExpiresCheckBox()
		{
		waitForElementPresent(deletewhenvideoexpireslocator);
		 customReport.reporter("Delete video expires loactor is visible on the page before clicking","");
         click(deletewhenvideoexpireslocator);
         return getText(deletewhenvideoexpirestextlocator);
		}      
		
		public void selectanycategory(String categorytext)
		{
			By categorypicker=By.xpath("//*[@data-placeholder='Pick a Category']");
			enterText(categorypicker,categorytext);
		}
		
}
