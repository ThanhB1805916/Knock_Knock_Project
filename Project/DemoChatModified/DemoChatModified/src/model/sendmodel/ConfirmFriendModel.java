package model.sendmodel;

import java.io.Serializable;

import model.ValidModel;

public class ConfirmFriendModel implements ValidModel, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Person sender;
	private Person friend;
	private boolean isFriend = false;
	
	public ConfirmFriendModel(Person sender, Person friend) {
		this.sender = sender;
		this.friend = friend;
	}
	
	@Override
	public boolean isValid() {
		return sender.isValid();
	}
	
	public Person getSender() {
		return sender;
	}
	public void setSender(Person sender) {
		this.sender = sender;
	}

	public Person getFriend() {
		return friend;
	}

	public void setFriend(Person friend) {
		this.friend = friend;
	}

	public boolean getIsFriend() {
		return isFriend;
	}
	public void setIsFriend(boolean isFriend) {
		this.isFriend = isFriend;
	}
}