/*    */ package controller.subcontroller.search;
/*    */ 
/*    */ import controller.Controller;
/*    */ import java.io.File;
/*    */ import java.net.URL;
/*    */ import java.time.LocalDate;
/*    */ import java.util.ResourceBundle;
/*    */ import javafx.fxml.FXML;
/*    */ import javafx.fxml.Initializable;
/*    */ import javafx.scene.control.Button;
/*    */ import javafx.scene.control.Label;
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
/*    */ public class SearchFriendTagController
/*    */   implements Initializable
/*    */ {
/*    */   @FXML
/*    */   private Label genderLabel;
/*    */   @FXML
/*    */   private Button addButton;
/*    */   @FXML
/*    */   private Label dateBrithTextField;
/*    */   @FXML
/*    */   private Label nameLabel;
/*    */   @FXML
/*    */   private ImageView avatar;
/*    */   private int id;
/*    */   
/*    */   public void initialize(URL location, ResourceBundle resources) {
/* 47 */     setAddButton();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setInfo(Person person) {
/* 57 */     this.id = person.getId();
/*    */     
/* 59 */     this.nameLabel.setText(person.getName());
/*    */     
/* 61 */     LocalDate localDate = person.getDateofbirth();
/*    */     
/* 63 */     this.dateBrithTextField.setText(String.valueOf(localDate.getDayOfMonth()) + "." + localDate.getMonthValue() + "." + localDate.getYear());
/*    */     
/* 65 */     if (person.getMale()) {
/* 66 */       this.genderLabel.setText("Nam");
/*    */     } else {
/* 68 */       this.genderLabel.setText("Nữ");
/*    */     } 
/* 70 */     if (person.getAvatar().getFile("tmp/")) {
/* 71 */       this.avatar.setImage(new Image((new File("tmp/" + person.getAvatar().getName())).toURI().toString()));
/*    */     } else {
/* 73 */       this.avatar.setImage(new Image((new File("tmp/user_icon.png")).toURI().toString()));
/*    */     } 
/* 75 */     if (Controller.getInstance().isFriend(this.id)) {
/* 76 */       this.addButton.setDisable(true);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void setAddButton() {
/* 86 */     this.addButton.setOnMousePressed(value -> {
/*    */           Controller.getInstance().addMoreFriend(this.id);
/*    */           this.addButton.setDisable(true);
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\controller\subcontroller\search\SearchFriendTagController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */