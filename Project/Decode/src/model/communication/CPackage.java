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
/*    */ 
/*    */ 
/*    */ public final class CPackage
/*    */   implements ValidModel, Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Type type;
/*    */   private Request request;
/*    */   
/*    */   public CPackage(Type type, Request request) {
/* 26 */     this.type = type;
/* 27 */     this.request = request;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isValid() {
/* 32 */     return (this.type != null && this.request.isValid());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Type getType() {
/* 42 */     return this.type;
/*    */   }
/*    */   
/*    */   public void setType(Type type) {
/* 46 */     this.type = type;
/*    */   }
/*    */   
/*    */   public Request getRequest() {
/* 50 */     return this.request;
/*    */   }
/*    */   
/*    */   public void setRequest(Request request) {
/* 54 */     this.request = request;
/*    */   }
/*    */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\model\communication\CPackage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */