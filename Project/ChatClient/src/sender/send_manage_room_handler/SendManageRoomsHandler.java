package sender.send_manage_room_handler;

import java.util.List;

import communication_standard.CommandType;
import communication_standard.CommunicationPackage;
import communication_standard.manage_type.EManageType;
import sender.SendHandler;
import socket.Client;

public class SendManageRoomsHandler extends SendHandler {

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Constructor
	// --------------------------------------------------------------------------------------------------------------------------------------------

	public SendManageRoomsHandler(Client client) {
		super(client);
		// TODO Auto-generated constructor stub
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Functions
	// --------------------------------------------------------------------------------------------------------------------------------------------

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------- Send Package To Server

	@Override
	public void packAndSend(CommandType commandType) {
		if (commandType.isValid()) {
			CommunicationPackage CPackage = new CommunicationPackage(EManageType.MANAGEROOMS, commandType);
			send(CPackage);
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------- View Room List

	public void ViewRoomList() {

//		CommandType commandType = new CommandType(EManageRooms.VIEWROOMLIST, client.getUser().getId());
//
//		PackAndSend(commandType);
//
//		Object obj = ReceiveAndUnPack(client.Receive());
//
//		@SuppressWarnings("unchecked")
//		List<Room> rooms = (List<Room>) obj;
//
//		client.getUser().setRooms(rooms);
	}

}
