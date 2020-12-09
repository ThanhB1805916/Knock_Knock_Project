package controller.subcontroller.createroom;

import java.net.URL;
import java.util.ResourceBundle;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;

public class FriendOvalController implements Initializable {
	@FXML 
		private ImageView avatar, checkedImage;
	
	@FXML 
		private Button buttonFill;
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->   ATTRIBUTES    <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	private boolean status = false;
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->  INITIALIZE    <------------------------------
	*
	*-----------------------------------------------------------------------------------*/
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	/** Set status for radio button *****************************************************/
	public void setRadio(RadioButton radio){
		buttonFill.setOnMousePressed(value->{
			radio.setSelected(status=!status);
			checkedImage.setVisible(status);
		});
	}
	
	/** Set image user for this oval ****************************************************/
	public void setImage(int id){
		avatar.setImage(Controller.getInstance().getImageOfPerson(id));
	}
}
