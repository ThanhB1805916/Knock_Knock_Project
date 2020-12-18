/*     */ package model.sendmodel;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.time.LocalDate;
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
/*     */ 
/*     */ public class Person
/*     */   implements ValidModel, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private int id;
/*     */   private String username;
/*     */   private String password;
/*     */   private String name;
/*     */   private boolean male;
/*     */   private String phonenumber;
/*     */   private LocalDate dateofbirth;
/*     */   private FileInfo avatar;
/*     */   
/*     */   public Person(int id, String username, String password, String name, boolean male, String phonenumber, LocalDate dateofbirth, FileInfo avatar) {
/*  34 */     this.id = id;
/*  35 */     this.username = username;
/*  36 */     this.password = password;
/*  37 */     this.name = name;
/*  38 */     this.male = male;
/*  39 */     this.phonenumber = phonenumber;
/*  40 */     this.dateofbirth = dateofbirth;
/*  41 */     this.avatar = avatar;
/*     */   }
/*     */   
/*     */   public Person(Person model) {
/*  45 */     this.id = model.id;
/*  46 */     this.username = model.username;
/*  47 */     this.password = model.password;
/*  48 */     this.name = model.name;
/*  49 */     this.male = model.male;
/*  50 */     this.phonenumber = model.phonenumber;
/*  51 */     this.dateofbirth = model.dateofbirth;
/*  52 */     this.avatar = model.avatar;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValid() {
/*  57 */     return (!this.username.isEmpty() && !this.password.isEmpty() && !this.name.isEmpty() && 
/*  58 */       !this.phonenumber.isEmpty());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getId() {
/*  68 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(int id) {
/*  72 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getUsername() {
/*  76 */     return this.username;
/*     */   }
/*     */   
/*     */   public void setUsername(String username) {
/*  80 */     this.username = username;
/*     */   }
/*     */   
/*     */   public String getPassword() {
/*  84 */     return this.password;
/*     */   }
/*     */   
/*     */   public void setPassword(String password) {
/*  88 */     this.password = password;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  92 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  96 */     this.name = name;
/*     */   }
/*     */   
/*     */   public boolean getMale() {
/* 100 */     return this.male;
/*     */   }
/*     */   
/*     */   public void setMale(boolean male) {
/* 104 */     this.male = male;
/*     */   }
/*     */   
/*     */   public String getPhonenumber() {
/* 108 */     return this.phonenumber;
/*     */   }
/*     */   
/*     */   public void setPhonenumber(String phonenumber) {
/* 112 */     this.phonenumber = phonenumber;
/*     */   }
/*     */   
/*     */   public LocalDate getDateofbirth() {
/* 116 */     return this.dateofbirth;
/*     */   }
/*     */   
/*     */   public void setDateofbirth(LocalDate dateofbirth) {
/* 120 */     this.dateofbirth = dateofbirth;
/*     */   }
/*     */   
/*     */   public FileInfo getAvatar() {
/* 124 */     return this.avatar;
/*     */   }
/*     */   
/*     */   public void setAvatar(FileInfo avatar) {
/* 128 */     this.avatar = avatar;
/*     */   }
/*     */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\model\sendmodel\Person.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */