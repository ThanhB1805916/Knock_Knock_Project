package RoomWindow;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import MainChatRoom.FrameController;
import Models.Room;
import Room.RoomPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class RoomDivController implements Initializable {
	@FXML ImageView avatar;
	@FXML Label name;
	
	private Room room;
	
	private FrameController controller;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
	}
	//**************---GET/SET-------------
	// --------- Lấy thông tin phòng -----------
	public void setRoom(Room room) {
		this.room = room;
		updating();
	}
	
	//---------Lấy controller của class chính
	public void setContext(FrameController controller) {
		this.controller = controller;
	}
	
	//*********Hàm API***************
	//-------cập nhật thông tin của phòng
	public void updating() {
		name.setText(room.get_name());
		setImage();
	}
	
	//*************HÀM SỰ KIỆN**************************
	//--------Hiện thông tin của phòng
	public void showRoomInfo(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("InforOfRoom.fxml"));
			AnchorPane anchorPane = loader.load();
			AnchorPane.setTopAnchor(anchorPane,0.0);
			AnchorPane.setBottomAnchor(anchorPane,0.0);
			AnchorPane.setLeftAnchor(anchorPane, 0.0);
			AnchorPane.setRightAnchor(anchorPane, 0.0);
			InforOfRoom infoController = loader.getController();
			infoController.getContext(controller);
			infoController.getRoom(room);
			controller.getBackgroundPane().getChildren().add(anchorPane);
		} catch (IOException e) {}	
	}
	
	//------------Rời khỏi phòng chat-------------------
	public void outOfRoom(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Xác nhận!");
		alert.setHeaderText("Bạn có chắc muốn rời khỏi ");
		Optional<ButtonType> type = alert.showAndWait();
		if(type.get()==ButtonType.OK)
			controller.outOfRoom(room);
	}
	
	//------------Hiện thông activity của phòng khi div được nhấp
	public void showThisRoom(MouseEvent event) {
		if(event.getClickCount()==2) {
			if (controller.getCurrentRoom(room) != null)
				controller.getCurrentRoom(room).getDivController().showorhide();
			else {
				RoomPane roomPane = new RoomPane(room);
				controller.reupOldRoom(roomPane);
			}
		}
	}
//*****************Nội hàm*************************
	//Thiết lập hình hảnh cho div
	private void setImage() {
		File file = new File(System.getProperty("user.dir")+"//src//tmp//"+room.get_avatar().getName());	
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(room.get_avatar().getData());
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (IOException e) {
			System.out.println("Error line 51 InfoUserClas");
		}
		Image imageLoader = new Image(file.toURI().toString());
		avatar.setImage(imageLoader);
		avatar.setPreserveRatio(true);
	}
}
