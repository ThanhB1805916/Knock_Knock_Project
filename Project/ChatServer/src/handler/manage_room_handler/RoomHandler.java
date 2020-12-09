package handler.manage_room_handler;

import java.util.List;

import model.sendmodel.Person;
import model.sendmodel.Room;

public interface RoomHandler {

	/*
	 * Get room list of login user
	 */
	List<Room> get(int id_person);
	
	/*
	 * Create new room
	 * */
	boolean add(Room room);
	
	/*
	 * Add member into room
	 * */
	boolean add(Room room, Person person);
	
	boolean update(Room room);
	
	/*
	 * */
	boolean remove(Room room);
	
	/*
	 * Remove a member from a room
	 * */
	boolean exitRoom(Person person, Room room);

}
