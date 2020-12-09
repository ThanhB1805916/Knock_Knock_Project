package model.converter;

import java.util.ArrayList;
import java.util.List;

import data_access.DAOFactory;
import data_model.MessageTable;
import model.sendmodel.FileInfo;
import model.sendmodel.Message;

public class MessageConverter implements Converter<MessageTable, Message> {

	private FileInfo createContent(MessageTable messageTable) {
		FileInfo content = null;
		if (messageTable != null && messageTable.isValid()) {
			// Message content
			content = new FileInfo();
			if (messageTable.getIsFile()) {
				// File name is message id
				content = new FileInfo(
						"sources/rooms/" + messageTable.getId_room() + "/messages/" + messageTable.getMessagecontent());
			}

			content.setName(messageTable.getMessagecontent());
		}
		return content;
	}

	@Override
	public Message convert(MessageTable messageTable) {
		Message message = null;
		if (messageTable != null && messageTable.isValid()) {
			FileInfo content = createContent(messageTable);

			message = new Message(messageTable.getId(), null, messageTable.getId_room(), content,
					messageTable.getIsFile(), messageTable.getSendtime());

			createSender(messageTable, message);
		}

		return message;
	}

	private void createSender(MessageTable messageTable, Message message) {
		// Create sender
		Thread createSender = new Thread(() -> {
			message.setSender(new PersonConverter()
					.convert(DAOFactory.getInstance().getPersonDAO().get(messageTable.getId_person())));
		});
		createSender.start();

		try {
			createSender.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Message> convert(List<MessageTable> messageTableList) {
		List<Message> messageList = new ArrayList<>();
		if (messageTableList != null) {
			for (MessageTable messageTable : messageTableList) {
				messageList.add(convert(messageTable));
			}
		}
		return messageList;
	}

	@Override
	public MessageTable revert(Message message) {
		MessageTable messageTable = null;
		if (message != null && message.isValid()) {
			// Write content to folder
			if (message.getIsFile()) {
				writeContent(message);
			}
			messageTable = new MessageTable(message.getId(), message.getRoomID(), message.getSender().getId(),
					message.getContent().getName(), message.getIsFile(), message.getSendTime());
		}
		return messageTable;
	}

	private void writeContent(Message message) {
		// Content stored in folder will have id__message's name format as name
		message.getContent().getFile("sources/rooms/" + message.getRoomID() + "/messages/");
	}

	@Override
	public List<MessageTable> revert(List<Message> messageList) {
		List<MessageTable> messageTableList = new ArrayList<>();
		if (messageList != null) {
			for (Message message : messageList) {
				messageTableList.add(revert(message));
			}
		}
		return messageTableList;
	}

}
