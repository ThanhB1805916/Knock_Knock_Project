package model.converter;

import java.util.ArrayList;
import java.util.List;

import data_model.RoomTable;
import model.sendmodel.FileInfo;
import model.sendmodel.Room;

//Convert RoomTable to Room

public class RoomConverter implements Converter<RoomTable, Room> {

	@Override
	public Room convert(RoomTable roomTable) {
		FileInfo avatar = new FileInfo("sources/rooms/" + roomTable.getId() + "/avatars/" + roomTable.getAvatar());
		
		//Memeber list and message list will be create later
		Room Room = new Room(roomTable.getId(), roomTable.getName(), roomTable.getDatecreate(), avatar, null, null);
		return Room;
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
		room.getAvatar().getFile("sources/rooms/" + room.getId() + "/avatars/" + room.getAvatar().getName());
		RoomTable roomTable = new RoomTable(room.getId(), room.getName(), room.getDateCreate(), room.getAvatar().getName());
		return roomTable;
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
