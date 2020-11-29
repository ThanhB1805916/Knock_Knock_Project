package controller.subcontroller.info;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import model.sendmodel.Person;
import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MemberOvalController implements Initializable{
	@FXML 
		private Button memberButton;
	
	@FXML
		private ImageView memberImage;
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->     ATTRIBUTES     <--------------------------
	*
	*-----------------------------------------------------------------------------------*/
	private int roomId, memberId;
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->     INITIALIZE     <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->     METHODS     <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	/** Set information for this controller *********************************************/
	public void setInfo(int memeberId, int roomId){
		this.roomId = roomId;
		this.memberId = memeberId;
		setMemberImage();
		setMemberButton();
	}
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->     MODULES     <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	/** Module to set image for member **************************************************/
	private void setMemberImage(){
		Person person = Controller.getInstance().getMemberById(roomId, memberId);
		person.getAvatar().getFile("tmp/");
		memberImage.setImage(new Image(new File("tmp/"+person.getAvatar().getName()).toURI().toString()));
	}
	
	/** Module for set member button *****************************************************/
	private void setMemberButton(){
		memberButton.setOnMousePressed(value->{
			Controller.getInstance().showMemberInfo(memberId, roomId);
		});
	}
}
