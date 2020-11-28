package data_access;

import connection.ConnectionString;
import data_access.message_access.*;
import data_access.message_access.imp.*;
import data_access.person_access.*;
import data_access.person_access.imp.*;
import data_access.room_access.*;
import data_access.room_access.imp.*;

// Get DAO from this

public final class DAOFactory {
	
	private static final DAOFactory Instance = new DAOFactory();

	private SQLDAO dao = new SQLDAOImp(new ConnectionString());

	private DAOFactory() {
	}

	public static DAOFactory getInstance() {
		return Instance;
	}

	public DAOFactory setSQLDAO(ConnectionString connection) {
		dao = new SQLDAOImp(connection);
		return this;
	}

	public SQLDAO getSQLDAO() {
		return dao;
	}

	public PersonDAO getPersonDAO() {
		return new PersonDAO_MySQL(getSQLDAO());
//		return new PersonDAO_SQLServer(getSQLDAO());
	}

	public RoomDAO getRoomDAO() {
		return new RoomDAO_MySQL(getSQLDAO());
	}

	public MessageDAO getMessageDAO() {
		return new MessageDAO_MySQL(getSQLDAO());
	}
}
