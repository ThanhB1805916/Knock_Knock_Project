package data_access.room_access;

import java.util.List;

import data_access.IModelDAO;
import data_model.RoomTable;

public interface IRoomDAO extends IModelDAO<RoomTable> {

	public List<RoomTable> getList(int id_person);
}
