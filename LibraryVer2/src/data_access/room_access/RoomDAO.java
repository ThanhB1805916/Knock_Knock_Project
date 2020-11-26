package data_access.room_access;

import java.util.List;

import data_access.model_access.ModelDAO;
import data_model.RoomTable;

public interface RoomDAO extends ModelDAO<RoomTable> {

	List<RoomTable> getList(int id_person);
}
