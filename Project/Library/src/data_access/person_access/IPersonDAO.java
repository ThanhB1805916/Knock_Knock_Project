package data_access.person_access;

import java.util.List;

import data_access.IModelDAO;
import data_model.PersonTable;

public interface IPersonDAO extends IModelDAO<PersonTable>{

	//Check if exist person has username
	boolean existUsername(String username);
	
	//Check if exist person has phonenumber
	boolean existPhonenumber(String phonenumber);
	
	// Get person by personaccount and password
	PersonTable get(String personaccount, String personpassword);

	// Get person list by id room
	List<PersonTable> getList(int id_room);
	
//	//Delete person by username
//	boolean delete(String username);
}