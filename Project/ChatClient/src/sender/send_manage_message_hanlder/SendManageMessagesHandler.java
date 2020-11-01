package sender.send_manage_message_hanlder;

import communication_standard.CommandType;
import communication_standard.CommunicationPackage;
import communication_standard.manage_type.EManageType;
import sender.SendHandler;
import socket.Client;

public class SendManageMessagesHandler extends SendHandler {

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Constructor
	// --------------------------------------------------------------------------------------------------------------------------------------------

	public SendManageMessagesHandler(Client client) {
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
			CommunicationPackage CPackage = new CommunicationPackage(EManageType.MANAGEMESSAGES, commandType);
			send(CPackage);
		}
	}

}
