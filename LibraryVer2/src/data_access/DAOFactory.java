package data_access;

import connection.ConnectionString;
import data_access.message_access.MessageDAO;
import data_access.message_access.imp.MessageDAO_MySQL;
import data_access.person_access.PersonDAO;
import data_access.person_access.imp.PersonDAO_MySQL;
import data_access.room_access.RoomDAO;
import data_access.room_access.imp.RoomDAO_MySQL;

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

	public PersonDAO getPeronDAO() {
		return new PersonDAO_MySQL(dao);
	}

	public RoomDAO getRoomDAO() {
		return new RoomDAO_MySQL(dao);
	}

	public MessageDAO getMessageDAO() {
		return new MessageDAO_MySQL(dao);
	}
}
