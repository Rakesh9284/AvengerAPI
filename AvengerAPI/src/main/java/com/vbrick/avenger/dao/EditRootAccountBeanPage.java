package com.vbrick.avenger.dao;

public class EditRootAccountBeanPage {
	private String firstname;
	private String lastname;
	private String contactemail;
	private String title;
	private String phonenumber;
	private String language;
	public String userconfirmationurl;
	public String getUserconfirmationurl() {
		return userconfirmationurl;
	}
	public void setUserconfirmationurl(String userconfirmationurl) {
		this.userconfirmationurl = userconfirmationurl;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getContactemail() {
		return contactemail;
	}
	public void setContactemail(String contactemail) {
		this.contactemail = contactemail;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

}
