package handler.authentication_hanlder;

import model.LoginModel;
import model.Person;

public interface IAuthenticationHandler {

	/*
	 * Create new person and storing information in database If create successfully
	 * call the setupUser(new person)
	 */
	boolean signup(Person person);

	/*
	 * Create a folder for storing new user's information like avatar
	 */
	void setupUser(Person person);

	/*
	 * Get information in LoginModel (username, password) Check if exist in database
	 */
	boolean login(LoginModel model);

	boolean logout(Object command);

	boolean existUsername(String username);

	boolean existPhonenumber(String phonenumber);
}