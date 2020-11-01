package model;

import java.io.Serializable;

public class Message implements IValidModel, Serializable {
	/**
	 * Attributes
	 */
	private static final long serialVersionUID = 1L;

	private Person sender;
	private Room room;
	private String content;
	private String sendDate;

	// ---------------------------------------------------------------------------
	// -------------------------------
	// ------------------------------- Constructors
	// -------------------------------
	// ---------------------------------------------------------------------------

	// Ctor for create exist message
	public Message(Person sender, Room room, String content, String sendDate) {
		this.sender = sender;
		this.room = room;
		this.content = content;
		this.sendDate = sendDate;
	}

	@Override
	public boolean isValid() {
		return sender.isValid() && room.isValid() && content.isEmpty() == false;
	}

	// ---------------------------------------------------------------------------
	// -------------------------------
	// ------------------------------- Setters - Getters
	// -------------------------------
	// ---------------------------------------------------------------------------

	public Person getSender() {
		return sender;
	}

	public void setSender(Person sender) {
		this.sender = sender;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

}
