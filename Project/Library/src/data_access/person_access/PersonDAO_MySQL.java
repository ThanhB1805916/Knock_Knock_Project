package data_access.person_access;

import data_access.SQLDAO;

public class PersonDAO_MySQL extends PersonDAOImp {

	public PersonDAO_MySQL(SQLDAO dao) {
		super(dao);
		
		spPerson_GetPerson_Byid = "CALL spPerson_GetPerson_Byid(?);";
		spPerson_GetPerson_ByUsername = "CALL spPerson_GetPerson_ByUsername (?);";
		spPerson_GetPerson_ByPhonenumber = "CALL spPerson_GetPerson_ByPhonenumber (?);";
		spPerson_GetPersonList_ByID_Room = "CALL spPerson_GetPersonList_ByID_Room(?);";
		spPerson_GetPersonList_ByID_Friend = "CALL  spPerson_GetPersonList_ByID_Friend(?);";
		spPerson_InsertPerson = "spPerson_InsertPerson(?, ?, ?, ?, ?, ?);";
		spPerson_UpdatePerson = "CALL spPerson_UpdatePerson(?, ?, ?, ?, ?, ?);";
	}
}
