package RoomWindow.RoomDetails;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import MainChatRoom.FrameController;
import MessageWindow.ActivityController;
import Models.FileInfo;
import Models.Room;
import RoomWindow.OvalRoom;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
	
public class RoomExtendController implements Initializable {
	@FXML AnchorPane superPane, mainPane;
	
	@FXML ScrollPane scrollPane;
	
	@FXML FlowPane flowPane;
	
	@FXML ImageView image;
	
	@FXML Button exitBtn, sendMessBtn, nameBtn;
	
	@FXML TextField nameEdit;
	
	private Room room;
	
	private ActivityController activity;
	
	private FrameController controller;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void getConText(FrameController controller) {
		this.controller = controller;
	}
	
	public void getRoom(Room room) {
		this.room =  room;
		update();
	}
	
	public void getActivity(ActivityController activity) {
		this.activity  = activity;
	}
	
	public void removeForm(MouseEvent event) {
		controller.getBackgroundPane().getChildren().remove(superPane);
	}
	
	public void messClicked(MouseEvent event) {
		controller.getBackgroundPane().getChildren().remove(superPane);
	}
	
	public void showScroll(MouseEvent event) {
		scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
	}
	
	public void hideScroll(MouseEvent event) {
		scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
	}
	
	public void exitRoom(MouseEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Xác nhận!");
		alert.setHeaderText("Bạn có chắc muốn rời khỏi?");
		Optional<ButtonType> type = alert.showAndWait();
		if(type.get()==ButtonType.OK) {
			controller.outOfRoom(room);
			controller.getBackgroundPane().getChildren().remove(superPane);
		}
	}
	
	//------------------API-----------------------
	
	public void update() {
		nameEdit.setText(room.get_name());
		setImage(room.get_avatar());
		setMember();
	}
	
	//---------------------------------------------------
	
	public void setMember() {
		flowPane.getChildren().clear();
		for (int i = 1; i < room.get_userList().size(); i++) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/listroom/OvalRoom.fxml"));
				AnchorPane pane;
				pane = loader.load();
				OvalRoom control = loader.getController();
				control.getContext(controller);
				control.getUser(room.get_userList().get(i));
				control.setSize(63.0f);
				control.canDelete(true);
				control.getRoomExtend(this);
				flowPane.getChildren().add(pane);
			} catch (IOException e) {System.out.println("error listroom.InforOfRoom.java");}
		}
	}
	
	public Room onRoom() {
		return this.room;
	}
	
	private void setImage(FileInfo fileInfo) {
		File file = new File(System.getProperty("user.dir")+"//src//tmp//"+fileInfo.getName());	
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(fileInfo.getData());
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
