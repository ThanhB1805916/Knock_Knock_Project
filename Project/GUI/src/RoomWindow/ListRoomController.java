package RoomWindow;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import CreateNewRoom.CreateController;
import MainChatRoom.FrameController;
import Models.Room;
import Models.User;
import Room.RoomPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
public class ListRoomController implements Initializable {
	@FXML TextField searchTxt;
	@FXML Button searchBtn;
	@FXML AnchorPane listRoom;
	@FXML ScrollPane scrollMess,scrollRoom, scrollFriend,scrollsearch;
	@FXML VBox list,boxFriend, boxRoom,boxsearch;
	
	@FXML Button addGroup;

	private FrameController controller;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		alignListRoom();
	}
	
	private void alignListRoom() {
		listRoom.widthProperty().addListener(function->{
			if(listRoom.getWidth()<=80) {
				searchTxt.setVisible(false);
				searchBtn.setVisible(false);
			}
			else {  
				searchTxt.setVisible(true);
				searchBtn.setVisible(true);
			}
		});
	}
	
	public void getElementList(AnchorPane pane) {
		list.getChildren().add(0,pane);
	}
	
	public void getContext(FrameController controller) {
		this.controller = controller;
	}
	
	public void adding(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CreateNewRoom/CreateRoom.fxml"));
		AnchorPane anchorPane = loader.load();
		CreateController createController = loader.getController();
		AnchorPane.setLeftAnchor(anchorPane,0.0);
		AnchorPane.setRightAnchor(anchorPane,0.0);
		AnchorPane.setBottomAnchor(anchorPane,0.0);
		AnchorPane.setTopAnchor(anchorPane,0.0);
		controller.getBackgroundPane().getChildren().add(anchorPane);
		createController.getContext(controller);
	}
	
	public void addNewDiv(RoomPane room) {
		getElementList(room.getDivRoom());
		controller.addRoomPane(room.getActivity());
		room.getActivityController().getContext(controller);
		room.getDivController().setActivity(controller,  room.getActivity(), room.getRoom());
		room.setvBox(list); 
	}
	
	public void setOnScrollMess() {
		scrollMess.setVisible(true);
		scrollFriend.setVisible(false);
		scrollRoom.setVisible(false);
	}
	
	public void setOnScrollFriend() {
		scrollMess.setVisible(false);
		scrollFriend.setVisible(true);
		scrollRoom.setVisible(false);
	}
	
	public void setOnScrollRoom() {
		scrollMess.setVisible(false);
		scrollFriend.setVisible(false);
		scrollRoom.setVisible(true);
	}
	boolean flag=true;
	public void textFocus(MouseEvent event) {
		if(flag) {
			scrollMess.setVisible(false);
			scrollFriend.setVisible(true);
			scrollRoom.setVisible(false);
			flag = false;
		}
	}
	public void showSearch(KeyEvent event) {
		if(searchTxt.getText().toString().length()==0 && !flag) {
			scrollMess.setVisible(false);
			scrollFriend.setVisible(true);
			scrollRoom.setVisible(false);
			scrollsearch.setVisible(false);
			flag = true;
		}
	}
	
	public void removeRoomMessage(AnchorPane removeRoom) {
		list.getChildren().remove(removeRoom);
	}
	
	public void searching(MouseEvent event) {
		boxsearch.getChildren().clear();
		if(searchTxt.getText().trim().length()>0) {
			for(int i=0; i<controller.getFriendList().size(); i++)
				if(controller.getFriendList().get(i).get_name().lastIndexOf(searchTxt.getText())!=-1 || controller.getFriendList().get(i).get_phone().lastIndexOf(searchTxt.getText())!=-1)
					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("FriendDiv.fxml"));
						AnchorPane anchorPane = loader.load();
						FriendDivController friendcontroller = loader.getController();
						friendcontroller.setUser(controller.getFriendList().get(i));
						friendcontroller.setContext(controller);
						boxsearch.getChildren().addAll(anchorPane);
					} catch (IOException e) {
					}
			scrollMess.setVisible(false);
			scrollFriend.setVisible(false);
			scrollRoom.setVisible(false);
			scrollsearch.setVisible(true);
		}
	}
	
	public void updateFriend(ArrayList<User> arrayList){
		boxFriend.getChildren().clear();
		for(int i=0; i<arrayList.size(); i++) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("FriendDiv.fxml"));
				AnchorPane anchorPane = loader.load();
				FriendDivController friendcontroller = loader.getController();
				friendcontroller.setUser(arrayList.get(i));
				friendcontroller.setContext(controller);
				boxFriend.getChildren().addAll(anchorPane);
			} catch (IOException e) {
			}
		}
	}
	
	public void updateRoom(ArrayList<Room> rooms){
		boxRoom.getChildren().clear();
		for(int i=0; i<rooms.size(); i++) 
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("DivRoom.fxml"));
				AnchorPane anchorPane = loader.load();
				RoomDivController roomcontroller = loader.getController();
				roomcontroller.setRoom(rooms.get(i));
				roomcontroller.setContext(controller);
				boxRoom.getChildren().addAll(anchorPane);
			} catch (IOException e) {}
	} 
}

