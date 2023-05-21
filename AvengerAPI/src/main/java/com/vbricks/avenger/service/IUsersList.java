package com.vbricks.avenger.service;

public interface IUsersList {

	String apieau = "apieau";
	String apimau = "apimau";
	String apimvu = "apimvu";
	String apimcu = "apimcu";
	String apiehu = "apiehu";

	// ***** Start ******** com.vbrick.avenger.createuserapi_AV_9874_testcases
	// ****************************
	// 1. AddNewUser only Admin user only
	String loginaccountadmin = "apiaau35";
	String Logineventhost = "apiehu35";
	String logineventadmin = "apieau35";
	String loginmediaadmin = "apimau35";
	String loginmediaviewer = "apimvu35";
	String loginmediacontributor = "apimcu35";

	// 2. LoginUser --Only Adminuser


	// ***** Start ******* com.vbrick.avenger.uploadvideoapi_AV_6015_testcases
	// ****************************
	// Edit Videos
	String EditVideoseventadmin = "apieau3";
	String EditVideosmediaadmin = "apimau3";
	String EditVideosmediaviewer = "apimvu3";
	String EditVideosmediacontributor = "apimcu3";

	// Upload Image File
	String UploadImageFileeventadmin = "apieau4";
	String UploadImageFilemediaadmin = "apimau4";
	String UploadImageFilemediaviewer = "apimvu5";
	String UploadImageFilemediacontributor = "apimcu4";

	// Upload Supplemental File
	String UploadSupplementalFileeventadmin = "apieau5";
	String UploadSupplementalFilemediaadmin = "apimau5";
	String UploadSupplementalFilemediaviewer = "apimvu5";
	String UploadSupplementalFilemediacontributor = "apimcu5";

	// Upload Transcription File
	String UploadTranscriptionFileeventadmin = "apieau6";
	String UploadTranscriptionFilemediaadmin = "apimau6";
	String UploadTranscriptionFilemediaviewer = "apimvu6";
	String UploadTranscriptionFilemediacontributor = "apimcu6";

	// Upload Videos
	String UploadVideoseventadmin = "apieau7";
	String UploadVideoseventhost = "apiehu7";
	String UploadVideosmediaadmin = "apimau7";
	String UploadVideosmediaviewer = "apimvu7";
	String UploadVideosmediacontributor = "apimcu7";

	// Video Access Control 32 updated in code
	String VideoAccessControleventadmin = "apieau8";
	String VideoAccessControlmediaadmin = "apimau8";
	String VideoAccessControlmediaviewer = "apimvu8";
	String VideoAccessControlmediacontributor = "apimcu8";

	// ***** End ******* com.vbrick.avenger.uploadvideoapi_AV_6015_testcases
	// ****************************

	// *****&&&& Start &&&& *************
	// com.vbrick.avenger.videocommentapi_AV_9879_testcases
	// ************************
	// Create category -1
	String CreateCategoryeventadmin = "apieau9";
	String CreateCategorymediaadmin = "apimau9";
	String CreateCategorymediaviewer = "apimvu9";
	String CreateCategorymediacontributor = "apimcu9";

	// Create Event -2
	String CreateEventeventadmin = "apieau10";
	String CreateEventmediaadmin = "apimau10";
	String CreateEventmediaviewer = "apimvu10";
	String CreateEventmediacontributor = "apimcu10";
	String CreateEventeventhost = "apiehu11";
	String CreateEventmediaviewer1 = "apimvu0";
	
	// Create Event for backgrund image upload 
	String CreateEventbackgroundimgeventadmin = "apieau0";
	String CreateEventbackgroundimgmediaadmin = "apimau0";
	String CreateEventbackgroundimgmediaviewer = "apimvu43";
	String CreateEventbackgroundimgmediacontributor = "apimcu0";
	String CreateEventbackgroundimgeventhost = "apiehu0";
	String CreateEventbackgroundimgeventadmin1 = "apimvu_e";


	// Custom Field -3 updated in code
	String CustomFieldeventadmin = "apieau11";
	String CustomFieldmediaadmin = "apimau11";
	String CustomFieldmediaviewer = "apimvu11";
	String CustomFieldmediacontributor = "apimcu11";
	String CustomFieldeventhost = "apiehu11";
	String CustomFieldmediaviewer1 = "apimvu1";

	// Delete Video -4 updated in code
	String DeleteVideoeventadmin = "apieau12";
	String DeleteVideomediaadmin = "apimau12";
	String DeleteVideomediaviewer = "apimvu12";
	String DeleteVideomediacontributor = "apimcu12";
	String DeleteVideomediacontributor1 = "apimcu1"; 
	String DeleteVideoeventhost = "apiehu12";
	String DeleteVideoaccountadmin = "apiaau12";

	// DME Device List -5 updated in code
	String DMEDeviceslisteventadmin = "apieau13";
	String DMEDeviceslistmediaadmin = "apimau13";
	String DMEDeviceslistmediaviewer = "apimvu13";
	String DMEDeviceslistmediacontributor = "apimcu13";

	// Video Download -6
	String DownloadVideoeventadmin = "apieau14";
	String DownloadVideomediaadmin = "apimau14";
	String DownloadVideomediaviewer = "apimvu47";
	String DownloadVideomediaviewer1 = "apimvu48";
	String DownloadVideomediacontributor = "apimcu14";
	String DownloadVideoeventhost = "apiehu14";

	// Edit Access Control Entities Event -7
	String EditAccessControlEntitiesEventsapieau = "apieau15";
	String EditAccessControlEntitiesEventsmediaadmin = "apimau15";
	String EditAccessControlEntitiesEventsmediaviewer = "apimvu15";
	String EditAccessControlEntitiesEventsmediacontributor = "apimcu15";

	// Edit Event -8
	String EditEventeventadmin = "apieau16";
	String EditEventmediaadmin = "apimau16";
	String EditEventmediaviewer = "apimvu16";
	String EditEventmediacontributor = "apimcu16";
	String EditEventeventhost = "apiehu2";
	String EditEventeventadmin1 = "apieau2";
	String EditEventmediaadmin1 = "apimau2";
	String EditEventmediaviewer1 = "apimvu2";
	String EditEventmediacontributor1 = "apimcu2";
	String EditEventeventhost1 = "apiehu2";
	
	// Edit Event -part2
	String EditEventeventadmin2 = "apieau35";
	String EditEventmediaadmin2 = "apimau35";
	String EditEventmediaviewer2 = "apimvu35";
	String EditEventmediacontributor2 = "apimcu35";
	// Edit Video Rating -10
	String EditVideoRatingeventadmin = "apieau17";
	String EditVideoRatingmediaadmin = "apimau17";
	String EditVideoRatingmediaviewer = "apimvu17";
	String EditVideoRatingmediacontributor = "apimcu17";

	// Edit Events List
	String EventListeventadmin = "apieau18";
	String EventListmediaadmin = "apimau18";
	String EventListmediaviewer = "apimvu18";
	String EventListmediacontributor = "apimcu18";
	String EventListaccountadmin = "apiaau14";

	// Get Category List
	String GetCategoryListeventadmin = "apieau19";
	String GetCategoryListmediaadmin = "apimau19";
	String GetCategoryListmediaviewer = "apimvu19";
	String GetCategoryListmediacontributor = "apimcu19";
	String GetCategoryListeventhost = "apiehu19";

	// Get Event Details
	String GetEventDetailseventadmin = "apieau20";
	String GetEventDetailsmediaadmin = "apimau20";
	String GetEventDetailsmediaviewer = "apimvu20";
	String GetEventDetailsmediacontributor = "apimcu20";
	String GetEventDetailseventhost = "apiehu20";

	// Get User Roles
	String GetUserRoleseventadmin = "apieau21";
	String GetUserRolesmediaadmin = "apimau21";
	String GetUserRolesmediaviewer = "apimvu21";
	String GetUserRolesmediacontributor = "apimcu21";
	String GetUserRoleseventhost = "apiehu21";

	// Get Video Comments -14
	String GetVideoCommentseventadmin = "apieau22";
	String GetVideoCommentsmediaadmin = "apimau22";
	String GetVideoCommentsmediaviewer = "apimvu22";
	String GetVideoCommentsmediacontributor = "apimcu22";

	// Get Video Status -15
	String GetVideoStatuseventadmin = "apieau23";
	String GetVideoStatusmediaadmin = "apimau23";
	String GetVideoStatusmediaviewer = "apimvu23";
	String GetVideoStatusmediacontributor = "apimcu23";

	// Manual Entered URL
	String ManualEnteredURLeventadmin = "apieau24";
	String ManualEnteredURLmediaadmin = "apimau24";
	String ManualEnteredURLmediaviewer = "apimvu24";
	String ManualEnteredURLmediacontributor = "apimcu24";

	// MIgrate Video
	String MigrateVideoeventadmin = "apieau25";
	String MigrateVideomediaadmin = "apimau25";
	String MigrateVideomediaviewer = "apimvu25";
	String MigrateVideomediacontributor = "apimcu25";

	// Presentation profile
	String PresentationProfileeventadmin = "apieau26";
	String PresentationProfilemediaadmin = "apimau26";
	String PresentationProfilemediaviewer = "apimvu26";
	String PresentationProfilemediacontributor = "apimcu26";

	// Search Access Entity 19
	String SearchAccessEntityeventadmin = "apieau27";
	String SearchAccessEntitymediaadmin = "apimau27";
	String SearchAccessEntitymediaviewer = "apimvu27";
	String SearchAccessEntitymediacontributor = "apimcu27";
	String SearchAccessEntityeventhost = "apiehu27";

	// VideoSearchApi URL
	// SubmitVideosComments
	String SubmitVideosCommentseventadmin = "apieau28";
	String SubmitVideosCommentsmediaadmin = "apimau28";
	String SubmitVideosCommentsmediaviewer = "apimvu28";
	String SubmitVideosCommentsmediacontributor = "apimcu28";

	// User Details
	String UserDetailseventadmin = "apieau29";
	String UserDetailsmediaadmin = "apimau29";
	String UserDetailsmediaviewer = "apimvu45";
	String UserDetailsmediacontributor = "apimcu29";
	String UserDetailseventhost = "apiehu41";
	String UserDetailseventhost1 = "apiehu42";
	String UploadRightsformediaviewer = "apimvu46";
	String UploadRidhtsformediaviewer1="apimvu43";
	String UploadRightsformediacontributor = "apimcu46";
	String UploadRightsforeventhost = "apiehu46";

	// User Name
	String UserNameeventadmin = "apieau40";
	String UserNamemediaadmin = "apimau30";
	String UserNamemediaviewer = "apimvu30";
	String UserNamemediacontributor = "apimcu30";
	String UserNameeventhost = "apiehu41";

	// User Video Watch Status 22
	String UserVideoWatchStatuseventadmin = "apieau31";
	String UserVideoWatchStatusmediaadmin = "apimau31";
	String UserVideoWatchStatusmediaviewer = "apimvu31";
	String UserVideoWatchStatusmediacontributor = "apimcu31";

	// Video List
	String VideoListeventadmin = "apieau32";
	String VideoListmediaadmin = "apimau32";
	String VideoListmediaviewer = "apimvu32";
	String VideoListmediacontributor = "apimcu32";

	// Video PlayBack URL
	String PlayBackURLeventadmin = "apieau33";
	String PlayBackURLeventhost = "apiehu33";
	String PlayBackURLmediaadmin = "apimau33";
	String PlayBackURLmediaviewer = "apimvu33";
	String PlayBackURLmediaviewer1 = "apimvu3";
	String PlayBackURLmediacontributor = "apimcu33";

	// Video Search
	String VideoSearcheventadmin = "apieau34";
	String VideoSearchmediaadmin = "apimau34";
	String VideoSearchmediaviewer = "apimvu34";
	String VideoSearchmediacontributor = "apimcu34";
	String VideoSearch="apimau35";
	
	// Video Details
    String VideoDetaileventadmin = "apieau36";
    String VideoDetailmediaadmin = "apimau36";
    String VideoDetailmediaviewer = "apimvu36";
    String canEditVideoDetailmediaviewer = "apimvu55";
	String VideoDetailmediacontributor = "apimcu40";
	String VideoDetaileventhost = "apiehu36";

	// User Email
    String UserEmaileventadmin = "apieau36";
    String UserEmailmediaadmin = "apimau40";
    String UserEmailmediaviewer = "apimvu36";
	String UserEmailmediacontributor = "apimcu36";
	String UserEmaileventhost = "apiehu36";
	String UserEmailAccountAdmin = "apiaau61";
	
	
	// Create Team
	String CreateTeameventadmin = "apieau34";
	String CreateTeameventhost = "apiehu34";
	String CreateTeammediaadmin = "apimau34";
	String CreateTeammediaviewer = "apimvu34";
	String CreateTeammediacontributor = "apimcu34";
	
	// Edit Team
	String EditTeameventadmin = "apieau35";
	String EditTeameventhost = "apiehu10";
	String EditTeammediaadmin = "apimau35";
	String EditTeammediaviewer = "apimvu35";
	String EditTeammediacontributor = "apimcu35";
	
	// Delete Team
	String DeleteTeameventadmin = "apieau36";
	String DeleteTeammediaadmin = "apimau36";
	String DeleteTeammediaviewer = "apimvu36";
	String DeleteTeammediacontributor = "apimcu36";

	// Create Group
	String CreateGroupEventAdmin = "apieau37";
	String CreateGroupEventHost = "apiehu37";
	String CreateGroupMediAdmin = "apimau37";
	String CreateGroupMediaViewer = "apimvu37";
	String CreateGroupMediaContributor = "apimcu37";
	
	// Edit Group
	String EditGroupEventAdmin = "apieau38";
	String EditGroupEventHost = "apiehu38";
	String EditGroupMediAdmin = "apimau38";
	String EditGroupMediaViewer = "apimvu38";
	String EditGroupMediaContributor = "apimcu38";
	
	// Delete Group
	String DeleteGroupeventadmin = "apieau39";
	String DeleteGroupmediaadmin = "apimau39";
	String DeleteGroupmediaviewer = "apimvu39";
	String DeleteGroupmediacontributor = "apimcu39";
	
	// Create Playlist
	String CreatePlaylistEventAdmin = "apieau2";
	String CreatePlaylistMediAdmin = "apimau2";
	String CreatePlaylistMediaViewer = "apimvu2";
	String CreatePlaylistMediaContributor = "apimcu2";
	String CreatePlaylistEventHost = "apiehu2";
	
	// Delete Playlist
	String DeletePlaylistEventAdmin = "apieau32";
	String DeletePlaylistMediAdmin = "apimau32";
	String DeletePlaylistMediaViewer = "apimvu32";
	String DeletePlaylistMediaContributor = "apimcu32";
	String DeletePlaylistEventHost = "apiehu32";
		
	// Delete Category
	String DeleteCategoryeventadmin = "apieau2";
	String DeleteCategorymediaadmin = "apimau2";
	String DeleteCategorymediaviewer = "apimvu2";
	String DeleteCategorymediacontributor = "apimcu2";
	
	// Get Teams
	String GetTeamseventadmin = "apieau3";
	String GetTeamseventhost = "apiehu3";
	String GetTeamsmediaadmin = "apimau3";
	String GetTeamsmediaviewer = "apimvu42";
	String GetTeamsmediaviewer1 = "apimvu3";
	String GetTeamsmediacontributor = "apimcu3";
	

	// Get Approval Templates
	String GetApprovalTemplateeventadmin = "apieau21";
	String GetApprovalTemplatemediaadmin = "apimau21";
	String GetApprovalTemplatemediaviewer = "apimvu21";
	String GetApprovalTemplatemediacontributor = "apimcu21";
	
	// Send video for approval
	String SendVideoForApprovaleventadmin = "apieau25";
	String SendVideoForApprovalmediaadmin = "apimau25";
	String SendVideoForApprovalmediaviewer = "apimvu25";
	String SendVideoForApprovalcontributor = "apimcu25";
	
	// Approve video
	String ApproveVideoeventadmin = "apieau30";
	String ApproveVideomediaadmin = "apimau30";
	String ApproveVideomediaviewer = "apimvu30";
	String ApproveVideomediacontributor = "apimcu30";
	
	// Reject Video
	String RejectVideoeventadmin = "apieau29";
	String RejectVideomediaadmin = "apimau29";
	String RejectVideomediaviewer = "apimvu29";
	String RejectVideomediacontributor = "apimcu29";
	
	// Get VideosPendingforApproval
	String VideoPendingeventadmin = "apieau1";
	String VideoPendingmediaadmin = "apimau12";
	String VideoPendingmediaviewer = "apimvu12";
    String VideoPendingmediacontributor = "apimcu12";
    String VideoPendingaccountadmin = "apiaau1";
	// *****&&&& END &&&& *************
	// com.vbrick.avenger.videocommentapi_AV_9879_testcases
	// ************************
    
    // Assigning user to a group
    String AssigninguserAccountAdmin = "apiaau61"; 
    String AssigninguserMediaAdmin = "apimau43";
    
    //DeleteVideoComment
    
    String DeleteCommentMediaViewer = "apimvu51"; 
    
    //UpdateVideoComment
    String UpdateCommentMediaViewer = "apimvu52"; 
    
    //Delete Transcription File
    String DeleteTranscriptionFile = "apimvu53";
    
  //GET Transcription File
    String GetTranscriptionFile = "apimvu54";
    
    //Search Suggestions API
    String SearchSuggestions = "apimvu55";
    
  //GET Video Thumbnail Image
    String GetVideoThumbnail = "apimvu56";
    
  //Start Event
    String StartWebCast_EventAdmin = "apieau41";
    String StartWebCast_EventHost = "apiehu22";
    String StartWebCast_MediaAdmin = "apimau41";
    String StartWebCast_MediaViewer = "apimvu40";
    
  //GET Assignable Categories
    String GetAssignableCategoriesMV = "apimvu57";
    String GetAssignableCategoriesMC = "apimcu57";
    
  //Create DME
    String CreateDME_MediaViewer = "apimvu41";
    
  //Delete DME
    String DeleteDME_MediaViewer = "apimvu42";
    
  //GET StartWebCast 
    String EndWebCast_EventAdmin = "apieau42";
    String EndWebCast_EventHost = "apiehu23";
    String EndWebCast_MediaAdmin = "apimau42";
    String EndWebCast_MediaViewer = "apimvu42";
    
  //Get Notifications
    String GetNotifications_MediaViewer = "apimvu44";
    String GetNotifications_MediaContributor = "apimcu58";
    String GetNotifications_EventHost = "apiehu24";
    String GetNotifications_EventAdmin = "apieau43";
    String GetNotifications_MediaAdmin = "apimau44";
    
    //PUT Notifications
    String PutNotifications_MediaViewer = "apimvu49";
    String PutNotifications_MediaContributor = "apimcu59";
    String PutNotifications_EventHost = "apiehu25";
    String PutNotifications_EventAdmin = "apieau44";
    String PutNotifications_MediaAdmin = "apimau45";
    
  //Get Subscriptions
    String GETSUBSCRIPTIONS_MediaViewer = "apimvu50"; // with NO Subscriptions
    
    //Delete Embedded content
    String DeleteEmbedded_Mediaviewer="apimvu20";
    
    //get embedded content
    String  Get_Mediaviewer="apimvu22";
}
