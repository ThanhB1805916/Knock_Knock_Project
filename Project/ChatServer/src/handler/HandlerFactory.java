package handler;

import communication_standard.CommunicationPackage;
import handler.authentication_hanlder.AuthenticationHandler;
import handler.manage_account_handler.ManageAccountHandler;
import handler.manage_room_handler.*;
import socket.IClient;
import data_access.SQLDAO;
import data_access.person_access.PersonDAO;


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

	// Create hanlder
	public void createHanlder(IClient client, CommunicationPackage CPackage) {

		if (CPackage.isValid()) {

			Handler Handler;

			//Choose handler for request
			
			switch (CPackage.getManageType()) {

			case AUTHENTICATION:
				Handler = new AuthenticationHandler(client, new PersonDAO(new SQLDAO()));
				break;

			case MANAGEACCOUNT:
				Handler = new ManageAccountHandler(client);
				break;
//
//			case MANAGEFRIENDS:
//				Handler = new ManageFriendsHandler(client);
//				break;
//
//			case MANAGEROOMS:
//				Handler = new ManageRoomsHandler(client);
//				break;
//
//			case MANAGEMESSAGES:
//				Handler = new ManageMessagesHandler(client);
//				break;

			default:
				Handler = null;
				break;
			}

			Handler.handleRequest(CPackage.getCommandType());
		}

	}
}
