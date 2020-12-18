/*    */ package controller.subcontroller.createroom;
/*    */ 
/*    */ import controller.Controller;
/*    */ import java.net.URL;
/*    */ import java.util.ResourceBundle;
/*    */ import javafx.fxml.FXML;
/*    */ import javafx.fxml.Initializable;
/*    */ import javafx.scene.control.Button;
/*    */ import javafx.scene.control.RadioButton;
/*    */ import javafx.scene.image.ImageView;
/*    */ import javafx.scene.input.MouseEvent;
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
/*    */ public class FriendOvalController
/*    */   implements Initializable
/*    */ {
/*    */   @FXML
/*    */   private ImageView avatar;
/*    */   @FXML
/*    */   private ImageView checkedImage;
/*    */   @FXML
/*    */   private Button buttonFill;
/*    */   private boolean status = false;
/*    */   
/*    */   public void initialize(URL location, ResourceBundle resources) {}
/*    */   
/*    */   public void setRadio(RadioButton radio) {
/* 39 */     this.buttonFill.setOnMousePressed(value -> {
/*    */           radio.setSelected(this.status = !this.status);
/*    */           this.checkedImage.setVisible(this.status);
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   public void setImage(int id) {
/* 47 */     this.avatar.setImage(Controller.getInstance().getImageOfPerson(id));
/*    */   }
/*    */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\controller\subcontroller\createroom\FriendOvalController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */