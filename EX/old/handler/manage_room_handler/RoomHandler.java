package handler.manage_room_handler;

import java.util.List;

import model.sendmodel.Room;
import socket.Client;

public interface RoomHandler {

	/*
	 * Get room list of login user
	 */
	List<Room> get(Client client);
	
	boolean add(Room room);
	
	boolean remove(Room room);
	
	boolean exit(Room room);
}
