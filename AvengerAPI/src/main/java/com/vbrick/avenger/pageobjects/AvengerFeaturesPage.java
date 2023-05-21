package com.vbrick.avenger.pageobjects;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerDashboardPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerFeaturesPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerUploadPropertyPage;
import com.vbrick.avenger.dao.AvengerFeaturesBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerFeaturesPage extends WebElements{
	
	private WebDriver driver;
	
	private CustomReport customReport;
	private BasePage basePage;
	
	By enablecommentslocator = By.xpath(AvengerFeaturesPropertyPage.avengerfeaturespage_enablecomments.getProperty());
	By savechangesbuttonlocator=By.xpath(AvengerFeaturesPropertyPage.avengerfeaturespage_savechangesbuttonlocator.getProperty());
	By enableratingslocator = By.name(AvengerFeaturesPropertyPage.avengerfeaturespage_enableratings.getProperty());
   By dashboardpagelocator=By.xpath(AvengerFeaturesPropertyPage.avengerfeaturespage_dashboardlink.getProperty()); 
   By uploadtablink=By.linkText(AvengerUploadPropertyPage.uploadpg_Uploadtab.getProperty());
   By mediatablink = By.xpath(AvengerDashboardPropertyPage.Avengerdashboardpage_mediatablocator.getProperty());
   By enablecategorieslocator=By.name(AvengerFeaturesPropertyPage.avengerfeaturespage_enablecategorieslocator.getProperty());
   By enabletagslocator=By.name(AvengerFeaturesPropertyPage.avengerfeaturespage_enabletagslocator.getProperty());
   By enabledownloadslocator=By.name(AvengerFeaturesPropertyPage.avengerfeaturespage_enabledownloadslocator.getProperty());
   By webExIntegrationlocator=By.xpath(AvengerFeaturesPropertyPage.avengerfeaturespage_webExIntegrationlocator.getProperty());
   By ssocheckboxlocator=By.xpath(AvengerFeaturesPropertyPage.avengerfeaturespage_ssocheckboxlocator.getProperty());
   By hostedwebsitelocator=By.name(AvengerFeaturesPropertyPage.avengerfeaturespage_hostedwebsitelocator.getProperty());
   By siteidlocator=By.name(AvengerFeaturesPropertyPage.avengerfeaturespage_siteidlocator.getProperty());
   By partneridlocator=By.name(AvengerFeaturesPropertyPage.avengerfeaturespage_partneridlocator.getProperty());
   By adminusernamelocator=By.name(AvengerFeaturesPropertyPage.avengerfeaturespage_adminusernamelocator.getProperty());
   By adminpasswordlocator=By.name(AvengerFeaturesPropertyPage.avengerfeaturespage_adminpasswordlocator.getProperty());
   By nbrstorageserviceurllocator=By.name(AvengerFeaturesPropertyPage.avengerfeaturespage_nbrstorageserviceurllocator.getProperty());
   By enableembedscheckbox=By.xpath(AvengerFeaturesPropertyPage.avengerfeaturespage_enabelembedscheckbox.getProperty());
   By enableembedsautoplaycheckbox=By.xpath(AvengerFeaturesPropertyPage.avengerfeaturespage_enabelembedsautoplaycheckbox.getProperty());
   By enableaddurlscheckbox=By.xpath(AvengerFeaturesPropertyPage.avengerfeaturespage_enableaddurlscheckbox.getProperty());
   By addedviaurltext=By.xpath(AvengerFeaturesPropertyPage.avengerfeaturespage_addedviaurltext.getProperty());
   By sparkIntegrationlocator=By.xpath(AvengerFeaturesPropertyPage.avengerfeaturespage_sparkIntegrationlocator.getProperty());
   By sparkIntegrationclientIdlocator=By.name(AvengerFeaturesPropertyPage.avengerfeaturespage_sparkIntegrationClientIDlocator.getProperty()); 
   By sparkIntegrationclentSecretlocator=By.name(AvengerFeaturesPropertyPage.avengerfeaturespage_sparkIntegrationClientSecretlocator.getProperty());
   By voicebaseintergrationcheckbox=By.xpath(AvengerFeaturesPropertyPage.avengerfeaturespage_voicebaseintegrationcheckbox.getProperty());
   By voicebaseintergrationapikeytextboxloactor = By.xpath(AvengerFeaturesPropertyPage.avengerfeaturespage_voicebaseintegrationAPIkeytextbox.getProperty());
   By voicebaseintergrationpasswordtextboxloactor = By.xpath(AvengerFeaturesPropertyPage.avengerfeaturespage_voicebaseintegrationpasswordtextbox.getProperty());
   By tropointegrationlocator=By.xpath(AvengerFeaturesPropertyPage.avengerfeaturespage_tropointegration.getProperty());
   By messageTokentextboxlocator=By.xpath(AvengerFeaturesPropertyPage.avengerfeaturespage_messagetokentextboxlocator.getProperty());
   By manualbuttonlocator=By.xpath(AvengerFeaturesPropertyPage.avengerfeaturespage_manualbuttonlocator.getProperty());
   By delayinsecondstextboxlocator=By.xpath(AvengerFeaturesPropertyPage.avengerfeaturespage_delayinsecondstextboxlocator.getProperty());
   By offbuttonlocator=By.xpath(AvengerFeaturesPropertyPage.avengerfeaturespage_offbuttonlocator.getProperty());
   By enableexpirationruleslocator = By.name("enableExpirationRules");
  By settingssavedalertlocator=By.xpath("//*[@class='modal-footer']/button[contains(@type,'submit')]");
  By sendlinkurltoallattendes=By.xpath(AvengerFeaturesPropertyPage.avengerfeaturespage_sendlinkurltoallattended.getProperty());
		 
  
   private static Logger logger = Logger.getLogger(AvengerFeaturesPage.class);
	public AvengerFeaturesPage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver=driver;

		this.customReport = customReport;
		this.basePage=basePage;
		// TODO Auto-generated constructor stub
	}
	
	
	public boolean get_defaultEnableComments()
	{
		if(isSelected(enablecommentslocator)==true)
		customReport.reporter("Enabled Comments Setting is in enabled Mode","");
		else
			customReport.reporter("Enabled Comments Setting is in Disabled Mode","");
				
		return isSelected(enablecommentslocator);
	}
	
	public boolean get_defaultEnableRatings()
	{
		if(isSelected(enableratingslocator)==true)
		customReport.reporter("Enabled Ratings Setting is in enabled Mode","");
		else
			customReport.reporter("Enabled Ratings Setting is in Disabled Mode","");
				
		return isSelected(enableratingslocator);
	}
	
	
	public void disableRatings()
	{
		uncheck_Checkbox(enableratingslocator);
		customReport.reporter("Enable Ratings Check Box is Disabled","");
		click(savechangesbuttonlocator);
		pause(3000);
		if(elements(settingssavedalertlocator)==1)
			click(settingssavedalertlocator);
		customReport.reporter("Save Changes Button is Clicked","");
	}
	
	
	public void disableComments()
	{
		uncheck_Checkbox(enablecommentslocator);
		customReport.reporter("Enable Comment Check Box is Disabled","");
		click(savechangesbuttonlocator);
		pause(2000);
		if(elements(settingssavedalertlocator)==1)
		click(settingssavedalertlocator);
		customReport.reporter("Save Changes Button is Clicked","");
	}
	
	public void enableComments()
	{
		check_Checkbox(enablecommentslocator);
		customReport.reporter("Enable Comment Check Box is Enabled","");
		click(savechangesbuttonlocator);
		pause(2000);
		if(elements(settingssavedalertlocator)==1)
		click(settingssavedalertlocator);
		customReport.reporter("Save Changes Button is Clicked","");
	}
	
	public void enableRatings()
	{
		check_Checkbox(enableratingslocator);
		customReport.reporter("Enable Ratings Check Box is Disabled","");
		click(savechangesbuttonlocator);
		pause(3000);
		if(elements(settingssavedalertlocator)==1)
		click(settingssavedalertlocator);
		customReport.reporter("Save Changes Button is Clicked","");
	}
	
	public AvengerDashboardPage click_avengerDashboardPage()
	{
	    pause(5000);
		click(dashboardpagelocator);
		customReport.reporter("Avenger Dashboard Page is Clicked","");
		waitForElementPresent(mediatablink);
		return basePage.avengerDashboardPage(driver,  customReport, basePage);
	}
	
	public void clickSaveChanges()
	{
		waitForElement(savechangesbuttonlocator);
		click(savechangesbuttonlocator);
		customReport.reporter("Save Changes Button is Clicked","");
		waitForElement(settingssavedalertlocator);
		pause(5000);
		if(elements(settingssavedalertlocator)==1)
		click(settingssavedalertlocator);
		customReport.reporter("Save Changes Button is Clicked","");
	}
	
	public Map<String, String> getLabels()
	{
		Map<String, String> featurespagehelperlabels = new HashMap<String, String>();
		By allowcategories = By.xpath("//*[@name='enableCategories']/..");
		By allowtags = By.xpath("//*[@name='enableTags']/..");
		By allowcomments = By.xpath("//*[@name='enableComments']/..");
		By allowratings = By.xpath("//*[@name='enableRatings']/..");
		
		featurespagehelperlabels.put("CategoriesHelper", getText(allowcategories));
		featurespagehelperlabels.put("TagsHelper", getText(allowtags));
		featurespagehelperlabels.put("CommentsHelper", getText(allowcomments));
		featurespagehelperlabels.put("RatingsHelper", getText(allowratings));
		return  featurespagehelperlabels;
	}
	public void acceptSaveChangesPopUp(){
		if(elements(settingssavedalertlocator)==1)
		click(settingssavedalertlocator);
		customReport.reporter("Save Changes Button is Clicked","");
	}


	public void uncheckDisableDownloads() {
		By uncheckcheckbox=By.xpath("//*[@ng-model='features.enableDownloads']");
		uncheck_Checkbox(uncheckcheckbox);
		pause(3000);
	}


	public void verifycheckboxforenabledownloads() {
		By checkcheckbox=By.xpath("//*[@ng-model='features.enableDownloads']");
		check_Checkbox(checkcheckbox);
	}
	
	public void enableCategories()
	{
		check_Checkbox(enablecategorieslocator);
		customReport.reporter("Enable Categories Check Box is Enabled","");
		
		waitForElementPresent(savechangesbuttonlocator);
		click(savechangesbuttonlocator);
		waitForElementPresent(settingssavedalertlocator);
		if(elements(settingssavedalertlocator)==1)
		click(settingssavedalertlocator);
		customReport.reporter("ok button is clicked for popup","");
	}
	
	public void disableCategories()
	{
		waitForElement(enablecategorieslocator);
		uncheck_Checkbox(enablecategorieslocator);
		customReport.reporter("Enable Categories Check Box is Disabled","");
		waitForElementPresent(savechangesbuttonlocator);
		click(savechangesbuttonlocator);
		waitForElement(settingssavedalertlocator);
		if(elements(settingssavedalertlocator)==1)
		click(settingssavedalertlocator);
		customReport.reporter("Save Changes Button is Clicked for pop","");
	}
	
	public Map<String, Boolean> get_featureslist()
	{
		Map<String, Boolean> featureslist= new HashMap<String, Boolean>();
		featureslist.put("enablecategories", isSelected(enablecategorieslocator));
		featureslist.put("enablecomments", isSelected(enablecommentslocator));
		featureslist.put("enableratings", isSelected(enableratingslocator));
		featureslist.put("enabletags", isSelected(enabletagslocator));
		featureslist.put("enableownloads", isSelected(enabledownloadslocator));
		return featureslist;	
	}
	
	public void check_webExIntegrationCheckBox()
	{
		check_Checkbox(webExIntegrationlocator);
	}
	
	public void unCheck_webExIntegrationCheckBox()
	{
	    uncheck_Checkbox(webExIntegrationlocator);
	}
	
	public void get_webExIntegrationCheckBoxStatus()
	{
		 check_Checkbox(webExIntegrationlocator);
	}
	
	public void check_SSOEnabledCheckBox()
	{
		check_Checkbox(ssocheckboxlocator);
	}
	
	public void enter_WebExIntegrationData(AvengerFeaturesBeanPage avengerfeaturesbeanpage)
	{
		enterText(hostedwebsitelocator, avengerfeaturesbeanpage.getHostedwebsite());
		enterText(siteidlocator, avengerfeaturesbeanpage.getSiteid());
		enterText(partneridlocator, avengerfeaturesbeanpage.getPartnerid());
		enterText(adminusernamelocator, avengerfeaturesbeanpage.getAdminusername());
		enterText(adminpasswordlocator, avengerfeaturesbeanpage.getAdminpassword());
		enterText(nbrstorageserviceurllocator,avengerfeaturesbeanpage.getNbrstorageserviceurl());
	}
	
	public LinkedHashMap<String, String> verify_enableEmbeds(){
	logger.info("The status of Enable Embeds checkbox "+enableembedscheckbox);
	By embedcodetext = By.xpath("//*[@name='enableEmbeds']/..");
	By enabledautoplay =By.xpath("//*[@name='autoplayDefault']/..");
	
	LinkedHashMap<String, String> enableembeddedcheckboxstatus=new LinkedHashMap<String, String>();
	enableembeddedcheckboxstatus.put("enablemebedcheckbox",String.valueOf(isSelected(enableembedscheckbox)));
	enableembeddedcheckboxstatus.put("autoplaycheckbox",String.valueOf(isSelected(enableembedsautoplaycheckbox)));
	enableembeddedcheckboxstatus.put("embedcode", getText(embedcodetext));
	enableembeddedcheckboxstatus.put("autoplayenable", getText(enabledautoplay));
	return enableembeddedcheckboxstatus;
	}
	
	
	public void check_AutoPlaycheckbox(){
		click(enableembedsautoplaycheckbox);
		logger.info("Auto play check box is enabled");
	}
	
	public boolean verify_embedcheckbox(){
		logger.info("embed code check box is visible");
		return isSelected(enableembedscheckbox);
		
	}
	
	public void checkEmbedCode()
	{
		check_Checkbox(enableembedscheckbox);
		logger.info("Enable Embed Check Box is clicked");
	}
	
	public void checkAutoplayEnable()
	{
		check_Checkbox(enableembedsautoplaycheckbox);
		logger.info("Autoplay Enable Check Box is clicked");
	}
	
	public void unCheckEmbedCode()
	{
		uncheck_Checkbox(enableembedscheckbox);
		logger.info("Enable Embed Check Box is clicked");
	}
	
	public boolean verify_enableaddurlcheckbox(){
		logger.info("enable add url check box is visible");
		return isSelected(enableaddurlscheckbox);
		
	}
	
	public String verify_addedviaurls(){
		logger.info("Allow videos to be added via URLs");
		 return getText(addedviaurltext);
	}
	
	public void uncheck_addedviaurlcheckbox(){
	
		waitForElementPresent(enableaddurlscheckbox);
		customReport.reporter("Add url checkbox is visible on the page for uncheck", "");
		uncheck_Checkbox(enableaddurlscheckbox);
		logger.info("enable add url check box is disabled");
	}
	
	public void click_savebutton(){
		waitForElementPresent(savechangesbuttonlocator);
		customReport.reporter("Save button is visible on the page", "");
		click(savechangesbuttonlocator);
		logger.info("Clicked on save button");
	}
	
	public void click_AcceptOrCancelWarningAlter(String option){
		int number;
		if(option.equalsIgnoreCase("ok"))
		number=3;
		else
			number=1;
		
		By acceptorcancelbutton=By.xpath("//*[@class='modal-footer']/button["+number+"]");
		waitForElementPresent(acceptorcancelbutton);
		customReport.reporter("Warning Alter is visible on the page", "");
		click(acceptorcancelbutton);
		logger.info("Warning alter is open");
		
	}
	
	public void checkEnableAddUrlsCheckbox(){
		waitForElementPresent(enableaddurlscheckbox);
		customReport.reporter("add url check box is visible on the page", "");
		check_Checkbox(enableaddurlscheckbox);
		logger.info("Add url checkbox is checked");
	}
	
	 public boolean get_defaultSparkIntegration()
		{
				return isSelected(sparkIntegrationlocator);
		}
	 
	 	public void checkSparkIntegrationCheckbox(){
			check_Checkbox(sparkIntegrationlocator);
			logger.info("Spark Integration checkbox is checked");
		}
		public void unCheckSparkIntegarionCheckbox(){
			uncheck_Checkbox(sparkIntegrationlocator);
			logger.info("Spark Integration checkbox is Unchecked");
		}
		
		public LinkedList<Boolean> verifysparkClientIdtextBox()	{
		pause(5000);
		LinkedList<Boolean> sparkclientidsecret = new LinkedList<Boolean>();
		sparkclientidsecret.add(isDisplayedWithoutException(sparkIntegrationclientIdlocator));
		sparkclientidsecret.add(isDisplayedWithoutException(sparkIntegrationclentSecretlocator));
		return sparkclientidsecret;
		}
		
		public void enter_SparkIntegrationData(AvengerFeaturesBeanPage avengerfeaturesbeanpage)
		{
			enterText(sparkIntegrationclientIdlocator, avengerfeaturesbeanpage.getSparkclientid());
			enterText(sparkIntegrationclentSecretlocator, avengerfeaturesbeanpage.getSparkclientsecret());
		}
		
		public boolean verify_voicebaseapikeycheckbox(){
			return isDisplayedWithoutException(voicebaseintergrationapikeytextboxloactor);
		}
		
		public void check_VoiceBaseIntergrationCheckBox(){
			check_Checkbox(voicebaseintergrationcheckbox);
			logger.info("Voice base integration checkbox is enabled");
			waitForElementPresent(voicebaseintergrationapikeytextboxloactor);
			logger.info("The API Key text box is visble after enabling voice integration checkbox");
		}
		
		public void enter_voicebaseapikey(AvengerFeaturesBeanPage avengerfeaturesbeanpage){
			enterText(voicebaseintergrationapikeytextboxloactor, avengerfeaturesbeanpage.getVoicebaseapikey());
			logger.info("The API Key entered for voicebase integration is " + avengerfeaturesbeanpage.getVoicebaseapikey());
		}
		
		public void enter_passwordforvoicebase(AvengerFeaturesBeanPage avengerfeaturesbeanpage){
			enterText(voicebaseintergrationpasswordtextboxloactor, avengerfeaturesbeanpage.getVoicebasepassword());
			logger.info("The Password entered for voicebase integration is " + avengerfeaturesbeanpage.getVoicebasepassword());
		}
		
		public void enable_voiceBaseIntegration(AvengerFeaturesBeanPage avengerfeaturesbeanpage){
			check_VoiceBaseIntergrationCheckBox();
			enter_voicebaseapikey(avengerfeaturesbeanpage);
			enter_passwordforvoicebase(avengerfeaturesbeanpage);
		}
		
		public void check_tropointegration(){
			check_Checkbox(tropointegrationlocator);
		}
	
		public boolean verify_messagetokentextbox() {
			return isDisplayedWithoutException(messageTokentextboxlocator);
		}
		
		public void click_ManualSlidedelayforHlsLiveVideo()
		{
		 click(manualbuttonlocator);
		 logger.info("Clicked on Manual slide delay button");
		}
		
		public String getDelayinSecondsTextbox()
		{
			return getAttribute(delayinsecondstextboxlocator, "value");
		}
		
		public void click_offSlideDelayforHlsLiveVideo()
		{
			click(offbuttonlocator);
			logger.info("Clicked on off button locator");
		}

        public boolean verify_DelayinSecondsTextboxVisibility()
        {
        	return isDisplayedWithoutException(delayinsecondstextboxlocator);
        }

		public void enter_DelayinSecondsTextbox(String value)
		{
			 enterText(delayinsecondstextboxlocator, value);
			 logger.info("Value entered in text box is"+value);
		}
		
		public void clearDelaySecondTextBox(){
			clearWebElementText(delayinsecondstextboxlocator);
			logger.info("Data cleared from clear text box");
		}
		
		public String delete_DelayinSecondsTextboxValue(String locatorvalue)
		{
			clearWebElementTextusingbackspace(delayinsecondstextboxlocator,getAttribute(delayinsecondstextboxlocator, "value").length());
			logger.info("Cleared text using backspace");
			By fieldrequiredlocator=By.xpath("//*[contains(@name,'delaySeconds')]/../..//*[contains(text(),'"+locatorvalue+"')]");
			return getText(fieldrequiredlocator);
	}
		
		public void checkexpirationRulesCheckbox() {
			check_Checkbox(enableexpirationruleslocator);
			logger.info("Enable Expiration Rules Checkbox is clicked");
		}

		public void unCheckexpirationRulesCheckbox() {
			uncheck_Checkbox(enableexpirationruleslocator);
			logger.info("Disable Expiration Rules Checkbox is clicked");
		}
		public void enablesharelink()
		{
			check_Checkbox(sendlinkurltoallattendes);
			logger.info("Enable Send a Link/URL to all Attendees Checkbox is clicked");
		}

		public void disablesharelink()
		{
			uncheck_Checkbox(sendlinkurltoallattendes);
			logger.info("Disable Send a Link/URL to all Attendees Checkbox is clicked");
		}
		





}

