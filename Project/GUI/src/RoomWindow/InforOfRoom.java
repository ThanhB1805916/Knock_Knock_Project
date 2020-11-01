package RoomWindow;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import MainChatRoom.FrameController;
import Models.Room;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class InforOfRoom implements Initializable {
	@FXML AnchorPane superPane;
	
	@FXML BorderPane borderPane;
	
	@FXML ImageView image;
	
	@FXML TextField label;
	
	@FXML FlowPane flowPane;
	
	private FrameController controller;
	
	private Room room;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void exitForm(MouseEvent event) {
		this.controller.getBackgroundPane().getChildren().remove(superPane);
	}
	
	//-------GET---------------
	public void getContext(FrameController controller) {
		this.controller = controller;
	}
	
	public void getRoom(Room room) {
		this.room = room;
		this.label.setText(room.get_name());
		setImage();
		setFriendList();
	}
	
	private void setImage() {
		File file = new File(System.getProperty("user.dir")+"//src//tmp//"+room.get_avatar().getName());	
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(room.get_avatar().getData());
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (IOException e) {
			System.out.println("error listroom.InforOfRoom.java");
		}
		Image imageLoader = new Image(file.toURI().toString());
		image.setImage(imageLoader);
		image.setPreserveRatio(true);
	}
	
	private void setFriendList() {
		for (int i = 1; i < room.get_userList().size(); i++) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("OvalRoom.fxml"));
				AnchorPane pane;
				pane = loader.load();
				OvalRoom control = loader.getController();
				control.getContext(controller);
				control.getUser(room.get_userList().get(i));
				flowPane.getChildren().add(pane);
			} catch (IOException e) {System.out.println("error listroom.InforOfRoom.java");}
		}
	}
}
