/*     */ package controller.subcontroller.room;
/*     */ 
/*     */ import controller.Controller;
/*     */ import controller.subcontroller.MessageTagController;
/*     */ import controller.subcontroller.RoomHomeController;
/*     */ import controller.subcontroller.RoomTagController;
/*     */ import controller.subcontroller.mediaelement.MediaElementController;
/*     */ import java.io.File;
/*     */ import javafx.application.Platform;
/*     */ import javafx.beans.Observable;
/*     */ import javafx.fxml.FXMLLoader;
/*     */ import javafx.geometry.Pos;
/*     */ import javafx.scene.Parent;
/*     */ import javafx.scene.control.Label;
/*     */ import javafx.scene.image.Image;
/*     */ import javafx.scene.layout.AnchorPane;
/*     */ import javafx.scene.layout.HBox;
/*     */ import javafx.scene.paint.ImagePattern;
/*     */ import javafx.scene.shape.Circle;
/*     */ import javafx.scene.text.Font;
/*     */ import model.sendmodel.Message;
/*     */ import model.sendmodel.Person;
/*     */ import model.sendmodel.Room;
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
/*     */ public class RoomController
/*     */ {
/*     */   private Parent roomTag;
/*     */   private Parent roomHome;
/*     */   private Parent messageTag;
/*     */   private RoomTagController roomTagController;
/*     */   private RoomHomeController roomHomeController;
/*     */   private MessageTagController messageTagController;
/*     */   private Room room;
/*     */   
/*     */   public RoomController(Room room) {
/*  47 */     this.room = room;
/*  48 */     init();
/*  49 */     setParent();
/*  50 */     setInfo();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Parent getRoomTag() {
/*  60 */     return this.roomTag;
/*     */   }
/*     */ 
/*     */   
/*     */   public Parent getRoomHome() {
/*  65 */     return this.roomHome;
/*     */   }
/*     */ 
/*     */   
/*     */   public Parent getMessageTag() {
/*  70 */     return this.messageTag;
/*     */   }
/*     */ 
/*     */   
/*     */   public RoomTagController getRoomTagController() {
/*  75 */     return this.roomTagController;
/*     */   }
/*     */ 
/*     */   
/*     */   public RoomHomeController getRoomHomeController() {
/*  80 */     return this.roomHomeController;
/*     */   }
/*     */ 
/*     */   
/*     */   public MessageTagController getMessageTagController() {
/*  85 */     return this.messageTagController;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRoom(Room room) {
/*  90 */     this.room = room;
/*     */   }
/*     */ 
/*     */   
/*     */   public Room getRoom() {
/*  95 */     return this.room;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/* 100 */     return this.room.getId();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 110 */     setInfo();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setMessage(Message message) {
/* 115 */     int userId = message.getSender().getId();
/* 116 */     if (!memberInRoom(userId)) {
/* 117 */       return false;
/*     */     }
/* 119 */     String modifyMess = message.getContent().getName();
/*     */     
/* 121 */     if (userId != Controller.getInstance().getUserId().intValue()) {
/* 122 */       modifyMess = String.valueOf(message.getSender().getName()) + ":\n" + modifyMess;
/*     */     }
/* 124 */     Label label = new Label(modifyMess);
/* 125 */     label.setWrapText(true);
/* 126 */     label.setFont(Font.font(24.0D));
/* 127 */     label.setStyle("-fx-background-color:#2ecc71; -fx-padding:10; -fx-background-radius:20");
/* 128 */     label.setMaxWidth(350.0D);
/*     */     
/* 130 */     HBox hbox = new HBox();
/* 131 */     hbox.setPrefWidth(this.roomHomeController.getScrollMessage().getViewportBounds().getWidth());
/*     */     
/* 133 */     if (userId == Controller.getInstance().getUserId().intValue()) {
/* 134 */       hbox.setAlignment(Pos.BASELINE_RIGHT);
/* 135 */       hbox.setStyle("-fx-padding: 0 15 0 5");
/*     */     } else {
/*     */       
/* 138 */       hbox.setAlignment(Pos.BASELINE_LEFT);
/* 139 */       hbox.setStyle("-fx-padding: 0 5 0 15");
/* 140 */       Circle circle = new Circle(10.0D, 10.0D, 20.0D);
/* 141 */       circle.setFill(getExternalImage(userId));
/* 142 */       hbox.getChildren().add(circle);
/*     */     } 
/* 144 */     hbox.getChildren().add(label);
/*     */     
/* 146 */     AnchorPane anchorPane = new AnchorPane();
/* 147 */     anchorPane.setPrefWidth(this.roomHomeController.getScrollMessage().getViewportBounds().getWidth() - 2.0D);
/*     */     
/* 149 */     this.roomHomeController.getChatBox().widthProperty().addListener(listener -> {
/*     */           hbox.setPrefWidth(this.roomHomeController.getScrollMessage().getViewportBounds().getWidth());
/*     */           anchorPane.setPrefWidth(this.roomHomeController.getScrollMessage().getViewportBounds().getWidth() - 2.0D);
/*     */           refreshFrame();
/*     */         });
/* 154 */     this.roomHomeController.getChatBox().heightProperty().addListener(listener -> {
/*     */           hbox.setPrefWidth(this.roomHomeController.getScrollMessage().getViewportBounds().getWidth());
/*     */           
/*     */           refreshFrame();
/*     */         });
/* 159 */     AnchorPane.setBottomAnchor(hbox, Double.valueOf(0.0D));
/* 160 */     AnchorPane.setTopAnchor(hbox, Double.valueOf(0.0D));
/* 161 */     AnchorPane.setRightAnchor(hbox, Double.valueOf(0.0D));
/* 162 */     AnchorPane.setLeftAnchor(hbox, Double.valueOf(0.0D));
/*     */     
/* 164 */     anchorPane.getChildren().add(hbox);
/*     */     
/* 166 */     Platform.runLater(() -> this.roomHomeController.upMessage(anchorPane));
/* 167 */     refreshFrame();
/* 168 */     this.messageTagController.updateCurrentMessage(String.valueOf(getMemberById(userId).getName()) + ":" + message.getContent().getName());
/* 169 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setMedia(Message message) {
/* 174 */     int userId = message.getSender().getId();
/* 175 */     if (!memberInRoom(userId))
/* 176 */       return false; 
/* 177 */     if (message.getContent().getFile("tmp/")) {
/* 178 */       Label label = new Label();
/*     */       try {
/* 180 */         FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mediaelement/mediaelement.fxml"));
/* 181 */         Parent parent = loader.<Parent>load();
/* 182 */         MediaElementController mediaElementController = loader.<MediaElementController>getController();
/* 183 */         mediaElementController.setFile(message.getContent());
/* 184 */         label.setGraphic(parent);
/* 185 */       } catch (Exception exception) {}
/* 186 */       label.setWrapText(true);
/* 187 */       label.setFont(Font.font(24.0D));
/* 188 */       label.setStyle("-fx-background-color:#2ecc71; -fx-padding:10; -fx-background-radius:20");
/* 189 */       label.setMaxWidth(350.0D);
/*     */       
/* 191 */       HBox hbox = new HBox();
/* 192 */       hbox.setPrefWidth(this.roomHomeController.getScrollMessage().getViewportBounds().getWidth());
/*     */       
/* 194 */       if (userId == Controller.getInstance().getUserId().intValue()) {
/* 195 */         hbox.setAlignment(Pos.BASELINE_RIGHT);
/* 196 */         hbox.setStyle("-fx-padding: 0 15 0 5");
/*     */       } else {
/*     */         
/* 199 */         hbox.setAlignment(Pos.BASELINE_LEFT);
/* 200 */         hbox.setStyle("-fx-padding: 0 5 0 15");
/*     */         
/* 202 */         Circle circle = new Circle(10.0D, 10.0D, 20.0D);
/* 203 */         circle.setFill(getExternalImage(userId));
/* 204 */         hbox.getChildren().add(circle);
/*     */       } 
/* 206 */       hbox.getChildren().add(label);
/*     */       
/* 208 */       AnchorPane anchorPane = new AnchorPane();
/* 209 */       anchorPane.setPrefWidth(this.roomHomeController.getScrollMessage().getViewportBounds().getWidth() - 2.0D);
/*     */       
/* 211 */       this.roomHomeController.getChatBox().widthProperty().addListener(listener -> {
/*     */             hbox.setPrefWidth(this.roomHomeController.getScrollMessage().getViewportBounds().getWidth());
/*     */             anchorPane.setPrefWidth(this.roomHomeController.getScrollMessage().getViewportBounds().getWidth() - 2.0D);
/*     */             refreshFrame();
/*     */           });
/* 216 */       this.roomHomeController.getChatBox().heightProperty().addListener(listener -> {
/*     */             hbox.setPrefWidth(this.roomHomeController.getScrollMessage().getViewportBounds().getWidth());
/*     */             
/*     */             refreshFrame();
/*     */           });
/* 221 */       AnchorPane.setBottomAnchor(hbox, Double.valueOf(0.0D));
/* 222 */       AnchorPane.setTopAnchor(hbox, Double.valueOf(0.0D));
/* 223 */       AnchorPane.setRightAnchor(hbox, Double.valueOf(0.0D));
/* 224 */       AnchorPane.setLeftAnchor(hbox, Double.valueOf(0.0D));
/*     */       
/* 226 */       anchorPane.getChildren().add(hbox);
/*     */       
/* 228 */       Platform.runLater(() -> this.roomHomeController.upMessage(anchorPane));
/* 229 */       refreshFrame();
/* 230 */       this.messageTagController.updateCurrentMessage(String.valueOf(getMemberById(userId).getName()) + ": Đã gửi 1 file!");
/* 231 */       return true;
/*     */     } 
/* 233 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void showThisRoom() {
/* 238 */     Controller.getInstance().showRoomCaller(this.room.getId());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void init() {
/*     */     try {
/* 249 */       FXMLLoader roomHomeLoader = new FXMLLoader(getClass().getResource("/view/roomhome/roomhome.fxml"));
/* 250 */       FXMLLoader roomTagLoader = new FXMLLoader(getClass().getResource("/view/tag/roomtag/roomtag.fxml"));
/* 251 */       FXMLLoader messageTagLoader = new FXMLLoader(getClass().getResource("/view/tag/messagetag/messagetag.fxml"));
/*     */       
/* 253 */       this.roomHome = roomHomeLoader.<Parent>load();
/* 254 */       this.roomTag = roomTagLoader.<Parent>load();
/* 255 */       this.messageTag = messageTagLoader.<Parent>load();
/*     */       
/* 257 */       this.roomHomeController = roomHomeLoader.<RoomHomeController>getController();
/* 258 */       this.roomTagController = roomTagLoader.<RoomTagController>getController();
/* 259 */       this.messageTagController = messageTagLoader.<MessageTagController>getController();
/* 260 */     } catch (Exception e) {
/* 261 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean memberInRoom(int userId) {
/* 267 */     if (this.room.getMembers() == null) return false; 
/* 268 */     for (Person person : this.room.getMembers()) {
/* 269 */       if (person.getId() == userId)
/* 270 */         return true; 
/* 271 */     }  return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private ImagePattern getExternalImage(int id) {
/* 276 */     return new ImagePattern(new Image(readFileFromPerson(id).toURI().toString()));
/*     */   }
/*     */ 
/*     */   
/*     */   private Image setImage() {
/* 281 */     return new Image(readFileFromRoom().toURI().toString());
/*     */   }
/*     */ 
/*     */   
/*     */   private Person getMemberById(int id) {
/* 286 */     for (Person person : this.room.getMembers()) {
/* 287 */       if (person.getId() == id)
/* 288 */         return person; 
/* 289 */     }  return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private File readFileFromRoom() {
/* 294 */     if (this.room.getAvatar().getFile("tmp/"))
/* 295 */       return new File("tmp/" + this.room.getAvatar().getName()); 
/* 296 */     return new File("tmp/user_icon.png");
/*     */   }
/*     */   
/*     */   private File readFileFromPerson(int id) {
/* 300 */     Person tmp = getMemberById(id);
/* 301 */     if (tmp != null && 
/* 302 */       tmp.getAvatar().getFile("tmp/"))
/* 303 */       return new File("tmp/" + tmp.getAvatar().getName()); 
/* 304 */     return new File("tmp/user_icon.png");
/*     */   }
/*     */ 
/*     */   
/*     */   private void refreshFrame() {
/* 309 */     Thread stream = new Thread(() -> {
/*     */           try {
/*     */             Thread.sleep(80L);
/*     */             setFrame();
/* 313 */           } catch (Exception exception) {}
/*     */         });
/* 315 */     stream.start();
/*     */   }
/*     */ 
/*     */   
/*     */   private void setFrame() {
/* 320 */     this.roomHomeController.getScrollMessage().vvalueProperty().setValue(Double.valueOf(1.0D));
/*     */   }
/*     */ 
/*     */   
/*     */   private void setInfo() {
/* 325 */     this.roomTagController.callUpdate(this.room.getName(), setImage());
/* 326 */     this.roomHomeController.callUpdate(this.room.getName(), this.room.getMembers().size(), setImage());
/* 327 */     this.messageTagController.callUpdate(this.room.getName(), setImage());
/*     */   }
/*     */ 
/*     */   
/*     */   private void setParent() {
/* 332 */     this.roomTagController.setParent(this);
/* 333 */     this.roomHomeController.setParent(this);
/* 334 */     this.messageTagController.setParent(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\controller\subcontroller\room\RoomController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */