package com.vbrick.avenger.apibeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class EditVideoCommentBean {
	public String title,description,enableComments,enableRatings,enableDownloads,uploader,isActive,
	 videoAccessControl,teamid,groupId,userId,is360,commentId2,userName,unlisted,customFieldValue,customFieldId,expirationAction,publishDate,expirationDate,whenUploaded,username,Usernames;

	public void username(HashMap<String, String> userName) {
		this.username = username;
	}
	public void setUsernames(String usernames) {
		Usernames = usernames;
}
}