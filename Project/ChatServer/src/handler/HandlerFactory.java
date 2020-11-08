package handler;

import data_access.DAOFactory;
import data_access.SQLDAO;
import data_access.SQLDAOImp;
import data_access.person_access.PersonDAO;
import data_access.room_access.RoomDAOImp;
import handler.authentication_hanlder.AuthenticationHandlerImp;
import handler.manage_account_handler.AccountHandlerImp;
import handler.manage_message_handler.MessageHandlerImp;
import handler.manage_room_handler.RoomHandlerImp;
import model.communication.CPackage;
import socket.Client;

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
	public void createHanlder(Client client, CPackage CPackage) {

		if (CPackage.isValid()) {

			Handler Handler = null;

			SQLDAO dao = new SQLDAOImp();

			// Choose handler for request

			switch (CPackage.getType()) {

			case AUTHENTICATION:
				Handler = new AuthenticationHandlerImp(client, DAOFactory.getPersonDAO());
				break;

			case ACCOUNT:
				Handler = new AccountHandlerImp(client, DAOFactory.getPersonDAO());
				break;

			case FRIEND:
//				Handler = new FriendHandlerImp(client, client, DAOFactory.getPersonDAO());
				break;

			case ROOM:
				Handler = new RoomHandlerImp(client, DAOFactory.getRoomDAO());
				break;

			case MESSAGE:
				Handler = new MessageHandlerImp(client, DAOFactory.getMessageDAO());
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
