/*    */ package controller.subcontroller.info;
/*    */ 
/*    */ import controller.Controller;
/*    */ import java.io.File;
/*    */ import java.net.URL;
/*    */ import java.util.ResourceBundle;
/*    */ import javafx.fxml.FXML;
/*    */ import javafx.fxml.Initializable;
/*    */ import javafx.scene.control.Button;
/*    */ import javafx.scene.image.Image;
/*    */ import javafx.scene.image.ImageView;
/*    */ import javafx.scene.input.MouseEvent;
/*    */ import model.sendmodel.Person;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MemberOvalController
/*    */   implements Initializable
/*    */ {
/*    */   @FXML
/*    */   private Button memberButton;
/*    */   @FXML
/*    */   private ImageView memberImage;
/*    */   private int roomId;
/*    */   private int memberId;
/*    */   
/*    */   public void initialize(URL location, ResourceBundle resources) {}
/*    */   
/*    */   public void setInfo(int memeberId, int roomId) {
/* 46 */     this.roomId = roomId;
/* 47 */     this.memberId = memeberId;
/* 48 */     setMemberImage();
/* 49 */     setMemberButton();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void setMemberImage() {
/* 59 */     Person person = Controller.getInstance().getMemberById(this.roomId, this.memberId);
/* 60 */     person.getAvatar().getFile("tmp/");
/* 61 */     this.memberImage.setImage(new Image((new File("tmp/" + person.getAvatar().getName())).toURI().toString()));
/*    */   }
/*    */ 
/*    */   
/*    */   private void setMemberButton() {
/* 66 */     this.memberButton.setOnMousePressed(value -> Controller.getInstance().showMemberInfo(this.memberId, this.roomId));
/*    */   }
/*    */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\controller\subcontroller\info\MemberOvalController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */