package data_access.person_access;

import java.util.List;

import data_access.ModelDAO;
import data_model.PersonTable;

public interface PersonDAO extends ModelDAO<PersonTable> {

	// * Function for set query

	PersonTable getByUsername(String username);

	// Query for getByUsername *
	String getByUsernameQuery();

	PersonTable getByPhonenumber(String phonenumber);

	// Query for getByPhonenumber *
	String getByPhonenumberQuery();

	List<PersonTable> getListByID_Room(int id_room);

	// Query for getListByID_Room *
	String getListByID_RoomQuery();

	List<PersonTable> getListByID_Friend(int id_friend);

	// Query for getListByID_Friend *
	String getListByID_FriendQuery();

	boolean addFriend(int id_person, int id_friend);

	// Query for add friend *
	String addFriendQuery();

	/*
	 * Accept addFriend request
	 */
	boolean accept(int id_person, int id_friend);

	// Query for add friend *
	String acceptQuery();

	boolean removeFriend(int id_person, int id_friend);

	// Query for removeFriend
	String removeFriendQuery();
}