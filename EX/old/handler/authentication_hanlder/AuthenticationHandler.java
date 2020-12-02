package handler.authentication_hanlder;

import model.sendmodel.LoginModel;
import model.sendmodel.Person;
import socket.Client;

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
	void setupUser(Person person);

	/*
	 * Get information in LoginModel (username, password) Check if exist in database
	 */
	boolean login(Client client, LoginModel model);

	boolean logout(Client client, Person person);
}