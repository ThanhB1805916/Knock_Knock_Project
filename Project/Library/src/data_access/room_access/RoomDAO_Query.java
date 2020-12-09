package data_access.room_access;

import data_access.model_access.ModelDAO_Query;

public interface RoomDAO_Query extends ModelDAO_Query {

	String getRoomSizeQuery();
	String addPersonQuery();
	String getListQuery();
	String exitRoomQuery();
}
