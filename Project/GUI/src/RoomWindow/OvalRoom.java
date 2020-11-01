package RoomWindow;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import FriendInfo.FriendInfo;
import MainChatRoom.FrameController;
import Models.Room;
import Models.User;
import RoomWindow.RoomDetails.RoomExtendController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class OvalRoom implements Initializable{
	
	@FXML ImageView image,image1;
	
	@FXML AnchorPane superPane;
	
	private FrameController controller;
	
	private boolean canDelete = false;
	
	private User user;
	
	private Room room;
	
	private RoomExtendController roomExtend;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void getUser(User user) {
		this.user =user;
		setImage();
	}
	
	public void getContext(FrameController controller) {
		this.controller = controller;
	}
	
	public void setSize(float size) {
		superPane.setPrefSize(size, size);
		image.setFitWidth(size-2);
		image.setFitHeight(size);
		image1.setFitWidth(size);
		image1.setFitHeight(size);
	}
	
	public void canDelete(boolean flag) {
		this.canDelete = flag;
	}
	
	
	public void getRoomExtend(RoomExtendController roomExtendController) {
		this.roomExtend = roomExtendController;
		this.room = roomExtend.onRoom();
	}
	
	public void delete(MouseEvent event) {
		if(canDelete) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Xác nhận!");
			alert.setHeaderText("Bạn có chắc muốn xóa "+user.get_name()+" ra khỏi phòng ?");
			Optional<ButtonType> type = alert.showAndWait();
			if(type.get()==ButtonType.OK) {
				room.get_userList().remove(user);
				roomExtend.update();
				roomExtend.setMember();
			}
		}
	}
	
	public void showRoomerInfo(MouseEvent event) {
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
			System.out.println("Error listRoom.Oval");
		}
	}
	
	private void setImage() {
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
		image.setImage(imageLoader);
		image.setPreserveRatio(true);
	}
}
