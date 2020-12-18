/*    */ package model.sendmodel;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import model.ValidModel;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConfirmFriendModel
/*    */   implements ValidModel, Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Person sender;
/*    */   private Person friend;
/*    */   private boolean isFriend = false;
/*    */   
/*    */   public ConfirmFriendModel(Person sender, Person friend) {
/* 17 */     this.sender = sender;
/* 18 */     this.friend = friend;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isValid() {
/* 23 */     return this.sender.isValid();
/*    */   }
/*    */   
/*    */   public Person getSender() {
/* 27 */     return this.sender;
/*    */   }
/*    */   public void setSender(Person sender) {
/* 30 */     this.sender = sender;
/*    */   }
/*    */   
/*    */   public Person getFriend() {
/* 34 */     return this.friend;
/*    */   }
/*    */   
/*    */   public void setFriend(Person friend) {
/* 38 */     this.friend = friend;
/*    */   }
/*    */   
/*    */   public boolean getIsFriend() {
/* 42 */     return this.isFriend;
/*    */   }
/*    */   public void setIsFriend(boolean isFriend) {
/* 45 */     this.isFriend = isFriend;
/*    */   }
/*    */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\model\sendmodel\ConfirmFriendModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */