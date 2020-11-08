package handler.manage_friend_handler;

import java.util.List;

import data_access.person_access.PersonDAO;
import handler.Handler;
import model.communication.CPackage;
import model.communication.Name;
import model.communication.Request;
import model.communication.Type;
import model.converter.PersonConverter;
import model.sendmodel.Person;
import socket.Client;

public class FriendHandlerImp extends Handler implements FriendHandler {

	private PersonDAO dao;

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Constructor
	// --------------------------------------------------------------------------------------------------------------------------------------------

	public FriendHandlerImp(Client client, PersonDAO dao) {
		super(client);
		this.dao = dao;
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
			case FIND:
				responseCommandType = new Request(Name.FIND, get());
				break;
			case ADD:
				responseCommandType = new Request(Name.ADD, get());
				break;
			case ACCEPT:
				responseCommandType = new Request(Name.ACCEPT, get());
				break;
			case REMOVE:
				responseCommandType = new Request(Name.REMOVE, get());
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
			CPackage CPackage = new CPackage(Type.FRIEND, request);
			send(CPackage);
		}
	}

	public void packAndSendTo(Client client, Request request) {
		if (request.isValid()) {
			CPackage CPackage = new CPackage(Type.FRIEND, request);
			client.send(CPackage);
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Get

	@Override
	public List<Person> get() {
		List<Person> friendList = new PersonConverter().convert(dao.getListByID_Friend(client.getPerson().getId()));
		return friendList;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Find

	@Override
	public Person find(String phonenumber) {
		Person person = new PersonConverter().convert(dao.getByPhonenumber(phonenumber));
		return person;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Add

	@Override
	public boolean add(Person person) {
		boolean success = false;

		if (person.isValid()) {
			// Send request if online
			Client clientFriend = authorizedClientList.get(person.getId());
			if (clientFriend != null) {
				packAndSendTo(clientFriend, new Request(Name.ADD, client.getPerson()));
			}

			// Add to database
			success = dao.addFriend(client.getPerson().getId(), person.getId());
		}

		return success;
	}

	@Override
	public boolean accept(Person person) {
		boolean success = false;

		if (person.isValid()) {
			// Send request if online
			Client clientFriend = authorizedClientList.get(person.getId());
			if (clientFriend != null) {
				packAndSendTo(clientFriend, new Request(Name.ACCEPT, client.getPerson()));
			}

			// Update database
			success = dao.accept(client.getPerson().getId(), person.getId());
		}

		return success;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Remove

	@Override
	public boolean remove(Person person) {
		boolean success = false;

		if (person.isValid()) {
			success = dao.removeFriend(client.getPerson().getId(), person.getId());
		}

		return success;
	}

}
