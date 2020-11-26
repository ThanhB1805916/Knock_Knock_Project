package model.sendmodel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import model.ValidModel;

public class Room implements ValidModel, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private LocalDateTime dateCreate;
	private FileInfo avatar;
	
	private List<Message> messages;
	private List<Person> members;

	// ---------------------------------------------------------------------------
	// -------------------------------
	// ------------------------------- Constructors
	// -------------------------------
	// ---------------------------------------------------------------------------

	// Ctor for create new room
	public Room(int id, String name, FileInfo avatar, List<Person> members) {
		this.id = id;
		this.name = name;
		this.avatar = avatar;
		this.members = members;
	}

	// Ctor full para
	public Room(int id, String name, LocalDateTime dateCreate, FileInfo avatar, List<Message> messages,
			List<Person> members) {
		super();
		this.id = id;
		this.name = name;
		this.dateCreate = dateCreate;
		this.avatar = avatar;
		this.messages = messages;
		this.members = members;
	}

	@Override
	public boolean isValid() {
		try {
			return name.isEmpty() == false && dateCreate != null;
		} catch (Exception e) {
			return false;
		}
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

	public LocalDateTime getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(LocalDateTime dateCreate) {
		this.dateCreate = dateCreate;
	}

	public FileInfo getAvatar() {
		return avatar;
	}

	public void setAvatar(FileInfo avatar) {
		this.avatar = avatar;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public List<Person> getMembers() {
		return members;
	}

	public void setMembers(List<Person> members) {
		this.members = members;
	}
}
