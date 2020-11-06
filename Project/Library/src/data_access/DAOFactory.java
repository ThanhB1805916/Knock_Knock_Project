package data_access;

import data_access.message_access.*;
import data_access.person_access.*;
import data_access.room_access.*;

// Get DAO from this

public class DAOFactory {
	public static DAOFactory Instance = new DAOFactory();

	private DAOFactory() {
	}

	public static SQLDAO getSQLDAO() {
		return new SQLDAOImp();
	}

	public static PersonDAO getPersonDAO() {
		return new PersonDAOImp(getSQLDAO());
	}

	public static RoomDAO getRoomDAO() {
		return new RoomDAOImp(getSQLDAO());
	}

	public static MessageDAO getMessageDAO() {
		return new MessageDAOImp(getSQLDAO());
	}
}
