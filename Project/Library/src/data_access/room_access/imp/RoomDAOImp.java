package data_access.room_access.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import data_access.SQLDAO;
import data_access.model_access.imp.ModelDAOImp;
import data_access.room_access.RoomDAO;
import data_access.room_access.RoomDAO_Query;
import data_model.RoomTable;

public abstract class RoomDAOImp extends ModelDAOImp<RoomTable> implements RoomDAO, RoomDAO_Query {

	public RoomDAOImp(SQLDAO dao) {
		super(dao);
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Get
	// --------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public RoomTable get(int id) {
		List<HashMap<String, Object>> dataTable = dao.executeQuery(getQuery(), new Object[] { id });

		RoomTable roomTable = null;
		if (dataTable != null) {
			roomTable = new RoomTable(dataTable.get(0));
		}

		return roomTable;
	}

	// Get room list by id_person
	public List<RoomTable> getList(int id_person) {
		List<HashMap<String, Object>> dataTable = dao.executeQuery(getListQuery(), new Object[] { id_person });

		List<RoomTable> roomTableList = null;

		if (dataTable != null) {
			roomTableList = new ArrayList<RoomTable>();
			for (HashMap<String, Object> row : dataTable) {
				roomTableList.add(new RoomTable(row));
			}
		}

		return roomTableList;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Add
	// --------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public boolean add(RoomTable roomTable) {
		int rows = 0;
		if (roomTable.isValid()) {
			rows = dao.executeNonQuery(addQuery(), new Object[] { roomTable.getName(), roomTable.getAvatar() });
		}
		return rows > 0;
	}

	@Override
	public boolean add(int id_room, int id_person) {
		int rows = dao.executeNonQuery(addPersonQuery(), new Object[] { id_room, id_person});
		return rows > 0;
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Update
	// --------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public boolean update(RoomTable roomTable) {
		int rows = 0;
		if (roomTable.isValid()) {
			rows = dao.executeNonQuery(updateQuery(),
					new Object[] { roomTable.getId(), roomTable.getName(), roomTable.getAvatar() });
		}
		return rows > 0;
	}

	@Override
	public boolean exit(int id_person, int id_room)
	{
		int rows = dao.executeNonQuery(exitRoomQuery(), new Object[] { id_person, id_room});
		return rows > 0;
	}
}
