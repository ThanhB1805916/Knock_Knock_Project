package handler.manage_message_handler;

import java.util.ArrayList;
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

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Constructor
	// --------------------------------------------------------------------------------------------------------------------------------------------

	public MessageHandlerImp(Client client, MessageDAO dao) {
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
	public void packAndSendTo(Client client, Request request) {
		if (request.isValid()) {
			CPackage CPack = new CPackage(Type.MESSAGE, request);
			client.send(CPack);
		}
	}

	@Override
	public List<Message> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Message message) {

		System.out.println(message.getContent().getName());
		boolean success = false;

		if (message.isValid()) {
			List<Thread> task = new ArrayList<>();

			// Add to database
			Thread addTask = new Thread(() -> {
				MessageTable msg = new MessageConverter().revert(message);
				dao.add(msg);
			});
			addTask.start();
			task.add(addTask);

			// Send to other online clients
			Thread sendTask = new Thread(() -> {
				send(message);
			});
			sendTask.start();
			task.add(sendTask);

			for (Thread thread : task) {
				try {
					thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

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
				packAndSendTo(clientMember, request);
			}

		}
	}

	@Override
	public boolean remove(Message message) {
		// TODO Auto-generated method stub
		return false;
	}

}
