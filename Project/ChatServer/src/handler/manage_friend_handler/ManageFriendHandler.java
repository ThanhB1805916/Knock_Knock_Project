package handler.manage_friend_handler;

import java.util.List;

import model.sendmodel.Person;

public interface ManageFriendHandler {

	/*
	 * Get friend list
	 */
	List<Person> get();

	Person find(String phonenumber);

	/*
	 * Remove a person from friend list
	 */
	boolean remove(Person person);
}
