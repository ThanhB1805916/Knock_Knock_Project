package data_model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

//Class represent for table in database

public class MessageTable implements ValidModel {

	// Attributes
	private int id;
	private int id_room;
	private int id_person;
	private String messagecontent;
	private boolean isFile;
	private LocalDateTime sendtime;

	// ---------------------------------------------------------------------------
	// -------------------------------
	// ------------------------------- Constructors
	// -------------------------------
	// ---------------------------------------------------------------------------

	// Ctor get hashmap type parameters
	public MessageTable(HashMap<String, Object> parameters) {
		id = (int) parameters.get("id");
		id_room = (int) parameters.get("id_room");
		id_person = (int) parameters.get("id_person");
		messagecontent = (String) parameters.get("messagecontent");
		isFile = (boolean) parameters.get("isFile");
		sendtime = LocalDateTime.parse(parameters.get("sendtime").toString(),
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
	}

	// Ctor full parameters
	public MessageTable(int id, int id_room, int id_person, String messagecontent, boolean isFile,
			LocalDateTime sendtime) {
		this.id = id;
		this.id_room = id_room;
		this.id_person = id_person;
		this.messagecontent = messagecontent;
		this.isFile = isFile;
		this.sendtime = sendtime;
	}

	@Override
	public boolean isValid() {

		boolean isValid = false;
		try {
			isValid = id >= 0 && id_room >= 0 && id_person >= 0 && messagecontent.isEmpty() == false
					&& sendtime != null;
		} catch (Exception e) {
		}

		return isValid;
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

	public int getId_room() {
		return id_room;
	}

	public void setId_room(int id_room) {
		this.id_room = id_room;
	}

	public int getId_person() {
		return id_person;
	}

	public void setId_person(int id_person) {
		this.id_person = id_person;
	}

	public String getMessagecontent() {
		return messagecontent;
	}

	public void setMessagecontent(String messagecontent) {
		this.messagecontent = messagecontent;
	}

	public boolean getIsFile() {
		return isFile;
	}

	public void setIsFile(boolean isFile) {
		this.isFile = isFile;
	}

	public LocalDateTime getSendtime() {
		return sendtime;
	}

	public void setSendtime(LocalDateTime sendtime) {
		this.sendtime = sendtime;
	}
}
