package libraryTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import connection.ConnectionString;
import data_access.DAOFactory;
import data_access.person_access.PersonDAO;
import data_model.PersonTable;

// Test Full get only

public class PersonDAO_Test {

	PersonDAO dao;
	PersonTable personValid;
	List<PersonTable> friendsValid;
	List<PersonTable> membersValid;

	@Before
	public void setUp() {
		dao = DAOFactory.getInstance()
//				.setSQLDAO(new ConnectionString(
//						"jdbc:mysql://localhost:3306/Knock_Knock_Project_Test?noAccessToProcedureBodies=true",
//						"stdUser", "std1234"))
				.setSQLDAO(new ConnectionString(
						"jdbc:sqlserver://localhost:1433; databaseName=Knock_Knock_Project;",
						"sa", "159357"))
				.getPersonDAO();
		
		personValid = new PersonTable(1, "admin", "1234", "Administrator", true, "0000000000",
				LocalDate.of(2020, 10, 04), "anh-avatar-supreme-dep-lam-dai-dien-facebook.jpg");

		PersonTable user2 = new PersonTable(2, "user1", "1234", "User1", true, "00000000001",
				LocalDate.of(2020, 10, 04), "default_avatar.png");

		membersValid = new ArrayList<>();
		membersValid.add(personValid);
		membersValid.add(user2);

		friendsValid = new ArrayList<PersonTable>();
		friendsValid.add(user2);
	}

	public boolean isEqual(PersonTable personA, PersonTable personB) {

		return personA.getId() == personB.getId() && personA.getUsername().equals(personB.getUsername())
				&& personA.getPassword().equals(personB.getPassword()) && personA.getName().equals(personB.getName())
				&& personA.getGender() == personB.getGender()
				&& personA.getDateofbirth().equals(personB.getDateofbirth())
				&& personA.getAvatar().equals(personB.getAvatar());
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Get ID

	@Test
	public void getByID() {
		PersonTable person = dao.get(personValid.getId());
		Assert.assertNotNull(person);
	}

	@Test
	public void ID_isNegative_when_getByID() {
		PersonTable person = dao.get(-1);
		Assert.assertNull(person);
	}

	@Test
	public void getByIDEqualsTrueValue() {
		PersonTable person = dao.get(personValid.getId());
		Assert.assertTrue(isEqual(person, personValid));
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Get Username

	@Test
	public void getByUsername() {
		PersonTable person = dao.getByUsername(personValid.getUsername());
		Assert.assertNotNull(person);
	}

	@Test
	public void username_isNull_when_getByUsername() {
		PersonTable person = dao.getByUsername(null);
		Assert.assertNull(person);
	}

	@Test
	public void username_isEmpty_when_getByUsername() {
		PersonTable person = dao.getByUsername("");
		Assert.assertNull(person);
	}

	@Test
	public void getByUserNameEqualsGetByID() {
		PersonTable personID = dao.get(personValid.getId());
		PersonTable personUsername = dao.getByUsername(personValid.getUsername());
		Assert.assertTrue(isEqual(personID, personUsername));
	}

	@Test
	public void getByUserNameEqualsTrueValue() {
		PersonTable person = dao.getByUsername(personValid.getUsername());
		Assert.assertTrue(isEqual(person, personValid));
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------ Get Phonenumber

	@Test
	public void getByPhonenumber() {
		String phonenumber = personValid.getPhonenumber();
		PersonTable person = dao.getByPhonenumber(phonenumber);

		Assert.assertTrue(person != null);
	}

	@Test
	public void phonenumber_isNull_when_getByPhonenubmer() {
		String phonenumber = null;
		PersonTable person = dao.getByPhonenumber(phonenumber);

		Assert.assertTrue(person == null);
	}

	@Test
	public void phonenumber_isEmpty_when_getByPhonenumber() {
		String phonenumber = "";
		PersonTable person = dao.getByPhonenumber(phonenumber);

		Assert.assertTrue(person == null);
	}

	@Test
	public void getByPhonenumberEqualsTrueValue() {
		String phonenumber = personValid.getPhonenumber();
		PersonTable person = dao.getByPhonenumber(phonenumber);
		Assert.assertTrue(isEqual(person, personValid));
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------- Get List Id_room

	@Test
	public void getListById_Room() {
		List<PersonTable> memebers = dao.getListByID_Room(personValid.getId());
		Assert.assertNotNull(memebers);
	}

	@Test
	public void id_isNegative_when_getListById_Room() {
		List<PersonTable> memebers = dao.getListByID_Room(-1);
		Assert.assertNull(memebers);
	}

//	@Test
//	public void getListById_RoomEqualsTrueValue() {
//		List<PersonTable> memebers = dao.getListByID_Room(personValid.getId());
//		int i = 0;
//		for (PersonTable personTable : memebers) {
//			Assert.assertTrue(isEqual(personTable, membersValid.get(i++)));
//		}
//	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------- Get List Id_Friend

	@Test
	public void getListById_Friend() {
		List<PersonTable> memebers = dao.getListByID_Friend(personValid.getId());
		Assert.assertNotNull(memebers);
	}

	@Test
	public void id_isNegative_when_getListById_Friend() {
		List<PersonTable> memebers = dao.getListByID_Friend(-1);
		Assert.assertNull(memebers);
	}

	@Test
	public void getListById_FriendEqualsTrueValue() {
		List<PersonTable> memebers = dao.getListByID_Friend(personValid.getId());
		int i = 0;
		for (PersonTable personTable : memebers) {
			Assert.assertTrue(isEqual(personTable, friendsValid.get(i++)));
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------- Update
	@Test
	public void update() {
		PersonTable person = dao.get(personValid.getId());
		boolean success = dao.update(person);
		assertTrue(success);
	}

	@Test
	public void id_isNegative_when_update() {
		PersonTable person = dao.get(personValid.getId());
		person.setId(-1);
		boolean success = dao.update(person);
		assertFalse(success);
	}

	@Test
	public void username_isNull_when_update() {
		PersonTable person = dao.get(personValid.getId());
		person.setUsername(null);
		boolean success = dao.update(person);
		assertFalse(success);
	}

	@Test
	public void username_isEmpty_when_update() {
		PersonTable person = dao.get(personValid.getId());
		person.setUsername("");
		boolean success = dao.update(person);
		assertFalse(success);
	}

	@Test
	public void password_isNull_when_update() {
		PersonTable person = dao.get(personValid.getId());
		person.setPassword(null);
		boolean success = dao.update(person);
		assertFalse(success);
	}

	@Test
	public void password_isEmpty_when_update() {
		PersonTable person = dao.get(personValid.getId());
		person.setPassword("");
		boolean success = dao.update(person);
		assertFalse(success);
	}

	@Test
	public void name_isNull_when_update() {
		PersonTable person = dao.get(personValid.getId());
		person.setName(null);
		boolean success = dao.update(person);
		assertFalse(success);
	}

	@Test
	public void name_isEmpty_when_update() {
		PersonTable person = dao.get(personValid.getId());
		person.setName("");
		boolean success = dao.update(person);
		assertFalse(success);
	}

	@Test
	public void phonenumber_isNull_when_update() {
		PersonTable person = dao.get(personValid.getId());
		person.setPhonenumber(null);
		boolean success = dao.update(person);
		assertFalse(success);
	}

	@Test
	public void phonenumber_isEmpty_when_update() {
		PersonTable person = dao.get(personValid.getId());
		person.setPhonenumber("");
		boolean success = dao.update(person);
		assertFalse(success);
	}

	@Test
	public void dateofbirth_isNull_when_update() {
		PersonTable person = dao.get(personValid.getId());
		person.setDateofbirth(null);
		boolean success = dao.update(person);
		assertFalse(success);
	}

	@Test
	public void avatar_isNull_when_update() {
		PersonTable person = dao.get(personValid.getId());
		person.setAvatar(null);
		boolean success = dao.update(person);
		assertFalse(success);
	}

	@Test
	public void avatar_isEmpty_when_update() {
		PersonTable person = dao.get(personValid.getId());
		person.setAvatar("");
		boolean success = dao.update(person);
		assertFalse(success);
	}
}
