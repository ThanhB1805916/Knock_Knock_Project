package data_access.person_access.imp;

import data_access.SQLDAO;

public class PersonDAO_MySQL extends PersonDAOImp {

	public PersonDAO_MySQL(SQLDAO dao) {
		super(dao);
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Get

	@Override
	public String getQuery() {
		return "CALL spPerson_GetPerson_Byid(?);";
	}

	@Override
	public String getByUsernameQuery() {
		return "CALL spPerson_GetPerson_ByUsername (?);";
	}

	@Override
	public String getByPhonenumberQuery() {
		return "CALL spPerson_GetPerson_ByPhonenumber (?);";
	}

	@Override
	public String getListByID_RoomQuery() {
		return "CALL spPerson_GetPersonList_ByID_Room(?);";
	}

	@Override
	public String getListByID_FriendQuery() {
		return "CALL  spPerson_GetPersonList_ByID_Friend(?);";
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Add

	@Override
	public String addQuery() {
		return "CALL spPerson_InsertPerson(?, ?, ?, ?, ?, ?, ?);";
	}

	@Override
	public String addFriendQuery() {
		return "CALL spPerson_AddFriend(?, ?);";
	}

	@Override
	public String acceptQuery() {
		return "CALL spPerson_Accept(?, ?);";
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Update
	@Override
	public String updateQuery() {
		return "CALL spPerson_UpdatePerson(?, ?, ?, ?, ?, ?, ?, ?);";
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Remove

	@Override
	public String removeFriendQuery() {
		return "CALL spPerson_RemoveFriend(?, ?);";
	}
}
