package data_access.person_access.imp;

import data_access.SQLDAO;

public class PersonDAO_SQLServer extends PersonDAOImp {

	public PersonDAO_SQLServer(SQLDAO dao) {
		super(dao);
	}

	@Override
	public String getQuery() {
		return "EXEC spPerson_GetPerson_Byid ?;";
	}

	@Override
	public String addQuery() {
		return "EXEC spPerson_InsertPerson ?, ?, ?, ?, ?, ?, ?;";
	}

	@Override
	public String updateQuery() {
		return "EXEC spPerson_UpdatePerson ?, ?, ?, ?, ?, ?, ?;";
	}

	@Override
	public String getByUsernameQuery() {
		return "EXEC spPerson_GetPerson_ByUsername ?;";
	}

	@Override
	public String getByPhonenumberQuery() {
		return "EXEC spPerson_GetPerson_ByPhonenumber ?;";
	}

	@Override
	public String getListByID_RoomQuery() {
		return "EXEC spPerson_GetPersonList_ByID_Room ?;";
	}

	@Override
	public String getListByID_FriendQuery() {
		return "EXEC  spPerson_GetPersonList_ByID_Friend ?;";
	}

	@Override
	public String removeFriendQuery() {
		return "EXEC spPerson_RemoveFriend ?, ?;";
	}

	@Override
	public String addFriendQuery() {
		// TODO Auto-generated method stub
		return null;
	}

}
