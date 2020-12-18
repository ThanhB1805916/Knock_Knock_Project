/*     */ package connector;
/*     */ 
/*     */ import controller.Controller;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.net.Socket;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Properties;
/*     */ import java.util.Queue;
/*     */ import javafx.application.Platform;
/*     */ import model.communication.CPackage;
/*     */ import model.communication.Name;
/*     */ import model.communication.Request;
/*     */ import model.communication.Type;
/*     */ import model.sendmodel.ConfirmFriendModel;
/*     */ import model.sendmodel.Message;
/*     */ import model.sendmodel.Person;
/*     */ import model.sendmodel.Room;
/*     */ import model.sendmodel.RoomPersonModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Connector
/*     */ {
/*     */   private Socket socket;
/*     */   private ObjectInputStream input;
/*     */   private ObjectOutputStream output;
/*     */   private Properties properties;
/*     */   private Thread streamThread;
/*     */   private Thread soldier;
/*  38 */   private Queue<Object> conveyor = new LinkedList();
/*     */   private boolean status = false;
/*     */   private boolean isDoing = false;
/*     */   
/*     */   public Connector() {
/*     */     try {
/*  44 */       this.properties = new Properties();
/*  45 */       this.properties.load(new FileReader("commons\\configs.properties"));
/*  46 */     } catch (IOException e) {
/*  47 */       e.printStackTrace();
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  57 */     System.out.println(String.valueOf(getIP()) + ": " + getPort());
/*     */     try {
/*  59 */       this.socket = new Socket(getIP(), getPort());
/*  60 */       this.input = new ObjectInputStream(this.socket.getInputStream());
/*  61 */       this.output = new ObjectOutputStream(this.socket.getOutputStream());
/*  62 */     } catch (IOException e) {
/*  63 */       System.err.println("Connect fail!");
/*  64 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void start() {
/*  74 */     this.status = true;
/*  75 */     this.streamThread = new Thread(() -> inputStreamFromServer());
/*     */ 
/*     */     
/*  78 */     this.streamThread.start();
/*     */   }
/*     */   
/*     */   public void stop() {
/*  82 */     this.status = false;
/*     */   }
/*     */   
/*     */   public boolean sendCPackage(CPackage pack) {
/*     */     try {
/*  87 */       this.output.writeObject(pack);
/*  88 */       this.output.flush();
/*  89 */       return true;
/*  90 */     } catch (IOException e) {
/*  91 */       e.printStackTrace();
/*  92 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void inputStreamFromServer() {
/* 103 */     while (this.status) {
/*     */       try {
/* 105 */         Object data = this.input.readObject();
/* 106 */         this.conveyor.add(data);
/* 107 */         wakeUp();
/* 108 */       } catch (ClassNotFoundException|IOException e) {
/* 109 */         e.printStackTrace();
/* 110 */         this.status = false;
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     try {
/* 115 */       this.input.close();
/* 116 */       this.output.close();
/* 117 */     } catch (IOException e) {
/* 118 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private String getIP() {
/* 123 */     return this.properties.get("IP").toString();
/*     */   }
/*     */   
/*     */   private int getPort() {
/* 127 */     return Integer.parseInt(this.properties.get("port").toString());
/*     */   }
/*     */   
/*     */   private void wakeUp() {
/* 131 */     this.soldier = new Thread(() -> callWakeUp());
/*     */ 
/*     */     
/* 134 */     this.soldier.start();
/*     */   }
/*     */   
/*     */   private void callWakeUp() {
/* 138 */     if (!this.isDoing) {
/* 139 */       this.isDoing = !this.isDoing;
/* 140 */       takeAndDistribute();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void takeAndDistribute() {
/* 145 */     while (this.conveyor.peek() != null) {
/* 146 */       CPackage pack = (CPackage)this.conveyor.poll();
/* 147 */       switch (pack.getType()) {
/*     */         case ACCOUNT:
/* 149 */           accountAction(pack.getRequest());
/*     */         
/*     */         case AUTHENTICATION:
/* 152 */           authenticAction(pack.getRequest());
/*     */         
/*     */         case FRIEND:
/* 155 */           friendAction(pack.getRequest());
/*     */         
/*     */         case MESSAGE:
/* 158 */           messageAction(pack.getRequest());
/*     */         
/*     */         case ROOM:
/* 161 */           roomAction(pack.getRequest());
/*     */       } 
/*     */ 
/*     */ 
/*     */     
/*     */     } 
/* 167 */     this.isDoing = false;
/*     */   }
/*     */   
/*     */   private void messageAction(Request request) {
/* 171 */     switch (request.getName()) {
/*     */       case ADD:
/* 173 */         addMessageAction(request);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addMessageAction(Request request) {
/* 182 */     Message message = (Message)request.getContent();
/* 183 */     Platform.runLater(() -> Controller.getInstance().inputMessage(message));
/*     */   }
/*     */   
/*     */   private void accountAction(Request request) {
/* 187 */     switch (request.getName()) {
/*     */       case GET:
/* 189 */         getAccountAction(request);
/*     */         break;
/*     */       case UPDATE:
/* 192 */         accountUpdateAction(request);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void accountUpdateAction(Request request) {
/* 200 */     boolean result = ((Boolean)request.getContent()).booleanValue();
/* 201 */     Controller.getInstance().inputUpdateAccountResult(result);
/*     */   }
/*     */   
/*     */   private void friendAction(Request request) {
/* 205 */     switch (request.getName()) {
/*     */       case GET:
/* 207 */         getFriendAction(request);
/*     */         break;
/*     */       case ADD:
/* 210 */         addFriendAction(request);
/*     */         break;
/*     */       case REMOVE:
/* 213 */         removeFriendAction(request);
/*     */         break;
/*     */       case FIND:
/* 216 */         findFriendAction(request);
/*     */         break;
/*     */       case CONFIRM:
/* 219 */         confirmFriendAction(request);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void delineFriendAcction(Person person) {
/* 227 */     Platform.runLater(() -> Controller.getInstance().inputDelineAddFriend(person.getName()));
/*     */   }
/*     */   
/*     */   private void acceptFriendAction(Person person) {
/* 231 */     Controller.getInstance().inputAcceptFriend(person);
/*     */   }
/*     */   
/*     */   private void removeFriendAction(Request request) {
/* 235 */     int id = ((Integer)request.getContent()).intValue();
/* 236 */     Platform.runLater(() -> Controller.getInstance().setRemoveFriend(id));
/* 237 */     Platform.runLater(() -> Controller.getInstance().inputKickFriend());
/*     */   }
/*     */   
/*     */   private void confirmFriendAction(Request request) {
/* 241 */     ConfirmFriendModel confirm = (ConfirmFriendModel)request.getContent();
/* 242 */     if (confirm.getSender().getId() == Controller.getInstance().getUserId().intValue()) {
/* 243 */       if (confirm.getIsFriend())
/* 244 */       { acceptFriendAction(confirm.getFriend()); }
/* 245 */       else { delineFriendAcction(confirm.getFriend()); }
/*     */     
/* 247 */     } else if (Controller.getInstance().getPersonById(confirm.getSender().getId()) == null) {
/* 248 */       Platform.runLater(() -> Controller.getInstance().inputRequestConfirmFriend(confirm));
/*     */     } 
/*     */   }
/*     */   private void addFriendAction(Request request) {
/* 252 */     boolean result = ((Boolean)request.getContent()).booleanValue();
/* 253 */     Platform.runLater(() -> Controller.getInstance().inputRequestAddFriendResult(result));
/*     */   }
/*     */   
/*     */   private void roomAction(Request request) {
/* 257 */     switch (request.getName()) {
/*     */       case GET:
/* 259 */         getRoomAction(request);
/*     */         break;
/*     */       case ADD:
/* 262 */         addRoomAction(request);
/*     */         break;
/*     */       case UPDATE:
/* 265 */         updateRoom(request);
/*     */         break;
/*     */       case EXIT:
/* 268 */         exitRoomAction(request);
/*     */         break;
/*     */       case ADDMEMBER:
/* 271 */         addMemberRoom(request);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void addMemberRoom(Request request) {
/* 279 */     RoomPersonModel roomPersonModel = (RoomPersonModel)request.getContent();
/* 280 */     if (Controller.getInstance().getRoomById(roomPersonModel.getRoom().getId()) != null) {
/* 281 */       Controller.getInstance().inputAddMemberToARoom(roomPersonModel.getPerson(), roomPersonModel.getRoom().getId());
/*     */     } else {
/* 283 */       Platform.runLater(() -> Controller.getInstance().inputToAddToRoom(roomPersonModel.getRoom()));
/*     */     } 
/*     */   }
/*     */   private void updateRoom(Request request) {
/* 287 */     Room room = (Room)request.getContent();
/* 288 */     Platform.runLater(() -> Controller.getInstance().inputUpdateRoom(room));
/*     */   }
/*     */   private void exitRoomAction(Request request) {
/* 291 */     RoomPersonModel roomPersonModel = (RoomPersonModel)request.getContent();
/* 292 */     if (roomPersonModel.getPerson().getId() == Controller.getInstance().getUserId().intValue())
/* 293 */     { Platform.runLater(() -> Controller.getInstance().inputKickoutRoom(roomPersonModel.getRoom().getId())); }
/* 294 */     else { Platform.runLater(() -> Controller.getInstance().inputNotifyKickOut(roomPersonModel, roomPersonModel.getRoom().getName(), roomPersonModel.getPerson().getName())); }
/*     */   
/*     */   }
/*     */   private void addRoomAction(Request request) {
/* 298 */     Room room = (Room)request.getContent();
/* 299 */     if (room.getId() == -1) {
/* 300 */       Platform.runLater(() -> Controller.getInstance().inputCreateRoomFalse());
/*     */     } else {
/* 302 */       Platform.runLater(() -> Controller.getInstance().inputNewRoomRuntime(room));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void authenticAction(Request request) {
/* 307 */     switch (request.getName()) {
/*     */       case LOGIN:
/* 309 */         loginAuthenticAction(request);
/*     */         break;
/*     */       case SIGNUP:
/* 312 */         signupAuthenticAction(request);
/*     */         break;
/*     */       case LOGOUT:
/* 315 */         logoutAuthenticAction(request);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void signupAuthenticAction(Request request) {
/* 323 */     boolean result = ((Boolean)request.getContent()).booleanValue();
/* 324 */     if (result)
/* 325 */     { Controller.getInstance().inputVerifyAccountSuccessful(); }
/* 326 */     else { Controller.getInstance().inputVerifyAccountFailure(); }
/*     */   
/*     */   }
/*     */   private void logoutAuthenticAction(Request request) {
/* 330 */     boolean reusult = ((Boolean)request.getContent()).booleanValue();
/* 331 */     if (reusult)
/* 332 */       Controller.getInstance().inputLogoutSuccessfull(); 
/*     */   }
/*     */   
/*     */   private void loginAuthenticAction(Request request) {
/* 336 */     boolean reusult = ((Boolean)request.getContent()).booleanValue();
/* 337 */     if (reusult) {
/* 338 */       Controller.getInstance().sendRequestUser();
/*     */     } else {
/* 340 */       Controller.getInstance().inputAuthenticAccountFailure();
/*     */     } 
/*     */   }
/*     */   private void getAccountAction(Request request) {
/* 344 */     Person person = (Person)request.getContent();
/* 345 */     Controller.getInstance().inputUserFromDatabase(person);
/* 346 */     Controller.getInstance().sendRequestFriend();
/*     */   }
/*     */ 
/*     */   
/*     */   private void getRoomAction(Request request) {
/* 351 */     ArrayList<Room> arrayList = (ArrayList<Room>)request.getContent();
/* 352 */     for (Room room : arrayList)
/* 353 */       Controller.getInstance().inputNewRoom(room); 
/* 354 */     Controller.getInstance().inputAuthenticAccountSuccessful();
/*     */   }
/*     */ 
/*     */   
/*     */   private void getFriendAction(Request request) {
/* 359 */     ArrayList<Person> arrayList = (ArrayList<Person>)request.getContent();
/* 360 */     Controller.getInstance().inputFriendList(arrayList);
/* 361 */     Controller.getInstance().sendRequestRoom();
/*     */   }
/*     */   
/*     */   private void findFriendAction(Request request) {
/* 365 */     Person person = (Person)request.getContent();
/* 366 */     ArrayList<Person> list = new ArrayList<>();
/* 367 */     if (person != null)
/* 368 */       list.add(person); 
/* 369 */     Controller.getInstance().inputSearchPerson(list);
/*     */   }
/*     */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\connector\Connector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */