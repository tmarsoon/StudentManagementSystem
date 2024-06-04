package roles;

public class Admin {

		private String adminId;
		private String adminName;
		private String adminUsername;
		private String adminPassword;
	
		 /**
	     * Constructing an admin with the provided information.
	     *
	     * @param adminID       id  of the admin.
	     * @param adminName     The name of the admin.
	     * @param adminUsername The username 
	     * @param adminPassword The password 
	     */
		
		
		public Admin(String adminID, String adminName, String adminUsername, String adminPassword) {
			this.adminId = adminID;
			this.adminName = adminName;
			this.adminUsername = adminUsername;
			this.adminPassword = adminPassword;
			
		}
		
		 /**
	     * Gets the id of the admin.
	     *
	     * @return The admin's id.
	     */
		public String getAdminId() {
			return adminId;
		}
		
		  /**
	     * admin name. 
	     *
	     * @return The name of the admin.
	     */
		public String getAdminName() {
			return adminName;
		}
		 	
		/**
	     * Gets the username.
	     * @return The username.
	     */
	public String getAdminUsername() {
		return adminUsername;
	}
	 
	/**
     * Gets the password.
     
     * @return The password.
     */
	
	public String getAdminPassword() {
		return adminPassword;
	}
}
