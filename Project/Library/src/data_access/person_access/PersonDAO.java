package data_access.person_access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import data_access.ISQLDAO;
import data_access.ModelDAO;
import data_model.PersonTable;

// Person Data Access Object
public class PersonDAO extends ModelDAO<PersonTable> implements IPersonDAO {

	public PersonDAO(ISQLDAO dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Exist
	// --------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public boolean existUsername(String username) {

		String query = "CALL spPerson_ExistPerson_ByUsername(?);";

		long row = (long) dao.executeScalar(query, new Object[] { username });

		return row > 0;
	}

	@Override
	public boolean existPhonenumber(String phonenumber) {
		String query = "CALL spPerson_ExistPerson_ByPhonenumber(?);";

		long row = (long) dao.executeScalar(query, new Object[] { phonenumber });

		return row > 0;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Get
	// --------------------------------------------------------------------------------------------------------------------------------------------

	// Get person by id
	@Override
	public PersonTable get(int id) {
		String query = "CALL spPerson_GetPerson_Byid(?);";

		List<HashMap<String, Object>> Hash_List = dao.executeQuery(query, new Object[] { id });
	
		PersonTable person = null;

		// if exist
		if (Hash_List.isEmpty() == false) {
			person = new PersonTable(Hash_List.get(0));
		}

		return person;
	}

	// Get person by personaccount and password
	@Override
	public PersonTable get(String personaccount, String personpassword) {
		String query = "CALL spPerson_GetPerson_ByAccountAndPassword (?, ?);";

		List<HashMap<String, Object>> Hash_List = dao.executeQuery(query,
				new Object[] { personaccount, personpassword });

		PersonTable person = null;

		// if exist
		if (Hash_List.isEmpty() == false) {
			person = new PersonTable(Hash_List.get(0));
		}

		return person;
	}

	// Get person list by id room
	@Override
	public List<PersonTable> getList(int id_room) {
		String query = "CALL spPerson_GetPersonList_ByID_Room(?);";

		List<HashMap<String, Object>> Hash_List = dao.executeQuery(query, new Object[] { id_room });

		List<PersonTable> Person_List = null;

		// if exist
		if (Hash_List.isEmpty() == false) {
			Person_List = new ArrayList<PersonTable>();

			for (HashMap<String, Object> hashMap : Hash_List) {
				Person_List.add(new PersonTable(hashMap));
			}
		}

		return Person_List;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Add
	// --------------------------------------------------------------------------------------------------------------------------------------------

	// Create new person
	@Override
	public boolean add(PersonTable person) {

		// Get valid date
		String dateofbirth = convertDate(person.getDateofbirth());

		String query = "CALL spPerson_InsertPerson(?, ?, ?, ?, ?, ?);";
		int rows = dao.executeNonQuery(query, new Object[] { person.getUsername(), person.getPassword(),
				person.getName(), person.getGender() == true ? 1 : 0, person.getPhonenumber(), dateofbirth

		});

		return rows > 0;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Update
	// --------------------------------------------------------------------------------------------------------------------------------------------

	// Update person
	@Override
	public boolean update(PersonTable person) {
		// Get valid date
		String dateofbirth = convertDate(person.getDateofbirth());

		String query = "CALL spPerson_UpdatePerson(?, ?, ?, ?, ?, ?);";
		int rows = dao.executeNonQuery(query, new Object[] { person.getUsername(), person.getPassword(),
				person.getName(), person.getGender() == true ? 1 : 0, person.getPhonenumber(), dateofbirth });

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
