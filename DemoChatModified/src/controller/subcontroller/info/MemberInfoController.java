package controller.subcontroller.info;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import controller.Controller;
import model.sendmodel.Person;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MemberInfoController implements Initializable{
	@FXML 
		private Label userSex, userNameBanner, userName, userID, userDate;
	
	@FXML 
		private Button userDelete, userAdd;
	
	@FXML 
		private ImageView userImage;
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->   ATTRIBUTES    <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	private Person person;
	
	private int roomId;
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->   INITIALIZE    <-----------------------------
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
	public void setInfo(Person person, int id){
		this.person = person;
		this.roomId = id;
		callUpdate(person,id);
	}
	
	/** Update the data when have changed ************************************************/
	public void update(Person person){
		callUpdate(person, this.roomId);
	}
	
	/** Set for delete button listener **************************************************/
	public void setDeleteButton(RoomInfoController roomInfoController){
		userAdd.setOnMousePressed(value->{
			Controller.getInstance().addMoreFriend(person.getId());
		});
		
		userDelete.setOnMousePressed(value->{
			Controller.getInstance().deleteMember(person.getId(), roomId);
			((Stage)userDelete.getScene().getWindow()).close();
			roomInfoController.updateInfo(roomId);
		});
	}
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->     MODULES     <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	/** External update data when receive ***********************************************/
	private void callUpdate(Person person, int id){
		userNameBanner.setText(person.getName());
		userID.setText(Integer.toString(person.getId()));
		userName.setText(person.getName());
		userDate.setText(person.getDateofbirth().toString());
		person.getAvatar().getFile("tmp/");
		userImage.setImage(new Image( new File("tmp/"+person.getAvatar().getName()).toURI().toString()));
		if(person.getMale())
			userSex.setText("Nam");
		else userSex.setText("Nữ");
		if(Controller.getInstance().getUserId()!=person.getId() && Controller.getInstance().getPersonById(person.getId())==null)
			userAdd.setVisible(true);
	}
}
