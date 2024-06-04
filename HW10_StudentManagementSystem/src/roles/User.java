package roles;

//abstract class
public abstract class User {
	private String ID;
	private String name;
	private String username;
	private String password;
	
	/**
     * providing user with the provided information.
     *
     * @param ID      The id of the user.
     * @param name    The name of the user.
     * @param username  The username.
     * @param password The password.
     */
	public User(String ID, String name, String username, String password) {
		this.ID = ID;
		this.name = name;
		this.username = username;
		this.password = password;
	}
	
	  /**
     * Gets id of the user.
     * @return The user's id.
     */
	public String getID() {
		return this.ID;
	}
	
	/**
    * Gets the name of the user.
    *
    * @return name of the user.
    */
	public String getName() {
		return this.name;
	}
	
	  /**
     * Gets the username 
     *
     * @return The username.
     */
	public String getUsername() {
		return this.username;
	}
	 /**
     * Gets the password
     *
     * @return The password.
     */
	public String getPassword() {
		return this.password;
	}
}
