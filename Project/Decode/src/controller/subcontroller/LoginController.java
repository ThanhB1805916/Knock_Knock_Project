/*     */ package controller.subcontroller;
/*     */ 
/*     */ import controller.Controller;
/*     */ import java.net.URL;
/*     */ import java.util.ResourceBundle;
/*     */ import javafx.animation.TranslateTransition;
/*     */ import javafx.fxml.FXML;
/*     */ import javafx.fxml.Initializable;
/*     */ import javafx.scene.control.Alert;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.control.Label;
/*     */ import javafx.scene.control.PasswordField;
/*     */ import javafx.scene.control.TextField;
/*     */ import javafx.scene.input.MouseEvent;
/*     */ import javafx.scene.layout.AnchorPane;
/*     */ import javafx.stage.Stage;
/*     */ import javafx.util.Duration;
/*     */ import model.sendmodel.Person;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LoginController
/*     */   implements Initializable
/*     */ {
/*     */   @FXML
/*     */   private AnchorPane firstPane;
/*     */   @FXML
/*     */   private AnchorPane loginPane;
/*     */   @FXML
/*     */   private AnchorPane signinPane;
/*     */   @FXML
/*     */   private Button loginButton;
/*     */   @FXML
/*     */   private Button returnButton;
/*     */   @FXML
/*     */   private Button signinButton;
/*     */   @FXML
/*     */   private Button startButton;
/*     */   
/*     */   public void initialize(URL location, ResourceBundle resources) {
/*  46 */     Controller.getInstance().setLoginController(this);
/*     */   } @FXML
/*     */   private Label banner; @FXML
/*     */   private PasswordField passwordLogin; @FXML
/*     */   private PasswordField passwordSignin; @FXML
/*     */   private PasswordField confirmPasswordSiginin; @FXML
/*     */   private TextField nameSignin; @FXML
/*     */   private TextField phoneSignin; @FXML
/*     */   private TextField usernameLogin; @FXML
/*     */   private TextField usernameSignin; public void startButton_Pressed(MouseEvent event) {
/*  56 */     TranslateTransition transition = new TranslateTransition(Duration.ONE);
/*  57 */     transition.setAutoReverse(false);
/*  58 */     transition.setNode(this.firstPane);
/*  59 */     transition.setFromX(this.firstPane.getLayoutX());
/*  60 */     transition.setDuration(Duration.millis(3000.0D));
/*  61 */     transition.setToX(this.firstPane.getLayoutX() + 800.0D);
/*  62 */     transition.play();
/*     */   }
/*     */ 
/*     */   
/*     */   public void signinButton_Pressed(MouseEvent event) {
/*  67 */     if (checkIsValid(this.usernameSignin.getText()) && checkIsValid(this.passwordSignin.getText()) && this.passwordSignin.getText().equals(this.confirmPasswordSiginin.getText()) && checkIsValid(this.nameSignin.getText()) && checkIsValid(this.phoneSignin.getText())) {
/*     */       
/*  69 */       if (this.passwordSignin.getText().equals(this.confirmPasswordSiginin.getText())) {
/*  70 */         Person person = new Person(0, this.usernameSignin.getText(), 
/*  71 */             this.passwordSignin.getText(), this.nameSignin.getText(), true, 
/*  72 */             this.phoneSignin.getText(), null, null);
/*  73 */         Controller.getInstance().callUserVerify(person);
/*  74 */         System.out.println("Sent");
/*     */       } 
/*     */     } else {
/*  77 */       Controller.getInstance().alertNotify(Alert.AlertType.ERROR, "Vui lòng điền đầy đủ thông tin!", "Lỗi", null);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void changeToSigninButton_Pressed(MouseEvent event) {
/*  82 */     TranslateTransition transition = new TranslateTransition(Duration.ONE);
/*  83 */     transition.setAutoReverse(false);
/*  84 */     transition.setNode(this.loginPane);
/*  85 */     transition.setFromX(this.loginPane.getLayoutX());
/*  86 */     transition.setDuration(Duration.millis(3000.0D));
/*  87 */     transition.setToX(this.loginPane.getLayoutX() - 800.0D);
/*  88 */     transition.play();
/*     */   }
/*     */ 
/*     */   
/*     */   public void returnButton_Pressed(MouseEvent event) {
/*  93 */     returnToLoginPane();
/*     */   }
/*     */ 
/*     */   
/*     */   public void loginButton_Pressed(MouseEvent event) {
/*  98 */     if (checkIsValid(this.usernameLogin.getText()) && checkIsValid(this.passwordLogin.getText())) {
/*  99 */       Controller.getInstance().callUserAuthentic(this.usernameLogin.getText(), 
/* 100 */           this.passwordLogin.getText());
/*     */     } else {
/* 102 */       Controller.getInstance().alertNotify(Alert.AlertType.ERROR, "Vui lòng điền đầy đủ thông tin!", "Lỗi", null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void returnToLoginPane() {
/* 113 */     TranslateTransition transition = new TranslateTransition(Duration.ONE);
/* 114 */     transition.setAutoReverse(false);
/* 115 */     transition.setNode(this.loginPane);
/* 116 */     transition.setFromX(this.loginPane.getLayoutX() - 800.0D);
/* 117 */     transition.setDuration(Duration.millis(3000.0D));
/* 118 */     transition.setToX(this.loginPane.getLayoutX());
/* 119 */     transition.play();
/*     */   }
/*     */ 
/*     */   
/*     */   public void closeWindows() {
/* 124 */     Stage current = (Stage)this.firstPane.getScene().getWindow();
/* 125 */     current.close();
/*     */   }
/*     */   
/*     */   private boolean checkIsValid(String text) {
/* 129 */     if (text.trim().length() > 0) return true; 
/* 130 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\controller\subcontroller\LoginController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */