package com.vbricks.avenger.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang.RandomStringUtils;

import com.vbrick.avenger.apibeans.AddUploadVideoBean;

 public interface IAddVideoLinkService {
 
	 String title="TestData";
	 String description="testDescription";
	 String enableCommentsAsFalse="false";
	 String enableCommentsAsTrue="true";
	 String enableRatingsAsFalse="false";
	 String enableRatingsAsTrue="true";
	 String enableDownloadsAsFalse="false";
	 String enableDownloadsAsTrue="true";
	 String IsActiveAsFalse="false";
	 String IsActiveAsTrue="true";
	 String IsUnlistedFalse="false";
	 String Is360False="false";
	 String typeVod="Vod";
	 String typeLive="Live";
	 String linkedUrl="linkedUrl";
	 String url="url";
	 String encodingtype="encodingType";
	 String type="type";
	 String isMulticast="isMulticast";
	 
	 
	
	 String  tags[]={"video", "upload", "api"}; 
	 String  tagsforedit[]=null; 
	 String  categories[]=null;
     String  categoryIds[]=null;
     String videoAccessControl[]={"Public","Private","AllUsers","Channels","All Users","AllInternalUsersACLToggle"};
	 String accessControlEntitiesforUser[]=null;           //It Depends on URL
	 String accessControlEntitiesforGroup[]=null;     //It Depends on URL
	 String accessControlEntitiesforTeam[]=null;
	 String accessControlEntities[]=null;
     String encodingType[]= {"H264", "HLS"," HDS", "H264TS", "MPEG4", "MPEG2", "Flash", "RTP" };
     
	 
	 String ConstantvideoIdvalue="d92ed58a-4ef7-490f-b29c-3388044fb0b67868";
	 String ConstantvideoIdVar="videoId";
     
     String invalidUploader="TestDemoabc";
     String blankbUploader="";
     String[] blankbtags={};
     
     String VideoTitle="APIVideoLinkTitle_"+RandomStringUtils.randomAlphabetic(5);
     String updateAPIUpdateVideoDesc="APIUpdateVideoDesc_"+RandomStringUtils.randomAlphabetic(5);
     String xpathvariableNameAllUsers="All Users";
     String INVALIDACCESSTOKEN="fgdfgdfgdfgfdg";
     
     
     
     
     String ENGLISH="English";
    
}
