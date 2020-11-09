package data_access;

import data_access.message_access.*;
import data_access.message_access.imp.MessageDAO_MySQL;
import data_access.person_access.*;
import data_access.person_access.imp.PersonDAO_MySQL;
import data_access.room_access.*;
import data_access.room_access.imp.RoomDAO_MySQL;

// Get DAO from this

public class DAOFactory {
	public static DAOFactory Instance = new DAOFactory();

	private DAOFactory() {
	}

	public static SQLDAO getSQLDAO() {
		return new SQLDAOImp();
	}

	public static PersonDAO getPersonDAO() {
		return new PersonDAO_MySQL(getSQLDAO());
//		return new PersonDAO_SQLServer(getSQLDAO());
	}

	public static RoomDAO getRoomDAO() {
		return new RoomDAO_MySQL(getSQLDAO());
	}

	public static MessageDAO getMessageDAO() {
		return new MessageDAO_MySQL(getSQLDAO());
	}
}
