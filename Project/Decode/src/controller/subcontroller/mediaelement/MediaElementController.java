/*    */ package controller.subcontroller.mediaelement;
/*    */ 
/*    */ import java.awt.Desktop;
/*    */ import java.io.File;
/*    */ import java.net.URL;
/*    */ import java.util.ArrayList;
/*    */ import java.util.ResourceBundle;
/*    */ import javafx.fxml.FXML;
/*    */ import javafx.fxml.Initializable;
/*    */ import javafx.scene.control.Button;
/*    */ import javafx.scene.control.Label;
/*    */ import javafx.scene.input.MouseEvent;
/*    */ import javafx.stage.DirectoryChooser;
/*    */ import javafx.stage.Stage;
/*    */ import model.sendmodel.FileInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MediaElementController
/*    */   implements Initializable
/*    */ {
/*    */   @FXML
/*    */   private Button downloadFile;
/*    */   @FXML
/*    */   private Button seeFile;
/*    */   @FXML
/*    */   private Label nameFile;
/*    */   @FXML
/*    */   private Label typeMedia;
/*    */   private FileInfo file;
/* 33 */   private ArrayList<String> listExtension = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void initialize(URL location, ResourceBundle resources) {
/* 37 */     String[] list = { ".png", ".gif", ".jpg", ".mp3", ".wav", ".aac", ".ogg", ".doc", ".docx", ".exls", ".pptx", ".pdf", ".txt", ".html", ".mp4", ".mov", ".wmv", ".flv", ".avi", ".mkv" }; byte b; int i; String[] arrayOfString1;
/* 38 */     for (i = (arrayOfString1 = list).length, b = 0; b < i; ) { String string = arrayOfString1[b];
/* 39 */       this.listExtension.add(string); b++; }
/* 40 */      setButtonListener();
/*    */   }
/*    */   
/*    */   public void setFile(FileInfo file) {
/* 44 */     this.file = file;
/* 45 */     this.typeMedia.setText("[" + getExtension(file.getName()) + "]");
/* 46 */     this.nameFile.setText(getName(file.getName()));
/* 47 */     if (!isFormalFile())
/* 48 */       this.seeFile.setVisible(false); 
/*    */   }
/*    */   
/*    */   private String getExtension(String text) {
/* 52 */     return text.substring(text.lastIndexOf(".")).toUpperCase();
/*    */   }
/*    */   
/*    */   private String getName(String text) {
/* 56 */     return text.substring(0, text.lastIndexOf("."));
/*    */   }
/*    */   
/*    */   private void setButtonListener() {
/* 60 */     this.seeFile.setOnMouseClicked(value -> {
/*    */           this.file.getFile("tmp/");
/*    */           try {
/*    */             Desktop.getDesktop().open(new File("tmp/" + this.file.getName()));
/* 64 */           } catch (Exception e) {
/*    */             e.printStackTrace();
/*    */           } 
/*    */         });
/*    */     
/* 69 */     this.downloadFile.setOnMouseClicked(value -> {
/*    */           Stage stage = new Stage();
/*    */           DirectoryChooser directoryChooser = new DirectoryChooser();
/*    */           directoryChooser.setTitle("Choose location");
/*    */           File tmp = directoryChooser.showDialog(stage);
/*    */           if (tmp != null)
/*    */             this.file.getFile(tmp.getPath()); 
/*    */         });
/*    */   }
/*    */   
/*    */   private boolean isFormalFile() {
/* 80 */     return this.listExtension.contains(getExtension(this.file.getName()).toLowerCase());
/*    */   }
/*    */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\controller\subcontroller\mediaelement\MediaElementController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */