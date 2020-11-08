package handler.manage_room_handler;

import java.util.ArrayList;
import java.util.List;

import data_access.DAOFactory;
import data_access.room_access.RoomDAO;
import data_model.RoomTable;
import handler.Handler;
import model.communication.CPackage;
import model.communication.Name;
import model.communication.Request;
import model.communication.Type;
import model.converter.PersonConverter;
import model.converter.RoomConverter;
import model.sendmodel.Person;
import model.sendmodel.Room;
import socket.Client;

public class RoomHandlerImp extends Handler implements RoomHandler {

	private RoomDAO dao;

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Constructor
	// --------------------------------------------------------------------------------------------------------------------------------------------

	public RoomHandlerImp(Client client, RoomDAO dao) {
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

			case UPDATE:
//				responseCommandType = new CommandType(ECommandType.UPDATE, update((Person) request.getContent()));
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
	public List<Room> get() {
		int id_person = client.getPerson().getId();

		List<RoomTable> roomTableList = dao.getList(id_person);

		List<Room> roomList = new ArrayList<>();

		if (roomTableList.size() > 0) {

			RoomConverter converter = new RoomConverter();

			roomList = converter.convert(roomTableList);

			// Get rooms' members
			List<Thread> threadList = new ArrayList<>();

			// Create memeber for room
			for (Room room : roomList) {
				Thread build = new Thread(() -> {
					List<Person> members = new PersonConverter()
							.convert(DAOFactory.getPersonDAO().getListByID_Room(room.getId()));
					room.setMembers(members);
				});
				threadList.add(build);
				build.start();
			}

			for (Thread thread : threadList) {
				try {
					thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return roomList;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Add

	@Override
	public boolean add(Room room) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(Person person) {
		// TODO Auto-generated method stub
		return false;
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
	public boolean exit(Room room) {
		// TODO Auto-generated method stub
		return false;
	}
}
