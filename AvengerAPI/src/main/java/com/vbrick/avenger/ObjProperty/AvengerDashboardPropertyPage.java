package com.vbrick.avenger.ObjProperty;

    public enum AvengerDashboardPropertyPage {
 
	avengerdashboardpage_eventstablocator("//*[contains(@uisref,'scheduledEvents')]"),
	Avengerdashboardpage_mediatablocator("//*[contains(@aria-label,'Media-Menu')]"),
	Avengerdashboardpage_mediatablocator1("//*[@class='dropdown-toggle']"),
	avengerdashboardpage_eventrightsliderlocator("(//*[@ng-click='next()'])[3]"),
	avengerdashboardpage_totaleventslocator("//*[contains(@class,'event-tile alert')]/a/h4"),
	avengerdashboardpage_eventleftliderlocator("(//*[contains(@class,'left carousel')])[4]"),	
 	avengerdashboardpage_eventpageclanderlocator("//*[contains(@class,'fc-header')]"),
	avengerdashboardpage_feaurecarousellocator("//*[contains(@ng-repeat,'featuredVideo')]/.."),
	avengerdashboardpage_statusfilterbutton("//h5[contains(text(),'Status')]/../span/button"),
	avengerdashboardpage_statusfilterdropdown("//*[contains(@name,'status')]"),
	avengerdashboardpage_filterbuttonlocator("//*[@class='glyphicons filter']/.."),
	avengerdashboardpage_filtersdropdowns("//*[@name='form']"),
	avengerdashboardpage_listviewvideolocator("//*[contains(@class,'list-wrap')]/div[2]/div[5]"),
	avengerdashboardpage_datefilterbutton("//h5[contains(text(),'Upload Date')]/../span/button"),
	avengerdashboardpage_datefilterdropdowns("//*[contains(@name,'dateInput')]"),
	avengerdashboardpage_fromdatepicker("(//*[contains(@class,'glyphicons calendar')])[1]/.."),
	avengerdashboardpage_todatepicker("(//*[contains(@name,'dateInput')])[2]"),
	avengerdashboardpage_fromdatecalender("(//*[contains(@aria-label,'Select a date')])[1]"),
	avengerdashboardpage_fromdatetextboxlocator("(//date-picker-styles/../div/input)[1]"),
	avengerdashboardpage_fromdatetextbox("(//*[contains(@name,'dateInput')])[1]"),
	avengerdashboardpage_firstvideolocator("//*[contains(@class,'file-list-wrap')]/div[2]/div/a"),
	avengerdashboardpage_firstvideotileviewlocator("//*[contains(@class,'row tile-grid ng-scope')]/div[1]/div/div[2]/div/h2/a"), 
	avengerdashboardpage_todatetextboxlocator("(//date-picker-styles/../div/input)[2]");
	
	private String property;
	private AvengerDashboardPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
