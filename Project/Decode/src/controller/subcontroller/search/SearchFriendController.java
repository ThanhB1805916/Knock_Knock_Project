/*    */ package controller.subcontroller.search;
/*    */ 
/*    */ import controller.Controller;
/*    */ import java.io.IOException;
/*    */ import java.net.URL;
/*    */ import java.util.ArrayList;
/*    */ import java.util.ResourceBundle;
/*    */ import javafx.application.Platform;
/*    */ import javafx.fxml.FXML;
/*    */ import javafx.fxml.FXMLLoader;
/*    */ import javafx.fxml.Initializable;
/*    */ import javafx.scene.Parent;
/*    */ import javafx.scene.control.Button;
/*    */ import javafx.scene.control.TextField;
/*    */ import javafx.scene.input.MouseEvent;
/*    */ import javafx.scene.layout.FlowPane;
/*    */ import model.sendmodel.Person;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SearchFriendController
/*    */   implements Initializable
/*    */ {
/*    */   @FXML
/*    */   private TextField searchTxt;
/*    */   @FXML
/*    */   private Button searchBtn;
/*    */   @FXML
/*    */   private FlowPane personFlow;
/*    */   
/*    */   public void initialize(URL location, ResourceBundle resources) {
/* 36 */     this.searchBtn.setOnMousePressed(value ->
/*    */           Platform.runLater(()->{
/*    */           if (this.searchTxt.getText().trim().length() > 0) {
/*    */             Controller.getInstance().callSearchFriend(this.searchTxt.getText());
/*    */           }
/*    */         }));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void getMember(Person person) {
/*    */     try {
/* 51 */       FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/searchfriend/searchfriendtag.fxml"));
/* 52 */       Parent parent = loader.<Parent>load();
/* 53 */       SearchFriendTagController searchFriendTagController = loader.<SearchFriendTagController>getController();
/* 54 */       searchFriendTagController.setInfo(person);
/* 55 */       this.personFlow.getChildren().add(parent);
/* 56 */     } catch (IOException e) {
/*    */       
/* 58 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateData(ArrayList<Person> personList) {
/* 69 */     for (Person person : personList)
/* 70 */       getMember(person); 
/*    */   }
/*    */ }


/* Location:              C:\Users\tamth\OneDrive\Máy tính\Project\Project.jar!\controller\subcontroller\search\SearchFriendController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */