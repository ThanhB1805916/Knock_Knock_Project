package handler;

import java.util.HashMap;

import communication_standard.CommandType;
import communication_standard.CommunicationPackage;
import communication_standard.model.Person;
import socket.IClient;
import socket.Server;

public abstract class Handler {

	protected IClient client;

	protected HashMap<IClient, Person> authorizedClient_List = Server.getInstance().getAuthorizedClient_List();

	public Handler(IClient client) {
		super();
		this.client = client;
	}

	public void send(CommunicationPackage CPackage) {
		if (CPackage.isValid()) {
			client.send(CPackage);
		}
	}

	public abstract void packAndSend(CommandType commandType);

	public abstract void handleRequest(CommandType request);
}
