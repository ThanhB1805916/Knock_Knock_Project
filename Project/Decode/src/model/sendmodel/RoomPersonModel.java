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
/*    */ public class RoomPersonModel
/*    */   implements ValidModel, Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Room room;
/*    */   private Person person;
/*    */   
/*    */   public RoomPersonModel(Room room, Person person) {
/* 19 */     this.room = room;
/* 20 */     this.person = person;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isValid() {
/* 25 */     return (this.room != null && this.room.isValid() && this.person != null && this.person.isValid());
/*    */   }
/*    */   
/*    */   public Room getRoom() {
/* 29 */     return this.room;
/*    */   }
/*    */   
/*    */   public void setRoom(Room room) {
/* 33 */     this.room = room;
/*    */   }
/*    */   
/*    */   public Person getPerson() {
/* 37 */     return this.person;
/*    */   }
/*    */   
/*    */   public void setPerson(Person person) {
/* 41 */     this.person = person;
/*    */   }
/*    */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\model\sendmodel\RoomPersonModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */