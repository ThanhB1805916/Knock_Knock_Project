package handler.authentication_hanlder;

import model.sendmodel.LoginModel;
import model.sendmodel.Person;

public interface AuthenticationHandler {

	/*
	 * Create new person and storing information in database If create successfully
	 * call the setupUser(new person)
	 */
	boolean existUsername(String username);
	boolean existPhonenumber(String phonenumber);
	boolean signup(Person person);

	/*
	 * Create a folder for storing new user's information like avatar
	 */
	boolean setupUser(Person person);
	boolean setupUserUndo(Person person);
	boolean deleteFolder(String path);
	
	/*
	 * Get information in LoginModel (username, password) Check if exist in database
	 */
	boolean login(LoginModel model);

	boolean logout(Person person);
}