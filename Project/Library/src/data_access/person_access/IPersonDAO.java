package data_access.person_access;

import java.util.List;

import data_access.IModelDAO;
import data_model.PersonTable;

public interface IPersonDAO extends IModelDAO<PersonTable>{

	// Get person by username
	PersonTable getByUsername(String username);

	// Get person by phonenumber
	PersonTable getByPhonenumber(String phonenumber);
	
	// Get person list by id room
	List<PersonTable> getListByID_Room(int id_room);
	
//	//Delete person by username
//	boolean delete(String username);
}