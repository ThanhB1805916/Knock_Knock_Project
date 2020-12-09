package handler.manage_room_handler;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import model.sendmodel.RoomPersonModel;
import socket.Client;

public class RoomHandlerImp extends Handler implements RoomHandler {

	private RoomDAO dao;
	private RoomConverter converter;
	public static int roomSize;
	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Constructor
	// --------------------------------------------------------------------------------------------------------------------------------------------

	public RoomHandlerImp(Client client, RoomDAO dao, RoomConverter converter) {
		super(client);
		this.dao = dao;
		this.converter = converter;
		roomSize = dao.getRoomSize();
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
			case ADD:
				Room room = (Room) request.getContent();
				if(add(room))
					room.setId(roomSize);
				else
					room.setId(-1);
				responseCommandType = new Request(Name.ADD, room);
				break;
			case ADDMEMBER:
				RoomPersonModel model = (RoomPersonModel) request.getContent();
				add(model.getRoom(), model.getPerson());
				break;
			case UPDATE:
				// No need to response
				update((Room) request.getContent());
				break;
			case EXIT:
				model = (RoomPersonModel) request.getContent();
				exitRoom(model.getPerson(), model.getRoom());
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
		if (request != null && request.isValid()) {
			CPackage CPackage = new CPackage(Type.ROOM, request);
			send(CPackage);
		}
	}

	private void sendToMember(Room room, Request request) {
		for (Person member : room.getMembers()) {
			// Get online member
			Client clientMember = authorizedClientList.get(member.getId());
			// Send message if not the sender
			if (member.getId() != client.getPerson().getId() && clientMember != null) {
				sendTo(clientMember, new CPackage(Type.ROOM, request));
			}
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Get

	@Override
	public List<Room> get(int id_person) {
		List<Room> roomList = new ArrayList<>();
		if (id_person > 0)
			roomList = converter.convert(dao.getList(id_person));
		return roomList;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Add

	@Override
	public boolean add(Room room) {

		boolean success = false;
		room.setDateCreate(LocalDateTime.now());
		if (room.isValid()) {

			room.setId(++roomSize);
			// Add room
			Thread saveRoom = new Thread(() -> {
				if (dao.add(converter.revert(room))) {
					// Save to database
					Thread saveMembers = new Thread(() -> {
						for (Person memeber : room.getMembers()) {
							dao.add(room.getId(), memeber.getId());
						}
					});
					saveMembers.start();
				}
			});
			saveRoom.start();

			// Send to online member
			Thread sendToMembers = new Thread(() -> {
				sendToMember(room, new Request(Name.ADD, room));
			});

			sendToMembers.start();
			
			success = true;
		}

		return success;
	}

	@Override
	public boolean add(Room room, Person person) {
		boolean success = false;
		if (person.isValid()) {
			Thread update = new Thread(() -> {
				dao.add(room.getId(), person.getId());
			});
			update.start();

			Thread sendToMemebers = new Thread(() -> {
				sendToMember(room, new Request(Name.ADDMEMBER, new RoomPersonModel(room, person)));
			});
			sendToMemebers.start();

			success = true;
		}

		return success;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Update

	@Override
	public boolean update(Room room) {
		boolean success = false;
		if (room.isValid()) {
			Thread update = new Thread(() -> {
				dao.update(converter.revert(room));
			});
			update.start();

			Thread sendToMembers = new Thread(() -> {
				sendToMember(room, new Request(Name.UPDATE, room));
			});
			sendToMembers.start();
			success = true;
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

	@Override
	public boolean exitRoom(Person person, Room room) {
		boolean success = false;
		if (person.isValid() && room.isValid()) {
			// Update database
			Thread update = new Thread(() -> {
				dao.exitRoom(person.getId(), room.getId());
			});
			update.start();

			// Notify member in room
			Thread notifyMembers = new Thread(() -> {
				sendToMember(room, new Request(Name.EXIT, new RoomPersonModel(room, person)));
			});
			notifyMembers.start();

			success = true;
		}
		return success;
	}
}
