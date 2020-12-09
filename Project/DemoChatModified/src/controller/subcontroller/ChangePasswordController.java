package controller.subcontroller;

import java.net.URL;
import java.util.ResourceBundle;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class ChangePasswordController implements Initializable{

    @FXML
    private Button cancel;

    @FXML
    private PasswordField oldPassword;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private Button update;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setButton();
	}
	
	private void setButton(){
		update.setOnMouseClicked(value->{
			if(checkValid()){
				Controller.getInstance().changePassword(confirmPassword.getText());
				oldPassword.setText("");
				newPassword.setText("");
				confirmPassword.setText("");
			}
		});
		
		cancel.setOnMouseClicked(value->{
			((Stage) cancel.getScene().getWindow()).close();
		});
		
	}
	
	private boolean checkValid(){
		if(oldPassword.getText().trim().length()>0 && newPassword.getText().trim().length()>0 && confirmPassword.getText().trim().length()>0)
			if(oldPassword.getText().equalsIgnoreCase(Controller.getInstance().getUser().getPassword()))
				if(confirmPassword.getText().equalsIgnoreCase(newPassword.getText())){
					if(Controller.getInstance().alertConfirmation("Bạn có chắc muốn đổi mật khẩu","Xác nhận",null))
						return true;
				}
				else Controller.getInstance().alertNotify(AlertType.ERROR, "Mật khẩu không khớp","Lỗi",null);
			else Controller.getInstance().alertNotify(AlertType.ERROR, "Mật khẩu cũ không đúng","Lỗi",null);
		else 
			Controller.getInstance().alertNotify(AlertType.ERROR, "Vui lòng nhập đủ thông tin!","Cảnh báo!",null);
		return false;
	}
}
