package handler.manage_friend_handler;

import java.util.List;

import model.sendmodel.Person;

public interface FriendHandler {

	/*
	 * Get friend list
	 */
	List<Person> get(int id_friend);

	Person find(String phonenumber);

	boolean add(Person person);
	
	boolean accept(Person person);
	
	/*
	 * Remove a person from friend list
	 */
	boolean remove(Person person);
}
