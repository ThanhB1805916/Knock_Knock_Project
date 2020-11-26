package libraryTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import connection.ConnectionString;
import data_access.DAOFactory;
import data_access.message_access.MessageDAO;
import data_model.MessageTable;

public class MessageDAO_Test {

	MessageDAO dao;

	int id_room = 1;
	
	MessageTable messageValid;

	List<MessageTable> messageListValid;

	@Before
	public void setUp() {
		dao = DAOFactory.getInstance()
				.setSQLDAO(new ConnectionString(
						"jdbc:mysql://localhost:3306/Knock_Knock_Project_Test?noAccessToProcedureBodies=true",
						"stdUser", "std1234"))
				.getMessageDAO();

		messageValid = new MessageTable(1, 1, 1, "Hello", false, LocalDateTime.of(2020, 9, 11, 9, 01, 00));

		messageListValid = new ArrayList<MessageTable>();

		messageListValid.add(messageValid);
		messageListValid.add(new MessageTable(2, 1, 2, "Hallo", false, LocalDateTime.of(2020, 9, 11, 9, 02, 00)));
		messageListValid.add(new MessageTable(3, 1, 3, "Hi", false, LocalDateTime.of(2020, 9, 11, 9, 03, 00)));
	}

	public boolean isEqual(MessageTable messageA, MessageTable messageB) {
		return messageA.getId() == messageB.getId() && messageA.getId_room() == messageB.getId_room()
				&& messageA.getId_person() == messageB.getId_person()
				&& messageA.getMessagecontent().equals(messageB.getMessagecontent())
				&& messageA.getIsFile() == messageB.getIsFile()
				&& messageA.getSendtime().equals(messageB.getSendtime());
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Get ID

	@Test
	public void getByID() {
		MessageTable message = dao.get(messageValid.getId());
		Assert.assertNotNull(message);
	}

	@Test
	public void id_isNegative_when_getByID() {
		MessageTable message = dao.get(-1);
		Assert.assertNull(message);
	}

	@Test
	public void getByIDEqualsTrueValue() {
		MessageTable message = dao.get(messageValid.getId());
		Assert.assertTrue(isEqual(message, messageValid));
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Get List ID_Room
	
	@Test
	public void getList()
	{
		List<MessageTable> messageList = dao.getList(id_room);
		Assert.assertNotNull(messageList);
	}
	
	@Test
	public void id_room_isNegative_when_getList()
	{
		List<MessageTable> messageList = dao.getList(-1);
		Assert.assertNull(messageList);
	}
	
	@Test
	public void getListEqualTrueValue()
	{
		List<MessageTable> messageList = dao.getList(id_room);
		int i = 0;
		for (MessageTable messageTable : messageList) {
			Assert.assertTrue(isEqual(messageTable, messageListValid.get(i++)));
		}
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Update

	@Test
	public void update() {
		MessageTable message = dao.get(messageValid.getId());
		boolean success = dao.update(message);
		Assert.assertTrue(success);
	}

	@Test
	public void id_isNegative_when_update() {
		MessageTable message = dao.get(messageValid.getId());
		message.setId(-1);
		boolean success = dao.update(message);
		Assert.assertFalse(success);
	}

	@Test
	public void id_person_isNegative_when_update() {
		MessageTable message = dao.get(messageValid.getId());
		message.setId_person(-1);
		boolean success = dao.update(message);
		Assert.assertFalse(success);
	}

	@Test
	public void id_room_isNegative_when_update() {
		MessageTable message = dao.get(messageValid.getId());
		message.setId_room(-1);
		boolean success = dao.update(message);
		Assert.assertFalse(success);
	}

	@Test
	public void messagecontent_isNull_when_update() {
		MessageTable message = dao.get(messageValid.getId());
		message.setMessagecontent(null);
		boolean success = dao.update(message);
		Assert.assertFalse(success);
	}

	@Test
	public void messagecontent_isEmpty_when_update() {
		MessageTable message = dao.get(messageValid.getId());
		message.setMessagecontent("");
		boolean success = dao.update(message);
		Assert.assertFalse(success);
	}

	@Test
	public void sendTime_isNull_when_update() {
		MessageTable message = dao.get(messageValid.getId());
		message.setSendtime(null);
		boolean success = dao.update(message);
		Assert.assertFalse(success);
	}
}
