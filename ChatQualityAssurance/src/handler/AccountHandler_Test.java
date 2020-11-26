package handler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import communicationTest.AccountHanlder;
import handler.manage_account_handler.AccountHandler;
import handler.manage_account_handler.AccountHandlerImp;
import model.sendmodel.Person;

public class AccountHandler_Test extends AuthenticationHanlder_Test{

	AccountHandler Achandler;
	
	@Before
	public void setUp()
	{
		super.setUp();
		Achandler = new AccountHandlerImp(clientValid, dao);
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------- Get
	
	@Test
	public void get()
	{
		boolean success = Auhandler.login(modelValid);
		Assert.assertTrue(success);
		Person person = Achandler.get();
		Assert.assertTrue(isEqual(person, personValid));
	}
	
	@Test
	public void notLogin_but_get()
	{
		Person person = Achandler.get();
		Assert.assertFalse(isEqual(person, personValid));
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------- Update
	
	@Test 
	public void update()
	{
		boolean success = Achandler.update(personValid);
		Assert.assertTrue(success);
	}
	
	@Test 
	public void id_isNegative_when_update()
	{
		personValid.setId(-1);
		boolean success = Achandler.update(personValid);
		Assert.assertFalse(success);
	}
	
	@Test 
	public void username_isNull_when_update()
	{
		personValid.setUsername(null);
		boolean success = Achandler.update(personValid);
		Assert.assertFalse(success);
	}
	
	@Test 
	public void username_isEmpty_when_update()
	{
		personValid.setUsername("");
		boolean success = Achandler.update(personValid);
		Assert.assertFalse(success);
	}
	
	@Test 
	public void password_isNull_when_update()
	{
		personValid.setPassword(null);
		boolean success = Achandler.update(personValid);
		Assert.assertFalse(success);
	}
	
	@Test 
	public void password_isEmpty_when_update()
	{
		personValid.setPassword("");
		boolean success = Achandler.update(personValid);
		Assert.assertFalse(success);
	}
	
	@Test 
	public void name_isNull_when_update()
	{
		personValid.setName(null);
		boolean success = Achandler.update(personValid);
		Assert.assertFalse(success);
	}
	
	@Test 
	public void name_isEmpty_when_update()
	{
		personValid.setName("");
		boolean success = Achandler.update(personValid);
		Assert.assertFalse(success);
	}
	
	@Test 
	public void dateofbirth_isNull_when_update()
	{
		personValid.setDateofbirth(null);
		boolean success = Achandler.update(personValid);
		Assert.assertFalse(success);
	}
	
	@Test 
	public void avatar_isNull_when_update()
	{
		personValid.setAvatar(null);
		boolean success = Achandler.update(personValid);
		Assert.assertFalse(success);
	}
}
