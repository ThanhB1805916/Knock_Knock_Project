/*    */ package controller.subcontroller.createroom;
/*    */ 
/*    */ import controller.Controller;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.net.URL;
/*    */ import java.util.ArrayList;
/*    */ import java.util.ResourceBundle;
/*    */ import javafx.fxml.FXML;
/*    */ import javafx.fxml.FXMLLoader;
/*    */ import javafx.fxml.Initializable;
/*    */ import javafx.scene.Parent;
/*    */ import javafx.scene.control.Alert;
/*    */ import javafx.scene.control.Button;
/*    */ import javafx.scene.control.RadioButton;
/*    */ import javafx.scene.control.TextField;
/*    */ import javafx.scene.image.Image;
/*    */ import javafx.scene.image.ImageView;
/*    */ import javafx.scene.input.MouseEvent;
/*    */ import javafx.scene.layout.FlowPane;
/*    */ import javafx.stage.FileChooser;
/*    */ import javafx.stage.Stage;
/*    */ import model.sendmodel.Person;
/*    */ 
/*    */ public class CreateRoomController
/*    */   implements Initializable {
/*    */   @FXML
/*    */   TextField name;
/*    */   @FXML
/*    */   FlowPane friend;
/* 31 */   private ArrayList<RadioButton> listChecked = new ArrayList<>(); @FXML Button create; @FXML Button change; @FXML
/* 32 */   ImageView avatar; private ArrayList<Integer> idList = new ArrayList<>();
/* 33 */   private String path = null;
/*    */   
/*    */   public void initialize(URL location, ResourceBundle resources) {
/* 36 */     setListChecked();
/* 37 */     initFriendFlow();
/* 38 */     setCreateButton();
/* 39 */     setChangeButton();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void setListChecked() {
/* 49 */     for (int i = 0; i < Controller.getInstance().getFriendList().size(); i++) {
/* 50 */       this.listChecked.add(new RadioButton(Integer.toString(((Person)Controller.getInstance().getFriendList().get(i)).getId())));
/*    */     }
/*    */   }
/*    */   
/*    */   private void initFriendFlow() {
/* 55 */     for (int i = 0; i < Controller.getInstance().getFriendList().size(); i++) {
/*    */       try {
/* 57 */         FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/createroom/friendoval.fxml"));
/* 58 */         Parent parent = loader.<Parent>load();
/* 59 */         int pID = ((Person)Controller.getInstance().getFriendList().get(i)).getId();
/* 60 */         FriendOvalController friendController = loader.<FriendOvalController>getController();
/* 61 */         ((RadioButton)this.listChecked.get(i)).setText((new StringBuilder(String.valueOf(pID))).toString());
/* 62 */         friendController.setRadio(this.listChecked.get(i));
/* 63 */         friendController.setImage(pID);
/* 64 */         this.friend.getChildren().add(parent);
/* 65 */       } catch (IOException e) {
/* 66 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private boolean checkVaild() {
/* 72 */     this.idList.clear();
/* 73 */     if (this.name.getText().trim().length() > 0) {
/* 74 */       for (RadioButton radioButton : this.listChecked) {
/* 75 */         if (radioButton.isSelected())
/* 76 */           this.idList.add(Integer.valueOf(Integer.parseInt(radioButton.getText()))); 
/* 77 */       }  if (this.idList.size() > 0)
/* 78 */         return true; 
/*    */     } 
/* 80 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   private void setCreateButton() {
/* 85 */     this.create.setOnMousePressed(value -> {
/*    */           if (checkVaild()) {
/*    */             Controller.getInstance().createNewRoom(this.idList, this.name.getText(), this.path);
/*    */             ((Stage)this.name.getScene().getWindow()).close();
/*    */           } else {
/*    */             Controller.getInstance().alertNotify(Alert.AlertType.ERROR, "Vui lòng điền đầy đủ thông tin!", "Lỗi", null);
/*    */           } 
/*    */         });
/*    */   }
/*    */   
/*    */   private void setChangeButton() {
/* 96 */     this.change.setOnMousePressed(value -> {
/*    */           Stage stage = new Stage();
/*    */           FileChooser fileChooser = new FileChooser();
/*    */           fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter[] { new FileChooser.ExtensionFilter("JPG", new String[] { "*.jpg" }), new FileChooser.ExtensionFilter("PNG", new String[] { "*.png" }) });
/*    */           fileChooser.setTitle("Open Resource File");
/*    */           File file = fileChooser.showOpenDialog(stage);
/*    */           if (file != null) {
/*    */             this.avatar.setImage(new Image(file.toURI().toString()));
/*    */             this.path = file.getPath();
/*    */           } 
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\controller\subcontroller\createroom\CreateRoomController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */