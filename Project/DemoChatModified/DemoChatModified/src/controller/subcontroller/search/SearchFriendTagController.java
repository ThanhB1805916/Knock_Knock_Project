package controller.subcontroller.search;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import controller.Controller;
import model.sendmodel.Person;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SearchFriendTagController implements Initializable {
	@FXML
		private Label genderLabel;

	@FXML
		private Button addButton;

	@FXML
		private Label dateBrithTextField;

	@FXML
		private Label nameLabel;

	@FXML
		private ImageView avatar;

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
		setAddButton();
	}
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->      METHODS    <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	/** Set information for this controller *********************************************/
	public void setInfo(Person person){
		id = person.getId();
		
		nameLabel.setText(person.getName());
		
		LocalDate localDate = person.getDateofbirth();
		
		dateBrithTextField.setText(localDate.getDayOfMonth() +"."+localDate.getMonthValue()+"."+localDate.getYear());
		
		if(person.getMale()) 
			genderLabel.setText("Nam");
		else 
			genderLabel.setText("Nữ");
		
		if(person.getAvatar().getFile("tmp/"))
			avatar.setImage(new Image(new File("tmp/"+person.getAvatar().getName()).toURI().toString()));
		else 
			avatar.setImage(new Image(new File("tmp/user_icon.png").toURI().toString()));	
		
		if(Controller.getInstance().isFriend(id))
			addButton.setDisable(true);
	}
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->     MODULDES    <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	/** Set add button ******************************************************************/
	private void setAddButton(){
		addButton.setOnMousePressed(value->{
			Controller.getInstance().addMoreFriend(id);
			addButton.setDisable(true);
		});
	}
}
