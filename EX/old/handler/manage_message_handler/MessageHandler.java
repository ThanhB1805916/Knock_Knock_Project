package handler.manage_message_handler;

import model.sendmodel.Message;
import socket.Client;

public interface MessageHandler {
	
	boolean add(Client client, Message message);
	void send(Client client, Message message);
	/*
	 * Remove a message from a room
	 */
	boolean remove(Message message);
}