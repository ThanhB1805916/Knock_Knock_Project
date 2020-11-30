package data_access.person_access;

import java.util.List;

import data_access.model_access.ModelDAO;
import data_model.PersonTable;

public interface PersonDAO extends ModelDAO<PersonTable> {

	PersonTable getByUsername(String username);

	PersonTable getByPhonenumber(String phonenumber);

	List<PersonTable> getListByID_Room(int id_room);

	List<PersonTable> getListByID_Friend(int id_friend);

	boolean addFriend(int id_person, int id_friend);

	boolean removeFriend(int id_person, int id_friend);

}