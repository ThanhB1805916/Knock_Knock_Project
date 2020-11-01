package data_access.message_access;

import java.util.List;

import data_access.IModelDAO;
import data_model.MessageTable;

public interface IMessageDAO extends IModelDAO<MessageTable> {

	public List<MessageTable> getList(int id_room);
}
