package controller.subcontroller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.Controller;
import controller.subcontroller.room.RoomController;
import controller.subcontroller.search.SearchFriendController;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.sendmodel.Person;

public class BackgroundController implements Initializable{
	@FXML
		private AnchorPane parentbackgroundPane;
	
	@FXML 
		private AnchorPane listLeftSubBackground, roomRightSubBackground, navigationSubBackground;
	
	@FXML 
		private AnchorPane listSubPaneTop;
	
	@FXML 
		private ScrollPane listbot_friend,listbot_room,listbot_message,listbot_search;
	
	@FXML
		private FlowPane messageFlowpane, friendFlowpane, roomFlowpane, searchFlowpane;
	
	@FXML 
		private TextField listtop_searchTextfield;
	
	@FXML 
		private Label listtop_bannerUsername;
	
	@FXML
		private Button listtop_addRoomButton, listtop_addFriendButton;
	
	@FXML 
		private Button userButton,nav_messageButton,nav_friendButton, nav_roomButton, nav_logoutButton;
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->   ATTRIBUTES    <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	
	private ArrayList<ScrollPane> list_scrolls = new ArrayList<>();
	
	private ArrayList<Button> nav_buttons = new ArrayList<Button>();
	
	private static final double WIDTH = 100;

	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->   INITIALIZE    <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		makeReponsive();
		
		searchTextFieldListener();
		
		listLeftSubBackground();
		
		navigationSubBackgroundListener();
		
		Controller.getInstance().setBackgroundController(this);
		
		setButton();
		
		Controller.getInstance().updateUserImage();
		
		Controller.getInstance().updateUserName();
		
		initFriendList();
		
