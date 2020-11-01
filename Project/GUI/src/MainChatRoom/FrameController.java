package MainChatRoom;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import Menu.MenuController;
import Models.FileInfo;
import Models.Room;
import Models.User;
import Room.RoomPane;
import RoomWindow.ListRoomController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class FrameController implements Initializable {

	@FXML
	FlowPane frame;

	@FXML
	AnchorPane backgroundPane; // tấm panel nền

	private VBox navigation; // thanh navigation bên trái

	private AnchorPane activity; // panel chứa các phòng chat trực quan

	private AnchorPane listRoomPane; // panel thanh xem thông tin phòng chat, bạn bè dạng list

	private FXMLLoader loaderlist, loadernavigation; // thanh loader (không cần quan tâm)

	private ListRoomController listController; // controller của thanh điều khiển phòng

	private MenuController navigationController; // controller của navigation

	private ArrayList<AnchorPane> roomAdded = new ArrayList<>(); // danh sách phòng panel

	private ArrayList<User> listFriend = new ArrayList<>(); // danh sách bạn bè

	private ArrayList<Room> listRoom = new ArrayList<>(); // danh sách các phòng (visual) tổng listroom đưa vào đây
	
	private HashMap<Room, RoomPane> hashCurrentRoom = new HashMap<>(); // danh sách các phòng tạm thời và có tin nhắn
 
	private User myUser;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	//-----------------------------------------------------------------------------------------------------------
		FileInfo ava1 = new FileInfo("resources/tmps/anh-avatar-dep.jpg");
		FileInfo ava2 = new FileInfo("");
		FileInfo ava3 = new FileInfo("");
		FileInfo ava4 = new FileInfo("");
		FileInfo ava5 = new FileInfo("");
		FileInfo ava6 = new FileInfo("");

		listFriend.add(new User(425, "Lê Giao", "2000-11-05", "0987072222", false, "legiao", "123", ava1));
		listFriend.add(new User(665, "Thạch Chí Tâm", "2000-07-09", "0907070422", true, "firefly", "123", ava2));
		listFriend.add(new User(456, "Minh Thông", "1999-06-12", "0987654321", true, "chaubathong", "123", ava3));
		listFriend.add(new User(888, "Vy Vy", "2000-03-31", "6868685843", false, "vovy", "123", ava4));
		listFriend.add(new User(127, "Thành Đạt", "2000-08-15", "1246843333", true, "mathew", "123", ava5));
		listFriend.add(new User(120, "Minh Thư", "2000-11-22", "6758493922", false, "minhthu", "123", ava1));
		listFriend.add(new User(840, "Nguyễn Thắm", "2000-08-27", "3425165786", false, "thambuoi", "123", ava2));
		listFriend.add(new User(973, "Công Công", "2000-02-28", "0909988767", true, "congle", "123", ava5));
		listFriend.add(new User(575, "Quỳnh Đức", "2000-08-30", "6733445533", true, "ducquynh", "123", ava3));
		listFriend.add(new User(253, "Văn Thọ", "2001-02-27", "8584843828", true, "thotho", "123", ava1));
		listFriend.add(new User(537, "Thành Nhân", "1998-07-13", "8473625485", true, "nhanduyen", "123", ava3));
		listFriend.add(new User(287, "Khả Ái", "2005-09-19", "9685473621", false, "nguyenngoc", "123", ava2));
		listFriend.add(new User(894, "Lê Khôi", "2004-10-23", "4678362522", true, "khoinguyen", "123", ava5));
		listFriend.add(new User(654, "Minh Ánh", "2000-04-12", "0908070533", false, "minhanh", "123", ava5));
		listFriend.add(new User(135, "Nguyệt Nga", "2000-01-15", "573722222", false, "nguyetnga", "123", ava4));

		myUser = new User(665, "Thạch Chí Tâm", "2000-07-09", "0907234545", true, "firefly", "12345", ava4);
	
		
		//-----------------------------------------------------------------------------------------------------------
		
		initRoom(); // initRoom line 66
		frame.widthProperty().addListener(function -> {
			refeshFrame(); // refesh line 44
		});
		frame.heightProperty().addListener(function -> {
			refeshFrame(); // refesh line 44
		});

		listController = loaderlist.getController();
		listController.getContext(this);

		navigationController = loadernavigation.getController();
		navigationController.setController(this);

		listController.updateFriend(listFriend);
		listController.updateRoom(listRoom);
		//----Cập nhật danh sách tất cả phòng---------
		Room room = new Room(1234,"Phòng mới", ava6, listFriend);
		listRoom.add(room);
		updateRoomFrame();
	}

//---cập nhật bạn bè-------------------------
	public void updateFriendFrame() {
		listController.updateFriend(listFriend);
	}

//---cập nhật phòng--------------------------
	public void updateRoomFrame() {
		listController.updateRoom(listRoom);
	}

//---cập nhật avatar-------------------
	public void updateAvatar() {
		navigationController.updateAvatar();
	}

