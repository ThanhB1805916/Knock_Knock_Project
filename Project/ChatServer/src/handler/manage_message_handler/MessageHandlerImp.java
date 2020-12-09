package handler.manage_message_handler;

import java.time.LocalDateTime;
import java.util.List;

import data_access.DAOFactory;
import data_access.message_access.MessageDAO;
import handler.Handler;
import model.communication.CPackage;
import model.communication.Name;
import model.communication.Request;
import model.communication.Type;
import model.converter.MessageConverter;
import model.converter.PersonConverter;
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

			case GET:
				responseCommandType = new Request(Name.GET, get((int) request.getContent()));
				break;

			case ADD:
				responseCommandType = new Request(null, null);
				add((Message) request.getContent());
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

	// Send message to all members in room
	private void send(Message message) {
		// Get all member in a room
		List<Person> members = new PersonConverter()
				.convert(DAOFactory.getInstance().getPersonDAO().getListByID_Room(message.getRoomID()));

		for (Person member : members) {
			// Get online member
			Client clientMember = authorizedClientList.get(member.getId());

			// Send message if not the sender and members are online
			if (member.getId() != message.getSender().getId() && clientMember != null) {
				Request request = new Request(Name.ADD, message);
				sendTo(clientMember, new CPackage(Type.MESSAGE, request));
			}
		}
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Get

	@Override
	public List<Message> get(int id_room) {
		List<Message> messageList = converter.convert(dao.getList(id_room));
		return messageList;
	}
	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Add

	@Override
	public boolean add(Message message) {
		boolean success = false;

		// Create default message sendtime
		if (message.getSendTime() == null)
			message.setSendTime(LocalDateTime.now());

		if (message.isValid()) {
			// Add to database
			Thread save = new Thread(() -> {
				dao.add(converter.revert(message));
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
	public boolean remove(Message message) {
		// TODO Auto-generated method stub
		return false;
	}

}
