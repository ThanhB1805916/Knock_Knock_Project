package handler;

import java.util.HashMap;

import model.communication.CPackage;
import model.communication.Request;
import socket.Client;
import socket.Server;

public abstract class Handler {

	protected Client client;

	protected HashMap<Integer, Client> authorizedClientList = Server.getInstance().getAuthorizedClientList();

	public Handler(Client client) {
		this.client = client;
	}

	public void send(CPackage CPackage) {
		if (CPackage != null && CPackage.isValid()) {
			client.send(CPackage);
		}
	}

	public void sendTo(Client client, CPackage CPackage)
	{
		if(CPackage.isValid())
		{
			client.send(CPackage);
		}
	}
	
	public abstract void packAndSend(Request request);

	public abstract void handleRequest(Request request);
}
