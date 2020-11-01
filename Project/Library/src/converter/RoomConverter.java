package converter;

import java.util.ArrayList;
import java.util.List;

import data_model.RoomTable;
import model.Room;

//Convert RoomTable to Room

public class RoomConverter implements IConverter<RoomTable, Room> {

	@Override
	public Room convert(RoomTable roomTable) {
		// Ctor from table in database

		Room Room = new Room(roomTable.getId(), roomTable.getName(), roomTable.getDatecreate(), null, null, null);

		return Room;
	}

	@Override
	public List<Room> convert(List<RoomTable> roomTable_List) {

		List<Room> Room_List = new ArrayList<>();

		for (RoomTable roomTable : roomTable_List) {
			Room_List.add(convert(roomTable));
		}

		return Room_List;
	}

	@Override
	public RoomTable revert(Room room) {

		RoomTable roomTable = new RoomTable(room.getId(), room.getName(), room.getDateCreate(), null);

		return roomTable;
	}

	@Override
	public List<RoomTable> revert(List<Room> Room_List) {

		List<RoomTable> roomTable_List = new ArrayList<>();

		for (Room room : Room_List) {
			roomTable_List.add(revert(room));
		}

		return roomTable_List;
	}

}
