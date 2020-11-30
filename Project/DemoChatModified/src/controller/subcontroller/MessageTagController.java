package controller.subcontroller;

import java.net.URL;
import java.util.ResourceBundle;

import controller.Controller;
import controller.subcontroller.room.RoomController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MessageTagController implements Initializable{
	@FXML
		private Button tagButton,moreButton;
	
	@FXML
		private Label currentMessage, userName;
	
	@FXML
		private ImageView userImage, moreImage;	
	
	@FXML
	private MenuItem seeRoomInfo,removeMessageTag;
	
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
		setMoreButton();
		setContextMenuItem();
	}	
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->     LISTENER    <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	
	/** Show room home when user clicked to message tag *********************************/
	public void showFromMessageTag(MouseEvent event){
		roomController.showThisRoom();
	}
	
	/*----------------------------------------------------------------------------------
	*
	*------------------------------------->   MODULES    <------------------------------
	*
	*-----------------------------------------------------------------------------------*/

	/** Set for more button**************************************************************/
	private void setMoreButton(){
		moreButton.setOnMouseEntered(event->{
			moreImage.setOpacity(0.5);
		});
		moreButton.setOnMouseExited(event->{
			moreImage.setOpacity(0.0);
		});
	}

	/** Set context menu item of more button ********************************************/
	private void setContextMenuItem(){
		seeRoomInfo.setOnAction(action->{
			Controller.getInstance().showRoomInfo(roomController.getId());
		});
		
		removeMessageTag.setOnAction(action->{
			Controller.getInstance().removeMessage(roomController);
		});
	}
	
	/** Set image for message tag   *****************************************************/
	private void setImageRoom(Image image){
		this.userImage.setImage(image);
	};

	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->    METHODS      <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	/** Set parent controller (room controller object) for this class *******************/
	public void setParent(RoomController roomController){
		this.roomController = roomController;
	};
	
	/** Update current message **********************************************************/
	public void updateCurrentMessage(String message){
		this.currentMessage.setText(message);
	};
	
	/** Update information for message tag **********************************************/
	public void callUpdate(String name, Image image){
		this.userName.setText(name);
		setImageRoom(image);
	};
}
