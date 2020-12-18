/*    */ package controller.subcontroller;
/*    */ 
/*    */ import controller.Controller;
/*    */ import java.io.File;
/*    */ import java.net.URL;
/*    */ import java.util.ResourceBundle;
/*    */ import javafx.fxml.FXML;
/*    */ import javafx.fxml.Initializable;
/*    */ import javafx.scene.control.Button;
/*    */ import javafx.scene.control.Label;
/*    */ import javafx.scene.image.Image;
/*    */ import javafx.scene.image.ImageView;
/*    */ import javafx.scene.input.MouseEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FriendTagController
/*    */   implements Initializable
/*    */ {
/*    */   @FXML
/*    */   private Label friendBrith;
/*    */   @FXML
/*    */   private Label friendName;
/*    */   @FXML
/*    */   private Label friendSex;
/*    */   @FXML
/*    */   private ImageView friendImage;
/*    */   @FXML
/*    */   private ImageView moreImage;
/*    */   @FXML
/*    */   private Button fillButton;
/*    */   @FXML
/*    */   private Button moreButton;
/*    */   private int friendId;
/*    */   
/*    */   public void initialize(URL location, ResourceBundle resources) {
/* 38 */     setButtonListener();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void setButtonListener() {
/* 48 */     this.fillButton.setOnMousePressed(event -> Controller.getInstance().showFriendInfo(this.friendId));
/*    */ 
/*    */     
/* 51 */     this.moreButton.setOnMouseEntered(value -> this.moreImage.setVisible(true));
/*    */ 
/*    */ 
/*    */     
/* 55 */     this.moreButton.setOnMouseExited(value -> this.moreImage.setVisible(false));
/*    */ 
/*    */ 
/*    */     
/* 59 */     this.moreButton.setOnMousePressed(value -> Controller.getInstance().removeFriend(this.friendId));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void setInfo() {
/* 66 */     this.friendBrith.setText(Controller.getInstance().getPersonById(this.friendId).getDateofbirth().toString());
/* 67 */     this.friendName.setText(Controller.getInstance().getPersonById(this.friendId).getName());
/* 68 */     if (Controller.getInstance().getPersonById(this.friendId).getMale())
/* 69 */     { this.friendSex.setText("Nam"); }
/* 70 */     else { this.friendSex.setText("Nữ"); }
/* 71 */      if (Controller.getInstance().getPersonById(this.friendId).getAvatar().getFile("tmp/"))
/* 72 */     { this.friendImage.setImage(new Image((new File("tmp/" + Controller.getInstance().getPersonById(this.friendId).getAvatar().getName())).toURI().toString())); }
/* 73 */     else { this.friendImage.setImage(new Image((new File("tmp/user_icon.png")).toURI().toString())); }
/*    */   
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setFriendID(int id) {
/* 83 */     this.friendId = id;
/* 84 */     setInfo();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFriendID() {
/* 89 */     return this.friendId;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 94 */     setInfo();
/*    */   }
/*    */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\controller\subcontroller\FriendTagController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */