/*     */ package controller.subcontroller;
/*     */ 
/*     */ import controller.Controller;
/*     */ import controller.subcontroller.room.RoomController;
/*     */ import java.net.URL;
/*     */ import java.util.ResourceBundle;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.fxml.FXML;
/*     */ import javafx.fxml.Initializable;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.control.Label;
/*     */ import javafx.scene.control.MenuItem;
/*     */ import javafx.scene.image.Image;
/*     */ import javafx.scene.image.ImageView;
/*     */ import javafx.scene.input.MouseEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MessageTagController
/*     */   implements Initializable
/*     */ {
/*     */   @FXML
/*     */   private Button tagButton;
/*     */   @FXML
/*     */   private Button moreButton;
/*     */   @FXML
/*     */   private Label currentMessage;
/*     */   @FXML
/*     */   private Label userName;
/*     */   @FXML
/*     */   private ImageView userImage;
/*     */   @FXML
/*     */   private ImageView moreImage;
/*     */   @FXML
/*     */   private MenuItem seeRoomInfo;
/*     */   @FXML
/*     */   private MenuItem removeMessageTag;
/*     */   private RoomController roomController;
/*     */   
/*     */   public void initialize(URL location, ResourceBundle resources) {
/*  43 */     setMoreButton();
/*  44 */     setContextMenuItem();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void showFromMessageTag(MouseEvent event) {
/*  55 */     this.roomController.showThisRoom();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setMoreButton() {
/*  66 */     this.moreButton.setOnMouseEntered(event -> this.moreImage.setOpacity(0.5D));
/*     */ 
/*     */     
/*  69 */     this.moreButton.setOnMouseExited(event -> this.moreImage.setOpacity(0.0D));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setContextMenuItem() {
/*  76 */     this.seeRoomInfo.setOnAction(action -> Controller.getInstance().showRoomInfo(this.roomController.getId()));
/*     */ 
/*     */ 
/*     */     
/*  80 */     this.removeMessageTag.setOnAction(action -> Controller.getInstance().removeMessage(this.roomController));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setImageRoom(Image image) {
/*  87 */     this.userImage.setImage(image);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParent(RoomController roomController) {
/*  97 */     this.roomController = roomController;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateCurrentMessage(String message) {
/* 102 */     this.currentMessage.setText(message);
/*     */   }
/*     */ 
/*     */   
/*     */   public void callUpdate(String name, Image image) {
/* 107 */     this.userName.setText(name);
/* 108 */     setImageRoom(image);
/*     */   }
/*     */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\controller\subcontroller\MessageTagController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */