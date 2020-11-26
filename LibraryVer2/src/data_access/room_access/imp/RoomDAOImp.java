package data_access.room_access.imp;

import java.util.ArrayList;
import java.util.List;

import data_access.SQLDAO;
import data_access.model_access.imp.ModelDAOImp;
import data_access.room_access.RoomDAO;
import data_access.room_access.RoomDAO_Query;
import data_model.RoomTable;

public abstract class RoomDAOImp extends ModelDAOImp<RoomTable> implements RoomDAO, RoomDAO_Query {

	public RoomDAOImp(SQLDAO dao) {
		super(dao, RoomTable.class);
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Get
	// --------------------------------------------------------------------------------------------------------------------------------------------

	// Get room list by id_person
	public List<RoomTable> getList(int id_person) {
		List<RoomTable> dataTable = dao.executeQuery(currentClass, getListQuery(), new Object[] { id_person });

		List<RoomTable> roomTableList = null;

		if (dataTable != null) {
			roomTableList = new ArrayList<RoomTable>();
			for (RoomTable row : dataTable) {
				roomTableList.add(row);
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

}
