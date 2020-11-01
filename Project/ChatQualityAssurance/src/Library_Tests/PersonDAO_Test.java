package Library_Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import data_access.SQLDAO;
import data_access.person_access.IPersonDAO;
import data_access.person_access.PersonDAO;
import data_model.PersonTable;

public class PersonDAO_Test {

	IPersonDAO dao = new PersonDAO(new SQLDAO());

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Get
	// --------------------------------------------------------------------------------------------------------------------------------------------

	// Test Get by id
	@Test
	public void GetId_Test() {
		PersonTable person = dao.get(1);

		// Exist person
		assertNotNull(person);
	}

	// Test Get by personaccount, personpassword
	@Test
	public void GetAccountPassword_Test() {
		PersonTable person = dao.get("Admin", "1234");

		// Exist person
		assertNotNull(person);
	}

	// Test Get list by id_room
	@Test
	public void GetListById_Room_Test() {
		List<PersonTable> person = dao.getList(1);

		// Exist person
		assertNotNull(person);
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
