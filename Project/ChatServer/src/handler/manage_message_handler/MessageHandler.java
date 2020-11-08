package handler.manage_message_handler;

import model.communication.Request;
import model.sendmodel.Message;
import socket.Client;

public interface MessageHandler {

	boolean add(Message message);

	void send(Message message);

	void packAndSendTo(Client client, Request request);

	/*
	 * Remove a message from a room
	 */
	boolean remove(Message message);
}