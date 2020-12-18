/*    */ package controller.subcontroller;
/*    */ 
/*    */ import controller.Controller;
/*    */ import java.net.URL;
/*    */ import java.util.ResourceBundle;
/*    */ import javafx.fxml.FXML;
/*    */ import javafx.fxml.Initializable;
/*    */ import javafx.scene.control.Alert;
/*    */ import javafx.scene.control.Button;
/*    */ import javafx.scene.control.PasswordField;
/*    */ import javafx.scene.input.MouseEvent;
/*    */ import javafx.stage.Stage;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChangePasswordController
/*    */   implements Initializable
/*    */ {
/*    */   @FXML
/*    */   private Button cancel;
/*    */   @FXML
/*    */   private PasswordField oldPassword;
/*    */   @FXML
/*    */   private PasswordField newPassword;
/*    */   @FXML
/*    */   private PasswordField confirmPassword;
/*    */   @FXML
/*    */   private Button update;
/*    */   
/*    */   public void initialize(URL location, ResourceBundle resources) {
/* 32 */     setButton();
/*    */   }
/*    */   
/*    */   private void setButton() {
/* 36 */     this.update.setOnMouseClicked(value -> {
/*    */           if (checkValid()) {
/*    */             Controller.getInstance().changePassword(this.confirmPassword.getText());
/*    */             
/*    */             this.oldPassword.setText("");
/*    */             this.newPassword.setText("");
/*    */             this.confirmPassword.setText("");
/*    */           } 
/*    */         });
/* 45 */     this.cancel.setOnMouseClicked(value -> ((Stage)this.cancel.getScene().getWindow()).close());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean checkValid() {
/* 52 */     if (this.oldPassword.getText().trim().length() > 0 && this.newPassword.getText().trim().length() > 0 && this.confirmPassword.getText().trim().length() > 0)
/* 53 */     { if (this.oldPassword.getText().equalsIgnoreCase(Controller.getInstance().getUser().getPassword()))
/* 54 */       { if (this.confirmPassword.getText().equalsIgnoreCase(this.newPassword.getText()))
/* 55 */         { if (Controller.getInstance().alertConfirmation("Bạn có chắc muốn đổi mật khẩu", "Xác nhận", null))
/* 56 */             return true;  }
/*    */         else
/* 58 */         { Controller.getInstance().alertNotify(Alert.AlertType.ERROR, "Mật khẩu không khớp", "Lỗi", null); }  }
/* 59 */       else { Controller.getInstance().alertNotify(Alert.AlertType.ERROR, "Mật khẩu cũ không đúng", "Lỗi", null); }
/*    */        }
/* 61 */     else { Controller.getInstance().alertNotify(Alert.AlertType.ERROR, "Vui lòng nhập đủ thông tin!", "Cảnh báo!", null); }
/* 62 */      return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\controller\subcontroller\ChangePasswordController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */