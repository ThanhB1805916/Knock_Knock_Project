package data_access.message_access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import data_access.ISQLDAO;
import data_access.ModelDAO;
import data_model.MessageTable;
import data_model.PersonTable;

public class MessageDAO extends ModelDAO<MessageTable> implements IMessageDAO {

	public MessageDAO(ISQLDAO dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

	//Insert Message
	public boolean InsertMessage(int id_room, int id_person, String messagecontent) {
		String query = "CALL spMessage_InsertMessage (?,?,?);";
		
		int c = dao.executeNonQuery(query, new Object[] {id_room,id_person,messagecontent} );
		boolean text = false;
		if(c != 0) return text = true;
		return text;
	}
	
	// Delete message
	public boolean DeleteMessage(int id_room, int id_person) {
		String query = "CALL spMessage_DeleteMessage (?,?);";
		
		int d = dao.executeNonQuery(query, new Object[] {id_room,id_person} );
		boolean text = false;
		if(d != 0) return text = true;
		return text;
	}
	
	// Get message by id_room and id_person
	public List<MessageTable> getList(int id_room, int id_person){
		String query = "CALL spMessage_GetMessage_ByID_RoomAndID_Person(?,?);";
		
		List<HashMap<String, Object>> dataTable = dao.executeQuery(query, new Object[] { id_room, id_person });

		List<MessageTable> Message_List = null;
		// If exist
		if (dataTable.isEmpty() == false) {
			Message_List = new ArrayList<MessageTable>();

			for (HashMap<String, Object> row : dataTable) {
				Message_List.add(new MessageTable(row));
			}
		}

		return Message_List;
	}
	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------- Get
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
