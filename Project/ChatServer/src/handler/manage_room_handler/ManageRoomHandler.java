package handler.manage_room_handler;

import java.util.ArrayList;
import java.util.List;

import communication_standard.CommandType;
import communication_standard.CommunicationPackage;
import communication_standard.manage_type.EManageType;
import communication_standard.manage_type.type.EManageRooms;
import handler.Handler;
import socket.IClient;

public class ManageRoomHandler extends Handler {

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Constructor
	// --------------------------------------------------------------------------------------------------------------------------------------------

	public ManageRoomHandler(IClient client) {
		super(client);
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Constructor
	// --------------------------------------------------------------------------------------------------------------------------------------------

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Handle Request
	@Override
	public void handleRequest(CommandType request) {
		if (request.isValid()) {
			EManageRooms command = (EManageRooms) request.getCommand();

			switch (command) {
			case VIEWROOMLIST:
				ViewRoomList();
				break;

			default:
				break;
			}
		}

	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------ Pack And Send
	@Override
	public void packAndSend(CommandType commandType) {
		if (commandType.isValid()) {
			CommunicationPackage CPackage = new CommunicationPackage(EManageType.MANAGEROOMS, commandType);
			send(CPackage);
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------ View Room List

	private void ViewRoomList() {

//		int id_person = client.getPerson().getId();
//
//		List<RoomTable> roomTables_List = RoomDAO.getInstance().getList(id_person);
//
//		List<RoomTable> viewRoom_List = new ArrayList<>();
//
//		if (Room_List.size() > 0) {
//			RoomConverter converter = new RoomConverter();
//
//			viewRoom_List = converter.Convert(Room_List);
//
//			List<Thread> threadList = new ArrayList<>();
//
//			// Create memeber for room
//			for (RoomTable viewRoom : viewRoom_List) {
//
////				Thread getMember = new Thread(() -> {
////					viewRoom.setMembers(
////							new PersonConverter().Convert(PersonDAO.getInstance().GetList(viewRoom.getId())));
////				});
////				getMember.start();
////
////				threadList.add(getMember);
//			}
//
//			for (Thread thread : threadList) {
//				try {
//					thread.join();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//
//		// Send back to client
//		CommandType commandType = new CommandType(EManageRooms.VIEWROOMLIST, viewRoom_List);
//		PackAndSend(commandType);
	}
}
