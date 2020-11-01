package Room;

import java.io.IOException;

import MessageWindow.ActivityController;
import Models.Room;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import roomelement.DivroomController;

public class RoomPane {
	private FXMLLoader loadDiv,loadChatRoom;
	private DivroomController divController;
	private ActivityController activityController;
	private Room room;
	private AnchorPane divRoom, activity;
	
	private VBox vBox;
	
	public RoomPane(Room room) {
		this.room = room;
		initRoom();
		initLayout();
		initController();
		update();
	}
	
	private void initRoom() {
		this.loadDiv = new FXMLLoader(getClass().getResource("/roomelement/Divroom.fxml"));
		this.loadChatRoom = new FXMLLoader(getClass().getResource("/activity/Activity.fxml"));
	}
	
	private void initLayout() {
		try {
			this.divRoom = loadDiv.load();
			this.activity = loadChatRoom.load();
		} catch (IOException e) {}
	}
	
	private void initController() {
		this.divController = loadDiv.getController();
		this.activityController = loadChatRoom.getController();
		this.activityController.setDiv(divController);
		this.activityController.setRoomModel(room);
	}
	
	private void update() {
		this.divController.setName(room.get_name());
		this.divController.setImage(room.get_avatar());
		this.activityController.update();
	}

	public DivroomController getDivController() {
		return divController;
	}

	public ActivityController getActivityController() {
		return activityController;
	}

	public AnchorPane getDivRoom() {
		return divRoom;
	}

	public AnchorPane getActivity() {
		return activity;
	}
	
	public void setvBox(VBox vBox) {
		this.vBox = vBox;
		this.activityController.setVBox(this.vBox);
	}
	
	public void setRoom(Room room) {
		this.room =room;
	}
	
	public Room getRoom() {
		return this.room;
	}
}
