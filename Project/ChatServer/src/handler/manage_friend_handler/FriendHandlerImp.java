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

	@Override
	public List<Person> get() {
		List<Person> friendList = new PersonConverter().convert(dao.getListByID_Friend(client.getPerson().getId()));
		return friendList;
	}

	@Override
	public Person find(String phonenumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Person person) {
		// TODO Auto-generated method stub
		return false;
	}

}
