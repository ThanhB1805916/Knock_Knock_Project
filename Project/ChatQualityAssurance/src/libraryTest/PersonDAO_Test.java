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
	public void username_isNull_when_getByUsername()
	{
		PersonTable person = dao.getByUsername(null);
		Assert.assertNull(person);
	}
	
	@Test
	public void username_isEmpty_when_getByUsername()
	{
		PersonTable person = dao.getByUsername("");
		Assert.assertNull(person);
	}
	
	@Test
	public void getByUserNameEqualsGetByID() {
		PersonTable personID = dao.get(1);
		PersonTable personUsername = dao.getByUsername("admin");
		Assert.assertTrue(personID.equals(personUsername));
	}

	@Test
	public void getByUserNameEqualsTrueValue() {
		PersonTable person = dao.getByUsername("admin");
		Assert.assertTrue(person.equals(personvalid));
	}

	@Test
	public void getByIDEqualsTrueValue() {
		PersonTable person = dao.get(1);
		Assert.assertTrue(person.equals(personvalid));
	}

	@Test
	public void getListById_Room_Test() {
		List<PersonTable> person = dao.getListByID_Room(1);
		Assert.assertNotNull(person);
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Insert
	// --------------------------------------------------------------------------------------------------------------------------------------------

//	// Test Get by id
//	@Test
//	public void Insert_Test() {
//		
//		PersonTable person = new PersonTable(0, "User100", "12345", "User One", true, "111111111122", null, null);
//
//		boolean result = dao.add(person);
//		
//		System.out.println(result);
//		
//		// Exist person
//		assertEquals(true, result);
//	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Update
	// --------------------------------------------------------------------------------------------------------------------------------------------
//
//	// Test Update
//	@Test
//	public void UpdatePerson_Test() {
//
//		Date date = null;
//
////		try {
////			date = new SimpleDateFormat("dd-MM-yyyy").parse("17-10-2020");
////		} catch (ParseException e) {
////			 TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//
//		date = new Date(2020, 10, 22);
//
//		Person person = new Person(0, "User1", "12345", "User One", true, "1111111122", date);
//
//		boolean success = dao.Update(person);
//
//		// Exist person
//		assertEquals(true, success);
//	}
}
