package data_access.person_access;

import data_access.model_access.ModelDAO_Query;

//Interface execute query at run time
public interface PersonDAO_Query extends ModelDAO_Query {

	String getByUsernameQuery();

	String getByPhonenumberQuery();

	String getListByID_RoomQuery();

	String getListByID_FriendQuery();

	String addFriendQuery();

	String removeFriendQuery();
}
