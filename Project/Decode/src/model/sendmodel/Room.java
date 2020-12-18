/*     */ package model.sendmodel;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.time.LocalDateTime;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import model.ValidModel;
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
/*     */ public class Room
/*     */   implements ValidModel, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private int id;
/*     */   private String name;
/*     */   private LocalDateTime dateCreate;
/*     */   private List<Message> messages;
/*     */   private List<Person> members;
/*     */   private FileInfo avatar;
/*     */   
/*     */   public Room(int id, String name, FileInfo avatar, List<Person> members) {
/*  33 */     this.id = id;
/*  34 */     this.name = name;
/*  35 */     this.avatar = avatar;
/*  36 */     this.members = members;
/*  37 */     this.messages = new ArrayList<>();
/*     */   }
/*     */ 
/*     */   
/*     */   public Room(int id, String name, List<Message> messages, List<Person> members) {
/*  42 */     this.id = id;
/*  43 */     this.name = name;
/*  44 */     this.messages = messages;
/*  45 */     this.members = members;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Room(int id, String name, LocalDateTime dateCreate, List<Message> messages, List<Person> members, FileInfo avatar) {
/*  52 */     this.id = id;
/*  53 */     this.name = name;
/*  54 */     this.dateCreate = dateCreate;
/*  55 */     this.messages = messages;
/*  56 */     this.members = members;
/*  57 */     this.avatar = avatar;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValid() {
/*  62 */     return !this.name.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getId() {
/*  72 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(int id) {
/*  76 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  80 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  84 */     this.name = name;
/*     */   }
/*     */   
/*     */   public LocalDateTime getDateCreate() {
/*  88 */     return this.dateCreate;
/*     */   }
/*     */   
/*     */   public void setDateCreate(LocalDateTime dateCreate) {
/*  92 */     this.dateCreate = dateCreate;
/*     */   }
/*     */   
/*     */   public List<Message> getMessages() {
/*  96 */     return this.messages;
/*     */   }
/*     */   
/*     */   public void setMessages(List<Message> messages) {
/* 100 */     this.messages = messages;
/*     */   }
/*     */   
/*     */   public List<Person> getMembers() {
/* 104 */     return this.members;
/*     */   }
/*     */   
/*     */   public void setMembers(List<Person> members) {
/* 108 */     this.members = members;
/*     */   }
/*     */   
/*     */   public FileInfo getAvatar() {
/* 112 */     return this.avatar;
/*     */   }
/*     */   
/*     */   public void setAvatar(FileInfo avatar) {
/* 116 */     this.avatar = avatar;
/*     */   }
/*     */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\model\sendmodel\Room.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */