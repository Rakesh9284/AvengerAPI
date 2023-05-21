package com.vbrick.avenger.ObjProperty;

    public enum AvengerEventInviteURLGuestUserLoginPropertyPage {

	avengereventinviteurlguestuserloginpage_guestusersubmit("//*[@type='submit']"),
	avengereventinviteurlguestuserloginpage_guestusersignin("//*[contains(@class,'registered')]/a"),
	avengereventinviteurlguestuserloginpage_eventtitleonlogin("//h1"),
	avengereventinviteurlguestuserloginpage_registeredtext("//*[contains(@class,'registered')]"),
	avengereventinviteurlguestuserloginpage_SinginLink("//*[contains(text(),'Sign In')]"),
	avengereventinviteurlguestuserloginpage_licensedSinginLink("//*[contains(text(),'Sign In')]");
	
	private String property;
	private AvengerEventInviteURLGuestUserLoginPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
}
