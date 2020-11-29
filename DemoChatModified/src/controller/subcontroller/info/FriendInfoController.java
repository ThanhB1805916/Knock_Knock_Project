package controller.subcontroller.info;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import controller.Controller;
import model.sendmodel.Person;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FriendInfoController implements Initializable{
	@FXML 
		private Label userSex, userNameBanner, userName, userID, userDate;
	
	@FXML 
		private ImageView friendImage;
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->   ATTRIBUTES    <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	private int id;
	
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
	*----------------------------------->   METHODS    <--------------------------------
	*
	*-----------------------------------------------------------------------------------*/
	
	/** Set information for this class **************************************************/
	public void setInfo(int id){
		this.id = id;
		userNameBanner.setText(Controller.getInstance().getPersonById(id).getName());
		userID.setText(Integer.toString(Controller.getInstance().getPersonById(id).getId()));
		userName.setText(Controller.getInstance().getPersonById(id).getName());
		userDate.setText(Controller.getInstance().getPersonById(id).getDateofbirth().toString());
		if(Controller.getInstance().getPersonById(id).getMale())
			userSex.setText("Nam");
		else userSex.setText("Nữ");
		if(Controller.getInstance().getPersonById(id).getAvatar().getFile("tmp/"))
			friendImage.setImage(new Image(new File("tmp/"+Controller.getInstance().getPersonById(id).getAvatar().getName()).toURI().toString()));
		else friendImage.setImage(new Image(new File("tmp/user_icon.png").toURI().toString()));
	}
	
	/** Update the information **********************************************************/
	public void update(Person person){
		setInfo(id);
	}
}
