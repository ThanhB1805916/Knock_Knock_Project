package handler.manage_message_handler;

import java.util.List;
import model.sendmodel.Message;

public interface MessageHandler {

	List<Message> get(int id_room);
	
	boolean add(Message message);
	/*
	 * Remove a message from a room
	 */
	boolean remove(Message message);
}