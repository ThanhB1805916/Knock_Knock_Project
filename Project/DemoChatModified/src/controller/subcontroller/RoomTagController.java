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

public class RoomTagController implements Initializable {
	@FXML
		private Button moreButton, roomFillButton;
	
	@FXML
		private ImageView roomImage,moreImage;
	
	@FXML
		private Label roomName;
	
	@FXML
		private MenuItem seeRoomInfo,exitRoom;
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->     ATTRIBUTES     <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	private RoomController roomController;
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->     INITIALIZE     <--------------------------
	*
	*-----------------------------------------------------------------------------------*/
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setMoreButtonMotion();
		setContextMenuItem();
	}

	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->     MODULES     <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	/** Set image for room tag **********************************************************/
	private void setImageRoom(Image image){
		this.roomImage.setImage(image);
	};
	
	/** Set for more button listener ****************************************************/
	private void setMoreButtonMotion(){
		moreButton.setOnMouseEntered(event->{
			moreImage.setOpacity(0.5);
		});
		
		moreButton.setOnMouseExited(event->{
			moreImage.setOpacity(0.0);
		});
	}

	/** Set action listener of menu items ***********************************************/
	private void setContextMenuItem(){
		seeRoomInfo.setOnAction(action->{
			Controller.getInstance().showRoomInfo(roomController.getId());
		});
		
		exitRoom.setOnAction(action->{
			Controller.getInstance().leaveRoom(roomController.getId());
		});
	}
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->     METHODS     <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	/** Show room how when call *********************************************************/
	public void showFromRoomTag(MouseEvent event){
		roomController.showThisRoom();
	}
	
	/** Set data when the controller was initialized*************************************/
	public void setParent(RoomController roomController){
		this.roomController = roomController;
	}; 
	
	/** Call update when the data have changed ******************************************/
	public void callUpdate(String name, Image image){
		this.roomName.setText(name);
		setImageRoom(image);
	};
}
