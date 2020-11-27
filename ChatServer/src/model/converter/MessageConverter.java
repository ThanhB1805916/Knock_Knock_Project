package model.converter;

import java.util.ArrayList;
import java.util.List;

import data_model.MessageTable;
import model.sendmodel.FileInfo;
import model.sendmodel.Message;

public class MessageConverter implements Converter<MessageTable, Message> {

	@Override
	public Message convert(MessageTable messageTable) {
		FileInfo content = new FileInfo();
		content.setName(messageTable.getMessagecontent());
		
		if(messageTable.getIsFile())
			content = new FileInfo();
		
		Message message = new Message(messageTable.getId(), null, null, content, messageTable.getIsFile(),
				messageTable.getSendtime());
		return message;
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
		MessageTable messageTable = new MessageTable(message.getId(), message.getRoom().getId(), message.getSender().getId(), message.getContent().getName(), message.getIsFile(), message.getSendTime());
		return messageTable;
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
