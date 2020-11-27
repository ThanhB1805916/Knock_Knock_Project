package handler.manage_message_handler;
import java.util.List;

import data_access.message_access.MessageDAO;
import data_model.MessageTable;
import handler.Handler;
import model.communication.CPackage;
import model.communication.Name;
import model.communication.Request;
import model.communication.Type;
import model.converter.MessageConverter;
import model.sendmodel.Message;
import model.sendmodel.Person;
import socket.Client;

public class MessageHandlerImp extends Handler implements MessageHandler {

	private MessageDAO dao;
	private MessageConverter converter;
	
	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Constructor
	// --------------------------------------------------------------------------------------------------------------------------------------------

	public MessageHandlerImp(Client client, MessageDAO dao, MessageConverter converter) {
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
			case ADD:
				responseCommandType = new Request(Name.ADD, add((Message) request.getContent()));
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
			CPackage CPack = new CPackage(Type.MESSAGE, request);
			send(CPack);
		}
	}

	@Override
	public List<Message> get(int id_room) {
		List<Message> messageList = converter.convert(dao.getList(id_room));
		return messageList;
	}

	@Override
	public boolean add(Message message) {
		boolean success = false;

		if (message.isValid()) {
			// Add to database
			Thread save = new Thread(() -> {
				MessageTable msg = converter.revert(message);
				dao.add(msg);
			});
			save.start();

			// Send to other online clients
			Thread send = new Thread(() -> {
				send(message);
			});
			send.start();
			
			success = true;
		}

		return success;
	}

	@Override
	public void send(Message message) {
		// Get all memeber in a room
		List<Person> members = message.getRoom().getMembers();
		for (Person member : members) {
			// Get online memeber
			Client clientMember = authorizedClientList.get(member.getId());
			// Send message if not the sender
			if (member.getId() != message.getSender().getId() && clientMember != null) {
				Request request = new Request(Name.ADD, message);
				sendTo(clientMember, new CPackage(Type.MESSAGE, request));
			}
		}
	}

	@Override
	public boolean remove(Message message) {
		// TODO Auto-generated method stub
		return false;
	}

}
