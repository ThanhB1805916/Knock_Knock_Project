package model;

import java.io.Serializable;
import java.util.Date;

public class Person implements IValidModel, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String username;
	private String password;
	private String name;
	private boolean male;
	private String phonenumber;
	private Date dateofbirth;

	private FileInfo avatar;

	// ---------------------------------------------------------------------------
	// -------------------------------
	// ------------------------------- Constructors
	// -------------------------------
	// ---------------------------------------------------------------------------

	public Person(int id, String username, String password, String name, boolean male, String phonenumber,
			Date dateofbirth, FileInfo avatar) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.male = male;
		this.phonenumber = phonenumber;
		this.dateofbirth = dateofbirth;
		this.avatar = avatar;
	}

	public Person(Person model) {
		this.id = model.id;
		this.username = model.username;
		this.password = model.password;
		this.name = model.name;
		this.male = model.male;
		this.phonenumber = model.phonenumber;
		this.dateofbirth = model.dateofbirth;
		this.avatar = model.avatar;
	}

	@Override
	public boolean isValid() {
		return username.isEmpty() == false && password.isEmpty() == false && name.isEmpty() == false
				&& phonenumber.isEmpty() == false;
	}

	// ---------------------------------------------------------------------------
	// -------------------------------
	// ------------------------------- Setters - Getters
	// -------------------------------
	// ---------------------------------------------------------------------------

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getMale() {
		return male;
	}

	public void setMale(boolean male) {
		this.male = male;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public Date getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public FileInfo getAvatar() {
		return avatar;
	}

	public void setAvatar(FileInfo avatar) {
		this.avatar = avatar;
	}

}
