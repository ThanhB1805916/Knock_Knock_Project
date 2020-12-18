/*    */ package model.sendmodel;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.time.LocalDateTime;
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
/*    */ 
/*    */ public class Message
/*    */   implements ValidModel, Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Person sender;
/*    */   private int roomID;
/*    */   private FileInfo content;
/*    */   private boolean isFile;
/*    */   private LocalDateTime sendDate;
/*    */   
/*    */   public Message(Person sender, int room, FileInfo content, boolean isFile, LocalDateTime sendDate) {
/* 28 */     this.sender = sender;
/* 29 */     this.roomID = room;
/* 30 */     this.content = content;
/* 31 */     this.isFile = isFile;
/* 32 */     this.sendDate = sendDate;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isValid() {
/* 37 */     return (this.sender.isValid() && this.roomID > 0 && this.content != null && this.sendDate != null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Person getSender() {
/* 47 */     return this.sender;
/*    */   }
/*    */   
/*    */   public void setSender(Person sender) {
/* 51 */     this.sender = sender;
/*    */   }
/*    */   
/*    */   public int getRoomID() {
/* 55 */     return this.roomID;
/*    */   }
/*    */   
/*    */   public void setRoomID(int room) {
/* 59 */     this.roomID = room;
/*    */   }
/*    */   
/*    */   public FileInfo getContent() {
/* 63 */     return this.content;
/*    */   }
/*    */   
/*    */   public void setContent(FileInfo content) {
/* 67 */     this.content = content;
/*    */   }
/*    */   
/*    */   public boolean getIsFile() {
/* 71 */     return this.isFile;
/*    */   }
/*    */   
/*    */   public void setIsFile(boolean isFile) {
/* 75 */     this.isFile = isFile;
/*    */   }
/*    */   
/*    */   public LocalDateTime getSendDate() {
/* 79 */     return this.sendDate;
/*    */   }
/*    */   
/*    */   public void setSendDate(LocalDateTime sendDate) {
/* 83 */     this.sendDate = sendDate;
/*    */   }
/*    */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\model\sendmodel\Message.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */