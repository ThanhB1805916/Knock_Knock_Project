package data_model;

import java.util.Date;
import java.util.HashMap;

//Class represent for table in database

public class MessageTable {

	// Attributes
	private int id_room;
	private int id_person;
	private String messagecontent;
	private Date sendtime;

	// ---------------------------------------------------------------------------
	// -------------------------------
	// ------------------------------- Constructors
	// -------------------------------
	// ---------------------------------------------------------------------------

	// Ctor get hashmap type parameters
	public MessageTable(HashMap<String, Object> parameters) {
		id_room = (int) parameters.get("id_room");
		id_person = (int) parameters.get("id_person");
		messagecontent = (String) parameters.get("messagecontent");
		sendtime = (Date) parameters.get("sendtime");
	}

	// Ctor full parameters
	public MessageTable(int id_room, int id_person, String messagecontent, Date sendtime) {
		this.id_room = id_room;
		this.id_person = id_person;
		this.messagecontent = messagecontent;
		this.sendtime = sendtime;
	}

	// ---------------------------------------------------------------------------
	// -------------------------------
	// ------------------------------- Setters - Getters
	// -------------------------------
	// ---------------------------------------------------------------------------

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

	public Date getSendtime() {
		return sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}
}
