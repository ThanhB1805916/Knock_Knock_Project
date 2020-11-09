
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import data_access.*;
import data_access.message_access.MessageDAO;
import data_access.person_access.PersonDAO;
import data_access.room_access.RoomDAO;
import data_model.MessageTable;
import data_model.PersonTable;
import data_model.RoomTable;

public class Demo {

	public static void main(String[] args) {

//		PersonDAO dao = DAOFactory.getPersonDAO();
//		PersonDAO dao = DAOFactory.getPersonDAO_Mysql();
		MessageDAO dao = DAOFactory.getMessageDAO();
//		RoomDAO dao = DAOFactory.getRoomDAO();
		MessageTable a = dao.get(1);
		System.out.println(a.getSendtime());
//		System.out.println(dao.get(1).getDatecreate());
	}

}
