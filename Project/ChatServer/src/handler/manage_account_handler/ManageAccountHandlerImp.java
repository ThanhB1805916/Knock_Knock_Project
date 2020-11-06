package handler.manage_account_handler;

import data_access.person_access.PersonDAO;
import data_model.PersonTable;
import handler.Handler;
import model.communication.*;
import model.converter.PersonConverter;
import model.sendmodel.Person;
import socket.IClient;

public class ManageAccountHandlerImp extends Handler implements ManageAccountHandler {

	private PersonDAO dao;
	
	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Constructor
	// --------------------------------------------------------------------------------------------------------------------------------------------

	public ManageAccountHandlerImp(IClient client, PersonDAO dao) {
		super(client);
		this.dao = dao;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Functions
	// --------------------------------------------------------------------------------------------------------------------------------------------

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
	// ---------------------------------------------------------------- Get Account

	@Override
	public Person get() {
		// Get after login
		Person person = authorizedClient_List.get(client);
		return person;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Update

	@Override
	public boolean update(Person person) {
		boolean success = false;
		if (person.isValid()) {
			// Cap nhat thong tin nguoi dung
			PersonConverter converter = new PersonConverter();
			PersonTable personTable = converter.revert(person);

			if (dao.update(personTable)) {
				success = true;
			}
		}
		return success;
	}

}
