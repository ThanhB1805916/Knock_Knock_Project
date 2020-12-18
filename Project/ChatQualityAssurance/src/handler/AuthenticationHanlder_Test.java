package handler;

import java.io.File;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import connection.ConnectionString;
import data_access.DAOFactory;
import data_access.person_access.PersonDAO;
import handler.authentication_hanlder.AuthenticationHandler;
import handler.authentication_hanlder.AuthenticationHandlerImp;
import model.converter.PersonConverter;
import model.sendmodel.FileInfo;
import model.sendmodel.LoginModel;
import model.sendmodel.Person;
import socket.Client;
import socket.ClientImp;

public class AuthenticationHanlder_Test {

	PersonDAO dao;
	Person personValid;
	Client clientValid;
	LoginModel modelValid;
	AuthenticationHandler Auhandler;

	@Before
	public void setUp() {
		dao = DAOFactory.getInstance()
				.setSQLDAO(new ConnectionString(
						"jdbc:mysql://localhost:3306/Knock_Knock_Project_Test?noAccessToProcedureBodies=true",
						"stdUser", "std1234"))
				.getPersonDAO();

		clientValid = new ClientImp();
		Auhandler = new AuthenticationHandlerImp(clientValid, dao, new PersonConverter());
		personValid = new Person(1, "admin", "1234", "Administrator", true, "0000000000", LocalDate.of(2020, 10, 04),
				new FileInfo("sources/default/avatars/default_avatar.png"));
		modelValid = new LoginModel(personValid.getUsername(), personValid.getPassword());
	}

	public boolean isEqual(Person personA, Person personB) {

		try {
			return personA.getId() == personB.getId() && personA.getUsername().equals(personB.getUsername())
					&& personA.getPassword().equals(personB.getPassword())
					&& personA.getName().equals(personB.getName()) && personA.getMale() == personB.getMale()
					&& personA.getDateofbirth().equals(personB.getDateofbirth())
//					&& personA.getAvatar().equals(personB.getAvatar())
			;
		} catch (Exception e) {
			return false;
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------- Exist Username

	@Test
	public void existUsername() {
		boolean success = Auhandler.existUsername(personValid.getUsername());
		Assert.assertTrue(success);
	}

	@Test
	public void username_isNull_when_existUsername() {
		boolean success = Auhandler.existUsername(null);
		Assert.assertFalse(success);
	}

	@Test
	public void username_isEmpty_when_existUsername() {
		boolean success = Auhandler.existUsername("");
		Assert.assertFalse(success);
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------- Exist Phonenumber

	@Test
	public void existPhonenumber() {
		boolean success = Auhandler.existPhonenumber(personValid.getPhonenumber());
		Assert.assertTrue(success);
	}

	@Test
	public void phonenumber_isNull_when_existPhonenumber() {
		boolean success = Auhandler.existPhonenumber(null);
		Assert.assertFalse(success);
	}

	@Test
	public void phonenumber_isEmpty_when_existPhonenumber() {
		boolean success = Auhandler.existPhonenumber("");
		Assert.assertFalse(success);
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------- SignUp User

	@Test
	public void username_isNull_when_singup() {
		personValid.setUsername(null);
		boolean success = Auhandler.signup(personValid);
		Assert.assertFalse(success);
	}

	@Test
	public void username_isEmpty_when_singup() {
		personValid.setUsername("");
		boolean success = Auhandler.signup(personValid);
		Assert.assertFalse(success);
	}

	@Test
	public void username_hasWhiteSpace_when_singup() {
		personValid.setUsername(" User name");
		boolean success = Auhandler.signup(personValid);
		Assert.assertFalse(success);
	}

	@Test
	public void password_isNull_when_singup() {
		personValid.setPassword(null);
		boolean success = Auhandler.signup(personValid);
		Assert.assertFalse(success);
	}

	@Test
	public void password_isEmpty_when_singup() {
		personValid.setPassword("");
		boolean success = Auhandler.signup(personValid);
		Assert.assertFalse(success);
	}

	@Test
	public void phonenumber_isNull_when_singup() {
		personValid.setPhonenumber(null);
		boolean success = Auhandler.signup(personValid);
		Assert.assertFalse(success);
	}

	@Test
	public void phonenumber_isEmpty_when_singup() {
		personValid.setPhonenumber("");
		boolean success = Auhandler.signup(personValid);
		Assert.assertFalse(success);
	}

	@Test
	public void userAndPhonenumberExist_when_signup() {
		boolean success = Auhandler.signup(personValid);
		Assert.assertFalse(success);
	}

	@Test
	public void userExist_when_signup() {
		personValid.setPhonenumber("999999999");
		boolean success = Auhandler.signup(personValid);
		Assert.assertFalse(success);
	}

	@Test
	public void phonenumberExist_when_signup() {
		personValid.setUsername("user99");
		;
		boolean success = Auhandler.signup(personValid);
		Assert.assertFalse(success);
	}

	@Test
	public void avatar_isNull_when_singup() {
		personValid.setUsername("user200");
		personValid.setAvatar(null);
		boolean success = Auhandler.signup(personValid);
		Assert.assertFalse(success);
	}

	@Test
	public void dateofbirth_isNull_when_singup() {
		personValid.setUsername("user200");
		personValid.setAvatar(null);
		boolean success = Auhandler.signup(personValid);
		Assert.assertFalse(success);
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------- SetUp User
	@Test
	public void setupUser() {
		Auhandler.setupUser(personValid);
		String folderName = personValid.getUsername();
		// Create folder for user and avatar
		File userDir = new File("sources/users/" + folderName + "/avatars");
		boolean success = userDir.exists();
		Assert.assertTrue(success);
		if (success) {
			Assert.assertTrue(Auhandler.setupUserUndo(personValid));
		}
	}

	@Test
	public void username_isNull_when_setupUser() {
		personValid.setUsername(null);
		Auhandler.setupUser(personValid);
		String folderName = personValid.getUsername();
		// Create folder for user and avatar
		File userDir = new File("sources/users/" + folderName + "/avatars");
		boolean success = userDir.exists();
		Assert.assertFalse(success);
	}

	@Test
	public void username_isEmpty_when_setupUser() {
		personValid.setUsername("");
		Auhandler.setupUser(personValid);
		String folderName = personValid.getUsername();
		// Create folder for user and avatar
		File userDir = new File("sources/users/" + folderName + "/avatars");
		boolean success = userDir.exists();
		Assert.assertFalse(success);
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------- Login

	@Test
	public void login() {
		boolean success = Auhandler.login(modelValid);
		Assert.assertTrue(success);
	}

	@Test
	public void username_isNull_when_login() {
		modelValid.setUsername(null);
		boolean success = Auhandler.login(modelValid);
		Assert.assertFalse(success);
	}

	@Test
	public void username_isEmpty_when_login() {
		modelValid.setUsername("");
		boolean success = Auhandler.login(modelValid);
		Assert.assertFalse(success);
	}

	@Test
	public void password_isNull_when_login() {
		modelValid.setPassword(null);
		boolean success = Auhandler.login(modelValid);
		Assert.assertFalse(success);
	}

	@Test
	public void password_isEmpty_when_login() {
		modelValid.setPassword("");
		boolean success = Auhandler.login(modelValid);
		Assert.assertFalse(success);
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------- Logout

	@Test
	public void logout() {
		boolean success = false;
		if (Auhandler.login(modelValid)) {
			success = Auhandler.logout(personValid);
		}

		Assert.assertTrue(success);
	}

	@Test(expected = NullPointerException.class)
	public void notLogin_but_logout() {
		Auhandler.logout(personValid);
	}
}
