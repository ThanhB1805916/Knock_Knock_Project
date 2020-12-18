/*    */ package main;
/*    */ 
/*    */ import javafx.application.Application;
/*    */ import javafx.fxml.FXMLLoader;
/*    */ import javafx.scene.Parent;
/*    */ import javafx.scene.Scene;
/*    */ import javafx.stage.Stage;
/*    */ 
/*    */ public class Main
/*    */   extends Application {
/*    */   public void start(Stage stage) {
/*    */     try {
/* 13 */       FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login/login.fxml"));
/* 14 */       Parent parent = loader.<Parent>load();
/* 15 */       Scene scene = new Scene(parent);
/* 16 */       stage.setScene(scene);
/* 17 */       stage.setTitle("Knock Knock");
/* 18 */       stage.setResizable(false);
/* 19 */       stage.show();
/* 20 */     } catch (Exception e) {
/* 21 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {
/* 26 */     launch(args);
/*    */   }
/*    */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\main\Main.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */