package handler.manage_room_handler;

import java.util.List;

import data_access.room_access.RoomDAO;
import handler.Handler;
import model.communication.CPackage;
import model.communication.Name;
import model.communication.Request;
import model.communication.Type;
import model.converter.RoomConverter;
import model.sendmodel.Person;
import model.sendmodel.Room;
import socket.Client;

public class RoomHandlerImp extends Handler implements RoomHandler {

	private RoomDAO dao;
	private RoomConverter converter;
	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Constructor
	// --------------------------------------------------------------------------------------------------------------------------------------------

	public RoomHandlerImp(Client client, RoomDAO dao, RoomConverter converter) {
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
				responseCommandType = new Request(Name.GET, get(client.getPerson().getId()));
				break;

			case UPDATE:
				responseCommandType = new Request(Name.UPDATE, update((Room) request.getContent()));
				break;

			case EXIT:
				responseCommandType = new Request(Name.EXIT, exit(client.getPerson(), (Room) request.getContent()));
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
			CPackage CPackage = new CPackage(Type.ROOM, request);
			send(CPackage);
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Get

	@Override
	public List<Room> get(int id_person) {
		List<Room> roomList = converter.convert(dao.getList(id_person));
		return roomList;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Add

	@Override
	public boolean add(Room room) {

		boolean success = false;
		if (room.isValid()) {
			success = dao.add(converter.revert(room));
		}

		return success;
	}

	@Override
	public boolean add(Room room, Person person) {
		boolean success = false;
		if (person.isValid()) {
			success = dao.add(room.getId(), person.getId());
		}

		return success;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Update

	@Override
	public boolean update(Room room) {
		boolean success = false;
		if (room.isValid()) {
			success = dao.update(converter.revert(room));
		}
		return success;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Remove

	@Override
	public boolean remove(Room room) {
		// TODO Auto-generated method stub
		return false;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Exit

	private void notify(Person person, Room room) {
		for (Person member : room.getMembers()) {
			// Get online member
			Client clientMember = authorizedClientList.get(member.getId());
			// Send message if not the sender
			if (member.getId() != person.getId() && clientMember != null) {
				Request request = new Request(Name.ADD, true);
				sendTo(clientMember, new CPackage(Type.ROOM, request));
			}
		}
	}

	@Override
	public boolean exit(Person person, Room room) {
		boolean success = false;
		if (room.isValid()) {
			// Update database
			Thread update = new Thread(() -> {
				dao.add(room.getId(), person.getId());
			});
			update.start();

			// Notify member in room
			Thread notifyMembers = new Thread(() -> {
				notify(person, room);
			});
			notifyMembers.start();

			success = true;
		}
		return success;
	}
}
