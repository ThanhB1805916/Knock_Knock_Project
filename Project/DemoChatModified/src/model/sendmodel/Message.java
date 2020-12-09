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
	private int roomID;
	private FileInfo content;
	private boolean isFile;
	private LocalDateTime sendDate;

	// ---------------------------------------------------------------------------
	// -------------------------------
	// ------------------------------- Constructors
	// -------------------------------
	// ---------------------------------------------------------------------------

	// Ctor for create exist message
	public Message(Person sender, int room, FileInfo content, boolean isFile, LocalDateTime sendDate) {
		this.sender = sender;
		this.roomID = room;
		this.content = content;
		this.isFile = isFile;
		this.sendDate = sendDate;
	}

	@Override
	public boolean isValid() {
		return sender.isValid() && roomID>0 && content != null && sendDate != null;
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

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int room) {
		this.roomID = room;
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

