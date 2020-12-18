/*    */ package controller.subcontroller.info;
/*    */ 
/*    */ import controller.Controller;
/*    */ import java.io.File;
/*    */ import java.net.URL;
/*    */ import java.util.ResourceBundle;
/*    */ import javafx.fxml.FXML;
/*    */ import javafx.fxml.Initializable;
/*    */ import javafx.scene.image.Image;
/*    */ import javafx.scene.image.ImageView;
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
/*    */ 
/*    */ public class MemberCanAddController
/*    */   implements Initializable
/*    */ {
/*    */   @FXML
/*    */   private ImageView memberImage;
/*    */   private int memberId;
/*    */   
/*    */   public void initialize(URL location, ResourceBundle resources) {}
/*    */   
/*    */   public void setInfo(int memeberId) {
/* 42 */     this.memberId = memeberId;
/* 43 */     setMemberImage();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void setMemberImage() {
/* 53 */     Person person = Controller.getInstance().getPersonById(this.memberId);
/* 54 */     person.getAvatar().getFile("tmp/");
/* 55 */     this.memberImage.setImage(new Image((new File("tmp/" + person.getAvatar().getName())).toURI().toString()));
/*    */   }
/*    */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\controller\subcontroller\info\MemberCanAddController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */