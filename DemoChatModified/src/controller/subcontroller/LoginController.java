package controller.subcontroller;

import java.net.URL;
import java.util.ResourceBundle;

import model.sendmodel.Person;
import controller.Controller;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginController implements Initializable {
	
	@FXML 
		private AnchorPane firstPane, loginPane, signinPane;
	
	@FXML 
		private Button loginButton, returnButton, signinButton, startButton;
	
	@FXML 
		private Label banner;
	
	@FXML 
		private PasswordField passwordLogin, passwordSignin, confirmPasswordSiginin;
	
	@FXML 
		private TextField nameSignin, phoneSignin, usernameLogin, usernameSignin;
	
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->   INITIALIZE    <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Controller.getInstance().setLoginController(this);
	}
	
	/*----------------------------------------------------------------------------------
	*
	*------------------------------------->   LISTENER    <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	/** The button on the first pane pressed*********************************************/
	public void startButton_Pressed(MouseEvent event){
		TranslateTransition transition = new TranslateTransition(Duration.ONE);
		transition.setAutoReverse(false);
		transition.setNode(firstPane);
		transition.setFromX(firstPane.getLayoutX());
		transition.setDuration(Duration.millis(3000));
		transition.setToX(firstPane.getLayoutX()+800);
		transition.play();
	}
	
	/** The button on the first pane pressed*********************************************/
	public void signinButton_Pressed(MouseEvent event){
		if(checkIsValid(usernameSignin.getText()) && checkIsValid(passwordSignin.getText()) && checkIsValid(nameSignin.getText()) && checkIsValid(phoneSignin.getText())){
			if (passwordSignin.getText().equals(confirmPasswordSiginin.getText())) {
				Person person = new Person(0, usernameSignin.getText(),
						passwordSignin.getText(), nameSignin.getText(), true,
						phoneSignin.getText(), null, null);
				Controller.getInstance().callUserVerify(person);
			}
		}
		else Controller.getInstance().alertNotify(AlertType.ERROR,"Vui lòng điền đầy đủ thông tin!","Lỗi", null);
	}
	
	/** The blue button on the login pane pressed ****************************************/
	public void changeToSigninButton_Pressed(MouseEvent event) {
		TranslateTransition transition = new TranslateTransition(Duration.ONE);
		transition.setAutoReverse(false);
		transition.setNode(loginPane);
		transition.setFromX(loginPane.getLayoutX());
		transition.setDuration(Duration.millis(3000));
		transition.setToX(loginPane.getLayoutX() - 800);
		transition.play();
	}

	/** The return button clicked to return to login pane ********************************/
	public void returnButton_Pressed(MouseEvent event) {
		returnToLoginPane();
	}

	/** The login button clicked to verify this account **********************************/
	public void loginButton_Pressed(MouseEvent event) {
		if(checkIsValid(usernameLogin.getText()) && checkIsValid(passwordLogin.getText()))
		Controller.getInstance().callUserAuthentic(usernameLogin.getText(),
				passwordLogin.getText());
		else
			Controller.getInstance().alertNotify(AlertType.ERROR,"Vui lòng điền đầy đủ thông tin!","Lỗi", null);

	}
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->   METHODS    <--------------------------------
	*
	*-----------------------------------------------------------------------------------*/
	
	/** Return to the login pane ********************************************************/
	public void returnToLoginPane() {
		TranslateTransition transition = new TranslateTransition(Duration.ONE);
		transition.setAutoReverse(false);
		transition.setNode(loginPane);
		transition.setFromX(loginPane.getLayoutX() - 800);
		transition.setDuration(Duration.millis(3000));
		transition.setToX(loginPane.getLayoutX());
		transition.play();
	}

	/** Close this windows ***************************************************************/
	public void closeWindows() {
		Stage current = (Stage) firstPane.getScene().getWindow();
		current.close();
	}
	
	private boolean checkIsValid (String text){
		if(text.trim().length()> 0) return true;
		return false;
	}
}
