package handler.manage_message_handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import data_access.message_access.MessageDAO;
import handler.Handler;
import model.communication.CPackage;
import model.communication.Name;
import model.communication.Request;
import model.communication.Type;
import model.converter.MessageConverter;
import model.sendmodel.Message;
import model.sendmodel.Person;
import model.sendmodel.Room;
import socket.Client;

public class MessageHandlerImp extends Handler implements MessageHandler {

	private MessageDAO dao;

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Constructor
	// --------------------------------------------------------------------------------------------------------------------------------------------

	public MessageHandlerImp(MessageDAO dao) {
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
			case ADD:
				responseCommandType = new Request(Name.ADD, add(client, (Message) request.getContent()));
				break;

			case UPDATE:
//				responseCommandType = new Request(Name.UPDATE, update(client, (Person) request.getContent()));
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
			CPackage CPack = new CPackage(Type.MESSAGE, request);
			send(client, CPack);
		}
	}

	@Override
	public boolean add(Client client, Message message) {

		boolean success = false;

		List<Thread> task = new ArrayList<>();

//		// Add to database
//		Thread addTask = new Thread(() -> {
//			MessageTable msg = new MessageConverter().revert(message.getMessage());
//			dao.add(msg);
//		});
//		addTask.start();
//		task.add(addTask);
		System.out.println(message.getContent());
		// Send to other online clients
		Thread sendTask = new Thread(() -> {
			send(client, message);
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

		return success;
	}

	@Override
	public void send(Client client, Message message) {
		// Get all memeber in a room
		List<Person> members = message.getRoom().getMembers();
		for (Person member : members) {

			Client clientMember = authorizedClientList.get(member);
			// Send message if not the sender
//				if (person.equals(onlineClient.getValue())
			if (member.getId() != message.getSender().getId()) {
				Request request = new Request(Name.ADD, message);
				packAndSend(clientMember, request);
			}

		}
	}

	@Override
	public boolean remove(Message message) {
		// TODO Auto-generated method stub
		return false;
	}

}
