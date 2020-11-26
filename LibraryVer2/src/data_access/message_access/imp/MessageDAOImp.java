package data_access.message_access.imp;

import java.util.ArrayList;
import java.util.List;

import data_access.SQLDAO;
import data_access.message_access.MessageDAO;
import data_access.message_access.MessageDAO_Query;
import data_access.model_access.imp.ModelDAOImp;
import data_model.MessageTable;

public abstract class MessageDAOImp extends ModelDAOImp<MessageTable> implements MessageDAO, MessageDAO_Query {

	public MessageDAOImp(SQLDAO dao) {
		super(dao, MessageTable.class);
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Get
	// --------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public List<MessageTable> getList(int id_room) {
		List<MessageTable> dataTable = dao.executeQuery(currentClass, getListQuery(), new Object[] { id_room });

		List<MessageTable> messageTableList = null;
		// If exist
		if (dataTable != null) {
			messageTableList = new ArrayList<MessageTable>();

			for (MessageTable row : dataTable) {
				messageTableList.add(row);
			}
		}

		return messageTableList;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Add
	// --------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public boolean add(MessageTable message) {
		int rows = 0;
		if (message.isValid()) {
			int isFile = booleanToBit(message.getIsFile());

			rows = dao.executeNonQuery(addQuery(), new Object[] { message.getId_room(), message.getId_person(),
					message.getMessagecontent(), isFile, message.getSendtime() });
		}
		return rows > 0;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Update
	// --------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public boolean update(MessageTable message) {
		int rows = 0;
		if (message.isValid()) {
			int isFile = booleanToBit(message.getIsFile());

			rows = dao.executeNonQuery(updateQuery(),
					new Object[] { message.getId(), message.getMessagecontent(), isFile });
		}
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
