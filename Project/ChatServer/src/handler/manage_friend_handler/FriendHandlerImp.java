package handler.manage_friend_handler;

import java.util.List;

import data_access.person_access.PersonDAO;
import handler.Handler;
import model.communication.CPackage;
import model.communication.Name;
import model.communication.Request;
import model.communication.Type;
import model.converter.PersonConverter;
import model.sendmodel.ConfirmFriendModel;
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
				responseCommandType = new Request(Name.GET, get(client.getPerson().getId()));
				break;
			case FIND:
				responseCommandType = new Request(Name.FIND, find(request.getContent().toString()));
				break;
			case ADD:
				responseCommandType = new Request(Name.ADD, add((int)request.getContent()));
				break;
			case CONFIRM:
				confirm((ConfirmFriendModel)request.getContent());
				responseCommandType = new Request(null, null);
				break;
			case REMOVE:
				remove((int)request.getContent());
				responseCommandType = new Request(null, null);
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
	public List<Person> get(int id) {
		List<Person> friendList = new PersonConverter().convert(dao.getListByID_Friend(id));
		return friendList;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Find

	@Override
	public Person find(String phonenumber) {
		Person person = null;
		if(phonenumber != null && phonenumber.length() == 10)
		{
			person = new PersonConverter().convert(dao.getByPhonenumber(phonenumber));
		}
		return person;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Add

	@Override
	public boolean add(int id) {
		boolean success = false;
		if (id > 0) {
			// Send request if online
			Client clientFriend = authorizedClientList.get(id);
			if (id != client.getPerson().getId() && clientFriend != null) {
				ConfirmFriendModel model = new ConfirmFriendModel(client.getPerson(), clientFriend.getPerson());
				packAndSendTo(clientFriend, new Request(Name.CONFIRM, model));
				success = true;
			}
		}

		return success;
	}

	@Override
	public boolean confirm(ConfirmFriendModel confirmFriend) {
		boolean success = false;
		if (confirmFriend.isValid()) {
			// Send request if online
			Client clientFriend = authorizedClientList.get(confirmFriend.getSender().getId());
			if (confirmFriend.getSender().getId() != client.getPerson().getId() && clientFriend != null) {
				
				// Update database if accept friend request
				if(confirmFriend.getIsFriend())
				{
					Thread update = new Thread(()->
					{
						dao.addFriend(confirmFriend.getSender().getId(), confirmFriend.getSender().getId());
					});
					update.start();
				}
				
				//Notify sender
				Thread notify = new Thread(()->
				{
					packAndSendTo(clientFriend, new Request(Name.CONFIRM, confirmFriend));
				});
				notify.start();
				success = true;
			}
		}

		return success;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Remove

	@Override
	public boolean remove(int id) {
		boolean success = false;

		if (id > 0) {
			success = dao.removeFriend(client.getPerson().getId(), id);
			// Send request if online
			Client friend = authorizedClientList.get(id);
			if(friend != null)
			{
				sendTo(friend, new CPackage(Type.FRIEND, new Request(Name.REMOVE, client.getPerson().getId())));
			}
		}

		return success;
	}
}
