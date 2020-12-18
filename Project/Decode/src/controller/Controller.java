/*      */ package controller;
/*      */ 
/*      */ import connector.Connector;
/*      */ import controller.subcontroller.BackgroundController;
/*      */ import controller.subcontroller.FriendTagController;
/*      */ import controller.subcontroller.LoginController;
/*      */ import controller.subcontroller.info.FriendInfoController;
/*      */ import controller.subcontroller.info.MemberInfoController;
/*      */ import controller.subcontroller.info.RoomInfoController;
/*      */ import controller.subcontroller.room.RoomController;

/*      */ import java.io.File;
/*      */ import java.time.LocalDate;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Optional;

/*      */ import javafx.application.Platform;
/*      */ import javafx.fxml.FXMLLoader;
/*      */ import javafx.scene.Parent;
/*      */ import javafx.scene.Scene;
/*      */ import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/*      */ import javafx.scene.control.ButtonType;
/*      */ import javafx.scene.image.Image;
/*      */ import javafx.scene.paint.ImagePattern;
/*      */ import javafx.stage.Stage;
/*      */ import javafx.stage.StageStyle;
/*      */ import javafx.stage.WindowEvent;
/*      */ import model.communication.CPackage;
/*      */ import model.communication.Name;
/*      */ import model.communication.Request;
/*      */ import model.communication.Type;
/*      */ import model.sendmodel.ConfirmFriendModel;
/*      */ import model.sendmodel.FileInfo;
/*      */ import model.sendmodel.LoginModel;
/*      */ import model.sendmodel.Message;
/*      */ import model.sendmodel.Person;
/*      */ import model.sendmodel.Room;
/*      */ import model.sendmodel.RoomPersonModel;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Controller
/*      */ {
/*      */   public static Controller controller;
/*      */   public Connector connector;
/*      */   private Person person;
/*   52 */   private ArrayList<Person> friendList = new ArrayList<>();
/*      */   
/*   54 */   private ArrayList<Room> roomList = new ArrayList<>();
/*      */   
/*   56 */   private ArrayList<RoomController> messageList = new ArrayList<>();
/*      */   
/*   58 */   private HashMap<Integer, Room> currentMessageMap = new HashMap<>();
/*      */   
/*   60 */   private HashMap<Integer, RoomController> roomControllerMap = new HashMap<>();
/*      */   
/*   62 */   private HashMap<Integer, Parent> friendTagMap = new HashMap<>();
/*      */   private BackgroundController backgroundController;
/*      */   private LoginController loginController;
/*      */   private RoomInfoController roomInfoController;
/*      */   private Person node;
/*      */   
/*      */   public void makeEmpty() {
/*   69 */     this.friendList.clear();
/*   70 */     this.roomList.clear();
/*   71 */     this.messageList.clear();
/*   72 */     this.currentMessageMap.clear();
/*   73 */     this.roomControllerMap.clear();
/*   74 */     this.friendTagMap.clear();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBackgroundController(BackgroundController backgroundController) {
/*   97 */     this.backgroundController = backgroundController;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLoginController(LoginController loginController) {
/*  102 */     this.loginController = loginController;
/*      */   }
/*      */ 
/*      */   
/*      */   public Person getUser() {
/*  107 */     return this.person;
/*      */   }
/*      */   
/*      */   public void setUser(Person person) {
/*  111 */     this.person = person;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setFriendList(ArrayList<Person> list) {
/*  116 */     this.friendList = list;
/*      */   }
/*      */ 
/*      */   
/*      */   public ArrayList<Person> getFriendList() {
/*  121 */     return this.friendList;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setRoomList(ArrayList<Room> list) {
/*  126 */     this.roomList = list;
/*      */   }
/*      */ 
/*      */   
/*      */   public ArrayList<Room> getRoomList() {
/*  131 */     return this.roomList;
/*      */   }
/*      */ 
/*      */   
/*      */   public Room getRoomById(int id) {
/*  136 */     for (Room room : this.roomList) {
/*  137 */       if (id == room.getId())
/*  138 */         return room; 
/*  139 */     }  return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public Integer getUserId() {
/*  144 */     if (this.person != null)
/*  145 */       return Integer.valueOf(this.person.getId()); 
/*  146 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public Person getPersonById(int id) {
/*  151 */     if (id == this.person.getId())
/*  152 */       return this.person; 
/*  153 */     for (Person person : this.friendList) {
/*  154 */       if (id == person.getId())
/*  155 */         return person; 
/*  156 */     }  return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public RoomController getRoomControllerById(int roomId) {
/*  161 */     return this.roomControllerMap.get(Integer.valueOf(roomId));
/*      */   }
/*      */ 
/*      */   
/*      */   public Image getImageOfPerson(int id) {
/*  166 */     Person tmp = getPersonById(id);
/*  167 */     tmp.getAvatar().getFile("tmp/");
/*  168 */     return new Image((new File("tmp/" + tmp.getAvatar().getName())).toURI()
/*  169 */         .toString());
/*      */   }
/*      */ 
/*      */   
/*      */   public Person getMemberById(int roomId, int memberId) {
/*  174 */     Room room = getRoomById(roomId);
/*  175 */     if (room != null)
/*  176 */       for (Person person : room.getMembers()) {
/*  177 */         if (person.getId() == memberId)
/*  178 */           return person; 
/*  179 */       }   return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Controller getInstance() {
/*  190 */     if (controller == null)
/*  191 */       controller = new Controller(); 
/*  192 */     return controller;
/*      */   }
/*      */ 
/*      */   
/*      */   public void openAppChat() {
/*  197 */     Platform.runLater(() -> {
/*      */           try {
/*      */             Stage stage = new Stage();
/*      */             
/*      */             FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/background/background.fxml"));
/*      */             
/*      */             Parent parent = loader.<Parent>load();
/*      */             
/*      */             Scene scene = new Scene(parent);
/*      */             stage.setScene(scene);
/*      */             stage.setTitle("Knock Knock");
/*      */             stage.setMinHeight(537.4D);
/*      */             stage.setMinWidth(600.0D);
/*      */             stage.setOnCloseRequest(value->{
	System.exit(0);
});
/*      */             stage.show();
/*      */             this.loginController.closeWindows();
/*  213 */           } catch (Exception e) {
/*      */             e.printStackTrace();
/*      */           } 
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void openLoginWindows() {
/*      */     try {
/*  223 */       Stage stage = new Stage();
/*  224 */       FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login/login.fxml"));
/*  225 */       Parent parent = loader.<Parent>load();
/*  226 */       Scene scene = new Scene(parent);
/*  227 */       stage.setScene(scene);
/*  228 */       stage.setTitle("Knock Knock - Login");
/*  229 */       stage.setResizable(false);
/*  230 */       stage.setOnCloseRequest(value -> System.exit(0));
/*      */ 
/*      */       
/*  233 */       stage.show();
/*  234 */       this.backgroundController.closeWindows();
/*  235 */     } catch (Exception e) {
/*  236 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean alertNotify(AlertType type, String header, String title, String content) {
/*  243 */     Platform.runLater(() -> {
/*      */           Alert alert = new Alert(type);
/*      */           alert.setHeaderText(header);
/*      */           alert.setTitle(title);
/*      */           if (content != null)
/*      */             alert.setContentText(content); 
/*      */           alert.show();
/*      */         });
/*  251 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean alertConfirmation(String header, String title, String content) {
/*  257 */     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
/*  258 */     alert.setHeaderText(header);
/*  259 */     alert.setTitle(title);
/*  260 */     if (content != null) alert.setContentText(content); 
/*  261 */     Optional<ButtonType> type = alert.showAndWait();
/*  262 */     if (type.get() == ButtonType.OK)
/*  263 */       return true; 
/*  264 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void showRoomInfo(int id) {
/*  270 */     if (getRoomById(id) != null) {
/*      */       try {
/*  272 */         FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/subinfo/roominfo.fxml"));
/*  273 */         Parent parent = loader.<Parent>load();
/*  274 */         this.roomInfoController = loader.<RoomInfoController>getController();
/*  275 */         this.roomInfoController.setInfo(id);
/*  276 */         Scene scene = new Scene(parent);
/*  277 */         Stage stage = new Stage(StageStyle.UTILITY);
/*  278 */         stage.setTitle("Room information " + getInstance().getRoomById(id).getName());
/*  279 */         stage.setScene(scene);
/*  280 */         stage.setResizable(false);
/*  281 */         stage.show();
/*  282 */       } catch (Exception e) {
/*  283 */         e.printStackTrace();
/*      */       } 
/*      */     } else {
/*  286 */       alertNotify(Alert.AlertType.INFORMATION, "Phòng không tồn tại!", "Lỗi!", null);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void showUserInfo() {
/*      */     try {
/*  292 */       Stage stage = new Stage(StageStyle.UTILITY);
/*  293 */       FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/subinfo/userinfo.fxml"));
/*  294 */       Parent parent = loader.<Parent>load();
/*  295 */       Scene scene = new Scene(parent);
/*  296 */       stage.setScene(scene);
/*  297 */       stage.setTitle("Information of " + this.person.getName());
/*  298 */       stage.setResizable(false);
/*  299 */       stage.show();
/*  300 */     } catch (Exception e) {
/*  301 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void showFriendInfo(int id) {
/*  307 */     if (getPersonById(id) != null) {
/*      */       try {
/*  309 */         FXMLLoader loader = new FXMLLoader(getClass().getResource(
/*  310 */               "/view/subinfo/friendinfo.fxml"));
/*  311 */         Parent parent = loader.<Parent>load();
/*  312 */         FriendInfoController friendController = loader.<FriendInfoController>getController();
/*  313 */         friendController.setInfo(id);
/*  314 */         Scene scene = new Scene(parent);
/*  315 */         Stage stage = new Stage(StageStyle.UTILITY);
/*  316 */         stage.setTitle("ThÃ´ng tin cá»§a " + getPersonById(id).getName());
/*  317 */         stage.setScene(scene);
/*  318 */         stage.setResizable(false);
/*  319 */         stage.show();
/*  320 */       } catch (Exception e) {
/*  321 */         e.printStackTrace();
/*      */       } 
/*      */     } else {
/*  324 */       alertNotify(Alert.AlertType.ERROR, "Bạn bè không tồn tại!", "Lỗi!", null);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void showMemberInfo(int memberId, int roomId) {
/*  329 */     if (getMemberById(roomId, memberId) != null) {
/*      */       try {
/*  331 */         FXMLLoader loader = new FXMLLoader(getClass().getResource(
/*  332 */               "/view/subinfo/memberinfo.fxml"));
/*  333 */         Parent parent = loader.<Parent>load();
/*  334 */         MemberInfoController memberController = loader.<MemberInfoController>getController();
/*  335 */         memberController.setInfo(getMemberById(roomId, memberId), roomId);
/*  336 */         memberController.setDeleteButton(this.roomInfoController);
/*  337 */         Scene scene = new Scene(parent);
/*  338 */         Stage stage = new Stage(StageStyle.UTILITY);
/*  339 */         stage.setTitle("ThÃ´ng tin cá»§a " + getMemberById(roomId, memberId).getName());
/*  340 */         stage.setScene(scene);
/*  341 */         stage.setResizable(false);
/*  342 */         stage.show();
/*  343 */       } catch (Exception e) {
/*  344 */         e.printStackTrace();
/*      */       } 
/*      */     } else {
/*  347 */       alertNotify(Alert.AlertType.INFORMATION, "Thành viên không tồn tại!", "Lỗi!", null);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void showChangePassword() {
/*      */     try {
/*  353 */       FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/changepassword/changepassword.fxml"));
/*  354 */       Parent parent = loader.<Parent>load();
/*  355 */       Scene scene = new Scene(parent);
/*  356 */       Stage stage = new Stage(StageStyle.UTILITY);
/*  357 */       stage.setTitle("Ä�á»•i máº­t kháº©u");
/*  358 */       stage.setScene(scene);
/*  359 */       stage.setResizable(false);
/*  360 */       stage.show();
/*  361 */     } catch (Exception e) {
/*  362 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void showCreateRoom() {
/*      */     try {
/*  369 */       FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/createroom/createroom.fxml"));
/*  370 */       Parent parent = loader.<Parent>load();
/*  371 */       Scene scene = new Scene(parent);
/*  372 */       Stage stage = new Stage(StageStyle.UTILITY);
/*  373 */       stage.setTitle("Táº¡o phÃ²ng!");
/*  374 */       stage.setScene(scene);
/*  375 */       stage.setResizable(false);
/*  376 */       stage.show();
/*  377 */     } catch (Exception e) {
/*  378 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void showRoomCaller(int id) {
/*  384 */     for (RoomController roomController : this.messageList)
/*  385 */       roomController.getRoomHome().setVisible(false); 
/*  386 */     if (!inCurrentMessage(id))
/*  387 */       addPartElse(this.roomControllerMap.get(Integer.valueOf(id))); 
/*  388 */     ((RoomController)this.roomControllerMap.get(Integer.valueOf(id))).getRoomHome().setVisible(true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isFriend(int id) {
/*  394 */     for (Person person : this.friendList) {
/*  395 */       if (id == person.getId())
/*  396 */         return true; 
/*  397 */     }  return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean addMessageTag(Parent messageTag) {
/*  402 */     if (messageTag != null)
/*      */       try {
/*  404 */         this.backgroundController.addToMessageFlowPane(messageTag);
/*  405 */         return true;
/*  406 */       } catch (Exception e) {
/*  407 */         e.printStackTrace();
/*  408 */         return false;
/*      */       }  
/*  410 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean removeMessageTag(Parent messageTag) {
/*  415 */     if (messageTag != null)
/*      */       try {
/*  417 */         this.backgroundController.removeToMessageFlowPane(messageTag);
/*  418 */         return true;
/*      */       }
/*  420 */       catch (Exception e) {
/*  421 */         e.printStackTrace();
/*  422 */         return false;
/*      */       }  
/*  424 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean addRoomTag(Parent roomTag) {
/*      */     try {
/*  430 */       this.backgroundController.addToRoomFlowPane(roomTag);
/*  431 */       return true;
/*  432 */     } catch (Exception e) {
/*  433 */       e.printStackTrace();
/*  434 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void removeRoomTag(Parent parent) {
/*  440 */     this.backgroundController.removeToRoomFlowPane(parent);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean addFriendTag(int id) {
/*      */     try {
/*  446 */       FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/tag/friendtag/friendtag.fxml"));
/*  447 */       Parent friendTag = loader.<Parent>load();
/*  448 */       FriendTagController friendTagController = loader.<FriendTagController>getController();
/*  449 */       friendTagController.setFriendID(id);
/*  450 */       this.friendTagMap.put(Integer.valueOf(id), friendTag);
/*  451 */       this.backgroundController.addToFriendFlowPane(friendTag);
/*  452 */       return true;
/*  453 */     } catch (Exception e) {
/*  454 */       e.printStackTrace();
/*  455 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void removeFriendTag(Parent parent) {
/*  461 */     this.backgroundController.removeToFriendFlowPane(parent);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean addRoomHome(Parent roomHome) {
/*  466 */     if (roomHome != null)
/*      */       try {
/*  468 */         this.backgroundController.addToRightPane(roomHome);
/*  469 */         return true;
/*  470 */       } catch (Exception e) {
/*  471 */         e.printStackTrace();
/*      */       }  
/*  473 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean removeRoomHome(Parent parent) {
/*  478 */     if (parent != null)
/*      */       try {
/*  480 */         this.backgroundController.removeToRightPane(parent);
/*  481 */         return true;
/*      */       }
/*  483 */       catch (Exception e) {
/*  484 */         e.printStackTrace();
/*  485 */         return false;
/*      */       }  
/*  487 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void addTagToFlowPane(String text) {
/*  492 */     for (Person person : this.friendList) {
/*  493 */       if (person.getName().toLowerCase().lastIndexOf(text.toLowerCase()) >= 0)
/*  494 */         this.backgroundController.addToSearchFlowPane(this.friendTagMap.get(Integer.valueOf(person.getId()))); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void addRoom(Room room) {
/*  499 */     RoomController roomController = new RoomController(room);
/*  500 */     addRoomTag(roomController.getRoomTag());
/*  501 */     if (room.getMessages() != null && room.getMessages().size() != 0) {
/*  502 */       this.currentMessageMap.put(Integer.valueOf(room.getId()), room);
/*  503 */       this.messageList.add(roomController);
/*  504 */       addMessageTag(roomController.getMessageTag());
/*  505 */       addRoomHome(roomController.getRoomHome());
/*  506 */       initMessage(room, roomController);
/*      */     } 
/*  508 */     this.roomList.add(room);
/*  509 */     this.roomControllerMap.put(Integer.valueOf(room.getId()), roomController);
/*      */   }
/*      */ 
/*      */   
/*      */   public void addToCurrentMessageMap(RoomController roomController) {
/*  514 */     this.currentMessageMap.put(Integer.valueOf(roomController.getRoom().getId()), roomController.getRoom());
/*  515 */     this.messageList.add(roomController);
/*  516 */     initMessage(roomController.getRoom(), roomController);
/*      */   }
/*      */   
/*      */   private Controller() {
/*  520 */     this.node = null;
/*      */     this.connector = new Connector();
/*      */     this.connector.start();
/*      */   } public void updateUserInfo(String day, String month, String year, boolean gender, String phone, String name, FileInfo file) {
/*  524 */     Person tmp = new Person(this.person);
/*  525 */     tmp.setDateofbirth(LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day)));
/*  526 */     tmp.setAvatar(file);
/*  527 */     tmp.setMale(gender);
/*  528 */     tmp.setPhonenumber(phone);
/*  529 */     tmp.setName(name);
/*  530 */     this.node = tmp;
/*  531 */     Platform.runLater(() -> callUpdateUserInfo(tmp));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addPartElse(RoomController roomController) {
/*  538 */     System.out.println((roomController == null));
/*  539 */     addMessageTag(roomController.getMessageTag());
/*  540 */     addRoomHome(roomController.getRoomHome());
/*  541 */     this.messageList.add(roomController);
/*      */   }
/*      */ 
/*      */   
/*      */   public void removePartElse(RoomController roomController) {
/*  546 */     removeMessageTag(roomController.getMessageTag());
/*  547 */     removeRoomHome(roomController.getRoomHome());
/*  548 */     getRoomById(roomController.getId()).getMessages().clear();
/*  549 */     this.messageList.remove(roomController);
/*      */   }
/*      */ 
/*      */   
/*      */   public void leaveRoom(int roomId) {
/*  554 */     callLeaveRoom(roomId);
/*  555 */     removePartElse(this.roomControllerMap.get(Integer.valueOf(roomId)));
/*  556 */     this.backgroundController.removeToRoomFlowPane(((RoomController)this.roomControllerMap.get(Integer.valueOf(roomId))).getRoomTag());
/*  557 */     this.roomControllerMap.remove(Integer.valueOf(roomId));
/*  558 */     this.roomList.remove(getRoomById(roomId));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void changePassword(String text) {
/*  564 */     this.person.setPassword(text);
/*  565 */     callChangePassword(text);
/*      */   }
/*      */ 
/*      */   
/*      */   public void removeMessage(RoomController roomController) {
/*  570 */     removePartElse(roomController);
/*  571 */     roomController.getRoomHomeController().removeAllMessage();
/*  572 */     roomController.getMessageTagController().updateCurrentMessage("");
/*  573 */     callRemoveMessage(roomController.getId());
/*      */   }
/*      */ 
/*      */   
/*      */   public void removeFriend(int id) {
/*  578 */     if (this.friendList.contains(getPersonById(id))) {
/*  579 */       String name = getPersonById(id).getName();
/*  580 */       if (alertConfirmation("Bạn có chắc muốn xóa " + name + " ra khỏi danh sách¨?", "Xác nhận!", null)) {
/*  581 */         setRemoveFriend(id);
/*  582 */         callRemoveFriend(id);
/*  583 */         alertNotify(Alert.AlertType.INFORMATION, "Bạn đã xóa " + name + " khỏi danh sách bạn bè!", "Thông báo", null);
/*      */       } 
/*      */     } else {
/*      */       
/*  587 */       alertNotify(Alert.AlertType.INFORMATION, "Bạn bè không tồn tại!", "Lỗi!", null);
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean initMessage(Room room, RoomController roomController) {
/*  592 */     for (Message mess : room.getMessages()) {
/*  593 */       if (!mess.getIsFile())
/*  594 */         roomController.setMessage(mess); 
/*  595 */       roomController.setMedia(mess);
/*      */     } 
/*  597 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean inCurrentMessage(int roomID) {
/*  602 */     for (RoomController room : this.messageList) {
/*  603 */       if (room.getId() == roomID)
/*  604 */         return true; 
/*  605 */     }  return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMessageToGuiExplicit(RoomController roomController, Message message) {
/*  610 */     if (message.getIsFile())
/*  611 */     { roomController.setMedia(message); }
/*  612 */     else { roomController.setMessage(message); }
/*      */   
/*      */   }
/*      */   
/*      */   public void setMessageToGuiImplicit(RoomController roomController, Message message) {
/*  617 */     addPartElse(roomController);
/*  618 */     setMessageToGuiExplicit(roomController, message);
/*      */   }
/*      */ 
/*      */   
/*      */   public void addMoreFriend(int id) {
/*  623 */     callAddFriend(id);
/*      */   }
/*      */ 
/*      */   
/*      */   public void deleteMember(RoomPersonModel roomPersonModel) {
/*  628 */     if (getRoomById(roomPersonModel.getRoom().getId()).getMembers().size() > 2) {
/*  629 */       setDeleteMemeber(roomPersonModel.getPerson().getId(), roomPersonModel.getRoom().getId());
/*  630 */       callDeleteMember(roomPersonModel);
/*      */     } else {
/*      */       
/*  633 */       alertNotify(Alert.AlertType.INFORMATION, "Trong phòng phải có ít nhất 2 thành viên!", "Thông báo!", null);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void addMemberToRoom(int roomId, int memberId) {
/*  639 */     setAddMember(memberId, roomId);
/*  640 */     callAddMember(roomId, memberId);
/*      */   }
/*      */ 
/*      */   
/*      */   public void changeAvatarRoom(int roomId, FileInfo avatar) {
/*  645 */     setAvatarRoom(roomId, avatar);
/*  646 */     Room room = getRoomById(roomId);
/*  647 */     Platform.runLater(() -> callRoomInfo(room));
/*      */   }
/*      */ 
/*      */   
/*      */   public void changeNameRoom(int id, String name) {
/*  652 */     setNameRoom(id, name);
/*  653 */     Platform.runLater(() -> changeRoomName(id, name));
/*      */   }
/*      */ 
/*      */   
/*      */   public void createNewRoom(ArrayList<Integer> idList, String name, String path) {
/*  658 */     FileInfo avatar = new FileInfo("tmp/user_icon.png");
/*  659 */     if (path != null)
/*  660 */       avatar.setFile(path); 
/*  661 */     avatar.getFile("tmp/");
/*  662 */     ArrayList<Person> members = new ArrayList<>();
/*  663 */     for (int i = 0; i < idList.size(); i++)
/*  664 */       members.add(getPersonById(((Integer)idList.get(i)).intValue())); 
/*  665 */     members.add(this.person);
/*  666 */     Room room = new Room(0, name, avatar, members);
/*  667 */     callCreateNewRoom(room);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void createIdForRoom(int oldId, int newId) {
/*  673 */     getRoomById(oldId).setId(newId);
/*  674 */     this.roomControllerMap.put(Integer.valueOf(newId), this.roomControllerMap.get(Integer.valueOf(oldId)));
/*  675 */     this.roomControllerMap.remove(Integer.valueOf(oldId));
/*      */   }
/*      */ 
/*      */   
/*      */   public void sendMessage(int roomId, Message message) {
/*  680 */     callSendMessage(roomId, message);
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateUserImage() {
/*  685 */     if (this.person != null && this.person.getAvatar().getFile("tmp/")) {
/*  686 */       this.backgroundController.changeImageUserButton(new ImagePattern(new Image((new File("tmp/" + this.person.getAvatar().getName())).toURI().toString())));
/*      */     } else {
/*  688 */       this.backgroundController.changeImageUserButton(new ImagePattern(new Image((new File("tmp/user_icon.png")).toURI().toString())));
/*      */     } 
/*      */   }
/*      */   
/*      */   public void updateUserName() {
/*  693 */     if (this.person != null) {
/*  694 */       this.backgroundController.changeUserName(this.person.getName());
/*      */     } else {
/*  696 */       this.backgroundController.changeUserName("User name");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRemoveFriend(int id) {
/*  706 */     if (getPersonById(id) != null) {
/*  707 */       this.friendList.remove(getPersonById(id));
/*  708 */       Platform.runLater(() -> removeFriendTag(this.friendTagMap.get(Integer.valueOf(id))));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void setDeleteMemeber(int memberId, int roomId) {
/*  714 */     getRoomById(roomId).getMembers().remove(getMemberById(roomId, memberId));
/*  715 */     ((RoomController)this.roomControllerMap.get(Integer.valueOf(roomId))).update();
/*      */   }
/*      */ 
/*      */   
/*      */   private void setAvatarRoom(int roomId, FileInfo avatar) {
/*  720 */     getRoomById(roomId).setAvatar(avatar);
/*  721 */     ((RoomController)this.roomControllerMap.get(Integer.valueOf(roomId))).update();
/*      */   }
/*      */ 
/*      */   
/*      */   private void setNameRoom(int id, String name) {
/*  726 */     getRoomById(id).setName(name);
/*  727 */     ((RoomController)this.roomControllerMap.get(Integer.valueOf(id))).update();
/*      */   }
/*      */   
/*      */   private void setAddMember(int memberId, int roomId) {
/*  731 */     getRoomById(roomId).getMembers().add(getPersonById(memberId));
/*  732 */     ((RoomController)this.roomControllerMap.get(Integer.valueOf(roomId))).update();
/*      */   }
/*      */   
/*      */   public Connector getConnector() {
/*  736 */     return this.connector;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void inputVerifyAccountSuccessful() {
/*  753 */     alertNotify(Alert.AlertType.INFORMATION, "Đăng ký thành công!", "Thông báo!", null);
/*  754 */     this.loginController.returnToLoginPane();
/*      */   }
/*      */ 
/*      */   
/*      */   public void inputVerifyAccountFailure() {
/*  759 */     alertNotify(Alert.AlertType.INFORMATION, "Tạo tài khoản thất bại!", "Lỗi!", null);
/*      */   }
/*      */ 
/*      */   
/*      */   public void inputAuthenticAccountSuccessful() {
/*  764 */     openAppChat();
/*      */   }
/*      */ 
/*      */   
/*      */   public void inputAuthenticAccountFailure() {
/*  769 */     alertNotify(Alert.AlertType.INFORMATION, "Đăng nhập không thành công", "Lỗi!", null);
/*      */   }
/*      */ 
/*      */   
/*      */   public void inputUserFromDatabase(Person person) {
/*  774 */     this.person = person;
/*      */   }
/*      */ 
/*      */   
/*      */   public void inputFriendList(ArrayList<Person> lists) {
/*  779 */     this.friendList = lists;
/*      */   }
/*      */ 
/*      */   
/*      */   public void inputNewFriend(Person person) {
/*  784 */     this.friendList.add(person);
/*  785 */     addFriendTag(person.getId());
/*      */   }
/*      */   
/*      */   public void inputAddMemberToARoom(Person person, int roomId) {
/*  789 */     getRoomById(roomId).getMembers().add(person);
/*      */   }
/*      */ 
/*      */   
/*      */   public void inputOutOfRoom(int roomId) {
/*  794 */     String name = getRoomById(roomId).getName();
/*  795 */     Platform.runLater(() -> {
/*      */           removePartElse(this.roomControllerMap.get(Integer.valueOf(roomId)));
/*      */           this.backgroundController.removeToRoomFlowPane((this.roomControllerMap.get(Integer.valueOf(roomId))).getRoomTag());
/*      */           alertNotify(Alert.AlertType.INFORMATION, "Bạn đã bị mời rời khỏi phòng " + name + "!", "Thông báo!", null);
/*      */         });
/*      */   }
/*      */ 
/*      */   
/*      */   public void inputMessage(Message message) {
/*  804 */     if (inCurrentMessage(message.getRoomID())) {
/*  805 */       setMessageToGuiExplicit(this.roomControllerMap.get(Integer.valueOf(message.getRoomID())), message);
/*      */     } else {
/*  807 */       setMessageToGuiImplicit(this.roomControllerMap.get(Integer.valueOf(message.getRoomID())), message);
/*      */     } 
/*      */   }
/*      */   public void inputSearchPerson(ArrayList<Person> persons) {
/*  811 */     if (persons.size() > 0)
/*  812 */     { Platform.runLater(() -> this.backgroundController.upToSearchPane(persons)); }
/*  813 */     else { Platform.runLater(() -> {
/*      */           
/*      */           }); }
/*      */   
/*      */   } public void inputNewRoom(Room room) {
/*  818 */     this.roomList.add(room);
/*  819 */     this.roomControllerMap.put(Integer.valueOf(room.getId()), new RoomController(room));
/*      */   }
/*      */ 
/*      */   
/*      */   public void inputUpdateAccountResult(boolean result) {
/*  824 */     if (result) {
/*  825 */       this.person = this.node;
/*  826 */       Platform.runLater(() -> {
/*      */             updateUserImage();
/*      */             updateUserName();
/*      */           });
/*  830 */       alertNotify(Alert.AlertType.INFORMATION, "Cập nhật tài khoản thành công!", "Thông báo!", null);
/*      */     } else {
/*      */       
/*  833 */       alertNotify(Alert.AlertType.INFORMATION, "Cập nhật không thành công!", "Thông báo!", null);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void inputRequestAddFriendResult(boolean result) {
/*  838 */     if (result) {
/*  839 */       alertNotify(Alert.AlertType.INFORMATION, "Đã gửi yêu cầu!", "Thông báo!", null);
/*      */     } else {
/*  841 */       alertNotify(Alert.AlertType.INFORMATION, "Người dùng không trực tuyến!", "Thông báo!", null);
/*      */     } 
/*      */   }
/*      */   public void inputRequestConfirmFriend(ConfirmFriendModel confirm) {
/*  845 */     if (alertConfirmation("Bạn có muốn thêm " + confirm.getSender().getName() + " vào danh sách bạn bè?", "Xác nhận!", null)) {
/*  846 */       this.friendList.add(confirm.getSender());
/*  847 */       this.backgroundController.initFriendList();
/*  848 */       confirm.setIsFriend(true);
/*  849 */       Platform.runLater(() -> { 
/*      */           });
/*      */     } else {
/*  852 */       alertNotify(Alert.AlertType.INFORMATION, "Đã từ chối¨!", "Thông báo!", null);
/*  853 */       this.connector.sendCPackage(new CPackage(Type.FRIEND, new Request(Name.CONFIRM, confirm)));
/*      */     } 
/*      */   }
/*      */   
/*      */   public void inputCreateRoomFalse() {
/*  858 */     alertNotify(Alert.AlertType.INFORMATION, "Tạo phòng không thành công!", "Thông báo!", null);
/*      */   }
/*      */   
/*      */   public void inputLogoutSuccessfull() {
/*  862 */     alertNotify(Alert.AlertType.INFORMATION, "Đăng xuất thành công!", "Thông báo", null);
/*      */   }
/*      */   
/*      */   public void inputKickoutRoom(int roomId) {
/*  866 */     String name = getRoomById(roomId).getName();
/*  867 */     System.out.println(name);
/*  868 */     Platform.runLater(() -> {
/*      */           leaveRoom(roomId);
/*      */           alertNotify(Alert.AlertType.INFORMATION, "Bạn đã bị mời ra khỏi phòng " + name, "Thông báo!!", null);
/*      */         });
/*      */   }
/*      */   
/*      */   public void inputUpdateRoom(Room room) {
/*  875 */     getRoomById(room.getId()).setAvatar(room.getAvatar());
/*  876 */     getRoomById(room.getId()).setName(room.getName());
/*      */     try {
/*  878 */       Thread.sleep(80L);
/*  879 */     } catch (InterruptedException e) {
/*  880 */       e.printStackTrace();
/*      */     } 
/*  882 */     Platform.runLater(() -> ((RoomController)this.roomControllerMap.get(Integer.valueOf(room.getId()))).update());
/*      */   }
/*      */   
/*      */   public void inputNewRoomRuntime(Room room) {
/*  886 */     alertNotify(Alert.AlertType.INFORMATION, "Tạo phòng thành công!", "Thông báo!", null);
/*  887 */     addRoom(room);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void callUserVerify(Person person) {
/*  898 */     CPackage cPackage = new CPackage(Type.AUTHENTICATION, new Request(Name.SIGNUP, person));
/*  899 */     this.connector.sendCPackage(cPackage);
/*      */   }
/*      */   
/*      */   public void sendRequestUser() {
/*  903 */     Request request = new Request(Name.GET, "GET");
/*  904 */     CPackage cPackage = new CPackage(Type.ACCOUNT, request);
/*  905 */     this.connector.sendCPackage(cPackage);
/*      */   }
/*      */   
/*      */   public void sendRequestRoom() {
/*  909 */     Request request = new Request(Name.GET, "GET");
/*  910 */     CPackage cPackage = new CPackage(Type.ROOM, request);
/*  911 */     this.connector.sendCPackage(cPackage);
/*      */   }
/*      */   
/*      */   public void sendRequestFriend() {
/*  915 */     Request request = new Request(Name.GET, "GET");
/*  916 */     CPackage cPackage = new CPackage(Type.FRIEND, request);
/*  917 */     this.connector.sendCPackage(cPackage);
/*      */   }
/*      */ 
/*      */   
/*      */   public void callUserAuthentic(String username, String password) {
/*  922 */     Request request = new Request(Name.LOGIN, new LoginModel(username, password));
/*  923 */     CPackage cPackage = new CPackage(Type.AUTHENTICATION, request);
/*  924 */     this.connector.sendCPackage(cPackage);
/*      */   }
/*      */ 
/*      */   
/*      */   public void callDeleteMember(RoomPersonModel roomPersonModel) {
/*  929 */     CPackage cPackage = new CPackage(Type.ROOM, new Request(Name.EXIT, roomPersonModel));
/*  930 */     this.connector.sendCPackage(cPackage);
/*      */   }
/*      */ 
/*      */   
/*      */   public void callSendMessage(int roomId, Message message) {
/*  935 */     CPackage cPackage = new CPackage(Type.MESSAGE, new Request(Name.ADD, message));
/*  936 */     this.connector.sendCPackage(cPackage);
/*      */   }
/*      */ 
/*      */   
/*      */   public void callAddFriend(int id) {
/*  941 */     CPackage cPackage = new CPackage(Type.FRIEND, new Request(Name.ADD, Integer.valueOf(id)));
/*  942 */     this.connector.sendCPackage(cPackage);
/*      */   }
/*      */ 
/*      */   
/*      */   public void callCreateNewRoom(Room room) {
/*  947 */     CPackage cPackage = new CPackage(Type.ROOM, new Request(Name.ADD, room));
/*  948 */     this.connector.sendCPackage(cPackage);
/*      */   }
/*      */ 
/*      */   
/*      */   public void callRemoveMessage(int roomId) {
/*  953 */     CPackage pack = new CPackage(Type.MESSAGE, new Request(Name.REMOVE, Integer.valueOf(roomId)));
/*  954 */     this.connector.sendCPackage(pack);
/*      */   }
/*      */ 
/*      */   
/*      */   public void callLeaveRoom(int roomId) {
/*  959 */     CPackage pack = new CPackage(Type.ROOM, new Request(Name.EXIT, new RoomPersonModel(getRoomById(roomId), this.person)));
/*  960 */     this.connector.sendCPackage(pack);
/*      */   }
/*      */ 
/*      */   
/*      */   public void callRemoveFriend(int friendId) {
/*  965 */     CPackage pack = new CPackage(Type.FRIEND, new Request(Name.REMOVE, Integer.valueOf(friendId)));
/*  966 */     this.connector.sendCPackage(pack);
/*      */   }
/*      */ 
/*      */   
/*      */   public void callRoomInfo(Room room) {
/*  971 */     CPackage cPackage = new CPackage(Type.ROOM, new Request(Name.UPDATE, room));
/*  972 */     this.connector.sendCPackage(cPackage);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void callUpdateUserInfo(Person person) {
/*  978 */     Request request = new Request(Name.UPDATE, person);
/*  979 */     CPackage cPackage = new CPackage(Type.ACCOUNT, request);
/*  980 */     this.connector.sendCPackage(cPackage);
/*      */   }
/*      */ 
/*      */   
/*      */   public void callSearchFriend(String text) {
/*  985 */     Request request = new Request(Name.FIND, text);
/*  986 */     CPackage cPackage = new CPackage(Type.FRIEND, request);
/*  987 */     this.connector.sendCPackage(cPackage);
/*      */   }
/*      */   
/*      */   public void callAddMember(int roomId, int memberId) {
/*  991 */     CPackage cPackage = new CPackage(Type.ROOM, new Request(Name.ADDMEMBER, new RoomPersonModel(getRoomById(roomId), getPersonById(memberId))));
/*  992 */     this.connector.sendCPackage(cPackage);
/*      */   }
/*      */   
/*      */   public void callChangePassword(String password) {
/*  996 */     Person tmp = new Person(this.person);
/*  997 */     tmp.setPassword(password);
/*  998 */     callUpdateUserInfo(tmp);
/*      */   }
/*      */   
/*      */   public void logoutRequest() {
/* 1002 */     CPackage cPackage = new CPackage(Type.AUTHENTICATION, new Request(Name.LOGOUT, this.person));
/* 1003 */     this.connector.sendCPackage(cPackage);
/*      */   }
/*      */   
/*      */   public void inputNotifyKickOut(RoomPersonModel roomPersonModel, String name, String name2) {
/* 1007 */     Platform.runLater(() -> setDeleteMemeber(roomPersonModel.getPerson().getId(), roomPersonModel.getRoom().getId()));
/* 1008 */     Platform.runLater(() -> {
/*      */         
/*      */         });
/*      */   } public void changeRoomName(int id, String currentName) {
/* 1012 */     Room room = getRoomById(id);
/* 1013 */     room.setName(currentName);
/* 1014 */     Room tmp = room;
/* 1015 */     Platform.runLater(() -> callRoomInfo(room));
/*      */   }
/*      */   
/*      */   public void inputDelineAddFriend(String name) {
/* 1019 */     alertNotify(Alert.AlertType.INFORMATION, "Người dùng " + name + " đã từ chối lời mời!", "Thông báo!", null);
/*      */   }
/*      */   
/*      */   public void inputAcceptFriend(Person person) {
/* 1023 */     Platform.runLater(() -> { 
/* 1024 */         }); Platform.runLater(() -> getInstance().inputNewFriend(person));
/*      */   }
/*      */   
/*      */   public void inputKickFriend() {
/* 1028 */     Platform.runLater(() -> {
/*      */         
/*      */         });
/*      */   } public void inputToAddToRoom(Room room) {
/* 1032 */     addRoom(room);
/* 1033 */     alertNotify(Alert.AlertType.INFORMATION, "Bạn đã được thêm vào phòng " + room.getName(), "Thông báo", null);
/*      */   }
/*      */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\controller\Controller.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */