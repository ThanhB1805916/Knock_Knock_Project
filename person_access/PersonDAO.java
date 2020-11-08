package data_access.person_access;

import java.util.List;

import data_access.ModelDAO;
import data_model.PersonTable;

public interface PersonDAO extends ModelDAO<PersonTable>{

	// Get person by username
	PersonTable getByUsername(String username);

	// Get person by phonenumber
	PersonTable getByPhonenumber(String phonenumber);
	
	// Get person list by id room
	List<PersonTable> getListByID_Room(int id_room);
	
	//Delete person by username
	List<PersonTable> getListByID_Friend(int id_friend);
}