		initRoomList();
	}
	
	/*----------------------------------------------------------------------------------
	*
	*------------------------------------>   MODULES    <-------------------------------
	*
	*-----------------------------------------------------------------------------------*/
	/** Set button for add room, add friend and search text *****************************/
	private void setButton(){
		listtop_addRoomButton.setOnMousePressed(value->{
			Controller.getInstance().showCreateRoom();
		});
		
		listtop_addFriendButton.setOnMousePressed(value->{
			showSearchPane();
		});
	}
	
	/** Initialize friend list to friend flow pane **************************************/
	public void initFriendList(){
		friendFlowpane.getChildren().clear();
		for (int i=0; i<Controller.getInstance().getFriendList().size(); i++)
			Controller.getInstance().addFriendTag(Controller.getInstance().getFriendList().get(i).getId());
	}
	
	/** Make empty all thing*************************************************************/
	private void makeAllEmpty(){
		//TODO
		 messageFlowpane.getChildren().clear();
		 friendFlowpane.getChildren().clear(); 
		 roomFlowpane.getChildren().clear();
		 searchFlowpane.getChildren().clear();
		 roomRightSubBackground.getChildren().clear();
		list_scrolls.clear();
		nav_buttons.clear();
		//
	}
	
	/** Initialize room list to room flow pane ******************************************/
	public void initRoomList(){
		roomFlowpane.getChildren().clear();
//	messageFlowpane.getChildren().clear();
//		roomFlowpane.getChildren().clear();
//		roomRightSubBackground.getChildren().clear();
		for (int i =0; i<Controller.getInstance().getRoomList().size(); i++) {
			RoomController roomController = Controller.getInstance().getRoomControllerById(Controller.getInstance().getRoomList().get(i).getId());
			Platform.runLater(()->addToRoomFlowPane(roomController.getRoomTag()));
			if(roomController.getRoom().getMessages()!=null && roomController.getRoom().getMessages().size()!=0){
				Controller.getInstance().addToCurrentMessageMap(roomController);
				addToMessageFlowPane(roomController.getMessageTag());
				addToRightPane(roomController.getRoomHome());
			}
		}
	}

	/** Make responsive for pane on background ******************************************/
	private void makeReponsive(){
		parentbackgroundPane.widthProperty().addListener(function->{
			double length = parentbackgroundPane.getWidth();
			if(length<=920){
				AnchorPane.setLeftAnchor(listLeftSubBackground, 0.0);
				listLeftSubBackground.setPrefWidth(WIDTH);
				listSubPaneTop.setPrefWidth(WIDTH);
				navigationSubBackground.setVisible(false);
				AnchorPane.setLeftAnchor(roomRightSubBackground,100.0);
				modifiedListSubPaneTop(true);
			}
			else{
				AnchorPane.setLeftAnchor(listLeftSubBackground, 85.0);
				listLeftSubBackground.setPrefWidth(320.0);
				navigationSubBackground.setVisible(true);
				AnchorPane.setLeftAnchor(roomRightSubBackground,320.0+85.0);
				listtop_addFriendButton.setLayoutY(49);
				modifiedListSubPaneTop(false);
			}
		});
	}
	
	/** Search text field is focus or not? **********************************************/
	private int currentIndex=0;
	private boolean flag = true;
	private void searchTextFieldListener(){
		listtop_searchTextfield.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				if (newValue){
		            changeScrollpaneStatus(3);
				}
		        else{
		        	initFriendList();
		        	if(listtop_searchTextfield.getText().trim().length()==0)
		        		changeButtonStatus(currentIndex);
		        }
			}
		});
		listtop_searchTextfield.setOnKeyPressed(value->{
			searchFlowpane.getChildren().clear();
			Platform.runLater(()->{
				Controller.getInstance().addTagToFlowPane(listtop_searchTextfield.getText());
			});
		
		});
	}
	
	/** Show or remove element on the top of left pane ********************************/
	private void modifiedListSubPaneTop(boolean status){
		if(status){
			listSubPaneTop.getChildren().remove(listtop_bannerUsername);
			listSubPaneTop.getChildren().remove(listtop_searchTextfield);
			listtop_addFriendButton.setLayoutY(10.0);
		}
		else
			if(!listSubPaneTop.getChildren().contains(listtop_bannerUsername)){
				listSubPaneTop.getChildren().add(listtop_bannerUsername);
				listSubPaneTop.getChildren().add(listtop_searchTextfield);
				listtop_addFriendButton.setLayoutY(49.0);
			}
	}

	/** Get all scroll pane to a array list *******************************************/
	private void listLeftSubBackground(){
		list_scrolls.add(listbot_message);
		list_scrolls.add(listbot_room);
		list_scrolls.add(listbot_friend);
		list_scrolls.add(listbot_search);
		listbot_friendListener();
	}
	
	/** Show or hide the vertical bar**************************************************/
	private void listbot_friendListener(){
		listbot_message.setOnMouseEntered(event->{
			listbot_message.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		});
		messageFlowpane.setOnMouseExited(event->{
			listbot_message.setVbarPolicy(ScrollBarPolicy.NEVER);
		});
		
		listbot_friend.setOnMouseEntered(event->{
			listbot_friend.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		});
		listbot_friend.setOnMouseExited(event->{
			listbot_friend.setVbarPolicy(ScrollBarPolicy.NEVER);
		});
		
		listbot_room.setOnMouseEntered(event->{
			listbot_room.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		});
		listbot_room.setOnMouseExited(event->{
			listbot_room.setVbarPolicy(ScrollBarPolicy.NEVER);
		});
		
		listbot_search.setOnMouseEntered(event->{
			listbot_search.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		});
		listbot_search.setOnMouseExited(event->{
			listbot_search.setVbarPolicy(ScrollBarPolicy.NEVER);
		});
	}
	
	/** Set action listener for button on the navigation ******************************/
	private void navigationSubBackgroundListener(){
		nav_buttons.add(nav_messageButton);
		
		nav_buttons.add(nav_roomButton);
		
		nav_buttons.add(nav_friendButton);
		
		nav_buttons.add(nav_logoutButton);
		
		nav_messageButton.setStyle("-fx-background-color:white");
		
		userButton.setOnMousePressed(value->{
			Controller.getInstance().showUserInfo();
		});
		
		nav_messageButton.setOnMousePressed(event->{
			currentIndex = 0;
			flag = true;
			changeButtonStatus(0);
		});
	
		nav_roomButton.setOnMousePressed(event -> {
			currentIndex = 1;
			flag = true;
			changeButtonStatus(1);
		});
		nav_friendButton.setOnMousePressed(event -> {
			currentIndex =2;
			flag = true;
			changeButtonStatus(2);
		});
		nav_logoutButton.setOnMousePressed(event -> {
			//TODO
			flag=false;
			changeButtonStatus(3);
			Platform.runLater(()->makeAllEmpty());
			try {
				Thread.sleep(80);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Controller.getInstance().logoutRequest();
			System.exit(0);
			Platform.exit();
		//	Controller.getInstance().openLoginWindows();
		});
	}
	
	/** Exchange the status (background color) of the navigation button****************/
	private void changeButtonStatus(int index){
		for (Button button : nav_buttons)
			button.setStyle("-fx-background-color:transparent");
		nav_buttons.get(index).setStyle("-fx-background-color:white");
		if(flag)
			changeScrollpaneStatus(index);
	}
	
	/** Exchange the scroll pane to show **********************************************/
	private void changeScrollpaneStatus(int index){
		for (ScrollPane scroll : list_scrolls) 
			scroll.setVisible(false);
		list_scrolls.get(index).setVisible(true);
	}
	
	/** Show search pane **************************************************************/
	private SearchFriendController searchFriendController =null;
	private void showSearchPane(){
		try{
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/searchfriend/searchfriend.fxml"));
			Parent parent = loader.load();
			searchFriendController = loader.getController();
			Scene scene = new Scene(parent);
			stage.setTitle("Tìm bạn");
			stage.setScene(scene);
			stage.show();
			stage.setOnCloseRequest(value->{
				searchFriendController = null;
			});
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->   METHODS    <--------------------------------
	*
	*-----------------------------------------------------------------------------------*/
	
	/** Update data(person list) to search pane *****************************************/
	public void upToSearchPane(ArrayList<Person> persons){
		if(searchFriendController!=null)
			searchFriendController.updateData(persons);
	}

	/** Close this windows***************************************************************/
	public void closeWindows(){
		Stage current =	(Stage) parentbackgroundPane.getScene().getWindow(); 
		current.close();
	}
		
	/** Add message tag to flow message pane*********************************************/
	public void addToMessageFlowPane(Parent parent){
		if(!messageFlowpane.getChildren().contains(parent))
		messageFlowpane.getChildren().add(0,parent);
	}
	
	/** Remove message tag to flow message pane******************************************/
	public void removeToMessageFlowPane(Parent parent){
		messageFlowpane.getChildren().remove(parent);
	}
	
	/** Add parent to friend flow pane **************************************************/
	public void addToFriendFlowPane(Parent parent){
		if(!friendFlowpane.getChildren().contains(parent))
		friendFlowpane.getChildren().add(parent);
	}
	
	/** Remove parent t friend flow pane ************************************************/
	public void removeToFriendFlowPane(Parent parent){
		if(friendFlowpane.getChildren().contains(parent))
			friendFlowpane.getChildren().remove(parent);
	}
	
	/** Add parent to right pane ********************************************************/
	public void addToRightPane(Parent parent){
		AnchorPane.setBottomAnchor(parent, 0.0);
		AnchorPane.setTopAnchor(parent, 0.0);
		AnchorPane.setRightAnchor(parent, 0.0);
		AnchorPane.setLeftAnchor(parent, 0.0);
		roomRightSubBackground.getChildren().add(parent);
	} 
	
	/** Remove parent to right pane *****************************************************/
	public void removeToRightPane(Parent parent){
		if(roomRightSubBackground.getChildren().contains(parent))
			roomRightSubBackground.getChildren().remove(parent);
	}

	/** Add parent to room flow pane  ***************************************************/
	public void addToRoomFlowPane(Parent parent) {
		//TODO
		if(!roomFlowpane.getChildren().contains(parent))
		roomFlowpane.getChildren().add(parent);
	}
	
	/** Remove parent to room flow pane  ************************************************/
	public void removeToRoomFlowPane(Parent parent) {
		if(roomFlowpane.getChildren().contains(parent))
			roomFlowpane.getChildren().remove(parent);
	}
	
	/** Add parent to search flow pane **************************************************/
	public void addToSearchFlowPane(Parent parent){
		if(!searchFlowpane.getChildren().contains(parent))
		searchFlowpane.getChildren().add(parent);
	}
	
	/** Change image user button in navigation ******************************************/
	public void changeImageUserButton(ImagePattern pattern){
		Circle circle = new Circle(32, 32, 32);
		circle.setFill(pattern);
		userButton.setGraphic(circle);
	}
	
	/** Update user name ****************************************************************/
	public void changeUserName(String name){
		listtop_bannerUsername.setText(name);
	}
	
	
}
