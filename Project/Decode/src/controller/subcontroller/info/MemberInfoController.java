/*    */ package controller.subcontroller.info;
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
/*    */ import javafx.stage.Stage;
/*    */ import model.sendmodel.Person;
/*    */ import model.sendmodel.RoomPersonModel;
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
/*    */ public class MemberInfoController
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
/*    */   private Button userDelete;
/*    */   @FXML
/*    */   private Button userAdd;
/*    */   @FXML
/*    */   private ImageView userImage;
/*    */   private Person person;
/*    */   private int roomId;
/*    */   
/*    */   public void initialize(URL location, ResourceBundle resources) {}
/*    */   
/*    */   public void setInfo(Person person, int id) {
/* 54 */     this.person = person;
/* 55 */     this.roomId = id;
/* 56 */     callUpdate(person, id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update(Person person) {
/* 61 */     callUpdate(person, this.roomId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDeleteButton(RoomInfoController roomInfoController) {
/* 66 */     this.userAdd.setOnMousePressed(value -> Controller.getInstance().addMoreFriend(this.person.getId()));
/*    */ 
/*    */ 
/*    */     
/* 70 */     this.userDelete.setOnMousePressed(value -> {
/*    */           Controller.getInstance().deleteMember(new RoomPersonModel(Controller.getInstance().getRoomById(this.roomId), this.person));
/*    */           ((Stage)this.userDelete.getScene().getWindow()).close();
/*    */           roomInfoController.updateInfo(this.roomId);
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void callUpdate(Person person, int id) {
/* 84 */     this.userNameBanner.setText(person.getName());
/* 85 */     this.userID.setText(Integer.toString(person.getId()));
/* 86 */     this.userName.setText(person.getUsername());
/* 87 */     this.userDate.setText(person.getDateofbirth().toString());
/* 88 */     person.getAvatar().getFile("tmp/");
/* 89 */     this.userImage.setImage(new Image((new File("tmp/" + person.getAvatar().getName())).toURI().toString()));
/* 90 */     if (person.getMale())
/* 91 */     { this.userSex.setText("Nam"); }
/* 92 */     else { this.userSex.setText("Nữ"); }
/* 93 */      if (Controller.getInstance().getUserId().intValue() != person.getId() && Controller.getInstance().getPersonById(person.getId()) == null)
/* 94 */       this.userAdd.setVisible(true); 
/*    */   }
/*    */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\controller\subcontroller\info\MemberInfoController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */