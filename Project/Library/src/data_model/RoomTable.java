package data_model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

//Class represent for table in database

public class RoomTable {
	// Attributes
	private int id;
	private String name;
	private LocalDateTime datecreate;
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
		datecreate = LocalDateTime.parse(parameters.get("datecreate").toString(),
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
		avatar = (String) parameters.get("avatar");
	}

	// Ctor full parameters
	public RoomTable(int id, String name, LocalDateTime datecreate, String avatar) {
		this.id = id;
		this.name = name;
		this.datecreate = datecreate;
		this.avatar = avatar;
	}

	public boolean equals(RoomTable room) {
		return id == room.getId() && name.equals(room.getName()) && datecreate.equals(room.getDatecreate())
				&& avatar.equals(room.getAvatar());
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

	public LocalDateTime getDatecreate() {
		return datecreate;
	}

	public void setDatecreate(LocalDateTime datecreate) {
		this.datecreate = datecreate;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
