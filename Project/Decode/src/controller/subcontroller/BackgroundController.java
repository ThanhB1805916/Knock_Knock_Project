/*     */ package controller.subcontroller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import model.sendmodel.Person;
import controller.Controller;
import controller.subcontroller.room.RoomController;
import controller.subcontroller.search.SearchFriendController;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/*     */ public class BackgroundController implements Initializable { @FXML
/*     */   private AnchorPane parentbackgroundPane; @FXML
/*     */   private AnchorPane listLeftSubBackground; @FXML
/*     */   private AnchorPane roomRightSubBackground; @FXML
/*     */   private AnchorPane navigationSubBackground; @FXML
/*     */   private AnchorPane listSubPaneTop; @FXML
/*     */   private ScrollPane listbot_friend; @FXML
/*     */   private ScrollPane listbot_room; @FXML
/*     */   private ScrollPane listbot_message; @FXML
/*     */   private ScrollPane listbot_search; @FXML
/*     */   private FlowPane messageFlowpane; @FXML
/*     */   private FlowPane friendFlowpane; @FXML
/*     */   private FlowPane roomFlowpane; @FXML
/*     */   private FlowPane searchFlowpane; @FXML
/*     */   private TextField listtop_searchTextfield;
/*     */   @FXML
/*     */   private Label listtop_bannerUsername;
/*     */   @FXML
/*     */   private Button listtop_addRoomButton;
/*     */   @FXML
/*     */   private Button listtop_addFriendButton;
/*     */   @FXML
/*     */   private Button userButton;
/*     */   @FXML
/*     */   private Button nav_messageButton;
/*     */   @FXML
/*     */   private Button nav_friendButton;
/*     */   @FXML
/*     */   private Button nav_roomButton;
/*     */   @FXML
/*     */   private Button nav_logoutButton;
/*     */   private ArrayList<ScrollPane> list_scrolls;
/*     */   private ArrayList<Button> nav_buttons;
/*     */   private static final double WIDTH = 100.0D;
/*     */   private int currentIndex;
/*     */   private boolean flag;
/*     */   private SearchFriendController searchFriendController;
/*     */   
/*     */   public void initialize(URL location, ResourceBundle resources) {
/*     */     makeReponsive();
/*     */     searchTextFieldListener();
/*     */     listLeftSubBackground();
/*     */     navigationSubBackgroundListener();
/*     */     Controller.getInstance().setBackgroundController(this);
/*     */     setButton();
/*     */     Controller.getInstance().updateUserImage();
/*     */     Controller.getInstance().updateUserName();
/*     */     initFriendList();
/*     */     initRoomList();
/*     */   }
/*     */   
/*     */   private void setButton() {
/*     */     this.listtop_addRoomButton.setOnMousePressed(value -> Controller.getInstance().showCreateRoom());
/*     */     this.listtop_addFriendButton.setOnMousePressed(value -> showSearchPane());
/*     */   }
/*     */   
/*     */   public void initFriendList() {
/*     */     this.friendFlowpane.getChildren().clear();
/*     */     for (int i = 0; i < Controller.getInstance().getFriendList().size(); i++)
/*     */       Controller.getInstance().addFriendTag((Controller.getInstance().getFriendList().get(i)).getId()); 
/*     */   }
/*     */   
/*  64 */   public BackgroundController() { this.list_scrolls = new ArrayList<>();
/*     */     
/*  66 */     this.nav_buttons = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 175 */     this.currentIndex = 0;
/* 176 */     this.flag = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 323 */     this.searchFriendController = null; }
/*     */   private void makeAllEmpty() { this.messageFlowpane.getChildren().clear(); this.friendFlowpane.getChildren().clear(); this.roomFlowpane.getChildren().clear(); this.searchFlowpane.getChildren().clear(); this.roomRightSubBackground.getChildren().clear(); this.list_scrolls.clear(); this.nav_buttons.clear(); }
/*     */   public void initRoomList() { this.roomFlowpane.getChildren().clear(); for (int i = 0; i < Controller.getInstance().getRoomList().size(); i++) { RoomController roomController = Controller.getInstance().getRoomControllerById((Controller.getInstance().getRoomList().get(i)).getId()); Platform.runLater(() -> addToRoomFlowPane(roomController.getRoomTag())); if (roomController.getRoom().getMessages() != null && roomController.getRoom().getMessages().size() != 0) { Controller.getInstance().addToCurrentMessageMap(roomController); addToMessageFlowPane(roomController.getMessageTag()); addToRightPane(roomController.getRoomHome()); }  }  }
/* 326 */   private void makeReponsive() { this.parentbackgroundPane.widthProperty().addListener(function -> { double length = this.parentbackgroundPane.getWidth(); if (length <= 920.0D) { AnchorPane.setLeftAnchor(this.listLeftSubBackground, Double.valueOf(0.0D)); this.listLeftSubBackground.setPrefWidth(100.0D); this.listSubPaneTop.setPrefWidth(100.0D); this.navigationSubBackground.setVisible(false); AnchorPane.setLeftAnchor(this.roomRightSubBackground, Double.valueOf(100.0D)); modifiedListSubPaneTop(true); } else { AnchorPane.setLeftAnchor(this.listLeftSubBackground, Double.valueOf(85.0D)); this.listLeftSubBackground.setPrefWidth(320.0D); this.navigationSubBackground.setVisible(true); AnchorPane.setLeftAnchor(this.roomRightSubBackground, Double.valueOf(405.0D)); this.listtop_addFriendButton.setLayoutY(49.0D); modifiedListSubPaneTop(false); }  }); } private void showSearchPane() { try { Stage stage = new Stage();
/* 327 */       FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/searchfriend/searchfriend.fxml"));
/* 328 */       Parent parent = loader.<Parent>load();
/* 329 */       this.searchFriendController = loader.<SearchFriendController>getController();
/* 330 */       Scene scene = new Scene(parent);
/* 331 */       stage.setTitle("Tìm bạn");
/* 332 */       stage.setScene(scene);
/* 333 */       stage.show();
/* 334 */       stage.setOnCloseRequest(value -> this.searchFriendController = null);
/*     */       
/*     */        }
/*     */     
/* 338 */     catch (Exception e)
/* 339 */     { e.printStackTrace(); }
/*     */      } private void searchTextFieldListener() { this.listtop_searchTextfield.focusedProperty().addListener(new ChangeListener<Boolean>() { public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) { if (newValue.booleanValue()) { BackgroundController.this.changeScrollpaneStatus(3); }
/*     */             else { BackgroundController.this.initFriendList(); if (BackgroundController.this.listtop_searchTextfield.getText().trim().length() == 0)
/*     */                 BackgroundController.this.changeButtonStatus(BackgroundController.this.currentIndex);  }
/*     */              } }
/*     */       ); this.listtop_searchTextfield.setOnKeyPressed(value -> { this.searchFlowpane.getChildren().clear(); Platform.runLater(()->{
	Controller.getInstance().addTagToFlowPane(listtop_searchTextfield.getText());
});
/*     */         }); }
/*     */   private void modifiedListSubPaneTop(boolean status) { if (status) { this.listSubPaneTop.getChildren().remove(this.listtop_bannerUsername); this.listSubPaneTop.getChildren().remove(this.listtop_searchTextfield); this.listtop_addFriendButton.setLayoutY(10.0D); }
/*     */     else if (!this.listSubPaneTop.getChildren().contains(this.listtop_bannerUsername)) { this.listSubPaneTop.getChildren().add(this.listtop_bannerUsername); this.listSubPaneTop.getChildren().add(this.listtop_searchTextfield); this.listtop_addFriendButton.setLayoutY(49.0D); }
/*     */      }
/*     */   private void listLeftSubBackground() { this.list_scrolls.add(this.listbot_message); this.list_scrolls.add(this.listbot_room); this.list_scrolls.add(this.listbot_friend); this.list_scrolls.add(this.listbot_search); listbot_friendListener(); }
/* 350 */   public void upToSearchPane(ArrayList<Person> persons) { if (this.searchFriendController != null)
/* 351 */       this.searchFriendController.updateData(persons);  } private void listbot_friendListener() { this.listbot_message.setOnMouseEntered(event -> this.listbot_message.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED)); this.messageFlowpane.setOnMouseExited(event -> this.listbot_message.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER)); this.listbot_friend.setOnMouseEntered(event -> this.listbot_friend.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED)); this.listbot_friend.setOnMouseExited(event -> this.listbot_friend.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER)); this.listbot_room.setOnMouseEntered(event -> this.listbot_room.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED)); this.listbot_room.setOnMouseExited(event -> this.listbot_room.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER)); this.listbot_search.setOnMouseEntered(event -> this.listbot_search.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED)); this.listbot_search.setOnMouseExited(event -> this.listbot_search.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER)); }
/*     */   private void navigationSubBackgroundListener() { this.nav_buttons.add(this.nav_messageButton); this.nav_buttons.add(this.nav_roomButton); this.nav_buttons.add(this.nav_friendButton); this.nav_buttons.add(this.nav_logoutButton); this.nav_messageButton.setStyle("-fx-background-color:white"); this.userButton.setOnMousePressed(value -> Controller.getInstance().showUserInfo()); this.nav_messageButton.setOnMousePressed(event -> { this.currentIndex = 0; this.flag = true; changeButtonStatus(0); }); this.nav_roomButton.setOnMousePressed(event -> { this.currentIndex = 1; this.flag = true; changeButtonStatus(1); }); this.nav_friendButton.setOnMousePressed(event -> { this.currentIndex = 2; this.flag = true; changeButtonStatus(2); }); this.nav_logoutButton.setOnMousePressed(event -> { this.flag = false; changeButtonStatus(3); Platform.runLater(()->makeAllEmpty()); try { Thread.sleep(80L); } catch (Exception e) { e.printStackTrace(); }  Controller.getInstance().logoutRequest(); System.exit(0); Platform.exit(); }); }
/*     */   private void changeButtonStatus(int index) { for (Button button : this.nav_buttons) button.setStyle("-fx-background-color:transparent");  ((Button)this.nav_buttons.get(index)).setStyle("-fx-background-color:white"); if (this.flag) changeScrollpaneStatus(index);  }
/*     */   private void changeScrollpaneStatus(int index) { for (ScrollPane scroll : this.list_scrolls)
/*     */       scroll.setVisible(false);  ((ScrollPane)this.list_scrolls.get(index)).setVisible(true); }
/* 356 */   public void closeWindows() { Stage current = (Stage)this.parentbackgroundPane.getScene().getWindow();
/* 357 */     current.close(); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addToMessageFlowPane(Parent parent) {
/* 362 */     if (!this.messageFlowpane.getChildren().contains(parent)) {
/* 363 */       this.messageFlowpane.getChildren().add(0, parent);
/*     */     }
/*     */   }
/*     */   
/*     */   public void removeToMessageFlowPane(Parent parent) {
/* 368 */     this.messageFlowpane.getChildren().remove(parent);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addToFriendFlowPane(Parent parent) {
/* 373 */     if (!this.friendFlowpane.getChildren().contains(parent)) {
/* 374 */       this.friendFlowpane.getChildren().add(parent);
/*     */     }
/*     */   }
/*     */   
/*     */   public void removeToFriendFlowPane(Parent parent) {
/* 379 */     if (this.friendFlowpane.getChildren().contains(parent)) {
/* 380 */       this.friendFlowpane.getChildren().remove(parent);
/*     */     }
/*     */   }
/*     */   
/*     */   public void addToRightPane(Parent parent) {
/* 385 */     AnchorPane.setBottomAnchor(parent, Double.valueOf(0.0D));
/* 386 */     AnchorPane.setTopAnchor(parent, Double.valueOf(0.0D));
/* 387 */     AnchorPane.setRightAnchor(parent, Double.valueOf(0.0D));
/* 388 */     AnchorPane.setLeftAnchor(parent, Double.valueOf(0.0D));
/* 389 */     this.roomRightSubBackground.getChildren().add(parent);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeToRightPane(Parent parent) {
/* 394 */     if (this.roomRightSubBackground.getChildren().contains(parent)) {
/* 395 */       this.roomRightSubBackground.getChildren().remove(parent);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void addToRoomFlowPane(Parent parent) {
/* 401 */     if (!this.roomFlowpane.getChildren().contains(parent)) {
/* 402 */       this.roomFlowpane.getChildren().add(parent);
/*     */     }
/*     */   }
/*     */   
/*     */   public void removeToRoomFlowPane(Parent parent) {
/* 407 */     if (this.roomFlowpane.getChildren().contains(parent)) {
/* 408 */       this.roomFlowpane.getChildren().remove(parent);
/*     */     }
/*     */   }
/*     */   
/*     */   public void addToSearchFlowPane(Parent parent) {
/* 413 */     if (!this.searchFlowpane.getChildren().contains(parent)) {
/* 414 */       this.searchFlowpane.getChildren().add(parent);
/*     */     }
/*     */   }
/*     */   
/*     */   public void changeImageUserButton(ImagePattern pattern) {
/* 419 */     Circle circle = new Circle(32.0D, 32.0D, 32.0D);
/* 420 */     circle.setFill(pattern);
/* 421 */     this.userButton.setGraphic(circle);
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeUserName(String name) {
/* 426 */     this.listtop_bannerUsername.setText(name);
/*     */   } }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\controller\subcontroller\BackgroundController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */