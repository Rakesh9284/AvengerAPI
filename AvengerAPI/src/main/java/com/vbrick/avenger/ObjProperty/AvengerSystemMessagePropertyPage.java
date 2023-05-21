package com.vbrick.avenger.ObjProperty;

public enum AvengerSystemMessagePropertyPage {

	systemMessagepage_startTime("(//*[@name='timeInput'])[1]"),
	systemMessagepage_endDtime("(//*[@name='timeInput'])[2]"),
	systemMessagepage_startDate("(//*[contains(@ng-model,'dateField.date')])[1]"),
	systemMessagepage_endDate("(//*[contains(@ng-model,'dateField.date')])[2]"),
	systemMessagepage_englishTextArealocator("(//*[contains(@ng-model,'systemMessage.messages[language.code]')])[1]"),
	systemMessagepage_saveButton("//*[contains(@class,'panel-footer')]//div/button"),
	systemMessage_alertOkbuttonLocator("//*[contains(@ng-click,'close()')]");
	private String property;
	
	
	private AvengerSystemMessagePropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
}
