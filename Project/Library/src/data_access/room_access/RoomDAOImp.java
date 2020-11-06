package data_access.room_access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import data_access.SQLDAO;
import data_access.ModelDAOImp;
import data_model.RoomTable;

public class RoomDAOImp extends ModelDAOImp<RoomTable> implements RoomDAO {

	public RoomDAOImp(SQLDAO dao) {
		super(dao);
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
		String query = "call spRoom_GetRoomList_ByID_Person(?);";
		List<HashMap<String, Object>> HashList = dao.executeQuery(query, new Object[] { id_person });

		List<RoomTable> roomList = new ArrayList<RoomTable>();

		if(HashList != null)
		{
			for (HashMap<String, Object> hashMap : HashList) {
				roomList.add(new RoomTable(hashMap));
			}
		}

		return roomList;
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
