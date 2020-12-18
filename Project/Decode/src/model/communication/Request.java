/*    */ package model.communication;
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
/*    */ 
/*    */ public final class Request
/*    */   implements ValidModel, Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Name name;
/*    */   private Object content;
/*    */   
/*    */   public Request(Name name, Object content) {
/* 24 */     this.name = name;
/* 25 */     this.content = content;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isValid() {
/* 30 */     return (this.name != null && this.content != null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setName(Name name) {
/* 40 */     this.name = name;
/*    */   }
/*    */   
/*    */   public Name getName() {
/* 44 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setContent(Object content) {
/* 48 */     this.content = content;
/*    */   }
/*    */   
/*    */   public Object getContent() {
/* 52 */     return this.content;
/*    */   }
/*    */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\model\communication\Request.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */