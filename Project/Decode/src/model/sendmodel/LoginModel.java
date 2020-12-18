/*    */ package model.sendmodel;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import model.ValidModel;
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
/*    */ public class LoginModel
/*    */   implements ValidModel, Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String username;
/*    */   private String password;
/*    */   
/*    */   public LoginModel(String username, String password) {
/* 23 */     this.username = username;
/* 24 */     this.password = password;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isValid() {
/* 29 */     return (!this.username.isEmpty() && !this.password.isEmpty());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getUsername() {
/* 39 */     return this.username;
/*    */   }
/*    */   
/*    */   public void setUsername(String username) {
/* 43 */     this.username = username;
/*    */   }
/*    */   
/*    */   public String getPassword() {
/* 47 */     return this.password;
/*    */   }
/*    */   
/*    */   public void setPassword(String password) {
/* 51 */     this.password = password;
/*    */   }
/*    */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\model\sendmodel\LoginModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */