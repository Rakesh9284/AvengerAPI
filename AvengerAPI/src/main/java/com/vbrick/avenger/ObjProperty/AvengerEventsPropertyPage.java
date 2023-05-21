package com.vbrick.avenger.ObjProperty;


    public enum AvengerEventsPropertyPage {

	avengereventspage_monthvaluelocator("//*[@class='fc-header-title']"),
	avengereventspage_monthviewlocator("//*[contains(@title,'Month view')]"),
	avengereventspage_weekviewlocator("//*[contains(@title,'Week view')]"),
	avengereventspage_dayviewlocator("//*[contains(@title,'Day view')]"),
	avengereventspage_highlightedsystemdate("//*[contains(@class,'fc-state-highlight')]"),
	avengereventspage_errortext("//h1"),
	avengereventspage_locklocator("//*[@class='glyphicons lock']"), 
	avengereventspage_eventCount("//span[@title='All Users']"),
	avengereventspage_weekpagedaylocator("//*[contains(@class,'agenda-days')]//tr[contains(@class,'fc-first')]/th[2]"),
	avengereventspage_daypagehourslocator("//*[@class='fc-scroller-harness fc-scroller-harness-liquid']");
	
	private String property;
	private AvengerEventsPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
