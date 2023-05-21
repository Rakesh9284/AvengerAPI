package com.vbrick.avenger.pageobjects;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import com.vbrick.Exception.CustomReport;
import com.vbrick.avenger.ObjProperty.AvengerCategoriesPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerGroupsPropertyPage;
import com.vbrick.avenger.ObjProperty.AvengerMediaPropertyPage;
import com.vbrick.avenger.dao.AddNewPlaylistBeanPage;
import com.vbrick.avenger.dao.AvengerGroupsBeanPage;
import com.vbrick.avenger.funUtil.WebElements;
import com.vbrick.avenger.setup.BasePage;

public class AvengerPlaylistPage extends WebElements{

	private WebDriver driver;
	private CustomReport customReport;
	
	private BasePage basePage;
	
	By videostablocator=By.xpath(AvengerMediaPropertyPage.avengermediapage_videostablocator.getProperty());
    By editplaylistbuttonlocator=By.xpath("//*[@ng-click='playlistNameForm.$show()']");
    By edittextboxlocator=By.xpath("//*[@class='glyphicon glyphicon-ok']/../../..//input");
	By emailPlayList=By.xpath("//*[contains(@class,'glyphicons envelope')]/..");
    private static Logger logger = Logger.getLogger(AvengerPlaylistPage.class);
	
	public AvengerPlaylistPage(WebDriver driver,  CustomReport customReport,BasePage basePage) {
		super(driver);
		this.driver = driver;
		
		this.customReport = customReport;
		this.basePage=basePage;
	}
	
	public AvengerPlaylistPage clickPlayList(String playlistName)
	{ 
		By fileName = By.xpath("//*[contains(text(),'"+playlistName+"')]/../..");
		waitForElementPresent(fileName);
		click(fileName);
		return basePage.avengerPlaylistPage(driver,  customReport, basePage);
	}
	
	public String verify_Playlist(String playlistname)
	{
		By fileName = By.xpath("//*[contains(text(),'"+playlistname+"')]");
		String createdPlaylist=getText(fileName);
		customReport.reporter("Playlist Created with name", createdPlaylist);
		return createdPlaylist;
	}
	public String get_NoofVideosinPlaylist(String playlistname)
	{
		pause(3000);
		By noofvideosplaylistlocator=By.xpath("(//*[contains(@col-id,'playlistIndex')])[last()]");//("//*[contains(@class,'table-cell table-cell-order hide-on-hover')]/span");
		logger.info("No of Videos In playlist are"+getText(noofvideosplaylistlocator));
		return getText(noofvideosplaylistlocator);
	}
	public String get_dateofPlaylist(String playlistname)
	{
		By dateofplaylistlocator=By.xpath("//*[text()='"+playlistname+"']/../../../../*[@class='tile-footer group']/span");
		return getText(dateofplaylistlocator);
	}
	
	
	public AvengerVideoCommentsPage clickVideo(String svideoName)
	{ 
		By fileName = By.xpath("//a[contains(text(),'"+svideoName+"')]");
		waitForElementPresent(fileName);
		clickUsingSwitch(fileName);
		return basePage.avengerVideoCommentsPage(driver,  customReport, basePage);
	}
	
     public String verifyPlaylistPresent(String playlistname)
	{
		By playlist = By.xpath("//*[text()='"+playlistname+"']");
		return String.valueOf(elements(playlist));
	}
	
	public List<String> get_allPlaylistNames()
	{
		By playlistnamelocator= By.xpath("//div/h2");
		List<WebElement> playlistnames=getAllWebElementValues(playlistnamelocator);
	    List<String> allplaylistnames= new ArrayList<String>();
		for (WebElement webElement : playlistnames) {
		logger.info("playlist name is"+webElement.getText());
		allplaylistnames.add(webElement.getText());
	  }
		return allplaylistnames;
		
	}
	

    public void deletePlaylist()
    {
    	By deleteplaylistlocator =By.xpath("//*[contains(@ng-click,'deletePlaylist')]");
    	click(deleteplaylistlocator);
     customReport.reporter("Clicked on Delete Playlist", "");
    }

    public void clickdeletePlaylistPopUp()
    {
    	By categoriespopuplocator=By.xpath(AvengerCategoriesPropertyPage.deletecategorypopuplocator.getProperty());
    	click(categoriespopuplocator);
    	customReport.reporter("Delete Category Pop Up is Clicked","");
    }
    
    public void click_editPlayList(String playlistname)
    {
    	click(editplaylistbuttonlocator);
       enterText(edittextboxlocator,  playlistname);
    }
    
  public void click_OkBUtton()
  {
	  By okbuttonlocator=By.xpath("//*[@class='glyphicon glyphicon-ok']");
      click(okbuttonlocator);
  }

  public AvengerVideoCommentsPage click_PlayButton()
  {
	  By playbuttonlocator=By.xpath("//*[@class='glyphicons play']/..");
	  waitForElementEnable(playbuttonlocator);
	  click(playbuttonlocator);
	  customReport.reporter("Clicked on Play Button in Play List","");
		return basePage.avengerVideoCommentsPage(driver,  customReport, basePage);
  
  }

public void clickListView() {
	By listviewlocator=By.xpath("//button[@aria-label='List View']/span");
	click(listviewlocator);
	customReport.reporter("Click on list view", "");
	
}

public void clickTileView() {
	By listviewlocator=By.xpath("//button[@aria-label='Tile View']/span");
	click(listviewlocator);
	customReport.reporter("Click on Tile view", "");
	
}

public void changeorderVideos(String firstvideo,String secondvideo) {
	
	By firstvideolocator=By.xpath("//*[contains(text(),'"+firstvideo+"')]/../..//*[contains(text(),'VOD')]");
	By secondvideolocator=By.xpath("//*[contains(text(),'"+secondvideo+"')]/../..//*[contains(text(),'VOD')]");
	dragAndDrop(secondvideolocator, firstvideolocator);
	customReport.reporter("Drag and drop completed", "");
	pause(5000);
	
}
  
public ArrayList<String> get_videoNamesInPlaylist()
{
	  By videosinplaylist=By.xpath("//*[@class='GF4oZ__title']/..");//("//*[@class='file-icon']/../a");
	  List<WebElement> allvideos=  getAllWebElementValues(videosinplaylist);
	  ArrayList<String> allvideoslist= new ArrayList<String>();
 for (WebElement webElement : allvideos) {
	   logger.info("video name are"+webElement.getText());
	   allvideoslist.add(webElement.getText());
    }
 return allvideoslist;
}
  
  
  public boolean verifyEmailPlaylistText(){
	  
	  return isDisplayedWithoutException(emailPlayList);
  }
  
  public String getEmailPlayListValue(){
	  
	  return getAttribute(emailPlayList,"ng-href");
  }
  
 
}
