package handler.manage_account_handler;

import data_access.person_access.PersonDAO;
import data_model.PersonTable;
import handler.Handler;
import model.communication.*;
import model.converter.PersonConverter;
import model.sendmodel.Person;
import socket.Client;

public class AccountHandlerImp extends Handler implements AccountHandler {

	private PersonDAO dao;

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Constructor
	// --------------------------------------------------------------------------------------------------------------------------------------------

	public AccountHandlerImp(PersonDAO dao) {
		this.dao = dao;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Functions
	// --------------------------------------------------------------------------------------------------------------------------------------------

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Handle Request
	@Override
	public void handleRequest(Client client, Request request) {
		if (request.isValid()) {
			Request responseCommandType = null;

			Name command = request.getName();
			switch (command) {
			case GET:
				responseCommandType = new Request(Name.GET, get(client));
				break;

			case UPDATE:
				responseCommandType = new Request(Name.UPDATE, update(client, (Person) request.getContent()));
				break;

			default:
				responseCommandType = new Request(null, null);
				break;
			}

			packAndSend(client, responseCommandType);
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------ Pack And Send
	@Override
	public void packAndSend(Client client, Request request) {
		if (request.isValid()) {
			CPackage CPackage = new CPackage(Type.ACCOUNT, request);
			send(client, CPackage);
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Get Account

	@Override
	public Person get(Client client) {
		// Get after login
		Person person = client.getPerson();
		return person;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Update

	@Override
	public boolean update(Client client, Person person) {
		boolean success = false;
		if (person.isValid()) {

			PersonConverter converter = new PersonConverter();
			PersonTable personTable = converter.revert(person);

			if (dao.update(personTable)) {
				authorizedClientList.remove(person);
				authorizedClientList.put(person, client);
				success = true;
			}
		}
		return success;
	}

}
