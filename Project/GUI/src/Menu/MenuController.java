package Menu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import MainChatRoom.FrameController;
import Models.User;
import RoomWindow.ListRoomController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MenuController implements Initializable{
	@FXML Button userBtn, roomsBtn, friendsBtn, messageBtn,exitBtn;
	@FXML ImageView myUser;
	
	private User user;
	
	
	FrameController controller;
	
	private ListRoomController listController;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		messageBtn.requestFocus();
	}
	
	public void setController(FrameController controller) {
		this.listController = controller.getListRoomController();
		this.controller = controller;
		this.user = controller.getMyUser();
		updateAvatar();
	}
	
	public void messageBtnCilcked(MouseEvent event) {
		listController.setOnScrollMess();
	}
	
	public void friendBtnCilcked(MouseEvent event) {
		listController.setOnScrollFriend();
	}
	
	public void roomBtnCilcked(MouseEvent event) {
		listController.setOnScrollRoom();
	}
	
	public void exited(MouseEvent event) {
		Platform.exit();
	}
	
	public void userEntry(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("UserInfo.fxml"));
		AnchorPane pane = loader.load();
		UserInfoController userController = loader.getController();
		userController.getUser(user);
		userController.getController(controller);
		AnchorPane.setLeftAnchor(pane,0.0);
		AnchorPane.setRightAnchor(pane,0.0);
		AnchorPane.setTopAnchor(pane, 0.0);
		AnchorPane.setBottomAnchor(pane,0.0);
		controller.getBackgroundPane().getChildren().add(pane);
	}
	
	public void updateAvatar() {
		File file = new File(System.getProperty("user.dir")+"//src//tmp//"+user.get_avatar().getName());	
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(user.get_avatar().getData());
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (IOException e) {
			System.out.println("Error line 51 InfoUserClas");
		}
		Image imageLoader = new Image(file.toURI().toString());
		myUser.setImage(imageLoader);
		myUser.setPreserveRatio(true);
	}
}
