package data_access.message_access;

import java.util.List;

import data_access.SQLDAO;
import data_access.ModelDAOImp;
import data_model.MessageTable;

public class MessageDAOImp extends ModelDAOImp<MessageTable> implements MessageDAO {

	public MessageDAOImp(SQLDAO dao) {
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
