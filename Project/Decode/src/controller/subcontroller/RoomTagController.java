/*    */ package controller.subcontroller;
/*    */ 
/*    */ import controller.Controller;
/*    */ import controller.subcontroller.room.RoomController;
/*    */ import java.net.URL;
/*    */ import java.util.ResourceBundle;
/*    */ import javafx.event.ActionEvent;
/*    */ import javafx.fxml.FXML;
/*    */ import javafx.fxml.Initializable;
/*    */ import javafx.scene.control.Button;
/*    */ import javafx.scene.control.Label;
/*    */ import javafx.scene.control.MenuItem;
/*    */ import javafx.scene.image.Image;
/*    */ import javafx.scene.image.ImageView;
/*    */ import javafx.scene.input.MouseEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RoomTagController
/*    */   implements Initializable
/*    */ {
/*    */   @FXML
/*    */   private Button moreButton;
/*    */   @FXML
/*    */   private Button roomFillButton;
/*    */   @FXML
/*    */   private ImageView roomImage;
/*    */   @FXML
/*    */   private ImageView moreImage;
/*    */   @FXML
/*    */   private Label roomName;
/*    */   @FXML
/*    */   private MenuItem seeRoomInfo;
/*    */   @FXML
/*    */   private MenuItem exitRoom;
/*    */   private RoomController roomController;
/*    */   
/*    */   public void initialize(URL location, ResourceBundle resources) {
/* 43 */     setMoreButtonMotion();
/* 44 */     setContextMenuItem();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void setImageRoom(Image image) {
/* 54 */     this.roomImage.setImage(image);
/*    */   }
/*    */ 
/*    */   
/*    */   private void setMoreButtonMotion() {
/* 59 */     this.moreButton.setOnMouseEntered(event -> this.moreImage.setOpacity(0.5D));
/*    */ 
/*    */ 
/*    */     
/* 63 */     this.moreButton.setOnMouseExited(event -> this.moreImage.setOpacity(0.0D));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void setContextMenuItem() {
/* 70 */     this.seeRoomInfo.setOnAction(action -> Controller.getInstance().showRoomInfo(this.roomController.getId()));
/*    */ 
/*    */ 
/*    */     
/* 74 */     this.exitRoom.setOnAction(action -> {
/*    */           if (Controller.getInstance().alertConfirmation("Bạn có chắc muốn rời khỏi phòng", "Thông báo", null)) {
/*    */             Controller.getInstance().leaveRoom(this.roomController.getId());
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void showFromRoomTag(MouseEvent event) {
/* 86 */     this.roomController.showThisRoom();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setParent(RoomController roomController) {
/* 91 */     this.roomController = roomController;
/*    */   }
/*    */ 
/*    */   
/*    */   public void callUpdate(String name, Image image) {
/* 96 */     this.roomName.setText(name);
/* 97 */     setImageRoom(image);
/*    */   }
/*    */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\controller\subcontroller\RoomTagController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */