package data_access.room_access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import data_access.ISQLDAO;
import data_access.ModelDAO;
import data_model.RoomTable;

public class RoomDAO extends ModelDAO<RoomTable> implements IRoomDAO {

	public RoomDAO(ISQLDAO dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Get
	// --------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public RoomTable get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	// Get room list by id_person
	public List<RoomTable> getList(int id_person) {
		String query = "CALL spRooms_GetRoomList_ById_Person(?);";
		List<HashMap<String, Object>> HashList = dao.executeQuery(query, new Object[] { id_person });

		List<RoomTable> Room_List = new ArrayList<RoomTable>();

		for (HashMap<String, Object> hashMap : HashList) {
			Room_List.add(new RoomTable(hashMap));
		}

		return Room_List;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Add
	// --------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public boolean add(RoomTable T) {
		// TODO Auto-generated method stub
		return false;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Update
	// --------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public boolean update(RoomTable T) {
		// TODO Auto-generated method stub
		return false;
	}

}
