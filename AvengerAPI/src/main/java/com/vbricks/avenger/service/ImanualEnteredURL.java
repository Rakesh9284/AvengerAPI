package com.vbricks.avenger.service;

import java.util.HashMap;

import com.vbrick.avenger.apibeans.AddUploadVideoBean;

public interface ImanualEnteredURL {

	
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
	
	 String  tags[]={"video", "upload"}; 
	 String  categoryIds[]=null;
	 String videoAccessControl[]={"Public","Private","AllUsers"};
	  HashMap<String,String> uploadVideos(HashMap<String,String> loginapiresponse,String filepath,AddUploadVideoBean adduploadvideobean );

	 
	 String ConstantvideoIdvalue="d92ed58a-4ef7-490f-b29c-3388044fb0b67868";
	 String ConstantvideoIdVar="videoId";
     
     String invalidUploader="TestDemoabc";
     String blankbUploader="";
     String[] blankbtags={};
     
     String LinkedUrl_Url="http://google.co.in/";
     String EncodingType="H264";
     String LinkedUrl_Type="Live";
     String LinkedUrl_IsMulticast="True";
}
