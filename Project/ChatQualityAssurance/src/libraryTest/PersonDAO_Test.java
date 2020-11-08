package libraryTest;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import data_access.DAOFactory;
import data_access.person_access.PersonDAO;
import data_model.PersonTable;

public class PersonDAO_Test {

	PersonDAO dao;
	PersonTable personvalid;

	@Before
	public void setUp() {
		dao = DAOFactory.getPersonDAO();
		personvalid = new PersonTable(1, "admin", "1234", "Administrator", true, "0000000000",
				LocalDate.of(2020, 10, 04), "sources/default/avatars/default_avatar.png");
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Get

	public boolean isEqual(PersonTable personA, PersonTable personB) {

		return personA.getId() == personB.getId() && personA.getUsername().equals(personB.getUsername())
				&& personA.getPassword().equals(personB.getPassword()) && personA.getName().equals(personB.getName())
				&& personA.getGender() == personB.getGender()
				&& personA.getDateofbirth().equals(personB.getDateofbirth())
				&& personA.getAvatar().equals(personB.getAvatar());
	}

	@Test
	public void getByID() {
		PersonTable person = dao.get(1);
		Assert.assertNotNull(person);
	}

	@Test
	public void ID_isNegative_when_getByID() {
		PersonTable person = dao.get(-1);
		Assert.assertNull(person);
	}

	@Test
	public void getByUsername() {
		PersonTable person = dao.getByUsername("admin");
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
		PersonTable personID = dao.get(1);
		PersonTable personUsername = dao.getByUsername("admin");
		Assert.assertTrue(isEqual(personID, personUsername));
	}

	@Test
	public void getByUserNameEqualsTrueValue() {
		PersonTable person = dao.getByUsername("admin");
		Assert.assertTrue(isEqual(person, personvalid));
	}

	@Test
	public void getByIDEqualsTrueValue() {
		PersonTable person = dao.get(1);
		Assert.assertTrue(isEqual(person, personvalid));
	}

	@Test
	public void getListById_Room_Test() {
		List<PersonTable> person = dao.getListByID_Room(1);
		Assert.assertNotNull(person);
	}
}
