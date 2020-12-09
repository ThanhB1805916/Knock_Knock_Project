package controller.subcontroller.info;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import model.sendmodel.Person;
import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MemberCanAddController implements Initializable{
	@FXML
		private ImageView memberImage;
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->     ATTRIBUTES     <--------------------------
	*
	*-----------------------------------------------------------------------------------*/
	private int memberId;
	
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
	public void setInfo(int memeberId){
		this.memberId = memeberId;
		setMemberImage();
	}
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->     MODULES     <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	/** Module to set image for member **************************************************/
	private void setMemberImage(){
		Person person = Controller.getInstance().getPersonById(memberId);
		person.getAvatar().getFile("tmp/");
		memberImage.setImage(new Image(new File("tmp/"+person.getAvatar().getName()).toURI().toString()));
	}
}
