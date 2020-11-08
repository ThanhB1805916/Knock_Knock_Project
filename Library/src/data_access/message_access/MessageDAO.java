package data_access.message_access;

import java.util.List;

import data_access.ModelDAO;
import data_model.MessageTable;

public interface MessageDAO extends ModelDAO<MessageTable> {

	List<MessageTable> getList(int id_room);
	String getListQuery();
	
	boolean delete(int id);
	String deleteQuery();
}
