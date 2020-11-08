package data_access.message_access;

import java.util.List;

import data_access.IModelDAO;
import data_model.MessageTable;
import data_model.RoomTable;

public interface IMessageDAO extends IModelDAO<MessageTable> {

	public List<MessageTable> getList(int id_room);
	
	// Get message by id_room and id_person
	public List<MessageTable> getList(int id_room, int id_person);
	
	// Insert message
	public boolean InsertMessage(int id_room, int id_person, String messagecontent);
	
	// Delete message
	public boolean DeleteMessage(int id_room, int id_person);
}
