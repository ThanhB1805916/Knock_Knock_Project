package model.sendmodel;

import java.io.Serializable;

import model.ValidModel;

// Use in room handler
public class RoomPersonModel implements ValidModel, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Room room;
	private Person person;

	public RoomPersonModel(Room room, Person person) {
		this.room = room;
		this.person = person;
	}

	@Override
	public boolean isValid() {
		return room != null && room.isValid() && person != null && person.isValid();
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
