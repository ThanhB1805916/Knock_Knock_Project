/*    */ package controller.subcontroller.info;
/*    */ 
/*    */ import controller.Controller;
/*    */ import java.io.File;
/*    */ import java.net.URL;
/*    */ import java.util.ResourceBundle;
/*    */ import javafx.fxml.FXML;
/*    */ import javafx.fxml.Initializable;
/*    */ import javafx.scene.control.Label;
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
/*    */ public class FriendInfoController
/*    */   implements Initializable
/*    */ {
/*    */   @FXML
/*    */   private Label userSex;
/*    */   @FXML
/*    */   private Label userNameBanner;
/*    */   @FXML
/*    */   private Label userName;
/*    */   @FXML
/*    */   private Label userID;
/*    */   @FXML
/*    */   private Label userDate;
/*    */   @FXML
/*    */   private ImageView friendImage;
/*    */   private int id;
/*    */   
/*    */   public void initialize(URL location, ResourceBundle resources) {}
/*    */   
/*    */   public void setInfo(int id) {
/* 47 */     this.id = id;
/* 48 */     this.userNameBanner.setText(Controller.getInstance().getPersonById(id).getName());
/* 49 */     this.userID.setText(Integer.toString(Controller.getInstance().getPersonById(id).getId()));
/* 50 */     this.userName.setText(Controller.getInstance().getPersonById(id).getUsername());
/* 51 */     this.userDate.setText(Controller.getInstance().getPersonById(id).getDateofbirth().toString());
/* 52 */     if (Controller.getInstance().getPersonById(id).getMale())
/* 53 */     { this.userSex.setText("Nam"); }
/* 54 */     else { this.userSex.setText("Nữ"); }
/* 55 */      if (Controller.getInstance().getPersonById(id).getAvatar().getFile("tmp/"))
/* 56 */     { this.friendImage.setImage(new Image((new File("tmp/" + Controller.getInstance().getPersonById(id).getAvatar().getName())).toURI().toString())); }
/* 57 */     else { this.friendImage.setImage(new Image((new File("tmp/user_icon.png")).toURI().toString())); }
/*    */   
/*    */   }
/*    */   
/*    */   public void update(Person person) {
/* 62 */     setInfo(this.id);
/*    */   }
/*    */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\controller\subcontroller\info\FriendInfoController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */