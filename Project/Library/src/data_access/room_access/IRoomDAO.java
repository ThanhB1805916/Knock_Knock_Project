package data_access.room_access;

import java.util.List;

import data_access.IModelDAO;
import data_model.RoomTable;

public interface IRoomDAO extends IModelDAO<RoomTable> {
	
	// get list room by id_person
	public List<RoomTable> getList(int id_person);
	
	// Insert room 
	public boolean InsertRoom(String roomname);
	
	// update room by id, roomname
//	public RoomTable UpdateRoom(int id, String roomname);
	
	// delete room by id
	public boolean DeleteRoom(int id);
}
