package data_model;

import java.util.Date;
import java.util.HashMap;

//Class represent for table in database

public class RoomTable {
	// Attributes
	private int id;
	private String name;
	private Date datecreate;
	private String avatar;

	// ---------------------------------------------------------------------------
	// -------------------------------
	// ------------------------------- Constructors
	// -------------------------------
	// ---------------------------------------------------------------------------

	// Ctor get hashmap type parameters
	public RoomTable(HashMap<String, Object> parameters) {
		id = (int) parameters.get("id");
		name = (String) parameters.get("name");
		datecreate = (Date) parameters.get("datecreate");
		avatar = (String) parameters.get("avatar");
	}

	// Ctor full parameters
	public RoomTable(int id, String name, Date datecreate, String avatar) {
		this.id = id;
		this.name = name;
		this.datecreate = datecreate;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDatecreate() {
		return datecreate;
	}

	public void setDatecreate(Date datecreate) {
		this.datecreate = datecreate;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
