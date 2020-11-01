package communication_standard.converter;

import java.util.List;

import communication_standard.model.Message;
import data_model.MessageTable;

//This class use to convert Person to ViewPerson and revert back
public class MessageConverter implements IConverter<MessageTable, Message> {

	@Override
	public Message convert(MessageTable T) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> convert(List<MessageTable> Ts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageTable revert(Message V) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessageTable> revert(List<Message> Vs) {
		// TODO Auto-generated method stub
		return null;
	}

}
