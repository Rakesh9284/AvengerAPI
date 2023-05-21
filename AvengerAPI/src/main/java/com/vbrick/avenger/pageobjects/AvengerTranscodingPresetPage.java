package com.vbrick.avenger.pageobjects;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerTranscodingPresetPropertyPage;
import com.vbrick.avenger.ObjProperty.TranscodingPropertyPage;
import com.vbrick.avenger.dao.TranscodingBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerTranscodingPresetPage extends WebElements {

	private static Logger logger = Logger.getLogger(AvengerTranscodingPresetPage.class);
	private CustomReport customReport;
	private WebDriver driver;
	
	private BasePage basePage;
	By presetlocator =By.xpath(AvengerTranscodingPresetPropertyPage.transcodingpreset_presetlocator.getProperty());
	By descriptionlocator =By.xpath(AvengerTranscodingPresetPropertyPage.transcodingpreset_descriptionlocator.getProperty());
	By outputtypelocator =By.xpath(AvengerTranscodingPresetPropertyPage.transcodingpreset_outputtypelocator.getProperty());
	By preventupscalinglocator =By.xpath(AvengerTranscodingPresetPropertyPage.transcodingpreset_preventupscalinglocator.getProperty());
	By keyframeintervallocator =By.xpath(AvengerTranscodingPresetPropertyPage.transcodingpreset_keyframeintervallocator.getProperty());
	By frameratelocator =By.xpath(AvengerTranscodingPresetPropertyPage.transcodingpreset_frameratelocator.getProperty());
	By widthlocator =By.xpath(AvengerTranscodingPresetPropertyPage.transcodingpreset_widthlocator.getProperty());
	By heightlocator =By.xpath(AvengerTranscodingPresetPropertyPage.transcodingpreset_heightlocator.getProperty());
	By audiobitratelocator =By.xpath(AvengerTranscodingPresetPropertyPage.transcodingpreset_audiobitratelocator.getProperty());
	By audiosampleratelocator =By.xpath(AvengerTranscodingPresetPropertyPage.transcodingpreset_audiosampleratelocator.getProperty());
	By transcodingprofilelocator =By.name(AvengerTranscodingPresetPropertyPage.transcodingpreset_transcodingprofilelocator.getProperty());
	By bitratelocator =By.xpath(AvengerTranscodingPresetPropertyPage.transcodingpreset_bitratelocator.getProperty());
	By videobitratelocator =By.xpath(AvengerTranscodingPresetPropertyPage.transcodingpreset_videobitratelocator.getProperty());
	By createbuttonlocator =By.xpath(AvengerTranscodingPresetPropertyPage.transcodingpreset_createbuttonlocator.getProperty());
	By savebuttonlocator=By.xpath("//*[contains(@class,'footer')]/div/div[3]/div/button[2]");			 
	By addpresetbuttonlocator =By.linkText(TranscodingPropertyPage.transcoding_addpresetbuttonlocator.getProperty());

	
	public AvengerTranscodingPresetPage(WebDriver driver,   CustomReport customReport, BasePage basePage) {
		super(driver);
		this.driver = driver;
		
		this.customReport=customReport;
		this.basePage=basePage;
		// TODO Auto-generated constructor stub
	}
	public void createTranscodingpreset(TranscodingBeanPage beanpage)
	{
		By presetstatus=By.xpath("//button[contains(text(),'"+beanpage.getPresetstatus()+"')]");
		enterText(presetlocator,  beanpage.getPresetname());
		enterText(descriptionlocator, beanpage.getDescription());
		click(presetstatus);
		selectValuefromDropDown(outputtypelocator,  beanpage.getOutputtype());
		enterText(keyframeintervallocator,  beanpage.getKeyframeinterval());
		selectValuefromDropDown(frameratelocator,  beanpage.getFramerate());
		enterText(widthlocator,  beanpage.getWidth());
		selectValuefromDropDown(audiobitratelocator,  beanpage.getAudiobitrate());
		selectValuefromDropDown(audiosampleratelocator, beanpage.getAudiosamplerate());
		selectValuefromDropDown(transcodingprofilelocator, beanpage.getTranscodingprofile());
		selectValuefromDropDown(videobitratelocator,  beanpage.getVideobitrate());
		enterText(bitratelocator,  beanpage.getBitrate());
		logger.info("value in video bit rate is"+beanpage.getVideobitrate());
		logger.info("value in bit rate is"+beanpage.getBitrate());
	

	}

	public AvengerTranscodingPage click_CreateButton(TranscodingBeanPage beanpage)
	{
		click(createbuttonlocator);
		waitForElementPresent(addpresetbuttonlocator);
       return basePage.avengerTranscodingPage(driver, customReport, basePage);
	}
	
	public void editTranscoding(TranscodingBeanPage beanpage)
	{
		
		clearWebElementTextusingbackspace(presetlocator,  26);
		enterText(presetlocator,  beanpage.getPresetname());
		enterText(descriptionlocator, beanpage.getDescription());
		enterText(keyframeintervallocator,  beanpage.getKeyframeinterval());
		selectValuefromDropDown(frameratelocator,  beanpage.getFramerate());
		enterText(widthlocator,  beanpage.getWidth());
		enterText(heightlocator,  beanpage.getHeight());
		selectValuefromDropDown(audiobitratelocator,  beanpage.getAudiobitrate());
		selectValuefromDropDown(audiosampleratelocator, beanpage.getAudiosamplerate());
		selectValuefromDropDown(videobitratelocator,  beanpage.getVideobitrate());
		enterText(bitratelocator,  beanpage.getBitrate());
		logger.info("value in video bit rate is"+beanpage.getVideobitrate());
		logger.info("value in bit rate is"+beanpage.getBitrate());
		click(savebuttonlocator);
		waitForElementPresent(addpresetbuttonlocator);
	}

	public String get_inactivePresetStatusError(TranscodingBeanPage beanpage)
	{
		By presetstatus=By.xpath("//button[contains(text(),'"+beanpage.getPresetstatus()+"')]");
		click(presetstatus);
		By inactivepresetstatuserrortext=By.xpath("//button[contains(text(),'"+beanpage.getPresetstatus()+"')]/../../div[2]");
		return getText(inactivepresetstatuserrortext);
	}

}
