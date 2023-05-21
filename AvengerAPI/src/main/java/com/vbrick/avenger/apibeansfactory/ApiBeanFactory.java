
package com.vbrick.avenger.apibeansfactory;

 
import java.text.DateFormat;
 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
 

import org.apache.commons.lang.RandomStringUtils;
 
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.Module.SetupContext;

import com.vbrick.avenger.apibeans.AddCategoryBean;
import com.vbrick.avenger.apibeans.AddDMEBean;
import com.vbrick.avenger.apibeans.AddGroupBean;
import com.vbrick.avenger.apibeans.AddPlaylistBean;
import com.vbrick.avenger.apibeans.AddTeamBean;
import com.vbrick.avenger.apibeans.AddUploadVideoBean;
import com.vbrick.avenger.apibeans.AddUserApiBean;
import com.vbrick.avenger.apibeans.AddVideoCommentBean;
import com.vbrick.avenger.apibeans.AddZoneBean;
import com.vbrick.avenger.apibeans.CreateEmbeddedContentBean;
import com.vbrick.avenger.apibeans.CreateEventBean;
import com.vbrick.avenger.apibeans.CreatePushContentBean;
import com.vbrick.avenger.apibeans.EventAccessControlBean;
import com.vbrick.avenger.apibeans.LoginBeanApi;
import com.vbrick.avenger.apibeans.ManualEnteredURL;
import com.vbrick.avenger.apibeans.VideoAccessControlBean;
import com.vbricks.avenger.service.ICreateEventService;
import com.vbricks.avenger.service.IEditAccessEvent;
import com.vbricks.avenger.service.IUploadVideoService;
import com.vbricks.avenger.service.IVideoAccessControlService;
import com.vbricks.avenger.service.ImanualEnteredURL;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiUtils;
 

public  class ApiBeanFactory      {
private static Logger logger = Logger.getLogger(ApiBeanFactory.class);


	public void AddUserBean(AddUserApiBean beansPage) {
		
		String val = yyyyMMDDHHmmssTime();
		String firstname = randomAlphabetic(10);
		String lastname = randomAlphabetic(10);
		String contactemail = randomAlphabetic(10) + "@gmail.com";
		String title = "title" + val;
		String phoneno = "432 234 123"+randomNumberGenerationWithinRange(1,10);
		String language = "en";
		String username = randomAlphabetic(10);
		String userrole = "Event Admin";
		String userdefaultrole = "Media Viewer";
		String publishDate = "";
		String whenUploaded = "";
		String expirationDate = "";
		beansPage.setFirstname(firstname);
		beansPage.setLastname(lastname);
		beansPage.setContactemail(contactemail);
		beansPage.setTitle(title);
		beansPage.setPhoneno(phoneno);
		beansPage.setLanguage(language);
		beansPage.setUsername(username);
		beansPage.setUserrole(userrole);
		beansPage.setUserdefaultrole(userdefaultrole);
		
 
	}
	public void AddLoginBean(LoginBeanApi beansPage) {
		
	 		 
		beansPage.setUsername("rajitha");
		beansPage.setPassword("vbqavbqa");
		 
	}

	public void UploadVideoBean(AddUploadVideoBean beansPage) {
		 
		String title="APIUserUpload_"+randomAlphabetic(10);
		String description="APIUpload Description_"+randomAlphabetic(10);
		String enableComments=IUploadVideoService.enableCommentsAsTrue;
		String enableDownloads=IUploadVideoService.enableDownloadsAsTrue;
		String enableRatings=IUploadVideoService.enableRatingsAsTrue;
		String uploader="";
		String isActive=IUploadVideoService.IsActiveAsTrue;
		String videoAccessControl=IUploadVideoService.videoAccessControl[0];
		String expirationAction = "";
		String publishDate = "";
		String expirationDate = "";
		String whenUploaded = "";
		String unlisted=IUploadVideoService.IsUnlistedFalse;
		String is360=IUploadVideoService.Is360False;
				
		String tags[]=IUploadVideoService.tags;

		
		String getAccesscontrolforuser="";
		String getAccesscontrolforgroup="";
		String getAccesscontrolforteam="";
		String categories[]=IUploadVideoService.categories;
   		String categoryIds[]=IUploadVideoService.categoryIds;
   				 
		
		beansPage.setTitle(title);
		beansPage.setDescription(description);
		beansPage.setUploader(uploader);
		
		beansPage.setEnableComments(enableComments);
		beansPage.setEnableDownloads(enableDownloads);
		beansPage.setEnableRatings(enableRatings);
		beansPage.setIsActive(isActive);
		beansPage.setVideoAccessControl(videoAccessControl);
		beansPage.setTags(tags);
		beansPage.setAccesscontrolforuser(getAccesscontrolforuser);
		beansPage.setAccesscontrolforgroup(getAccesscontrolforgroup);
		beansPage.setAccesscontrolforteam(getAccesscontrolforteam);
		beansPage.setCategories(categories);
		beansPage.setCategoryIds(categoryIds);
		beansPage.setPublishDate(publishDate);
		beansPage.setExpirationAction(expirationAction);
		beansPage.setExpirationDate(expirationDate);
		beansPage.setWhenUploaded(whenUploaded);
		beansPage.setIs360(is360);
		beansPage.setUnlisted(unlisted);
	}
	 
	public void EditVideoBean(AddUploadVideoBean beansPage) {
		 
		String title="";
		String description="";
		String enableComments="";
		String enableDownloads="";
		String enableRatings="";
		String uploader="";
		String isActive="";
		String videoAccessControl=IUploadVideoService.videoAccessControl[0];
		
		String tags[]=IUploadVideoService.tagsforedit;
		String getAccesscontrolforuser="";
		String getAccesscontrolforgroup="";
		String getAccesscontrolforteam="";
		String categories[]=IUploadVideoService.categories;
   		String categoryIds[]=IUploadVideoService.categoryIds;
		 
		
		beansPage.setTitle(title);
		beansPage.setDescription(description);
		beansPage.setUploader(uploader);
		
		beansPage.setEnableComments(enableComments);
		beansPage.setEnableDownloads(enableDownloads);
		beansPage.setEnableRatings(enableRatings);
		beansPage.setIsActive(isActive);
		beansPage.setVideoAccessControl(videoAccessControl);
		beansPage.setTags(tags);
		beansPage.setAccesscontrolforuser(getAccesscontrolforuser);
		beansPage.setAccesscontrolforgroup(getAccesscontrolforgroup);
		beansPage.setAccesscontrolforteam(getAccesscontrolforteam);
		beansPage.setCategories(categories);
		beansPage.setCategoryIds(categoryIds);
	}
	 
 
	public void SumbitCommentBean(AddVideoCommentBean beansPage) {
	

		String comment="APIVideoComment_"+randomAlphabetic(5);
		beansPage.setComment(comment);
		
	}
	
public void SubmitZoneBean(AddZoneBean beansPage) {
		

		String name="APIZone_"+randomAlphabetic(5);
		beansPage.setName(name);
		
	}
	
    public void createDMEBean(AddDMEBean beansPage) {
		

		String name="APIDME_"+randomAlphabetic(5);
		beansPage.setName(name);;
		String macAddress="000C2808C8"+randomNumberGenerationWithinRange(1,9)+randomNumberGenerationWithinRange(1,9);
		beansPage.setMacAddress(macAddress);
		beansPage.setIsActive(true);
		
		
	}

	 

	public void CreateCategoryBean(AddCategoryBean beansPage) {
	
		String name="API_Category_"+randomAlphabetic(5);
		beansPage.setName(name);
		String parentCategoryId="API_parentCategoryId_"+randomAlphabetic(5);
		beansPage.setParentCategoryId(parentCategoryId); 
	}
	
	public void CreateTeamBean(AddTeamBean beansPage) {
		
		String name="API_Team_"+randomAlphabetic(5);
		beansPage.setName(name);
		
		String description="API_Team_Description"+randomAlphabetic(5);
		beansPage.setDescription(description);
		
		beansPage.setUserids(new String[0]); 
		
		beansPage.setGroupids(new String[0]);
		
 		}

   public void CreatePlaylistBean(AddPlaylistBean beansPage) {
		
		String name="API_Playlist_"+randomAlphabetic(5);
		beansPage.setName(name);
		
		String videoids="";
		beansPage.setVideoids(videoids); 
		
 		}
	
   public void CreateGroupBean(AddGroupBean beansPage) {
		
		String name="API_Group_"+randomAlphabetic(5);
		beansPage.setName(name);
				
		beansPage.setUserids(new String[0]); 
		
		beansPage.setRoleids(new String[0]);
				
	}

	public void VideoAccesscontrolBean(VideoAccessControlBean beanPage){
		
		String canEditGroup=IVideoAccessControlService.groupcanEdit;
        beanPage.setGroupcanEdit(canEditGroup);
          
        beanPage.setGroupid(IVideoAccessControlService.groupid);
        beanPage.setGrouptype(IVideoAccessControlService.grouptype);
          
        beanPage.setUsercanEdit(IVideoAccessControlService.usercanEdit);
        beanPage.setUserid(IVideoAccessControlService.userid);
        beanPage.setUsertype(IVideoAccessControlService.usertype);
          
        beanPage.setTeamcanEdit(IVideoAccessControlService.teamcanEdit);
        beanPage.setTeamid(IVideoAccessControlService.teamid);
        beanPage.setTeamtype(IVideoAccessControlService.teamtype);
               
	}
	
	public void ManualEnteredBean(ManualEnteredURL beansPage) {
		 
		String title="APIManualEnteredURL_"+randomAlphabetic(10);
		String description="APIManualEntered Description_"+randomAlphabetic(10);
		String enableComments=ImanualEnteredURL.enableCommentsAsTrue;
		String enableDownloads=ImanualEnteredURL.enableDownloadsAsFalse;
		String enableRatings=ImanualEnteredURL.enableRatingsAsTrue;
		String uploader="kalyan";
		String isActive=ImanualEnteredURL.IsActiveAsTrue;
		String videoAccessControl=ImanualEnteredURL.videoAccessControl[0];
		
		String tags[]=ImanualEnteredURL.tags;
   		String categoryIds[]=ImanualEnteredURL.categoryIds;
   	
		beansPage.setTitle(title);
		beansPage.setDescription(description);
		beansPage.setUploader(uploader);
		
		beansPage.setEnableComments(enableComments);
		beansPage.setEnableDownloads(enableDownloads);
		beansPage.setEnableRatings(enableRatings);
		beansPage.setIsActive(isActive);
		beansPage.setVideoAccessControl(videoAccessControl);
		beansPage.setTags(tags);
		
		beansPage.setCategoryIds(categoryIds);
	}
	
	 
	  public void EventBean(CreateEventBean beansPage) {
		ApiUtils apiutils=new ApiUtils();
		HashMap<String,String>dates=apiutils.UTCdatetimeformatEvent(beansPage.getDays() ,beansPage.getMinutes());
		String Title="API CREATEEVENT_"+randomAlphabetic(10);
		String Description="APICREATEEVENT Description_"+randomAlphabetic(10);
		String Shortcut="Shortcut-"+randomAlphabetic(4);

		
		String StartDate= dates.get("eventstartdate");
		String EndDate= dates.get("eventenddate");
					
		String PresentationProfileId=ICreateEventService.PresentationProfileId;
		String AnonymousQuestionsEnabled=ICreateEventService.anonymousQuestionsEnabled;
		
		String IsPublic=ICreateEventService.Ispublic;
		String PollsEnabled=ICreateEventService.pollsenabled;
		String AutomatedWebcast=ICreateEventService.automatedwebcast;
		String ChatEnabled=ICreateEventService.chatenabled;
		String QuestionAndAnswerEnabled=ICreateEventService.questionAndanswerenabled;
		String Unlisted=ICreateEventService.UnListed;
		String VideoAddress=ICreateEventService.videoaddress;
		String LobbyTime=ICreateEventService.lobbytime;
		
		String UserIds[]=ICreateEventService.UserIds;
		String ModeratorIds[]=ICreateEventService.ModeratorIds;
		String GroupIds[]=ICreateEventService.GroupIds;
		String EventAdminIds[]=ICreateEventService.EventAdminIds;
		String accessControl = ICreateEventService.accessControl;
		String estimatedAttendees = ICreateEventService.attendees;
	
		
		beansPage.setTitle(Title);
		beansPage.setDescription(Description);
		beansPage.setStartDate(StartDate);
		
		beansPage.setEndDate(EndDate);
		beansPage.setPresentationProfileId(PresentationProfileId);
		beansPage.setAnonymousQuestionsEnabled(AnonymousQuestionsEnabled);
		beansPage.setIsPublic(IsPublic);
		beansPage.setPollsEnabled(PollsEnabled);
		beansPage.setAutomatedWebcast(AutomatedWebcast);
		beansPage.setChatEnabled(ChatEnabled);
		beansPage.setQuestionAndAnswerEnabled(QuestionAndAnswerEnabled);
		
		beansPage.setUserIds(UserIds);
		beansPage.setModeratorIds(ModeratorIds);
		beansPage.setGroupIds(GroupIds);
		beansPage.setEventAdminId(EventAdminIds);
		beansPage.setAccessControl(accessControl);
		beansPage.setEstimatedAttendees(estimatedAttendees);
		beansPage.setShortcut(Shortcut);
		beansPage.setUnlisted(Unlisted);
		beansPage.setVideoAddress(VideoAddress);
		beansPage.setLobbytime(LobbyTime);
	}
	
	
	   public void EditAccessEventBean(EventAccessControlBean beansPage) {
			
	    String UserIds[]=IEditAccessEvent.UserIds;
	    String Usernames[]=IEditAccessEvent.Usernames;
		String GroupIds[]=IEditAccessEvent.GroupIds;
		
		beansPage.setUserIds(UserIds);
		beansPage.setUsernames(Usernames);
		beansPage.setGroupIds(GroupIds);
	
		}
 
	public String yyyyMMDDHHmmssTime() {
		DateFormat df = new SimpleDateFormat("yyyyMMDDHHmmss");
		String sdt = df.format(new Date(System.currentTimeMillis()));
		System.out.println(sdt);
		return sdt;
		
	}

		public String randomAlphabetic(int a) {
		 		
			return   RandomStringUtils.randomAlphanumeric(a + 1);
	}

		public int randomNumberGenerationWithinRange(int low,int high)
		{
			Random Hours = new Random();
			int randomnumber=Hours.nextInt(high-low) + low;	
		    logger.info("The Random Number Generated is"+randomnumber);
			return randomnumber;
		}
		  
		
		public static void main(String args[]){
			ApiBeanFactory a=new ApiBeanFactory();
			a.yyyyMMDDHHmmssTime();
		}
		
		public void EventBean1(CreateEventBean beansPage) {
			ApiUtils apiutils=new ApiUtils();
			HashMap<String,String>dates=apiutils.UTCdatetimeformatEvent(beansPage.getDays() ,beansPage.getMinutes());
			String Title="API CREATEEVENT_"+randomAlphabetic(10);
			String Description="APICREATEEVENT Description_"+randomAlphabetic(10);
			String Shortcut="Shortcut-"+randomAlphabetic(4);
			String videoSourceType=ICreateEventService.videoSourceType[1];
			String presenterId=ICreateEventService.videoSourceType[1];
			
			String StartDate= dates.get("eventstartdate");
			String EndDate= dates.get("eventenddate");
			
			String AnonymousQuestionsEnabled=ICreateEventService.anonymousQuestionsEnabled;
			
			String IsPublic=ICreateEventService.Ispublic;
			String PollsEnabled=ICreateEventService.pollsenabled;
			String AutomatedWebcast=ICreateEventService.automatedwebcast;
			String ChatEnabled=ICreateEventService.chatenabled;
			String QuestionAndAnswerEnabled=ICreateEventService.questionAndanswerenabled;
			String Unlisted=ICreateEventService.UnListed;
			String VideoAddress=ICreateEventService.videoaddress;
			String LobbyTime=ICreateEventService.lobbytime;
			
			String UserIds[]=ICreateEventService.UserIds;
			String ModeratorIds[]=ICreateEventService.ModeratorIds;
			String GroupIds[]=ICreateEventService.GroupIds;
			String EventAdminIds[]=ICreateEventService.EventAdminIds;
			String accessControl = ICreateEventService.accessControl;
			String estimatedAttendees = ICreateEventService.attendees;
		
			beansPage.setTitle(Title);
			beansPage.setDescription(Description);
			beansPage.setStartDate(StartDate);
			beansPage.setEndDate(EndDate);
			beansPage.setAnonymousQuestionsEnabled(AnonymousQuestionsEnabled);
			beansPage.setIsPublic(IsPublic);
			beansPage.setPollsEnabled(PollsEnabled);
			beansPage.setAutomatedWebcast(AutomatedWebcast);
			beansPage.setChatEnabled(ChatEnabled);
			beansPage.setQuestionAndAnswerEnabled(QuestionAndAnswerEnabled);
			beansPage.setVideoSourceType(videoSourceType);
			beansPage.setUserIds(UserIds);
			beansPage.setModeratorIds(ModeratorIds);
			beansPage.setGroupIds(GroupIds);
			beansPage.setEventAdminId(EventAdminIds);
			beansPage.setAccessControl(accessControl);
			beansPage.setEstimatedAttendees(estimatedAttendees);
			beansPage.setShortcut(Shortcut);
			beansPage.setUnlisted(Unlisted);
			beansPage.setVideoAddress(VideoAddress);
			beansPage.setLobbytime(LobbyTime);
		}
		
