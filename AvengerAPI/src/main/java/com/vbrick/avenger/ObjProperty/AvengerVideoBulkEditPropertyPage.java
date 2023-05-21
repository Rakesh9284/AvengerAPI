package com.vbrick.avenger.ObjProperty;

public enum AvengerVideoBulkEditPropertyPage {

	avengervideobulkeditpage_settingsoptionlocator("//*[contains(@ui-sref,'.bulk-edit')]/span"),
	avengervideobulkeditpage_statusdropdownlocator("//*[contains(@ng-model,'fields.isActive.value')]"),
	avengervideobulkeditpage_savebuttonlocator("//*[contains(@class,'section-header')]//button[2]"),
	avengervideobulkeditpage_videosettingsdialogsavebuttonlocator("//*[contains(@class,'modal-footer')]//*[contains(@type,'submit')]"),
	avengervideobulkeditpage_videosettingsdailogokbuttonlocator("//*[contains(@class,'modal-footer')]//button[contains(text(),'OK')]"),
	avengervideobulkeditpage_publishdropdownlocator("publishOp"),
	avengervideobulkeditpage_publishdatetextboxlocator("publishDate"),
	avengervideobulkeditpage_expirationdropdownlocator("expirationOp"),
	avengervideobulkeditpage_expirationdatetextboxlocator("expirationDate"),
	avengervideobulkeditpage_deleteoptionlocator("//*[contains(@aria-label,'Delete Videos')]"),
	avengervideobulkeditpage_accesscontroldropdownlocator("//*[contains(@ng-model,'fields.accessControl.value')]"),
	avengervideobulkeditpage_accesscontrollistdropdownlocator("//*[contains(@ng-model,'fields.accessControlEntities.operation')]"),
	avengervideobulkeditpage_downloaddropdownlocator("//*[contains(@ng-model,'fields.downloadingEnabled.value')]"),
	avengervideobulkeditpage_commentsdropdownlocator("//*[contains(@ng-model,'fields.commentsEnabled.value')]"),
	avengervideobulkeditpage_tagsdropdownlocator("//*[contains(@ng-model,'fields.tags.operation')]"),
	avengervideobulkeditpage_ratingsdropdownlocator("//*[contains(@ng-model,'fields.ratingsEnabled.value')]"),
	avengervideobulkeditpage_categoriesdropdownlocator("categoriesOp"),
	avengervideobulkeditpage_selectallcheckboxlocator("//*[contains(@class,'theme-accent-bg theme-accent-txt P1Jzi__button')]"),
	avengervideobulkeditpage_backbuttonlocator("//*[contains(@ng-show,'backLink')]/a/span"),
	avengervideobulkeditpage_cancelbuttonlocator("//*[contains(@class,'section-header')]//button[1]");
	
	private String property;
	private AvengerVideoBulkEditPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
	
}
