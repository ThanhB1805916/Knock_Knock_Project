package RoomWindow;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import FriendInfo.FriendInfo;
import MainChatRoom.FrameController;
import Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class FriendDivController implements Initializable {
	@FXML ImageView avatar;
	
	@FXML Label name;
	
	private User user;
	
	private FrameController controller;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void setUser(User user) {
		this.user = user;
		updating();
	}
	
	public void setContext(FrameController controller) {
		this.controller = controller;
	}
	
	public void updating() {
		name.setText(user.get_name());
		setImage();
	}
	
	private void setImage() {
		File file = new File(System.getProperty("user.dir")+"//src//tmp//"+user.get_avatar().getName());	
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(user.get_avatar().getData());
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (IOException e) {
			System.out.println("Error setImage listroom.FriendDivController");
		}
		Image imageLoader = new Image(file.toURI().toString());
		avatar.setImage(imageLoader);
		avatar.setPreserveRatio(true);
	}
	
	public void seeInfor(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/FriendInfo/FriendInfo.fxml"));
		try {
			AnchorPane anchorPane = loader.load();
			AnchorPane.setBottomAnchor(anchorPane, 0.0);
			AnchorPane.setTopAnchor(anchorPane, 0.0);
			AnchorPane.setLeftAnchor(anchorPane, 0.0);
			AnchorPane.setRightAnchor(anchorPane, 0.0);
			FriendInfo control = loader.getController();
			control.getContext(controller);
			control.getUser(user);
			controller.getBackgroundPane().getChildren().add(anchorPane);
		} catch (IOException e) {
			System.out.println("Error listRoom.FriendDivController");
		}
	}
	
	public void delete(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Bạn có chắc muốn xóa "+user.get_name()+" ra khỏi danh sách bạn bè?");
		alert.setTitle("Xác nhận");
		Optional<ButtonType> type = alert.showAndWait();
		if(type.get()==ButtonType.OK)
			controller.removeFriend(user);
	}
}
