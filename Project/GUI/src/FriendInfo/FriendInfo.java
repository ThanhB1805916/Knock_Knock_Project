package FriendInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import MainChatRoom.FrameController;
import Models.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class FriendInfo implements Initializable {
	@FXML AnchorPane superPane;
	
	@FXML ImageView image;
	
	@FXML Label banner, name, day, month, year, phone,gender, stamp4;
	
	@FXML Circle stamp1,stamp2,stamp3;
	
	private FrameController  controller;
	
	private User user;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void getContext(FrameController controller) {
		this.controller = controller;
	}
	
	public void getUser(User user) {
		this.user = user;
		updateInfo();
	}
	
	public void removeForm(MouseEvent event) {
		
		controller.getBackgroundPane().getChildren().remove(superPane);
		
	}
// --cập nhật thông tin của form--------
	private void updateInfo() {
		setImage();
		name.setText(user.get_name());
		phone.setText(user.get_phone());
		day.setText(user.get_day()+"");
		month.setText(user.get_month()+"");
		year.setText(user.get_year()+"");
		if(user.is_male())
			gender.setText("Nam");
		else gender.setText("Nữ");
		setStampt();
	}
	
	private void setStampt() {
		if(controller.isFriend(user)) {
			stamp1.setVisible(true);
			stamp2.setVisible(true);
			stamp3.setVisible(true);
			stamp4.setVisible(true);
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
			System.out.println("Error Friendinfo.FriendInfo");
		}
		Image imageLoader = new Image(file.toURI().toString());
		image.setImage(imageLoader);
		image.setPreserveRatio(true);
	}
}
