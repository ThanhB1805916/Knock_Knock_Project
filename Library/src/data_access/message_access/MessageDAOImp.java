package data_access.message_access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import data_access.ModelDAOImp;
import data_access.SQLDAO;
import data_model.MessageTable;

public abstract class MessageDAOImp extends ModelDAOImp<MessageTable> implements MessageDAO {

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
		if (dataTable.isEmpty() == false) {
			for (HashMap<String, Object> row : dataTable) {
				messageTableList.add(new MessageTable(row));
			}
		}

		return messageTableList;
	}

	@Override
	public List<MessageTable> getList(int id_room) {
		List<HashMap<String, Object>> dataTable = dao.executeQuery(getListQuery(), new Object[] { id_room });

		List<MessageTable> messageTableList = new ArrayList<MessageTable>();
		// If exist
		if (dataTable.isEmpty() == false) {
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
		int rows = dao.executeNonQuery(addQuery(),
				new Object[] { message.getId_person(), message.getId_room(), message.getMessagecontent() });
		return rows > 0;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Update
	// --------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public boolean update(MessageTable message) {
		int rows = dao.executeNonQuery(updateQuery(),
				new Object[] { message.getId_person(), message.getId_room(), message.getMessagecontent() });
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
