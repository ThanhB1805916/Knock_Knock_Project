package communication_standard.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Room implements IValidModel, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private Date dateCreate;

	private List<Message> messages;
	private List<Person> members;
	private FileInfo avatar;

	// ---------------------------------------------------------------------------
	// -------------------------------
	// ------------------------------- Constructors
	// -------------------------------
	// ---------------------------------------------------------------------------

	// Ctor for create new room
	public Room(int id, String name, FileInfo avatar, List<Person> members) {
		this.id = id;
		this.name = name;
		this.members = members;
	}

	// Ctor for exist room
	public Room(int id, String name, List<Message> messages, List<Person> members) {
		this.id = id;
		this.name = name;
		this.messages = messages;
		this.members = members;
	}

	// Ctor full para
	public Room(int id, String name, Date dateCreate, List<Message> messages, List<Person> members,
			FileInfo avatar) {
		super();
		this.id = id;
		this.name = name;
		this.dateCreate = dateCreate;
		this.messages = messages;
		this.members = members;
		this.avatar = avatar;
	}

	@Override
	public boolean isValid() {
		return name.isEmpty() == false;
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

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
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

	public FileInfo getAvatar() {
		return avatar;
	}

	public void setAvatar(FileInfo avatar) {
		this.avatar = avatar;
	}
}
