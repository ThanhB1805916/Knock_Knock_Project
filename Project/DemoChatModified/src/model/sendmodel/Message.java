package model.sendmodel;

import java.io.Serializable;
import java.time.LocalDateTime;

import model.ValidModel;

public class Message implements ValidModel, Serializable {
	/**
	 * Attributes
	 */
	private static final long serialVersionUID = 1L;

	private Person sender;
	private Room room;
	private FileInfo content;
	private boolean isFile;
	private LocalDateTime sendDate;

	// ---------------------------------------------------------------------------
	// -------------------------------
	// ------------------------------- Constructors
	// -------------------------------
	// ---------------------------------------------------------------------------

	// Ctor for create exist message
	public Message(Person sender, Room room, FileInfo content, boolean isFile, LocalDateTime sendDate) {
		this.sender = sender;
		this.room = room;
		this.content = content;
		this.isFile = isFile;
		this.sendDate = sendDate;
	}

	@Override
	public boolean isValid() {
		return sender.isValid() && room.isValid() && content != null && sendDate != null;
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

	public FileInfo getContent() {
		return content;
	}

	public void setContent(FileInfo content) {
		this.content = content;
	}

	public boolean getIsFile() {
		return isFile;
	}

	public void setIsFile(boolean isFile) {
		this.isFile = isFile;
	}

	public LocalDateTime getSendDate() {
		return sendDate;
	}

	public void setSendDate(LocalDateTime sendDate) {
		this.sendDate = sendDate;
	}

}

