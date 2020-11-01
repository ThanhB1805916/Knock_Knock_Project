package sender;

import communication_standard.CommandType;
import communication_standard.CommunicationPackage;
import socket.IClient;

public abstract class SendHandler {

	protected IClient client;

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Constructor
	// --------------------------------------------------------------------------------------------------------------------------------------------

	public SendHandler(IClient client) {
		super();
		this.client = client;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Functions
	// --------------------------------------------------------------------------------------------------------------------------------------------

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// --------------------------------------------- Pack And Send Package To Server

	public abstract void packAndSend(CommandType commandType);

	public void send(CommunicationPackage CPackage) {
		if (CPackage.isValid()) {
			client.send(CPackage);
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// --------------------------------------------- UnPack Package

	public Object receiveAndUnPack(CommunicationPackage CPackage) {
		return CPackage.getCommandType().getContent();
	}
}
