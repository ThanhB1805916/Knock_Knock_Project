package data_model;

import java.util.Date;
import java.util.HashMap;

//Class represent for table in database

public class PersonTable {
	// Attributes
	private int id;
	private String username;
	private String password;
	private String name;
	private boolean gender;
	private String phonenumber;
	private Date dateofbirth;
	private String avatar;
	// ---------------------------------------------------------------------------
	// -------------------------------
	// ------------------------------- Constructors
	// -------------------------------
	// ---------------------------------------------------------------------------

	// Ctor get hashmap type parameters
	public PersonTable(HashMap<String, Object> parameters) {
		id = (int) parameters.get("id");
		setUsername((String) parameters.get("username"));
		setPassword((String) parameters.get("password"));
		setName((String) parameters.get("name"));
		gender = (boolean) parameters.get("gender");
		phonenumber = (String) parameters.get("phonenumber");
		dateofbirth = (Date) parameters.get("dateofbirth");
		avatar = (String) parameters.get("avatar");
	}

	// Ctor full parameters
	public PersonTable(int id, String username, String password, String name, boolean gender, String phonenumber,
			Date dateofbirth, String avatar) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.phonenumber = phonenumber;
		this.dateofbirth = dateofbirth;
		this.avatar = avatar;
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

	public boolean getGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
