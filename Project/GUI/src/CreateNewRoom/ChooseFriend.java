package CreateNewRoom;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import Models.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ChooseFriend implements Initializable{
	@FXML RadioButton radio;
	@FXML ImageView avatar;
	@FXML Label name;
	@FXML Button fillBtn;
	private User user;
	private boolean flag = true;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void getUser(User user) {
		this.user = user;
		init();
	}
	
	public void mouseClicked(MouseEvent event) {
		if(flag) {
			radio.setSelected(true);
			flag =false;
		}
		else {
			radio.setSelected(false);
			flag =true;
		}
	}
	
	public boolean getChecked() {
		return radio.isSelected();
	}
	
	private void init() {
		name.setText(this.user.get_name());
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
			System.out.println("Error ChooseFiend.java");
		}
		Image imageLoader = new Image(file.toURI().toString());
		avatar.setImage(imageLoader);
		avatar.setPreserveRatio(true);
	}

}
