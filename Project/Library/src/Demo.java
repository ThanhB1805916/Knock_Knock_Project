


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import data_access.*;
import data_access.person_access.PersonDAO;
import data_access.room_access.RoomDAO;
import data_model.PersonTable;
import data_model.RoomTable;


public class Demo {

	public static void main(String[] args) {

		RoomDAO dao = DAOFactory.getRoomDAO();
		
		RoomTable room = dao.getList(2).get(0);
		System.out.println(System.getProperty("user.dir"));
		System.out.println(room.getDatecreate().toString());
	}

	
}

