package controller.subcontroller;

import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;


import model.sendmodel.FileInfo;
import model.sendmodel.Message;
import controller.Controller;
import controller.subcontroller.room.RoomController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class RoomHomeController implements Initializable {
	@FXML TextField sendTextfield;
	
	@FXML MenuButton menuSendButton;
	
	@FXML Label roomNameLabel, amountMemberLabel;
	
	@FXML Button sendButton, moreInfoButton;
	
	@FXML FlowPane chatBox;
	
	@FXML ImageView roomImage;
	
	@FXML ScrollPane scrollMessage;
	
	@FXML MenuItem sendImageItem, sendAudioItem, sendVideoItem, sendFileItem,sendDocumentItem;
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->   ATTRIBUTES    <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	
	private RoomController roomController;

	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->   INITIALIZE    <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setSendButton();
		sendImageItemAction();
		setMoreInfoButton();
	}
	
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->   MODULES    <--------------------------------
	*
	*-----------------------------------------------------------------------------------*/
	/** Set more information button *****************************************************/
	private void setMoreInfoButton(){
		moreInfoButton.setOnMouseClicked(value->{
			Controller.getInstance().showRoomInfo(roomController.getId());
		});
	}
	
	/** Set send button *****************************************************************/
	private void setSendButton(){
		sendButton.setOnMousePressed(value->{
			String messageData = sendTextfield.getText().toString();
			if(messageData.trim().length()>0){
				sendMessage(messageData,false);
				sendTextfield.setText("");
			}
		});
		
		sendTextfield.setOnKeyPressed(value->{
			String messageData = sendTextfield.getText().toString();
			if(value.getCode()==KeyCode.ENTER){
				sendMessage(messageData,false);
				sendTextfield.setText("");
			}
		});
	}
	/** Set room image ******************************************************************/
	private void setImageRoom(Image image){
		this.roomImage.setImage(image);
	};
	
	/** Send media **********************************************************************/
	private void sendMessage(String path, boolean isFile){
		Message message = new Message(Controller.getInstance().getUser(),roomController.getRoom().getId(),new FileInfo(path),isFile, LocalDateTime.now());
		Controller.getInstance().sendMessage(roomController.getId(),message);
		if(isFile)
			roomController.setMedia(message);
		else roomController.setMessage(message);
		
	}
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->     METHODS     <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	/** Some method waiting for coding **************************************************/
	private void sendImageItemAction(){
		sendImageItem.setOnAction(value->{
			Stage stage = new Stage();
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(
				      new FileChooser.ExtensionFilter("IMAGE","*.png","*.gif", "*.jpg"));
			
			fileChooser.setTitle("Open Resource File");
			File tmp = fileChooser.showOpenDialog(stage);
			if(tmp!=null)
				sendMessage(tmp.getPath(),true);
		});
		sendAudioItem.setOnAction(value->{
			Stage stage = new Stage();
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(
				      new FileChooser.ExtensionFilter("AUDIO","*.mp3","*.wav", "*.aac","*.ogg"));
			
			fileChooser.setTitle("Open Resource File");
			File tmp = fileChooser.showOpenDialog(stage);
			if(tmp!=null)
				sendMessage(tmp.getPath(),true);
		});
		
		sendDocumentItem.setOnAction(value->{
			Stage stage = new Stage();
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(
				      new FileChooser.ExtensionFilter("DOCUMENT","*.doc","*.docx", "*.exls","*.pptx","*.pdf","*.txt","*.html"));
			
			fileChooser.setTitle("Open Resource File");
			File tmp = fileChooser.showOpenDialog(stage);
			if(tmp!=null)
				sendMessage(tmp.getPath(), true);
		});
		
		sendVideoItem.setOnAction(value->{
			Stage stage = new Stage();
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(
				      new FileChooser.ExtensionFilter("VIDEO","*.mp4","*.mov", "*.wmv","*.flv","*.avi","*.mkv"));
			
			fileChooser.setTitle("Open Resource File");
			File tmp = fileChooser.showOpenDialog(stage);
			if(tmp!=null)
				sendMessage(tmp.getPath(),true);
		});
		
		sendFileItem.setOnAction(value->{
			Stage stage = new Stage();
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			File tmp = fileChooser.showOpenDialog(stage);
			if(tmp!=null) {
				sendMessage(tmp.getPath(),true);
			}
		});
	}

	public void changeRoomInfo(){};
	
	/** Call update information ********************************************************/
	public void callUpdate(String name,int amount, Image image){
		this.roomNameLabel.setText(name);
		setImageRoom(image);
		amountMemberLabel.setText(Integer.toString(amount));
	}
	
	/** Remove all message *************************************************************/
	public void removeAllMessage(){
		this.chatBox.getChildren().clear();
	}
	
	/** Upload message to panel ********************************************************/
	public void upMessage(AnchorPane message){
		chatBox.getChildren().add(message);
	}
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->     GETTER/SETTER     <-----------------------
	*
	*-----------------------------------------------------------------------------------*/
	/** Get chat box (flow pane) ********************************************************/
	public FlowPane getChatBox(){
		return this.chatBox;
	}
	
	/** Get scroll pane ******************************************************************/
	public ScrollPane getScrollMessage(){
		return this.scrollMessage;
	}
	
	/** Set parent controller (room controller object) for this class ********************/
	public void setParent(RoomController roomController){
		this.roomController = roomController;
	};
}
