package handler;

import data_access.DAOFactory;
import handler.authentication_hanlder.AuthenticationHandlerImp;
import handler.manage_account_handler.AccountHandlerImp;
import handler.manage_friend_handler.FriendHandlerImp;
import handler.manage_message_handler.MessageHandlerImp;
import handler.manage_room_handler.RoomHandlerImp;
import model.communication.CPackage;
import model.converter.MessageConverter;
import model.converter.PersonConverter;
import model.converter.RoomConverter;
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

			DAOFactory factory = DAOFactory.getInstance();

			// Choose handler for request
			switch (CPackage.getType()) {

			case AUTHENTICATION:
				Handler = new AuthenticationHandlerImp(client, factory.getPersonDAO(), new PersonConverter());
				break;

			case ACCOUNT:
				Handler = new AccountHandlerImp(client, factory.getPersonDAO(), new PersonConverter());
				break;

			case FRIEND:
				Handler = new FriendHandlerImp(client, factory.getPersonDAO());
				break;

			case ROOM:
				Handler = new RoomHandlerImp(client, factory.getRoomDAO(), new RoomConverter());
				break;

			case MESSAGE:
				Handler = new MessageHandlerImp(client, factory.getMessageDAO(), new MessageConverter());
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
