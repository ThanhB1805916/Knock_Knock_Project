package socket;

import java.util.List;

import communication_standard.model.Person;
import communication_standard.model.Room;

public class User {

	private Person person;
	
	private List<Person> friends;
	private List<Room> rooms;

	// ---------------------------------------------------------------------------
	// ------------------------------- Constructor
	// ---------------------------------------------------------------------------

	public User(Person person) {
		this.setPerson(person);
	}

	public User(Person person, List<Person> friends, List<Room> rooms) {
		this.setPerson(person);
		this.friends = friends;
		this.rooms = rooms;
	}

	// ---------------------------------------------------------------------------
	// ------------------------------- Setter - Getter
	// ---------------------------------------------------------------------------

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Person> getFriends() {
		return friends;
	}

	public void setFriends(List<Person> friends) {
		this.friends = friends;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

}
