package handler.manage_friend_handler;

import java.util.List;

import model.sendmodel.ConfirmFriendModel;
import model.sendmodel.Person;

public interface FriendHandler {

	/*
	 * Get friend list
	 */
	List<Person> get(int id_friend);

	Person find(String phonenumber);

	boolean add(int id);
	
	boolean confirm(ConfirmFriendModel  person);
	
	/*
	 * Remove a person from friend list
	 */
	boolean remove(int id);
}
