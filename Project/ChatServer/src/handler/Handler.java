package handler;

import java.util.HashMap;

import model.communication.CPackage;
import model.communication.Request;
import model.sendmodel.Person;
import socket.IClient;
import socket.Server;

public abstract class Handler {

	protected IClient client;

	protected HashMap<IClient, Person> authorizedClient_List = Server.getInstance().getAuthorizedClient_List();

	public Handler(IClient client) {
		super();
		this.client = client;
	}

	public void send(CPackage CPackage) {
		if (CPackage.isValid()) {
			client.send(CPackage);
		}
	}

	public abstract void packAndSend(Request request);

	public abstract void handleRequest(Request request);
}
