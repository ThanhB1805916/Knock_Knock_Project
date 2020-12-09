package controller.subcontroller.info;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import model.sendmodel.FileInfo;
import model.sendmodel.Person;
import model.sendmodel.Room;
import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;import javafx.stage.StageStyle;



public class RoomInfoController implements Initializable {
	@FXML
		private Button roomAdd, roomChangeAvatar, changeName, completeName;
	
	@FXML
		private FlowPane flowMember,flowCanAdd;
	
	@FXML
		private ImageView roomAvatar;
	
	@FXML
		private TextField roomName;
	
	@FXML 
		private ContextMenu contextaddmenu;
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->     ATTRIBUTES     <--------------------------
	*
	*-----------------------------------------------------------------------------------*/
	private int id;
	
	private String currentName;
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->     INITIALIZE     <--------------------------
	*
	*-----------------------------------------------------------------------------------*/
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setButton();
		currentName = roomName.getText();
	}
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->     METHODS     <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	/** Set information for that controller *********************************************/
	public void setInfo(int id){
		this.id = id;
		setRoomName();
		setRoomAvatar();
		setFlowMember();
		setFlowCanAdd();
	}
	
	/** Update the data when have changed ***********************************************/
	public void updateInfo(int id){
		setRoomName();
		setRoomAvatar();
		setFlowMember();
		setFlowCanAdd();
	}
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->     MODULES     <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	/** Set room name *******************************************************************/
	private void setRoomName(){
		roomName.setText(Controller.getInstance().getRoomById(id).getName());
	}
	
	/** Set image for this room *********************************************************/
	private void setRoomAvatar(){
		if(Controller.getInstance().getRoomById(id).getAvatar().getFile("tmp/"))
			roomAvatar.setImage(new Image(new File("tmp/"+Controller.getInstance().getRoomById(id).getAvatar().getName()).toURI().toString()));
		else
			roomAvatar.setImage(new Image(new File("tmp/user_icon.png").toURI().toString()));
	}
	
	/** Set member parent for flow pane **************************************************/
	private void setFlowMember(){
		flowMember.getChildren().clear();
		for (Person person : Controller.getInstance().getRoomById(id).getMembers())
			if(person.getId()!=Controller.getInstance().getUserId()){
				try{
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/subinfo/memberoval.fxml"));
					AnchorPane memberPane = loader.load();
					MemberOvalController memberController = loader.getController();
					memberController.setInfo(person.getId(), id);
					flowMember.getChildren().add(memberPane);
				}
				catch(Exception e){e.printStackTrace();}
			}
	}
	
	private void setFlowCanAdd(){
		flowCanAdd.getChildren().clear();
		Room room = Controller.getInstance().getRoomById(id);
		
		for (int i = 0 ; i<Controller.getInstance().getFriendList().size(); i++) {
			if(!isInRoom(room, Controller.getInstance().getFriendList().get(i).getId()))
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/subinfo/membercanadd.fxml"));
					AnchorPane memberPane = loader.load();
					MemberCanAddController memberController = loader.getController();
					Person person = Controller.getInstance().getFriendList().get(i);
					memberController.setInfo(person.getId());
					memberPane.setOnMouseClicked(value->{
						if(Controller.getInstance().alertConfirmation("Xác nhận","Bạn có chắc muốn thêm "+ person.getName(),null)){
							Controller.getInstance().addMemberToRoom(id, person.getId());
							updateInfo(id);
						}
					});
					flowCanAdd.getChildren().add(memberPane);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}
	
	/** Set button for this controller ***************************************************/
	private void setButton(){
		roomChangeAvatar.setOnMousePressed(value->{
			Stage stage  = new Stage(StageStyle.UTILITY);
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(
				      new FileChooser.ExtensionFilter("JPG", "*.jpg","*.png","*.gif"));
			File file = fileChooser.showOpenDialog(stage);
			if(file!=null){
				if(Controller.getInstance().alertConfirmation("Bạn có chắc muốn đổi ảnh của phòng không ?","Xác nhận", null)){
					Controller.getInstance().changeAvatarRoom(id, new FileInfo(file.getPath()));
					roomAvatar.setImage(new Image(file.toURI().toString()));
				}
			}
		});
		
		changeName.setOnMousePressed(value->{
			currentName = roomName.getText();
			roomName.setEditable(true);
			changeName.setVisible(false);
			completeName.setVisible(true);
		});
		
		completeName.setOnMousePressed(value->{
			roomName.setEditable(false);
			changeName.setVisible(true);
			completeName.setVisible(false);
			if(roomName.getText().toString().trim().length()>0 && !currentName.equals(roomName.getText())){
				currentName = roomName.getText();
				Controller.getInstance().changeNameRoom(id,currentName);
			}
			else roomName.setText(currentName);
		});
	}
	
	private boolean isInRoom(Room room, int id){
		for(Person person: room.getMembers())
			if(person.getId()==id)
				return true;
		return false;
	}
}