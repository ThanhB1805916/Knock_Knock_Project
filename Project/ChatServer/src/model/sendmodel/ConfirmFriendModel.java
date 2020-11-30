package model.sendmodel;

import java.io.Serializable;

import model.ValidModel;

public class ConfirmFriendModel implements ValidModel, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Person sender;
	private boolean isFriend = false;
	
	public ConfirmFriendModel(Person sender) {
		this.sender = sender;
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

	public boolean getIsFriend() {
		return isFriend;
	}
	public void setIsFriend(boolean isFriend) {
		this.isFriend = isFriend;
	}
}
