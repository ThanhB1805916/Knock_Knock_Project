package CreateNewRoom;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import MainChatRoom.FrameController;
import Models.FileInfo;
import Models.Room;
import Models.User;
import Room.RoomPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CreateController implements Initializable {
	
	@FXML AnchorPane fillPane;
	
	@FXML Button create;
	
	@FXML TextField roomName;
	
	@FXML VBox vBox;
	
	@FXML ImageView roomAvatar;
	
	@FXML Button imageBtn;
	
	private FrameController controller;
	
	private ArrayList<User> friendList = new ArrayList<>();
	
	private Room newRoom;
	
	private FileInfo fileInfo;
	
	private ArrayList<ChooseFriend> chooseList = new ArrayList<>();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fileInfo = new FileInfo(System.getProperty("user.dir")+"//src//tmp//user.png");
	}
	
	public void getContext(FrameController controller) throws IOException {
		this.controller =controller;
		this.friendList = controller.getFriendList();
		
		for(int i=0; i<friendList.size(); i++) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ChooseFriend.fxml"));
			AnchorPane anchorPane = loader.load();
			ChooseFriend chooseFriend = loader.getController();
			chooseFriend.getUser(friendList.get(i));
			vBox.getChildren().add(anchorPane);
			chooseList.add(chooseFriend);
		}
	}
	
	public void createClicked(MouseEvent event) {
		ArrayList<User> newUserList = new ArrayList<>();
		for (int i = 0; i < chooseList.size(); i++) {
			if(chooseList.get(i).getChecked())
				newUserList.add(friendList.get(i));
		}
		if(roomName.getText().trim().length()==0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Vui lòng nhập tên phòng!");
			alert.show();
		}
		else if(newUserList.size()==0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Vui lòng chọn bạn vào phòng!");
			alert.show();
		}
		else {
			newRoom = new Room(1234567890, roomName.getText(),fileInfo, newUserList);
			newRoom.get_userList().add(controller.getMyUser());
			RoomPane roomPane = new RoomPane(newRoom);
			controller.createNewRoom(roomPane);
			controller.getBackgroundPane().getChildren().remove(fillPane);
		}
	}
	
	public void imageClicked(MouseEvent event) {
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(
			      new FileChooser.ExtensionFilter("JPG", "*.jpg"),
			      new FileChooser.ExtensionFilter("PNG", "*.png"));
		
		fileChooser.setTitle("Open Resource File");
		File file = fileChooser.showOpenDialog(stage);
		if(file!=null) {
			Image image = new Image(file.toURI().toString());
			roomAvatar.setImage(image);
			fileInfo = new FileInfo(file.getPath());
		}
	}
	
	public void mouseCancel(MouseEvent event) {
		controller.getBackgroundPane().getChildren().remove(fillPane);
	}
}
