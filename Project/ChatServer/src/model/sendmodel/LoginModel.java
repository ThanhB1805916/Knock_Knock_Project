package model.sendmodel;

import java.io.Serializable;

import model.ValidModel;

public class LoginModel implements ValidModel, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;

	// ---------------------------------------------------------------------------
	// -------------------------------
	// ------------------------------- Constructors
	// -------------------------------
	// ---------------------------------------------------------------------------

	public LoginModel(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public boolean isValid() {
		boolean valid = false;
		try {
			valid = username.isEmpty() == false && password.isEmpty() == false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valid;
	}

	// ---------------------------------------------------------------------------
	// -------------------------------
	// ------------------------------- Setters - Getters
	// -------------------------------
	// ---------------------------------------------------------------------------

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
