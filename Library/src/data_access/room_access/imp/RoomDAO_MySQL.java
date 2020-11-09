package data_access.room_access.imp;

import data_access.SQLDAO;

public class RoomDAO_MySQL extends RoomDAOImp {

	public RoomDAO_MySQL(SQLDAO dao) {
		super(dao);
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Get

	@Override
	public String getQuery() {
		return "Call spRoom_GetRoom_ByID(?)";
	}

	@Override
	public String getListQuery() {
		return "call spRoom_GetRoomList_ByID_Person(?);";
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Add

	@Override
	public String addQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Update

	@Override
	public String updateQuery() {
		// TODO Auto-generated method stub
		return null;
	}
}
