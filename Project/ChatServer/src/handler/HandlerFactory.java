package handler;

import handler.authentication_hanlder.AuthenticationHandlerImp;
import handler.manage_account_handler.ManageAccountHandlerImp;
import handler.manage_friend_handler.ManageFriendHandlerImp;
import handler.manage_room_handler.ManageRoomHandler;
import model.communication.CPackage;
import socket.IClient;
import data_access.SQLDAO;
import data_access.SQLDAOImp;
import data_access.person_access.PersonDAOImp;
import data_access.room_access.RoomDAOImp;

// Singleton
public class HandlerFactory {

	// Eager Initialization
	private static final HandlerFactory Instance = new HandlerFactory();

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Constructor
	// --------------------------------------------------------------------------------------------------------------------------------------------

	private HandlerFactory() {
	}

	public static HandlerFactory getInstance() {
		return Instance;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Functions
	// --------------------------------------------------------------------------------------------------------------------------------------------

	// Create handler
	public void createHanlder(IClient client, CPackage CPackage) {

		if (CPackage.isValid()) {

			Handler Handler = null;

			SQLDAO dao = new SQLDAOImp(); 
			
			// Choose handler for request

			switch (CPackage.getType()) {

			case AUTHENTICATION:
				Handler = new AuthenticationHandlerImp(client, new PersonDAOImp(dao));
				break;

			case ACCOUNT:
				Handler = new ManageAccountHandlerImp(client, new PersonDAOImp(dao));
				break;

			case FRIEND:
				Handler = new ManageFriendHandlerImp(client, new PersonDAOImp(dao));
				break;

			case ROOM:
				Handler = new ManageRoomHandler(client, new RoomDAOImp(dao));
				break;

			case MESSAGE:
//				Handler = new ManageMessageHandler(client);
				break;

			default:
				Handler = null;
				break;
			}

			if (Handler != null)
				Handler.handleRequest(CPackage.getRequest());
			else
				System.out.println("No handler created");
		}

	}
}
