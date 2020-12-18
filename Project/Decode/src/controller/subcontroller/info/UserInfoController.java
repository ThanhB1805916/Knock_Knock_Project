/*     */ package controller.subcontroller.info;
/*     */ 
/*     */ import controller.Controller;
/*     */ import java.io.File;
/*     */ import java.net.URL;
/*     */ import java.util.ResourceBundle;
/*     */ import javafx.application.Platform;
/*     */ import javafx.fxml.FXML;
/*     */ import javafx.fxml.Initializable;
/*     */ import javafx.scene.control.Alert;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.control.Label;
/*     */ import javafx.scene.control.TextField;
/*     */ import javafx.scene.image.Image;
/*     */ import javafx.scene.image.ImageView;
/*     */ import javafx.scene.input.KeyEvent;
/*     */ import javafx.scene.input.MouseEvent;
/*     */ import javafx.scene.layout.AnchorPane;
/*     */ import javafx.stage.FileChooser;
/*     */ import javafx.stage.Stage;
/*     */ import model.sendmodel.FileInfo;
/*     */ import model.sendmodel.Person;
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
/*     */ public class UserInfoController
/*     */   implements Initializable
/*     */ {
/*     */   @FXML
/*     */   private ImageView userImage;
/*     */   @FXML
/*     */   private TextField month;
/*     */   @FXML
/*     */   private Button userUpdate;
/*     */   @FXML
/*     */   private Button changePassword;
/*     */   @FXML
/*     */   private TextField userBannerName;
/*     */   @FXML
/*     */   private TextField year;
/*     */   @FXML
/*     */   private TextField userGender;
/*     */   @FXML
/*     */   private Label userName;
/*     */   @FXML
/*     */   private Button userChange;
/*     */   @FXML
/*     */   private Label userID;
/*     */   @FXML
/*     */   private TextField day;
/*     */   @FXML
/*     */   private TextField userPhone;
/*     */   @FXML
/*     */   private AnchorPane parentPane;
/*     */   @FXML
/*     */   private Button userChangeAvatar;
/*     */   private boolean status = false;
/*     */   private String currentName;
/*     */   private String currentDay;
/*     */   private String currentMonth;
/*     */   private String currentYear;
/*     */   private String currentPhone;
/*     */   private boolean currentGender;
/*     */   private FileInfo file;
/*     */   private FileInfo currentFile;
/*     */   
/*     */   public void initialize(URL location, ResourceBundle resources) {
/*  88 */     setButton();
/*  89 */     if (Controller.getInstance().getUserId() != null)
/*  90 */     { getId(Controller.getInstance().getUserId().intValue()); }
/*  91 */     else { Controller.getInstance().alertNotify(Alert.AlertType.ERROR, "Tài khoản chưa đăng nhập!", "Guess", null); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setButton() {
/* 102 */     this.userChange.setOnMouseClicked(value -> {
/*     */           changeStatus();
/*     */           if (this.status) {
/*     */             this.userChange.setText("Hủy");
/*     */           } else {
/*     */             this.userChange.setText("Sửa");
/*     */           } 
/*     */         });
/* 110 */     this.userBannerName.setOnKeyPressed(value -> {
/*     */           String old = this.userBannerName.getText();
/*     */ 
/*     */           
/*     */           Platform.runLater(()->{
						if(value.getText().matches("[0-9]"))
								userBannerName.setText(old);
							});
/*     */           
/*     */           updateAvalid();
/*     */         });
/*     */     
/* 119 */     this.userPhone.setOnKeyPressed(value -> {
/*     */           if (this.status) {
/*     */             String old = this.userPhone.getText();
/*     */ 
/*     */             
/*     */             Platform.runLater(()->{
	if(value.getText().matches("[^0-9]"))
		userPhone.setText(old);
});
/*     */           } 
/*     */         });
/*     */ 
/*     */     
/* 129 */     this.day.setOnKeyPressed(value -> {
/*     */           if (this.status) {
/*     */             String old = this.day.getText();
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             Platform.runLater(()->{
	if(value.getText().matches("[^0-9]"))
		day.setText(old);
	if(day.getText().equals("") || month.getText().equals("") || year.getText().equals(""))
		userUpdate.setDisable(true);
	else updateAvalid();
});
/*     */           } 
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 142 */     this.month.setOnKeyPressed(value -> {
/*     */           if (this.status) {
/*     */             String old = this.day.getText();
/*     */ 
/*     */ 
/*     */             
/*     */             Platform.runLater(()->{
	if(value.getText().matches("[^0-9]"))
		month.setText(old);
	if(day.getText().equals("") || month.getText().equals("") || year.getText().equals(""))
		userUpdate.setDisable(true);
	else updateAvalid();
});
/*     */           } 
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 154 */     this.year.setOnKeyPressed(value -> {
/*     */           if (this.status) {
/*     */             String old = this.year.getText();
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             Platform.runLater(()->{
	if(value.getText().matches("[^0-9]"))
		year.setText(old);
	if(day.getText().equals("") || month.getText().equals("") || year.getText().equals(""))
		userUpdate.setDisable(true);
	else updateAvalid();
});
/*     */           } 
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 167 */     this.userBannerName.setOnKeyPressed(value -> {
/*     */           if (this.status) {
/*     */             updateAvalid();
/*     */           }
/*     */         });
/*     */     
/* 173 */     this.userPhone.setOnKeyPressed(value -> {
/*     */           if (this.status) {
/*     */             String old = this.userPhone.getText();
/*     */ 
/*     */ 
/*     */             
/*     */             Platform.runLater(()->{
	if(value.getText().matches("[^0-9]") || userPhone.getText().trim().length()>10)
		userPhone.setText(old);
	updateAvalid();
});
/*     */           } 
/*     */         });
/*     */ 
/*     */     
/* 184 */     this.userGender.setOnKeyReleased(value -> {
/*     */           if (this.status) {
/*     */             updateAvalid();
/*     */           }
/*     */         });
/* 189 */     this.userUpdate.setOnMousePressed(value -> {
/*     */           this.currentDay = this.day.getText();
/*     */           
/*     */           this.currentMonth = this.month.getText();
/*     */           
/*     */           this.currentYear = this.year.getText();
/*     */           
/*     */           this.currentPhone = this.userPhone.getText();
/*     */           this.currentName = this.userBannerName.getText();
/*     */           if (this.userGender.getText().equalsIgnoreCase("nam")) {
/*     */             this.currentGender = true;
/*     */           } else {
/*     */             this.currentGender = false;
/*     */           } 
/*     */           changeStatus();
/*     */           Controller.getInstance().updateUserInfo(this.currentDay, this.currentMonth, this.currentYear, this.currentGender, this.currentPhone, this.currentName, this.currentFile);
/*     */           this.userChange.setText("Sửa");
/*     */         });
/* 207 */     this.userChangeAvatar.setOnMouseClicked(value -> {
/*     */           Stage stage = new Stage();
/*     */           
/*     */           FileChooser fileChooser = new FileChooser();
/*     */           
/*     */           fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter[] { new FileChooser.ExtensionFilter("JPG", new String[] { "*.jpg" }), new FileChooser.ExtensionFilter("PNG", new String[] { "*.png" }) });
/*     */           
/*     */           fileChooser.setTitle("Open Resource File");
/*     */           File tmp = fileChooser.showOpenDialog(stage);
/*     */           if (tmp != null) {
/*     */             this.currentFile = new FileInfo(tmp.getPath());
/*     */             if (this.currentFile.getFile("tmp/")) {
/*     */               this.userImage.setImage(new Image(tmp.toURI().toString()));
/*     */             }
/*     */             this.userUpdate.setDisable(false);
/*     */           } 
/*     */         });
/* 224 */     this.changePassword.setOnMouseClicked(value -> Controller.getInstance().showChangePassword());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void changeStatus() {
/* 231 */     this.status = !this.status;
/* 232 */     this.day.setEditable(this.status);
/* 233 */     this.month.setEditable(this.status);
/* 234 */     this.year.setEditable(this.status);
/* 235 */     this.userBannerName.setEditable(this.status);
/* 236 */     this.userGender.setEditable(this.status);
/* 237 */     this.userPhone.setEditable(this.status);
/* 238 */     this.userChangeAvatar.setDisable(!this.status);
/* 239 */     this.userUpdate.setDisable(true);
/* 240 */     update();
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateAvalid() {
/* 245 */     calucateDate();
/* 246 */     boolean current = false;
/* 247 */     if (this.userGender.getText().equals("nam"))
/* 248 */       current = true; 
/* 249 */     if (this.userBannerName.getText().equalsIgnoreCase(this.currentName) && this.userPhone.getText().equalsIgnoreCase(this.currentPhone) && 
/* 250 */       this.day.getText().equalsIgnoreCase(this.currentDay) && this.month.getText().equals(this.currentMonth) && 
/* 251 */       this.year.getText().equals(this.currentYear) && this.currentGender == current && 
/* 252 */       this.file.equals(this.currentFile)) {
/* 253 */       this.userUpdate.setDisable(true);
/* 254 */     } else if (this.userName.getText().trim().length() > 0 && this.userPhone.getText().trim().length() > 0) {
/* 255 */       this.userUpdate.setDisable(false);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void calucateDate() {
/* 260 */     if (Integer.parseInt(this.month.getText()) > 12 || Integer.parseInt(this.month.getText()) <= 0)
/* 261 */       this.month.setText("1"); 
/* 262 */     int[] max = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
/* 263 */     if (Integer.parseInt(this.year.getText()) % 400 == 0 || (Integer.parseInt(this.year.getText()) % 4 == 0 && Integer.parseInt(this.year.getText()) % 100 != 0))
/* 264 */       max[2] = 29; 
/* 265 */     if (Integer.parseInt(this.day.getText()) > max[Integer.parseInt(this.month.getText())]) {
/* 266 */       this.day.setText("1");
/* 267 */       this.month.setText(Integer.toString(Integer.parseInt(this.month.getText()) + 1));
/* 268 */       if (Integer.parseInt(this.month.getText()) > 12) {
/* 269 */         this.month.setText("1");
/* 270 */         this.year.setText(Integer.toString(Integer.parseInt(this.year.getText()) + 1));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getId(int id) {
/* 282 */     Person person = Controller.getInstance().getPersonById(id);
/* 283 */     this.currentName = person.getName();
/* 284 */     this.currentGender = person.getMale();
/* 285 */     this.currentDay = Integer.toString(person.getDateofbirth().getDayOfMonth());
/* 286 */     this.currentMonth = Integer.toString(person.getDateofbirth().getMonthValue());
/* 287 */     this.currentYear = Integer.toString(person.getDateofbirth().getYear());
/* 288 */     this.currentPhone = person.getPhonenumber();
/*     */     
/* 290 */     this.file = this.currentFile = person.getAvatar();
/* 291 */     this.userImage.setImage(new Image((new File("tmp/" + this.file.getName())).toURI().toString()));
/* 292 */     this.userID.setText(Integer.toString(person.getId()));
/* 293 */     this.userName.setText(person.getUsername());
/* 294 */     update();
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/* 299 */     this.userImage.setImage(new Image((new File("tmp/" + this.currentFile.getName())).toURI().toString()));
/* 300 */     this.userBannerName.setText(this.currentName);
/* 301 */     this.day.setText(this.currentDay);
/* 302 */     this.month.setText(this.currentMonth);
/* 303 */     this.year.setText(this.currentYear);
/* 304 */     if (this.currentGender)
/* 305 */     { this.userGender.setText("Nam"); }
/* 306 */     else { this.userGender.setText("Nữ"); }
/* 307 */      this.userPhone.setText(this.currentPhone);
/*     */   }
/*     */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\controller\subcontroller\info\UserInfoController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */