package com.vbrick.avenger.dao;

       public class AddExistingUserBean {
		private String firstname;
		private String lastname;
		private String username;
		private String password;
		private String userrole;
		private String userdefaultrole;
		private String domainName;

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

		public String getUserrole() {
			return userrole;
		}

		public void setUserrole(String userrole) {
			this.userrole = userrole;
		}

		public String getUserdefaultrole() {
			return userdefaultrole;
		}

		public void setUserdefaultrole(String userdefaultrole) {
			this.userdefaultrole = userdefaultrole;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getDomainName() {
			return domainName;
		}

		public void setDomainName(String domainName) {
			this.domainName = domainName;
		}
}