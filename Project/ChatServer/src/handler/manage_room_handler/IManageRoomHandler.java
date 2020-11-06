package handler.manage_room_handler;

import java.util.List;

import model.sendmodel.Room;

public interface IManageRoomHandler {

	/*
	 * Get room list of login user
	 */
	List<Room> get();
	
	boolean add(Room room);
	
	boolean remove(Room room);
	
	boolean exit(Room room);
}
