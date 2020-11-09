package data_access.message_access.imp;

import data_access.SQLDAO;

public class MessageDAO_MySQL extends MessageDAOImp {

	public MessageDAO_MySQL(SQLDAO dao) {
		super(dao);
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Get

	@Override
	public String getQuery() {
		return "CALL spMessage_GetMessage_ByID(?);";
	}

	@Override
	public String getListQuery() {
		// TODO Auto-generated method stub
		return "call spMessage_GetMessageList_ByID_Room(?);";
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Add

	@Override
	public String addQuery() {
		// TODO Auto-generated method stub
		return "CALL spMessage_InsertMessage(?, ?, ?, ?, ?);";
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Update

	@Override
	public String updateQuery() {
		// TODO Auto-generated method stub
		return "CALL spMessage_UpdateMessage(?, ?, ?, ?, ?);";
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Delete

	@Override
	public String deleteQuery() {
		// TODO Auto-generated method stub
		return "call spMessage_DeleteMessage(?);";
	}

}
