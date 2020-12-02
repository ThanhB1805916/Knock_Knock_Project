package handler.manage_account_handler;

import model.sendmodel.Person;
import socket.Client;

public interface AccountHandler {

	/*
	 * Get person after login the system
	 */
	Person get(Client client);

	boolean update(Client client, Person person);
}
