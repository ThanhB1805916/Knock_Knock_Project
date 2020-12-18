/*    */ package model.sendmodel;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.Serializable;
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
/*    */ public class FileInfo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String name;
/*    */   private byte[] data;
/*    */   
/*    */   public FileInfo() {}
/*    */   
/*    */   public FileInfo(String url) {
/* 29 */     this.name = new String((new File(url)).getName());
/* 30 */     this.data = changeToByte(url);
/*    */   }
/*    */ 
/*    */   
/*    */   public FileInfo(FileInfo fileInfo) {
/* 35 */     this.name = new String(fileInfo.name);
/* 36 */     this.data = fileInfo.data;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setName(String name) {
/* 44 */     this.name = name;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 48 */     return this.name;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean setFile(String url) {
/* 53 */     this.name = new String((new File(url)).getName());
/* 54 */     this.data = changeToByte(url);
/* 55 */     return (this.data.length > 0);
/*    */   }
/*    */   
/*    */   public boolean getFile(String directory) {
/* 59 */     if (this.data != null && this.data.length > 0) {
/* 60 */       File file = new File(directory);
/* 61 */       file.mkdirs();
/* 62 */       File fileReader = new File(String.valueOf(directory) + "/" + this.name);
/*    */       try {
/* 64 */         FileOutputStream fileOutputStream = new FileOutputStream(fileReader);
/* 65 */         fileOutputStream.write(getData());
/* 66 */         fileOutputStream.flush();
/* 67 */         fileOutputStream.close();
/* 68 */         return true;
/* 69 */       } catch (IOException e) {
/* 70 */         e.printStackTrace();
/* 71 */         return false;
/*    */       } 
/*    */     } 
/* 74 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private byte[] changeToByte(String url) {
/* 81 */     File file = new File(url);
/* 82 */     byte[] dataFile = new byte[(int)file.length()];
/*    */     try {
/* 84 */       FileInputStream fileInputStream = new FileInputStream(file);
/* 85 */       fileInputStream.read(dataFile);
/* 86 */       fileInputStream.close();
/* 87 */       return dataFile;
/* 88 */     } catch (Exception e1) {
/* 89 */       System.err.println("Failure to read file in: " + url);
/* 90 */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   private byte[] getData() {
/* 95 */     return this.data;
/*    */   }
/*    */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\model\sendmodel\FileInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */