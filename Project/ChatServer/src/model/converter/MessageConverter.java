package model.converter;

import java.util.ArrayList;
import java.util.List;

import data_access.DAOFactory;
import data_model.MessageTable;
import model.sendmodel.FileInfo;
import model.sendmodel.Message;

public class MessageConverter implements Converter<MessageTable, Message> {

	private FileInfo createContent(MessageTable messageTable) {
		// Message content
		FileInfo content = new FileInfo();

		if (messageTable.getIsFile()) {
			// File name is message id
			content = new FileInfo("sources/rooms/" + messageTable.getId_room() + "/messages/" + messageTable.getId()
					+ "__" + messageTable.getMessagecontent());
		}

		content.setName(messageTable.getMessagecontent());

		return content;
	}

	@Override
	public Message convert(MessageTable messageTable) {

		FileInfo content = createContent(messageTable);

		Message message = new Message(messageTable.getId(), null, null, content, messageTable.getIsFile(),
				messageTable.getSendtime());

		createRoomAndSender(messageTable, message);

		return message;
	}

	private void createRoomAndSender(MessageTable messageTable, Message message) {
		List<Thread> taskList = new ArrayList<>();
		// Create sender
		Thread createSender = new Thread(() -> {
			message.setSender(new PersonConverter()
					.convert(DAOFactory.getInstance().getPersonDAO().get(messageTable.getId_person())));
		});
		createSender.start();
		taskList.add(createSender);

		// Create room
		Thread createRoom = new Thread(() -> {
			message.setRoom(
					new RoomConverter().convert(DAOFactory.getInstance().getRoomDAO().get(messageTable.getId_room())));
		});
		createRoom.start();
		taskList.add(createRoom);

		for (Thread thread : taskList) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Message> convert(List<MessageTable> messageTableList) {
		List<Message> messageList = new ArrayList<>();
		for (MessageTable messageTable : messageTableList) {
			messageList.add(convert(messageTable));
		}
		return messageList;
	}

	@Override
	public MessageTable revert(Message message) {

		// Write content to folder
		if (message.getIsFile()) {
			writeContent(message);
		}
		MessageTable messageTable = new MessageTable(message.getId(), message.getRoom().getId(),
				message.getSender().getId(), message.getContent().getName(), message.getIsFile(),
				message.getSendTime());
		return messageTable;
	}

	private void writeContent(Message message) {
		// Content stored in folder will have id__message's name format as name
		message.getContent().setName(Integer.toString(message.getId()) + "__" + message.getContent().getName());
		message.getContent().getFile("sources/rooms/" + message.getRoom().getId() + "/messages/");
	}

	@Override
	public List<MessageTable> revert(List<Message> messageList) {
		List<MessageTable> messageTableList = new ArrayList<>();
		for (Message message : messageList) {
			messageTableList.add(revert(message));
		}
		return messageTableList;
	}

}
