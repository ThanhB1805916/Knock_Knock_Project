package libraryTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import connection.ConnectionString;
import data_access.DAOFactory;
import data_access.room_access.RoomDAO;
import data_model.RoomTable;

public class RoomDAO_Test {

	RoomDAO dao;
	RoomTable roomValid;
	List<RoomTable> roomListValid;
	int id_person = 1;

	@Before
	public void setUp() {
		dao = DAOFactory.getInstance().setSQLDAO(new ConnectionString(
						"jdbc:mysql://localhost:3306/Knock_Knock_Project_Test?noAccessToProcedureBodies=true",
						"stdUser", "std1234")).getRoomDAO();

		roomValid = new RoomTable(1, "Phong 1", LocalDateTime.of(LocalDate.of(2020, 11, 9), LocalTime.of(9, 00, 00)),
				"default_room_avatar.jpg");

		roomListValid = new ArrayList<RoomTable>();
		roomListValid.add(roomValid);
	}

	public boolean isEqual(RoomTable roomA, RoomTable roomB) {

		return roomA.getId() == roomB.getId() && roomA.getName().equals(roomB.getName())
				&& roomA.getDatecreate().equals(roomB.getDatecreate()) && roomA.getAvatar().equals(roomB.getAvatar());
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------- Get By ID

	@Test
	public void getByID() {
		RoomTable room = dao.get(roomValid.getId());
		Assert.assertNotNull(room);
	}

	@Test
	public void id_isNegative_when_getByID() {
		RoomTable room = dao.get(-1);
		Assert.assertNull(room);
	}

	@Test
	public void getByIDEqualsTrueValue() {
		RoomTable room = dao.get(roomValid.getId());
		Assert.assertTrue(isEqual(room, roomValid));
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------- Get List

	@Test
	public void getListByID_Person() {
		List<RoomTable> roomList = dao.getList(id_person);
		Assert.assertNotNull(roomList);
	}

	@Test
	public void id_isNegative_when_getListByID_Peron() {
		List<RoomTable> roomList = dao.getList(-1);
		Assert.assertNull(roomList);
	}

	@Test
	public void getByListID_PersonEqualsTrueValue() {
		List<RoomTable> roomList = dao.getList(id_person);
		int i = 0;
		for (RoomTable roomTable : roomList) {
			Assert.assertTrue(isEqual(roomTable, roomListValid.get(i++)));
		}
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------- Update
	
	@Test
	public void update()
	{
		RoomTable room = dao.get(roomValid.getId());
		boolean success = dao.update(room);
		Assert.assertTrue(success);
	}
	
	@Test
	public void id_isNegative_when_update()
	{
		RoomTable room = dao.get(roomValid.getId());
		room.setId(-1);
		boolean success = dao.update(room);
		Assert.assertFalse(success);
	}
	
	@Test
	public void name_isNull_when_update()
	{
		RoomTable room = dao.get(roomValid.getId());
		room.setName(null);
		boolean success = dao.update(room);
		Assert.assertFalse(success);
	}
	
	@Test
	public void name_isEmpty_when_update()
	{
		RoomTable room = dao.get(roomValid.getId());
		room.setName("");
		boolean success = dao.update(room);
		Assert.assertFalse(success);
	}
	
	@Test
	public void dateCreate_isNull_when_update()
	{
		RoomTable room = dao.get(roomValid.getId());
		room.setDatecreate(null);
		boolean success = dao.update(room);
		Assert.assertFalse(success);
	}
	
	@Test
	public void avatar_isNull_when_update()
	{
		RoomTable room = dao.get(roomValid.getId());
		room.setAvatar(null);
		boolean success = dao.update(room);
		Assert.assertFalse(success);
	}
	
	@Test
	public void avatar_isEmpty_when_update()
	{
		RoomTable room = dao.get(roomValid.getId());
		room.setAvatar("");
		boolean success = dao.update(room);
		Assert.assertFalse(success);
	}
}