		 public void EventBean2(CreateEventBean beansPage) {
				ApiUtils apiutils=new ApiUtils();
				HashMap<String,String>dates=apiutils.UTCdatetimeformatEvent1(beansPage.getDays() ,beansPage.getMinutes());
				String Title="API CREATEEVENT_"+randomAlphabetic(10);
				String Description="APICREATEEVENT Description_"+randomAlphabetic(10);
				String Shortcut="Shortcut-"+randomAlphabetic(4);

				
				String StartDate= dates.get("eventstartdate");
				String EndDate= dates.get("eventenddate");
							
				String PresentationProfileId=ICreateEventService.PresentationProfileId;
				String AnonymousQuestionsEnabled=ICreateEventService.anonymousQuestionsEnabled;
				
				String IsPublic=ICreateEventService.Ispublic;
				String PollsEnabled=ICreateEventService.pollsenabled;
				String AutomatedWebcast=ICreateEventService.automatedwebcast;
				String ChatEnabled=ICreateEventService.chatenabled;
				String QuestionAndAnswerEnabled=ICreateEventService.questionAndanswerenabled;
				String Unlisted=ICreateEventService.UnListed;
				String VideoAddress=ICreateEventService.videoaddress;
				String LobbyTime=ICreateEventService.lobbytime;
				
				String UserIds[]=ICreateEventService.UserIds;
				String ModeratorIds[]=ICreateEventService.ModeratorIds;
				String GroupIds[]=ICreateEventService.GroupIds;
				String EventAdminIds[]=ICreateEventService.EventAdminIds;
				String accessControl = ICreateEventService.accessControl;
				String estimatedAttendees = ICreateEventService.attendees;
			
				
				beansPage.setTitle(Title);
				beansPage.setDescription(Description);
				beansPage.setStartDate(StartDate);
				
				beansPage.setEndDate(EndDate);
				beansPage.setPresentationProfileId(PresentationProfileId);
				beansPage.setAnonymousQuestionsEnabled(AnonymousQuestionsEnabled);
				beansPage.setIsPublic(IsPublic);
				beansPage.setPollsEnabled(PollsEnabled);
				beansPage.setAutomatedWebcast(AutomatedWebcast);
				beansPage.setChatEnabled(ChatEnabled);
				beansPage.setQuestionAndAnswerEnabled(QuestionAndAnswerEnabled);
				
				beansPage.setUserIds(UserIds);
				beansPage.setModeratorIds(ModeratorIds);
				beansPage.setGroupIds(GroupIds);
				beansPage.setEventAdminId(EventAdminIds);
				beansPage.setAccessControl(accessControl);
				beansPage.setEstimatedAttendees(estimatedAttendees);
				beansPage.setShortcut(Shortcut);
				beansPage.setUnlisted(Unlisted);
				beansPage.setVideoAddress(VideoAddress);
				beansPage.setLobbytime(LobbyTime);
			}
		public void PushContentbean(CreatePushContentBean bean) {
        String name="PushContent_"+randomAlphabetic(4);
         bean.setName(name);
       }
		
		public void EmbeddedContentbean(CreateEmbeddedContentBean beanPage) {
	        String name="EmbeddedContent_"+randomAlphabetic(4);
	         beanPage.setName(name);
	       }
			
		
		
		
		
		
		
		
		
		
}