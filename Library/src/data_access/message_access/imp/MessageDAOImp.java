package data_access.message_access.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import data_access.SQLDAO;
import data_access.message_access.MessageDAO;
import data_access.message_access.MessageDAO_Query;
import data_access.model_access.imp.ModelDAOImp;
import data_model.MessageTable;

public abstract class MessageDAOImp extends ModelDAOImp<MessageTable> implements MessageDAO, MessageDAO_Query {

	public MessageDAOImp(SQLDAO dao) {
		super(dao);
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Get
	// --------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public MessageTable get(int id) {
		List<HashMap<String, Object>> dataTable = dao.executeQuery(getQuery(), new Object[] { id });

		MessageTable messageTable = null;
		// If exist
		if (dataTable != null) {
			messageTable = new MessageTable(dataTable.get(0));
		}

		return messageTable;
	}

	@Override
	public List<MessageTable> getList(int id_room) {
		List<HashMap<String, Object>> dataTable = dao.executeQuery(getListQuery(), new Object[] { id_room });

		List<MessageTable> messageTableList = new ArrayList<MessageTable>();
		// If exist
		if (dataTable != null) {
			for (HashMap<String, Object> row : dataTable) {
				messageTableList.add(new MessageTable(row));
			}
		}

		return messageTableList;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Add
	// --------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public boolean add(MessageTable message) {
		int rows = dao.executeNonQuery(addQuery(), new Object[] { message.getId_person(), message.getId_room(),
				message.getIsFile(), message.getMessagecontent(), message.getSendtime() });
		return rows > 0;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Update
	// --------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public boolean update(MessageTable message) {
		int rows = dao.executeNonQuery(updateQuery(),
				new Object[] { message.getId(), message.getIsFile(), message.getMessagecontent() });
		return rows > 0;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Delete
	// --------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public boolean delete(int id) {
		int rows = dao.executeNonQuery(deleteQuery(), new Object[] { id });
		return rows > 0;
	}

}
