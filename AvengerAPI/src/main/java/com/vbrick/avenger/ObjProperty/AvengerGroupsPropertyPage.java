package com.vbrick.avenger.ObjProperty;

public enum AvengerGroupsPropertyPage {

		avengergrouppage_createbuttonlocator("(//*[@type='submit'])[last()]"),
		avengergrouppage_newgroupname("//*[@id='rootGroupName']"),
		avengergrouppage_creategrouplocator("//*[contains(@ui-sref,'groups.create')]"),
		avengergrouppage_creategrouperrortext("//*[contains(@ng-show,'groupNameInUse')][not(contains(@class,'hide'))]"),
		avengergrouppage_customizebuttonlocator("//*[contains(text(),'Customize')]"),
		avengergrouppage_cancelbuttonlocator("//*[contains(text(),'Cancel')]"),
		avengergrouppage_importgroupfromldaplocator("//*[@ng-show='devices']/a"),
		avengergrouppage_expandallbuttonlocator("//*[@ng-click='expandAll()']"),
		avengergrouppage_collapseallbuttonlocator("//*[@ng-click='collapseAll()']"),
		avengergrouppage_groupssearchlocator("//*[@name='grpSearch']"),
		avengergrouppage_allgroupslocator("//a[@ui-sref='portal.admin.user-access.groups.edit({groupId: group.id})']");
		
		private String property;
		private AvengerGroupsPropertyPage(String property) {
			this.setProperty(property);
		}

		public String getProperty() {
			return property;
		}

		public void setProperty(String property) {
			this.property = property;
		}


	}
	

	
	

