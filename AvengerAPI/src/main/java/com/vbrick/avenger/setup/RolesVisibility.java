package com.vbrick.avenger.setup;

import java.util.HashMap;
import java.util.Map;

public class RolesVisibility {
	
	 private  final int AccountAdmin =1 ,MediaAdmin =2 ,MediaContributor=3,MediaViewer=4,EventAdmin=5,MediaApprover=6;
		
	
	public Map<String, Boolean> dynamicRolesLocatorLoading(int role)
	{
		Map< String,Boolean> rolemap =new HashMap<String, Boolean>();
		switch(role)
		{
		case AccountAdmin:
			rolemap.put("Settingslink", true);
			rolemap.put("mediasettings", true);
			rolemap.put("categorieslinklocator", true);
			rolemap.put("createcategorybuttonlocator", true);
			rolemap.put("librarieslinklocator", true);
			rolemap.put("librarycreatelibrarybuttonlocator", true);
			rolemap.put("librarynewlibrarylocator", true);
			rolemap.put("librarycreatebuttonlocator", true);
			rolemap.put("addaccountbuttonlocator", true);
			rolemap.put("roleslink", true);
			rolemap.put("deviceslinklocator", true);
			rolemap.put("AddaDevicebuttonlocator", true);
			rolemap.put("AddaDmelinklocator", true);
			rolemap.put("transcodinglinklocator", true);
			rolemap.put("addpresetbuttonlocator", true);
			rolemap.put("userslink", true);
			rolemap.put("adduserlink", true);
			rolemap.put("systemsettingslink", true);
			rolemap.put("securitylink", true);
			rolemap.put("maximumlogonattempts", true);
			rolemap.put("AddaVbricklinklocator", true);
			rolemap.put("zoneslocator",true);
			rolemap.put("groupslink",true);
			rolemap.put("creategrouplocator",true);
			rolemap.put("deletegrouplocator",true);
			rolemap.put("addzonelocator",true);
			rolemap.put("uploadingfileaddbuttonlocator",true);
			rolemap.put("settingsbuttonlocator",true);
			rolemap.put("addvideoeditbuttonlocator",true);
			rolemap.put("addcustomdevicelocator",true);
			rolemap.put("Editsettingsbuttonlocator",true);
			rolemap.put("addtoplaylistlocator",true);
			rolemap.put("allplaylistlcoator",true);
			rolemap.put("settingslinklocator",true);
			rolemap.put("deletebuttonlocator",true);
			rolemap.put("playersettingslinklocator",true);
			rolemap.put("recordinglocator",true);
			rolemap.put("brandinglink",true);
			rolemap.put("accountstablocator",true);
			rolemap.put("reportsLinklocator",true);
			rolemap.put("presentationprofiletab",true);
			rolemap.put("AddaPresentationprofilelocator",true);
			rolemap.put("deletepresentationbuttonlocator",true);
			rolemap.put("systemsettingslink",true);
			rolemap.put("apikeyslink",true);
			rolemap.put("environmentlink",true);
			rolemap.put("featureslinklocator",true);
			rolemap.put("eventdeletebutton",true);
			rolemap.put("eventeditbutton",true);
			rolemap.put("userdropdown",true);
			rolemap.put("accountstab",true);
			rolemap.put("approvalprocesses",true);
			
	
			break;
		case MediaAdmin:
			rolemap.put("Settingslink", true);
			rolemap.put("mediasettings", true);
			rolemap.put("categorieslinklocator", true);
			rolemap.put("createcategorybuttonlocator", true);
			rolemap.put("librarieslinklocator", true);
			rolemap.put("librarycreatelibrarybuttonlocator", true);
			rolemap.put("librarynewlibrarylocator", true);
			rolemap.put("librarycreatebuttonlocator", true);
			rolemap.put("addaccountbuttonlocator", false);
			rolemap.put("roleslink", false);
			rolemap.put("deviceslinklocator",false);
			rolemap.put("AddaDevicebuttonlocator", false);
			rolemap.put("AddaDmelinklocator", false);
			rolemap.put("transcodinglinklocator", true);
			rolemap.put("addpresetbuttonlocator", true);
			rolemap.put("userslink", true);
			rolemap.put("adduserlink", false);
			rolemap.put("systemsettingslink", false);
			rolemap.put("securitylink", false);
			rolemap.put("maximumlogonattempts", false);
			rolemap.put("AddaVbricklinklocator",false);
			rolemap.put("zoneslocator",false);
			rolemap.put("groupslink",false);
			rolemap.put("creategrouplocator",false);
			rolemap.put("deletegrouplocator",false);
			rolemap.put("addzonelocator",false);
			rolemap.put("uploadingfileaddbuttonlocator",true);
			rolemap.put("settingsbuttonlocator",true);
			rolemap.put("addvideoeditbuttonlocator",true);
			rolemap.put("addcustomdevicelocator",false);
			rolemap.put("Editsettingsbuttonlocator",true);
			rolemap.put("addtoplaylistlocator",true);
			rolemap.put("allplaylistlcoator",true);
			rolemap.put("settingslinklocator",true);
			rolemap.put("deletebuttonlocator",true);
			rolemap.put("playersettingslinklocator",true);
			rolemap.put("recordinglocator",true);
			rolemap.put("brandinglink",false);
			rolemap.put("accountstablocator",false);
			rolemap.put("reportsLinklocator",false);
			rolemap.put("presentationprofiletab",false);
			rolemap.put("AddaPresentationprofilelocator",false);
			rolemap.put("deletepresentationbuttonlocator",false);
			rolemap.put("systemsettingslink",false);
			rolemap.put("apikeyslink",false);
			rolemap.put("environmentlink",false);
			rolemap.put("featureslinklocator",true);
			rolemap.put("eventdeletebutton",true);
			rolemap.put("eventeditbutton",true);
			rolemap.put("userdropdown",false);
			rolemap.put("accountstab",false);
			rolemap.put("approvalprocesses",true);
				
																									
			break;
		case MediaContributor:
			rolemap.put("Settingslink", false);//AvengerHomePage
			rolemap.put("mediasettings", false);
			rolemap.put("categorieslinklocator", false);
			rolemap.put("createcategorybuttonlocator", false);
			rolemap.put("librarieslinklocator", false);
			rolemap.put("librarycreatelibrarybuttonlocator", false);
			rolemap.put("librarynewlibrarylocator", false);
			rolemap.put("librarycreatebuttonlocator", false);
			rolemap.put("addaccountbuttonlocator", false);
			rolemap.put("roleslink", false);
			rolemap.put("deviceslinklocator",false);
			rolemap.put("AddaDevicebuttonlocator", false);
			rolemap.put("AddaDmelinklocator", false);
			rolemap.put("transcodinglinklocator", false);
			rolemap.put("addpresetbuttonlocator", false);
			rolemap.put("userslink", false);
			rolemap.put("adduserlink", false);
			rolemap.put("systemsettingslink", false);
			rolemap.put("securitylink", false);
			rolemap.put("maximumlogonattempts", false);
			rolemap.put("AddaVbricklinklocator",false);
			rolemap.put("zoneslocator",false);
			rolemap.put("groupslink",false);					
			rolemap.put("creategrouplocator",false);
			rolemap.put("addzonelocator",false);
			rolemap.put("uploadingfileaddbuttonlocator",true);
			rolemap.put("settingsbuttonlocator",true);
			rolemap.put("addvideoeditbuttonlocator",true);
			rolemap.put("addcustomdevicelocator",false);
			rolemap.put("Editsettingsbuttonlocator",false);
			rolemap.put("addtoplaylistlocator",true);
			rolemap.put("allplaylistlcoator",false);
			rolemap.put("settingslinklocator",false);
			rolemap.put("deletebuttonlocator",false);
			rolemap.put("playersettingslinklocator",false);
			rolemap.put("recordinglocator",false);
			rolemap.put("brandinglink",false);
			rolemap.put("accountstablocator",false);
			rolemap.put("reportsLinklocator",false);
			rolemap.put("presentationprofiletab",false);
			rolemap.put("AddaPresentationprofilelocator",false);
			rolemap.put("deletepresentationbuttonlocator",false);					
			rolemap.put("systemsettingslink",false);
			rolemap.put("apikeyslink",false);
			rolemap.put("environmentlink",false);
			rolemap.put("featureslinklocator",false);
			rolemap.put("deletegrouplocator",false);
			rolemap.put("eventdeletebutton",false);
			rolemap.put("eventeditbutton",false);
			rolemap.put("userdropdown",false);
			rolemap.put("accountstab",false);
			rolemap.put("approvalprocesses",false);
						
			
			break;
		case MediaViewer:
			rolemap.put("Settingslink", false);
			rolemap.put("mediasettings", false);
			rolemap.put("categorieslinklocator", false);
			rolemap.put("createcategorybuttonlocator", false);
			rolemap.put("librarieslinklocator", false);
			rolemap.put("librarycreatelibrarybuttonlocator", false);
			rolemap.put("librarynewlibrarylocator", false);
			rolemap.put("librarycreatebuttonlocator", false);
			rolemap.put("addaccountbuttonlocator", false);
			rolemap.put("roleslink", false);
			rolemap.put("deviceslinklocator",false);
			rolemap.put("AddaDevicebuttonlocator", false);
			rolemap.put("AddaDmelinklocator", false);
			rolemap.put("transcodinglinklocator", false);
			rolemap.put("addpresetbuttonlocator", false);
			rolemap.put("userslink", false);
			rolemap.put("adduserlink", false);
			rolemap.put("systemsettingslink", false);
			rolemap.put("securitylink", false);
			rolemap.put("maximumlogonattempts", false);
			rolemap.put("AddaVbricklinklocator",false);
			rolemap.put("zoneslocator",false);
			rolemap.put("groupslink",false);
			rolemap.put("creategrouplocator",false);
			rolemap.put("addzonelocator",false);
			rolemap.put("uploadingfileaddbuttonlocator",false);
			rolemap.put("settingsbuttonlocator",false);
			rolemap.put("addvideoeditbuttonlocator",false);
			rolemap.put("addcustomdevicelocator",false);
			rolemap.put("Editsettingsbuttonlocator",false);
			rolemap.put("addtoplaylistlocator",false);
			rolemap.put("allplaylistlcoator",false);
			rolemap.put("settingslinklocator",false);
			rolemap.put("deletebuttonlocator",false);
			rolemap.put("playersettingslinklocator",false);
			rolemap.put("recordinglocator",false);
			rolemap.put("brandinglink",false);
			rolemap.put("accountstablocator",false);
			rolemap.put("reportsLinklocator",false);
			rolemap.put("presentationprofiletab",false);
			rolemap.put("AddaPresentationprofilelocator",false);
			rolemap.put("deletepresentationbuttonlocator",false);	
			rolemap.put("systemsettingslink",false);
			rolemap.put("apikeyslink",false);
			rolemap.put("environmentlink",false);
			rolemap.put("featureslinklocator",false);
			rolemap.put("deletegrouplocator",false);
			rolemap.put("eventdeletebutton",false);
			rolemap.put("eventeditbutton",false);
			rolemap.put("userdropdown",false);
			rolemap.put("accountstab",false);
			rolemap.put("approvalprocesses",false);
				
			
			break;
		case EventAdmin:
			rolemap.put("Settingslink", false);
			rolemap.put("mediasettings", false);
			rolemap.put("categorieslinklocator", false);
			rolemap.put("createcategorybuttonlocator", false);
			rolemap.put("librarieslinklocator", false);
			rolemap.put("librarycreatelibrarybuttonlocator", false);
			rolemap.put("librarynewlibrarylocator", false);
			rolemap.put("librarycreatebuttonlocator", false);
			rolemap.put("addaccountbuttonlocator", false);
			rolemap.put("roleslink", false);
			rolemap.put("deviceslinklocator",false);
			rolemap.put("AddaDevicebuttonlocator", false);
			rolemap.put("AddaDmelinklocator", false);
			rolemap.put("transcodinglinklocator", false);
			rolemap.put("addpresetbuttonlocator", false);
			rolemap.put("userslink", false);
			rolemap.put("adduserlink", false);
			rolemap.put("systemsettingslink", false);
			rolemap.put("securitylink", false);
			rolemap.put("maximumlogonattempts", false);
			rolemap.put("AddaVbricklinklocator",false);
			rolemap.put("zoneslocator",false);
			rolemap.put("groupslink",false);	
			rolemap.put("creategrouplocator",false);
			rolemap.put("addzonelocator",false);
			rolemap.put("uploadingfileaddbuttonlocator",false);
			rolemap.put("settingsbuttonlocator",false);
			rolemap.put("addvideoeditbuttonlocator",false);
			rolemap.put("addcustomdevicelocator",false);
			rolemap.put("Editsettingsbuttonlocator",false);
			rolemap.put("addtoplaylistlocator",false);
			rolemap.put("allplaylistlcoator",false);
			rolemap.put("settingslinklocator",false);
			rolemap.put("deletebuttonlocator",false);
			rolemap.put("playersettingslinklocator",false);
			rolemap.put("recordinglocator",false);
			rolemap.put("brandinglink",false);
			rolemap.put("accountstablocator",false);
			rolemap.put("reportsLinklocator",false);
			rolemap.put("presentationprofiletab",false);
			rolemap.put("AddaPresentationprofilelocator",false);
			rolemap.put("deletepresentationbuttonlocator",false);	
			rolemap.put("systemsettingslink",false);
			rolemap.put("apikeyslink",false);
			rolemap.put("environmentlink",false);
			rolemap.put("featureslinklocator",false);
			rolemap.put("deletegrouplocator",false);
			rolemap.put("eventdeletebutton",true);
			rolemap.put("eventeditbutton",true);
			rolemap.put("userdropdown",false);
			rolemap.put("accountstab",false);
			rolemap.put("approvalprocesses",false);
			
			
			
			break;
		case MediaApprover:
			rolemap.put("Settingslink", false);
			rolemap.put("mediasettings", false);
			rolemap.put("categorieslinklocator", false);
			rolemap.put("createcategorybuttonlocator", false);
			rolemap.put("librarieslinklocator", false);
			rolemap.put("librarycreatelibrarybuttonlocator", false);
			rolemap.put("librarynewlibrarylocator", false);
			rolemap.put("librarycreatebuttonlocator", false);
			rolemap.put("addaccountbuttonlocator", false);
			rolemap.put("roleslink", false);
			rolemap.put("deviceslinklocator",false);
			rolemap.put("AddaDevicebuttonlocator", false);
			rolemap.put("AddaDmelinklocator", false);
			rolemap.put("transcodinglinklocator", false);
			rolemap.put("addpresetbuttonlocator", false);
			rolemap.put("userslink", false);
			rolemap.put("adduserlink", false);
			rolemap.put("systemsettingslink", false);
			rolemap.put("securitylink", false);
			rolemap.put("maximumlogonattempts", false);
			rolemap.put("AddaVbricklinklocator",false);
			rolemap.put("zoneslocator",false);
			rolemap.put("groupslink",false);
			rolemap.put("creategrouplocator",false);
			rolemap.put("addzonelocator",false);
			rolemap.put("uploadingfileaddbuttonlocator",false);
			rolemap.put("settingsbuttonlocator",false);
			rolemap.put("addvideoeditbuttonlocator",false);
			rolemap.put("addcustomdevicelocator",false);
			rolemap.put("addtoplaylistlocator",false);
			rolemap.put("allplaylistlcoator",false);
			rolemap.put("settingslinklocator",false);
			rolemap.put("deletebuttonlocator",false);
			rolemap.put("playersettingslinklocator",false);
			rolemap.put("recordinglocator",false);
			rolemap.put("brandinglink",false);
			rolemap.put("accountstablocator",false);
			rolemap.put("reportsLinklocator",false);
			rolemap.put("presentationprofiletab",false);
			rolemap.put("AddaPresentationprofilelocator",false);
			rolemap.put("deletepresentationbuttonlocator",false);
			rolemap.put("systemsettingslink",false);
			rolemap.put("apikeyslink",false);
			rolemap.put("environmentlink",false);
			rolemap.put("featureslinklocator",false);
			rolemap.put("deletegrouplocator",false);
			rolemap.put("userdropdown",false);
			rolemap.put("accountstab",false);
			rolemap.put("approvalprocesses",false);
			
			break;
			
			
			
		}	
		return rolemap;
	}
	

}
