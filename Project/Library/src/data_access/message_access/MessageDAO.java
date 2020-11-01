package data_access.message_access;

import java.util.List;

import data_access.ISQLDAO;
import data_access.ModelDAO;
import data_model.MessageTable;

public class MessageDAO extends ModelDAO<MessageTable> implements IMessageDAO {

	public MessageDAO(ISQLDAO dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Get
	// --------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public MessageTable get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessageTable> getList(int id_room) {
		// TODO Auto-generated method stub
		return null;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Add
	// --------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public boolean add(MessageTable T) {
		// TODO Auto-generated method stub
		return false;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Update
	// --------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public boolean update(MessageTable T) {
		// TODO Auto-generated method stub
		return false;
	}

}
