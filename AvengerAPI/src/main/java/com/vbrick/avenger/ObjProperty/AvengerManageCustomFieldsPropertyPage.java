package com.vbrick.avenger.ObjProperty;

public enum AvengerManageCustomFieldsPropertyPage {

	managecustomfields_addfiled("//*[contains(@uisref,'portal.admin.system-settings.metadata.video-fields')]"),
	managecustomfields_nametextbox("//*[contains(@name,'name')]"),
	managecustomfields_widgetdropdownloactor("//*[@name='fieldType']"),
	managecustomfields_submitbutton("(//*[contains(@type,'submit')])[2]"),
	managecustomfields_widgetpicklistoptiontextbox("//*[contains(@name,'optionName')]"),
	managecustomfields_widgetoptionaddbutton("//*[contains(@class,'btn btn-primary')]/../..//*[@type='button']"),
	managecustomfields_customfieldbackbutton("//*[@class='panel-footer']/div/div/a");
	
	private String property;
	private AvengerManageCustomFieldsPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
}
