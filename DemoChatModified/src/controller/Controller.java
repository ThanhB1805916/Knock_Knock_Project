package controller;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.communication.CPackage;
import model.communication.Name;
import model.communication.Request;
import model.communication.Type;
import model.sendmodel.FileInfo;
import model.sendmodel.LoginModel;
import model.sendmodel.Message;
import model.sendmodel.Person;
import model.sendmodel.Room;
import connector.Connector;
import controller.subcontroller.BackgroundController;
import controller.subcontroller.FriendTagController;
import controller.subcontroller.LoginController;
import controller.subcontroller.info.FriendInfoController;
import controller.subcontroller.info.MemberInfoController;
import controller.subcontroller.info.RoomInfoController;
import controller.subcontroller.room.RoomController;

public class Controller {
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->   ATTRIBUTES    <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	public static Controller controller;
	
	public Connector connector;
	
	private Person person;
	
	private ArrayList<Person> friendList = new ArrayList<Person>();
	
	private ArrayList<Room> roomList =new ArrayList<Room>();
	
	private ArrayList<RoomController> messageList = new ArrayList<RoomController>();
	
	private HashMap<Integer, Room> currentMessageMap = new HashMap<Integer, Room>();
	
	private HashMap<Integer, RoomController> roomControllerMap = new HashMap<Integer, RoomController>();
	
	private HashMap<Integer, Parent> friendTagMap = new HashMap<Integer, Parent>();
	
	private BackgroundController backgroundController;
	
	private LoginController loginController;

	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->   CONSTRUCTORS    <---------------------------
	*
	*-----------------------------------------------------------------------------------*/
	
	/** Constructor none parameter ******************************************************/
	private Controller() {
		connector =  new Connector();
		connector.start();
	}
	
	/*----------------------------------------------------------------------------------
	*
	*------------------------------->   GETTER/SETTER SOMETHING    <--------------------
	*
	*-----------------------------------------------------------------------------------*/
	/** Set background controller *******************************************************/
	public void setBackgroundController(
			BackgroundController backgroundController) {
		this.backgroundController = backgroundController;
	}

	/** Set login controller ************************************************************/
	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}
	
	/** Get user ************************************************************************/
	public Person getUser() {
		return this.person;
	}
	/** Set user ************************************************************************/
	public void setUser(Person person) {
		this.person = person;
	}

	/** Set friend list *****************************************************************/
	public void setFriendList(ArrayList<Person> list) {
		this.friendList = list;
	}

	/** Get friend list *****************************************************************/
	public ArrayList<Person> getFriendList() {
		return this.friendList;
	}
	
	/** Set room list *******************************************************************/
	public void setRoomList(ArrayList<Room> list) {
		this.roomList = list;
	}
	
	/** Get room list *******************************************************************/
	public ArrayList<Room> getRoomList() {
		return this.roomList;
	}

	/** Get room object by id ***********************************************************/
	public Room getRoomById(int id) {
		for (Room room : roomList)
			if (id == room.getId())
				return room;
		return null;
	}
	
	/**Get user id **********************************************************************/
	public Integer getUserId() {
		if (person != null)
			return this.person.getId();
		return null;
	}

	/** Get friend or user object by id *************************************************/
	public Person getPersonById(int id) {
		if (id == person.getId())
			return person;
		for (Person person : friendList)
			if (id == person.getId())
				return person;
		return null;
	}

	/** get room controller object by id ************************************************/
	public RoomController getRoomControllerById(int roomId) {
		return roomControllerMap.get(roomId);
	}

	/** Get image object of a person by id **********************************************/
	public Image getImageOfPerson(int id) {
		Person tmp = getPersonById(id);
		tmp.getAvatar().getFile("tmp/");
		return new Image(new File("tmp/" + tmp.getAvatar().getName()).toURI()
				.toString());
	}

	/** Get person object (member's room)  by room id and member id *********************/
	public Person getMemberById(int roomId, int memberId) {
		Room room = getRoomById(roomId);
		if (room != null)
			for (Person person : room.getMembers())
				if (person.getId() == memberId)
					return person;
		return null;
	}
	
	/*----------------------------------------------------------------------------------
	*
	*---------------------------------->   METHODS    <---------------------------------
	*
	*-----------------------------------------------------------------------------------*/

	/** Instance*************************************************************************/
	public static Controller getInstance(){
		if(controller == null)
			controller  = new Controller();
		return controller;
	}

	/** Access to the main chat application *********************************************/
	public void openAppChat(){
		Platform.runLater(()->{
			try{
				Stage stage = new Stage();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/background/background.fxml"));
				Parent parent = loader.load();
				Scene scene = new Scene(parent);
				stage.setScene(scene);
				stage.setTitle("Knock Knock");
				stage.setMinHeight(537.4);
				stage.setMinWidth(600);
				stage.setOnCloseRequest(value->{
					System.exit(0);
				});
				stage.show();
				loginController.closeWindows();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		});
	}

	/** Return to login windows when user logout*****************************************/
	public void openLoginWindows(){
		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login/login.fxml"));	
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setTitle("Knock Knock - Login");
			stage.setResizable(false);
			stage.setOnCloseRequest(value->{
				System.exit(0);
			});
			stage.show();
			backgroundController.closeWindows();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/** Notify alert ********************************************************************/
	public boolean alertNotify(AlertType type, String header, String title,
			String content) {
		Platform.runLater(()->{
			Alert alert = new Alert(type);
			alert.setHeaderText(header);
			alert.setTitle(title);
			if (content != null)
				alert.setContentText(content);
			alert.show();
		});
		return true;
		
	}
	
	/** Confirm alert *******************************************************************/
	public boolean alertConfirmation(String header, String title, String content){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText(header);
		alert.setTitle(title);
		if(content!=null) alert.setContentText(content);
		Optional<ButtonType> type = alert.showAndWait();
		if(type.get() == ButtonType.OK)
			return true;
		return false;
	}
	
	/** Show room information ***********************************************************/
	private RoomInfoController roomInfoController; // just is a flag, but don't delete it
	public void showRoomInfo(int id){
		if(getRoomById(id)!=null)
			try{
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/subinfo/roominfo.fxml"));
				Parent parent = loader.load();
				roomInfoController = loader.getController();
				roomInfoController.setInfo(id);
				Scene scene = new Scene(parent);
				Stage stage = new Stage(StageStyle.UTILITY);
				stage.setTitle("Room information "+Controller.getInstance().getRoomById(id).getName());
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			}catch(Exception e){
				e.printStackTrace();
			}
		else
			alertNotify(AlertType.INFORMATION,"Room's not available!","Error",null);
	}
	
	/** Show main user information ******************************************************/
	public void showUserInfo(){
		try {
			Stage stage = new Stage(StageStyle.UTILITY);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/subinfo/userinfo.fxml"));	
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setTitle("Information of "+person.getName());
			stage.setResizable(false);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Show friend information *********************************************************/
	public void showFriendInfo(int id) {
		if (getPersonById(id) != null)
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource(
						"/view/subinfo/friendinfo.fxml"));
				Parent parent = loader.load();
				FriendInfoController friendController = loader.getController();
				friendController.setInfo(id);
				Scene scene = new Scene(parent);
				Stage stage = new Stage(StageStyle.UTILITY);
				stage.setTitle("Thông tin của "+ getPersonById(id).getName());
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		else
			alertNotify(AlertType.ERROR,"Bạn bè không tồn tại!","Error", null);
	}
	
	/** Show member of a room information panel *****************************************/
	public void showMemberInfo(int memberId, int roomId) {
		if (getMemberById(roomId, memberId)!= null)
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource(
						"/view/subinfo/memberinfo.fxml"));
				Parent parent = loader.load();
				MemberInfoController memberController = loader.getController();
				memberController.setInfo(getMemberById(roomId, memberId), roomId);
				memberController.setDeleteButton(roomInfoController);
				Scene scene = new Scene(parent);
				Stage stage = new Stage(StageStyle.UTILITY);
				stage.setTitle("Thông tin của "+  getMemberById(roomId, memberId).getName());
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		else
			alertNotify(AlertType.INFORMATION,"Thành viên không tồn tại!","Error!", null);
	}
	
	/** Show change password panel ******************************************************/
	public void showChangePassword(){
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/changepassword/changepassword.fxml"));
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			Stage stage = new Stage(StageStyle.UTILITY);
			stage.setTitle("Đổi mật khẩu");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** Show create room panel **********************************************************/
	public void showCreateRoom(){
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/createroom/createroom.fxml"));
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			Stage stage = new Stage(StageStyle.UTILITY);
			stage.setTitle("Tạo phòng!");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** Show message tag and home room when clicked from room tag ***********************/
	public void showRoomCaller(int id) {
		for (RoomController roomController : messageList) 
			roomController.getRoomHome().setVisible(false);
		if(!inCurrentMessage(id))
			addPartElse(roomControllerMap.get(id));
		roomControllerMap.get(id).getRoomHome().setVisible(true);
	}
	

	/** Authentic that is a friend? *****************************************************/
	public boolean isFriend(int id){
		for (Person person : friendList)
			if(id==person.getId())
				return true;
		return false;
	}

	/** Add message tag for friend flow pane of background controller object*************/ 
	public boolean addMessageTag(Parent messageTag){
		if(messageTag!=null)
			try{
				backgroundController.addToMessageFlowPane(messageTag);
				return true;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
		return true;
	}
	
	/** Remove message tag to background controller object ******************************/ 
	public boolean removeMessageTag(Parent messageTag){
		if(messageTag!=null)
			try{
				backgroundController.removeToMessageFlowPane(messageTag);
				return true;
			}
			catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		return true;
	}

	/** Add room tag to background controller object ************************************/ 
	public boolean addRoomTag(Parent roomTag){
		try{
			backgroundController.addToRoomFlowPane(roomTag);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/** Remove room tag to background controller object *********************************/ 
	public void removeRoomTag(Parent parent){
		backgroundController.removeToRoomFlowPane(parent);
	}
	
	/** Add friend tag to background controller object **********************************/ 
	public boolean addFriendTag(int id){
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/tag/friendtag/friendtag.fxml"));
			Parent friendTag =loader.load();
			FriendTagController friendTagController = loader.getController();
			friendTagController.setFriendID(id);
			friendTagMap.put(id,friendTag);
			backgroundController.addToFriendFlowPane(friendTag);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/** Remove friend tag background controller object **********************************/ 
	public void removeFriendTag(Parent parent){
		backgroundController.removeToFriendFlowPane(parent);
	}
	
	/** Add room home to background controller object************************************/
	public boolean addRoomHome(Parent roomHome) {
		if(roomHome!=null)
			try {
				backgroundController.addToRightPane(roomHome);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		return false;
	}
	
	/** Remove room home to background controller object ********************************/ 
	public boolean removeRoomHome(Parent parent){
		if(parent!=null)
			try{
				backgroundController.removeToRightPane(parent);
				return true;
			}
			catch(Exception e){
				e.printStackTrace();
				return false;
			}
		return false;
	}
	
	/** Add friend tag to background controller *****************************************/
	public void addTagToFlowPane(String text){
		for (Person person : friendList)
			if(person.getName().toLowerCase().lastIndexOf(text.toLowerCase())>=0)
				backgroundController.addToSearchFlowPane(friendTagMap.get(person.getId()));
	}
	
	/** Add new room ********************************************************************/ 
	public void addRoom(Room room){
		RoomController roomController = new RoomController(room);
		addRoomTag(roomController.getRoomTag());
		if(room.getMessages()!=null && room.getMessages().size()!=0){
			currentMessageMap.put(room.getId(), room);
			messageList.add(roomController);
			addMessageTag(roomController.getMessageTag());
			addRoomHome(roomController.getRoomHome());
			initMessage(room, roomController);
		}
		roomList.add(room);
		roomControllerMap.put(room.getId(), roomController);
	}
	
	/** Add room to current message map when room have message **************************/
	public void addToCurrentMessageMap(RoomController roomController){
		currentMessageMap.put(roomController.getRoom().getId(), roomController.getRoom());
		messageList.add(roomController);
		initMessage(roomController.getRoom(), roomController);
	}
	
	/** Update user information *********************************************************/
	private Person node = null;
	public void updateUserInfo(String day, String month,
			String year, boolean gender, String phone,
			String name, FileInfo file) {
		Person tmp = new Person(person);
		tmp.setDateofbirth(LocalDate.of(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day)));
		tmp.setAvatar(file);
		tmp.setMale(gender);
		tmp.setPhonenumber(phone);
		tmp.setName(name);		
		node = tmp;
		Platform.runLater(()->{
			callUpdateUserInfo(tmp);	
		});
	} 
	
	/** Add some else to GUI when caller ************************************************/
	public void addPartElse(RoomController roomController){
		addMessageTag(roomController.getMessageTag());
		addRoomHome(roomController.getRoomHome());
		messageList.add(roomController);
	}
	
	/** Remove some else to GUI when caller *********************************************/
	public void removePartElse(RoomController roomController){
		removeMessageTag(roomController.getMessageTag());
		removeRoomHome(roomController.getRoomHome());
		getRoomById(roomController.getId()).getMessages().clear();
		messageList.remove(roomController);
	}
	
	/** Leave room **********************************************************************/
	public void leaveRoom(int roomId){
		removePartElse(roomControllerMap.get(roomId));
		backgroundController.removeToRoomFlowPane(roomControllerMap.get(roomId).getRoomTag());
		roomControllerMap.remove(roomId);
		roomList.remove(getRoomById(roomId));
		callLeaveRoom(roomId);
	}
	
	/** Change password of user *********************************************************/
	public void changePassword(String text){
		person.setPassword(text);
		callChangePassword(text);
		alertNotify(AlertType.INFORMATION,"Bạn đã đổi mật khẩu","Thông báo", null);
	}
	
	/** Remove message ******************************************************************/
	public void removeMessage(RoomController roomController){
		removePartElse(roomController);
		roomController.getRoomHomeController().removeAllMessage();
		roomController.getMessageTagController().updateCurrentMessage("");
		callRemoveMessage(roomController.getId());
	}
	
	/** Remove friend *******************************************************************/
	public void removeFriend(int id){
		if(friendList.contains(getPersonById(id))){
			String name = getPersonById(id).getName();
			if(alertConfirmation("Bạn có chắc muốn xóa "+name+" ra khỏi danh sách bạn bè?","Xác nhận", null)){
				setRemoveFriend(id);
				callRemoveFriend(id);
			}
		}
		else
			alertNotify(AlertType.INFORMATION,"Bạn bè không tồn tại.\nVui lòng thử lại sau!","Thất bại",null);
	}
	
	/** Initialize message for room when load that room *********************************/
	public boolean initMessage(Room room, RoomController roomController){
		for (Message mess : room.getMessages()) 
			roomController.setMessage(mess);
		return true;
	}
	
	/** Return that the current message with that id is exits? **************************/
	public boolean inCurrentMessage(int roomID){
		for (RoomController room : messageList)
			if(room.getId()==roomID)
				return true;
		return false;
	}
	
	/** Set message to GUI when room home alive *****************************************/
	public void setMessageToGuiExplicit(RoomController roomController, Message message){
		if(message.getIsFile())
		roomController.setMedia(message);
		else roomController.setMessage(message);
	}
	
	/** Set message to GUI when room home dead ******************************************/
	public void setMessageToGuiImplicit(RoomController roomController, Message message){
		addPartElse(roomController);
		setMessageToGuiExplicit(roomController, message);
	}
	
	/** Request add a person to a friend ************************************************/
	public void addMoreFriend(int id){
		callAddFriend(id);
	}
	
	/** Request delete a person out of room**********************************************/
	public void deleteMember(int memberId, int roomId){
		if(getRoomById(roomId).getMembers().size()>2){
			setDeleteMemeber(memberId, roomId);
			callDeleteMember(roomId, memberId);
		}
		else{
			alertNotify(AlertType.INFORMATION,"Trong phòng phải có ít nhất 2 thành viên","Thông báo",null);
		}
	}
	
	/** Request add a person to a room***************************************************/
	public void addMemberToRoom(int roomId, int memberId){
		setAddMember(memberId, roomId);
		callAddMember(roomId, memberId);
	}
	
	/** Change avatar of a room *********************************************************/
	public void changeAvatarRoom(int roomId, FileInfo avatar){
		setAvatarRoom(roomId, avatar);
		callChangeAvatarRoom(roomId, avatar);
	}
	
	/** Change room name ****************************************************************/
	public void changeNameRoom(int id,String name){
		setNameRoom(id, name);
		callChangeNameRoom(id, name);
	}
	
	/** Create new room *****************************************************************/
	private int up=0;// @need change delete
	public void createNewRoom(ArrayList<Integer> idList,String name, String path) {
		FileInfo avatar = new FileInfo("tmp/user_icon.png");
		if(path!=null)
			avatar.setFile(path);
		avatar.getFile("tmp/");
		ArrayList<Person> members = new ArrayList<Person>();
		for(int i=0; i<idList.size(); i++)
			members.add(getPersonById(idList.get(i)));
		members.add(person);
		Room room = new Room(up, name, avatar, members);
		callCreateNewRoom(room);
		alertNotify(AlertType.INFORMATION,"Tạo phòng thành công","Thông báo", null);
		addRoom(room);
		up++; // need  delete
	}
	
	
	/** Set new ID for room from server *************************************************/
	public void createIdForRoom(int oldId, int newId){
		getRoomById(oldId).setId(newId);
		roomControllerMap.put(newId, roomControllerMap.get(oldId));
		roomControllerMap.remove(oldId);
	}
	
	/** Send message text to server *****************************************************/
	public void sendMessage(int roomId, Message message){
		callSendMessage(roomId, message);
	}
	
	/** Update user image ***************************************************************/
	public void updateUserImage(){
		if(person !=null && person.getAvatar().getFile("tmp/"))
			backgroundController.changeImageUserButton(new ImagePattern(new Image(new File("tmp/"+person.getAvatar().getName()).toURI().toString())));
		else 
			backgroundController.changeImageUserButton(new ImagePattern(new Image(new File("tmp/user_icon.png").toURI().toString())));
	}

	/** Update user name*****************************************************************/
	public void updateUserName(){
		if(person!=null)
			backgroundController.changeUserName(person.getName());
		else {
			backgroundController.changeUserName("User name");
		}
	}
	/*----------------------------------------------------------------------------------
	*
	*-------------------------------------->   MODULES    <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	/** Module for remove friend ********************************************************/
	private void setRemoveFriend(int id){
		friendList.remove(getPersonById(id));
		removeFriendTag(friendTagMap.get(id));
		alertNotify(AlertType.INFORMATION,"Đã xóa!","Thành công",null);
	}
	
	/** Module for  delete member *******************************************************/
	private void setDeleteMemeber(int memberId, int roomId){
		String name = getMemberById(roomId, memberId).getName();
		getRoomById(roomId).getMembers().remove(getMemberById(roomId, memberId));
		alertNotify(AlertType.INFORMATION,"Bạn đã xóa "+ name+" ra khỏi phòng!","Thông báo",null);
		roomControllerMap.get(roomId).update();
	}
	
	/** Module for  set avatar room *****************************************************/
	private void setAvatarRoom(int roomId, FileInfo avatar){
		getRoomById(roomId).setAvatar(avatar);
		roomControllerMap.get(roomId).update();
	}
	
	/** Module for set name of room *****************************************************/
	private void setNameRoom(int id, String name){
		getRoomById(id).setName(name);
		roomControllerMap.get(id).update();
	}
	
	private void setAddMember(int memberId, int roomId){
		getRoomById(roomId).getMembers().add(getPersonById(memberId));
		roomControllerMap.get(roomId).update();
	}
	
	public Connector getConnector(){
		return connector;
	}
	/*----------------------------------------------------------------------------------
	*
	*-------------------------------->   I.O COMUNICATION    <--------------------------
	*
	*-----------------------------------------------------------------------------------*/
	
	/**============================
	 *
	 *			   INPUT
	 * 
	 **===========================*/
	
	
	/** Sign in successfully*************************************************************/
	public void verifyAccountSuccessful(){
		alertNotify(AlertType.INFORMATION,"Đăng ký thành công!","Thông báo!", null);
		loginController.returnToLoginPane();
	}
	
	/** Sign in failure******************************************************************/
	public void verifyAccountFailure(){
		alertNotify(AlertType.INFORMATION,"Tạo tài khoản thất bại!","Lỗi", null);
	}
	
	/** Login successfully****************************************************************/
	public void authenticAccountSuccessful(){
		openAppChat();
	}
	
	/** Login failure*********************************************************************/
	public void authenticAccountFailure(){
		alertNotify(AlertType.INFORMATION,"Đăng nhập thất bại","Lỗi", null);
	}
	
	/** Get person from server ***********************************************************/
	public void inputUserFromDatabase(Person person){
		this.person = person;
	}
	
	/** Get friend list from server ******************************************************/
	public void inputFriendList(ArrayList<Person> lists){
		friendList = lists;
	}
	
	/** Another user accept add friend ***************************************************/
	public void inputNewFriend(Person person){
		friendList.add(person);
		Platform.runLater(()->{
			addFriendTag(person.getId());
		});
	}
	/** Input add member to a room *******************************************************/
	public void inputAddMemberToARoom(Person person,int roomId){
		getRoomById(roomId).getMembers().add(person);
		
	}
	
	/** Another kick you out of room *****************************************************/
	public void inputOutOfRoom(int roomId){
		removePartElse(roomControllerMap.get(roomId));
		backgroundController.removeToRoomFlowPane(roomControllerMap.get(roomId).getRoomTag());
		alertNotify(AlertType.INFORMATION,"Bạn đã bị mời rời khỏi phòng!","Thông báo", null);
	}
	
	/** Receive message*******************************************************************/
	public void inputMessage(Message message){
		if(inCurrentMessage(message.getRoom().getId()))
			setMessageToGuiExplicit(roomControllerMap.get(message.getRoom().getId()), message);
		else
			setMessageToGuiImplicit(roomControllerMap.get(message.getRoom().getId()), message);
	}
	/** Receive list person - result of searching *****************************************/
	public void inputSearchPerson(ArrayList<Person> persons){
		if(persons.size()>0)
			Platform.runLater(()->{
				backgroundController.upToSearchPane(persons);
			});
		else alertNotify(AlertType.CONFIRMATION,"Tài khoản không tồn tại!","Thông báo",null);
	}
	
	/** New from was create from another user *********************************************/
	public void inputNewRoom(Room room){
		roomList.add(room);
		roomControllerMap.put(room.getId(),new RoomController(room));
	}
	
	/** Result update user ****************************************************************/
	public void updateAccountResult(boolean result){
		if(result){
			person = node;
			Platform.runLater(()->{
				updateUserImage();
				updateUserName();
			});
			alertNotify(AlertType.INFORMATION,"Đã cập nhật thông tin của bạn","Thông báo",null);
		}
		else {
			alertNotify(AlertType.INFORMATION,"Chưa cập nhất được!","Thông báo",null);
		}
	}
	
	public void requestAddFriendResult(boolean result){
		if(result) 
			alertNotify(AlertType.INFORMATION,"Đã gửi yêu cầu!","Thông báo",null);
		else 
			alertNotify(AlertType.INFORMATION,"Người dùng không trực tuyến!","Thông báo",null);
	}
	
	public void requestConfirmFriend(Person person){
		if(alertConfirmation("Bạn có muốn thêm " +person.getName()+" vào danh sách bạn bè?","Confirm", null)){
			friendList.add(person);
			Platform.runLater(()->{
				backgroundController.initFriendList();
			});
		}
		else alertNotify(AlertType.INFORMATION,"Đã thêm bạn bè!","Thông báo!",null);
	}
	
	/**============================
	 *
	 *			   OUTPUT
	 * 
	 **===========================*/
	
	/** Request server verify that new account********************************************/
	public void callUserVerify(Person person) {
		verifyAccountFailure();// call I/O in here
	}
	
	public void sendRequestUser(){
		Request request = new Request(Name.GET,"GET");
		CPackage cPackage = new CPackage(Type.ACCOUNT,request);
		connector.sendCPackage(cPackage);
	}
	
	public void sendRequestRoom(){
		Request request = new Request(Name.GET,"GET");
		CPackage cPackage = new CPackage(Type.ROOM,request);
		connector.sendCPackage(cPackage);
	}
	
	public void sendRequestFriend(){
		Request request = new Request(Name.GET,"GET");
		CPackage cPackage = new CPackage(Type.FRIEND,request);
		connector.sendCPackage(cPackage);
	}

	/** Request server authentic old user to login****************************************/
	public void callUserAuthentic(String username, String password) {
		Request request = new Request(Name.LOGIN,new LoginModel(username, password));
		CPackage cPackage = new CPackage(Type.AUTHENTICATION,request);
		connector.sendCPackage(cPackage);
	}
	
	/** Request server delete a member in that room***************************************/
	public void callDeleteMember(int roomId, int memberId){
		// Call IO
		System.out.println("Server called - call delete member - 703");
	}
	
	/** Request server send message to that room *****************************************/
	public void callSendMessage(int roomId, Message message){
		System.out.println("Call sen message");
	}
	
	/** Request server add friend ********************************************************/
	public void callAddFriend(int id){
		Request request = new Request(Name.ADD,id);
		CPackage cPackage = new CPackage(Type.FRIEND,request);
		connector.sendCPackage(cPackage);
	}

	/** Request server create new room ***************************************************/
	public void callCreateNewRoom(Room room) {
		System.out.println("Create new room");
	}
	
	/** Request server remove message of a room by id ************************************/
	public void callRemoveMessage(int roomId){
		 System.out.println("Call remove message");
	}
	
	/** Request server leave a room by id ************************************************/
	public void callLeaveRoom(int roomId){
		System.out.println("CALL SERVER LEAVE ROOM");
	}
	
	/** Request server remove a friend ***************************************************/
	public void callRemoveFriend(int friendId){
		System.out.println("call remove friend");
	}
	
	/** Request server change avatar of a room *******************************************/
	public void callChangeAvatarRoom(int roomId,FileInfo avatar){
		System.out.println("Call to server: change avatar of room");
	}
	
	/** Request server change room name **************************************************/
	public void callChangeNameRoom(int id, String name){
		System.out.println("Call to server: change name of room");
	}
	
	/** Request server update user information *******************************************/
	public void callUpdateUserInfo(Person person){
		Request request = new Request(Name.UPDATE,person);
		CPackage cPackage = new CPackage(Type.ACCOUNT,request);
		connector.sendCPackage(cPackage);
	}

	/** Request server search friend white that key (text) *******************************/
	public void callSearchFriend(String text) {
		Request request = new Request(Name.FIND,text);
		CPackage cPackage = new CPackage(Type.FRIEND,request);
		connector.sendCPackage(cPackage);
	}
	
	public void callAddMember(int roomId, int memberId){
		System.out.println("Call add member to room 844");
	}
	
	public void callChangePassword(String password){
		System.out.println("Call change password");
	}
}