package model.sendmodel;

import java.io.Serializable;
import java.time.LocalDateTime;

import model.ValidModel;

public class Message implements ValidModel, Serializable {
	/**
	 * Attributes
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private Person sender;
	private int roomID;
	private FileInfo content;
	private boolean isFile;
	private LocalDateTime sendTime;

	// ---------------------------------------------------------------------------
	// -------------------------------
	// ------------------------------- Constructors
	// -------------------------------
	// ---------------------------------------------------------------------------

	// Ctor for create exist message
	public Message(int id, Person sender, int room, FileInfo content, boolean isFile, LocalDateTime sendTime) {
		this.setId(id);
		this.sender = sender;
		this.roomID = room;
		this.content = content;
		this.isFile = isFile;
		this.sendTime = sendTime;
	}

	@Override
	public boolean isValid() {
		return sender.isValid() && roomID >= 0 && sendTime != null && content != null;
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

	public Person getSender() {
		return sender;
	}

	public void setSender(Person sender) {
		this.sender = sender;
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
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

	public LocalDateTime getSendTime() {
		return sendTime;
	}

	public void setSendTime(LocalDateTime sendTime) {
		this.sendTime = sendTime;
	}

}
