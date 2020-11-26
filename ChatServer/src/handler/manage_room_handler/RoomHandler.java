package handler.manage_room_handler;

import java.util.List;

import model.sendmodel.Person;
import model.sendmodel.Room;

public interface RoomHandler {

	/*
	 * Get room list of login user
	 */
	List<Room> get();
	
	/*
	 * Create new room
	 * */
	boolean add(Room room);
	
	/*
	 * Add memeber into room
	 * */
	boolean add(Person person);
	
	/*
	 * */
	boolean remove(Room room);
	
	/*
	 * */
	boolean exit(Room room);
}
