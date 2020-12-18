/*     */ package controller.subcontroller.info;
/*     */ 
/*     */ import controller.Controller;
/*     */ import java.io.File;
/*     */ import java.net.URL;
/*     */ import java.util.ResourceBundle;
/*     */ import javafx.fxml.FXML;
/*     */ import javafx.fxml.FXMLLoader;
/*     */ import javafx.fxml.Initializable;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.control.ContextMenu;
/*     */ import javafx.scene.control.TextField;
/*     */ import javafx.scene.image.Image;
/*     */ import javafx.scene.image.ImageView;
/*     */ import javafx.scene.input.MouseEvent;
/*     */ import javafx.scene.layout.AnchorPane;
/*     */ import javafx.scene.layout.FlowPane;
/*     */ import javafx.stage.FileChooser;
/*     */ import javafx.stage.Stage;
/*     */ import javafx.stage.StageStyle;
/*     */ import model.sendmodel.FileInfo;
/*     */ import model.sendmodel.Person;
/*     */ import model.sendmodel.Room;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RoomInfoController
/*     */   implements Initializable
/*     */ {
/*     */   @FXML
/*     */   private Button roomAdd;
/*     */   @FXML
/*     */   private Button roomChangeAvatar;
/*     */   @FXML
/*     */   private Button changeName;
/*     */   @FXML
/*     */   private Button completeName;
/*     */   @FXML
/*     */   private FlowPane flowMember;
/*     */   @FXML
/*     */   private FlowPane flowCanAdd;
/*     */   @FXML
/*     */   private ImageView roomAvatar;
/*     */   @FXML
/*     */   private TextField roomName;
/*     */   @FXML
/*     */   private ContextMenu contextaddmenu;
/*     */   private int id;
/*     */   private String currentName;
/*     */   
/*     */   public void initialize(URL location, ResourceBundle resources) {
/*  58 */     setButton();
/*  59 */     this.currentName = this.roomName.getText();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInfo(int id) {
/*  69 */     this.id = id;
/*  70 */     setRoomName();
/*  71 */     setRoomAvatar();
/*  72 */     setFlowMember();
/*  73 */     setFlowCanAdd();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateInfo(int id) {
/*  78 */     setRoomName();
/*  79 */     setRoomAvatar();
/*  80 */     setFlowMember();
/*  81 */     setFlowCanAdd();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setRoomName() {
/*  91 */     this.roomName.setText(Controller.getInstance().getRoomById(this.id).getName());
/*     */   }
/*     */ 
/*     */   
/*     */   private void setRoomAvatar() {
/*  96 */     if (Controller.getInstance().getRoomById(this.id).getAvatar().getFile("tmp/")) {
/*  97 */       this.roomAvatar.setImage(new Image((new File("tmp/" + Controller.getInstance().getRoomById(this.id).getAvatar().getName())).toURI().toString()));
/*     */     } else {
/*  99 */       this.roomAvatar.setImage(new Image((new File("tmp/user_icon.png")).toURI().toString()));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setFlowMember() {
/* 104 */     this.flowMember.getChildren().clear();
/* 105 */     for (Person person : Controller.getInstance().getRoomById(this.id).getMembers()) {
/* 106 */       if (person.getId() != Controller.getInstance().getUserId().intValue())
/*     */         try {
/* 108 */           FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/subinfo/memberoval.fxml"));
/* 109 */           AnchorPane memberPane = loader.<AnchorPane>load();
/* 110 */           MemberOvalController memberController = loader.<MemberOvalController>getController();
/* 111 */           memberController.setInfo(person.getId(), this.id);
/* 112 */           this.flowMember.getChildren().add(memberPane);
/*     */         } catch (Exception e) {
/* 114 */           e.printStackTrace();
/*     */         }  
/*     */     } 
/*     */   }
/*     */   private void setFlowCanAdd() {
/* 119 */     this.flowCanAdd.getChildren().clear();
/* 120 */     Room room = Controller.getInstance().getRoomById(this.id);
/*     */     
/* 122 */     for (int i = 0; i < Controller.getInstance().getFriendList().size(); i++) {
/* 123 */       if (!isInRoom(room, ((Person)Controller.getInstance().getFriendList().get(i)).getId())) {
/*     */         try {
/* 125 */           FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/subinfo/membercanadd.fxml"));
/* 126 */           AnchorPane memberPane = loader.<AnchorPane>load();
/* 127 */           MemberCanAddController memberController = loader.<MemberCanAddController>getController();
/* 128 */           Person person = Controller.getInstance().getFriendList().get(i);
/* 129 */           memberController.setInfo(person.getId());
/* 130 */           memberPane.setOnMouseClicked(value -> {
/*     */                 if (Controller.getInstance().alertConfirmation("Xác nhận", "Bạn có chắc muốn thêm " + person.getName(), null)) {
/*     */                   Controller.getInstance().addMemberToRoom(this.id, person.getId());
/*     */                   updateInfo(this.id);
/*     */                 } 
/*     */               });
/* 136 */           this.flowCanAdd.getChildren().add(memberPane);
/* 137 */         } catch (Exception e) {
/* 138 */           e.printStackTrace();
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setButton() {
/* 145 */     this.roomChangeAvatar.setOnMousePressed(value -> {
/*     */           Stage stage = new Stage(StageStyle.UTILITY);
/*     */           
/*     */           FileChooser fileChooser = new FileChooser();
/*     */           
/*     */           fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter[] { new FileChooser.ExtensionFilter("JPG", new String[] { "*.jpg", "*.png", "*.gif" }) });
/*     */           
/*     */           File file = fileChooser.showOpenDialog(stage);
/*     */           
/*     */           if (file != null && Controller.getInstance().alertConfirmation("Bạn có chắc muốn đổi ảnh của phòng không ?", "Xác nhận", null)) {
/*     */             Controller.getInstance().changeAvatarRoom(this.id, new FileInfo(file.getPath()));
/*     */             this.roomAvatar.setImage(new Image(file.toURI().toString()));
/*     */           } 
/*     */         });
/* 159 */     this.changeName.setOnMousePressed(value -> {
/*     */           this.currentName = this.roomName.getText();
/*     */           
/*     */           this.roomName.setEditable(true);
/*     */           this.changeName.setVisible(false);
/*     */           this.completeName.setVisible(true);
/*     */         });
/* 166 */     this.completeName.setOnMousePressed(value -> {
/*     */           this.roomName.setEditable(false);
/*     */           this.changeName.setVisible(true);
/*     */           this.completeName.setVisible(false);
/*     */           if (this.roomName.getText().toString().trim().length() > 0 && !this.currentName.equals(this.roomName.getText())) {
/*     */             this.currentName = this.roomName.getText();
/*     */             Controller.getInstance().changeNameRoom(this.id, this.currentName);
/*     */           } else {
/*     */             this.roomName.setText(this.currentName);
/*     */           } 
/*     */         });
/*     */   }
/*     */   private boolean isInRoom(Room room, int id) {
/* 179 */     for (Person person : room.getMembers()) {
/* 180 */       if (person.getId() == id)
/* 181 */         return true; 
/* 182 */     }  return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\controller\subcontroller\info\RoomInfoController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */