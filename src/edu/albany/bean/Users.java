/**
 * 
 */
package edu.albany.bean;

/**
 * @author Yu Zhang
 *
 */
public class Users {

	private int userId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private int accountType;

	/**
	 * 
	 */
	public Users() {
		super();
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the accountType
	 */
	public int getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType
	 *            the accountType to set
	 */
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

}
