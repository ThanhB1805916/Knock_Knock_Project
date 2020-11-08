package data_access.person_access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import data_access.ModelDAOImp;
import data_access.SQLDAO;
import data_model.PersonTable;

// Person Data Access Object
public abstract class PersonDAOImp extends ModelDAOImp<PersonTable> implements PersonDAO {

	protected String spPerson_GetPerson_Byid;
	protected String spPerson_GetPerson_ByUsername;
	protected String spPerson_GetPerson_ByPhonenumber;
	protected String spPerson_GetPersonList_ByID_Room;
	protected String spPerson_GetPersonList_ByID_Friend;
	protected String spPerson_InsertPerson;
	protected String spPerson_UpdatePerson;

	public PersonDAOImp(SQLDAO dao) {
		super(dao);
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Get
	// --------------------------------------------------------------------------------------------------------------------------------------------

	// Get person by id
	@Override
	public PersonTable get(int id) {
		List<HashMap<String, Object>> dataTable = dao.executeQuery(spPerson_GetPerson_Byid, new Object[] { id });

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
		List<HashMap<String, Object>> dataTable = dao.executeQuery(spPerson_GetPerson_ByUsername,
				new Object[] { username });

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
		List<HashMap<String, Object>> dataTable = dao.executeQuery(spPerson_GetPerson_ByPhonenumber,
				new Object[] { phonenumber });

		PersonTable person = null;
		// If exist
		if (dataTable.isEmpty() == false) {
			person = new PersonTable(dataTable.get(0));
		}

		return person;

	}

	@Override
	public List<PersonTable> getListByID_Room(int id_room) {
		List<HashMap<String, Object>> dataTable = dao.executeQuery(spPerson_GetPersonList_ByID_Room, new Object[] { id_room });

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
		List<HashMap<String, Object>> dataTable = dao.executeQuery(spPerson_GetPersonList_ByID_Friend,
				new Object[] { id_friend });

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
		int rows = dao.executeNonQuery(spPerson_InsertPerson, new Object[] { person.getUsername(), person.getPassword(),
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
		int rows = dao.executeNonQuery(spPerson_UpdatePerson,
				new Object[] { person.getUsername(), person.getPassword(), person.getName(),
						person.getGender() == true ? 1 : 0, person.getPhonenumber(), person.getDateofbirth() });

		return rows > 0;
	}

}
