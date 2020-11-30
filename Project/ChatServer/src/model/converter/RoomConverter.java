package model.converter;

import java.util.ArrayList;
import java.util.List;

import data_access.DAOFactory;
import data_model.RoomTable;
import model.sendmodel.FileInfo;
import model.sendmodel.Message;
import model.sendmodel.Room;

//Convert RoomTable to Room

public class RoomConverter implements Converter<RoomTable, Room> {

	@Override
	public Room convert(RoomTable roomTable) {
		FileInfo avatar = getAvatar(roomTable);

		Room room = new Room(roomTable.getId(), roomTable.getName(), roomTable.getDatecreate(), avatar, null, null);

		getMembersAndMessages(roomTable, room);

		return room;
	}

	private void getMembersAndMessages(RoomTable roomTable, Room room) {
		List<Thread> taskList = new ArrayList<>();
		// Get rooms' members
		Thread getMember = new Thread(() -> {
			room.setMembers(new PersonConverter()
					.convert(DAOFactory.getInstance().getPersonDAO().getListByID_Room(roomTable.getId())));
		});
		getMember.start();
		taskList.add(getMember);

		// Get room's messages
		Thread getMessage = new Thread(() -> {
			List<Message> messages = new MessageConverter()
					.convert(DAOFactory.getInstance().getMessageDAO().getList(room.getId()));
			room.setMessages(messages);
		});
		getMessage.start();
		taskList.add(getMessage);

		// Wait for all tasks are finished
		for (Thread thread : taskList) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private FileInfo getAvatar(RoomTable roomTable) {
		FileInfo avatar = new FileInfo("sources/rooms/" + roomTable.getId() + "/avatars/" + roomTable.getAvatar());
		if (avatar.isValid() == false)
			avatar = new FileInfo("sources/default/avatars/default_room_avatar.jpg");
		return avatar;
	}

	@Override
	public List<Room> convert(List<RoomTable> roomTableList) {
		List<Room> roomList = new ArrayList<>();
		for (RoomTable roomTable : roomTableList) {
			roomList.add(convert(roomTable));
		}
		return roomList;
	}

	@Override
	public RoomTable revert(Room room) {
		writeAvatar(room);
		RoomTable roomTable = new RoomTable(room.getId(), room.getName(), room.getDateCreate(),
				room.getAvatar().getName());
		return roomTable;
	}

	private void writeAvatar(Room room) {
		room.getAvatar().getFile("sources/rooms/" + room.getId() + "/avatars/" + room.getAvatar().getName());
	}

	@Override
	public List<RoomTable> revert(List<Room> roomList) {
		List<RoomTable> roomTableList = new ArrayList<>();
		for (Room room : roomList) {
			roomTableList.add(revert(room));
		}
		return roomTableList;
	}

}
