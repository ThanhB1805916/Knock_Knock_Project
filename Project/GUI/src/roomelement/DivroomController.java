package roomelement;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import MainChatRoom.FrameController;
import Models.FileInfo;
import Models.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class DivroomController implements Initializable {
	
	@FXML AnchorPane mainPane;
	
	@FXML Label name, currentTxt;
	
	@FXML ImageView avatar;
	
	@FXML Button fillBtn;
	
	private FrameController frameController;
	
	private AnchorPane activity;
	
	private Room room;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
	}
	
	public void setActivity(FrameController controller, AnchorPane activity, Room room) {
		this.frameController = controller;
		this.activity = activity;
		this.room = room;
	} 
	
	//**************LISTENER*********************
	
	public void showandhide(MouseEvent event) {
		showorhide();
	}
	
	public void showorhide() {
		for(int i =0; i<frameController.getActivityList().size(); i++)
			frameController.getActivityList().get(i).setVisible(false);
		activity.setVisible(true);
	}
	
	public void setName(String name) {
		this.name.setText(name);
	}
	
	public void setImage(FileInfo fileInfo) {
		File file = new File(System.getProperty("user.dir")+"//src//tmp//"+fileInfo.getName());	
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(fileInfo.getData());
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (IOException e) {
			System.out.println("Error roomelement.DivroomController.java");
		}
		Image imageLoader = new Image(file.toURI().toString());
		avatar.setImage(imageLoader);
		avatar.setPreserveRatio(true);
	}
	
	public void setCurrentText(String text) {
		currentTxt.setText(text);
	}
	
	public void deleteMessageRoom(ActionEvent event) {
		frameController.removeMessageRoom(room);
	}	
	
	public AnchorPane getDivPane() {
		return mainPane;
	}
}
