package data_access.room_access;

import java.util.List;

import data_access.model_access.ModelDAO;
import data_model.RoomTable;

public interface RoomDAO extends ModelDAO<RoomTable> {

	int getRoomSize();
	//Add member into room
	boolean add(int id_room, int id_person);
	List<RoomTable> getList(int id_person);
	
	//Exit room
	boolean exitRoom(int id_person, int id_room);
}
