package com.vbrick.avenger.pageobjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerCategoriesPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerLibrariesPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerLibraryInformationPropertyPage;
import com.vbrick.avenger.apibeans.AddCategoryBean;
import com.vbrick.avenger.dao.AvengerGroupsBeanPage;
import com.vbrick.avenger.dao.LibraryBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerLibrariesPage extends WebElements {

	private WebDriver driver;
	private CustomReport customReport;
	
	By librarycreatelibrarybuttonlocator=By.linkText(AvengerLibrariesPropertyPage.library_createlibrarybuttonlocator.getProperty());
	By librarynewlibrarylocator = By.xpath(AvengerLibrariesPropertyPage.library_newlibrarylocator.getProperty());
	By librarycreatebuttonlocator = By.xpath(AvengerLibrariesPropertyPage.library_createbuttonlocator.getProperty());
	By publiclibrarylocator = By.xpath(AvengerLibrariesPropertyPage.library_publiclibrarylocator.getProperty());
	By customizebuttonlocator=By.linkText(AvengerLibrariesPropertyPage.library_customizebuttonlocator.getProperty());
	By cancelbuttonlocator=By.linkText(AvengerLibrariesPropertyPage.library_cancelbuttonlocator.getProperty());
	By librarynameerrortext=By.xpath(AvengerLibrariesPropertyPage.library_librarynameerrortext.getProperty());
	By publicsublibrarybuttonlocator=By.xpath(AvengerLibrariesPropertyPage.library_publicsublibrarybuttonlocator.getProperty());
	By alllibrarieslocator=By.xpath(AvengerLibrariesPropertyPage.library_alllibrarieslocator.getProperty());
	By firstlibrarylocator=By.xpath(AvengerLibrariesPropertyPage.library_firstlibrarylocator.getProperty());
	By saveteamlocator=By.xpath(AvengerLibrariesPropertyPage.library_saveteamlocator.getProperty());
	By librarynametextboxlocator = By.xpath(AvengerLibraryInformationPropertyPage.libraryinformationpage_librarynametextboxlocator.getProperty());
	By findchannelslocator=By.xpath("//*[contains(@aria-label,'Find Channels')]");
	
	private static Logger logger = Logger.getLogger(AvengerLibrariesPage.class);

	private BasePage basePage;
	public AvengerLibrariesPage(WebDriver driver,  
			CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		
		this.customReport = customReport;
		this.basePage=basePage;
	}

	

	public AvengerLibraryInformationPage click_CustomizeButtonLocator()
	{
		click(customizebuttonlocator);
		customReport.reporter("Customize Button is Clicked","");
	   pause(5000);
		waitForElementPresent(librarynametextboxlocator);
		return basePage.avengerLibraryInformationPage(driver,  customReport, basePage);
		
	}
	
	 public ArrayList<String> get_allTeamList()
	    {
	           
	           return getAllWebElementValueslist(alllibrarieslocator);
	    }
	 
	 public AvengerLibraryInformationPage click_firstlibrary()
	 {
		 click(firstlibrarylocator);
		 customReport.reporter("First library is clicked","");
		 waitForElementPresent(saveteamlocator);
		 return basePage.avengerLibraryInformationPage(driver,  customReport, basePage);
		 
	 }
	    
	 
	public Boolean verify_cancelButtonExists()
	{
		if(isEnabled(cancelbuttonlocator)==true)
			customReport.reporter("Cancel Button Exists in Libraries Page","");
		return isEnabled(cancelbuttonlocator);
	}
	
	public Boolean verify_createButtonExists()
	{
		if(isEnabled(librarycreatebuttonlocator)==true)
			customReport.reporter("Create Button Exists in Libraries Page","");
		return isEnabled(librarycreatebuttonlocator);
	}
	
	
	public Boolean verify_IsElementdoesnot_exists(LibraryBeanPage libraryBeanPage)
	{
		By librarysearch = By.xpath(AvengerLibrariesPropertyPage.library_searchlibrary.getProperty());
		enterText(librarysearch,  libraryBeanPage.getLibraryname());
		clickEnter(librarysearch);
		By librarynamelocator= By.partialLinkText(libraryBeanPage.getLibraryname());
		return isDisplayedWithoutException(librarynamelocator);
	}
	

	public Boolean verify_IsElementdoesnot_exists(AddCategoryBean addCategoryBean)
	{
		
		By librarynamelocator= By.partialLinkText(addCategoryBean.getName());
		return isDisplayedWithoutException(librarynamelocator);
	}
	
	
	public Boolean verify_customizeButtonExists()
	{
		if(isEnabled(customizebuttonlocator)==true)
			customReport.reporter("Customize Button Exists in Libraries Page","");
		return isEnabled(customizebuttonlocator);
	}
	
	
	
	public Boolean verify_CreateLibraryButtonExists()
	{
		if(isEnabled(librarycreatelibrarybuttonlocator)==true)
			customReport.reporter("Create Library Button Exists in Libraries Page","");
		return isEnabled(librarycreatelibrarybuttonlocator);
	}

	public AvengerLibraryInformationPage clickCreateLibrary()
	{
		click(librarycreatelibrarybuttonlocator);
		customReport.reporter("Create Library in information page is clicked","");
		pause(5000);
		waitForElementPresent(librarynametextboxlocator);
		return basePage.avengerLibraryInformationPage(driver,  customReport, basePage);
		
	}
	
	public boolean verify_CreateLibrarybutton()
	{
		return locatorsVisibilityAsPerRoles(librarycreatelibrarybuttonlocator);
	}
	
	/**S
	 * Used to Click on create Lib button
	 * 
	 * @param stextValue
	 *            : Enter the text you need.
	 */
	
	public void enter_CreateLibrarytext(LibraryBeanPage libraryBeanPage)
	{
		pause(5000);
		waitForElementPresent(librarynewlibrarylocator);
		enterText(librarynewlibrarylocator, libraryBeanPage.getLibraryname());
		customReport.reporter("Text entered in Create Library TextBox",libraryBeanPage.getLibraryname());
	}
	
	public boolean verify_CreateLibrarytext()
	{
	return locatorsVisibilityAsPerRoles(librarynewlibrarylocator);	
	}
	
	
	public void click_CreateButton() {
		
		submit(librarycreatebuttonlocator);
	    logger.info("Library created on library page");
	    pause(5000);
	}

	public boolean verify_CreateButton()
	{
		return locatorsVisibilityAsPerRoles(librarycreatebuttonlocator);
	}
	
	public String verify_PubliclibraryonTop()
	{
		customReport.reporter("First Library value is",getText(publiclibrarylocator));
		return " "+getText(publiclibrarylocator);
	}
	
	public String verify_publiclibraryCannotBeDeleted()
	{
		 return getAttribute(publicsublibrarybuttonlocator, "class");
	}
	
	public AvengerLibraryInformationPage click_PublicLibrary()
	{
		click(publiclibrarylocator);
		customReport.reporter("Public Library is Clicked","");
		waitForElementPresent(librarynametextboxlocator);
		return basePage.avengerLibraryInformationPage(driver,  customReport, basePage);
	}
	
	public String createNewteam(LibraryBeanPage libraryBeanPage)
	{
		By checkteamcreation=By.xpath("//*[@class='table-cell-library-name padding-left-15']/.");
		enterText(librarynewlibrarylocator, libraryBeanPage.getLibraryname());
		logger.info("The create new team value  is"+libraryBeanPage.getLibraryname());
		click(librarycreatebuttonlocator);
		customReport.reporter("Quick add Team Button Locator is Clicked", "");
		waitForElementPresent(checkteamcreation);
		enterText(librarysearch,  libraryBeanPage.getLibraryname());
		pause(3000);
		clickEnter(librarysearch);
		waitForElementPresent(checkteamcreation);
         pause(3000);
         
		customReport.reporter("Team Created Successfully with Team Name",getText(checkteamcreation));
		String finalteamname = getText(checkteamcreation);
		//to remove NEW from team name
		String finalteamname1 = finalteamname.substring(4,finalteamname.length());
		return  finalteamname1;
	}
	
	public String getteamid(String teamname)
	{
		By checkteamcreation=By.xpath("//*[@class='table-cell-library-name padding-left-15']/.");
		waitForElementPresent(librarycreatelibrarybuttonlocator);
		enterText(librarysearch,  teamname);
		click(checkteamcreation);
		pause(3000);
		String url = driver.getCurrentUrl();
		String urlforid = url.split("/")[9];
		System.out.println(urlforid);
		return urlforid;
	}
	
	
	By librarysearch = By.xpath(AvengerLibrariesPropertyPage.library_searchlibrary.getProperty());
	public String verifyLibraryCreation(LibraryBeanPage libraryBeanPage) {
		
		enterText(librarysearch,  libraryBeanPage.getLibraryname());
		clickEnter(librarysearch);
		By librarynamelocator= By.partialLinkText(libraryBeanPage.getLibraryname());
		waitForElementPresent(librarynamelocator);
		String libname = getText(librarynamelocator);
		customReport.reporter("Library Created Successfully","");
        return libname;
	}
	
	public AvengerLibraryInformationPage clickLibraryCreation(LibraryBeanPage libraryBeanPage) {
		waitForElementPresent(librarycreatelibrarybuttonlocator);
		By librarynamelocator= By.partialLinkText(libraryBeanPage.getLibraryname());
		waitForElementPresent(librarynamelocator);
		customReport.reporter("Library Created Successfully","");
		click(librarynamelocator);
		waitForElementPresent(librarynametextboxlocator);
		return basePage.avengerLibraryInformationPage(driver,  customReport, basePage);
	
	}
	
	
	public void click_subLibraryButton(LibraryBeanPage libraryBeanPage)
	{
		By sublibrarynamelocator= By.xpath("//*[contains(text(),'"+libraryBeanPage.getLibraryname()+"')]/../../div[5]/button");
		click(sublibrarynamelocator);
	}
	
	public void enter_subLibraryText(LibraryBeanPage libraryBeanPage)
	{
		By sublibrarytext=By.xpath("//*[contains(text(),'"+libraryBeanPage.getLibraryname()+"')]/../../../../div[3]//input");
		pause(5000);
		enterText(sublibrarytext,  libraryBeanPage.getSublibraryname());
		
	}
	
	public void create_subLibraryButton(LibraryBeanPage libraryBeanPage)
	{
		By createsublibrarybutton=By.xpath("//*[contains(text(),'"+libraryBeanPage.getLibraryname()+"')]/../../../..//*[@type='submit']");
		submit(createsublibrarybutton);
	}

	public boolean check_createSubLibraryButtonDisplayed(LibraryBeanPage libraryBeanPage)
	{
		By createsublibrarybutton=By.xpath("//*[contains(text(),'"+libraryBeanPage.getLibraryname()+"')]/../../../..//*[@type='submit']");
		return  isDisplayed(createsublibrarybutton);
	}
	
	public boolean check_cancelSubLibraryButtonDisplayed(LibraryBeanPage libraryBeanPage)
	{
		By cancelsublibrarybutton=By.xpath("//*[contains(text(),'"+libraryBeanPage.getLibraryname()+"')]/../../../..//a[@type='button']");
     return    isDisplayed(cancelsublibrarybutton);
	}
	
	public boolean check_customizeSubLibraryButtonDisplayed(LibraryBeanPage libraryBeanPage)
	{
		By customizesublibrarybutton=By.xpath("//a[contains(text(),'"+libraryBeanPage.getLibraryname()+"')]/../../../../div[3]/form/div[2]/a[1]");
     return    isDisplayed(customizesublibrarybutton);
	}
	
	
	
	public String verifySubLibraryCreation(LibraryBeanPage libraryBeanPage)
	{
	By sublibrarynamelocator= By.xpath("//*[contains(text(),'"+libraryBeanPage.getLibraryname()+"')]/../../../../div[4]//*[contains(@class,'library-name')]/a");
	getText(sublibrarynamelocator);
	logger.info("sub library value is"+getText(sublibrarynamelocator));
	customReport.reporter("Sub Library Created Successfully","");
	return getText(sublibrarynamelocator);
	}

	public void dragAndDropLibrary(String Source,String target)
	{
		By sourcelocator=By.xpath("//*[text()='"+Source+"']/../../div[2]/span/span");
		By targetlocator=By.xpath("//*[text()='"+target+"']/../../div[2]/span");
		dragAndDrop(sourcelocator, targetlocator);
		pause(5000);
		customReport.reporter("Moved library through Drag and Drop",Source+target);
	}
	
	public String get_libraryNameErrorText()
	{
		logger.info("The error text value is"+ getText(librarynameerrortext));
		return getText(librarynameerrortext);
	}

	public ArrayList<String> create_MultipleLibraries(LibraryBeanPage librarybeanpage,int nooflibrariestobecreated)
	{
		 ArrayList<String> multiplelibrariesList = new ArrayList<String>();
		  for(int j=0;j<nooflibrariestobecreated;j++)
		  {
		multiplelibrariesList.add(RandomStringUtils.randomAlphabetic(5).toLowerCase());
		  librarybeanpage.setLibraryname(multiplelibrariesList.get(j));
		  enter_CreateLibrarytext(librarybeanpage);
		  click_CreateButton();
		  verifyLibraryCreation(librarybeanpage);  
		  }
		  Collections.sort(multiplelibrariesList);
	      return multiplelibrariesList;
	}
	
	public ArrayList<String> get_AllLibraries()
	{
		
		ArrayList<String> librarynames= new ArrayList<String>();
		
		for (WebElement libraryname : getAllWebElementValues(alllibrarieslocator)) 
			{
			logger.info("library name text is"+libraryname.getText());
		     librarynames.add(libraryname.getText());	
		    }
		return librarynames;
	}
	
	
	public void clickExpandAll()
	 {
		By expandalllocator = By.xpath(AvengerLibrariesPropertyPage.library_expandallbuttonlocator.getProperty());
		clickUsingSwitch(expandalllocator);
	 }
	
	public void clickCollapseAll()
	 {
		By collapsealllocator = By.xpath(AvengerLibrariesPropertyPage.library_collapseallbuttonlocator.getProperty());
		clickUsingSwitch(collapsealllocator);
	 }
	 
	 public boolean libraryVisibility(String libraryname)
	 {
		 By librarylocator = By.xpath("//*[contains(text(),'"+libraryname+"')]");
		 return isDisplayed(librarylocator);
	 }
	 
	 public void delete_Library(String libraryname)
	 {
		 By deletelibrarylocator = By.xpath("//*[text()='"+libraryname+"']/../..//*[@class='glyphicons bin']");
		 click(deletelibrarylocator);
	     customReport.reporter("Deleted Library Button Clicked",libraryname);
	 }
	 public void confirmDelete(String command){

			By deleteusercancelpopuplocator=By.xpath(AvengerCategoriesPropertyPage.deletecategorypopupnolocator.getProperty());
			By deleteuserpopuplocator=By.xpath(AvengerCategoriesPropertyPage.deletecategorypopuplocator.getProperty());
			pause(3000);
			if(command.equals("Y"))
			{
				click(deleteuserpopuplocator);
				customReport.reporter("Library deleted succesfully!!!!", "");
			}
			else
			{
				click(deleteusercancelpopuplocator);

			}
		}
	 public String verifyDeletionOflibrary(LibraryBeanPage librarybean) {
		 pause(3000);
		 By librarynamelocator= By.xpath("//a[(text()='"+librarybean.getLibraryname()+"')]");
		 return String.valueOf(elements(librarynamelocator));
	 }	

	 public  Map<String, String> libraryCannotbeDeletedMessage(LibraryBeanPage librarybean)
	 {
		By librarycannotbedeletedmessage=By.xpath(AvengerLibrariesPropertyPage.librarycannotbedeletedmessage.getProperty());
		By librarynamelocator=By.xpath("//table//*[text()='"+librarybean.getLibraryname()+"']");
		By videocount=By.xpath("//table//*[text()='"+librarybean.getLibraryname()+"']/../td[2]");
		Map<String,String> librarymessage=new HashMap<String, String>();
		librarymessage.put("librarycannotdeletemessage",getText(librarycannotbedeletedmessage));
		librarymessage.put("libraryname",getText(librarynamelocator));
		librarymessage.put("videocount",getText(videocount));
		return librarymessage; 
	 }
	 
	 public String checkingdeletebutton() {
		 pause(3000);
		 By deletebuttonlocator=By.xpath("//span[@class='glyphicons bin']");
		 return String.valueOf(elements(deletebuttonlocator));
	 }


	public Map<String, String> Verify_Labels() {
		
		By createcollectionlocator=By.linkText("Create Collection");
		By findcollectionslocator=By.xpath("//input[@ng-model='filterTxt']");
		By newlibrary=By.xpath("//*[@ng-model='rootLibraryFormData.name']");
		By quickaddlocator=By.xpath("//*[@ng-model='rootLibraryFormData.name']/../..//*[@type='submit']");
		By addchild=By.xpath("//button[contains(@ng-click, 'CreateChildLibrary')]");
		By deletelocator=By.xpath("//button[@ng-click='removeLibrary(library)']");
		Map<String, String> labelsmap=new  HashMap<String, String>();
		labelsmap.put("createcollection", getText(createcollectionlocator));
		labelsmap.put("findcollections", getAttribute(findcollectionslocator, "placeholder"));		
		labelsmap.put("newlibrary", getAttribute(newlibrary, "placeholder"));
		labelsmap.put("quickadd", getText(quickaddlocator));
		labelsmap.put("addchild", getText(addchild));
		labelsmap.put("delete", getText(deletelocator));
		return labelsmap;
	} 
  
	 public String verify_Label(String text)
	 {
		By textlocator=By.xpath("//*[contains(text(),'"+text+"')]"); 
		return getText(textlocator);
	 }
	 
	 By librarysearch1 = By.xpath(AvengerLibrariesPropertyPage.library_searchlibrary.getProperty());
	 By teamadminloc = By.xpath("//button[contains(@ng-model,'isAdmin')]");
	 By teamadminuser = By.xpath("//button[contains(@ng-model,'isAdmin')]/../../child::div[2]");
	 By saveteamloc = By.xpath("//span[@ng-show='$ctrl.team.id']");
		
	 	public ArrayList<String> markasTeamAdmin(LibraryBeanPage libraryBeanPage,int number) {
		
		enterText(librarysearch1,  libraryBeanPage.getLibraryname());
		clickEnter(librarysearch1);
				
		By librarynamelocator= By.partialLinkText(libraryBeanPage.getLibraryname());
		waitForElementPresent(librarynamelocator);
		clickEnter(librarynamelocator);

		List<WebElement> teamadmins = driver.findElements(By.xpath("//div[@ng-if='insight.showIsAdminOption']"));
		if(number==0){
		for(WebElement e : teamadmins) {
				if(e.getText().contains("Team")){
				e.click();
				
				}
		}
		
		}
		
		else if (number==1){
			
			teamadmins.get(0).click();
		
		}
		
		ArrayList<String> teamadminusers = getAllWebElementValueslist(teamadminuser);
		click(saveteamloc);
					
		return teamadminusers;
			
		}
	 
	 	public void Clickfindchannel(String text) {
	 		enterText(librarysearch1,text);
	 		
	 	}
	 	 public void click_createdchannel(String text)
		 {
	 		waitForElement(librarysearch1);
			By textlocator=By.xpath("//a[contains(text(),'"+text+"')]"); 
			click(textlocator);
		 }
	 	 public String click_getusercreatedinchannel(String text)
		 {
			By textlocator=By.xpath("//*[contains(text(),'"+text+"')][2]"); 
			 getText(textlocator); 
			 return text;
			  
		 }
	 	 
	 	 public String click_getgroupcreatedinchannel(String text)
		 {
			By textlocator=By.xpath("//*[contains(text(),'"+text+"')][1]"); 
			 getText(textlocator); 
			 return text;
			  
		 }
	 	 
	 	 
	 	 
	 	 
	 	 
	 	 
	 	 
	 	 
}
