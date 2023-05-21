package com.vbrick.avenger.ObjProperty;


    public enum AvengerApprovalProcessPropertyPage {
	
	avengerapprovalprocess_createapporvalprocessbuttonlocator("//*[contains(@href,'processes/new')]"),
	avengerapprovalprocess_approvalprocessnamelocator("//input[@name='approvalProcessName']"),
	avengerapprovalprocess_groupusersrequiringapprovallocator("(//*[contains(@name,'query')])[1]"),
	avengerapprovalprocess_createapprovalprocesssubmitbuttonlocator("(//button[@type='submit'])[last()]"),
	avengerapprovalprocess_approverslocator("(//*[contains(@name,'query')])[2]"),
	approvalProcessesSearchBox("//*[contains(@ng-model,'searchText')]"),
	approvalProcessIcon("//span[@class='glyphicons cogwheels circle']"), 
	aprrovalProcessDeleteButton("//button[contains(@ng-click,'deleteApprovalProcessTemplate(process)')]"),
	deletepopuplocator("//button[contains(@ng-click,'ok()')]"),
	deletepopupNolocator("//button[contains(@class,'btn btn-cancel')]"), 
	deleteMessageLocator("//*[@class='modal-header ng-scope']/h4"),
	deleteVideoListMessageLocator("//*[@class='modal-body']/p"),
	deleteVideoListLocator("//*[@class='file-name ng-binding']"),
	approversstepnameLocator("approverStepName");
	
	
	private String property;
	
	private AvengerApprovalProcessPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
