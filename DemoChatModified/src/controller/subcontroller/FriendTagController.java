package controller.subcontroller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FriendTagController implements Initializable {
	@FXML
		private Label friendBrith, friendName, friendSex;	
	
	@FXML
		private ImageView friendImage, moreImage;
	
	@FXML 
		private Button fillButton, moreButton;
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->   ATTRIBUTES    <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	private int friendId;
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->   INITIALIZE    <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setButtonListener();
	}
	
	/*----------------------------------------------------------------------------------
	*
	*------------------------------------>   MODULES    <-------------------------------
	*
	*-----------------------------------------------------------------------------------*/
	/** Set fill button on the tag ******************************************************/
	private void setButtonListener(){
		fillButton.setOnMousePressed(event->{
			Controller.getInstance().showFriendInfo(friendId);
		});
		moreButton.setOnMouseEntered(value->{
			moreImage.setVisible(true);
		});
		
		moreButton.setOnMouseExited(value->{
			moreImage.setVisible(false);
		});
		
		moreButton.setOnMousePressed(value->{
			Controller.getInstance().removeFriend(friendId);
		});
	}
	
	/** Set information for friend tag ****************************************************/
	private void setInfo(){
		friendBrith.setText(Controller.getInstance().getPersonById(friendId).getDateofbirth().toString());
		friendName.setText(Controller.getInstance().getPersonById(friendId).getName());
		if(Controller.getInstance().getPersonById(friendId).getMale())
			friendSex.setText("Nam");
		else friendSex.setText("Nữ");
		if(Controller.getInstance().getPersonById(friendId).getAvatar().getFile("tmp/"))
			friendImage.setImage(new Image(new File("tmp/"+Controller.getInstance().getPersonById(friendId).getAvatar().getName()).toURI().toString()));
		else friendImage.setImage(new Image(new File("tmp/user_icon.png").toURI().toString()));
	}
	
	/*----------------------------------------------------------------------------------
	*
	*------------------------------------>   METHODS    <-------------------------------
	*
	*-----------------------------------------------------------------------------------*/
	/** Set data when the controller was initialized*************************************/
	public void setFriendID(int id){
		this.friendId = id;
		setInfo();
	}
	
	/** Get friend id *******************************************************************/
	public int getFriendID(){
		return this.friendId;
	}
	
	/** Call update when the date have changed*******************************************/
	public void update(){
		setInfo();
	};
}
