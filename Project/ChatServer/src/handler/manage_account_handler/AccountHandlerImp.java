package handler.manage_account_handler;

import data_access.person_access.PersonDAO;
import handler.Handler;
import model.communication.*;
import model.converter.PersonConverter;
import model.sendmodel.Person;
import socket.Client;

public class AccountHandlerImp extends Handler implements AccountHandler {

	private PersonDAO dao;
	private PersonConverter converter;

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Constructor
	// --------------------------------------------------------------------------------------------------------------------------------------------

	public AccountHandlerImp(Client client, PersonDAO dao, PersonConverter converter) {
		super(client);
		this.dao = dao;
		this.converter = converter;
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Handle Request
	@Override
	public void handleRequest(Request request) {
		if (request.isValid()) {
			Request responseCommandType = null;

			Name command = request.getName();
			switch (command) {
			case GET:
				responseCommandType = new Request(Name.GET, get());
				break;

			case UPDATE:
				responseCommandType = new Request(Name.UPDATE, update((Person) request.getContent()));
				break;

			default:
				responseCommandType = new Request(null, null);
				break;
			}

			packAndSend(responseCommandType);
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------ Pack And Send
	@Override
	public void packAndSend(Request request) {
		if (request.isValid()) {
			CPackage CPackage = new CPackage(Type.ACCOUNT, request);
			send(CPackage);
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Get 

	@Override
	public Person get() {
		// Get after login
		Person person = client.getPerson();
		return person;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Update

	@Override
	public boolean update(Person person) {
		boolean success = false;
		if (person.isValid()) {
			if (dao.update(converter.revert(person))) {
				authorizedClientList.remove(person.getId());
				authorizedClientList.put(person.getId(), client);
				success = true;
			}
		}
		return success;
	}

}
