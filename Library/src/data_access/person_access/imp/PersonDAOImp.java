package data_access.person_access.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import data_access.SQLDAO;
import data_access.model_access.imp.ModelDAOImp;
import data_access.person_access.PersonDAO;
import data_access.person_access.PersonDAO_Query;
import data_model.PersonTable;

// Person Data Access Object
public abstract class PersonDAOImp extends ModelDAOImp<PersonTable> implements PersonDAO, PersonDAO_Query {

	public PersonDAOImp(SQLDAO dao) {
		super(dao);
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Get
	// --------------------------------------------------------------------------------------------------------------------------------------------

	// Get person by id
	@Override
	public PersonTable get(int id) {
		List<HashMap<String, Object>> dataTable = dao.executeQuery(getQuery(), new Object[] { id });

		PersonTable person = null;
		// If exist
		if (dataTable != null) {
			person = new PersonTable(dataTable.get(0));
		}

		return person;
	}

	// Get person by username
	@Override
	public PersonTable getByUsername(String username) {
		List<HashMap<String, Object>> dataTable = dao.executeQuery(getByUsernameQuery(), new Object[] { username });

		PersonTable person = null;
		// If exist
		if (dataTable != null) {
			person = new PersonTable(dataTable.get(0));
		}

		return person;
	}

	// Get person by phonenumber
	@Override
	public PersonTable getByPhonenumber(String phonenumber) {
		List<HashMap<String, Object>> dataTable = dao.executeQuery(getByPhonenumberQuery(),
				new Object[] { phonenumber });

		PersonTable person = null;
		// If exist
		if (dataTable != null) {
			person = new PersonTable(dataTable.get(0));
		}

		return person;

	}

	@Override
	public List<PersonTable> getListByID_Room(int id_room) {
		List<HashMap<String, Object>> dataTable = dao.executeQuery(getListByID_RoomQuery(), new Object[] { id_room });

		List<PersonTable> personList = null;
		// If exist
		if (dataTable != null) {
			personList = new ArrayList<PersonTable>();

			for (HashMap<String, Object> row : dataTable) {
				personList.add(new PersonTable(row));
			}
		}

		return personList;
	}

	@Override
	public List<PersonTable> getListByID_Friend(int id_friend) {
		List<HashMap<String, Object>> dataTable = dao.executeQuery(getListByID_FriendQuery(),
				new Object[] { id_friend });

		List<PersonTable> personList = null;
		// If exist
		if (dataTable != null) {
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
		int rows = dao.executeNonQuery(addQuery(),
				new Object[] { person.getUsername(), person.getPassword(), person.getName(),
						person.getGender() == true ? 1 : 0, person.getPhonenumber(), person.getDateofbirth(),
						person.getAvatar()

				});

		return rows > 0;
	}

	// Send request add friend
	@Override
	public boolean addFriend(int id_person, int id_friend) {
		int rows = dao.executeNonQuery(addFriendQuery(), new Object[] { id_person, id_friend });

		return rows > 0;
	}

	// Accept add friend
	@Override
	public boolean accept(int id_person, int id_friend) {
		int rows = dao.executeNonQuery(acceptQuery(), new Object[] { id_person, id_friend });

		return rows > 0;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Update
	// --------------------------------------------------------------------------------------------------------------------------------------------

	// Update person
	@Override
	public boolean update(PersonTable person) {
		int rows = dao.executeNonQuery(updateQuery(),
				new Object[] { person.getUsername(), person.getPassword(), person.getName(),
						person.getGender() == true ? 1 : 0, person.getPhonenumber(), person.getDateofbirth(),
						person.getAvatar() });

		return rows > 0;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Remove
	// --------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public boolean removeFriend(int id_person, int id_friend) {
		int rows = dao.executeNonQuery(removeFriendQuery(), new Object[] { id_person, id_friend });

		return rows > 0;

	}

}
