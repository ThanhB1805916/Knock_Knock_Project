package controller.subcontroller.createroom;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;



import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CreateRoomController implements Initializable{
	@FXML TextField name;
	@FXML FlowPane friend;
	@FXML Button create, change;
	@FXML ImageView avatar;
	private ArrayList<RadioButton> listChecked = new ArrayList<RadioButton>();
	private ArrayList<Integer> idList  = new ArrayList<>();
	private String path = null;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setListChecked();
		initFriendFlow();
		setCreateButton();
		setChangeButton();
	}
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->     MODULES     <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	/** Set list checked when user choose another user into this room *******************/
	private void setListChecked(){
		for(int i=0; i<Controller.getInstance().getFriendList().size(); i++)
			listChecked.add(new RadioButton(Integer.toString(Controller.getInstance().getFriendList().get(i).getId())));
	}
	
	/** Initialize friend list to flow pane *********************************************/
	private void initFriendFlow(){
		for (int i =0 ;i<Controller.getInstance().getFriendList().size();i++)
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/createroom/friendoval.fxml"));
				Parent parent = loader.load();
				int pID = Controller.getInstance().getFriendList().get(i).getId();
				FriendOvalController friendController = loader.getController();
				listChecked.get(i).setText(pID+"");
				friendController.setRadio(listChecked.get(i));
				friendController.setImage(pID);
				friend.getChildren().add(parent);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	/** Check valid that having more one radio button was checked? **********************/
	private boolean checkVaild(){
		idList.clear();
		if(name.getText().trim().length()>0){
			for (RadioButton radioButton : listChecked)
				if (radioButton.isSelected())
					idList.add(Integer.parseInt(radioButton.getText()));
			if(idList.size()>0)
				return true;
		}
		return false;
	}
	
	/** Set create button was clicked ***************************************************/
	private void setCreateButton(){
		create.setOnMousePressed(value->{
			if(checkVaild()){
				Controller.getInstance().createNewRoom(idList,name.getText(),path);
				((Stage) name.getScene().getWindow()).close();
			}
			else Controller.getInstance().alertNotify(AlertType.ERROR,"Vui lòng điền đầy đủ thông tin!","Lỗi",null);
		});
	}
	
	/** Set change image button clicked ************************************************/
	private void setChangeButton(){
		change.setOnMousePressed(value->{
			Stage stage = new Stage();
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(
				      new FileChooser.ExtensionFilter("JPG", "*.jpg"),
				      new FileChooser.ExtensionFilter("PNG", "*.png"));
			
			fileChooser.setTitle("Open Resource File");
			File file = fileChooser.showOpenDialog(stage);
			if(file!=null) {
				avatar.setImage(new Image(file.toURI().toString()));
				path = file.getPath();
			}
		});
	}
}