//---xóa bạn khỏi danh sách------------------
	public void removeFriend(User user) {
		listFriend.remove(user);
		updateFriendFrame();
	}

//---thêm panel tin nhắn vào activity--------
	public void addRoomPane(AnchorPane newRoomPane) {
		newRoomPane.setVisible(true);
		AnchorPane.setTopAnchor(newRoomPane, 0.0);
		AnchorPane.setRightAnchor(newRoomPane, 0.0);
		AnchorPane.setLeftAnchor(newRoomPane, 0.0);
		AnchorPane.setBottomAnchor(newRoomPane, 0.0);

		this.roomAdded.add(newRoomPane);
		this.activity.getChildren().add(newRoomPane);
	}
	
//---get/set phòng chat----------------------
	public ArrayList<AnchorPane> getActivityList() {
		return roomAdded;
	}

	public void addActivityList(AnchorPane anhorPane) {
		roomAdded.add(anhorPane);
	}

//---getFriendList---------------------------
	public ArrayList<User> getFriendList() {
		return listFriend;
	}

//---Lấy panel nền của ứng dụng chat---------
	public AnchorPane getBackgroundPane() {
		return backgroundPane;
	}

//---Tạo phòng chat mới---------------------- i/O có thể gọi cái này
	public void createNewRoom(RoomPane roomPane) {
		listController.addNewDiv(roomPane);
		addListRoom(roomPane.getRoom());
		hashCurrentRoom.put(roomPane.getRoom(), roomPane);
	}
//------------gọi phòng chat cũ đã xóa
	public void reupOldRoom(RoomPane roomPane) {
		listController.addNewDiv(roomPane);
		hashCurrentRoom.put(roomPane.getRoom(), roomPane);
		roomPane.getDivController().showorhide();
	}
	
//---- Rời khỏi phòng chat---------*upate thoát server*-----------------------
	public void outOfRoom(Room room) {
		listRoom.remove(room);
		if(hashCurrentRoom.containsKey(room)) {
			listController.removeRoomMessage(hashCurrentRoom.get(room).getDivRoom());
			activity.getChildren().remove(hashCurrentRoom.get(room).getActivity());
			hashCurrentRoom.remove(room);
		}
		listController.updateRoom(listRoom);
	}
	
//---Kiểm tra thành viên phải là bạn bè không
	public boolean isFriend(User user) {
		return listFriend.contains(user);
	}
	
//----Xóa phòng tin nhắn *xóa sương suong//
	public void removeMessageRoom(Room room) {
		listController.removeRoomMessage(hashCurrentRoom.get(room).getDivRoom());
		activity.getChildren().remove(hashCurrentRoom.get(room).getActivity());
		hashCurrentRoom.remove(room);
	}

//---set/get thông tin chủ tài khoản---------
	public User getMyUser() {
		return myUser;
	}

	public void setMyUser(User user) {
		this.myUser = user;
	}

//---Thêm phòng vào danh sách ---------------
	public void addListRoom(Room room) {
		listRoom.add(room);
		updateRoomFrame();
	}

//--- Lấy danh sách phòng ------------
	public ArrayList<Room> getRoomList() {
		return this.listRoom;
	}

//---Lấy controller của thanh quản lí phòng chat----
	public ListRoomController getListRoomController() {
		return listController;
	}
	
//---lấy phòng có trong danh sách hiện tại
	public RoomPane getCurrentRoom(Room room) {
		if(hashCurrentRoom.containsKey(room)) return hashCurrentRoom.get(room);
		else return null;
	}
//Private function/method here-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//---------------Refesh Frame-----------------------------------------------------------------------------
	private boolean show = false;

	private void refeshFrame() {
		Platform.runLater(() -> {
			navigation.setPrefHeight(Math.round(frame.getHeight() * 10) / 10);
			listRoomPane.setPrefHeight(frame.getHeight());
		});

		if (frame.getWidth() < 750) {
			frame.getChildren().remove(navigation);
			show = true;
			listRoomPane.setPrefWidth(70);
			activity.setPrefSize(Math.round((frame.getWidth() - 70) * 10) / 10 - 1, frame.getHeight());
		} else {
			if (show) {
				frame.getChildren().add(0, navigation);
				listRoomPane.setPrefWidth(223);
				show = false;
			}
			activity.setPrefSize(Math.round((frame.getWidth() - 283) * 10) / 10 - 1, frame.getHeight());
		}
	}
	
	

	// ---------------------Init------------------------------------------------------------------
	private void initRoom() {
		try {
			loadernavigation = new FXMLLoader(getClass().getResource("/navigation/Navigation.fxml"));
			navigation = loadernavigation.load();
			loaderlist = new FXMLLoader(getClass().getResource("/listroom/ListRoom.fxml"));
			listRoomPane = loaderlist.load();
			activity = new AnchorPane();
			activity.setStyle("-fx-background-color:black");
		} catch (IOException e) {

		}

		Platform.runLater(() -> {
			frame.getChildren().add(navigation);
			frame.getChildren().add(listRoomPane);
			frame.getChildren().add(activity);
		});
	}
}
