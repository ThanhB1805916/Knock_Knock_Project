package handler.manage_account_handler;

import model.sendmodel.Person;

public interface AccountHandler {

	/*
	 * Get person after login the system
	 */
	Person get();

	boolean update(Person person);
}
