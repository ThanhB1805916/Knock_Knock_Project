package data_access.message_access;

import java.util.List;

import data_access.model_access.ModelDAO;
import data_model.MessageTable;

//Interface execute query at run time
public interface MessageDAO extends ModelDAO<MessageTable> {
	
	List<MessageTable> getList(int id_room);
	
	boolean delete(int id);
}
