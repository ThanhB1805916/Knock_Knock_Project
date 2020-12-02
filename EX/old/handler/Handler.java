package handler;

import java.util.HashMap;

import model.communication.CPackage;
import model.communication.Request;
import model.sendmodel.Person;
import socket.Client;
import socket.Server;

public abstract class Handler {

	protected HashMap<Person, Client> authorizedClientList = Server.getInstance().getAuthorizedClientList();

	public void send(Client client, CPackage CPackage) {
		if (CPackage.isValid()) {
			client.send(CPackage);
		}
	}
	
	public abstract void packAndSend(Client client, Request request);

	public abstract void handleRequest(Client client, Request request);
}
