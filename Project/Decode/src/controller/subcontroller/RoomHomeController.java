/*     */ package controller.subcontroller;
/*     */ 
/*     */ import controller.Controller;
/*     */ import controller.subcontroller.room.RoomController;
/*     */ import java.io.File;
/*     */ import java.net.URL;
/*     */ import java.time.LocalDateTime;
/*     */ import java.util.ResourceBundle;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.fxml.FXML;
/*     */ import javafx.fxml.Initializable;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.control.Label;
/*     */ import javafx.scene.control.MenuButton;
/*     */ import javafx.scene.control.MenuItem;
/*     */ import javafx.scene.control.ScrollPane;
/*     */ import javafx.scene.control.TextField;
/*     */ import javafx.scene.image.Image;
/*     */ import javafx.scene.image.ImageView;
/*     */ import javafx.scene.input.KeyCode;
/*     */ import javafx.scene.input.KeyEvent;
/*     */ import javafx.scene.input.MouseEvent;
/*     */ import javafx.scene.layout.AnchorPane;
/*     */ import javafx.scene.layout.FlowPane;
/*     */ import javafx.stage.FileChooser;
/*     */ import javafx.stage.Stage;
/*     */ import model.sendmodel.FileInfo;
/*     */ import model.sendmodel.Message;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RoomHomeController
/*     */   implements Initializable
/*     */ {
/*     */   @FXML
/*     */   TextField sendTextfield;
/*     */   @FXML
/*     */   MenuButton menuSendButton;
/*     */   @FXML
/*     */   Label roomNameLabel;
/*     */   @FXML
/*     */   Label amountMemberLabel;
/*     */   @FXML
/*     */   Button sendButton;
/*     */   @FXML
/*     */   Button moreInfoButton;
/*     */   @FXML
/*     */   FlowPane chatBox;
/*     */   
/*     */   public void initialize(URL location, ResourceBundle resources) {
/*  61 */     setSendButton();
/*  62 */     sendImageItemAction();
/*  63 */     setMoreInfoButton();
/*     */   }
/*     */   @FXML
/*     */   ImageView roomImage; @FXML
/*     */   ScrollPane scrollMessage; @FXML
/*     */   MenuItem sendImageItem; @FXML
/*     */   MenuItem sendAudioItem; @FXML
/*     */   MenuItem sendVideoItem; @FXML
/*     */   MenuItem sendFileItem; @FXML
/*     */   MenuItem sendDocumentItem; private RoomController roomController;
/*     */   private void setMoreInfoButton() {
/*  74 */     this.moreInfoButton.setOnMouseClicked(value -> Controller.getInstance().showRoomInfo(this.roomController.getId()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setSendButton() {
/*  81 */     this.sendButton.setOnMousePressed(value -> {
/*     */           String messageData = this.sendTextfield.getText().toString();
/*     */           
/*     */           if (messageData.trim().length() > 0) {
/*     */             sendMessage(messageData, false);
/*     */             this.sendTextfield.setText("");
/*     */           } 
/*     */         });
/*  89 */     this.sendTextfield.setOnKeyPressed(value -> {
/*     */           String messageData = this.sendTextfield.getText().toString();
/*     */           if (value.getCode() == KeyCode.ENTER) {
/*     */             sendMessage(messageData, false);
/*     */             this.sendTextfield.setText("");
/*     */           } 
/*     */         });
/*     */   }
/*     */   
/*     */   private void setImageRoom(Image image) {
/*  99 */     this.roomImage.setImage(image);
/*     */   }
/*     */ 
/*     */   
/*     */   private void sendMessage(String path, boolean isFile) {
/* 104 */     Message message = new Message(Controller.getInstance().getUser(), this.roomController.getRoom().getId(), new FileInfo(path), isFile, LocalDateTime.now());
/* 105 */     Controller.getInstance().sendMessage(this.roomController.getId(), message);
/* 106 */     if (isFile)
/* 107 */     { this.roomController.setMedia(message); }
/* 108 */     else { this.roomController.setMessage(message); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void sendImageItemAction() {
/* 119 */     this.sendImageItem.setOnAction(value -> {
/*     */           Stage stage = new Stage();
/*     */           
/*     */           FileChooser fileChooser = new FileChooser();
/*     */           fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter[] { new FileChooser.ExtensionFilter("IMAGE", new String[] { "*.png", "*.gif", "*.jpg" }) });
/*     */           fileChooser.setTitle("Open Resource File");
/*     */           File tmp = fileChooser.showOpenDialog(stage);
/*     */           if (tmp != null) {
/*     */             sendMessage(tmp.getPath(), true);
/*     */           }
/*     */         });
/* 130 */     this.sendAudioItem.setOnAction(value -> {
/*     */           Stage stage = new Stage();
/*     */           
/*     */           FileChooser fileChooser = new FileChooser();
/*     */           
/*     */           fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter[] { new FileChooser.ExtensionFilter("AUDIO", new String[] { "*.mp3", "*.wav", "*.aac", "*.ogg" }) });
/*     */           fileChooser.setTitle("Open Resource File");
/*     */           File tmp = fileChooser.showOpenDialog(stage);
/*     */           if (tmp != null) {
/*     */             sendMessage(tmp.getPath(), true);
/*     */           }
/*     */         });
/* 142 */     this.sendDocumentItem.setOnAction(value -> {
/*     */           Stage stage = new Stage();
/*     */           
/*     */           FileChooser fileChooser = new FileChooser();
/*     */           
/*     */           fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter[] { new FileChooser.ExtensionFilter("DOCUMENT", new String[] { "*.doc", "*.docx", "*.exls", "*.pptx", "*.pdf", "*.txt", "*.html" }) });
/*     */           fileChooser.setTitle("Open Resource File");
/*     */           File tmp = fileChooser.showOpenDialog(stage);
/*     */           if (tmp != null) {
/*     */             sendMessage(tmp.getPath(), true);
/*     */           }
/*     */         });
/* 154 */     this.sendVideoItem.setOnAction(value -> {
/*     */           Stage stage = new Stage();
/*     */           
/*     */           FileChooser fileChooser = new FileChooser();
/*     */           
/*     */           fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter[] { new FileChooser.ExtensionFilter("VIDEO", new String[] { "*.mp4", "*.mov", "*.wmv", "*.flv", "*.avi", "*.mkv" }) });
/*     */           fileChooser.setTitle("Open Resource File");
/*     */           File tmp = fileChooser.showOpenDialog(stage);
/*     */           if (tmp != null) {
/*     */             sendMessage(tmp.getPath(), true);
/*     */           }
/*     */         });
/* 166 */     this.sendFileItem.setOnAction(value -> {
/*     */           Stage stage = new Stage();
/*     */           FileChooser fileChooser = new FileChooser();
/*     */           fileChooser.setTitle("Open Resource File");
/*     */           File tmp = fileChooser.showOpenDialog(stage);
/*     */           if (tmp != null) {
/*     */             sendMessage(tmp.getPath(), true);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeRoomInfo() {}
/*     */   
/*     */   public void callUpdate(String name, int amount, Image image) {
/* 181 */     this.roomNameLabel.setText(name);
/* 182 */     setImageRoom(image);
/* 183 */     this.amountMemberLabel.setText(Integer.toString(amount));
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAllMessage() {
/* 188 */     this.chatBox.getChildren().clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void upMessage(AnchorPane message) {
/* 193 */     this.chatBox.getChildren().add(message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FlowPane getChatBox() {
/* 203 */     return this.chatBox;
/*     */   }
/*     */ 
/*     */   
/*     */   public ScrollPane getScrollMessage() {
/* 208 */     return this.scrollMessage;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParent(RoomController roomController) {
/* 213 */     this.roomController = roomController;
/*     */   }
/*     */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\controller\subcontroller\RoomHomeController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */