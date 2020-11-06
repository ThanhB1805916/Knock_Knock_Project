package data_access.person_access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import data_access.SQLDAO;
import data_access.ModelDAOImp;
import data_model.PersonTable;

// Person Data Access Object
public class PersonDAOImp extends ModelDAOImp<PersonTable> implements PersonDAO {

	public PersonDAOImp(SQLDAO dao) {
		super(dao);
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Get
	// --------------------------------------------------------------------------------------------------------------------------------------------

	// Get person by id
	@Override
	public PersonTable get(int id) {
		String query = "CALL spPerson_GetPerson_Byid(?);";

		List<HashMap<String, Object>> dataTable = dao.executeQuery(query, new Object[] { id });

		PersonTable person = null;
		// If exist
		if (dataTable.isEmpty() == false) {
			person = new PersonTable(dataTable.get(0));
		}

		return person;
	}

	// Get person by username
	@Override
	public PersonTable getByUsername(String username) {
		String query = "CALL spPerson_GetPerson_ByUsername (?);";

		List<HashMap<String, Object>> dataTable = dao.executeQuery(query, new Object[] { username });

		PersonTable person = null;
		// If exist
		if (dataTable.isEmpty() == false) {
			person = new PersonTable(dataTable.get(0));
		}

		return person;
	}

	// Get person by phonenumber
	@Override
	public PersonTable getByPhonenumber(String phonenumber) {
		String query = "CALL spPerson_GetPerson_ByPhonenumber (?);";

		List<HashMap<String, Object>> dataTable = dao.executeQuery(query, new Object[] { phonenumber });

		PersonTable person = null;
		// If exist
		if (dataTable.isEmpty() == false) {
			person = new PersonTable(dataTable.get(0));
		}

		return person;

	}

	@Override
	public List<PersonTable> getListByID_Room(int id_room) {
		String query = "CALL spPerson_GetPersonList_ByID_Room(?);";

		List<HashMap<String, Object>> dataTable = dao.executeQuery(query, new Object[] { id_room });

		List<PersonTable> personList = null;
		// If exist
		if (dataTable.isEmpty() == false) {
			personList = new ArrayList<PersonTable>();

			for (HashMap<String, Object> row : dataTable) {
				personList.add(new PersonTable(row));
			}
		}

		return personList;
	}

	@Override
	public List<PersonTable> getListByID_Friend(int id_friend) {
		String query = "CALL  spPerson_GetPersonList_ByID_Friend(?);";

		List<HashMap<String, Object>> dataTable = dao.executeQuery(query, new Object[] { id_friend });

		List<PersonTable> personList = null;
		// If exist
		if (dataTable.isEmpty() == false) {
			personList = new ArrayList<PersonTable>();

			for (HashMap<String, Object> row : dataTable) {
				personList.add(new PersonTable(row));
			}
		}

		return personList;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Add
	// --------------------------------------------------------------------------------------------------------------------------------------------

	// Create new person
	@Override
	public boolean add(PersonTable person) {

		String query = "CALL spPerson_InsertPerson(?, ?, ?, ?, ?, ?);";
		int rows = dao.executeNonQuery(query, new Object[] { person.getUsername(), person.getPassword(),
				person.getName(), person.getGender() == true ? 1 : 0, person.getPhonenumber(), person.getDateofbirth()

		});

		return rows > 0;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Update
	// --------------------------------------------------------------------------------------------------------------------------------------------

	// Update person
	@Override
	public boolean update(PersonTable person) {

		String query = "CALL spPerson_UpdatePerson(?, ?, ?, ?, ?, ?);";
		int rows = dao.executeNonQuery(query,
				new Object[] { person.getUsername(), person.getPassword(), person.getName(),
						person.getGender() == true ? 1 : 0, person.getPhonenumber(), person.getDateofbirth() });

		return rows > 0;
	}

//	// --------------------------------------------------------------------------------------------------------------------------------------------
//	// ---------------------------------------------------------------- Update
//	// --------------------------------------------------------------------------------------------------------------------------------------------
//
//	//Delete person by username
//	@Override
//	public boolean delete(String username) {
//		
//		String query = "CALL spPerson_DeletePerson_ByUsername(?)";
//		int rows = dao.executeNonQuery(query, new Object[] { username});
//
//		return rows > 0;
//	}
}
