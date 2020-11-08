package data_access.person_access;

import data_access.SQLDAO;

public class PersonDAO_SQLServer extends PersonDAOImp {

	public PersonDAO_SQLServer(SQLDAO dao) {
		super(dao);

		spPerson_GetPerson_Byid = "EXEC spPerson_GetPerson_Byid ?;";
		spPerson_GetPerson_ByUsername = "EXEC spPerson_GetPerson_ByUsername ?;";
		spPerson_GetPerson_ByPhonenumber = "EXEC spPerson_GetPerson_ByPhonenumber ?;";
		spPerson_GetPersonList_ByID_Room = "EXEC spPerson_GetPersonList_ByID_Room ?;";
		spPerson_GetPersonList_ByID_Friend = "EXEC  spPerson_GetPersonList_ByID_Friend ?;";
		spPerson_InsertPerson = "EXEC spPerson_InsertPerson ?, ?, ?, ?, ?, ?;";
		spPerson_UpdatePerson = "EXEC spPerson_UpdatePerson ?, ?, ?, ?, ?, ?;";
	}
}
