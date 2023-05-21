package com.vbrick.avenger.dao;

public class UserPasswordParametersBean {
	private String minimumnoofcharacters;
    private String invalidloginattemptsallowed;
    private String sessioninactivitytimeout;
    private String sessioninactivitytimeoutdefault;
    private String lockoutTimePeriod;
    private String invalidattemptperiodminutes;
    private String userAgreementMessage;
	public String getUserAgreementMessage() {
		return userAgreementMessage;
	}

	public void setUserAgreementMessage(String userAgreementMessage) {
		this.userAgreementMessage = userAgreementMessage;
	}

	public String getInvalidloginattemptsallowed() {
		return invalidloginattemptsallowed;
	}

	public String getInvalidattemptperiodminutes() {
		return invalidattemptperiodminutes;
	}

	public void setInvalidattemptperiodminutes(String invalidattemptperiodminutes) {
		this.invalidattemptperiodminutes = invalidattemptperiodminutes;
	}

	public void setInvalidloginattemptsallowed(String invalidloginattemptsallowed) {
		this.invalidloginattemptsallowed = invalidloginattemptsallowed;
	}

	public String getSessioninactivitytimeout() {
		return sessioninactivitytimeout;
	}

	public void setSessioninactivitytimeout(String sessioninactivitytimeout) {
		this.sessioninactivitytimeout = sessioninactivitytimeout;
	}

	public String getMinimumnoofcharacters() {
		return minimumnoofcharacters;
	}

	public void setMinimumnoofcharacters(String minimumnoofcharacters) {
		this.minimumnoofcharacters = minimumnoofcharacters;
	}

	public String getSessioninactivitytimeoutdefault() {
		return sessioninactivitytimeoutdefault;
	}

	public void setSessioninactivitytimeoutdefault(String sessioninactivitytimeoutdefault) {
		this.sessioninactivitytimeoutdefault = sessioninactivitytimeoutdefault;
	}

	public String getLockoutTimePeriod() {
		return lockoutTimePeriod;
	}

	public void setLockoutTimePeriod(String lockoutTimePeriod) {
		this.lockoutTimePeriod = lockoutTimePeriod;
	}
}
